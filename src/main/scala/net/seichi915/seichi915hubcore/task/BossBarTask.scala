package net.seichi915.seichi915hubcore.task

import net.seichi915.seichi915hubcore.Seichi915HubCore
import org.bukkit.boss.{BarColor, BossBar}
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class BossBarTask extends BukkitRunnable {
  override def run(): Unit =
    Seichi915HubCore.bossBarMap.foreach {
      case (_: Player, bossBar: BossBar) =>
        bossBar.getTitle match {
          case "Welcome to _" =>
            bossBar.setTitle("Welcome to s_")
          case "Welcome to s_" =>
            bossBar.setTitle("Welcome to se_")
          case "Welcome to se_" =>
            bossBar.setTitle("Welcome to sei_")
          case "Welcome to sei_" =>
            bossBar.setTitle("Welcome to seic_")
          case "Welcome to seic_" =>
            bossBar.setTitle("Welcome to seich_")
          case "Welcome to seich_" =>
            bossBar.setTitle("Welcome to seichi_")
          case "Welcome to seichi_" =>
            bossBar.setTitle("Welcome to seichi9_")
          case "Welcome to seichi9_" =>
            bossBar.setTitle("Welcome to seichi91_")
          case "Welcome to seichi91_" =>
            bossBar.setTitle("Welcome to seichi915_")
          case "Welcome to seichi915_" =>
            bossBar.setTitle("Welcome to seichi915N_")
          case "Welcome to seichi915N_" =>
            bossBar.setTitle("Welcome to seichi915Ne_")
          case "Welcome to seichi915Ne_" =>
            bossBar.setTitle("Welcome to seichi915Net_")
          case "Welcome to seichi915Net_" =>
            bossBar.setTitle("Welcome to seichi915Netw_")
          case "Welcome to seichi915Netw_" =>
            bossBar.setTitle("Welcome to seichi915Netwo_")
          case "Welcome to seichi915Netwo_" =>
            bossBar.setTitle("Welcome to seichi915Networ_")
          case "Welcome to seichi915Networ_" =>
            bossBar.setTitle("Welcome to seichi915Network_")
          case "Welcome to seichi915Network_" =>
            bossBar.setTitle("Welcome to seichi915Network!")
          case "Welcome to seichi915Network!"
              if bossBar.getColor == BarColor.WHITE =>
            bossBar.setColor(BarColor.BLUE)
          case "Welcome to seichi915Network!"
              if bossBar.getColor == BarColor.BLUE =>
            bossBar.setColor(BarColor.RED)
          case "Welcome to seichi915Network!"
              if bossBar.getColor == BarColor.RED =>
            bossBar.setColor(BarColor.YELLOW)
          case "Welcome to seichi915Network!"
              if bossBar.getColor == BarColor.YELLOW =>
            bossBar.setColor(BarColor.GREEN)
          case "Welcome to seichi915Network!"
              if bossBar.getColor == BarColor.GREEN =>
            bossBar.setColor(BarColor.PINK)
          case "Welcome to seichi915Network!"
              if bossBar.getColor == BarColor.PINK =>
            bossBar.setColor(BarColor.PURPLE)
          case "Welcome to seichi915Network!"
              if bossBar.getColor == BarColor.PURPLE =>
            bossBar.setColor(BarColor.WHITE)
            bossBar.setTitle("Welcome to _")
          case _ =>
        }
    }
}
