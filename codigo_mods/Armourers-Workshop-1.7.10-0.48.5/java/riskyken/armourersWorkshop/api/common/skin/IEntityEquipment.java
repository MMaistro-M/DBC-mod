/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.api.common.skin;

import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPointer;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;

public interface IEntityEquipment {
    public void addEquipment(ISkinType var1, int var2, ISkinPointer var3);

    public void removeEquipment(ISkinType var1, int var2);

    public boolean haveEquipment(ISkinType var1, int var2);

    @Deprecated
    public int getEquipmentId(ISkinType var1, int var2);

    public ISkinPointer getSkinPointer(ISkinType var1, int var2);

    @Deprecated
    public ISkinDye getSkinDye(ISkinType var1, int var2);

    @Deprecated
    public int getNumberOfSlots();
}

