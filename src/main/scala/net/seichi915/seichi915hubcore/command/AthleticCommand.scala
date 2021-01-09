package net.seichi915.seichi915hubcore.command

import net.seichi915.seichi915hubcore.Seichi915HubCore
import net.seichi915.seichi915hubcore.util.Implicits._
import org.bukkit.command.{Command, CommandExecutor, CommandSender, TabExecutor}
import org.bukkit.entity.Player

import java.util
import java.util.Collections

class AthleticCommand extends CommandExecutor with TabExecutor {
  override def onCommand(sender: CommandSender,
                         command: Command,
                         label: String,
                         args: Array[String]): Boolean = {
    if (!sender.isInstanceOf[Player]) {
      sender.sendMessage("このコマンドはプレイヤーのみが実行できます。".toErrorMessage)
      return true
    }
    if (args.length != 0) {
      sender.sendMessage("コマンドの使用法が間違っています。".toErrorMessage)
      return true
    }
    val athleticWorld =
      Seichi915HubCore.multiverseCore.getMVWorldManager.getMVWorld("Athletic")
    sender.asInstanceOf[Player].teleport(athleticWorld.getSpawnLocation)
    true
  }

  override def onTabComplete(sender: CommandSender,
                             command: Command,
                             alias: String,
                             args: Array[String]): util.List[String] =
    Collections.emptyList()
}
