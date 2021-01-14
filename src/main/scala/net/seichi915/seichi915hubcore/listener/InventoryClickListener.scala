package net.seichi915.seichi915hubcore.listener

import net.seichi915.seichi915hubcore.Seichi915HubCore
import net.seichi915.seichi915hubcore.util.Implicits._
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.{EventHandler, Listener}
import org.bukkit.persistence.PersistentDataType

import java.util.UUID

class InventoryClickListener extends Listener {
  @EventHandler
  def onInventoryClick(event: InventoryClickEvent): Unit = {
    if (!event.getInventory.isSeichi915HubInventory) return
    if (event.getCurrentItem.isNull) return
    if (!event.getWhoClicked.isInstanceOf[Player]) return
    val uuid = UUID.fromString(
      event.getCurrentItem.getItemMeta.getPersistentDataContainer.get(
        new NamespacedKey(Seichi915HubCore.instance, "click_action"),
        PersistentDataType.STRING))
    val clickAction = Seichi915HubCore.clickActionMap(uuid)
    clickAction.onClick(event.getWhoClicked.asInstanceOf[Player])
    event.setCancelled(true)
  }
}
