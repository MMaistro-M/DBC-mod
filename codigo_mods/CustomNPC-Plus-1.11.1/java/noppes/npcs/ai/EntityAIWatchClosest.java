/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.player.EntityPlayer
 */
package noppes.npcs.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.constants.AiMutex;
import noppes.npcs.entity.EntityNPCInterface;

public class EntityAIWatchClosest
extends EntityAIBase {
    private EntityNPCInterface theWatcher;
    protected Entity closestEntity;
    private float field_75333_c;
    private int lookTime;
    private float field_75331_e;
    private Class watchedClass;

    public EntityAIWatchClosest(EntityNPCInterface par1EntityLiving, Class par2Class, float par3) {
        this.theWatcher = par1EntityLiving;
        this.watchedClass = par2Class;
        this.field_75333_c = par3;
        this.field_75331_e = 0.002f;
        this.func_75248_a(AiMutex.LOOK);
    }

    public boolean func_75250_a() {
        if (this.theWatcher.abilities.isRotationLocked() || this.theWatcher.func_70681_au().nextFloat() >= this.field_75331_e || this.theWatcher.isInteracting()) {
            return false;
        }
        if (this.theWatcher.func_70638_az() != null) {
            this.closestEntity = this.theWatcher.func_70638_az();
        }
        if (this.watchedClass == EntityPlayer.class) {
            this.closestEntity = this.theWatcher.field_70170_p.func_72890_a((Entity)this.theWatcher, (double)this.field_75333_c);
        } else {
            this.closestEntity = this.theWatcher.field_70170_p.func_72857_a(this.watchedClass, this.theWatcher.field_70121_D.func_72314_b((double)this.field_75333_c, 3.0, (double)this.field_75333_c), (Entity)this.theWatcher);
            if (this.closestEntity != null) {
                return this.theWatcher.canSee(this.closestEntity);
            }
        }
        return this.closestEntity != null;
    }

    public boolean func_75253_b() {
        if (this.theWatcher.isInteracting() || this.theWatcher.isAttacking() || !this.closestEntity.func_70089_S() || !this.theWatcher.func_70089_S()) {
            return false;
        }
        return this.theWatcher.func_70068_e(this.closestEntity) > (double)(this.field_75333_c * this.field_75333_c) ? false : this.lookTime > 0;
    }

    public void func_75249_e() {
        this.lookTime = 60 + this.theWatcher.func_70681_au().nextInt(60);
    }

    public void func_75251_c() {
        this.closestEntity = null;
    }

    public void func_75246_d() {
        this.theWatcher.func_70671_ap().func_75650_a(this.closestEntity.field_70165_t, this.closestEntity.field_70163_u + (double)this.closestEntity.func_70047_e(), this.closestEntity.field_70161_v, 10.0f, (float)this.theWatcher.func_70646_bf());
        --this.lookTime;
    }
}

