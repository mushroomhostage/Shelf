package net.minecraft.src;

public class mod_Shelf extends BaseModMp
{
    public static final Block skins[];
    public static Block shelf;
    public static int shelfModelID;
    public static final float itemspace = 0.25F;
    public static final float width = 0.3333333F;
    public static int ShelfID = 132;
    public static boolean Render3DItems = false;
    public static boolean RotateItems = false;

    public static final int shelfGuiId = 47;

    public mod_Shelf()
    {
    }

    public String getVersion()
    {
        return "1.1";
    }
    public String Version() //mlmp again
    {
        return "1.1";
    }
    public void load() {} // use ModsLoaded because of MLMP
    public void ModsLoaded()
    {
        shelfModelID = ModLoader.getUniqueBlockModelID(this, true);
        shelf = (new BlockShelf(ShelfID, skins[1])).setBlockName("shelf");
        ModLoader.RegisterBlock(shelf, net.minecraft.src.ItemShelf.class);
        ModLoader.RegisterTileEntity(net.minecraft.src.TileEntityShelf.class, "Shelf");
        for (int i = 0; i < skins.length; i++)
        {
            ModLoader.AddRecipe(new ItemStack(shelf, 1, i << 2), new Object[]
                    {
                        "# ", "##", Character.valueOf('#'), skins[i]
                    });
        }
    }

    static
    {
        skins = (new Block[]
                {
                    Block.planks, Block.stone, Block.brick, Block.obsidian
                });
    }
}
