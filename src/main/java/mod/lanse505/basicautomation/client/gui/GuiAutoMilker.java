package mod.lanse505.basicautomation.client.gui;

import mod.lanse505.basicautomation.BasicAutomation;
import mod.lanse505.basicautomation.common.container.AutoMilkerContainer;
import mod.lanse505.basicautomation.common.tiles.TileAutoMilker;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class GuiAutoMilker extends GuiContainer {
    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;

    private static final ResourceLocation background = new ResourceLocation(BasicAutomation.MODID, "textures/container/automilkercontainer.png");

    public GuiAutoMilker(TileAutoMilker tileAutoMilker, AutoMilkerContainer container) {
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