/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.MathHelper
 */
package riskyken.armourersWorkshop.client.model.bake;

import net.minecraft.util.MathHelper;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.client.model.bake.FaceRenderer;
import riskyken.armourersWorkshop.client.render.IRenderBuffer;
import riskyken.armourersWorkshop.client.render.SkinPartRenderData;
import riskyken.armourersWorkshop.client.skin.ClientSkinPartData;

public class ColouredFace {
    public final byte x;
    public final byte y;
    public final byte z;
    public final byte r;
    public final byte g;
    public final byte b;
    private final byte a;
    private final byte t;
    public final byte face;
    private final byte lodLevel;

    public ColouredFace(byte x, byte y, byte z, byte r, byte g, byte b, byte a, byte paintType, byte face, byte lodLevel) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        this.t = paintType;
        this.face = face;
        this.lodLevel = lodLevel;
    }

    public void renderVertex(IRenderBuffer renderBuffer, SkinPartRenderData renderData, ClientSkinPartData cspd, boolean useTexture) {
        byte r = this.r;
        byte g = this.g;
        byte b = this.b;
        int type = this.t & 0xFF;
        ISkinDye skinDye = renderData.getSkinDye();
        byte[] extraColour = renderData.getExtraColours();
        if (type != 0) {
            int[] averageRGB;
            byte[] dye;
            if (type >= 1 && type <= 8 && skinDye != null && skinDye.haveDyeInSlot(type - 1) && (dye = skinDye.getDyeColour(type - 1)).length == 4) {
                if ((dye[3] & 0xFF) == 0) {
                    return;
                }
                int dyeType = dye[3] & 0xFF;
                int[] averageRGB2 = cspd.getAverageDyeColour(type - 1);
                byte[] dyedColour = null;
                dyedColour = dyeType == 253 & extraColour != null ? ColouredFace.dyeColour(r, g, b, new byte[]{extraColour[0], extraColour[1], extraColour[2]}, averageRGB2) : (dyeType == 254 & extraColour != null ? ColouredFace.dyeColour(r, g, b, new byte[]{extraColour[3], extraColour[4], extraColour[5]}, averageRGB2) : ColouredFace.dyeColour(r, g, b, dye, averageRGB2));
                r = dyedColour[0];
                g = dyedColour[1];
                b = dyedColour[2];
            }
            if (type == 253 & extraColour != null) {
                averageRGB = cspd.getAverageDyeColour(8);
                byte[] dyedColour = ColouredFace.dyeColour(r, g, b, new byte[]{extraColour[0], extraColour[1], extraColour[2]}, averageRGB);
                r = dyedColour[0];
                g = dyedColour[1];
                b = dyedColour[2];
            }
            if (type == 254 & extraColour != null) {
                averageRGB = cspd.getAverageDyeColour(9);
                byte[] dyedColour = ColouredFace.dyeColour(r, g, b, new byte[]{extraColour[3], extraColour[4], extraColour[5]}, averageRGB);
                r = dyedColour[0];
                g = dyedColour[1];
                b = dyedColour[2];
            }
            FaceRenderer.renderFace(this.x, this.y, this.z, r, g, b, this.a, this.face, useTexture, this.lodLevel);
        }
    }

    public static byte[] dyeColour(byte r, byte g, byte b, byte[] dyeColour, int[] modelAverageColour) {
        int average = ((r & 0xFF) + (g & 0xFF) + (b & 0xFF)) / 3;
        int modelAverage = (modelAverageColour[0] + modelAverageColour[1] + modelAverageColour[2]) / 3;
        int nR = average + (dyeColour[0] & 0xFF) - modelAverage;
        int nG = average + (dyeColour[1] & 0xFF) - modelAverage;
        int nB = average + (dyeColour[2] & 0xFF) - modelAverage;
        nR = MathHelper.func_76125_a((int)nR, (int)0, (int)255);
        nG = MathHelper.func_76125_a((int)nG, (int)0, (int)255);
        nB = MathHelper.func_76125_a((int)nB, (int)0, (int)255);
        return new byte[]{(byte)nR, (byte)nG, (byte)nB};
    }
}

