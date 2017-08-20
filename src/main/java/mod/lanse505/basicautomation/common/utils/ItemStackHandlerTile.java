package mod.lanse505.basicautomation.common.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class ItemStackHandlerTile extends ItemStackHandler {
    private TileEntity tile;

    public ItemStackHandlerTile(TileEntity tile, int size) {
        super(size);
        this.tile = tile;
    }

    @Override
    protected void onContentsChanged(int SLOT) {
        tile.markDirty();
    }
}
