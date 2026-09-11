/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.world.World
 */
package noppes.npcs.entity.data;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import noppes.npcs.compat.PixelmonHelper;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.entity.data.ModelDataShared;

public class ModelData
extends ModelDataShared {
    public EntityLivingBase getEntity(EntityNPCInterface npc) {
        if (this.entityClass == null) {
            return null;
        }
        if (this.entity == null) {
            try {
                this.entity = (EntityLivingBase)this.entityClass.getConstructor(World.class).newInstance(npc.field_70170_p);
                this.entity.func_70037_a(this.extra);
                if (this.entity instanceof EntityLiving) {
                    EntityLiving living = (EntityLiving)this.entity;
                    living.func_70062_b(0, npc.func_70694_bm() != null ? npc.func_70694_bm() : npc.getOffHand());
                    living.func_70062_b(1, npc.inventory.armorItemInSlot(3));
                    living.func_70062_b(2, npc.inventory.armorItemInSlot(2));
                    living.func_70062_b(3, npc.inventory.armorItemInSlot(1));
                    living.func_70062_b(4, npc.inventory.armorItemInSlot(0));
                }
                if (PixelmonHelper.isPixelmon((Entity)this.entity) && npc.field_70170_p.field_72995_K) {
                    if (this.extra.func_74764_b("Name")) {
                        PixelmonHelper.setName(this.entity, this.extra.func_74779_i("Name"));
                    } else {
                        PixelmonHelper.setName(this.entity, "Abra");
                    }
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        return this.entity;
    }

    public ModelData copy() {
        ModelData data = new ModelData();
        data.readFromNBT(this.writeToNBT());
        return data;
    }
}

