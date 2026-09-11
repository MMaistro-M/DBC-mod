/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 */
package riskyken.armourersWorkshop.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public interface IRenderBuffer {
    public void draw();

    public void startDrawingQuads();

    public void startDrawing(int var1);

    public void setBrightness(int var1);

    public void setColourRGBA_F(float var1, float var2, float var3, float var4);

    public void setColourRGBA_B(byte var1, byte var2, byte var3, byte var4);

    public void setColorOpaque_F(float var1, float var2, float var3);

    public void setColorOpaque_I(int var1, int var2, int var3);

    public void setColorOpaque_B(byte var1, byte var2, byte var3);

    public void setNormal(float var1, float var2, float var3);

    public void setTextureUV(double var1, double var3);

    public void addVertex(double var1, double var3, double var5);

    public void addVertexWithUV(double var1, double var3, double var5, double var7, double var9);
}

