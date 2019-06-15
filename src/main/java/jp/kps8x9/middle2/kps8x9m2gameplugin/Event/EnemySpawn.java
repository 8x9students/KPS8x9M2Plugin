package jp.kps8x9.middle2.kps8x9m2gameplugin.Event;

import jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin;
import jp.kps8x9.middle2.kps8x9m2gameplugin.MHCommand;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class EnemySpawn implements CommandExecutor{
    private final KPS8x9M2gamePlugin plg;
    private final MHCommand cmd;

    public EnemySpawn(KPS8x9M2gamePlugin plg, MHCommand cmd){
        this.plg=plg;
        this.cmd=cmd;
    }
    @Override
    public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
        if(!(arg0 instanceof Player))return true;//コマンドを実行した人がプレイヤー以外なら無視
        Player player = (Player)arg0;
        int r = 20;//半径
        //Bukkitには特定の範囲のブロックを取得するAPIは無いので手動でブロックを取得する。
        Location startLoc = player.getLocation().subtract(r, r, r);//ブロック処理の始点の座標を取得
        for(int i=startLoc.getBlockX(); i<startLoc.getBlockX()+r*2; i++){
            for(int j=startLoc.getBlockY(); j<startLoc.getBlockY()+r*2; j++){
                for(int k=startLoc.getBlockZ(); k<startLoc.getBlockZ()+r*2; k++){
                    Location loc = new Location(startLoc.getWorld(), i, j, k);
                    Block b = loc.getBlock();
                }
            }
        }
        return true;
    }
}