/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.RenderLivingEvent$Specials$Post
 *  net.minecraftforge.client.event.RenderLivingEvent$Specials$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore.entity;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderJRMC
extends RendererLivingEntity {
    boolean b = false;

    public RenderJRMC(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
        this.field_77045_g = par1ModelBase;
        this.field_76989_e = par2;
    }

    public void func_76986_a(Entity entity, double d0, double d1, double d2, float f, float f1) {
        this.func_76986_a((EntityLivingBase)entity, d0, d1, d2, f, f1);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return new ResourceLocation("jinryuudragonbc:npcs/" + entity.func_70005_c_().replaceAll("entity.jinryuudragonblockc.", "").replaceAll(".name", "") + ".png");
    }

    protected void func_77033_b(EntityLivingBase entity, double par2, double par4, double par6) {
        if (MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Specials.Pre(entity, (RendererLivingEntity)this, par2, par4, par6))) {
            return;
        }
        if (this.b) {
            float f2;
            float f = 1.6f;
            float f1 = 0.016666668f * f;
            double d3 = entity.func_70068_e((Entity)this.field_76990_c.field_78734_h);
            float f3 = f2 = entity.func_70093_af() ? NAME_TAG_RANGE_SNEAK : NAME_TAG_RANGE;
            if (d3 < (double)(f2 * f2)) {
                String s = entity.func_70005_c_();
                if (entity.func_70093_af()) {
                    FontRenderer fontrenderer = this.func_76983_a();
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)((float)par2 + 0.0f), (float)((float)par4 + entity.field_70131_O + 0.5f), (float)((float)par6));
                    GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)(-this.field_76990_c.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)this.field_76990_c.field_78732_j, (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glScalef((float)(-f1), (float)(-f1), (float)f1);
                    GL11.glDisable((int)2896);
                    GL11.glTranslatef((float)0.0f, (float)(0.25f / f1), (float)0.0f);
                    GL11.glDepthMask((boolean)false);
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)771);
                    Tessellator tessellator = Tessellator.field_78398_a;
                    GL11.glDisable((int)3553);
                    tessellator.func_78382_b();
                    int i = fontrenderer.func_78256_a(s) / 2;
                    tessellator.func_78369_a(0.0f, 0.0f, 0.0f, 0.25f);
                    tessellator.func_78377_a((double)(-i - 1), -1.0, 0.0);
                    tessellator.func_78377_a((double)(-i - 1), 8.0, 0.0);
                    tessellator.func_78377_a((double)(i + 1), 8.0, 0.0);
                    tessellator.func_78377_a((double)(i + 1), -1.0, 0.0);
                    tessellator.func_78381_a();
                    GL11.glEnable((int)3553);
                    GL11.glDepthMask((boolean)true);
                    fontrenderer.func_78276_b(s, -fontrenderer.func_78256_a(s) / 2, 0, 0x20FFFFFF);
                    GL11.glEnable((int)2896);
                    GL11.glDisable((int)3042);
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    GL11.glPopMatrix();
                } else {
                    this.func_96449_a(entity, par2, par4, par6, s, f1, d3);
                }
            }
        }
        MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Specials.Post(entity, (RendererLivingEntity)this, par2, par4, par6));
    }
}

