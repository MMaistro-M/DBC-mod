/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import kamkeel.npcs.network.packets.request.script.EffectScriptPacket;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.constants.ScriptContext;
import noppes.npcs.controllers.data.IScriptHandlerPacket;
import noppes.npcs.controllers.data.IScriptUnit;
import noppes.npcs.controllers.data.SingleScriptHandler;
import noppes.npcs.janino.EventJaninoScript;

public class EffectScript
extends SingleScriptHandler
implements IScriptHandlerPacket {
    private int effectId = -1;

    public EffectScript() {
    }

    public EffectScript(int effectId) {
        this.effectId = effectId;
    }

    @Override
    public ScriptContext getContext() {
        return ScriptContext.EFFECT;
    }

    @Override
    public IScriptUnit createJaninoScriptUnit() {
        return new EventJaninoScript(ScriptContext.EFFECT);
    }

    @Override
    public String noticeString() {
        return this.effectId >= 0 ? "CustomEffect[" + this.effectId + "]" : "CustomEffect";
    }

    @Override
    public void requestData() {
        if (this.effectId >= 0) {
            EffectScriptPacket.Get(this.effectId);
        }
    }

    @Override
    public void sendSavePacket(int index, int totalCount, NBTTagCompound nbt) {
        if (this.effectId >= 0) {
            EffectScriptPacket.Save(this.effectId, index, totalCount, nbt);
        }
    }
}

