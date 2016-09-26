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
package com.raffaele.plantador.plant.cotton;

import com.raffaele.plantador.plant.Plant;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class CottonBlock extends BlockCrops {
    /**
     * seeds
     * @return 
     */
    @Override
    protected Item func_149866_i()
    {
        return Plant.cottonSeed;
    }
   
    /**
     * crop
     * @return
     */
    @Override
    protected Item func_149865_P()
    {
        return Plant.cottonCrop;
    }
    
    /**
     *
     * on changed condition
     * 
     * @param world
     * @param x
     * @param y
     * @param z
     * @param block 
     */  
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        if(!canBlockStay(world, x, y, z))
        {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }
    
    /**
     * 
     * @param world
     * @param x
     * @param y
     * @param z
     * @return Plant can stay on that block
     */
    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        return world.getBlock(x, y - 1, z) == Blocks.farmland;
    }
}
