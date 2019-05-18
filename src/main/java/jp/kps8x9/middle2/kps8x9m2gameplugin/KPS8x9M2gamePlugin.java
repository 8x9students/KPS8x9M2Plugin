package jp.kps8x9.middle2.kps8x9m2gameplugin;

<<<<<<< HEAD
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.ClassUtil;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.GameStart;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

import static com.sun.javafx.binding.Logging.getLogger;
=======
import jp.kps8x9.middle2.kps8x9m2gameplugin.Event.NexusDamage;
import jp.kps8x9.commons.util.ClassUtil;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.Scouter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

>>>>>>> d5a5260e9955cc038eae1db15310efe06a2474d9

public final class KPS8x9M2gamePlugin extends JavaPlugin {

    public static FileConfiguration config;//コンフィグ

    @Override
    public void onEnable() {
        getLogger().info(ClassUtil.getLogInfo());
        // Plugin startup logic

        // config.ymlが存在しない場合はファイルに出力します。
        saveDefaultConfig();
        // config.ymlを読み込みます。
        config = getConfig();

        // ----------------------------------------
        // コマンドをここに登録
        // plugin.yml への記載も忘れずに！
        // ----------------------------------------
<<<<<<< HEAD
        Objects.requireNonNull(this.getCommand("m2test")).setExecutor(new M2TestCommand(this));
        Objects.requireNonNull(this.getCommand("start")).setExecutor(new GameStart());
=======
        MHCommand cmd = new MHCommand(this);
        this.getCommand("m2test").setExecutor(new M2TestCommand(this));
        this.getCommand("mh").setExecutor(cmd);
        this.getCommand("finish").setExecutor(new FinishEvent(this));
>>>>>>> d5a5260e9955cc038eae1db15310efe06a2474d9

        // ----------------------------------------
        // イベントリスナーをここに登録
        // ----------------------------------------
        this.getServer().getPluginManager().registerEvents(new M2TestLoginListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerDamageListener(this), this);
        this.getServer().getPluginManager().registerEvents(new NexusDamage(this, cmd), this);
        this.getServer().getPluginManager().registerEvents(new Scouter(), this);
    }


    @Override
    public void onDisable() {
        getLogger().info(ClassUtil.getLogInfo());
        // Plugin shutdown logic
    }
}