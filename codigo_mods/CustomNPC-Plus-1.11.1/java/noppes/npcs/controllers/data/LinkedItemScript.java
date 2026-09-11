/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import kamkeel.npcs.network.packets.request.script.item.LinkedItemScriptPacket;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.constants.ScriptContext;
import noppes.npcs.controllers.data.IScriptHandlerPacket;
import noppes.npcs.controllers.data.IScriptUnit;
import noppes.npcs.controllers.data.SingleScriptHandler;
import noppes.npcs.janino.EventJaninoScript;

public class LinkedItemScript
extends SingleScriptHandler
implements IScriptHandlerPacket {
    private int linkedItemId = -1;

    public LinkedItemScript() {
    }

    public LinkedItemScript(int linkedItemId) {
        this.linkedItemId = linkedItemId;
    }

    @Override
    public ScriptContext getContext() {
        return ScriptContext.LINKED_ITEM;
    }

    @Override
    public IScriptUnit createJaninoScriptUnit() {
        return new EventJaninoScript(ScriptContext.LINKED_ITEM);
    }

    @Override
    public String noticeString() {
        return "LinkedItem";
    }

    @Override
    public void requestData() {
        if (this.linkedItemId >= 0) {
            LinkedItemScriptPacket.Get(this.linkedItemId);
        }
    }

    @Override
    public void sendSavePacket(int index, int totalCount, NBTTagCompound nbt) {
        if (this.linkedItemId >= 0) {
            LinkedItemScriptPacket.Save(this.linkedItemId, index, totalCount, nbt);
        }
    }
}

