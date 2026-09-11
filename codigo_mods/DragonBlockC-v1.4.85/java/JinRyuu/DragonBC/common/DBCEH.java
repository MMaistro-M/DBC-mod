/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.RenderLivingEvent$Post
 *  net.minecraftforge.event.world.BlockEvent$BreakEvent
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.DragonBC.common;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.DBCClient;
import JinRyuu.DragonBC.common.Npcs.EntityDBC;
import JinRyuu.DragonBC.common.Npcs.EntityDBCEvil;
import JinRyuu.DragonBC.common.Npcs.EntityDBCGood;
import JinRyuu.DragonBC.common.Npcs.EntityDBCNeut;
import JinRyuu.JRMCore.JRMCoreCliTicH;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHC;
import JinRyuu.JRMCore.entity.EntitySafeZone;
import JinRyuu.JRMCore.mod_JRMCore;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.world.BlockEvent;
import org.lwjgl.opengl.GL11;

public class DBCEH {
    public static final int ksm_default = 0;
    public static final int ksm_onlyPly = 1;
    public static final int ksm_onlyMob = 2;
    public static final int ksm_showall = 3;
    public static final int ksm_off = 4;
    public static int kisnsMd = 0;
    public String lastRender = "";

    public void renderSense(int hp, int maxhp, int ki, int maxki, int align, RenderLivingEvent.Post event) {
        float f2;
        double X = event.x;
        double Y = event.y;
        double Z = event.z;
        EntityPlayer p = mod_JRMCore.proxy.getPlayerClient();
        EntityLivingBase entity = event.entity;
        if (entity instanceof EntityPlayer && JRMCoreH.plyrs != null && JRMCoreH.plyrs.length > 0) {
            EntityPlayer player = (EntityPlayer)entity;
            for (int pl = 0; pl < JRMCoreH.plyrs.length; ++pl) {
                EntityPlayer fusionSpectator;
                String[] fusionData;
                String[] fullFusionData;
                if (JRMCoreH.dat18 == null || JRMCoreH.dat18.length <= pl || JRMCoreH.dat18[pl] == null || (fullFusionData = JRMCoreH.dat18[pl].split(";")).length < 3 || (fusionData = fullFusionData[2].split(",")).length != 3 || (fusionSpectator = player.field_70170_p.func_72924_a(fusionData[1])) == null || !fusionSpectator.func_70005_c_().equals(player.func_70005_c_())) continue;
                return;
            }
        }
        int kiSenseLvl = JRMCoreH.SklLvl(6);
        double distance = entity.func_70068_e((Entity)p);
        if (distance < (double)((f2 = (float)(30 + kiSenseLvl * 10)) * f2) && (!(entity.field_70163_u < 61.0) || Y > (double)(-kiSenseLvl * 4))) {
            int bc1;
            boolean loo;
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
            long pwr = (res = JRMCoreH.gkap(JRMCoreH.bpc((Entity)entity), "sns;" + entity.func_70005_c_() + ";" + entity.func_145782_y())) > (bps = JRMCoreHC.BPC_ME) ? res / bps : bps / res;
            long bpsa = JRMCoreHC.BPC_ME2;
            long pwra = res > bpsa ? res / bpsa : bpsa / res;
            boolean bl = loo = JRMCoreCliTicH.lockOn != null && JRMCoreConfig.lockon;
            if (loo ? !loo : res <= 5L || res < bps && kisnsMd != 3 && bps / res > 100L) {
                return;
            }
            FontRenderer fontrenderer = RenderManager.field_78727_a.func_78716_a();
            float f = 1.6f;
            float f1 = 0.016666668f * f;
            GL11.glPushMatrix();
            float angle = (float)Math.toDegrees(Math.atan2(0.0 - event.z, 0.0 - event.x));
            double rtx = Math.sin(angle / 57.295776f);
            double rty = Math.cos(angle / 57.295776f);
            GL11.glTranslatef((float)((float)(X + 0.0 * rtx)), (float)((float)Y + entity.field_70131_O + 1.75f - entity.field_70131_O * 0.75f + 0.5f), (float)((float)(Z - 0.0 * rty)));
            GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
            boolean t = DBCClient.mc.field_71474_y.field_74320_O == 2;
            GL11.glRotatef((float)(-RenderManager.field_78727_a.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(t ? -RenderManager.field_78727_a.field_78732_j : RenderManager.field_78727_a.field_78732_j), (float)1.0f, (float)0.0f, (float)0.0f);
            double ikr = RenderManager.field_78727_a.field_78735_i % 180.0f / 57.295776f;
            GL11.glScalef((float)(-f1), (float)(-f1), (float)f1);
            distance = entity.func_70032_d((Entity)p);
            if (distance > 10.0) {
                GL11.glScaled((double)(distance / 10.0), (double)(distance / 10.0), (double)(distance / 10.0));
            }
            GL11.glDisable((int)2896);
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)240.0f, (float)240.0f);
            GL11.glDepthMask((boolean)false);
            GL11.glDisable((int)2929);
            GL11.glEnable((int)3042);
            OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
            int Ypos = -40;
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.7f);
            ResourceLocation guiLocation = new ResourceLocation(JRMCoreH.tjdbcAssts + ":kisense.png");
            JRMCoreClient.mc.field_71446_o.func_110577_a(guiLocation);
            this.drawTexturedModalRect(0.0f, 0 + Ypos, 0, 0, 21.0f, 41.0f, 0.0f);
            int red = 16522030;
            int blu = 654591;
            int pur = 13478142;
            int ora = 16737280;
            int darkred = 0x6E0000;
            int yel = 16574610;
            switch (align) {
                case 0: {
                    bc1 = blu;
                    break;
                }
                case 1: {
                    bc1 = pur;
                    break;
                }
                case 2: {
                    bc1 = red;
                    break;
                }
                default: {
                    bc1 = pur;
                }
            }
            DBCEH.glColor4f(bc1, 1.0f);
            this.drawTexturedModalRect(1.0f, 39 - (int)var20 + 1 + Ypos, 25, 0, 10 + (kiSenseLvl > 1 ? 0 : 9), (int)var20, 0.0f);
            if (kiSenseLvl > 1) {
                DBCEH.glColor4f(0x6E0000, 1.0f);
                this.drawTexturedModalRect(11.0f, 39 - (int)var21 + 1 + Ypos, 35, 0, 9.0f, (int)var21, 0.0f);
            }
            if (kiSenseLvl > 3) {
                width = 41.0f;
                maxperc = width / 10.0f;
                float var22 = maxperc * (float)(pwr > 10L ? 10L : pwr);
                if (var22 > width) {
                    var22 = width;
                }
                DBCEH.glColor4f(res > bps ? 16737280 : 654591, 1.0f);
                this.drawTexturedModalRect(-3.0f, 41 - (int)var22 + Ypos, 21, 0, 3.0f, (int)var22, 0.0f);
                if (pwr > 10L && kiSenseLvl > 6) {
                    maxperc = width / 100.0f;
                    var22 = maxperc * (float)(pwr > 100L ? 100L : pwr);
                    if (var22 > width) {
                        var22 = width;
                    }
                    this.drawTexturedModalRect(-4.0f, 41 - (int)var22 + Ypos, 24, 0, 1.0f, (int)var22, 0.0f);
                }
                if ((var22 = (maxperc = width / 10.0f) * (float)(pwra > 10L ? 10L : pwra)) > width) {
                    var22 = width;
                }
                DBCEH.glColor4f(res > bpsa ? 16737280 : 654591, 1.0f);
                this.drawTexturedModalRect(21.0f, 41 - (int)var22 + Ypos, 21, 0, 3.0f, (int)var22, 0.0f);
                if (pwra > 10L && kiSenseLvl > 6) {
                    maxperc = width / 100.0f;
                    var22 = maxperc * (float)(pwra > 100L ? 100L : pwra);
                    if (var22 > width) {
                        var22 = width;
                    }
                    this.drawTexturedModalRect(24.0f, 41 - (int)var22 + Ypos, 24, 0, 1.0f, (int)var22, 0.0f);
                }
            }
            if (kiSenseLvl > 9) {
                String n = entity.func_70005_c_();
                String[] d10 = JRMCoreH.data(n, 10, "0;0").split(";");
                String[] d2 = JRMCoreH.data(n, 2, "0;0").split(";");
                String[] d1 = JRMCoreH.data(n, 1, "0;0").split(";");
                int inc = 0;
                String d = hp + "";
                int ho = Ypos - inc * 9;
                int hpw = fontrenderer.func_78256_a(d);
                fontrenderer.func_78276_b(d, (int)(13.0f - (float)hpw / 2.0f), -8 + ho, 0);
                fontrenderer.func_78276_b(d, (int)(11.0f - (float)hpw / 2.0f), -8 + ho, 0);
                fontrenderer.func_78276_b(d, (int)(12.0f - (float)hpw / 2.0f), -7 + ho, 0);
                fontrenderer.func_78276_b(d, (int)(12.0f - (float)hpw / 2.0f), -9 + ho, 0);
                fontrenderer.func_78276_b(d, 12 - hpw / 2, -8 + ho, 14812441);
                ++inc;
            }
            GL11.glEnable((int)2929);
            GL11.glDepthMask((boolean)true);
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
        boolean lo;
        boolean plyr = event.entity instanceof EntityPlayer;
        boolean loo = JRMCoreCliTicH.lockOn != null && JRMCoreConfig.lockon;
        boolean bl = lo = loo && event.entity.equals((Object)JRMCoreCliTicH.lockOn);
        if (JRMCoreH.Pwrtyp == 1 && JRMCoreH.SklLvl(6) > 0 && kisnsMd != 4) {
            if (!(event.entity instanceof EntitySafeZone) && event.entity instanceof EntityCreature && (loo ? lo : kisnsMd != 1)) {
                int align = 1;
                if (event.entity instanceof EntityMob) {
                    align = 2;
                } else if (event.entity instanceof EntityDBCEvil) {
                    align = 2;
                } else if (event.entity instanceof EntityDBCNeut) {
                    align = 1;
                } else if (event.entity instanceof EntityDBCGood) {
                    align = 0;
                } else if (event.entity instanceof EntityDBC) {
                    align = 1;
                } else if (event.entity instanceof EntityDBC) {
                    align = 1;
                }
                this.renderSense((int)event.entity.func_110143_aJ(), (int)event.entity.func_110138_aP(), 100, 100, align, event);
            }
            if (plyr && (loo ? lo : kisnsMd != 2) && !JRMCoreClient.mc.field_71439_g.func_70005_c_().equals(event.entity.func_70005_c_()) && JRMCoreH.dnn(14) && JRMCoreH.dnn(8) && JRMCoreH.dnn(9) && JRMCoreH.dnn(1) && JRMCoreH.dnn(5)) {
                EntityPlayer p = (EntityPlayer)event.entity;
                int hp = Integer.parseInt(JRMCoreH.data(event.entity.func_70005_c_(), 8, "200"));
                int ki = Integer.parseInt(JRMCoreH.data(event.entity.func_70005_c_(), 9, "200"));
                int[] atr = JRMCoreH.PlyrAttrbtsC((EntityPlayer)event.entity);
                String[] s = JRMCoreH.data(event.entity.func_70005_c_(), 1, "0;0;0;0;0;0").split(";");
                int race = Integer.parseInt(s[0]);
                int pwr = Integer.parseInt(s[2]);
                int cls = Integer.parseInt(s[3]);
                s = JRMCoreH.data(event.entity.func_70005_c_(), 6, "pty;pty;pty;pty").split(";");
                String[] PlyrSkills = s[0].split(",");
                int maxhp = JRMCoreH.stat((Entity)p, 2, pwr, 2, atr[2], race, cls, 0.0f);
                int maxki = JRMCoreH.stat((Entity)p, 5, pwr, 5, atr[5], race, cls, JRMCoreH.SklLvl_KiBs(PlyrSkills, pwr));
                int align = JRMCoreH.Algnmnt(Integer.parseInt(JRMCoreH.data(event.entity.func_70005_c_(), 5, "50;0").split(";")[0]));
                this.renderSense(hp, maxhp, ki, maxki, align, event);
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
    public void gldsog(BlockEvent.BreakEvent event) {
        if (event.block == BlocksDBC.ppBlock) {
            event.setCanceled(true);
        }
    }
}

