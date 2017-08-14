package mod.lanse505.basicautomation.client.gui;

import mod.lanse505.basicautomation.BasicAutomation;
import mod.lanse505.basicautomation.common.container.AutoShearContainer;
import mod.lanse505.basicautomation.common.container.MobGrinderContainer;
import mod.lanse505.basicautomation.common.tiles.TileAutoShear;
import mod.lanse505.basicautomation.common.tiles.TileMobGrinder;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class GuiAutoShear extends GuiContainer {
    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;

    private static final ResourceLocation background = new ResourceLocation(BasicAutomation.MODID, "textures/container/autoshearcontainer.png");

    public GuiAutoShear(TileAutoShear tileAutoShear, AutoShearContainer container) {
        super(container);
        xSize = WIDTH;
        ySize = HEIGHT;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}