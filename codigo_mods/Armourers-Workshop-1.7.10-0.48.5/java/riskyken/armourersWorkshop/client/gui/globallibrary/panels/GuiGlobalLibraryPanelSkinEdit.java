/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StringUtils
 *  org.apache.logging.log4j.Level
 */
package riskyken.armourersWorkshop.client.gui.globallibrary.panels;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.client.config.GuiButtonExt;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import org.apache.logging.log4j.Level;
import riskyken.armourersWorkshop.client.gui.AbstractGuiDialog;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiLabeledTextField;
import riskyken.armourersWorkshop.client.gui.controls.GuiPanel;
import riskyken.armourersWorkshop.client.gui.globallibrary.GuiGlobalLibrary;
import riskyken.armourersWorkshop.client.gui.globallibrary.dialog.GuiGlobalLibraryDialogDelete;
import riskyken.armourersWorkshop.common.library.global.GlobalSkinLibraryUtils;
import riskyken.armourersWorkshop.common.library.global.auth.PlushieAuth;
import riskyken.armourersWorkshop.common.library.global.auth.PlushieSession;
import riskyken.armourersWorkshop.utils.ModLogger;
import riskyken.armourersWorkshop.utils.TranslateUtils;

public class GuiGlobalLibraryPanelSkinEdit
extends GuiPanel
implements AbstractGuiDialog.IDialogCallback {
    private static final ResourceLocation BUTTON_TEXTURES = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/gui/globalLibrary.png");
    private final String guiName;
    private GuiLabeledTextField textName;
    private GuiLabeledTextField textTags;
    private GuiLabeledTextField textDescription;
    private GuiButtonExt buttonUpdate;
    private GuiButtonExt buttonDelete;
    private FutureTask<JsonObject> taskSkinEdit;
    private JsonObject skinJson = null;
    private GuiGlobalLibrary.Screen returnScreen;
    private boolean firstTick = false;

    public GuiGlobalLibraryPanelSkinEdit(GuiScreen parent, int x, int y, int width, int height) {
        super(parent, x, y, width, height);
        this.guiName = ((GuiGlobalLibrary)parent).getGuiName() + ".edit";
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
        if (this.skinJson != null) {
            if (this.skinJson.has("name")) {
                this.textName.func_146180_a(this.skinJson.get("name").getAsString());
            }
            if (this.skinJson.has("description")) {
                this.textDescription.func_146180_a(this.skinJson.get("description").getAsString());
            }
        }
        this.buttonUpdate = new GuiButtonExt(0, this.x + 5, this.y + this.height - 25, 100, 20, GuiHelper.getLocalizedControlName(this.guiName, "buttonUpdate"));
        this.buttonDelete = new GuiButtonExt(0, this.x + this.width - 105, this.y + this.height - 25, 100, 20, GuiHelper.getLocalizedControlName(this.guiName, "buttonDelete"));
        this.buttonList.add(this.buttonUpdate);
        this.buttonList.add(this.buttonDelete);
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
        super.update();
        this.buttonUpdate.field_146124_l = false;
        if (!StringUtils.func_151246_b((String)this.textName.func_146179_b())) {
            this.buttonUpdate.field_146124_l = true;
        }
        this.firstTick = false;
        if (this.taskSkinEdit != null && this.taskSkinEdit.isDone()) {
            try {
                JsonObject json = this.taskSkinEdit.get();
                this.taskSkinEdit = null;
                if (json != null) {
                    if (json.has("valid") & json.has("action")) {
                        String action = json.get("action").getAsString();
                        boolean valid = json.get("valid").getAsBoolean();
                        if (action.equals("user-skin-edit")) {
                            ((GuiGlobalLibrary)this.parent).panelHome.updateSkinPanels();
                            ((GuiGlobalLibrary)this.parent).switchScreen(this.returnScreen);
                        } else if (action.equals("user-skin-delete")) {
                            ((GuiGlobalLibrary)this.parent).switchScreen(this.returnScreen);
                        } else {
                            ModLogger.log(Level.WARN, "Server send unknown action: " + action);
                        }
                    } else {
                        ModLogger.log(Level.ERROR, "Server returned invalid responce.");
                    }
                } else {
                    ModLogger.log(Level.ERROR, "Server returned invalid responce.");
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
        if (!this.visible | !this.enabled) {
            return;
        }
        if (this.firstTick) {
            return;
        }
        if (button == this.buttonUpdate && this.skinJson != null && this.skinJson.has("id") && this.authenticateUser()) {
            this.updateSkin();
        }
        if (button == this.buttonDelete) {
            ((GuiGlobalLibrary)this.parent).openDialog(new GuiGlobalLibraryDialogDelete((GuiScreen)((GuiGlobalLibrary)this.parent), this.guiName + ".dialog.delete", this, 190, 100));
        }
    }

    public void displaySkinInfo(JsonObject jsonObject, GuiGlobalLibrary.Screen returnScreen) {
        this.skinJson = jsonObject;
        ((GuiGlobalLibrary)this.parent).switchScreen(GuiGlobalLibrary.Screen.SKIN_EDIT);
        this.returnScreen = returnScreen;
        this.firstTick = true;
    }

    private boolean authenticateUser() {
        GameProfile gameProfile = this.mc.field_71439_g.func_146103_bH();
        PlushieSession plushieSession = PlushieAuth.PLUSHIE_SESSION;
        if (!plushieSession.isAuthenticated()) {
            JsonObject jsonObject = PlushieAuth.updateAccessToken(gameProfile.getName(), gameProfile.getId().toString());
            plushieSession.authenticate(jsonObject);
        }
        if (!plushieSession.isAuthenticated()) {
            ModLogger.log(Level.ERROR, "Authentication failed.");
            return false;
        }
        return true;
    }

    public void updateSkin() {
        PlushieSession plushieSession = PlushieAuth.PLUSHIE_SESSION;
        GuiGlobalLibrary globalLibrary = (GuiGlobalLibrary)this.parent;
        int userId = plushieSession.getServerId();
        String accessToken = plushieSession.getAccessToken();
        int skinId = this.skinJson.get("id").getAsInt();
        String name = this.textName.func_146179_b().trim();
        String description = this.textDescription.func_146179_b().trim();
        this.taskSkinEdit = GlobalSkinLibraryUtils.editSkin(globalLibrary.uploadExecutor, userId, accessToken, skinId, name, description);
    }

    public void deleteSkin() {
        PlushieSession plushieSession = PlushieAuth.PLUSHIE_SESSION;
        GuiGlobalLibrary globalLibrary = (GuiGlobalLibrary)this.parent;
        int userId = plushieSession.getServerId();
        String accessToken = plushieSession.getAccessToken();
        int skinId = this.skinJson.get("id").getAsInt();
        this.taskSkinEdit = GlobalSkinLibraryUtils.deleteSkin(globalLibrary.jsonDownloadExecutor, userId, accessToken, skinId);
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTickTime) {
        if (!this.visible) {
            return;
        }
        this.func_73733_a(this.x, this.y, this.x + this.width, this.y + this.height, -1072689136, -804253680);
        super.draw(mouseX, mouseY, partialTickTime);
        this.fontRenderer.func_78276_b(GuiHelper.getLocalizedControlName(this.guiName, "name"), this.x + 5, this.y + 5, 0xFFFFFF);
        this.fontRenderer.func_78276_b(GuiHelper.getLocalizedControlName(this.guiName, "skinName"), this.x + 5, this.y + 25, 0xFFFFFF);
        this.textName.func_146194_f();
        this.fontRenderer.func_78276_b(GuiHelper.getLocalizedControlName(this.guiName, "skinTags"), this.x + 5, this.y + 55, 0xFFFFFF);
        this.textTags.func_146194_f();
        this.fontRenderer.func_78276_b(GuiHelper.getLocalizedControlName(this.guiName, "skinDescription"), this.x + 5, this.y + 85, 0xFFFFFF);
        this.textDescription.func_146194_f();
        this.fontRenderer.func_78276_b(this.textDescription.func_146179_b().length() + " / 255", this.x + 5, this.y + 115, 0xFFFFFF);
        int[] javaVersion = GlobalSkinLibraryUtils.getJavaVersion();
        if (!GlobalSkinLibraryUtils.isValidJavaVersion(javaVersion)) {
            this.fontRenderer.func_78279_b(TranslateUtils.translate("inventory.armourersworkshop:globalSkinLibrary.invalidJava", javaVersion[0], javaVersion[1]), this.x + 135, this.y + 65, this.width - 140, 0xFF8888);
        }
    }

    @Override
    public void dialogResult(AbstractGuiDialog dialog, AbstractGuiDialog.DialogResult result) {
        ModLogger.log((Object)result);
        if (result == AbstractGuiDialog.DialogResult.OK && this.skinJson != null && this.skinJson.has("id") && this.authenticateUser()) {
            this.deleteSkin();
        }
        ((GuiGlobalLibrary)this.parent).dialogResult(dialog, result);
    }
}

