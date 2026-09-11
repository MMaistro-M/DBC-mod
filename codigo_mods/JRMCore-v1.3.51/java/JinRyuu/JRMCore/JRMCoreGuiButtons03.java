/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.client.notification.JGNotificationHandler;
import java.awt.Color;
import java.time.Duration;
import java.time.Instant;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class JRMCoreGuiButtons03
extends GuiButton {
    public static String icons = JRMCoreH.tjjrmc + ":icons3.png";
    public static int newsAnimation = 0;
    public static int noticiationAnimation = 0;
    public static int noticiationAnimationLast = 0;
    public static Instant animation = null;
    public static float rotation = 0.0f;
    private int col;
    private int type;
    private int resourceID;

    public JRMCoreGuiButtons03(int par1, int par2, int par3, String par6Str, int type, int col, int resourceID) {
        super(par1, par2, par3, 20, 20, par6Str);
        this.field_146120_f = 20;
        this.field_146121_g = 20;
        this.col = col;
        this.type = type;
        this.resourceID = resourceID;
    }

    public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
        if (this.field_146125_m) {
            boolean isNotification;
            float h1;
            int t = (int)(System.currentTimeMillis() / 100L % 20L);
            newsAnimation = t - 10;
            newsAnimation = newsAnimation < 0 ? newsAnimation * -1 : newsAnimation;
            boolean hasNewContent = JGNotificationHandler.hasNewContent;
            if (!hasNewContent) {
                animation = null;
            }
            FontRenderer var4 = par1Minecraft.field_71466_p;
            ResourceLocation txx = new ResourceLocation(icons);
            par1Minecraft.func_110434_K().func_110577_a(txx);
            GL11.glColor4f((float)0.7f, (float)0.7f, (float)0.7f, (float)1.0f);
            this.field_146123_n = par2 >= this.field_146128_h && par3 >= this.field_146129_i && par2 < this.field_146128_h + this.field_146120_f && par3 < this.field_146129_i + this.field_146121_g;
            int var5 = this.func_146114_a(this.field_146123_n);
            boolean redBack = this.resourceID == 10 || this.resourceID == 9 || this.resourceID == 8;
            int j = this.col == 0 ? 0xFAFF00 : this.col;
            float h2 = (float)(j >> 16 & 0xFF) / 255.0f;
            float h3 = (float)(j >> 8 & 0xFF) / 255.0f;
            float h4 = (float)(j & 0xFF) / 255.0f;
            float f = h1 = redBack ? 2.0f : 1.0f;
            if (var5 == 2) {
                int r = (int)(h2 * 254.0f);
                int g = (int)(h3 * 254.0f);
                int b = (int)(h4 * 254.0f);
                float[] hsb = Color.RGBtoHSB(r, g, b, null);
                float hue = hsb[0];
                float saturation = 0.33f;
                float brightness = hsb[2];
                int rgb = Color.HSBtoRGB(hue, saturation, brightness);
                h2 = (float)(rgb >> 16 & 0xFF) / 255.0f;
                h3 = (float)(rgb >> 8 & 0xFF) / 255.0f;
                h4 = (float)(rgb & 0xFF) / 255.0f;
            }
            GL11.glColor3f((float)(h1 * h2), (float)(h1 * h3), (float)(h1 * h4));
            this.func_73729_b(this.field_146128_h, this.field_146129_i, 0 + var5 * 20 + (redBack ? 80 - var5 * 20 : 0), 156 + this.type * 20 + (redBack ? var5 * 20 : 0), this.field_146120_f, this.field_146121_g);
            this.func_146119_b(par1Minecraft, par2, par3);
            int var6 = 0xE0E0E0;
            if (!this.field_146124_l) {
                var6 = -6250336;
            } else if (this.field_146123_n) {
                var6 = 0xFFFFA0;
            }
            if (this.resourceID == 0 || this.resourceID == 10) {
                int r = (int)(h2 * 254.0f);
                int g = (int)(h3 * 254.0f);
                int b = (int)(h4 * 254.0f);
                float[] hsb = Color.RGBtoHSB(r, g, b, null);
                float hue = 0.0f;
                float saturation = 0.0f;
                float brightness = 1.0f;
                brightness = 0.75f + (float)newsAnimation / 40.0f;
                brightness = brightness < 0.75f ? 0.75f : (brightness > 1.0f ? 1.0f : brightness);
                int rgb = Color.HSBtoRGB(hue, saturation, brightness);
                h2 = (float)(rgb >> 16 & 0xFF) / 255.0f;
                h3 = (float)(rgb >> 8 & 0xFF) / 255.0f;
                h4 = (float)(rgb & 0xFF) / 255.0f;
                GL11.glColor3f((float)(h1 * h2), (float)(h1 * h3), (float)(h1 * h4));
            } else {
                float color = var5 == 2 || this.type == 1 ? 1.0f : 0.6f;
                GL11.glColor4f((float)color, (float)color, (float)color, (float)1.0f);
            }
            txx = new ResourceLocation(icons);
            par1Minecraft.func_110434_K().func_110577_a(txx);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(this.field_146128_h + 2), (float)(this.field_146129_i + 2), (float)0.0f);
            boolean bl = isNotification = this.resourceID == 10;
            if (isNotification && animation != null) {
                float rotation = (float)Duration.between(animation, Instant.now()).toMillis() / 10.0f;
                JRMCoreGuiButtons03.rotation += rotation;
                rotation = (float)Math.sin(JRMCoreGuiButtons03.rotation / 25.0f) * 20.0f;
                GL11.glTranslatef((float)8.0f, (float)8.0f, (float)0.0f);
                GL11.glRotatef((float)rotation, (float)0.0f, (float)0.0f, (float)1.0f);
            }
            this.func_73729_b(isNotification && hasNewContent && animation != null ? -8 : 0, isNotification && hasNewContent && animation != null ? -8 : 0, this.resourceID * 16, JRMCoreH.Pwrtyp == 2 ? 16 : (JRMCoreH.Pwrtyp == 3 ? 32 : 0), 16, 16);
            GL11.glPopMatrix();
            if (isNotification) {
                float rotation = (float)Math.sin(JRMCoreGuiButtons03.rotation / 30.0f) * 3.0f;
                boolean MUTED = false;
                boolean NEW = true;
                int NORMAL = 2;
                int state = hasNewContent ? 1 : 2;
                int bonusY = (int)rotation;
                if (state == 1) {
                    if (JRMCoreClient.mc.field_71439_g.field_70173_aa != noticiationAnimationLast) {
                        if (++noticiationAnimation > 3) {
                            noticiationAnimation = 0;
                        }
                        noticiationAnimationLast = JRMCoreClient.mc.field_71439_g.field_70173_aa;
                    }
                } else if (bonusY != 0) {
                    bonusY = 0;
                }
                rotation = (float)Math.sin(JRMCoreGuiButtons03.rotation / 25.0f) * 20.0f;
                GL11.glPushMatrix();
                GL11.glTranslatef((float)(this.field_146128_h + 2), (float)(this.field_146129_i + 2 + bonusY), (float)0.0f);
                GL11.glTranslatef((float)8.0f, (float)8.0f, (float)0.0f);
                GL11.glRotatef((float)rotation, (float)0.0f, (float)0.0f, (float)1.0f);
                this.func_73729_b(isNotification ? -8 : 0, isNotification ? -8 : 0, 16, 48 + state * 16, 16, 16);
                GL11.glPopMatrix();
                if (state == 1) {
                    animation = Instant.now();
                }
            }
        }
    }
}

