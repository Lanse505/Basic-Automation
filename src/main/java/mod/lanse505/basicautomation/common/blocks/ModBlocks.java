package mod.lanse505.basicautomation.common.blocks;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {
    @GameRegistry.ObjectHolder("basicautomation:automilker")
    public static BlockAutoMilkerS automilker;

    @GameRegistry.ObjectHolder("basicautomation:autoshear")
    public static BlockAutoShearS autoshear;

    @GameRegistry.ObjectHolder("basicautomation:mobgrinder")
    public static BlockMobGrinderS mobgrinder;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        automilker.initModel();
        autoshear.initModel();
        mobgrinder.initModel();
    }
}
