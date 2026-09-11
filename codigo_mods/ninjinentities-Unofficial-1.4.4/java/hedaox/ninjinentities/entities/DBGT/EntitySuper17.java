/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package hedaox.ninjinentities.entities.DBGT;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hedaox.ninjinentities.entities.EntityDBCNinjin;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntitySuper17
extends EntityDBCNinjin {
    private double initialMaxHealth;
    private double initialAttackDamage;
    public int randomSoundDelay = 0;
    public static double attackBoostPercentPerHit;
    public static double attackBoostEnergyFactor;
    public static double attackMaxPercent;

    public EntitySuper17(World par1World) {
        super(par1World, 85, EntityDBCNinjin.MindState.AGGRESSIVE, false, true, new byte[]{1, 2, 3, 4, 5, 6}, new byte[]{7, 7, 7, 7, 7, 7});
        this.field_70728_aV = 80;
        this.func_70105_a(0.72f, 2.16f);
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(140000.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(14000.0);
    }

    public void func_70030_z() {
        super.func_70030_z();
        if (this.initialAttackDamage == 0.0 && this.getEntityData().func_74764_b("jrmcSpawnInitiatedCAT")) {
            this.initialAttackDamage = this.getEntityData().func_74769_h("jrmcSpawnInitiatedCAT");
        }
    }

    @Override
    public boolean func_70097_a(DamageSource source, float amount) {
        String type = source.func_76355_l();
        if ("EnergyAttack".equals(type)) {
            if (!this.field_70170_p.field_72995_K) {
                this.enhanceEntityWithEnergy(amount);
            }
            return false;
        }
        if ("causeEnExplosion".equals(type)) {
            return false;
        }
        return super.func_70097_a(source, amount);
    }

    private void enhanceEntityWithEnergy(float energyDamage) {
        double currentAttackDamage = this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b();
        double attackBoost = Math.min((double)energyDamage * attackBoostEnergyFactor, this.initialAttackDamage * attackBoostPercentPerHit);
        double maxAttack = this.initialAttackDamage * attackMaxPercent;
        double newAttackDamage = Math.min(currentAttackDamage + attackBoost, maxAttack);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(newAttackDamage);
        this.getEntityData().func_74780_a("jrmcSpawnInitiatedCAT", newAttackDamage);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "ninjinentities:textures/entity/Super17.png";
    }
}

