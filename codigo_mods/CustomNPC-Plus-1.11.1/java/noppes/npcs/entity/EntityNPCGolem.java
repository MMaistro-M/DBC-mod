/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package noppes.npcs.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import noppes.npcs.constants.EnumAnimation;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.entity.data.ModelData;

public class EntityNPCGolem
extends EntityNPCInterface {
    public EntityNPCGolem(World world) {
        super(world);
        this.display.texture = "customnpcs:textures/entity/golem/Iron Golem.png";
        this.field_70130_N = 1.4f;
        this.field_70131_O = 2.5f;
    }

    @Override
    public void updateHitbox() {
        float newHeight;
        float newWidth;
        this.currentAnimation = EnumAnimation.values()[this.field_70180_af.func_75679_c(14)];
        if (this.currentAnimation == EnumAnimation.LYING) {
            newWidth = 0.5f;
            newHeight = 0.5f;
        } else if (this.currentAnimation == EnumAnimation.SITTING) {
            newWidth = 1.4f;
            newHeight = 2.0f;
        } else {
            newWidth = 1.4f;
            newHeight = 2.5f;
        }
        this.field_70130_N = newWidth;
        this.field_70131_O = newHeight;
        this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        if ((double)(this.field_70130_N / 2.0f) > World.MAX_ENTITY_RADIUS) {
            World.MAX_ENTITY_RADIUS = this.field_70130_N / 2.0f;
        }
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
            data.setEntityClass(EntityNPCGolem.class);
            this.field_70170_p.func_72838_d((Entity)npc);
        }
        super.func_70071_h_();
    }
}

