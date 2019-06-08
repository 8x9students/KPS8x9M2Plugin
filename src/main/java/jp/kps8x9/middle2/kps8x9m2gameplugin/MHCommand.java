package jp.kps8x9.middle2.kps8x9m2gameplugin;

import jp.kps8x9.middle2.kps8x9m2gameplugin.MHcommand.*;
import jp.kps8x9.commons.util.ClassUtil;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.MHGame;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MHCommand implements CommandExecutor {
    public final KPS8x9M2gamePlugin plg;
    private MHGame mhGame;

    /**
     * コンストラクタ
     * @param plg_  プラグインメインクラスのインスタンス
     */
    public MHCommand(KPS8x9M2gamePlugin plg_) {
        plg = plg_;
        plg.getLogger().info(ClassUtil.getLogInfo() + " enabled.");

        mhGame=MHGame.getInstance();
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
                    case "start":
                        ret=new MH_start(plg,this).onCommand(sender, command, label, args);
                        break;
                    case "end":
                        ret = new MH_end(this.plg, this).onCommand(sender, command, label, args);
                        break;
                    case "waveup":
                        if (sender.isOp() && mhGame != null) {
                            mhGame.waveUp();
                        }
                        break;
                    case "wavedown":
                        if (sender.isOp() && mhGame != null) {
                            mhGame.waveDown();
                        }
                        break;
                    case "scouter":
                        ret=new MH_scouter(plg,this).onCommand(sender,command,label,args);
                        break;
                    case "wave":
                        sender.sendMessage(ChatColor.LIGHT_PURPLE + "WAVE " + mhGame.wave + " !!");
                        break;
                    case "spawnpoint":
                        ret = new MH_spawnpoint(this.plg, this).onCommand(sender, command, label, args);
                        sender.sendMessage(ChatColor.GREEN + "スポーンポイントを設定しました。");
                        break;
                    case "coin":
                        ret=new MH_coin(this.plg,this).onCommand(sender,command,label,args);
                        break;
                    default:
                        sender.sendMessage(ChatColor.RED + "/mh 内容");
                        break;
                }
            } else if (args.length == 2) {
                switch (args[0].toLowerCase()) {
                    //それぞれのクラスへ移動
                    case "sethp":
                        ret=new MH_sethp(plg,this).onCommand(sender,command,label,args);
                        break;
                    case "setwave":
                        if (sender.isOp() && mhGame != null) {
                            mhGame.setWave(Integer.parseInt(args[1]));
                        }
                        break;
                    default:
                        sender.sendMessage(ChatColor.RED + "/mh 内容");
                        break;
                }
            } else if (args.length == 4 && args[0].equalsIgnoreCase("spawnpoint")){
                ret = new MH_spawnpoint(this.plg, this).onCommand(sender, command, label, args);
                sender.sendMessage(ChatColor.GREEN + "スポーンポイントを現在地に設定しました。");
            }else {
                sender.sendMessage(ChatColor.RED + "/mh 内容");
            }
        }catch (Exception e){
            e.printStackTrace();
            sender.sendMessage(ChatColor.RED + "エラー");
        }
        return ret;
    }
}
