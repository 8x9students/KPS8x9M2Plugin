package jp.kps8x9.middle2.kps8x9m2gameplugin.MHcommand;

import jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin;
import jp.kps8x9.middle2.kps8x9m2gameplugin.MHCommand;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MH_spawnpoint {
    private final KPS8x9M2gamePlugin plg;
    private final MHCommand cmd;
    public Location location
            ;
    public MH_spawnpoint(KPS8x9M2gamePlugin plg_, MHCommand cmd_) {
        plg = plg_;
        cmd = cmd_;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if(args.length == 1) {
            this.location = player.getLocation();
        }else if(args.length == 4){

            Double x = parse(args[1], player.getLocation().getX());
            Double y = parse(args[2], player.getLocation().getY());
            Double z = parse(args[3], player.getLocation().getZ());

            this.location = new Location(player.getWorld(), x, y, z);
        }

        return true;
    }

    double parse(String str, Double playerPosition){
        if(str.equals("~")){
            return playerPosition;
        }else {
            return Double.parseDouble(str);
        }
    }
}