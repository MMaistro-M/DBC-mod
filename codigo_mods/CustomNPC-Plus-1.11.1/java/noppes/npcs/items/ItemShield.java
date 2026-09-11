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
import noppes.npcs.CustomItems;
import noppes.npcs.constants.EnumNpcToolMaterial;
import noppes.npcs.items.ItemNpcInterface;
import org.lwjgl.opengl.GL11;

public class ItemShield
extends ItemNpcInterface {
    public EnumNpcToolMaterial material;

    public ItemShield(int par1, EnumNpcToolMaterial material) {
        super(par1);
        this.material = material;
        this.func_77656_e(material.getMaxUses());
        this.func_77637_a(CustomItems.tabWeapon);
    }

    @Override
    public void renderSpecial() {
        GL11.glScalef((float)0.6f, (float)0.6f, (float)0.6f);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.2f);
        GL11.glRotatef((float)-6.0f, (float)0.0f, (float)1.0f, (float)0.0f);
    }

    public EnumAction func_77661_b(ItemStack par1ItemStack) {
        return EnumAction.block;
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        par3EntityPlayer.func_71008_a(par1ItemStack, this.func_77626_a(par1ItemStack));
        return par1ItemStack;
    }

    public int func_77626_a(ItemStack par1ItemStack) {
        return 72000;
    }
}

