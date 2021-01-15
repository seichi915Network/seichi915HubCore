package net.seichi915.seichi915hubcore.command

import net.seichi915.seichi915hubcore.Seichi915HubCore
import net.seichi915.seichi915hubcore.util.Implicits._
import org.bukkit.command.{Command, CommandExecutor, CommandSender, TabExecutor}
import org.bukkit.entity.Player

import java.util
import java.util.Collections

class SpawnCommand extends CommandExecutor with TabExecutor {
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
    val worldName = player.getWorld.getName
    if (worldName.equalsIgnoreCase("PvP")) {
      player.sendMessage("PvPワールドでこのコマンドは使用できません。".toErrorMessage)
      return true
    }
    val world =
      Seichi915HubCore.multiverseCore.getMVWorldManager.getMVWorld(worldName)
    player.teleport(world.getSpawnLocation)
    true
  }

  override def onTabComplete(sender: CommandSender,
                             command: Command,
                             alias: String,
                             args: Array[String]): util.List[String] =
    Collections.emptyList()
}
