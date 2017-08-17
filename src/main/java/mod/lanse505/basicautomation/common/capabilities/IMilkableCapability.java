package mod.lanse505.basicautomation.common.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class IMilkableCapability {
    @CapabilityInject(IMilkable.class)
    public static Capability<IMilkable> IMILKABLE_CAP;

    public static void register(){
        CapabilityManager.INSTANCE.register(IMilkable.class, new Capability.IStorage<IMilkable>() {
            @Nullable
            @Override
            public NBTBase writeNBT(Capability<IMilkable> capability, IMilkable instance, EnumFacing side) {
                return instance.serializeNBT();
            }

            @Override
            public void readNBT(Capability<IMilkable> capability, IMilkable instance, EnumFacing side, NBTBase nbt) {

                }
            }
        );
    }
}
