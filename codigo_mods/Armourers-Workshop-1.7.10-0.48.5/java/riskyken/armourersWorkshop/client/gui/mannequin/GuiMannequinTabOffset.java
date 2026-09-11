/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.client.config.GuiSlider
 *  cpw.mods.fml.client.config.GuiSlider$ISlider
 *  cpw.mods.fml.client.config.GuiUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 */
package riskyken.armourersWorkshop.client.gui.mannequin;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.client.config.GuiSlider;
import cpw.mods.fml.client.config.GuiUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiCustomSlider;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabPanel;
import riskyken.armourersWorkshop.client.gui.mannequin.GuiMannequin;
import riskyken.armourersWorkshop.common.data.Rectangle_I_2D;
import riskyken.armourersWorkshop.common.data.TextureType;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiMannequinData;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMannequin;

@SideOnly(value=Side.CLIENT)
public class GuiMannequinTabOffset
extends GuiTabPanel
implements GuiSlider.ISlider {
    private static final int TAB_WIDTH = 216;
    private static final int TAB_HEIGHT = 90;
    private final String inventoryName;
    private final TileEntityMannequin tileEntity;
    private boolean guiLoaded = false;
    private GuiButtonExt resetOffsetButton;
    private GuiCustomSlider bipedOffsetXslider;
    private GuiCustomSlider bipedOffsetYslider;
    private GuiCustomSlider bipedOffsetZslider;

    public GuiMannequinTabOffset(int tabId, GuiScreen parent, String inventoryName, TileEntityMannequin tileEntity) {
        super(tabId, parent, true);
        this.inventoryName = inventoryName;
        this.tileEntity = tileEntity;
    }

    @Override
    public void initGui(int xPos, int yPos, int width, int height) {
        super.initGui(xPos, yPos, width, height);
        this.guiLoaded = false;
        this.resetOffsetButton = new GuiButtonExt(0, 0, 64, 50, 18, GuiHelper.getLocalizedControlName(this.inventoryName, "reset"));
        this.resetOffsetButton.field_146120_f = this.fontRenderer.func_78256_a(this.resetOffsetButton.field_146126_j) + this.fontRenderer.func_78256_a(" ") * 4;
        this.resetOffsetButton.field_146128_h = this.width / 2 - 108 + 216 - 10 - this.resetOffsetButton.field_146120_f;
        this.bipedOffsetXslider = new GuiCustomSlider(0, (int)((float)width / 2.0f - 108.0f) + 10, 25, 196, 10, "X: ", "", -3.0, 3.0, 0.0, true, true, this);
        this.bipedOffsetYslider = new GuiCustomSlider(0, (int)((float)width / 2.0f - 108.0f) + 10, 37, 196, 10, "Y: ", "", -3.0, 3.0, 0.0, true, true, this);
        this.bipedOffsetZslider = new GuiCustomSlider(0, (int)((float)width / 2.0f - 108.0f) + 10, 49, 196, 10, "Z: ", "", -3.0, 3.0, 0.0, true, true, this);
        this.setSliderValue(this.bipedOffsetXslider, this.tileEntity.getOffsetX());
        this.setSliderValue(this.bipedOffsetYslider, this.tileEntity.getOffsetY());
        this.setSliderValue(this.bipedOffsetZslider, this.tileEntity.getOffsetZ());
        this.buttonList.add(this.resetOffsetButton);
        this.buttonList.add(this.bipedOffsetXslider);
        this.buttonList.add(this.bipedOffsetYslider);
        this.buttonList.add(this.bipedOffsetZslider);
        this.guiLoaded = true;
    }

    private void setSliderValue(GuiCustomSlider slider, double value) {
        slider.setValue(value);
        slider.precision = 2;
        slider.updateSlider();
    }

    @Override
    public void drawBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
        Rectangle_I_2D rec = new Rectangle_I_2D(0, 0, 216, 90);
        rec.x = this.width / 2 - rec.width / 2;
        GuiUtils.drawContinuousTexturedBox((int)rec.x, (int)rec.y, (int)0, (int)200, (int)rec.width, (int)rec.height, (int)38, (int)38, (int)4, (float)this.field_73735_i);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == this.resetOffsetButton) {
            this.bipedOffsetXslider.setValue(0.0);
            this.bipedOffsetYslider.setValue(0.0);
            this.bipedOffsetZslider.setValue(0.0);
            this.bipedOffsetXslider.updateSlider();
            this.bipedOffsetYslider.updateSlider();
            this.bipedOffsetZslider.updateSlider();
            this.sendData();
        }
    }

    public void onChangeSliderValue(GuiSlider slider) {
        if (!this.guiLoaded) {
            return;
        }
        this.sendData();
    }

    public void sendData() {
        float offsetX = (float)this.bipedOffsetXslider.getValue();
        float offsetY = (float)this.bipedOffsetYslider.getValue();
        float offsetZ = (float)this.bipedOffsetZslider.getValue();
        boolean renderExtras = ((GuiMannequin)this.parent).tabExtraRenders.isExtraRenders.isChecked();
        boolean flying = ((GuiMannequin)this.parent).tabExtraRenders.isFlying.isChecked();
        boolean visible = ((GuiMannequin)this.parent).tabExtraRenders.isVisible.isChecked();
        TextureType textureType = TextureType.values()[((GuiMannequin)this.parent).tabTexture.textureTypeList.getListSelectedIndex()];
        String name = ((GuiMannequin)this.parent).tabTexture.nameTextbox.func_146179_b();
        int skinColour = ((GuiMannequin)this.parent).tabSkinAndHair.skinColour;
        int hairColour = ((GuiMannequin)this.parent).tabSkinAndHair.hairColour;
        MessageClientGuiMannequinData message = new MessageClientGuiMannequinData(offsetX, offsetY, offsetZ, skinColour, hairColour, name, renderExtras, flying, visible, textureType);
        PacketHandler.networkWrapper.sendToServer((IMessage)message);
    }
}

