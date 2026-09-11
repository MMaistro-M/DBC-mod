/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import noppes.npcs.items.ItemMusic;
import org.lwjgl.opengl.GL11;

public class ItemMusicClarinet
extends ItemMusic {
    @Override
    public void renderSpecial() {
        GL11.glScalef((float)0.8f, (float)0.9f, (float)0.8f);
        GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)0.44f, (float)-0.7f, (float)-0.04f);
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }

    @Override
    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par3EntityPlayer.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
        return super.func_77659_a(par1ItemStack, par2World, par3EntityPlayer);
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }
}

