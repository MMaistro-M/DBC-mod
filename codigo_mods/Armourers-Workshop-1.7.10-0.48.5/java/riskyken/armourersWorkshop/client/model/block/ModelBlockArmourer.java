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

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMiniArmourer;
import riskyken.armourersWorkshop.utils.UtilColour;

public class ModelBlockArmourer
extends ModelBase {
    private static final ResourceLocation modelImage = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/models/ModelBlockArmourer-texturemap.png");
    public ModelRenderer FrameTop2;
    public ModelRenderer FrameBottom;
    public ModelRenderer PillerFR;
    public ModelRenderer PillerBR;
    public ModelRenderer PillerBL;
    public ModelRenderer PillerFL;
    public ModelRenderer FrameTop1;
    public ModelRenderer shape8;

    public ModelBlockArmourer() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.shape8 = new ModelRenderer((ModelBase)this, 0, 51);
        this.shape8.func_78793_a(0.0f, 1.5f, 0.0f);
        this.shape8.func_78789_a(-2.5f, -2.5f, -2.5f, 5, 5, 5);
        this.PillerBL = new ModelRenderer((ModelBase)this, 0, 36);
        this.PillerBL.func_78793_a(6.0f, -3.0f, 6.0f);
        this.PillerBL.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 9, 2);
        this.FrameTop2 = new ModelRenderer((ModelBase)this, 8, 36);
        this.FrameTop2.func_78793_a(0.0f, -6.0f, 0.0f);
        this.FrameTop2.func_78789_a(-7.0f, 0.0f, -7.0f, 14, 1, 14);
        this.FrameTop1 = new ModelRenderer((ModelBase)this, 0, 18);
        this.FrameTop1.func_78793_a(0.0f, -5.0f, 0.0f);
        this.FrameTop1.func_78789_a(-8.0f, 0.0f, -8.0f, 16, 2, 16);
        this.PillerFL = new ModelRenderer((ModelBase)this, 0, 36);
        this.PillerFL.func_78793_a(6.0f, -3.0f, -6.0f);
        this.PillerFL.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 9, 2);
        this.PillerBR = new ModelRenderer((ModelBase)this, 0, 36);
        this.PillerBR.func_78793_a(-6.0f, -3.0f, 6.0f);
        this.PillerBR.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 9, 2);
        this.FrameBottom = new ModelRenderer((ModelBase)this, 0, 0);
        this.FrameBottom.func_78793_a(0.0f, 8.0f, 0.0f);
        this.FrameBottom.func_78789_a(-8.0f, -2.0f, -8.0f, 16, 2, 16);
        this.PillerFR = new ModelRenderer((ModelBase)this, 0, 36);
        this.PillerFR.func_78793_a(-6.0f, -3.0f, -6.0f);
        this.PillerFR.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 9, 2);
    }

    public void render(TileEntityMiniArmourer tileEntity, float tickTime, float scale) {
        Minecraft.func_71410_x().field_71446_o.func_110577_a(modelImage);
        if (tileEntity != null) {
            float angle = (float)((tileEntity.func_145831_w().func_82737_E() + (long)((Object)((Object)tileEntity)).hashCode()) % 360L) + tickTime;
            this.setRotateAngle(this.shape8, (float)Math.toRadians(angle * 4.0f), (float)Math.toRadians(angle), (float)Math.toRadians(angle * 2.0f));
            Color c = new Color(tileEntity.red, tileEntity.green, tileEntity.blue);
            if (tileEntity.func_145831_w().func_82737_E() % 2L == 1L) {
                c = UtilColour.addColourNoise(c, 3);
            }
            tileEntity.red = c.getRed();
            tileEntity.green = c.getGreen();
            tileEntity.blue = c.getBlue();
            float r = (float)tileEntity.red / 255.0f;
            float g = (float)tileEntity.green / 255.0f;
            float b = (float)tileEntity.blue / 255.0f;
            GL11.glColor3f((float)r, (float)g, (float)b);
        } else {
            this.setRotateAngle(this.shape8, 0.0f, 0.0f, 0.0f);
        }
        this.shape8.func_78785_a(scale);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        this.PillerBL.func_78785_a(scale);
        this.FrameTop2.func_78785_a(scale);
        this.FrameTop1.func_78785_a(scale);
        this.PillerFL.func_78785_a(scale);
        this.PillerBR.func_78785_a(scale);
        this.FrameBottom.func_78785_a(scale);
        this.PillerFR.func_78785_a(scale);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

