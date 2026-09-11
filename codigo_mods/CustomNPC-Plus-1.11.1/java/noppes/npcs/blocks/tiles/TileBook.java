/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.blocks.tiles;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.blocks.tiles.TileVariant;

public class TileBook
extends TileVariant {
    public ItemStack book = new ItemStack(Items.field_151099_bA);

    @Override
    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.book = ItemStack.func_77949_a((NBTTagCompound)compound.func_74775_l("Items"));
        if (this.book == null) {
            this.book = new ItemStack(Items.field_151099_bA);
        }
    }

    @Override
    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
        compound.func_74782_a("Items", (NBTBase)this.book.func_77955_b(new NBTTagCompound()));
    }
}

