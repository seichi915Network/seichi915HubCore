package net.seichi915.seichi915hubcore.listener

import org.bukkit.Material
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.event.{EventHandler, Listener}
import org.bukkit.inventory.ItemStack

class PlayerItemConsumeListener extends Listener {
  @EventHandler
  def onPlayerItemConsume(event: PlayerItemConsumeEvent): Unit = {
    if (!event.getPlayer.getWorld.getName.equalsIgnoreCase("PvP")) return
    if (event.getItem.getType != Material.BREAD && event.getItem.getType != Material.POTION)
      return
    val item = event.getItem.clone()
    item.setAmount(1)
    event.setReplacement(item)
  }
}
