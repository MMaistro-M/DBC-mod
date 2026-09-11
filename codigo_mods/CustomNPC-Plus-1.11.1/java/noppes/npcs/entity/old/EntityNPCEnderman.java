/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package noppes.npcs.entity.old;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import noppes.npcs.constants.EnumAnimation;
import noppes.npcs.controllers.data.SkinOverlay;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.data.ModelData;
import noppes.npcs.entity.old.EntityNpcEnderchibi;

public class EntityNPCEnderman
extends EntityNpcEnderchibi {
    public EntityNPCEnderman(World world) {
        super(world);
        this.display.texture = "customnpcs:textures/entity/enderman/enderman.png";
        this.display.skinOverlayData.overlayList.put(0, new SkinOverlay("customnpcs:textures/overlays/ender_eyes.png"));
        this.field_70130_N = 0.6f;
        this.field_70131_O = 2.9f;
    }

    @Override
    public void updateHitbox() {
        float newHeight;
        float newWidth;
        if (this.currentAnimation == EnumAnimation.LYING) {
            newWidth = 0.2f;
            newHeight = 0.2f;
        } else if (this.currentAnimation == EnumAnimation.SITTING) {
            newWidth = 0.6f;
            newHeight = 2.3f;
        } else {
            newWidth = 0.6f;
            newHeight = 2.9f;
        }
        newWidth = newWidth / 5.0f * (float)this.display.modelSize;
        newHeight = newHeight / 5.0f * (float)this.display.modelSize;
        newWidth = Math.max(newWidth, 1.0E-5f);
        newHeight = Math.max(newHeight, 1.0E-5f);
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
            data.setEntityClass(EntityEnderman.class);
            this.field_70170_p.func_72838_d((Entity)npc);
        }
        super.func_70071_h_();
    }
}

