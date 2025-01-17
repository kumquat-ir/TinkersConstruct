package tconstruct.tools.logic;

import mantle.blocks.abstracts.InventoryLogic;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import tconstruct.library.crafting.MoldBuilder;
import tconstruct.tools.inventory.MoldingTableContainer;

public class MoldingTableLogic extends InventoryLogic implements ISidedInventory {

    private ItemStack selectedStack;

    public MoldingTableLogic() {
        super(2);
    }

    @Override
    public boolean canUpdate() {
        return false;
    }

    @Override
    public String getDefaultName() {
        return "toolstation.MoldingTable";
    }

    @Override
    public Container getGuiContainer(InventoryPlayer inventoryplayer, World world, int x, int y, int z) {
        return new MoldingTableContainer(inventoryplayer, this);
    }

    /*
     * @Override public void onInventoryChanged() { if (inventory[0] == null) inventory[1] = null;
     * super.onInventoryChanged(); }
     */

    public void setSelectedMold(ItemStack stack) {
        selectedStack = stack;
        this.setInventorySlotContents(1, stack);
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemstack) {
        super.setInventorySlotContents(slot, itemstack);
        if (slot == 0) {
            if (itemstack != null && MoldBuilder.isBlank(itemstack)) {
                super.setInventorySlotContents(1, selectedStack);
            } else if (itemstack != null) {
                ItemStack cpy = itemstack.copy();
                cpy.stackSize = 1;
                super.setInventorySlotContents(1, cpy);
            } else {
                super.setInventorySlotContents(1, null);
            }
        }
    }

    @Override
    public ItemStack decrStackSize(int slot, int quantity) {
        if (slot == 1) {
            super.decrStackSize(0, 1);
            if (inventory[0] == null) return super.decrStackSize(slot, quantity);
            else return inventory[1].copy();
        } else {
            ItemStack ret = super.decrStackSize(slot, quantity);
            if (inventory[0] == null) super.decrStackSize(1, 1);
            return ret;
        }
    }

    public void altDecrStackSize(int slot, int quantity) {
        if (inventory[slot] != null) {
            if (inventory[slot].stackSize <= quantity) {
                ItemStack stack = inventory[slot];
                inventory[slot] = null;
                return;
            }
            ItemStack split = inventory[slot].splitStack(quantity);
            if (inventory[slot].stackSize == 0) {
                inventory[slot] = null;
            }
        }
    }

    @Override
    public boolean canDropInventorySlot(int slot) {
        return slot == 0;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemstack, int j) {
        return false;
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemstack, int j) {
        return false;
    }

    @Override
    public String getInventoryName() {
        return StatCollector.translateToLocal("toolstation.MoldingTable");
    }

    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }

    @Override
    public void closeInventory() {}

    @Override
    public void openInventory() {}
}
