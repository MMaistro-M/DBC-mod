/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.World
 */
package hedaox.ninjinentities.entities.doujin;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hedaox.ninjinentities.entities.EntityDBCNinjin;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.World;

public class EntityVegetoUw
extends EntityDBCNinjin {
    public int randomSoundDelay = 0;
    private double initialAttackDamage;

    public EntityVegetoUw(World par1World) {
        super(par1World, 85, EntityDBCNinjin.MindState.AGGRESSIVE, true, true, new byte[]{1, 2, 3, 4, 5, 6}, new byte[]{7, 7, 2, 2, 7, 2}, 135.0f, 40.0f, 200.0f, false, true, 135.0f, 0.0f, 155.0f);
        this.field_70728_aV = 80;
        this.func_70105_a(0.6f, 1.8f);
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(90000.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(9000.0);
    }

    public void func_70030_z() {
        super.func_70030_z();
        if (this.initialAttackDamage == 0.0 && this.getEntityData().func_74764_b("jrmcSpawnInitiatedCAT")) {
            this.initialAttackDamage = this.getEntityData().func_74769_h("jrmcSpawnInitiatedCAT");
        }
    }

    @Override
    public void func_70071_h_() {
        super.func_70071_h_();
        this.updateAttackBasedOnHealth();
    }

    private void updateAttackBasedOnHealth() {
        double currentHealth = this.func_110143_aJ();
        double maxHealth = this.func_110138_aP();
        double healthPercentage = currentHealth / maxHealth * 100.0;
        double attackIncreasePercentage = 100.0 - healthPercentage;
        double newAttackDamage = this.initialAttackDamage * (1.0 + attackIncreasePercentage / 100.0);
        this.getEntityData().func_74780_a("jrmcSpawnInitiatedCAT", newAttackDamage);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(newAttackDamage);
        this.updateAttackDamageModifier(newAttackDamage);
    }

    private void updateAttackDamageModifier(double newAttackDamage) {
        AttributeModifier oldAttackDamageModifier = this.func_110140_aT().func_111151_a(SharedMonsterAttributes.field_111264_e).func_111127_a(this.func_110124_au());
        if (oldAttackDamageModifier != null) {
            this.func_110140_aT().func_111151_a(SharedMonsterAttributes.field_111264_e).func_111124_b(oldAttackDamageModifier);
        }
        double modifierValue = newAttackDamage - this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b();
        AttributeModifier newAttackDamageModifier = new AttributeModifier(this.func_110124_au(), "attackDamageModifier", modifierValue, 0);
        this.func_110140_aT().func_111151_a(SharedMonsterAttributes.field_111264_e).func_111121_a(newAttackDamageModifier);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "ninjinentities:textures/entity/vegetoUw.png";
    }
}

