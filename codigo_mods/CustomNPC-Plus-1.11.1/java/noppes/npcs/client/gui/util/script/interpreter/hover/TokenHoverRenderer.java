/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util.script.interpreter.hover;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import noppes.npcs.client.ClientProxy;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.script.interpreter.hover.HoverState;
import noppes.npcs.client.gui.util.script.interpreter.hover.TokenHoverInfo;
import org.lwjgl.opengl.GL11;

public class TokenHoverRenderer {
    private static final int PADDING = 6;
    private static final int LINE_SPACING = 2;
    private static final int SEPARATOR_HEIGHT = 1;
    private static final int SEPARATOR_SPACING = 5;
    private static final int VERTICAL_OFFSET = 1;
    private static final float MAX_WIDTH_RATIO = 0.9f;
    private static final int MIN_WIDTH = 50;
    private static final int BG_COLOR = -265211083;
    private static final int BORDER_COLOR = -12828863;
    private static int PACKAGE_COLOR = -10186526;
    private static final int ERROR_COLOR = -38040;
    private static final int INFO_COLOR = -8355712;
    private static final int DOC_COLOR = -5654586;
    private static final int CODE_BG_COLOR = -14803166;
    private static final int CODE_ACCENT_COLOR = -11550209;
    private static final int CODE_INDENT = 10;
    private static final int CODE_VPAD = 6;
    private static final int BOTTOM_GAP = 4;

    public static void render(HoverState hoverState, int viewportX, int viewportWidth, int viewportY, int viewportHeight, GuiNPCInterface gui) {
        int availableWidth;
        int bottomBound;
        int tooltipY;
        int tooltipX;
        int maxVisibleContentHeight;
        if (!hoverState.isTooltipVisible()) {
            return;
        }
        hoverState.updateSmoothScroll();
        TokenHoverInfo info = hoverState.getHoverInfo();
        if (info == null || !info.hasContent()) {
            return;
        }
        int lineHeight = ClientProxy.Font.height();
        int tokenX = hoverState.getTokenScreenX();
        int tokenY = hoverState.getTokenScreenY();
        Minecraft mc2 = Minecraft.func_71410_x();
        ScaledResolution sr2 = new ScaledResolution(mc2, mc2.field_71443_c, mc2.field_71440_d);
        int screenW = sr2.func_78326_a();
        int screenH = sr2.func_78328_b();
        boolean positionOverridden = hoverState.hasOverriddenPosition();
        boolean useScreenBounds = positionOverridden || hoverState.hasOverriddenSize();
        int maxContentWidth = TokenHoverRenderer.getMaxContentWidth(viewportX, viewportWidth, tokenX);
        int contentWidth = TokenHoverRenderer.calculateContentWidth(info, maxContentWidth);
        int totalContentHeight = TokenHoverRenderer.calculateContentHeight(info, contentWidth);
        if (totalContentHeight > (maxVisibleContentHeight = (int)((float)(useScreenBounds ? screenH : viewportHeight) * 0.6f))) {
            totalContentHeight = TokenHoverRenderer.calculateContentHeight(info, contentWidth - 6);
        }
        int visibleContentHeight = Math.min(totalContentHeight, maxVisibleContentHeight);
        int boxWidth = contentWidth + 12;
        int boxHeight = visibleContentHeight + 12;
        if (hoverState.hasOverriddenSize()) {
            int overW = Math.max(62, hoverState.getOverriddenTooltipW());
            int overH = Math.max(lineHeight + 12, hoverState.getOverriddenTooltipH());
            boxWidth = overW;
            visibleContentHeight = overH - 12;
            boxHeight = overH;
            int newContentW = boxWidth - 12;
            totalContentHeight = TokenHoverRenderer.calculateContentHeight(info, newContentW);
            if (totalContentHeight > visibleContentHeight) {
                totalContentHeight = TokenHoverRenderer.calculateContentHeight(info, newContentW - 6);
            }
        }
        if (positionOverridden) {
            tooltipX = hoverState.getOverriddenTooltipX();
            tooltipY = hoverState.getOverriddenTooltipY();
        } else if (hoverState.isPositionLocked()) {
            tooltipX = hoverState.getLockedMouseX() + 2;
            tooltipY = tokenY + lineHeight + 1;
        } else {
            tooltipX = tokenX;
            tooltipY = tokenY + lineHeight + 1;
        }
        int leftBound = useScreenBounds ? 0 : viewportX;
        int rightBound = useScreenBounds ? screenW : viewportX + viewportWidth;
        int topBound = useScreenBounds ? 0 : viewportY;
        int n = bottomBound = useScreenBounds ? screenH : viewportY + viewportHeight;
        if (tooltipX + boxWidth > rightBound) {
            tooltipX = rightBound - boxWidth;
        }
        if (tooltipX < leftBound) {
            tooltipX = leftBound;
        }
        if (boxWidth > (availableWidth = rightBound - tooltipX)) {
            boxWidth = availableWidth;
        }
        if (tooltipY + boxHeight > bottomBound) {
            tooltipY = useScreenBounds ? bottomBound - boxHeight : tokenY - boxHeight;
        }
        if (tooltipY < topBound) {
            tooltipY = topBound;
        }
        hoverState.setTooltipPanel(tooltipX, tooltipY, boxWidth, boxHeight);
        if (hoverState.hasOverriddenPosition()) {
            hoverState.setTooltipBounds(tooltipX, tooltipY, boxWidth, boxHeight);
        } else if (tooltipY >= tokenY + lineHeight) {
            int boundsTopY = tokenY + lineHeight;
            int boundsBottomY = tooltipY + boxHeight;
            hoverState.setTooltipBounds(tooltipX, boundsTopY, boxWidth, boundsBottomY - boundsTopY);
        } else {
            hoverState.setTooltipBounds(tooltipX, tooltipY, boxWidth, boxHeight);
        }
        TokenHoverRenderer.renderTooltipBox(tooltipX, tooltipY, boxWidth, maxContentWidth, info, hoverState, totalContentHeight, visibleContentHeight, gui);
    }

    private static int getMaxContentWidth(int viewportX, int viewportWidth, int tokenX) {
        int maxWidth = (int)((float)viewportWidth * 0.9f);
        return Math.max(50, maxWidth);
    }

    private static void renderTooltipBox(int x, int y, int boxWidth, int wrapWidth, TokenHoverInfo info, HoverState hoverState, int totalContentHeight, int visibleContentHeight, GuiNPCInterface gui) {
        List<TokenHoverInfo.DocumentationLine> jsDocLines;
        List<String> wrappedLines;
        boolean hasScrollbar = totalContentHeight > visibleContentHeight;
        int contentWrapWidth = boxWidth - 12;
        int effectiveWrapWidth = hasScrollbar ? contentWrapWidth - 6 : contentWrapWidth;
        int boxHeight = visibleContentHeight + 12;
        hoverState.setTooltipMaxScroll(Math.max(0, totalContentHeight - visibleContentHeight + (hasScrollbar ? 4 : 0)));
        Gui.func_73734_a((int)x, (int)y, (int)(x + boxWidth), (int)(y + boxHeight), (int)-265211083);
        Gui.func_73734_a((int)x, (int)y, (int)(x + boxWidth), (int)(y + 1), (int)-12828863);
        Gui.func_73734_a((int)x, (int)(y + boxHeight - 1), (int)(x + boxWidth), (int)(y + boxHeight), (int)-12828863);
        Gui.func_73734_a((int)x, (int)y, (int)(x + 1), (int)(y + boxHeight), (int)-12828863);
        Gui.func_73734_a((int)(x + boxWidth - 1), (int)y, (int)(x + boxWidth), (int)(y + boxHeight), (int)-12828863);
        Minecraft mc = Minecraft.func_71410_x();
        ScaledResolution sr = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
        int sf = sr.func_78325_e();
        double panX = gui != null ? gui.getPanX() : 0.0;
        double panY = gui != null ? gui.getPanY() : 0.0;
        int clipX = (int)(((double)(x + 1) - panX) * (double)sf);
        int clipY = (int)(((double)sr.func_78328_b() - ((double)y - panY + (double)boxHeight - 1.0 - 4.0)) * (double)sf);
        int clipW = (hasScrollbar ? boxWidth - 6 - 1 : boxWidth - 2) * sf;
        int clipH = (boxHeight - 2 - 4) * sf;
        GL11.glEnable((int)3089);
        GL11.glScissor((int)clipX, (int)clipY, (int)clipW, (int)clipH);
        int textX = x + 6;
        int currentY = y + 6 - hoverState.getTooltipScrollOffset();
        int lineHeight = ClientProxy.Font.height();
        String packageName = info.getPackageName();
        List<TokenHoverInfo.TextSegment> declaration = info.getDeclaration();
        List<String> docs = info.getDocumentation();
        List<String> additionalInfo = info.getAdditionalInfo();
        List<String> errors = info.getErrors();
        if (!errors.isEmpty()) {
            boolean onlyErrors;
            for (String error : errors) {
                wrappedLines = TokenHoverRenderer.wrapText(error, effectiveWrapWidth);
                for (String line : wrappedLines) {
                    TokenHoverRenderer.drawText(textX, currentY, line, -38040);
                    currentY += lineHeight + 2;
                }
            }
            boolean bl = onlyErrors = !(errors == null || errors.isEmpty() || packageName != null && !packageName.isEmpty() || declaration != null && !declaration.isEmpty() || docs != null && !docs.isEmpty() || additionalInfo != null && !additionalInfo.isEmpty());
            if (!onlyErrors) {
                Gui.func_73734_a((int)textX, (int)(++currentY), (int)(x + boxWidth - 6), (int)(currentY + 1), (int)-12828863);
                currentY += 6;
            }
            currentY += 2;
        }
        if (packageName != null && !packageName.isEmpty()) {
            String packageText = "\u25cb " + packageName;
            List<String> wrappedLines2 = TokenHoverRenderer.wrapText(packageText, effectiveWrapWidth);
            for (String string : wrappedLines2) {
                TokenHoverRenderer.drawText(textX, currentY, string, PACKAGE_COLOR);
                currentY += lineHeight + 2;
            }
        }
        if (!declaration.isEmpty()) {
            currentY = TokenHoverRenderer.drawWrappedSegments(textX, currentY, effectiveWrapWidth, declaration);
            currentY += 2;
        }
        if (!docs.isEmpty()) {
            Gui.func_73734_a((int)textX, (int)currentY, (int)(x + boxWidth - 6), (int)(currentY + 1), (int)-12828863);
            currentY += 6;
            for (String doc : docs) {
                wrappedLines = TokenHoverRenderer.wrapText(doc, effectiveWrapWidth);
                for (String line : wrappedLines) {
                    TokenHoverRenderer.drawText(textX, currentY, line, -5654586);
                    currentY += lineHeight + 2;
                }
            }
        }
        if (!(jsDocLines = info.getJSDocLines()).isEmpty()) {
            if (docs.isEmpty() && !declaration.isEmpty()) {
                Gui.func_73734_a((int)textX, (int)currentY, (int)(x + boxWidth - 6), (int)(currentY + 1), (int)-12828863);
                currentY += 6;
            }
            boolean previousVisibleJSDocWasCode = false;
            for (int di = 0; di < jsDocLines.size(); ++di) {
                TokenHoverInfo.DocumentationLine documentationLine = jsDocLines.get(di);
                if (documentationLine.isCodeBlockFirst) {
                    int blockH = 6;
                    for (int k = di; k < jsDocLines.size() && jsDocLines.get((int)k).isCodeLine; ++k) {
                        TokenHoverInfo.DocumentationLine cl = jsDocLines.get(k);
                        if (!cl.isEmpty()) {
                            int clIndent = cl.codeLeadingSpaces * ClientProxy.Font.width(" ");
                            int clWrapW = Math.max(10, effectiveWrapWidth - 10 - clIndent);
                            blockH += TokenHoverRenderer.calculateSegmentsHeight(clWrapW, cl.segments) + 2;
                            continue;
                        }
                        blockH += lineHeight / 2;
                    }
                    blockH -= 4;
                    Gui.func_73734_a((int)textX, (int)(currentY - 6), (int)(textX + effectiveWrapWidth + 4), (int)(currentY - 6 + (blockH += 6)), (int)-14803166);
                    Gui.func_73734_a((int)textX, (int)(currentY - 6), (int)(textX + 2), (int)(currentY - 6 + blockH), (int)-11550209);
                }
                if (documentationLine.isCodeLine) {
                    if (!documentationLine.isEmpty()) {
                        int explicitIndent = documentationLine.codeLeadingSpaces * ClientProxy.Font.width(" ");
                        int codeX = textX + 10 + explicitIndent;
                        int codeWrapW = Math.max(10, effectiveWrapWidth - 10 - explicitIndent);
                        currentY = TokenHoverRenderer.drawWrappedSegments(codeX, currentY, codeWrapW, documentationLine.segments);
                        currentY += 2;
                        previousVisibleJSDocWasCode = true;
                        continue;
                    }
                    currentY += lineHeight / 2;
                    continue;
                }
                if (!documentationLine.isEmpty()) {
                    if (previousVisibleJSDocWasCode) {
                        currentY += 2;
                    }
                    currentY = TokenHoverRenderer.drawWrappedSegments(textX, currentY, effectiveWrapWidth, documentationLine.segments);
                    currentY += 2;
                    previousVisibleJSDocWasCode = false;
                    continue;
                }
                currentY += di > 0 && jsDocLines.get((int)(di - 1)).isCodeLine ? 2 : lineHeight / 2;
            }
        }
        if (!additionalInfo.isEmpty()) {
            currentY += 2;
            for (String infoLine : additionalInfo) {
                List<String> list = TokenHoverRenderer.wrapText(infoLine, effectiveWrapWidth);
                for (String line : list) {
                    TokenHoverRenderer.drawText(textX, currentY, line, -8355712);
                    currentY += lineHeight + 2;
                }
            }
        }
        GL11.glDisable((int)3089);
        if (hasScrollbar) {
            int scrollbarX = x + boxWidth - 6;
            int scrollbarTrackTop = y + 6;
            int n = visibleContentHeight - 4;
            Gui.func_73734_a((int)scrollbarX, (int)scrollbarTrackTop, (int)(scrollbarX + 3), (int)(scrollbarTrackTop + n), (int)0x40888888);
            float scrollRatio = (float)visibleContentHeight / (float)(totalContentHeight + 4);
            int thumbHeight = Math.max(6, (int)((float)n * scrollRatio));
            int effectiveMaxScroll = totalContentHeight - visibleContentHeight + 4;
            float scrollProgress = effectiveMaxScroll > 0 ? Math.min(1.0f, (float)hoverState.getTooltipScrollOffset() / (float)effectiveMaxScroll) : 0.0f;
            int thumbY = scrollbarTrackTop + (int)((float)(n - thumbHeight) * scrollProgress);
            thumbY = Math.max(scrollbarTrackTop, Math.min(scrollbarTrackTop + n - thumbHeight, thumbY));
            hoverState.setScrollbarThumb(scrollbarX, thumbY, thumbHeight, scrollbarTrackTop, n);
            int mouseX = hoverState.getLastMouseX();
            int mouseY = hoverState.getLastMouseY();
            boolean thumbActive = hoverState.isDraggingScrollbar() || hoverState.isMouseOverScrollbarThumb(mouseX, mouseY);
            int thumbColor = thumbActive ? -3355444 : -8355712;
            Gui.func_73734_a((int)scrollbarX, (int)thumbY, (int)(scrollbarX + 3), (int)(thumbY + thumbHeight), (int)thumbColor);
        }
        int mouseX = hoverState.getLastMouseX();
        int mouseY = hoverState.getLastMouseY();
        boolean bl = hoverState.isResizingTooltip() || hoverState.isMouseOverResizeHandle(mouseX, mouseY);
        int dotColor = bl ? -3355444 : -2138535800;
        int rhX = x + boxWidth - 2;
        int rhY = y + boxHeight - 2;
        Gui.func_73734_a((int)(rhX - 1), (int)(rhY - 1), (int)rhX, (int)rhY, (int)dotColor);
        Gui.func_73734_a((int)(rhX - 3), (int)(rhY - 1), (int)(rhX - 2), (int)rhY, (int)dotColor);
        Gui.func_73734_a((int)(rhX - 1), (int)(rhY - 3), (int)rhX, (int)(rhY - 2), (int)dotColor);
        Gui.func_73734_a((int)(rhX - 5), (int)(rhY - 1), (int)(rhX - 4), (int)rhY, (int)dotColor);
        Gui.func_73734_a((int)(rhX - 3), (int)(rhY - 3), (int)(rhX - 2), (int)(rhY - 2), (int)dotColor);
        Gui.func_73734_a((int)(rhX - 1), (int)(rhY - 5), (int)rhX, (int)(rhY - 4), (int)dotColor);
    }

    private static int drawWrappedSegments(int startX, int startY, int maxWidth, List<TokenHoverInfo.TextSegment> segments) {
        int lineHeight = ClientProxy.Font.height();
        int currentX = startX;
        int currentY = startY;
        for (TokenHoverInfo.TextSegment segment : segments) {
            String[] words;
            String text = segment.text;
            int color = segment.color;
            for (String word : words = text.split("(?<=\\s)|(?=\\s)")) {
                int wordWidth = ClientProxy.Font.width(word);
                if (currentX + wordWidth > startX + maxWidth && currentX > startX) {
                    currentY += lineHeight + 2;
                    currentX = startX;
                    if (word.trim().isEmpty()) continue;
                }
                TokenHoverRenderer.drawText(currentX, currentY, word, color);
                currentX += wordWidth;
            }
        }
        return currentY + lineHeight;
    }

    private static List<String> wrapText(String text, int maxWidth) {
        String[] paragraphs;
        ArrayList<String> lines = new ArrayList<String>();
        if (text == null || text.isEmpty()) {
            return lines;
        }
        for (String paragraph : paragraphs = text.split("\n", -1)) {
            if (paragraph.isEmpty()) {
                lines.add("");
                continue;
            }
            String[] words = paragraph.split(" ");
            StringBuilder currentLine = new StringBuilder();
            for (String word : words) {
                String testLine = currentLine.length() == 0 ? word : currentLine + " " + word;
                int testWidth = ClientProxy.Font.width(testLine);
                if (testWidth > maxWidth && currentLine.length() > 0) {
                    lines.add(currentLine.toString());
                    currentLine = new StringBuilder(word);
                    continue;
                }
                if (currentLine.length() > 0) {
                    currentLine.append(" ");
                }
                currentLine.append(word);
            }
            if (currentLine.length() <= 0) continue;
            lines.add(currentLine.toString());
        }
        if (lines.isEmpty()) {
            lines.add(text);
        }
        return lines;
    }

    private static void drawText(int x, int y, String text, int color) {
        ClientProxy.Font.drawString(text, x, y, color);
    }

    private static int calculateContentWidth(TokenHoverInfo info, int maxWidth) {
        List<String> wrappedLines;
        int longestLineWidth = 0;
        String packageName = info.getPackageName();
        if (packageName != null && !packageName.isEmpty()) {
            String packageText = "\u25cb " + packageName;
            List<String> wrappedLines2 = TokenHoverRenderer.wrapText(packageText, maxWidth);
            for (String string : wrappedLines2) {
                longestLineWidth = Math.max(longestLineWidth, ClientProxy.Font.width(string));
            }
        }
        if (!info.getDeclaration().isEmpty()) {
            int declarationLongestLine = TokenHoverRenderer.calculateSegmentsLongestLine(maxWidth, info.getDeclaration());
            longestLineWidth = Math.max(longestLineWidth, declarationLongestLine);
        }
        for (String error : info.getErrors()) {
            wrappedLines = TokenHoverRenderer.wrapText(error, maxWidth);
            for (String line : wrappedLines) {
                longestLineWidth = Math.max(longestLineWidth, ClientProxy.Font.width(line));
            }
        }
        for (String doc : info.getDocumentation()) {
            wrappedLines = TokenHoverRenderer.wrapText(doc, maxWidth);
            for (String line : wrappedLines) {
                longestLineWidth = Math.max(longestLineWidth, ClientProxy.Font.width(line));
            }
        }
        List<TokenHoverInfo.DocumentationLine> jsDocLines = info.getJSDocLines();
        if (jsDocLines != null) {
            for (TokenHoverInfo.DocumentationLine docLine : jsDocLines) {
                if (docLine.isEmpty()) continue;
                int n = docLine.isCodeLine ? 10 + docLine.codeLeadingSpaces * ClientProxy.Font.width(" ") : 0;
                int lineLongest = TokenHoverRenderer.calculateSegmentsLongestLine(maxWidth - n, docLine.segments) + n;
                longestLineWidth = Math.max(longestLineWidth, lineLongest);
            }
        }
        for (String line : info.getAdditionalInfo()) {
            List<String> list = TokenHoverRenderer.wrapText(line, maxWidth);
            for (String wrappedLine : list) {
                longestLineWidth = Math.max(longestLineWidth, ClientProxy.Font.width(wrappedLine));
            }
        }
        return Math.max(50, longestLineWidth);
    }

    private static int calculateContentHeight(TokenHoverInfo info, int contentWidth) {
        List<String> wrappedLines;
        int lineHeight = ClientProxy.Font.height();
        int totalHeight = 0;
        String packageName = info.getPackageName();
        List<TokenHoverInfo.TextSegment> declaration = info.getDeclaration();
        List<String> docs = info.getDocumentation();
        List<TokenHoverInfo.DocumentationLine> jsDocLines = info.getJSDocLines();
        List<String> additionalInfo = info.getAdditionalInfo();
        List<String> errors = info.getErrors();
        if (!errors.isEmpty()) {
            boolean onlyErrors;
            for (String error : errors) {
                wrappedLines = TokenHoverRenderer.wrapText(error, contentWidth);
                totalHeight += wrappedLines.size() * (lineHeight + 2);
            }
            boolean bl = onlyErrors = !(errors == null || errors.isEmpty() || packageName != null && !packageName.isEmpty() || declaration != null && !declaration.isEmpty() || docs != null && !docs.isEmpty() || jsDocLines != null && !jsDocLines.isEmpty() || additionalInfo != null && !additionalInfo.isEmpty());
            if (!onlyErrors) {
                totalHeight += 7;
            }
            totalHeight += 2;
        }
        if (packageName != null && !packageName.isEmpty()) {
            String packageText = "\u25cb " + packageName;
            List<String> wrappedLines2 = TokenHoverRenderer.wrapText(packageText, contentWidth);
            totalHeight += wrappedLines2.size() * (lineHeight + 2);
        }
        if (!declaration.isEmpty()) {
            totalHeight += TokenHoverRenderer.calculateSegmentsHeight(contentWidth, declaration);
            totalHeight += 2;
        }
        if (!docs.isEmpty()) {
            totalHeight += 6;
            for (String doc : docs) {
                wrappedLines = TokenHoverRenderer.wrapText(doc, contentWidth);
                totalHeight += wrappedLines.size() * (lineHeight + 2);
            }
        }
        if (jsDocLines != null && !jsDocLines.isEmpty()) {
            if (docs.isEmpty() && !declaration.isEmpty()) {
                totalHeight += 6;
            }
            boolean previousVisibleJSDocWasCode = false;
            for (int i = 0; i < jsDocLines.size(); ++i) {
                TokenHoverInfo.DocumentationLine docLine = jsDocLines.get(i);
                if (!docLine.isEmpty()) {
                    int explicitIndent = docLine.isCodeLine ? docLine.codeLeadingSpaces * ClientProxy.Font.width(" ") : 0;
                    int wrapW = Math.max(10, contentWidth - (docLine.isCodeLine ? 10 : 0) - explicitIndent);
                    if (!docLine.isCodeLine && previousVisibleJSDocWasCode) {
                        totalHeight += 2;
                    }
                    totalHeight += TokenHoverRenderer.calculateSegmentsHeight(wrapW, docLine.segments);
                    totalHeight += 2;
                    previousVisibleJSDocWasCode = docLine.isCodeLine;
                    continue;
                }
                totalHeight += i > 0 && jsDocLines.get((int)(i - 1)).isCodeLine ? 2 : lineHeight / 2;
            }
        }
        if (!additionalInfo.isEmpty()) {
            totalHeight += 2;
            for (String infoLine : additionalInfo) {
                wrappedLines = TokenHoverRenderer.wrapText(infoLine, contentWidth);
                totalHeight += wrappedLines.size() * (lineHeight + 2);
            }
        }
        return Math.max(lineHeight, Math.max(0, totalHeight - 2)) - 2;
    }

    private static int calculateSegmentsHeight(int maxWidth, List<TokenHoverInfo.TextSegment> segments) {
        int lineHeight = ClientProxy.Font.height();
        int currentLineWidth = 0;
        int lineCount = 1;
        for (TokenHoverInfo.TextSegment segment : segments) {
            String[] words;
            String text = segment.text;
            for (String word : words = text.split("(?<=\\s)|(?=\\s)")) {
                int wordWidth = ClientProxy.Font.width(word);
                if (currentLineWidth + wordWidth > maxWidth && currentLineWidth > 0) {
                    ++lineCount;
                    currentLineWidth = word.trim().isEmpty() ? 0 : wordWidth;
                    continue;
                }
                currentLineWidth += wordWidth;
            }
        }
        if (lineCount <= 0) {
            return 0;
        }
        return lineCount * lineHeight + (lineCount - 1) * 2;
    }

    private static int calculateSegmentsLongestLine(int maxWidth, List<TokenHoverInfo.TextSegment> segments) {
        int currentLineWidth = 0;
        int longestLineWidth = 0;
        for (TokenHoverInfo.TextSegment segment : segments) {
            String[] words;
            String text = segment.text;
            for (String word : words = text.split("(?<=\\s)|(?=\\s)")) {
                int wordWidth = ClientProxy.Font.width(word);
                if (currentLineWidth + wordWidth > maxWidth && currentLineWidth > 0) {
                    longestLineWidth = Math.max(longestLineWidth, currentLineWidth);
                    currentLineWidth = word.trim().isEmpty() ? 0 : wordWidth;
                    continue;
                }
                currentLineWidth += wordWidth;
            }
        }
        longestLineWidth = Math.max(longestLineWidth, currentLineWidth);
        return longestLineWidth;
    }
}

