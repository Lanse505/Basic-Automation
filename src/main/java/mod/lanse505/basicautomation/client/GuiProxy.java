package mod.lanse505.basicautomation.client;

import mod.lanse505.basicautomation.client.gui.GuiAutoShear;
import mod.lanse505.basicautomation.client.gui.GuiMobGrinder;
import mod.lanse505.basicautomation.common.container.AutoShearContainer;
import mod.lanse505.basicautomation.common.container.MobGrinderContainer;
import mod.lanse505.basicautomation.common.tiles.TileAutoMilker;
import mod.lanse505.basicautomation.common.tiles.TileAutoShear;
import mod.lanse505.basicautomation.common.tiles.TileMobGrinder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TileAutoShear) {
            return new AutoShearContainer(player.inventory, (TileAutoShear) te);
        }

        if (te instanceof TileMobGrinder) {
            return new MobGrinderContainer(player.inventory, (TileMobGrinder) te);
        }

        if (te instanceof TileAutoMilker) {
        }

        return null;
    }


    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TileAutoShear) {
            TileAutoShear AutoShearContainer = (TileAutoShear) te;
            return new GuiAutoShear(AutoShearContainer, new AutoShearContainer(player.inventory, AutoShearContainer));
        }

        if (te instanceof TileMobGrinder) {
            TileMobGrinder MobGrinderContainer = (TileMobGrinder) te;
            return new GuiMobGrinder(MobGrinderContainer, new MobGrinderContainer(player.inventory, MobGrinderContainer));
        }

        if (te instanceof TileAutoMilker) {
        }

        return null;
    }
}