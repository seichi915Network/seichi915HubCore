package net.seichi915.seichi915hubcore.listener

import org.bukkit.event.player.PlayerTakeLecternBookEvent
import org.bukkit.event.{EventHandler, Listener}

class PlayerTakeLecternBookListener extends Listener {
  @EventHandler
  def onPlayerTakeLecternBook(event: PlayerTakeLecternBookEvent): Unit =
    if (!event.getPlayer.isOp) event.setCancelled(true)
}
