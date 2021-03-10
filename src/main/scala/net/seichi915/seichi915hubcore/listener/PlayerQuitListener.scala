package net.seichi915.seichi915hubcore.listener

import net.seichi915.seichi915hubcore.Seichi915HubCore
import net.seichi915.seichi915hubcore.external.ExternalPlugins
import net.seichi915.seichi915hubcore.util.Implicits._
import org.bukkit.{Material, NamespacedKey}
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.{EventHandler, Listener}

class PlayerQuitListener extends Listener {
  @EventHandler
  def onPlayerQuit(event: PlayerQuitEvent): Unit = {
    if (Seichi915HubCore.mainWorldInventories.contains(event.getPlayer)) {
      event.getPlayer.getInventory.clear()
      val inventory = Seichi915HubCore.mainWorldInventories(event.getPlayer)
      (0 to 35).foreach { index =>
        val item = inventory.getStorageContents.apply(index)
        if (item.nonNull && item.getType != Material.AIR)
          event.getPlayer.getInventory.setItem(index, item)
      }
      event.getPlayer.getInventory
        .setHelmet(Seichi915HubCore.mainWorldHelmets(event.getPlayer))
      event.getPlayer.getInventory
        .setChestplate(Seichi915HubCore.mainWorldChestplates(event.getPlayer))
      event.getPlayer.getInventory
        .setLeggings(Seichi915HubCore.mainWorldLeggings(event.getPlayer))
      event.getPlayer.getInventory
        .setBoots(Seichi915HubCore.mainWorldBoots(event.getPlayer))
      event.getPlayer.getInventory.setItemInOffHand(
        Seichi915HubCore.mainWorldItemInOffHand(event.getPlayer))
      Seichi915HubCore.mainWorldInventories.remove(event.getPlayer)
      Seichi915HubCore.mainWorldHelmets.remove(event.getPlayer)
      Seichi915HubCore.mainWorldChestplates.remove(event.getPlayer)
      Seichi915HubCore.mainWorldLeggings.remove(event.getPlayer)
      Seichi915HubCore.mainWorldBoots.remove(event.getPlayer)
      Seichi915HubCore.mainWorldItemInOffHand.remove(event.getPlayer)
    }
    val mainWorld =
      ExternalPlugins.getMultiverseCore.getMVWorldManager.getMVWorld("Hub")
    event.getPlayer.teleport(mainWorld.getSpawnLocation)
    Seichi915HubCore.bossBarMap.get(event.getPlayer) match {
      case Some(bossBar) =>
        bossBar.removeAll()
        Seichi915HubCore.instance.getServer.removeBossBar(
          new NamespacedKey(Seichi915HubCore.instance,
                            s"${event.getPlayer.getName}_BossBar"))
        Seichi915HubCore.bossBarMap.remove(event.getPlayer)
      case None =>
    }
  }
}
