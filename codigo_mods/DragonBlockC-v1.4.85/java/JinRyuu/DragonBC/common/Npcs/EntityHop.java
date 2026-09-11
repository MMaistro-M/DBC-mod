/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.DragonBC.common.Npcs.EntityDBCBasicEvil;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityHop
extends EntityDBCBasicEvil {
    public final int AttPow = 7000;
    public final int HePo = 40000;

    public EntityHop(World world) {
        super(world);
        this.tex = "hop";
        this.setHardDifficulty();
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(40000.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7000.0);
    }

    public long BattlePowerOld() {
        int BP = 165425152;
        int exp = this.field_70728_aV * 100;
        long BattlePower = BP + this.field_70146_Z.nextInt((int)Math.pow(10.0, (BP + "").length() - 2));
        return BattlePower;
    }
}

