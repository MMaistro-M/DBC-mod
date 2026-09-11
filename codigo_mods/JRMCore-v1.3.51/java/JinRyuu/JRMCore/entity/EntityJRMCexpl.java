/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.entity.EntityCusPar;
import JinRyuu.JRMCore.entity.EntityCusPars;
import JinRyuu.JRMCore.entity.EntityEnergyAttJ4;
import JinRyuu.JRMCore.mod_JRMCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

@SideOnly(value=Side.CLIENT)
public class EntityJRMCexpl
extends Entity {
    public int randomSoundDelay = 0;
    public int tex = 1;
    public float explsiz;
    public byte type;
    int Age;
    int MaxAge = 40;

    public EntityJRMCexpl(World par1World, byte type) {
        super(par1World);
        this.type = type;
    }

    public void func_70071_h_() {
        if (this.field_70170_p.field_72995_K && JGConfigClientSettings.CLIENT_GR4) {
            for (int k = 0; k < JGConfigClientSettings.get_da1(); ++k) {
                if (this.type == 10) {
                    if (this.field_70173_aa % 2 != 0 || this.field_70173_aa >= 10) continue;
                    EntityJRMCexpl pl = this;
                    float area = this.explsiz;
                    for (int i = 0; i < (int)area + 5; ++i) {
                        float a = 1.0f;
                        float h1 = 1.0f;
                        float scale = 1.0f + area;
                        scale *= 0.01f;
                        double x = 0.0;
                        double y = 0.0;
                        double z = 0.0;
                        float size1 = 0.3f;
                        float motx = ((float)(Math.random() * (double)size1) - size1 / 2.0f) * (area / 5.0f);
                        float moty = (float)(Math.random() * (double)size1 / 2.0) * (area / 5.0f);
                        float motz = ((float)(Math.random() * (double)size1) - size1 / 2.0f) * (area / 5.0f);
                        float size = area;
                        x = (float)(Math.random() * (double)size) - size / 2.0f;
                        y = (float)(Math.random() * (double)size) - size / 2.0f;
                        z = (float)(Math.random() * (double)size) - size / 2.0f;
                        EntityCusPar entity7 = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.4f, 0.4f, pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, x, y, z, motx *= 0.1f, moty *= 0.8f, motz *= 0.1f, 0.0f, 10, 12, 4, 32, true, (float)(Math.random() * (double)0.3f) + 0.3f, false, 0.0f, 1, "", 50, 1, ((float)(Math.random() * (double)0.01f) + 0.02f) * scale, ((float)(Math.random() * (double)0.02f) + 0.09f) * scale, ((float)(Math.random() * (double)0.002f) + 0.003f) * scale, 1, 0.9647059f, 0.38431373f, 0.98039216f, -0.01f, -0.001f, -0.001f, 0.8392157f, 0.32941177f, 0.9137255f, 3, 1.0f, 0.0f, 0.0f, 0.0f, -0.05f, false, -1, false, null);
                        entity7.setdata39((int)(Math.random() * 360.0));
                        this.field_70170_p.func_72838_d((Entity)entity7);
                    }
                    continue;
                }
                if (this.field_70173_aa == 1 && (this.type == 3 || this.type == 4)) {
                    if (this.type == 3) {
                        this.field_70170_p.func_72838_d((Entity)new EntityEnergyAttJ4(this.field_70170_p, 0, this.field_70165_t, this.field_70163_u + 1.0, this.field_70161_v));
                    }
                    if (this.type != 4) continue;
                    this.field_70170_p.func_72838_d((Entity)new EntityEnergyAttJ4(this.field_70170_p, 1, this.field_70165_t, this.field_70163_u + 1.0, this.field_70161_v));
                    continue;
                }
                if (this.field_70173_aa == 1) {
                    if (this.type != 5) {
                        if (this.type != 0) {
                            if (JGConfigClientSettings.CLIENT_DA6) {
                                if (this.type != 3 && this.type != 4) {
                                    float a = 1.0f;
                                    float h1 = 1.0f;
                                    float scale = 1.0f + this.explsiz;
                                    EntityJRMCexpl pl = this;
                                    double x = 0.0;
                                    double y = 0.0;
                                    double z = 0.0;
                                    EntityCusPar entity7 = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.4f, 0.4f, pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, x, y, z, 0.0, 0.0, 0.0, 0.0f, (int)(Math.random() * 2.0) + 12, 12, 4, 32, true, (float)(Math.random() * (double)0.3f) + 0.3f, false, 0.0f, 1, "", 30, 1, ((float)(Math.random() * (double)0.02f) + 0.02f) * (scale *= 1.2f), ((float)(Math.random() * (double)0.04f) + 0.09f) * scale, ((float)(Math.random() * (double)0.003f) + 0.005f) * scale, 0, 116.0f, 187.0f, 255.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 3, 1.0f, 0.0f, 0.0f, 0.0f, -0.05f, false, -1, false, null);
                                    entity7.setdata39((int)(Math.random() * 360.0));
                                    this.field_70170_p.func_72838_d((Entity)entity7);
                                    scale *= 0.65f;
                                    int num = (int)(Math.random() * 4.0) + 1;
                                    for (int i = 0; i < num; ++i) {
                                        EntityCusPar entity72 = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.4f, 0.4f, pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, x, y, z, 0.0, 0.0, 0.0, 0.0f, (int)(Math.random() * 2.0) + 6, 4, 4, 64, true, (float)(Math.random() * (double)0.2f) + 0.2f, false, 0.0f, 1, "", 15, 1, ((float)(Math.random() * (double)0.02f) + 0.02f) * scale, ((float)(Math.random() * (double)0.04f) + 0.09f) * scale, ((float)(Math.random() * (double)0.003f) + 0.005f) * scale, 0, 116.0f, 187.0f, 255.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 3, 1.0f, 0.0f, 0.0f, 0.0f, -0.05f, false, -1, false, null);
                                        entity72.setdata39((int)(Math.random() * 360.0));
                                        this.field_70170_p.func_72838_d((Entity)entity72);
                                    }
                                }
                            } else {
                                if (this.type == 5) {
                                    for (int i = 0; i < 5; ++i) {
                                        if (this.field_70173_aa % 2 != 0) continue;
                                        this.func_exa();
                                        this.func_ex3();
                                    }
                                }
                                for (int i = 0; i < 5; ++i) {
                                    if (this.type == 1) {
                                        if (!(this.explsiz > 0.5f)) continue;
                                        this.func_exa();
                                        continue;
                                    }
                                    if (this.type != 2) continue;
                                    for (int j = 0; j < 2; ++j) {
                                        this.func_ex3();
                                    }
                                }
                            }
                        }
                    } else if (this.field_70173_aa < 15 && this.field_70173_aa % 2 == 0) {
                        this.func_exa();
                    }
                }
                if (this.type != 5) {
                    if (JGConfigClientSettings.CLIENT_DA6) {
                        if (this.type != 3 && this.type != 4 && this.type != 0 && this.field_70173_aa < 10) {
                            float motz;
                            float moty;
                            double z;
                            double y;
                            int i;
                            EntityJRMCexpl pl = this;
                            for (i = 0; i < (int)this.explsiz + 5; ++i) {
                                float a = 1.0f;
                                float h1 = 1.0f;
                                float scale = 1.0f + this.explsiz;
                                scale *= 0.4f;
                                double x = 0.0;
                                y = 0.0;
                                z = 0.0;
                                float size1 = 0.5f;
                                float motx = ((float)(Math.random() * (double)size1) - size1 / 2.0f) * (this.explsiz / 5.0f);
                                moty = ((float)(Math.random() * (double)size1) - size1 / 2.0f) * (this.explsiz / 5.0f);
                                motz = ((float)(Math.random() * (double)size1) - size1 / 2.0f) * (this.explsiz / 5.0f);
                                EntityCusPar entity7 = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.4f, 0.4f, pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, x, y, z, motx, moty, motz, 0.0f, 10, 12, 4, 32, true, (float)(Math.random() * (double)0.3f) + 0.3f, false, 0.0f, 1, "", 50, 1, ((float)(Math.random() * (double)0.01f) + 0.02f) * scale, ((float)(Math.random() * (double)0.02f) + 0.09f) * scale, ((float)(Math.random() * (double)0.002f) + 0.003f) * scale, 1, 116.0f, 187.0f, 255.0f, -0.02f, -0.02f, -0.03f, 56.0f, 67.0f, 100.0f, 3, 1.0f, 0.0f, 0.0f, 0.0f, -0.05f, false, -1, false, null);
                                entity7.setdata39((int)(Math.random() * 360.0));
                                this.field_70170_p.func_72838_d((Entity)entity7);
                            }
                            for (i = 0; i < (int)this.explsiz + 5; ++i) {
                                float a = 1.0f;
                                float h1 = 1.0f;
                                float scale = 1.0f + this.explsiz;
                                scale *= 0.25f;
                                double x = 0.0;
                                y = 0.0;
                                z = 0.0;
                                float size1 = 0.3f;
                                float motx = ((float)(Math.random() * (double)size1) - size1 / 2.0f) * (this.explsiz / 5.0f);
                                moty = ((float)(Math.random() * (double)size1) - size1 / 2.0f) * (this.explsiz / 5.0f);
                                motz = ((float)(Math.random() * (double)size1) - size1 / 2.0f) * (this.explsiz / 5.0f);
                                EntityCusPar entity7 = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.4f, 0.4f, pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, x, y, z, motx, moty, motz, 0.0f, 10, 12, 4, 32, true, (float)(Math.random() * (double)0.3f) + 0.3f, false, 0.0f, 1, "", 50, 1, ((float)(Math.random() * (double)0.01f) + 0.02f) * scale, ((float)(Math.random() * (double)0.02f) + 0.09f) * scale, ((float)(Math.random() * (double)0.002f) + 0.003f) * scale, 1, 1.0f, 1.0f, 1.0f, -0.01f, -0.005f, -0.005f, 216.0f, 244.0f, 245.0f, 3, 1.0f, 0.0f, 0.0f, 0.0f, -0.05f, false, -1, false, null);
                                entity7.setdata39((int)(Math.random() * 360.0));
                                this.field_70170_p.func_72838_d((Entity)entity7);
                            }
                        }
                    } else if (this.type == 1 && this.explsiz > 0.5f && (float)this.MaxAge * 0.8f >= (float)this.Age) {
                        this.func_exa();
                    }
                    if (this.type != 0) continue;
                    this.func_ex1();
                    mod_JRMCore.proxy.func_gcp(this, EntityCusPars.PART1, Math.random() * 4.0 - 2.0, 0.0 + Math.random() * (double)(this.field_70131_O * 0.25f) + (double)(this.field_70131_O / 2.0f) - (double)(this.field_70131_O * 0.25f), Math.random() * 4.0 - 2.0, Math.random() * 0.05 - 0.025, Math.random() * 0.1 + 0.05, Math.random() * 0.05 - 0.025, 0.5f, 0.5f, 0.5f);
                    continue;
                }
                if (this.field_70173_aa >= 15 || this.field_70173_aa % 2 != 0) continue;
                this.func_exa();
            }
        }
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        if (this.Age++ >= this.MaxAge) {
            this.func_70106_y();
        }
        this.field_70181_x += 0.0;
        this.func_70091_d(0.0, 0.0, 0.0);
        if (this.field_70163_u == this.field_70167_r) {
            this.field_70159_w *= 1.0;
            this.field_70179_y *= 1.0;
        }
        this.field_70159_w *= 0.0;
        this.field_70181_x *= 0.0;
        this.field_70179_y *= 0.0;
        if (this.field_70122_E) {
            this.field_70159_w *= 0.0;
            this.field_70179_y *= 0.0;
        }
    }

    private void func_exa() {
        this.func_ex1();
        this.func_ex2();
        this.func_ex3();
    }

    private void func_ex1() {
        mod_JRMCore.proxy.func_gcp(this, EntityCusPars.PART2, Math.random() * 6.0 - 3.0, 0.0 + (double)(this.field_70131_O / 2.0f), Math.random() * 6.0 - 3.0, Math.random() * 0.2 - 0.1, Math.random() * 0.2 + 0.1, Math.random() * 0.2 - 0.1, 1.0f, 1.0f, 1.0f);
    }

    private void func_ex2() {
        mod_JRMCore.proxy.func_gcp(this, EntityCusPars.PART3, Math.random() * 6.0 - 3.0, 0.0 + (double)(this.field_70131_O / 2.0f), Math.random() * 6.0 - 3.0, Math.random() * 0.1 - 0.075, Math.random() * 0.2 + 0.1, Math.random() * 0.15 - 0.075, 0.5f, 0.5f, 0.5f);
    }

    private void func_ex3() {
        mod_JRMCore.proxy.func_gcp(this, EntityCusPars.PART4, Math.random() * 4.0 - 2.0, 0.0 + (double)(this.field_70131_O / 2.0f), Math.random() * 4.0 - 2.0, Math.random() * 1.2 - 0.6, Math.random() * 0.2 + 0.1, Math.random() * 1.2 - 0.6, 0.05f, 0.01f, 0.1f);
    }

    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        String textura = "";
        return textura;
    }

    public boolean getCanSpawnHere() {
        return !this.field_70170_p.func_72855_b(this.field_70121_D);
    }

    public void onLivingUpdate() {
    }

    protected void func_70088_a() {
    }

    protected void func_70037_a(NBTTagCompound var1) {
    }

    protected void func_70014_b(NBTTagCompound var1) {
    }

    @SideOnly(value=Side.CLIENT)
    public boolean isInRangeToRenderVec3D(Vec3 par1Vec3) {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return 65536.0;
    }

    public boolean func_70112_a(double par1) {
        return true;
    }
}

