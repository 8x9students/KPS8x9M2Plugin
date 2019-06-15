package jp.kps8x9.middle2.kps8x9m2gameplugin.Event;

import jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.MHGame;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.ShopItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class ShopBuy implements Listener {
    private final KPS8x9M2gamePlugin plg;
    private final MHGame mhGame;

    public ShopBuy(KPS8x9M2gamePlugin plg){
        this.plg=plg;
        this.mhGame=MHGame.getInstance();
    }

    @EventHandler
    public void shopBuy(InventoryClickEvent e){
        //ショップのインベントリでなければreturn
        if(e.getClickedInventory()==null||!e.getClickedInventory().equals(mhGame.shopKeeper.getInventory())) return;
        //nullもしくは空気ならばreturn
        if(e.getCurrentItem()==null||e.getCurrentItem().getType()== Material.AIR)return;

        e.setCancelled(true);
        buy((Player) e.getWhoClicked(),e.getCurrentItem());
    }

    //買う
    public void buy(Player buyer,ItemStack itm){
        int cost=mhGame.shopKeeper.getShopItem(itm).getPrice();
        if(mhGame.getCoin(buyer)<cost){
            buyer.sendMessage(ChatColor.RED+"お金が足りません");
            return;
        }
        mhGame.setCoin(buyer,mhGame.getCoin(buyer)-cost);
        buyer.getInventory().addItem(itm.clone());//購入したアイテムを渡す
    }
}
