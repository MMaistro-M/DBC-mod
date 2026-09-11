/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package hedaox.ninjinentities.entities.DBS;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hedaox.ninjinentities.config.ModConfig;
import hedaox.ninjinentities.entities.EntityDBCNinjin;
import hedaox.ninjinentities.event.DodgeSystem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityGokuUI
extends EntityDBCNinjin {
    private final DodgeSystem dodgeSystem = new DodgeSystem((EntityLivingBase)this);
    private final float counterChance = (float)(ModConfig.gokuUIMCounterChance / 100.0);

    public EntityGokuUI(World world) {
        super(world, 100, EntityDBCNinjin.MindState.NEUTRAL, true, true, new byte[]{3, 3, 1, 6, 5, 2}, new byte[]{2, 2, 2, 2, 2, 7}, 255.0f, 255.0f, 255.0f, false, true, 225.0f, 225.0f, 225.0f);
        this.field_70728_aV = 80;
        this.func_70105_a(0.6f, 1.8f);
        this.dodgeSystem.dodge = (int)ModConfig.gokuUIDodgeRate;
        this.dodgeSystem.strikeBack = (int)ModConfig.gokuUICounterChance;
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(110000.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(15000.0);
    }

    @Override
    public boolean func_70097_a(DamageSource source, float damage) {
        if (this.dodgeSystem.tryDodge(source.func_76346_g())) {
            this.tryCounterAttack(source.func_76346_g());
            return false;
        }
        return super.func_70097_a(source, damage);
    }

    private void tryCounterAttack(Entity attacker) {
        NBTTagCompound nbt = this.getEntityData();
        if (nbt.func_74764_b("jrmcSpawnInitiatedCAT") && attacker instanceof EntityPlayer && DodgeSystem.RANDOM.nextFloat() < this.counterChance) {
            double attackDamage = nbt.func_74769_h("jrmcSpawnInitiatedCAT");
            this.dodgeSystem.performCounterAttack(attacker, attackDamage);
        }
    }

    @Override
    public boolean func_70652_k(Entity target) {
        this.func_71038_i();
        return super.func_70652_k(target);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "ninjinentities:textures/entity/gokuUi.png";
    }
}

