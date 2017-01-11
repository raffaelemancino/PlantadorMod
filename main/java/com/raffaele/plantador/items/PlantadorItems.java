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
package com.raffaele.plantador.items;

import com.raffaele.plantador.Info;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class PlantadorItems
{
    public static Item cup;
    public static Item cup_raw;
    
   public static void preInit()
   {
       cup = new Item();
       cup.setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("cup").setTextureName(Info.ID + ":cup");
       
       cup_raw = new Item();
       cup_raw.setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("cup_raw").setTextureName(Info.ID + ":cup_raw");
       
       GameRegistry.registerItem(cup, cup.getUnlocalizedName());
       GameRegistry.registerItem(cup_raw, cup_raw.getUnlocalizedName());
   }
   
   public static void init()
   {
       crafting();
   }
   
   public static void postInit()
   {
       
   }
   
   public static void crafting()
   {
       GameRegistry.addRecipe(new ItemStack(cup_raw),
               "x x",
               " x ",
               'x', new ItemStack(Items.clay_ball));
       GameRegistry.addSmelting(new ItemStack(cup_raw), new ItemStack(cup), 0);
   }
}
