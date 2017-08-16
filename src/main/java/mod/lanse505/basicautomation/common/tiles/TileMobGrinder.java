package mod.lanse505.basicautomation.common.tiles;

import com.mojang.authlib.GameProfile;
import mod.lanse505.basicautomation.common.utils.Config;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeMap;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
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

import java.util.List;
import java.util.UUID;

public class TileMobGrinder extends TileEntity implements ITickable {
    public static final int SIZE = 1;
    public static int config = Config.Configs.Utils.speedMG;
    public static int currentCount = config;
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

    @SuppressWarnings("unchecked")
    public void update() {
        if (!world.isRemote) {
            currentCount--;
            if (currentCount == 0) {
                EntityPlayerMP mobGrinder = FakePlayerFactory.get((WorldServer) world, new GameProfile(UUID.nameUUIDFromBytes(new TextComponentTranslation("fakeplayer.basicautomation.mob.grinder").getFormattedText().getBytes()), new TextComponentTranslation("fakeplayer.basicautomation.mob_grinder").getFormattedText()));
                ItemStack weapon = this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).getStackInSlot(0);
                mobGrinder.setPosition(this.pos.getX(), -2D, this.pos.getZ());
                mobGrinder.setHeldItem(EnumHand.MAIN_HAND, weapon);
                List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos).expand(Config.Configs.Utils.rangeMG, Config.Configs.Utils.rangeMG, Config.Configs.Utils.rangeMG));
                for (int i = 0; i < list.size(); i++) {
                    Entity mob = list.get(i);
                    if (mob != null && !mob.isDead) {
                        causePlayerDamage(mobGrinder, mob);
                    }
                }
                currentCount = config;
            }
        }
    }

    ////////////////////////////////////////////////////////////
    //Huge Thanks to @Darkhax for providing me with this code!//
    ////////////////////////////////////////////////////////////
    public void causePlayerDamage(EntityPlayer source, Entity target) {
        if (source != null && target != null) {
            //Handles base damage.
            source.attackTargetEntityWithCurrentItem(target);

            //Gets the damage for the held item.
            final IAttributeInstance weaponDamage = new AttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);

            if (!source.getHeldItemMainhand().isEmpty()) {
                for (AttributeModifier modifier : source.getHeldItemMainhand().getAttributeModifiers(EntityEquipmentSlot.MAINHAND).get(SharedMonsterAttributes.ATTACK_DAMAGE.getName())) {
                    weaponDamage.applyModifier(modifier);
                }
            }

            float damage = (float) (weaponDamage.getAttributeValue() + ((target instanceof EntityLivingBase) ? EnchantmentHelper.getModifierForCreature(source.getHeldItemMainhand(), ((EntityLivingBase) target).getCreatureAttribute()) : 0f));
            target.attackEntityFrom(DamageSource.causePlayerDamage(source), damage);
        }
    }
}

class ItemStackHandlerTile extends ItemStackHandler {
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