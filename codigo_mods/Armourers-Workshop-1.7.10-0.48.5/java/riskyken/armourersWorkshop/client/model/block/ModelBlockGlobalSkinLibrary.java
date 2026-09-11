/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.model.block;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.common.tileentities.TileEntityGlobalSkinLibrary;

public class ModelBlockGlobalSkinLibrary
extends ModelBase {
    private static final ResourceLocation MODEL_TEXTURE = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/tileEntities/globalSkinLibrary.png");
    public ModelRenderer globe;

    public ModelBlockGlobalSkinLibrary() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.globe = new ModelRenderer((ModelBase)this, 0, 0);
        this.globe.func_78793_a(0.0f, 0.0f, 0.0f);
        this.globe.func_78789_a(-8.0f, -8.0f, -8.0f, 16, 16, 16);
    }

    public void render(TileEntityGlobalSkinLibrary tileEntity, float partialTickTime, float scale) {
        Minecraft.func_71410_x().field_71446_o.func_110577_a(MODEL_TEXTURE);
        ModRenderHelper.disableLighting();
        ModRenderHelper.enableAlphaBlend();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.5f);
        GL11.glEnable((int)2884);
        if (tileEntity != null) {
            float angle = (float)((tileEntity.func_145831_w().func_82737_E() + (long)tileEntity.hashCode()) % 360L) + partialTickTime;
            this.setRotateAngle(this.globe, (float)Math.toRadians(angle * 4.0f), (float)Math.toRadians(angle), (float)Math.toRadians(angle * 2.0f));
        } else {
            this.setRotateAngle(this.globe, 0.0f, 0.0f, 0.0f);
        }
        GL11.glScalef((float)0.6f, (float)0.6f, (float)0.6f);
        this.globe.func_78785_a(scale);
        GL11.glDisable((int)2884);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ModRenderHelper.disableAlphaBlend();
        ModRenderHelper.enableLighting();
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

