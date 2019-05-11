package jp.kps8x9.middle2.kps8x9m2gameplugin.Event;

import jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin;
import jp.kps8x9.middle2.kps8x9m2gameplugin.MHCommand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import static jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin.*;

public class NexusDamage implements Listener {
    private final KPS8x9M2gamePlugin plugin;
    private final MHCommand cmd;

    public NexusDamage(KPS8x9M2gamePlugin plugin,MHCommand cmd){
        this.plugin=plugin;
        this.cmd=cmd;
    }

    @EventHandler
    private void nexusdamage(EntityDamageByEntityEvent e){
        if(e.getEntity()==cmd.nexus&&e.getDamager().getType()== EntityType.PLAYER&&!e.getDamager().isOp()){
            e.setCancelled(true);
            return;
        }
        if(e.getEntity()!=cmd.nexus)
            return;
        cmd.nexushp-=e.getDamage();
        if(cmd.nexushp<=0) {
            cmd.finish();
            return;
        }
        cmd.bossbar.setProgress(cmd.nexushp/config.getDouble("NexusHp"));
    }
}
