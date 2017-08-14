package mod.lanse505.basicautomation.common.blocks;

import mod.lanse505.basicautomation.BasicAutomation;
import mod.lanse505.basicautomation.common.tiles.TileAutoMilker;
import mod.lanse505.basicautomation.common.utils.CreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAutoMilker extends Block implements ITileEntityProvider {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public BlockAutoMilker() {
        super(Material.IRON);
        setUnlocalizedName(BasicAutomation.MODID + ".automilker");
        setRegistryName("automilker");
        setCreativeTab(CreativeTab.basicAutomation);
        setHardness(7.5f);
        setSoundType(SoundType.METAL);
    }

    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileAutoMilker() {
        };
    }
}
