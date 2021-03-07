package net.seichi915.seichi915hubcore.listener

import net.seichi915.seichi915hubcore.external.ExternalPlugins
import org.bukkit.entity.{Arrow, Player}
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.{EventHandler, Listener}

import scala.jdk.CollectionConverters._

class EntityDamageByEntityListener extends Listener {
  @EventHandler
  def onEntityDamageByEntity(event: EntityDamageByEntityEvent): Unit = {
    if (!event.getEntity.getWorld.getName.equalsIgnoreCase("PvP")) return
    if (!event.getEntity.isInstanceOf[Player]) return
    if (event.getEntity.getName.equalsIgnoreCase(event.getDamager.getName)) {
      event.getEntity.asInstanceOf[Player].sendActionBar("自分を攻撃することはできません。")
      event.setCancelled(true)
      return
    }
    event.getDamager match {
      case arrow: Arrow if arrow.getShooter.isInstanceOf[Player] =>
        if (arrow.getShooter
              .asInstanceOf[Player]
              .getName
              .equalsIgnoreCase(event.getEntity.getName)) {
          event.getEntity.asInstanceOf[Player].sendActionBar("自分を攻撃することはできません。")
          event.setCancelled(true)
          return
        }
      case _ =>
    }
    val player = event.getEntity.asInstanceOf[Player]
    if (player.getHealth - event.getFinalDamage > 0) return
    event.setCancelled(true)
    event.getDamager match {
      case arrow: Arrow if arrow.getShooter.isInstanceOf[Player] =>
        player.sendActionBar(
          s"${arrow.getShooter.asInstanceOf[Player].getName}さんによって倒されました。")
        arrow.getShooter
          .asInstanceOf[Player]
          .sendActionBar(s"${player.getName}さんを倒しました。")
      case damager: Player =>
        player.sendActionBar(s"${damager.getName}さんによって倒されました。")
        damager.sendActionBar(s"${player.getName}さんを倒しました。")
      case _ =>
    }
    val pvpWorld =
      ExternalPlugins.getMultiverseCore.getMVWorldManager.getMVWorld("PvP")
    player.teleport(pvpWorld.getSpawnLocation)
    player.setHealth(20)
    player.setFoodLevel(20)
    player.setFireTicks(0)
    player.getActivePotionEffects.asScala
      .map(_.getType)
      .foreach(player.removePotionEffect)
  }
}
