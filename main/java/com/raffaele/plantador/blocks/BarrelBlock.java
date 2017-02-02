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
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.block.Block;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class BarrelBlock extends BlockContainer
{
    
    @SideOnly(Side.CLIENT)
    private IIcon[] side = new IIcon[3];
    private static boolean isBrewing;
    private final boolean isBrewing2;
    private final Random random = new Random();
    
    public BarrelBlock(boolean active)
    {
        super(Material.wood);
        this.setHardness(2.0F);
        this.setResistance(5.0F);
        this.setStepSound(soundTypeWood);
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
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileEntityBarrel();
    }
    
    @Override
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
    
    public static void updateBarrelBlockState(boolean bool, World world, int x, int y, int z)
    {
        int l = world.getBlockMetadata(x, y, z);
        TileEntity tileentity = world.getTileEntity(x, y, z);
        isBrewing = true;

        if (bool)
        {
            world.setBlock(x, y, z, PlantadorBlocks.barrel_active);
        }
        else
        {
            world.setBlock(x, y, z, PlantadorBlocks.barrel);
        }

        isBrewing = false;
        world.setBlockMetadataWithNotify(x, y, z, l, 2);

        if (tileentity != null)
        {
            tileentity.validate();
            world.setTileEntity(x, y, z, tileentity);
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        if (this.isBrewing2)
        {
            float rx = random.nextFloat();
            float rz = random.nextFloat();
            world.spawnParticle("smoke", (double)(x + rx), (double)y+1.2F, (double)(z + rz), 0.0D, 0.0D, 0.0D);
            rx = random.nextFloat();
            rz = random.nextFloat();
            world.spawnParticle("spell", (double)(x + rx), (double)y+1.2F, (double)(z + rz), 0.0D, 0.0D, 0.0D);
        }
    }
}
