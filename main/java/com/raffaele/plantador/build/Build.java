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
package com.raffaele.plantador.build;

import com.raffaele.plantador.Info;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class Build {
    
    public static Block clay_tiles;
    public static Block clay_tiles_stairs;
    public static Block marble;
    public static Block marble_brick;
    public static Block marble_pillar;
    public static Block marble_doric_capital;
    public static Block marble_ionic_capital;
    public static Block marble_corinthian_capital;
    
    
    public static void preInit()
    {
        clay_tiles = new BuildingBlock("clay_tiles").setBlockTextureName(Info.ID + ":build/" + "clay_tiles");
        clay_tiles_stairs = new BuildingStairs(clay_tiles, "clay_tiles_stairs");
        
        marble = new BuildingBlock("marble").setBlockTextureName(Info.ID + ":build/marble/" + "marble");
        marble_brick = new BuildingBlock("marble_brick").setBlockTextureName(Info.ID + ":build/marble/" + "marble_brick");
        marble_pillar = new BuildingPillar("marble_pillar").setBlockTextureName(Info.ID + ":build/marble/" + "marble_pillar");
        marble_doric_capital = new BuildingPillar("doric").setBlockTextureName(Info.ID + ":build/marble/" + "doric");
        marble_ionic_capital = new BuildingPillar("ionic").setBlockTextureName(Info.ID + ":build/marble/" + "ionic");
        marble_corinthian_capital = new BuildingPillar("corinthian").setBlockTextureName(Info.ID + ":build/marble/" + "corinthian");
        
        GameRegistry.registerBlock(clay_tiles, clay_tiles.getUnlocalizedName());
        GameRegistry.registerBlock(clay_tiles_stairs, clay_tiles_stairs.getUnlocalizedName());
        
        GameRegistry.registerBlock(marble, marble.getUnlocalizedName());
        GameRegistry.registerBlock(marble_brick, marble_brick.getUnlocalizedName());
        GameRegistry.registerBlock(marble_pillar, marble_pillar.getUnlocalizedName());
        GameRegistry.registerBlock(marble_doric_capital, marble_doric_capital.getUnlocalizedName());
        GameRegistry.registerBlock(marble_ionic_capital, marble_ionic_capital.getUnlocalizedName());
        GameRegistry.registerBlock(marble_corinthian_capital, marble_corinthian_capital.getUnlocalizedName());
        
        crafting();
    }
    
    public static void init()
    {
        
    }
    
    private static void crafting()
    {
        //obtain clay
        GameRegistry.addSmelting(new ItemStack(Blocks.gravel), new ItemStack(Blocks.clay), 5);
        //clay tiles
        GameRegistry.addRecipe(new ItemStack(clay_tiles, 4),
                "xx",
                "xx",
                'x', new ItemStack(Blocks.hardened_clay));
        GameRegistry.addRecipe(new ItemStack(clay_tiles_stairs, 4),
                "x  ",
                "xx ",
                "xxx",
                'x', new ItemStack(clay_tiles));
        //marble
        GameRegistry.addRecipe(new ItemStack(marble),
                "xy",
                'x', new ItemStack(Blocks.stone),
                'y', new ItemStack(Items.iron_pickaxe.setContainerItem(Items.iron_pickaxe)));
        GameRegistry.addRecipe(new ItemStack(marble_brick, 4),
                "xx",
                "xx",
                'x', new ItemStack(marble));
        GameRegistry.addRecipe(new ItemStack(marble_pillar, 3),
                "x",
                "x",
                "x",
                'x', new ItemStack(marble));
        GameRegistry.addRecipe(new ItemStack(marble_doric_capital),
                "   ",
                " xy",
                "   ",
                'x', new ItemStack(marble_pillar),
                'y', new ItemStack(Items.iron_pickaxe.setContainerItem(Items.iron_pickaxe)));
        GameRegistry.addRecipe(new ItemStack(marble_ionic_capital),
                "   ",
                " x ",
                " y ",
                'x', new ItemStack(marble_pillar),
                'y', new ItemStack(Items.iron_pickaxe.setContainerItem(Items.iron_pickaxe)));
        GameRegistry.addRecipe(new ItemStack(marble_corinthian_capital),
                " y ",
                " x ",
                "   ",
                'y', new ItemStack(Items.iron_pickaxe.setContainerItem(Items.iron_pickaxe)),
                'x', new ItemStack(marble_pillar));
        
    }
}
