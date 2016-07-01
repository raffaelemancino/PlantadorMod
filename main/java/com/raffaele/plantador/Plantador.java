package com.raffaele.plantador;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.world.World;

@Mod(modid = Info.ID, name = Info.NAME, acceptedMinecraftVersions = Info.MINECRAFT, version = Info.VERSION)
public class Plantador
{
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        System.out.println("Mod PreInit");
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        System.out.println("Mod Init");
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        System.out.println("Mod PostInit");
    }
}
