/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package noppes.npcs.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import noppes.npcs.constants.AiMutex;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityAIStalkTarget
extends EntityAIBase {
    private EntityNPCInterface theEntity;
    private EntityLivingBase targetEntity;
    private Vec3 movePosition;
    private double distance;
    private boolean overRide;
    private World theWorld;
    private int delay;
    private int tick = 0;

    public EntityAIStalkTarget(EntityNPCInterface par1EntityCreature, double par2) {
        this.theEntity = par1EntityCreature;
        this.theWorld = par1EntityCreature.field_70170_p;
        this.distance = par2 * par2;
        this.overRide = false;
        this.delay = 0;
        this.func_75248_a(AiMutex.PASSIVE + AiMutex.LOOK);
    }

    public boolean func_75250_a() {
        this.targetEntity = this.theEntity.func_70638_az();
        if (this.targetEntity == null) {
            return false;
        }
        if (this.tick > 0) {
            --this.tick;
            return false;
        }
        return this.targetEntity.func_70068_e((Entity)this.theEntity) > this.distance;
    }

    public void func_75251_c() {
        this.theEntity.func_70661_as().func_75499_g();
        if (this.theEntity.func_70638_az() == null && this.targetEntity != null) {
            this.theEntity.func_70624_b(this.targetEntity);
        }
        if (this.theEntity.getRangedTask() != null) {
            this.theEntity.getRangedTask().navOverride(false);
        }
    }

    public void func_75249_e() {
        if (this.theEntity.getRangedTask() != null) {
            this.theEntity.getRangedTask().navOverride(true);
        }
    }

    public void func_75246_d() {
        if (!this.theEntity.abilities.isRotationLocked()) {
            this.theEntity.func_70671_ap().func_75651_a((Entity)this.targetEntity, 30.0f, 30.0f);
        }
        if (this.theEntity.func_70661_as().func_75500_f() || this.overRide) {
            if (this.isLookingAway()) {
                this.movePosition = this.stalkTarget();
                if (this.movePosition != null) {
                    this.theEntity.func_70661_as().func_75492_a(this.movePosition.field_72450_a, this.movePosition.field_72448_b, this.movePosition.field_72449_c, 1.0);
                    this.overRide = false;
                } else {
                    this.tick = 100;
                }
            } else if (this.theEntity.canSee((Entity)this.targetEntity)) {
                this.movePosition = this.hideFromTarget();
                if (this.movePosition != null) {
                    this.theEntity.func_70661_as().func_75492_a(this.movePosition.field_72450_a, this.movePosition.field_72448_b, this.movePosition.field_72449_c, 1.33);
                    this.overRide = false;
                } else {
                    this.tick = 100;
                }
            }
        }
        if (this.delay > 0) {
            --this.delay;
        }
        if (!this.isLookingAway() && this.theEntity.canSee((Entity)this.targetEntity) && this.delay == 0) {
            this.overRide = true;
            this.delay = 60;
        }
    }

    private Vec3 hideFromTarget() {
        for (int i = 1; i <= 8; ++i) {
            Vec3 vec = this.findSecludedXYZ(i, false);
            if (vec == null) continue;
            return vec;
        }
        return null;
    }

    private Vec3 stalkTarget() {
        for (int i = 8; i >= 1; --i) {
            Vec3 vec = this.findSecludedXYZ(i, true);
            if (vec == null) continue;
            return vec;
        }
        return null;
    }

    private Vec3 findSecludedXYZ(int radius, boolean nearest) {
        Vec3 idealPos = null;
        double dist = this.targetEntity.func_70068_e((Entity)this.theEntity);
        double u = 0.0;
        double v = 0.0;
        double w = 0.0;
        if (this.movePosition != null) {
            u = this.movePosition.field_72450_a;
            v = this.movePosition.field_72448_b;
            w = this.movePosition.field_72449_c;
        }
        for (int y = -2; y <= 2; ++y) {
            for (int x = -radius; x <= radius; ++x) {
                for (int z = -radius; z <= radius; ++z) {
                    boolean weight;
                    Vec3 vec2;
                    Vec3 vec1;
                    MovingObjectPosition movingobjectposition;
                    double l;
                    double k;
                    double j = (double)MathHelper.func_76128_c((double)(this.theEntity.field_70165_t + (double)x)) + 0.5;
                    if (!this.theWorld.func_147439_a((int)j, (int)(k = (double)MathHelper.func_76128_c((double)(this.theEntity.field_70121_D.field_72338_b + (double)y))), (int)(l = (double)MathHelper.func_76128_c((double)(this.theEntity.field_70161_v + (double)z)) + 0.5)).func_149662_c() || this.theWorld.func_147439_a((int)j, (int)k + 1, (int)l).func_149662_c() || this.theWorld.func_147439_a((int)j, (int)k + 2, (int)l).func_149662_c() || (movingobjectposition = this.theWorld.func_72933_a(vec1 = Vec3.func_72443_a((double)this.targetEntity.field_70165_t, (double)(this.targetEntity.field_70163_u + (double)this.targetEntity.func_70047_e()), (double)this.targetEntity.field_70161_v), vec2 = Vec3.func_72443_a((double)j, (double)(k + (double)this.theEntity.func_70047_e()), (double)l))) == null) continue;
                    boolean bl = nearest ? this.targetEntity.func_70092_e(j, k, l) <= dist : (weight = true);
                    if (!weight || j == u && k == v && l == w) continue;
                    idealPos = Vec3.func_72443_a((double)j, (double)k, (double)l);
                    if (!nearest) continue;
                    dist = this.targetEntity.func_70092_e(j, k, l);
                }
            }
        }
        return idealPos;
    }

    private boolean isLookingAway() {
        Vec3 vec3 = this.targetEntity.func_70676_i(1.0f).func_72432_b();
        Vec3 vec31 = Vec3.func_72443_a((double)(this.theEntity.field_70165_t - this.targetEntity.field_70165_t), (double)(this.theEntity.field_70121_D.field_72338_b + (double)(this.theEntity.field_70131_O / 2.0f) - (this.targetEntity.field_70163_u + (double)this.targetEntity.func_70047_e())), (double)(this.theEntity.field_70161_v - this.targetEntity.field_70161_v));
        double d0 = vec31.func_72433_c();
        double d1 = vec3.func_72430_b(vec31 = vec31.func_72432_b());
        return d1 < 0.6;
    }
}

