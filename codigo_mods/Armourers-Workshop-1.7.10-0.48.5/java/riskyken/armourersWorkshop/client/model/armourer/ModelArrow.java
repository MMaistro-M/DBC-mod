/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderArrow
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.model.armourer;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.utils.UtilRender;

@SideOnly(value=Side.CLIENT)
public class ModelArrow {
    public static ModelArrow MODEL = new ModelArrow();
    private final ResourceLocation arrowTextures = (ResourceLocation)ReflectionHelper.getPrivateValue(RenderArrow.class, null, (String[])new String[]{"arrowTextures", "field_110780_a"});
    private int displayList = -1;

    protected void finalize() throws Throwable {
        if (this.displayList != -1) {
            GLAllocation.func_74523_b((int)this.displayList);
        }
        super.finalize();
    }

    public void render(float scale, boolean ghost) {
        if (this.displayList == -1) {
            this.buildDisplayList();
        }
        GL11.glPushMatrix();
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)(-3.0f * scale), (float)(-0.5f * scale), (float)(-0.5f * scale));
        ModRenderHelper.enableAlphaBlend();
        if (ghost) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.25f);
        }
        GL11.glEnable((int)32826);
        UtilRender.bindTexture(this.arrowTextures);
        GL11.glCallList((int)this.displayList);
        GL11.glDisable((int)32826);
        if (ghost) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
        ModRenderHelper.disableAlphaBlend();
        GL11.glPopMatrix();
    }

    private void buildDisplayList() {
        this.displayList = GLAllocation.func_74526_a((int)1);
        GL11.glNewList((int)this.displayList, (int)4864);
        Tessellator tessellator = Tessellator.field_78398_a;
        int b0 = 0;
        float f2 = 0.0f;
        float f3 = 0.5f;
        float f4 = (float)(0 + b0 * 10) / 32.0f;
        float f5 = (float)(5 + b0 * 10) / 32.0f;
        float f6 = 0.0f;
        float f7 = 0.15625f;
        float f8 = (float)(5 + b0 * 10) / 32.0f;
        float f9 = (float)(10 + b0 * 10) / 32.0f;
        float f10 = 0.05625f;
        GL11.glRotatef((float)45.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glScalef((float)f10, (float)f10, (float)f10);
        GL11.glTranslatef((float)-4.0f, (float)0.0f, (float)0.0f);
        GL11.glNormal3f((float)f10, (float)0.0f, (float)0.0f);
        tessellator.func_78382_b();
        tessellator.func_78374_a(-7.0, -2.0, -2.0, (double)f6, (double)f8);
        tessellator.func_78374_a(-7.0, -2.0, 2.0, (double)f7, (double)f8);
        tessellator.func_78374_a(-7.0, 2.0, 2.0, (double)f7, (double)f9);
        tessellator.func_78374_a(-7.0, 2.0, -2.0, (double)f6, (double)f9);
        tessellator.func_78381_a();
        GL11.glNormal3f((float)(-f10), (float)0.0f, (float)0.0f);
        tessellator.func_78382_b();
        tessellator.func_78374_a(-7.0, 2.0, -2.0, (double)f6, (double)f8);
        tessellator.func_78374_a(-7.0, 2.0, 2.0, (double)f7, (double)f8);
        tessellator.func_78374_a(-7.0, -2.0, 2.0, (double)f7, (double)f9);
        tessellator.func_78374_a(-7.0, -2.0, -2.0, (double)f6, (double)f9);
        tessellator.func_78381_a();
        for (int i = 0; i < 4; ++i) {
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glNormal3f((float)0.0f, (float)0.0f, (float)f10);
            tessellator.func_78382_b();
            tessellator.func_78374_a(-8.0, -2.0, 0.0, (double)f2, (double)f4);
            tessellator.func_78374_a(8.0, -2.0, 0.0, (double)f3, (double)f4);
            tessellator.func_78374_a(8.0, 2.0, 0.0, (double)f3, (double)f5);
            tessellator.func_78374_a(-8.0, 2.0, 0.0, (double)f2, (double)f5);
            tessellator.func_78381_a();
        }
        GL11.glEndList();
    }
}

