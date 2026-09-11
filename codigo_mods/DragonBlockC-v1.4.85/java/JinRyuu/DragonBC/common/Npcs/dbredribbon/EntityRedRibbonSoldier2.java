/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs.dbredribbon;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityRedRibbon2;
import JinRyuu.JRMCore.entity.EntityPrjtls_1;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityRedRibbonSoldier2
extends EntityRedRibbon2 {
    public final int AttPow = 20;
    public final int HePo = 80;

    public EntityRedRibbonSoldier2(World world) {
        super(world);
        this.func_70105_a(0.6f, 2.0f);
        this.texture = "redribbon_soldier2";
        this.setAttributes(DBCConfig.RRSoldier2DAM, DBCConfig.RRSoldier2HP, 20, 80);
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(80.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(20.0);
    }

    @Override
    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K && !this.field_70128_L && this.field_70789_a != null && this.field_70789_a.func_70089_S() && this.field_70789_a.func_70032_d((Entity)this) < 25.0f && this.field_70173_aa % 50 < 12 && this.field_70173_aa % 4 == 0) {
            EntityPrjtls_1 var8 = new EntityPrjtls_1(this.field_70170_p, (Entity)this, this.field_70789_a, 1.8f, 1.0f, 4);
            this.field_70170_p.func_72956_a((Entity)this, "jinryuudragonbc:DBC5.gun_shot_single", 0.2f, this.field_70170_p.field_73012_v.nextFloat() * 0.1f + 0.9f);
            this.field_70170_p.func_72838_d((Entity)var8);
        }
    }
}

