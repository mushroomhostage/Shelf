package net.minecraft.src;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class GuiShelf extends GuiContainer
{
    private IInventory inventory;
    private TileEntityShelf shelf;

    public GuiShelf(IInventory iinventory, TileEntityShelf tileentityshelf)
    {
        super(new CraftingInventoryShelfCB(iinventory, tileentityshelf));
        inventory = iinventory;
        shelf = tileentityshelf;
        allowUserInput = false;
    }

    protected void drawGuiContainerForegroundLayer()
    {
        fontRenderer.drawString(shelf.getInvName(), 60, 6, 0x404040);
        fontRenderer.drawString(inventory.getInvName(), 8, (ySize - 96) + 2, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        int k = mc.renderEngine.getTexture("/gui/trap.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(k);
        int l = width - xSize >> 1;
        int i1 = height - ySize >> 1;
        drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
    }
}
