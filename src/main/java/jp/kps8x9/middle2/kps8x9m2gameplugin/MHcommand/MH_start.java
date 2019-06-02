package jp.kps8x9.middle2.kps8x9m2gameplugin.MHcommand;

import jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin;
import jp.kps8x9.middle2.kps8x9m2gameplugin.MHCommand;
import jp.kps8x9.commons.util.ClassUtil;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.MHGame;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.ShopItem;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.ShopKeeper;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import static jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin.*;

public class MH_start {
    private final KPS8x9M2gamePlugin plg;
    private final MHCommand cmd;
    private final MHGame mhGame;

    public MH_start(KPS8x9M2gamePlugin plg,MHCommand cmd) {
        this.plg=plg;
        this.cmd=cmd;
        mhGame=MHGame.getInstance();
        mhGame.second=config.getInt("NexusTimer.Second")+config.getInt("NexusTimer.Minute")*60;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plg.getLogger().info(ClassUtil.getLogInfo());

        mhGame.wave=Integer.parseInt(args[1]);
        boolean ret=false;

        try {
            World w=((Player)sender).getWorld();
            mhGame.nexus = (LivingEntity) w.spawnEntity(new Location(w, config.getDouble("NexusLocation.x"), config.getDouble("NexusLocation.y"), config.getDouble("NexusLocation.z")), EntityType.CREEPER);
            mhGame.nexus.setAI(false);
            int hp=config.getInt("NexusHp");
            mhGame.nexus.setMaxHealth(hp);
            mhGame.nexus.setHealth(hp);
        }catch(Exception e){
            e.printStackTrace();
            return false;
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

        mhGame.startTimer(plg);

        new ShopKeeper(plg,new Location(((Player)sender).getWorld(),config.getDouble("ShopKeeperLocation.x"),config.getDouble("ShopKeeperLocation.y"),config.getDouble("ShopKeeperLocation.z")));
        mhGame.shopKeeper.addItem(new ShopItem[]{
                new ShopItem(new ItemStack(Material.DIAMOND_AXE),25),
                new ShopItem(new ItemStack(Material.SNOWBALL,10),10),
                new ShopItem(new ItemStack(Material.IRON_HOE),15),
        });

        mhGame.bossbar=Bukkit.createBossBar(ChatColor.RED+"NEXUS HP", BarColor.PURPLE, BarStyle.SOLID);
        mhGame.bossbar.setProgress(1.0);
        Bukkit.getOnlinePlayers().forEach(p->mhGame.bossbar.addPlayer(p));

        ret=true;
        return ret;
    }
}
