package jp.kps8x9.middle2.kps8x9m2gameplugin.MHcommand;

import jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin;
import jp.kps8x9.middle2.kps8x9m2gameplugin.MHCommand;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.ClassUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;


public class MH_sethp {
    private final KPS8x9M2gamePlugin plg;
    private final MHCommand cmd;

    public MH_sethp(KPS8x9M2gamePlugin plg,MHCommand cmd){
        this.plg=plg;
        this.cmd=cmd;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plg.getLogger().info(ClassUtil.getLogInfo());

        boolean ret=false;

        double hp=Double.parseDouble(args[1]);
        if(hp<0)
            hp=0;

        cmd.nexus.setMaxHealth(hp);
        cmd.nexus.setHealth(hp);
        cmd.nexushp=hp;

        ret=true;
        return ret;
    }
}
