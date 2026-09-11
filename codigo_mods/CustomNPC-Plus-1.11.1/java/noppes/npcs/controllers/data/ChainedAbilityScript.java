/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import kamkeel.npcs.network.packets.request.script.ChainedAbilityScriptPacket;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.constants.ScriptContext;
import noppes.npcs.controllers.data.IScriptHandlerPacket;
import noppes.npcs.controllers.data.IScriptUnit;
import noppes.npcs.controllers.data.SingleScriptHandler;
import noppes.npcs.janino.EventJaninoScript;

public class ChainedAbilityScript
extends SingleScriptHandler
implements IScriptHandlerPacket {
    private String chainId = "";

    public ChainedAbilityScript() {
    }

    public ChainedAbilityScript(String chainId) {
        this.chainId = chainId != null ? chainId : "";
    }

    @Override
    public IScriptUnit createJaninoScriptUnit() {
        return new EventJaninoScript(ScriptContext.CHAINED_ABILITY);
    }

    @Override
    public ScriptContext getContext() {
        return ScriptContext.CHAINED_ABILITY;
    }

    @Override
    public String noticeString() {
        return !this.chainId.isEmpty() ? "ChainedAbility[" + this.chainId + "]" : "ChainedAbility";
    }

    @Override
    public void requestData() {
        if (!this.chainId.isEmpty()) {
            ChainedAbilityScriptPacket.Get(this.chainId);
        }
    }

    @Override
    public void sendSavePacket(int index, int totalCount, NBTTagCompound nbt) {
        if (!this.chainId.isEmpty()) {
            ChainedAbilityScriptPacket.Save(this.chainId, index, totalCount, nbt);
        }
    }
}

