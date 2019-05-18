package jp.kps8x9.middle2.kps8x9m2gameplugin;

import jp.kps8x9.middle2.kps8x9m2gameplugin.util.ClassUtil;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.GameStart;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

import static com.sun.javafx.binding.Logging.getLogger;

public final class KPS8x9M2gamePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info(ClassUtil.getLogInfo());
        // Plugin startup logic

        // ----------------------------------------
        // コマンドをここに登録
        // plugin.yml への記載も忘れずに！
        // ----------------------------------------
        Objects.requireNonNull(this.getCommand("m2test")).setExecutor(new M2TestCommand(this));
        Objects.requireNonNull(this.getCommand("start")).setExecutor(new GameStart());

        // ----------------------------------------
        // イベントリスナーをここに登録
        // ----------------------------------------
        this.getServer().getPluginManager().registerEvents( new M2TestLoginListener(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info(ClassUtil.getLogInfo());
        // Plugin shutdown logic
    }
}