/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 */
package riskyken.armourersWorkshop.client.gui.hologramprojector;

import cpw.mods.fml.client.config.GuiUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiCheckBox;
import riskyken.armourersWorkshop.client.gui.controls.GuiDropDownList;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabPanel;
import riskyken.armourersWorkshop.common.data.Rectangle_I_2D;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiHologramProjector;
import riskyken.armourersWorkshop.common.tileentities.TileEntityHologramProjector;

public class GuiHologramProjectorTabExtra
extends GuiTabPanel
implements GuiDropDownList.IDropDownListCallback {
    private final String inventoryName;
    private final TileEntityHologramProjector tileEntity;
    private GuiCheckBox checkGlowing;
    private GuiDropDownList dropDownPowerMode;

    public GuiHologramProjectorTabExtra(int tabId, GuiScreen parent, String inventoryName, TileEntityHologramProjector tileEntity) {
        super(tabId, parent, true);
        this.inventoryName = inventoryName;
        this.tileEntity = tileEntity;
    }

    @Override
    public void initGui(int xPos, int yPos, int width, int height) {
        super.initGui(xPos, yPos, width, height);
        this.checkGlowing = new GuiCheckBox(-1, (int)((float)width / 2.0f - 100.0f) + 10, 30, GuiHelper.getLocalizedControlName(this.inventoryName, "glowing"), this.tileEntity.isGlowing());
        this.dropDownPowerMode = new GuiDropDownList(0, (int)((float)width / 2.0f - 100.0f) + 10, 55, 80, "", this);
        for (int i = 0; i < TileEntityHologramProjector.PowerMode.values().length; ++i) {
            TileEntityHologramProjector.PowerMode powerMode = TileEntityHologramProjector.PowerMode.values()[i];
            this.dropDownPowerMode.addListItem(GuiHelper.getLocalizedControlName(this.inventoryName, "powerMode." + powerMode.toString().toLowerCase()), powerMode.toString(), true);
            if (powerMode != this.tileEntity.getPowerMode()) continue;
            this.dropDownPowerMode.setListSelectedIndex(i);
        }
        this.buttonList.add(this.checkGlowing);
        this.buttonList.add(this.dropDownPowerMode);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == this.checkGlowing) {
            MessageClientGuiHologramProjector message = new MessageClientGuiHologramProjector();
            message.setGlowing(this.checkGlowing.isChecked());
            PacketHandler.networkWrapper.sendToServer((IMessage)message);
        }
    }

    @Override
    public void drawBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
        Rectangle_I_2D rec = new Rectangle_I_2D(0, 0, 200, 78);
        rec.x = this.width / 2 - rec.width / 2;
        GuiUtils.drawContinuousTexturedBox((int)rec.x, (int)rec.y, (int)0, (int)138, (int)rec.width, (int)rec.height, (int)38, (int)38, (int)4, (float)this.field_73735_i);
    }

    @Override
    public void drawForegroundLayer(int mouseX, int mouseY) {
        super.drawForegroundLayer(mouseX, mouseY);
        String labelPowerMode = GuiHelper.getLocalizedControlName(this.inventoryName, "label.powerMode");
        this.fontRenderer.func_78276_b(labelPowerMode, (int)((float)this.width / 2.0f - 100.0f) + 10, 45, 0x404040);
        this.dropDownPowerMode.drawForeground(this.mc, mouseX, mouseY, 0.0f);
    }

    @Override
    public void onDropDownListChanged(GuiDropDownList dropDownList) {
        MessageClientGuiHologramProjector message = new MessageClientGuiHologramProjector();
        message.setPowerMode(TileEntityHologramProjector.PowerMode.valueOf(this.dropDownPowerMode.getListSelectedItem().tag));
        PacketHandler.networkWrapper.sendToServer((IMessage)message);
    }
}

