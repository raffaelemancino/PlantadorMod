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

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class Build {
    
    public static Block clay_tiles;
    public static Block clay_tiles_stairs;
    
    public static void preInit()
    {
        clay_tiles = new BuildingBlock("clay_tiles");
        clay_tiles_stairs = new BuildingStairs(clay_tiles, "clay_tiles_stairs");
        
        GameRegistry.registerBlock(clay_tiles, clay_tiles.getUnlocalizedName());
        GameRegistry.registerBlock(clay_tiles_stairs, clay_tiles_stairs.getUnlocalizedName());
        
        crafting();
    }
    
    public static void init()
    {
        
    }
    
    private static void crafting()
    {
        GameRegistry.addSmelting(new ItemStack(Blocks.gravel), new ItemStack(Blocks.clay), 5);
        GameRegistry.addRecipe(new ItemStack(clay_tiles, 4),
                "xx",
                "xx",
                'x', new ItemStack(Blocks.hardened_clay));
        GameRegistry.addRecipe(new ItemStack(clay_tiles_stairs, 4),
                "x  ",
                "xx ",
                "xxx",
                'x', new ItemStack(clay_tiles));
        GameRegistry.addRecipe(new ItemStack(clay_tiles_stairs, 4),
                "  x",
                " xx",
                "xxx",
                'x', new ItemStack(clay_tiles));
        
    }
}
