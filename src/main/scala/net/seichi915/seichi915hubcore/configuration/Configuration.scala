package net.seichi915.seichi915hubcore.configuration

import net.seichi915.seichi915hubcore.Seichi915HubCore
import net.seichi915.seichi915hubcore.server.{Server, ServerIcon}
import org.bukkit.{ChatColor, Material}

import scala.jdk.CollectionConverters._

object Configuration {
  def saveDefaultConfig(): Unit = Seichi915HubCore.instance.saveDefaultConfig()

  def getServers: List[Server] = {
    val config = Seichi915HubCore.instance.getConfig
    config
      .getConfigurationSection("Servers")
      .getKeys(false)
      .asScala
      .map { serverName =>
        val index = config.getInt(s"Servers.$serverName.Index")
        val title =
          s"${ChatColor.WHITE}${ChatColor.translateAlternateColorCodes('&', config.getString(s"Servers.$serverName.Title"))}"
        val description = {
          val stringList =
            config.getStringList(s"Servers.$serverName.Description").asScala
          if (stringList.isEmpty) None
          else
            Some(stringList.toList.map(str =>
              s"${ChatColor.WHITE}${ChatColor.translateAlternateColorCodes('&', str)}"))
        }
        val iconMaterial = Material.getMaterial(
          config.getString(s"Servers.$serverName.Icon.Material"))
        val isIconEnchanted =
          config.getBoolean(s"Servers.$serverName.Icon.Enchanted")
        val serverIcon = ServerIcon(iconMaterial, isIconEnchanted)
        val server = Server(serverName, index, title, description, serverIcon)
        server
      }
      .toList
  }
}
