/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StringUtils
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.client.gui.globallibrary.panels;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiLabeledTextField;
import riskyken.armourersWorkshop.client.gui.controls.GuiPanel;
import riskyken.armourersWorkshop.client.gui.globallibrary.GuiGlobalLibrary;
import riskyken.armourersWorkshop.common.inventory.slot.SlotHidable;
import riskyken.armourersWorkshop.common.library.global.GlobalSkinLibraryUtils;
import riskyken.armourersWorkshop.common.library.global.SkinUploader;
import riskyken.armourersWorkshop.common.library.global.auth.PlushieAuth;
import riskyken.armourersWorkshop.common.library.global.auth.PlushieSession;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiButton;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.utils.ModLogger;
import riskyken.armourersWorkshop.utils.SkinIOUtils;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;
import riskyken.armourersWorkshop.utils.TranslateUtils;

@SideOnly(value=Side.CLIENT)
public class GuiGlobalLibraryPanelUpload
extends GuiPanel {
    private static final ResourceLocation BUTTON_TEXTURES = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/gui/globalLibrary.png");
    private final String guiName;
    private GuiLabeledTextField textName;
    private GuiLabeledTextField textTags;
    private GuiLabeledTextField textDescription;
    private GuiButtonExt buttonUpload;
    private FutureTask<JsonObject> taskSkinUpload;
    private String error = null;

    public GuiGlobalLibraryPanelUpload(GuiScreen parent, int x, int y, int width, int height) {
        super(parent, x, y, width, height);
        this.guiName = ((GuiGlobalLibrary)parent).getGuiName() + ".upload";
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        this.textName = new GuiLabeledTextField(this.fontRenderer, this.x + 5, this.y + 35, 180, 12);
        this.textName.setEmptyLabel(GuiHelper.getLocalizedControlName(this.guiName, "enterName"));
        this.textName.func_146203_f(80);
        this.textTags = new GuiLabeledTextField(this.fontRenderer, this.x + 5, this.y + 65, 180, 12);
        this.textTags.setEmptyLabel(GuiHelper.getLocalizedControlName(this.guiName, "enterTags"));
        this.textDescription = new GuiLabeledTextField(this.fontRenderer, this.x + 5, this.y + 95, this.width - 10, 12);
        this.textDescription.setEmptyLabel(GuiHelper.getLocalizedControlName(this.guiName, "enterDescription"));
        this.textDescription.func_146203_f(255);
        this.buttonUpload = new GuiButtonExt(0, this.x + 5, this.y + 110, 100, 20, GuiHelper.getLocalizedControlName(this.guiName, "buttonUpload"));
        this.buttonUpload.field_146124_l = false;
        this.buttonList.add(this.buttonUpload);
        if (this.visible) {
            this.updatePlayerSlots();
        }
    }

    @Override
    public GuiPanel setVisible(boolean visible) {
        if (visible) {
            this.updatePlayerSlots();
        }
        return super.setVisible(visible);
    }

    private void updatePlayerSlots() {
        ((GuiGlobalLibrary)this.parent).setPlayerSlotLocation(this.x + this.width / 2 - 81, this.y + this.height - 81);
        ((GuiGlobalLibrary)this.parent).setInputSlotLocation(this.x + 6, this.y + 140);
        ((GuiGlobalLibrary)this.parent).setOutputSlotLocation(this.x + 83, this.y + 140);
    }

    @Override
    public boolean keyTyped(char c, int keycode) {
        if (!this.visible | !this.enabled) {
            return false;
        }
        if (this.textName.func_146201_a(c, keycode)) {
            return true;
        }
        if (this.textTags.func_146201_a(c, keycode)) {
            return true;
        }
        return this.textDescription.func_146201_a(c, keycode);
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int button) {
        if (!this.visible | !this.enabled) {
            return false;
        }
        super.mouseClicked(mouseX, mouseY, button);
        this.textName.func_146192_a(mouseX, mouseY, button);
        this.textTags.func_146192_a(mouseX, mouseY, button);
        this.textDescription.func_146192_a(mouseX, mouseY, button);
        if (button == 1) {
            if (this.textName.func_146206_l()) {
                this.textName.func_146180_a("");
            }
            if (this.textTags.func_146206_l()) {
                this.textTags.func_146180_a("");
            }
            if (this.textDescription.func_146206_l()) {
                this.textDescription.func_146180_a("");
            }
        }
        return false;
    }

    @Override
    public void update() {
        SlotHidable slot;
        super.update();
        this.buttonUpload.field_146124_l = false;
        if (!StringUtils.func_151246_b((String)this.textName.func_146179_b()) && SkinNBTHelper.stackHasSkinData((slot = ((GuiGlobalLibrary)this.parent).getInputSlot()).func_75211_c())) {
            this.buttonUpload.field_146124_l = true;
        }
        if (this.taskSkinUpload != null && this.taskSkinUpload.isDone()) {
            try {
                JsonObject json = this.taskSkinUpload.get();
                this.taskSkinUpload = null;
                if (json != null) {
                    if (json.has("valid") & json.has("action")) {
                        String action = json.get("action").getAsString();
                        boolean valid = json.get("valid").getAsBoolean();
                        if (valid & action.equals("skin-upload")) {
                            ((GuiGlobalLibrary)this.parent).panelHome.updateSkinPanels();
                            ((GuiGlobalLibrary)this.parent).switchScreen(GuiGlobalLibrary.Screen.HOME);
                        }
                    } else if (json.has("reason")) {
                        String reason;
                        this.error = reason = json.get("reason").getAsString();
                    }
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == this.buttonUpload) {
            GameProfile gameProfile = this.mc.field_71439_g.func_146103_bH();
            PlushieSession plushieSession = PlushieAuth.PLUSHIE_SESSION;
            if (!plushieSession.isAuthenticated()) {
                JsonObject jsonObject = PlushieAuth.updateAccessToken(gameProfile.getName(), gameProfile.getId().toString());
                plushieSession.authenticate(jsonObject);
            }
            if (!plushieSession.isAuthenticated()) {
                ModLogger.log(Level.ERROR, "Authentication failed.");
                return;
            }
            MessageClientGuiButton message = new MessageClientGuiButton(0);
            PacketHandler.networkWrapper.sendToServer((IMessage)message);
        }
    }

    public void uploadSkin(Skin skin) {
        GameProfile gameProfile = this.mc.field_71439_g.func_146103_bH();
        PlushieSession plushieSession = PlushieAuth.PLUSHIE_SESSION;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        SkinIOUtils.saveSkinToStream(outputStream, skin);
        byte[] fileBytes = outputStream.toByteArray();
        IOUtils.closeQuietly((OutputStream)outputStream);
        this.taskSkinUpload = SkinUploader.uploadSkin(fileBytes, this.textName.func_146179_b().trim(), Integer.toString(plushieSession.getServerId()), this.textDescription.func_146179_b().trim(), plushieSession.getAccessToken());
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTickTime) {
        int[] javaVersion;
        if (!this.visible) {
            return;
        }
        this.func_73733_a(this.x, this.y, this.x + this.width, this.y + this.height, -1072689136, -804253680);
        this.mc.field_71446_o.func_110577_a(BUTTON_TEXTURES);
        this.func_73729_b(this.x + this.width / 2 - 81 - 1, this.y + this.height - 82, 0, 180, 162, 76);
        this.func_73729_b(this.x + 5, this.y + 139, 0, 162, 18, 18);
        this.func_73729_b(this.x + 78, this.y + 135, 18, 154, 26, 26);
        super.draw(mouseX, mouseY, partialTickTime);
        this.fontRenderer.func_78276_b(GuiHelper.getLocalizedControlName(this.guiName, "name"), this.x + 5, this.y + 5, 0xFFFFFF);
        this.fontRenderer.func_78276_b(GuiHelper.getLocalizedControlName(this.guiName, "skinName"), this.x + 5, this.y + 25, 0xFFFFFF);
        this.textName.func_146194_f();
        this.fontRenderer.func_78276_b(GuiHelper.getLocalizedControlName(this.guiName, "skinTags"), this.x + 5, this.y + 55, 0xFFFFFF);
        this.textTags.func_146194_f();
        this.fontRenderer.func_78276_b(GuiHelper.getLocalizedControlName(this.guiName, "skinDescription"), this.x + 5, this.y + 85, 0xFFFFFF);
        this.textDescription.func_146194_f();
        this.fontRenderer.func_78279_b(GuiHelper.getLocalizedControlName(this.guiName, "closedBetaWarning"), this.x + 195, this.y + 35, this.width - 200, 0xFF8888);
        if (!StringUtils.func_151246_b((String)this.error)) {
            this.fontRenderer.func_78279_b("Error: " + this.error, this.x + 195, this.y + 115, this.width - 200, 0xFF8888);
        }
        if (!GlobalSkinLibraryUtils.isValidJavaVersion(javaVersion = GlobalSkinLibraryUtils.getJavaVersion())) {
            this.fontRenderer.func_78279_b(TranslateUtils.translate("inventory.armourersworkshop:globalSkinLibrary.invalidJava", javaVersion[0], javaVersion[1]), this.x + 135, this.y + 65, this.width - 140, 0xFF8888);
        }
    }
}

