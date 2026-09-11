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

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import noppes.npcs.constants.AiMutex;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityAIAmbushTarget
extends EntityAIBase {
    private EntityNPCInterface theEntity;
    private EntityLivingBase targetEntity;
    private double shelterX;
    private double shelterY;
    private double shelterZ;
    private double movementSpeed;
    private double distance;
    private int delay = 0;
    private World theWorld;
    private int tick;
    private boolean attackFromBehind;

    public EntityAIAmbushTarget(EntityNPCInterface par1EntityCreature, double par2, double par3, boolean par4) {
        this.theEntity = par1EntityCreature;
        this.movementSpeed = par2;
        this.theWorld = par1EntityCreature.field_70170_p;
        this.distance = par3 * par3;
        this.attackFromBehind = par4;
        this.func_75248_a(AiMutex.PASSIVE + AiMutex.LOOK);
    }

    public boolean func_75250_a() {
        this.targetEntity = this.theEntity.func_70638_az();
        if (this.targetEntity == null) {
            return false;
        }
        if (this.targetEntity.func_70068_e((Entity)this.theEntity) < this.distance || !this.theEntity.canSee((Entity)this.targetEntity)) {
            return false;
        }
        if (this.delay > 0) {
            --this.delay;
            return false;
        }
        Vec3 vec3 = this.findHidingSpot();
        if (vec3 == null) {
            return false;
        }
        this.shelterX = vec3.field_72450_a;
        this.shelterY = vec3.field_72448_b;
        this.shelterZ = vec3.field_72449_c;
        return true;
    }

    public boolean func_75253_b() {
        boolean shouldHide = this.targetEntity.func_70068_e((Entity)this.theEntity) > this.distance;
        boolean isSeen = this.theEntity.canSee((Entity)this.targetEntity);
        return !this.theEntity.func_70661_as().func_75500_f() && shouldHide || !isSeen && (shouldHide || this.theEntity.ais.directLOS);
    }

    public void func_75249_e() {
        this.theEntity.func_70661_as().func_75492_a(this.shelterX, this.shelterY, this.shelterZ, this.movementSpeed);
    }

    public void func_75251_c() {
        this.theEntity.func_70661_as().func_75499_g();
        if (this.theEntity.func_70638_az() == null && this.targetEntity != null) {
            this.theEntity.func_70624_b(this.targetEntity);
        }
        if (this.targetEntity.func_70068_e((Entity)this.theEntity) < this.distance) {
            this.delay = 60;
        }
    }

    public void func_75246_d() {
        if (!this.theEntity.abilities.isRotationLocked()) {
            this.theEntity.func_70671_ap().func_75651_a((Entity)this.targetEntity, 30.0f, 30.0f);
        }
    }

    private Vec3 findHidingSpot() {
        Random random = this.theEntity.func_70681_au();
        Vec3 idealPos = null;
        for (int i = 1; i <= 8; ++i) {
            for (int y = -2; y <= 2; ++y) {
                for (int x = -i; x <= i; ++x) {
                    for (int z = -i; z <= i; ++z) {
                        Vec3 vec2;
                        Vec3 vec1;
                        MovingObjectPosition movingobjectposition;
                        double l;
                        double k;
                        double j = (double)MathHelper.func_76128_c((double)(this.theEntity.field_70165_t + (double)x)) + 0.5;
                        if (!this.theWorld.func_147439_a((int)j, (int)(k = (double)MathHelper.func_76128_c((double)(this.theEntity.field_70121_D.field_72338_b + (double)y))), (int)(l = (double)MathHelper.func_76128_c((double)(this.theEntity.field_70161_v + (double)z)) + 0.5)).func_149662_c() || this.theWorld.func_147439_a((int)j, (int)k + 1, (int)l).func_149662_c() || this.theWorld.func_147439_a((int)j, (int)k + 2, (int)l).func_149662_c() || (movingobjectposition = this.theWorld.func_72933_a(vec1 = Vec3.func_72443_a((double)this.targetEntity.field_70165_t, (double)(this.targetEntity.field_70163_u + (double)this.targetEntity.func_70047_e()), (double)this.targetEntity.field_70161_v), vec2 = Vec3.func_72443_a((double)j, (double)(k + (double)this.theEntity.func_70047_e()), (double)l))) == null || this.shelterX == j || this.shelterY == k || this.shelterZ == l) continue;
                        idealPos = Vec3.func_72443_a((double)j, (double)k, (double)l);
                    }
                }
            }
            if (idealPos == null) continue;
            return idealPos;
        }
        this.delay = 60;
        return null;
    }
}

