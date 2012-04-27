package net.minecraft.server;

import net.minecraft.server.ItemBlock;
import net.minecraft.server.ItemStack;
import net.minecraft.server.mod_Shelf;

public class ItemShelf extends ItemBlock {

   public ItemShelf(int i) {
      super(i);
      this.setMaxDurability(0);
      this.a(true);
   }

   public int getIconFromDamage(int i) {
      return mod_Shelf.shelf.a(2, i);
   }

   public int filterData(int i) {
      return i;
   }

   public String a(ItemStack itemstack) {
      return super.a(itemstack) + "." + mod_Shelf.skins[itemstack.getData() >> 2 & 3];
   }
}
