package mod.lanse505.basicautomation.common.tiles;

import java.util.List;
import java.util.UUID;

import com.mojang.authlib.GameProfile;

import mod.lanse505.basicautomation.common.utils.Config;
import mod.lanse505.basicautomation.common.utils.Config.Configs.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileMobGrinder extends TileEntity implements ITickable {
	public static int config = Config.Configs.Utils.speedMG;
	public static int currentCount = config;

	public static final int SIZE = 1;

	// Both unused
	// private static final int SLOT = 0;
	// private List<EntityLivingBase> list;

	// Not sure you can reference it like you were. Cleaner this way, at least.
	private ItemStackHandler itemStackHandler = new ItemStackHandlerTile(this, SIZE);

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		if(compound.hasKey("items")) {
			// Use typed getter
			itemStackHandler.deserializeNBT(compound.getCompoundTag("items"));
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
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHandler);
		}
		return super.getCapability(capability, facing);
	}

	@Override // For uniformity
	public void update() {
		if(!world.isRemote) {
			currentCount--;
			if(currentCount == 0) {
				
				ItemStack weapon =
						this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).getStackInSlot(0);
				EntityPlayerMP mobGrinder = FakePlayerFactory..get((WorldServer) world, new GameProfile(
						UUID.nameUUIDFromBytes(new TextComponentTranslation("fakeplayer.basicautomation.mob.grinder")
								.getFormattedText().getBytes()),
						new TextComponentTranslation("fakeplayer.basicautomation.mob_grinder").getFormattedText()));
				FMLLog.warning("" + mobGrinder.getCooledAttackStrength(0.5F));
				mobGrinder.setPosition(this.pos.getX(), -2D, this.pos.getZ());
				mobGrinder.setHeldItem(EnumHand.MAIN_HAND, weapon);
				// Equivalent but slightly neater
				List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class,
						new AxisAlignedBB(pos).expand(Utils.rangeMG, Utils.rangeMG, Utils.rangeMG));
				// Enhanced for loop - first arg is current item, second arg is what you're looping through
				for(EntityLivingBase living : list) {
					if(living != null && !living.isDead) {
						mobGrinder.attackTargetEntityWithCurrentItem(living);
						mobGrinder.resetCooldown();
					}
				}
				currentCount = config;
			}
		}
	}

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

}