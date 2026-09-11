/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import java.util.UUID;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.handler.data.ITag;
import noppes.npcs.controllers.TagController;

public class Tag
implements ITag {
    public String name = "";
    public int color = Integer.parseInt("FF00", 16);
    public int id = -1;
    public UUID uuid = UUID.randomUUID();
    public boolean hideTag = false;

    public Tag() {
    }

    public Tag(int id, String name, int color) {
        this.name = name;
        this.color = color;
        this.id = id;
    }

    public static String formatName(String name) {
        name = name.toLowerCase().trim();
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public void readNBT(NBTTagCompound compound) {
        this.name = compound.func_74779_i("Name");
        this.color = compound.func_74762_e("Color");
        this.id = compound.func_74762_e("Slot");
        this.hideTag = compound.func_74767_n("HideTag");
        this.uuid = UUID.fromString(compound.func_74779_i("Uuid"));
    }

    public void writeNBT(NBTTagCompound compound) {
        compound.func_74768_a("Slot", this.id);
        compound.func_74778_a("Name", this.name);
        compound.func_74768_a("Color", this.color);
        compound.func_74778_a("Uuid", this.uuid.toString());
        compound.func_74757_a("HideTag", this.hideTag);
    }

    public void readShortNBT(NBTTagCompound compound) {
        this.name = compound.func_74779_i("Name");
        this.uuid = UUID.fromString(compound.func_74779_i("Uuid"));
    }

    public void writeShortNBT(NBTTagCompound compound) {
        compound.func_74778_a("Name", this.name);
        compound.func_74778_a("Uuid", this.uuid.toString());
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getUuid() {
        return this.uuid.toString();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int getColor() {
        return this.color;
    }

    @Override
    public boolean getIsHidden() {
        return this.hideTag;
    }

    @Override
    public void setIsHidden(boolean bo) {
        this.hideTag = bo;
    }

    @Override
    public void save() {
        TagController.getInstance().saveTag(this);
    }
}

