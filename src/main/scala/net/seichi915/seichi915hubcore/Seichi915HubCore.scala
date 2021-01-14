package net.seichi915.seichi915hubcore

import com.onarandombox.MultiverseCore.MultiverseCore
import net.seichi915.seichi915hubcore.command._
import net.seichi915.seichi915hubcore.configuration.Configuration
import net.seichi915.seichi915hubcore.listener._
import net.seichi915.seichi915hubcore.menu.ClickAction
import net.seichi915.seichi915hubcore.task._
import org.bukkit.Bukkit
import org.bukkit.boss.BossBar
import org.bukkit.command.{CommandExecutor, TabExecutor}
import org.bukkit.entity.Player
import org.bukkit.inventory.{Inventory, ItemStack}
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

import java.util.UUID
import scala.collection.mutable
import scala.jdk.CollectionConverters._

object Seichi915HubCore {
  var instance: Seichi915HubCore = _
  var multiverseCore: MultiverseCore = _

  var bossBarMap: mutable.HashMap[Player, BossBar] = mutable.HashMap()
  var noFallEntities: mutable.HashMap[Int, Int] = mutable.HashMap()
  var mainWorldInventories: mutable.HashMap[Player, Inventory] =
    mutable.HashMap()
  var mainWorldHelmets: mutable.HashMap[Player, ItemStack] = mutable.HashMap()
  var mainWorldChestplates: mutable.HashMap[Player, ItemStack] =
    mutable.HashMap()
  var mainWorldLeggings: mutable.HashMap[Player, ItemStack] = mutable.HashMap()
  var mainWorldBoots: mutable.HashMap[Player, ItemStack] = mutable.HashMap()
  var mainWorldItemInOffHand: mutable.HashMap[Player, ItemStack] =
    mutable.HashMap()
  var clickActionMap: mutable.HashMap[UUID, ClickAction] = mutable.HashMap()
}

class Seichi915HubCore extends JavaPlugin {
  Seichi915HubCore.instance = this

  override def onEnable(): Unit = {
    Configuration.saveDefaultConfig()
    Seq(
      new EntityDamageByEntityListener,
      new InventoryClickListener,
      new PlayerFishListener,
      new PlayerInteractListener,
      new PlayerItemConsumeListener,
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
    Map(
      "admins" -> new AdminsCommand,
      "main" -> new MainCommand,
      "menu" -> new MenuCommand,
      "pvp" -> new PvPCommand
    ).foreach {
      case (commandName: String, commandExecutor: CommandExecutor) =>
        Bukkit.getPluginCommand(commandName).setExecutor(commandExecutor)
        Bukkit
          .getPluginCommand(commandName)
          .setTabCompleter(commandExecutor.asInstanceOf[TabExecutor])
    }
    Seichi915HubCore.multiverseCore = getServer.getPluginManager
      .getPlugin("Multiverse-Core")
      .asInstanceOf[MultiverseCore]
    getServer.getMessenger
      .registerOutgoingPluginChannel(Seichi915HubCore.instance, "BungeeCord")

    getLogger.info("seichi915HubCoreが有効になりました。")
  }

  override def onDisable(): Unit = {
    getServer.getOnlinePlayers.asScala.foreach(_.chat("/main"))

    getLogger.info("seichi915HubCoreが無効になりました。")
  }
}
