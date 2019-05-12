package jp.kps8x9.middle2.kps8x9m2gameplugin;

import jp.kps8x9.commons.util.ClassUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class M2TestLoginListener implements Listener {
    private final KPS8x9M2gamePlugin plg;

    /**
     * コンストラクタ
     *
     * @param plg_ プラグインメインクラスのインスタンス
     */
    public M2TestLoginListener(KPS8x9M2gamePlugin plg_) {
        plg = plg_;
        plg.getLogger().info(ClassUtil.getLogInfo() + " enabled.");
    }

    /**
     * PlayerLoginイベント処理
     * プレイヤーログイン時のイベントについて実装する
     *
     * @param event ログインイベント情報
     */
    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        // プレイヤーがOP権限かどうかログしておく。
        plg.getLogger().info(ClassUtil.getLogInfo() + event.getPlayer());
        plg.getLogger().info(ClassUtil.getLogInfo() + " isOp=" + event.getPlayer().isOp());
    }

    /**
     * PlayerJoinイベント処理
     * プレイヤーログイン後のイベントについて実装する
     *
     * @param event JOINイベント情報
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // ログインしたプレイヤーにようこそメッセージを表示する
        plg.getLogger().info(ClassUtil.getLogInfo() + event.getPlayer());
        event.getPlayer().sendMessage("ようこそ8x9M2ゲームサーバーへ！");
    }
}
