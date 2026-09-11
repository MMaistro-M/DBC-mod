/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.StringUtils
 */
package riskyken.armourersWorkshop.client.gui.globallibrary.panels;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.UUID;
import java.util.concurrent.FutureTask;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StringUtils;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiLabeledTextField;
import riskyken.armourersWorkshop.client.gui.controls.GuiPanel;
import riskyken.armourersWorkshop.client.gui.globallibrary.GuiGlobalLibrary;
import riskyken.armourersWorkshop.common.library.global.GlobalSkinLibraryUtils;
import riskyken.armourersWorkshop.common.library.global.auth.PlushieAuth;
import riskyken.armourersWorkshop.utils.TranslateUtils;

@SideOnly(value=Side.CLIENT)
public class GuiGlobalLibraryPaneJoinBeta
extends GuiPanel {
    private final String guiName;
    private GuiLabeledTextField textBetaCode;
    private GuiButtonExt buttonCheckBetaCode;
    private final String STATE_CHECK_CODE = "checkCode";
    private final String STATE_CODE_INVALID = "codeInvalid";
    private final String STATE_JOINING_BETA = "joiningBeta";
    private final String STATE_JOIN_FAILED = "joinedFailed";
    private final String STATE_JOINED_BETA = "joinedBeta";
    private FutureTask<JsonObject> taskBetaJoinJson = null;
    private boolean joining = false;
    private String joinState = "";
    private String joinFailMessage = null;

    public GuiGlobalLibraryPaneJoinBeta(GuiScreen parent, int x, int y, int width, int height) {
        super(parent, x, y, width, height);
        this.guiName = ((GuiGlobalLibrary)parent).getGuiName() + ".joinBeta";
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        this.textBetaCode = new GuiLabeledTextField(this.fontRenderer, this.x + 5, this.y + 35, 230, 12);
        this.textBetaCode.setEmptyLabel(GuiHelper.getLocalizedControlName(this.guiName, "enterBetaCode"));
        this.textBetaCode.func_146203_f(36);
        this.buttonCheckBetaCode = new GuiButtonExt(0, this.x + 5, this.y + 50, 230, 20, GuiHelper.getLocalizedControlName(this.guiName, "buttonJoinBeta"));
        this.buttonCheckBetaCode.field_146124_l = false;
        this.buttonList.add(this.buttonCheckBetaCode);
    }

    @Override
    public void update() {
        super.update();
        if (this.taskBetaJoinJson != null && this.taskBetaJoinJson.isDone()) {
            try {
                JsonObject jsonObject = this.taskBetaJoinJson.get();
                this.taskBetaJoinJson = null;
                if (jsonObject.has("action") & jsonObject.has("valid")) {
                    String action = jsonObject.get("action").getAsString();
                    boolean valid = jsonObject.get("valid").getAsBoolean();
                    if (action.equals("beta-code-check")) {
                        if (valid) {
                            GameProfile gameProfile = this.mc.field_71439_g.func_146103_bH();
                            this.joinState = "joiningBeta";
                            this.taskBetaJoinJson = PlushieAuth.joinBeta(gameProfile.getName(), gameProfile.getId().toString(), this.textBetaCode.func_146179_b());
                        } else {
                            this.invalidBetaCode();
                        }
                    } else if (action.equals("beta-join")) {
                        if (valid) {
                            this.joinedBeta(jsonObject);
                        } else {
                            String reason = null;
                            if (jsonObject.has("reason")) {
                                reason = jsonObject.get("reason").getAsString();
                            }
                            this.joinedBetaFailed(reason);
                        }
                    }
                }
            }
            catch (Exception e) {
                this.taskBetaJoinJson = null;
                e.printStackTrace();
            }
        }
    }

    private void invalidBetaCode() {
        this.joining = false;
        this.joinState = "codeInvalid";
    }

    private void joinedBetaFailed(String reason) {
        this.joining = false;
        this.joinState = "joinedFailed";
        this.joinFailMessage = reason;
    }

    private void joinedBeta(JsonObject jsonObject) {
        this.joining = false;
        this.joinState = "joinedBeta";
        PlushieAuth.doRemoteUserCheck();
        PlushieAuth.PLUSHIE_SESSION.authenticate(jsonObject);
        ((GuiGlobalLibrary)this.parent).switchScreen(GuiGlobalLibrary.Screen.HOME);
    }

    @Override
    public boolean keyTyped(char c, int keycode) {
        if (!this.visible | !this.enabled) {
            return false;
        }
        if (this.textBetaCode.func_146201_a(c, keycode)) {
            boolean bl = this.buttonCheckBetaCode.field_146124_l = this.textBetaCode.func_146179_b().length() == 36;
            if (!GlobalSkinLibraryUtils.isValidJavaVersion()) {
                this.buttonCheckBetaCode.field_146124_l = false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseClicked(int mouseX, int mouseY, int button) {
        if (!this.visible | !this.enabled) {
            return false;
        }
        super.mouseClicked(mouseX, mouseY, button);
        this.textBetaCode.func_146192_a(mouseX, mouseY, button);
        if (button == 1 && this.textBetaCode.func_146206_l()) {
            this.textBetaCode.func_146180_a("");
        }
        return false;
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == this.buttonCheckBetaCode && this.textBetaCode.func_146179_b().length() == 36) {
            try {
                UUID uuid = UUID.fromString(this.textBetaCode.func_146179_b());
                this.joining = true;
                this.joinState = "checkCode";
                this.taskBetaJoinJson = PlushieAuth.checkBetaCode(uuid);
            }
            catch (IllegalArgumentException e) {
                this.invalidBetaCode();
            }
        }
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTickTime) {
        int[] javaVersion;
        if (!this.visible) {
            return;
        }
        this.func_73733_a(this.x, this.y, this.x + this.width, this.y + this.height, -1072689136, -804253680);
        super.draw(mouseX, mouseY, partialTickTime);
        this.fontRenderer.func_78276_b(GuiHelper.getLocalizedControlName(this.guiName, "name"), this.x + 5, this.y + 5, 0xFFFFFF);
        this.fontRenderer.func_78276_b(GuiHelper.getLocalizedControlName(this.guiName, "betaCode"), this.x + 5, this.y + 25, 0xFFFFFF);
        this.textBetaCode.func_146194_f();
        this.fontRenderer.func_78279_b(GuiHelper.getLocalizedControlName(this.guiName, "closedBeta"), this.x + 5, this.y + 75, 230, 0xEEEEEE);
        if (this.joinState != null && !StringUtils.func_151246_b((String)this.joinState)) {
            this.fontRenderer.func_78279_b(GuiHelper.getLocalizedControlName(this.guiName, this.joinState), this.x + 5, this.y + 115, 230, 0xEEEEEE);
        }
        if (this.joinFailMessage != null) {
            this.fontRenderer.func_78279_b(this.joinFailMessage, this.x + 5, this.y + 140, 230, 0xFF8888);
        }
        if (!GlobalSkinLibraryUtils.isValidJavaVersion(javaVersion = GlobalSkinLibraryUtils.getJavaVersion())) {
            this.fontRenderer.func_78279_b(TranslateUtils.translate("inventory.armourersworkshop:globalSkinLibrary.invalidJava", javaVersion[0], javaVersion[1]), this.x + 5, this.y + 160, 230, 0xFF8888);
        }
    }
}

