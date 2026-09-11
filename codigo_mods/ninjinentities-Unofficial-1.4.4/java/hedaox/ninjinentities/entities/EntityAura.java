/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package hedaox.ninjinentities.entities;

import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.entity.EntityCusPar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

@SideOnly(value=Side.CLIENT)
public class EntityAura
extends Entity {
    public final int number_of_lightVerts = 10;
    public long[] lightVert = new long[10];
    private int lightLivingTime;
    public long lightVert2;
    private int Lightcolor = 0xFFFFFF;
    private int Lightcolor2 = 0xFFFFFF;
    private boolean bolLighting;
    private boolean bolLighting2;
    public int rm;
    private Entity mot;
    private boolean rot = false;
    private int Age;
    private int color = 0xFFFFFF;
    private int colorl2 = 0xFFFFFF;
    private int colorl3 = -1;
    private final float state;
    private final float state2;
    private final int crel;
    private float yaw;
    private float pitch;
    private float alpha;
    private String tex;
    private String texl2;
    private String texl3;
    private int speed;
    private boolean inner;
    private int rendpass;
    private boolean bol;
    private boolean bol2;
    private boolean bol3;
    private boolean bol4;
    private boolean bol4a;
    private byte bol6;
    private boolean bol7;
    private boolean bolGODX;
    private boolean bolGODPARTC;
    private boolean bolROTATION;

    public int getLightLivingTime() {
        return this.lightLivingTime;
    }

    public EntityAura(World par1World) {
        super(par1World);
        this.state = 0.0f;
        this.state2 = 0.0f;
        this.crel = 0;
        this.yaw = 0.0f;
        this.pitch = 0.0f;
        this.alpha = 0.1f;
        this.tex = "aura";
        this.texl2 = "";
        this.texl3 = "";
        this.speed = 20;
        this.inner = true;
        this.rendpass = 1;
        this.bol = false;
        this.bol2 = false;
        this.bol3 = false;
        this.bol4 = false;
        this.bol4a = false;
        this.bol6 = (byte)-1;
        this.bol7 = false;
        this.bolGODX = false;
        this.bolGODPARTC = false;
        this.bolROTATION = false;
        this.bolLighting = false;
        this.bolLighting2 = false;
    }

    public EntityAura(World par1World, Entity dbcCharger, int c, float s, float s2, int cr, boolean b, float a) {
        this(par1World, dbcCharger, c, s, s2, cr, b);
        this.alpha = a;
    }

    public EntityAura(World par1World, Entity dbcCharger, int c, float s, float s2, int cr, boolean b) {
        this(par1World, dbcCharger, c, s, s2, cr);
        this.rot = b;
    }

    public EntityAura(World par1World, Entity dbcCharger, int c, float s, float s2, int cr) {
        super(par1World);
        this.bolLighting = false;
        this.bolLighting2 = false;
        this.yaw = 0.0f;
        this.pitch = 0.0f;
        this.alpha = 0.1f;
        this.tex = "aura";
        this.texl2 = "";
        this.texl3 = "";
        this.speed = 20;
        this.inner = true;
        this.rendpass = 1;
        this.bol = false;
        this.bol2 = false;
        this.bol3 = false;
        this.bol4 = false;
        this.bol4a = false;
        this.bol6 = (byte)-1;
        this.bol7 = false;
        this.bolGODX = false;
        this.bolGODPARTC = false;
        this.bolROTATION = false;
        this.mot = dbcCharger;
        this.color = c;
        this.state = s;
        this.state2 = s2;
        this.crel = cr;
        this.rm = this.field_70146_Z.nextInt(10);
        ((Object)((Object)this)).getClass();
        for (int i = 0; i < 10; ++i) {
            this.lightVert[i] = this.field_70146_Z.nextLong();
        }
        this.lightVert2 = this.field_70146_Z.nextLong();
        this.lightLivingTime = this.field_70146_Z.nextInt(4);
        Entity entityPlayer = this.mot;
        if (entityPlayer != null) {
            if (this.rot) {
                this.yaw = entityPlayer.field_70177_z;
                this.pitch = entityPlayer.field_70125_A;
            }
            this.func_70080_a(entityPlayer.field_70165_t, entityPlayer.field_70163_u + (double)(entityPlayer instanceof EntityPlayerSP ? -1.6f : 0.0f), entityPlayer.field_70161_v, entityPlayer.field_70177_z, entityPlayer.field_70125_A);
        }
    }

    @SideOnly(value=Side.CLIENT)
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

    public int getLightCol() {
        return this.Lightcolor;
    }

    public void setLightCol(int c) {
        this.Lightcolor = c;
    }

    public int getLightCol2() {
        return this.Lightcolor2;
    }

    public void setLightCol2(int c) {
        this.Lightcolor2 = c;
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

    public Entity getmot() {
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

    public void setBolGODX(boolean b) {
        this.bolGODX = b;
    }

    public void setBolGODPARTC(boolean b) {
        this.bolGODPARTC = b;
    }

    public void setBolROTATION(boolean b) {
        this.bolROTATION = b;
    }

    public void setBolLighting(boolean b) {
        this.bolLighting = b;
    }

    public void setBolLighting2(boolean b) {
        this.bolLighting2 = b;
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

    public boolean getBolGODX() {
        return this.bolGODX;
    }

    public boolean getBolGODPARTC() {
        return this.bolGODPARTC;
    }

    public boolean getBolROTATION() {
        return this.bolROTATION;
    }

    public boolean getBolLighting() {
        return this.bolLighting;
    }

    public boolean getBolLighting2() {
        return this.bolLighting2;
    }

    public void func_70071_h_() {
        boolean aura_type = JGConfigClientSettings.CLIENT_DA13;
        boolean aura_type2 = JGConfigClientSettings.CLIENT_DA20;
        Entity entityPlayer = this.mot;
        if (entityPlayer != null) {
            boolean sneak;
            boolean bl = sneak = !entityPlayer.func_70093_af();
            if (JGConfigClientSettings.CLIENT_GR0) {
                for (int k = 0; k < JGConfigClientSettings.get_da1(); ++k) {
                    EntityCusPar entityCusPar;
                    double z;
                    double y;
                    double x;
                    EntityAura pl;
                    float blue;
                    float green;
                    float red;
                    float h4;
                    float h3;
                    float h2;
                    float spe2;
                    float h1;
                    float a;
                    if (!this.bolGODPARTC) continue;
                    if (!this.bol4) {
                        if (this.field_70173_aa % 17 != 0) continue;
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
                        entityCusPar = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, x, y, z, 0.0, 0.05 + Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 48, 48, 8, 32, false, 0.0f, false, 0.0f, 1, "", 35, 0, 0.003f + (float)(Math.random() * (double)0.006f), 0.0f, 0.0f, 0, red, green, blue, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2, 0.8f, 0.0f, 0.9f, 0.95f, 0.018f, false, -1, true, entityPlayer);
                        entityCusPar.field_70170_p.func_72838_d((Entity)entityCusPar);
                        continue;
                    }
                    if (this.field_70173_aa % 17 != 0) continue;
                    a = this.alpha;
                    h1 = 1.0f;
                    spe2 = 1.3f;
                    h2 = (float)(this.color >> 16 & 0xFF) / 255.0f;
                    h3 = (float)(this.color >> 8 & 0xFF) / 255.0f;
                    h4 = (float)(this.color & 0xFF) / 255.0f;
                    red = h1 * h2 + 0.6f;
                    green = h1 * h3 + 0.6f;
                    blue = h1 * h4 + 0.6f;
                    pl = this;
                    x = Math.random() * (double)spe2 - (double)(spe2 / 2.0f);
                    y = -0.3f;
                    z = Math.random() * (double)spe2 - (double)(spe2 / 2.0f);
                    entityCusPar = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, pl.field_70165_t, pl.field_70163_u, pl.field_70161_v, x, y, z, 0.0, 0.05 + Math.random() * (double)0.1f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 48, 48, 8, 32, false, 0.0f, false, 0.0f, 1, "", 20, 0, 0.003f + (float)(Math.random() * (double)0.006f), 0.0f, 0.0f, 0, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2, 0.8f, 0.0f, 0.9f, 0.95f, 0.05f, false, -1, true, entityPlayer);
                    entityCusPar.field_70170_p.func_72838_d((Entity)entityCusPar);
                }
            }
            if (this.rot) {
                this.yaw = entityPlayer.field_70177_z;
                this.pitch = entityPlayer.field_70125_A;
            }
            this.func_70080_a(entityPlayer.field_70165_t, entityPlayer.field_70163_u + (double)(entityPlayer instanceof EntityPlayerSP ? -1.6f : 0.0f), entityPlayer.field_70161_v, entityPlayer.field_70177_z, entityPlayer.field_70125_A);
            if (this.getAge() < this.getLightLivingTime() && (this.getState() > 4.0f && this.getState() < 7.0f || this.getBolLighting() || this.getBolLighting2()) && this.getAge() == 2) {
                entityPlayer.func_85030_a("jinryuudragonbc:1610.spark", 0.0375f, 0.85f + (float)this.lightLivingTime * 0.05f);
            }
        } else {
            this.func_70106_y();
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
}

