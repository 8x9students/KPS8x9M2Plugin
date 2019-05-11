package jp.kps8x9.middle2.kps8x9m2gameplugin.MHcommand;

import jp.kps8x9.middle2.kps8x9m2gameplugin.KPS8x9M2gamePlugin;
import jp.kps8x9.middle2.kps8x9m2gameplugin.MHCommand;
import jp.kps8x9.middle2.kps8x9m2gameplugin.util.ClassUtil;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MH_scouter {
    private final KPS8x9M2gamePlugin plg;
    private final MHCommand cmd;

    public MH_scouter(KPS8x9M2gamePlugin plg, MHCommand cmd){
        this.plg=plg;
        this.cmd=cmd;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plg.getLogger().info(ClassUtil.getLogInfo());

        boolean ret = false;

        Inventory inventory = ((Player)sender).getInventory();
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

        ret = true;
        return ret;
    }
}
