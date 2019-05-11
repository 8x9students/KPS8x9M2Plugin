package jp.kps8x9.middle2.kps8x9m2gameplugin.MHcommand;

import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import static jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin.*;

public class MH_start {
    public int wave = 1;
    public MH_start(World w, int wave_) {
        this.wave = wave_;
        try {
            nexus = (LivingEntity) w.spawnEntity(new Location(w, config.getDouble("NexusLocation.x"), config.getDouble("NexusLocation.y"), config.getDouble("NexusLocation.z")), EntityType.CREEPER);
            nexus.setAI(false);
            nexus.setMaxHealth(nexushp);
            nexus.setHealth(nexushp);
        }catch(Exception e){
            e.printStackTrace();
            return;
        }

        ItemStack sword = new ItemStack(Material.IRON_SWORD);
        ItemStack shield = new ItemStack(Material.SHIELD);
        ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE, 3);
        ItemStack food = new ItemStack(Material.COOKED_BEEF, 64);
        ItemStack plank = new ItemStack(Material.OAK_PLANKS, 64);
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack leggins = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
        //アイテムの設定
        for (Player player : Bukkit.getOnlinePlayers()) {
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

        bossbar=Bukkit.createBossBar(ChatColor.RED+"NEXUS HP", BarColor.PURPLE, BarStyle.SOLID);
        bossbar.setProgress(1.0);
        Bukkit.getOnlinePlayers().forEach(p->bossbar.addPlayer(p));
    }
}
