package net.seichi915.seichi915hubcore.listener

import net.seichi915.seichi915hubcore.Seichi915HubCore
import org.bukkit.NamespacedKey
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.{EventHandler, Listener}

class PlayerQuitListener extends Listener {
  @EventHandler
  def onPlayerQuit(event: PlayerQuitEvent): Unit =
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
