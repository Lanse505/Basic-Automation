package mod.lanse505.basicautomation.common.tiles;

import mod.lanse505.basicautomation.common.utils.Config;
import mod.lanse505.basicautomation.common.utils.ItemStackHandlerTile;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
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
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
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
            if (currentCount <= 0) {
                System.out.println("1");
                BlockPos pos = new BlockPos(this.pos);
                System.out.println("2");
                ItemStack itemStack = itemStackHandler.getStackInSlot(0);
                System.out.println("3");
                List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos).grow(Config.Configs.Utils.rangeAS, Config.Configs.Utils.rangeAS + 1, Config.Configs.Utils.rangeAS));

                System.out.println("4");
                for (EntityLivingBase entity : list) {
                    System.out.println("5");
                    if (entity instanceof IShearable) {
                        System.out.println("6");
                        IShearable shearable = (IShearable) entity;
                        System.out.println("7");
                        if (shearable.isShearable(itemStack, world, pos)) {
                            System.out.println("8");
                            Random rand = new Random();
                            System.out.println("9");
                            BlockPos posE = new BlockPos(entity.posX, entity.posY, entity.posZ);
                            System.out.println("10");
                            int Fortune = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, itemStack);
                            System.out.println("11");
                            List<ItemStack> drops = shearable.onSheared(itemStack, world, posE, Fortune);
                            System.out.println("12");
                            for (ItemStack stack : drops) {
                                System.out.println("13");
                                EntityItem ent = entity.entityDropItem(stack, 1.0F);
                                System.out.println("14");
                                ent.motionY += rand.nextFloat() * 0.05F;
                                System.out.println("15");
                                ent.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                                System.out.println("16");
                                ent.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.1F;
                            }
                            System.out.println("17");
                            itemStack.damageItem(1, entity);
                        }
                    }
                    //Resets the Timer
                    currentCount = config;
                }
            }
        }
        this.markDirty();
    }
}