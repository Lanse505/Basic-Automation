package mod.lanse505.basicautomation.common.blocks;

import mod.lanse505.basicautomation.BasicAutomation;
import mod.lanse505.basicautomation.common.tiles.TileAutoShear;
import mod.lanse505.basicautomation.common.utils.CreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class BlockAutoShear extends Block {

    public static final int GUI_ID = 2;
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public BlockAutoShear() {
        super(Material.IRON);
        setUnlocalizedName(BasicAutomation.MODID + ".autoshear");
        setRegistryName("autoshear");
        setCreativeTab(CreativeTab.basicAutomation);
        setHardness(7.5f);
        setSoundType(SoundType.METAL);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public boolean hasTileEntity(IBlockState blockState) {
        return true;
    }

    @Nonnull
    public TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState blockState) {
        return new TileAutoShear();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            return true;
        }

        TileEntity te = world.getTileEntity(pos);
        if (!(te instanceof TileAutoShear)) {
            return false;
        }
        player.openGui(BasicAutomation.instance, GUI_ID, world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
