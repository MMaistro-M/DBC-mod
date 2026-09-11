/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcs;
import noppes.npcs.items.ItemRenderInterface;
import org.lwjgl.opengl.GL11;

public class ItemNpcInterface
extends Item
implements ItemRenderInterface {
    public ItemNpcInterface(int par1) {
        this();
    }

    public ItemNpcInterface() {
        this.func_77637_a(CustomItems.tab);
        CustomNpcs.proxy.registerItem(this);
    }

    @Override
    public void renderSpecial() {
        GL11.glScalef((float)0.66f, (float)0.66f, (float)0.66f);
        GL11.glTranslatef((float)0.0f, (float)0.3f, (float)0.0f);
    }

    public int func_77619_b() {
        return super.func_77619_b();
    }

    public Item func_77655_b(String name) {
        super.func_77655_b(name);
        GameRegistry.registerItem((Item)this, (String)name);
        return this;
    }

    public boolean func_77644_a(ItemStack par1ItemStack, EntityLivingBase par2EntityLiving, EntityLivingBase par3EntityLiving) {
        if (par2EntityLiving.func_110143_aJ() <= 0.0f) {
            return false;
        }
        par1ItemStack.func_77972_a(1, par3EntityLiving);
        return true;
    }

    public boolean hasItem(EntityPlayer player, Item item) {
        return player.field_71071_by.func_146028_b(item);
    }

    public boolean consumeItem(EntityPlayer player, Item item) {
        return player.field_71071_by.func_146026_a(item);
    }
}

