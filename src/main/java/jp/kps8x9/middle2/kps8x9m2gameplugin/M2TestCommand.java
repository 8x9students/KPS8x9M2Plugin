package jp.kps8x9.middle2.kps8x9m2gameplugin;

import jp.kps8x9.commons.util.ClassUtil;
import jp.kps8x9.commons.util.LogUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class M2TestCommand implements CommandExecutor {
    private final KPS8x9M2gamePlugin plg;

    /**
     * コンストラクタ
     *
     * @param plg_ プラグインメインクラスのインスタンス
     */
    public M2TestCommand(KPS8x9M2gamePlugin plg_) {
        plg = plg_;
        plg.getLogger().info(ClassUtil.getLogInfo() + " enabled.");
    }

    /**
     * コマンド処理
     *
     * @param sender
     * @param command
     * @param label
     * @param args
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plg.getLogger().info(ClassUtil.getLogInfo() + "sender=" + sender);
        plg.getLogger().info(ClassUtil.getLogInfo() + "command=" + command);
        plg.getLogger().info(ClassUtil.getLogInfo() + "label=" + label);
        plg.getLogger().info(ClassUtil.getLogInfo() + "args=" + LogUtil.arrayToString(args));

        // ----------------------------------------
        // ここから処理
        // ----------------------------------------


        // ----------------------------------------
        // /ここまで処理
        // ----------------------------------------
        return false;
    }
}
