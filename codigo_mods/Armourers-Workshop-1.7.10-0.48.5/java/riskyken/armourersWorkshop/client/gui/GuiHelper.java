/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.StringUtils
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringUtils;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.texture.PlayerTexture;
import riskyken.armourersWorkshop.common.data.TextureType;
import riskyken.armourersWorkshop.proxies.ClientProxy;

@SideOnly(value=Side.CLIENT)
public final class GuiHelper {
    private GuiHelper() {
    }

    public static void drawPlayerHead(int x, int y, int size, String username) {
        ResourceLocation rl = AbstractClientPlayer.field_110314_b;
        if (!StringUtils.func_151246_b((String)username)) {
            PlayerTexture playerTexture = GuiHelper.getPlayerTexture(username, TextureType.USER);
            rl = playerTexture.getResourceLocation();
        }
        Minecraft.func_71410_x().field_71446_o.func_110577_a(rl);
        int sourceSize = 8;
        Gui.func_152125_a((int)(x + 1), (int)(y + 1), (float)8.0f, (float)8.0f, (int)sourceSize, (int)sourceSize, (int)size, (int)size, (float)64.0f, (float)32.0f);
        Gui.func_152125_a((int)x, (int)y, (float)40.0f, (float)8.0f, (int)sourceSize, (int)sourceSize, (int)(size + 2), (int)(size + 2), (float)64.0f, (float)32.0f);
    }

    private static PlayerTexture getPlayerTexture(String textureString, TextureType textureType) {
        return ClientProxy.playerTextureDownloader.getPlayerTexture(textureString, textureType);
    }

    public static void renderLocalizedGuiName(FontRenderer fontRenderer, int xSize, String name) {
        GuiHelper.renderLocalizedGuiName(fontRenderer, xSize, name, null, 0x404040);
    }

    public static void renderLocalizedGuiName(FontRenderer fontRenderer, int xSize, String name, int colour) {
        GuiHelper.renderLocalizedGuiName(fontRenderer, xSize, name, null, colour);
    }

    public static void renderLocalizedGuiName(FontRenderer fontRenderer, int xSize, String name, String append) {
        GuiHelper.renderLocalizedGuiName(fontRenderer, xSize, name, append, 0x404040);
    }

    public static void renderLocalizedGuiName(FontRenderer fontRenderer, int xSize, String name, String append, int colour) {
        String unlocalizedName = "inventory." + "armourersWorkshop".toLowerCase() + ":" + name + ".name";
        String localizedName = StatCollector.func_74838_a((String)unlocalizedName);
        String renderText = unlocalizedName;
        if (!unlocalizedName.equals(localizedName)) {
            renderText = localizedName;
        }
        if (append != null) {
            renderText = renderText + " - " + append;
        }
        int xPos = xSize / 2 - fontRenderer.func_78256_a(renderText) / 2;
        fontRenderer.func_78276_b(renderText, xPos, 6, colour);
    }

    public static String getLocalizedControlName(String guiName, String controlName) {
        String localizedName;
        String unlocalizedName = "inventory." + "armourersWorkshop".toLowerCase() + ":" + guiName + "." + controlName;
        if (!unlocalizedName.equals(localizedName = StatCollector.func_74838_a((String)unlocalizedName))) {
            return localizedName;
        }
        return unlocalizedName;
    }

    public static void drawHoveringText(List textList, int xPos, int yPos, FontRenderer font, int width, int height, float zLevel) {
        if (!textList.isEmpty()) {
            GL11.glDisable((int)32826);
            GL11.glDisable((int)2896);
            GL11.glDisable((int)2929);
            int textWidth = 0;
            for (String line : textList) {
                int sWidth = font.func_78256_a(line);
                if (sWidth <= textWidth) continue;
                textWidth = sWidth;
            }
            int renderX = xPos + 12;
            int renderY = yPos - 12;
            int textHeight = 8;
            if (textList.size() > 1) {
                textHeight += 2 + (textList.size() - 1) * 10;
            }
            if (renderX + textWidth > width - 2) {
                renderX -= 28 + textWidth;
            }
            if (renderY + textHeight + 6 > height) {
                renderY = height - textHeight - 6;
            }
            if (renderY < 5) {
                renderY = 5;
            }
            zLevel = 300.0f;
            int j1 = -267386864;
            GuiHelper.drawGradientRect(renderX - 3, renderY - 4, renderX + textWidth + 3, renderY - 3, j1, j1, zLevel);
            GuiHelper.drawGradientRect(renderX - 3, renderY + textHeight + 3, renderX + textWidth + 3, renderY + textHeight + 4, j1, j1, zLevel);
            GuiHelper.drawGradientRect(renderX - 3, renderY - 3, renderX + textWidth + 3, renderY + textHeight + 3, j1, j1, zLevel);
            GuiHelper.drawGradientRect(renderX - 4, renderY - 3, renderX - 3, renderY + textHeight + 3, j1, j1, zLevel);
            GuiHelper.drawGradientRect(renderX + textWidth + 3, renderY - 3, renderX + textWidth + 4, renderY + textHeight + 3, j1, j1, zLevel);
            int k1 = 0x505000FF;
            int l1 = (k1 & 0xFEFEFE) >> 1 | k1 & 0xFF000000;
            GuiHelper.drawGradientRect(renderX - 3, renderY - 3 + 1, renderX - 3 + 1, renderY + textHeight + 3 - 1, k1, l1, zLevel);
            GuiHelper.drawGradientRect(renderX + textWidth + 2, renderY - 3 + 1, renderX + textWidth + 3, renderY + textHeight + 3 - 1, k1, l1, zLevel);
            GuiHelper.drawGradientRect(renderX - 3, renderY - 3, renderX + textWidth + 3, renderY - 3 + 1, k1, k1, zLevel);
            GuiHelper.drawGradientRect(renderX - 3, renderY + textHeight + 2, renderX + textWidth + 3, renderY + textHeight + 3, l1, l1, zLevel);
            for (int i2 = 0; i2 < textList.size(); ++i2) {
                String line = (String)textList.get(i2);
                font.func_78261_a(line, renderX, renderY, -1);
                if (i2 == 0) {
                    renderY += 2;
                }
                renderY += 10;
            }
            zLevel = 0.0f;
            GL11.glEnable((int)2929);
            GL11.glEnable((int)32826);
        }
    }

    private static void drawGradientRect(int p_73733_1_, int p_73733_2_, int p_73733_3_, int p_73733_4_, int p_73733_5_, int p_73733_6_, float zLevel) {
        float f = (float)(p_73733_5_ >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(p_73733_5_ >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(p_73733_5_ >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(p_73733_5_ & 0xFF) / 255.0f;
        float f4 = (float)(p_73733_6_ >> 24 & 0xFF) / 255.0f;
        float f5 = (float)(p_73733_6_ >> 16 & 0xFF) / 255.0f;
        float f6 = (float)(p_73733_6_ >> 8 & 0xFF) / 255.0f;
        float f7 = (float)(p_73733_6_ & 0xFF) / 255.0f;
        GL11.glDisable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3008);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        GL11.glShadeModel((int)7425);
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78369_a(f1, f2, f3, f);
        tessellator.func_78377_a((double)p_73733_3_, (double)p_73733_2_, (double)zLevel);
        tessellator.func_78377_a((double)p_73733_1_, (double)p_73733_2_, (double)zLevel);
        tessellator.func_78369_a(f5, f6, f7, f4);
        tessellator.func_78377_a((double)p_73733_1_, (double)p_73733_4_, (double)zLevel);
        tessellator.func_78377_a((double)p_73733_3_, (double)p_73733_4_, (double)zLevel);
        tessellator.func_78381_a();
        GL11.glShadeModel((int)7424);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3008);
        GL11.glEnable((int)3553);
    }
}

