package mod.lanse505.basicautomation.common.utils;

import mod.lanse505.basicautomation.BasicAutomation;
import mod.lanse505.basicautomation.common.blocks.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTab extends CreativeTabs {

    public static final CreativeTab basicAutomation = new CreativeTab();

    public CreativeTab(){
        super(BasicAutomation.MODID);
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ModBlocks.mobgrinder);
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }

    @Override
    public CreativeTabs setBackgroundImageName(String texture) {
       return super.setBackgroundImageName("tab_item_search.png");
    }
}
