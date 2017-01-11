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
package com.raffaele.plantador.plant.tobacco;

import com.raffaele.plantador.plant.Plant;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import java.lang.Math;
import net.minecraft.entity.player.EntityPlayer;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class Pipe extends Item {
    public Pipe()
    {
        this.setMaxStackSize(1);
        this.setNoRepair();
        this.setMaxDamage(40);
    }
    
    @Override
    public EnumAction getItemUseAction(ItemStack item)
    {
        return EnumAction.bow;
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack item, World world, EntityPlayer player)
    {
        if (player.capabilities.isCreativeMode || player.inventory.hasItem(Plant.tobacco))
        {
            player.setItemInUse(item, this.getMaxItemUseDuration(item));
        }

        return item;
    }
    
    @Override
    public int getMaxItemUseDuration(ItemStack item)
    {
        return 72000;
    }
    
    @Override
    public void onPlayerStoppedUsing(ItemStack item, World world, EntityPlayer player, int itemInUse)
    {
        for (int i=0; i<20; i++)
        {
            float x0 = -(float)Math.sin(Math.toRadians(player.rotationYawHead));
            float z0 = (float)Math.cos(Math.toRadians(player.rotationYawHead));
            x0 *= 0.4;
            z0 *= 0.4;
            world.spawnParticle("smoke", player.posX + x0, player.posY, player.posZ + z0, 0.0D, 0.0D, 0.0D);
        }
        if (player.canEat(false))
        {
            player.getFoodStats().addStats(2, 0.3F);
        }
        player.inventory.consumeInventoryItem(Plant.tobacco);
        item.damageItem(1, player);
    }

    @Override
    public ItemStack onEaten(ItemStack item, World world, EntityPlayer player) {
        return item;
    }
}