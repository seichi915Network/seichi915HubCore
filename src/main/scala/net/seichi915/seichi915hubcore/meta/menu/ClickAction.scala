package net.seichi915.seichi915hubcore.meta.menu

import org.bukkit.entity.Player

trait ClickAction {
  def onClick(player: Player): Unit
}
