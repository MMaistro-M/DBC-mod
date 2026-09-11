/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.DragonBC.common.Npcs.EntityDBCEvil;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.entity.EntityCusPar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityChampa
extends EntityDBCEvil {
    public int randomSoundDelay = 0;
    public String tex;
    public final int AttPow = 9200;
    public final int HePo = 92000;
    private int target;

    public EntityChampa(World par1World) {
        super(par1World);
        this.field_70728_aV = 200;
        this.tex = "champa";
        this.func_70105_a(0.6f, 2.0f);
        this.setData1(5);
        this.setData2(8);
        this.setHardDifficulty();
        this.addAAiTeleport();
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(92000.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(9200.0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "jinryuudragonbc:npcs/" + this.tex + ".png";
    }

    public long BattlePowerOld() {
        int BP = 1776275456;
        int exp = this.field_70728_aV * 100;
        long BattlePower = BP + this.field_70146_Z.nextInt((int)Math.pow(10.0, (BP + "").length() - 2));
        return BattlePower;
    }

    @Override
    public void func_70636_d() {
        if (this.doBlst()) {
            if ((int)(Math.random() * 2.0) == 0) {
                this.setData1(5);
                this.setData2(8);
            } else {
                this.setData1(1);
                this.setData2(0);
            }
        }
        if (this.field_70170_p.field_72995_K && JGConfigClientSettings.CLIENT_DA8) {
            for (int k = 0; k < JGConfigClientSettings.get_da1(); ++k) {
                for (int i = 0; i < 5; ++i) {
                    EntityChampa pl = this;
                    double x = Math.random() * 1.0 - 0.5;
                    double y = Math.random() * (double)this.field_70131_O - 0.5;
                    double z = Math.random() * 1.0 - 0.5;
                    EntityCusPar entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, ((Entity)pl).field_70165_t, ((Entity)pl).field_70163_u, ((Entity)pl).field_70161_v, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 30, 2, (float)(Math.random() * (double)0.03f), (float)(Math.random() * (double)0.03f) + 0.05f, 0.1f, 2, 168.0f, 50.0f, 214.0f, 0.0f, 0.0f, 0.0f, 175.0f, 55.0f, 228.0f, 3, 0.5f, 0.0f, 0.0f, 0.0f, -0.1f, false, -1, false, (Entity)this);
                    entity.field_70170_p.func_72838_d((Entity)entity);
                    x = Math.random() * 1.0 - 0.5;
                    y = Math.random() * (double)this.field_70131_O - 0.5;
                    z = Math.random() * 1.0 - 0.5;
                    entity = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, ((Entity)pl).field_70165_t, ((Entity)pl).field_70163_u, ((Entity)pl).field_70161_v, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 30, 2, (float)(Math.random() * (double)0.03f), (float)(Math.random() * (double)0.03f) + 0.05f, 0.1f, 2, 168.0f, 50.0f, 214.0f, 0.0f, 0.0f, 0.0f, 175.0f, 55.0f, 228.0f, 3, 0.5f, 0.0f, 0.0f, 0.0f, -0.1f, false, -1, false, (Entity)this);
                    entity.field_70170_p.func_72838_d((Entity)entity);
                }
            }
        }
        super.func_70636_d();
    }

    @Override
    public void func_70645_a(DamageSource par1DamageSource) {
        Entity var3 = par1DamageSource.func_76346_g();
        if (var3 instanceof EntityPlayer) {
            this.becomeAngryAt(var3);
        }
        super.func_70645_a(par1DamageSource);
    }

    private void becomeAngryAt(Entity par1Entity) {
        this.field_70789_a = par1Entity;
        this.angerLevel = 400 + this.field_70146_Z.nextInt(400);
        this.randomSoundDelay = this.field_70146_Z.nextInt(40);
    }
}

