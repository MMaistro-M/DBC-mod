/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package noppes.npcs.controllers.data;

import java.util.HashMap;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import noppes.npcs.api.handler.data.IMagicCycle;
import noppes.npcs.constants.EnumDiagramLayout;
import noppes.npcs.controllers.data.MagicAssociation;

public class MagicCycle
implements IMagicCycle {
    public int id = -1;
    public String name = "";
    public String displayName = "";
    public EnumDiagramLayout layout = EnumDiagramLayout.CIRCULAR;
    public HashMap<Integer, MagicAssociation> associations = new HashMap();

    public void readNBT(NBTTagCompound compound) {
        this.id = compound.func_74762_e("ID");
        this.name = compound.func_74779_i("Name");
        this.displayName = compound.func_74779_i("DisplayName");
        this.layout = EnumDiagramLayout.values()[compound.func_74762_e("Layout")];
        this.associations.clear();
        NBTTagList list = compound.func_150295_c("Associations", 10);
        for (int i = 0; i < list.func_74745_c(); ++i) {
            NBTTagCompound assocTag = list.func_150305_b(i);
            MagicAssociation assoc = new MagicAssociation();
            assoc.magicId = assocTag.func_74762_e("MagicID");
            assoc.index = assocTag.func_74762_e("Index");
            assoc.priority = assocTag.func_74762_e("Priority");
            this.associations.put(assoc.magicId, assoc);
        }
    }

    public void writeNBT(NBTTagCompound compound) {
        compound.func_74768_a("ID", this.id);
        compound.func_74778_a("Name", this.name);
        compound.func_74778_a("DisplayName", this.displayName);
        compound.func_74768_a("Layout", this.layout.ordinal());
        NBTTagList list = new NBTTagList();
        for (MagicAssociation assoc : this.associations.values()) {
            NBTTagCompound assocTag = new NBTTagCompound();
            assocTag.func_74768_a("MagicID", assoc.magicId);
            assocTag.func_74768_a("Index", assoc.index);
            assocTag.func_74768_a("Priority", assoc.priority);
            list.func_74742_a((NBTBase)assocTag);
        }
        compound.func_74782_a("Associations", (NBTBase)list);
    }

    @Override
    public int getId() {
        return this.id;
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
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public EnumDiagramLayout getLayout() {
        return this.layout;
    }

    @Override
    public int getLayoutType() {
        return this.layout.ordinal();
    }

    public void setLayout(EnumDiagramLayout layout) {
        this.layout = layout;
    }

    @Override
    public void setLayoutType(int layout) {
        if (layout < 0 || layout > EnumDiagramLayout.values().length - 1) {
            return;
        }
        this.layout = EnumDiagramLayout.values()[layout];
    }

    public HashMap<Integer, MagicAssociation> getAssociations() {
        return this.associations;
    }

    public void setAssociations(HashMap<Integer, MagicAssociation> associations) {
        this.associations = associations;
    }
}

