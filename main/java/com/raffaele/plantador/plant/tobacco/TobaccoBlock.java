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
package com.raffaele.plantador.plant.tobacco;

import com.raffaele.plantador.plant.Plant;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class TobaccoBlock extends BlockCrops {
    /**
     * seeds
     * @return 
     */
    @Override
    protected Item func_149866_i()
    {
        return Plant.tobaccoSeed;
    }
   
    /**
     * crop
     * @return
     */
    @Override
    protected Item func_149865_P()
    {
        return Plant.tobaccoCrop;
    }
    
    /**
     * tobacco leafs to drop
     * @param rand
     * @return integer quantity
     */
    /*@Override
    public int quantityDropped(Random rand)
    {
        return 3;
    }*/
    
    /**
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
    
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this.func_149865_P(), 1, 0));

        if (metadata >= 7)
        {
            for (int i = 0; i < 1 + fortune; ++i)
            {
                if (world.rand.nextInt(15) <= metadata)
                {
                    ret.add(new ItemStack(this.func_149865_P(), 1, 0));
                }
            }
        }

        return ret;
    }
}
