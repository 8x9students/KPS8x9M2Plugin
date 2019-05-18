package jp.kps8x9.middle2.kps8x9m2gameplugin.util;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;



public class GameStart implements CommandExecutor {
    ItemStack sword = new ItemStack(Material.IRON_SzWORD);
    ItemStack shield = new ItemStack(Material.SHIELD);
    ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE,3);
    ItemStack food = new ItemStack(Material.COOKED_BEEF,64);
    ItemStack plank = new ItemStack(Material.OAK_PLANKS,64);
    ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
    ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
    ItemStack leggins = new ItemStack(Material.LEATHER_LEGGINGS);
    ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
    //アイテムの設定
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if (player.getGameMode() == GameMode.SURVIVAL) {
                PlayerInventory inventory = player.getInventory();
                inventory.clear();//インベントリの初期化
                inventory.setItem(0, sword);
                inventory.setItemInOffHand(shield);
                inventory.setItem(1, gapple);
                inventory.setItem(2, food);
                inventory.setItem(3, plank);
                inventory.setItem(4, plank);
                inventory.setHelmet(helmet);
                inventory.setChestplate(chestplate);
                inventory.setLeggings(leggins);
                inventory.setBoots(boots);
            }
            player.updateInventory();
        }
        return true;
    }
}
