/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.entity.EntityCusPar;
import JinRyuu.JRMCore.entity.EntityCusPars;
import JinRyuu.JRMCore.mod_JRMCore;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

@SideOnly(value=Side.CLIENT)
public class EntityAura2
extends Entity {
    public static final int NORMAL = -1;
    public static final int SSJ_GOD = 0;
    public static final int SSJ_BLUE = 1;
    public static final int SSJ_BLUE_EVO = 2;
    public static final int SSJ_ROSE = 3;
    public static final int GOLDEN = 4;
    public static final int SSJ_ROSE_SHINKA = 5;
    public static final int GOD_OF_DESTRUCTION = 6;
    public final int number_of_lightVerts = 10;
    public long[] lightVert = new long[10];
    private int lightLivingTime;
    public int rm;
    private String mot = "";
    private boolean rot = false;
    private int Age;
    private int color = 0xFFFFFF;
    private int colorl2 = 0xFFFFFF;
    private int colorl3 = -1;
    private float state = 0.0f;
    private float state2 = 0.0f;
    private int crel = 0;
    private float yaw = 0.0f;
    private float pitch = 0.0f;
    private float alpha = 0.1f;
    private String tex = "aura";
    private String texl2 = "";
    private String texl3 = "";
    private int speed = 20;
    private boolean inner = true;
    private int rendpass = 1;
    private boolean bol = false;
    private boolean bol2 = false;
    private boolean bol3 = false;
    private boolean bol4 = false;
    private boolean bol4a = false;
    private byte bol6 = (byte)-1;
    private boolean bol7 = false;
    public byte kettleMode = 0;

    public int getLightLivingTime() {
        return this.lightLivingTime;
    }

    public EntityAura2(World par1World) {
        super(par1World);
    }

    public EntityAura2(World par1World, String dbcCharger, int c, float s, float s2, int cr, boolean b, float a) {
        this(par1World, dbcCharger, c, s, s2, cr, b);
        this.alpha = a;
    }

    public EntityAura2(World par1World, String dbcCharger, int c, float s, float s2, int cr, boolean b) {
        this(par1World, dbcCharger, c, s, s2, cr);
        this.rot = b;
    }

    public EntityAura2(World par1World, String dbcCharger, int c, float s, float s2, int cr) {
        super(par1World);
        EntityPlayer other;
        this.mot = dbcCharger;
        this.color = c;
        this.state = s;
        this.state2 = s2;
        this.crel = cr;
        this.rm = this.field_70146_Z.nextInt(10);
        for (int i = 0; i < this.number_of_lightVerts; ++i) {
            this.lightVert[i] = this.field_70146_Z.nextLong();
        }
        this.lightLivingTime = this.field_70146_Z.nextInt(4) + 0;
        if (this.mot.length() > 1 && (other = this.field_70170_p.func_72924_a(this.mot)) != null) {
            if (this.rot) {
                this.yaw = other.field_70177_z;
                this.pitch = other.field_70125_A;
            }
            this.func_70080_a(other.field_70165_t, other.field_70163_u + (double)(other instanceof EntityPlayerSP ? -1.6f : 0.0f), other.field_70161_v, other.field_70177_z, other.field_70125_A);
        }
    }

    public boolean shouldRenderInPass(int pass) {
        return pass == this.rendpass;
    }

    @SideOnly(value=Side.CLIENT)
    public float func_70053_R() {
        return 0.0f;
    }

    public boolean getRot() {
        return this.rot;
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public int getAge() {
        return this.Age;
    }

    public float getState() {
        return this.state;
    }

    public float getState2() {
        return this.state2;
    }

    public float getCRel() {
        return this.crel;
    }

    public int getCol() {
        return this.color;
    }

    public void setCol(int c) {
        this.color = c;
    }

    public int getColL2() {
        return this.colorl2;
    }

    public void setColL2(int c) {
        this.colorl2 = c;
    }

    public int getColL3() {
        return this.colorl3;
    }

    public void setColL3(int c) {
        this.colorl3 = c;
    }

    public float getAlp() {
        return this.alpha;
    }

    public void setAlp(float f) {
        this.alpha = f;
    }

    public String getTex() {
        return this.tex;
    }

    public void setTex(String s) {
        this.tex = s;
    }

    public String getTexL2() {
        return this.texl2;
    }

    public void setTexL2(String s) {
        this.texl2 = s;
    }

    public String getTexL3() {
        return this.texl3;
    }

    public void setTexL3(String s) {
        this.texl3 = s;
    }

    public int getSpd() {
        return this.speed;
    }

    public void setSpd(int s) {
        this.speed = s;
    }

    public boolean getInner() {
        return this.inner;
    }

    public void setInner(boolean s) {
        this.inner = s;
    }

    public void setRendPass(int s) {
        this.rendpass = s;
    }

    public String getmot() {
        return this.mot;
    }

    public void setBol(boolean b) {
        this.bol = b;
    }

    public void setBol2(boolean b) {
        this.bol2 = b;
    }

    public void setBol3(boolean b) {
        this.bol3 = b;
    }

    public void setBol4(boolean b) {
        this.bol4 = b;
    }

    public void setBol4a(boolean b) {
        this.bol4a = b;
    }

    public void setBol6(int b) {
        this.bol6 = (byte)b;
    }

    public void setBol7(boolean b) {
        this.bol7 = b;
    }

    public boolean getBol() {
        return this.bol;
    }

    public boolean getBol2() {
        return this.bol2;
    }

    public boolean getBol3() {
        return this.bol3;
    }

    public boolean getBol4() {
        return this.bol4;
    }

    public boolean getBol4a() {
        return this.bol4a;
    }

    public byte getBol6() {
        return this.bol6;
    }

    public boolean getBol7() {
        return this.bol7;
    }

    public void func_70071_h_() {
        boolean aura_type = JGConfigClientSettings.CLIENT_DA13;
        boolean aura_type2 = JGConfigClientSettings.CLIENT_DA20;
        if (this.mot.length() > 1) {
            EntityPlayer other = this.field_70170_p.func_72924_a(this.mot);
            if (other != null) {
                int k;
                int sneak;
                int n = sneak = other.func_70093_af() ? 0 : 1;
                if (this.kettleMode > 0) {
                    for (k = 0; k < JGConfigClientSettings.get_da1(); ++k) {
                        if (this.field_70173_aa >= 15) continue;
                        EntityPlayer pl = other;
                        double posXOth = pl.field_70165_t;
                        double posYOth = pl.field_70163_u + (double)(other instanceof EntityPlayerSP ? -1.6f : 0.0f);
                        double posZOth = pl.field_70161_v;
                        float red = 250.0f;
                        float green = 250.0f;
                        float blue = 250.0f;
                        float out = 0.9f;
                        float life = 0.8f * other.field_70131_O;
                        float extra_scale = 1.2f;
                        int dea = 50;
                        float outNew = 0.9f;
                        float alpha = 1.0f;
                        float speed = 1.2f;
                        outNew = 0.99f;
                        double y = other.field_70131_O * 0.8f;
                        double x = Math.random() * (double)outNew - (double)(outNew / 2.0f);
                        double z = Math.random() * (double)outNew - (double)(outNew / 2.0f);
                        EntityCusPar entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, Math.random() * (double)0.3f - (double)0.15f, 0.25 + Math.random() * 0.125 * (double)speed, Math.random() * (double)0.3f - (double)0.15f, -0.02f * speed, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, 0.05f + ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 1.2f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 1.2f / 2.0f, 0.3f * life * 1.2f, 0, red, green, blue, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.1f, 0.0f, 0.4f * alpha, 0.45f * alpha, 0.02f * alpha, false, -1, false, null);
                        entity.field_70170_p.func_72838_d((Entity)entity);
                    }
                }
                if (this.kettleMode != 1 && aura_type) {
                    for (k = 0; k < JGConfigClientSettings.get_da1(); ++k) {
                        int i = 0;
                        while ((float)i < (float)((int)other.field_70131_O) / 2.0f + 1.0f) {
                            if (this.field_70173_aa < 5) {
                                float out;
                                double z;
                                double x;
                                double y;
                                float blue2;
                                float green2;
                                float red2;
                                float blue;
                                float red;
                                EntityPlayer pl = other;
                                double posXOth = pl.field_70165_t;
                                double posYOth = pl.field_70163_u + (double)(other instanceof EntityPlayerSP ? -1.6f : 0.0f);
                                double posZOth = pl.field_70161_v;
                                if (this.bol4) {
                                    EntityCusPar entity;
                                    int repeat2;
                                    float blue3;
                                    float green3;
                                    float red3;
                                    float green;
                                    if (this.bol4a) {
                                        red = 87.0f;
                                        green = 200.0f;
                                        blue = 208.0f;
                                        red2 = 203.0f;
                                        green2 = 137.0f;
                                        blue2 = 234.0f;
                                        red3 = 245.0f;
                                        green3 = 250.0f;
                                        blue3 = 252.0f;
                                    } else {
                                        red = 141.0f;
                                        green = 158.0f;
                                        blue = 210.0f;
                                        red2 = 215.0f;
                                        green2 = 152.0f;
                                        blue2 = 219.0f;
                                        red3 = 243.0f;
                                        green3 = 247.0f;
                                        blue3 = 250.0f;
                                    }
                                    float out2 = 1.6f;
                                    float in = 1.5f;
                                    float life = 0.8f * other.field_70131_O;
                                    float extra_scale = 0.5f;
                                    int dea = 50;
                                    float outNew = 1.6f;
                                    float target_fullsize_one1 = 0.32f;
                                    float targetsizeMin = other.field_70131_O * (8.0f / target_fullsize_one1) * 0.01f;
                                    float target_fullsize_one2 = 0.32f;
                                    float targetsizeMax = other.field_70131_O * (26.0f / target_fullsize_one2) * 0.01f;
                                    float alpha = 0.7f;
                                    for (repeat2 = 0; repeat2 < 4; ++repeat2) {
                                        outNew = 1.7600001f;
                                        y = (Math.random() * (double)other.field_70131_O - 0.5) * (double)0.8f;
                                        x = Math.random() * (double)outNew - (double)(outNew / 2.0f);
                                        z = Math.random() * (double)outNew - (double)(outNew / 2.0f);
                                        entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 150.0f, 186.0f, 255.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.3f * alpha, 0.35f * alpha, 0.01f * alpha, false, -1, true, (Entity)pl);
                                        entity.field_70170_p.func_72838_d((Entity)entity);
                                    }
                                    for (repeat2 = 0; repeat2 < 4; ++repeat2) {
                                        outNew = 1.7600001f;
                                        y = (Math.random() * (double)other.field_70131_O - 0.5) * (double)0.8f;
                                        x = Math.random() * (double)outNew - (double)(outNew / 2.0f);
                                        z = Math.random() * (double)outNew - (double)(outNew / 2.0f);
                                        entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, red, green, blue, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.3f * alpha, 0.35f * alpha, 0.01f * alpha, false, -1, true, (Entity)pl);
                                        entity.field_70170_p.func_72838_d((Entity)entity);
                                    }
                                    if (this.field_70173_aa % 4 == 0) {
                                        x = Math.random() * (double)outNew - (double)(outNew / 2.0f);
                                        y = Math.random() * (double)other.field_70131_O - 0.5;
                                        z = Math.random() * (double)outNew - (double)(outNew / 2.0f);
                                        EntityCusPar entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f * 0.5f, 0.2f * life * 0.5f * 0.5f, 0, red, green, blue, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f * alpha, 0.45f * alpha, 0.015f * alpha, false, -1, true, (Entity)pl);
                                        entity2.field_70170_p.func_72838_d((Entity)entity2);
                                    }
                                    if (this.bol4) {
                                        if (this.bol4a) {
                                            outNew = 1.8000001f;
                                            y = (Math.random() * (double)other.field_70131_O - 0.5) * (double)0.8f;
                                            x = Math.random() * (double)outNew - (double)(outNew / 2.0f);
                                            z = Math.random() * (double)outNew - (double)(outNew / 2.0f);
                                            EntityCusPar entity2 = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, red2, green2, blue2, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f * alpha, 0.45f * alpha, 0.015f * alpha, false, -1, true, (Entity)pl);
                                            entity2.field_70170_p.func_72838_d((Entity)entity2);
                                            for (int repeat22 = 0; repeat22 < 1 + (this.bol4a ? 1 : 0); ++repeat22) {
                                                y = Math.random() * (double)(targetsizeMax - targetsizeMin) + (double)targetsizeMin;
                                                outNew = 1.9499999f;
                                                x = Math.random() * (double)outNew - (double)(outNew / 2.0f);
                                                z = Math.random() * (double)outNew - (double)(outNew / 2.0f);
                                                EntityCusPar entity1 = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y -= (double)0.3f, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, red2, green2, blue2, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f * alpha, 0.45f * alpha, 0.015f * alpha, false, -1, true, (Entity)pl);
                                                entity1.field_70170_p.func_72838_d((Entity)entity1);
                                            }
                                        }
                                        if (this.field_70173_aa % (this.bol4a ? 1 : 4) == 0) {
                                            x = Math.random() * 1.5 - 0.75;
                                            y = (Math.random() * (double)other.field_70131_O - 0.5) * (double)0.8f;
                                            z = Math.random() * 1.5 - 0.75;
                                            EntityCusPar entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.02f) + 0.02f) * life * 0.5f * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f * 0.5f, 0.2f * life * 0.5f * 0.5f, 0, red2, green2, blue2, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f * (this.bol4a ? 1.2f : 1.0f) * alpha, 0.45f * (this.bol4a ? 1.2f : 1.0f) * alpha, 0.015f * (this.bol4a ? 1.2f : 1.0f) * alpha, false, -1, true, (Entity)pl);
                                            entity2.field_70170_p.func_72838_d((Entity)entity2);
                                        }
                                    }
                                    float in2 = 0.6f;
                                    for (int repeat = 0; repeat < 3; ++repeat) {
                                        outNew = 0.6f;
                                        x = Math.random() * (double)outNew - (double)(outNew / 2.0f);
                                        y = (Math.random() * (double)other.field_70131_O - 0.5) * (double)0.8f * (double)0.6f - (double)0.3f;
                                        z = Math.random() * (double)outNew - (double)(outNew / 2.0f);
                                        EntityCusPar entity3 = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.04f + 0.01, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 243.0f, 247.0f, 250.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.2f * alpha, 0.25f * alpha, 0.005f * alpha, false, -1, true, (Entity)pl);
                                        entity3.field_70170_p.func_72838_d((Entity)entity3);
                                        for (int repeat23 = 0; repeat23 < 2; ++repeat23) {
                                            y = Math.random() * (double)(targetsizeMax - targetsizeMin) + (double)targetsizeMin;
                                            outNew = 1.26f;
                                            x = Math.random() * (double)outNew - (double)(outNew / 2.0f);
                                            z = Math.random() * (double)outNew - (double)(outNew / 2.0f);
                                            EntityCusPar entity1 = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y -= (double)0.3f, z, 0.0, Math.random() * (double)0.04f + 0.01, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 32, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, red3, green3, blue3, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.3f * alpha, 0.35f * alpha, 0.02f * alpha, false, -1, true, (Entity)pl);
                                            entity1.field_70170_p.func_72838_d((Entity)entity1);
                                        }
                                    }
                                    if (this.field_70173_aa % 4 == 0) {
                                        x = Math.random() * (double)0.6f - (double)0.3f;
                                        y = (Math.random() * (double)other.field_70131_O - 0.5) * (double)0.8f;
                                        z = Math.random() * (double)0.6f - (double)0.3f;
                                        EntityCusPar entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.03f + 0.01, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f * 0.5f, 0.2f * life * 0.5f * 0.5f, 0, red3, green3, blue3, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f * alpha, 0.45f * alpha, 0.015f * alpha, false, -1, true, (Entity)pl);
                                        entity2.field_70170_p.func_72838_d((Entity)entity2);
                                        y = Math.random() * (double)(targetsizeMax - targetsizeMin) + (double)targetsizeMin;
                                        outNew = 1.26f;
                                        x = Math.random() * (double)outNew - (double)(outNew / 2.0f);
                                        z = Math.random() * (double)outNew - (double)(outNew / 2.0f);
                                        EntityCusPar entity3 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y -= (double)0.3f, z, 0.0, Math.random() * (double)0.03f + 0.01, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.02f) + 0.02f) * life * 0.5f * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f * 0.5f, 0.2f * life * 0.5f * 0.5f, 0, red3, green3, blue3, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f * alpha, 0.45f * alpha, 0.015f * alpha, false, -1, true, (Entity)pl);
                                        entity3.field_70170_p.func_72838_d((Entity)entity3);
                                    }
                                } else if (this.bol6 == -1 && !aura_type2) {
                                    float a = this.alpha;
                                    float h1 = 1.0f;
                                    float h2 = (float)(this.color >> 16 & 0xFF) / 255.0f;
                                    float h3 = (float)(this.color >> 8 & 0xFF) / 255.0f;
                                    float h4 = (float)(this.color & 0xFF) / 255.0f;
                                    float red3 = h1 * h2;
                                    float green = h1 * h3;
                                    float blue3 = h1 * h4;
                                    if (red3 > 1.0f) {
                                        red3 = 1.0f;
                                    }
                                    if (green > 1.0f) {
                                        green = 1.0f;
                                    }
                                    if (blue3 > 1.0f) {
                                        blue3 = 1.0f;
                                    }
                                    for (int gh2 = 0; gh2 < 3; ++gh2) {
                                        float life = 0.8f * pl.field_70131_O;
                                        float extra_scale = 1.0f + (pl.field_70131_O > 2.1f ? pl.field_70131_O / 2.0f : 0.0f) / 5.0f;
                                        float width = pl.field_70130_N * 3.0f;
                                        x = (Math.random() * 1.0 - 0.5) * (double)(width * 1.3f);
                                        y = Math.random() * (double)(other.field_70131_O * 1.4f) - (double)(other.field_70131_O / 2.0f) - (double)0.3f;
                                        z = (Math.random() * 1.0 - 0.5) * (double)(width * 1.3f);
                                        double motx = Math.random() * (double)0.02f - (double)0.01f;
                                        double moty = (Math.random() * (double)0.9f + (double)0.9f) * ((double)(life * extra_scale) * 0.07);
                                        double motz = Math.random() * (double)0.02f - (double)0.01f;
                                        EntityCusPar entity = new EntityCusPar("jinryuumodscore:bens_particles.png", pl.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, motx, moty, motz, 0.0f, (int)(Math.random() * 3.0) + 32, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", (int)(30.0f * life * 0.5f), 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * extra_scale, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * extra_scale, 0.2f * life * extra_scale, 0, red3, green, blue3, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.08f, false, -1, true, (Entity)pl);
                                        pl.field_70170_p.func_72838_d((Entity)entity);
                                        EntityCusPar entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", pl.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, motx, moty, motz, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", (int)(30.0f * life * 0.5f), 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * extra_scale, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * extra_scale, 0.1f * life * extra_scale, 0, red3, green, blue3, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.08f, false, -1, true, (Entity)pl);
                                        pl.field_70170_p.func_72838_d((Entity)entity2);
                                        if (this.getColL3() <= 0) continue;
                                        life *= 0.8f;
                                        x = (Math.random() * 1.0 - 0.5) * (double)((width *= 0.8f) * 0.8f);
                                        y = -1.0;
                                        z = (Math.random() * 1.0 - 0.5) * (double)(width * 0.8f);
                                        h2 = (float)(this.colorl3 >> 16 & 0xFF) / 255.0f;
                                        h3 = (float)(this.colorl3 >> 8 & 0xFF) / 255.0f;
                                        h4 = (float)(this.colorl3 & 0xFF) / 255.0f;
                                        red3 = h1 * h2;
                                        green = h1 * h3;
                                        blue3 = h1 * h4;
                                        if (red3 > 1.0f) {
                                            red3 = 1.0f;
                                        }
                                        if (green > 1.0f) {
                                            green = 1.0f;
                                        }
                                        if (blue3 > 1.0f) {
                                            blue3 = 1.0f;
                                        }
                                        moty = (Math.random() * (double)0.1f + (double)0.1f) * ((double)(life *= 0.95f) * 0.57);
                                        entity = new EntityCusPar("jinryuumodscore:bens_particles.png", pl.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, motx, moty, motz, 0.0f, (int)(Math.random() * 3.0) + 32, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", (int)(30.0f * life * 1.6f), 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * extra_scale, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * extra_scale, 0.1f * life * extra_scale, 0, red3, green, blue3, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2, 0.0f, 0.0f, 0.3f, 0.35f, 0.02f, false, -1, true, (Entity)pl);
                                        pl.field_70170_p.func_72838_d((Entity)entity);
                                        entity = new EntityCusPar("jinryuudragonbc:bens_particles.png", pl.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, motx, moty, motz, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", (int)(30.0f * life * 1.6f), 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * extra_scale, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * extra_scale, 0.1f * life * extra_scale, 0, red3, green, blue3, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2, 0.0f, 0.0f, 0.3f, 0.35f, 0.02f, false, -1, true, (Entity)pl);
                                        pl.field_70170_p.func_72838_d((Entity)entity);
                                    }
                                } else if (this.bol6 == 6) {
                                    float width = 1.25f;
                                    for (int j = 0; j < 2; ++j) {
                                        x = Math.random() * 1.25 - 0.625;
                                        y = Math.random() * (double)this.field_70131_O - 0.5;
                                        z = Math.random() * 1.25 - 0.625;
                                        EntityCusPar entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 30, 2, (float)(Math.random() * (double)0.03f), (float)(Math.random() * (double)0.03f) + 0.05f, 0.1f, 2, 168.0f, 50.0f, 214.0f, 0.0f, 0.0f, 0.0f, 175.0f, 55.0f, 228.0f, 3, 0.5f, 0.0f, 0.0f, 0.0f, -0.1f, false, -1, true, (Entity)pl);
                                        entity.field_70170_p.func_72838_d((Entity)entity);
                                        x = Math.random() * 1.0 - 0.5;
                                        y = Math.random() * (double)this.field_70131_O - 0.5;
                                        z = Math.random() * 1.0 - 0.5;
                                        entity = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 30, 2, (float)(Math.random() * (double)0.03f), (float)(Math.random() * (double)0.03f) + 0.05f, 0.1f, 2, 168.0f, 50.0f, 214.0f, 0.0f, 0.0f, 0.0f, 175.0f, 55.0f, 228.0f, 3, 0.5f, 0.0f, 0.0f, 0.0f, -0.1f, false, -1, true, (Entity)pl);
                                        entity.field_70170_p.func_72838_d((Entity)entity);
                                    }
                                } else if (this.bol6 == 0) {
                                    red = 215.0f;
                                    float green = 107.0f;
                                    blue = 61.0f;
                                    red2 = 218.0f;
                                    green2 = 209.0f;
                                    blue2 = 71.0f;
                                    out = 1.6f;
                                    float in = 1.5f;
                                    float life = 0.8f * other.field_70131_O;
                                    float extra_scale = 0.5f;
                                    int dea = 50;
                                    x = Math.random() * (double)1.6f - (double)0.8f;
                                    y = Math.random() * (double)other.field_70131_O - 0.5;
                                    z = Math.random() * (double)1.6f - (double)0.8f;
                                    EntityCusPar entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 215.0f, 107.0f, 61.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    x = Math.random() * (double)1.6f - (double)0.8f;
                                    y = Math.random() * (double)other.field_70131_O - 0.5;
                                    z = Math.random() * (double)1.6f - (double)0.8f;
                                    entity.field_70170_p.func_72838_d((Entity)entity);
                                    EntityCusPar entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 215.0f, 107.0f, 61.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    entity.field_70170_p.func_72838_d((Entity)entity2);
                                    x = Math.random() * 1.5 - 0.75;
                                    y = (Math.random() * (double)other.field_70131_O - 0.5) * (double)0.8f;
                                    z = Math.random() * 1.5 - 0.75;
                                    entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 218.0f, 209.0f, 71.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    x = Math.random() * 1.5 - 0.75;
                                    y = (Math.random() * (double)other.field_70131_O - 0.5) * (double)0.8f;
                                    z = Math.random() * 1.5 - 0.75;
                                    entity.field_70170_p.func_72838_d((Entity)entity);
                                    entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 218.0f, 209.0f, 71.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    entity.field_70170_p.func_72838_d((Entity)entity2);
                                } else if (this.bol6 == 1) {
                                    red = 48.0f;
                                    float green = 208.0f;
                                    blue = 232.0f;
                                    float out3 = 1.6f;
                                    float in = 1.0f;
                                    float life = 0.8f * other.field_70131_O;
                                    float extra_scale = 0.5f;
                                    int dea = 50;
                                    for (int gh3 = 0; gh3 < 2; ++gh3) {
                                        x = Math.random() * (double)1.6f - (double)0.8f;
                                        y = Math.random() * (double)other.field_70131_O - 0.5;
                                        z = Math.random() * (double)1.6f - (double)0.8f;
                                        EntityCusPar entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 48.0f, 208.0f, 232.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                        x = Math.random() * (double)1.6f - (double)0.8f;
                                        y = Math.random() * (double)other.field_70131_O - 0.5;
                                        z = Math.random() * (double)1.6f - (double)0.8f;
                                        entity.field_70170_p.func_72838_d((Entity)entity);
                                        EntityCusPar entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 48.0f, 208.0f, 232.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                        entity.field_70170_p.func_72838_d((Entity)entity2);
                                    }
                                    x = Math.random() * 1.0 - 0.5;
                                    y = (Math.random() * (double)other.field_70131_O - 0.5) * (double)0.8f;
                                    z = Math.random() * 1.0 - 0.5;
                                    EntityCusPar entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 160.0f, 220.0f, 255.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    x = Math.random() * 1.0 - 0.5;
                                    y = (Math.random() * (double)other.field_70131_O - 0.5) * (double)0.8f;
                                    z = Math.random() * 1.0 - 0.5;
                                    entity.field_70170_p.func_72838_d((Entity)entity);
                                    EntityCusPar entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 160.0f, 220.0f, 255.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    entity.field_70170_p.func_72838_d((Entity)entity2);
                                } else if (this.bol6 == 2) {
                                    red = 80.0f;
                                    float green = 179.0f;
                                    blue = 215.0f;
                                    red2 = 46.0f;
                                    green2 = 46.0f;
                                    blue2 = 211.0f;
                                    float in = 1.0f;
                                    float out4 = 1.6f;
                                    float life = 0.8f * other.field_70131_O;
                                    float extra_scale = 0.5f;
                                    int dea = 50;
                                    for (int gh4 = 0; gh4 < 2; ++gh4) {
                                        x = Math.random() * (double)out4 - (double)(out4 / 2.0f);
                                        y = Math.random() * (double)other.field_70131_O - 0.5;
                                        z = Math.random() * (double)out4 - (double)(out4 / 2.0f);
                                        EntityCusPar entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 80.0f, 179.0f, 215.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                        x = Math.random() * (double)out4 - (double)(out4 / 2.0f);
                                        y = Math.random() * (double)other.field_70131_O - 0.5;
                                        z = Math.random() * (double)out4 - (double)(out4 / 2.0f);
                                        entity.field_70170_p.func_72838_d((Entity)entity);
                                        EntityCusPar entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 80.0f, 179.0f, 215.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                        entity.field_70170_p.func_72838_d((Entity)entity2);
                                    }
                                    x = Math.random() * (double)(out4 *= 1.4f) - (double)(out4 / 2.0f);
                                    y = Math.random() * (double)other.field_70131_O - 0.5;
                                    z = Math.random() * (double)out4 - (double)(out4 / 2.0f);
                                    EntityCusPar entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 46.0f, 46.0f, 211.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    x = Math.random() * (double)out4 - (double)(out4 / 2.0f);
                                    y = Math.random() * (double)other.field_70131_O - 0.5;
                                    z = Math.random() * (double)out4 - (double)(out4 / 2.0f);
                                    entity.field_70170_p.func_72838_d((Entity)entity);
                                    EntityCusPar entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 46.0f, 46.0f, 211.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    entity.field_70170_p.func_72838_d((Entity)entity2);
                                    x = Math.random() * 1.0 - 0.5;
                                    y = (Math.random() * (double)other.field_70131_O - 0.5) * (double)0.8f;
                                    z = Math.random() * 1.0 - 0.5;
                                    entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 160.0f, 220.0f, 255.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    x = Math.random() * 1.0 - 0.5;
                                    y = (Math.random() * (double)other.field_70131_O - 0.5) * (double)0.8f;
                                    z = Math.random() * 1.0 - 0.5;
                                    entity.field_70170_p.func_72838_d((Entity)entity);
                                    entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 160.0f, 220.0f, 255.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    entity.field_70170_p.func_72838_d((Entity)entity2);
                                } else if (this.bol6 == 3 && this.field_70173_aa < 3) {
                                    red = 186.0f;
                                    float green = 37.0f;
                                    blue = 197.0f;
                                    red2 = 140.0f;
                                    green2 = 8.0f;
                                    blue2 = 62.0f;
                                    out = 1.6f;
                                    float in = 1.0f;
                                    float life = 0.8f * other.field_70131_O;
                                    float extra_scale = 0.5f;
                                    int dea = 50;
                                    for (int gh5 = 0; gh5 < 2; ++gh5) {
                                        x = Math.random() * (double)out - (double)(out / 2.0f);
                                        y = Math.random() * (double)other.field_70131_O - 0.5;
                                        z = Math.random() * (double)out - (double)(out / 2.0f);
                                        EntityCusPar entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 186.0f, 37.0f, 197.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                        x = Math.random() * (double)out - (double)(out / 2.0f);
                                        y = Math.random() * (double)other.field_70131_O - 0.5;
                                        z = Math.random() * (double)out - (double)(out / 2.0f);
                                        entity.field_70170_p.func_72838_d((Entity)entity);
                                        EntityCusPar entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 186.0f, 37.0f, 197.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                        entity.field_70170_p.func_72838_d((Entity)entity2);
                                    }
                                    x = Math.random() * (double)(out *= 1.4f) - (double)(out / 2.0f);
                                    y = Math.random() * (double)other.field_70131_O - 0.5;
                                    z = Math.random() * (double)out - (double)(out / 2.0f);
                                    EntityCusPar entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 140.0f, 8.0f, 62.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    x = Math.random() * (double)out - (double)(out / 2.0f);
                                    y = Math.random() * (double)other.field_70131_O - 0.5;
                                    z = Math.random() * (double)out - (double)(out / 2.0f);
                                    entity.field_70170_p.func_72838_d((Entity)entity);
                                    EntityCusPar entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 140.0f, 8.0f, 62.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    entity.field_70170_p.func_72838_d((Entity)entity2);
                                    x = Math.random() * (double)in - (double)(in / 2.0f);
                                    y = (Math.random() * (double)other.field_70131_O - 0.5) * (double)0.8f;
                                    z = Math.random() * (double)in - (double)(in / 2.0f);
                                    entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 184.0f, 147.0f, 241.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    x = Math.random() * (double)in - (double)(in / 2.0f);
                                    y = (Math.random() * (double)other.field_70131_O - 0.5) * (double)0.8f;
                                    z = Math.random() * (double)in - (double)(in / 2.0f);
                                    entity.field_70170_p.func_72838_d((Entity)entity);
                                    entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 184.0f, 147.0f, 241.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    entity.field_70170_p.func_72838_d((Entity)entity2);
                                    x = Math.random() * (double)(in *= 1.2f) - (double)(in / 2.0f);
                                    y = (Math.random() * (double)other.field_70131_O - 0.5) * (double)0.8f;
                                    z = Math.random() * (double)in - (double)(in / 2.0f);
                                    entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 93.0f, 3.0f, 177.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    x = Math.random() * (double)in - (double)(in / 2.0f);
                                    y = (Math.random() * (double)other.field_70131_O - 0.5) * (double)0.8f;
                                    z = Math.random() * (double)in - (double)(in / 2.0f);
                                    entity.field_70170_p.func_72838_d((Entity)entity);
                                    entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 93.0f, 3.0f, 177.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    entity.field_70170_p.func_72838_d((Entity)entity2);
                                } else if (this.bol6 == 5 && this.field_70173_aa < 3) {
                                    red = 191.0f;
                                    float green = 5.0f;
                                    blue = 184.0f;
                                    red2 = 90.0f;
                                    green2 = 19.0f;
                                    blue2 = 2.0f;
                                    out = 1.6f;
                                    float in = 1.0f;
                                    float life = 0.8f * other.field_70131_O;
                                    float extra_scale = 0.5f;
                                    int dea = 50;
                                    for (int gh6 = 0; gh6 < 2; ++gh6) {
                                        x = Math.random() * (double)out - (double)(out / 2.0f);
                                        y = Math.random() * (double)other.field_70131_O - 0.5;
                                        z = Math.random() * (double)out - (double)(out / 2.0f);
                                        EntityCusPar entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 191.0f, 5.0f, 184.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                        x = Math.random() * (double)out - (double)(out / 2.0f);
                                        y = Math.random() * (double)other.field_70131_O - 0.5;
                                        z = Math.random() * (double)out - (double)(out / 2.0f);
                                        entity.field_70170_p.func_72838_d((Entity)entity);
                                        EntityCusPar entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 191.0f, 5.0f, 184.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                        entity.field_70170_p.func_72838_d((Entity)entity2);
                                    }
                                    x = Math.random() * (double)(out *= 1.4f) - (double)(out / 2.0f);
                                    y = Math.random() * (double)other.field_70131_O - 0.5;
                                    z = Math.random() * (double)out - (double)(out / 2.0f);
                                    EntityCusPar entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 90.0f, 19.0f, 2.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    x = Math.random() * (double)out - (double)(out / 2.0f);
                                    y = Math.random() * (double)other.field_70131_O - 0.5;
                                    z = Math.random() * (double)out - (double)(out / 2.0f);
                                    entity.field_70170_p.func_72838_d((Entity)entity);
                                    EntityCusPar entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 90.0f, 19.0f, 2.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    entity.field_70170_p.func_72838_d((Entity)entity2);
                                    x = Math.random() * (double)in - (double)(in / 2.0f);
                                    y = (Math.random() * (double)other.field_70131_O - 0.5) * (double)0.8f;
                                    z = Math.random() * (double)in - (double)(in / 2.0f);
                                    entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 215.0f, 154.0f, 251.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    x = Math.random() * (double)in - (double)(in / 2.0f);
                                    y = (Math.random() * (double)other.field_70131_O - 0.5) * (double)0.8f;
                                    z = Math.random() * (double)in - (double)(in / 2.0f);
                                    entity.field_70170_p.func_72838_d((Entity)entity);
                                    entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 215.0f, 154.0f, 251.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    entity.field_70170_p.func_72838_d((Entity)entity2);
                                    x = Math.random() * (double)(in *= 1.2f) - (double)(in / 2.0f);
                                    y = (Math.random() * (double)other.field_70131_O - 0.5) * (double)0.8f;
                                    z = Math.random() * (double)in - (double)(in / 2.0f);
                                    entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 158.0f, 0.0f, 216.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    x = Math.random() * (double)in - (double)(in / 2.0f);
                                    y = (Math.random() * (double)other.field_70131_O - 0.5) * (double)0.8f;
                                    z = Math.random() * (double)in - (double)(in / 2.0f);
                                    entity.field_70170_p.func_72838_d((Entity)entity);
                                    entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 158.0f, 0.0f, 216.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    entity.field_70170_p.func_72838_d((Entity)entity2);
                                } else if (this.bol6 == 4) {
                                    red = 249.0f;
                                    float green = 212.0f;
                                    blue = 33.0f;
                                    red2 = 234.0f;
                                    green2 = 134.0f;
                                    blue2 = 34.0f;
                                    out = 1.6f;
                                    float in = 1.0f;
                                    float life = 0.8f * other.field_70131_O;
                                    float extra_scale = 0.5f;
                                    int dea = 50;
                                    x = Math.random() * (double)out - (double)(out / 2.0f);
                                    y = Math.random() * (double)other.field_70131_O - 0.5;
                                    z = Math.random() * (double)out - (double)(out / 2.0f);
                                    EntityCusPar entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 249.0f, 212.0f, 33.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    x = Math.random() * (double)out - (double)(out / 2.0f);
                                    y = Math.random() * (double)other.field_70131_O - 0.5;
                                    z = Math.random() * (double)out - (double)(out / 2.0f);
                                    entity.field_70170_p.func_72838_d((Entity)entity);
                                    EntityCusPar entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 249.0f, 212.0f, 33.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    entity.field_70170_p.func_72838_d((Entity)entity2);
                                    x = Math.random() * (double)(out *= 1.3f) - (double)(out / 2.0f);
                                    y = Math.random() * (double)other.field_70131_O - 0.5;
                                    z = Math.random() * (double)out - (double)(out / 2.0f);
                                    entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 234.0f, 134.0f, 34.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    x = Math.random() * (double)out - (double)(out / 2.0f);
                                    y = Math.random() * (double)other.field_70131_O - 0.5;
                                    z = Math.random() * (double)out - (double)(out / 2.0f);
                                    entity.field_70170_p.func_72838_d((Entity)entity);
                                    entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 234.0f, 134.0f, 34.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    entity.field_70170_p.func_72838_d((Entity)entity2);
                                    x = Math.random() * 1.0 - 0.5;
                                    y = (Math.random() * (double)other.field_70131_O - 0.5) * (double)0.8f;
                                    z = Math.random() * 1.0 - 0.5;
                                    entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 255.0f, 255.0f, 208.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    x = Math.random() * 1.0 - 0.5;
                                    y = (Math.random() * (double)other.field_70131_O - 0.5) * (double)0.8f;
                                    z = Math.random() * 1.0 - 0.5;
                                    entity.field_70170_p.func_72838_d((Entity)entity);
                                    entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 255.0f, 255.0f, 208.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                                    entity.field_70170_p.func_72838_d((Entity)entity2);
                                }
                            }
                            ++i;
                        }
                    }
                }
                if (JGConfigClientSettings.CLIENT_GR0) {
                    for (k = 0; k < JGConfigClientSettings.get_da1(); ++k) {
                        EntityCusPar entity;
                        double z;
                        double y;
                        double x;
                        EntityAura2 pl;
                        float blue;
                        float green;
                        float red;
                        float h4;
                        float h3;
                        float h2;
                        float spe2;
                        float h1;
                        float a;
                        if (this.kettleMode == 1 || !this.texl2.equals("aurai2") && !this.texl3.equals("auragb") || this.bol6 == 6) continue;
                        if (!this.bol4) {
                            if (this.field_70173_aa % 2 != 0) continue;
                            a = this.alpha;
                            h1 = 1.0f;
                            spe2 = 1.3f;
                            h2 = (float)(this.color >> 16 & 0xFF) / 255.0f;
                            h3 = (float)(this.color >> 8 & 0xFF) / 255.0f;
                            h4 = (float)(this.color & 0xFF) / 255.0f;
                            red = h1 * h2 + 0.6f;
                            green = h1 * h3 + 0.6f;
                            blue = h1 * h4 + 0.6f;
                            if (red > 1.0f) {
                                red = 1.0f;
                            }
                            if (green > 1.0f) {
                                green = 1.0f;
                            }
                            if (blue > 1.0f) {
                                blue = 1.0f;
                            }
                            pl = this;
                            x = Math.random() * (double)spe2 - (double)(spe2 / 2.0f);
                            y = -0.3f;
                            z = Math.random() * (double)spe2 - (double)(spe2 / 2.0f);
                            entity = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, x, y, z, 0.0, 0.05 + Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 48, 48, 8, 32, false, 0.0f, false, 0.0f, 1, "", 35, 0, 0.003f + (float)(Math.random() * (double)0.006f), 0.0f, 0.0f, 0, red, green, blue, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2, 0.8f, 0.0f, 0.9f, 0.95f, 0.018f, false, -1, true, (Entity)other);
                            entity.field_70170_p.func_72838_d((Entity)entity);
                            continue;
                        }
                        if (this.field_70173_aa % 10 != 0) continue;
                        a = this.alpha;
                        h1 = 1.0f;
                        spe2 = 1.3f;
                        h2 = (float)(this.color >> 16 & 0xFF) / 255.0f;
                        h3 = (float)(this.color >> 8 & 0xFF) / 255.0f;
                        h4 = (float)(this.color & 0xFF) / 255.0f;
                        red = h1 * h2 + 0.6f;
                        green = h1 * h3 + 0.6f;
                        blue = h1 * h4 + 0.6f;
                        if (red > 1.0f) {
                            red = 1.0f;
                        }
                        if (green > 1.0f) {
                            green = 1.0f;
                        }
                        if (blue > 1.0f) {
                            blue = 1.0f;
                        }
                        pl = this;
                        x = Math.random() * (double)spe2 - (double)(spe2 / 2.0f);
                        y = -0.3f;
                        z = Math.random() * (double)spe2 - (double)(spe2 / 2.0f);
                        entity = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, x, y, z, 0.0, 0.05 + Math.random() * (double)0.1f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 48, 48, 8, 32, false, 0.0f, false, 0.0f, 1, "", 20, 0, 0.003f + (float)(Math.random() * (double)0.006f), 0.0f, 0.0f, 0, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2, 0.8f, 0.0f, 0.9f, 0.95f, 0.05f, false, -1, true, (Entity)other);
                        entity.field_70170_p.func_72838_d((Entity)entity);
                    }
                }
                if (this.kettleMode != 1 && JGConfigClientSettings.CLIENT_GR7) {
                    for (k = 0; k < JGConfigClientSettings.get_da1(); ++k) {
                        if (!this.texl2.equals("aurai2") && !this.texl3.equals("auragb") || !this.bol4 || this.field_70173_aa % 10 != 0) continue;
                        EntityPlayer pl = other;
                        double x = Math.random() * 1.0 - 0.5;
                        double y = Math.random() * (double)other.field_70131_O - 0.5;
                        double z = Math.random() * 1.0 - 0.5;
                        EntityCusPar entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 100, 2, (float)(Math.random() * (double)0.05f), (float)(Math.random() * (double)0.08f) + 0.1f, 0.1f, 2, 105.0f, 40.0f, 148.0f, 0.0f, 0.0f, 0.0f, 105.0f, 40.0f, 148.0f, 1, 0.5f, 0.0f, 0.0f, 0.0f, -0.01f, false, -1, true, (Entity)other);
                        entity.field_70170_p.func_72838_d((Entity)entity);
                        x = Math.random() * 1.0 - 0.5;
                        y = Math.random() * (double)other.field_70131_O - 0.5;
                        z = Math.random() * 1.0 - 0.5;
                        double motion = Math.random() * (double)0.03f + (double)0.001f;
                        int image = (int)(Math.random() * 8.0) + 32;
                        float sizem = (float)(Math.random() * (double)0.03f);
                        float sizemm = (float)(Math.random() * (double)0.03f) + 0.05f;
                        EntityCusPar entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, x, y, z, 0.0, motion, 0.0, 0.0f, image, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 100, 2, sizem, sizemm, 0.1f, 0, 80.0f, 156.0f, 186.0f, 0.0f, 0.0f, 0.0f, 80.0f, 156.0f, 186.0f, 1, 0.8f, 0.0f, 0.0f, 0.0f, -0.01f, false, -1, true, (Entity)other);
                        entity2.field_70170_p.func_72838_d((Entity)entity2);
                        entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, x, y, z, 0.0, motion, 0.0, 0.0f, image, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 100, 2, sizem * 0.9f, sizemm * 0.9f, 0.1f, 1, 1.0f, 1.0f, 1.0f, -0.03f, -0.02f, -0.01f, 80.0f, 156.0f, 186.0f, 1, 0.65f, 0.0f, 0.0f, 0.0f, -0.01f, false, -1, true, (Entity)other);
                        entity2.field_70170_p.func_72838_d((Entity)entity2);
                        x = Math.random() * 1.0 - 0.5;
                        y = Math.random() * (double)other.field_70131_O - (double)0.2f;
                        z = Math.random() * 1.0 - 0.5;
                        EntityCusPar entity5 = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, x, y, z, 0.0, Math.random() * (double)0.005f, 0.0, 0.0f, 8, 8, 1, 32, false, 0.0f, false, 0.0f, 1, "", 100, 2, (float)(Math.random() * (double)0.1f), (float)(Math.random() * (double)0.2f) + 0.5f, 0.1f, 2, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1, 0.4f, 0.0f, 0.0f, 0.0f, -0.01f, false, -1, true, (Entity)other);
                        entity5.field_70170_p.func_72838_d((Entity)entity5);
                        x = Math.random() * 1.0 - 0.5;
                        y = Math.random() * (double)other.field_70131_O - 0.5;
                        z = Math.random() * 1.0 - 0.5;
                        EntityCusPar entity4 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 100, 2, (float)(Math.random() * (double)0.03f), (float)(Math.random() * (double)0.03f) + 0.05f, 0.1f, 2, 189.0f, 138.0f, 227.0f, 0.0f, 0.0f, 0.0f, 189.0f, 138.0f, 227.0f, 1, 0.7f, 0.0f, 0.0f, 0.0f, -0.01f, false, -1, true, (Entity)other);
                        entity4.field_70170_p.func_72838_d((Entity)entity4);
                    }
                }
                if (JGConfigClientSettings.CLIENT_GR1 || JGConfigClientSettings.CLIENT_GR8 || JGConfigClientSettings.CLIENT_GR9) {
                    for (k = 0; k < JGConfigClientSettings.get_da1(); ++k) {
                        int[] tavolsagok = new int[]{5, 6, 8, 10, 20};
                        int tav = 20;
                        boolean b1 = false;
                        boolean b2 = false;
                        String[] s = new String[]{"dirt", "grass", "rock", "stone"};
                        for (int j = 0; j < 5; ++j) {
                            if (!b2 && this.field_70170_p.func_147439_a((int)this.field_70165_t, (int)this.field_70163_u - (sneak + j), (int)this.field_70161_v).func_149739_a().toLowerCase().contains("grass")) {
                                b2 = true;
                                tav = tavolsagok[j];
                            }
                            for (int i = 0; i < s.length; ++i) {
                                if (b1 || !this.field_70170_p.func_147439_a((int)this.field_70165_t, (int)this.field_70163_u - (sneak + j), (int)this.field_70161_v).func_149739_a().toLowerCase().contains(s[i])) continue;
                                b1 = true;
                                tav = tavolsagok[j];
                            }
                        }
                        if (this.field_70173_aa == 1) {
                            EntityCusPar entity;
                            double z;
                            double y;
                            double x;
                            int spwnd = 0;
                            boolean b12 = false;
                            boolean b22 = false;
                            String[] s2 = new String[]{"dirt", "grass", "rock", "stone"};
                            for (int j = 0; j < 5; ++j) {
                                if (!b22 && (this.bol2 || this.bol) && this.field_70170_p.func_147439_a((int)this.field_70165_t, (int)this.field_70163_u - (sneak + j), (int)this.field_70161_v).func_149739_a().toLowerCase().contains("grass")) {
                                    b22 = true;
                                    spwnd = j;
                                }
                                for (int i = 0; i < s2.length; ++i) {
                                    if (b12 || !this.bol2 && !this.bol || !this.field_70170_p.func_147439_a((int)this.field_70165_t, (int)this.field_70163_u - (sneak + j), (int)this.field_70161_v).func_149739_a().toLowerCase().contains(s2[i])) continue;
                                    b12 = true;
                                    spwnd = j;
                                }
                            }
                            if (JGConfigClientSettings.CLIENT_GR8 && b22) {
                                float spe = 10.0f;
                                EntityAura2 pl = this;
                                if (!JGConfigClientSettings.CLIENT_GR11) {
                                    x = Math.random() * (double)spe - (double)(spe / 2.0f);
                                    y = -0.2f - (float)spwnd;
                                    z = Math.random() * (double)spe - (double)(spe / 2.0f);
                                    entity = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 1.0f, 1.0f, pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, x, y, z, 0.01 + Math.random() * (double)0.1f - (double)0.05f, 0.05 + Math.random() * (double)0.2f, 0.01 + Math.random() * (double)0.1f - (double)0.05f, (float)(Math.random() * (double)0.001f) - 5.0E-4f, (int)(Math.random() * 8.0) + 16, 16, 8, 32, true, 0.5f, false, 0.0f, 1, "", 160, 0, 0.005f + (float)(Math.random() * (double)0.01f), 0.0f, 0.0f, 0, 255.0f, 255.0f, 255.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2, 0.7f, 0.0f, 0.95f, 1.0f, 0.01f, false, -1, false, null);
                                } else {
                                    x = Math.random() * (double)spe - (double)(spe / 2.0f);
                                    y = -0.2f - (float)spwnd;
                                    z = Math.random() * (double)spe - (double)(spe / 2.0f);
                                    entity = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 1.0f, 1.0f, pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, x, y, z, 0.01 + Math.random() * (double)0.1f - (double)0.05f, 0.05 + Math.random() * (double)0.2f, 0.01 + Math.random() * (double)0.1f - (double)0.05f, (float)(Math.random() * (double)0.001f) - 5.0E-4f, (int)(Math.random() * 8.0) + 16, 16, 8, 32, true, 0.5f, false, 0.0f, 1, "", 160, 0, 0.005f + (float)(Math.random() * (double)0.01f), 0.0f, 0.0f, 0, 255.0f, 255.0f, 255.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2, 0.7f, 0.0f, 0.95f, 1.0f, 0.01f, true, 0, false, null);
                                }
                                entity.field_70170_p.func_72838_d((Entity)entity);
                            }
                            if (JGConfigClientSettings.CLIENT_GR1 && b12) {
                                float spe2 = 10.0f;
                                EntityAura2 pl = this;
                                if (!JGConfigClientSettings.CLIENT_GR11) {
                                    x = Math.random() * (double)spe2 - (double)(spe2 / 2.0f);
                                    y = -0.2f - (float)spwnd;
                                    z = Math.random() * (double)spe2 - (double)(spe2 / 2.0f);
                                    entity = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 1.0f, 1.0f, pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, x, y, z, 0.0 + Math.random() * (double)0.1f - (double)0.05f, 0.05 + (double)((float)(Math.random() * (double)0.2f)), 0.0 + Math.random() * (double)0.1f - (double)0.05f, 0.0f, (int)(Math.random() * 13.0), 0, 13, 32, true, 0.5f, false, 0.0f, 1, "", 160, 0, 0.005f + (float)(Math.random() * (double)0.02f), 0.0f, 0.0f, 0, 255.0f, 255.0f, 255.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2, 0.7f, 0.0f, 0.95f, 1.0f, 0.01f, false, -1, false, null);
                                    entity.field_70170_p.func_72838_d((Entity)entity);
                                } else {
                                    x = Math.random() * (double)spe2 - (double)(spe2 / 2.0f);
                                    y = -0.2f - (float)spwnd;
                                    z = Math.random() * (double)spe2 - (double)(spe2 / 2.0f);
                                    entity = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 1.0f, 1.0f, pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, x, y, z, 0.0 + Math.random() * (double)0.1f - (double)0.05f, 0.05 + (double)((float)(Math.random() * (double)0.2f)), 0.0 + Math.random() * (double)0.1f - (double)0.05f, 0.0f, (int)(Math.random() * 13.0), 0, 13, 32, true, 0.5f, false, 0.0f, 1, "", 160, 0, 0.005f + (float)(Math.random() * (double)0.02f), 0.0f, 0.0f, 0, 255.0f, 255.0f, 255.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2, 0.7f, 0.0f, 0.95f, 1.0f, 0.01f, true, 1, false, null);
                                    entity.field_70170_p.func_72838_d((Entity)entity);
                                }
                            }
                        }
                        boolean create = JGConfigClientSettings.CLIENT_GR9;
                        if (this.bol4) {
                            boolean bl = create = (int)(Math.random() * 3.0) == 0;
                        }
                        if (!create || this.field_70173_aa % tav != 0) continue;
                        int spwnd = 0;
                        boolean b13 = false;
                        boolean b23 = false;
                        String[] s3 = new String[]{"dirt", "grass", "rock", "stone"};
                        for (int j = 0; j < 5; ++j) {
                            if (!b23 && (this.bol2 || this.bol) && this.field_70170_p.func_147439_a((int)this.field_70165_t, (int)this.field_70163_u - (sneak + j), (int)this.field_70161_v).func_149739_a().toLowerCase().contains("grass")) {
                                b23 = true;
                                spwnd = j;
                            }
                            for (int i = 0; i < s3.length; ++i) {
                                if (b13 || !this.bol3 && !this.bol2 && !this.bol || !this.field_70170_p.func_147439_a((int)this.field_70165_t, (int)this.field_70163_u - (sneak + j), (int)this.field_70161_v).func_149739_a().toLowerCase().contains(s3[i])) continue;
                                b13 = true;
                                spwnd = j;
                            }
                        }
                        if (!JGConfigClientSettings.CLIENT_GR9 || !b13 && !b23) continue;
                        float spe = (5.0f - (float)spwnd) / 10.0f;
                        mod_JRMCore.proxy.func_gcp(this, EntityCusPars.PART6, 0.0, -0.2f - (float)spwnd, 0.0, Math.random() * (double)spe - (double)(spe / 2.0f), 0.0, Math.random() * (double)spe - (double)(spe / 2.0f), 64);
                    }
                }
                if (this.kettleMode != 1 && JGConfigClientSettings.CLIENT_DA11 && !other.field_70122_E && (other.field_70142_S != other.field_70165_t || other.field_70136_U != other.field_70161_v)) {
                    float blue;
                    float green;
                    float red;
                    float a = this.alpha;
                    float h1 = 1.0f;
                    if (this.bol4) {
                        red = 215.0f;
                        green = 152.0f;
                        blue = 219.0f;
                        a /= 2.0f;
                    } else {
                        float h2 = (float)(this.color >> 16 & 0xFF) / 255.0f;
                        float h3 = (float)(this.color >> 8 & 0xFF) / 255.0f;
                        float h4 = (float)(this.color & 0xFF) / 255.0f;
                        red = h1 * h2;
                        green = h1 * h3;
                        blue = h1 * h4;
                    }
                    EntityPlayer pl = other;
                    double x = -pl.field_70159_w * 2.5 + (double)(pl.field_70159_w > 0.0 ? -0.2f : 0.2f);
                    double y = (double)(other.field_70131_O * 0.25f + (other instanceof EntityPlayerSP ? -1.6f : 0.0f)) - pl.field_70181_x * 2.5 + (double)(pl.field_70181_x > 0.0 ? -0.1f : 0.1f);
                    double z = -pl.field_70179_y * 2.5 + (double)(pl.field_70179_y > 0.0 ? -0.2f : 0.2f);
                    EntityCusPar entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 1.0f, 1.0f, pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, x, y, z, 0.0, -0.02, 0.0, 0.0f, 8, 11, 1, 32, false, 0.0f, false, 0.0f, 1, "", 15, 1, 0.09f, 0.001f, -0.0045f, 0, red, green, blue, 0.0f, 0.0f, 0.0f, red, green, blue, 1, 0.2f, 0.0f, 0.0f, 0.0f, -0.001f, false, -1, true, null);
                    entity.field_70170_p.func_72838_d((Entity)entity);
                    if (this.bol4) {
                        red = 141.0f;
                        green = 158.0f;
                        blue = 210.0f;
                    } else {
                        float h2 = (float)(this.colorl3 >> 16 & 0xFF) / 255.0f;
                        float h3 = (float)(this.colorl3 >> 8 & 0xFF) / 255.0f;
                        float h4 = (float)(this.colorl3 & 0xFF) / 255.0f;
                        red = h1 * h2;
                        green = h1 * h3;
                        blue = h1 * h4;
                    }
                    x = -pl.field_70159_w * 2.5 + (double)(pl.field_70159_w > 0.0 ? -0.2f : 0.2f);
                    y = (double)(other.field_70131_O * 0.25f + (other instanceof EntityPlayerSP ? -1.6f : 0.0f)) - pl.field_70181_x * 2.5 + (double)(pl.field_70181_x > 0.0 ? -0.1f : 0.1f);
                    z = -pl.field_70179_y * 2.5 + (double)(pl.field_70179_y > 0.0 ? -0.2f : 0.2f);
                    EntityCusPar entity2 = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 1.0f, 1.0f, pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, x, y, z, 0.0, -0.02, 0.0, 0.0f, 8, 11, 1, 32, false, 0.0f, false, 0.0f, 1, "", 15, 1, 0.06f, 0.001f, -0.003f, 0, red, green, blue, 0.0f, 0.0f, 0.0f, red, green, blue, 1, 0.1f, 0.0f, 0.0f, 0.0f, -0.001f, false, -1, true, null);
                    entity.field_70170_p.func_72838_d((Entity)entity2);
                }
                if (this.rot) {
                    this.yaw = other.field_70177_z;
                    this.pitch = other.field_70125_A;
                }
                this.func_70080_a(other.field_70165_t, other.field_70163_u + (double)(other instanceof EntityPlayerSP ? -1.6f : 0.0f), other.field_70161_v, other.field_70177_z, other.field_70125_A);
                if (this.getAge() < this.getLightLivingTime() && this.getState() > 4.0f && this.getState() < 7.0f && this.getAge() == 2) {
                    other.func_85030_a("jinryuudragonbc:1610.spark", 0.0375f, 0.85f + (float)this.lightLivingTime * 0.05f);
                }
            } else {
                this.func_70106_y();
            }
        }
        if (this.Age++ >= this.speed) {
            this.func_70106_y();
        }
    }

    public boolean getCanSpawnHere() {
        return !this.field_70170_p.func_72855_b(this.field_70121_D);
    }

    public void onLivingUpdate() {
    }

    protected void func_70037_a(NBTTagCompound var1) {
    }

    protected void func_70014_b(NBTTagCompound var1) {
    }

    protected void func_70088_a() {
    }

    public boolean func_70112_a(double par1) {
        if (JGConfigClientSettings.renderdistanceMultiplierAuras == 10000) {
            return true;
        }
        double d1 = this.field_70121_D.func_72320_b();
        return par1 < (d1 *= 64.0 * this.field_70155_l) * d1 * ((double)JGConfigClientSettings.renderdistanceMultiplierAuras / 100.0);
    }
}

