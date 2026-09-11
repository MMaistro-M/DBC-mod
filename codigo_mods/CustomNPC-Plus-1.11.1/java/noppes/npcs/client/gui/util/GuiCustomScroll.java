/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import kamkeel.npcs.util.TextSplitter;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiCustomScroll
extends GuiScreen {
    public static final ResourceLocation resource = new ResourceLocation("customnpcs", "textures/gui/misc.png");
    public List<String> list;
    public final HashMap<String, Integer> colors = new HashMap();
    public final HashSet<String> nonInteractive = new HashSet();
    public int id;
    public int guiLeft = 0;
    public int guiTop = 0;
    public int xSize;
    public int ySize;
    public int selected;
    protected HashSet<String> selectedList;
    protected int hover;
    protected int oldHover;
    private int hoverCount = 0;
    protected boolean hoverableText = false;
    public int listHeight;
    public int scrollY;
    public int maxScrollY;
    public int scrollHeight;
    private boolean isScrolling;
    public boolean multipleSelection = false;
    private ICustomScrollListener listener;
    private boolean isSorted = true;
    public boolean visible = true;
    private boolean selectable = true;
    private boolean hasSubGUI = false;
    private int lastClickedItem;
    private long lastClickedTime = 0L;

    public GuiCustomScroll(GuiScreen parent, int id) {
        this.field_146294_l = 176;
        this.field_146295_m = 166;
        this.xSize = 176;
        this.ySize = 159;
        this.selected = -1;
        this.hover = -1;
        this.selectedList = new HashSet();
        this.listHeight = 0;
        this.scrollY = 0;
        this.scrollHeight = 0;
        this.isScrolling = false;
        if (parent instanceof ICustomScrollListener) {
            this.listener = (ICustomScrollListener)parent;
        }
        this.list = new ArrayList<String>();
        this.id = id;
    }

    public GuiCustomScroll(GuiScreen parent, int id, boolean multipleSelection) {
        this(parent, id);
        this.multipleSelection = multipleSelection;
    }

    public GuiCustomScroll(GuiScreen parent, int id, int allowHover) {
        this(parent, id);
        this.hoverableText = true;
    }

    public void setSize(int x, int y) {
        this.ySize = y;
        this.xSize = x;
        this.listHeight = 14 * this.list.size();
        this.scrollHeight = this.listHeight > 0 ? (int)((double)(this.ySize - 8) / (double)this.listHeight * (double)(this.ySize - 8)) : Integer.MAX_VALUE;
        this.maxScrollY = this.listHeight - (this.ySize - 8) - 1;
    }

    public void drawScreen(int i, int j, float f, int mouseScrolled) {
        if (!this.visible) {
            return;
        }
        this.func_73733_a(this.guiLeft, this.guiTop, this.xSize + this.guiLeft, this.ySize + this.guiTop, -1072689136, -804253680);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(resource);
        if (this.scrollHeight < this.ySize - 8) {
            this.drawScrollBar();
        }
        GL11.glPushMatrix();
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)this.guiLeft, (float)this.guiTop, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (this.selectable && !this.hasSubGUI) {
            this.hover = this.getMouseOver(i, j);
        } else if (this.hasSubGUI) {
            this.hover = -1;
        }
        this.drawItems();
        if (this.oldHover != this.hover) {
            this.oldHover = this.hover;
            this.hoverCount = 0;
        } else if (this.hoverCount < 110) {
            ++this.hoverCount;
        }
        if (!this.hasSubGUI && this.hover != -1 && this.hoverableText && this.hoverCount > 100) {
            String displayString = StatCollector.func_74838_a((String)this.list.get(this.hover));
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            List<String> lines = TextSplitter.splitText(displayString, 30);
            super.drawHoveringText(lines, i - this.guiLeft, j - this.guiTop, this.field_146289_q);
            GL11.glDisable((int)2896);
        }
        GL11.glPopMatrix();
        if (this.scrollHeight < this.ySize - 8) {
            i -= this.guiLeft;
            j -= this.guiTop;
            if (Mouse.isButtonDown((int)0) && !GuiNPCInterface.resizingActive) {
                if (i >= this.xSize - 9 && i < this.xSize - 4 && j >= 4 && j < this.ySize) {
                    this.isScrolling = true;
                }
            } else {
                this.isScrolling = false;
            }
            if (this.isScrolling) {
                this.scrollY = (j - 8) * this.listHeight / (this.ySize - 8) - this.scrollHeight;
                if (this.scrollY < 0) {
                    this.scrollY = 0;
                }
                if (this.scrollY > this.maxScrollY) {
                    this.scrollY = this.maxScrollY;
                }
            }
            if (mouseScrolled != 0) {
                this.scrollY += mouseScrolled > 0 ? -14 : 14;
                if (this.scrollY > this.maxScrollY) {
                    this.scrollY = this.maxScrollY;
                }
                if (this.scrollY < 0) {
                    this.scrollY = 0;
                }
            }
        }
    }

    public void drawHover(int i, int j) {
        if (!this.visible) {
            return;
        }
        if (!this.hoverableText) {
            return;
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)this.guiLeft, (float)this.guiTop, (float)0.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (this.oldHover != this.hover) {
            this.oldHover = this.hover;
            this.hoverCount = 0;
        } else if (this.hoverCount < 110) {
            ++this.hoverCount;
        }
        if (!this.hasSubGUI && this.hover != -1 && this.hoverableText && this.hoverCount > 100) {
            String displayString = StatCollector.func_74838_a((String)this.list.get(this.hover));
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            List<String> lines = TextSplitter.splitText(displayString, 30);
            super.drawHoveringText(lines, i - this.guiLeft, j - this.guiTop, this.field_146289_q);
            GL11.glDisable((int)2896);
        }
        GL11.glPopMatrix();
    }

    public boolean mouseInOption(int i, int j, int k) {
        int l = 4;
        int i1 = 14 * k + 4 - this.scrollY;
        return i >= l - 1 && i < l + this.xSize - 11 && j >= i1 - 1 && j < i1 + 8;
    }

    protected void drawItems() {
        for (int i = 0; i < this.list.size(); ++i) {
            int j = 4;
            int k = 14 * i + 4 - this.scrollY;
            if (k < 4 || k + 12 >= this.ySize) continue;
            int xOffset = this.scrollHeight < this.ySize - 8 ? 0 : 10;
            String rawEntry = this.list.get(i);
            String displayString = StatCollector.func_74838_a((String)rawEntry);
            String text = "";
            float maxWidth = (float)(this.xSize + xOffset - 8) * 0.8f;
            if ((float)this.field_146289_q.func_78256_a(displayString) > maxWidth) {
                for (int h = 0; h < displayString.length(); ++h) {
                    char c = displayString.charAt(h);
                    if ((float)this.field_146289_q.func_78256_a(text = text + c) > maxWidth) break;
                }
                if (displayString.length() > text.length()) {
                    text = text + "...";
                }
            } else {
                text = displayString;
            }
            Integer customColor = this.colors.get(rawEntry);
            if (this.nonInteractive.contains(rawEntry)) {
                if (text.isEmpty()) continue;
                this.field_146289_q.func_78276_b(text, j, k, customColor != null ? customColor : 0xFFFFFF);
                continue;
            }
            if (this.multipleSelection && this.selectedList.contains(text) || !this.multipleSelection && this.selected == i) {
                this.func_73728_b(j - 2, k - 4, k + 10, -1);
                this.func_73728_b(j + this.xSize - 18 + xOffset, k - 4, k + 10, -1);
                this.func_73730_a(j - 2, j + this.xSize - 18 + xOffset, k - 3, -1);
                this.func_73730_a(j - 2, j + this.xSize - 18 + xOffset, k + 10, -1);
                int selColor = customColor != null ? customColor : 0xFFFFFF;
                this.field_146289_q.func_78276_b(text, j, k, selColor);
                continue;
            }
            if (i == this.hover) {
                int hoverColor = customColor != null ? customColor : 65280;
                this.field_146289_q.func_78276_b(text, j, k, hoverColor);
                continue;
            }
            int defaultColor = customColor != null ? customColor : 0xFFFFFF;
            this.field_146289_q.func_78276_b(text, j, k, defaultColor);
        }
    }

    public String getSelected() {
        if (this.selected == -1 || this.selected >= this.list.size()) {
            return null;
        }
        return this.list.get(this.selected);
    }

    private int getMouseOver(int i, int j) {
        if ((i -= this.guiLeft) >= 4 && i < this.xSize - 4 && (j -= this.guiTop) >= 4 && j < this.ySize) {
            for (int j1 = 0; j1 < this.list.size(); ++j1) {
                if (!this.mouseInOption(i, j, j1) || this.nonInteractive.contains(this.list.get(j1))) continue;
                return j1;
            }
        }
        return -1;
    }

    public void func_73864_a(int i, int j, int k) {
        if (GuiNPCInterface.resizingActive) {
            return;
        }
        if (k != 0 || this.hover < 0) {
            return;
        }
        if (this.multipleSelection) {
            if (this.selectedList.contains(this.list.get(this.hover))) {
                this.selectedList.remove(this.list.get(this.hover));
            } else {
                this.selectedList.add(this.list.get(this.hover));
            }
        } else {
            if (this.hover >= 0) {
                this.selected = this.hover;
            }
            this.hover = -1;
        }
        if (this.listener != null) {
            long time = System.currentTimeMillis();
            this.listener.customScrollClicked(i, j, k, this);
            if (this.selected >= 0 && this.selected == this.lastClickedItem && time - this.lastClickedTime < 500L) {
                this.listener.customScrollDoubleClicked(this.list.get(this.selected), this);
            }
            this.lastClickedTime = time;
            this.lastClickedItem = this.selected;
        }
    }

    private void drawScrollBar() {
        int j;
        int i = this.guiLeft + this.xSize - 9;
        int k = j = this.guiTop + (int)((double)this.scrollY / (double)this.listHeight * (double)(this.ySize - 8)) + 4;
        this.func_73729_b(i, k, this.xSize, 9, 5, 1);
        ++k;
        while (k < j + this.scrollHeight - 1) {
            this.func_73729_b(i, k, this.xSize, 10, 5, 1);
            ++k;
        }
        this.func_73729_b(i, k, this.xSize, 11, 5, 1);
    }

    public boolean hasSelected() {
        return this.selected >= 0;
    }

    public void setList(List<String> list) {
        this.isSorted = true;
        list.removeAll(Collections.singleton(null));
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        this.list = list;
        this.setSize(this.xSize, this.ySize);
    }

    public void setList(List<String> list, boolean ascending, boolean caseOrder) {
        this.isSorted = true;
        if (caseOrder) {
            Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        }
        if (!ascending) {
            Collections.reverse(list);
        }
        this.list = list;
        this.setSize(this.xSize, this.ySize);
    }

    public void setUnsortedList(List<String> list) {
        this.isSorted = false;
        this.list = list;
        this.setSize(this.xSize, this.ySize);
    }

    public void replace(String old, String name) {
        String select = this.getSelected();
        this.list.remove(old);
        this.list.add(name);
        if (this.isSorted) {
            Collections.sort(this.list, String.CASE_INSENSITIVE_ORDER);
        }
        if (old.equals(select)) {
            select = name;
        }
        this.selected = this.list.indexOf(select);
        this.setSize(this.xSize, this.ySize);
    }

    public void setSelected(String name) {
        this.selected = this.list.indexOf(name);
    }

    public void clear() {
        this.list = new ArrayList<String>();
        this.selected = -1;
        this.scrollY = 0;
        this.setSize(this.xSize, this.ySize);
    }

    public List<String> getList() {
        return this.list;
    }

    public HashSet<String> getSelectedList() {
        return this.selectedList;
    }

    public void setSelectedList(HashSet<String> selectedList) {
        this.selectedList = selectedList;
    }

    public GuiCustomScroll setUnselectable() {
        this.selectable = false;
        return this;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
        if (!selectable) {
            this.hover = -1;
        }
    }

    public boolean isMouseOver(int x, int y) {
        return x >= this.guiLeft && x <= this.guiLeft + this.xSize && y >= this.guiTop && y <= this.guiTop + this.ySize;
    }

    public void resetScroll() {
        this.scrollY = 0;
    }

    public void updateSubGUI(boolean hasSubGUI) {
        this.hasSubGUI = hasSubGUI;
    }
}

