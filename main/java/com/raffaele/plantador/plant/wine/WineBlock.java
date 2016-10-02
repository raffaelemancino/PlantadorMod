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
package com.raffaele.plantador.plant.wine;

import com.raffaele.plantador.plant.Plant;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class WineBlock extends BlockCrops
{
    
    private IIcon[] level;
    
    public WineBlock()
    {
        float f = 0.375F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        Block underBlock = world.getBlock(x, y - 1, z);
        return underBlock == Blocks.farmland || underBlock == this;
    }
    
    @Override
    public void updateTick(World world, int x, int y, int z, Random rand)
    {
        super.updateTick(world, x, y, z, rand);

        if (world.getBlockLightValue(x, y + 1, z) >= 9)
        {
            int meta = world.getBlockMetadata(x, y, z);

            if (meta < 7)
            {
                float f = 1.0F;

                if (rand.nextInt((int)(25.0F / f) + 1) == 0)
                {
                    ++meta;
                    world.setBlockMetadataWithNotify(x, y, z, meta, 2);
                }
            }
            
            if (world.isAirBlock(x, y+1, z))
            {
                int hight;
                for(hight=0;world.getBlock(x, y-hight, z)==this;hight++)
                {
                    ;
                }
                if (hight<3 && meta>=7)
                {
                    if (rand.nextInt(15)==0)
                    {
                        world.setBlock(x, y+1, z, this);
                        world.setBlockMetadataWithNotify(x, y+1, z, 8, 2);
                    }
                }
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.level = new IIcon[9];

        for (int i = 0; i < this.level.length; ++i)
        {
            this.level[i] = p_149651_1_.registerIcon(this.getTextureName() + "_stage_" + i);
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.level[meta];
    }
    
    
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();

        if (metadata == 8)
        {
            for (int i = 0; i < 2 + fortune; ++i)
            {
                if (world.rand.nextInt(15) <= metadata)
                {
                    ret.add(new ItemStack(this.func_149865_P(), 2, 0));
                }
            }
        }else{
            ret.add(new ItemStack(this.func_149866_i(), 1, 0));
        }

        return ret;
    }
    
    @Override
    protected Item func_149866_i()
    {
        return Plant.wineSeed;
    }
    
    @Override
    protected Item func_149865_P()
    {
        return Plant.wineCrop;
    }
}