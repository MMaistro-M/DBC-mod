/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.miniarmourer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.nio.FloatBuffer;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public final class GuiMiniArmourerHelper {
    private GuiMiniArmourerHelper() {
    }

    public static Color getColourAtPos(int x, int y) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer((int)3);
        GL11.glReadPixels((int)x, (int)y, (int)1, (int)1, (int)6407, (int)5126, (FloatBuffer)buffer);
        int r = Math.round(buffer.get() * 255.0f);
        int g = Math.round(buffer.get() * 255.0f);
        int b = Math.round(buffer.get() * 255.0f);
        return new Color(r, g, b);
    }

    public static int getIdFromColour(Color colour) {
        Color c = new Color(colour.getRGB());
        int id = c.getRed();
        id += c.getGreen() * 256;
        return id += c.getBlue() * 256 * 256;
    }

    public static Color getColourFromId(int id) {
        int r = 0;
        int g = 0;
        int b = 0;
        while (id > 65280) {
            ++b;
            id -= 65536;
        }
        while (id > 255) {
            ++g;
            id -= 256;
        }
        while (id > 0) {
            ++r;
            --id;
        }
        return new Color(r, g, b);
    }

    public static ForgeDirection getDirectionForCubeFace(int cubeFace) {
        ForgeDirection dir;
        switch (cubeFace) {
            case 1: {
                dir = ForgeDirection.EAST;
                break;
            }
            case 0: {
                dir = ForgeDirection.WEST;
                break;
            }
            case 4: {
                dir = ForgeDirection.DOWN;
                break;
            }
            case 5: {
                dir = ForgeDirection.UP;
                break;
            }
            case 3: {
                dir = ForgeDirection.NORTH;
                break;
            }
            case 2: {
                dir = ForgeDirection.SOUTH;
                break;
            }
            default: {
                dir = ForgeDirection.UNKNOWN;
            }
        }
        return dir;
    }
}

