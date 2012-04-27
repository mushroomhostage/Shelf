package net.minecraft.server;

import java.util.ArrayList;
import net.minecraft.server.AxisAlignedBB;
import net.minecraft.server.Block;
import net.minecraft.server.BlockContainer;
import net.minecraft.server.CraftingInventoryShelfCB;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityItem;
import net.minecraft.server.EntityLiving;
import net.minecraft.server.IInventory;
import net.minecraft.server.ItemStack;
import net.minecraft.server.MathHelper;
import net.minecraft.server.ModLoader;
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.TileEntity;
import net.minecraft.server.TileEntityShelf;
import net.minecraft.server.World;
import net.minecraft.server.mod_Shelf;

public class BlockShelf extends BlockContainer {

   protected BlockShelf(int i, Block block) {
      super(i, block.textureId, block.material);
      this.strength = block.strength;
      this.durability = block.durability;
   }

   public int c() {
      return mod_Shelf.shelfModelID;
   }

   public int a(int i, int j) {
      int k = j >> 2 & 3;
      return mod_Shelf.skins[k].a(i);
   }

   public boolean a() {
      return false;
   }

   public boolean b() {
      return false;
   }

   public TileEntity a_() {
      return new TileEntityShelf();
   }

   public void onPlace(World world, int i, int j, int k) {
      super.onPlace(world, i, j, k);
      world.a(i, j, k);
   }

   public void postPlace(World world, int i, int j, int k, EntityLiving entityliving) {
      int l = MathHelper.floor((double)(entityliving.yaw * 4.0F / 360.0F) + 0.5D) & 3;
      int i1 = world.getData(i, j, k) & 12;
      if(l == 0) {
         i1 |= 2;
      }

      if(l == 1) {
         i1 |= 1;
      }

      if(l == 2) {
         i1 |= 3;
      }

      if(l == 3) {
         i1 |= 0;
      }

      world.setData(i, j, k, i1);
   }

   public void a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList arraylist) {
      int l = world.getData(i, j, k) & 3;
      if(l == 0) {
         this.a(0.0F, 0.0F, 0.0F, 0.3333333F, 0.08333334F, 1.0F);
         super.a(world, i, j, k, axisalignedbb, arraylist);
         this.a(0.3333333F, 0.0F, 0.0F, 0.6666667F, 0.4166667F, 1.0F);
         super.a(world, i, j, k, axisalignedbb, arraylist);
         this.a(0.6666667F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
         super.a(world, i, j, k, axisalignedbb, arraylist);
      } else if(l == 1) {
         this.a(0.0F, 0.0F, 0.0F, 0.3333333F, 0.75F, 1.0F);
         super.a(world, i, j, k, axisalignedbb, arraylist);
         this.a(0.3333333F, 0.0F, 0.0F, 0.6666667F, 0.4166667F, 1.0F);
         super.a(world, i, j, k, axisalignedbb, arraylist);
         this.a(0.6666667F, 0.0F, 0.0F, 1.0F, 0.08333334F, 1.0F);
         super.a(world, i, j, k, axisalignedbb, arraylist);
      } else if(l == 2) {
         this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.08333334F, 0.3333333F);
         super.a(world, i, j, k, axisalignedbb, arraylist);
         this.a(0.0F, 0.0F, 0.3333333F, 1.0F, 0.4166667F, 0.6666667F);
         super.a(world, i, j, k, axisalignedbb, arraylist);
         this.a(0.0F, 0.0F, 0.6666667F, 1.0F, 0.75F, 1.0F);
         super.a(world, i, j, k, axisalignedbb, arraylist);
      } else if(l == 3) {
         this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 0.3333333F);
         super.a(world, i, j, k, axisalignedbb, arraylist);
         this.a(0.0F, 0.0F, 0.3333333F, 1.0F, 0.4166667F, 0.6666667F);
         super.a(world, i, j, k, axisalignedbb, arraylist);
         this.a(0.0F, 0.0F, 0.6666667F, 1.0F, 0.08333334F, 1.0F);
         super.a(world, i, j, k, axisalignedbb, arraylist);
      }

      this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public boolean interact(World world, int i, int j, int k, EntityHuman entityhuman) {
      TileEntity tileentity = world.getTileEntity(i, j, k);
      if(tileentity == null || !(tileentity instanceof TileEntityShelf)) {
         return true;
      }

      entityhuman.openGui(mod_Shelf.instance, mod_Shelf.shelfGuiId, world, i, j, k);

      return true;
   }

   public int a(int i) {
      return i & 12;
   }

   public void remove(World world, int i, int j, int k) {
      IInventory iinventory = (IInventory)world.getTileEntity(i, j, k);
      if(iinventory != null) {
         for(int l = 0; l < iinventory.getSize(); ++l) {
            ItemStack itemstack = iinventory.getItem(l);
            if(itemstack != null) {
               float f = world.random.nextFloat() * 0.8F + 0.1F;
               float f1 = world.random.nextFloat() * 0.8F + 0.1F;

               EntityItem entityitem;
               for(float f2 = world.random.nextFloat() * 0.8F + 0.1F; itemstack.count > 0; world.addEntity(entityitem)) {
                  int i1 = world.random.nextInt(21) + 10;
                  if(i1 > itemstack.count) {
                     i1 = itemstack.count;
                  }

                  itemstack.count -= i1;
                  entityitem = new EntityItem(world, (double)((float)i + f), (double)((float)j + f1), (double)((float)k + f2), new ItemStack(itemstack.id, i1, itemstack.getData()));
                  double d = 0.05000000074505806D;
                  entityitem.motX = world.random.nextGaussian() * d;
                  entityitem.motY = world.random.nextGaussian() * d + 0.20000000298023224D;
                  entityitem.motZ = world.random.nextGaussian() * d;
                  if(itemstack.hasTag()) {
                     entityitem.itemStack.setTag((NBTTagCompound)itemstack.getTag().clone());
                  }
               }
            }
         }
      }

      super.remove(world, i, j, k);
   }
}
