/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import kamkeel.npcs.network.packets.request.script.AbilityScriptPacket;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.constants.ScriptContext;
import noppes.npcs.controllers.data.IScriptHandlerPacket;
import noppes.npcs.controllers.data.IScriptUnit;
import noppes.npcs.controllers.data.SingleScriptHandler;
import noppes.npcs.janino.EventJaninoScript;

public class AbilityScript
extends SingleScriptHandler
implements IScriptHandlerPacket {
    private String abilityId = "";

    public AbilityScript() {
    }

    public AbilityScript(String abilityId) {
        this.abilityId = abilityId != null ? abilityId : "";
    }

    @Override
    public ScriptContext getContext() {
        return ScriptContext.ABILITY;
    }

    @Override
    public IScriptUnit createJaninoScriptUnit() {
        return new EventJaninoScript(ScriptContext.ABILITY);
    }

    @Override
    public String noticeString() {
        return !this.abilityId.isEmpty() ? "Ability[" + this.abilityId + "]" : "Ability";
    }

    @Override
    public void requestData() {
        if (!this.abilityId.isEmpty()) {
            AbilityScriptPacket.Get(this.abilityId);
        }
    }

    @Override
    public void sendSavePacket(int index, int totalCount, NBTTagCompound nbt) {
        if (!this.abilityId.isEmpty()) {
            AbilityScriptPacket.Save(this.abilityId, index, totalCount, nbt);
        }
    }
}

