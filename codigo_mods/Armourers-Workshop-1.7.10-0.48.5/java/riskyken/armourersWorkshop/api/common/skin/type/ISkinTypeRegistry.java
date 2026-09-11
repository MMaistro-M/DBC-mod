/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.api.common.skin.type;

import java.util.ArrayList;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;

public interface ISkinTypeRegistry {
    public boolean registerSkin(ISkinType var1);

    public ISkinType getSkinTypeFromRegistryName(String var1);

    public ISkinPartType getSkinPartFromRegistryName(String var1);

    public ArrayList<ISkinType> getRegisteredSkinTypes();

    public ISkinType getSkinTypeHead();

    public ISkinType getSkinTypeChest();

    public ISkinType getSkinTypeLegs();

    @Deprecated
    public ISkinType getSkinTypeSkirt();

    public ISkinType getSkinTypeFeet();
}

