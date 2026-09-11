/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs.dbredribbon;

import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityRedRibbon;
import JinRyuu.JRMCore.entity.EntityPrjtls_1;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityOfficerBlack3
extends EntityRedRibbon {
    public final int AttPow = 170;
    public final int HePo = 480;
    private int lastShot;
    private boolean shot;

    public EntityOfficerBlack3(World world) {
        super(world);
        this.texture = "officer_black_mecha";
        this.func_70105_a(1.5f, 2.8f);
        this.lastShot = -1;
        this.shot = false;
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(480.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(170.0);
    }

    @Override
    public long BattlePowerOld() {
        int BP = 16320000;
        int exp = this.field_70728_aV * 100;
        long BattlePower = BP + this.field_70146_Z.nextInt((int)Math.pow(10.0, (BP + "").length() - 2));
        return BattlePower;
    }

    @Override
    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K && !this.field_70128_L && this.field_70789_a != null && this.field_70789_a.func_70089_S() && this.field_70789_a.func_70032_d((Entity)this) < 25.0f) {
            if (this.lastShot == -1) {
                this.lastShot = new Random().nextInt(2);
            }
            if (this.lastShot == 0) {
                if ((this.field_70173_aa + 200) % 400 < 15) {
                    EntityPrjtls_1 var8 = new EntityPrjtls_1(this.field_70170_p, (Entity)this, this.field_70789_a, 1.8f, 1.0f, 6);
                    this.field_70170_p.func_72956_a((Entity)this, "jinryuudragonbc:DBC5.gun_shot_single", 0.2f, this.field_70170_p.field_73012_v.nextFloat() * 0.1f + 0.9f);
                    this.field_70170_p.func_72838_d((Entity)var8);
                    this.shot = true;
                } else if (this.shot) {
                    this.lastShot = -1;
                    this.shot = false;
                }
            } else if (this.field_70173_aa % 100 == 0) {
                EntityPrjtls_1 var8 = new EntityPrjtls_1(this.field_70170_p, (Entity)this, this.field_70789_a, 1.8f, 1.0f, 2);
                this.field_70170_p.func_72956_a((Entity)this, "jinryuudragonbc:DBC4.rocket_shot", 0.6f, this.field_70170_p.field_73012_v.nextFloat() * 0.1f + 0.9f);
                this.field_70170_p.func_72838_d((Entity)var8);
                this.shot = true;
            } else if (this.shot) {
                this.lastShot = -1;
                this.shot = false;
            }
        }
    }
}

