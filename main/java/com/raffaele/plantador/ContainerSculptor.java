/*
 * Copyright (C) 2016 Raffaele Francesco Mancino
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
package com.raffaele.plantador;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class ContainerSculptor extends ContainerWorkbench
{
    private World world;
    public ContainerSculptor(InventoryPlayer player, World world, int x, int y, int z)
    {
        super(player, world, x, y, z);
        this.world = world;
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventory)
    {
        craftResult.setInventorySlotContents(0, CraftingManagerSculptor.instance.findMatchingRecipe(craftMatrix, this.world));
    }
}
