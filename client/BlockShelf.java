package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;

public class BlockShelf extends BlockContainer
{
    protected BlockShelf(int i, Block block)
    {
        super(i, block.blockIndexInTexture, block.blockMaterial);
        blockHardness = block.blockHardness;
        blockResistance = block.blockResistance;
    }

    public int getRenderType()
    {
        return mod_Shelf.shelfModelID;
    }

    public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
        int k = j >> 2 & 3;
        return mod_Shelf.skins[k].getBlockTextureFromSide(i);
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public TileEntity getBlockEntity()
    {
        return new TileEntityShelf();
    }

    public void onBlockAdded(World world, int i, int j, int k)
    {
        super.onBlockAdded(world, i, j, k);
        world.markBlockNeedsUpdate(i, j, k);
    }

    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving)
    {
        int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        int i1 = world.getBlockMetadata(i, j, k) & 0xc;
        if (l == 0)
        {
            i1 |= 2;
        }
        if (l == 1)
        {
            i1 |= 1;
        }
        if (l == 2)
        {
            i1 |= 3;
        }
        if (l == 3)
        {
            i1 |= 0;
        }
        world.setBlockMetadataWithNotify(i, j, k, i1);
    }

    public void getCollidingBoundingBoxes(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList arraylist)
    {
        int l = world.getBlockMetadata(i, j, k) & 3;
        if (l == 0)
        {
            setBlockBounds(0.0F, 0.0F, 0.0F, 0.3333333F, 0.08333334F, 1.0F);
            super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb, arraylist);
            setBlockBounds(0.3333333F, 0.0F, 0.0F, 0.6666667F, 0.4166667F, 1.0F);
            super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb, arraylist);
            setBlockBounds(0.6666667F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
            super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb, arraylist);
        }
        else if (l == 1)
        {
            setBlockBounds(0.0F, 0.0F, 0.0F, 0.3333333F, 0.75F, 1.0F);
            super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb, arraylist);
            setBlockBounds(0.3333333F, 0.0F, 0.0F, 0.6666667F, 0.4166667F, 1.0F);
            super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb, arraylist);
            setBlockBounds(0.6666667F, 0.0F, 0.0F, 1.0F, 0.08333334F, 1.0F);
            super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb, arraylist);
        }
        else if (l == 2)
        {
            setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.08333334F, 0.3333333F);
            super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb, arraylist);
            setBlockBounds(0.0F, 0.0F, 0.3333333F, 1.0F, 0.4166667F, 0.6666667F);
            super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb, arraylist);
            setBlockBounds(0.0F, 0.0F, 0.6666667F, 1.0F, 0.75F, 1.0F);
            super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb, arraylist);
        }
        else if (l == 3)
        {
            setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 0.3333333F);
            super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb, arraylist);
            setBlockBounds(0.0F, 0.0F, 0.3333333F, 1.0F, 0.4166667F, 0.6666667F);
            super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb, arraylist);
            setBlockBounds(0.0F, 0.0F, 0.6666667F, 1.0F, 0.08333334F, 1.0F);
            super.getCollidingBoundingBoxes(world, i, j, k, axisalignedbb, arraylist);
        }
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        TileEntityShelf tileentityshelf = (TileEntityShelf)world.getBlockTileEntity(i, j, k);
        if (tileentityshelf == null || world.isRemote) {
            return true;
        }
        entityplayer.openGui(mod_Shelf.instance, mod_Shelf.shelfGuiId, world, i, j, k);
        return true;
    }

    public int getBlockTextureFromSide(int i)
    {
        return i & 0xc;
    }

    public void onBlockRemoval(World world, int i, int j, int k)
    {
        IInventory iinventory = (IInventory)world.getBlockTileEntity(i, j, k);
        if (iinventory != null)
        {
            for (int l = 0; l < iinventory.getSizeInventory(); l++)
            {
                ItemStack itemstack = iinventory.getStackInSlot(l);
                if (itemstack != null)
                {
                    float f = world.rand.nextFloat() * 0.8F + 0.1F;
                    float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
                    float f2 = world.rand.nextFloat() * 0.8F + 0.1F;
                    while (itemstack.stackSize > 0)
                    {
                        int i1 = world.rand.nextInt(21) + 10;
                        if (i1 > itemstack.stackSize)
                        {
                            i1 = itemstack.stackSize;
                        }
                        itemstack.stackSize -= i1;
                        EntityItem entityitem = new EntityItem(world, (float)i + f, (float)j + f1, (float)k + f2, new ItemStack(itemstack.itemID, i1, itemstack.getItemDamage()));
                        double d = 0.05000000074505806D;
                        entityitem.motionX = world.rand.nextGaussian() * d;
                        entityitem.motionY = world.rand.nextGaussian() * d + 0.20000000298023224D;
                        entityitem.motionZ = world.rand.nextGaussian() * d;
                        if (itemstack.hasTagCompound())
                        {
                            entityitem.item.setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }
                        world.spawnEntityInWorld(entityitem);
                    }
                }
            }
        }
        super.onBlockRemoval(world, i, j, k);
    }
}
