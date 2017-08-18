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

public class TileAutoMilker extends TileEntity implements ITickable {
    public static int SIZE = 2;
    private int config = Config.Configs.Utils.speedAS;
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
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHandler);
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            currentCount--;
            if (currentCount == 0) {
                ItemStack slot0 = this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).getStackInSlot(0);
                ItemStack slot1 = this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).getStackInSlot(1);
                int count0 = slot0.getCount();

                //Get The Entity
                List<EntityCow> list = world.getEntitiesWithinAABB(EntityCow.class, new AxisAlignedBB(pos).expand(Config.Configs.Utils.rangeAS, Config.Configs.Utils.rangeAS, Config.Configs.Utils.rangeAS));

                //Iterate through the list
                for (EntityCow entity : list) {
                    if (!entity.isDead) {
                        if (slot0.getItem() instanceof ItemBucket) {
                            if (slot1.getCount() == 0) {
                                slot0.setCount(slot0.getCount() - 1);
                                this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).insertItem(1, new ItemStack(Items.MILK_BUCKET), false);
                            }
                        }
                    }
                }
            }
            //Resets the Timer
            currentCount = config;
        }
    }
}
