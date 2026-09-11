/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.ResourceLocation
 */
package riskyken.armourersWorkshop.client.gui.globallibrary.panels;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiIconButton;
import riskyken.armourersWorkshop.client.gui.controls.GuiPanel;
import riskyken.armourersWorkshop.client.gui.globallibrary.GuiGlobalLibrary;
import riskyken.armourersWorkshop.common.library.global.auth.PlushieAuth;
import riskyken.armourersWorkshop.common.library.global.auth.PlushieSession;
import riskyken.armourersWorkshop.common.library.global.permission.PermissionSystem;

@SideOnly(value=Side.CLIENT)
public class GuiGlobalLibraryPanelHeader
extends GuiPanel {
    private static final ResourceLocation BUTTON_TEXTURES = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/gui/globalLibrary.png");
    private GuiIconButton iconButtonHome;
    private GuiIconButton iconButtonFavourites;
    private GuiIconButton iconButtonMyFiles;
    private GuiIconButton iconButtonUploadSkin;
    private GuiIconButton iconButtonJoinBeta;

    public GuiGlobalLibraryPanelHeader(GuiScreen parent, int x, int y, int width, int height) {
        super(parent, x, y, width, height);
    }

    @Override
    public void initGui() {
        super.initGui();
        String guiName = ((GuiGlobalLibrary)this.parent).getGuiName();
        this.buttonList.clear();
        this.iconButtonHome = new GuiIconButton(this.parent, 0, this.x + this.width - 21, this.y + 4, 18, 18, GuiHelper.getLocalizedControlName(guiName, "header.home"), BUTTON_TEXTURES).setIconLocation(0, 0, 16, 16);
        this.iconButtonMyFiles = new GuiIconButton(this.parent, 2, this.x + this.width - 41, this.y + 4, 18, 18, GuiHelper.getLocalizedControlName(guiName, "header.myFiles"), BUTTON_TEXTURES).setIconLocation(0, 34, 16, 16);
        this.iconButtonUploadSkin = new GuiIconButton(this.parent, 3, this.x + this.width - 61, this.y + 4, 18, 18, GuiHelper.getLocalizedControlName(guiName, "header.uploadSkin"), BUTTON_TEXTURES).setIconLocation(0, 51, 16, 16);
        this.iconButtonJoinBeta = new GuiIconButton(this.parent, 4, this.x + this.width - 41, this.y + 4, 18, 18, GuiHelper.getLocalizedControlName(guiName, "header.joinBeta"), BUTTON_TEXTURES).setIconLocation(0, 68, 16, 16);
        this.iconButtonUploadSkin.setDisableText(GuiHelper.getLocalizedControlName(guiName, "header.uploadSkinBan"));
        this.buttonList.add(this.iconButtonHome);
        this.buttonList.add(this.iconButtonMyFiles);
        this.buttonList.add(this.iconButtonUploadSkin);
        this.buttonList.add(this.iconButtonJoinBeta);
        this.betaCheckUpdate();
    }

    @Override
    public void update() {
        super.update();
        this.betaCheckUpdate();
    }

    private void betaCheckUpdate() {
        boolean inBeta = PlushieAuth.isRemoteUser();
        boolean doneBetaCheck = PlushieAuth.doneRemoteUserCheck();
        PlushieSession session = PlushieAuth.PLUSHIE_SESSION;
        if (session.hasServerId()) {
            this.iconButtonMyFiles.field_146125_m = inBeta;
            this.iconButtonUploadSkin.field_146125_m = inBeta;
            this.iconButtonUploadSkin.field_146124_l = session.hasPermission(PermissionSystem.PlushieAction.SKIN_UPLOAD);
        } else {
            this.iconButtonMyFiles.field_146125_m = false;
            this.iconButtonUploadSkin.field_146125_m = false;
        }
        this.iconButtonJoinBeta.field_146125_m = doneBetaCheck ? !inBeta : false;
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == this.iconButtonHome) {
            ((GuiGlobalLibrary)this.parent).switchScreen(GuiGlobalLibrary.Screen.HOME);
            ((GuiGlobalLibrary)this.parent).panelHome.updateSkinPanels();
        }
        if (button == this.iconButtonFavourites) {
            ((GuiGlobalLibrary)this.parent).switchScreen(GuiGlobalLibrary.Screen.FAVOURITES);
        }
        if (button == this.iconButtonMyFiles) {
            GuiGlobalLibrary guiGlobalLibrary = (GuiGlobalLibrary)this.parent;
            int serverId = PlushieAuth.PLUSHIE_SESSION.getServerId();
            ((GuiGlobalLibrary)this.parent).panelUserSkins.clearResults();
            ((GuiGlobalLibrary)this.parent).switchScreen(GuiGlobalLibrary.Screen.USER_SKINS);
            ((GuiGlobalLibrary)this.parent).panelUserSkins.switchToUser(serverId);
        }
        if (button == this.iconButtonUploadSkin) {
            ((GuiGlobalLibrary)this.parent).switchScreen(GuiGlobalLibrary.Screen.UPLOAD);
        }
        if (button == this.iconButtonJoinBeta) {
            ((GuiGlobalLibrary)this.parent).switchScreen(GuiGlobalLibrary.Screen.JOIN_BETA);
        }
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTickTime) {
        if (!this.visible) {
            return;
        }
        this.func_73733_a(this.x, this.y, this.x + this.width, this.y + this.height, -1072689136, -804253680);
        super.draw(mouseX, mouseY, partialTickTime);
        String username = "player";
        GameProfile gameProfile = this.mc.field_71439_g.func_146103_bH();
        if (gameProfile != null) {
            username = gameProfile.getName();
            GuiHelper.drawPlayerHead(this.x + 4, this.y + 4, 16, username);
            this.fontRenderer.func_78276_b(" - " + username, this.x + 24, this.y + this.height / 2 - this.fontRenderer.field_78288_b / 2, 0xAAFFAA);
        } else {
            this.fontRenderer.func_78276_b("Not logged in.", this.x + 90, this.y + this.height / 2 - this.fontRenderer.field_78288_b / 2, 0xFFAAAA);
        }
        String titleText = ((GuiGlobalLibrary)this.parent).tileEntity.func_145838_q().func_149732_F();
        this.func_73732_a(this.fontRenderer, titleText, this.x + this.width / 2, this.y + this.height / 2 - this.fontRenderer.field_78288_b / 2, -1118482);
    }

    private void drawPlayerHead(String username) {
        ResourceLocation rl = AbstractClientPlayer.field_110314_b;
        rl = AbstractClientPlayer.func_110311_f((String)username);
        AbstractClientPlayer.func_110304_a((ResourceLocation)rl, (String)username);
        this.mc.field_71446_o.func_110577_a(rl);
        int sourceSize = 8;
        int targetSize = 16;
        GuiGlobalLibraryPanelHeader.func_152125_a((int)(this.x + 5), (int)(this.y + 5), (float)8.0f, (float)8.0f, (int)sourceSize, (int)sourceSize, (int)targetSize, (int)targetSize, (float)64.0f, (float)32.0f);
        GuiGlobalLibraryPanelHeader.func_152125_a((int)(this.x + 4), (int)(this.y + 4), (float)40.0f, (float)8.0f, (int)sourceSize, (int)sourceSize, (int)(targetSize + 2), (int)(targetSize + 2), (float)64.0f, (float)32.0f);
    }
}

