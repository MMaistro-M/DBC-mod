/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.player.EntityPlayer
 */
package noppes.npcs.ai;

import java.util.Iterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.constants.AiMutex;
import noppes.npcs.constants.EnumStandingType;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityAILook
extends EntityAIBase {
    private final EntityNPCInterface npc;
    private int idle = 0;
    private double lookX;
    private double lookZ;
    boolean rotatebody;

    public EntityAILook(EntityNPCInterface npc) {
        this.npc = npc;
        this.func_75248_a(AiMutex.LOOK);
    }

    public boolean func_75250_a() {
        return !this.npc.abilities.isRotationLocked() && !this.npc.isAttacking() && this.npc.func_70661_as().func_75500_f() && !this.npc.func_70608_bn() && this.npc.func_70089_S();
    }

    public void func_75249_e() {
        this.rotatebody = this.npc.ais.standingType == EnumStandingType.RotateBody || this.npc.ais.standingType == EnumStandingType.HeadRotation;
    }

    public void func_75251_c() {
        this.rotatebody = false;
    }

    public void func_75246_d() {
        if (this.npc.ais.standingType == EnumStandingType.Stalking) {
            EntityPlayer player = this.npc.field_70170_p.func_72890_a((Entity)this.npc, 16.0);
            if (player == null) {
                this.rotatebody = true;
            } else {
                this.npc.func_70671_ap().func_75651_a((Entity)player, 10.0f, (float)this.npc.func_70646_bf());
            }
        }
        if (this.npc.isInteracting()) {
            Iterator<EntityLivingBase> ita = this.npc.interactingEntities.iterator();
            EntityLivingBase closest = null;
            double closestDistance = 12.0;
            while (ita.hasNext()) {
                EntityLivingBase entity = ita.next();
                double distance = entity.func_70068_e((Entity)this.npc);
                if (distance < closestDistance) {
                    closestDistance = entity.func_70068_e((Entity)this.npc);
                    closest = entity;
                    continue;
                }
                if (!(distance > 12.0)) continue;
                ita.remove();
            }
            if (closest != null) {
                this.npc.func_70671_ap().func_75651_a(closest, 10.0f, (float)this.npc.func_70646_bf());
                return;
            }
        }
        if (this.rotatebody) {
            if (this.idle == 0 && this.npc.func_70681_au().nextFloat() < 0.02f) {
                double var1 = Math.PI * 2 * this.npc.func_70681_au().nextDouble();
                if (this.npc.ais.standingType == EnumStandingType.HeadRotation) {
                    var1 = Math.PI / 180 * (double)this.npc.ais.orientation + 0.6283185307179586 + 1.8849555921538759 * this.npc.func_70681_au().nextDouble();
                }
                this.lookX = Math.cos(var1);
                this.lookZ = Math.sin(var1);
                this.idle = 20 + this.npc.func_70681_au().nextInt(20);
            }
            if (this.idle > 0) {
                --this.idle;
                this.npc.func_70671_ap().func_75650_a(this.npc.field_70165_t + this.lookX, this.npc.field_70163_u + (double)this.npc.func_70047_e(), this.npc.field_70161_v + this.lookZ, 10.0f, (float)this.npc.func_70646_bf());
            }
        }
        if (this.npc.ais.standingType == EnumStandingType.NoRotation) {
            this.npc.field_70177_z = this.npc.field_70761_aq = (float)this.npc.ais.orientation;
            this.npc.field_70759_as = this.npc.field_70761_aq;
        }
    }
}

