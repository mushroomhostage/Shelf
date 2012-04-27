package net.minecraft.src;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.lwjgl.opengl.GL11;

public class TileEntityShelfRenderer extends TileEntitySpecialRenderer
{
    private RenderBlocks blockrender;
    private static Method render;

    public TileEntityShelfRenderer()
    {
        blockrender = new RenderBlocks();
    }

    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2,
            float f)
    {
        a((TileEntityShelf)tileentity, d, d1, d2, f);
    }

    public void a(TileEntityShelf tileentityshelf, double d, double d1, double d2,
            float f)
    {
        float f1 = 0.0F;
        if (mod_Shelf.RotateItems)
        {
            f1 = tileentityshelf.worldObj.getWorldTime() % 360L;
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d + 0.5F, (float)d1 + 0.8F, (float)d2 + 0.5F);
        int i = tileentityshelf.getBlockMetadata() & 3;
        switch (i)
        {
            case 0:
                GL11.glRotatef(270F, 0.0F, 1.0F, 0.0F);
                break;

            case 1:
                GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
                break;

            case 2:
                GL11.glRotatef(180F, 0.0F, 1.0F, 0.0F);
                break;

            case 3:
                GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
                break;
        }
        GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        for (int j = 0; j < tileentityshelf.getSizeInventory(); j++)
        {
            if (j == 0)
            {
                GL11.glTranslatef(-0.3333333F, 0.0F, -0.3333333F);
            }
            else if (j % 3 != 0)
            {
                GL11.glTranslatef(0.3333333F, 0.0F, 0.0F);
            }
            else
            {
                GL11.glTranslatef(-0.6666667F, -0.3333333F, 0.3333333F);
            }
            ItemStack itemstack = tileentityshelf.getStackInSlot(j);
            if (itemstack != null && Item.itemsList[itemstack.itemID] != null)
            {
                if (itemstack.itemID < 256 && RenderBlocks.renderItemIn3d(Block.blocksList[itemstack.itemID].getRenderType()))
                {
                    bindTextureByName("/terrain.png");
                    float f2 = 0.25F;
                    int i1 = Block.blocksList[itemstack.itemID].getRenderType();
                    if (i1 == 1 || i1 == 19 || i1 == 12 || i1 == 2)
                    {
                        f2 = 0.5F;
                    }
                    GL11.glPushMatrix();
                    if (mod_Shelf.RotateItems)
                    {
                        GL11.glRotatef(f1, 0.0F, 1.0F, 0.0F);
                    }
                    GL11.glScalef(f2, f2, f2);
                    GL11.glTranslatef(0.0F, 0.35F, 0.0F);
                    float f3 = 1.0F;
                    blockrender.renderBlockAsItem(Block.blocksList[itemstack.itemID], itemstack.getItemDamage(), f3);
                    GL11.glPopMatrix();
                }
                else
                {
                    GL11.glPushMatrix();
                    try
                    {
                        GL11.glScalef(0.3333333F, 0.3333333F, 0.3333333F);
                        if (mod_Shelf.RotateItems)
                        {
                            GL11.glRotatef(f1, 0.0F, 1.0F, 0.0F);
                        }
                        if (mod_Shelf.Render3DItems)
                        {
                            GL11.glTranslatef(-0.5F, 0.0F, 0.0F);
                        }
                        if (itemstack.getItem().func_46058_c())
                        {
                            bindTextureByName("/gui/items.png");
                            for (int k = 0; k <= 1; k++)
                            {
                                int j1 = itemstack.getItem().func_46057_a(itemstack.getItemDamage(), k);
                                int l1 = itemstack.getItem().getColorFromDamage(itemstack.getItemDamage(), k);
                                float f5 = (float)(l1 >> 16 & 0xff) / 255F;
                                float f7 = (float)(l1 >> 8 & 0xff) / 255F;
                                float f9 = (float)(l1 & 0xff) / 255F;
                                GL11.glColor4f(f5, f7, f9, 1.0F);
                                drawItem(j1);
                            }
                        }
                        else
                        {
                            int l = itemstack.getIconIndex();
                            if (itemstack.itemID < 256)
                            {
                                bindTextureByName("/terrain.png");
                            }
                            else
                            {
                                bindTextureByName("/gui/items.png");
                            }
                            int k1 = Item.itemsList[itemstack.itemID].getColorFromDamage(itemstack.getItemDamage(), 0);
                            float f4 = (float)(k1 >> 16 & 0xff) / 255F;
                            float f6 = (float)(k1 >> 8 & 0xff) / 255F;
                            float f8 = (float)(k1 & 0xff) / 255F;
                            GL11.glColor4f(f4, f6, f8, 1.0F);
                            drawItem(l);
                        }
                    }
                    catch (Throwable throwable)
                    {
                        throw new RuntimeException(throwable);
                    }
                    GL11.glPopMatrix();
                }
            }
        }

        GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        GL11.glPopMatrix();
    }

    private void drawItem(int i)
    throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        Tessellator tessellator = Tessellator.instance;
        float f = (float)((i % 16) * 16 + 0) / 256F;
        float f1 = (float)((i % 16) * 16 + 16) / 256F;
        float f2 = (float)((i / 16) * 16 + 0) / 256F;
        float f3 = (float)((i / 16) * 16 + 16) / 256F;
        float f4 = 1.0F;
        float f5 = 0.5F;
        float f6 = 0.25F;
        if (mod_Shelf.Render3DItems)
        {
            render.invoke(RenderManager.instance.itemRenderer, new Object[]
                    {
                        tessellator, Float.valueOf(f1), Float.valueOf(f2), Float.valueOf(f), Float.valueOf(f3)
                    });
        }
        else
        {
            tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, 1.0F, 0.0F);
            tessellator.addVertexWithUV(0.0F - f5, 0.0F - f6, 0.0D, f, f3);
            tessellator.addVertexWithUV(f4 - f5, 0.0F - f6, 0.0D, f1, f3);
            tessellator.addVertexWithUV(f4 - f5, 1.0F - f6, 0.0D, f1, f2);
            tessellator.addVertexWithUV(0.0F - f5, 1.0F - f6, 0.0D, f, f2);
            tessellator.addVertexWithUV(0.0F - f5, 1.0F - f6, 0.0D, f, f2);
            tessellator.addVertexWithUV(f4 - f5, 1.0F - f6, 0.0D, f1, f2);
            tessellator.addVertexWithUV(f4 - f5, 0.0F - f6, 0.0D, f1, f3);
            tessellator.addVertexWithUV(0.0F - f5, 0.0F - f6, 0.0D, f, f3);
            tessellator.draw();
        }
    }

    static Class _mthclass$(String s)
    {
        try
        {
            return Class.forName(s);
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
    }

    static
    {
        render = null;
        if (mod_Shelf.Render3DItems)
        {
            try
            {
                render = (net.minecraft.src.ItemRenderer.class).getDeclaredMethod("a", new Class[]
                        {
                            net.minecraft.src.Tessellator.class, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE
                        });
                render.setAccessible(true);
            }
            catch (Throwable throwable)
            {
                throwable.printStackTrace();
            }
        }
    }
}
