/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.DragonBC.common.Items.ItemsDBC;
import JinRyuu.DragonBC.common.Npcs.EntityFreezaSoldiers;
import JinRyuu.DragonBC.common.Npcs.EntityNamekian01;
import JinRyuu.DragonBC.common.Npcs.EntityNamekian03;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityFreezaSoldier1
extends EntityFreezaSoldiers {
    public int randomSoundDelay = 0;
    public final int AttPow = 20;
    public final int HePo = 200;

    public EntityFreezaSoldier1(World par1World) {
        super(par1World);
        this.field_70728_aV = 5;
        this.field_70715_bh.func_75776_a(0, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityNamekian01.class, 200, false));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityNamekian03.class, 200, false));
        this.setMediumDifficulty();
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(200.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(20.0);
    }

    @Override
    public void func_70071_h_() {
        if (this.randomSoundDelay <= 0 || --this.randomSoundDelay == 0) {
            // empty if block
        }
        super.func_70071_h_();
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "jinryuudragonbc:npcs/soldier2.png";
    }

    @Override
    public boolean func_70601_bi() {
        return this.field_70170_p.func_72855_b(this.field_70121_D) && this.field_70170_p.func_72945_a((Entity)this, this.field_70121_D).isEmpty() && !this.field_70170_p.func_72953_d(this.field_70121_D);
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74777_a("Anger", (short)this.angerLevel);
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.angerLevel = par1NBTTagCompound.func_74765_d("Anger");
    }

    @Override
    protected Entity func_70782_k() {
        return this.target != null ? this.target : (this.angerLevel == 0 ? null : super.func_70782_k());
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
    }

    protected void func_70628_a(boolean par1, int par2) {
        int var3 = this.field_70146_Z.nextInt(1);
        if (var3 != 0) {
            this.func_145779_a(ItemsDBC.BattleArmorHelmet04, 1);
        }
        var3 = this.field_70146_Z.nextInt(2 + par2);
        for (int var4 = 0; var4 < var3; ++var4) {
        }
    }

    public boolean func_70085_c(EntityPlayer par1EntityPlayer) {
        return false;
    }
}

