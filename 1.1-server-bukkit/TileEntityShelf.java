package net.minecraft.server;

import net.minecraft.server.EntityHuman;
import net.minecraft.server.IInventory;
import net.minecraft.server.ItemStack;
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.NBTTagList;
import net.minecraft.server.TileEntity;

public class TileEntityShelf extends TileEntity implements IInventory {

   private ItemStack[] items = new ItemStack[9];


   public int getSize() {
      return this.items.length;
   }

   public ItemStack getItem(int i) {
      return this.items[i];
   }

   public ItemStack splitStack(int i, int j) {
      if(this.items[i] != null) {
         ItemStack itemstack1;
         if(this.items[i].count <= j) {
            itemstack1 = this.items[i];
            this.items[i] = null;
            this.update();
            return itemstack1;
         } else {
            itemstack1 = this.items[i].a(j);
            if(this.items[i].count == 0) {
               this.items[i] = null;
            }

            this.update();
            return itemstack1;
         }
      } else {
         return null;
      }
   }

   public void setItem(int i, ItemStack itemstack) {
      this.items[i] = itemstack;
      if(itemstack != null && itemstack.count > this.getMaxStackSize()) {
         itemstack.count = this.getMaxStackSize();
      }

      this.update();
   }

   public String getName() {
      return "Shelf";
   }

   public void a(NBTTagCompound nbttagcompound) {
      super.a(nbttagcompound);
      NBTTagList nbttaglist = nbttagcompound.getList("Items");
      this.items = new ItemStack[this.getSize()];

      for(int i = 0; i < nbttaglist.size(); ++i) {
         NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.get(i);
         int j = nbttagcompound1.getByte("Slot") & 255;
         if(j >= 0 && j < this.items.length) {
            this.items[j] = ItemStack.a(nbttagcompound1);
         }
      }

   }

   public void b(NBTTagCompound nbttagcompound) {
      super.b(nbttagcompound);
      NBTTagList nbttaglist = new NBTTagList();

      for(byte byte0 = 0; byte0 < this.items.length; ++byte0) {
         if(this.items[byte0] != null) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setByte("Slot", byte0);
            this.items[byte0].b(nbttagcompound1);
            nbttaglist.add(nbttagcompound1);
         }
      }

      nbttagcompound.set("Items", nbttaglist);
   }

   public int getMaxStackSize() {
      return 64;
   }

   public boolean a(EntityHuman entityhuman) {
      return this.world.getTileEntity(this.x, this.y, this.z) != this?false:entityhuman.e((double)this.x + 0.5D, (double)this.y + 0.5D, (double)this.z + 0.5D) <= 64.0D;
   }

   public void f() {}

   public void g() {}

   public ItemStack[] getContents() {
      throw new UnsupportedOperationException("Not supported yet.");
   }
}
