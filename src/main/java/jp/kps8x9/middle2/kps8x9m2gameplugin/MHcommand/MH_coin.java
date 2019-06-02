package jp.kps8x9.middle2.kps8x9m2gameplugin.MHcommand;

import jp.kps8x9.commons.util.ClassUtil;
import jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin;
import jp.kps8x9.middle2.kps8x9m2gameplugin.MHCommand;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.MHGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MH_coin {
    private final KPS8x9M2gamePlugin plg;
    private final MHCommand cmd;
    private final MHGame mhGame;

    /**
     * コンストラクタ
     * @param plg_  プラグインメインクラスのインスタンス
     */
    public MH_coin(KPS8x9M2gamePlugin plg_, MHCommand cmd_) {
        plg = plg_;
        cmd=cmd_;
        mhGame=MHGame.getInstance();
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plg.getLogger().info(ClassUtil.getLogInfo());

        boolean ret = false;

        Bukkit.broadcastMessage(ChatColor.GREEN+"あなたが所持しているコインは"+mhGame.getCoin((Player) sender)+"です");

        ret = true;
        return ret;
    }
}
