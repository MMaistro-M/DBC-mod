/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package riskyken.armourersWorkshop.api.common.skin.entity;

import net.minecraft.entity.Entity;
import riskyken.armourersWorkshop.api.common.skin.entity.ISkinnableEntity;

public interface IEntitySkinHandler {
    public void registerEntity(ISkinnableEntity var1);

    public boolean isValidEntity(Entity var1);
}

