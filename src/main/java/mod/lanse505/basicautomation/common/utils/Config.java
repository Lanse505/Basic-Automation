package mod.lanse505.basicautomation.common.utils;

import mod.lanse505.basicautomation.BasicAutomation;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Config {

    @net.minecraftforge.common.config.Config(modid = BasicAutomation.MODID)
    public static class Configs {

        public static General general;
        public static Blocks blocks;
        public static Utils utils;

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

        public static class Utils {
            @net.minecraftforge.common.config.Config.Comment("Determines the range of the Mob Grinder: " + "[1 = 3x3, 2 = 5x5, 3 = 7x7, 4 = 9x9]")
            @net.minecraftforge.common.config.Config.RangeInt(min = 1, max = 4)
            public static int rangeMG = 1;

            @net.minecraftforge.common.config.Config.Comment("Determines how often the Grinder will /Attack/ mobs")
            @net.minecraftforge.common.config.Config.RangeInt(min = 1, max = 20)
            public static int speedMG = 1;

            @net.minecraftforge.common.config.Config.Comment("Determines the range of the Auto-Shear: " + "[1 = 3x3, 2 = 5x5, 3 = 7x7, 4 = 9x9]")
            @net.minecraftforge.common.config.Config.RangeInt(min = 1, max = 4)
            public static int rangeAS = 1;
        }
    }

    public static void preInit(FMLPreInitializationEvent event) {
    }
}
