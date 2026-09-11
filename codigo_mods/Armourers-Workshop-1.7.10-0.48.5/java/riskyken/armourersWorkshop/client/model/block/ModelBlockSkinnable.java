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
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinnable;

public class ModelBlockSkinnable
extends ModelBase {
    private static final ResourceLocation modelImage = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/models/ModelBlockSkinnable.png");
    public ModelRenderer spinningCube;

    public ModelBlockSkinnable() {
        this.field_78090_t = 32;
        this.field_78089_u = 16;
        this.spinningCube = new ModelRenderer((ModelBase)this, 0, 0);
        this.spinningCube.func_78793_a(0.0f, 0.0f, 0.0f);
        this.spinningCube.func_78789_a(-2.5f, -2.5f, -2.5f, 5, 5, 5);
    }

    public void render(TileEntitySkinnable tileEntity, float partialTickTime, float scale) {
        Minecraft.func_71410_x().field_71446_o.func_110577_a(modelImage);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        if (tileEntity != null) {
            float angle = (float)((tileEntity.func_145831_w().func_82737_E() + (long)((Object)((Object)tileEntity)).hashCode()) % 360L) + partialTickTime;
            this.setRotateAngle(this.spinningCube, (float)Math.toRadians(angle * 4.0f), (float)Math.toRadians(angle), (float)Math.toRadians(angle * 2.0f));
        } else {
            this.setRotateAngle(this.spinningCube, 0.0f, 0.0f, 0.0f);
        }
        this.spinningCube.func_78785_a(scale);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

