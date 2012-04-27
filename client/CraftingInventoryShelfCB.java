package net.minecraft.src;

public class CraftingInventoryShelfCB extends Container
{
    private TileEntityShelf entity;
    public EntityPlayer player;
    public IInventory iinventory;

    public CraftingInventoryShelfCB(EntityPlayer player, TileEntityShelf tileentityshelf)
    {
        this.player = player;
        this.iinventory = player.inventory;
        entity = tileentityshelf;
        for (int i = 0; i < 3; i++)
        {
            for (int l = 0; l < 3; l++)
            {
                addSlot(new Slot(tileentityshelf, l + i * 3, 62 + l * 18, 17 + i * 18));
            }
        }

        for (int j = 0; j < 3; j++)
        {
            for (int i1 = 0; i1 < 9; i1++)
            {
                addSlot(new Slot(iinventory, i1 + j * 9 + 9, 8 + i1 * 18, 84 + j * 18));
            }
        }

        for (int k = 0; k < 9; k++)
        {
            addSlot(new Slot(iinventory, k, 8 + k * 18, 142));
        }
    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return entity.isUseableByPlayer(entityplayer);
    }

    public ItemStack transferStackInSlot(int i)
    {
        return null;
    }
}
