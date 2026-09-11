/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.blocks.tiles;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.blocks.tiles.TileVariant;
import noppes.npcs.items.ItemNpcTool;

public class TileColorable
extends TileVariant {
    public int color = 0xFFFFFF;

    @Override
    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.color = compound.func_74762_e(ItemNpcTool.BRUSH_COLOR_TAG);
    }

    @Override
    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
        compound.func_74768_a(ItemNpcTool.BRUSH_COLOR_TAG, this.color);
    }

    public void setColor(int color) {
        this.color = color;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public static ColorChangeType allowColorChange(ItemStack stack) {
        if (stack == null || stack.func_77973_b() == null) {
            return ColorChangeType.NONE;
        }
        if (stack.func_77973_b() == Items.field_151100_aR) {
            return ColorChangeType.DYE;
        }
        if (ItemNpcTool.isPaintbrush(stack)) {
            return ColorChangeType.PAINTBRUSH;
        }
        return ColorChangeType.NONE;
    }

    public static enum ColorChangeType {
        NONE,
        DYE,
        PAINTBRUSH;

    }
}

