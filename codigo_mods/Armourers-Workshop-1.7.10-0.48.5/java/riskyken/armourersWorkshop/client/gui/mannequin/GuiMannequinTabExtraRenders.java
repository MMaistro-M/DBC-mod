/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiUtils
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 */
package riskyken.armourersWorkshop.client.gui.mannequin;

import cpw.mods.fml.client.config.GuiUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiCheckBox;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabPanel;
import riskyken.armourersWorkshop.client.gui.mannequin.GuiMannequin;
import riskyken.armourersWorkshop.common.data.Rectangle_I_2D;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMannequin;

@SideOnly(value=Side.CLIENT)
public class GuiMannequinTabExtraRenders
extends GuiTabPanel {
    private final String inventoryName;
    private final TileEntityMannequin tileEntity;
    private GuiCheckBox isChildCheck;
    public GuiCheckBox isExtraRenders;
    public GuiCheckBox isFlying;
    public GuiCheckBox isVisible;

    public GuiMannequinTabExtraRenders(int tabId, GuiScreen parent, String inventoryName, TileEntityMannequin tileEntity) {
        super(tabId, parent, true);
        this.inventoryName = inventoryName;
        this.tileEntity = tileEntity;
    }

    @Override
    public void initGui(int xPos, int yPos, int width, int height) {
        super.initGui(xPos, yPos, width, height);
        this.isChildCheck = new GuiCheckBox(3, this.width / 2 - 78, 25, GuiHelper.getLocalizedControlName(this.inventoryName, "label.isChild"), false);
        this.isExtraRenders = new GuiCheckBox(0, this.width / 2 - 78, 40, GuiHelper.getLocalizedControlName(this.inventoryName, "label.isExtraRenders"), this.tileEntity.isRenderExtras());
        this.isFlying = new GuiCheckBox(0, this.width / 2 - 78, 55, GuiHelper.getLocalizedControlName(this.inventoryName, "label.isFlying"), this.tileEntity.isFlying());
        this.isVisible = new GuiCheckBox(0, this.width / 2 - 78, 70, GuiHelper.getLocalizedControlName(this.inventoryName, "label.isVisible"), this.tileEntity.isVisible());
        if (((GuiMannequin)this.parent).tabRotations.getBipedRotations() != null) {
            this.isChildCheck.setIsChecked(((GuiMannequin)this.parent).tabRotations.getBipedRotations().isChild);
        }
        this.buttonList.add(this.isChildCheck);
        this.buttonList.add(this.isExtraRenders);
        this.buttonList.add(this.isFlying);
        this.buttonList.add(this.isVisible);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == this.isExtraRenders) {
            ((GuiMannequin)this.parent).tabOffset.sendData();
        }
        if (button == this.isFlying) {
            ((GuiMannequin)this.parent).tabOffset.sendData();
        }
        if (button == this.isChildCheck) {
            ((GuiMannequin)this.parent).tabRotations.getBipedRotations().isChild = this.isChildCheck.isChecked();
            ((GuiMannequin)this.parent).tabRotations.checkAndSendRotationValues();
        }
        if (button == this.isVisible) {
            ((GuiMannequin)this.parent).tabOffset.sendData();
        }
    }

    @Override
    public void drawBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
        Rectangle_I_2D rec = new Rectangle_I_2D(0, 0, 176, 88);
        rec.x = this.width / 2 - rec.width / 2;
        GuiUtils.drawContinuousTexturedBox((int)rec.x, (int)rec.y, (int)0, (int)200, (int)rec.width, (int)rec.height, (int)38, (int)38, (int)4, (float)this.field_73735_i);
    }
}

