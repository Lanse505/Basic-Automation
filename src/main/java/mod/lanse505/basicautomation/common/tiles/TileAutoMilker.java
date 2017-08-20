package mod.lanse505.basicautomation.common.tiles;

import mod.lanse505.basicautomation.common.utils.Config;
import mod.lanse505.basicautomation.common.utils.ItemStackHandlerTile;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.List;

public class TileAutoMilker extends TileEntity implements ITickable{
    public static int SIZE = 2;
    private int config = Config.Configs.Utils.speedAM;
    private int currentCount = config;
    private ItemStackHandler itemStackHandler = new ItemStackHandlerTile(this, SIZE);



    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("items")) {
            itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("items", itemStackHandler.serializeNBT());
        return compound;
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        return !isInvalid() && playerIn.getDistanceSqToCenter(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            if (facing != EnumFacing.DOWN && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
                return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (facing == EnumFacing.DOWN && capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ) {
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHandler);
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            currentCount--;
            if (currentCount == 0) {
                ItemStack slot0 = this.itemStackHandler.getStackInSlot(0);
                ItemStack slot1 = this.itemStackHandler.getStackInSlot(1);
                List<EntityCow> list = world.getEntitiesWithinAABB(EntityCow.class, new AxisAlignedBB(pos).grow(Config.Configs.Utils.rangeAM, Config.Configs.Utils.rangeAM + 1, Config.Configs.Utils.rangeAM));

                for (EntityCow entity : list) {
                    if (!entity.isDead) {
                        if (slot0.getItem() instanceof ItemBucket) {
                            if (slot1.isEmpty()) {
                                slot0.shrink(1);
                                this.itemStackHandler.setStackInSlot(1, new ItemStack(Items.MILK_BUCKET));
                            }
                        }
                    }
                }
                //Resets the Timer
                currentCount = config;
            }
        }
        this.markDirty();
    }
}
