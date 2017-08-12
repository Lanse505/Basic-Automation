package mod.lanse505.basicautomation.common;

import mod.lanse505.basicautomation.BasicAutomation;
import mod.lanse505.basicautomation.common.blocks.BlockMobGrinderS;
import mod.lanse505.basicautomation.common.blocks.ModBlocks;

import mod.lanse505.basicautomation.common.blocks.BlockAutoMilkerS;
import mod.lanse505.basicautomation.common.blocks.BlockAutoShearS;
import mod.lanse505.basicautomation.common.tiles.TileAutoMilkerS;
import mod.lanse505.basicautomation.common.tiles.TileAutoShearS;
import mod.lanse505.basicautomation.common.tiles.TileMobGrinderS;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static mod.lanse505.basicautomation.BasicAutomation.instance;

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
    }

    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiProxy());
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event){
    event.getRegistry().register(new BlockAutoMilkerS());
    event.getRegistry().register(new BlockAutoShearS());
    event.getRegistry().register(new BlockMobGrinderS());

    GameRegistry.registerTileEntity(TileAutoMilkerS.class, BasicAutomation.MODID + "_automilker");
    GameRegistry.registerTileEntity(TileAutoShearS.class, BasicAutomation.MODID + "_autoshear");
    GameRegistry.registerTileEntity(TileMobGrinderS.class, BasicAutomation.MODID + "_mobgrinder");
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event){
    event.getRegistry().register(new ItemBlock(ModBlocks.autoshear).setRegistryName(ModBlocks.autoshear.getRegistryName()));
    event.getRegistry().register(new ItemBlock(ModBlocks.automilker).setRegistryName(ModBlocks.automilker.getRegistryName()));
    event.getRegistry().register(new ItemBlock(ModBlocks.mobgrinder).setRegistryName(ModBlocks.mobgrinder.getRegistryName()));
    }
}
