package net.minecraft.src;

public class ItemShelf extends ItemBlock
{
    public ItemShelf(int i)
    {
        super(i);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    public int getIconFromDamage(int i)
    {
        return mod_Shelf.shelf.getBlockTextureFromSideAndMetadata(2, i);
    }

    public int getMetadata(int i)
    {
        return i;
    }

    public String getItemNameIS(ItemStack itemstack)
    {
        return (new StringBuilder(String.valueOf(super.isFull3D()))).append(".").append(mod_Shelf.skins[itemstack.getItemDamage() >> 2 & 3]).toString();
    }
}
