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
package com.raffaele.plantador.food;

import com.raffaele.plantador.Info;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class Food {
    
    public static Item cheese;
    
    public static void preInit()
    {
        cheese = new ItemFood(8, 0.6F, false);
        cheese.setCreativeTab(CreativeTabs.tabFood).setUnlocalizedName("cheese").setTextureName(Info.ID + ":food/cheese");
        
        GameRegistry.registerItem(cheese, cheese.getUnlocalizedName());
        
        crafting();
    }
    
    public static void init()
    {
        
    }
    
    public static void crafting()
    {
        GameRegistry.addShapedRecipe(new ItemStack(cheese),
                "xxx",
                'x', new ItemStack(Items.milk_bucket.setContainerItem(Items.bucket)));
    }
    
}
