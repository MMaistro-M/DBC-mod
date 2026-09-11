/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 */
package noppes.npcs.util;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import noppes.npcs.entity.EntityNPCInterface;

public class GameProfileAlt
extends GameProfile {
    private static final UUID id = UUID.randomUUID();
    public EntityNPCInterface npc;

    public GameProfileAlt() {
        super(null, "customnpc");
    }

    public String getName() {
        if (this.npc == null) {
            return super.getName();
        }
        return this.npc.func_70005_c_();
    }

    public UUID getId() {
        if (this.npc == null) {
            return id;
        }
        return this.npc.getPersistentID();
    }
}

