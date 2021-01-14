package net.seichi915.seichi915hubcore.inventory

import org.bukkit.inventory.{Inventory, InventoryHolder}

object Seichi915HubInventoryHolder {
  val seichi915HubInventoryHolder: Seichi915HubInventoryHolder =
    new Seichi915HubInventoryHolder {
      override def getInventory: Inventory = null
    }
}

trait Seichi915HubInventoryHolder extends InventoryHolder
