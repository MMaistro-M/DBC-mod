/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package hedaox.ninjinentities.entities.DBS;

import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hedaox.ninjinentities.config.ModConfig;
import hedaox.ninjinentities.entities.EntityDBCNinjin;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityMara_Fusion
extends EntityDBCNinjin {
    public int randomSoundDelay = 0;
    private int tickCounter = 0;
    private int totalKiAbsorbed = 0;
    private int storedKi = 0;
    private long lastConversionTime = 0L;
    private double initialMaxHealth;
    private double initialAttackDamage;
    private final long spawnTime;

    public EntityMara_Fusion(World par1World) {
        super(par1World, 0, EntityDBCNinjin.MindState.AGGRESSIVE, false, true, new byte[]{1, 6, 2, 3, 4, 3}, new byte[]{3, 2, 7, 3, 7, 2});
        this.field_70728_aV = 80;
        this.func_70105_a(1.0f, 2.3f);
        this.spawnTime = System.currentTimeMillis();
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(18000.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1800.0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "ninjinentities:textures/entity/fusion_mara.png";
    }

    public void func_70030_z() {
        super.func_70030_z();
        if (this.initialMaxHealth == 0.0 && this.getEntityData().func_74764_b("jrmcSpawnInitiatedCHP")) {
            this.initialMaxHealth = this.getEntityData().func_74769_h("jrmcSpawnInitiatedCHP");
        }
        if (this.initialAttackDamage == 0.0 && this.getEntityData().func_74764_b("jrmcSpawnInitiatedCAT")) {
            this.initialAttackDamage = this.getEntityData().func_74769_h("jrmcSpawnInitiatedCAT");
        }
    }

    @Override
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70128_L || this.func_110143_aJ() <= 0.0f || this.func_110143_aJ() < 0.1f) {
            return;
        }
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.spawnTime < 5000L) {
            return;
        }
        if (!this.getEntityData().func_74764_b("jrmcSpawnInitiatedCAT") || !this.getEntityData().func_74764_b("jrmcSpawnInitiatedCHP")) {
            return;
        }
        if (this.tickCounter % ModConfig.kiDrainIntervalFusion == 0) {
            List<EntityPlayer> playersInRange = this.getPlayersInRange(ModConfig.kiDrainRangeFusion);
            for (EntityPlayer player : playersInRange) {
                int drainedKi;
                if (player == null || (drainedKi = this.reducePlayerKi(player, ModConfig.kiDrainAmountFusion)) <= 0) continue;
                this.totalKiAbsorbed += drainedKi;
                this.storedKi += drainedKi;
            }
        }
        if (currentTime - this.lastConversionTime >= 5000L && this.func_110143_aJ() > 0.0f) {
            this.handleKiConversion();
            this.lastConversionTime = currentTime;
        }
        ++this.tickCounter;
    }

    public List<EntityPlayer> getPlayersInRange(double range) {
        List<Object> playersInRange = new ArrayList<EntityPlayer>();
        if (this.field_70170_p == null) {
            return playersInRange;
        }
        double x = this.field_70165_t;
        double y = this.field_70163_u;
        double z = this.field_70161_v;
        AxisAlignedBB boundingBox = AxisAlignedBB.func_72330_a((double)(x - range), (double)(y - range), (double)(z - range), (double)(x + range), (double)(y + range), (double)(z + range));
        playersInRange = this.field_70170_p.func_72872_a(EntityPlayer.class, boundingBox);
        return playersInRange;
    }

    public int reducePlayerKi(EntityPlayer player, String amount) {
        int drainAmount;
        if (player == null) {
            return 0;
        }
        NBTTagCompound playerData = player.getEntityData();
        if (playerData.func_74764_b("playerGameType") && playerData.func_74762_e("playerGameType") == 1) {
            return 0;
        }
        int currentKi = JRMCoreH.getInt(player, "jrmcEnrgy");
        if (amount.endsWith("%")) {
            double percentage = Double.parseDouble(amount.substring(0, amount.length() - 1)) / 100.0;
            drainAmount = (int)((double)currentKi * percentage);
        } else {
            drainAmount = Integer.parseInt(amount);
        }
        int minKi = 100;
        int newKi = Math.max(currentKi - drainAmount, minKi);
        JRMCoreH.setInt(newKi, player, "jrmcEnrgy");
        return drainAmount;
    }

    private void handleKiConversion() {
        if (this.field_70128_L || this.func_110143_aJ() <= 0.0f || this.func_110143_aJ() < 0.1f) {
            return;
        }
        double maxHealth = this.getEntityData().func_74769_h("jrmcSpawnInitiatedCHP");
        double currentHealth = this.func_110143_aJ();
        if (currentHealth < maxHealth * 0.8) {
            this.healFromKi();
        } else {
            this.enhanceEntity();
        }
    }

    private void healFromKi() {
        if (this.field_70128_L || this.func_110143_aJ() <= 0.0f || this.func_110143_aJ() < 0.1f) {
            return;
        }
        double maxHealth = this.getEntityData().func_74769_h("jrmcSpawnInitiatedCHP");
        double currentHealth = this.func_110143_aJ();
        if (currentHealth <= 0.0) {
            return;
        }
        if (this.func_110143_aJ() <= 0.0f) {
            return;
        }
        double maxHealAmount = maxHealth * (ModConfig.kiToHealthRatioFusion / 100.0);
        double healthToHeal = Math.min((double)this.storedKi, maxHealAmount);
        double newHealth = Math.min(currentHealth + healthToHeal, maxHealth);
        this.func_70606_j((float)newHealth);
        double remainingKi = (double)this.storedKi - healthToHeal;
        if (remainingKi > 0.0) {
            this.enhanceEntityWithRemainingKi(remainingKi);
        }
        this.storedKi = 0;
    }

    private void enhanceEntityWithKi(double ki) {
        double currentMaxHealth = this.getEntityData().func_74769_h("jrmcSpawnInitiatedCHP");
        double currentAttackDamage = this.getEntityData().func_74769_h("jrmcSpawnInitiatedCAT");
        double newMaxHealth = Math.floor(currentMaxHealth + ki * this.parseRatio(ModConfig.kiToMaxHealthRatioFusion, 1.0));
        double newAttackDamage = Math.floor(currentAttackDamage + ki * this.parseRatio(ModConfig.kiToMaxHealthRatioFusion, 1.0));
        double maxHealthLimit = this.initialMaxHealth * (1.0 + ModConfig.maxHealthEnhancementLimit / 100.0);
        double attackDamageLimit = this.initialAttackDamage * (1.0 + ModConfig.attackEnhancementLimit / 100.0);
        newMaxHealth = Math.min(newMaxHealth, maxHealthLimit);
        newAttackDamage = Math.min(newAttackDamage, attackDamageLimit);
        this.getEntityData().func_74780_a("jrmcSpawnInitiatedCHP", newMaxHealth);
        this.getEntityData().func_74780_a("jrmcSpawnInitiatedCAT", newAttackDamage);
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(newMaxHealth);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(newAttackDamage);
        this.updateMaxHealthModifier(newMaxHealth);
        this.updateAttackDamageModifier(newAttackDamage);
    }

    private void enhanceEntity() {
        this.enhanceEntityWithKi(this.storedKi);
        this.storedKi = 0;
    }

    private void enhanceEntityWithRemainingKi(double remainingKi) {
        this.enhanceEntityWithKi(remainingKi);
    }

    private void updateMaxHealthModifier(double newMaxHealth) {
        AttributeModifier oldHealthModifier = this.func_110140_aT().func_111151_a(SharedMonsterAttributes.field_111267_a).func_111127_a(this.func_110124_au());
        if (oldHealthModifier != null) {
            this.func_110140_aT().func_111151_a(SharedMonsterAttributes.field_111267_a).func_111124_b(oldHealthModifier);
        }
        double modifierValue = newMaxHealth - this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b();
        AttributeModifier newHealthModifier = new AttributeModifier(this.func_110124_au(), "maxHealthModifier", modifierValue, 0);
        this.func_110140_aT().func_111151_a(SharedMonsterAttributes.field_111267_a).func_111121_a(newHealthModifier);
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

    private double parseRatio(String ratio, double defaultValue) {
        if (ratio.endsWith("%")) {
            try {
                return Double.parseDouble(ratio.substring(0, ratio.length() - 1)) / 100.0;
            }
            catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        try {
            return Double.parseDouble(ratio);
        }
        catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}

