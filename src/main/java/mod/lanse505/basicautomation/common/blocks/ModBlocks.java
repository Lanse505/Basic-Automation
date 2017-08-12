package mod.lanse505.basicautomation.common.blocks;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {
    @GameRegistry.ObjectHolder("basicautomation:automilker")
    public static BlockAutoMilker automilker;

    @GameRegistry.ObjectHolder("basicautomation:autoshear")
    public static BlockAutoShear autoshear;

    @GameRegistry.ObjectHolder("basicautomation:mobgrinder")
    public static BlockMobGrinder mobgrinder;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        automilker.initModel();
        autoshear.initModel();
        mobgrinder.initModel();
    }
}
