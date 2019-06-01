package jp.kps8x9.middle2.kps8x9m2gameplugin;

import jp.kps8x9.middle2.kps8x9m2gameplugin.Event.SetShopItems;
import jp.kps8x9.middle2.kps8x9m2gameplugin.Event.ShopBuy;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.GameStart;
import java.util.Objects;
import jp.kps8x9.middle2.kps8x9m2gameplugin.Event.NexusDamage;
import jp.kps8x9.commons.util.ClassUtil;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.Scouter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

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

        this.getCommand("m2test").setExecutor(new M2TestCommand(this));
        this.getCommand("start").setExecutor(new GameStart());
        this.getCommand("mh").setExecutor(new MHCommand(this));
        this.getCommand("finish").setExecutor(new FinishEvent(this));

        // ----------------------------------------
        // イベントリスナーをここに登録
        // ----------------------------------------
        this.getServer().getPluginManager().registerEvents(new M2TestLoginListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerDamageListener(this), this);
        this.getServer().getPluginManager().registerEvents(new NexusDamage(this), this);
        this.getServer().getPluginManager().registerEvents(new Scouter(), this);
        this.getServer().getPluginManager().registerEvents(new SetShopItems(this), this);
        this.getServer().getPluginManager().registerEvents(new ShopBuy(this), this);
    }


    @Override
    public void onDisable() {
        getLogger().info(ClassUtil.getLogInfo());
        // Plugin shutdown logic
    }
}