package mod.lanse505.basicautomation.common;

import mod.lanse505.basicautomation.common.gui.GuiMobGrinder;
import mod.lanse505.basicautomation.common.gui.MobGrinderContainer;
import mod.lanse505.basicautomation.common.tiles.TileMobGrinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TileMobGrinder){
            return new MobGrinderContainer(player.inventory, (TileMobGrinder) te);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if(te instanceof TileMobGrinder){
            TileMobGrinder MobGrinderContainer = (TileMobGrinder) te;
            return new GuiMobGrinder(MobGrinderContainer, new MobGrinderContainer(player.inventory, MobGrinderContainer));
        }
        return null;
    }
}