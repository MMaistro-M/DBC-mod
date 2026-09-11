/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.blocks.tiles;

import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.blocks.tiles.TileColorable;

public class TileCouchWool
extends TileColorable {
    public boolean hasLeft = false;
    public boolean hasRight = false;
    public boolean hasCornerLeft = false;
    public boolean hasCornerRight = false;

    @Override
    public void func_145839_a(NBTTagCompound compound) {
        super.func_145839_a(compound);
        this.hasLeft = compound.func_74767_n("CouchLeft");
        this.hasRight = compound.func_74767_n("CouchRight");
        this.hasCornerLeft = compound.func_74767_n("CouchCornerLeft");
        this.hasCornerRight = compound.func_74767_n("CouchCornerRight");
    }

    @Override
    public void func_145841_b(NBTTagCompound compound) {
        super.func_145841_b(compound);
        compound.func_74757_a("CouchLeft", this.hasLeft);
        compound.func_74757_a("CouchRight", this.hasRight);
        compound.func_74757_a("CouchCornerLeft", this.hasCornerLeft);
        compound.func_74757_a("CouchCornerRight", this.hasCornerRight);
    }
}

