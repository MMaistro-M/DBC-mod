/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 */
package noppes.npcs.blocks.tiles;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import noppes.npcs.blocks.tiles.ITileIcon;
import noppes.npcs.blocks.tiles.TileVariant;

public class TileSign
extends TileVariant
implements ITileIcon {
    public ItemStack icon;
    public long time = 0L;

    @Override
    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.icon = ItemStack.func_77949_a((NBTTagCompound)compound.func_74775_l("BannerIcon"));
    }

    @Override
    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
        if (this.icon != null) {
            compound.func_74782_a("BannerIcon", (NBTBase)this.icon.func_77955_b(new NBTTagCompound()));
        }
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 2), (double)(this.field_145849_e + 1));
    }

    @Override
    public boolean canEdit() {
        return System.currentTimeMillis() - this.time < 20000L;
    }

    @Override
    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public void setIcon(ItemStack stack) {
        this.icon = stack;
    }
}

