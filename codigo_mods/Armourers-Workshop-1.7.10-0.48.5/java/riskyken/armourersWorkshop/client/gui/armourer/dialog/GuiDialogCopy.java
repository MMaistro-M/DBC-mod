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
public class GuiDialogCopy
extends AbstractGuiDialog {
    private final ISkinType skinType;
    private GuiButtonExt buttonClose;
    private GuiButtonExt buttonCopy;
    private GuiDropDownList dropDownSrcPart;
    private GuiDropDownList dropDownDesPart;
    private GuiCheckBox checkMirror;
    private final SkinProperties skinProperties;

    public GuiDialogCopy(GuiScreen parent, String name, AbstractGuiDialog.IDialogCallback callback, int width, int height, ISkinType skinType, SkinProperties skinProperties) {
        super(parent, name, callback, width, height);
        this.skinType = skinType;
        this.skinProperties = skinProperties;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        this.buttonClose = new GuiButtonExt(-1, this.x + this.width - 80 - 10, this.y + this.height - 30, 80, 20, GuiHelper.getLocalizedControlName(this.name, "close"));
        this.buttonCopy = new GuiButtonExt(-1, this.x + this.width - 160 - 20, this.y + this.height - 30, 80, 20, GuiHelper.getLocalizedControlName(this.name, "copy"));
        this.checkMirror = new GuiCheckBox(-1, this.x + 10, this.y + this.height - 50, GuiHelper.getLocalizedControlName(this.name, "mirror"), false);
        this.dropDownSrcPart = new GuiDropDownList(0, this.x + 10, this.y + 35, 80, "", null);
        this.dropDownDesPart = new GuiDropDownList(0, this.x + 100, this.y + 35, 80, "", null);
        if (this.skinType != null) {
            if (this.skinType != SkinTypeRegistry.skinBlock) {
                for (int i = 0; i < this.skinType.getSkinParts().size(); ++i) {
                    this.addPartToDropDown(this.dropDownSrcPart, this.skinType.getSkinParts().get(i));
                    this.addPartToDropDown(this.dropDownDesPart, this.skinType.getSkinParts().get(i));
                }
            } else {
                boolean multiblock = SkinProperties.PROP_BLOCK_MULTIBLOCK.getValue(this.skinProperties);
                ISkinPartType partType = multiblock ? ((SkinBlock)SkinTypeRegistry.skinBlock).partMultiblock : ((SkinBlock)SkinTypeRegistry.skinBlock).partBase;
                this.addPartToDropDown(this.dropDownSrcPart, partType);
                this.addPartToDropDown(this.dropDownDesPart, partType);
            }
        }
        this.dropDownSrcPart.setListSelectedIndex(0);
        this.dropDownDesPart.setListSelectedIndex(0);
        this.buttonList.add(this.buttonClose);
        this.buttonList.add(this.buttonCopy);
        this.buttonList.add(this.checkMirror);
        this.buttonList.add(this.dropDownSrcPart);
        this.buttonList.add(this.dropDownDesPart);
    }

    private void addPartToDropDown(GuiDropDownList dropDown, ISkinPartType partType) {
        String regName = partType.getRegistryName();
        String disName = SkinTypeRegistry.INSTANCE.getLocalizedSkinPartTypeName(partType);
        dropDown.addListItem(disName, regName, true);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == this.buttonClose) {
            this.returnDialogResult(AbstractGuiDialog.DialogResult.CANCEL);
        }
        if (button == this.buttonCopy) {
            this.returnDialogResult(AbstractGuiDialog.DialogResult.OK);
        }
    }

    @Override
    public void drawForeground(int mouseX, int mouseY, float partialTickTime) {
        super.drawForeground(mouseX, mouseY, partialTickTime);
        this.fontRenderer.func_78276_b(GuiHelper.getLocalizedControlName(this.name, "srcPart"), this.x + 10, this.y + 25, 0x404040);
        this.fontRenderer.func_78276_b(GuiHelper.getLocalizedControlName(this.name, "desPart"), this.x + 100, this.y + 25, 0x404040);
        this.drawTitle();
        this.dropDownSrcPart.drawForeground(this.mc, mouseX, mouseY, partialTickTime);
        this.dropDownDesPart.drawForeground(this.mc, mouseX, mouseY, partialTickTime);
    }

    public ISkinPartType getSrcPart() {
        return SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName(this.dropDownSrcPart.getListSelectedItem().tag);
    }

    public ISkinPartType getDesPart() {
        return SkinTypeRegistry.INSTANCE.getSkinPartFromRegistryName(this.dropDownDesPart.getListSelectedItem().tag);
    }

    public boolean isMirror() {
        return this.checkMirror.isChecked();
    }
}

