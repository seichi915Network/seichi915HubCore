package net.seichi915.seichi915hubcore.listener

import net.seichi915.seichi915hubcore.Seichi915HubCore
import net.seichi915.seichi915hubcore.util.Implicits._
import org.bukkit.{Material, NamespacedKey}
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.{EventHandler, Listener}
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.persistence.PersistentDataType

class PlayerInteractListener extends Listener {
  @EventHandler
  def onPlayerInteract(event: PlayerInteractEvent): Unit = {
    if (event.getAction != Action.RIGHT_CLICK_AIR && event.getAction != Action.RIGHT_CLICK_BLOCK)
      return
    if (event.getHand == EquipmentSlot.OFF_HAND) return
    if (event.getItem.isNull || event.getItem.getType != Material.NETHER_STAR)
      return
    if (!event.getItem.getItemMeta.getPersistentDataContainer.has(
          new NamespacedKey(Seichi915HubCore.instance,
                            "right_click_to_open_menu"),
          PersistentDataType.STRING)) return
    event.getPlayer.chat("/menu")
  }
}
