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
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.armourer.tab;

import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.armourer.GuiArmourer;
import riskyken.armourersWorkshop.client.gui.controls.GuiDropDownList;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabPanel;
import riskyken.armourersWorkshop.client.lib.LibGuiResources;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiButton;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiSetArmourerSkinProps;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiSetArmourerSkinType;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientLoadArmour;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourer;

@SideOnly(value=Side.CLIENT)
public class GuiTabArmourerMain
extends GuiTabPanel
implements GuiDropDownList.IDropDownListCallback {
    private static final ResourceLocation TEXTURE = new ResourceLocation(LibGuiResources.ARMOURER);
    private final TileEntityArmourer tileEntity;
    private GuiDropDownList dropDownSkinType;
    private GuiTextField textItemName;
    private boolean resetting;
    int fidgCount = 0;
    String[] fidgMessage = new String[]{"STOP!", "STOP ALREADY", "I SAID STOP!"};

    public GuiTabArmourerMain(int tabId, GuiScreen parent) {
        super(tabId, parent, false);
        this.tileEntity = ((GuiArmourer)parent).tileEntity;
    }

    @Override
    public void initGui(int xPos, int yPos, int width, int height) {
        super.initGui(xPos, yPos, width, height);
        String guiName = this.tileEntity.func_145825_b();
        this.buttonList.clear();
        SkinTypeRegistry str = SkinTypeRegistry.INSTANCE;
        this.dropDownSkinType = new GuiDropDownList(0, 4, 20, 58, "", this);
        ArrayList<ISkinType> skinList = str.getRegisteredSkinTypes();
        int skinCount = 0;
        for (int i = 0; i < skinList.size(); ++i) {
            ISkinType skinType = skinList.get(i);
            if (!(!skinType.isHidden() & skinType != SkinTypeRegistry.skinOutfit)) continue;
            String skinLocalizedName = str.getLocalizedSkinTypeName(skinType);
            String skinRegistryName = skinType.getRegistryName();
            this.dropDownSkinType.addListItem(skinLocalizedName, skinRegistryName, skinType.enabled());
            if (skinType == this.tileEntity.getSkinType()) {
                this.dropDownSkinType.setListSelectedIndex(skinCount);
            }
            ++skinCount;
        }
        this.buttonList.add(this.dropDownSkinType);
        this.buttonList.add(new GuiButtonExt(13, 86, 16, 50, 12, GuiHelper.getLocalizedControlName(guiName, "save")));
        this.buttonList.add(new GuiButtonExt(14, 86, 29, 50, 12, GuiHelper.getLocalizedControlName(guiName, "load")));
        this.textItemName = new GuiTextField(this.fontRenderer, this.x + 64, this.y + 58, 103, 16);
        this.textItemName.func_146203_f(40);
        this.textItemName.func_146180_a(this.tileEntity.getSkinProps().getPropertyString("customName", ""));
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        super.mouseClicked(mouseX, mouseY, button);
        this.textItemName.func_146192_a(mouseX, mouseY, button);
        if (button == 1 && this.textItemName.func_146206_l()) {
            this.textItemName.func_146180_a("");
        }
    }

    @Override
    public boolean keyTyped(char c, int keycode) {
        String oldText;
        if (!this.textItemName.func_146201_a(c, keycode)) {
            return super.keyTyped(c, keycode);
        }
        SkinProperties skinProps = this.tileEntity.getSkinProps();
        String sendText = this.textItemName.func_146179_b().trim();
        if (this.fidgCount < 3 && sendText.equalsIgnoreCase("fidget spinner")) {
            sendText = this.fidgMessage[this.fidgCount];
            ++this.fidgCount;
        }
        if (!sendText.equals(oldText = skinProps.getPropertyString("customName", ""))) {
            skinProps.setProperty("customName", sendText);
            PacketHandler.networkWrapper.sendToServer((IMessage)new MessageClientGuiSetArmourerSkinProps(skinProps));
            return true;
        }
        return false;
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.field_146127_k) {
            case 13: {
                PacketHandler.networkWrapper.sendToServer((IMessage)new MessageClientLoadArmour(this.textItemName.func_146179_b().trim(), ""));
                break;
            }
            default: {
                if (button.field_146127_k == 14) {
                    // empty if block
                }
                PacketHandler.networkWrapper.sendToServer((IMessage)new MessageClientGuiButton((byte)button.field_146127_k));
            }
        }
    }

    @Override
    public void drawBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.func_71410_x().func_110434_K().func_110577_a(TEXTURE);
        this.func_73729_b(this.x, this.y, 0, 0, this.width, this.height);
        this.func_73729_b(this.x + 63, this.y + 20, 238, 0, 18, 18);
        this.func_73729_b(this.x + 142, this.y + 16, 230, 18, 26, 26);
        this.textItemName.func_146194_f();
    }

    public void resetValues(SkinProperties skinProperties) {
        this.resetting = true;
        String newText = skinProperties.getPropertyString("customName", "");
        if (!this.textItemName.func_146179_b().startsWith(newText)) {
            int cur = this.textItemName.func_146198_h();
            this.textItemName.func_146180_a(newText);
            this.textItemName.func_146190_e(cur);
        }
        this.resetting = false;
    }

    @Override
    public void drawForegroundLayer(int mouseX, int mouseY) {
        super.drawForegroundLayer(mouseX, mouseY);
        this.fontRenderer.func_78276_b(I18n.func_135052_a((String)"container.inventory", (Object[])new Object[0]), 8, this.height - 96 + 2, 0x404040);
        String itemNameLabel = GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "label.itemName");
        String cloneLabel = GuiHelper.getLocalizedControlName(this.tileEntity.func_145825_b(), "label.clone");
        String versionLabel = "Beta: 1.7.10-0.48.5";
        this.fontRenderer.func_78276_b(itemNameLabel, 64, 48, 0x404040);
        int versionWidth = this.fontRenderer.func_78256_a(versionLabel);
        this.fontRenderer.func_78276_b(versionLabel, this.width - versionWidth - 7, this.height - 96 + 2, 0x404040);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.dropDownSkinType.drawForeground(this.mc, mouseX - this.x, mouseY - this.y, 0.0f);
    }

    @Override
    public void onDropDownListChanged(GuiDropDownList dropDownList) {
        GuiDropDownList.DropDownListItem listItem = dropDownList.getListSelectedItem();
        ISkinType skinType = SkinTypeRegistry.INSTANCE.getSkinTypeFromRegistryName(listItem.tag);
        ((GuiArmourer)this.parent).skinTypeUpdate(skinType);
        PacketHandler.networkWrapper.sendToServer((IMessage)new MessageClientGuiSetArmourerSkinType(skinType));
    }
}

