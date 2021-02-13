package net.seichi915.seichi915hubcore.server

import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.{ItemFlag, ItemStack}

import scala.jdk.CollectionConverters._

case class Server(serverName: String,
                  title: String,
                  description: Option[List[String]],
                  serverIcon: ServerIcon) {
  def getServerName: String = serverName

  def getTitle: String = title

  def getDescription: Option[List[String]] = description

  def getServerIcon: ServerIcon = serverIcon

  def toItemStack: ItemStack = {
    val itemStack = new ItemStack(serverIcon.getMaterial)
    val itemMeta = itemStack.getItemMeta
    itemMeta.setDisplayName(title)
    description match {
      case Some(value) =>
        itemMeta.setLore(value.asJava)
      case None =>
    }
    if (serverIcon.isEnchanted)
      itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
    itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
    itemStack.setItemMeta(itemMeta)
    if (serverIcon.isEnchanted)
      itemStack.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1)
    itemStack
  }
}
