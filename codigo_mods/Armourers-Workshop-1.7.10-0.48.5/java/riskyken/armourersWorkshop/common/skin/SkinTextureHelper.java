/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.util.ForgeDirection
 */
package riskyken.armourersWorkshop.common.skin;

import java.awt.Point;
import net.minecraftforge.common.util.ForgeDirection;
import riskyken.armourersWorkshop.api.common.IPoint3D;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartTypeTextured;
import riskyken.armourersWorkshop.common.tileentities.TileEntityBoundingBox;

public class SkinTextureHelper {
    public static Point getTextureLocationFromWorldBlock(TileEntityBoundingBox te, int side) {
        ISkinPartTypeTextured skinPart = (ISkinPartTypeTextured)te.getSkinPart();
        Point textureLocation = skinPart.getTextureLocation();
        IPoint3D textureModelSize = skinPart.getTextureModelSize();
        ForgeDirection blockFace = ForgeDirection.getOrientation((int)side);
        byte blockX = te.getGuideX();
        byte blockY = te.getGuideY();
        byte blockZ = te.getGuideZ();
        int textureX = textureLocation.x;
        int textureY = textureLocation.y;
        int shiftX = 0;
        int shiftY = 0;
        if (skinPart.isTextureMirrored() && blockFace == ForgeDirection.EAST | blockFace == ForgeDirection.WEST) {
            blockFace = blockFace.getOpposite();
        }
        switch (blockFace) {
            case EAST: {
                textureY += textureModelSize.getZ();
                shiftX = (byte)(-blockZ + textureModelSize.getZ() - 1);
                shiftY = (byte)(-blockY + textureModelSize.getY() - 1);
                break;
            }
            case NORTH: {
                textureX += textureModelSize.getZ();
                textureY += textureModelSize.getZ();
                shiftX = (byte)(-blockX + textureModelSize.getX() - 1);
                if (skinPart.isTextureMirrored()) {
                    shiftX = blockX;
                }
                shiftY = (byte)(-blockY + textureModelSize.getY() - 1);
                break;
            }
            case WEST: {
                textureX += textureModelSize.getZ() + textureModelSize.getX();
                textureY += textureModelSize.getZ();
                shiftX = blockZ;
                shiftY = (byte)(-blockY + textureModelSize.getY() - 1);
                break;
            }
            case SOUTH: {
                textureX += textureModelSize.getZ() + textureModelSize.getX() + textureModelSize.getZ();
                textureY += textureModelSize.getZ();
                shiftX = blockX;
                if (skinPart.isTextureMirrored()) {
                    shiftX = (byte)(-blockX + textureModelSize.getX() - 1);
                }
                shiftY = (byte)(-blockY + textureModelSize.getY() - 1);
                break;
            }
            case DOWN: {
                textureX += textureModelSize.getZ() + textureModelSize.getX();
                shiftX = (byte)(-blockX + textureModelSize.getX() - 1);
                if (skinPart.isTextureMirrored()) {
                    shiftX = blockX;
                }
                shiftY = (byte)(-blockZ + textureModelSize.getZ() - 1);
                break;
            }
            case UP: {
                textureX += textureModelSize.getZ();
                shiftX = (byte)(-blockX + textureModelSize.getX() - 1);
                if (skinPart.isTextureMirrored()) {
                    shiftX = blockX;
                }
                shiftY = (byte)(-blockZ + textureModelSize.getZ() - 1);
                break;
            }
        }
        return new Point(textureX += shiftX, textureY += shiftY);
    }
}

