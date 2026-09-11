/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.api.common.skin.data;

import java.util.ArrayList;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPart;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;

public interface ISkin {
    public ISkinType getSkinType();

    public ArrayList<ISkinPart> getSubParts();
}

