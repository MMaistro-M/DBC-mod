/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.util.ResourceLocation
 */
package riskyken.armourersWorkshop.client.gui.armourer.tab;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.armourer.GuiArmourer;
import riskyken.armourersWorkshop.client.gui.controls.GuiCheckBox;
import riskyken.armourersWorkshop.client.gui.controls.GuiDropDownList;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabPanel;
import riskyken.armourersWorkshop.client.lib.LibGuiResources;
import riskyken.armourersWorkshop.client.texture.PlayerTexture;
import riskyken.armourersWorkshop.common.data.TextureType;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiButton;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiSetSkin;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourer;

@SideOnly(value=Side.CLIENT)
public class GuiTabArmourerDisplaySettings
extends GuiTabPanel
implements GuiDropDownList.IDropDownListCallback {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LibGuiResources.ARMOURER);
    private final TileEntityArmourer tileEntity;
    private GuiDropDownList textureTypeList;
    private GuiTextField textUserSkin;
    private GuiCheckBox checkShowGuides;
    private GuiCheckBox checkShowHelper;

    public GuiTabArmourerDisplaySettings(int tabId, GuiScreen parent) {
        super(tabId, parent, false);
        this.tileEntity = ((GuiArmourer)parent).tileEntity;
    }

    @Override
    public void initGui(int xPos, int yPos, int width, int height) {
        super.initGui(xPos, yPos, width, height);
        String guiName = this.tileEntity.func_145825_b();
        this.buttonList.clear();
        this.checkShowGuides = new GuiCheckBox(7, 10, 95, GuiHelper.getLocalizedControlName(guiName, "showGuide"), this.tileEntity.isShowGuides());
        this.checkShowHelper = new GuiCheckBox(6, 10, 110, GuiHelper.getLocalizedControlName(guiName, "showHelper"), this.tileEntity.isShowHelper());
        this.buttonList.add(new GuiButtonExt(8, width - 36 - 5, 70, 30, 16, GuiHelper.getLocalizedControlName(guiName, "set")));
        this.textUserSkin = new GuiTextField(this.fontRenderer, this.x + 10, this.y + 70, 120, 16);
        this.textUserSkin.func_146203_f(300);
        this.textUserSkin.func_146180_a(this.tileEntity.getTexture().getTextureString());
        this.textureTypeList = new GuiDropDownList(0, 10, 30, 50, "", this);
        this.textureTypeList.addListItem(GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "dropdown.user"), TextureType.USER.toString(), true);
        this.textureTypeList.addListItem(GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "dropdown.url"), TextureType.URL.toString(), true);
        this.textureTypeList.setListSelectedIndex(this.tileEntity.getTexture().getTextureType().ordinal());
        this.buttonList.add(this.checkShowGuides);
        this.buttonList.add(this.checkShowHelper);
        this.buttonList.add(this.textureTypeList);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        super.mouseClicked(mouseX, mouseY, button);
        this.textUserSkin.func_146192_a(mouseX, mouseY, button);
    }

    @Override
    public boolean keyTyped(char c, int keycode) {
        return this.textUserSkin.func_146201_a(c, keycode);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.field_146127_k == 8) {
            String username = this.textUserSkin.func_146179_b().trim();
            PacketHandler.networkWrapper.sendToServer((IMessage)new MessageClientGuiSetSkin(new PlayerTexture(username, TextureType.values()[this.textureTypeList.getListSelectedIndex()])));
        } else {
            PacketHandler.networkWrapper.sendToServer((IMessage)new MessageClientGuiButton((byte)button.field_146127_k));
        }
    }

    @Override
    public void drawBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
        Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE);
        this.func_73729_b(this.x, this.y, 0, 0, this.width, this.height);
        this.func_73729_b(this.x + 7, this.y + 141, 7, 3, 162, 76);
        this.textUserSkin.func_146194_f();
        this.checkShowGuides.setIsChecked(this.tileEntity.isShowGuides());
        int checkY = 110;
        if (this.tileEntity.getSkinType() != null) {
            this.checkShowHelper.field_146125_m = this.tileEntity.getSkinType().showHelperCheckbox();
            this.checkShowHelper.field_146129_i = checkY;
        } else {
            this.checkShowHelper.field_146125_m = false;
        }
    }

    @Override
    public void drawForegroundLayer(int mouseX, int mouseY) {
        String labelSkinType = GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "label.skinType");
        String usernameLabel = GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "label.username");
        String urlLabel = GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "label.url");
        this.fontRenderer.func_78276_b(labelSkinType, 10, 20, 0x404040);
        if (this.textureTypeList.getListSelectedIndex() == 0) {
            this.fontRenderer.func_78276_b(usernameLabel, 10, 60, 0x404040);
        } else {
            this.fontRenderer.func_78276_b(urlLabel, 10, 60, 0x404040);
        }
        super.drawForegroundLayer(mouseX, mouseY);
        this.textureTypeList.drawForeground(this.mc, mouseX - this.x, mouseY - this.y, 0.0f);
    }

    @Override
    public void onDropDownListChanged(GuiDropDownList dropDownList) {
        this.textUserSkin.func_146180_a("");
    }
}

