package net.seichi915.seichi915hubcore.listener

import net.seichi915.seichi915hubcore.Seichi915HubCore
import net.seichi915.seichi915hubcore.util.Implicits._
import org.bukkit.{ChatColor, Material, NamespacedKey}
import org.bukkit.boss.{BarColor, BarStyle}
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.{EventHandler, Listener}
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

import scala.jdk.CollectionConverters._

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
    val itemStack = new ItemStack(Material.NETHER_STAR)
    val itemMeta = itemStack.getItemMeta
    itemMeta.setDisplayName(s"${ChatColor.AQUA}メニューを開く")
    itemMeta.setLore(
      List(s"${ChatColor.WHITE}右クリックでメニューを", s"${ChatColor.WHITE}開けます。").asJava)
    itemMeta.getPersistentDataContainer.set(
      new NamespacedKey(Seichi915HubCore.instance, "right_click_to_open_menu"),
      PersistentDataType.STRING,
      "")
    itemStack.setItemMeta(itemMeta)
    if (!event.getPlayer.getInventory.contains(itemStack))
      event.getPlayer.giveItemSafety(itemStack)
    Seichi915HubCore.bossBarMap += event.getPlayer -> bossBar
  }
}
