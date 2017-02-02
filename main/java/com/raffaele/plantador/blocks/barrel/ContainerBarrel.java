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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class ContainerBarrel extends Container
{
    private TileEntityBarrel tileBarrel;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;
    private static final String __OBFID = "CL_00001748";

    public ContainerBarrel(InventoryPlayer inventoryPlayer, TileEntityBarrel tileEntityBarrel)
    {
        this.tileBarrel = tileEntityBarrel;
        this.addSlotToContainer(new Slot(tileEntityBarrel, 0, 56, 17));
        this.addSlotToContainer(new Slot(tileEntityBarrel, 1, 56, 53));
        this.addSlotToContainer(new SlotFurnace(inventoryPlayer.player, tileEntityBarrel, 2, 116, 35));
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    public void addCraftingToCrafters(ICrafting iCrafting)
    {
        super.addCraftingToCrafters(iCrafting);
        iCrafting.sendProgressBarUpdate(this, 0, this.tileBarrel.barrelCookTime);
        iCrafting.sendProgressBarUpdate(this, 1, this.tileBarrel.barrelBrewingTime);
        iCrafting.sendProgressBarUpdate(this, 2, this.tileBarrel.currentItemBrewingTime);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookTime != this.tileBarrel.barrelCookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileBarrel.barrelCookTime);
            }

            if (this.lastBurnTime != this.tileBarrel.barrelBrewingTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileBarrel.barrelBrewingTime);
            }

            if (this.lastItemBurnTime != this.tileBarrel.currentItemBrewingTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileBarrel.currentItemBrewingTime);
            }
        }

        this.lastCookTime = this.tileBarrel.barrelCookTime;
        this.lastBurnTime = this.tileBarrel.barrelBrewingTime;
        this.lastItemBurnTime = this.tileBarrel.currentItemBrewingTime;
    }

    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int i, int j)
    {
        if (i == 0)
        {
            this.tileBarrel.barrelCookTime = j;
        }

        if (i == 1)
        {
            this.tileBarrel.barrelBrewingTime = j;
        }

        if (i == 2)
        {
            this.tileBarrel.currentItemBrewingTime = j;
        }
    }

    public boolean canInteractWith(EntityPlayer entityPlayer)
    {
        return this.tileBarrel.isUseableByPlayer(entityPlayer);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(i);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (i == 2)
            {
                if (!this.mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (i != 1 && i != 0)
            {
                if (RecipeBarrel.smelting().getSmeltingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityBarrel.isItemFuel(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (i >= 3 && i < 30)
                {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (i >= 30 && i < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(entityPlayer, itemstack1);
        }

        return itemstack;
    }
    
}
