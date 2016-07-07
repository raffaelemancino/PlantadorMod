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

import com.raffaele.plantador.Info;
import com.raffaele.plantador.plant.cotton.CottonBlock;
import com.raffaele.plantador.plant.tobacco.TobaccoBlock;
import com.raffaele.plantador.plant.tobacco.TobaccoGrassBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class Plant {
    
    public static Block cottonBlock;
    public static Item cottonSeed;
    public static Item cottonCrop;
    
    public static Block tobaccoGrass;//create tobacco tall grass
    public static Block tobaccoBlock;
    public static Item tobaccoSeed;
    public static Item tobaccoCrop;
    public static Item tobacco;
    
    public static void preInit()
    {
        cottonBlock = new CottonBlock();
        cottonBlock.setBlockName("cotton_block").setBlockTextureName(Info.ID + ":cotton/cotton").setTickRandomly(true);
        
        cottonSeed = new ItemSeeds(cottonBlock, Blocks.farmland);
        cottonSeed.setCreativeTab(CreativeTabs.tabMisc).setUnlocalizedName("cotton_seed").setTextureName(Info.ID + ":cotton/cotton_seed");
        
        cottonCrop = new Item();
        cottonCrop.setCreativeTab(CreativeTabs.tabMisc).setUnlocalizedName("cotton_crop").setTextureName(Info.ID + ":cotton/cotton_crop");
        
        GameRegistry.registerBlock(cottonBlock, cottonBlock.getUnlocalizedName());
        GameRegistry.registerItem(cottonSeed, cottonSeed.getUnlocalizedName());
        MinecraftForge.addGrassSeed(new ItemStack(cottonSeed), 10);
        GameRegistry.registerItem(cottonCrop, cottonCrop.getUnlocalizedName());
        
        tobaccoGrass = new TobaccoGrassBlock();
        tobaccoGrass.setCreativeTab(CreativeTabs.tabDecorations).setBlockName("tobacco_grass").setBlockTextureName(Info.ID + ":tobacco/tobacco_grass");

        tobaccoBlock = new TobaccoBlock();
        tobaccoBlock.setBlockName("tobacco_block").setBlockTextureName(Info.ID + ":tobacco/tobacco");
        
        tobaccoSeed = new ItemSeeds(tobaccoBlock, Blocks.farmland);
        tobaccoSeed.setCreativeTab(CreativeTabs.tabMisc).setUnlocalizedName("tobacco_seed").setTextureName(Info.ID + ":tobacco/tobacco_seed");
        
        tobaccoCrop = new Item();
        tobaccoCrop.setCreativeTab(CreativeTabs.tabMisc).setUnlocalizedName("tobacco_crop").setTextureName(Info.ID + ":tobacco/tobacco_crop");
        
        tobacco = new Item();
        tobacco.setCreativeTab(CreativeTabs.tabMisc).setUnlocalizedName("tobacco").setTextureName(Info.ID + ":tobacco/tobacco");
        
        GameRegistry.registerBlock(tobaccoGrass, tobaccoGrass.getUnlocalizedName());
        GameRegistry.registerBlock(tobaccoBlock, tobaccoBlock.getUnlocalizedName());
        GameRegistry.registerItem(tobaccoSeed, tobaccoSeed.getUnlocalizedName());
        MinecraftForge.addGrassSeed(new ItemStack(tobaccoSeed), 8);
        GameRegistry.registerItem(tobaccoCrop, tobaccoCrop.getUnlocalizedName());
        GameRegistry.registerItem(tobacco, tobacco.getUnlocalizedName());
        
        crafting();
    }
    
    public static void init()
    {
        
    }
    
    public static void crafting()
    {
        GameRegistry.addShapelessRecipe(new ItemStack(Items.string), new ItemStack(cottonCrop)); //producing string
        GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 4), new ItemStack(Blocks.wool));
        GameRegistry.addShapelessRecipe(new ItemStack(tobaccoSeed, 3), new ItemStack(tobaccoCrop));
        GameRegistry.addSmelting(new ItemStack(tobaccoCrop), new ItemStack(tobacco), 5); //producing tobacco
    }
}
