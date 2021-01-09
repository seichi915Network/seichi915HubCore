package net.seichi915.seichi915hubcore

import net.seichi915.seichi915hubcore.listener._
import net.seichi915.seichi915hubcore.task._
import org.bukkit.Bukkit
import org.bukkit.boss.BossBar
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

import scala.collection.mutable

object Seichi915HubCore {
  var instance: Seichi915HubCore = _

  var bossBarMap: mutable.HashMap[Player, BossBar] = mutable.HashMap()
  var noFallEntities: mutable.HashMap[Int, Int] = mutable.HashMap()
}

class Seichi915HubCore extends JavaPlugin {
  Seichi915HubCore.instance = this

  override def onEnable(): Unit = {
    Seq(
      new PlayerFishListener,
      new PlayerJoinListener,
      new PlayerMoveListener,
      new PlayerQuitListener
    ).foreach(Bukkit.getPluginManager.registerEvents(_, this))
    Map(
      (4, 4) -> new BossBarTask
    ).foreach {
      case ((delay: Int, period: Int), bukkitRunnable: BukkitRunnable) =>
        bukkitRunnable.runTaskTimer(this, delay, period)
    }

    getLogger.info("seichi915HubCoreが有効になりました。")
  }

  override def onDisable(): Unit = {
    getLogger.info("seichi915HubCoreが無効になりました。")
  }
}
