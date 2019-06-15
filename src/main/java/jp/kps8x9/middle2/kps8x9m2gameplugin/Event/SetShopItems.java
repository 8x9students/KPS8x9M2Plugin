package jp.kps8x9.middle2.kps8x9m2gameplugin.Event;

import jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.MHGame;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.ShopItem;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.ShopKeeper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SetShopItems implements Listener {
    private final KPS8x9M2gamePlugin plg;
    private final MHGame mhGame;
    private static ShopKeeper shopKeeper;
    private Inventory editmodeinv;//編集インベントリ
    private HashMap<Player,Inventory> priceinvs=new HashMap<>();//値段変更インベントリ
    private HashMap<Player,ItemStack> clickitmdata=new HashMap<>();//選択したアイテムの保存
    private ItemMeta pricem,deletem;//値段変更、削除
    private ItemStack price_ok;//値段変更完了ボタン
    private ItemStack[] numbers=new ItemStack[10];

    private List<Player> click=new ArrayList<>();//エンティティークリックイベントが２回発動されるのでそれを防ぐ

    public SetShopItems(KPS8x9M2gamePlugin plg){
        this.plg=plg;
        this.mhGame=MHGame.getInstance();

        mhGame.editInv= Bukkit.createInventory(null,54, ChatColor.GREEN+"編集モード");

        ItemStack pricechange=new ItemStack(Material.YELLOW_CONCRETE);
        pricem=pricechange.getItemMeta();
        pricem.setDisplayName("値段変更");
        pricechange.setItemMeta(pricem);

        ItemStack delete=new ItemStack(Material.RED_CONCRETE);
        deletem=pricechange.getItemMeta();
        deletem.setDisplayName("削除");
        delete.setItemMeta(deletem);

        price_ok=new ItemStack(Material.DIAMOND_BLOCK);
        ItemMeta price_okm=price_ok.getItemMeta();
        price_okm.setDisplayName("完了");
        price_ok.setItemMeta(price_okm);

        for(int i=0;i<numbers.length;i++){
            ItemStack number=new ItemStack(Material.LIME_CONCRETE);
            ItemMeta numbermeta=number.getItemMeta();
            numbermeta.setDisplayName(String.valueOf(i));
            number.setItemMeta(numbermeta);
            numbers[i]=number;
        }

        editmodeinv=Bukkit.createInventory(null,9,ChatColor.GREEN+"モードを選択");
        editmodeinv.setItem(3,pricechange);
        editmodeinv.setItem(6,delete);
    }

    public static void setShopKeeper(ShopKeeper keeper){
        shopKeeper=keeper;
    }

    @EventHandler
    public void Click(PlayerInteractEntityEvent e){
        if(e.getRightClicked()!=shopKeeper.getKeeper()) return;
        Player p=e.getPlayer();
        if(p.isOp()&&p.isSneaking()) {//スニークしているかどうか
            if(p.getInventory().getItemInMainHand().getType()!=Material.AIR){//アイテムの追加かどうか
                if(!click.contains(p)) {
                    shopKeeper.addItem(new ShopItem[]{
                            new ShopItem(p.getInventory().getItemInMainHand(), 0)
                    });
                    click.add(p);
                }else{
                    click.remove(p);
                }
            }else {
                //編集インベントリの更新
                mhGame.editInv.clear();
                ItemStack[] itms = new ItemStack[shopKeeper.getShopItems().size()];
                for (int i = 0; i < shopKeeper.getShopItems().size(); i++) {
                    itms[i] = shopKeeper.getShopItems().get(i).getItemStack();
                }
                mhGame.editInv.addItem(itms);
                p.openInventory(mhGame.editInv);
            }
        }
    }

    @EventHandler
    public void invclick(InventoryClickEvent e){
        if(e.getCurrentItem()==null||e.getCurrentItem().getType()== Material.AIR) return;
        Player p=(Player) e.getWhoClicked();
        if(e.getClickedInventory().equals(mhGame.editInv)){//編集インベントリ
            e.setCancelled(true);
            clickitmdata.put(p,e.getCurrentItem());//選択したアイテムを保存
            p.closeInventory();
            p.openInventory(editmodeinv);
        }else if(e.getClickedInventory().equals(editmodeinv)){//編集モード選択インベントリ
            e.setCancelled(true);
            ItemMeta meta=e.getCurrentItem().getItemMeta();

            if(meta.equals(pricem)){//値段変更
                p.closeInventory();
                //インベントリ用意
                Inventory priceinv=Bukkit.createInventory(null,54,ChatColor.GREEN+"値段を設定");
                int index=0;
                for(int y=18;y<=36;y+=9){
                    for(int x=0;x<=2;x++){
                        priceinv.setItem(y+x,numbers[index].clone());
                        index++;
                    }
                }
                priceinv.setItem(45,numbers[9].clone());
                priceinv.setItem(33,price_ok.clone());
                for (int x=0;x<=8;x++){
                    priceinv.setItem(x,new ItemStack(Material.RED_STAINED_GLASS_PANE));
                }
                priceinvs.put(p,priceinv);
                p.openInventory(priceinv);
            }else if(meta.equals(deletem)){//削除
                shopKeeper.removeItem(shopKeeper.getShopItem(clickitmdata.get(p)));
                p.closeInventory();
            }
        }else if(priceinvs.containsKey(p)&&e.getClickedInventory().equals(priceinvs.get(p))){//値段変更インベントリ
            e.setCancelled(true);
            ItemMeta meta=e.getCurrentItem().getItemMeta();

            if(meta.equals(price_ok.getItemMeta())){//値段変更完了ボタン
                Inventory inv=e.getClickedInventory();
                StringBuilder price_str=new StringBuilder("0");//何も入力されてない場合の0
                //値を保存
                for(int i=0;i<=8;i++){
                    if(inv.getItem(i)==null||inv.getItem(i).getType()==Material.AIR) continue;
                    price_str.append(inv.getItem(i).getItemMeta().getDisplayName());
                }
                //値段を変更
                shopKeeper.getShopItem(clickitmdata.get(p)).setPrice(Integer.parseInt(price_str.toString()));
                //インベントリを更新
                shopKeeper.updateInventory();
                p.closeInventory();
            }else {//その他
                ItemMeta[] numbermeta = new ItemMeta[numbers.length];
                for (int i = 0; i < numbers.length; i++) {
                    numbermeta[i] = numbers[i].getItemMeta();
                }
                if (Arrays.asList(numbermeta).contains(meta)) {//数字かどうか
                    addDigit(priceinvs.get(p), e.getCurrentItem().clone());//数を加える
                }
            }
        }
    }

    private void addDigit(Inventory inv,ItemStack num){
        ItemStack[] digits=new ItemStack[9];
        //数を全て１つ左に寄せる
        for(int i=1;i<=8;i++){
            if(inv.getItem(i)==null||inv.getItem(i).getType()==Material.AIR) continue;
            digits[i-1]=inv.getItem(i);
        }
        //一の位を保存
        digits[8]=num;
        //更新
        for (int i=0;i<=8;i++){
            inv.setItem(i,digits[i]);
        }
    }

    @EventHandler
    public void join(PlayerJoinEvent e){
        mhGame.setCoin(e.getPlayer(),100);
    }
}
