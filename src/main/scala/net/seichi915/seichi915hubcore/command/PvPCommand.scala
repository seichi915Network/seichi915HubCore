package net.seichi915.seichi915hubcore.command

import net.seichi915.seichi915hubcore.Seichi915HubCore
import net.seichi915.seichi915hubcore.util.Implicits._
import org.bukkit.Material
import org.bukkit.command.{Command, CommandExecutor, CommandSender, TabExecutor}
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.{ItemFlag, ItemStack}

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
    if (args.length != 0) {
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
    val sword = new ItemStack(Material.IRON_SWORD)
    val swordMeta = sword.getItemMeta
    swordMeta.setUnbreakable(true)
    swordMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS)
    sword.setItemMeta(swordMeta)
    sword.addEnchantment(Enchantment.KNOCKBACK, 2)
    val bow = new ItemStack(Material.BOW)
    val bowMeta = bow.getItemMeta
    bowMeta.setUnbreakable(true)
    bowMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS)
    bow.setItemMeta(bowMeta)
    bow.addEnchantment(Enchantment.ARROW_INFINITE, 1)
    val arrow = new ItemStack(Material.ARROW)
    val food = new ItemStack(Material.COOKED_BEEF)
    val helmet = new ItemStack(Material.IRON_HELMET)
    val helmetMeta = helmet.getItemMeta
    helmetMeta.setUnbreakable(true)
    helmetMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
    helmet.setItemMeta(helmetMeta)
    val chestplate = new ItemStack(Material.IRON_CHESTPLATE)
    val chestplateMeta = chestplate.getItemMeta
    chestplateMeta.setUnbreakable(true)
    chestplateMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
    chestplate.setItemMeta(chestplateMeta)
    val leggings = new ItemStack(Material.IRON_LEGGINGS)
    val leggingsMeta = leggings.getItemMeta
    leggingsMeta.setUnbreakable(true)
    leggingsMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
    leggings.setItemMeta(leggingsMeta)
    val boots = new ItemStack(Material.IRON_BOOTS)
    val bootsMeta = boots.getItemMeta
    bootsMeta.setUnbreakable(true)
    bootsMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
    boots.setItemMeta(bootsMeta)
    player.getInventory.setItem(0, sword)
    player.getInventory.setItem(1, bow)
    player.getInventory.setItem(17, arrow)
    player.getInventory.setItem(2, food)
    player.getInventory.setHelmet(helmet)
    player.getInventory.setChestplate(chestplate)
    player.getInventory.setLeggings(leggings)
    player.getInventory.setBoots(boots)
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
