/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.api.common.skin.data;

import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinIdentifier;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;

public interface ISkinPointer {
    public ISkinIdentifier getIdentifier();

    @Deprecated
    public int getSkinId();

    public ISkinType getSkinType();

    public ISkinDye getSkinDye();
}

