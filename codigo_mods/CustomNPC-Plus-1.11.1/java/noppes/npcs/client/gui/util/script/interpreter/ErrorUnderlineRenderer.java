/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util.script.interpreter;

import noppes.npcs.client.ClientProxy;
import noppes.npcs.client.gui.util.script.interpreter.DocumentError;
import noppes.npcs.client.gui.util.script.interpreter.ScriptDocument;
import noppes.npcs.client.gui.util.script.interpreter.ScriptLine;
import org.lwjgl.opengl.GL11;

public class ErrorUnderlineRenderer {
    private static final int ERROR_COLOR = 0xFF5555;

    public static void drawErrorUnderlines(ScriptDocument doc, int lineStartX, int baselineY, String lineText, int lineStart, int lineEnd) {
        ErrorUnderlineRenderer.drawErrorUnderlines(doc, null, lineStartX, baselineY, lineText, lineStart, lineEnd);
    }

    public static void drawErrorUnderlines(ScriptDocument doc, ScriptLine line, int lineStartX, int baselineY, String lineText, int lineStart, int lineEnd) {
        if (doc == null) {
            return;
        }
        for (DocumentError error : doc.getErrors()) {
            ErrorUnderlineRenderer.drawUnderlineForSpan(error.getStartPos(), error.getEndPos(), lineStartX, baselineY, lineText, lineStart, lineEnd, 0xFF5555, line);
        }
    }

    private static UnderlinePosition calculateUnderlinePosition(int spanStart, int spanEnd, int lineStartX, String lineText, int lineStart, int lineEnd, ScriptLine line) {
        int spanWidth;
        int beforeWidth;
        int clipEnd;
        if (spanEnd < lineStart || spanStart > lineEnd) {
            return null;
        }
        int clipStart = Math.max(spanStart, lineStart);
        if (clipStart >= (clipEnd = Math.min(spanEnd, lineEnd))) {
            return null;
        }
        int lineLocalStart = clipStart - lineStart;
        int lineLocalEnd = clipEnd - lineStart;
        if (lineLocalStart < 0 || lineLocalStart >= lineText.length()) {
            return null;
        }
        if (line != null) {
            beforeWidth = line.getRenderedWidth(0, lineLocalStart);
            int clampedEnd = Math.min(lineLocalEnd, lineText.length());
            spanWidth = line.getRenderedWidth(lineLocalStart, clampedEnd);
        } else {
            String beforeSpan = lineText.substring(0, lineLocalStart);
            beforeWidth = ClientProxy.Font.width(beforeSpan);
            if (lineLocalEnd > lineText.length()) {
                spanWidth = ClientProxy.Font.width(lineText.substring(lineLocalStart));
            } else {
                String spanTextOnLine = lineText.substring(lineLocalStart, lineLocalEnd);
                spanWidth = ClientProxy.Font.width(spanTextOnLine);
            }
        }
        return new UnderlinePosition(lineStartX + beforeWidth, spanWidth);
    }

    public static void drawUnderlineForSpan(int spanStart, int spanEnd, int lineStartX, int baselineY, String lineText, int lineStart, int lineEnd, int color) {
        ErrorUnderlineRenderer.drawUnderlineForSpan(spanStart, spanEnd, lineStartX, baselineY, lineText, lineStart, lineEnd, color, null);
    }

    public static void drawUnderlineForSpan(int spanStart, int spanEnd, int lineStartX, int baselineY, String lineText, int lineStart, int lineEnd, int color, ScriptLine line) {
        UnderlinePosition pos = ErrorUnderlineRenderer.calculateUnderlinePosition(spanStart, spanEnd, lineStartX, lineText, lineStart, lineEnd, line);
        if (pos != null && pos.isValid()) {
            ErrorUnderlineRenderer.drawCurlyUnderline(pos.x, baselineY, pos.width, color);
        }
    }

    public static void drawCurlyUnderline(int x, int y, int width, int color) {
        if (width <= 0) {
            return;
        }
        float a = (float)(color >> 24 & 0xFF) / 255.0f;
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        if (a == 0.0f) {
            a = 1.0f;
        }
        GL11.glPushMatrix();
        GL11.glDisable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)r, (float)g, (float)b, (float)a);
        GL11.glLineWidth((float)1.0f);
        GL11.glBegin((int)3);
        boolean waveHeight = true;
        float waveLength = 4.0f;
        for (float i = -0.5f; i <= (float)(width - 1); i += 0.125f) {
            double phase = (double)i / (double)waveLength * Math.PI * 2.0;
            float yOffset = (float)(Math.sin(phase) * (double)waveHeight) - 0.25f;
            GL11.glVertex2f((float)((float)x + i + 2.0f), (float)((float)y + yOffset));
        }
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glPopMatrix();
    }

    private static class UnderlinePosition {
        final int x;
        final int width;

        UnderlinePosition(int x, int width) {
            this.x = x;
            this.width = width;
        }

        boolean isValid() {
            return this.width > 0;
        }
    }
}

