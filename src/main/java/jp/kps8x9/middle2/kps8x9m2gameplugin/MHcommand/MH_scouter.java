package jp.kps8x9.middle2.kps8x9m2gameplugin.MHcommand;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MH_scouter {
    public MH_scouter(Player player){
        Inventory inventory = player.getInventory();
        ItemStack scouter = new ItemStack(Material.PRISMARINE_SHARD);
        ItemMeta im = scouter.getItemMeta();
        assert im != null;
        List<String> lores = new ArrayList<>();
        lores.add("プレイヤーの戦闘力を測ることができます。");
        im.setDisplayName("スカウター");
        im.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,4,true);
        im.setLore(lores);
        scouter.setItemMeta(im);
        int emptyslot = inventory.firstEmpty();
        inventory.setItem(emptyslot,scouter);
    }
}
