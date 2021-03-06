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
import com.raffaele.plantador.items.PlantadorItems;
import com.raffaele.plantador.plant.Plant;
import com.raffaele.plantador.Plantador;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSoup;
import net.minecraft.item.ItemStack;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class Food {
    
    public static Item cheese;
    public static Item ham;
    public static Item chicory_stew;
    public static Item chicorybroadbean_stew;
    public static Item coffee_cup;
    
    public static void preInit()
    {
        cheese = new ItemFood(8, 0.6F, false);
        cheese.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("cheese").setTextureName(Info.ID + ":food/cheese");
        
        ham = new ItemFood(4, 0.8F, true);
        ham.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("ham").setTextureName(Info.ID + ":food/ham");
        
        chicory_stew = new ItemSoup(6);
        chicory_stew.setUnlocalizedName("chicory_stew").setTextureName(Info.ID + ":food/chicory_stew").setCreativeTab(Plantador.tabPlantador);
        
        coffee_cup = new CoffeeCup();
        coffee_cup.setUnlocalizedName("coffee_cup").setTextureName(Info.ID + ":food/coffee_cup").setCreativeTab(Plantador.tabPlantador);
        
        chicorybroadbean_stew = new ItemSoup(7);
        chicorybroadbean_stew.setUnlocalizedName("chicorybroadbean_stew").setTextureName(Info.ID + ":food/chicory&broadbean_stew").setCreativeTab(Plantador.tabPlantador);
        
        GameRegistry.registerItem(cheese, cheese.getUnlocalizedName());
        GameRegistry.registerItem(ham, ham.getUnlocalizedName());
        GameRegistry.registerItem(chicory_stew, chicory_stew.getUnlocalizedName());
        GameRegistry.registerItem(coffee_cup, coffee_cup.getUnlocalizedName());
        GameRegistry.registerItem(chicorybroadbean_stew, chicorybroadbean_stew.getUnlocalizedName());
    }
    
    public static void init()
    {
        crafting();
    }
    
    public static void crafting()
    {
        GameRegistry.addShapedRecipe(new ItemStack(cheese),
                "xxx",
                'x', new ItemStack(Items.milk_bucket.setContainerItem(Items.bucket)));
        GameRegistry.addShapelessRecipe(new ItemStack(ham, 3),new ItemStack(Items.porkchop));
        GameRegistry.addRecipe(new ItemStack(chicory_stew), 
                "xx",
                " y",
                'x', new ItemStack(Plant.chicory),
                'y', new ItemStack(Items.bowl));        
        GameRegistry.addRecipe(new ItemStack(coffee_cup,6),
                "xxx",
                "xxx",
                " y ",
                'x', new ItemStack(PlantadorItems.cup),
                'y', new ItemStack(PlantadorItems.coffeepot_neapolis_cooked));
        GameRegistry.addRecipe(new ItemStack(coffee_cup,6),
                "xxx",
                "xxx",
                " y ",
                'x', new ItemStack(PlantadorItems.cup),
                'y', new ItemStack(PlantadorItems.coffeepot_moka_cooked));
        GameRegistry.addRecipe(new ItemStack(chicorybroadbean_stew),
                "xy",
                " z",
                'x',new ItemStack(Plant.broadbean),
                'y',new ItemStack(Plant.chicory),
                'z',new ItemStack(Items.bowl));
    }
}
