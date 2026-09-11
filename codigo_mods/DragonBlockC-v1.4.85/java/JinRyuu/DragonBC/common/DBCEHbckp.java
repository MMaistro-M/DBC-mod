/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.jin_ryuu.SwordArtC.Main
 *  com.jin_ryuu.SwordArtC.proxy.Client
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.RenderLivingEvent$Post
 *  net.minecraftforge.event.entity.living.LivingDropsEvent
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.DragonBC.common;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.mod_JRMCore;
import com.jin_ryuu.SwordArtC.Main;
import com.jin_ryuu.SwordArtC.proxy.Client;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import org.lwjgl.opengl.GL11;

public class DBCEHbckp {
    public long bpc(Entity e) {
        if (e instanceof EntityCreature) {
            int atr = (int)(((EntityCreature)e).func_110138_aP() / JRMCoreH.statInc(1, 2, 1, 0, 0, 0.0f) * 6.0f);
            long res = 1L;
            for (int t = 0; t <= atr; ++t) {
                res += (long)JRMCoreH.attrCst(t);
            }
            return res;
        }
        if (e instanceof EntityPlayer) {
            return JRMCoreH.bpc((EntityPlayer)e, e.func_70005_c_());
        }
        return 1L;
    }

    public void renderSense(int hp, int maxhp, int ki, int maxki, RenderLivingEvent.Post event) {
        float f2;
        double X = event.x;
        double Y = event.y;
        double Z = event.z;
        EntityPlayer p = Main.proxy.getPlayerClient();
        double d3 = event.entity.func_70068_e((Entity)p);
        float f = f2 = p.func_70093_af() ? 40.0f : 20.0f;
        if (d3 < (double)(f2 * f2)) {
            long bps;
            long res;
            float var21;
            float width = 39.0f;
            float maxperc = width / (float)maxki;
            float var20 = maxperc * (float)ki;
            if (var20 > width) {
                var20 = width;
            }
            if ((var21 = (maxperc = width / (float)maxhp) * (float)hp) > width) {
                var21 = width;
            }
            long pwr = (res = this.bpc((Entity)event.entity)) > (bps = JRMCoreH.bpc((EntityPlayer)JRMCoreClient.mc.field_71439_g, JRMCoreClient.mc.field_71439_g.func_70005_c_(), JRMCoreH.Pwrtyp)) ? res / bps : bps / res;
            String disp = (res > bps ? "+" : "-") + (pwr > 100L ? "100^x" : pwr + "x");
            long bpsa = JRMCoreH.bpc((EntityPlayer)JRMCoreClient.mc.field_71439_g, JRMCoreClient.mc.field_71439_g.func_70005_c_(), JRMCoreH.Pwrtyp, 100);
            long pwra = res > bpsa ? res / bps : bpsa / res;
            String dispa = (res > bpsa ? "+" : "-") + (pwra > 100L ? "100^x" : pwra + "x");
            FontRenderer fontrenderer = RenderManager.field_78727_a.func_78716_a();
            float f3 = 1.6f;
            float f1 = 0.016666668f * f3;
            GL11.glPushMatrix();
            float angle = (float)Math.toDegrees(Math.atan2(0.0 - event.z, 0.0 - event.x));
            double rtx = Math.sin(angle / 57.295776f);
            double rty = Math.cos(angle / 57.295776f);
            GL11.glTranslatef((float)((float)(X + 1.5 * rtx)), (float)((float)Y + event.entity.field_70131_O + 1.75f - event.entity.field_70131_O * 0.75f + 0.5f), (float)((float)(Z - 1.5 * rty)));
            GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(-RenderManager.field_78727_a.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)RenderManager.field_78727_a.field_78732_j, (float)1.0f, (float)0.0f, (float)0.0f);
            double ikr = RenderManager.field_78727_a.field_78735_i % 180.0f / 57.295776f;
            GL11.glScalef((float)(-f1), (float)(-f1), (float)f1);
            d3 = event.entity.func_70032_d((Entity)p);
            if (d3 > 10.0) {
                GL11.glScaled((double)(d3 / 10.0), (double)(d3 / 10.0), (double)(d3 / 10.0));
            }
            GL11.glDisable((int)2896);
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)240.0f, (float)240.0f);
            GL11.glDepthMask((boolean)false);
            GL11.glDisable((int)2929);
            GL11.glEnable((int)3042);
            OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.7f);
            ResourceLocation guiLocation = new ResourceLocation(JRMCoreH.tjdbcAssts + ":kisense.png");
            Client.mc.field_71446_o.func_110577_a(guiLocation);
            this.drawTexturedModalRect(0.0f, 0.0f, 0, 0, 21.0f, 41.0f, 0.0f);
            int bc1 = 654591;
            if (JRMCoreH.align > 66) {
                bc1 = 654591;
            } else if (JRMCoreH.align <= 66 && JRMCoreH.align >= 33) {
                bc1 = 13478142;
            } else if (JRMCoreH.align < 33) {
                bc1 = 16522030;
            }
            int s = (JRMCoreH.Race == 1 || JRMCoreH.Race == 2) && JRMCoreH.State > 0 ? 16574610 : bc1;
            DBCEHbckp.glColor4f(s, 1.0f);
            this.drawTexturedModalRect(1.0f, 39 - (int)var20 + 1, 24, 0, 10.0f, (int)var20, 0.0f);
            DBCEHbckp.glColor4f(0x6E0000, 1.0f);
            this.drawTexturedModalRect(11.0f, 39 - (int)var21 + 1, 34, 0, 9.0f, (int)var21, 0.0f);
            width = 41.0f;
            maxperc = width / 10.0f;
            float var22 = maxperc * (float)(pwr > 10L ? 10L : pwr);
            if (var22 > width) {
                var22 = width;
            }
            DBCEHbckp.glColor4f(res > bps ? 16737280 : 654591, 1.0f);
            this.drawTexturedModalRect(-3.0f, 41 - (int)var22, 21, 0, 3.0f, (int)var22, 0.0f);
            var22 = maxperc * (float)(pwra > 10L ? 10L : pwra);
            if (var22 > width) {
                var22 = width;
            }
            DBCEHbckp.glColor4f(res > bpsa ? 16737280 : 654591, 1.0f);
            this.drawTexturedModalRect(21.0f, 41 - (int)var22, 21, 0, 3.0f, (int)var22, 0.0f);
            GL11.glEnable((int)2929);
            GL11.glDepthMask((boolean)true);
            GL11.glEnable((int)2896);
            GL11.glDisable((int)3042);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glEnable((int)2896);
            GL11.glDisable((int)3042);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glPopMatrix();
        }
    }

    public static void glColor4f(int c, float a) {
        float h2 = (float)(c >> 16 & 0xFF) / 255.0f;
        float h3 = (float)(c >> 8 & 0xFF) / 255.0f;
        float h4 = (float)(c & 0xFF) / 255.0f;
        float h1 = 1.0f;
        GL11.glColor4f((float)(h1 * h2), (float)(h1 * h3), (float)(h1 * h4), (float)a);
    }

    public static void glColor4f(int c, int c2, float p, float a) {
        float h2 = (float)(c >> 16 & 0xFF) / 255.0f;
        float h3 = (float)(c >> 8 & 0xFF) / 255.0f;
        float h4 = (float)(c & 0xFF) / 255.0f;
        float h22 = (float)(c2 >> 16 & 0xFF) / 255.0f;
        float h32 = (float)(c2 >> 8 & 0xFF) / 255.0f;
        float h42 = (float)(c2 & 0xFF) / 255.0f;
        float percentComplete = p;
        percentComplete = percentComplete > 1.0f ? 1.0f : percentComplete;
        float percentGone = 1.0f - percentComplete;
        float red = h2 * percentGone + h22 * percentComplete;
        float green = h3 * percentGone + h32 * percentComplete;
        float blue = h4 * percentGone + h42 * percentComplete;
        h2 = red;
        h3 = green;
        h4 = blue;
        GL11.glColor4f((float)h2, (float)h3, (float)h4, (float)a);
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void onRenderLivingEvent(RenderLivingEvent.Post event) {
        boolean plyr = event.entity instanceof EntityPlayer;
        if (JRMCoreH.Pwrtyp == 1 && (event.entity instanceof EntityCreature || plyr)) {
            this.renderSense((int)event.entity.func_110143_aJ(), (int)event.entity.func_110138_aP(), 100, 100, event);
        }
        if (event.entity instanceof EntityPlayer && JRMCoreH.Pwrtyp == 1) {
            float f2;
            int hp = Integer.parseInt(JRMCoreH.data(event.entity.func_70005_c_(), 8, "200"));
            int ki = Integer.parseInt(JRMCoreH.data(event.entity.func_70005_c_(), 9, "200"));
            int[] atr = JRMCoreH.PlyrAttrbtsC((EntityPlayer)event.entity);
            String[] s = JRMCoreH.data(event.entity.func_70005_c_(), 1, "0;0;0;0;0;0").split(";");
            int cls = Integer.parseInt(s[3]);
            int race = Integer.parseInt(s[0]);
            int maxhp = JRMCoreH.stat((Entity)event.entity, 2, 1, 2, atr[2], race, cls, 0.0f);
            int maxki = JRMCoreH.stat((Entity)event.entity, 5, 1, 5, atr[5], race, cls, 0.0f);
            this.renderSense(hp, maxhp, ki, maxki, event);
            EntityPlayer p = mod_JRMCore.proxy.getPlayerClient();
            double d3 = event.entity.func_70068_e((Entity)p);
            float f = f2 = p.func_70093_af() ? 4.0f : 8.0f;
            if (d3 < (double)(f2 * f2)) {
                float g;
                float r;
                double X = event.x;
                double Y = event.y;
                double Z = event.z;
                float width = 120.0f;
                float maxperc = width / (float)maxhp;
                float var20 = maxperc * (float)hp;
                if (var20 > width) {
                    var20 = width;
                }
                float f3 = var20 > 50.0f ? 0.58f : (r = var20 > 25.0f ? 1.0f : 1.0f);
                float f4 = var20 > 50.0f ? 0.83f : (g = var20 > 25.0f ? 1.0f : 0.2f);
                float b = var20 > 50.0f ? 0.23f : (var20 > 25.0f ? 0.2f : 0.2f);
                FontRenderer fontrenderer = JRMCoreClient.mc.field_71466_p;
                GL11.glPushMatrix();
                GL11.glTranslatef((float)((float)X), (float)((float)Y + event.entity.field_70131_O + 1.75f - event.entity.field_70131_O * 0.75f), (float)((float)Z));
                GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)(-RenderManager.field_78727_a.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)RenderManager.field_78727_a.field_78732_j, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)-30.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                float f1 = 0.0116f;
                GL11.glScalef((float)(-f1), (float)(-f1), (float)f1);
                GL11.glDisable((int)2896);
                GL11.glTranslatef((float)(-width / 2.0f - event.entity.field_70130_N * 40.0f), (float)(1.0f / f1), (float)(0.0f - event.entity.field_70130_N * 80.0f));
                GL11.glEnable((int)3042);
                OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                ResourceLocation guiLocation = new ResourceLocation(JRMCoreH.tjsaoc + ":gui2.png");
                JRMCoreClient.mc.field_71446_o.func_110577_a(guiLocation);
                this.drawTexturedModalRect(0.0f, -1.0f, 0, 43, 126.0f, 15.0f, 0.0f);
                GL11.glColor4f((float)r, (float)g, (float)b, (float)1.0f);
                this.drawTexturedModalRect(4.0f, 1.0f, 0, 58, var20, 11.0f, -0.01f);
                GL11.glTranslatef((float)width, (float)0.0f, (float)0.0f);
                GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                this.drawTexturedModalRect(0.0f, -1.0f, 126, 43, -126.0f, 15.0f, 0.0f);
                GL11.glColor4f((float)r, (float)g, (float)b, (float)1.0f);
                this.drawTexturedModalRect((float)((int)var20) - width - 2.0f, 1.0f, (int)var20, 58, -var20, 11.0f, 0.01f);
                GL11.glEnable((int)2896);
                GL11.glDisable((int)3042);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glPopMatrix();
            }
        }
    }

    public void drawTexturedModalRect(float x, float y, int u, int v, float width, float height, float z) {
        float f = 0.00390625f;
        float f1 = 0.00390625f;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78374_a((double)x, (double)(y + 0.0f), (double)z, (double)((float)(u + 0) * f), (double)((float)(v + 0) * f1));
        tessellator.func_78374_a((double)x, (double)(y + height), (double)z, (double)((float)(u + 0) * f), (double)(((float)v + height) * f1));
        tessellator.func_78374_a((double)(x + width), (double)(y + height), (double)z, (double)(((float)u + width) * f), (double)(((float)v + height) * f1));
        tessellator.func_78374_a((double)(x + width), (double)(y + 0.0f), (double)z, (double)(((float)u + width) * f), (double)((float)(v + 0) * f1));
        tessellator.func_78381_a();
    }

    @SubscribeEvent
    public void onLivingDropsEvent(LivingDropsEvent event) {
    }
}

