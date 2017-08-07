package mod.lanse505.basicautomation.common.blocks;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {
    @GameRegistry.ObjectHolder("basicautomation:automilker")
    public static blockAutoMilker automilker;

    @GameRegistry.ObjectHolder("basicautomation:autoshear")
    public static blockAutoShear autoshear;

    @GameRegistry.ObjectHolder("basicautomation:mobgrinder")
    public static blockMobGrinder mobgrinder;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        automilker.initModel();
        autoshear.initModel();
        mobgrinder.initModel();
    }
}
