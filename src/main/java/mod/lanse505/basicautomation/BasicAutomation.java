package mod.lanse505.basicautomation;

import mod.lanse505.basicautomation.common.CommonProxy;
import mod.lanse505.basicautomation.common.utils.Config;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

@Mod(modid = BasicAutomation.MODID, name = BasicAutomation.NAME, version = BasicAutomation.VERSION, dependencies = BasicAutomation.DEP, acceptedMinecraftVersions = BasicAutomation.MCVER)
public class BasicAutomation {
    public static final String MODID = "basicautomation";
    public static final String NAME = "basicautomation";
    public static final String VERSION = "1.12-0.0.0.0-Alpha";
    public static final String MCVER = "1.12.0";
    public static final String DEP = "";

    @SidedProxy(clientSide = "mod.lanse505.basicautomation.client.ClientProxy", serverSide = "mod.lanse505.basicautomation.server.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static BasicAutomation instance;
    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
        Config.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        logger.log(Level.INFO, "Hello World");
    }
}
