package jp.kps8x9.middle2.kps8x9m2gameplugin;

import jp.kps8x9.middle2.kps8x9m2gameplugin.Event.NexusDamage;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.ClassUtil;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.Scouter;
import org.bukkit.Location;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;


public final class KPS8x9M2gamePlugin extends JavaPlugin {

    public static BossBar bossbar;//ネクサスのHPバー
    public static LivingEntity nexus;//ネクサス
    public static double nexushp;//ネクサスのHP

    public static FileConfiguration config;//コンフィグ

    @Override
    public void onEnable() {
        getLogger().info(ClassUtil.getLogInfo());
        // Plugin startup logic

        // config.ymlが存在しない場合はファイルに出力します。
        saveDefaultConfig();
        // config.ymlを読み込みます。
        config = getConfig();

        //初期設定
        nexushp=config.getDouble("NexusHp");

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
        this.getServer().getPluginManager().registerEvents( new NexusDamage(this), this);
        this.getServer().getPluginManager().registerEvents( new Scouter(), this);
    }


    @Override
    public void onDisable() {
        getLogger().info(ClassUtil.getLogInfo());
        // Plugin shutdown logic

    }
}
