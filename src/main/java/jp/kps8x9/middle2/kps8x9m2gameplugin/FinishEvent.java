package jp.kps8x9.middle2.kps8x9m2gameplugin;

import jp.kps8x9.middle2.kps8x9m2gameplugin.util.ClassUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class FinishEvent implements CommandExecutor {
    private final KPS8x9M2gamePlugin plg;

    public FinishEvent(KPS8x9M2gamePlugin plg_) {
        plg = plg_;
        plg.getLogger().info(ClassUtil.getLogInfo() + " enabled.");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        return false;
    }
}
