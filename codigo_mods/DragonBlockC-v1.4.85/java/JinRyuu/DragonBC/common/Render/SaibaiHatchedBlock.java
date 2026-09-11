/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Render;

import JinRyuu.DragonBC.common.Npcs.EntitySaibaiman;
import JinRyuu.DragonBC.common.Render.SaibaiHatchedTileEntity;
import JinRyuu.DragonBC.common.mod_DragonBC;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SaibaiHatchedBlock
extends BlockContainer {
    private Class SaibaiHatchedTileEntity = SaibaiHatchedTileEntity.class;
    int port1 = 0;

    public SaibaiHatchedBlock() {
        super(Material.field_151576_e);
        this.func_149675_a(true);
        this.func_149647_a(mod_DragonBC.DragonBlockC);
    }

    public String getTextureFile() {
        return "jinryuudragonbc:";
    }

    public void registerIcons(IIconRegister par1IconRegister) {
        this.field_149761_L = par1IconRegister.func_94245_a("jinryuudragonbc:" + this.func_149739_a());
    }

    public int idDropped(int i, Random random, int j) {
        return 0;
    }

    public int quanityDropped(Random random) {
        return 0;
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
        if (player.field_71075_bZ.field_75098_d) {
            EntitySaibaiman Saibaiman = new EntitySaibaiman(world);
            Saibaiman.func_70012_b((double)x + 0.5, y + 1, (double)z + 0.5, 0.0f, 0.0f);
            if (!world.field_72995_K) {
                world.func_72838_d((Entity)Saibaiman);
            }
        }
        return true;
    }

    public TileEntity func_149915_a(World world, int i) {
        return new SaibaiHatchedTileEntity();
    }
}

