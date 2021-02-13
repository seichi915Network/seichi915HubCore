package net.seichi915.seichi915hubcore.util

import net.seichi915.seichi915hubcore.Seichi915HubCore
import net.seichi915.seichi915hubcore.inventory.Seichi915HubInventoryHolder
import net.seichi915.seichi915hubcore.meta.menu.ClickAction
import org.bukkit.entity.Player
import org.bukkit.{Bukkit, ChatColor, Material, NamespacedKey}
import org.bukkit.inventory.{Inventory, ItemStack, PlayerInventory}
import org.bukkit.persistence.PersistentDataType

import java.io.{ByteArrayOutputStream, DataOutputStream}
import java.util.UUID
import scala.jdk.CollectionConverters._

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

    def isSeichi915HubInventory: Boolean =
      inventory.getHolder.nonNull && inventory.getHolder
        .isInstanceOf[Seichi915HubInventoryHolder]
  }

  implicit class ItemStackOps(itemStack: ItemStack) {
    def setClickAction(clickAction: ClickAction): Unit = {
      val itemMeta = itemStack.getItemMeta
      val uuid = UUID.randomUUID()
      itemMeta.getPersistentDataContainer.set(
        new NamespacedKey(Seichi915HubCore.instance, "click_action"),
        PersistentDataType.STRING,
        uuid.toString)
      itemStack.setItemMeta(itemMeta)
      Seichi915HubCore.clickActionMap += uuid -> clickAction
    }
  }

  implicit class PlayerOps(player: Player) {
    def connectToServer(name: String): Unit = {
      val byteArrayOutputStream = new ByteArrayOutputStream()
      val dataOutputStream = new DataOutputStream(byteArrayOutputStream)
      dataOutputStream.writeUTF("Connect")
      dataOutputStream.writeUTF(name)
      player.sendPluginMessage(Seichi915HubCore.instance,
                               "BungeeCord",
                               byteArrayOutputStream.toByteArray)
      dataOutputStream.close()
      byteArrayOutputStream.close()
    }

    def giveItemSafety(itemStacks: ItemStack*): Unit = {
      player.getInventory
        .addItem(itemStacks: _*)
        .values()
        .asScala
        .filter(_.getType != Material.AIR)
        .foreach(player.getWorld.dropItemNaturally(player.getLocation, _))
    }
  }
}
