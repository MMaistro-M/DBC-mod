/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package kamkeel.npcs.controllers.data.attribute.tracker;

import java.util.ArrayList;
import java.util.List;
import kamkeel.npcs.controllers.data.attribute.tracker.PlayerAttributeTracker;
import net.minecraft.entity.player.EntityPlayer;

public final class AttributeRecalcEvent {
    private static final List<PreListener> preListeners = new ArrayList<PreListener>();
    private static final List<Listener> postListeners = new ArrayList<Listener>();

    public static void pre(EntityPlayer player, PlayerAttributeTracker tracker) {
        for (PreListener l : preListeners) {
            l.onPre(player, tracker);
        }
    }

    public static void registerListener(Listener l) {
        postListeners.add(l);
    }

    public static void post(EntityPlayer player, PlayerAttributeTracker tracker) {
        for (Listener l : postListeners) {
            l.onPost(player, tracker);
        }
    }

    public static void registerPreListener(PreListener l) {
        preListeners.add(l);
    }

    @FunctionalInterface
    public static interface Listener {
        public void onPost(EntityPlayer var1, PlayerAttributeTracker var2);
    }

    @FunctionalInterface
    public static interface PreListener {
        public void onPre(EntityPlayer var1, PlayerAttributeTracker var2);
    }
}

