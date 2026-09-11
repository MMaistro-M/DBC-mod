/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 */
package noppes.npcs.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import noppes.npcs.api.IPos;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.handler.data.INaturalSpawn;

public interface ICustomNPCsEvent {
    public String getHookName();

    public static interface ScriptedCommandEvent
    extends ICustomNPCsEvent {
        public IWorld getSenderWorld();

        public IPos getSenderPosition();

        public String getSenderName();

        public void setReplyMessage(String var1);

        public String getId();

        public String[] getArgs();
    }

    @Cancelable
    public static interface CNPCNaturalSpawnEvent
    extends ICustomNPCsEvent {
        public INaturalSpawn getNaturalSpawn();

        public void setAttemptPosition(IPos var1);

        public IPos getAttemptPosition();

        public boolean animalSpawnPassed();

        public boolean monsterSpawnPassed();

        public boolean liquidSpawnPassed();

        public boolean airSpawnPassed();
    }
}

