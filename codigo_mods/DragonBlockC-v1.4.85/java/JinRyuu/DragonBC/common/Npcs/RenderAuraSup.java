/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.DragonBC.common.Npcs.EntityAuraSup;
import JinRyuu.DragonBC.common.Npcs.ModelAura;
import JinRyuu.DragonBC.common.Npcs.RenderDBC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderAuraSup
extends RenderDBC {
    private ModelAura aModel = new ModelAura();
    private int field_77068_a;

    public RenderAuraSup() {
        super((ModelBase)new ModelAura(), 0.5f);
    }

    public void renderAuraSup(EntityAuraSup par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.field_76989_e = 0.0f;
        GL11.glPushMatrix();
        GL11.glPushMatrix();
        float var13 = this.handleRotationFloat(par1Entity, par9);
        Random rand = new Random();
        float randfloat = (float)((double)rand.nextInt(5) * 0.1);
        GL11.glTranslatef((float)((float)par2 + 0.0f), (float)((float)par4 + 1.5f), (float)((float)par6 + 0.0f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        ResourceLocation txx = new ResourceLocation("jinryuudragonbc:auras.png");
        this.field_76990_c.field_78724_e.func_110577_a(txx);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.15f);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        for (int i = 0; i < 8; ++i) {
            GL11.glPushMatrix();
            GL11.glRotatef((float)(i * 45), (float)0.0f, (float)1.0f, (float)0.0f);
            this.aModel.renderModel(par1Entity, 0.0625f, var13, 0.0f, 20.0f);
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glPopMatrix();
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
    }

    protected float handleRotationFloat(Entity par1Entity, float par2) {
        return (float)par1Entity.field_70173_aa + par2;
    }

    @Override
    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderAuraSup((EntityAuraSup)par1Entity, par2, par4, par6, par8, par9);
    }
}

