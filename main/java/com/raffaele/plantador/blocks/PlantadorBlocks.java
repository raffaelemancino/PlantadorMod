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
package com.raffaele.plantador.blocks;

import com.raffaele.plantador.Info;
import com.raffaele.plantador.Plantador;
import com.raffaele.plantador.build.sculptortable.CraftingManagerSculptor;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;



/** original minecraft for furnace
 * 
 * import net.minecraft.block.BlockFurnace;
 * import net.minecraft.inventory.ContainerFurnace;
 * import net.minecraft.client.gui.inventory.GuiFurnace;
 * import net.minecraft.tileentity.TileEntityFurnace;
 * import net.minecraft.item.crafting.FurnaceRecipes;
 * 
 */


/**
 *
 * @author Raffaele Francesco Mancino
 */
public class PlantadorBlocks
{
    
    public static Block clay_tiles;
    public static Block clay_tiles_stairs;
    public static Block marble;
    public static Block marble_brick;
    public static Block marble_slab_half;
    public static Block marble_stairs;
    public static Block marble_doric;
    public static Block marble_ionic;
    public static Block marble_corinthian;
    public static Block marble_chiseled;
    public static Block table_sculptor;
    public static Block barrel;
    public static Block barrel_active;
        
    public static void preInit()
    {
        clay_tiles = new BuildingBlock("clay_tiles").setBlockTextureName(Info.ID + ":build/" + "clay_tiles").setCreativeTab(Plantador.tabPlantador);
        clay_tiles_stairs = new BuildingStairs(clay_tiles, "clay_tiles_stairs").setCreativeTab(Plantador.tabPlantador);
        
        marble = new BuildingBlock("marble").setBlockTextureName(Info.ID + ":build/marble/" + "marble").setCreativeTab(Plantador.tabPlantador);
        marble_brick = new BuildingBlock("marble_brick").setBlockTextureName(Info.ID + ":build/marble/" + "marble_brick").setCreativeTab(Plantador.tabPlantador);
        marble_slab_half = new BuildingSlab(Material.rock, "marble_slab", false).setBlockTextureName(Info.ID + ":build/marble/" + "marble_brick").setCreativeTab(Plantador.tabPlantador);
        marble_stairs = new BuildingStairs(marble_brick, "marble_stairs").setCreativeTab(Plantador.tabPlantador);
        
        marble_doric = new BuildingPillar("marble_doric", "doric").setBlockTextureName(Info.ID + ":build/marble/" + "marble_pillar").setCreativeTab(Plantador.tabPlantador);
        marble_ionic = new BuildingPillar("marble_ionic", "ionic").setBlockTextureName(Info.ID + ":build/marble/" + "marble_pillar").setCreativeTab(Plantador.tabPlantador);
        marble_corinthian = new BuildingPillar("marble_corinthian", "corinthian").setBlockTextureName(Info.ID + ":build/marble/" + "marble_pillar").setCreativeTab(Plantador.tabPlantador);
        
        marble_chiseled = new BuildingBlock("marble_chiseled").setBlockTextureName(Info.ID + ":build/marble/" + "marble_chiseled").setCreativeTab(Plantador.tabPlantador);
        
        GameRegistry.registerBlock(clay_tiles, clay_tiles.getUnlocalizedName());
        GameRegistry.registerBlock(clay_tiles_stairs, clay_tiles_stairs.getUnlocalizedName());
        
        GameRegistry.registerBlock(marble, marble.getUnlocalizedName());
        GameRegistry.registerBlock(marble_brick, marble_brick.getUnlocalizedName());
        GameRegistry.registerBlock(marble_slab_half, marble_slab_half.getUnlocalizedName());
        GameRegistry.registerBlock(marble_stairs, marble_stairs.getUnlocalizedName());
        
        GameRegistry.registerBlock(marble_doric, marble_doric.getUnlocalizedName());
        GameRegistry.registerBlock(marble_ionic, marble_ionic.getUnlocalizedName());
        GameRegistry.registerBlock(marble_corinthian, marble_corinthian.getUnlocalizedName());
        
        GameRegistry.registerBlock(marble_chiseled, marble_chiseled.getUnlocalizedName());
        
        table_sculptor = new CraftingTable("table_sculptor", Plantador.TABLE_SCULPTOR).setBlockTextureName(Info.ID + ":build/" + "table_sculptor").setCreativeTab(Plantador.tabPlantador);
        GameRegistry.registerBlock(table_sculptor, table_sculptor.getUnlocalizedName());
        
        barrel = new Barrel(false).setBlockName("barrel").setBlockTextureName(Info.ID + ":build/" + "barrel").setCreativeTab(Plantador.tabPlantador);
        barrel_active = new Barrel(true).setBlockName("barrel_active").setBlockTextureName(Info.ID + ":build/" + "barrel");
        GameRegistry.registerBlock(barrel, barrel.getUnlocalizedName());
        GameRegistry.registerBlock(barrel_active, barrel_active.getUnlocalizedName());
        
        crafting();
    }
    
    public static void init()
    {
        
    }
    
    public static void postInit()
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
        GameRegistry.addRecipe(new ItemStack(table_sculptor), //table
                "xx",
                "xx",
                'x', new ItemStack(Blocks.stonebrick));
        CraftingManagerSculptor.getInstance().addShapelessRecipe(new ItemStack(marble), new ItemStack(Blocks.stone)); //marble
        CraftingManagerSculptor.getInstance().addRecipe(new ItemStack(marble_brick, 4), //marble brick
                "xx",
                "xx",
                'x',new ItemStack(marble));
        CraftingManagerSculptor.getInstance().addRecipe(new ItemStack(marble_chiseled, 4), //marble brick
                "xx",
                "xx",
                'x',new ItemStack(marble_brick));
        CraftingManagerSculptor.getInstance().addRecipe(new ItemStack(marble_slab_half, 6), //marble slab
                "xxx",
                'x', new ItemStack(marble_brick));
        CraftingManagerSculptor.getInstance().addRecipe(new ItemStack(marble_stairs, 4), //marble stairs
                "  x",
                " xx",
                "xxx",
                'x', new ItemStack(marble_brick));
        CraftingManagerSculptor.getInstance().addRecipe(new ItemStack(marble_doric), //marble doric
                "x",
                "x",
                "x",
                'x',new ItemStack(marble));
        CraftingManagerSculptor.getInstance().addRecipe(new ItemStack(marble_ionic), //marble ionic
                "y",
                "x",
                "x",
                'x',new ItemStack(marble),
                'y',new ItemStack(marble_brick));
        CraftingManagerSculptor.getInstance().addRecipe(new ItemStack(marble_corinthian), //marble corinthian
                "y",
                "x",
                "y",
                'x',new ItemStack(marble),
                'y',new ItemStack(marble_brick));
    }
}
