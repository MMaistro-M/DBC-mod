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
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.apache.logging.log4j.Level
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.globallibrary.panels;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.File;
import java.util.concurrent.FutureTask;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.apache.logging.log4j.Level;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiIconButton;
import riskyken.armourersWorkshop.client.gui.controls.GuiPanel;
import riskyken.armourersWorkshop.client.gui.globallibrary.GuiGlobalLibrary;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.client.render.SkinItemRenderHelper;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.common.library.ILibraryManager;
import riskyken.armourersWorkshop.common.library.global.DownloadUtils;
import riskyken.armourersWorkshop.common.library.global.GlobalSkinLibraryUtils;
import riskyken.armourersWorkshop.common.library.global.PlushieUser;
import riskyken.armourersWorkshop.common.library.global.SkinDownloader;
import riskyken.armourersWorkshop.common.library.global.auth.PlushieAuth;
import riskyken.armourersWorkshop.common.library.global.auth.PlushieSession;
import riskyken.armourersWorkshop.common.library.global.permission.PermissionSystem;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinIdentifier;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.utils.ModLogger;
import riskyken.armourersWorkshop.utils.SkinIOUtils;

@SideOnly(value=Side.CLIENT)
public class GuiGlobalLibraryPanelSkinInfo
extends GuiPanel {
    private static final ResourceLocation BUTTON_TEXTURES = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/gui/globalLibrary.png");
    private static final String BASE_URL = "https://plushie.moe/armourers_workshop/";
    private static final String SKIN_ACTION_URL = "https://plushie.moe/armourers_workshop/user-skin-action.php";
    private GuiButtonExt buttonBack;
    private GuiButtonExt buttonDownload;
    private GuiButtonExt buttonUserSkins;
    private GuiButtonExt buttonEditSkin;
    private GuiIconButton buttonLikeSkin;
    private GuiIconButton buttonUnlikeSkin;
    private final String guiName;
    private JsonObject skinJson = null;
    private GuiGlobalLibrary.Screen returnScreen;
    private boolean doneLikeCheck = false;
    private boolean haveLiked = false;
    private FutureTask<JsonObject> taskCheckIfLiked;
    private FutureTask<JsonObject> taskDoLiked;

    public GuiGlobalLibraryPanelSkinInfo(GuiScreen parent, int x, int y, int width, int height) {
        super(parent, x, y, width, height);
        this.guiName = ((GuiGlobalLibrary)parent).getGuiName() + ".skinInfo";
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.clear();
        int panelCenter = this.x + this.width / 2;
        this.buttonBack = new GuiButtonExt(0, panelCenter + 25, this.y + this.height - 25, 80, 20, GuiHelper.getLocalizedControlName(this.guiName, "back"));
        this.buttonDownload = new GuiButtonExt(0, panelCenter - 105, this.y + this.height - 25, 80, 20, GuiHelper.getLocalizedControlName(this.guiName, "downloadSkin"));
        this.buttonUserSkins = new GuiButtonExt(0, this.x + 6, this.y + 6, 26, 26, "");
        this.buttonEditSkin = new GuiButtonExt(0, this.x + 6, this.y + this.height - 25, 80, 20, GuiHelper.getLocalizedControlName(this.guiName, "editSkin"));
        this.buttonLikeSkin = new GuiIconButton(this.parent, 0, this.x + 200, this.y + 10, 20, 20, GuiHelper.getLocalizedControlName(this.guiName, "like"), BUTTON_TEXTURES);
        this.buttonLikeSkin.setIconLocation(102, 0, 16, 16);
        this.buttonUnlikeSkin = new GuiIconButton(this.parent, 0, this.x + 200, this.y + 10, 20, 20, GuiHelper.getLocalizedControlName(this.guiName, "unlike"), BUTTON_TEXTURES);
        this.buttonUnlikeSkin.setIconLocation(102, 17, 16, 16);
        this.updateLikeButtons();
        this.buttonList.add(this.buttonBack);
        this.buttonList.add(this.buttonDownload);
        this.buttonList.add(this.buttonUserSkins);
        this.buttonList.add(this.buttonEditSkin);
        this.buttonList.add(this.buttonLikeSkin);
        this.buttonList.add(this.buttonUnlikeSkin);
    }

    @Override
    public void update() {
        JsonObject json;
        this.buttonEditSkin.field_146125_m = false;
        if (PlushieAuth.isRemoteUser() && this.skinJson != null && this.skinJson.has("user_id")) {
            boolean bl = this.buttonEditSkin.field_146125_m = this.skinJson.get("user_id").getAsInt() == PlushieAuth.PLUSHIE_SESSION.getServerId();
            if (PlushieAuth.PLUSHIE_SESSION.hasPermission(PermissionSystem.PlushieAction.SKIN_MOD_EDIT)) {
                this.buttonEditSkin.field_146125_m = true;
            }
        }
        if (this.taskCheckIfLiked != null && this.taskCheckIfLiked.isDone()) {
            try {
                json = this.taskCheckIfLiked.get();
                ModLogger.log("taskCheckIfLiked: " + json);
                if (json != null && json.has("isLiked")) {
                    this.haveLiked = json.get("isLiked").getAsBoolean();
                    this.doneLikeCheck = true;
                    this.updateLikeButtons();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            this.taskCheckIfLiked = null;
        }
        if (this.taskDoLiked != null && this.taskDoLiked.isDone()) {
            try {
                json = this.taskDoLiked.get();
                ModLogger.log("taskDoLiked: " + json);
                if (json != null && json.has("valid") && json.get("valid").getAsBoolean()) {
                    if (this.skinJson != null && this.skinJson.has("likes")) {
                        if (this.haveLiked) {
                            this.skinJson.addProperty("likes", (Number)(this.skinJson.get("likes").getAsInt() - 1));
                        } else {
                            this.skinJson.addProperty("likes", (Number)(this.skinJson.get("likes").getAsInt() + 1));
                        }
                    }
                    this.haveLiked = !this.haveLiked;
                    this.updateLikeButtons();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            this.taskDoLiked = null;
        }
    }

    private void updateLikeButtons() {
        this.buttonLikeSkin.field_146125_m = false;
        this.buttonUnlikeSkin.field_146125_m = false;
        this.buttonLikeSkin.field_146124_l = true;
        this.buttonUnlikeSkin.field_146124_l = true;
        if (this.doneLikeCheck) {
            this.buttonLikeSkin.field_146125_m = !this.haveLiked;
            this.buttonUnlikeSkin.field_146125_m = this.haveLiked;
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        int userId;
        PlushieUser plushieUser;
        if (button == this.buttonBack) {
            ((GuiGlobalLibrary)this.parent).switchScreen(this.returnScreen);
        }
        if (button == this.buttonDownload && this.skinJson != null) {
            this.buttonDownload.field_146124_l = false;
            new DownloadSkin(this.skinJson);
        }
        if (button == this.buttonUserSkins && this.skinJson != null && this.skinJson.has("user_id") && (plushieUser = GlobalSkinLibraryUtils.getUserInfo(userId = this.skinJson.get("user_id").getAsInt())) != null) {
            ((GuiGlobalLibrary)this.parent).panelUserSkins.clearResults();
            ((GuiGlobalLibrary)this.parent).switchScreen(GuiGlobalLibrary.Screen.USER_SKINS);
            ((GuiGlobalLibrary)this.parent).panelUserSkins.switchToUser(userId);
        }
        if (button == this.buttonEditSkin && this.skinJson != null) {
            ((GuiGlobalLibrary)this.parent).panelSkinEdit.displaySkinInfo(this.skinJson, this.returnScreen);
        }
        if (button == this.buttonLikeSkin) {
            this.setSkinLike(true);
            this.buttonLikeSkin.field_146124_l = false;
        }
        if (button == this.buttonUnlikeSkin) {
            this.setSkinLike(false);
            this.buttonUnlikeSkin.field_146124_l = false;
        }
    }

    public void displaySkinInfo(JsonObject jsonObject, GuiGlobalLibrary.Screen returnScreen) {
        this.skinJson = jsonObject;
        ((GuiGlobalLibrary)this.parent).switchScreen(GuiGlobalLibrary.Screen.SKIN_INFO);
        this.returnScreen = returnScreen;
        this.doneLikeCheck = false;
        if (PlushieAuth.isRemoteUser() & this.skinJson != null) {
            this.checkIfLiked();
        }
    }

    private void checkIfLiked() {
        PlushieSession plushieSession = PlushieAuth.PLUSHIE_SESSION;
        GuiGlobalLibrary globalLibrary = (GuiGlobalLibrary)this.parent;
        int userId = plushieSession.getServerId();
        String accessToken = "";
        int skinId = this.skinJson.get("id").getAsInt();
        String url = SKIN_ACTION_URL;
        url = url + "?userId=" + String.valueOf(userId);
        url = url + "&accessToken=" + accessToken;
        url = url + "&action=hasLike";
        url = url + "&skinId=" + String.valueOf(skinId);
        this.taskCheckIfLiked = new FutureTask<JsonObject>(new DownloadUtils.DownloadJsonObjectCallable(url));
        ((GuiGlobalLibrary)this.parent).jsonDownloadExecutor.execute(this.taskCheckIfLiked);
    }

    private void setSkinLike(boolean like) {
        if (this.authenticateUser()) {
            PlushieSession plushieSession = PlushieAuth.PLUSHIE_SESSION;
            GuiGlobalLibrary globalLibrary = (GuiGlobalLibrary)this.parent;
            int userId = plushieSession.getServerId();
            String accessToken = plushieSession.getAccessToken();
            int skinId = this.skinJson.get("id").getAsInt();
            String url = SKIN_ACTION_URL;
            url = url + "?userId=" + String.valueOf(userId);
            url = url + "&accessToken=" + accessToken;
            url = like ? url + "&action=like" : url + "&action=unlike";
            url = url + "&skinId=" + String.valueOf(skinId);
            this.taskDoLiked = new FutureTask<JsonObject>(new DownloadUtils.DownloadJsonObjectCallable(url));
            ((GuiGlobalLibrary)this.parent).jsonDownloadExecutor.execute(this.taskDoLiked);
        }
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

    @Override
    public void draw(int mouseX, int mouseY, float partialTickTime) {
        if (!this.visible) {
            return;
        }
        PlushieUser user = null;
        if (this.skinJson != null && this.skinJson.has("user_id")) {
            int userId = this.skinJson.get("user_id").getAsInt();
            user = GlobalSkinLibraryUtils.getUserInfo(userId);
        }
        this.func_73733_a(this.x, this.y, this.x + this.width, this.y + this.height, -1072689136, -804253680);
        Skin skin = null;
        if (this.skinJson != null && this.skinJson.has("id")) {
            SkinIdentifier identifier = new SkinIdentifier(0, null, this.skinJson.get("id").getAsInt(), null);
            skin = ClientSkinCache.INSTANCE.getSkin(identifier);
        }
        super.draw(mouseX, mouseY, partialTickTime);
        this.drawUserbox(this.x + 5, this.y + 5, 185, 30, mouseX, mouseY, partialTickTime);
        this.drawSkinInfo(skin, this.x + 5, this.y + 20 + 20, 185, this.height - 70, mouseX, mouseY, partialTickTime);
        this.drawPreviewBox(skin, this.x + 195, this.y + 5, this.width - 200, this.height - 35, mouseX, mouseY, partialTickTime);
    }

    public void drawUserbox(int boxX, int boxY, int boxWidth, int boxHeight, int mouseX, int mouseY, float partialTickTime) {
        this.func_73733_a(boxX, boxY, boxX + boxWidth, boxY + boxHeight, 0x22888888, 0x22CCCCCC);
        String fullName = "inventory." + "armourersWorkshop".toLowerCase() + ":" + this.guiName + ".";
        PlushieUser user = null;
        if (this.skinJson != null && this.skinJson.has("user_id")) {
            int userId = this.skinJson.get("user_id").getAsInt();
            user = GlobalSkinLibraryUtils.getUserInfo(userId);
        }
        if (user != null) {
            this.func_73731_b(this.fontRenderer, StatCollector.func_74837_a((String)(fullName + "uploader"), (Object[])new Object[]{user.getUsername()}), boxX + 28, boxY + 5, -1118482);
            GuiHelper.drawPlayerHead(boxX + 5, boxY + 5, 16, user.getUsername());
        } else {
            GuiHelper.drawPlayerHead(boxX + 5, boxY + 5, 16, null);
        }
    }

    public void drawSkinInfo(Skin skin, int boxX, int boxY, int boxWidth, int boxHeight, int mouseX, int mouseY, float partialTickTime) {
        this.func_73733_a(boxX, boxY, boxX + boxWidth, boxY + boxHeight, 0x22888888, 0x22CCCCCC);
        String fullName = "inventory." + "armourersWorkshop".toLowerCase() + ":" + this.guiName + ".";
        this.func_73731_b(this.fontRenderer, GuiHelper.getLocalizedControlName(this.guiName, "title"), boxX + 5, boxY + 5, -1118482);
        if (this.skinJson != null) {
            int yOffset = 18;
            this.func_73731_b(this.fontRenderer, GuiHelper.getLocalizedControlName(this.guiName, "name"), boxX + 5, boxY + 5 + yOffset, -1118482);
            this.func_73731_b(this.fontRenderer, this.skinJson.get("name").getAsString(), boxX + 5, boxY + 5 + (yOffset += 12), -1118482);
            yOffset += 18;
            if (this.skinJson.has("downloads")) {
                this.func_73731_b(this.fontRenderer, StatCollector.func_74837_a((String)(fullName + "downloads"), (Object[])new Object[]{this.skinJson.get("downloads").getAsInt()}), boxX + 5, boxY + 5 + yOffset, -1118482);
                yOffset += 18;
            }
            if (this.skinJson.has("likes")) {
                this.func_73731_b(this.fontRenderer, StatCollector.func_74837_a((String)(fullName + "likes"), (Object[])new Object[]{this.skinJson.get("likes").getAsInt()}), boxX + 5, boxY + 5 + yOffset, -1118482);
                yOffset += 18;
            }
            if (skin != null) {
                this.func_73731_b(this.fontRenderer, GuiHelper.getLocalizedControlName(this.guiName, "author"), boxX + 5, boxY + 5 + yOffset, -1118482);
                this.func_73731_b(this.fontRenderer, skin.getAuthorName(), boxX + 5, boxY + 5 + (yOffset += 12), -1118482);
                yOffset += 18;
            }
            this.func_73731_b(this.fontRenderer, "Global ID: " + this.skinJson.get("id").getAsInt(), boxX + 5, boxY + 5 + yOffset, -1118482);
            yOffset += 18;
            if (this.skinJson.has("description")) {
                this.func_73731_b(this.fontRenderer, GuiHelper.getLocalizedControlName(this.guiName, "description"), boxX + 5, boxY + 5 + yOffset, -1118482);
                this.fontRenderer.func_78279_b(this.skinJson.get("description").getAsString(), boxX + 5, boxY + 5 + (yOffset += 12), boxWidth - 10, -1118482);
                yOffset += 18;
            }
        }
    }

    public void drawPreviewBox(Skin skin, int boxX, int boxY, int boxWidth, int boxHeight, int mouseX, int mouseY, float partialTickTime) {
        this.func_73733_a(boxX, boxY, boxX + boxWidth, boxY + boxHeight, 0x22888888, 0x22CCCCCC);
        if (skin != null) {
            int iconSize = Math.min(boxWidth, boxHeight);
            ScaledResolution scaledResolution = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
            float scale = 10 - scaledResolution.func_78325_e();
            GL11.glPushMatrix();
            GL11.glPushAttrib((int)8192);
            GL11.glTranslatef((float)(boxX + boxWidth / 2), (float)(boxY + boxHeight / 2), (float)500.0f);
            GL11.glScalef((float)(-scale), (float)scale, (float)scale);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            float rotation = (float)((double)System.currentTimeMillis() / 10.0 % 360.0);
            GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
            RenderHelper.func_74519_b();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glEnable((int)2977);
            GL11.glEnable((int)2903);
            ModRenderHelper.enableAlphaBlend();
            SkinItemRenderHelper.renderSkinAsItem(skin, new SkinPointer(skin), true, false, boxWidth, boxHeight);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }
    }

    private static class DownloadSkin
    implements Runnable {
        private final JsonObject skinJson;
        private final File target;

        public DownloadSkin(JsonObject skinJson) {
            this.skinJson = skinJson;
            int skinId = skinJson.get("id").getAsInt();
            String idString = String.format("%04d", skinId);
            String skinName = skinJson.get("name").getAsString();
            File path = new File(SkinIOUtils.getSkinLibraryDirectory(), "downloads/");
            this.target = new File(path, SkinIOUtils.makeFileNameValid(idString + " - " + skinName + ".armour"));
            if (!path.exists()) {
                path.mkdirs();
            }
            new Thread(this).start();
        }

        @Override
        public void run() {
            int serverId;
            String fileName = this.skinJson.get("file_name").getAsString();
            Skin skin = SkinDownloader.downloadSkin(fileName, serverId = this.skinJson.get("id").getAsInt());
            if (skin != null && SkinIOUtils.saveSkinToFile(this.target, skin)) {
                ILibraryManager libraryManager = ArmourersWorkshop.proxy.libraryManager;
                libraryManager.reloadLibrary();
            }
        }
    }
}

