package net.seichi915.seichi915hubcore

import net.seichi915.seichi915hubcore.listener._
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

object Seichi915HubCore {
  var instance: Seichi915HubCore = _
}

class Seichi915HubCore extends JavaPlugin {
  Seichi915HubCore.instance = this

  override def onEnable(): Unit = {
    Seq(
      new PlayerMoveListener
    ).foreach(Bukkit.getPluginManager.registerEvents(_, this))

    getLogger.info("seichi915HubCoreが有効になりました。")
  }

  override def onDisable(): Unit = {
    getLogger.info("seichi915HubCoreが無効になりました。")
  }
}
