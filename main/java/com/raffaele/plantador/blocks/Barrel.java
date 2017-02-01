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
import com.raffaele.plantador.blocks.barrel.TileEntityBarrel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
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
    
    @Override
    public Item getItem(World world, int x, int y, int z) {
        return Item.getItemFromBlock(PlantadorBlocks.barrel);
    }
    
    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        this.direction(world, x, y, z);
    }

    private void direction(World world, int x, int y, int z) {
        if (!world.isRemote)
        {
            Block blockdz = world.getBlock(x, y, z - 1);
            Block blocksz = world.getBlock(x, y, z + 1);
            Block blockdx = world.getBlock(x - 1, y, z);
            Block blocksx = world.getBlock(x + 1, y, z);
            byte direction=3;
            if( blockdz.func_149730_j() && !blocksz.func_149730_j() )
            {
                direction=3;
            }
            if( !blockdz.func_149730_j() && blocksz.func_149730_j() )
            {
                direction=2;
            }
            if( blockdx.func_149730_j() && !blocksx.func_149730_j() )
            {
                direction=5;
            }
            if( blockdz.func_149730_j() && !blocksz.func_149730_j() )
            {
                direction=4;
            }
            world.setBlockMetadataWithNotify(x, y, z, direction, 2);
            world.spawnParticle(textureName, minX, minX, minX, minX, minX, minX);
        }
    }
    
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack) {
	int direction = MathHelper.floor_double((double) (entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        
	if (direction == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
	}
        if (direction == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
	}
        if (direction == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
	}
	if (direction == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
	}
        if (itemstack.hasDisplayName()) {
            //((TileEntityTutFurnace) world.getTileEntity(x, y, z)).furnaceName(itemstack.getDisplayName());
	}
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntityBarrel();
    }
    
    public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		if (!isBrewing) {
			TileEntityBarrel tileEntityBarrel = (TileEntityBarrel) world.getTileEntity(x, y, z);

			if (tileEntityBarrel != null) {
				for (int i = 0; i < tileEntityBarrel.getSizeInventory(); ++i) {
					ItemStack itemstack = tileEntityBarrel.getStackInSlot(i);

					if (itemstack != null) {
						float f = this.random.nextFloat() * 0.6F + 0.1F;
						float f1 = this.random.nextFloat() * 0.6F + 0.1F;
						float f2 = this.random.nextFloat() * 0.6F + 0.1F;

						while (itemstack.stackSize > 0) {
							int j = this.random.nextInt(21) + 10;

							if (j > itemstack.stackSize) {
								j = itemstack.stackSize;
							}

							itemstack.stackSize -= j;
							EntityItem entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));

							if (itemstack.hasTagCompound()) {
								entityitem.getEntityItem().setTagCompound(((NBTTagCompound) itemstack.getTagCompound().copy()));
							}

							float f3 = 0.025F;
							entityitem.motionX = (double) ((float) this.random.nextGaussian() * f3);
							entityitem.motionY = (double) ((float) this.random.nextGaussian() * f3 + 0.1F);
							entityitem.motionZ = (double) ((float) this.random.nextGaussian() * f3);
							world.spawnEntityInWorld(entityitem);
						}
					}
				}
				world.func_147453_f(x, y, z, block);
			}
		}
		super.breakBlock(world, x, y, z, block, meta);
	}
    
    /**
    * continuare
    * https://www.youtube.com/watch?v=lCH-S4LPF0E    
    */
    
}
