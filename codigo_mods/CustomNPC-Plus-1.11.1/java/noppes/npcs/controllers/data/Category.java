/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import net.minecraft.nbt.NBTTagCompound;

public class Category {
    public int id = -1;
    public String title = "";

    public Category() {
    }

    public Category(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public void readNBT(NBTTagCompound compound) {
        this.id = compound.func_74762_e("CatID");
        this.title = compound.func_74779_i("CatTitle");
    }

    public NBTTagCompound writeNBT(NBTTagCompound compound) {
        compound.func_74768_a("CatID", this.id);
        compound.func_74778_a("CatTitle", this.title);
        return compound;
    }
}

