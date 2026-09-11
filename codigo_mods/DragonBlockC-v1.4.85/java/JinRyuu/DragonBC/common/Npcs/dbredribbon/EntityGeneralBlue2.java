/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs.dbredribbon;

import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityRedRibbon;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityGeneralBlue2
extends EntityRedRibbon {
    public final int AttPow = 110;
    public final int HePo = 350;

    public EntityGeneralBlue2(World world) {
        super(world);
        this.texture = "general_blue2";
        this.func_70105_a(0.6f, 2.0f);
        this.setEasyDifficulty();
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(350.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(110.0);
    }

    @Override
    public long BattlePowerOld() {
        int BP = 7700000;
        int exp = this.field_70728_aV * 100;
        long BattlePower = BP + this.field_70146_Z.nextInt((int)Math.pow(10.0, (BP + "").length() - 2));
        return BattlePower;
    }
}

