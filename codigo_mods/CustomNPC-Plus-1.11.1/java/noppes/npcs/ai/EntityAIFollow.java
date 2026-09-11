/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package noppes.npcs.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import noppes.npcs.constants.AiMutex;
import noppes.npcs.constants.EnumAnimation;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityAIFollow
extends EntityAIBase {
    private EntityNPCInterface npc;
    private EntityLivingBase owner;
    private double distance;
    public int updateTick = 0;

    public EntityAIFollow(EntityNPCInterface npc) {
        this.npc = npc;
        this.func_75248_a(AiMutex.PASSIVE + AiMutex.LOOK);
    }

    public boolean func_75250_a() {
        if (!this.excute()) {
            return false;
        }
        return this.distance > (double)this.npc.followRange();
    }

    public boolean excute() {
        if (!this.npc.func_70089_S() || !this.npc.isFollower() || this.npc.isAttacking() || (this.owner = this.npc.getOwner()) == null || this.npc.ais.animationType == EnumAnimation.SITTING) {
            return false;
        }
        this.distance = this.npc.func_70068_e((Entity)this.owner);
        return true;
    }

    public void func_75249_e() {
        this.updateTick = 10;
    }

    public boolean func_75253_b() {
        return !this.npc.func_70661_as().func_75500_f() && this.distance > 4.0 && this.excute();
    }

    public void func_75251_c() {
        this.owner = null;
        this.npc.func_70661_as().func_75499_g();
    }

    public void func_75246_d() {
        double speed;
        ++this.updateTick;
        if (this.updateTick < 10) {
            return;
        }
        this.updateTick = 0;
        if (!this.npc.abilities.isRotationLocked()) {
            this.npc.func_70671_ap().func_75651_a((Entity)this.owner, 10.0f, (float)this.npc.func_70646_bf());
        }
        if ((speed = 1.0 + this.distance / 150.0) > 3.0) {
            speed = 3.0;
        }
        if (this.npc.func_70661_as().func_75497_a((Entity)this.owner, speed) || this.distance < 225.0) {
            return;
        }
        int i = MathHelper.func_76128_c((double)this.owner.field_70165_t) - 2;
        int j = MathHelper.func_76128_c((double)this.owner.field_70161_v) - 2;
        int k = MathHelper.func_76128_c((double)this.owner.field_70121_D.field_72338_b);
        for (int l = 0; l <= 4; ++l) {
            for (int i1 = 0; i1 <= 4; ++i1) {
                if (l >= 1 && i1 >= 1 && l <= 3 && i1 <= 3 || !World.func_147466_a((IBlockAccess)this.npc.field_70170_p, (int)(i + l), (int)(k - 1), (int)(j + i1)) || this.npc.field_70170_p.func_147439_a(i + l, k, j + i1).func_149721_r() || this.npc.field_70170_p.func_147439_a(i + l, k + 1, j + i1).func_149721_r()) continue;
                this.npc.func_70012_b((float)(i + l) + 0.5f, k, (float)(j + i1) + 0.5f, this.npc.field_70177_z, this.npc.field_70125_A);
                this.npc.func_70661_as().func_75499_g();
                return;
            }
        }
    }
}

