/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package JinRyuu.JRMCore.items;

import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWeight
extends Item {
    private float weight = 0.0f;

    public ItemWeight(int weight) {
        this.weight = weight;
        this.field_77777_bU = 1;
        this.func_77656_e(100);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = par1IconRegister.func_94245_a(JRMCoreH.tjjrmc + ":" + this.func_77658_a());
    }

    public void data() {
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        super.func_77624_a(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        float w = JRMCoreH.getItemWeight(p_77624_1_);
        if (w > 0.0f) {
            p_77624_3_.add(JRMCoreH.trl("jrmc", "trainingweight") + ": " + w);
        } else {
            String[] s = JRMCoreH.trl("jrmc", "trainingweightno").split(";");
            for (int i = 0; i < s.length; ++i) {
                p_77624_3_.add(s[i]);
            }
        }
    }

    public void func_77663_a(ItemStack p_77663_1_, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) {
        super.func_77663_a(p_77663_1_, p_77663_2_, p_77663_3_, p_77663_4_, p_77663_5_);
    }

    public void func_77622_d(ItemStack p_77622_1_, World p_77622_2_, EntityPlayer p_77622_3_) {
        super.func_77622_d(p_77622_1_, p_77622_2_, p_77622_3_);
        JRMCoreH.addItemWeightStats(p_77622_1_, 5);
    }

    public boolean func_77648_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        return false;
    }
}

