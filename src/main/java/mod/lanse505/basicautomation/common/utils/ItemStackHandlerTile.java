package mod.lanse505.basicautomation.common.utils;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.ItemStackHandler;

public class ItemStackHandlerTile extends ItemStackHandler {
    TileEntity tile;

    public ItemStackHandlerTile(TileEntity tile, int size) {
        super(size);
        this.tile = tile;
    }

    @Override
    protected void onContentsChanged(int SLOT) {
        tile.markDirty();
    }
}
