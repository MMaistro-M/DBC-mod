/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package noppes.npcs.entity.old;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.entity.data.ModelData;

public class EntityNPCOrcMale
extends EntityNPCInterface {
    public EntityNPCOrcMale(World world) {
        super(world);
        this.scaleY = 1.0f;
        this.scaleZ = 1.2f;
        this.scaleX = 1.2f;
        this.display.texture = "customnpcs:textures/entity/orcmale/StrandedOrc.png";
    }

    @Override
    public void func_70071_h_() {
        this.field_70128_L = true;
        if (!this.field_70170_p.field_72995_K) {
            NBTTagCompound compound = new NBTTagCompound();
            this.func_70109_d(compound);
            EntityCustomNpc npc = new EntityCustomNpc(this.field_70170_p);
            npc.func_70020_e(compound);
            ModelData data = npc.modelData;
            data.modelScale.legs.setScale(1.2f, 1.05f);
            data.modelScale.arms.setScale(1.2f, 1.05f);
            data.modelScale.body.setScale(1.4f, 1.1f, 1.5f);
            data.modelScale.head.setScale(1.2f, 1.1f);
            this.field_70170_p.func_72838_d((Entity)npc);
        }
        super.func_70071_h_();
    }
}

