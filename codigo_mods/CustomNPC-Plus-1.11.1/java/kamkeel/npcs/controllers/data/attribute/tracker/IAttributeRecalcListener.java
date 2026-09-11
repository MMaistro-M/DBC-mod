/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package kamkeel.npcs.controllers.data.attribute.tracker;

import kamkeel.npcs.controllers.data.attribute.tracker.PlayerAttributeTracker;
import net.minecraft.entity.player.EntityPlayer;

public interface IAttributeRecalcListener {
    public void onAttributesRecalculated(EntityPlayer var1, PlayerAttributeTracker var2);
}

