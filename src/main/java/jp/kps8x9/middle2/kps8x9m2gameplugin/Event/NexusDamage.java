package jp.kps8x9.middle2.kps8x9m2gameplugin.Event;

import jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin;
import jp.kps8x9.middle2.kps8x9m2gameplugin.MHCommand;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.MHGame;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import static jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin.*;

public class NexusDamage implements Listener {
    private final KPS8x9M2gamePlugin plugin;
    private final MHGame mhGame;

    public NexusDamage(KPS8x9M2gamePlugin plugin){
        this.plugin=plugin;
        this.mhGame=MHGame.getInstance();
    }

    @EventHandler
    private void nexusdamage(EntityDamageByEntityEvent e){
        if((e.getEntity()==mhGame.nexus||e.getEntity()==mhGame.shopKeeper.getKeeper())&&e.getDamager().getType()== EntityType.PLAYER&&!((Player)e.getDamager()).getGameMode().equals(GameMode.CREATIVE)){
            e.setCancelled(true);
            return;
        }
        if(e.getEntity()!=mhGame.nexus) {
            return;
        }
        if(mhGame.nexus.getHealth()-e.getDamage()<=0) {
            mhGame.finish(plugin);
            return;
        }
        mhGame.bossbar.setProgress((mhGame.nexus.getHealth()-e.getDamage())/config.getDouble("NexusHp"));
    }
}
