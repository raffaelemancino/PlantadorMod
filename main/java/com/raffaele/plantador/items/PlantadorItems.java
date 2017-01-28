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
import com.raffaele.plantador.Plantador;
import com.raffaele.plantador.plant.Plant;
import cpw.mods.fml.common.registry.GameRegistry;
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
    public static Item coffeepot_neapolis;
    public static Item coffeepot_neapolis_loaded;
    public static Item coffeepot_neapolis_cooked;
    public static Item coffeepot_moka;
    public static Item coffeepot_moka_loaded;
    public static Item coffeepot_moka_cooked;
    public static Item amphora;
    public static Item amphora_raw;
    
   public static void preInit()
   {
       cup = new Item();
       cup.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("cup").setTextureName(Info.ID + ":cup");
       
       cup_raw = new Item();
       cup_raw.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("cup_raw").setTextureName(Info.ID + ":cup_raw");
       
       coffeepot_neapolis = new Item();
       coffeepot_neapolis.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("coffeepot_neapolis").setTextureName(Info.ID + ":coffeepot_neapolis_0");
       coffeepot_neapolis_loaded = new Item();
       coffeepot_neapolis_loaded.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("coffeepot_neapolis_loaded").setTextureName(Info.ID + ":coffeepot_neapolis_1");
       coffeepot_neapolis_cooked = new Item();
       coffeepot_neapolis_cooked.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("coffeepot_neapolis_cooked").setTextureName(Info.ID + ":coffeepot_neapolis_2").setContainerItem(coffeepot_neapolis);
       
       coffeepot_moka = new Item();
       coffeepot_moka.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("coffeepot_moka").setTextureName(Info.ID + ":coffeepot_moka_0");
       coffeepot_moka_loaded = new Item();
       coffeepot_moka_loaded.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("coffeepot_moka_loaded").setTextureName(Info.ID + ":coffeepot_moka_1");
       coffeepot_moka_cooked = new Item();
       coffeepot_moka_cooked.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("coffeepot_moka_cooked").setTextureName(Info.ID + ":coffeepot_moka_2").setContainerItem(coffeepot_moka);
       
       amphora = new Item();
       amphora.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("amphora").setTextureName(Info.ID + ":amphora");
       
       amphora_raw = new Item();
       amphora_raw.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("amphora_raw").setTextureName(Info.ID + ":amphora_raw");
       
       GameRegistry.registerItem(cup, cup.getUnlocalizedName());
       GameRegistry.registerItem(cup_raw, cup_raw.getUnlocalizedName());
       GameRegistry.registerItem(coffeepot_neapolis, coffeepot_neapolis.getUnlocalizedName());
       GameRegistry.registerItem(coffeepot_neapolis_loaded, coffeepot_neapolis_loaded.getUnlocalizedName());
       GameRegistry.registerItem(coffeepot_neapolis_cooked, coffeepot_neapolis_cooked.getUnlocalizedName());
       GameRegistry.registerItem(coffeepot_moka, coffeepot_moka.getUnlocalizedName());
       GameRegistry.registerItem(coffeepot_moka_loaded, coffeepot_moka_loaded.getUnlocalizedName());
       GameRegistry.registerItem(coffeepot_moka_cooked, coffeepot_moka_cooked.getUnlocalizedName());
       GameRegistry.registerItem(amphora, amphora.getUnlocalizedName());
       GameRegistry.registerItem(amphora_raw, amphora_raw.getUnlocalizedName());
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
       GameRegistry.addRecipe(new ItemStack(cup_raw,6),
               "x x",
               " x ",
               'x', new ItemStack(Items.clay_ball));
       GameRegistry.addSmelting(new ItemStack(cup_raw), new ItemStack(cup), 0);
       GameRegistry.addRecipe(new ItemStack(amphora_raw),
               " x ",
               "x x",
               " x ",
               'x', new ItemStack(Items.clay_ball));
       GameRegistry.addSmelting(new ItemStack(amphora_raw), new ItemStack(amphora), 1);
       GameRegistry.addRecipe(new ItemStack(coffeepot_neapolis),
               " x",
               "xx",
               'x', new ItemStack(Items.iron_ingot));
       crafting_coffee(coffeepot_neapolis, coffeepot_neapolis_loaded, coffeepot_neapolis_cooked);
       GameRegistry.addRecipe(new ItemStack(coffeepot_moka),
               "xx",
               " x",
               'x', new ItemStack(Items.iron_ingot));
       crafting_coffee(coffeepot_moka, coffeepot_moka_loaded, coffeepot_moka_cooked);
   }
   
   private static void crafting_coffee(Item coffeepot, Item coffeepot_loaded, Item coffeepot_cooked)
   {
       GameRegistry.addRecipe(new ItemStack(coffeepot_loaded),
               "x",
               "y",
               "z",
               'x', new ItemStack(Plant.coffee),
               'y', new ItemStack(coffeepot),
               'z', new ItemStack(Items.water_bucket.setContainerItem(Items.bucket)));
       GameRegistry.addSmelting(new ItemStack(coffeepot_loaded), new ItemStack(coffeepot_cooked), 1);
   }
}
