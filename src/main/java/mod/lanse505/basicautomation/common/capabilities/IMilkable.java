package mod.lanse505.basicautomation.common.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fluids.Fluid;

import javax.annotation.Nonnull;
import java.util.Map;

public interface IMilkable extends INBTSerializable<NBTTagCompound>{
    void addMilkable(@Nonnull IMilkable milkable);

    void setMilkable(@Nonnull Map<Fluid, IMilkable> milkable);

    void getFluidFromMilkable();

    @Nonnull
    Map<Fluid, IMilkable> getEntityLiquidList();

}
