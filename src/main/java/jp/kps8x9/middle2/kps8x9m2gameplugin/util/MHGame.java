package jp.kps8x9.middle2.kps8x9m2gameplugin.util;

import jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;

public class MHGame {

    public int wave;
    public BossBar bossbar;//ネクサスのHPバー
    public LivingEntity nexus;//ネクサス
    public int second;//秒
    private BukkitTask timer;//タイマー
    private BukkitTask finishtask;
    private int color_num;
    public ShopKeeper shopKeeper;
    public Inventory editInv;
    private HashMap<Player,Integer> coinmap=new HashMap<>();
    private Location nexus_loc=null,shopkeeper_loc=null;

    private static MHGame mhGame=new MHGame(1);

    private MHGame(int wave){
        this.wave = wave;
    }

    public static MHGame getInstance(){
        return mhGame;
    }

    public void waveDown(){ //waveを下げる
        if(this.wave<=1) {
            this.wave = 1;
        }else if(this.wave>1000) {
            this.wave = 1000;
        }else{
            this.wave--;
        }
    }

    public void waveUp(){ //waveを上げる
        if(this.wave>=1000) {
            this.wave = 1000;
        }else if(this.wave<1) {
            this.wave = 1;
        }else{
            this.wave++;
        }
    }

    public void setWave(int wave){ //waveを設定する
        if(wave<0) {
            this.wave = 1;
        }else if(wave>1000) {
            this.wave =1000;
        }else {
            this.wave = wave;
        }
    }

    public int getCoin(Player p){
        if(!coinmap.containsKey(p)){
            return 0;
        }
        return coinmap.get(p);
    }

    public void setCoin(Player p,int coin){
        coinmap.put(p,coin);
    }

    public void setNexus_loc(Location loc){
        nexus_loc=loc;
    }

    public Location getNexus_loc(){
        return nexus_loc;
    }

    public void setShopKeeper_loc(Location loc){
        shopkeeper_loc=loc;
    }

    public Location getShopkeeper_loc(){
        return shopkeeper_loc;
    }

    //タイマー起動
    public void startTimer(KPS8x9M2gamePlugin plg){
        timer=new BukkitRunnable() {
            @Override
            public void run() {
                second--;
                if(second<=0) {
                    finish(plg);
                    cancel();
                }
            }
        }.runTaskTimer(plg,20,20);
    }

    public void finish(KPS8x9M2gamePlugin plg){
        if(!nexus.isDead())
            nexus.setHealth(0);

        bossbar.setProgress(1.0);

        if(!nexus.isDead()) {
            nexus.setHealth(0);
        }
        nexus=null;

        timer.cancel();

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
        finishtask=new BukkitRunnable(){
            @Override
            public void run() {
                bossbar.setColor(colors[color_num]);
                color_num++;
                if(color_num>=colors.length)
                    color_num=0;
            }
        }.runTaskTimer(plg,4,4);
    }

    public void clear(){
        if(finishtask!=null)
            finishtask.cancel();
        if(bossbar!=null)
            bossbar.removeAll();
    }
}