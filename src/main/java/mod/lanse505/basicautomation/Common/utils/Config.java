package mod.lanse505.basicautomation.Common.utils;

import mod.lanse505.basicautomation.BasicAutomation;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Config {
    @net.minecraftforge.common.config.Config(modid = BasicAutomation.MODID)
    public static class Configs{
        public static General General;
        public static Blocks Blocks;
        public static Utils Utils;

        public static class General {
            @net.minecraftforge.common.config.Config.Comment("General Configs")
            public static boolean allBlocks = true;
        }

        public static class Blocks {
            @net.minecraftforge.common.config.Config.Comment("Enable the Mob Grinder?")
            public static boolean mobGrinder = true;

            @net.minecraftforge.common.config.Config.Comment("Enable the Auto-Shear?")
            public static boolean autoShear = true;
        }

        public static class Utils{
            @net.minecraftforge.common.config.Config.Comment("Determines the range of the Mob Grinder")
            @net.minecraftforge.common.config.Config.RangeInt(min = 1, max = 4)
            public static int rangeMG = 1;

            @net.minecraftforge.common.config.Config.Comment("Determines the range of the Auto-Shear")
            @net.minecraftforge.common.config.Config.RangeInt(min = 1, max = 4)
            public static int rangeAS = 1;
        }
    }


    public static void preInit(FMLPreInitializationEvent event) {
    }
}
