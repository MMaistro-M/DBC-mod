/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderPlayerEvent$Specials$Post
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.glu.Project
 */
package noppes.npcs.client.renderer;

import cpw.mods.fml.common.Loader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.renderer.ImageData;
import noppes.npcs.client.renderer.RenderCNPCHand;
import noppes.npcs.controllers.data.SkinOverlay;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;

public class RenderCNPCPlayer
extends RenderPlayer {
    public static boolean hasMPM = false;
    public Minecraft mc = Minecraft.func_71410_x();
    public RenderCNPCHand itemRenderer = new RenderCNPCHand(this.mc);
    public float tempRenderPartialTicks;
    private float debugCamFOV;
    private float prevDebugCamFOV;
    private float fovModifierHand;
    private float fovModifierHandPrev;
    private float fovMultiplierTemp;

    public RenderCNPCPlayer() {
        this.field_77109_a = (ModelBiped)this.field_77045_g;
        this.field_77108_b = new ModelBiped(1.0f);
        this.field_77111_i = new ModelBiped(0.5f);
        this.func_76976_a(RenderManager.field_78727_a);
        if (Loader.isModLoaded((String)"moreplayermodels")) {
            hasMPM = true;
        }
    }

    private boolean preRenderOverlay(SkinOverlay overlayData, EntityPlayer player) {
        if (overlayData.texture.isEmpty()) {
            return false;
        }
        ImageData imageData = ClientCacheHandler.getImageData(overlayData.texture);
        if (!imageData.imageLoaded()) {
            return false;
        }
        try {
            imageData.bindTexture();
        }
        catch (Exception e) {
            return false;
        }
        GL11.glEnable((int)3042);
        if (overlayData.blend) {
            GL11.glBlendFunc((int)1, (int)1);
        } else {
            GL11.glBlendFunc((int)770, (int)771);
        }
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        if (overlayData.glow) {
            GL11.glDisable((int)2896);
            Minecraft.func_71410_x().field_71460_t.func_78483_a(0.0);
            RenderHelper.func_74518_a();
        }
        RenderCNPCPlayer.glColor(overlayData.getColor(), overlayData.getAlpha());
        GL11.glDepthMask((!player.func_82150_aj() ? 1 : 0) != 0);
        GL11.glPushMatrix();
        GL11.glMatrixMode((int)5890);
        GL11.glLoadIdentity();
        GL11.glTranslatef((float)((float)overlayData.ticks * 0.001f * overlayData.speedX), (float)((float)overlayData.ticks * 0.001f * overlayData.speedY), (float)0.0f);
        GL11.glScalef((float)overlayData.scaleX, (float)overlayData.scaleY, (float)1.0f);
        ++overlayData.ticks;
        GL11.glMatrixMode((int)5888);
        GL11.glTranslatef((float)overlayData.offsetX, (float)overlayData.offsetY, (float)overlayData.offsetZ);
        GL11.glScalef((float)overlayData.size, (float)overlayData.size, (float)overlayData.size);
        return true;
    }

    private static void glColor(int color, float alpha) {
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        GL11.glColor4f((float)r, (float)g, (float)b, (float)alpha);
    }

    public void postRenderOverlay() {
        GL11.glPopMatrix();
        GL11.glMatrixMode((int)5890);
        GL11.glLoadIdentity();
        GL11.glMatrixMode((int)5888);
        GL11.glEnable((int)2896);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        Minecraft.func_71410_x().field_71460_t.func_78463_b(0.0);
        RenderHelper.func_74519_b();
    }

    protected void func_77036_a(EntityLivingBase entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        EntityPlayer player = (EntityPlayer)entity;
        if (!entity.func_82150_aj() && ClientCacheHandler.skinOverlays.containsKey(player.func_110124_au())) {
            for (SkinOverlay overlayData : ClientCacheHandler.skinOverlays.get(player.func_110124_au()).values()) {
                ImageData imageData;
                if (overlayData.texture.isEmpty() || !(imageData = ClientCacheHandler.getImageData(overlayData.texture)).imageLoaded() || !this.preRenderOverlay(overlayData, player)) continue;
                if (hasMPM) {
                    this.renderMorePlayerModel(entity, par2, par3, par4, par5, par6, par7);
                } else {
                    this.field_77109_a.func_78088_a((Entity)entity, par2, par3, par4, par5, par6, par7);
                }
                this.postRenderOverlay();
            }
        }
    }

    protected int func_77032_a(AbstractClientPlayer p_77032_1_, int p_77032_2_, float p_77032_3_) {
        return -1;
    }

    public void func_77033_b(EntityLivingBase p_77033_1_, double p_77033_2_, double p_77033_4_, double p_77033_6_) {
        Render render = RenderManager.field_78727_a.func_78713_a((Entity)p_77033_1_);
        if (render instanceof RenderPlayer) {
            RenderPlayer renderPlayer = (RenderPlayer)render;
            renderPlayer.func_77033_b(p_77033_1_, p_77033_2_, p_77033_4_, p_77033_6_);
        }
    }

    public void func_77043_a(EntityLivingBase p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
        Render render = RenderManager.field_78727_a.func_78713_a((Entity)p_77043_1_);
        if (render instanceof RenderPlayer) {
            RenderPlayer renderPlayer = (RenderPlayer)render;
            renderPlayer.func_77043_a(p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
        }
    }

    protected void func_96449_a(AbstractClientPlayer p_96449_1_, double p_96449_2_, double p_96449_4_, double p_96449_6_, String p_96449_8_, float p_96449_9_, double p_96449_10_) {
    }

    public void renderHand(float partialTicks, int renderPass) {
        Minecraft mc = Minecraft.func_71410_x();
        EntityRenderer entityRenderer = mc.field_71460_t;
        if (entityRenderer.field_78532_q <= 0) {
            GL11.glMatrixMode((int)5889);
            GL11.glLoadIdentity();
            float f1 = 0.07f;
            if (mc.field_71474_y.field_74337_g) {
                GL11.glTranslatef((float)((float)(-(renderPass * 2 - 1)) * f1), (float)0.0f, (float)0.0f);
            }
            Project.gluPerspective((float)this.getFOVModifier(partialTicks, false), (float)((float)mc.field_71443_c / (float)mc.field_71440_d), (float)0.05f, (float)((float)(mc.field_71474_y.field_151451_c * 16) * 2.0f));
            if (mc.field_71442_b.func_78747_a()) {
                float f2 = 0.6666667f;
                GL11.glScalef((float)1.0f, (float)f2, (float)1.0f);
            }
            GL11.glMatrixMode((int)5888);
            GL11.glLoadIdentity();
            if (mc.field_71474_y.field_74337_g) {
                GL11.glTranslatef((float)((float)(renderPass * 2 - 1) * 0.1f), (float)0.0f, (float)0.0f);
            }
            GL11.glPushMatrix();
            this.hurtCameraEffect(partialTicks);
            if (mc.field_71474_y.field_74336_f) {
                this.setupViewBobbing(partialTicks);
            }
            if (!(mc.field_71474_y.field_74320_O != 0 || mc.field_71451_h.func_70608_bn() || mc.field_71474_y.field_74319_N || mc.field_71442_b.func_78747_a())) {
                entityRenderer.func_78463_b((double)partialTicks);
                this.itemRenderer.renderOverlayInFirstPerson(partialTicks);
                entityRenderer.func_78483_a((double)partialTicks);
            }
            GL11.glPopMatrix();
            if (mc.field_71474_y.field_74336_f) {
                this.setupViewBobbing(partialTicks);
            }
        }
    }

    private float getFOVModifier(float p_78481_1_, boolean p_78481_2_) {
        Block block;
        Minecraft mc = Minecraft.func_71410_x();
        EntityLivingBase entityplayer = mc.field_71451_h;
        float f1 = 70.0f;
        if (p_78481_2_) {
            f1 = mc.field_71474_y.field_74334_X;
            f1 *= this.fovModifierHandPrev + (this.fovModifierHand - this.fovModifierHandPrev) * p_78481_1_;
        }
        if (entityplayer.func_110143_aJ() <= 0.0f) {
            float f2 = (float)entityplayer.field_70725_aQ + p_78481_1_;
            f1 /= (1.0f - 500.0f / (f2 + 500.0f)) * 2.0f + 1.0f;
        }
        if ((block = ActiveRenderInfo.func_151460_a((World)mc.field_71441_e, (EntityLivingBase)entityplayer, (float)p_78481_1_)).func_149688_o() == Material.field_151586_h) {
            f1 = f1 * 60.0f / 70.0f;
        }
        return f1 + this.prevDebugCamFOV + (this.debugCamFOV - this.prevDebugCamFOV) * p_78481_1_;
    }

    private void hurtCameraEffect(float p_78482_1_) {
        float f2;
        Minecraft mc = Minecraft.func_71410_x();
        EntityLivingBase entitylivingbase = mc.field_71451_h;
        float f1 = (float)entitylivingbase.field_70737_aN - p_78482_1_;
        if (entitylivingbase.func_110143_aJ() <= 0.0f) {
            f2 = (float)entitylivingbase.field_70725_aQ + p_78482_1_;
            GL11.glRotatef((float)(40.0f - 8000.0f / (f2 + 200.0f)), (float)0.0f, (float)0.0f, (float)1.0f);
        }
        if (f1 >= 0.0f) {
            f1 /= (float)entitylivingbase.field_70738_aO;
            f1 = MathHelper.func_76126_a((float)(f1 * f1 * f1 * f1 * (float)Math.PI));
            f2 = entitylivingbase.field_70739_aP;
            GL11.glRotatef((float)(-f2), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(-f1 * 14.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)f2, (float)0.0f, (float)1.0f, (float)0.0f);
        }
    }

    private void setupViewBobbing(float p_78475_1_) {
        Minecraft mc = Minecraft.func_71410_x();
        if (mc.field_71451_h instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)mc.field_71451_h;
            float f1 = entityplayer.field_70140_Q - entityplayer.field_70141_P;
            float f2 = -(entityplayer.field_70140_Q + f1 * p_78475_1_);
            float f3 = entityplayer.field_71107_bF + (entityplayer.field_71109_bG - entityplayer.field_71107_bF) * p_78475_1_;
            float f4 = entityplayer.field_70727_aS + (entityplayer.field_70726_aT - entityplayer.field_70727_aS) * p_78475_1_;
            GL11.glTranslatef((float)(MathHelper.func_76126_a((float)(f2 * (float)Math.PI)) * f3 * 0.5f), (float)(-Math.abs(MathHelper.func_76134_b((float)(f2 * (float)Math.PI)) * f3)), (float)0.0f);
            GL11.glRotatef((float)(MathHelper.func_76126_a((float)(f2 * (float)Math.PI)) * f3 * 3.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)(Math.abs(MathHelper.func_76134_b((float)(f2 * (float)Math.PI - 0.2f)) * f3) * 5.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)f4, (float)1.0f, (float)0.0f, (float)0.0f);
        }
    }

    public void renderFirstPersonArmOverlay(EntityPlayer player) {
        if (player == null || this.field_77109_a == null) {
            return;
        }
        float gender = -1.0f;
        try {
            Class<?> RenderPlayerJBRA = Class.forName("JinRyuu.JBRA.RenderPlayerJBRA");
            gender = ((Float)RenderPlayerJBRA.getMethod("genGet", new Class[0]).invoke(null, new Object[0])).floatValue();
        }
        catch (Exception RenderPlayerJBRA) {
            // empty catch block
        }
        UUID uuid = player.func_110124_au();
        if (uuid == null || !ClientCacheHandler.skinOverlays.containsKey(uuid)) {
            return;
        }
        Map overlayMap = ClientCacheHandler.skinOverlays.get(uuid);
        if (overlayMap == null || overlayMap.isEmpty()) {
            return;
        }
        for (SkinOverlay overlayData : overlayMap.values()) {
            ImageData imageData;
            if (overlayData == null || overlayData.texture == null || overlayData.texture.isEmpty() || (imageData = ClientCacheHandler.getImageData(overlayData.texture)) == null || !imageData.imageLoaded() || !this.preRenderOverlay(overlayData, player)) continue;
            if (gender >= 2.0f) {
                GL11.glRotatef((float)7.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)0.015f, (float)0.0375f, (float)-0.0025f);
            }
            this.field_77109_a.field_78095_p = 0.0f;
            this.field_77109_a.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)player);
            if (this.field_77109_a.field_78112_f != null) {
                this.field_77109_a.field_78112_f.func_78785_a(0.0625f);
            }
            this.postRenderOverlay();
        }
    }

    public void renderMorePlayerModel(EntityLivingBase entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        Class<?> ModelMPMClass = null;
        Field isArmor = null;
        try {
            ModelMPMClass = Class.forName("noppes.mpm.client.model.ModelMPM");
            isArmor = ModelMPMClass.getDeclaredField("isArmor");
        }
        catch (Exception exception) {
            // empty catch block
        }
        if (ModelMPMClass != null && ModelMPMClass.isInstance(this.field_77109_a)) {
            try {
                if (isArmor != null) {
                    isArmor.setBoolean(this.field_77109_a, true);
                    this.field_77109_a.func_78088_a((Entity)entity, par2, par3, par4, par5, par6, par7);
                    isArmor.setBoolean(this.field_77109_a, false);
                }
            }
            catch (IllegalAccessException illegalAccessException) {
                // empty catch block
            }
        }
    }

    public void renderDBCModel(RenderPlayerEvent.Specials.Post event) {
        block14: {
            EntityPlayer player = event.entityPlayer;
            Class<?> RenderPlayerJBRA = null;
            Class<?> ModelBipedDBC = null;
            Class<?> ModelBipedBody = null;
            Method renderDBC = null;
            Field rot1 = null;
            Field rot2 = null;
            Field rot3 = null;
            Field rot4 = null;
            Field rot5 = null;
            Field rot6 = null;
            Object m = null;
            ModelRenderer bipedHead = null;
            ModelRenderer bipedBody = null;
            ModelRenderer bipedRA = null;
            ModelRenderer bipedLA = null;
            ModelRenderer bipedRL = null;
            ModelRenderer bipedLL = null;
            ModelRenderer Brightarm = null;
            ModelRenderer Bleftarm = null;
            ModelRenderer rightleg = null;
            ModelRenderer leftleg = null;
            ModelRenderer body = null;
            ModelRenderer hip = null;
            ModelRenderer waist = null;
            ModelRenderer bottom = null;
            ModelRenderer Bbreast = null;
            ModelRenderer Bbreast2 = null;
            ModelRenderer breast = null;
            ModelRenderer breast2 = null;
            float childScl = 0.0f;
            try {
                RenderPlayerJBRA = Class.forName("JinRyuu.JBRA.RenderPlayerJBRA");
                ModelBipedDBC = Class.forName("JinRyuu.JBRA.ModelBipedDBC");
                ModelBipedBody = Class.forName("JinRyuu.JRMCore.entity.ModelBipedBody");
                renderDBC = ModelBipedBody.getMethod("render", Entity.class, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE);
                rot1 = ModelBipedDBC.getField("rot1");
                rot2 = ModelBipedDBC.getField("rot2");
                rot3 = ModelBipedDBC.getField("rot3");
                rot4 = ModelBipedDBC.getField("rot4");
                rot5 = ModelBipedDBC.getField("rot5");
                rot6 = ModelBipedDBC.getField("rot6");
                m = RenderPlayerJBRA.getField("modelMain").get(event.renderer);
                ModelBipedBody.getField("isRiding").set(m, player.func_70115_ae());
                ModelBipedBody.getField("isChild").set(m, player.func_70631_g_());
                ModelBipedBody.getField("isSneak").set(m, player.func_70093_af());
                ModelBipedBody.getField("y").set(null, ModelBipedDBC.getField("y").get(null));
                bipedHead = (ModelRenderer)ModelBipedBody.getField("bipedHead").get(m);
                bipedBody = (ModelRenderer)ModelBipedBody.getField("bipedBody").get(m);
                bipedRA = (ModelRenderer)ModelBipedBody.getField("bipedRightArm").get(m);
                bipedLA = (ModelRenderer)ModelBipedBody.getField("bipedLeftArm").get(m);
                bipedRL = (ModelRenderer)ModelBipedBody.getField("bipedRightLeg").get(m);
                bipedLL = (ModelRenderer)ModelBipedBody.getField("bipedLeftLeg").get(m);
                Brightarm = (ModelRenderer)ModelBipedBody.getField("Brightarm").get(m);
                Bleftarm = (ModelRenderer)ModelBipedBody.getField("Bleftarm").get(m);
                rightleg = (ModelRenderer)ModelBipedBody.getField("rightleg").get(m);
                leftleg = (ModelRenderer)ModelBipedBody.getField("leftleg").get(m);
                body = (ModelRenderer)ModelBipedBody.getField("body").get(m);
                hip = (ModelRenderer)ModelBipedBody.getField("hip").get(m);
                waist = (ModelRenderer)ModelBipedBody.getField("waist").get(m);
                bottom = (ModelRenderer)ModelBipedBody.getField("bottom").get(m);
                Bbreast = (ModelRenderer)ModelBipedBody.getField("Bbreast").get(m);
                Bbreast2 = (ModelRenderer)ModelBipedBody.getField("Bbreast2").get(m);
                breast = (ModelRenderer)ModelBipedBody.getField("breast").get(m);
                breast2 = (ModelRenderer)ModelBipedBody.getField("breast2").get(m);
                childScl = ((Float)RenderPlayerJBRA.getMethod("childSclGet", new Class[0]).invoke(null, new Object[0])).floatValue();
            }
            catch (Exception exception) {
                // empty catch block
            }
            try {
                RenderPlayerJBRA = Class.forName("JinRyuu.JBRA.RenderPlayerJBRA");
                ModelBipedDBC = Class.forName("JinRyuu.JBRA.ModelBipedDBC");
                ModelBipedBody = Class.forName("JinRyuu.JRMCore.entity.ModelBipedBody");
                renderDBC = ModelBipedBody.getMethod("func_78088_a", Entity.class, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE);
                rot1 = ModelBipedDBC.getField("rot1");
                rot2 = ModelBipedDBC.getField("rot2");
                rot3 = ModelBipedDBC.getField("rot3");
                rot4 = ModelBipedDBC.getField("rot4");
                rot5 = ModelBipedDBC.getField("rot5");
                rot6 = ModelBipedDBC.getField("rot6");
                m = RenderPlayerJBRA.getField("modelMain").get(event.renderer);
                ModelBipedBody.getField("field_78093_q").set(m, player.func_70115_ae());
                ModelBipedBody.getField("field_78091_s").set(m, player.func_70631_g_());
                ModelBipedBody.getField("field_78117_n").set(m, player.func_70093_af());
                ModelBipedBody.getField("y").set(null, ModelBipedDBC.getField("y").get(null));
                bipedHead = (ModelRenderer)ModelBipedBody.getField("field_78116_c").get(m);
                bipedBody = (ModelRenderer)ModelBipedBody.getField("field_78115_e").get(m);
                bipedRA = (ModelRenderer)ModelBipedBody.getField("field_78112_f").get(m);
                bipedLA = (ModelRenderer)ModelBipedBody.getField("field_78113_g").get(m);
                bipedRL = (ModelRenderer)ModelBipedBody.getField("field_78123_h").get(m);
                bipedLL = (ModelRenderer)ModelBipedBody.getField("field_78124_i").get(m);
                Brightarm = (ModelRenderer)ModelBipedBody.getField("Brightarm").get(m);
                Bleftarm = (ModelRenderer)ModelBipedBody.getField("Bleftarm").get(m);
                rightleg = (ModelRenderer)ModelBipedBody.getField("rightleg").get(m);
                leftleg = (ModelRenderer)ModelBipedBody.getField("leftleg").get(m);
                body = (ModelRenderer)ModelBipedBody.getField("body").get(m);
                hip = (ModelRenderer)ModelBipedBody.getField("hip").get(m);
                waist = (ModelRenderer)ModelBipedBody.getField("waist").get(m);
                bottom = (ModelRenderer)ModelBipedBody.getField("bottom").get(m);
                Bbreast = (ModelRenderer)ModelBipedBody.getField("Bbreast").get(m);
                Bbreast2 = (ModelRenderer)ModelBipedBody.getField("Bbreast2").get(m);
                breast = (ModelRenderer)ModelBipedBody.getField("breast").get(m);
                breast2 = (ModelRenderer)ModelBipedBody.getField("breast2").get(m);
                childScl = ((Float)RenderPlayerJBRA.getMethod("childSclGet", new Class[0]).invoke(null, new Object[0])).floatValue();
            }
            catch (Exception exception) {
                // empty catch block
            }
            try {
                if (!ClientCacheHandler.skinOverlays.containsKey(player.func_110124_au())) break block14;
                for (SkinOverlay overlayData : ClientCacheHandler.skinOverlays.get(player.func_110124_au()).values()) {
                    ImageData imageData;
                    if (overlayData.texture.isEmpty() || !(imageData = ClientCacheHandler.getImageData(overlayData.texture)).imageLoaded()) continue;
                    try {
                        imageData.bindTexture();
                    }
                    catch (Exception e) {
                        continue;
                    }
                    if (!this.preRenderOverlay(overlayData, player)) continue;
                    bipedHead.field_78807_k = true;
                    renderDBC.invoke(m, player, Float.valueOf(((Float)rot1.get(m)).floatValue()), Float.valueOf(((Float)rot2.get(m)).floatValue()), Float.valueOf(((Float)rot3.get(m)).floatValue()), Float.valueOf(((Float)rot4.get(m)).floatValue()), Float.valueOf(((Float)rot5.get(m)).floatValue()), Float.valueOf(((Float)rot6.get(m)).floatValue()));
                    bipedHead.field_78807_k = false;
                    bipedBody.field_78807_k = true;
                    bipedRA.field_78807_k = true;
                    bipedLA.field_78807_k = true;
                    bipedRL.field_78807_k = true;
                    bipedLL.field_78807_k = true;
                    Brightarm.field_78807_k = true;
                    Bleftarm.field_78807_k = true;
                    rightleg.field_78807_k = true;
                    leftleg.field_78807_k = true;
                    body.field_78807_k = true;
                    hip.field_78807_k = true;
                    waist.field_78807_k = true;
                    bottom.field_78807_k = true;
                    Bbreast.field_78807_k = true;
                    Bbreast2.field_78807_k = true;
                    breast.field_78807_k = true;
                    breast2.field_78807_k = true;
                    if (player.func_70093_af()) {
                        GL11.glTranslatef((float)0.0f, (float)0.06f, (float)0.0f);
                    }
                    if (childScl > 1.5f) {
                        GL11.glTranslatef((float)0.0f, (float)-0.015f, (float)0.0f);
                        GL11.glScalef((float)1.025f, (float)1.025f, (float)1.025f);
                    } else if (childScl > 1.0f) {
                        GL11.glTranslatef((float)0.0f, (float)-0.01f, (float)0.0f);
                        GL11.glScalef((float)1.025f, (float)1.025f, (float)1.025f);
                    } else {
                        GL11.glTranslatef((float)0.0f, (float)0.0025f, (float)0.0f);
                        GL11.glScalef((float)1.02f, (float)1.02f, (float)1.02f);
                    }
                    renderDBC.invoke(m, player, Float.valueOf(((Float)rot1.get(m)).floatValue()), Float.valueOf(((Float)rot2.get(m)).floatValue()), Float.valueOf(((Float)rot3.get(m)).floatValue()), Float.valueOf(((Float)rot4.get(m)).floatValue()), Float.valueOf(((Float)rot5.get(m)).floatValue()), Float.valueOf(((Float)rot6.get(m)).floatValue()));
                    bipedBody.field_78807_k = false;
                    bipedRA.field_78807_k = false;
                    bipedLA.field_78807_k = false;
                    bipedRL.field_78807_k = false;
                    bipedLL.field_78807_k = false;
                    Brightarm.field_78807_k = false;
                    Bleftarm.field_78807_k = false;
                    rightleg.field_78807_k = false;
                    leftleg.field_78807_k = false;
                    body.field_78807_k = false;
                    hip.field_78807_k = false;
                    waist.field_78807_k = false;
                    bottom.field_78807_k = false;
                    Bbreast.field_78807_k = false;
                    Bbreast2.field_78807_k = false;
                    breast.field_78807_k = false;
                    breast2.field_78807_k = false;
                    this.postRenderOverlay();
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }
}

