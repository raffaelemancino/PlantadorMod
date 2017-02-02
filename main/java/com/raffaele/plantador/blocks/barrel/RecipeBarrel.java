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

import com.raffaele.plantador.items.PlantadorItems;
import com.raffaele.plantador.plant.Plant;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class RecipeBarrel
{
    private static final RecipeBarrel smeltingBase = new RecipeBarrel();
    /** The list of smelting results. */
    private Map smeltingList = new HashMap();
    private Map experienceList = new HashMap();
    private static final String __OBFID = "CL_00000085";

    /**
     * Used to call methods addSmelting and getSmeltingResult.
     */
    public static RecipeBarrel smelting()
    {
        return smeltingBase;
    }

    private RecipeBarrel()
    {
        this.addRecipe(Plant.rwineCrop, new ItemStack(PlantadorItems.rwine_bucket, 1), 1);
        this.addRecipe(Plant.wwineCrop, new ItemStack(PlantadorItems.wwine_bucket, 1), 1);
    }

    public void addRecipe(Block block, ItemStack itemStack, float exp)
    {
        this.addRecipe(Item.getItemFromBlock(block), itemStack, exp);
    }

    public void addRecipe(Item item, ItemStack itemStack, float exp)
    {
        this.func_151394_a(new ItemStack(item, 1, 32767), itemStack, exp);
    }

    public void func_151394_a(ItemStack itemStack1, ItemStack itemStack2, float exp)
    {
        this.smeltingList.put(itemStack1, itemStack2);
        this.experienceList.put(itemStack2, Float.valueOf(exp));
    }

    /**
     * Returns the smelting result of an item.
     */
    public ItemStack getSmeltingResult(ItemStack itemStack)
    {
        Iterator iterator = this.smeltingList.entrySet().iterator();
        Map.Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Map.Entry)iterator.next();
        }
        while (!this.func_151397_a(itemStack, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
    }

    private boolean func_151397_a(ItemStack itemStack1, ItemStack itemStack2)
    {
        return itemStack2.getItem() == itemStack1.getItem() && (itemStack2.getItemDamage() == 32767 || itemStack2.getItemDamage() == itemStack1.getItemDamage());
    }

    public Map getSmeltingList()
    {
        return this.smeltingList;
    }

    public float func_151398_b(ItemStack itemStack)
    {
        float ret = itemStack.getItem().getSmeltingExperience(itemStack);
        if (ret != -1) return ret;

        Iterator iterator = this.experienceList.entrySet().iterator();
        Map.Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return 0.0F;
            }

            entry = (Map.Entry)iterator.next();
        }
        while (!this.func_151397_a(itemStack, (ItemStack)entry.getKey()));

        return ((Float)entry.getValue()).floatValue();
    }
}
