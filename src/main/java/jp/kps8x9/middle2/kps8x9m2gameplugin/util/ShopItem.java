package jp.kps8x9.middle2.kps8x9m2gameplugin.util;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class ShopItem {
    private ItemStack itm;
    private int price;

    public ShopItem(ItemStack itm,int price){
        this.itm=itm;
        this.price=price;
        ItemMeta meta=itm.getItemMeta();
        meta.setLore(Collections.singletonList(price+"コイン"));
        itm.setItemMeta(meta);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price){
        if(price<0)
            price=0;
        this.price=price;
        ItemMeta meta=itm.getItemMeta();
        meta.setLore(Collections.singletonList(price+"コイン"));
        itm.setItemMeta(meta);
    }

    public ItemStack getItemStack() {
        return itm;
    }
}
