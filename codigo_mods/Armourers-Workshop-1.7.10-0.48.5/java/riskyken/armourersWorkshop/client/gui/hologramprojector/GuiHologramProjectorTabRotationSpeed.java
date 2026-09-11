/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiSlider
 *  cpw.mods.fml.client.config.GuiSlider$ISlider
 *  cpw.mods.fml.client.config.GuiUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.client.gui.GuiScreen
 */
package riskyken.armourersWorkshop.client.gui.hologramprojector;

import cpw.mods.fml.client.config.GuiSlider;
import cpw.mods.fml.client.config.GuiUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.client.gui.GuiScreen;
import riskyken.armourersWorkshop.client.gui.controls.GuiCustomSlider;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabPanel;
import riskyken.armourersWorkshop.common.data.Rectangle_I_2D;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiHologramProjector;
import riskyken.armourersWorkshop.common.tileentities.TileEntityHologramProjector;

public class GuiHologramProjectorTabRotationSpeed
extends GuiTabPanel
implements GuiSlider.ISlider {
    private final String inventoryName;
    private final TileEntityHologramProjector tileEntity;
    private boolean guiLoaded = false;
    private GuiCustomSlider sliderOffsetX;
    private GuiCustomSlider sliderOffsetY;
    private GuiCustomSlider sliderOffsetZ;

    public GuiHologramProjectorTabRotationSpeed(int tabId, GuiScreen parent, String inventoryName, TileEntityHologramProjector tileEntity) {
        super(tabId, parent, true);
        this.inventoryName = inventoryName;
        this.tileEntity = tileEntity;
    }

    @Override
    public void initGui(int xPos, int yPos, int width, int height) {
        super.initGui(xPos, yPos, width, height);
        this.guiLoaded = false;
        this.sliderOffsetX = new GuiCustomSlider(-1, (int)((float)width / 2.0f - 100.0f) + 10, 30, 178, 10, "", "ms", -10000.0, 10000.0, this.tileEntity.getRotationSpeedX(), false, true, this);
        this.sliderOffsetY = new GuiCustomSlider(-1, (int)((float)width / 2.0f - 100.0f) + 10, 45, 178, 10, "", "ms", -10000.0, 10000.0, this.tileEntity.getRotationSpeedY(), false, true, this);
        this.sliderOffsetZ = new GuiCustomSlider(-1, (int)((float)width / 2.0f - 100.0f) + 10, 60, 178, 10, "", "ms", -10000.0, 10000.0, this.tileEntity.getRotationSpeedZ(), false, true, this);
        this.sliderOffsetX.setFineTuneButtons(true);
        this.sliderOffsetY.setFineTuneButtons(true);
        this.sliderOffsetZ.setFineTuneButtons(true);
        this.buttonList.add(this.sliderOffsetX);
        this.buttonList.add(this.sliderOffsetY);
        this.buttonList.add(this.sliderOffsetZ);
        this.guiLoaded = true;
    }

    @Override
    public void drawBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
        Rectangle_I_2D rec = new Rectangle_I_2D(0, 0, 200, 82);
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
        message.setRotationSpeedX(xOffset, yOffset, zOffset);
        PacketHandler.networkWrapper.sendToServer((IMessage)message);
    }
}

