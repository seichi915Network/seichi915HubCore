package net.seichi915.seichi915hubcore.menu

import net.seichi915.seichi915hubcore.configuration.Configuration
import net.seichi915.seichi915hubcore.inventory.Seichi915HubInventoryHolder
import net.seichi915.seichi915hubcore.meta.menu.Menu
import net.seichi915.seichi915hubcore.util.Implicits._
import org.bukkit.{Bukkit, ChatColor, Material}
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

object MainMenu extends Menu {
  override def open(player: Player): Unit = {
    val inventory = Bukkit.createInventory(
      Seichi915HubInventoryHolder.seichi915HubInventoryHolder,
      54,
      "seichi915Hubメニュー")
    Configuration.getServers.foreach { server =>
      val itemStack = server.toItemStack
      itemStack.setClickAction(_.connectToServer(server.getServerName))
      inventory.setItem(server.getIndex, itemStack)
    }
    val closeButton = new ItemStack(Material.BARRIER)
    val closeButtonMeta = closeButton.getItemMeta
    closeButtonMeta.setDisplayName(s"${ChatColor.RED}閉じる")
    closeButton.setItemMeta(closeButtonMeta)
    closeButton.setClickAction((player: Player) => player.closeInventory())
    inventory.setItem(53, closeButton)
    player.openInventory(inventory)
  }
}
