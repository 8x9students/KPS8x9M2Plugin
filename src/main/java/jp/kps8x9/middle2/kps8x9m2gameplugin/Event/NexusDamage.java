package jp.kps8x9.middle2.kps8x9m2gameplugin.Event;

import jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin;
import jp.kps8x9.middle2.kps8x9m2gameplugin.MHcommand.MH_end;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import static jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin.*;

public class NexusDamage implements Listener {
    private final KPS8x9M2gamePlugin plugin;

    public NexusDamage(KPS8x9M2gamePlugin plugin){
        this.plugin=plugin;
    }

    @EventHandler
    private void nexusdamage(EntityDamageByEntityEvent e){
        if(e.getEntity()==nexus&&e.getDamager().getType()== EntityType.PLAYER){
            e.setCancelled(true);
            return;
        }
        if(e.getEntity()!=nexus)
            return;
        nexushp-=e.getDamage();
        if(nexushp<=0) {
            new MH_end(plugin);
            return;
        }
        bossbar.setProgress(nexushp/config.getDouble("NexusHp"));
    }
}
