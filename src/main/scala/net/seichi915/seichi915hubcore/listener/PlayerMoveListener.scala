package net.seichi915.seichi915hubcore.listener

import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.{EventHandler, Listener}

class PlayerMoveListener extends Listener {
  @EventHandler
  def onPlayerMove(event: PlayerMoveEvent): Unit =
    event.getTo.getWorld.getName match {
      case "Hub" if event.getTo.getBlockY <= 0 =>
        event.getPlayer.teleport(
          event.getTo.getWorld.getSpawnLocation.add(0, 2, 0))
      case _ =>
    }
}
