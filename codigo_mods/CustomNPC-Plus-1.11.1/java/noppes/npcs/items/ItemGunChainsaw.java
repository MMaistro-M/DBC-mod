/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import noppes.npcs.items.ItemNpcWeaponInterface;
import org.lwjgl.opengl.GL11;

public class ItemGunChainsaw
extends ItemNpcWeaponInterface {
    public ItemGunChainsaw(int par1, Item.ToolMaterial tool) {
        super(par1, tool);
    }

    public boolean func_77644_a(ItemStack par1ItemStack, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving) {
        if (par2EntityLiving.func_110143_aJ() <= 0.0f) {
            return false;
        }
        double x = par2EntityLiving.field_70165_t;
        double y = par2EntityLiving.field_70163_u + (double)(par2EntityLiving.field_70131_O / 2.0f);
        double z = par2EntityLiving.field_70161_v;
        par3EntityLiving.field_70170_p.func_72908_a(x, y, z, "random.explode", 0.8f, (1.0f + (par3EntityLiving.field_70170_p.field_73012_v.nextFloat() - par3EntityLiving.field_70170_p.field_73012_v.nextFloat()) * 0.2f) * 0.7f);
        par3EntityLiving.field_70170_p.func_72869_a("largeexplode", x, y, z, 0.0, 0.0, 0.0);
        return super.func_77644_a(par1ItemStack, par2EntityLiving, par3EntityLiving);
    }

    @Override
    public void renderSpecial() {
        super.renderSpecial();
        GL11.glTranslatef((float)-0.1f, (float)0.0f, (float)-0.16f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)-16.0f, (float)0.0f, (float)0.0f, (float)1.0f);
    }
}

