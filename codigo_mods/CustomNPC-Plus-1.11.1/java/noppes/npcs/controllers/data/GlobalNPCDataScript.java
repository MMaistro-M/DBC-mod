/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.controllers.data;

import kamkeel.npcs.network.packets.request.script.GlobalNPCScriptPacket;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import noppes.npcs.CustomNpcs;
import noppes.npcs.api.entity.ICustomNpc;
import noppes.npcs.constants.ScriptContext;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.data.IScriptUnit;
import noppes.npcs.controllers.data.MultiScriptHandler;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.janino.EventJaninoScript;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.util.ScriptToStringHelper;

public class GlobalNPCDataScript
extends MultiScriptHandler {
    private EntityNPCInterface npc;
    private ICustomNpc npcAPI;
    private long lastNpcUpdate = -1L;

    public GlobalNPCDataScript(EntityNPCInterface npc) {
        if (npc != null) {
            this.npc = npc;
        }
    }

    @Override
    protected boolean canRunScripts() {
        return this.isEnabled();
    }

    public boolean isEnabled() {
        return CustomNpcs.proxy.isGlobalNPCScripts() && this.enabled && ScriptController.HasStart && this.scripts.size() > 0;
    }

    @Override
    public ScriptContext getContext() {
        return ScriptContext.NPC;
    }

    @Override
    protected boolean needsReInit() {
        return ScriptController.Instance.lastLoaded > this.lastInited || ScriptController.Instance.lastGlobalNpcUpdate > this.lastNpcUpdate;
    }

    @Override
    protected void reInitScripts() {
        super.reInitScripts();
        this.lastNpcUpdate = ScriptController.Instance.lastGlobalNpcUpdate;
    }

    @Override
    public IScriptUnit createJaninoScriptUnit() {
        return new EventJaninoScript(ScriptContext.NPC);
    }

    @Override
    public void requestData() {
        GlobalNPCScriptPacket.Get();
    }

    @Override
    public void sendSavePacket(int index, int totalCount, NBTTagCompound nbt) {
        GlobalNPCScriptPacket.Save(index, totalCount, nbt);
    }

    @Override
    public boolean isClient() {
        return this.npc != null && this.npc.func_70613_aW();
    }

    @Override
    public String noticeString() {
        if (this.npc == null) {
            return "Global script";
        }
        BlockPos pos = new BlockPos((Entity)this.npc);
        return ScriptToStringHelper.toStringHelper((Object)this.npc).add("x", pos.getX()).add("y", pos.getY()).add("z", pos.getZ()).toString();
    }

    public ICustomNpc getNpc() {
        if (this.npcAPI == null) {
            this.npcAPI = (ICustomNpc)NpcAPI.Instance().getIEntity((Entity)this.npc);
        }
        return this.npcAPI;
    }
}

