package net.seichi915.seichi915hubcore.listener

import net.seichi915.seichi915hubcore.Seichi915HubCore
import org.bukkit.{ChatColor, NamespacedKey}
import org.bukkit.boss.{BarColor, BarStyle}
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.{EventHandler, Listener}

class PlayerJoinListener extends Listener {
  @EventHandler
  def onPlayerJoin(event: PlayerJoinEvent): Unit = {
    event.getPlayer.sendMessage("------------------------------")
    event.getPlayer.sendMessage("")
    event.getPlayer.sendMessage(s"${ChatColor.GREEN}seichi915Networkへようこそ!")
    event.getPlayer.sendMessage("")
    event.getPlayer.sendMessage("メニューから遊びたいサーバーを")
    event.getPlayer.sendMessage("選択してください。")
    event.getPlayer.sendMessage("")
    event.getPlayer.sendMessage("------------------------------")
    val bossBar = Seichi915HubCore.instance.getServer
      .createBossBar(new NamespacedKey(Seichi915HubCore.instance,
                                       s"${event.getPlayer.getName}_BossBar"),
                     "Welcome to _",
                     BarColor.WHITE,
                     BarStyle.SOLID)
    bossBar.setProgress(1.0)
    bossBar.addPlayer(event.getPlayer)
    Seichi915HubCore.bossBarMap += event.getPlayer -> bossBar
  }
}
