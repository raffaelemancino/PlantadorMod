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
package com.raffaele.plantador.blocks;

import com.raffaele.plantador.Plantador;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class Barrel extends Block
{
    
    @SideOnly(Side.CLIENT)
    private IIcon[] side = new IIcon[3];
    private static boolean isBrewing;
    private final boolean isBrewing2;
    private final Random random = new Random();
    
    public Barrel(boolean active)
    {
        super(Material.wood);
        this.isBrewing2 = active;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        for(int i=0;i<2;i++)
        {
            this.side[i] = iconRegister.registerIcon(this.getTextureName() + "_" + i);
        }
    }
    
    @Override
    public IIcon getIcon(int side, int meta) {
        if (side==0 || side==1)
        {
            return this.side[0];
        }else{
            return this.side[1];
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float h)
    {
        player.openGui(Plantador.instance, Plantador.BARREL, world, x, y, z);
        return true;
    }

    @Override
    public Item getItemDropped(int i, Random r, int j)
    {
        return Item.getItemFromBlock(PlantadorBlocks.barrel);
    }
    
    /**
    * continuare
    * https://www.youtube.com/watch?v=lCH-S4LPF0E    
    */
    
    
}
