/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiSlider
 *  cpw.mods.fml.client.config.GuiSlider$ISlider
 *  cpw.mods.fml.client.config.GuiUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 */
package riskyken.armourersWorkshop.client.gui.hologramprojector;

import cpw.mods.fml.client.config.GuiSlider;
import cpw.mods.fml.client.config.GuiUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiCheckBox;
import riskyken.armourersWorkshop.client.gui.controls.GuiCustomSlider;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabPanel;
import riskyken.armourersWorkshop.common.data.Rectangle_I_2D;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiHologramProjector;
import riskyken.armourersWorkshop.common.tileentities.TileEntityHologramProjector;

public class GuiHologramProjectorTabRotationOffset
extends GuiTabPanel
implements GuiSlider.ISlider {
    private final String inventoryName;
    private final TileEntityHologramProjector tileEntity;
    private boolean guiLoaded = false;
    private GuiCustomSlider sliderOffsetX;
    private GuiCustomSlider sliderOffsetY;
    private GuiCustomSlider sliderOffsetZ;
    private GuiCheckBox checkShowRotationPoint;

    public GuiHologramProjectorTabRotationOffset(int tabId, GuiScreen parent, String inventoryName, TileEntityHologramProjector tileEntity) {
        super(tabId, parent, true);
        this.inventoryName = inventoryName;
        this.tileEntity = tileEntity;
    }

    @Override
    public void initGui(int xPos, int yPos, int width, int height) {
        super.initGui(xPos, yPos, width, height);
        this.guiLoaded = false;
        this.sliderOffsetX = new GuiCustomSlider(-1, (int)((float)width / 2.0f - 100.0f) + 10, 30, 178, 10, "X: ", "", -64.0, 64.0, this.tileEntity.getRotationOffsetX(), false, true, this);
        this.sliderOffsetY = new GuiCustomSlider(-1, (int)((float)width / 2.0f - 100.0f) + 10, 45, 178, 10, "Y: ", "", -64.0, 64.0, this.tileEntity.getRotationOffsetY(), false, true, this);
        this.sliderOffsetZ = new GuiCustomSlider(-1, (int)((float)width / 2.0f - 100.0f) + 10, 60, 178, 10, "Z: ", "", -64.0, 64.0, this.tileEntity.getRotationOffsetZ(), false, true, this);
        this.checkShowRotationPoint = new GuiCheckBox(-1, (int)((float)width / 2.0f - 100.0f) + 10, 75, GuiHelper.getLocalizedControlName(this.inventoryName, "showRotationPoint"), this.tileEntity.isShowRotationPoint());
        this.sliderOffsetX.setFineTuneButtons(true);
        this.sliderOffsetY.setFineTuneButtons(true);
        this.sliderOffsetZ.setFineTuneButtons(true);
        this.buttonList.add(this.sliderOffsetX);
        this.buttonList.add(this.sliderOffsetY);
        this.buttonList.add(this.sliderOffsetZ);
        this.buttonList.add(this.checkShowRotationPoint);
        this.guiLoaded = true;
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == this.checkShowRotationPoint) {
            this.tileEntity.setShowRotationPoint(this.checkShowRotationPoint.isChecked());
        }
    }

    @Override
    public void drawBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
        Rectangle_I_2D rec = new Rectangle_I_2D(0, 0, 200, 92);
        rec.x = this.width / 2 - rec.width / 2;
        GuiUtils.drawContinuousTexturedBox((int)rec.x, (int)rec.y, (int)0, (int)138, (int)rec.width, (int)rec.height, (int)38, (int)38, (int)4, (float)this.field_73735_i);
    }

    public void onChangeSliderValue(GuiSlider slider) {
        if (!this.guiLoaded) {
            return;
        }
        int xOffset = this.sliderOffsetX.getValueInt();
        int yOffset = this.sliderOffsetY.getValueInt();
        int zOffset = this.sliderOffsetZ.getValueInt();
        MessageClientGuiHologramProjector message = new MessageClientGuiHologramProjector();
        message.setRotationOffset(xOffset, yOffset, zOffset);
        PacketHandler.networkWrapper.sendToServer((IMessage)message);
    }
}

