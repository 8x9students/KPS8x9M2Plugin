package jp.kps8x9.middle2.kps8x9m2gameplugin.util;

import jp.kps8x9.middle2.kps8x9m2gameplugin.Event.SetShopItems;
import jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopKeeper implements Listener {
    private final KPS8x9M2gamePlugin plg;

    private Inventory inv;
    private LivingEntity keeper;
    private List<ShopItem> shopItems=new ArrayList<>();

    public ShopKeeper(KPS8x9M2gamePlugin plg,Location loc){
        this.plg=plg;
        plg.getServer().getPluginManager().registerEvents(this, plg);

        keeper= (LivingEntity) loc.getWorld().spawnEntity(loc, EntityType.BLAZE);
        keeper.setAI(false);
        MHGame.getInstance().shopKeeper=this;
        SetShopItems.setShopKeeper(this);
        inv= Bukkit.createInventory(null,54, ChatColor.RED+"SHOP");
    }

    public LivingEntity getKeeper(){
        return keeper;
    }


    public Inventory getInventory(){
        return inv;
    }

    public List<ShopItem> getShopItems(){return shopItems;}

    //ショップアイテムの取得
    public ShopItem getShopItem(ItemStack itm){
        ShopItem shopitm=null;
        for(ShopItem i:shopItems){
            if(i.getItemStack().equals(itm)){
                shopitm=i;
            }
        }
        return shopitm;
    }

    public void addItem(ShopItem[] itms){
        //item_listを配列に変換
        ItemStack[] itms_array=new ItemStack[itms.length];
        for(int index=0;index<itms.length;index++){
            itms_array[index]=itms[index].getItemStack();
        }
        //ショップのアイテムを保存
        shopItems.addAll(Arrays.asList(itms));
        //編集インベントリの更新
        MHGame.getInstance().editInv.clear();
        MHGame.getInstance().editInv.addItem(itms_array);
        //ショップインベントリの更新
        updateInventory();
    }

    public void removeItem(ShopItem itm){
        //インベントリを空にする
        inv.clear();
        //ショップのアイテムを更新
        shopItems.remove(itm);
        //shopItemsを配列に変換
        ShopItem[] itms_array=new ShopItem[shopItems.size()];
        for(int index=0;index<shopItems.size();index++){
            itms_array[index]=shopItems.get(index);
        }
        shopItems.clear();
        //新しいアイテム群を追加
        addItem(itms_array);
    }

    public void updateInventory(){//ショップインベントリの更新
        inv.clear();
        ItemStack[] itms_array=new ItemStack[shopItems.size()];
        for (int index=0;index<shopItems.size();index++){
            itms_array[index]=shopItems.get(index).getItemStack();
        }
        inv.addItem(itms_array);
    }

    @EventHandler
    public void Click(PlayerInteractEntityEvent e){
        if(e.getRightClicked()!=keeper) return;
        Player p=e.getPlayer();
        if(!p.isOp()||!p.isSneaking()) {
            updateInventory();
            p.openInventory(inv);
        }
    }
}
