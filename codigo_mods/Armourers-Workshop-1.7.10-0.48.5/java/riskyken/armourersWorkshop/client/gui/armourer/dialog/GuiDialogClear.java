/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 */
package riskyken.armourersWorkshop.client.gui.armourer.dialog;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.gui.AbstractGuiDialog;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiCheckBox;
import riskyken.armourersWorkshop.client.gui.controls.GuiDropDownList;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.skin.type.block.SkinBlock;

@SideOnly(value=Side.CLIENT)
public class GuiDialogClear
extends AbstractGuiDialog {
    private GuiButtonExt buttonClose;
    private GuiButtonExt buttonClear;
    private GuiDropDownList dropDownParts;
    private GuiCheckBox checkClearBlocks;
    private GuiCheckBox checkClearPaint;
    private GuiCheckBox checkClearMarkers;
    private final ISkinType skinType;
    private final SkinProperties skinProperties;

    public GuiDialogClear(GuiScreen parent, String name, AbstractGuiDialog.IDialogCallback callback, int width, int height, ISkinType skinType, SkinProperties skinProperties) {
        super(parent, name, callback, width, height);
        this.skinType = skinType;
        this.skinProperties = skinProperties;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        this.buttonClose = new GuiButtonExt(-1, this.x + this.width - 80 - 10, this.y + this.height - 30, 80, 20, GuiHelper.getLocalizedControlName(this.name, "close"));
        this.buttonClear = new GuiButtonExt(-1, this.x + this.width - 160 - 20, this.y + this.height - 30, 80, 20, GuiHelper.getLocalizedControlName(this.name, "clear"));
        this.dropDownParts = new GuiDropDownList(0, this.x + 10, this.y + 20, 60, "", null);
        this.dropDownParts.addListItem("*", "*", true);
        if (this.skinType != null) {
            if (this.skinType != SkinTypeRegistry.skinBlock) {
                for (int i = 0; i < this.skinType.getSkinParts().size(); ++i) {
                    ISkinPartType partType = this.skinType.getSkinParts().get(i);
                    this.addPartToDropDown(this.dropDownParts, partType);
                }
            } else {
                boolean multiblock = SkinProperties.PROP_BLOCK_MULTIBLOCK.getValue(this.skinProperties);
                ISkinPartType partType = multiblock ? ((SkinBlock)SkinTypeRegistry.skinBlock).partMultiblock : ((SkinBlock)SkinTypeRegistry.skinBlock).partBase;
                this.addPartToDropDown(this.dropDownParts, partType);
            }
        }
        this.dropDownParts.setListSelectedIndex(0);
        this.checkClearBlocks = new GuiCheckBox(0, this.x + 10, this.y + this.height - 60, GuiHelper.getLocalizedControlName(this.name, "clearBlocks"), true);
        this.checkClearPaint = new GuiCheckBox(0, this.x + 10, this.y + this.height - 50, GuiHelper.getLocalizedControlName(this.name, "clearPaint"), true);
        this.checkClearMarkers = new GuiCheckBox(0, this.x + 10, this.y + this.height - 70, GuiHelper.getLocalizedControlName(this.name, "clearMarkers"), true);
        this.buttonList.add(this.buttonClose);
        this.buttonList.add(this.buttonClear);
        this.buttonList.add(this.dropDownParts);
        this.buttonList.add(this.checkClearBlocks);
        this.buttonList.add(this.checkClearPaint);
        this.buttonList.add(this.checkClearMarkers);
    }

    private void addPartToDropDown(GuiDropDownList dropDown, ISkinPartType partType) {
        String regName = partType.getRegistryName();
        String disName = SkinTypeRegistry.INSTANCE.getLocalizedSkinPartTypeName(partType);
        dropDown.addListItem(disName, regName, true);
    }

    public String getClearTag() {
        return this.dropDownParts.getListSelectedItem().tag;
    }

    public boolean isClearBlocks() {
        return this.checkClearBlocks.isChecked();
    }

    public boolean isClearPaint() {
        return this.checkClearPaint.isChecked();
    }

    public boolean isClearMarkers() {
        return this.checkClearMarkers.isChecked();
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == this.buttonClose) {
            this.returnDialogResult(AbstractGuiDialog.DialogResult.CANCEL);
        }
        if (button == this.buttonClear) {
            this.returnDialogResult(AbstractGuiDialog.DialogResult.OK);
        }
    }

    @Override
    public void drawForeground(int mouseX, int mouseY, float partialTickTime) {
        super.drawForeground(mouseX, mouseY, partialTickTime);
        this.drawTitle();
        this.dropDownParts.drawForeground(this.mc, mouseX, mouseY, partialTickTime);
    }
}

