package mod.lanse505.basicautomation.common.tiles;

import com.mojang.authlib.GameProfile;
import mod.lanse505.basicautomation.common.utils.Config;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.List;
import java.util.UUID;

public class TileMobGrinder extends TileEntity implements ITickable {
    public static final int SIZE = 1;
    private static final int[] SLOTS = new int[] {0};

    @SuppressWarnings("unchecked")
    private List<EntityLivingBase> list;

    private ItemStackHandler itemStackHandler = new ItemStackHandler(SIZE){
        protected void onContentChanged(int slot){
            TileMobGrinder.this.markDirty();
        }
    };

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
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
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

    @SuppressWarnings("unchecked")
    public void update() {
        if (!world.isRemote) {
            list = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos.getX() - Config.Configs.Utils.rangeMG, pos.getY(), pos.getZ() - Config.Configs.Utils.rangeMG, pos.getX() + 1 + Config.Configs.Utils.rangeMG, pos.getY() + 3 + Config.Configs.Utils.rangeMG, pos.getZ() + 1 + Config.Configs.Utils.rangeMG));
            targetForBlock();
        }
    }

    protected Entity targetForBlock(){
        for (int i = 0; i < list.size(); i++) {
            Entity mob = list.get(i);
            if (mob != null) {
                if (mob instanceof EntityLivingBase) {
                    EntityPlayerMP mobGrinder = FakePlayerFactory.get((WorldServer) world, new GameProfile(UUID.nameUUIDFromBytes(new TextComponentTranslation("fakeplayer.basicautomation.mob.grinder").getFormattedText().getBytes()), new TextComponentTranslation("fakeplayer.basicautomation.mob_grinder").getFormattedText()));
                    mobGrinder.setPosition(this.pos.getX(), -2D, this.pos.getZ());
                    //ItemStack weapon = new ItemStack(, 1, 0);
                    //mobGrinder.setHeldItem(EnumHand.MAIN_HAND, weapon);
                    mobGrinder.attackTargetEntityWithCurrentItem(mob);
                    mobGrinder.resetCooldown();
                }
            }
        }
        return null;
    }
}