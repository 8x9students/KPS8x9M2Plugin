package jp.kps8x9.middle2.kps8x9m2gameplugin;

import jp.kps8x9.middle2.kps8x9m2gameplugin.MHcommand.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MHCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        if(args.length==1) {
            switch (args[0].toLowerCase()) {
                //それぞれのクラスへ移動
                case "start":
                    new MH_start();
                    break;
                case "end":
                    new MH_end();
                    break;
                case "waveup":
                    new MH_waveup();
                    break;
                case "wavedown":
                    new MH_wavedown();
                    break;
                default:
                    sender.sendMessage(ChatColor.RED+"/mh 内容");
                    break;
            }
        }else if(args.length==2) {
            try {
                switch (args[0].toLowerCase()) {
                    //それぞれのクラスへ移動
                    case "sethp":
                        new MH_sethp(Integer.parseInt(args[1]));
                        break;
                    case "setwave":
                        new MH_sethp(Integer.parseInt(args[1]));
                        break;
                    default:
                        sender.sendMessage(ChatColor.RED+"/mh 内容");
                        break;
                }
            }catch (Exception e){
                e.printStackTrace();
                sender.sendMessage(ChatColor.RED+"エラー");
            }
        }else if(args.length==3&&args[0].equalsIgnoreCase("time")) {
            //例外
            try {
                //クラスへ移動
                new MH_time(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
            }catch (Exception e){
                e.printStackTrace();
                sender.sendMessage(ChatColor.RED+"エラー");
            }
        }else if(args.length==4&&args[0].equalsIgnoreCase("spawnpoint")){
            //例外
            try {
                //クラスへ移動
                new MH_spawnpoint(Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]));
            }catch (Exception e){
                e.printStackTrace();
                sender.sendMessage(ChatColor.RED+"エラー");
            }
        }else{
            sender.sendMessage(ChatColor.RED+"/mh 内容");
        }
        return false;
    }
}
