/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.ResourceLocation
 */
package riskyken.armourersWorkshop.client.gui.globallibrary.panels;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiControlSkinPanel;
import riskyken.armourersWorkshop.client.gui.controls.GuiIconButton;
import riskyken.armourersWorkshop.client.gui.controls.GuiPanel;
import riskyken.armourersWorkshop.client.gui.globallibrary.GuiGlobalLibrary;
import riskyken.armourersWorkshop.common.library.global.DownloadUtils;
import riskyken.armourersWorkshop.common.library.global.MultipartForm;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.utils.TranslateUtils;

@SideOnly(value=Side.CLIENT)
public class GuiGlobalLibraryPanelSearchResults
extends GuiPanel {
    private static final ResourceLocation BUTTON_TEXTURES = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/gui/globalLibrary.png");
    protected static final String BASE_URL = "http://plushie.moe/armourers_workshop/";
    private static final String SEARCH_URL = "http://plushie.moe/armourers_workshop/skin-search-page.php";
    protected final CompletionService<JsonObject> pageCompletion;
    protected final GuiControlSkinPanel skinPanelResults;
    protected GuiIconButton iconButtonSmall;
    protected GuiIconButton iconButtonMedium;
    protected GuiIconButton iconButtonLarge;
    protected static int iconScale = 110;
    protected String search = null;
    protected ISkinType skinType = null;
    protected JsonArray[] pageList;
    protected HashSet<Integer> downloadedPageList;
    protected JsonArray jsonCurrentPage;
    protected int currentPageIndex = 0;
    protected int totalPages = 0;
    protected int totalResults = 0;

    public GuiGlobalLibraryPanelSearchResults(GuiScreen parent, int x, int y, int width, int height) {
        super(parent, x, y, width, height);
        this.pageCompletion = new ExecutorCompletionService<JsonObject>(((GuiGlobalLibrary)parent).jsonDownloadExecutor);
        this.skinPanelResults = new GuiControlSkinPanel();
        this.pageList = null;
        this.downloadedPageList = new HashSet();
    }

    public void doSearch(String search, ISkinType skinType) {
        this.clearResults();
        this.search = search;
        this.skinType = skinType;
        if (this.search == null) {
            return;
        }
        this.fetchPage(0);
    }

    public void clearResults() {
        this.search = null;
        this.pageList = null;
        this.jsonCurrentPage = null;
        this.currentPageIndex = 0;
        this.totalPages = 0;
        this.totalResults = 0;
        this.skinPanelResults.clearIcons();
        this.downloadedPageList.clear();
    }

    protected void resize() {
        String thisSearch = this.search;
        ISkinType thisSkinType = this.skinType;
        int thisPage = this.currentPageIndex;
        this.clearResults();
        this.doSearch(thisSearch, thisSkinType);
    }

    protected void fetchPage(int pageIndex) {
        if (this.downloadedPageList.contains(pageIndex)) {
            return;
        }
        this.downloadedPageList.add(pageIndex);
        try {
            String searchUrl = SEARCH_URL;
            searchUrl = searchUrl + "?search=" + URLEncoder.encode(this.search, "UTF-8");
            searchUrl = searchUrl + "&maxFileVersion=" + String.valueOf(13);
            searchUrl = searchUrl + "&pageIndex=" + String.valueOf(pageIndex);
            searchUrl = searchUrl + "&pageSize=" + String.valueOf(this.skinPanelResults.getIconCount());
            ArrayList<ISkinType> skinTypes = SkinTypeRegistry.INSTANCE.getRegisteredSkinTypes();
            String searchTypes = "";
            if (this.skinType == null) {
                for (int i = 0; i < skinTypes.size(); ++i) {
                    searchTypes = searchTypes + skinTypes.get(i).getRegistryName();
                    if (i >= skinTypes.size() - 1) continue;
                    searchTypes = searchTypes + ";";
                }
            } else {
                searchTypes = this.skinType.getRegistryName();
            }
            MultipartForm multipartForm = new MultipartForm(searchUrl);
            multipartForm.addText("searchTypes", searchTypes);
            this.pageCompletion.submit(new DownloadUtils.DownloadJsonMultipartForm(multipartForm));
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        Future<JsonObject> futureJson = this.pageCompletion.poll();
        if (futureJson != null) {
            try {
                JsonObject pageJson = futureJson.get();
                if (pageJson != null) {
                    this.onPageJsonDownload(pageJson);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void onPageJsonDownload(JsonObject pageJson) {
        if (pageJson.has("totalPages")) {
            this.totalPages = pageJson.get("totalPages").getAsInt();
        }
        if (pageJson.has("totalResults")) {
            this.totalResults = pageJson.get("totalResults").getAsInt();
        }
        int pageIndex = 0;
        if (pageJson.has("currentPageIndex") && (pageIndex = pageJson.get("currentPageIndex").getAsInt()) == 0 & this.totalPages > 1) {
            this.fetchPage(1);
        }
        if (pageJson.has("results")) {
            JsonArray pageResults = pageJson.get("results").getAsJsonArray();
            if (this.pageList == null) {
                this.pageList = new JsonArray[this.totalPages];
            }
            this.pageList[pageIndex] = pageResults;
            this.updateSkinForPage();
        }
    }

    @Override
    public GuiPanel setSize(int width, int height) {
        boolean resized = width != this.width | height != this.height;
        super.setSize(width, height);
        this.initGui();
        if (resized) {
            this.resize();
        }
        return this;
    }

    @Override
    public void initGui() {
        super.initGui();
        String guiName = ((GuiGlobalLibrary)this.parent).getGuiName();
        this.buttonList.clear();
        this.skinPanelResults.init(this.x + 5, this.y + 24, this.width - 10, this.height - 52);
        this.skinPanelResults.setIconSize(iconScale);
        this.skinPanelResults.setPanelPadding(0);
        this.skinPanelResults.setShowName(true);
        this.iconButtonSmall = new GuiIconButton(this.parent, 0, this.x + this.width - 63, this.y + 5, 16, 16, GuiHelper.getLocalizedControlName(guiName, "searchResults.small"), BUTTON_TEXTURES);
        this.iconButtonSmall.setIconLocation(51, 0, 16, 16);
        this.iconButtonMedium = new GuiIconButton(this.parent, 0, this.x + this.width - 42, this.y + 5, 16, 16, GuiHelper.getLocalizedControlName(guiName, "searchResults.medium"), BUTTON_TEXTURES);
        this.iconButtonMedium.setIconLocation(51, 17, 16, 16);
        this.iconButtonLarge = new GuiIconButton(this.parent, 0, this.x + this.width - 21, this.y + 5, 16, 16, GuiHelper.getLocalizedControlName(guiName, "searchResults.large"), BUTTON_TEXTURES);
        this.iconButtonLarge.setIconLocation(51, 34, 16, 16);
        this.buttonList.add(this.iconButtonSmall);
        this.buttonList.add(this.iconButtonMedium);
        this.buttonList.add(this.iconButtonLarge);
        this.buttonList.add(this.skinPanelResults);
        this.buttonList.add(new GuiButtonExt(1, this.x + 5, this.y + this.height - 25, 80, 20, "<<"));
        this.buttonList.add(new GuiButtonExt(2, this.x + this.width - 85, this.y + this.height - 25, 80, 20, ">>"));
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
            ((GuiGlobalLibrary)this.parent).panelSkinInfo.displaySkinInfo(skinIcon.getSkinJson(), GuiGlobalLibrary.Screen.SEARCH);
        }
    }

    protected void changePage(int pageIndex) {
        if (pageIndex < this.totalPages & pageIndex >= 0) {
            this.currentPageIndex = pageIndex;
        }
        if (this.currentPageIndex > this.totalPages) {
            this.currentPageIndex = this.totalPages - 1;
        }
        if (this.currentPageIndex < 0) {
            this.currentPageIndex = 0;
        }
        this.updateSkinForPage();
    }

    protected void updateSkinForPage() {
        this.jsonCurrentPage = this.getJsonForPage(this.currentPageIndex);
        if (this.jsonCurrentPage != null) {
            int skinsPerPage = this.skinPanelResults.getIconCount();
            int pageOffset = skinsPerPage * this.currentPageIndex;
            this.skinPanelResults.clearIcons();
            JsonArray downloadArray = new JsonArray();
            for (int i = 0; i < skinsPerPage; ++i) {
                if (i >= this.jsonCurrentPage.size()) continue;
                JsonObject skinJson = this.jsonCurrentPage.get(i).getAsJsonObject();
                downloadArray.add((JsonElement)skinJson);
                this.skinPanelResults.addIcon(skinJson);
            }
        }
    }

    private JsonArray getJsonForPage(int pageIndex) {
        if (this.pageList != null && pageIndex >= 0 & pageIndex < this.pageList.length) {
            return this.pageList[pageIndex];
        }
        return null;
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTickTime) {
        if (((Object)((Object)this)).getClass().equals(GuiGlobalLibraryPanelSearchResults.class)) {
            if (!this.visible) {
                return;
            }
            this.func_73733_a(this.x, this.y, this.x + this.width, this.y + this.height, -1072689136, -804253680);
            super.draw(mouseX, mouseY, partialTickTime);
            int maxPages = this.totalPages;
            int totalSkins = this.totalResults;
            String guiName = ((GuiGlobalLibrary)this.parent).getGuiName();
            String unlocalizedName = "inventory." + "armourersWorkshop".toLowerCase() + ":" + guiName + ".searchResults.results";
            String resultsText = TranslateUtils.translate(unlocalizedName, this.currentPageIndex + 1, maxPages, totalSkins);
            if (this.jsonCurrentPage == null) {
                resultsText = GuiHelper.getLocalizedControlName(guiName, "searchResults.label.searching");
            }
            this.fontRenderer.func_78276_b(resultsText, this.x + 5, this.y + 6, -1118482);
        } else {
            super.draw(mouseX, mouseY, partialTickTime);
        }
    }
}

