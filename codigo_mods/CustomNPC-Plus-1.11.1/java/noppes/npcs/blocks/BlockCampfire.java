/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcs;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.blocks.BlockLightable;
import noppes.npcs.blocks.tiles.TileCampfire;
import noppes.npcs.config.ConfigClient;
import org.lwjgl.opengl.GL11;

public class BlockCampfire
extends BlockLightable {
    public BlockCampfire(boolean lit) {
        super(Blocks.field_150347_e, lit);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.5f, 1.0f);
        if (lit) {
            this.func_149715_a(0.9375f);
        }
    }

    public TileEntity func_149915_a(World var1, int var2) {
        return new TileCampfire();
    }

    @Override
    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        ItemStack item = player.field_71071_by.func_70448_g();
        if (item == null) {
            return true;
        }
        int meta = world.func_72805_g(x, y, z);
        if ((item.func_77973_b() == Items.field_151145_ak || item.func_77973_b() == Items.field_151033_d) && this.unlitBlock() == this) {
            super.func_149727_a(world, x, y, z, player, par6, par7, par8, par9);
            CustomNpcs.proxy.spawnParticle("largesmoke", (float)x + 0.5f, (float)y + 0.5f, (float)z + 0.5f, 0.0, 0.0, 0.0, 2.0f);
            if (item.func_77973_b() == Items.field_151145_ak) {
                NoppesUtilServer.consumeItemStack(1, player);
            } else {
                item.func_77972_a(1, (EntityLivingBase)player);
            }
            return true;
        }
        if (item.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150354_m) && this.litBlock() == this) {
            super.func_149727_a(world, x, y, z, player, par6, par7, par8, par9);
        }
        return true;
    }

    @Override
    public int maxRotation() {
        return 8;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = par1IconRegister.func_94245_a(this.func_149641_N());
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int p_149691_1_, int meta) {
        return this.field_149761_L;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random random) {
        int meta = world.func_72805_g(x, y, z);
        if (meta == 1) {
            return;
        }
        if (random.nextInt(36) == 0) {
            world.func_72980_b((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), "fire.fire", 1.0f + random.nextFloat(), 0.3f + random.nextFloat() * 0.7f, false);
        }
        TileCampfire tile = (TileCampfire)world.func_147438_o(x, y, z);
        float xOffset = 0.5f;
        float yOffset = 0.7f;
        float zOffset = 0.5f;
        double d0 = (float)x + xOffset;
        double d1 = (float)y + yOffset;
        double d2 = (float)z + zOffset;
        GL11.glPushMatrix();
        if (ConfigClient.LegacyCampfire) {
            CustomNpcs.proxy.spawnParticle("largesmoke", d0, d1, d2, 0.0, 0.0, 0.0, 2.0f);
            CustomNpcs.proxy.spawnParticle("flame", d0, d1, d2, 0.0, 0.0, 0.0, 4.0f);
        } else {
            tile.func_145845_h();
        }
        GL11.glPopMatrix();
    }

    @Override
    public Block unlitBlock() {
        return CustomItems.campfire_unlit;
    }

    @Override
    public Block litBlock() {
        return CustomItems.campfire;
    }
}

