/*
 * Copyright (C) 2017 Raffaele Francesco Mancino
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
package com.raffaele.plantador.plant.lettuce;

import com.raffaele.plantador.plant.Plant;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class LettuceBlock extends BlockCrops
{
    private IIcon[] level;

    @Override
    protected Item func_149865_P() {
        return Plant.lettuce;
    }

    @Override
    protected Item func_149866_i() {
        return Plant.lettuce;
    }

    @Override
    public int getRenderType() {
        return 1;
    }

    @Override
    public void registerBlockIcons(IIconRegister register) {
        this.level = new IIcon[4];
        
        for(int i=0; i<this.level.length; i++)
        {
            this.level[i] = register.registerIcon(this.getTextureName() + "_stage_" + i);
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta) {
        if (meta < 7)
        {
            if (meta == 6)
            {
                meta = 5;
            }

            return this.level[meta >> 1];
        }
        else
        {
            return this.level[3];
        }
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        return world.getBlock(x, y-1, z)==Blocks.farmland;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        if(!canBlockStay(world, x, y, z))
        {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }
    
}
