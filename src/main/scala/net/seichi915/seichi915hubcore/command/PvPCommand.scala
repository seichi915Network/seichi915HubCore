package net.seichi915.seichi915hubcore.command

import net.seichi915.seichi915hubcore.Seichi915HubCore
import net.seichi915.seichi915hubcore.util.Implicits._
import org.bukkit.command.{Command, CommandExecutor, CommandSender, TabExecutor}
import org.bukkit.entity.Player

import java.util
import java.util.Collections

class PvPCommand extends CommandExecutor with TabExecutor {
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
    if (player.getWorld.getName.equalsIgnoreCase("Hub") || player.getWorld.getName
          .equalsIgnoreCase("Administrators")) {
      Seichi915HubCore.mainWorldInventories += player -> player.getInventory.cloneInventory
      Seichi915HubCore.mainWorldHelmets += player -> player.getInventory.getHelmetSafety
        .clone()
      Seichi915HubCore.mainWorldChestplates += player -> player.getInventory.getChestplateSafety
        .clone()
      Seichi915HubCore.mainWorldLeggings += player -> player.getInventory.getLeggingsSafety
        .clone()
      Seichi915HubCore.mainWorldBoots += player -> player.getInventory.getBootsSafety
        .clone()
      Seichi915HubCore.mainWorldItemInOffHand += player -> player.getInventory.getItemInOffHandSafety
        .clone()
    }
    player.getInventory.clear()
    val pvpWorld =
      Seichi915HubCore.multiverseCore.getMVWorldManager.getMVWorld("PvP")
    player.teleport(pvpWorld.getSpawnLocation)
    true
  }

  override def onTabComplete(sender: CommandSender,
                             command: Command,
                             alias: String,
                             args: Array[String]): util.List[String] =
    Collections.emptyList()
}
