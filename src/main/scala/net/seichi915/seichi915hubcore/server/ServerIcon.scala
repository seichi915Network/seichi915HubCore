package net.seichi915.seichi915hubcore.server

import org.bukkit.Material

case class ServerIcon(material: Material, enchanted: Boolean) {
  def getMaterial: Material = material

  def isEnchanted: Boolean = enchanted
}
