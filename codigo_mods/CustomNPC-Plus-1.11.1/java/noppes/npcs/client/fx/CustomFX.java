/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.fx;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.ClientProxy;
import noppes.npcs.client.renderer.ImageData;
import noppes.npcs.scripted.ScriptParticle;
import org.lwjgl.opengl.GL11;

public class CustomFX
extends EntityFX {
    protected final Entity entity;
    protected final String directory;
    protected final ImageData imageData;
    protected static final ResourceLocation resource = new ResourceLocation("textures/particle/particles.png");
    protected float startX = 0.0f;
    protected float startY = 0.0f;
    protected float startZ = 0.0f;
    public float scaleX1 = 1.0f;
    public float scaleX2 = 1.0f;
    public float scaleXRate = 1.0f;
    public int scaleXRateStart = 0;
    public float scaleY1 = 1.0f;
    public float scaleY2 = 1.0f;
    public float scaleYRate = 1.0f;
    public int scaleYRateStart = 0;
    public float alpha1 = 1.0f;
    public float alpha2 = 0.0f;
    public float alphaRate = 0.0f;
    public int alphaRateStart = 0;
    public float rotationX = 0.0f;
    public float rotationX1 = 0.0f;
    public float rotationX2 = 0.0f;
    public float rotationXRate = 0.0f;
    public int rotationXRateStart = 0;
    public float rotationY = 0.0f;
    public float rotationY1 = 0.0f;
    public float rotationY2 = 0.0f;
    public float rotationYRate = 0.0f;
    public int rotationYRateStart = 0;
    public float rotationZ = 0.0f;
    public float rotationZ1 = 0.0f;
    public float rotationZ2 = 0.0f;
    public float rotationZRate = 0.0f;
    public int rotationZRateStart = 0;
    public boolean facePlayer = true;
    public boolean glows = true;
    public int field_70130_N;
    public int field_70131_O;
    public int offsetX;
    public int offsetY;
    protected int timeSinceStart;
    public int animRate;
    public boolean animLoop;
    public int animStart;
    public int animEnd;
    protected int animPosX;
    protected int animPosY;
    public int HEXColor = 0xFFFFFF;
    public int HEXColor2 = 0xFFFFFF;
    public float HEXColorRate = 0.0f;
    public int HEXColorStart = 0;
    protected double renderPosX;
    protected double renderPosY;
    protected double renderPosZ;

    public CustomFX(World worldObj, Entity entity, String directory, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(worldObj, x, y, z, motionX, motionY, motionZ);
        this.entity = entity;
        this.directory = directory;
        this.imageData = ClientCacheHandler.getImageData(this.directory);
    }

    public void setMaxAge(int age) {
        this.field_70547_e = age;
    }

    public static CustomFX fromScriptedParticle(ScriptParticle particle, World worldObj, Entity entity) {
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        if (entity == player) {
            particle.y -= (double)player.field_70129_M;
        }
        CustomFX customFX = new CustomFX(worldObj, entity, particle.directory, particle.x, particle.y, particle.z, particle.motionX, particle.motionY, particle.motionZ);
        customFX.HEXColor = particle.HEXColor;
        customFX.HEXColor2 = particle.HEXColor2;
        customFX.HEXColorRate = particle.HEXColorRate;
        customFX.HEXColorStart = particle.HEXColorStart;
        customFX.field_70552_h = (float)(customFX.HEXColor >> 16 & 0xFF) / 255.0f;
        customFX.field_70553_i = (float)(customFX.HEXColor >> 8 & 0xFF) / 255.0f;
        customFX.field_70551_j = (float)(customFX.HEXColor & 0xFF) / 255.0f;
        customFX.scaleX1 = particle.scaleX1;
        customFX.scaleX2 = particle.scaleX2;
        customFX.scaleXRate = Math.abs(particle.scaleXRate);
        customFX.scaleXRateStart = particle.scaleXRateStart;
        if (customFX.scaleX1 > customFX.scaleX2) {
            customFX.scaleXRate *= -1.0f;
        }
        customFX.scaleY1 = particle.scaleY1;
        customFX.scaleY2 = particle.scaleY2;
        customFX.scaleYRate = Math.abs(particle.scaleYRate);
        customFX.scaleYRateStart = particle.scaleYRateStart;
        if (customFX.scaleY1 > customFX.scaleY2) {
            customFX.scaleYRate *= -1.0f;
        }
        customFX.alpha1 = particle.alpha1;
        customFX.alpha2 = particle.alpha2;
        customFX.alphaRate = Math.abs(particle.alphaRate);
        customFX.alphaRateStart = particle.alphaRateStart;
        customFX.field_82339_as = customFX.alpha1;
        if (customFX.alpha1 > customFX.alpha2) {
            customFX.alphaRate *= -1.0f;
        }
        customFX.rotationX1 = particle.rotationX1;
        customFX.rotationX2 = particle.rotationX2;
        customFX.rotationXRate = Math.abs(particle.rotationXRate);
        customFX.rotationXRateStart = particle.rotationXRateStart;
        customFX.rotationX = customFX.rotationX1;
        if (customFX.rotationX1 > customFX.rotationX2) {
            customFX.rotationXRate *= -1.0f;
        }
        customFX.rotationY1 = particle.rotationY1;
        customFX.rotationY2 = particle.rotationY2;
        customFX.rotationYRate = Math.abs(particle.rotationYRate);
        customFX.rotationYRateStart = particle.rotationYRateStart;
        customFX.rotationY = customFX.rotationY1;
        if (customFX.rotationY1 > customFX.rotationY2) {
            customFX.rotationYRate *= -1.0f;
        }
        customFX.rotationZ1 = particle.rotationZ1;
        customFX.rotationZ2 = particle.rotationZ2;
        customFX.rotationZRate = Math.abs(particle.rotationZRate);
        customFX.rotationZRateStart = particle.rotationZRateStart;
        customFX.rotationZ = customFX.rotationZ1;
        if (customFX.rotationZ1 > customFX.rotationZ2) {
            customFX.rotationZRate *= -1.0f;
        }
        customFX.field_70545_g = particle.gravity / 0.04f;
        customFX.field_70547_e = particle.maxAge;
        customFX.field_70159_w = particle.motionX;
        customFX.field_70181_x = particle.motionY;
        customFX.field_70179_y = particle.motionZ;
        customFX.field_70145_X = particle.noClip;
        customFX.field_70130_N = particle.width;
        customFX.field_70131_O = particle.height;
        customFX.offsetX = particle.offsetX;
        customFX.offsetY = particle.offsetY;
        customFX.animRate = particle.animRate;
        customFX.animLoop = particle.animLoop;
        customFX.animStart = particle.animStart;
        customFX.animEnd = particle.animEnd;
        if (customFX.animEnd < customFX.animStart) {
            customFX.animEnd = customFX.animStart + customFX.field_70547_e;
        }
        customFX.facePlayer = particle.facePlayer;
        customFX.glows = particle.glows;
        return customFX;
    }

    public void func_70071_h_() {
        if (!this.imageLoaded()) {
            return;
        }
        ++this.timeSinceStart;
        if (this.timeSinceStart == this.field_70547_e) {
            this.func_70106_y();
        }
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        this.field_70181_x -= 0.04 * (double)this.field_70545_g;
        this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        if (this.animRate > 0 && this.timeSinceStart % this.animRate == 0 && this.timeSinceStart > 0 && this.timeSinceStart >= this.animStart && this.timeSinceStart <= this.animEnd && (this.animLoop || this.animPosY <= this.imageData.getTotalHeight())) {
            this.animPosX += this.field_70130_N;
            if (this.animPosX + this.field_70130_N > this.imageData.getTotalWidth()) {
                this.animPosX = this.offsetX;
                this.animPosY += this.field_70131_O;
                if (this.animPosY > this.imageData.getTotalHeight() && this.animLoop) {
                    this.animPosY = this.offsetY;
                }
            }
        }
        this.field_70552_h = (float)(this.HEXColor >> 16 & 0xFF) / 255.0f;
        this.field_70553_i = (float)(this.HEXColor >> 8 & 0xFF) / 255.0f;
        this.field_70551_j = (float)(this.HEXColor & 0xFF) / 255.0f;
        if (this.timeSinceStart >= this.HEXColorStart) {
            this.HEXColor = this.lerpColor(this.HEXColor, this.HEXColor2, this.HEXColorRate);
        }
    }

    public void func_70539_a(Tessellator tessellator, float partialTick, float cosYaw, float cosPitch, float sinYaw, float sinSinPitch, float cosSinPitch) {
        if (!this.imageLoaded()) {
            return;
        }
        tessellator.func_78381_a();
        this.imageData.bindTexture();
        if (this.imageData.invalid()) {
            this.func_70106_y();
        }
        if (this.entity != null) {
            this.startX = (float)(this.entity.field_70169_q + (this.entity.field_70165_t - this.entity.field_70169_q) * (double)partialTick);
            this.startY = (float)(this.entity.field_70167_r + (this.entity.field_70163_u - this.entity.field_70167_r) * (double)partialTick);
            this.startZ = (float)(this.entity.field_70166_s + (this.entity.field_70161_v - this.entity.field_70166_s) * (double)partialTick);
        }
        float scaleXChange = this.scaleXRate / (float)this.field_70547_e;
        if (this.scaleXRate < 0.0f && this.scaleX1 + scaleXChange < this.scaleX2 || this.scaleXRate > 0.0f && this.scaleX1 + scaleXChange > this.scaleX2) {
            this.scaleX1 = this.scaleX2;
        } else if (this.timeSinceStart >= this.scaleXRateStart) {
            this.scaleX1 += scaleXChange;
        }
        float scaleYChange = this.scaleYRate / (float)this.field_70547_e;
        if (this.scaleYRate < 0.0f && this.scaleY1 + scaleYChange < this.scaleY2 || this.scaleYRate > 0.0f && this.scaleY1 + scaleYChange > this.scaleY2) {
            this.scaleY1 = this.scaleY2;
        } else if (this.timeSinceStart >= this.scaleYRateStart) {
            this.scaleY1 += scaleYChange;
        }
        float alphaChange = this.alphaRate / (float)this.field_70547_e;
        if (this.alphaRate < 0.0f && this.field_82339_as + alphaChange < this.alpha2 || this.alphaRate > 0.0f && this.field_82339_as + alphaChange > this.alpha2) {
            this.field_82339_as = this.alpha2;
        } else if (this.timeSinceStart >= this.alphaRateStart) {
            this.field_82339_as += alphaChange;
        }
        float rotationXChange = this.rotationXRate / (float)this.field_70547_e;
        if (this.rotationXRate < 0.0f && this.rotationX + rotationXChange < this.rotationX2 || this.rotationXRate > 0.0f && this.rotationX + rotationXChange > this.rotationX2) {
            this.rotationX = this.rotationX2;
        } else if (this.timeSinceStart >= this.rotationXRateStart) {
            this.rotationX += rotationXChange;
        }
        float rotationYChange = this.rotationYRate / (float)this.field_70547_e;
        if (this.rotationYRate < 0.0f && this.rotationY + rotationYChange < this.rotationY2 || this.rotationYRate > 0.0f && this.rotationY + rotationYChange > this.rotationY2) {
            this.rotationY = this.rotationY2;
        } else if (this.timeSinceStart >= this.rotationYRateStart) {
            this.rotationY += rotationYChange;
        }
        float rotationZChange = this.rotationZRate / (float)this.field_70547_e;
        if (this.rotationZRate < 0.0f && this.rotationZ + rotationZChange < this.rotationZ2 || this.rotationZRate > 0.0f && this.rotationZ + rotationZChange > this.rotationZ2) {
            this.rotationZ = this.rotationZ2;
        } else if (this.timeSinceStart >= this.rotationZRateStart) {
            this.rotationZ += rotationZChange;
        }
        this.renderParticleSide(true, tessellator, partialTick);
        this.renderParticleSide(false, tessellator, partialTick);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ClientProxy.bindTexture(resource);
        tessellator.func_78382_b();
    }

    public void renderParticleSide(boolean front, Tessellator tessellator, float partialTick) {
        int totalWidth = this.imageData.getTotalWidth();
        int totalHeight = this.imageData.getTotalHeight();
        float u1 = (float)this.offsetX / (float)totalWidth + (float)this.animPosX / (float)totalWidth;
        float u2 = u1 + (float)this.field_70130_N / (float)totalWidth;
        float v1 = (float)this.offsetY / (float)totalHeight + (float)this.animPosY / (float)totalHeight;
        float v2 = v1 + (float)this.field_70131_O / (float)totalHeight;
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        float posX = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)partialTick - field_70556_an) + this.startX;
        float posY = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)partialTick - field_70554_ao) + this.startY;
        float posZ = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)partialTick - field_70555_ap) + this.startZ;
        GL11.glPushMatrix();
        this.renderPosX = (double)(this.startX + posX) + this.field_70165_t;
        this.renderPosY = (double)(this.startY + posY) + this.field_70163_u;
        this.renderPosZ = (double)(this.startZ + posZ) + this.field_70161_v;
        GL11.glTranslated((double)posX, (double)posY, (double)posZ);
        if (this.facePlayer) {
            GL11.glRotated((double)(180.0f - player.field_70177_z), (double)0.0, (double)1.0, (double)0.0);
            GL11.glRotated((double)(-player.field_70125_A + 90.0f), (double)1.0, (double)0.0, (double)0.0);
            GL11.glRotated((double)this.rotationX, (double)1.0, (double)0.0, (double)0.0);
            GL11.glRotated((double)this.rotationZ, (double)0.0, (double)1.0, (double)0.0);
            GL11.glRotated((double)(this.rotationY + (float)(!front ? 180 : 0)), (double)0.0, (double)0.0, (double)1.0);
        } else {
            GL11.glRotated((double)this.rotationX, (double)1.0, (double)0.0, (double)0.0);
            GL11.glRotated((double)this.rotationY, (double)0.0, (double)1.0, (double)0.0);
            GL11.glRotated((double)(this.rotationZ + (float)(!front ? 180 : 0)), (double)0.0, (double)0.0, (double)1.0);
        }
        tessellator.func_78382_b();
        if (!this.glows) {
            tessellator.func_78380_c(this.func_70070_b(partialTick));
        } else {
            tessellator.func_78380_c(240);
        }
        float textureXScale = 1.0f;
        float textureYScale = 1.0f;
        if (totalWidth > totalHeight) {
            textureYScale = (float)totalHeight / (float)totalWidth;
            GL11.glScalef((float)(1.0f / textureYScale / 2.0f), (float)(1.0f / textureYScale / 2.0f), (float)(1.0f / textureYScale / 2.0f));
        } else if (totalHeight > totalWidth) {
            textureXScale = (float)totalWidth / (float)totalHeight;
            GL11.glScalef((float)(1.0f / textureXScale / 2.0f), (float)(1.0f / textureXScale / 2.0f), (float)(1.0f / textureXScale / 2.0f));
        }
        tessellator.func_78386_a(1.0f, 1.0f, 1.0f);
        tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as);
        tessellator.func_78374_a((double)((textureXScale *= this.scaleX1 / 10.0f) * (u2 - u1) / 2.0f), 0.0, (double)((textureYScale *= this.scaleY1 / 10.0f) * (v2 - v1) / 2.0f), (double)u2, (double)v2);
        tessellator.func_78374_a((double)(textureXScale * (u2 - u1) / 2.0f), 0.0, (double)(textureYScale * -(v2 - v1) / 2.0f), (double)u2, (double)v1);
        tessellator.func_78374_a((double)(textureXScale * -(u2 - u1) / 2.0f), 0.0, (double)(textureYScale * -(v2 - v1) / 2.0f), (double)u1, (double)v1);
        tessellator.func_78374_a((double)(textureXScale * -(u2 - u1) / 2.0f), 0.0, (double)(textureYScale * (v2 - v1) / 2.0f), (double)u1, (double)v2);
        tessellator.func_78381_a();
        GL11.glPopMatrix();
    }

    @SideOnly(value=Side.CLIENT)
    public int func_70070_b(float p_70070_1_) {
        int j;
        int i = MathHelper.func_76128_c((double)this.renderPosX);
        if (this.field_70170_p.func_72899_e(i, 0, j = MathHelper.func_76128_c((double)this.renderPosZ))) {
            double d0 = (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * 0.66;
            int k = MathHelper.func_76128_c((double)(this.renderPosY - (double)this.field_70129_M + d0));
            return this.field_70170_p.func_72802_i(i, k, j, 0);
        }
        return 0;
    }

    public int lerpColor(int from, int to, float ratio) {
        float ar = (from & 0xFF0000) >> 16;
        float ag = (from & 0xFF00) >> 8;
        float ab = from & 0xFF;
        float br = (to & 0xFF0000) >> 16;
        float bg = (to & 0xFF00) >> 8;
        float bb = to & 0xFF;
        float rr = ar + ratio * (br - ar);
        float rg = ag + ratio * (bg - ag);
        float rb = ab + ratio * (bb - ab);
        return (int)((float)(((int)rr << 16) + ((int)rg << 8)) + rb);
    }

    public int func_70537_b() {
        return 0;
    }

    public boolean imageLoaded() {
        if (this.imageData.imageLoaded()) {
            this.field_70130_N = this.field_70130_N < 0 ? this.imageData.getTotalWidth() : this.field_70130_N;
            this.field_70131_O = this.field_70131_O < 0 ? this.imageData.getTotalHeight() : this.field_70131_O;
            return true;
        }
        return false;
    }
}

