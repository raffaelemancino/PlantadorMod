/*
 * Copyright (C) 2017 Raffaele Francesco Mancino
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.raffaele.plantador.blocks.barrel;

import com.raffaele.plantador.blocks.BarrelBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class TileEntityBarrel extends TileEntity implements ISidedInventory
{
    private static final int[] slotsTop = new int[] {0};
    private static final int[] slotsBottom = new int[] {2, 1};
    private static final int[] slotsSides = new int[] {1};
    /** The ItemStacks that hold the items currently being used in the furnace */
    private ItemStack[] furnaceItemStacks = new ItemStack[3];
    /** The number of ticks that the furnace will keep burning */
    public int barrelBrewingTime;
    /** The number of ticks that a fresh copy of the currently-burning item would keep the furnace burning for */
    public int currentItemBrewingTime;
    /** The number of ticks that the current item has been cooking for */
    public int barrelCookTime;
    private String barrelName;
    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory()
    {
        return this.furnaceItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    @Override
    public ItemStack getStackInSlot(int i)
    {
        return this.furnaceItemStacks[i];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    @Override
    public ItemStack decrStackSize(int i, int j)
    {
        if (this.furnaceItemStacks[i] != null)
        {
            ItemStack itemstack;

            if (this.furnaceItemStacks[i].stackSize <= j)
            {
                itemstack = this.furnaceItemStacks[i];
                this.furnaceItemStacks[i] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.furnaceItemStacks[i].splitStack(j);

                if (this.furnaceItemStacks[i].stackSize == 0)
                {
                    this.furnaceItemStacks[i] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int i)
    {
        if (this.furnaceItemStacks[i] != null)
        {
            ItemStack itemstack = this.furnaceItemStacks[i];
            this.furnaceItemStacks[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack)
    {
        this.furnaceItemStacks[i] = itemStack;

        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
        {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Returns the barrelName of the inventory
     */
    @Override
    public String getInventoryName()
    {
        return this.hasCustomInventoryName() ? this.barrelName : "container.barrel";
    }

    /**
     * Returns if the inventory is barrelNamed
     */
    @Override
    public boolean hasCustomInventoryName()
    {
        return this.barrelName != null && this.barrelName.length() > 0;
    }
    
    public void func_145951_a(String name)
    {
        this.barrelName = name;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound bTTagCompound)
    {
        super.readFromNBT(bTTagCompound);
        NBTTagList nbttaglist = bTTagCompound.getTagList("Items", 10);
        this.furnaceItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.furnaceItemStacks.length)
            {
                this.furnaceItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.barrelBrewingTime = bTTagCompound.getShort("BrewingTime");
        this.barrelCookTime = bTTagCompound.getShort("CookTime");
        this.currentItemBrewingTime = getItemBurnTime(this.furnaceItemStacks[1]);

        if (bTTagCompound.hasKey("CustomName", 8))
        {
            this.barrelName = bTTagCompound.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound bTTagCompound)
    {
        super.writeToNBT(bTTagCompound);
        bTTagCompound.setShort("BrewingTime", (short)this.barrelBrewingTime);
        bTTagCompound.setShort("CookTime", (short)this.barrelCookTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.furnaceItemStacks.length; ++i)
        {
            if (this.furnaceItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.furnaceItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        bTTagCompound.setTag("Items", nbttaglist);

        if (this.hasCustomInventoryName())
        {
            bTTagCompound.setString("CustomName", this.barrelName);
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot.
     */
    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * Returns an integer between 0 and the passed value representing how close the current item is to being completely
     * cooked
     */
    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int i)
    {
        return this.barrelCookTime * i / 24000;
    }

    /**
     * Returns an integer between 0 and the passed value representing how much burn time is left on the current fuel
     * item, where 0 means that the item is exhausted and the passed value means that the item is fresh
     */
    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int i)
    {
        if (this.currentItemBrewingTime == 0)
        {
            this.currentItemBrewingTime = 24000;
        }

        return this.barrelBrewingTime * i / this.currentItemBrewingTime;
    }

    /**
     * Furnace isBurning
     */
    public boolean isBurning()
    {
        return this.barrelBrewingTime > 0;
    }

    @Override
    public void updateEntity()
    {
        boolean flag = this.barrelBrewingTime > 0;
        boolean flag1 = false;

        if (this.barrelBrewingTime > 0)
        {
            --this.barrelBrewingTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.barrelBrewingTime != 0 || this.furnaceItemStacks[1] != null && this.furnaceItemStacks[0] != null)
            {
                if (this.barrelBrewingTime == 0 && this.canSmelt())
                {
                    this.currentItemBrewingTime = this.barrelBrewingTime = getItemBurnTime(this.furnaceItemStacks[1]);

                    if (this.barrelBrewingTime > 0)
                    {
                        flag1 = true;

                        if (this.furnaceItemStacks[1] != null)
                        {
                            --this.furnaceItemStacks[1].stackSize;

                            if (this.furnaceItemStacks[1].stackSize == 0)
                            {
                                this.furnaceItemStacks[1] = furnaceItemStacks[1].getItem().getContainerItem(furnaceItemStacks[1]);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt())
                {
                    ++this.barrelCookTime;

                    if (this.barrelCookTime == 24000)
                    {
                        this.barrelCookTime = 0;
                        this.smeltItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.barrelCookTime = 0;
                }
            }

            if (flag != this.barrelBrewingTime > 0)
            {
                flag1 = true;
                BarrelBlock.updateBarrelBlockState(this.barrelBrewingTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }
        if (flag1)
        {
            this.markDirty();
        }
    }

    /**
     * Returns true if the furnace can smelt an item, i.e. has a source item, destination stack isn't full, etc.
     */
    private boolean canSmelt()
    {
        if (this.furnaceItemStacks[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = RecipeBarrel.smelting().getSmeltingResult(this.furnaceItemStacks[0]);
            if (itemstack == null) return false;
            if (this.furnaceItemStacks[2] == null) return true;
            if (!this.furnaceItemStacks[2].isItemEqual(itemstack)) return false;
            int result = furnaceItemStacks[2].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.furnaceItemStacks[2].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
        }
    }

    /**
     * Turn one item from the furnace source stack into the appropriate smelted item in the furnace result stack
     */
    public void smeltItem()
    {
        if (this.canSmelt())
        {
            ItemStack itemstack = RecipeBarrel.smelting().getSmeltingResult(this.furnaceItemStacks[0]);

            if (this.furnaceItemStacks[2] == null)
            {
                this.furnaceItemStacks[2] = itemstack.copy();
            }
            else if (this.furnaceItemStacks[2].getItem() == itemstack.getItem())
            {
                this.furnaceItemStacks[2].stackSize += itemstack.stackSize; // Forge BugFix: Results may have multiple items
            }

            --this.furnaceItemStacks[0].stackSize;

            if (this.furnaceItemStacks[0].stackSize <= 0)
            {
                this.furnaceItemStacks[0] = null;
            }
        }
    }

    /**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     * fuel
     */
    public static int getItemBurnTime(ItemStack itemStack)
    {
        int ret = 0;
        if (itemStack.getItem() == Items.water_bucket)
            ret = 24000;
        return ret;
    }

    public static boolean isItemFuel(ItemStack itemStack)
    {
        /**
         * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
         * fuel
         */
        return getItemBurnTime(itemStack) > 0;
    }

    /**
     * Do not make give this method the barrelName canInteractWith because it clashes with Container
     */
    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openInventory() {}

    public void closeInventory() {}

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack)
    {
        return i == 2 ? false : (i == 1 ? isItemFuel(itemStack) : true);
    }

    /**
     * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
     * block.
     */
    @Override
    public int[] getAccessibleSlotsFromSide(int i)
    {
        return i == 0 ? slotsBottom : (i == 1 ? slotsTop : slotsSides);
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    @Override
    public boolean canInsertItem(int i, ItemStack itemStack, int j)
    {
        return this.isItemValidForSlot(i, itemStack);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
     * side
     */
    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, int side)
    {
        return side != 0 || slot != 1 || itemStack.getItem() == Items.bucket;
    }
}
