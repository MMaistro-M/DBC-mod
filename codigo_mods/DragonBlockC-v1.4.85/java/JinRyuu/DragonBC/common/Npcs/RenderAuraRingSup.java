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

import JinRyuu.DragonBC.common.Npcs.EntityAuraRingSup;
import JinRyuu.DragonBC.common.Npcs.ModelKiAuraRing;
import JinRyuu.DragonBC.common.Npcs.RenderDBC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderAuraRingSup
extends RenderDBC {
    private ModelKiAuraRing aModel = new ModelKiAuraRing();

    public RenderAuraRingSup() {
        super((ModelBase)new ModelKiAuraRing(), 0.5f);
    }

    public void renderAuraRingSup(EntityAuraRingSup par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.field_76989_e = 0.0f;
        GL11.glPushMatrix();
        GL11.glPushMatrix();
        float var13 = this.handleRotationFloat(par1Entity, par9);
        float size = this.handleSizeFloat(par1Entity, par9);
        Random rand = new Random();
        float randfloat = (float)((double)rand.nextInt(5) * 0.1);
        float var20 = size;
        GL11.glTranslatef((float)((float)par2 + 0.0f), (float)((float)par4 + 1.6f), (float)((float)par6 + 0.0f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        ResourceLocation txx = new ResourceLocation("jinryuudragonbc:aurarings.png");
        this.field_76990_c.field_78724_e.func_110577_a(txx);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.15f);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glScalef((float)var20, (float)1.0f, (float)var20);
        this.aModel.renderModel(par1Entity, 0.0f, 0.0f, 0.0625f, var13);
        GL11.glDisable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glPopMatrix();
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
    }

    protected float handleRotationFloat(Entity par1Entity, float par2) {
        return (float)par1Entity.field_70173_aa + par2;
    }

    protected float handleSizeFloat(Entity par1Entity, float par2) {
        float ticksExsisted = ((float)par1Entity.field_70173_aa + par2) / 2.0f;
        return ticksExsisted;
    }

    @Override
    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderAuraRingSup((EntityAuraRingSup)par1Entity, par2, par4, par6, par8, par9);
    }
}

