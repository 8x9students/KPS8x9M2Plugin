package jp.kps8x9.middle2.kps8x9m2gameplugin.MHcommand;

import jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.ClassUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class MH_end {
    private final KPS8x9M2gamePlugin plg;

    /**
     * コンストラクタ
     * @param plg_  プラグインメインクラスのインスタンス
     */
    public MH_end(KPS8x9M2gamePlugin plg_) {
        plg = plg_;
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

        // todo:

        return ret;
    }
}
