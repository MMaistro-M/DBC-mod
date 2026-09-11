/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.ai.EntityAIDoorInteract
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 */
package noppes.npcs.ai;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIDoorInteract;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class EntityAIBustDoor
extends EntityAIDoorInteract {
    private int breakingTime;
    private int field_75358_j = -1;

    public EntityAIBustDoor(EntityLiving par1EntityLiving) {
        super(par1EntityLiving);
    }

    public boolean func_75250_a() {
        return !super.func_75250_a() ? false : (!this.field_75356_a.field_70170_p.func_82736_K().func_82766_b("mobGriefing") ? false : !this.field_151504_e.func_150015_f((IBlockAccess)this.field_75356_a.field_70170_p, this.field_75354_b, this.field_75355_c, this.field_75352_d));
    }

    public void func_75249_e() {
        super.func_75249_e();
        this.breakingTime = 0;
    }

    public boolean func_75253_b() {
        double var1 = this.field_75356_a.func_70092_e((double)this.field_75354_b, (double)this.field_75355_c, (double)this.field_75352_d);
        return this.breakingTime <= 240 && !this.field_151504_e.func_150015_f((IBlockAccess)this.field_75356_a.field_70170_p, this.field_75354_b, this.field_75355_c, this.field_75352_d) && var1 < 4.0;
    }

    public void func_75251_c() {
        super.func_75251_c();
        this.field_75356_a.field_70170_p.func_147443_d(this.field_75356_a.func_145782_y(), this.field_75354_b, this.field_75355_c, this.field_75352_d, -1);
    }

    public void func_75246_d() {
        super.func_75246_d();
        if (this.field_75356_a.func_70681_au().nextInt(20) == 0) {
            this.field_75356_a.field_70170_p.func_72926_e(1010, this.field_75354_b, this.field_75355_c, this.field_75352_d, 0);
            this.field_75356_a.func_71038_i();
        }
        ++this.breakingTime;
        int var1 = (int)((float)this.breakingTime / 240.0f * 10.0f);
        if (var1 != this.field_75358_j) {
            this.field_75356_a.field_70170_p.func_147443_d(this.field_75356_a.func_145782_y(), this.field_75354_b, this.field_75355_c, this.field_75352_d, var1);
            this.field_75358_j = var1;
        }
        if (this.breakingTime == 240) {
            this.field_75356_a.field_70170_p.func_147449_b(this.field_75354_b, this.field_75355_c, this.field_75352_d, Blocks.field_150350_a);
            this.field_75356_a.field_70170_p.func_72926_e(1012, this.field_75354_b, this.field_75355_c, this.field_75352_d, 0);
            this.field_75356_a.field_70170_p.func_72926_e(2001, this.field_75354_b, this.field_75355_c, this.field_75352_d, Block.func_149682_b((Block)this.field_151504_e));
        }
    }
}

