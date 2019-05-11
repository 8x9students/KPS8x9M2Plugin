package jp.kps8x9.middle2.kps8x9m2gameplugin;

import jp.kps8x9.middle2.kps8x9m2gameplugin.MHcommand.*;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.ClassUtil;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import static jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin.*;

public class MHCommand implements CommandExecutor {
    public final KPS8x9M2gamePlugin plg;
    public int wave;
    public BossBar bossbar;//ネクサスのHPバー
    public LivingEntity nexus;//ネクサス
    public double nexushp;//ネクサスのHP
    public BukkitTask timer;//タイマー
    public int minute;//分
    public int second;//秒
    private int color_num;
    MH_start mh=null;

    /**
     * コンストラクタ
     * @param plg_  プラグインメインクラスのインスタンス
     */
    public MHCommand(KPS8x9M2gamePlugin plg_) {
        plg = plg_;
        plg.getLogger().info(ClassUtil.getLogInfo() + " enabled.");

        nexushp=config.getDouble("NexusHp");
    }

    public void finish(){
        if(!nexus.isDead())
            nexus.setHealth(0);

        bossbar.setProgress(1.0);

        //cmd.timer.cancel();

        StringBuilder title=new StringBuilder()
                .append(ChatColor.RED).append("F")
                .append(ChatColor.YELLOW).append("I")
                .append(ChatColor.GREEN).append("N")
                .append(ChatColor.BLUE).append("I")
                .append(ChatColor.LIGHT_PURPLE).append("S")
                .append(ChatColor.DARK_PURPLE).append("H");

        bossbar.setTitle(title.toString());

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

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            return false;
        }

        boolean ret = false;

        try {
            if (args.length == 1) {
                switch (args[0].toLowerCase()) {
                    //それぞれのクラスへ移動
                    case "end":
                        ret = new MH_end(this.plg, this).onCommand(sender, command, label, args);
                        break;
                    case "waveup":
                        if (sender.isOp() && mh != null)
                            mh.waveUp();
                        break;
                    case "wavedown":
                        if (sender.isOp() && mh != null)
                            mh.waveDown();
                        break;
                    case "scouter":
                        ret=new MH_scouter(plg,this).onCommand(sender,command,label,args);
                        break;
                    case "wave":
                        sender.sendMessage(ChatColor.LIGHT_PURPLE + "WAVE " + wave + " !!");
                    default:
                        sender.sendMessage(ChatColor.RED + "/mh 内容");
                        break;
                }
            } else if (args.length == 2) {
                switch (args[0].toLowerCase()) {
                    //それぞれのクラスへ移動
                    case "start":
                        mh = new MH_start(plg,this);
                        ret=mh.onCommand(sender, command, label, args);
                        break;
                    case "sethp":
                        ret=new MH_sethp(plg,this).onCommand(sender,command,label,args);
                        break;
                    case "setwave":
                        if (sender.isOp() && mh != null)
                            mh.setWave(Integer.parseInt(args[1]));
                        break;
                    default:
                        sender.sendMessage(ChatColor.RED + "/mh 内容");
                        break;
                }
            } else if (args.length == 4 && args[0].equalsIgnoreCase("spawnpoint"))
                new MH_spawnpoint(Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]));
            else {
                sender.sendMessage(ChatColor.RED + "/mh 内容");
            }
        }catch (Exception e){
            e.printStackTrace();
            sender.sendMessage(ChatColor.RED + "エラー");
        }
        return ret;
    }
}
