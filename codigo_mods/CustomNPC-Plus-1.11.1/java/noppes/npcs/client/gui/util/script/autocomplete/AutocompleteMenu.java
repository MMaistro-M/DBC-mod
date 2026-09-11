/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util.script.autocomplete;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.script.autocomplete.AutocompleteItem;
import noppes.npcs.client.gui.util.script.interpreter.token.TokenType;
import noppes.npcs.client.gui.util.script.interpreter.type.TypeInfo;
import org.lwjgl.opengl.GL11;

public class AutocompleteMenu
extends Gui {
    private static final int MAX_VISIBLE_ITEMS = 10;
    private static final int ITEM_HEIGHT = 16;
    private static final int PADDING = 4;
    private static final int ICON_WIDTH = 16;
    private static final int MIN_WIDTH = 200;
    private static final int MAX_WIDTH = 400;
    private static final int HINT_HEIGHT = 18;
    private static final int BG_COLOR = -13816528;
    private static final int BORDER_COLOR = -12829636;
    private static final int SELECTED_BG = -16169103;
    private static final int HOVER_BG = -13158595;
    private static final int TEXT_COLOR = -2039584;
    private static final int DIM_TEXT_COLOR = -8355712;
    private static final int HIGHLIGHT_COLOR = -10138;
    private static final int HINT_BG_COLOR = -14342874;
    private static final int SCROLLBAR_BG = -12829636;
    private static final int SCROLLBAR_FG = -10461088;
    private boolean visible = false;
    private List<AutocompleteItem> items = new ArrayList<AutocompleteItem>();
    private int selectedIndex = 0;
    private int scrollOffset = 0;
    private int hoveredIndex = -1;
    private boolean isDraggingScrollbar = false;
    private int dragStartY = 0;
    private int dragStartScroll = 0;
    private boolean isDraggingPanel;
    private int panelDragOffsetX;
    private int panelDragOffsetY;
    private boolean panelPositionOverridden;
    private int overriddenX;
    private int overriddenY;
    private boolean hasPendingPanelDrag;
    private int pendingDragStartX;
    private int pendingDragStartY;
    private boolean pendingItemConfirm;
    private boolean isResizingPanel;
    private int resizeInitMouseX;
    private int resizeInitMouseY;
    private int resizeInitW;
    private int resizeInitH;
    private boolean panelSizeOverridden;
    private int overriddenW;
    private int overriddenH;
    private static final int MIN_RESIZE_W = 140;
    private static final int MIN_RESIZE_H = 60;
    private static final int DRAG_THRESHOLD = 4;
    private int x;
    private int y;
    public GuiNPCInterface ownerGui;
    private int width;
    private int height;
    private int menuWidth;
    private int visibleItemsCount;
    private final FontRenderer font;
    private AutocompleteCallback callback;

    public AutocompleteMenu() {
        this.font = Minecraft.func_71410_x().field_71466_p;
        this.visibleItemsCount = 10;
    }

    private int getVisibleItemCapacity() {
        int cap;
        if (this.items == null || this.items.isEmpty()) {
            return 0;
        }
        int n = cap = this.visibleItemsCount > 0 ? this.visibleItemsCount : 10;
        if (!this.panelSizeOverridden) {
            cap = Math.min(cap, 10);
        }
        return Math.max(1, Math.min(cap, this.items.size()));
    }

    private int getMaxScrollOffset() {
        int cap = this.getVisibleItemCapacity();
        if (cap <= 0) {
            return 0;
        }
        return Math.max(0, this.items.size() - cap);
    }

    public void setCallback(AutocompleteCallback callback) {
        this.callback = callback;
    }

    public void show(int x, int y, List<AutocompleteItem> items, int viewportWidth, int viewportHeight) {
        this.items = items != null ? new ArrayList<AutocompleteItem>(items) : new ArrayList();
        this.selectedIndex = 0;
        this.scrollOffset = 0;
        this.hoveredIndex = -1;
        this.calculateDimensions(x, y, viewportWidth, viewportHeight);
        this.visible = !this.items.isEmpty();
    }

    public void updateItems(List<AutocompleteItem> newItems) {
        int maxScroll;
        List<AutocompleteItem> list = this.items = newItems != null ? new ArrayList<AutocompleteItem>(newItems) : new ArrayList();
        if (this.selectedIndex >= this.items.size()) {
            this.selectedIndex = Math.max(0, this.items.size() - 1);
        }
        if (this.scrollOffset > (maxScroll = this.getMaxScrollOffset())) {
            this.scrollOffset = maxScroll;
        }
        this.visible = !this.items.isEmpty();
    }

    public void hide() {
        this.visible = false;
        this.isDraggingPanel = false;
        this.isResizingPanel = false;
        this.panelPositionOverridden = false;
        this.panelSizeOverridden = false;
        this.hasPendingPanelDrag = false;
        this.pendingItemConfirm = false;
        if (this.callback != null) {
            this.callback.onDismiss();
        }
    }

    public boolean isVisible() {
        return this.visible;
    }

    public boolean hasItems() {
        return !this.items.isEmpty();
    }

    private void calculateDimensions(int cursorX, int cursorY, int viewportWidth, int viewportHeight) {
        int maxItemWidth = 200;
        for (AutocompleteItem item : this.items) {
            int itemWidth = 20 + this.font.func_78256_a(item.getName()) + 4;
            if (item.getTypeLabel() != null) {
                itemWidth += this.font.func_78256_a(item.getTypeLabel()) + 8;
            }
            maxItemWidth = Math.max(maxItemWidth, itemWidth);
        }
        this.menuWidth = Math.min(maxItemWidth + 20, 400);
        int visibleItems = Math.min(this.items.size(), 10);
        int menuHeight = visibleItems * 16 + 18 + 8;
        int lineHeight = 20;
        int spaceBelow = viewportHeight - cursorY - 10;
        int spaceAbove = cursorY - lineHeight - 10;
        boolean useHorizontalPosition = false;
        if (spaceBelow < menuHeight && spaceAbove < menuHeight) {
            int spaceRight = viewportWidth - (cursorX + 50) - 10;
            if (spaceRight >= this.menuWidth && viewportHeight > menuHeight + 20) {
                useHorizontalPosition = true;
            } else {
                int availableVerticalSpace = Math.max(spaceBelow, spaceAbove);
                if (availableVerticalSpace < menuHeight) {
                    int maxItemsForSpace = (availableVerticalSpace - 18 - 8) / 16;
                    visibleItems = maxItemsForSpace = Math.max(3, Math.min(maxItemsForSpace, this.items.size()));
                    menuHeight = visibleItems * 16 + 18 + 8;
                }
            }
        }
        this.visibleItemsCount = visibleItems;
        if (useHorizontalPosition) {
            this.x = cursorX + 50;
            this.y = cursorY - lineHeight;
            if (this.y + menuHeight > viewportHeight - 10) {
                this.y = viewportHeight - menuHeight - 10;
            }
        } else {
            this.x = cursorX;
            this.y = spaceBelow >= menuHeight ? cursorY : (spaceAbove >= menuHeight ? cursorY - menuHeight - lineHeight : (spaceBelow >= spaceAbove ? cursorY : cursorY - menuHeight - lineHeight));
            if (this.x + this.menuWidth > viewportWidth - 10) {
                this.x = viewportWidth - this.menuWidth - 10;
            }
        }
        this.x = Math.max(5, this.x);
        this.y = Math.max(5, this.y);
        this.width = this.menuWidth;
        this.height = menuHeight;
        if (this.panelPositionOverridden) {
            this.x = this.overriddenX;
            this.y = this.overriddenY;
        }
        if (this.panelSizeOverridden) {
            this.menuWidth = this.overriddenW;
            this.width = this.overriddenW;
            this.height = this.overriddenH;
            this.visibleItemsCount = Math.max(1, (this.overriddenH - 18 - 8) / 16);
        }
    }

    public void selectPrevious() {
        if (this.items.isEmpty()) {
            return;
        }
        --this.selectedIndex;
        if (this.selectedIndex < 0) {
            this.selectedIndex = this.items.size() - 1;
            this.scrollOffset = this.getMaxScrollOffset();
        } else if (this.selectedIndex < this.scrollOffset) {
            this.scrollOffset = this.selectedIndex;
        }
    }

    public void selectNext() {
        if (this.items.isEmpty()) {
            return;
        }
        ++this.selectedIndex;
        if (this.selectedIndex >= this.items.size()) {
            this.selectedIndex = 0;
            this.scrollOffset = 0;
        } else {
            int cap = this.getVisibleItemCapacity();
            if (cap > 0 && this.selectedIndex >= this.scrollOffset + cap) {
                this.scrollOffset = this.selectedIndex - cap + 1;
            }
        }
    }

    public void confirmSelection() {
        if (this.items.isEmpty() || this.selectedIndex < 0 || this.selectedIndex >= this.items.size()) {
            this.hide();
            return;
        }
        AutocompleteItem selected = this.items.get(this.selectedIndex);
        if (this.callback != null) {
            this.callback.onItemSelected(selected);
        }
        this.hide();
    }

    public AutocompleteItem getSelectedItem() {
        if (this.items.isEmpty() || this.selectedIndex < 0 || this.selectedIndex >= this.items.size()) {
            return null;
        }
        return this.items.get(this.selectedIndex);
    }

    public void draw(int mouseX, int mouseY) {
        int itemIndex;
        if (!this.visible || this.items.isEmpty()) {
            return;
        }
        this.updateHoverState(mouseX, mouseY);
        GL11.glEnable((int)3089);
        this.setScissor(this.x - 2, this.y - 2, this.width + 4, this.height + 4);
        AutocompleteMenu.func_73734_a((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + this.height), (int)-13816528);
        AutocompleteMenu.func_73734_a((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + 1), (int)-12829636);
        AutocompleteMenu.func_73734_a((int)this.x, (int)(this.y + this.height - 1), (int)(this.x + this.width), (int)(this.y + this.height), (int)-12829636);
        AutocompleteMenu.func_73734_a((int)this.x, (int)this.y, (int)(this.x + 1), (int)(this.y + this.height), (int)-12829636);
        AutocompleteMenu.func_73734_a((int)(this.x + this.width - 1), (int)this.y, (int)(this.x + this.width), (int)(this.y + this.height), (int)-12829636);
        int itemAreaH = this.height - 18 - 8;
        this.setScissor(this.x + 1, this.y + 4, this.width - 2, itemAreaH);
        int itemY = this.y + 4;
        int visibleCount = this.getVisibleItemCapacity();
        int renderCount = Math.min(visibleCount + 1, this.items.size() - this.scrollOffset);
        for (int i = 0; i < renderCount && (itemIndex = this.scrollOffset + i) < this.items.size(); ++i) {
            AutocompleteItem item = this.items.get(itemIndex);
            boolean isSelected = itemIndex == this.selectedIndex;
            boolean isHovered = itemIndex == this.hoveredIndex;
            this.drawItem(item, this.x + 4, itemY, this.width - 8 - 8, isSelected, isHovered);
            itemY += 16;
        }
        this.setScissor(this.x - 2, this.y - 2, this.width + 4, this.height + 4);
        if (this.items.size() > visibleCount) {
            this.drawScrollbar(mouseX, mouseY);
        }
        this.drawHintBar();
        GL11.glDisable((int)3089);
        this.drawResizeHandle(mouseX, mouseY);
    }

    private void drawItem(AutocompleteItem item, int itemX, int itemY, int itemWidth, boolean selected, boolean hovered) {
        if (selected) {
            AutocompleteMenu.func_73734_a((int)(itemX - 2), (int)itemY, (int)(itemX + itemWidth + 2), (int)(itemY + 16), (int)-16169103);
        } else if (hovered) {
            AutocompleteMenu.func_73734_a((int)(itemX - 2), (int)itemY, (int)(itemX + itemWidth + 2), (int)(itemY + 16), (int)-13158595);
        }
        int textX = itemX;
        int textY = itemY + (16 - this.font.field_78288_b) / 2;
        boolean isStatic = item.isStatic();
        boolean isFinal = item.isFinal();
        if (isStatic || isFinal) {
            GL11.glPushMatrix();
            float scale = 0.5f;
            GL11.glScalef((float)scale, (float)scale, (float)scale);
            int col = TokenType.KEYWORD.getHexColor();
            if (isStatic) {
                this.font.func_78276_b("s", (int)((float)textX / scale), (int)((float)textY / scale), col);
            }
            if (isFinal) {
                this.font.func_78276_b("f", (int)((float)textX / scale), (int)((float)textY / scale) + 10, col);
            }
            GL11.glPopMatrix();
        }
        String icon = item.getIconId();
        int iconColor = item.getIconColor();
        this.font.func_78276_b(icon, textX + (16 - this.font.func_78256_a(icon)) / 2, textY, iconColor);
        int availableWidth = itemWidth - ((textX += 16) - itemX);
        if (item.getTypeLabel() != null && !item.getTypeLabel().isEmpty()) {
            int typeLabelWidth = this.font.func_78256_a(item.getTypeLabel());
            availableWidth -= typeLabelWidth + 8;
        }
        int textColor = this.getColor(item);
        if (item.getKind() == AutocompleteItem.Kind.METHOD) {
            this.drawMethodNameTruncated(item.getName(), item.getMatchIndices(), textX, textY, textColor, availableWidth);
        } else {
            this.drawHighlightedTextTruncated(item.getName(), item.getMatchIndices(), textX, textY, textColor, availableWidth);
        }
        if (item.getTypeLabel() != null && !item.getTypeLabel().isEmpty()) {
            String typeLabel = item.getTypeLabel();
            int typeLabelWidth = this.font.func_78256_a(typeLabel);
            int typeLabelX = itemX + itemWidth - typeLabelWidth - 4;
            TypeInfo type = item.getTypeInfo();
            int col = type != null ? type.getTokenType().getHexColor() : -8355712;
            this.drawTypeLabel(item, typeLabel, typeLabelX, textY, col, type);
        }
    }

    public int getColor(AutocompleteItem item) {
        int col = item.getColor();
        if (col != -1) {
            return col;
        }
        if (item.isInheritedObjectMethod() || item.isDeprecated()) {
            return -8355712;
        }
        switch (item.getKind()) {
            case METHOD: {
                return TokenType.METHOD_CALL.getHexColor();
            }
            case FIELD: {
                return TokenType.GLOBAL_FIELD.getHexColor();
            }
            case ENUM_CONSTANT: {
                return TokenType.ENUM_CONSTANT.getHexColor();
            }
            case CLASS: {
                return TokenType.getColor(item.getTypeInfo());
            }
            case VARIABLE: {
                return TokenType.LOCAL_FIELD.getHexColor();
            }
            case PARAMETER: {
                return TokenType.PARAMETER.getHexColor();
            }
            case KEYWORD: {
                return TokenType.KEYWORD.getHexColor();
            }
        }
        return -2039584;
    }

    private void drawMethodName(String text, int[] matchIndices, int x, int y, int baseColor) {
        int parenIndex = text.indexOf(40);
        if (parenIndex == -1) {
            this.drawHighlightedText(text, matchIndices, x, y, baseColor);
            return;
        }
        String methodName = text.substring(0, parenIndex);
        this.drawHighlightedText(methodName, matchIndices, x, y, baseColor);
        String params = text.substring(parenIndex);
        int paramX = x + this.font.func_78256_a(methodName);
        this.font.func_78276_b(params, paramX, y, -8355712);
    }

    private void drawMethodNameTruncated(String text, int[] matchIndices, int x, int y, int baseColor, int maxWidth) {
        int paramsWidth;
        int parenIndex = text.indexOf(40);
        if (parenIndex == -1) {
            this.drawHighlightedTextTruncated(text, matchIndices, x, y, baseColor, maxWidth);
            return;
        }
        String methodName = text.substring(0, parenIndex);
        String params = text.substring(parenIndex);
        int methodNameWidth = this.font.func_78256_a(methodName);
        int totalWidth = methodNameWidth + (paramsWidth = this.font.func_78256_a(params));
        if (totalWidth <= maxWidth) {
            this.drawHighlightedText(methodName, matchIndices, x, y, baseColor);
            int paramX = x + methodNameWidth;
            this.font.func_78276_b(params, paramX, y, -8355712);
        } else {
            String ellipsis = "...";
            int ellipsisWidth = this.font.func_78256_a(ellipsis);
            if (methodNameWidth + ellipsisWidth < maxWidth) {
                this.drawHighlightedText(methodName, matchIndices, x, y, baseColor);
                int paramX = x + methodNameWidth;
                int availableForParams = maxWidth - methodNameWidth - ellipsisWidth;
                String truncatedParams = this.truncateString(params, availableForParams);
                this.font.func_78276_b(truncatedParams + ellipsis, paramX, y, -8355712);
            } else {
                int availableForMethod = maxWidth - ellipsisWidth;
                String truncatedMethod = this.truncateString(methodName, availableForMethod);
                this.drawHighlightedText(truncatedMethod, matchIndices, x, y, baseColor);
                this.font.func_78276_b(ellipsis, x + this.font.func_78256_a(truncatedMethod), y, baseColor);
            }
        }
    }

    private void drawHighlightedText(String text, int[] matchIndices, int x, int y, int baseColor) {
        if (matchIndices == null || matchIndices.length == 0) {
            this.font.func_78276_b(text, x, y, baseColor);
            return;
        }
        HashSet<Integer> highlightSet = new HashSet<Integer>();
        for (int idx : matchIndices) {
            highlightSet.add(idx);
        }
        int currentX = x;
        for (int i = 0; i < text.length(); ++i) {
            String ch = String.valueOf(text.charAt(i));
            int color = highlightSet.contains(i) ? -10138 : baseColor;
            this.font.func_78276_b(ch, currentX, y, color);
            currentX += this.font.func_78256_a(ch);
        }
    }

    private void drawHighlightedTextTruncated(String text, int[] matchIndices, int x, int y, int baseColor, int maxWidth) {
        int textWidth = this.font.func_78256_a(text);
        if (textWidth <= maxWidth) {
            this.drawHighlightedText(text, matchIndices, x, y, baseColor);
        } else {
            String ellipsis = "...";
            int ellipsisWidth = this.font.func_78256_a(ellipsis);
            String truncated = this.truncateString(text, maxWidth - ellipsisWidth);
            int[] adjustedIndices = null;
            if (matchIndices != null) {
                ArrayList<Integer> validIndices = new ArrayList<Integer>();
                for (int idx : matchIndices) {
                    if (idx >= truncated.length()) continue;
                    validIndices.add(idx);
                }
                adjustedIndices = new int[validIndices.size()];
                for (int i = 0; i < validIndices.size(); ++i) {
                    adjustedIndices[i] = (Integer)validIndices.get(i);
                }
            }
            this.drawHighlightedText(truncated, adjustedIndices, x, y, baseColor);
            this.font.func_78276_b(ellipsis, x + this.font.func_78256_a(truncated), y, baseColor);
        }
    }

    private String truncateString(String text, int maxWidth) {
        if (text.isEmpty()) {
            return text;
        }
        int width = 0;
        for (int i = 0; i < text.length(); ++i) {
            if ((width += this.font.func_78256_a(String.valueOf(text.charAt(i)))) <= maxWidth) continue;
            return text.substring(0, Math.max(0, i));
        }
        return text;
    }

    private void drawTypeLabel(AutocompleteItem item, String typeLabel, int x, int y, int typeColor, TypeInfo typeInfo) {
        if (typeInfo == null || item.getKind() == AutocompleteItem.Kind.CLASS) {
            if (typeInfo != null && !typeInfo.isClass()) {
                typeColor = TokenType.getPackageColor();
            }
            this.drawSimpleTypeWithArraySuffix(typeLabel, x, y, typeColor);
            return;
        }
        this.drawTypeLabel(x, y, typeInfo, 0);
    }

    private int drawTypeLabel(int x, int y, TypeInfo typeInfo, int depth) {
        if (depth > 25) {
            String name = typeInfo.getDisplayName();
            this.font.func_78276_b(name, x, y, TokenType.getColor(typeInfo));
            return x + this.font.func_78256_a(name);
        }
        int defaultColor = TokenType.DEFAULT.getHexColor();
        if (typeInfo.isParameterized()) {
            TypeInfo raw = typeInfo.getRawType();
            x = this.drawSimpleTypeWithArraySuffix(raw.getDisplayName(), x, y, TokenType.getColor(raw));
            this.font.func_78276_b("<", x, y, defaultColor);
            x += this.font.func_78256_a("<");
            List<TypeInfo> args = typeInfo.getAppliedTypeArgs();
            for (int i = 0; i < args.size(); ++i) {
                if (i > 0) {
                    this.font.func_78276_b(", ", x, y, defaultColor);
                    x += this.font.func_78256_a(", ");
                }
                x = this.drawTypeLabel(x, y, args.get(i), depth + 1);
            }
            this.font.func_78276_b(">", x, y, defaultColor);
            return x + this.font.func_78256_a(">");
        }
        return this.drawSimpleTypeWithArraySuffix(typeInfo.getDisplayName(), x, y, TokenType.getColor(typeInfo));
    }

    private int drawSimpleTypeWithArraySuffix(String typeLabel, int x, int y, int typeColor) {
        int suffixStart;
        if (!typeLabel.endsWith("[]")) {
            this.font.func_78276_b(typeLabel, x, y, typeColor);
            return x + this.font.func_78256_a(typeLabel);
        }
        for (suffixStart = typeLabel.length() - 2; suffixStart >= 2 && typeLabel.charAt(suffixStart - 2) == '[' && typeLabel.charAt(suffixStart - 1) == ']'; suffixStart -= 2) {
        }
        String core = typeLabel.substring(0, suffixStart);
        String suffix = typeLabel.substring(suffixStart);
        this.font.func_78276_b(core, x, y, typeColor);
        int coreWidth = this.font.func_78256_a(core);
        this.font.func_78276_b(suffix, x + coreWidth, y, TokenType.DEFAULT.getHexColor());
        return x + coreWidth + this.font.func_78256_a(suffix);
    }

    private void drawScrollbar(int mouseX, int mouseY) {
        int scrollbarX = this.x + this.width - 8;
        int scrollbarY = this.y + 4;
        int visibleCount = this.getVisibleItemCapacity();
        if (visibleCount <= 0) {
            return;
        }
        int scrollbarHeight = visibleCount * 16 - 8;
        AutocompleteMenu.func_73734_a((int)scrollbarX, (int)scrollbarY, (int)(scrollbarX + 6), (int)(scrollbarY + scrollbarHeight), (int)-12829636);
        float thumbRatio = (float)visibleCount / (float)this.items.size();
        int thumbHeight = Math.max(20, (int)((float)scrollbarHeight * thumbRatio));
        float thumbPosRatio = Math.min(1.0f, (float)this.scrollOffset / (float)Math.max(1, this.items.size() - visibleCount));
        int thumbY = scrollbarY + (int)((float)(scrollbarHeight - thumbHeight) * thumbPosRatio);
        thumbY = Math.max(scrollbarY, Math.min(scrollbarY + scrollbarHeight - thumbHeight, thumbY));
        boolean isAboveScrollbar = mouseX >= scrollbarX && mouseX <= scrollbarX + 6 && mouseY >= thumbY && mouseY < thumbY + thumbHeight;
        int col = this.isDraggingScrollbar || isAboveScrollbar ? -8355712 : -10461088;
        AutocompleteMenu.func_73734_a((int)(scrollbarX + 1), (int)thumbY, (int)(scrollbarX + 5), (int)(thumbY + thumbHeight), (int)col);
    }

    private void drawHintBar() {
        int hintY = this.y + this.height - 18;
        AutocompleteMenu.func_73734_a((int)(this.x + 1), (int)hintY, (int)(this.x + this.width - 1), (int)(this.y + this.height - 1), (int)-14342874);
        int hintTextY = hintY + (18 - this.font.field_78288_b) / 2;
        int hintX = this.x + 4;
        this.drawKeyHint("Tab", hintX, hintTextY);
        this.font.func_78276_b("or", hintX += this.font.func_78256_a("Tab") + 8, hintTextY, -8355712);
        this.drawKeyHint("Enter", hintX += this.font.func_78256_a("or") + 4, hintTextY);
        this.font.func_78276_b("to insert", hintX += this.font.func_78256_a("Enter") + 8, hintTextY, -8355712);
    }

    private void drawKeyHint(String key, int x, int y) {
        int keyWidth = this.font.func_78256_a(key);
        int boxPadding = 2;
        AutocompleteMenu.func_73734_a((int)(x - boxPadding), (int)(y - boxPadding), (int)(x + keyWidth + boxPadding), (int)(y + this.font.field_78288_b + boxPadding), (int)-12566464);
        this.font.func_78276_b(key, x, y, -2039584);
    }

    private void updateHoverState(int mouseX, int mouseY) {
        int idx;
        this.hoveredIndex = -1;
        if (!this.isMouseInBounds(mouseX, mouseY)) {
            return;
        }
        int itemY = this.y + 4;
        int visibleItems = this.getVisibleItemCapacity();
        for (int i = 0; i <= visibleItems && (idx = this.scrollOffset + i) < this.items.size(); ++i) {
            if (mouseY >= itemY && mouseY < itemY + 16) {
                this.hoveredIndex = idx;
                break;
            }
            itemY += 16;
        }
    }

    public boolean mouseClicked(int mouseX, int mouseY, int button) {
        int scrollbarX;
        if (!this.visible) {
            return false;
        }
        if (!this.isMouseInBounds(mouseX, mouseY)) {
            this.hide();
            return false;
        }
        if (button == 0 && this.isMouseOverResizeHandle(mouseX, mouseY)) {
            this.isResizingPanel = true;
            this.resizeInitMouseX = mouseX;
            this.resizeInitMouseY = mouseY;
            this.resizeInitW = this.width;
            this.resizeInitH = this.height;
            return true;
        }
        if (button == 0 && this.items.size() > this.getVisibleItemCapacity() && mouseX >= (scrollbarX = this.x + this.width - 8) && mouseX <= scrollbarX + 6) {
            this.isDraggingScrollbar = true;
            this.dragStartY = mouseY;
            this.dragStartScroll = this.scrollOffset;
            return true;
        }
        if (button == 0) {
            this.hasPendingPanelDrag = true;
            this.pendingDragStartX = mouseX;
            this.pendingDragStartY = mouseY;
            this.panelDragOffsetX = mouseX - this.x;
            this.panelDragOffsetY = mouseY - this.y;
            if (this.hoveredIndex >= 0 && this.hoveredIndex < this.items.size()) {
                this.selectedIndex = this.hoveredIndex;
                this.pendingItemConfirm = true;
            }
            return true;
        }
        return true;
    }

    public boolean mouseReleased(int mouseX, int mouseY, int button) {
        if (button == 0) {
            if (this.isDraggingScrollbar) {
                this.isDraggingScrollbar = false;
                return true;
            }
            if (this.isDraggingPanel) {
                this.isDraggingPanel = false;
                return true;
            }
            if (this.isResizingPanel) {
                this.isResizingPanel = false;
                return true;
            }
            this.hasPendingPanelDrag = false;
            if (this.pendingItemConfirm) {
                this.pendingItemConfirm = false;
                this.confirmSelection();
                return true;
            }
        }
        return false;
    }

    public boolean mouseDragged(int mouseX, int mouseY) {
        if (!this.visible) {
            return false;
        }
        if (this.isDraggingPanel) {
            this.updatePanelDrag(mouseX, mouseY);
            return true;
        }
        if (this.isResizingPanel) {
            this.updatePanelResize(mouseX, mouseY);
            return true;
        }
        if (this.hasPendingPanelDrag) {
            int dx = Math.abs(mouseX - this.pendingDragStartX);
            int dy = Math.abs(mouseY - this.pendingDragStartY);
            if (dx > 4 || dy > 4) {
                this.isDraggingPanel = true;
                this.hasPendingPanelDrag = false;
                this.pendingItemConfirm = false;
                this.updatePanelDrag(mouseX, mouseY);
                return true;
            }
            return true;
        }
        if (!this.isDraggingScrollbar) {
            return false;
        }
        int visibleCount = this.getVisibleItemCapacity();
        if (visibleCount <= 0) {
            return false;
        }
        int scrollbarHeight = visibleCount * 16 - 8;
        int thumbHeight = Math.max(20, (int)((float)scrollbarHeight * (float)visibleCount / (float)Math.max(1, this.items.size())));
        int scrollTrackHeight = Math.max(1, scrollbarHeight - thumbHeight);
        int maxScroll = this.items.size() - visibleCount;
        int deltaY = mouseY - this.dragStartY;
        int scrollDelta = deltaY * maxScroll / scrollTrackHeight;
        this.scrollOffset = Math.max(0, Math.min(maxScroll, this.dragStartScroll + scrollDelta));
        return true;
    }

    public boolean mouseScrolled(int mouseX, int mouseY, int delta) {
        if (!this.visible || !this.isMouseInBounds(mouseX, mouseY)) {
            return false;
        }
        int visibleCount = this.getVisibleItemCapacity();
        if (this.items.size() > visibleCount) {
            this.scrollOffset = delta > 0 ? Math.max(0, this.scrollOffset - 1) : Math.min(this.items.size() - visibleCount, this.scrollOffset + 1);
            return true;
        }
        return false;
    }

    private boolean isMouseInBounds(int mouseX, int mouseY) {
        return mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height;
    }

    private void setScissor(int x, int y, int width, int height) {
        Minecraft mc = Minecraft.func_71410_x();
        ScaledResolution sr = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
        int scaleFactor = sr.func_78325_e();
        if (this.ownerGui != null) {
            x -= (int)this.ownerGui.getPanX();
            y -= (int)this.ownerGui.getPanY();
        }
        int scissorX = x * scaleFactor;
        int scissorY = mc.field_71440_d - (y + height) * scaleFactor;
        int scissorW = width * scaleFactor;
        int scissorH = height * scaleFactor;
        GL11.glScissor((int)scissorX, (int)scissorY, (int)scissorW, (int)scissorH);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public List<AutocompleteItem> getItems() {
        return this.items;
    }

    public boolean isMouseOverResizeHandle(int mx, int my) {
        if (!this.visible) {
            return false;
        }
        return mx >= this.x + this.width - 8 && mx <= this.x + this.width && my >= this.y + this.height - 8 && my <= this.y + this.height;
    }

    public void updatePanelDrag(int mouseX, int mouseY) {
        if (!this.isDraggingPanel) {
            return;
        }
        this.x = mouseX - this.panelDragOffsetX;
        this.y = mouseY - this.panelDragOffsetY;
        this.overriddenX = this.x;
        this.overriddenY = this.y;
        this.panelPositionOverridden = true;
    }

    public void releasePanelDrag() {
        this.isDraggingPanel = false;
        this.hasPendingPanelDrag = false;
    }

    public void updatePanelResize(int mouseX, int mouseY) {
        if (!this.isResizingPanel) {
            return;
        }
        this.overriddenW = Math.max(140, this.resizeInitW + (mouseX - this.resizeInitMouseX));
        this.overriddenH = Math.max(60, this.resizeInitH + (mouseY - this.resizeInitMouseY));
        this.panelSizeOverridden = true;
        this.menuWidth = this.overriddenW;
        this.width = this.overriddenW;
        this.height = this.overriddenH;
        this.visibleItemsCount = Math.max(1, (this.overriddenH - 18 - 8) / 16);
    }

    public void releasePanelResize() {
        this.isResizingPanel = false;
    }

    public void updateScrollbarDragDraw(int mouseY) {
        if (!this.isDraggingScrollbar || this.items.isEmpty()) {
            return;
        }
        int visibleCount = this.getVisibleItemCapacity();
        if (visibleCount <= 0) {
            return;
        }
        int scrollbarHeight = visibleCount * 16 - 8;
        int thumbHeight = Math.max(20, (int)((float)scrollbarHeight * (float)visibleCount / (float)this.items.size()));
        int scrollTrackHeight = Math.max(1, scrollbarHeight - thumbHeight);
        int maxScroll = this.items.size() - visibleCount;
        int deltaY = mouseY - this.dragStartY;
        int scrollDelta = deltaY * maxScroll / scrollTrackHeight;
        this.scrollOffset = Math.max(0, Math.min(maxScroll, this.dragStartScroll + scrollDelta));
    }

    public void releaseScrollbarDrag() {
        this.isDraggingScrollbar = false;
    }

    public boolean isDraggingPanel() {
        return this.isDraggingPanel;
    }

    public boolean isDraggingScrollbarMenu() {
        return this.isDraggingScrollbar;
    }

    public boolean isResizingPanel() {
        return this.isResizingPanel;
    }

    private void drawResizeHandle(int mouseX, int mouseY) {
        boolean resizeActive = this.isResizingPanel || this.isMouseOverResizeHandle(mouseX, mouseY);
        int dotColor = resizeActive ? -3355444 : -2138535800;
        int rhX = this.x + this.width - 2;
        int rhY = this.y + this.height - 2;
        AutocompleteMenu.func_73734_a((int)(rhX - 1), (int)(rhY - 1), (int)rhX, (int)rhY, (int)dotColor);
        AutocompleteMenu.func_73734_a((int)(rhX - 3), (int)(rhY - 1), (int)(rhX - 2), (int)rhY, (int)dotColor);
        AutocompleteMenu.func_73734_a((int)(rhX - 1), (int)(rhY - 3), (int)rhX, (int)(rhY - 2), (int)dotColor);
        AutocompleteMenu.func_73734_a((int)(rhX - 5), (int)(rhY - 1), (int)(rhX - 4), (int)rhY, (int)dotColor);
        AutocompleteMenu.func_73734_a((int)(rhX - 3), (int)(rhY - 3), (int)(rhX - 2), (int)(rhY - 2), (int)dotColor);
        AutocompleteMenu.func_73734_a((int)(rhX - 1), (int)(rhY - 5), (int)rhX, (int)(rhY - 4), (int)dotColor);
    }

    public static interface AutocompleteCallback {
        public void onItemSelected(AutocompleteItem var1);

        public void onDismiss();
    }
}

