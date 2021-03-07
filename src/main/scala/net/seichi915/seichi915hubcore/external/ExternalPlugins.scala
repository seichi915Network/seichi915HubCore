package net.seichi915.seichi915hubcore.external

import com.onarandombox.MultiverseCore.MultiverseCore
import org.bukkit.Bukkit

object ExternalPlugins {
  def getMultiverseCore: MultiverseCore =
    Bukkit.getPluginManager
      .getPlugin("Multiverse-Core")
      .asInstanceOf[MultiverseCore]
}
