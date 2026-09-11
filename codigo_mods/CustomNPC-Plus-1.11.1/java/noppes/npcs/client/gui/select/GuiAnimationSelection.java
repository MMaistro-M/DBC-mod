/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 */
package noppes.npcs.client.gui.select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.animation.AnimationsGetPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.constants.EnumScrollData;

public class GuiAnimationSelection
extends SubGuiInterface
implements ICustomScrollListener,
IScrollData {
    private GuiCustomScroll scrollAnimations;
    private HashMap<String, Integer> builtInData = new HashMap();
    private HashMap<String, Integer> customData = new HashMap();
    private boolean showingBuiltIn = true;
    private int initialAnimationId;
    private String initialAnimationName;
    public int selectedAnimationId = -1;
    public String selectedBuiltInName = "";
    private String selectedCustomName = null;
    private String search = "";

    public GuiAnimationSelection(int animationId) {
        this(animationId, "");
    }

    public GuiAnimationSelection(int animationId, String animationName) {
        this.title = "";
        this.setBackground("menubg.png");
        this.xSize = 220;
        this.ySize = 226;
        this.initialAnimationId = animationId;
        this.initialAnimationName = animationName != null ? animationName : "";
        this.selectedAnimationId = animationId;
        this.selectedBuiltInName = this.initialAnimationName;
        this.showingBuiltIn = !this.initialAnimationName.isEmpty();
        PacketClient.sendClient(new AnimationsGetPacket());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 4;
        String viewLabel = this.showingBuiltIn ? "gui.builtinAnimations" : "gui.customAnimations";
        this.addButton(new GuiNpcButton(10, this.guiLeft + 4, y, 212, 20, viewLabel));
        y += 24;
        if (this.scrollAnimations == null) {
            this.scrollAnimations = new GuiCustomScroll((GuiScreen)this, 0, 0);
            this.scrollAnimations.setSize(212, 130);
        }
        this.scrollAnimations.setList(this.getSearchList());
        if (this.showingBuiltIn && !this.selectedBuiltInName.isEmpty()) {
            this.scrollAnimations.setSelected(this.selectedBuiltInName);
        } else if (!this.showingBuiltIn && this.selectedCustomName != null) {
            this.scrollAnimations.setSelected(this.selectedCustomName);
        }
        this.scrollAnimations.guiLeft = this.guiLeft + 4;
        this.scrollAnimations.guiTop = y;
        this.addScroll(this.scrollAnimations);
        this.addTextField(new GuiNpcTextField(55, this, this.field_146289_q, this.guiLeft + 4, y += 134, 212, 20, this.search));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 4, y += 24, 50, 20, "gui.clear"));
        this.addButton(new GuiNpcButton(3, this.guiLeft + this.xSize - 108, y, 50, 20, "gui.cancel"));
        this.addButton(new GuiNpcButton(2, this.guiLeft + this.xSize - 56, y, 50, 20, "gui.done"));
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        if (type == EnumScrollData.BUILTIN_ANIMATIONS) {
            this.builtInData = data;
            if (!this.initialAnimationName.isEmpty()) {
                for (String name : this.builtInData.keySet()) {
                    if (!name.equalsIgnoreCase(this.initialAnimationName)) continue;
                    this.selectedBuiltInName = name;
                    this.selectedAnimationId = -1;
                    this.selectedCustomName = null;
                    this.showingBuiltIn = true;
                    break;
                }
            }
        } else if (type == EnumScrollData.ANIMATIONS) {
            this.customData = data;
            if (this.selectedBuiltInName.isEmpty() && this.initialAnimationId >= 0) {
                for (String name : data.keySet()) {
                    if (data.get(name) != this.initialAnimationId) continue;
                    this.selectedCustomName = name;
                    this.showingBuiltIn = false;
                    break;
                }
            }
        }
        if (this.scrollAnimations != null) {
            this.scrollAnimations.setList(this.getSearchList());
            if (this.showingBuiltIn && !this.selectedBuiltInName.isEmpty()) {
                this.scrollAnimations.setSelected(this.selectedBuiltInName);
            } else if (!this.showingBuiltIn && this.selectedCustomName != null) {
                this.scrollAnimations.setSelected(this.selectedCustomName);
            }
        }
    }

    @Override
    public void setSelected(String selected) {
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.getTextField(55) != null && this.getTextField(55).func_146206_l()) {
            if (this.search.equals(this.getTextField(55).func_146179_b())) {
                return;
            }
            this.search = this.getTextField(55).func_146179_b().toLowerCase();
            if (this.scrollAnimations != null) {
                this.scrollAnimations.resetScroll();
                this.scrollAnimations.setList(this.getSearchList());
            }
        }
    }

    private List<String> getSearchList() {
        HashMap<String, Integer> sourceData;
        HashMap<String, Integer> hashMap = sourceData = this.showingBuiltIn ? this.builtInData : this.customData;
        if (this.search.isEmpty()) {
            return new ArrayList<String>(sourceData.keySet());
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String name : sourceData.keySet()) {
            if (!name.toLowerCase().contains(this.search)) continue;
            list.add(name);
        }
        return list;
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        String selected = this.scrollAnimations.getSelected();
        if (selected == null) {
            return;
        }
        if (this.showingBuiltIn) {
            if (this.builtInData.containsKey(selected)) {
                this.selectedBuiltInName = selected;
                this.selectedAnimationId = -1;
                this.selectedCustomName = null;
            }
        } else if (this.customData.containsKey(selected)) {
            this.selectedCustomName = selected;
            this.selectedAnimationId = this.customData.get(selected);
            this.selectedBuiltInName = "";
        }
        this.func_73866_w_();
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll scroll) {
        if (!this.selectedBuiltInName.isEmpty() || this.selectedAnimationId >= 0) {
            this.close();
        }
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        int id = guibutton.field_146127_k;
        if (id == 10) {
            this.showingBuiltIn = !this.showingBuiltIn;
            this.search = "";
            if (this.scrollAnimations != null) {
                this.scrollAnimations.resetScroll();
            }
            this.func_73866_w_();
        } else if (id == 1) {
            this.selectedBuiltInName = "";
            this.selectedAnimationId = -1;
            this.selectedCustomName = null;
            this.close();
        } else if (id == 2) {
            this.close();
        } else if (id == 3) {
            this.selectedAnimationId = this.initialAnimationId;
            this.selectedBuiltInName = this.initialAnimationName;
            this.close();
        }
    }

    public String getSelectedName() {
        if (!this.selectedBuiltInName.isEmpty()) {
            return this.selectedBuiltInName;
        }
        return this.selectedCustomName;
    }

    public boolean isBuiltInSelected() {
        return !this.selectedBuiltInName.isEmpty();
    }
}

