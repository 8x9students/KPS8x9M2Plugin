package jp.kps8x9.middle2.kps8x9m2gameplugin.MHcommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftArmorStand;
import org.bukkit.entity.EntityType;

import static jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin.*;

public class MH_start {
    public MH_start(World w){
        double x=config.getDouble("NexusLocation.x");
        double y=config.getDouble("NexusLocation.y");
        double z=config.getDouble("NexusLocation.z");

        bossbar=Bukkit.createBossBar(ChatColor.RED+"NexusHP", BarColor.PURPLE, BarStyle.SOLID);
        bossbar.setProgress(1.0);
        Bukkit.getOnlinePlayers().forEach(p->bossbar.addPlayer(p));
        nexus_target=w.spawnEntity(new Location(w,x,y,z), EntityType.ARMOR_STAND);
        ((CraftArmorStand)nexus_target).getHandle().setInvisible(false);
        Bukkit.broadcastMessage("success:"+x+","+y+","+z);
    }
}
