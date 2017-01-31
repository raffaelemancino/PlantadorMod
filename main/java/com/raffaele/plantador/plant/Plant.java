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
package com.raffaele.plantador.plant;

import com.raffaele.plantador.plant.wine.WineBlock;
import com.raffaele.plantador.Info;
import com.raffaele.plantador.Plantador;
import com.raffaele.plantador.plant.broadbean.BroadBeanBlock;
import com.raffaele.plantador.plant.coffee.CoffeeBlock;
import com.raffaele.plantador.plant.cotton.CottonBlock;
import com.raffaele.plantador.plant.lettuce.LettuceBlock;
import com.raffaele.plantador.plant.tobacco.TobaccoBlock;
import com.raffaele.plantador.items.Pipe;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class Plant {
    
    public static Item chicory;
    
    public static Block cottonBlock;
    public static Item cottonSeed;
    public static Item cottonCrop;
    
    public static Block tobaccoBlock;
    public static Item tobaccoSeed;
    public static Item tobaccoCrop;
    public static Item tobacco;
    
    public static Block wineBlock;
    public static Item wineSeed;
    public static Item wineCrop;
    
    public static Block coffeeBlock;
    public static Item coffeeSeed;
    public static Item coffee;
    
    public static Block broadbeanBlock;
    public static Item broadbean;
    
    public static Block lettuceBlock;
    public static Item lettuce;
        
    public static void preInit()
    {
        chicory = new Item();
        chicory.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("chicory").setTextureName(Info.ID + ":chicory");
        
        GameRegistry.registerItem(chicory, chicory.getUnlocalizedName());
        MinecraftForge.addGrassSeed(new ItemStack(chicory), 12);
        
        cottonBlock = new CottonBlock();
        cottonBlock.setBlockName("cotton_block").setBlockTextureName(Info.ID + ":cotton/cotton");
        
        cottonSeed = new ItemSeeds(cottonBlock, Blocks.farmland);
        cottonSeed.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("cotton_seed").setTextureName(Info.ID + ":cotton/cotton_seed");
        
        cottonCrop = new Item();
        cottonCrop.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("cotton_crop").setTextureName(Info.ID + ":cotton/cotton_crop");
        
        GameRegistry.registerBlock(cottonBlock, cottonBlock.getUnlocalizedName());
        GameRegistry.registerItem(cottonSeed, cottonSeed.getUnlocalizedName());
        GameRegistry.registerItem(cottonCrop, cottonCrop.getUnlocalizedName());

        tobaccoBlock = new TobaccoBlock();
        tobaccoBlock.setBlockName("tobacco_block").setBlockTextureName(Info.ID + ":tobacco/tobacco");
        
        tobaccoSeed = new ItemSeeds(tobaccoBlock, Blocks.farmland);
        tobaccoSeed.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("tobacco_seed").setTextureName(Info.ID + ":tobacco/tobacco_seed");
        
        tobaccoCrop = new Item();
        tobaccoCrop.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("tobacco_crop").setTextureName(Info.ID + ":tobacco/tobacco_crop");
        
        tobacco = new Item();
        tobacco.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("tobacco").setTextureName(Info.ID + ":tobacco/tobacco");
        
                        
        GameRegistry.registerBlock(tobaccoBlock, tobaccoBlock.getUnlocalizedName());
        GameRegistry.registerItem(tobaccoSeed, tobaccoSeed.getUnlocalizedName());
        GameRegistry.registerItem(tobaccoCrop, tobaccoCrop.getUnlocalizedName());
        GameRegistry.registerItem(tobacco, tobacco.getUnlocalizedName());
                
        wineBlock = new WineBlock();
        wineBlock.setBlockName("wine_block").setBlockTextureName(Info.ID + ":wine/wine");
        
        wineSeed = new ItemSeeds(wineBlock, Blocks.farmland);
        wineSeed.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("wine_seed").setTextureName(Info.ID + ":wine/wine_seed");
        
        wineCrop = new ItemFood(4, 0.3F, false);
        wineCrop.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("wine_crop").setTextureName(Info.ID + ":wine/wine_crop");
        
        GameRegistry.registerBlock(wineBlock, wineBlock.getUnlocalizedName());
        GameRegistry.registerItem(wineSeed, wineSeed.getUnlocalizedName());
        GameRegistry.registerItem(wineCrop, wineCrop.getUnlocalizedName());
        
        coffeeBlock = new CoffeeBlock();
        coffeeBlock.setBlockName("coffee_block").setBlockTextureName(Info.ID + ":coffee/coffee");
        
        coffeeSeed = new ItemSeeds(coffeeBlock, Blocks.farmland);
        coffeeSeed.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("coffee_seed").setTextureName(Info.ID + ":coffee/coffee_seed");
        
        coffee = new Item();
        coffee.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("coffee").setTextureName(Info.ID + ":coffee/coffee");
        
        GameRegistry.registerBlock(coffeeBlock, coffeeBlock.getUnlocalizedName());
        GameRegistry.registerItem(coffeeSeed, coffeeSeed.getUnlocalizedName());
        GameRegistry.registerItem(coffee, coffee.getUnlocalizedName());
        
        broadbeanBlock = new BroadBeanBlock();
        broadbeanBlock.setBlockName("broadbean_block").setBlockTextureName(Info.ID + ":broadbean/broadbean");
        
        broadbean = new ItemSeeds(broadbeanBlock, Blocks.farmland);
        broadbean.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("broadbean").setTextureName(Info.ID + ":broadbean/broadbean");
        
        GameRegistry.registerBlock(broadbeanBlock, broadbeanBlock.getUnlocalizedName());
        GameRegistry.registerItem(broadbean, broadbean.getUnlocalizedName());
        
        lettuceBlock = new LettuceBlock();
        lettuceBlock.setBlockName("lettuceBlock").setBlockTextureName(Info.ID + ":lettuce/lettuce");
        
        lettuce = new ItemSeedFood(3, 0.4F, lettuceBlock, lettuceBlock);
        lettuce.setCreativeTab(Plantador.tabPlantador).setUnlocalizedName("lettuce").setTextureName(Info.ID + ":lettuce");
        
        GameRegistry.registerBlock(lettuceBlock, lettuceBlock.getUnlocalizedName());
        GameRegistry.registerItem(lettuce, lettuce.getUnlocalizedName());
    }
    
    public static void init()
    {
        crafting();
    }
    
    public static void postInit()
    {
        
    }
    
    private static void crafting()
    {
        GameRegistry.addShapelessRecipe(new ItemStack(Items.string), new ItemStack(cottonCrop)); //producing string
        GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 4), new ItemStack(Blocks.wool));
        
        GameRegistry.addSmelting(new ItemStack(tobaccoCrop), new ItemStack(tobacco), 5); //producing tobacco
        
        GameRegistry.addShapelessRecipe(new ItemStack(wineSeed, 3), new ItemStack(wineCrop));
        GameRegistry.addSmelting(new ItemStack(coffeeSeed), new ItemStack(coffee), 5);
    }
}
