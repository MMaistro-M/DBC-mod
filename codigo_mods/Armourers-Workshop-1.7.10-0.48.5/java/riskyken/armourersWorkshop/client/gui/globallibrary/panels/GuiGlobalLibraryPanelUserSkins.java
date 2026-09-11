/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 */
package riskyken.armourersWorkshop.client.gui.globallibrary.panels;

import java.util.ArrayList;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiControlSkinPanel;
import riskyken.armourersWorkshop.client.gui.globallibrary.GuiGlobalLibrary;
import riskyken.armourersWorkshop.client.gui.globallibrary.panels.GuiGlobalLibraryPanelSearchResults;
import riskyken.armourersWorkshop.common.library.global.DownloadUtils;
import riskyken.armourersWorkshop.common.library.global.GlobalSkinLibraryUtils;
import riskyken.armourersWorkshop.common.library.global.MultipartForm;
import riskyken.armourersWorkshop.common.library.global.PlushieUser;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.utils.TranslateUtils;

public class GuiGlobalLibraryPanelUserSkins
extends GuiGlobalLibraryPanelSearchResults {
    private static final String USER_URL = "http://plushie.moe/armourers_workshop/user-skins-page.php";
    private int userId;

    public GuiGlobalLibraryPanelUserSkins(GuiScreen parent, int x, int y, int width, int height) {
        super(parent, x, y, width, height);
    }

    public void switchToUser(int userId) {
        this.clearResults();
        if (userId != 0) {
            this.userId = userId;
            this.fetchPage(0);
        }
    }

    @Override
    protected void resize() {
        int thisUserId = this.userId;
        this.clearResults();
        this.switchToUser(thisUserId);
    }

    @Override
    protected void fetchPage(int pageIndex) {
        if (this.downloadedPageList.contains(pageIndex)) {
            return;
        }
        this.downloadedPageList.add(pageIndex);
        ArrayList<ISkinType> skinTypes = SkinTypeRegistry.INSTANCE.getRegisteredSkinTypes();
        String searchTypes = "";
        for (int i = 0; i < skinTypes.size(); ++i) {
            searchTypes = searchTypes + skinTypes.get(i).getRegistryName();
            if (i >= skinTypes.size() - 1) continue;
            searchTypes = searchTypes + ";";
        }
        String searchUrl = USER_URL;
        searchUrl = searchUrl + "?userId=" + String.valueOf(this.userId);
        searchUrl = searchUrl + "&maxFileVersion=" + String.valueOf(13);
        searchUrl = searchUrl + "&pageIndex=" + String.valueOf(pageIndex);
        searchUrl = searchUrl + "&pageSize=" + String.valueOf(this.skinPanelResults.getIconCount());
        MultipartForm multipartFormSearch = new MultipartForm(searchUrl);
        multipartFormSearch.addText("searchTypes", searchTypes);
        this.pageCompletion.submit(new DownloadUtils.DownloadJsonMultipartForm(multipartFormSearch));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        GuiControlSkinPanel.SkinIcon skinIcon;
        if (button.field_146127_k == 1) {
            this.changePage(this.currentPageIndex - 1);
        }
        if (button.field_146127_k == 2) {
            this.changePage(this.currentPageIndex + 1);
            if (this.currentPageIndex + 1 < this.totalPages) {
                this.fetchPage(this.currentPageIndex + 1);
            }
        }
        if (button == this.iconButtonSmall) {
            iconScale = 50;
            this.skinPanelResults.setIconSize(iconScale);
            this.resize();
        }
        if (button == this.iconButtonMedium) {
            iconScale = 80;
            this.skinPanelResults.setIconSize(iconScale);
            this.resize();
        }
        if (button == this.iconButtonLarge) {
            iconScale = 110;
            this.skinPanelResults.setIconSize(iconScale);
            this.resize();
        }
        if (button == this.skinPanelResults && (skinIcon = ((GuiControlSkinPanel)button).getLastPressedSkinIcon()) != null) {
            ((GuiGlobalLibrary)this.parent).panelSkinInfo.displaySkinInfo(skinIcon.getSkinJson(), GuiGlobalLibrary.Screen.USER_SKINS);
        }
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTickTime) {
        if (!this.visible) {
            return;
        }
        PlushieUser plushieUser = GlobalSkinLibraryUtils.getUserInfo(this.userId);
        String username = "unknown";
        if (plushieUser != null) {
            username = plushieUser.getUsername();
        }
        this.func_73733_a(this.x, this.y, this.x + this.width, this.y + this.height, -1072689136, -804253680);
        super.draw(mouseX, mouseY, partialTickTime);
        int maxPages = this.totalPages;
        int totalSkins = this.totalResults;
        String guiName = ((GuiGlobalLibrary)this.parent).getGuiName();
        String unlocalizedName = "inventory." + "armourersWorkshop".toLowerCase() + ":" + guiName + ".userSkins.results";
        String resultsText = TranslateUtils.translate(unlocalizedName, username, this.currentPageIndex + 1, maxPages, totalSkins);
        if (this.jsonCurrentPage == null) {
            resultsText = GuiHelper.getLocalizedControlName(guiName, "searchResults.label.searching");
        }
        this.fontRenderer.func_78276_b(resultsText, this.x + 5, this.y + 6, -1118482);
    }
}

