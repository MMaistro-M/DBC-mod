/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.particle.EntityAuraFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore;

import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreGuiScreen;
import JinRyuu.JRMCore.JRMCoreH;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.particle.EntityAuraFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class JRMCoreDamInd
extends EntityAuraFX {
    private static final ResourceLocation particleTextures = new ResourceLocation("textures/particle/particles.png");
    private double amount = 0.0;
    private double timeleft = 0.0;

    public JRMCoreDamInd(double amount, float timeleft, World parWorld, double parX, double parY, double parZ, double parMotionX, double parMotionY, double parMotionZ) {
        super(parWorld, parX, parY, parZ, parMotionX, parMotionY, parMotionZ);
        this.func_70536_a(82);
        this.field_70544_f = 1.0f;
        this.func_70538_b(136.0f, 0.0f, 136.0f);
        this.amount = amount;
        this.timeleft = timeleft;
        this.field_70547_e = 50;
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        this.field_70159_w *= 0.99;
        this.field_70181_x *= 0.99;
        this.field_70179_y *= 0.99;
        this.field_70181_x += 0.00125;
        if (this.field_70547_e-- <= 0) {
            this.func_70106_y();
        }
    }

    public void func_70539_a(Tessellator p_70539_1_, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_) {
        float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)p_70539_2_ - field_70556_an);
        float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)p_70539_2_ - field_70554_ao);
        float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)p_70539_2_ - field_70555_ap);
        p_70539_1_.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as);
        FontRenderer fontrenderer = JRMCoreClient.mc.field_71466_p;
        GL11.glPushMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslatef((float)(f11 + 0.0f), (float)(f12 - 0.75f + 1.75f), (float)f13);
        GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
        boolean ro = JRMCoreClient.mc.field_71474_y.field_74320_O == 2;
        GL11.glRotatef((float)(-RenderManager.field_78727_a.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)RenderManager.field_78727_a.field_78732_j, (float)(1.0f * (float)(ro ? -1 : 1)), (float)0.0f, (float)0.0f);
        float f1 = 0.0516f;
        GL11.glScalef((float)(-f1), (float)(-f1), (float)f1);
        GL11.glTranslatef((float)0.0f, (float)(0.25f / f1), (float)0.0f);
        String text = "" + JRMCoreH.numSep((int)this.amount);
        int textWidth = fontrenderer.func_78256_a(text);
        JRMCoreGuiScreen.drawStringWithBorder(fontrenderer, text, -textWidth / 2, -2, JRMCoreH.techCol[4]);
        JRMCoreClient.mc.field_71446_o.func_110577_a(particleTextures);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }
}

