/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Render;

import JinRyuu.DragonBC.common.mod_DragonBC;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DragonBlockNamek01Item
extends Item {
    private Block spawn;

    public DragonBlockNamek01Item(Block spawn) {
        this.spawn = spawn;
        this.func_77637_a(mod_DragonBC.DragonBlockC);
        this.field_77777_bU = 7;
    }

    public String getTextureFile() {
        return "jinryuudragonbc:dragonitems1.png";
    }

    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a("jinryuudragonbc:" + this.func_77658_a());
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if (par3World.func_147439_a(par4, par5, par6) != Blocks.field_150433_aE) {
            if (par7 == 0) {
                --par5;
            }
            if (par7 == 1) {
                ++par5;
            }
            if (par7 == 2) {
                --par6;
            }
            if (par7 == 3) {
                ++par6;
            }
            if (par7 == 4) {
                --par4;
            }
            if (par7 == 5) {
                ++par4;
            }
            if (!par3World.func_147437_c(par4, par5, par6)) {
                return false;
            }
        }
        if (!par2EntityPlayer.func_82247_a(par4, par5, par6, par7, par1ItemStack)) {
            return false;
        }
        if (this.spawn != null && this.spawn.func_149742_c(par3World, par4, par5, par6)) {
            --par1ItemStack.field_77994_a;
            par3World.func_147449_b(par4, par5, par6, this.spawn);
        }
        return true;
    }
}

