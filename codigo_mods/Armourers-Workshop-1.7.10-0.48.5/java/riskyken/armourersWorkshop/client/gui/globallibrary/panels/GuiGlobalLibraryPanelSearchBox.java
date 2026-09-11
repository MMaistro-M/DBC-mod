/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.globallibrary.panels;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiDropDownList;
import riskyken.armourersWorkshop.client.gui.controls.GuiLabeledTextField;
import riskyken.armourersWorkshop.client.gui.controls.GuiPanel;
import riskyken.armourersWorkshop.client.gui.globallibrary.GuiGlobalLibrary;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;

@SideOnly(value=Side.CLIENT)
public class GuiGlobalLibraryPanelSearchBox
extends GuiPanel
implements GuiDropDownList.IDropDownListCallback {
    private GuiLabeledTextField searchTextbox;
    private GuiDropDownList dropDownList;
    public static ISkinType selectedSkinType;

    public GuiGlobalLibraryPanelSearchBox(GuiGlobalLibrary parent, int x, int y, int width, int height) {
        super((GuiScreen)parent, x, y, width, height);
    }

    @Override
    public void initGui() {
        super.initGui();
        String guiName = ((GuiGlobalLibrary)this.parent).getGuiName();
        this.buttonList.clear();
        this.searchTextbox = new GuiLabeledTextField(this.fontRenderer, this.x + 5, this.y + 5, this.width - 10 - 160, 12);
        this.searchTextbox.setEmptyLabel(GuiHelper.getLocalizedControlName(guiName, "searchBox.typeToSearch"));
        this.buttonList.add(new GuiButtonExt(0, this.x + this.width - 85, this.y + 3, 80, 16, GuiHelper.getLocalizedControlName(guiName, "searchBox.search")));
        SkinTypeRegistry str = SkinTypeRegistry.INSTANCE;
        this.dropDownList = new GuiDropDownList(1, this.x + this.width - 160, this.y + 4, 70, "", this);
        ArrayList<ISkinType> skinList = str.getRegisteredSkinTypes();
        this.dropDownList.addListItem("*");
        this.dropDownList.setListSelectedIndex(0);
        int skinCount = 0;
        for (int i = 0; i < skinList.size(); ++i) {
            ISkinType skinType = skinList.get(i);
            if (!(!skinType.isHidden() | skinType != SkinTypeRegistry.skinOutfit)) continue;
            String skinLocalizedName = str.getLocalizedSkinTypeName(skinType);
            String skinRegistryName = skinType.getRegistryName();
            this.dropDownList.addListItem(skinLocalizedName, skinRegistryName, skinType.enabled());
            if (skinType == selectedSkinType) {
                this.dropDownList.setListSelectedIndex(skinCount + 1);
            }
            ++skinCount;
        }
        this.buttonList.add(this.dropDownList);
    }

    @Override
    public void onDropDownListChanged(GuiDropDownList dropDownList) {
        GuiDropDownList.DropDownListItem listItem = dropDownList.getListSelectedItem();
        selectedSkinType = SkinTypeRegistry.INSTANCE.getSkinTypeFromRegistryName(listItem.tag);
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int button) {
        if (!this.visible | !this.enabled) {
            return false;
        }
        boolean clicked = super.mouseClicked(mouseX, mouseY, button);
        if (!clicked) {
            this.searchTextbox.func_146192_a(mouseX, mouseY, button);
            if (button == 1 && this.searchTextbox.func_146206_l()) {
                this.searchTextbox.func_146180_a("");
            }
        }
        return clicked;
    }

    @Override
    public boolean keyTyped(char c, int keycode) {
        if (!this.visible | !this.enabled) {
            return false;
        }
        boolean pressed = this.searchTextbox.func_146201_a(c, keycode);
        if (keycode == 28) {
            this.doSearch();
        }
        return pressed;
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.field_146127_k == 0) {
            this.doSearch();
        }
    }

    private void doSearch() {
        ((GuiGlobalLibrary)this.parent).panelSearchResults.clearResults();
        String search = this.searchTextbox.func_146179_b();
        ((GuiGlobalLibrary)this.parent).switchScreen(GuiGlobalLibrary.Screen.SEARCH);
        ((GuiGlobalLibrary)this.parent).panelSearchResults.doSearch(search, selectedSkinType);
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTickTime) {
        if (!this.visible) {
            return;
        }
        this.func_73733_a(this.x, this.y, this.x + this.width, this.y + this.height, -1072689136, -804253680);
        super.draw(mouseX, mouseY, partialTickTime);
        this.searchTextbox.func_146194_f();
    }

    @Override
    public void drawForeground(int mouseX, int mouseY, float partialTickTime) {
        GL11.glDisable((int)2929);
        this.dropDownList.drawForeground(this.mc, mouseX, mouseY, partialTickTime);
    }
}

