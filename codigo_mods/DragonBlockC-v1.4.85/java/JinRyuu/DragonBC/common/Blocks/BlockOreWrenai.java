/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.Item
 *  net.minecraft.util.MathHelper
 */
package JinRyuu.DragonBC.common.Blocks;

import JinRyuu.DragonBC.common.Items.ItemsDBC;
import JinRyuu.DragonBC.common.mod_DragonBC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;

public class BlockOreWrenai
extends Block {
    public BlockOreWrenai() {
        super(Material.field_151576_e);
        this.func_149675_a(true);
        this.func_149711_c(3.0f);
        this.func_149752_b(5.0f);
        this.func_149672_a(field_149769_e);
        this.func_149647_a(mod_DragonBC.DragonBlockC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = par1IconRegister.func_94245_a("jinryuudragonbc:" + this.func_149739_a());
    }

    public int func_149679_a(int par1, Random par2Random) {
        return MathHelper.func_76125_a((int)(this.func_149745_a(par2Random) + par2Random.nextInt(par1 + 1)), (int)1, (int)1);
    }

    public int func_149745_a(Random par1Random) {
        return 2 + par1Random.nextInt(3);
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return ItemsDBC.ItemWarenai;
    }

    public String getTextureFile() {
        return "jinryuudragonbc:";
    }
}

