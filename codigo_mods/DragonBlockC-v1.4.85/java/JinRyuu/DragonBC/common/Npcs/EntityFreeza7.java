/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.DragonBC.common.Items.ItemsDBC;
import JinRyuu.DragonBC.common.Npcs.EntityDBCEvil;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.entity.EntityCusPar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityFreeza7
extends EntityDBCEvil {
    public int randomSoundDelay = 0;
    public final int AttPow = 12000;
    public final int HePo = 90000;
    int trans;

    public EntityFreeza7(World par1World) {
        super(par1World);
        this.field_70728_aV = 300;
        this.setData1(3);
        this.setData2(3);
        this.setHardDifficulty();
        this.addAAiTeleport();
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "jinryuudragonbc:npcs/freeza7.png";
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(90000.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(12000.0);
    }

    @Override
    public void func_70071_h_() {
        if (this.randomSoundDelay <= 0 || --this.randomSoundDelay == 0) {
            // empty if block
        }
        super.func_70071_h_();
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
    public void func_70645_a(DamageSource par1DamageSource) {
        Entity var3 = par1DamageSource.func_76346_g();
        if (var3 instanceof EntityPlayer) {
            this.becomeAngryAt(var3);
        }
        super.func_70645_a(par1DamageSource);
    }

    @Override
    public void func_70636_d() {
        if (this.doBlst()) {
            int r = (int)(Math.random() * 4.0);
            if (r == 0) {
                this.setData1(3);
                this.setData2(3);
            } else if (r == 1) {
                this.setData1(1);
                this.setData2(3);
            } else if (r == 2) {
                this.setData1(5);
                this.setData2(3);
            } else if (r == 3) {
                this.setData1(6);
                this.setData2(0);
            }
        }
        if (this.field_70170_p.field_72995_K && JGConfigClientSettings.CLIENT_DA8) {
            float red = 249.0f;
            float green = 212.0f;
            float blue = 33.0f;
            float red2 = 234.0f;
            float green2 = 134.0f;
            float blue2 = 34.0f;
            float out = 1.6f;
            float in = 1.0f;
            float life = 0.8f * this.field_70131_O;
            float extra_scale = 0.5f;
            int dea = 50;
            for (int k = 0; k < JGConfigClientSettings.get_da1(); ++k) {
                EntityCusPar entity2;
                EntityCusPar entity;
                double z;
                double y;
                double x;
                EntityFreeza7 pl;
                int i;
                for (i = 0; i < 2; ++i) {
                    pl = this;
                    x = Math.random() * (double)out - (double)(out / 2.0f);
                    y = Math.random() * (double)this.field_70131_O - 0.5;
                    z = Math.random() * (double)out - (double)(out / 2.0f);
                    entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, ((Entity)pl).field_70165_t, ((Entity)pl).field_70163_u, ((Entity)pl).field_70161_v, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 249.0f, 212.0f, 33.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, false, (Entity)this);
                    x = Math.random() * (double)out - (double)(out / 2.0f);
                    y = Math.random() * (double)this.field_70131_O - 0.5;
                    z = Math.random() * (double)out - (double)(out / 2.0f);
                    entity.field_70170_p.func_72838_d((Entity)entity);
                    entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, ((Entity)pl).field_70165_t, ((Entity)pl).field_70163_u, ((Entity)pl).field_70161_v, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 249.0f, 212.0f, 33.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, false, (Entity)this);
                    entity.field_70170_p.func_72838_d((Entity)entity2);
                }
                for (i = 0; i < 2; ++i) {
                    pl = this;
                    x = Math.random() * (double)(out *= 1.3f) - (double)(out / 2.0f);
                    y = Math.random() * (double)this.field_70131_O - 0.5;
                    z = Math.random() * (double)out - (double)(out / 2.0f);
                    entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, ((Entity)pl).field_70165_t, ((Entity)pl).field_70163_u, ((Entity)pl).field_70161_v, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 234.0f, 134.0f, 34.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, false, (Entity)this);
                    x = Math.random() * (double)out - (double)(out / 2.0f);
                    y = Math.random() * (double)this.field_70131_O - 0.5;
                    z = Math.random() * (double)out - (double)(out / 2.0f);
                    entity.field_70170_p.func_72838_d((Entity)entity);
                    entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, ((Entity)pl).field_70165_t, ((Entity)pl).field_70163_u, ((Entity)pl).field_70161_v, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 234.0f, 134.0f, 34.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, false, (Entity)this);
                    entity.field_70170_p.func_72838_d((Entity)entity2);
                }
                EntityFreeza7 pl2 = this;
                double x2 = Math.random() * 1.0 - 0.5;
                double y2 = (Math.random() * (double)this.field_70131_O - 0.5) * (double)0.8f;
                double z2 = Math.random() * 1.0 - 0.5;
                EntityCusPar entity3 = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, ((Entity)pl2).field_70165_t, ((Entity)pl2).field_70163_u, ((Entity)pl2).field_70161_v, x2, y2, z2, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 255.0f, 255.0f, 208.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, false, (Entity)this);
                x2 = Math.random() * 1.0 - 0.5;
                y2 = (Math.random() * (double)this.field_70131_O - 0.5) * (double)0.8f;
                z2 = Math.random() * 1.0 - 0.5;
                entity3.field_70170_p.func_72838_d((Entity)entity3);
                EntityCusPar entity22 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, ((Entity)pl2).field_70165_t, ((Entity)pl2).field_70163_u, ((Entity)pl2).field_70161_v, x2, y2, z2, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 255.0f, 255.0f, 208.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, false, (Entity)this);
                entity3.field_70170_p.func_72838_d((Entity)entity22);
            }
        }
        ++this.trans;
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
                if (!(var6 instanceof EntityFreeza7)) continue;
                EntityFreeza7 var7 = (EntityFreeza7)var6;
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
        int var4;
        int var3 = this.field_70146_Z.nextInt(1 + par2);
        for (var4 = 0; var4 < var3; ++var4) {
            this.func_145779_a(ItemsDBC.BattleArmorHelmet04, 1);
        }
        var3 = this.field_70146_Z.nextInt(2 + par2);
        for (var4 = 0; var4 < var3; ++var4) {
        }
    }

    public boolean func_70085_c(EntityPlayer par1EntityPlayer) {
        return false;
    }
}

