package net.minecraft.server;

import net.minecraft.server.Block;
import net.minecraft.server.BlockShelf;
import net.minecraft.server.ItemShelf;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ModLoader;
import net.minecraft.server.TileEntityShelf;

import forge.*;

public class mod_Shelf extends NetworkMod {

   public static final Block[] skins = new Block[]{Block.WOOD, Block.STONE, Block.BRICK, Block.OBSIDIAN};
   public static Block shelf;
   public static int shelfModelID;
   public static final float itemspace = 0.25F;
   public static final float width = 0.3333333F;
   public static int ShelfID = 132;
   public static boolean Render3DItems = false;
   public static boolean RotateItems = false;
   public static final int shelfGuiId = 47;


   public String getVersion() {
      return "1.1";
   }

   public String Version() {
      return "1.1";
   }

   public void load() {}

   public void ModsLoaded() {
      shelfModelID = ModLoader.getUniqueBlockModelID(this, true);
      shelf = (new BlockShelf(ShelfID, skins[1])).a("shelf");
      ModLoader.registerBlock(shelf, ItemShelf.class);
      ModLoader.registerTileEntity(TileEntityShelf.class, "Shelf");

      for(int i = 0; i < skins.length; ++i) {
         ModLoader.addRecipe(new ItemStack(shelf, 1, i << 2), new Object[]{"# ", "##", Character.valueOf('#'), skins[i]});
      }

   }

   public boolean clientSideRequired()
   {
       return true;
   }
   public boolean serverSideRequired()
   {
       return false;
   }
}
