/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import cpw.mods.fml.common.eventhandler.Event;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcs;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.data.IScriptUnit;
import noppes.npcs.controllers.data.ScriptHandler;

public abstract class SingleScriptHandler
extends ScriptHandler {
    public IScriptUnit container;

    @Override
    public boolean isSingleContainer() {
        return true;
    }

    @Override
    protected boolean canRunScripts() {
        return this.enabled && ScriptController.HasStart && CustomNpcs.proxy.isScriptingEnabled() && this.container != null;
    }

    @Override
    public void callScript(String hookName, Event event) {
        if (!this.canRunScripts()) {
            return;
        }
        this.container.run(hookName, (Object)event);
    }

    @Override
    public void setScripts(List<IScriptUnit> list) {
        if (list == null || list.isEmpty()) {
            this.container = null;
            this.scripts = new ArrayList();
        } else {
            this.container = list.get(0);
            this.scripts = new ArrayList(1);
            this.scripts.add(this.container);
        }
    }

    @Override
    public List<IScriptUnit> getScripts() {
        if (this.container == null) {
            return new ArrayList<IScriptUnit>();
        }
        return Collections.singletonList(this.container);
    }

    @Override
    public void addScriptUnit(IScriptUnit unit) {
        this.container = unit;
        this.scripts.clear();
        if (unit != null) {
            this.scripts.add(unit);
        }
    }

    @Override
    public void replaceScriptUnit(int index, IScriptUnit unit) {
        this.container = unit;
        this.scripts.clear();
        if (unit != null) {
            this.scripts.add(unit);
        }
    }

    @Override
    public void removeScriptUnit(int index) {
        this.container = null;
        this.scripts.clear();
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.func_74778_a("ScriptLanguage", this.scriptLanguage);
        compound.func_74757_a("ScriptEnabled", this.enabled);
        if (this.container != null) {
            compound.func_74782_a("ScriptContent", (NBTBase)this.container.writeToNBT(new NBTTagCompound()));
        }
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.scriptLanguage = compound.func_74779_i("ScriptLanguage");
        this.enabled = compound.func_74767_n("ScriptEnabled");
        if (compound.func_150297_b("ScriptContent", 10)) {
            this.container = IScriptUnit.createFromNBT(compound.func_74775_l("ScriptContent"), this);
        }
    }

    public void saveScript(ByteBuf buffer) throws IOException {
        int tab = buffer.readInt();
        int totalScripts = buffer.readInt();
        if (totalScripts == 0) {
            this.container = null;
        }
        if (tab == 0) {
            NBTTagCompound tabCompound = ByteBufUtils.readNBT(buffer);
            this.container = IScriptUnit.createFromNBT(tabCompound, this);
        } else {
            NBTTagCompound compound = ByteBufUtils.readNBT(buffer);
            this.setLanguage(compound.func_74779_i("ScriptLanguage"));
            this.normalizeLanguage();
            this.setEnabled(compound.func_74767_n("ScriptEnabled"));
        }
    }

    protected void normalizeLanguage() {
        if (this.getLanguage() == null || this.getLanguage().isEmpty()) {
            if (!ScriptController.Instance.languages.isEmpty()) {
                this.setLanguage((String)ScriptController.Instance.languages.keySet().toArray()[0]);
            } else {
                this.setLanguage("ECMAScript");
            }
        }
    }
}

