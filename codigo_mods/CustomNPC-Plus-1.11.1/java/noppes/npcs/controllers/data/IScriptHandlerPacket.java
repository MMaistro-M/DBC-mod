/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import java.util.List;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.NBTTags;
import noppes.npcs.controllers.data.IScriptHandler;
import noppes.npcs.controllers.data.IScriptUnit;

public interface IScriptHandlerPacket
extends IScriptHandler {
    public void requestData();

    public void sendSavePacket(int var1, int var2, NBTTagCompound var3);

    default public GuiDataResult setGuiData(NBTTagCompound compound) {
        if (compound.func_74764_b("LoadComplete")) {
            return new GuiDataResult(GuiDataKind.LOAD_COMPLETE, -1);
        }
        if (!(compound.func_74764_b("Tab") || compound.func_74764_b("ScriptLanguage") || compound.func_74764_b("ScriptEnabled"))) {
            return null;
        }
        if (!compound.func_74764_b("Tab")) {
            this.setLanguage(compound.func_74779_i("ScriptLanguage"));
            this.setEnabled(compound.func_74767_n("ScriptEnabled"));
            return new GuiDataResult(GuiDataKind.METADATA, -1);
        }
        int tab = compound.func_74762_e("Tab");
        NBTTagCompound scriptCompound = compound.func_74775_l("Script");
        IScriptUnit unit = IScriptUnit.createFromNBT(scriptCompound, this);
        this.replaceScriptUnit(tab, unit);
        return new GuiDataResult(GuiDataKind.TAB, tab);
    }

    default public void sync() {
        List<IScriptUnit> containers = this.getScripts();
        for (int i = 0; i < containers.size(); ++i) {
            IScriptUnit container = containers.get(i);
            this.sendSavePacket(i, containers.size(), container.writeToNBT(new NBTTagCompound()));
        }
        NBTTagCompound scriptData = new NBTTagCompound();
        scriptData.func_74778_a("ScriptLanguage", this.getLanguage());
        scriptData.func_74757_a("ScriptEnabled", this.getEnabled());
        scriptData.func_74782_a("ScriptConsole", (NBTBase)NBTTags.NBTLongStringMap(this.getConsoleText()));
        this.sendSavePacket(-1, containers.size(), scriptData);
    }

    public static class GuiDataResult {
        public final GuiDataKind kind;
        public final int tabIndex;

        public GuiDataResult(GuiDataKind kind, int tabIndex) {
            this.kind = kind;
            this.tabIndex = tabIndex;
        }
    }

    public static enum GuiDataKind {
        LOAD_COMPLETE,
        METADATA,
        TAB;

    }
}

