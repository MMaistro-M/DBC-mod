/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiConfirmOpenLink
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiYesNo
 *  net.minecraft.client.gui.GuiYesNoCallback
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.Display
 */
package noppes.npcs.client.gui.script;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;
import noppes.npcs.NoppesStringUtils;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.script.EventGuiScriptList;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiMenuTopButton;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextArea;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.GuiScriptTextArea;
import noppes.npcs.client.gui.util.GuiUtil;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.IJTextAreaListener;
import noppes.npcs.client.gui.util.ITextChangeListener;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.client.gui.util.SubGuiAPISelect;
import noppes.npcs.client.gui.util.SubGuiConfirmLink;
import noppes.npcs.client.gui.util.script.interpreter.js_parser.JSTypeRegistry;
import noppes.npcs.constants.ScriptContext;
import noppes.npcs.controllers.APIRegistry;
import noppes.npcs.controllers.ScriptContainer;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.data.ForgeDataScript;
import noppes.npcs.controllers.data.IScriptHandler;
import noppes.npcs.controllers.data.IScriptHandlerPacket;
import noppes.npcs.controllers.data.IScriptUnit;
import noppes.npcs.janino.JaninoScript;
import noppes.npcs.scripted.item.ScriptCustomItem;
import org.lwjgl.opengl.Display;

public class GuiScriptInterface
extends GuiNPCInterface
implements GuiYesNoCallback,
IGuiData,
ITextChangeListener,
ICustomScrollListener,
IJTextAreaListener,
ITextfieldListener {
    protected int activeTab = 0;
    public IScriptHandler handler;
    public Map<String, List<String>> languages = new HashMap<String, List<String>>();
    protected int scriptLimit = 1;
    public List<String> hookList = new ArrayList<String>();
    protected boolean loaded = false;
    protected boolean serverDataReceived = false;
    protected Map<Integer, GuiScriptTextArea> textAreas = new HashMap<Integer, GuiScriptTextArea>();
    protected GuiScreen parent;
    protected boolean useScrollTabs = false;
    protected boolean useSettingsToggle = false;
    public static boolean isFullscreen = false;
    public final FullscreenButton fullscreenButton = new FullscreenButton();
    public String previousHookClicked = "";

    public GuiScriptInterface() {
        this.drawDefaultBackground = true;
        this.closeOnEsc = true;
        this.xSize = 420;
        this.isPannableGUI = true;
        this.setBackground("menubg.png");
    }

    public static GuiScriptInterface create(GuiScreen parent, IScriptHandler handler) {
        GuiScriptInterface gui = new GuiScriptInterface();
        gui.handler = handler;
        gui.parent = parent;
        gui.hookList = new ArrayList<String>(handler.getHooks());
        JSTypeRegistry registry = JSTypeRegistry.getInstance();
        if (!registry.isInitialized()) {
            registry.initializeFromResources();
        }
        registry.syncHooksFromScriptHookControllerIfNeeded();
        if (handler instanceof IScriptHandlerPacket) {
            ((IScriptHandlerPacket)handler).requestData();
        } else {
            gui.serverDataReceived = true;
        }
        return gui;
    }

    public static GuiScriptInterface open(GuiScreen parent, IScriptHandler handler) {
        GuiScriptInterface gui = GuiScriptInterface.create(parent, handler);
        Minecraft.func_71410_x().func_147108_a((GuiScreen)gui);
        return gui;
    }

    @Override
    public void func_73866_w_() {
        this.isPanning = false;
        this.ySize = (int)((double)this.xSize * 0.56);
        if ((double)this.ySize > (double)this.field_146295_m * 0.95) {
            this.ySize = (int)((double)this.field_146295_m * 0.95);
            this.xSize = (int)((double)this.ySize / 0.56);
        }
        this.bgScale = (float)this.xSize / 400.0f;
        super.func_73866_w_();
        this.guiTop += 10;
        int yoffset = (int)((double)this.ySize * 0.02);
        if (this.hookList.isEmpty()) {
            this.hookList = new ArrayList<String>(this.handler.getHooks());
        }
        if (!this.useSettingsToggle) {
            int topButtonsX = isFullscreen && this.activeTab != 0 ? FullscreenConfig.paddingLeft : this.guiLeft + 4;
            int topButtonsY = isFullscreen && this.activeTab != 0 ? FullscreenConfig.paddingTop - 20 : this.guiTop - 17;
            GuiMenuTopButton top = new GuiMenuTopButton(0, topButtonsX, topButtonsY, "gui.settings");
            this.addTopButton(top);
            int topXoffset = 0;
            int topYoffset = 0;
            boolean isSingle = this.handler.isSingleContainer();
            if (!isSingle) {
                for (int ta = 0; ta < this.handler.getScripts().size(); ++ta) {
                    if (ta % 20 == 0 && ta > 0) {
                        topYoffset -= 20;
                        topXoffset -= top.field_146120_f + 440;
                    }
                    top = new GuiMenuTopButton(ta + 1, top.field_146128_h + top.field_146120_f + topXoffset, top.field_146129_i + topYoffset, ta + 1 + "");
                    this.addTopButton(top);
                    topXoffset = 0;
                    topYoffset = 0;
                    this.scriptLimit = ta + 2;
                }
            }
            if (isSingle && this.handler.getSingleScript() != null) {
                top = new GuiMenuTopButton(1, top.field_146128_h + top.field_146120_f + topXoffset, top.field_146129_i + topYoffset, "Script");
                this.addTopButton(top);
            } else if (this.handler.getScripts().size() < 100) {
                this.addTopButton(new GuiMenuTopButton(this.scriptLimit, top.field_146128_h + top.field_146120_f, top.field_146129_i, "+"));
            }
            top = this.getTopButton(this.activeTab);
            if (top == null) {
                this.activeTab = 0;
                top = this.getTopButton(0);
            }
            top.active = true;
        }
        if (!this.useSettingsToggle) {
            if (this.activeTab > 0) {
                this.initScriptEditorTab(yoffset);
            } else {
                this.initSettingsTab(yoffset);
            }
        }
        this.xSize = 420;
        this.ySize = 256;
    }

    private void initScriptEditorTab(int yoffset) {
        int editorHeight;
        int editorWidth;
        int editorY;
        int editorX;
        IScriptUnit container = this.getCurrentContainer();
        FullscreenConfig.paddingTop = 30;
        FullscreenConfig.paddingBottom = 20;
        FullscreenConfig.paddingLeft = 20;
        FullscreenConfig.paddingRight = 20;
        if (isFullscreen) {
            editorX = FullscreenConfig.paddingLeft;
            editorY = FullscreenConfig.paddingTop;
            editorWidth = this.field_146294_l - FullscreenConfig.paddingLeft - FullscreenConfig.paddingRight;
            editorHeight = this.field_146295_m - FullscreenConfig.paddingTop - FullscreenConfig.paddingBottom;
        } else {
            editorX = this.guiLeft + 1 + yoffset;
            editorY = this.guiTop + yoffset;
            editorWidth = this.xSize - 108 - yoffset;
            editorHeight = (int)((double)this.ySize * 0.96) - yoffset * 2;
        }
        if (!isFullscreen) {
            GuiCustomScroll hooks = new GuiCustomScroll(this, 1);
            hooks.field_146291_p = false;
            hooks.setSize(108, 198);
            hooks.guiLeft = this.guiLeft - 110;
            hooks.guiTop = this.guiTop + 14;
            if (this.handler instanceof ForgeDataScript) {
                hooks.setSize(238, 198);
                hooks.guiLeft = this.guiLeft - 240;
            }
            hooks.setUnsortedList(this.hookList);
            this.addScroll(hooks);
            GuiNpcLabel hookLabel = new GuiNpcLabel(0, "script.hooks", hooks.guiLeft, this.guiTop + 5);
            hookLabel.color = 0xAAAAAA;
            this.addLabel(hookLabel);
        }
        int idx = this.getActiveScriptIndex();
        GuiScriptTextArea activeArea = this.getActiveScriptArea();
        if (activeArea == null) {
            activeArea = new GuiScriptTextArea(this, 2, editorX, editorY, editorWidth, editorHeight, container == null ? "" : container.getScript());
            activeArea.setListener(this);
            this.closeOnEsc(activeArea::closeOnEsc);
            this.textAreas.put(idx, activeArea);
        } else {
            activeArea.init(editorX, editorY, editorWidth, editorHeight, container == null ? "" : container.getScript());
        }
        String language = container != null ? container.getLanguage() : this.handler.getLanguage();
        activeArea.setLanguage(language);
        activeArea.setScriptContext(this.getScriptContext());
        activeArea.enableCodeHighlighting();
        this.updateScriptDocumentImports(activeArea, container);
        GuiScriptTextArea.KEYS.FULLSCREEN.setTask(e -> {
            if (e.isPress()) {
                this.toggleFullscreen();
            }
        });
        activeArea.enableCodeHighlighting();
        this.addTextField(activeArea);
        int scrollbarOffset = activeArea.hasVerticalScrollbar() ? -8 : -2;
        this.fullscreenButton.initGui(editorX + editorWidth, editorY, scrollbarOffset);
        if (!isFullscreen) {
            int left1 = this.guiLeft + this.xSize - 104;
            this.addButton(new GuiNpcButton(102, left1, this.guiTop + yoffset, 60, 20, "gui.clear"));
            this.addButton(new GuiNpcButton(101, left1 + 61, this.guiTop + yoffset, 60, 20, "gui.paste"));
            this.addButton(new GuiNpcButton(100, left1, this.guiTop + 21 + yoffset, 60, 20, "gui.copy"));
            this.addButton(new GuiNpcButton(105, left1 + 61, this.guiTop + 21 + yoffset, 60, 20, "gui.remove"));
            List<String> langOptions = this.getLanguageOptions();
            if (langOptions.size() > 1 && container != null) {
                int langIdx = this.getContainerLanguageIndex(langOptions, container);
                this.addButton(new GuiNpcButton(113, left1, this.guiTop + 42 + yoffset, 121, 20, langOptions.toArray(new String[0]), langIdx));
            }
            this.addButton(new GuiNpcButton(107, left1, this.guiTop + 66 + yoffset, 80, 20, "script.loadscript"));
            if (!this.serverDataReceived) {
                for (int btnId : new int[]{100, 101, 102, 105, 107, 113}) {
                    GuiNpcButton btn = this.getButton(btnId);
                    if (btn == null) continue;
                    btn.field_146124_l = false;
                }
            }
            GuiCustomScroll scroll = new GuiCustomScroll(this, 0).setUnselectable();
            scroll.setSize(100, (int)((double)this.ySize * 0.54) - yoffset * 2);
            scroll.guiLeft = left1;
            scroll.guiTop = this.guiTop + 88 + yoffset;
            if (container != null) {
                scroll.setList(container.getExternalScripts());
            }
            this.addScroll(scroll);
        }
        this.computePanBounds(editorX, editorY, editorWidth, editorHeight);
    }

    private void initSettingsTab(int yoffset) {
        GuiNpcTextArea var8 = new GuiNpcTextArea(2, this, this.guiLeft + 4 + yoffset, this.guiTop + 6 + yoffset, this.xSize - 160 - yoffset, (int)((float)this.ySize * 0.92f) - yoffset * 2, this.getConsoleText());
        var8.canEdit = false;
        var8.func_146195_b(true);
        this.addTextField(var8);
        int var9 = this.guiLeft + this.xSize - 150;
        this.addButton(new GuiNpcButton(100, var9, this.guiTop + 125, 60, 20, "gui.copy"));
        this.addButton(new GuiNpcButton(102, var9, this.guiTop + 146, 60, 20, "gui.clear"));
        this.addLabel(new GuiNpcLabel(1, "script.default", var9, this.guiTop + 15));
        List<String> languageOptions = this.getLanguageOptions();
        this.addButton(new GuiNpcButton(103, var9 + 60, this.guiTop + 10, 80, 20, languageOptions.toArray(new String[0]), this.getLanguageIndex(languageOptions)));
        this.addLabel(new GuiNpcLabel(2, "gui.enabled", var9, this.guiTop + 36));
        this.addButton(new GuiNpcButton(104, var9 + 60, this.guiTop + 31, 50, 20, new String[]{"gui.no", "gui.yes"}, this.handler.getEnabled() ? 1 : 0));
        if (this.player.field_70170_p.field_72995_K) {
            this.addButton(new GuiNpcButton(106, var9, this.guiTop + 55, 150, 20, "script.openfolder"));
        }
        this.addButton(new GuiNpcButton(109, var9, this.guiTop + 78, 80, 20, "gui.website"));
        this.addButton(new GuiNpcButton(110, var9 + 81, this.guiTop + 78, 80, 20, "gui.api"));
        if (!this.serverDataReceived) {
            for (int btnId : new int[]{100, 102, 103, 104, 106, 109, 110}) {
                GuiNpcButton btn = this.getButton(btnId);
                if (btn == null) continue;
                btn.field_146124_l = false;
            }
        } else {
            this.getButton((int)103).field_146124_l = languageOptions.size() > 0;
        }
        int consoleX = this.guiLeft + 4 + yoffset;
        int consoleY = this.guiTop + 6 + yoffset;
        int consoleW = this.xSize - 160 - yoffset;
        int consoleH = (int)((float)this.ySize * 0.92f) - yoffset * 2;
        this.computePanBounds(consoleX, consoleY, consoleW, consoleH);
    }

    public GuiScriptInterface setDimensions(int x, int y) {
        this.xSize = x;
        this.ySize = y;
        return this;
    }

    protected ScriptContext getScriptContext() {
        return this.handler.getContext();
    }

    @Override
    protected boolean isPannableArea(int mx, int my) {
        if (this.activeTab > 0 && this.fullscreenButton.isMouseOver(mx, my)) {
            return false;
        }
        GuiScriptTextArea activeArea = this.getActiveScriptArea();
        if (activeArea != null && activeArea.isPointOnAutocompleteMenu(mx, my)) {
            return false;
        }
        return super.isPannableArea(mx, my);
    }

    @Override
    public void func_73864_a(int i, int j, int k) {
        boolean isOverAutocomplete;
        int adjX = this.panAdjustedX(i);
        int adjY = this.panAdjustedY(j);
        GuiScriptTextArea activeArea = this.getActiveScriptArea();
        boolean bl = isOverAutocomplete = activeArea != null && activeArea.isPointOnAutocompleteMenu(adjX, adjY);
        if (isOverAutocomplete) {
            activeArea.func_146192_a(adjX, adjY, k);
            return;
        }
        super.func_73864_a(i, j, k);
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll scroll) {
        String hook = scroll.getSelected();
        if (this.previousHookClicked.equals(hook)) {
            IScriptUnit container = this.getCurrentContainer();
            if (container == null) {
                return;
            }
            String addString = "";
            if (!this.getTextField(2).func_146179_b().isEmpty()) {
                addString = addString + "\n";
            }
            addString = addString + container.generateHookStub(hook, null);
            this.getTextField(2).func_146180_a(this.getTextField(2).func_146179_b() + addString);
            this.previousHookClicked = "";
            this.updateScriptDocumentImports(this.getActiveScriptArea(), container);
        } else {
            this.previousHookClicked = hook;
        }
    }

    public void updateScriptDocumentImports(GuiScriptTextArea activeArea, IScriptUnit container) {
        if (container instanceof JaninoScript) {
            JaninoScript janinoScript = (JaninoScript)container;
            ScriptContext ctx = this.getScriptContext();
            activeArea.addImplicitImports(janinoScript.getDefaultImports());
            Set<String> hookTypes = janinoScript.getHookTypes();
            activeArea.addImplicitImports(hookTypes.toArray(new String[0]));
            HashSet<String> wildcardImports = new HashSet<String>();
            for (String fqn : ctx.getNamespaceFQNs()) {
                wildcardImports.add(fqn + ".*");
            }
            activeArea.addImplicitImports(wildcardImports.toArray(new String[0]));
            activeArea.formatCodeText();
        }
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
    }

    protected String getConsoleText() {
        Map<Long, String> map = this.handler.getConsoleText();
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Long, String> entry : map.entrySet()) {
            builder.insert(0, new Date(entry.getKey()) + entry.getValue() + "\n");
        }
        return builder.toString();
    }

    public int getLanguageIndex() {
        return this.getLanguageIndex(this.getLanguageOptions());
    }

    protected int getLanguageIndex(List<String> languageOptions) {
        if (languageOptions == null || languageOptions.isEmpty()) {
            return 0;
        }
        int i = 0;
        String current = this.handler != null ? this.handler.getLanguage() : null;
        for (String language : languageOptions) {
            if (current != null && language.equalsIgnoreCase(current)) {
                return i;
            }
            ++i;
        }
        return 0;
    }

    protected int getContainerLanguageIndex(List<String> languageOptions, IScriptUnit container) {
        if (languageOptions == null || languageOptions.isEmpty() || container == null) {
            return 0;
        }
        String current = container.getLanguage();
        for (int i = 0; i < languageOptions.size(); ++i) {
            if (current == null || !languageOptions.get(i).equalsIgnoreCase(current)) continue;
            return i;
        }
        return 0;
    }

    protected List<String> getLanguageOptions() {
        ArrayList<String> options = new ArrayList<String>();
        if (this.languages != null && !this.languages.isEmpty()) {
            options.addAll(this.languages.keySet());
        }
        if (this.handler != null) {
            String defaultLang;
            if (this.handler.supportsJanino()) {
                if (!options.contains("Java")) {
                    ArrayList<String> scripts = new ArrayList<String>();
                    if (ScriptController.Instance != null && ScriptController.Instance.scripts != null) {
                        scripts.addAll(ScriptController.Instance.scripts.keySet());
                    }
                    if (this.languages == null) {
                        this.languages = new HashMap<String, List<String>>();
                    }
                    if (!this.languages.containsKey("Java")) {
                        this.languages.put("Java", scripts);
                    }
                    options.add("Java");
                }
            } else {
                options.remove("Java");
            }
            if ((defaultLang = this.handler.getLanguage()) != null && !defaultLang.isEmpty() && !options.contains(defaultLang)) {
                options.add(0, defaultLang);
            }
        }
        return options;
    }

    public void func_73878_a(boolean flag, int i) {
        if (flag) {
            if (i == 0) {
                this.openLink("https://www.curseforge.com/minecraft/mc-mods/customnpc-plus");
            }
            if (i == 10) {
                this.handler.removeScriptUnit(this.activeTab - 1);
                this.activeTab = 0;
            }
            if (i == 101) {
                this.getTextField(2).func_146180_a(NoppesStringUtils.getClipboardContents());
                this.setScript();
            }
            if (i == 102) {
                if (this.activeTab > 0) {
                    IScriptUnit container = this.handler.getScripts().get(this.activeTab - 1);
                    container.setScript("");
                } else {
                    this.handler.clearConsole();
                    if (this.handler instanceof ScriptCustomItem) {
                        ((ScriptCustomItem)this.handler).saveScriptData();
                    }
                }
                this.func_73866_w_();
            }
        }
        this.displayGuiScreen(this);
    }

    public GuiScriptTextArea getActiveScriptArea() {
        int idx = this.getActiveScriptIndex();
        if (idx >= 0 && this.textAreas.containsKey(idx)) {
            return this.textAreas.get(idx);
        }
        return null;
    }

    protected int getActiveScriptIndex() {
        return this.activeTab - 1;
    }

    protected IScriptUnit getCurrentContainer() {
        int idx = this.getActiveScriptIndex();
        if (idx >= 0 && idx < this.handler.getScripts().size()) {
            return this.handler.getScripts().get(idx);
        }
        return null;
    }

    @Override
    public GuiNpcTextField getTextField(int id) {
        GuiScriptTextArea area;
        if (id == 2 && (area = this.getActiveScriptArea()) != null) {
            return area;
        }
        return super.getTextField(id);
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiYesNo guiyesno;
        if (guibutton.field_146127_k >= 0 && guibutton.field_146127_k < this.scriptLimit) {
            this.setScript();
            this.activeTab = guibutton.field_146127_k;
            this.func_73866_w_();
            IScriptUnit current = this.getCurrentContainer();
            if (current != null) {
                current.ensureCompiled();
            }
        }
        if (guibutton.field_146127_k == this.scriptLimit) {
            if (this.handler.isSingleContainer()) {
                if (this.handler.getSingleScript() == null) {
                    this.handler.addScriptUnit(this.createDefaultScriptUnit());
                } else {
                    this.setScript();
                }
                this.activeTab = 1;
            } else {
                this.handler.addScriptUnit(this.createDefaultScriptUnit());
                this.activeTab = this.handler.getScripts().size();
            }
            this.func_73866_w_();
        }
        if (guibutton.field_146127_k == 109) {
            this.displayGuiScreen((GuiScreen)new GuiConfirmOpenLink((GuiYesNoCallback)this, "https://kamkeel.github.io/CustomNPC-Plus/", 0, true));
        }
        if (guibutton.field_146127_k == 110) {
            if (APIRegistry.Instance.size() == 1) {
                String url = APIRegistry.Instance.getEntries().values().iterator().next();
                this.setSubGui(new SubGuiConfirmLink(url));
            } else {
                this.setSubGui(new SubGuiAPISelect());
            }
        }
        if (guibutton.field_146127_k == 100) {
            NoppesStringUtils.setClipboardContents(this.getTextField(2).func_146179_b());
        }
        if (guibutton.field_146127_k == 101) {
            guiyesno = new GuiYesNo((GuiYesNoCallback)this, StatCollector.func_74838_a((String)"gui.paste"), StatCollector.func_74838_a((String)"gui.sure"), 101);
            this.displayGuiScreen((GuiScreen)guiyesno);
        }
        if (guibutton.field_146127_k == 102) {
            guiyesno = new GuiYesNo((GuiYesNoCallback)this, StatCollector.func_74838_a((String)"gui.clear"), StatCollector.func_74838_a((String)"gui.sure"), 102);
            this.displayGuiScreen((GuiScreen)guiyesno);
        }
        if (guibutton.field_146127_k == 103) {
            this.handler.setLanguage(((GuiNpcButton)guibutton).field_146126_j);
        }
        if (guibutton.field_146127_k == 104) {
            this.handler.setEnabled(((GuiNpcButton)guibutton).getValue() == 1);
        }
        if (guibutton.field_146127_k == 105) {
            GuiYesNo container1 = new GuiYesNo((GuiYesNoCallback)this, "", ((GuiNpcButton)guibutton).field_146126_j, 10);
            this.displayGuiScreen((GuiScreen)container1);
        }
        if (guibutton.field_146127_k == 106) {
            NoppesUtil.openFolder(ScriptController.Instance.dir);
        }
        if (guibutton.field_146127_k == 107) {
            IScriptUnit container = this.getCurrentContainer();
            if (container == null) {
                container = this.createDefaultScriptUnit();
                this.handler.addScriptUnit(container);
            }
            String language = container.getLanguage();
            this.setSubGui(new EventGuiScriptList(this.languages.get(language), container));
        }
        if (guibutton.field_146127_k == 113) {
            String selectedLanguage = ((GuiNpcButton)guibutton).field_146126_j;
            int idx = this.getActiveScriptIndex();
            if (idx >= 0 && idx < this.handler.getScripts().size()) {
                IScriptUnit currentUnit = this.handler.getScripts().get(idx);
                boolean currentIsJanino = currentUnit.isJanino();
                boolean targetIsJanino = "Java".equals(selectedLanguage);
                if (currentIsJanino && !targetIsJanino) {
                    ScriptContainer newUnit = new ScriptContainer(this.handler);
                    newUnit.setScript(currentUnit.getScript());
                    newUnit.setExternalScripts(new ArrayList<String>(currentUnit.getExternalScripts()));
                    this.handler.replaceScriptUnit(idx, newUnit);
                    this.textAreas.remove(idx);
                    this.func_73866_w_();
                } else if (!currentIsJanino && targetIsJanino) {
                    IScriptUnit newUnit = this.handler.createJaninoScriptUnit();
                    if (newUnit != null) {
                        newUnit.setScript(currentUnit.getScript());
                        newUnit.setExternalScripts(new ArrayList<String>(currentUnit.getExternalScripts()));
                        this.handler.replaceScriptUnit(idx, newUnit);
                        this.textAreas.remove(idx);
                        this.func_73866_w_();
                    }
                } else if (!currentIsJanino) {
                    currentUnit.setLanguage(selectedLanguage);
                    GuiScriptTextArea area = this.getActiveScriptArea();
                    if (area != null) {
                        area.setLanguage(selectedLanguage);
                    }
                }
            }
        }
    }

    protected void setScript() {
        if (this.activeTab > 0 || this.useScrollTabs) {
            IScriptUnit container = this.getCurrentContainer();
            if (container == null) {
                container = this.createDefaultScriptUnit();
                this.handler.addScriptUnit(container);
            }
            if (container != null) {
                String text = this.getTextField(2).func_146179_b();
                text = text.replace("\r\n", "\n");
                text = text.replace("\r", "\n");
                container.setScript(text);
            }
        }
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        if (this.handler instanceof IScriptHandlerPacket) {
            IScriptHandlerPacket.GuiDataResult result = ((IScriptHandlerPacket)this.handler).setGuiData(compound);
            if (result == null) {
                return;
            }
            if (result.kind == IScriptHandlerPacket.GuiDataKind.LOAD_COMPLETE) {
                this.loaded = true;
                return;
            }
            if (result.kind == IScriptHandlerPacket.GuiDataKind.METADATA) {
                this.loadLanguagesData(compound);
                return;
            }
            if (result.kind == IScriptHandlerPacket.GuiDataKind.TAB) {
                this.func_73866_w_();
                return;
            }
            return;
        }
        if (!compound.func_74764_b("ScriptLanguage") && !compound.func_74764_b("Languages")) {
            return;
        }
        this.loadLanguagesData(compound);
    }

    protected void loadLanguagesData(NBTTagCompound compound) {
        List<String> javaScripts;
        NBTTagList data = compound.func_150295_c("Languages", 10);
        HashMap<String, List<String>> languages = new HashMap<String, List<String>>();
        for (int i = 0; i < data.func_74745_c(); ++i) {
            NBTTagCompound comp = data.func_150305_b(i);
            ArrayList<String> scripts = new ArrayList<String>();
            NBTTagList list = comp.func_150295_c("Scripts", 8);
            for (int j = 0; j < list.func_74745_c(); ++j) {
                scripts.add(list.func_150307_f(j));
            }
            languages.put(comp.func_74779_i("Language"), scripts);
        }
        if (this.handler != null && this.handler.supportsJanino() && ((javaScripts = languages.get("Java")) == null || javaScripts.isEmpty())) {
            javaScripts = new ArrayList<String>();
            if (ScriptController.Instance != null && ScriptController.Instance.scripts != null) {
                javaScripts.addAll(ScriptController.Instance.scripts.keySet());
            }
            languages.put("Java", javaScripts);
        }
        this.languages = languages;
        this.serverDataReceived = true;
        this.func_73866_w_();
    }

    @Override
    public void save() {
        if (!this.loaded || !this.serverDataReceived) {
            return;
        }
        this.setScript();
        if (this.handler instanceof IScriptHandlerPacket) {
            ((IScriptHandlerPacket)this.handler).sync();
        }
    }

    @Override
    public void textUpdate(String text) {
        IScriptUnit container = this.getCurrentContainer();
        if (container != null) {
            container.setScript(text);
        }
    }

    @Override
    @Deprecated
    public void saveText(String text) {
        IScriptUnit container = this.getCurrentContainer();
        if (container != null) {
            container.setScript(text);
        }
        this.func_73866_w_();
    }

    @Override
    public void close() {
        IScriptUnit current = this.getCurrentContainer();
        if (current != null) {
            current.ensureCompiled();
        }
        if (this.parent != null) {
            this.save();
            this.parent.func_146280_a(this.field_146297_k, this.field_146294_l, this.field_146295_m);
            this.parent.func_73866_w_();
            this.field_146297_k.field_71462_r = this.parent;
        } else {
            super.close();
        }
    }

    protected IScriptUnit createDefaultScriptUnit() {
        IScriptUnit unit;
        String defaultLang = this.handler.getLanguage();
        if ("Java".equals(defaultLang) && (unit = this.handler.createJaninoScriptUnit()) != null) {
            return unit;
        }
        ScriptContainer sc = new ScriptContainer(this.handler);
        sc.setLanguage(defaultLang);
        return sc;
    }

    public void toggleFullscreen() {
        boolean bl = isFullscreen = !isFullscreen;
        if (!isFullscreen || !Display.isFullscreen()) {
            // empty if block
        }
        this.func_73866_w_();
    }

    public boolean isEditorFullscreen() {
        return isFullscreen;
    }

    public class FullscreenButton {
        public int x;
        public int y;
        public int size = 12;
        public boolean hovered = false;

        public void initGui(int viewportEndX, int viewportY, int scrollbarOffset) {
            this.x = viewportEndX + scrollbarOffset - this.size - 4;
            this.y = viewportY + 4;
        }

        public void draw(int mouseX, int mouseY) {
            this.hovered = this.isMouseOver(mouseX, mouseY);
            int y = this.y + this.getYOffset();
            int bgColor = this.hovered ? -2140772762 : 0x60444444;
            GuiUtil.drawRectD(this.x, y, this.x + this.size, y + this.size, bgColor);
            int iconColor = this.hovered ? -1 : -5592406;
            int inset = 2;
            int cornerLen = 3;
            if (isFullscreen) {
                int top = y + inset + 2;
                int left = this.x + inset + 2;
                int right = this.x + this.size - inset - 3;
                int bottom = y + this.size - inset - 3;
                this.drawRect(left, top - cornerLen + 1, left + 1, top + 1, iconColor);
                this.drawRect(left - cornerLen + 1, top, left + 1, top + 1, iconColor);
                this.drawRect(right, top, right + cornerLen, top + 1, iconColor);
                this.drawRect(right, top - cornerLen + 1, right + 1, top + 1, iconColor);
                this.drawRect(left - cornerLen + 1, bottom, left + 1, bottom + 1, iconColor);
                this.drawRect(left, bottom, left + 1, bottom + cornerLen, iconColor);
                this.drawRect(right, bottom, right + cornerLen, bottom + 1, iconColor);
                this.drawRect(right, bottom, right + 1, bottom + cornerLen, iconColor);
            } else {
                this.drawRect(this.x + inset, y + inset, this.x + inset + 1, y + inset + cornerLen, iconColor);
                this.drawRect(this.x + inset, y + inset, this.x + inset + cornerLen, y + inset + 1, iconColor);
                this.drawRect(this.x + this.size - inset - 1, y + inset, this.x + this.size - inset, y + inset + cornerLen, iconColor);
                this.drawRect(this.x + this.size - inset - cornerLen, y + inset, this.x + this.size - inset, y + inset + 1, iconColor);
                this.drawRect(this.x + inset, y + this.size - inset - cornerLen, this.x + inset + 1, y + this.size - inset, iconColor);
                this.drawRect(this.x + inset, y + this.size - inset - 1, this.x + inset + cornerLen, y + this.size - inset, iconColor);
                this.drawRect(this.x + this.size - inset - 1, y + this.size - inset - cornerLen, this.x + this.size - inset, y + this.size - inset, iconColor);
                this.drawRect(this.x + this.size - inset - cornerLen, y + this.size - inset - 1, this.x + this.size - inset, y + this.size - inset, iconColor);
            }
        }

        public void drawRect(int x1, int y1, int x2, int y2, int color) {
            GuiUtil.drawRectD(x1, y1, x2, y2, color);
        }

        public boolean isMouseOver(int mouseX, int mouseY) {
            int y = this.y + this.getYOffset();
            return mouseX >= this.x && mouseX < this.x + this.size && mouseY >= y && mouseY < y + this.size;
        }

        public boolean mouseClicked(int mouseX, int mouseY, int button) {
            if (button == 0 && this.isMouseOver(mouseX, mouseY)) {
                GuiScriptInterface.this.toggleFullscreen();
                return true;
            }
            return false;
        }

        public int getYOffset() {
            return GuiScriptTextArea.searchBar.getTotalHeight();
        }
    }

    public static class FullscreenConfig {
        public static int paddingTop = 20;
        public static int paddingBottom = 20;
        public static int paddingLeft = 20;
        public static int paddingRight = 20;
    }
}

