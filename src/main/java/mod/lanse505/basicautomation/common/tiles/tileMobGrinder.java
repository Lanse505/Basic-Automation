package mod.lanse505.basicautomation.common.tiles;

import com.mojang.authlib.GameProfile;
import mod.lanse505.basicautomation.common.utils.Config;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;

import java.util.List;
import java.util.UUID;

public class TileMobGrinder extends TileEntity implements ITickable {
    private static int confRange = Config.Configs.Utils.rangeMG;
    private static final int[] SLOTS = new int[] {0};

    @SuppressWarnings("unchecked")
    private List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + confRange, pos.getY() + 2D, pos.getZ() + confRange));


    public void update() {
        targetForBlock();
        list = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + confRange, pos.getY() + 2D, pos.getZ() + confRange));

    }

    protected Entity targetForBlock(){
        for (int i = 0; i < list.size(); i++) {
            Entity mob = list.get(i);
            if (mob != null) {
                if (mob instanceof EntityLivingBase) {
                    EntityPlayerMP mobGrinder = FakePlayerFactory.get((WorldServer) world, new GameProfile(UUID.nameUUIDFromBytes(new TextComponentTranslation("fakeplayer.basicautomation.mob.grinder").getFormattedText().getBytes()), new TextComponentTranslation("fakeplayer.basicautomation.mob_grinder").getFormattedText()));
                    mobGrinder.setPosition(this.pos.getX(), -2D, this.pos.getZ());
                    ItemStack weapon = new ItemStack(, 1, 0);
                    mobGrinder.setHeldItem(EnumHand.MAIN_HAND, weapon);
                    mobGrinder.attackTargetEntityWithCurrentItem(mob);
                    mobGrinder.resetCooldown();
                }
            }
        }
    }
}