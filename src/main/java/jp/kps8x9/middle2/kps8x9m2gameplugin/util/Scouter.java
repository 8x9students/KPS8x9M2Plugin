package jp.kps8x9.middle2.kps8x9m2gameplugin.util;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class Scouter implements Listener {
    @EventHandler
    public void scouter(PlayerInteractAtEntityEvent event){
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        ItemStack items = player.getInventory().getItemInMainHand();
        if (items.getType()== Material.PRISMARINE_SHARD) {
            if (Objects.requireNonNull(items.getItemMeta()).getDisplayName().equals("スカウター")) {
                if(entity.getType()== EntityType.PLAYER){
                    Player target = (Player)entity;
                    Location location = player.getLocation();
                    int kills = player.getStatistic(Statistic.MOB_KILLS);
                    int damage = player.getStatistic(Statistic.DAMAGE_DEALT);
                    int guard = player.getStatistic(Statistic.DAMAGE_RESISTED);
                    int shield = player.getStatistic(Statistic.DAMAGE_BLOCKED_BY_SHIELD);
                    double helth = target.getHealth();
                    double food = target.getFoodLevel();
                    double exp = target.getLevel();
                    double fightlebel = helth*food*exp+kills*damage+guard*shield;
                    if(fightlebel>=600000){
                        player.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL,location.getX(),location.getY(),location.getZ(),1,0,0,0);
                    }
                    player.sendMessage(ChatColor.GREEN+target.getDisplayName()+"の戦闘力は"+ChatColor.RED+fightlebel+"です");
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,1);
                }else {
                    player.playSound(player.getLocation(), Sound.ENTITY_SHULKER_OPEN,1,10);
                    player.sendMessage(ChatColor.DARK_RED+"測定不能");
                }
            }
        }
    }
}
