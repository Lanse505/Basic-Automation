package mod.lanse505.basicautomation.common.utils;

import mod.lanse505.basicautomation.BasicAutomation;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Config {
    public static void preInit(FMLPreInitializationEvent event) {
    }

    @net.minecraftforge.common.config.Config(modid = BasicAutomation.MODID)
    public static class Configs {
        public static Blocks blocks;
        public static Utils utils;

        public static class Blocks {
            @net.minecraftforge.common.config.Config.Comment("Enable the Mob Grinder?")
            public static boolean mobGrinder = true;

            @net.minecraftforge.common.config.Config.Comment("Enable the Auto-Shear?")
            public static boolean autoShear = true;

            @net.minecraftforge.common.config.Config.Comment("Enable the Auto-Milker?")
            public static boolean autoMilker = true;
        }

        public static class Utils {
            @net.minecraftforge.common.config.Config.Comment("Determines the range of the Mob Grinder: " + "[1 = 3x3, 2 = 5x5, 3 = 7x7, 4 = 9x9]")
            @net.minecraftforge.common.config.Config.RangeInt(min = 1, max = 4)
            public static int rangeMG = 1;

            @net.minecraftforge.common.config.Config.Comment("Determines how often the Grinder will /Attack/ mobs: " + "[Set in Ticks: 20 Ticks = 1 Second]")
            @net.minecraftforge.common.config.Config.RangeInt(min = 1, max = Integer.MAX_VALUE)
            public static int speedMG = 20;

            @net.minecraftforge.common.config.Config.Comment("Determines the range of the Auto-Shear: " + "[1 = 3x3, 2 = 5x5, 3 = 7x7, 4 = 9x9]")
            @net.minecraftforge.common.config.Config.RangeInt(min = 1, max = 4)
            public static int rangeAS = 1;

            @net.minecraftforge.common.config.Config.Comment("Determines how often the Auto-Shear will /Shear/ Sheep: " + "[Set in Ticks: 20 Ticks = 1 Second]")
            @net.minecraftforge.common.config.Config.RangeInt(min = 1, max = Integer.MAX_VALUE)
            public static int speedAS = 20;
        }
    }
}
