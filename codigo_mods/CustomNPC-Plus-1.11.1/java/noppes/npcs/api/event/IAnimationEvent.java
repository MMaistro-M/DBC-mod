/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 */
package noppes.npcs.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import noppes.npcs.api.entity.IAnimatable;
import noppes.npcs.api.event.ICustomNPCsEvent;
import noppes.npcs.api.handler.data.IAnimation;
import noppes.npcs.api.handler.data.IAnimationData;
import noppes.npcs.api.handler.data.IFrame;

public interface IAnimationEvent
extends ICustomNPCsEvent {
    public IAnimation getAnimation();

    public IAnimationData getAnimationData();

    public IAnimatable getEntity();

    public static interface IFrameEvent
    extends IAnimationEvent {
        public int getIndex();

        public IFrame getFrame();

        public static interface Exited
        extends IFrameEvent {
        }

        public static interface Entered
        extends IFrameEvent {
        }
    }

    public static interface Ended
    extends IAnimationEvent {
    }

    @Cancelable
    public static interface Started
    extends IAnimationEvent {
    }
}

