package net.minecraft.src;
/*client*/
import org.lwjgl.opengl.GL11;

public class mod_Shelf extends BaseModMp
{
    public static final Block skins[];
    public static Block shelf;
    public static int shelfModelID;
    public static final float itemspace = 0.25F;
    public static final float width = 0.3333333F;
    public static int ShelfID = 180;
    public static boolean Render3DItems = false;
    public static boolean RotateItems = false;

    public static final short shelfGuiId = 47;
    private static TileEntityShelf hax;

    public mod_Shelf()
    {
    }

    private void RenderShelfInInv(RenderBlocks renderblocks, Block block, int i)
    {
        Tessellator tessellator = Tessellator.instance;
        for (int j = 0; j < 3; j++)
        {
            if (j == 0)
            {
                block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 0.3333333F);
            }
            else if (j == 1)
            {
                block.setBlockBounds(0.0F, 0.0F, 0.3333333F, 1.0F, 0.4166667F, 0.6666667F);
            }
            else if (j == 2)
            {
                block.setBlockBounds(0.0F, 0.0F, 0.6666667F, 1.0F, 0.08333334F, 1.0F);
            }
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, -1F, 0.0F);
            renderblocks.renderBottomFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(0, i));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 1.0F, 0.0F);
            renderblocks.renderTopFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(1, i));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, -1F);
            renderblocks.renderEastFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(2, i));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 0.0F, 1.0F);
            renderblocks.renderWestFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(3, i));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(-1F, 0.0F, 0.0F);
            renderblocks.renderNorthFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(4, i));
            tessellator.draw();
            tessellator.startDrawingQuads();
            tessellator.setNormal(1.0F, 0.0F, 0.0F);
            renderblocks.renderSouthFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(5, i));
            tessellator.draw();
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        }

        block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    private boolean RenderShelfInWorld(RenderBlocks renderblocks, IBlockAccess iblockaccess, int i, int j, int k, Block block)
    {
        int l = iblockaccess.getBlockMetadata(i, j, k) & 3;
        if (l == 0)
        {
            block.setBlockBounds(0.0F, 0.0F, 0.0F, 0.3333333F, 0.08333334F, 1.0F);
            renderblocks.renderStandardBlock(block, i, j, k);
            block.setBlockBounds(0.3333333F, 0.0F, 0.0F, 0.6666667F, 0.4166667F, 1.0F);
            renderblocks.renderStandardBlock(block, i, j, k);
            block.setBlockBounds(0.6666667F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
            renderblocks.renderStandardBlock(block, i, j, k);
        }
        else if (l == 1)
        {
            block.setBlockBounds(0.0F, 0.0F, 0.0F, 0.3333333F, 0.75F, 1.0F);
            renderblocks.renderStandardBlock(block, i, j, k);
            block.setBlockBounds(0.3333333F, 0.0F, 0.0F, 0.6666667F, 0.4166667F, 1.0F);
            renderblocks.renderStandardBlock(block, i, j, k);
            block.setBlockBounds(0.6666667F, 0.0F, 0.0F, 1.0F, 0.08333334F, 1.0F);
            renderblocks.renderStandardBlock(block, i, j, k);
        }
        else if (l == 2)
        {
            block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.08333334F, 0.3333333F);
            renderblocks.renderStandardBlock(block, i, j, k);
            block.setBlockBounds(0.0F, 0.0F, 0.3333333F, 1.0F, 0.4166667F, 0.6666667F);
            renderblocks.renderStandardBlock(block, i, j, k);
            block.setBlockBounds(0.0F, 0.0F, 0.6666667F, 1.0F, 0.75F, 1.0F);
            renderblocks.renderStandardBlock(block, i, j, k);
        }
        else if (l == 3)
        {
            block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 0.3333333F);
            renderblocks.renderStandardBlock(block, i, j, k);
            block.setBlockBounds(0.0F, 0.0F, 0.3333333F, 1.0F, 0.4166667F, 0.6666667F);
            renderblocks.renderStandardBlock(block, i, j, k);
            block.setBlockBounds(0.0F, 0.0F, 0.6666667F, 1.0F, 0.08333334F, 1.0F);
            renderblocks.renderStandardBlock(block, i, j, k);
        }
        block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        return false;
    }

    public void RenderInvBlock(RenderBlocks renderblocks, Block block, int i, int j)
    {
        if (j == shelfModelID)
        {
            RenderShelfInInv(renderblocks, block, i);
        }
    }

    public boolean RenderWorldBlock(RenderBlocks renderblocks, IBlockAccess iblockaccess, int i, int j, int k, Block block, int l)
    {
        if (l == shelfModelID)
        {
            return RenderShelfInWorld(renderblocks, iblockaccess, i, j, k, block);
        }
        else
        {
            return false;
        }
    }

    public String getVersion()
    {
        return "1.1";
    }

    public void load()
    {
        shelfModelID = ModLoader.getUniqueBlockModelID(this, true);
        shelf = (new BlockShelf(ShelfID, skins[1])).setBlockName("shelf");
        ModLoader.RegisterBlock(shelf, net.minecraft.src.ItemShelf.class);
        ModLoader.RegisterTileEntity(net.minecraft.src.TileEntityShelf.class, "Shelf", new TileEntityShelfRenderer());
        ModLoader.AddName(shelf, "Shelf");
        ModLoader.AddName(new ItemStack(shelf, 1, 0), "Wooden Shelf");
        ModLoader.AddName(new ItemStack(shelf, 1, 4), "Stone Shelf");
        ModLoader.AddName(new ItemStack(shelf, 1, 8), "Brick Shelf");
        ModLoader.AddName(new ItemStack(shelf, 1, 12), "Obsidian Shelf");
        for (int i = 0; i < skins.length; i++)
        {
            ModLoader.AddRecipe(new ItemStack(shelf, 1, i << 2), new Object[]
                    {
                        "# ", "##", Character.valueOf('#'), skins[i]
                    });
        }
	ModLoaderMp.RegisterGUI(this,shelfGuiId);
    }
    public GuiScreen HandleGUI(int inventoryType)
    {
	if(inventoryType == shelfGuiId)
        {
		if(hax == null) System.out.println("This isn't working.");
		TileEntityShelf temp = hax; hax = null;
		return new GuiShelf(ModLoader.getMinecraftInstance().thePlayer.inventory, temp);
	}
	else return null;
    }

    public static void setCurTileEnt(TileEntityShelf e)
    {
	hax = e;
    }
    static
    {
        skins = (new Block[]
                {
                    Block.planks, Block.stone, Block.brick, Block.obsidian
                });
    }
}
