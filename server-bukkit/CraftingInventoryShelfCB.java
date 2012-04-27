package net.minecraft.server;

import net.minecraft.server.Container;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.IInventory;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Slot;
import net.minecraft.server.TileEntityShelf;

public class CraftingInventoryShelfCB extends Container {

   private TileEntityShelf entity;


   public CraftingInventoryShelfCB(IInventory iinventory, TileEntityShelf tileentityshelf) {
      this.entity = tileentityshelf;

      int k;
      int i1;
      for(k = 0; k < 3; ++k) {
         for(i1 = 0; i1 < 3; ++i1) {
            this.a(new Slot(tileentityshelf, i1 + k * 3, 62 + i1 * 18, 17 + k * 18));
         }
      }

      for(k = 0; k < 3; ++k) {
         for(i1 = 0; i1 < 9; ++i1) {
            this.a(new Slot(iinventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
         }
      }

      for(k = 0; k < 9; ++k) {
         this.a(new Slot(iinventory, k, 8 + k * 18, 142));
      }

   }

   public boolean b(EntityHuman entityhuman) {
      return this.entity.a(entityhuman);
   }

   public ItemStack a(int i) {
      return null;
   }
}
