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

import com.raffaele.plantador.plant.Plant;
import com.raffaele.plantador.build.Build;
import com.raffaele.plantador.food.Food;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = Info.ID, name = Info.NAME, acceptedMinecraftVersions = Info.MINECRAFT, version = Info.VERSION)
public class Plantador
{
    public static int TABLE_SCULPTOR = 1;
    
    /*@Instance
    public static Plantador instance = new Plantador();*/
    
    @Instance(Info.ID)
    public static Plantador instance;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        System.out.println("++++++++++MOD PREINIT++++++++++");
        Plant.preInit();
        Build.preInit();
        Food.preInit();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        System.out.println("++++++++++MOD INIT++++++++++");
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        Plant.init();
        Build.init();
        Food.init();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        System.out.println("++++++++++MOD POSTINIT++++++++++");
        Plant.postInit();
        Build.postInit();
        Food.postInit();
    }
}
