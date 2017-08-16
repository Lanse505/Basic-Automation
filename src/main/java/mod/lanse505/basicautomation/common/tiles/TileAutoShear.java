package mod.lanse505.basicautomation.common.tiles;

import mod.lanse505.basicautomation.common.utils.Config;
import mod.lanse505.basicautomation.common.utils.ItemStackHandlerTile;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.List;
import java.util.Random;

public class TileAutoShear extends TileEntity implements ITickable {

    public static int SIZE = 1;
    int config = Config.Configs.Utils.speedAS;
    int currentCount = config;

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
                BlockPos pos = new BlockPos(this.pos);

                //Get The Item
                ItemStack item = this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).getStackInSlot(0);

                //Get The Entity
                List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos).expand(Config.Configs.Utils.rangeAS, Config.Configs.Utils.rangeAS, Config.Configs.Utils.rangeAS));

                //Iterate through the list
                for (int i = 0; i < list.size(); i++) {
                    EntityLivingBase entity = list.get(i);
                    if (entity instanceof IShearable) {
                        IShearable shearable = (IShearable) entity;

                        //Check if the Item is an instanceof ItemShear
                        if (shearable.isShearable(item, world, pos)) {
                            Random rand = new java.util.Random();
                            BlockPos posE = new BlockPos(entity.posX, entity.posY, entity.posZ);
                            int Fortune = EnchantmentHelper.getEnchantmentLevel(net.minecraft.init.Enchantments.FORTUNE, item);

                            List<ItemStack> drops = shearable.onSheared(item, world, posE, Fortune);

                            for (ItemStack stack : drops) {
                                EntityItem ent = entity.entityDropItem(stack, 1.0F);
                                ent.motionY += rand.nextFloat() * 0.05F;
                                ent.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                                ent.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                            }
                            item.damageItem(1, entity);
                        }
                    }
                    //Resets the Timer
                    currentCount = config;
                }
            }
        }
    }
}
