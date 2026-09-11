/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.player.EntityPlayer
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.wardrobe.tab;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.BitSet;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiCheckBox;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabPanel;
import riskyken.armourersWorkshop.client.gui.wardrobe.GuiWardrobe;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientSkinWardrobeUpdate;
import riskyken.armourersWorkshop.common.wardrobe.EquipmentWardrobeData;
import riskyken.armourersWorkshop.common.wardrobe.ExPropsPlayerSkinData;

@SideOnly(value=Side.CLIENT)
public class GuiTabWardrobeDisplaySettings
extends GuiTabPanel {
    EntityPlayer entityPlayer;
    ExPropsPlayerSkinData propsPlayerSkinData;
    EquipmentWardrobeData equipmentWardrobeData;
    BitSet armourOverride;
    private GuiCheckBox[] armourOverrideCheck;
    String guiName = "equipmentWardrobe";

    public GuiTabWardrobeDisplaySettings(int tabId, GuiScreen parent, EntityPlayer entityPlayer, ExPropsPlayerSkinData propsPlayerSkinData, EquipmentWardrobeData equipmentWardrobeData) {
        super(tabId, parent, false);
        this.entityPlayer = entityPlayer;
        this.propsPlayerSkinData = propsPlayerSkinData;
        this.equipmentWardrobeData = equipmentWardrobeData;
        this.armourOverride = equipmentWardrobeData.armourOverride;
    }

    @Override
    public void initGui(int xPos, int yPos, int width, int height) {
        super.initGui(xPos, yPos, width, height);
        this.armourOverrideCheck = new GuiCheckBox[4];
        this.armourOverrideCheck[0] = new GuiCheckBox(2, 83, 27, GuiHelper.getLocalizedControlName(this.guiName, "renderHeadArmour"), !this.armourOverride.get(0));
        this.armourOverrideCheck[1] = new GuiCheckBox(3, 83, 37, GuiHelper.getLocalizedControlName(this.guiName, "renderChestArmour"), !this.armourOverride.get(1));
        this.armourOverrideCheck[2] = new GuiCheckBox(4, 83, 47, GuiHelper.getLocalizedControlName(this.guiName, "renderLegArmour"), !this.armourOverride.get(2));
        this.armourOverrideCheck[3] = new GuiCheckBox(5, 83, 57, GuiHelper.getLocalizedControlName(this.guiName, "renderFootArmour"), !this.armourOverride.get(3));
        this.buttonList.add(this.armourOverrideCheck[0]);
        this.buttonList.add(this.armourOverrideCheck[1]);
        this.buttonList.add(this.armourOverrideCheck[2]);
        this.buttonList.add(this.armourOverrideCheck[3]);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button instanceof GuiCheckBox) {
            for (int i = 0; i < 4; ++i) {
                this.armourOverride.set(i, !this.armourOverrideCheck[i].isChecked());
            }
        }
        if (button.field_146127_k >= 1) {
            this.equipmentWardrobeData.armourOverride = this.armourOverride;
            PacketHandler.networkWrapper.sendToServer((IMessage)new MessageClientSkinWardrobeUpdate(this.equipmentWardrobeData));
        }
    }

    @Override
    public void drawBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    @Override
    public void drawForegroundLayer(int mouseX, int mouseY) {
        super.drawForegroundLayer(mouseX, mouseY);
        GL11.glPushMatrix();
        GL11.glTranslated((double)(-this.x), (double)(-this.y), (double)0.0);
        ((GuiWardrobe)this.parent).drawPlayerPreview(this.x, this.y, mouseX, mouseY);
        GL11.glPopMatrix();
    }
}

