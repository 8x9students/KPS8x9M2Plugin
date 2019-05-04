package jp.kps8x9.middle2.kps8x9m2gameplugin;

import jp.kps8x9.middle2.kps8x9m2gameplugin.util.ClassUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.event.FocusEvent;
import java.net.http.WebSocket;

import static org.bukkit.event.entity.EntityDamageEvent.DamageCause.*;

public final class KPS8x9M2gamePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info(ClassUtil.getLogInfo());
        // Plugin startup logic

        // ----------------------------------------
        // コマンドをここに登録
        // plugin.yml への記載も忘れずに！
        // ----------------------------------------
        this.getCommand("m2test").setExecutor(new M2TestCommand(this));

        // ----------------------------------------
        class PlayerDamageListener implements Listener {
            @EventHandler
            public void EntityDamage(EntityDamageByEntityEvent event){
                  Entity Damager = event.getDamager();
                    Entity entity = event.getEntity();
                     EntityDamageEvent.DamageCause cause = event.getCause();
                    System.out.println(entity);
                    if (entity instanceof Player) {
                        int Damage = (int) event.getDamage();
                        System.out.println(Damage);
                        String ga = "が";
                        String damageuketa = "ダメージ受けた";
                        switch (cause) {
                            case BLOCK_EXPLOSION:
                                String blkexp1 = "ブロックが爆発されたことにより";
                                System.out.println(entity + ga + blkexp1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + blkexp1 + Damage + damageuketa);
                                break;
                            case CONTACT:
                                String con1 = "ブロックに触れたことにより";
                                System.out.println(entity + ga + con1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + con1 + Damage + damageuketa);
                                break;
                            case CRAMMING:
                                String cra1 = "1ブロックに多くのエンティティがいたため";
                                System.out.println(entity + ga + cra1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + cra1 + Damage + damageuketa);
                                break;
                            case CUSTOM:
                                String cus1 = "カスタムダメージにより";
                                System.out.println(entity + ga + cus1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + cus1 + Damage + damageuketa);
                                break;
                            case DRAGON_BREATH:
                                String dra1 = "ドラゴンのブレスにより";
                                System.out.println(entity + ga + dra1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + dra1 + Damage + damageuketa);
                            case DROWNING:
                                String dro1 = "水中で空気を切られたことによって";
                                System.out.println(entity + ga + dro1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + dro1 + Damage + damageuketa);break;
                            case DRYOUT:
                                String dry1 = "乾いたため";
                                System.out.println(entity + ga + dry1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + dry1 + Damage + damageuketa);
                                break;
                            case ENTITY_ATTACK:
                                String ena1 = "により";
                                System.out.println(entity + ga + Damager + ena1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + Damager + ena1 + Damage + damageuketa);
                                break;
                            case ENTITY_EXPLOSION:
                                String ene1 = "による爆発により";
                                System.out.println(entity + ga + Damager + ene1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + Damager + ene1 + Damage + damageuketa);
                                break;
                            case ENTITY_SWEEP_ATTACK:
                                String ens1 = "によるスイープ攻撃により";
                                System.out.println(entity + ga + Damager + ens1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + Damager + ens1 + Damage + damageuketa);
                                break;
                            case FALL:
                                String fal1 = "落下したため";
                                System.out.println(entity + ga + fal1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + fal1 + Damage + damageuketa);
                                break;
                            case FALLING_BLOCK:
                                String fab1 = "落下するブロックに当たったため";
                                System.out.println(entity + ga + fab1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + fab1 + Damage + damageuketa);
                                break;
                            case FIRE:
                                String fir1 = "直火により";
                                System.out.println(entity + ga + fir1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + fir1 + Damage + damageuketa);
                                break;
                            case FIRE_TICK:
                                String fit1 = "火事により";
                                System.out.println(entity + ga + fit1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + fit1 + Damage + damageuketa);
                                break;
                            case FLY_INTO_WALL:
                                String fiw1 = "壁にぶつかったため";
                                System.out.println(entity + ga + fiw1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + fiw1 + Damage + damageuketa);
                                break;
                            case HOT_FLOOR:
                                String hot1 = "踏み込んだ先のブロックにより";
                                System.out.println(entity + ga + hot1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + hot1 + Damage + damageuketa);
                                break;
                            case LAVA:
                                String lav1 = "溶岩に直接さらされたため";
                                System.out.println(entity + ga + lav1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + lav1 + Damage + damageuketa);
                                break;
                            case LIGHTNING:
                                String lig1 = "落雷により";
                                System.out.println(entity + ga + lig1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + lig1 + Damage + damageuketa);
                                break;
                            case MAGIC:
                                String mag1 = "ポーションや呪いによい";
                                System.out.println(entity + ga + mag1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + mag1 + Damage + damageuketa);
                                break;
                            case MELTING:
                                String mel1 = "雪だるまの融解により";
                                System.out.println(entity + ga + mel1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + mel1 + Damage + damageuketa);
                                break;
                            case POISON:
                                String poi1 = "毒により";
                                System.out.println(entity + ga + poi1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + poi1 + Damage + damageuketa);
                                break;
                            case PROJECTILE:
                                String pro1 = "発射体に攻撃されたため";
                                System.out.println(entity + ga + pro1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + pro1 + Damage + damageuketa);
                                break;
                            case STARVATION:
                                String str1 = "空腹により";
                                System.out.println(entity + ga + str1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + str1 + Damage + damageuketa);
                                break;
                            case SUFFOCATION:
                                String suf1 = "ブロックに入れられたため";
                                System.out.println(entity + ga + suf1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + suf1 + Damage + damageuketa);
                                break;
                            case SUICIDE:
                                String sui1 = "/killコマンドを使い自殺したため";
                                System.out.println(entity + ga + sui1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + sui1 + Damage + damageuketa);
                                break;
                            case THORNS:
                                String tho1 = "棘エンチャントにより";
                                System.out.println(entity + ga + tho1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + tho1 + Damage + damageuketa);
                                break;
                            case VOID:
                                String voi1 = "奈落に落ちたことにより";
                                System.out.println(entity + ga + voi1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + voi1 + Damage + damageuketa);
                                break;
                            case WITHER:
                                String wit1 = "ウィザー効果のあるものにより";
                                System.out.println(entity + ga + wit1 + Damage + damageuketa);
                                Bukkit.broadcastMessage(entity + ga + wit1 + Damage + damageuketa);
                                break;
                        }
                    }

                }
        }
        // ----------------------------------------
        this.getServer().getPluginManager().registerEvents( new M2TestLoginListener(this), this);
        this.getServer().getPluginManager().registerEvents( new PlayerDamageListener(), this);
    }


    @Override
    public void onDisable() {
        getLogger().info(ClassUtil.getLogInfo());
        // Plugin shutdown logic

    }
}
