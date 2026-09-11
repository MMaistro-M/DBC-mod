/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonObject
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 */
package riskyken.armourersWorkshop.client.gui.globallibrary.panels;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.concurrent.FutureTask;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiControlSkinPanel;
import riskyken.armourersWorkshop.client.gui.controls.GuiPanel;
import riskyken.armourersWorkshop.client.gui.globallibrary.GuiGlobalLibrary;
import riskyken.armourersWorkshop.common.library.global.DownloadUtils;
import riskyken.armourersWorkshop.common.library.global.MultipartForm;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;

@SideOnly(value=Side.CLIENT)
public class GuiGlobalLibraryPanelHome
extends GuiPanel {
    private static final String BASE_URL = "http://plushie.moe/armourers_workshop/";
    private static final String RECENTLY_UPLOADED_URL = "http://plushie.moe/armourers_workshop/recently-uploaded.php";
    private static final String MOST_DOWNLOADED_URL = "http://plushie.moe/armourers_workshop/most-downloaded.php";
    private static final String MOST_LIKED_URL = "http://plushie.moe/armourers_workshop/most-liked.php";
    private final GuiControlSkinPanel skinPanelRecentlyUploaded = new GuiControlSkinPanel();
    private final GuiControlSkinPanel skinPanelMostDownloaded = new GuiControlSkinPanel();
    private final GuiControlSkinPanel skinPanelMostLiked = new GuiControlSkinPanel();
    private FutureTask<JsonArray> taskDownloadJsonRecentlyUploaded;
    private FutureTask<JsonArray> taskDownloadJsonMostDownloaded;
    private FutureTask<JsonArray> taskDownloadJsonMostLiked;
    private GuiButtonExt buttonShowAll;

    public GuiGlobalLibraryPanelHome(GuiScreen parent, int x, int y, int width, int height) {
        super(parent, x, y, width, height);
    }

    @Override
    public void initGui() {
        super.initGui();
        String guiName = ((GuiGlobalLibrary)this.parent).getGuiName();
        this.buttonList.clear();
        this.buttonShowAll = new GuiButtonExt(-1, this.x + 5, this.y + 5, 80, 20, GuiHelper.getLocalizedControlName(guiName, "home.showAllSkins"));
        int boxW = (this.width - 15) / 2;
        int boxH = this.height - 10 - 35;
        this.skinPanelRecentlyUploaded.init(this.x + 5, this.y + 5 + 35, boxW, boxH);
        this.skinPanelMostDownloaded.init(this.x + boxW + 10, this.y + 5 + 35, boxW, boxH / 2 - 10);
        this.skinPanelMostLiked.init(this.x + boxW + 10, this.y + 5 + 35 + boxH / 2 + 5, boxW, boxH / 2 - 5);
        this.skinPanelRecentlyUploaded.setIconSize(40);
        this.skinPanelMostDownloaded.setIconSize(40);
        this.skinPanelMostLiked.setIconSize(40);
        this.buttonList.add(this.buttonShowAll);
        this.buttonList.add(this.skinPanelRecentlyUploaded);
        this.buttonList.add(this.skinPanelMostDownloaded);
        this.buttonList.add(this.skinPanelMostLiked);
    }

    public void updateSkinPanels() {
        int iconCountRecentlyUploaded = this.skinPanelRecentlyUploaded.getIconCount();
        int iconCountMostDownloaded = this.skinPanelMostDownloaded.getIconCount();
        int iconCountMostLiked = this.skinPanelMostLiked.getIconCount();
        ArrayList<ISkinType> skinTypes = SkinTypeRegistry.INSTANCE.getRegisteredSkinTypes();
        String searchTypes = "";
        for (int i = 0; i < skinTypes.size(); ++i) {
            searchTypes = searchTypes + skinTypes.get(i).getRegistryName();
            if (i >= skinTypes.size() - 1) continue;
            searchTypes = searchTypes + ";";
        }
        MultipartForm multipartFormRecently = new MultipartForm("http://plushie.moe/armourers_workshop/recently-uploaded.php?limit=" + iconCountRecentlyUploaded + "&maxFileVersion=" + String.valueOf(13));
        MultipartForm multipartFormMostDownloaded = new MultipartForm("http://plushie.moe/armourers_workshop/most-downloaded.php?limit=" + iconCountRecentlyUploaded + "&maxFileVersion=" + String.valueOf(13));
        MultipartForm multipartFormMostLiked = new MultipartForm("http://plushie.moe/armourers_workshop/most-liked.php?limit=" + iconCountRecentlyUploaded + "&maxFileVersion=" + String.valueOf(13));
        multipartFormRecently.addText("searchTypes", searchTypes);
        multipartFormMostDownloaded.addText("searchTypes", searchTypes);
        multipartFormMostLiked.addText("searchTypes", searchTypes);
        this.taskDownloadJsonRecentlyUploaded = new FutureTask<JsonArray>(new DownloadUtils.DownloadJsonArrayMultipartForm(multipartFormRecently));
        this.taskDownloadJsonMostDownloaded = new FutureTask<JsonArray>(new DownloadUtils.DownloadJsonArrayMultipartForm(multipartFormMostDownloaded));
        this.taskDownloadJsonMostLiked = new FutureTask<JsonArray>(new DownloadUtils.DownloadJsonArrayMultipartForm(multipartFormMostLiked));
        ((GuiGlobalLibrary)this.parent).jsonDownloadExecutor.execute(this.taskDownloadJsonRecentlyUploaded);
        ((GuiGlobalLibrary)this.parent).jsonDownloadExecutor.execute(this.taskDownloadJsonMostDownloaded);
        ((GuiGlobalLibrary)this.parent).jsonDownloadExecutor.execute(this.taskDownloadJsonMostLiked);
    }

    @Override
    public void update() {
        JsonObject skinJson;
        int i;
        JsonArray jsonArray;
        if (this.taskDownloadJsonRecentlyUploaded != null && this.taskDownloadJsonRecentlyUploaded.isDone()) {
            try {
                jsonArray = this.taskDownloadJsonRecentlyUploaded.get();
                this.skinPanelRecentlyUploaded.clearIcons();
                if (jsonArray != null) {
                    for (i = 0; i < jsonArray.size(); ++i) {
                        skinJson = jsonArray.get(i).getAsJsonObject();
                        this.skinPanelRecentlyUploaded.addIcon(skinJson);
                    }
                }
                this.taskDownloadJsonRecentlyUploaded = null;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.taskDownloadJsonMostDownloaded != null && this.taskDownloadJsonMostDownloaded.isDone()) {
            try {
                jsonArray = this.taskDownloadJsonMostDownloaded.get();
                this.skinPanelMostDownloaded.clearIcons();
                if (jsonArray != null) {
                    for (i = 0; i < jsonArray.size(); ++i) {
                        skinJson = jsonArray.get(i).getAsJsonObject();
                        this.skinPanelMostDownloaded.addIcon(skinJson);
                    }
                }
                this.taskDownloadJsonMostDownloaded = null;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.taskDownloadJsonMostLiked != null && this.taskDownloadJsonMostLiked.isDone()) {
            try {
                jsonArray = this.taskDownloadJsonMostLiked.get();
                this.skinPanelMostLiked.clearIcons();
                if (jsonArray != null) {
                    for (i = 0; i < jsonArray.size(); ++i) {
                        skinJson = jsonArray.get(i).getAsJsonObject();
                        this.skinPanelMostLiked.addIcon(skinJson);
                    }
                }
                this.taskDownloadJsonMostLiked = null;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        GuiControlSkinPanel.SkinIcon skinIcon;
        if (button == this.buttonShowAll) {
            ((GuiGlobalLibrary)this.parent).panelSearchResults.clearResults();
            ((GuiGlobalLibrary)this.parent).switchScreen(GuiGlobalLibrary.Screen.SEARCH);
            ((GuiGlobalLibrary)this.parent).panelSearchResults.doSearch("", null);
        }
        if (button == this.skinPanelRecentlyUploaded | button == this.skinPanelMostDownloaded | button == this.skinPanelMostLiked && (skinIcon = ((GuiControlSkinPanel)button).getLastPressedSkinIcon()) != null) {
            ((GuiGlobalLibrary)this.parent).panelSkinInfo.displaySkinInfo(skinIcon.getSkinJson(), GuiGlobalLibrary.Screen.HOME);
        }
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTickTime) {
        if (!this.visible) {
            return;
        }
        this.func_73733_a(this.x, this.y, this.x + this.width, this.y + this.height, -1072689136, -804253680);
        super.draw(mouseX, mouseY, partialTickTime);
        String guiName = ((GuiGlobalLibrary)this.parent).getGuiName();
        String labelRecentlyUploaded = GuiHelper.getLocalizedControlName(guiName, "home.recentlyUploaded");
        String labelMostDownloaded = GuiHelper.getLocalizedControlName(guiName, "home.mostDownloaded");
        String labelMostLikes = GuiHelper.getLocalizedControlName(guiName, "home.mostLikes");
        int boxW = (this.width - 15) / 2;
        int boxH = this.height - 10 - 35;
        this.fontRenderer.func_78276_b(labelRecentlyUploaded, this.x + 5, this.y + 30, -1118482);
        this.fontRenderer.func_78276_b(labelMostDownloaded, this.x + boxW + 10, this.y + 30, -1118482);
        this.fontRenderer.func_78276_b(labelMostLikes, this.x + boxW + 10, this.y + 30 + boxH / 2 + 5, -1118482);
    }
}

