package net.seichi915.seichi915hubcore.menu

import org.bukkit.entity.Player

trait Menu {
  def open(player: Player): Unit
}
