package mod.lanse505.basicautomation.utils;

import mod.lanse505.basicautomation.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;

import java.io.File;

import static mod.lanse505.basicautomation.BasicAutomation.logger;

public class Config {
    private static final String CATEGORY_GENERAL = "General";
    private static final String CATEGORY_UTILS = "Utilities";
    private static final String CATEGORY_BLOCKS = "Blocks";

    public static int rangeMG;
    public static boolean mobGrinder;
    public static int rangeAS;
    public static boolean autoShear;


    public static void readConfig(){
        Configuration cfg = CommonProxy.config;
        try{
            cfg.load();
            initGeneralConfig(cfg);
            initBlockConfig(cfg);
            initUtilConfig(cfg);
        } catch (Exception e1){
            logger.log(Level.ERROR, "Problem Loading Configuration File");
        } finally {
            if (cfg.hasChanged()){
                cfg.save();
            }
        }
    }

    public static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General Configuration");
    }

    private static void initBlockConfig(Configuration cfg){
        cfg.addCustomCategoryComment(CATEGORY_BLOCKS, "Block Configurations");
        mobGrinder = cfg.getBoolean("mobGrinder", CATEGORY_BLOCKS, true, "Enable Mob Grinder");
        autoShear = cfg.getBoolean("autoShear", CATEGORY_BLOCKS, true, "Enable Auto-Shear");
    }

    private static void initUtilConfig(Configuration cfg){
        cfg.addCustomCategoryComment(CATEGORY_UTILS, "Utility Configurations");
        rangeMG = cfg.getInt("rangeMG", CATEGORY_UTILS, 1, 1, 4, "How many blocks the Mob Grinder should check in XZ");
        rangeAS = cfg.getInt("rangeAS", CATEGORY_UTILS, 1, 1, 4, "How many blocks the Auto-Shear should check in XZ");
    }
}
