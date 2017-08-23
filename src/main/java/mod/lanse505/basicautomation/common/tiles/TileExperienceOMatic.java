package mod.lanse505.basicautomation.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileExperienceOMatic extends TileEntity implements ITickable {
    public static final int SIZE = 1;
    //    public NBTTagInt experience = new NBTTagInt();
    public int IncValue;

//    @Override
//    public void readFromNBT(NBTTagCompound compound) {
//        super.readFromNBT(compound);
//        compound.getInteger(experience);
//    }

//    @Override
//    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
//        super.writeToNBT(compound);
//        compound.setInteger(experience + IncValue);
//        return compound;
//    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        return !isInvalid() && playerIn.getDistanceSqToCenter(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    @Override
    public void update() {

    }
}
