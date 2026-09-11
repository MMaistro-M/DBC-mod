/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.EntityEvent$EntityConstructing
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.fx;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityEvent;
import org.lwjgl.opengl.GL11;

public class EntityBigSmokeFX
extends EntityFX {
    public static final int TEXTURE_COUNT = 12;
    public static final ResourceLocation[] TEXTURES = new ResourceLocation[12];
    protected final int texIndex;
    protected boolean alphaFading = false;
    protected float alphaFadePerTick = -0.015f;
    protected boolean localizedCombustion = true;
    protected boolean isColored = false;

    public EntityBigSmokeFX(World world, int x, int y, int z, boolean signalFire, float[] colours) {
        super(world, (double)x, (double)y, (double)z);
        this.func_70107_b((double)x + 0.5 + this.field_70146_Z.nextDouble() / 10.0 * (double)(this.field_70146_Z.nextBoolean() ? 1 : -1), (double)y + 0.1 + this.field_70146_Z.nextDouble() * 0.1, (double)z + 0.5 + this.field_70146_Z.nextDouble() / 10.0 * (double)(this.field_70146_Z.nextBoolean() ? 1 : -1));
        this.field_70544_f = 4.0f * (this.field_70146_Z.nextFloat() * 0.5f + 0.5f);
        this.func_70105_a(0.25f, 0.25f);
        this.field_70545_g = 0.005f;
        this.field_70159_w = 0.0;
        this.field_70181_x = 0.02f + this.field_70146_Z.nextFloat() * 0.01f;
        this.field_70179_y = 0.0;
        this.field_70547_e = this.field_70146_Z.nextInt(50) + 100;
        this.field_82339_as = 0.8f;
        if (colours.length == 3) {
            this.func_70538_b(colours[0], colours[1], colours[2]);
        }
        this.texIndex = this.field_70146_Z.nextInt(12);
        this.isColored = this.field_70552_h != 1.0f || this.field_70553_i != 1.0f || this.field_70551_j != 1.0f;
    }

    public void func_70539_a(Tessellator tess, float partialTicks, float rotX, float rotXZ, float rotZ, float rotYZ, float rotXY) {
        rotX = ActiveRenderInfo.field_74588_d;
        rotXZ = ActiveRenderInfo.field_74589_e;
        rotZ = ActiveRenderInfo.field_74586_f;
        rotYZ = ActiveRenderInfo.field_74587_g;
        rotXY = ActiveRenderInfo.field_74596_h;
        EntityLivingBase view = Minecraft.func_71410_x().field_71451_h;
        double interpX = view.field_70142_S + (view.field_70165_t - view.field_70142_S) * (double)partialTicks;
        double interpY = view.field_70137_T + (view.field_70163_u - view.field_70137_T) * (double)partialTicks;
        double interpZ = view.field_70136_U + (view.field_70161_v - view.field_70136_U) * (double)partialTicks;
        float partialPosX = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)partialTicks - interpX);
        float partialPosY = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)partialTicks - interpY);
        float partialPosZ = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)partialTicks - interpZ);
        float scale = 0.1f * this.field_70544_f;
        double minU = (double)this.field_94054_b * 0.25;
        double maxU = minU + 0.25;
        double minV = (double)this.field_94055_c * 0.125;
        double maxV = minV + 0.125;
        GL11.glEnable((int)3042);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)3008);
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(TEXTURES[this.texIndex % TEXTURES.length]);
        tess.func_78382_b();
        tess.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as);
        tess.func_78380_c(0xF000E0);
        tess.func_78374_a((double)(partialPosX - rotX * scale - rotYZ * scale), (double)(partialPosY - rotXZ * scale), (double)(partialPosZ - rotZ * scale - rotXY * scale), 1.0, 1.0);
        tess.func_78374_a((double)(partialPosX - rotX * scale + rotYZ * scale), (double)(partialPosY + rotXZ * scale), (double)(partialPosZ - rotZ * scale + rotXY * scale), 1.0, 0.0);
        tess.func_78374_a((double)(partialPosX + rotX * scale + rotYZ * scale), (double)(partialPosY + rotXZ * scale), (double)(partialPosZ + rotZ * scale + rotXY * scale), 0.0, 0.0);
        tess.func_78374_a((double)(partialPosX + rotX * scale - rotYZ * scale), (double)(partialPosY - rotXZ * scale), (double)(partialPosZ + rotZ * scale - rotXY * scale), 0.0, 1.0);
        tess.func_78381_a();
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)false);
        GL11.glAlphaFunc((int)516, (float)0.1f);
    }

    public int func_70537_b() {
        return 3;
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        if (this.field_70546_d++ >= this.field_70547_e - 40) {
            this.alphaFading = true;
        }
        if (this.field_70546_d < this.field_70547_e && this.field_82339_as > 0.0f) {
            float horizontalMotionScale = 0.001f;
            this.field_70159_w += (double)(this.field_70146_Z.nextFloat() * horizontalMotionScale * (float)(this.field_70146_Z.nextBoolean() ? 1 : -1));
            this.field_70179_y += (double)(this.field_70146_Z.nextFloat() * horizontalMotionScale * (float)(this.field_70146_Z.nextBoolean() ? 1 : -1));
            this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
            if (this.alphaFading) {
                this.field_82339_as = MathHelper.func_76131_a((float)(this.field_82339_as + this.alphaFadePerTick), (float)0.0f, (float)1.0f);
            }
        } else {
            this.func_70106_y();
        }
    }

    static {
        for (int i = 0; i < 12; ++i) {
            EntityBigSmokeFX.TEXTURES[i] = new ResourceLocation("customnpcs:textures/particle/big_smoke_" + i + ".png");
        }
    }

    @Cancelable
    public static class EntityBigSmokeFXConstructingEvent
    extends EntityEvent.EntityConstructing {
        public final int[] campfirePosition;
        public float particleRed;
        public float particleGreen;
        public float particleBlue;
        public double motionX;
        public double motionY;
        public double motionZ;
        public float particleGravity;
        public float particleAlpha;
        public boolean alphaFading;
        public float alphaFadePerTick;
        public int particleMaxAge;

        public EntityBigSmokeFXConstructingEvent(EntityBigSmokeFX entity, int x, int y, int z) {
            super((Entity)entity);
            this.campfirePosition = new int[]{x, y, z};
            this.particleRed = entity.field_70552_h;
            this.particleGreen = entity.field_70553_i;
            this.particleBlue = entity.field_70551_j;
            this.motionX = entity.field_70159_w;
            this.motionY = entity.field_70181_x;
            this.motionZ = entity.field_70179_y;
            this.particleGravity = entity.field_70545_g;
            this.particleAlpha = entity.field_82339_as;
            this.alphaFading = entity.alphaFading;
            this.alphaFadePerTick = entity.alphaFadePerTick;
            this.particleMaxAge = entity.field_70547_e;
        }
    }
}

