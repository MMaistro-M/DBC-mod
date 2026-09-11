/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.api.common.skin.type;

import java.awt.Point;
import riskyken.armourersWorkshop.api.common.IPoint3D;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;

public interface ISkinPartTypeTextured
extends ISkinPartType {
    public Point getTextureLocation();

    public boolean isTextureMirrored();

    public IPoint3D getTextureModelSize();
}

