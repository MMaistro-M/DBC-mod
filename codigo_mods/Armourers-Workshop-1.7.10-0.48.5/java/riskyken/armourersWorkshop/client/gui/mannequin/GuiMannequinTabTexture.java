/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.client.config.GuiUtils
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiTextField
 */
package riskyken.armourersWorkshop.client.gui.mannequin;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.client.config.GuiUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiDropDownList;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabPanel;
import riskyken.armourersWorkshop.client.gui.mannequin.GuiMannequin;
import riskyken.armourersWorkshop.common.data.Rectangle_I_2D;
import riskyken.armourersWorkshop.common.data.TextureType;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMannequin;

@SideOnly(value=Side.CLIENT)
public class GuiMannequinTabTexture
extends GuiTabPanel
implements GuiDropDownList.IDropDownListCallback {
    private static final int TAB_WIDTH = 240;
    private static final int TAB_HEIGHT = 68;
    private final TileEntityMannequin tileEntity;
    public GuiDropDownList textureTypeList;
    public GuiTextField nameTextbox;
    private GuiButtonExt setNameButton;

    public GuiMannequinTabTexture(int tabId, GuiScreen parent, TileEntityMannequin tileEntity) {
        super(tabId, parent, true);
        this.tileEntity = tileEntity;
    }

    @Override
    public void initGui(int xPos, int yPos, int width, int height) {
        super.initGui(xPos, yPos, width, height);
        this.textureTypeList = new GuiDropDownList(0, width / 2 - 110, 25, 50, "", this);
        this.textureTypeList.addListItem(GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "dropdown.user"), TextureType.USER.toString(), true);
        this.textureTypeList.addListItem(GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "dropdown.url"), TextureType.URL.toString(), true);
        this.textureTypeList.setListSelectedIndex(this.tileEntity.getTextureType().ordinal());
        this.nameTextbox = new GuiTextField(this.fontRenderer, width / 2 - 110 + 55, 25, 165, 14);
        this.nameTextbox.func_146203_f(300);
        if (this.tileEntity.getTextureType() == TextureType.USER) {
            if (this.tileEntity.getGameProfile() != null) {
                this.nameTextbox.func_146180_a(this.tileEntity.getGameProfile().getName());
            }
        } else if (this.tileEntity.getImageUrl() != null) {
            this.nameTextbox.func_146180_a(this.tileEntity.getImageUrl());
        }
        this.setNameButton = new GuiButtonExt(0, width / 2 + 60, 45, 50, 14, GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "set"));
        this.setNameButton.field_146120_f = this.fontRenderer.func_78256_a(this.setNameButton.field_146126_j + "  ");
        this.setNameButton.field_146128_h = width / 2 + 120 - this.setNameButton.field_146120_f - 10;
        this.buttonList.add(this.textureTypeList);
        this.buttonList.add(this.setNameButton);
    }

    @Override
    public void drawBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
        Rectangle_I_2D rec = new Rectangle_I_2D(0, 0, 240, 68);
        rec.x = this.width / 2 - rec.width / 2;
        GuiUtils.drawContinuousTexturedBox((int)rec.x, (int)rec.y, (int)0, (int)200, (int)rec.width, (int)rec.height, (int)38, (int)38, (int)4, (float)this.field_73735_i);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        super.mouseClicked(mouseX, mouseY, button);
        if (button == 1) {
            this.nameTextbox.func_146180_a("");
        } else {
            this.nameTextbox.func_146192_a(mouseX, mouseY, button);
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == this.setNameButton) {
            ((GuiMannequin)this.parent).tabOffset.sendData();
        }
    }

    @Override
    public boolean keyTyped(char c, int keycode) {
        return this.nameTextbox.func_146201_a(c, keycode);
    }

    @Override
    public void drawForegroundLayer(int mouseX, int mouseY) {
        super.drawForegroundLayer(mouseX, mouseY);
        this.nameTextbox.func_146194_f();
        this.textureTypeList.drawForeground(this.mc, mouseX, mouseY, 0.0f);
    }

    @Override
    public void onDropDownListChanged(GuiDropDownList dropDownList) {
        ((GuiMannequin)this.parent).tabOffset.sendData();
    }
}

