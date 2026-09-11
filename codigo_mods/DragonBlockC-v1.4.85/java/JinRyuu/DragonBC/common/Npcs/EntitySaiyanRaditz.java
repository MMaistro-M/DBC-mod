/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.DragonBC.common.Items.ItemsDBC;
import JinRyuu.DragonBC.common.Npcs.EntityDBCEvil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntitySaiyanRaditz
extends EntityDBCEvil {
    public int randomSoundDelay = 0;
    public final int AttPow = 30;
    public final int HePo = 300;

    public EntitySaiyanRaditz(World par1World) {
        super(par1World);
        this.field_70728_aV = 30;
        this.setMediumDifficulty();
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(300.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(30.0);
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
        return "jinryuudragonbc:npcs/raditz.png";
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

    @Override
    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        if (this.func_85032_ar()) {
            return false;
        }
        Entity var3 = par1DamageSource.func_76346_g();
        if (var3 instanceof EntityPlayer) {
            List var4 = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72314_b(32.0, 32.0, 32.0));
            for (int var5 = 0; var5 < var4.size(); ++var5) {
                Entity var6 = (Entity)var4.get(var5);
                if (!(var6 instanceof EntitySaiyanRaditz)) continue;
                EntitySaiyanRaditz var7 = (EntitySaiyanRaditz)var6;
                var7.becomeAngryAt(var3);
            }
            this.becomeAngryAt(var3);
        }
        return super.func_70097_a(par1DamageSource, par2);
    }

    private void becomeAngryAt(Entity par1Entity) {
        this.field_70789_a = par1Entity;
        this.angerLevel = 400 + this.field_70146_Z.nextInt(400);
        this.randomSoundDelay = this.field_70146_Z.nextInt(40);
    }

    protected void func_70628_a(boolean par1, int par2) {
        int k;
        int j = this.field_70146_Z.nextInt(2) + this.field_70146_Z.nextInt(1 + par2);
        for (k = 0; k < j; ++k) {
            this.func_145779_a(ItemsDBC.BattleArmorHelmet04, 1);
        }
        j = this.field_70146_Z.nextInt(1) + this.field_70146_Z.nextInt(1 + par2);
        for (k = 0; k < j; ++k) {
            this.func_145779_a(Items.field_151016_H, 1);
        }
    }

    public boolean func_70085_c(EntityPlayer par1EntityPlayer) {
        return false;
    }
}

