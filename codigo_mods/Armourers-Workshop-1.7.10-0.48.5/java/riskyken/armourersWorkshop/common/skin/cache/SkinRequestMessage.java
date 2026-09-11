/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package riskyken.armourersWorkshop.common.skin.cache;

import net.minecraft.entity.player.EntityPlayerMP;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinIdentifier;

public class SkinRequestMessage {
    private final ISkinIdentifier skinIdentifier;
    private final EntityPlayerMP player;

    public SkinRequestMessage(ISkinIdentifier skinIdentifier, EntityPlayerMP player) {
        this.skinIdentifier = skinIdentifier;
        this.player = player;
    }

    public ISkinIdentifier getSkinIdentifier() {
        return this.skinIdentifier;
    }

    public EntityPlayerMP getPlayer() {
        return this.player;
    }
}

