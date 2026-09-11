/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemSword
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcs;
import noppes.npcs.items.ItemRenderInterface;
import org.lwjgl.opengl.GL11;

public class ItemNpcWeaponInterface
extends ItemSword
implements ItemRenderInterface {
    public ItemNpcWeaponInterface(int par1, Item.ToolMaterial material) {
        this(material);
    }

    public ItemNpcWeaponInterface(Item.ToolMaterial material) {
        super(material);
        this.func_77637_a(CustomItems.tab);
        CustomNpcs.proxy.registerItem((Item)this);
        this.func_77637_a(CustomItems.tabWeapon);
    }

    @Override
    public void renderSpecial() {
        GL11.glScalef((float)0.66f, (float)0.66f, (float)0.66f);
        GL11.glTranslatef((float)0.16f, (float)0.26f, (float)0.06f);
    }

    public Item func_77655_b(String name) {
        GameRegistry.registerItem((Item)this, (String)name);
        return super.func_77655_b(name);
    }
}

