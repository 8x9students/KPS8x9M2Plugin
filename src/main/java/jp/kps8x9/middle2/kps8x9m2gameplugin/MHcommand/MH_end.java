package jp.kps8x9.middle2.kps8x9m2gameplugin.MHcommand;

import jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin;
import jp.kps8x9.middle2.kps8x9m2gameplugin.MHCommand;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.ClassUtil;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

import static jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin.*;

public class MH_end {
    private final KPS8x9M2gamePlugin plg;
    private final MHCommand cmd;
    private int color_num;

    /**
     * コンストラクタ
     * @param plg_  プラグインメインクラスのインスタンス
     */
    public MH_end(KPS8x9M2gamePlugin plg_, MHCommand cmd_) {
        plg = plg_;
        cmd = cmd_;

        bossbar.setProgress(1.0);

        StringBuilder title=new StringBuilder()
                .append(ChatColor.RED).append("F")
                .append(ChatColor.YELLOW).append("I")
                .append(ChatColor.GREEN).append("N")
                .append(ChatColor.BLUE).append("I")
                .append(ChatColor.LIGHT_PURPLE).append("S")
                .append(ChatColor.DARK_PURPLE).append("H");

        bossbar.setTitle(title.toString());
        finish();
    }

    private void finish(){
        BarColor[] colors=BarColor.values();
        color_num=0;
        new BukkitRunnable(){
            @Override
            public void run() {
                bossbar.setColor(colors[color_num]);
                color_num++;
                if(color_num>=colors.length)
                    color_num=0;
            }
        }.runTaskTimer(plg,4,4);
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
