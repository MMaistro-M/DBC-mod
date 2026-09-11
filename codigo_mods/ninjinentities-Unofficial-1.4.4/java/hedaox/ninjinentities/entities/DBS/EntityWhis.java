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

import JinRyuu.DragonBC.common.Items.ItemsDBC;
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

public class EntityWhis
extends EntityDBCNinjin {
    private final DodgeSystem dodgeSystem = new DodgeSystem((EntityLivingBase)this);
    private final float counterChance = (float)(ModConfig.gokuUIMCounterChance / 100.0);
    public int randomSoundDelay = 0;

    public EntityWhis(World par1World) {
        super(par1World, 50, EntityDBCNinjin.MindState.NEUTRAL, true, true, new byte[]{6}, new byte[]{3});
        this.field_70728_aV = 80;
        this.func_70105_a(0.6f, 1.98f);
        this.dodgeSystem.dodge = (int)ModConfig.WhisDodgeRate;
        this.dodgeSystem.strikeBack = (int)ModConfig.WhisCounterChance;
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(250000.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(25000.0);
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
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "jinryuudragonbc:npcs/whis.png";
    }

    @Override
    public boolean func_70601_bi() {
        return this.field_70170_p.func_72855_b(this.field_70121_D) && this.field_70170_p.func_72945_a((Entity)this, this.field_70121_D).isEmpty() && !this.field_70170_p.func_72953_d(this.field_70121_D);
    }

    protected void func_70628_a(boolean par1, int par2) {
        int var4;
        int var3 = this.field_70146_Z.nextInt(2 + par2);
        for (var4 = 0; var4 < var3; ++var4) {
            this.func_145779_a(ItemsDBC.ItemsOutfit1[40], 1);
        }
        var3 = this.field_70146_Z.nextInt(2 + par2);
        for (var4 = 0; var4 < var3; ++var4) {
            this.func_145779_a(ItemsDBC.ItemsOutfit2[40], 1);
        }
        var3 = this.field_70146_Z.nextInt(2 + par2);
        for (var4 = 0; var4 < var3; ++var4) {
            this.func_145779_a(ItemsDBC.ItemsOutfit3[40], 1);
        }
    }
}

