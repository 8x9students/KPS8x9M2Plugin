package jp.kps8x9.middle2.kps8x9m2gameplugin.MHcommand;

import jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin;
import jp.kps8x9.middle2.kps8x9m2gameplugin.MHCommand;
import jp.kps8x9.commons.util.ClassUtil;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.MHGame;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class MH_end {
    private final KPS8x9M2gamePlugin plg;
    private final MHCommand cmd;
    private final MHGame mhGame;

    /**
     * コンストラクタ
     * @param plg_  プラグインメインクラスのインスタンス
     */
    public MH_end(KPS8x9M2gamePlugin plg_, MHCommand cmd_) {
        plg = plg_;
        cmd = cmd_;
        mhGame=MHGame.getInstance();
    }

    /**
     * コマンド処理
     * @param sender
     * @param command
     * @param label
     * @param args
     * @return
     */

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plg.getLogger().info(ClassUtil.getLogInfo());

        boolean ret = false;

        // End処理
        mhGame.finish(plg);

        ret = true;
        return ret;
    }
}
