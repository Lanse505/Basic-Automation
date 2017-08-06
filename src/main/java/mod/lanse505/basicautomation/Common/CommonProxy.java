package mod.lanse505.basicautomation.common;

import mod.lanse505.basicautomation.BasicAutomation;
import mod.lanse505.basicautomation.common.blocks.ModBlocks;

import mod.lanse505.basicautomation.common.blocks.blockAutoMilker;
import mod.lanse505.basicautomation.common.blocks.blockAutoShear;
import mod.lanse505.basicautomation.common.blocks.blockMobGrinder;
import mod.lanse505.basicautomation.common.tiles.tileAutoMilker;
import mod.lanse505.basicautomation.common.tiles.tileAutoShear;
import mod.lanse505.basicautomation.common.tiles.tileMobGrinder;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
    }

    public void init(FMLInitializationEvent e) {
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event){
    event.getRegistry().register(new blockAutoMilker());
    event.getRegistry().register(new blockAutoShear());
    event.getRegistry().register(new blockMobGrinder());

    GameRegistry.registerTileEntity(tileAutoMilker.class, BasicAutomation.MODID + "_automilker");
    GameRegistry.registerTileEntity(tileAutoShear.class, BasicAutomation.MODID + "_autoshear");
    GameRegistry.registerTileEntity(tileMobGrinder.class, BasicAutomation.MODID + "_mobgrinder");
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event){
    event.getRegistry().register(new ItemBlock(basicautomation.autoshear).setRegistryName(ModBlocks.autoshear.getRegistryName()));
    event.getRegistry().register(new ItemBlock(basicautomation.automilker).setRegistryName(ModBlocks.automilker.getRegistryName()));
    event.getRegistry().register(new ItemBlock(basicautomation.mobgrinder).setRegistryName(ModBlocks.mobgrinder.getRegistryName()));
    }
}
