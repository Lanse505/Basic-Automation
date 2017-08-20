package mod.lanse505.basicautomation.common.items;

import mod.lanse505.basicautomation.BasicAutomation;
import mod.lanse505.basicautomation.common.utils.CreativeTab;
import net.minecraft.item.Item;

public class ItemBag extends Item {
    private int SIZE = 27;

    public ItemBag() {
        setMaxStackSize(1);
        setCreativeTab(CreativeTab.basicAutomation);
        setUnlocalizedName(BasicAutomation.MODID + ".itembag");
        setRegistryName("itembag");
    }

    public void initModel() {
        BasicAutomation.proxy.registerItemRenderer(this, 0, "itemBag");
    }
}
