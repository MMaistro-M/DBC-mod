/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.Tessellator
 */
package riskyken.armourersWorkshop.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import riskyken.armourersWorkshop.client.render.IRenderBuffer;

@SideOnly(value=Side.CLIENT)
public class RenderBridge
implements IRenderBuffer {
    public static IRenderBuffer INSTANCE = new RenderBridge();
    Tessellator tessellator = Tessellator.field_78398_a;

    public static void init() {
        INSTANCE = new RenderBridge();
    }

    @Override
    public void draw() {
        this.tessellator.func_78381_a();
    }

    @Override
    public void startDrawingQuads() {
        this.tessellator.func_78382_b();
    }

    @Override
    public void startDrawing(int drawMode) {
        this.tessellator.func_78371_b(drawMode);
    }

    @Override
    public void setBrightness(int brightness) {
        this.tessellator.func_78380_c(brightness);
    }

    @Override
    public void setColourRGBA_F(float r, float g, float b, float a) {
        this.tessellator.func_78369_a(r, g, b, a);
    }

    @Override
    public void setColourRGBA_B(byte r, byte g, byte b, byte a) {
        this.tessellator.func_78370_a(r & 0xFF, g & 0xFF, b & 0xFF, a & 0xFF);
    }

    @Override
    public void setColorOpaque_F(float r, float g, float b) {
        this.tessellator.func_78386_a(r, g, b);
    }

    @Override
    public void setColorOpaque_I(int r, int g, int b) {
        this.tessellator.func_78376_a(r, g, b);
    }

    @Override
    public void setColorOpaque_B(byte r, byte g, byte b) {
        this.tessellator.func_78376_a(r & 0xFF, g & 0xFF, b & 0xFF);
    }

    @Override
    public void setNormal(float x, float y, float z) {
        this.tessellator.func_78375_b(x, y, z);
    }

    @Override
    public void setTextureUV(double u, double v) {
        this.tessellator.func_78385_a(u, v);
    }

    @Override
    public void addVertex(double x, double y, double z) {
        this.tessellator.func_78377_a(x, y, z);
    }

    @Override
    public void addVertexWithUV(double x, double y, double z, double u, double v) {
        this.tessellator.func_78374_a(x, y, z, u, v);
    }
}

