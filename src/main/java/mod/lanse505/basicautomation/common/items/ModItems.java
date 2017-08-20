package mod.lanse505.basicautomation.common.items;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems {
    @GameRegistry.ObjectHolder("basicautomation:itembag")
    public static ItemBag itemBag;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        itemBag.initModel();
    }
}
