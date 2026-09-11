/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityCusPar
extends Entity {
    private Entity ent;
    private byte id_min;
    private byte id_max;
    private byte id;
    private String rr;
    private boolean ended;
    private boolean rotation;
    private boolean rotation2;
    private float rotation_sp;
    private float rotation_sp2;
    private float max_rotation_sp;
    private float max_rotation_sp2;
    private boolean rotate;
    private boolean rotate2;
    private float data39;
    private float data40;
    private float data41;
    private byte data42;
    private float data43;
    private float data44;
    private float data45;
    private byte data1;
    private int data2;
    private String data3;
    private byte data4;
    private float data5;
    private float data6;
    private float data7;
    private byte data31;
    private float data8;
    private float data9;
    private float data10;
    private float data11;
    private float data12;
    private float data13;
    private float data14;
    private float data15;
    private float data16;
    private double data17;
    private double data18;
    private double data19;
    private double data36;
    private double data37;
    private double data38;
    private byte data20;
    private float data21;
    private float data22;
    private float data23;
    private float data24;
    private float data25;
    private boolean data26;
    private boolean data27;
    private float data28;
    private float data29;
    private float data30;
    private byte data32;
    private boolean data33;
    private byte data34;
    private boolean data35;

    public boolean hasEnt() {
        return this.ent != null && !this.ent.field_70128_L;
    }

    public Entity getEnt() {
        return this.ent;
    }

    public byte getId_min() {
        return this.id_min;
    }

    public byte getId_max() {
        return this.id_max;
    }

    public byte getId() {
        return this.id;
    }

    public String getrr() {
        return this.rr;
    }

    public boolean hasEnded() {
        return this.ended;
    }

    public boolean getRotation() {
        return this.rotation;
    }

    public boolean getRotation2() {
        return this.rotation2;
    }

    public float getRotation_Sp() {
        return this.rotation_sp;
    }

    public void setRotation_Sp(float r) {
        this.rotation_sp = r;
    }

    public float getRotation_Sp2() {
        return this.rotation_sp2;
    }

    public float getMaxRotation_Sp() {
        return this.max_rotation_sp;
    }

    public float getMaxRotation_Sp2() {
        return this.max_rotation_sp2;
    }

    public boolean getRotate() {
        return this.rotate;
    }

    public void setRotate(boolean b) {
        this.rotate = b;
    }

    public boolean getRotate2() {
        return this.rotate2;
    }

    public void setRotate2(boolean b) {
        this.rotate2 = b;
    }

    public float getdata39() {
        return this.data39;
    }

    public void setdata39(float f) {
        this.data39 = f;
    }

    public float getdata40() {
        return this.data40;
    }

    public void setdata40(float f) {
        this.data40 = f;
    }

    public float getdata41() {
        return this.data41;
    }

    public void setdata41(float f) {
        this.data41 = f;
    }

    public byte getdata42() {
        return this.data42;
    }

    public void setdata42(int f) {
        this.data42 = (byte)f;
    }

    public float getdata43() {
        return this.data43;
    }

    public void setdata43(float f) {
        this.data43 = f;
    }

    public float getdata44() {
        return this.data44;
    }

    public void setdata44(float f) {
        this.data44 = f;
    }

    public float getdata45() {
        return this.data45;
    }

    public void setdata45(float f) {
        this.data45 = f;
    }

    public byte getdata1() {
        return this.data1;
    }

    public int getdata2() {
        return this.data2;
    }

    public String getdata3() {
        return this.data3;
    }

    public byte getdata4() {
        return this.data4;
    }

    public float getdata5() {
        return this.data5;
    }

    public float getdata6() {
        return this.data6;
    }

    public float getdata7() {
        return this.data7;
    }

    public byte getdata31() {
        return this.data31;
    }

    public float getdata8() {
        return this.data8;
    }

    public float getdata9() {
        return this.data9;
    }

    public float getdata10() {
        return this.data10;
    }

    public float getdata11() {
        return this.data12;
    }

    public float getdata12() {
        return this.data12;
    }

    public float getdata13() {
        return this.data13;
    }

    public float getdata14() {
        return this.data14;
    }

    public float getdata15() {
        return this.data15;
    }

    public float getdata16() {
        return this.data16;
    }

    public byte getdata20() {
        return this.data20;
    }

    public float getdata21() {
        return this.data21;
    }

    public float getdata22() {
        return this.data22;
    }

    public float getdata23() {
        return this.data23;
    }

    public float getdata24() {
        return this.data24;
    }

    public float getdata25() {
        return this.data25;
    }

    public float getdata28() {
        return this.data28;
    }

    public byte getdata32() {
        return this.data32;
    }

    public boolean getdata33() {
        return this.data33;
    }

    public byte getdata34() {
        return this.data34;
    }

    public boolean getdata35() {
        return this.data35;
    }

    public EntityCusPar(String data3, World w, float box1, float box2, double poX, double poY, double poZ, double start_poX, double start_poY, double start_poZ, double moX, double moY, double moZ, float data29, int id, int id_min, int id_max, int data32, boolean rotate, float max_rotation_sp, boolean rotate2, float max_rotation_sp2, int data1, String rr, int data2, int data4, float data5, float data6, float data7, int data31, float data8, float data9, float data10, float data11, float data12, float data13, float data14, float data15, float data16, int data20, float data21, float data22, float data23, float data24, float data25, boolean data33, int data34, boolean data35, Entity ent) {
        super(w);
        this.ent = ent;
        this.func_70105_a(box1, box2);
        this.data17 = start_poX;
        this.data18 = start_poY;
        this.data19 = start_poZ;
        this.field_70165_t = poX + this.data17;
        this.field_70163_u = this.hasEnt() ? poY + this.data18 : poY + this.data18;
        this.field_70161_v = poZ + this.data19;
        this.data36 = 0.0;
        this.data37 = 0.0;
        this.data38 = 0.0;
        this.field_70159_w = moX;
        this.field_70181_x = moY;
        this.field_70179_y = moZ;
        this.data29 = data29;
        this.data30 = 1.0f;
        this.field_70133_I = true;
        this.func_70080_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0f, 0.0f);
        this.id = (byte)id;
        this.id_min = (byte)id_min;
        this.id_max = (byte)id_max;
        this.data32 = (byte)data32;
        this.rotate = rotate;
        this.rotate2 = rotate2;
        this.rotation = false;
        this.rotation2 = false;
        this.rotation_sp = 0.0f;
        this.rotation_sp2 = 0.0f;
        this.max_rotation_sp = 0.0f;
        this.max_rotation_sp2 = 0.0f;
        this.data39 = 0.0f;
        this.data40 = 0.0f;
        this.data41 = 0.0f;
        this.data42 = 0;
        this.data43 = 0.0f;
        this.data44 = 0.0f;
        this.data45 = 0.0f;
        if (rotate) {
            this.rotation = (int)(Math.random() * 2.0) == 0;
            this.max_rotation_sp = max_rotation_sp;
            this.rotation_sp = (float)(Math.random() * (double)max_rotation_sp) + 0.001f;
        }
        if (rotate) {
            this.rotation2 = (int)(Math.random() * 2.0) == 0;
            this.max_rotation_sp2 = max_rotation_sp2;
            this.rotation_sp2 = (float)(Math.random() * (double)max_rotation_sp2) + 0.001f;
        }
        this.rr = rr;
        this.ended = false;
        this.data1 = (byte)data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = (byte)data4;
        this.data5 = data5;
        this.data6 = data6;
        this.data7 = data7;
        float egy = 2.55f;
        this.data31 = (byte)data31;
        this.data8 = data8;
        if (this.data8 > 1.0f) {
            this.data8 = this.data8 / egy / 100.0f;
        }
        this.data9 = data9;
        if (this.data9 > 1.0f) {
            this.data9 = this.data9 / egy / 100.0f;
        }
        this.data10 = data10;
        if (this.data10 > 1.0f) {
            this.data10 = this.data10 / egy / 100.0f;
        }
        this.data11 = data11;
        this.data12 = data12;
        this.data13 = data13;
        this.data14 = data14;
        if (this.data14 > 1.0f) {
            this.data14 = this.data14 / egy / 100.0f;
        }
        this.data15 = data15;
        if (this.data15 > 1.0f) {
            this.data15 = this.data15 / egy / 100.0f;
        }
        this.data16 = data16;
        if (this.data16 > 1.0f) {
            this.data16 = this.data16 / egy / 100.0f;
        }
        this.data20 = (byte)data20;
        this.data21 = data21;
        this.data22 = data22;
        this.data23 = data23;
        this.data24 = data24;
        this.data25 = data25;
        this.data26 = false;
        this.data27 = false;
        this.data28 = data21;
        this.data33 = data33;
        this.data34 = (byte)data34;
        this.data35 = data35;
    }

    public EntityCusPar(String data3, float box1, float box2, int id_min, int id_max, int data32, boolean rotate, float max_rotation_sp, int data1, String rr, int data2, int data4, float data5, float data6, float data7, int data31, float data8, float data9, float data10, float data11, float data12, float data13, float data14, float data15, float data16, int data20, float data21, float data22, float data23, float data24, float data25) {
        super(null);
        this.func_70105_a(box1, box2);
        this.field_70165_t = 0.0;
        this.field_70163_u = 0.0;
        this.field_70161_v = 0.0;
        this.data17 = 0.0;
        this.data18 = 0.0;
        this.data19 = 0.0;
        this.data36 = 0.0;
        this.data37 = 0.0;
        this.data38 = 0.0;
        this.field_70159_w = 0.0;
        this.field_70181_x = 0.0;
        this.field_70179_y = 0.0;
        this.data29 = 0.0f;
        this.data30 = 1.0f;
        this.func_70080_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0f, 0.0f);
        this.id = 0;
        this.id_min = (byte)id_min;
        this.id_max = (byte)id_max;
        this.data32 = (byte)data32;
        this.rotate = rotate;
        this.rotate2 = false;
        this.rotation = false;
        this.rotation2 = false;
        this.rotation_sp = 0.0f;
        this.rotation_sp2 = 0.0f;
        this.max_rotation_sp = 0.0f;
        this.max_rotation_sp2 = 0.0f;
        if (rotate) {
            this.rotation = (int)(Math.random() * 2.0) == 0;
            this.max_rotation_sp = max_rotation_sp;
            this.rotation_sp = (float)(Math.random() * (double)max_rotation_sp) + 0.001f;
        }
        if (rotate) {
            this.rotation2 = (int)(Math.random() * 2.0) == 0;
            this.max_rotation_sp2 = 0.0f;
            this.rotation_sp2 = (float)(Math.random() * (double)this.max_rotation_sp2) + 0.001f;
        }
        this.data39 = 0.0f;
        this.rr = rr;
        this.ended = false;
        this.data1 = (byte)data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = (byte)data4;
        this.data5 = data5;
        this.data6 = data6;
        this.data7 = data7;
        this.data31 = (byte)data31;
        this.data8 = data8;
        this.data9 = data9;
        this.data10 = data10;
        this.data11 = data11;
        this.data12 = data12;
        this.data13 = data13;
        this.data14 = data14;
        this.data15 = data15;
        this.data16 = data16;
        this.data20 = (byte)data20;
        this.data21 = data21;
        this.data22 = data22;
        this.data23 = data23;
        this.data24 = data24;
        this.data25 = data25;
        this.data26 = false;
        this.data27 = false;
        this.data28 = data21;
        this.data33 = false;
        this.data34 = (byte)-1;
        this.data35 = false;
    }

    public void func_70071_h_() {
        if (this.field_70170_p.field_72995_K) {
            if (!JRMCoreClient.mc.func_147113_T() && !this.ended) {
                this.part_atlatszosag();
                if (this.field_70173_aa > 60 && this.data28 == 0.0f) {
                    this.ended = true;
                    this.func_70106_y();
                }
                if (this.data29 != 0.0f) {
                    this.data30 += this.data29;
                    this.field_70159_w *= (double)this.data30;
                    this.field_70181_x *= (double)this.data30;
                    this.field_70179_y *= (double)this.data30;
                }
                this.field_70165_t += this.field_70159_w * (double)JGConfigClientSettings.get_da2();
                this.field_70163_u += this.field_70181_x * (double)JGConfigClientSettings.get_da2();
                this.field_70161_v += this.field_70179_y * (double)JGConfigClientSettings.get_da2();
                this.data36 += this.field_70159_w * (double)JGConfigClientSettings.get_da2();
                this.data37 += this.field_70181_x * (double)JGConfigClientSettings.get_da2();
                this.data38 += this.field_70179_y * (double)JGConfigClientSettings.get_da2();
            }
            if (this.hasEnt()) {
                this.func_70107_b(this.ent.field_70165_t + this.data17 + this.data36, this.ent.field_70163_u + this.data18 + this.data37 + (double)(this.ent instanceof EntityPlayerSP ? -1.6f : 0.0f), this.ent.field_70161_v + this.data19 + this.data38);
            } else {
                this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            }
            if (this.data1 == 0) {
                if (this.hasEnded() || this.field_70173_aa > 10000) {
                    this.func_70106_y();
                }
            } else if (this.data2 <= this.field_70173_aa) {
                this.ended = true;
                this.func_70106_y();
            }
        }
    }

    public void part_atlatszosag() {
        if (!this.data27) {
            if (this.data20 == 1) {
                if (this.data25 > 0.0f) {
                    this.data21 += this.data25;
                    this.data28 += this.data25;
                    if (this.data21 > this.data22) {
                        this.data21 = this.data22;
                        this.data28 = this.data22;
                    }
                } else if (this.data25 < 0.0f) {
                    this.data21 += this.data25;
                    this.data28 += this.data25;
                    if (this.data21 < this.data22) {
                        this.data21 = this.data22;
                        this.data28 = this.data22;
                    }
                }
            } else if (this.data20 == 2) {
                if (this.data25 > 0.0f) {
                    if (!this.data26) {
                        this.data21 += this.data25;
                        if (this.data21 > this.data23) {
                            this.data26 = true;
                        }
                        if (!this.data26) {
                            this.data28 += this.data25;
                        }
                    } else {
                        this.data21 -= this.data25;
                        this.data28 -= this.data25;
                        if (this.data21 < this.data22) {
                            this.data27 = true;
                        }
                    }
                } else if (this.data25 < 0.0f) {
                    if (!this.data26) {
                        this.data21 -= this.data25;
                        if (this.data21 < this.data23) {
                            this.data26 = true;
                        }
                        if (!this.data26) {
                            this.data28 -= this.data25;
                        }
                    } else {
                        this.data21 += this.data25;
                        this.data28 += this.data25;
                        if (this.data21 > this.data22) {
                            this.data27 = true;
                        }
                    }
                }
            } else if (this.data20 == 3) {
                float szaz = this.data2;
                float egy = szaz / 100.0f;
                float calc = (float)this.field_70173_aa / egy / 100.0f;
                if (this.data25 > 0.0f) {
                    this.data21 = calc;
                    this.data28 = calc;
                    if (this.data21 > 1.0f) {
                        this.data21 = 1.0f;
                        this.data28 = 1.0f;
                    }
                } else if (this.data25 < 0.0f) {
                    this.data21 = 1.0f - calc;
                    this.data28 = 1.0f - calc;
                    if (0.0f < this.data22) {
                        this.data21 = 0.0f;
                        this.data28 = 0.0f;
                    }
                }
            }
        }
        if (this.data21 < 0.0f) {
            this.data21 = 0.0f;
        } else if (this.data21 > 1.0f) {
            this.data21 = 1.0f;
        }
        if (this.data28 < 0.0f) {
            this.data28 = 0.0f;
        } else if (this.data28 > 1.0f) {
            this.data28 = 1.0f;
        }
    }

    protected void func_70088_a() {
    }

    protected void func_70037_a(NBTTagCompound p_70037_1_) {
    }

    protected void func_70014_b(NBTTagCompound p_70014_1_) {
    }

    @SideOnly(value=Side.CLIENT)
    public boolean isInRangeToRenderVec3D(Vec3 par1Vec3) {
        return true;
    }

    public boolean func_70112_a(double par1) {
        if (JGConfigClientSettings.renderdistanceMultiplierParticles == 10000) {
            return true;
        }
        double d1 = this.field_70121_D.func_72320_b();
        return par1 < (d1 *= 64.0 * this.field_70155_l) * d1 * ((double)JGConfigClientSettings.renderdistanceMultiplierParticles / 100.0);
    }
}

