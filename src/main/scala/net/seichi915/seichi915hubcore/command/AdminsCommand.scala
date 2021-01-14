package net.seichi915.seichi915hubcore.command

import net.seichi915.seichi915hubcore.Seichi915HubCore
import net.seichi915.seichi915hubcore.util.Implicits._
import org.bukkit.Material
import org.bukkit.command.{Command, CommandExecutor, CommandSender, TabExecutor}
import org.bukkit.entity.Player

import java.util
import java.util.Collections

class AdminsCommand extends CommandExecutor with TabExecutor {
  override def onCommand(sender: CommandSender,
                         command: Command,
                         label: String,
                         args: Array[String]): Boolean = {
    if (!sender.isInstanceOf[Player]) {
      sender.sendMessage("このコマンドはプレイヤーのみが実行できます。".toErrorMessage)
      return true
    }
    if (args.nonEmpty) {
      sender.sendMessage("コマンドの使用法が間違っています。".toErrorMessage)
      return true
    }
    val player = sender.asInstanceOf[Player]
    if (Seichi915HubCore.mainWorldInventories.contains(player)) {
      player.getInventory.clear()
      val inventory = Seichi915HubCore.mainWorldInventories(player)
      (0 to 35).foreach { index =>
        val item = inventory.getStorageContents.apply(index)
        if (item.nonNull && item.getType != Material.AIR)
          player.getInventory.setItem(index, item)
      }
      player.getInventory.setHelmet(Seichi915HubCore.mainWorldHelmets(player))
      player.getInventory.setChestplate(
        Seichi915HubCore.mainWorldChestplates(player))
      player.getInventory.setLeggings(
        Seichi915HubCore.mainWorldLeggings(player))
      player.getInventory.setBoots(Seichi915HubCore.mainWorldBoots(player))
      player.getInventory.setItemInOffHand(
        Seichi915HubCore.mainWorldItemInOffHand(player))
      Seichi915HubCore.mainWorldInventories.remove(player)
      Seichi915HubCore.mainWorldHelmets.remove(player)
      Seichi915HubCore.mainWorldChestplates.remove(player)
      Seichi915HubCore.mainWorldLeggings.remove(player)
      Seichi915HubCore.mainWorldBoots.remove(player)
      Seichi915HubCore.mainWorldItemInOffHand.remove(player)
    }
    val adminIntroductionWorld =
      Seichi915HubCore.multiverseCore.getMVWorldManager
        .getMVWorld("Administrators")
    player.teleport(adminIntroductionWorld.getSpawnLocation)
    true
  }

  override def onTabComplete(sender: CommandSender,
                             command: Command,
                             alias: String,
                             args: Array[String]): util.List[String] =
    Collections.emptyList()
}
