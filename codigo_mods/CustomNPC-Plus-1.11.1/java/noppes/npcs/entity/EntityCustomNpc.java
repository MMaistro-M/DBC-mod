/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package noppes.npcs.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import noppes.npcs.CustomNpcs;
import noppes.npcs.VersionCompatibility;
import noppes.npcs.client.EntityUtil;
import noppes.npcs.entity.EntityNPCFlying;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.entity.data.ModelData;
import noppes.npcs.entity.data.ModelPartData;

public class EntityCustomNpc
extends EntityNPCFlying {
    public ModelData modelData = new ModelData();

    public EntityCustomNpc(World world) {
        super(world);
    }

    @Override
    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
        if (compound.func_74764_b("NpcModelData")) {
            this.npcVersion = compound.func_74762_e("ModRev");
            VersionCompatibility.CheckModelCompatibility(this, compound);
            this.modelData.readFromNBT(compound.func_74775_l("NpcModelData"));
        }
    }

    @Override
    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
        compound.func_74782_a("NpcModelData", (NBTBase)this.modelData.writeToNBT());
    }

    @Override
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.isRemote()) {
            EntityLivingBase entity;
            ModelPartData particles = this.modelData.getPartData("particles");
            if (particles != null && !this.isKilled()) {
                CustomNpcs.proxy.spawnParticle((EntityLivingBase)this, "ModelData", this.modelData, particles);
            }
            if ((entity = this.modelData.getEntity(this)) != null) {
                try {
                    entity.func_70071_h_();
                }
                catch (Exception exception) {
                    // empty catch block
                }
                EntityUtil.Copy((EntityLivingBase)this, entity);
            }
        }
    }

    public void func_70078_a(Entity par1Entity) {
        super.func_70078_a(par1Entity);
        this.updateHitbox();
    }

    @Override
    public void updateHitbox() {
        EntityLivingBase entity = this.modelData.getEntity(this);
        if (this.modelData == null || entity == null) {
            this.baseHeight = 1.9f - this.modelData.getBodyY() + (this.modelData.modelScale.head.scaleY - 1.0f) / 2.0f;
            super.updateHitbox();
        } else {
            if (entity instanceof EntityNPCInterface) {
                ((EntityNPCInterface)entity).updateHitbox();
            }
            float newWidth = entity.field_70130_N / 5.0f * (float)this.display.modelSize;
            float newHeight = entity.field_70131_O / 5.0f * (float)this.display.modelSize;
            if (this.display.hitboxData.isHitboxEnabled()) {
                newWidth *= this.display.hitboxData.getWidthScale();
                newHeight *= this.display.hitboxData.getHeightScale();
            }
            if (this.isKilled() && this.stats.hideKilledBody) {
                newWidth = 1.0E-5f;
            }
            newWidth = Math.max(newWidth, 1.0E-5f);
            newHeight = Math.max(newHeight, 1.0E-5f);
            if ((double)(newWidth / 2.0f) > World.MAX_ENTITY_RADIUS) {
                World.MAX_ENTITY_RADIUS = newWidth / 2.0f;
            }
            this.field_70130_N = newWidth;
            this.field_70131_O = newHeight;
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        }
    }
}

