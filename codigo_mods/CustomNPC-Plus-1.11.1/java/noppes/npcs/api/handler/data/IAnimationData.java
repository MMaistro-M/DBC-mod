/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

import noppes.npcs.api.entity.IAnimatable;
import noppes.npcs.api.handler.data.IAnimation;

public interface IAnimationData {
    public IAnimatable getEntity();

    public void updateClient();

    public boolean isActive();

    public boolean isClientAnimating();

    public void setEnabled(boolean var1);

    public boolean enabled();

    public void setAnimation(IAnimation var1);

    public IAnimation getAnimation();

    public long getAnimatingTime();
}

