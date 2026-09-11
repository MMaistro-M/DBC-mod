/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Render;

import JinRyuu.DragonBC.common.Blocks.DBCMaterial;
import JinRyuu.DragonBC.common.Items.ItemsDBC;
import JinRyuu.DragonBC.common.Npcs.EntityPorunga;
import JinRyuu.DragonBC.common.Render.DragonBlockNamek01TileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class DragonBlockNamek01Block
extends BlockContainer {
    private Class DragonBlockNamek01TileEntity = DragonBlockNamek01TileEntity.class;
    int port1 = 0;

    public DragonBlockNamek01Block() {
        super(DBCMaterial.dragonblock);
        this.func_149711_c(0.1f);
        float var4 = 0.4f;
        this.func_149676_a(0.5f - var4, 0.4f - var4, 0.5f - var4, 0.5f + var4, 0.4f + var4, 0.5f + var4);
    }

    public String getTextureFile() {
        return "jinryuudragonbc:";
    }

    public void registerIcons(IIconRegister par1IconRegister) {
        this.field_149761_L = par1IconRegister.func_94245_a("jinryuudragonbc:" + this.func_149739_a());
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        Item item = ItemsDBC.ItemNamekDragonBlock;
        DragonBlockNamek01Block block = item instanceof ItemBlock && !this.func_149648_K() ? Block.func_149634_a((Item)item) : this;
        return new ItemStack(item, 1, block.func_149643_k(world, x, y, z));
    }

    @SideOnly(value=Side.CLIENT)
    public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return ItemsDBC.ItemNamekDragonBlock;
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return ItemsDBC.ItemNamekDragonBlock;
    }

    public int quanityDropped(Random random) {
        return 1;
    }

    public int func_149645_b() {
        return -1;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
        return null;
    }

    public int tickRate() {
        return 1;
    }

    public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random) {
    }

    public void func_149670_a(World par1World, int par2, int par3, int par4, Entity entity) {
        if (entity.field_70154_o == null && entity.field_70153_n == null && entity instanceof EntityPlayerMP) {
            EntityPlayerMP thePlayer = (EntityPlayerMP)entity;
            ++this.port1;
        }
    }

    public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer player, int i, float f, float g, float t) {
        TileEntity tile_entity = par1World.func_147438_o(par2, par3, par4);
        if (tile_entity == null || player.func_70093_af()) {
            return false;
        }
        if (!par1World.field_72995_K) {
            if (par1World.func_147439_a(par2 + 1, par3, par4) == this) {
                if (par1World.func_147439_a(par2 + 1, par3, par4 + 1) == this && par1World.func_147439_a(par2 + 1, par3, par4 - 1) == this && par1World.func_147439_a(par2 - 1, par3, par4) == this && par1World.func_147439_a(par2 - 1, par3, par4 - 1) == this && par1World.func_147439_a(par2 - 1, par3, par4 + 1) == this) {
                    if (!par1World.field_72995_K) {
                        EntityPorunga Dragon = new EntityPorunga(par1World);
                        Dragon.func_70012_b(par2, par3, par4, f, 0.0f);
                        par1World.func_72838_d((Entity)Dragon);
                        par1World.func_72908_a((double)par2, (double)par3, (double)par4, "jinryuudragonbc:dragon.makeone", 1.0f, 1.0f);
                    }
                    par1World.func_147449_b(par2 + 1, par3, par4, Blocks.field_150350_a);
                    par1World.func_147449_b(par2 + 1, par3, par4 + 1, Blocks.field_150350_a);
                    par1World.func_147449_b(par2 + 1, par3, par4 - 1, Blocks.field_150350_a);
                    par1World.func_147449_b(par2 - 1, par3, par4, Blocks.field_150350_a);
                    par1World.func_147449_b(par2 - 1, par3, par4 - 1, Blocks.field_150350_a);
                    par1World.func_147449_b(par2 - 1, par3, par4 + 1, Blocks.field_150350_a);
                    par1World.func_147449_b(par2, par3, par4, Blocks.field_150350_a);
                }
            } else if (par1World.func_147439_a(par2, par3, par4 + 1) == this && par1World.func_147439_a(par2 + 1, par3, par4 + 1) == this && par1World.func_147439_a(par2 + 1, par3, par4 - 1) == this && par1World.func_147439_a(par2, par3, par4 - 1) == this && par1World.func_147439_a(par2 - 1, par3, par4 - 1) == this && par1World.func_147439_a(par2 - 1, par3, par4 + 1) == this) {
                if (!par1World.field_72995_K) {
                    EntityPorunga Dragon = new EntityPorunga(par1World);
                    Dragon.func_70012_b(par2, par3, par4, f, 0.0f);
                    par1World.func_72838_d((Entity)Dragon);
                    par1World.func_72908_a((double)par2, (double)par3, (double)par4, "jinryuudragonbc:dragon.makeone", 1.0f, 1.0f);
                }
                par1World.func_147449_b(par2, par3, par4 + 1, Blocks.field_150350_a);
                par1World.func_147449_b(par2 + 1, par3, par4 + 1, Blocks.field_150350_a);
                par1World.func_147449_b(par2 + 1, par3, par4 - 1, Blocks.field_150350_a);
                par1World.func_147449_b(par2, par3, par4 - 1, Blocks.field_150350_a);
                par1World.func_147449_b(par2 - 1, par3, par4 - 1, Blocks.field_150350_a);
                par1World.func_147449_b(par2 - 1, par3, par4 + 1, Blocks.field_150350_a);
                par1World.func_147449_b(par2, par3, par4, Blocks.field_150350_a);
            }
        }
        return true;
    }

    public void func_149749_a(World world, int x, int y, int z, Block i, int j) {
        super.func_149749_a(world, x, y, z, i, j);
    }

    public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
        return new DragonBlockNamek01TileEntity();
    }
}

