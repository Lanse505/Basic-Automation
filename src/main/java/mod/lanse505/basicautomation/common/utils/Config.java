package mod.lanse505.basicautomation.common.utils;

import mod.lanse505.basicautomation.BasicAutomation;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Config {
    public static void preInit(FMLPreInitializationEvent event) {
    }

    @net.minecraftforge.common.config.Config(modid = BasicAutomation.MODID)
    public static class Configs {
        public static Blocks blocks;
        public static Utils utils;

        public static class Blocks {
            @Comment("Enable the Mob Grinder?")
            public static boolean mobGrinder = true;

            @Comment("Enable the Auto-Shear?")
            public static boolean autoShear = true;

            @Comment("Enable the Auto-Milker?")
            public static boolean autoMilker = true;
        }

        public static class Utils {
            @Comment("Determines the range of the Mob Grinder: " + "[1 = 3x3, 2 = 5x5, 3 = 7x7, 4 = 9x9]")
            @RangeInt(min = 1, max = 4)
            public static int rangeMG = 1;

            @Comment("Determines how often the Grinder will /Attack/ mobs: " + "[Set in Ticks: 20 Ticks = 1 Second]")
            @RangeInt(min = 1)
            public static int speedMG = 20;

            @Comment("Determines the range of the Auto-Shear: " + "[1 = 3x3, 2 = 5x5, 3 = 7x7, 4 = 9x9]")
            @RangeInt(min = 1, max = 4)
            public static int rangeAS = 1;

            @Comment("Determines how often the Auto-Shear will /Shear/ Sheep: " + "[Set in Ticks: 20 Ticks = 1 Second]")
            @RangeInt(min = 1)
            public static int speedAS = 20;

            @Comment("Determines the range of the Auto-Shear: " + "[1 = 3x3, 2 = 5x5, 3 = 7x7, 4 = 9x9]")
            @RangeInt(min = 1, max = 4)
            public static int rangeAM = 1;

            @Comment("Determines how often the Auto-Shear will /Shear/ Sheep: " + "[Set in Ticks: 20 Ticks = 1 Second]")
            @RangeInt(min = 1)
            public static int speedAM = 20;
        }
    }
}
