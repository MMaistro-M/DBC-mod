/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.AbstractTexture
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.client.resources.IResourceManager
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.skin;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.image.BufferedImage;
import java.io.IOException;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.client.model.bake.ColouredFace;
import riskyken.armourersWorkshop.client.skin.SkinTextureKey;
import riskyken.armourersWorkshop.common.painting.PaintType;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.utils.BitwiseUtils;

@SideOnly(value=Side.CLIENT)
public class SkinModelTexture
extends AbstractTexture {
    private final BufferedImage texture = new BufferedImage(64, 32, 2);

    public void createTextureForColours(Skin skin, SkinTextureKey cmk) {
        for (int ix = 0; ix < 64; ++ix) {
            for (int iy = 0; iy < 32; ++iy) {
                int paintColour = skin.getPaintData()[ix + iy * 64];
                int paintType = BitwiseUtils.getUByteFromInt(paintColour, 0);
                if (cmk != null) {
                    int dyeNumber;
                    int colour;
                    if (paintType == 255) {
                        this.texture.setRGB(ix, iy, BitwiseUtils.setUByteToInt(paintColour, 0, 255));
                    }
                    if (paintType == 254) {
                        byte[] hairColour = cmk.getExtraColours();
                        colour = this.dyeColour(new byte[]{hairColour[3], hairColour[4], hairColour[5]}, paintColour, 9, skin, cmk);
                        this.texture.setRGB(ix, iy, BitwiseUtils.setUByteToInt(colour, 0, 255));
                    }
                    if (paintType == 253) {
                        byte[] skinColour = cmk.getExtraColours();
                        colour = this.dyeColour(new byte[]{skinColour[0], skinColour[1], skinColour[2]}, paintColour, 8, skin, cmk);
                        this.texture.setRGB(ix, iy, BitwiseUtils.setUByteToInt(colour, 0, 255));
                    }
                    if (!(paintType >= 1 & paintType <= 8)) continue;
                    ISkinDye skinDye = cmk.getSkinDye();
                    if (skinDye.haveDyeInSlot(dyeNumber = paintType - 1)) {
                        byte[] dye = skinDye.getDyeColour(dyeNumber);
                        if ((dye[3] & 0xFF) == 0) continue;
                        int colour2 = this.dyeColour(dye, paintColour, dyeNumber, skin, cmk);
                        this.texture.setRGB(ix, iy, colour2);
                        continue;
                    }
                    this.texture.setRGB(ix, iy, BitwiseUtils.setUByteToInt(paintColour, 0, 255));
                    continue;
                }
                if (paintType == 255) {
                    this.texture.setRGB(ix, iy, BitwiseUtils.setUByteToInt(paintColour, 0, 255));
                }
                if (!(paintType >= 1 & paintType <= 8)) continue;
                this.texture.setRGB(ix, iy, BitwiseUtils.setUByteToInt(paintColour, 0, 255));
            }
        }
    }

    private int dyeColour(byte[] dye, int colour, int dyeIndex, Skin skin, SkinTextureKey cmk) {
        byte r = (byte)(colour >>> 16 & 0xFF);
        byte g = (byte)(colour >>> 8 & 0xFF);
        byte b = (byte)(colour & 0xFF);
        if (dye.length > 3) {
            byte t = dye[3];
            if ((t & 0xFF) == PaintType.HAIR.getKey()) {
                dye = new byte[]{cmk.getExtraColours()[3], cmk.getExtraColours()[4], cmk.getExtraColours()[5]};
            }
            if ((t & 0xFF) == PaintType.SKIN.getKey()) {
                dye = new byte[]{cmk.getExtraColours()[0], cmk.getExtraColours()[1], cmk.getExtraColours()[2]};
            }
        }
        int[] average = new int[]{127, 127, 127};
        if (skin != null) {
            average = skin.getAverageDyeColour(dyeIndex);
        }
        dye = ColouredFace.dyeColour(r, g, b, dye, average);
        return -16777216 + ((dye[0] & 0xFF) << 16) + ((dye[1] & 0xFF) << 8) + (dye[2] & 0xFF);
    }

    public void func_110551_a(IResourceManager resourceManager) throws IOException {
    }

    public void bindTexture() {
        if (this.field_110553_a == -1) {
            this.func_110552_b();
            TextureUtil.func_110987_a((int)this.field_110553_a, (BufferedImage)this.texture);
        }
        GL11.glBindTexture((int)3553, (int)this.field_110553_a);
    }
}

