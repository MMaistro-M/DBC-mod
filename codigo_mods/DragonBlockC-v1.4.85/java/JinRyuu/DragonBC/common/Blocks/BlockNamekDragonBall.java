/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Blocks;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.Blocks.DBCMaterial;
import JinRyuu.DragonBC.common.mod_DragonBC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockNamekDragonBall
extends Block {
    protected int maxStackSize = 7;

    public BlockNamekDragonBall setMaxStackSize(int par1) {
        this.maxStackSize = par1;
        return this;
    }

    public int getItemStackLimit() {
        return this.maxStackSize;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = par1IconRegister.func_94245_a("jinryuudragonbc:" + this.func_149739_a());
    }

    public BlockNamekDragonBall(int par1) {
        super(DBCMaterial.dragonblock);
        this.func_149675_a(true);
        this.func_149711_c(0.1f);
        float var4 = 0.4f;
        this.func_149676_a(0.5f - var4, 0.4f - var4, 0.5f - var4, 0.5f + var4, 0.4f + var4, 0.5f + var4);
        this.func_149647_a(mod_DragonBC.DragonBlockC);
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t) {
        TileEntity tile_entity = world.func_147438_o(x, y, z);
        if (tile_entity == null || player.func_70093_af()) {
            return false;
        }
        if (!world.field_72995_K) {
            World par1World = world;
            if (par1World.func_147439_a(x + 1, y, z) == this) {
                if (par1World.func_147439_a(x + 1, y, z + 1) == this && par1World.func_147439_a(x + 1, y, z - 1) == this && par1World.func_147439_a(x - 1, y, z) == this && par1World.func_147439_a(x - 1, y, z - 1) == this && par1World.func_147439_a(x - 1, y, z + 1) == this) {
                    par1World.func_147449_b(x, y, z, BlocksDBC.BlockDragonBallStone);
                }
            } else if (par1World.func_147439_a(x, y, z + 1) == this && par1World.func_147439_a(x + 1, y, z + 1) == this && par1World.func_147439_a(x + 1, y, z - 1) == this && par1World.func_147439_a(x, y, z - 1) == this && par1World.func_147439_a(x - 1, y, z - 1) == this && par1World.func_147439_a(x - 1, y, z + 1) == this) {
                par1World.func_147449_b(x, y, z, BlocksDBC.BlockDragonBallStone);
            }
        }
        return true;
    }

    public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random) {
        if (!par1World.field_72995_K) {
            if (par1World.func_147439_a(par2 + 1, par3, par4) == this) {
                if (par1World.func_147439_a(par2 + 1, par3, par4 + 1) == this && par1World.func_147439_a(par2 + 1, par3, par4 - 1) == this && par1World.func_147439_a(par2 - 1, par3, par4) == this && par1World.func_147439_a(par2 - 1, par3, par4 - 1) == this && par1World.func_147439_a(par2 - 1, par3, par4 + 1) == this) {
                    par1World.func_147449_b(par2, par3, par4, BlocksDBC.BlockDragonBallStone);
                }
            } else if (par1World.func_147439_a(par2, par3, par4 + 1) == this && par1World.func_147439_a(par2 + 1, par3, par4 + 1) == this && par1World.func_147439_a(par2 + 1, par3, par4 - 1) == this && par1World.func_147439_a(par2, par3, par4 - 1) == this && par1World.func_147439_a(par2 - 1, par3, par4 - 1) == this && par1World.func_147439_a(par2 - 1, par3, par4 + 1) == this) {
                par1World.func_147449_b(par2, par3, par4, BlocksDBC.BlockDragonBallStone);
            }
        }
    }

    public void addCreativeItems(ArrayList itemList) {
        itemList.add(new ItemStack((Block)this, 1));
    }

    public int func_149679_a(int par1, Random par2Random) {
        return MathHelper.func_76125_a((int)(this.func_149745_a(par2Random) + par2Random.nextInt(par1 + 1)), (int)1, (int)1);
    }

    public int func_149745_a(Random par1Random) {
        return 2 + par1Random.nextInt(3);
    }

    public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
        return null;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return 0;
    }

    public boolean func_149662_c() {
        return false;
    }
}

