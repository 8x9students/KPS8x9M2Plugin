package jp.kps8x9.middle2.kps8x9m2gameplugin;

import jp.kps8x9.commons.util.ClassUtil;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;


public class FinishEvent implements CommandExecutor {
    private final KPS8x9M2gamePlugin plg;
    public FinishEvent(KPS8x9M2gamePlugin plg_) {
        plg = plg_;
        plg.getLogger().info(ClassUtil.getLogInfo() + " enabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //モブを消す
        World world = ((Player)sender).getWorld();
        for(LivingEntity entity : world.getLivingEntities()){
            if(entity instanceof Monster){
                double damage = 100.0;
                entity.damage(damage);
            }
        }
        return false;
    }
}
