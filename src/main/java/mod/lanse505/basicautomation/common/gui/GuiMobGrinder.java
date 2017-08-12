package mod.lanse505.basicautomation.common.gui;

import mod.lanse505.basicautomation.BasicAutomation;
import mod.lanse505.basicautomation.common.tiles.TileMobGrinder;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class GuiMobGrinder extends GuiContainer {
    public static final int WIDTH = 180;
    public static final int HEIGHT = 152;

    private static final ResourceLocation background = new ResourceLocation(BasicAutomation.MODID, "textures/gui/mobgrindercontainer.png");

    public GuiMobGrinder(TileMobGrinder tileMobGrinder, MobGrinderContainer container){
        super(container);
        xSize = WIDTH;
        ySize = HEIGHT;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}