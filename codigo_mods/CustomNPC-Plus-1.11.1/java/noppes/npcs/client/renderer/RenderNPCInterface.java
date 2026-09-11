/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture
 *  com.mojang.authlib.minecraft.MinecraftProfileTexture$Type
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.IImageBuffer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.SkinManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.boss.BossStatus
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.RenderLivingEvent$Post
 *  net.minecraftforge.client.event.RenderLivingEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import cpw.mods.fml.common.eventhandler.Event;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Map;
import javax.imageio.ImageIO;
import kamkeel.npcs.addon.client.GeckoAddonClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import noppes.npcs.api.ISkinOverlay;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.ImageDownloadAlt;
import noppes.npcs.client.model.ModelMPM;
import noppes.npcs.client.renderer.ImageBufferDownloadAlt;
import noppes.npcs.client.renderer.ImageData;
import noppes.npcs.constants.EnumAnimation;
import noppes.npcs.constants.EnumStandingType;
import noppes.npcs.controllers.data.SkinOverlay;
import noppes.npcs.controllers.data.TintData;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.opengl.GL11;

public class RenderNPCInterface
extends RenderLiving {
    public static long LastTextureTick = 0L;
    public static RenderManager staticRenderManager;
    public ModelBase originalModel;
    public static ResourceLocation steve64;
    public static ResourceLocation alex;

    public RenderNPCInterface(ModelBase model, float f) {
        super(model, f);
        this.originalModel = model;
    }

    protected void renderName(EntityNPCInterface npc, double d, double d1, double d2) {
        float f3;
        if (!this.func_110813_b((EntityLiving)npc)) {
            return;
        }
        float f2 = npc.func_70032_d((Entity)this.field_76990_c.field_78734_h);
        float f = f3 = npc.func_70093_af() ? 32.0f : 64.0f;
        if (f2 > f3) {
            return;
        }
        if (npc.messages != null) {
            float height = npc.baseHeight / 5.0f * (float)npc.display.modelSize;
            float offset = npc.field_70131_O * (1.2f + (!npc.display.showName() ? 0.0f : (npc.display.title.isEmpty() ? 0.15f : 0.25f)));
            npc.messages.renderMessages(d, d1 + (double)offset, d2, 0.666667f * height, npc.isInRange((Entity)this.field_76990_c.field_78734_h, 4.0));
        }
        float scale = npc.baseHeight / 5.0f * (float)npc.display.modelSize;
        int height = 0;
        if (npc.display.showName()) {
            String s = npc.func_70005_c_();
            if (!npc.display.title.isEmpty()) {
                this.renderLivingLabel(npc, d, d1 + (double)npc.field_70131_O - (double)(0.06f * scale), d2, 64, "<" + npc.display.title + ">", Float.valueOf(0.6f), s, Float.valueOf(1.0f));
                height = 2;
            } else {
                this.renderLivingLabel(npc, d, d1 + (double)npc.field_70131_O - (double)(0.06f * scale), d2, 64, s, Float.valueOf(1.0f));
                height = 1;
            }
        }
    }

    public void func_76979_b(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        EntityNPCInterface npc = (EntityNPCInterface)par1Entity;
        if (!npc.isKilled() && !npc.scriptInvisibleToPlayer((EntityPlayer)Minecraft.func_71410_x().field_71439_g)) {
            super.func_76979_b(par1Entity, par2, par4, par6, par8, par9);
        }
    }

    protected void renderLivingLabel(EntityNPCInterface npc, double d, double d1, double d2, int i, Object ... obs) {
        FontRenderer fontrenderer = this.func_76983_a();
        i = npc.func_70070_b(0.0f);
        int j = i % 65536;
        int k = i / 65536;
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)j / 1.0f), (float)((float)k / 1.0f));
        float f1 = npc.baseHeight / 5.0f * (float)npc.display.modelSize;
        float f2 = 0.01666667f * f1;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)d + 0.0f), (float)((float)d1), (float)((float)d2));
        GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-this.field_76990_c.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)this.field_76990_c.field_78732_j, (float)1.0f, (float)0.0f, (float)0.0f);
        Tessellator tessellator = Tessellator.field_78398_a;
        float height = f1 / 6.5f;
        for (j = 0; j < obs.length; j += 2) {
            float scale = ((Float)obs[j + 1]).floatValue();
            height += f1 / 6.5f * scale;
            GL11.glPushMatrix();
            GL11.glDisable((int)2896);
            GL11.glDepthMask((boolean)false);
            GL11.glDisable((int)3008);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glDisable((int)3553);
            String s = obs[j].toString();
            GL11.glTranslatef((float)0.0f, (float)height, (float)0.0f);
            GL11.glScalef((float)(-f2 * scale), (float)(-f2 * scale), (float)(f2 * scale));
            tessellator.func_78382_b();
            int size = fontrenderer.func_78256_a(s) / 2;
            tessellator.func_78369_a(0.0f, 0.0f, 0.0f, 0.25f);
            tessellator.func_78377_a((double)(-size - 1), -1.0, 0.0);
            tessellator.func_78377_a((double)(-size - 1), 8.0, 0.0);
            tessellator.func_78377_a((double)(size + 1), 8.0, 0.0);
            tessellator.func_78377_a((double)(size + 1), -1.0, 0.0);
            tessellator.func_78381_a();
            GL11.glEnable((int)3553);
            GL11.glEnable((int)2929);
            GL11.glDepthMask((boolean)true);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            int color = npc.faction.color;
            fontrenderer.func_78276_b(s, -fontrenderer.func_78256_a(s) / 2, 0, color);
            GL11.glPopMatrix();
        }
        GL11.glEnable((int)3008);
        GL11.glEnable((int)2896);
        GL11.glDisable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }

    protected void renderPlayerScale(EntityNPCInterface npc, float f) {
        GL11.glScalef((float)(npc.scaleX / 5.0f * (float)npc.display.modelSize), (float)(npc.scaleY / 5.0f * (float)npc.display.modelSize), (float)(npc.scaleZ / 5.0f * (float)npc.display.modelSize));
    }

    protected void renderPlayerSleep(EntityNPCInterface npc, double d, double d1, double d2) {
        this.field_76989_e = (float)npc.display.modelSize / 10.0f;
        float xOffset = 0.0f;
        float yOffset = npc.currentAnimation == EnumAnimation.NONE ? npc.ais.bodyOffsetY / 10.0f - 0.5f : 0.0f;
        float zOffset = 0.0f;
        if (npc.func_70089_S()) {
            if (npc.func_70608_bn()) {
                xOffset = (float)(-Math.cos(Math.toRadians(180 - npc.ais.orientation)));
                zOffset = (float)(-Math.sin(Math.toRadians(npc.ais.orientation)));
                yOffset += 0.14f;
            } else if (npc.func_70115_ae()) {
                yOffset -= 0.5f - ((EntityCustomNpc)npc).modelData.getLegsY() * 0.8f;
            }
        }
        this.renderLiving(npc, d, d1, d2, xOffset, yOffset, zOffset);
    }

    private void renderLiving(EntityNPCInterface npc, double d, double d1, double d2, float xoffset, float yoffset, float zoffset) {
        xoffset = xoffset / 5.0f * (float)npc.display.modelSize;
        yoffset = yoffset / 5.0f * (float)npc.display.modelSize;
        zoffset = zoffset / 5.0f * (float)npc.display.modelSize;
        super.func_77039_a((EntityLivingBase)npc, d + (double)xoffset, d1 + (double)yoffset, d2 + (double)zoffset);
    }

    protected void func_77043_a(EntityLivingBase entity, float f, float f1, float f2) {
        EntityNPCInterface npc = (EntityNPCInterface)entity;
        if (npc.func_70089_S() && npc.func_70608_bn()) {
            GL11.glRotatef((float)npc.ais.orientation, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)this.func_77037_a((EntityLivingBase)npc), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        } else if (npc.func_70089_S() && npc.currentAnimation == EnumAnimation.CRAWLING) {
            GL11.glRotatef((float)(270.0f - f1), (float)0.0f, (float)1.0f, (float)0.0f);
            float scale = (float)((EntityCustomNpc)npc).display.modelSize / 5.0f;
            GL11.glTranslated((double)(-scale + ((EntityCustomNpc)npc).modelData.getLegsY() * scale), (double)0.14f, (double)0.0);
            GL11.glRotatef((float)270.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        } else {
            super.func_77043_a((EntityLivingBase)npc, f, f1, f2);
        }
    }

    public void func_77033_b(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6) {
        this.renderName((EntityNPCInterface)par1EntityLivingBase, par2, par4, par6);
    }

    protected void func_77041_b(EntityLivingBase entityliving, float f) {
        this.renderPlayerScale((EntityNPCInterface)entityliving, f);
    }

    public void func_76986_a(EntityLiving entityliving, double d, double d1, double d2, float f, float f1) {
        EntityNPCInterface npc = (EntityNPCInterface)entityliving;
        if (npc.isKilled() && npc.stats.hideKilledBody && npc.field_70725_aQ > 20) {
            return;
        }
        if ((npc.display.showBossBar == 1 || npc.display.showBossBar == 2 && npc.isAttacking()) && !npc.isKilled() && npc.field_70725_aQ <= 20 && npc.canSee((Entity)Minecraft.func_71410_x().field_71439_g)) {
            BossStatus.func_82824_a((IBossDisplayData)npc, (boolean)true);
        }
        if (npc.ais.standingType == EnumStandingType.HeadRotation && !npc.isWalking() && !npc.isInteracting()) {
            npc.field_70760_ar = npc.field_70761_aq = (float)npc.ais.orientation;
        }
        staticRenderManager = this.field_76990_c;
        this.doRenderLiving(npc, d, d1, d2, f, f1);
    }

    public void doRenderLiving(EntityNPCInterface p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        if (MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Pre((EntityLivingBase)p_76986_1_, (RendererLivingEntity)this, p_76986_2_, p_76986_4_, p_76986_6_))) {
            return;
        }
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        this.field_77045_g.field_78095_p = this.func_77040_d((EntityLivingBase)p_76986_1_, p_76986_9_);
        if (this.field_77046_h != null) {
            this.field_77046_h.field_78095_p = this.field_77045_g.field_78095_p;
        }
        this.field_77045_g.field_78093_q = p_76986_1_.func_70115_ae();
        if (this.field_77046_h != null) {
            this.field_77046_h.field_78093_q = this.field_77045_g.field_78093_q;
        }
        this.field_77045_g.field_78091_s = p_76986_1_.func_70631_g_();
        if (this.field_77046_h != null) {
            this.field_77046_h.field_78091_s = this.field_77045_g.field_78091_s;
        }
        try {
            float f10;
            float f9;
            float f8;
            int j;
            float f4;
            float f2 = this.interpolateRotation(p_76986_1_.field_70760_ar, p_76986_1_.field_70761_aq, p_76986_9_);
            float f3 = this.interpolateRotation(p_76986_1_.field_70758_at, p_76986_1_.field_70759_as, p_76986_9_);
            if (p_76986_1_.func_70115_ae() && p_76986_1_.field_70154_o instanceof EntityLivingBase) {
                EntityLivingBase entitylivingbase1 = (EntityLivingBase)p_76986_1_.field_70154_o;
                f2 = this.interpolateRotation(entitylivingbase1.field_70760_ar, entitylivingbase1.field_70761_aq, p_76986_9_);
                f4 = MathHelper.func_76142_g((float)(f3 - f2));
                if (f4 < -85.0f) {
                    f4 = -85.0f;
                }
                if (f4 >= 85.0f) {
                    f4 = 85.0f;
                }
                f2 = f3 - f4;
                if (f4 * f4 > 2500.0f) {
                    f2 += f4 * 0.2f;
                }
            }
            float f13 = p_76986_1_.field_70127_C + (p_76986_1_.field_70125_A - p_76986_1_.field_70127_C) * p_76986_9_;
            this.func_77039_a((EntityLivingBase)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_);
            f4 = this.func_77044_a((EntityLivingBase)p_76986_1_, p_76986_9_);
            this.func_77043_a((EntityLivingBase)p_76986_1_, f4, f2, p_76986_9_);
            float f5 = 0.0625f;
            GL11.glEnable((int)32826);
            GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
            this.func_77041_b((EntityLivingBase)p_76986_1_, p_76986_9_);
            GL11.glTranslatef((float)0.0f, (float)(-24.0f * f5 - 0.0078125f), (float)0.0f);
            float f6 = p_76986_1_.field_70722_aY + (p_76986_1_.field_70721_aZ - p_76986_1_.field_70722_aY) * p_76986_9_;
            float f7 = p_76986_1_.field_70754_ba - p_76986_1_.field_70721_aZ * (1.0f - p_76986_9_);
            if (p_76986_1_.func_70631_g_()) {
                f7 *= 3.0f;
            }
            if (f6 > 1.0f) {
                f6 = 1.0f;
            }
            GL11.glEnable((int)3008);
            this.field_77045_g.func_78086_a((EntityLivingBase)p_76986_1_, f7, f6, p_76986_9_);
            this.func_77036_a((EntityLivingBase)p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);
            for (int i = 0; i < 4; ++i) {
                j = this.func_77032_a((EntityLivingBase)p_76986_1_, i, p_76986_9_);
                if (j <= 0) continue;
                this.field_77046_h.func_78086_a((EntityLivingBase)p_76986_1_, f7, f6, p_76986_9_);
                this.field_77046_h.func_78088_a((Entity)p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);
                if ((j & 0xF0) == 16) {
                    this.func_82408_c((EntityLivingBase)p_76986_1_, i, p_76986_9_);
                    this.field_77046_h.func_78088_a((Entity)p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);
                }
                if ((j & 0xF) == 15) {
                    f8 = (float)p_76986_1_.field_70173_aa + p_76986_9_;
                    this.func_110776_a(new ResourceLocation("textures/misc/enchanted_item_glint.png"));
                    GL11.glEnable((int)3042);
                    f9 = 0.5f;
                    GL11.glColor4f((float)f9, (float)f9, (float)f9, (float)1.0f);
                    GL11.glDepthFunc((int)514);
                    GL11.glDepthMask((boolean)false);
                    for (int k = 0; k < 2; ++k) {
                        GL11.glDisable((int)2896);
                        f10 = 0.76f;
                        GL11.glColor4f((float)(0.5f * f10), (float)(0.25f * f10), (float)(0.8f * f10), (float)1.0f);
                        GL11.glBlendFunc((int)768, (int)1);
                        GL11.glMatrixMode((int)5890);
                        GL11.glLoadIdentity();
                        float f11 = f8 * (0.001f + (float)k * 0.003f) * 20.0f;
                        float f12 = 0.33333334f;
                        GL11.glScalef((float)f12, (float)f12, (float)f12);
                        GL11.glRotatef((float)(30.0f - (float)k * 60.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                        GL11.glTranslatef((float)0.0f, (float)f11, (float)0.0f);
                        GL11.glMatrixMode((int)5888);
                        this.field_77046_h.func_78088_a((Entity)p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);
                    }
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    GL11.glMatrixMode((int)5890);
                    GL11.glDepthMask((boolean)true);
                    GL11.glLoadIdentity();
                    GL11.glMatrixMode((int)5888);
                    GL11.glEnable((int)2896);
                    GL11.glDisable((int)3042);
                    GL11.glDepthFunc((int)515);
                }
                GL11.glDisable((int)3042);
                GL11.glEnable((int)3008);
            }
            GL11.glDepthMask((boolean)true);
            this.func_77029_c((EntityLivingBase)p_76986_1_, p_76986_9_);
            float f14 = p_76986_1_.func_70013_c(p_76986_9_);
            j = this.func_77030_a((EntityLivingBase)p_76986_1_, f14, p_76986_9_);
            OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77476_b);
            GL11.glDisable((int)3553);
            OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77478_a);
            TintData tintData = p_76986_1_.display.tintData;
            if ((j >> 24 & 0xFF) > 0 || p_76986_1_.field_70737_aN > 0 || p_76986_1_.field_70725_aQ > 0 || tintData.isTintEnabled() && tintData.isGeneralTintEnabled()) {
                GL11.glDisable((int)3553);
                GL11.glDisable((int)3008);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glDepthFunc((int)514);
                if (!(p_76986_1_.field_70737_aN <= 0 && p_76986_1_.field_70725_aQ <= 0 || tintData.isTintEnabled() && !tintData.isHurtTintEnabled())) {
                    float b;
                    float g;
                    float r;
                    if (tintData.isTintEnabled()) {
                        r = (float)(tintData.getHurtTint() >> 16 & 0xFF) / 255.0f * f14;
                        g = (float)(tintData.getHurtTint() >> 8 & 0xFF) / 255.0f * f14;
                        b = (float)(tintData.getHurtTint() & 0xFF) / 255.0f * f14;
                    } else {
                        r = f14;
                        g = 0.0f;
                        b = 0.0f;
                    }
                    GL11.glColor4f((float)r, (float)g, (float)b, (float)0.4f);
                    this.field_77045_g.func_78088_a((Entity)p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);
                    for (int l = 0; l < 4; ++l) {
                        if (this.func_77035_b((EntityLivingBase)p_76986_1_, l, p_76986_9_) < 0) continue;
                        GL11.glColor4f((float)r, (float)g, (float)b, (float)0.4f);
                        this.field_77046_h.func_78088_a((Entity)p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);
                    }
                }
                if ((j >> 24 & 0xFF) > 0) {
                    f8 = (float)(j >> 16 & 0xFF) / 255.0f;
                    f9 = (float)(j >> 8 & 0xFF) / 255.0f;
                    float f15 = (float)(j & 0xFF) / 255.0f;
                    f10 = (float)(j >> 24 & 0xFF) / 255.0f;
                    GL11.glColor4f((float)f8, (float)f9, (float)f15, (float)f10);
                    this.field_77045_g.func_78088_a((Entity)p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);
                    for (int i1 = 0; i1 < 4; ++i1) {
                        if (this.func_77035_b((EntityLivingBase)p_76986_1_, i1, p_76986_9_) < 0) continue;
                        GL11.glColor4f((float)f8, (float)f9, (float)f15, (float)f10);
                        this.field_77046_h.func_78088_a((Entity)p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);
                    }
                }
                GL11.glDepthFunc((int)515);
                GL11.glDisable((int)3042);
                GL11.glEnable((int)3008);
                GL11.glEnable((int)3553);
            }
            GL11.glDisable((int)32826);
        }
        catch (Exception exception) {
            // empty catch block
        }
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77476_b);
        GL11.glEnable((int)3553);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77478_a);
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
        this.func_77033_b((EntityLivingBase)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_);
        MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Post((EntityLivingBase)p_76986_1_, (RendererLivingEntity)this, p_76986_2_, p_76986_4_, p_76986_6_));
        this.func_110827_b((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    private float interpolateRotation(float p_77034_1_, float p_77034_2_, float p_77034_3_) {
        float f3;
        for (f3 = p_77034_2_ - p_77034_1_; f3 < -180.0f; f3 += 360.0f) {
        }
        while (f3 >= 180.0f) {
            f3 -= 360.0f;
        }
        return p_77034_1_ + p_77034_3_ * f3;
    }

    protected int func_77030_a(EntityLivingBase p_77030_1_, float p_77030_2_, float p_77030_3_) {
        EntityNPCInterface npc = (EntityNPCInterface)p_77030_1_;
        TintData tintData = npc.display.tintData;
        int alpha = (int)(255.0 * ((double)tintData.getGeneralAlpha() / 100.0)) << 24;
        return tintData.isTintEnabled() && tintData.isGeneralTintEnabled() ? tintData.getGeneralTint() + alpha : 0;
    }

    protected void func_77036_a(EntityLivingBase entityliving, float par2, float par3, float par4, float par5, float par6, float par7) {
        EntityNPCInterface npc = (EntityNPCInterface)entityliving;
        if (GeckoAddonClient.Instance.isGeckoModel(this.field_77045_g)) {
            GeckoAddonClient.Instance.geckoRenderModel((ModelMPM)this.field_77045_g, npc, npc.field_70177_z, Minecraft.func_71410_x().field_71428_T.field_74281_c);
        } else if (this.func_110775_a((Entity)entityliving) != null) {
            super.func_77036_a(entityliving, par2, par3, par4, par5, par6, par7);
        }
        if (!npc.display.skinOverlayData.overlayList.isEmpty()) {
            for (ISkinOverlay overlayData : npc.display.skinOverlayData.overlayList.values()) {
                try {
                    ImageData imageData;
                    if (((SkinOverlay)overlayData).texture.isEmpty() || !(imageData = ClientCacheHandler.getImageData(((SkinOverlay)overlayData).texture)).imageLoaded()) continue;
                    try {
                        imageData.bindTexture();
                    }
                    catch (Exception e) {
                        continue;
                    }
                    GL11.glEnable((int)3042);
                    if (overlayData.getBlend()) {
                        GL11.glBlendFunc((int)1, (int)1);
                    } else {
                        GL11.glBlendFunc((int)770, (int)771);
                    }
                    GL11.glAlphaFunc((int)516, (float)0.003921569f);
                    if (overlayData.getGlow()) {
                        GL11.glDisable((int)2896);
                        Minecraft.func_71410_x().field_71460_t.func_78483_a(0.0);
                    }
                    RenderNPCInterface.glColor(overlayData.getColor(), overlayData.getAlpha());
                    GL11.glDepthMask((!npc.func_82150_aj() ? 1 : 0) != 0);
                    GL11.glPushMatrix();
                    GL11.glMatrixMode((int)5890);
                    GL11.glLoadIdentity();
                    GL11.glTranslatef((float)((float)npc.display.overlayRenderTicks * 0.001f * overlayData.getSpeedX()), (float)((float)npc.display.overlayRenderTicks * 0.001f * overlayData.getSpeedY()), (float)0.0f);
                    GL11.glScalef((float)overlayData.getTextureScaleX(), (float)overlayData.getTextureScaleY(), (float)1.0f);
                    GL11.glMatrixMode((int)5888);
                    float scale = 1.005f * overlayData.getSize();
                    GL11.glTranslatef((float)overlayData.getOffsetX(), (float)overlayData.getOffsetY(), (float)overlayData.getOffsetZ());
                    GL11.glScalef((float)scale, (float)scale, (float)scale);
                    if (this.field_77045_g instanceof ModelMPM) {
                        ((ModelMPM)this.field_77045_g).isArmor = true;
                        this.field_77045_g.func_78088_a((Entity)entityliving, par2, par3, par4, par5, par6, par7);
                        ((ModelMPM)this.field_77045_g).isArmor = false;
                    } else {
                        this.field_77045_g.func_78088_a((Entity)entityliving, par2, par3, par4, par5, par6, par7);
                    }
                    GL11.glPopMatrix();
                    GL11.glMatrixMode((int)5890);
                    GL11.glLoadIdentity();
                    GL11.glMatrixMode((int)5888);
                    GL11.glEnable((int)2896);
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    GL11.glDepthFunc((int)515);
                    GL11.glDisable((int)3042);
                    GL11.glAlphaFunc((int)516, (float)0.1f);
                    Minecraft.func_71410_x().field_71460_t.func_78463_b(0.0);
                }
                catch (Exception exception) {}
            }
            ++npc.display.overlayRenderTicks;
        }
    }

    private static void glColor(int color, float alpha) {
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        GL11.glColor4f((float)r, (float)g, (float)b, (float)alpha);
    }

    protected float func_77044_a(EntityLivingBase par1EntityLiving, float par2) {
        EntityNPCInterface npc = (EntityNPCInterface)par1EntityLiving;
        if (npc.isKilled() || npc.display.disableLivingAnimation) {
            return 0.0f;
        }
        return super.func_77044_a(par1EntityLiving, par2);
    }

    protected void func_77039_a(EntityLivingBase entityliving, double d, double d1, double d2) {
        this.renderPlayerSleep((EntityNPCInterface)entityliving, d, d1, d2);
    }

    public ResourceLocation func_110775_a(Entity entity) {
        EntityNPCInterface npc = (EntityNPCInterface)entity;
        if (npc.textureLocation != null) {
            return npc.textureLocation;
        }
        if (npc.display.skinType == 0) {
            if (npc.display.texture.isEmpty()) {
                npc.textureLocation = this.fallBackSkin(npc);
                return npc.textureLocation;
            }
            try {
                if (npc instanceof EntityCustomNpc && ((EntityCustomNpc)npc).modelData.entityClass == null) {
                    npc.textureLocation = this.adjustLocalTexture(npc, new ResourceLocation(npc.display.texture));
                }
                ResourceLocation resLoc = new ResourceLocation(npc.display.texture);
                Minecraft.func_71410_x().func_110442_L().func_110536_a(resLoc);
                npc.textureLocation = resLoc;
            }
            catch (IOException ignored) {
                return this.fallBackSkin(npc);
            }
        } else if (npc.display.skinType == 1 && npc.display.playerProfile != null) {
            Minecraft minecraft = Minecraft.func_71410_x();
            Map map = minecraft.func_152342_ad().func_152788_a(npc.display.playerProfile);
            if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
                npc.textureLocation = minecraft.func_152342_ad().func_152792_a((MinecraftProfileTexture)map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
            }
            LastTextureTick = 0L;
        } else if (npc.display.skinType == 2 || npc.display.skinType == 3) {
            if (npc.display.url.isEmpty()) {
                return this.fallBackSkin(npc);
            }
            ResourceLocation location = new ResourceLocation("skins/" + (npc.display.skinType + npc.display.url).hashCode());
            try {
                if (ClientCacheHandler.isCachedNPC(location)) {
                    ResourceLocation loc = ClientCacheHandler.getNPCTexture(npc.display.url, npc.display.skinType == 3, location).getLocation();
                    if (loc == null) {
                        return this.fallBackSkin(npc);
                    }
                    npc.textureLocation = loc;
                }
                if (LastTextureTick >= 5L) {
                    ResourceLocation loc = ClientCacheHandler.getNPCTexture(npc.display.url, npc.display.skinType == 3, location).getLocation();
                    if (loc == null) {
                        return this.fallBackSkin(npc);
                    }
                    npc.textureLocation = loc;
                    LastTextureTick = 0L;
                }
                return this.fallBackSkin(npc);
            }
            catch (Exception ignored) {
                return this.fallBackSkin(npc);
            }
        } else {
            return this.fallBackSkin(npc);
        }
        return npc.textureLocation == null ? this.fallBackSkin(npc) : npc.textureLocation;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private ResourceLocation adjustLocalTexture(EntityNPCInterface npc, ResourceLocation location) throws IOException {
        try (InputStream inputstream = null;){
            TextureManager texturemanager = Minecraft.func_71410_x().func_110434_K();
            ResourceLocation skinLocation = location;
            try {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                byte[] hash = digest.digest(npc.display.texture.getBytes("UTF-8"));
                StringBuilder sb = new StringBuilder(2 * hash.length);
                for (byte b : hash) {
                    sb.append(String.format("%02x", b & 0xFF));
                }
                skinLocation = npc.display.modelType == 0 ? new ResourceLocation("skin/" + sb.toString()) : new ResourceLocation("skin64/" + sb.toString());
            }
            catch (Exception digest) {
                // empty catch block
            }
            texturemanager.func_147645_c(skinLocation);
            IResource iresource = Minecraft.func_71410_x().func_110442_L().func_110536_a(location);
            inputstream = iresource.func_110527_b();
            BufferedImage bufferedimage = ImageIO.read(inputstream);
            int totalWidth = bufferedimage.getWidth();
            int totalHeight = bufferedimage.getHeight();
            if (totalWidth == totalHeight && npc.display.modelType == 0) {
                bufferedimage = bufferedimage.getSubimage(0, 0, totalWidth, totalWidth / 2);
            }
            ImageDownloadAlt object = new ImageDownloadAlt(null, npc.display.texture, SkinManager.field_152793_a, (IImageBuffer)new ImageBufferDownloadAlt(true));
            object.setBufferedImage(bufferedimage);
            texturemanager.func_110579_a(skinLocation, (ITextureObject)object);
            ResourceLocation resourceLocation = skinLocation;
            return resourceLocation;
        }
    }

    private ResourceLocation fallBackSkin(EntityNPCInterface npcInterface) {
        switch (npcInterface.display.modelType) {
            case 2: {
                return alex;
            }
            case 1: {
                return steve64;
            }
        }
        return AbstractClientPlayer.field_110314_b;
    }

    static {
        steve64 = new ResourceLocation("customnpcs", "textures/entity/64-Textures/humanmale/Steve.png");
        alex = new ResourceLocation("customnpcs", "textures/entity/64-Textures/humanfemale/Alex.png");
    }
}

