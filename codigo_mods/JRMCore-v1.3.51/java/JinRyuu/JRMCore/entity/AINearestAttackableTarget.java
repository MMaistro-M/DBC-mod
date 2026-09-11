/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget$Sorter
 */
package JinRyuu.JRMCore.entity;

import JinRyuu.FamilyC.EntityNPC;
import JinRyuu.JRMCore.entity.AINearestAttackableTargetSelector;
import JinRyuu.JRMCore.entity.AITarget;
import java.util.Collections;
import java.util.List;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;

public class AINearestAttackableTarget
extends AITarget {
    private final Class targetClass;
    private final int targetChance;
    private final EntityAINearestAttackableTarget.Sorter theNearestAttackableTargetSorter;
    private final IEntitySelector targetEntitySelector;
    private EntityLivingBase targetEntity;

    public AINearestAttackableTarget(EntityCreature par1EntityCreature, Class par2Class, int par3, boolean par4) {
        this(par1EntityCreature, par2Class, par3, par4, false);
    }

    public AINearestAttackableTarget(EntityCreature par1EntityCreature, Class par2Class, int par3, boolean par4, boolean par5) {
        this(par1EntityCreature, par2Class, par3, par4, par5, null);
    }

    public AINearestAttackableTarget(EntityCreature par1EntityCreature, Class par2Class, int par3, boolean par4, boolean par5, IEntitySelector par6IEntitySelector) {
        super(par1EntityCreature, par4, par5);
        this.targetClass = par2Class;
        this.targetChance = par3;
        this.theNearestAttackableTargetSorter = new EntityAINearestAttackableTarget.Sorter((Entity)par1EntityCreature);
        this.func_75248_a(1);
        this.targetEntitySelector = new AINearestAttackableTargetSelector(this, par6IEntitySelector);
    }

    public boolean func_75250_a() {
        if (this.taskOwner instanceof EntityNPC && !((EntityNPC)this.taskOwner).getAggr()) {
            return false;
        }
        if (this.targetChance > 0 && this.taskOwner.func_70681_au().nextInt(this.targetChance) != 0) {
            return false;
        }
        double d0 = this.getTargetDistance();
        List list = this.taskOwner.field_70170_p.func_82733_a(this.targetClass, this.taskOwner.field_70121_D.func_72314_b(d0, 4.0, d0), this.targetEntitySelector);
        Collections.sort(list, this.theNearestAttackableTargetSorter);
        if (list.isEmpty()) {
            return false;
        }
        this.targetEntity = (EntityLivingBase)list.get(0);
        return true;
    }

    @Override
    public void func_75249_e() {
        this.taskOwner.func_70624_b(this.targetEntity);
        super.func_75249_e();
    }
}

