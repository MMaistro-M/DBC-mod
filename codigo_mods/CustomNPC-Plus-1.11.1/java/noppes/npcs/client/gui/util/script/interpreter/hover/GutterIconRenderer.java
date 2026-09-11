/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util.script.interpreter.hover;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.ClientProxy;
import noppes.npcs.client.gui.util.GuiUtil;
import noppes.npcs.client.gui.util.script.interpreter.method.MethodInfo;
import noppes.npcs.client.gui.util.script.interpreter.token.TokenType;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;
import org.lwjgl.opengl.GL11;

public class GutterIconRenderer {
    private static final ResourceLocation SCRIPT_ICONS = new ResourceLocation("customnpcs", "textures/gui/script/icons.png");
    private static final int GUTTER_ICON_SIZE = 10;
    public static final int ICON_GUTTER_WIDTH = 12;
    private static final int PADDING = 6;
    private static final int LINE_SPACING = 2;
    private static final int VERTICAL_OFFSET = 10;
    private static final int BG_COLOR = -265211083;
    private static final int BORDER_COLOR = -12828863;
    private static final int INFO_COLOR = -5654586;

    public static MethodInfo renderIcons(int lineHeight, int gutterX, int gutterY, int renderStart, int renderEnd, int scrolledLine, int stringYOffset, List<MethodInfo> methods, List<?> lines, int xMouse, int yMouse, float fracPixels) {
        if (methods == null || methods.isEmpty()) {
            return null;
        }
        MethodInfo hoveredMethod = null;
        float adjustedMouseY = (float)yMouse + fracPixels;
        for (int lineIndex = renderStart; lineIndex <= renderEnd; ++lineIndex) {
            MethodInfo method = GutterIconRenderer.getMethodAtLine(lineIndex, methods, lines);
            if (method == null || !method.hasInheritanceMarker()) continue;
            int posY = gutterY + (lineIndex - scrolledLine) * lineHeight + stringYOffset;
            int iconY = posY + (lineHeight - 10) / 2 - 1;
            GutterIconRenderer.renderIcon(gutterX, iconY, method.isOverride());
            int iconScaleOffsetX = -4;
            int screenPosY = gutterY + (lineIndex - scrolledLine) * lineHeight;
            if (xMouse < gutterX + iconScaleOffsetX || xMouse >= gutterX + 10 + iconScaleOffsetX || !(adjustedMouseY >= (float)screenPosY) || !(adjustedMouseY < (float)(screenPosY + lineHeight))) continue;
            hoveredMethod = method;
        }
        return hoveredMethod;
    }

    private static void renderIcon(int x, int y, boolean isOverride) {
        int iconU = isOverride ? 0 : 32;
        Minecraft.func_71410_x().field_71446_o.func_110577_a(SCRIPT_ICONS);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPushMatrix();
        float scale = 2.0f;
        float scaleOffsetX = -4.0f;
        float scaleOffsetY = -3.25f;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        GL11.glTranslatef((float)scaleOffsetX, (float)scaleOffsetY, (float)0.0f);
        GuiUtil.drawScaledTexturedRect((int)((float)x / scale), (int)((float)y / scale), iconU, 0, 32, 32, 10, 10, 64, 32);
        GL11.glPopMatrix();
    }

    public static void renderTooltip(MethodInfo method, int mouseX, int mouseY, int viewportX, int viewportWidth, int viewportY, int viewportHeight) {
        if (method == null) {
            return;
        }
        List<TextSegment> segments = GutterIconRenderer.buildTooltipContent(method);
        if (segments.isEmpty()) {
            return;
        }
        int lineHeight = ClientProxy.Font.height();
        int contentWidth = GutterIconRenderer.calculateContentWidth(segments);
        int contentHeight = lineHeight;
        int boxWidth = contentWidth + 12;
        int boxHeight = contentHeight + 12;
        int tooltipX = mouseX + 10;
        int tooltipY = mouseY - 5;
        int rightBound = viewportX + viewportWidth;
        int bottomBound = viewportY + viewportHeight;
        if (tooltipX + boxWidth > rightBound) {
            tooltipX = mouseX - boxWidth - 5;
        }
        if (tooltipX < viewportX) {
            tooltipX = viewportX;
        }
        if (tooltipY + boxHeight > bottomBound) {
            tooltipY = bottomBound - boxHeight;
        }
        if (tooltipY < viewportY) {
            tooltipY = viewportY;
        }
        GutterIconRenderer.renderTooltipBox(tooltipX, tooltipY, boxWidth, boxHeight, segments);
    }

    private static MethodInfo getMethodAtLine(int lineIndex, List<MethodInfo> methods, List<?> lines) {
        int lineEnd;
        int lineStart;
        if (lineIndex < 0 || lineIndex >= lines.size()) {
            return null;
        }
        Object lineObj = lines.get(lineIndex);
        try {
            Field startField = lineObj.getClass().getField("start");
            Field endField = lineObj.getClass().getField("end");
            lineStart = startField.getInt(lineObj);
            lineEnd = endField.getInt(lineObj);
        }
        catch (Exception e) {
            return null;
        }
        for (MethodInfo method : methods) {
            int nameOffset;
            if (!method.hasInheritanceMarker() || (nameOffset = method.getNameOffset()) < lineStart || nameOffset >= lineEnd) continue;
            return method;
        }
        return null;
    }

    private static List<TextSegment> buildTooltipContent(MethodInfo method) {
        ArrayList<TextSegment> segments = new ArrayList<TextSegment>();
        if (method.isOverride()) {
            TypeInfo overridesFrom = method.getOverridesFrom();
            segments.add(new TextSegment("Overrides method in ", -5654586));
            if (overridesFrom != null) {
                int color = TokenType.getColor(overridesFrom);
                segments.add(new TextSegment(overridesFrom.getSimpleName(), color));
            } else {
                segments.add(new TextSegment("parent class", -5654586));
            }
        } else if (method.isImplements()) {
            TypeInfo implementsFrom = method.getImplementsFrom();
            segments.add(new TextSegment("Implements method from ", -5654586));
            if (implementsFrom != null) {
                int color = TokenType.getColor(implementsFrom);
                segments.add(new TextSegment(implementsFrom.getSimpleName(), color));
            } else {
                segments.add(new TextSegment("interface", -5654586));
            }
        }
        return segments;
    }

    private static int calculateContentWidth(List<TextSegment> segments) {
        int totalWidth = 0;
        for (TextSegment segment : segments) {
            totalWidth += ClientProxy.Font.width(segment.text);
        }
        return totalWidth;
    }

    private static void renderTooltipBox(int x, int y, int width, int height, List<TextSegment> segments) {
        GL11.glDisable((int)3089);
        int paddedHeight = y + height - 4;
        Gui.func_73734_a((int)x, (int)y, (int)(x + width), (int)paddedHeight, (int)-265211083);
        Gui.func_73734_a((int)x, (int)y, (int)(x + width), (int)(y + 1), (int)-12828863);
        Gui.func_73734_a((int)x, (int)(paddedHeight - 1), (int)(x + width), (int)paddedHeight, (int)-12828863);
        Gui.func_73734_a((int)x, (int)y, (int)(x + 1), (int)paddedHeight, (int)-12828863);
        Gui.func_73734_a((int)(x + width - 1), (int)y, (int)(x + width), (int)paddedHeight, (int)-12828863);
        int currentX = x + 6;
        int currentY = y + 6;
        for (TextSegment segment : segments) {
            ClientProxy.Font.drawString(segment.text, currentX, currentY, segment.color);
            currentX += ClientProxy.Font.width(segment.text);
        }
    }

    private static class TextSegment {
        final String text;
        final int color;

        TextSegment(String text, int color) {
            this.text = text;
            this.color = color;
        }
    }
}

