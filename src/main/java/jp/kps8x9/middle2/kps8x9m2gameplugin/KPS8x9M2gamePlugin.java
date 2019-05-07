package jp.kps8x9.middle2.kps8x9m2gameplugin;

import jp.kps8x9.middle2.kps8x9m2gameplugin.util.ClassUtil;
import org.bukkit.Location;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;


public final class KPS8x9M2gamePlugin extends JavaPlugin {

    public static BossBar bossbar;
    public static Entity nexus_target;
    public static FileConfiguration config;
    public static int nexushp;

    @Override
    public void onEnable() {
        getLogger().info(ClassUtil.getLogInfo());
        // Plugin startup logic

        // config.ymlが存在しない場合はファイルに出力します。
        saveDefaultConfig();
        // config.ymlを読み込みます。
        config = getConfig();

        //初期設定
        nexushp=config.getInt("NexusHp");

        // ----------------------------------------
        // コマンドをここに登録
        // plugin.yml への記載も忘れずに！
        // ----------------------------------------
        this.getCommand("m2test").setExecutor(new M2TestCommand(this));
        this.getCommand("mh").setExecutor(new MHCommand(this));

        // ----------------------------------------
        // イベントリスナーをここに登録
        // ----------------------------------------
        this.getServer().getPluginManager().registerEvents( new M2TestLoginListener(this), this);
        this.getServer().getPluginManager().registerEvents( new PlayerDamageListener(this), this);
    }


    @Override
    public void onDisable() {
        getLogger().info(ClassUtil.getLogInfo());
        // Plugin shutdown logic

    }
}
