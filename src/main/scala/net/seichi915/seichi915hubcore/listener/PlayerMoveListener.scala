package net.seichi915.seichi915hubcore.listener

import net.seichi915.seichi915hubcore.external.ExternalPlugins
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.{EventHandler, Listener}

class PlayerMoveListener extends Listener {
  @EventHandler
  def onPlayerMove(event: PlayerMoveEvent): Unit =
    event.getTo.getWorld.getName match {
      case "Hub" if event.getTo.getBlockY <= 0 =>
        val mainWorld =
          ExternalPlugins.getMultiverseCore.getMVWorldManager.getMVWorld("Hub")
        event.getPlayer.teleport(mainWorld.getSpawnLocation)
      case _ =>
    }
}
