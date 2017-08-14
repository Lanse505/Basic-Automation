package mod.lanse505.basicautomation.common.tiles;

import com.mojang.authlib.GameProfile;
import mod.lanse505.basicautomation.common.utils.Config;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemShears;
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
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

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

    @Override
    public void update() {
        if (!world.isRemote){
            currentCount--;
            if (currentCount == 0){
                //Create and Get The Fake Player
                EntityPlayerMP autoShear = FakePlayerFactory.get((WorldServer) world, new GameProfile(UUID.nameUUIDFromBytes(new TextComponentTranslation("fakeplayer.basicautomation.auto.shear").getFormattedText().getBytes()), new TextComponentTranslation("fakeplayer.basicautomation.auto_shear").getFormattedText()));

                //Get The Item
                ItemStack item = this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).getStackInSlot(0);

                //Set the Pos and HeldItem of the Fake Player
                autoShear.setPosition(this.pos.getX(), -2D, this.pos.getZ());
                autoShear.setHeldItem(EnumHand.MAIN_HAND, item);

                //Get The Sheep
                List<EntitySheep> list = world.getEntitiesWithinAABB(EntitySheep.class, new AxisAlignedBB(pos).expand(Config.Configs.Utils.rangeAS, Config.Configs.Utils.rangeAS, Config.Configs.Utils.rangeAS));

                //Iterate through the list
                for (int i = 0; i < list.size(); i++) {
                    Entity shearable = list.get(i);

                    //Check if Sheep are still there and alive
                    if (shearable != null && !shearable.isDead) {

                        //Check if the Item is an instanceof ItemShear
                        if (item.getItem() instanceof ItemShears) {
                            shearable.processInitialInteract(autoShear, EnumHand.MAIN_HAND);
                        }
                    }
                }
                //Resets the Timer
                currentCount = config;
            }
        }
    }
}