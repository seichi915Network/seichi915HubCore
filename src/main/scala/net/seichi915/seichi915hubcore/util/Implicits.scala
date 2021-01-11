package net.seichi915.seichi915hubcore.util

import org.bukkit.{Bukkit, ChatColor, Material}
import org.bukkit.inventory.{Inventory, ItemStack, PlayerInventory}

object Implicits {
  implicit class AnyOps(any: Any) {
    def isNull: Boolean = Option(any).flatMap(_ => Some(false)).getOrElse(true)

    def nonNull: Boolean = !isNull
  }

  implicit class StringOps(string: String) {
    def toNormalMessage: String =
      s"${ChatColor.AQUA}[${ChatColor.WHITE}seichi915Hub${ChatColor.AQUA}]${ChatColor.RESET} $string"

    def toSuccessMessage: String =
      s"${ChatColor.AQUA}[${ChatColor.GREEN}seichi915Hub${ChatColor.AQUA}]${ChatColor.RESET} $string"

    def toWarningMessage: String =
      s"${ChatColor.AQUA}[${ChatColor.GOLD}seichi915Hub${ChatColor.AQUA}]${ChatColor.RESET} $string"

    def toErrorMessage: String =
      s"${ChatColor.AQUA}[${ChatColor.RED}seichi915Hub${ChatColor.AQUA}]${ChatColor.RESET} $string"
  }

  implicit class PlayerInventoryOps(playerInventory: PlayerInventory) {
    def getHelmetSafety: ItemStack = {
      val helmet = playerInventory.getHelmet
      if (helmet.isNull) return new ItemStack(Material.AIR)
      helmet
    }

    def getChestplateSafety: ItemStack = {
      val chestplate = playerInventory.getChestplate
      if (chestplate.isNull) return new ItemStack(Material.AIR)
      chestplate
    }

    def getLeggingsSafety: ItemStack = {
      val leggings = playerInventory.getLeggings
      if (leggings.isNull) return new ItemStack(Material.AIR)
      leggings
    }

    def getBootsSafety: ItemStack = {
      val boots = playerInventory.getBoots
      if (boots.isNull) return new ItemStack(Material.AIR)
      boots
    }

    def getItemInOffHandSafety: ItemStack = {
      val itemInOffHand = playerInventory.getItemInOffHand
      if (itemInOffHand.isNull) return new ItemStack(Material.AIR)
      itemInOffHand
    }
  }

  implicit class InventoryOps(inventory: Inventory) {
    def cloneInventory: Inventory = {
      val inventory = Bukkit
        .createInventory(null, 36, "")
      (0 to 35).foreach { index =>
        val item = this.inventory.getStorageContents.apply(index)
        if (item.nonNull && item.getType != Material.AIR)
          inventory.setItem(index, item)
      }
      inventory
    }
  }
}
