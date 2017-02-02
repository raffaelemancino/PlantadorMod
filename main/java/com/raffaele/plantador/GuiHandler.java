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
package com.raffaele.plantador;

import com.raffaele.plantador.blocks.barrel.ContainerBarrel;
import com.raffaele.plantador.blocks.barrel.GuiBarrel;
import com.raffaele.plantador.blocks.barrel.TileEntityBarrel;
import com.raffaele.plantador.blocks.sculptortable.GuiSculptor;
import com.raffaele.plantador.blocks.sculptortable.ContainerSculptor;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class GuiHandler implements IGuiHandler{

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {        
        if (ID == Plantador.TABLE_SCULPTOR)
            return new ContainerSculptor(player.inventory, world, x, y, z);
        if (ID == Plantador.BARREL) {
            TileEntityBarrel entityBarrel = (TileEntityBarrel)world.getTileEntity(x, y, z);
            return new ContainerBarrel(player.inventory, entityBarrel);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        if (ID == Plantador.TABLE_SCULPTOR)
            return new GuiSculptor(player.inventory, world, x, y, z);
        if (ID == Plantador.BARREL) {
            TileEntityBarrel entityBarrel = (TileEntityBarrel)world.getTileEntity(x, y, z);
            return new  GuiBarrel(player.inventory, entityBarrel);
        }
        return null;
    }
    
}
