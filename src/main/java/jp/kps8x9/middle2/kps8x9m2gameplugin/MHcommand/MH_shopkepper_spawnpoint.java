package jp.kps8x9.middle2.kps8x9m2gameplugin.MHcommand;

import jp.kps8x9.commons.util.ClassUtil;
import jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin;
import jp.kps8x9.middle2.kps8x9m2gameplugin.MHCommand;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.MHGame;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MH_shopkepper_spawnpoint {
    private final KPS8x9M2gamePlugin plg;
    private final MHCommand cmd;
    private final MHGame mhGame;

    public MH_shopkepper_spawnpoint(KPS8x9M2gamePlugin plg,MHCommand cmd){
        this.plg=plg;
        this.cmd=cmd;
        mhGame=MHGame.getInstance();
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plg.getLogger().info(ClassUtil.getLogInfo());

        boolean ret=false;

        Location loc=((Player)sender).getLocation();
        loc.setPitch(0);
        loc.setYaw(0);
        mhGame.setShopKeeper_loc(loc);
        sender.sendMessage("ショップキーパーのスポーン位置を設定しました");

        ret=true;
        return ret;
    }
}
