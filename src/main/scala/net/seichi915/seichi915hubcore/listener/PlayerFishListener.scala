package net.seichi915.seichi915hubcore.listener

import net.seichi915.seichi915hubcore.util.Implicits._
import net.seichi915.seichi915hubcore.util.Util
import org.bukkit.ChatColor
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.event.{EventHandler, EventPriority, Listener}

class PlayerFishListener extends Listener {
  @EventHandler(priority = EventPriority.HIGHEST)
  def onFish(event: PlayerFishEvent): Unit = {
    if (!event.getPlayer.getWorld.getName.equalsIgnoreCase("Hub")) return
    if (event.getPlayer.getInventory.getItemInMainHand.isNull) return
    if (event.getPlayer.getInventory.getItemInMainHand.getItemMeta.isNull)
      return
    if (event.getPlayer.getInventory.getItemInMainHand.getItemMeta.getDisplayName.isNull)
      return
    if (!event.getPlayer.getInventory.getItemInMainHand.getItemMeta.getDisplayName
          .equalsIgnoreCase(s"${ChatColor.AQUA}フックショット")) return
    event.getState match {
      case PlayerFishEvent.State.IN_GROUND =>
        val hookLocation = event.getHook.getLocation
        Util.pullEntityToLocation(event.getPlayer, hookLocation)
      case PlayerFishEvent.State.FAILED_ATTEMPT =>
        val hookLocation = event.getHook.getLocation
        Util.pullEntityToLocation(event.getPlayer, hookLocation)
      case PlayerFishEvent.State.CAUGHT_ENTITY =>
        Util.pullEntityToLocation(event.getPlayer, event.getCaught.getLocation)
      case _ =>
    }
  }
}
