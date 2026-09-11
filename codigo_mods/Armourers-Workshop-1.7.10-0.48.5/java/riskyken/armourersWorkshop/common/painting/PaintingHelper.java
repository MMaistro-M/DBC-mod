/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package riskyken.armourersWorkshop.common.painting;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import riskyken.armourersWorkshop.common.data.PlayerPointer;
import riskyken.armourersWorkshop.common.painting.PaintType;
import riskyken.armourersWorkshop.common.wardrobe.EquipmentWardrobeData;
import riskyken.armourersWorkshop.common.wardrobe.ExtraColours;
import riskyken.armourersWorkshop.proxies.ClientProxy;

public final class PaintingHelper {
    private static final String TAG_TOOL_PAINT = "toolPaint";

    public static boolean getToolHasPaint(ItemStack stack) {
        NBTTagCompound compound = stack.func_77978_p();
        return compound != null && compound.func_74764_b(TAG_TOOL_PAINT);
    }

    public static void setToolPaintColour(ItemStack stack, byte[] rgb) {
        byte[] rgbt = PaintingHelper.getToolPaintData(stack);
        rgbt[0] = rgb[0];
        rgbt[1] = rgb[1];
        rgbt[2] = rgb[2];
        PaintingHelper.setToolPaintData(stack, rgbt);
    }

    public static void setToolPaintColour(ItemStack stack, int colour) {
        byte[] rgbt = PaintingHelper.getToolPaintData(stack);
        Color c = new Color(colour);
        rgbt[0] = (byte)c.getRed();
        rgbt[1] = (byte)c.getGreen();
        rgbt[2] = (byte)c.getBlue();
        PaintingHelper.setToolPaintData(stack, rgbt);
    }

    public static byte[] getToolPaintColourArray(ItemStack stack) {
        byte[] rgbt = PaintingHelper.getToolPaintData(stack);
        return new byte[]{rgbt[0], rgbt[1], rgbt[2]};
    }

    public static int getToolPaintColourRGB(ItemStack stack) {
        return PaintingHelper.getToolPaintColour(stack).getRGB();
    }

    public static Color getToolPaintColour(ItemStack stack) {
        byte[] rgbt = PaintingHelper.getToolPaintData(stack);
        return new Color(rgbt[0] & 0xFF, rgbt[1] & 0xFF, rgbt[2] & 0xFF, 255);
    }

    public static void setToolPaint(ItemStack stack, PaintType paintType) {
        byte[] rgbt = PaintingHelper.getToolPaintData(stack);
        rgbt[3] = (byte)paintType.getKey();
        PaintingHelper.setToolPaintData(stack, rgbt);
    }

    public static PaintType getToolPaintType(ItemStack stack) {
        byte[] rgbt = PaintingHelper.getToolPaintData(stack);
        return PaintType.getPaintTypeFormSKey(rgbt[3]);
    }

    public static byte[] getToolPaintData(ItemStack stack) {
        NBTTagCompound compound = stack.func_77978_p();
        if (compound != null && compound.func_150297_b(TAG_TOOL_PAINT, 7)) {
            return compound.func_74770_j(TAG_TOOL_PAINT);
        }
        return PaintingHelper.getBlankPaintData();
    }

    public static void setToolPaintData(ItemStack stack, byte[] paintData) {
        NBTTagCompound compound = stack.func_77978_p();
        if (compound == null) {
            compound = new NBTTagCompound();
        }
        compound.func_74773_a(TAG_TOOL_PAINT, paintData);
        stack.func_77982_d(compound);
    }

    public static void setPaintData(NBTTagCompound compound, byte[] paintData) {
        compound.func_74773_a(TAG_TOOL_PAINT, paintData);
    }

    public static byte[] getBlankRGBColour() {
        return new byte[]{-1, -1, -1};
    }

    public static byte[] getBlankPaintData() {
        return new byte[]{-1, -1, -1, -1};
    }

    @SideOnly(value=Side.CLIENT)
    public static int getLocalPlayersSkinColour() {
        PlayerPointer playerPointer = new PlayerPointer((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
        EquipmentWardrobeData ewd = ClientProxy.equipmentWardrobeHandler.getEquipmentWardrobeData(playerPointer);
        if (ewd != null) {
            return ewd.getExtraColours().getColour(ExtraColours.ExtraColourType.SKIN);
        }
        return Color.decode("#F9DFD2").getRGB();
    }

    @SideOnly(value=Side.CLIENT)
    public static int getLocalPlayersHairColour() {
        PlayerPointer playerPointer = new PlayerPointer((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
        EquipmentWardrobeData ewd = ClientProxy.equipmentWardrobeHandler.getEquipmentWardrobeData(playerPointer);
        if (ewd != null) {
            return ewd.getExtraColours().getColour(ExtraColours.ExtraColourType.HAIR);
        }
        return Color.decode("#804020").getRGB();
    }

    @SideOnly(value=Side.CLIENT)
    public static byte[] getLocalPlayerExtraColours() {
        int skin = PaintingHelper.getLocalPlayersSkinColour();
        int hair = PaintingHelper.getLocalPlayersHairColour();
        byte[] ec = new byte[]{(byte)(skin >>> 16 & 0xFF), (byte)(skin >>> 8 & 0xFF), (byte)(skin & 0xFF), (byte)(hair >>> 16 & 0xFF), (byte)(hair >>> 8 & 0xFF), (byte)(hair & 0xFF)};
        return ec;
    }

    public static byte[] intToBytes(int trgb) {
        int t = 0xFF & trgb >> 24;
        int r = 0xFF & trgb >> 16;
        int g = 0xFF & trgb >> 8;
        int b = 0xFF & trgb >> 0;
        return new byte[]{(byte)r, (byte)g, (byte)b, (byte)t};
    }

    public static int bytesToInt(byte[] rgbt) {
        return (rgbt[3] & 0xFF) << 24 | (rgbt[0] & 0xFF) << 16 | (rgbt[1] & 0xFF) << 8 | rgbt[2] & 0xFF;
    }
}

