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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class BuildingPillar extends BuildingBlock{
    
    private IIcon icons[] = new IIcon[3];
    
    public BuildingPillar(String name)
    {
        super(name);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister reg)
    {       
        icons[0] = reg.registerIcon(this.getTextureName() + "_top");
        icons[1] = reg.registerIcon(this.getTextureName() + "_side");
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int meta)
    {
        if (side==0 || side==1) //side 0 - 1 is top and bottom
        {
            return icons[0];
        }else{
            return icons[1];
        }
    }
}
