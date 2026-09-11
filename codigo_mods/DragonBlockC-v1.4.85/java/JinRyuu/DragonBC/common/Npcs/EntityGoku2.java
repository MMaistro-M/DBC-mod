/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.entity.EntityPlayerSP
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
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityGoku2
extends EntityDBCEvil {
    public int randomSoundDelay = 0;
    public String tex;
    public final int AttPow = 14000;
    public final int HePo = 94000;
    private int target;

    public EntityGoku2(World par1World) {
        super(par1World);
        this.field_70728_aV = 200;
        this.tex = "goku_ssj";
        this.func_70105_a(0.6f, 2.0f);
        this.setData2(2);
        this.setHardDifficulty();
        this.addAAiTeleport();
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(94000.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(14000.0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "jinryuudragonbc:npcs/" + this.tex + ".png";
    }

    public long BattlePowerOld() {
        int BP = 1206994944;
        int exp = this.field_70728_aV * 100;
        long BattlePower = BP + this.field_70146_Z.nextInt((int)Math.pow(10.0, (BP + "").length() - 2));
        return BattlePower;
    }

    @Override
    public void func_70636_d() {
        if (this.doBlst()) {
            int r = (int)(Math.random() * 3.0);
            if (r == 0) {
                this.setData1(1);
                this.setData2(2);
            } else if (r == 1) {
                this.setData1(1);
                this.setData2(2);
            } else {
                this.setData1(6);
                this.setData2(7);
            }
        }
        if (this.field_70170_p.field_72995_K && JGConfigClientSettings.CLIENT_DA8) {
            for (int k = 0; k < JGConfigClientSettings.get_da1(); ++k) {
                for (int i = 0; i < 5; ++i) {
                    EntityGoku2 pl = this;
                    float a = 0.5f;
                    float h1 = 1.0f;
                    float red = 255.0f;
                    float green = 217.0f;
                    float blue = 25.0f;
                    float life = 0.8f * ((Entity)pl).field_70131_O;
                    float extra_scale = 1.0f + (((Entity)pl).field_70131_O > 2.1f ? ((Entity)pl).field_70131_O / 2.0f : 0.0f) / 5.0f;
                    float width = ((Entity)pl).field_70130_N * 3.0f;
                    double x = (Math.random() * 1.0 - 0.5) * (double)(width * 1.2f);
                    double y = Math.random() * (double)(this.field_70131_O * 1.4f) - (double)(this.field_70131_O / 2.0f) - (double)0.3f;
                    double z = (Math.random() * 1.0 - 0.5) * (double)(width * 1.2f);
                    double motx = Math.random() * (double)0.02f - (double)0.01f;
                    double moty = (Math.random() * (double)0.9f + (double)0.9f) * ((double)(life * extra_scale) * 0.07);
                    double motz = Math.random() * (double)0.02f - (double)0.01f;
                    EntityCusPar entity = new EntityCusPar("jinryuumodscore:bens_particles.png", ((Entity)pl).field_70170_p, 0.2f, 0.2f, ((Entity)pl).field_70165_t, ((Entity)pl).field_70163_u + (double)(pl instanceof EntityPlayerSP ? -1.6f : 0.0f), ((Entity)pl).field_70161_v, x, y, z, motx, moty, motz, 0.0f, (int)(Math.random() * 3.0) + 32, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", (int)(30.0f * life * 0.5f), 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * extra_scale, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * extra_scale, 0.2f * life * extra_scale, 0, red, green, blue, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.08f, false, -1, true, (Entity)pl);
                    ((Entity)pl).field_70170_p.func_72838_d((Entity)entity);
                    EntityCusPar entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", ((Entity)pl).field_70170_p, 0.2f, 0.2f, ((Entity)pl).field_70165_t, ((Entity)pl).field_70163_u + (double)(pl instanceof EntityPlayerSP ? -1.6f : 0.0f), ((Entity)pl).field_70161_v, x, y, z, motx, moty, motz, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", (int)(30.0f * life * 0.5f), 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * extra_scale, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * extra_scale, 0.1f * life * extra_scale, 0, red, green, blue, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.08f, false, -1, true, (Entity)pl);
                    ((Entity)pl).field_70170_p.func_72838_d((Entity)entity2);
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

