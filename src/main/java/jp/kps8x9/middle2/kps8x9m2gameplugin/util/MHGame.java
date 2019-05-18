package jp.kps8x9.middle2.kps8x9m2gameplugin.util;

import jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class MHGame {

    public int wave;
    public BossBar bossbar;//ネクサスのHPバー
    public LivingEntity nexus;//ネクサス
    public int second;//秒
    private BukkitTask timer;//タイマー
    private int color_num;

    private static MHGame mhGame=new MHGame();

    private MHGame(){}

    public static MHGame getInstance(){
        return mhGame;
    }

    public void waveDown(){ //waveを上げる
        wave--;
    }

    public void waveUp(){ //waveを下げる
        wave++;
    }

    public void setWave(int wave){ //waveを設定する
        if(wave<0)
            wave=1;
        this.wave=wave;
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
}
