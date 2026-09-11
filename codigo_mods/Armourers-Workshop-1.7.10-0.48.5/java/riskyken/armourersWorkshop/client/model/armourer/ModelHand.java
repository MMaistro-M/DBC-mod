/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.model.armourer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;

@SideOnly(value=Side.CLIENT)
public class ModelHand
extends ModelBase {
    public static final ModelHand MODEL = new ModelHand();
    private ModelRenderer armSolid = new ModelRenderer((ModelBase)this, 40, 16);
    private ModelRenderer armTransparent;

    public ModelHand() {
        this.armSolid.func_78789_a(-2.0f, -10.0f, -4.0f, 4, 8, 4);
        this.armSolid.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armTransparent = new ModelRenderer((ModelBase)this, 40, 24);
        this.armTransparent.func_78789_a(-2.0f, -2.0f, -4.0f, 4, 4, 4);
        this.armTransparent.func_78793_a(0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float scale, float f2, float f3, float f4, float f5, float f6) {
        this.render(scale);
    }

    public void render(float scale) {
        GL11.glPushMatrix();
        GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glPushMatrix();
        this.armSolid.func_78785_a(scale);
        GL11.glPopMatrix();
        ModRenderHelper.enableAlphaBlend();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.75f);
        GL11.glTranslatef((float)0.0f, (float)-1.0E-5f, (float)0.0f);
        this.armTransparent.func_78785_a(scale);
        ModRenderHelper.disableAlphaBlend();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }
}

