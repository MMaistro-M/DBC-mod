/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Render;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.Render.ArtGravDevTileEntity;
import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ArtGravDevBlock
extends BlockContainer {
    private final Random field_149933_a = new Random();
    private Class ArtGravDevTileEntity = ArtGravDevTileEntity.class;

    public ArtGravDevBlock() {
        super(Material.field_151573_f);
        this.func_149711_c(3.0f);
        this.func_149752_b(5.0f);
        this.func_149675_a(true);
        this.func_149647_a(mod_DragonBC.DragonBlockC);
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add(JRMCoreH.trl("dbc", "ArtGravDevDesc"));
    }

    public String getTextureFile() {
        return "jinryuudragonbc:";
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

    public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random) {
    }

    public int func_149738_a(World par1World) {
        return 1;
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int i, float f, float g, float t) {
        TileEntity tile_entity = world.func_147438_o(x, y, z);
        if (tile_entity == null || player.func_70093_af()) {
            return false;
        }
        player.openGui((Object)mod_DragonBC.instance, 5, world, x, y, z);
        return true;
    }

    public TileEntity func_149915_a(World world, int i) {
        return new ArtGravDevTileEntity();
    }

    public static void updateFurnaceBlockState(boolean p_149931_0_, World p_149931_1_, int p_149931_2_, int p_149931_3_, int p_149931_4_) {
        int l = p_149931_1_.func_72805_g(p_149931_2_, p_149931_3_, p_149931_4_);
        TileEntity tileentity = p_149931_1_.func_147438_o(p_149931_2_, p_149931_3_, p_149931_4_);
        if (p_149931_0_) {
            p_149931_1_.func_147449_b(p_149931_2_, p_149931_3_, p_149931_4_, BlocksDBC.ArtGravDevBlock);
        } else {
            p_149931_1_.func_147449_b(p_149931_2_, p_149931_3_, p_149931_4_, BlocksDBC.ArtGravDevBlock);
        }
        p_149931_1_.func_72921_c(p_149931_2_, p_149931_3_, p_149931_4_, l, 2);
        if (tileentity != null) {
            tileentity.func_145829_t();
            p_149931_1_.func_147455_a(p_149931_2_, p_149931_3_, p_149931_4_, tileentity);
        }
    }

    public void func_149749_a(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
        ArtGravDevTileEntity tileentityfurnace = (ArtGravDevTileEntity)p_149749_1_.func_147438_o(p_149749_2_, p_149749_3_, p_149749_4_);
        if (tileentityfurnace != null) {
            for (int i1 = 0; i1 < tileentityfurnace.func_70302_i_(); ++i1) {
                ItemStack itemstack = tileentityfurnace.func_70301_a(i1);
                if (itemstack == null) continue;
                float f = this.field_149933_a.nextFloat() * 0.8f + 0.1f;
                float f1 = this.field_149933_a.nextFloat() * 0.8f + 0.1f;
                float f2 = this.field_149933_a.nextFloat() * 0.8f + 0.1f;
                while (itemstack.field_77994_a > 0) {
                    int j1 = this.field_149933_a.nextInt(21) + 10;
                    if (j1 > itemstack.field_77994_a) {
                        j1 = itemstack.field_77994_a;
                    }
                    itemstack.field_77994_a -= j1;
                    EntityItem entityitem = new EntityItem(p_149749_1_, (double)((float)p_149749_2_ + f), (double)((float)p_149749_3_ + f1), (double)((float)p_149749_4_ + f2), new ItemStack(itemstack.func_77973_b(), j1, itemstack.func_77960_j()));
                    if (itemstack.func_77942_o()) {
                        entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
                    }
                    float f3 = 0.05f;
                    entityitem.field_70159_w = (float)this.field_149933_a.nextGaussian() * f3;
                    entityitem.field_70181_x = (float)this.field_149933_a.nextGaussian() * f3 + 0.2f;
                    entityitem.field_70179_y = (float)this.field_149933_a.nextGaussian() * f3;
                    p_149749_1_.func_72838_d((Entity)entityitem);
                }
            }
            p_149749_1_.func_147453_f(p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_);
        }
        super.func_149749_a(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
    }
}

