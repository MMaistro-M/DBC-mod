/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.ai.EntityAIBase
 */
package noppes.npcs.ai;

import net.minecraft.entity.ai.EntityAIBase;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityAIWaterNav
extends EntityAIBase {
    private EntityNPCInterface theEntity;

    public EntityAIWaterNav(EntityNPCInterface par1EntityNPCInterface) {
        this.theEntity = par1EntityNPCInterface;
        par1EntityNPCInterface.func_70661_as().func_75495_e(true);
    }

    public boolean func_75250_a() {
        if (this.theEntity.func_70090_H() || this.theEntity.func_70058_J()) {
            if (this.theEntity.ais.canSwim) {
                return true;
            }
            if (this.theEntity.field_70123_F) {
                return true;
            }
        }
        return false;
    }

    public void func_75246_d() {
        if (this.theEntity.func_70681_au().nextFloat() < 0.8f) {
            this.theEntity.func_70683_ar().func_75660_a();
        }
    }
}

