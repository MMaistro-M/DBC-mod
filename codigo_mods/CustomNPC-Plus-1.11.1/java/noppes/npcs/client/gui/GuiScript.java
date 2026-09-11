/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiYesNo
 *  net.minecraft.client.gui.GuiYesNoCallback
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.client.gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import kamkeel.npcs.network.packets.request.script.NPCScriptPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.StatCollector;
import noppes.npcs.NoppesStringUtils;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.GuiScriptList;
import noppes.npcs.client.gui.script.GuiScriptInterface;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiMenuTopButton;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextArea;
import noppes.npcs.client.gui.util.GuiScriptTextArea;
import noppes.npcs.client.gui.util.SubGuiAPISelect;
import noppes.npcs.client.gui.util.SubGuiConfirmLink;
import noppes.npcs.client.gui.util.script.interpreter.ScriptTextContainer;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.constants.ScriptContext;
import noppes.npcs.controllers.APIRegistry;
import noppes.npcs.controllers.ScriptContainer;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.data.DataScript;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiScript
extends GuiScriptInterface {
    public boolean showScript = false;
    public DataScript script;
    private static int activeConsole = 0;

    public GuiScript(EntityNPCInterface npc) {
        this.npc = npc;
        this.script = npc.script;
        this.handler = this.script;
        this.useScrollTabs = true;
        this.useSettingsToggle = true;
        this.hookList.add("script.init");
        this.hookList.add("script.update");
        this.hookList.add("script.interact");
        this.hookList.add("dialog.dialog");
        this.hookList.add("script.damaged");
        this.hookList.add("script.killed");
        this.hookList.add("script.attack");
        this.hookList.add("script.target");
        this.hookList.add("script.collide");
        this.hookList.add("script.kills");
        this.hookList.add("script.dialog_closed");
        this.hookList.add("script.timer");
        this.hookList.add("script.targetLost");
        this.hookList.add("script.projectileTick");
        this.hookList.add("script.projectileImpact");
        NPCScriptPacket.Get();
    }

    @Override
    protected ScriptContext getScriptContext() {
        return ScriptContext.NPC;
    }

    @Override
    protected int getActiveScriptIndex() {
        return this.activeTab;
    }

    @Override
    protected ScriptContainer getCurrentContainer() {
        return this.script.getNPCScript(this.activeTab);
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.guiTop += 10;
        if (isFullscreen) {
            GuiScriptInterface.FullscreenConfig.paddingTop = 30;
        }
        boolean isFullscreenView = isFullscreen && this.showScript;
        int menuX = isFullscreenView ? GuiScriptInterface.FullscreenConfig.paddingLeft : this.guiLeft + 4;
        int menuY = isFullscreenView ? GuiScriptInterface.FullscreenConfig.paddingTop - 20 : this.guiTop - 17;
        int rightX = isFullscreenView ? this.field_146294_l - GuiScriptInterface.FullscreenConfig.paddingRight : this.guiLeft + this.xSize;
        GuiMenuTopButton top = new GuiMenuTopButton(13, menuX, menuY, "script.scripts");
        this.addTopButton(top);
        this.addTopButton(new GuiMenuTopButton(16, rightX - 102, menuY, "eventscript.eventScripts"));
        this.addTopButton(new GuiMenuTopButton(17, rightX - 22, menuY, "X"));
        top.active = this.showScript;
        top = new GuiMenuTopButton(14, top, "gui.settings");
        this.addTopButton(top);
        top.active = !this.showScript;
        this.addTopButton(new GuiMenuTopButton(15, top, "gui.api"));
        if (this.showScript) {
            this.initScriptView();
        } else {
            this.initSettingsView();
        }
    }

    private void initScriptView() {
        int editorHeight;
        int editorWidth;
        int editorY;
        int editorX;
        ScriptContainer container = this.getCurrentContainer();
        if (isFullscreen) {
            GuiScriptInterface.FullscreenConfig.paddingTop = 30;
            GuiScriptInterface.FullscreenConfig.paddingBottom = 20;
            GuiScriptInterface.FullscreenConfig.paddingLeft = 20;
            GuiScriptInterface.FullscreenConfig.paddingRight = 20;
            editorX = GuiScriptInterface.FullscreenConfig.paddingLeft;
            editorY = GuiScriptInterface.FullscreenConfig.paddingTop;
            editorWidth = this.field_146294_l - GuiScriptInterface.FullscreenConfig.paddingLeft - GuiScriptInterface.FullscreenConfig.paddingRight;
            editorHeight = this.field_146295_m - GuiScriptInterface.FullscreenConfig.paddingTop - GuiScriptInterface.FullscreenConfig.paddingBottom;
        } else {
            editorX = this.guiLeft + 74;
            editorY = this.guiTop + 4;
            editorWidth = 239;
            editorHeight = 208;
        }
        if (!isFullscreen) {
            this.addLabel(new GuiNpcLabel(0, "script.hooks", this.guiLeft + 4, this.guiTop + 5));
            GuiCustomScroll hooks = new GuiCustomScroll(this, 1);
            hooks.setSize(68, 198);
            hooks.guiLeft = this.guiLeft + 4;
            hooks.guiTop = this.guiTop + 14;
            hooks.setUnsortedList(this.hookList);
            hooks.selected = this.activeTab;
            this.addScroll(hooks);
        }
        int idx = this.getActiveScriptIndex();
        GuiScriptTextArea activeArea = this.getActiveScriptArea();
        if (activeArea == null) {
            activeArea = new GuiScriptTextArea(this, 2, editorX, editorY, editorWidth, editorHeight, container == null ? "" : container.script);
            activeArea.setListener(this);
            this.closeOnEsc(activeArea::closeOnEsc);
            this.textAreas.put(idx, activeArea);
        } else {
            activeArea.init(editorX, editorY, editorWidth, editorHeight, container == null ? "" : container.script);
        }
        String language = container != null ? container.getLanguage() : this.script.getLanguage();
        activeArea.setLanguage(language);
        activeArea.setScriptContext(this.getScriptContext());
        String hookName = EnumScriptType.values()[this.activeTab].function;
        this.applyEditorGlobals(activeArea, hookName);
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
            this.addButton(new GuiNpcButton(102, this.guiLeft + 315, this.guiTop + 4, 50, 20, "gui.clear"));
            this.addButton(new GuiNpcButton(101, this.guiLeft + 366, this.guiTop + 4, 50, 20, "gui.paste"));
            this.addButton(new GuiNpcButton(100, this.guiLeft + 315, this.guiTop + 25, 50, 20, "gui.copy"));
            this.addButton(new GuiNpcButton(107, this.guiLeft + 315, this.guiTop + 70, 80, 20, "script.loadscript"));
            if (!this.serverDataReceived) {
                for (int btnId : new int[]{100, 101, 102, 107}) {
                    GuiNpcButton btn = this.getButton(btnId);
                    if (btn == null) continue;
                    btn.field_146124_l = false;
                }
            }
            GuiCustomScroll scroll = new GuiCustomScroll(this, 0).setUnselectable();
            scroll.setSize(100, 120);
            scroll.guiLeft = this.guiLeft + 315;
            scroll.guiTop = this.guiTop + 92;
            if (container != null) {
                scroll.setList(container.scripts);
            }
            this.addScroll(scroll);
        }
    }

    private void initSettingsView() {
        this.addLabel(new GuiNpcLabel(0, "script.console", this.guiLeft + 4, this.guiTop + 16));
        if (this.getTopButton(14) != null) {
            this.getTopButton((int)14).active = true;
        }
        GuiNpcTextArea consoleArea = new GuiNpcTextArea(2, this, this.guiLeft + 4, this.guiTop + 26, 226, 186, this.getConsoleText());
        consoleArea.canEdit = false;
        this.addTextField(consoleArea);
        this.addButton(new GuiNpcButton(100, this.guiLeft + 232, this.guiTop + 170, 56, 20, "gui.copy"));
        this.addButton(new GuiNpcButton(102, this.guiLeft + 232, this.guiTop + 192, 56, 20, "gui.clear"));
        ArrayList<String> consoleOptions = new ArrayList<String>();
        consoleOptions.add("All");
        consoleOptions.addAll(this.hookList);
        this.addButton(new GuiNpcButton(105, this.guiLeft + 60, this.guiTop + 4, 80, 20, consoleOptions.toArray(new String[0]), activeConsole));
        this.addLabel(new GuiNpcLabel(1, "script.default", this.guiLeft + 232, this.guiTop + 30));
        List<String> languageOptions = this.getLanguageOptions();
        this.addButton(new GuiNpcButton(103, this.guiLeft + 294, this.guiTop + 25, 80, 20, languageOptions.toArray(new String[0]), this.getLanguageIndex(languageOptions)));
        this.addLabel(new GuiNpcLabel(2, "gui.enabled", this.guiLeft + 232, this.guiTop + 53));
        this.addButton(new GuiNpcButton(104, this.guiLeft + 294, this.guiTop + 48, 50, 20, new String[]{"gui.no", "gui.yes"}, this.script.enabled ? 1 : 0));
        if (MinecraftServer.func_71276_C() != null) {
            this.addButton(new GuiNpcButton(106, this.guiLeft + 232, this.guiTop + 71, 150, 20, "script.openfolder"));
        }
        if (!this.serverDataReceived) {
            for (int btnId : new int[]{100, 102, 103, 104, 105, 106}) {
                GuiNpcButton btn = this.getButton(btnId);
                if (btn == null) continue;
                btn.field_146124_l = false;
            }
        } else {
            this.getButton((int)103).field_146124_l = languageOptions.size() > 0;
        }
    }

    private void applyEditorGlobals(GuiScriptTextArea activeArea, String hookName) {
        if (activeArea == null) {
            return;
        }
        ScriptTextContainer textContainer = activeArea.getContainer();
        if (textContainer == null) {
            return;
        }
        if (this.script != null) {
            textContainer.setEditorGlobalsMap(this.script.getEditorGlobals(hookName));
        }
    }

    @Override
    protected String getConsoleText() {
        Map<Long, String> map = this.script.getOldConsoleText();
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Long, String> entry : map.entrySet()) {
            builder.insert(0, new Date(entry.getKey()) + entry.getValue() + "\n");
        }
        return builder.toString();
    }

    @Override
    public void func_73878_a(boolean result, int id) {
        NoppesUtil.openGUI((EntityPlayer)this.player, this);
        if (!result) {
            return;
        }
        if (id == 101) {
            this.getTextField(2).func_146180_a(NoppesStringUtils.getClipboardContents());
        }
        if (id == 102) {
            this.getTextField(2).func_146180_a("");
            if (!this.showScript) {
                if (activeConsole == 0) {
                    for (ScriptContainer container : this.script.getNPCScripts()) {
                        container.console.clear();
                    }
                } else {
                    ScriptContainer container = this.script.getNPCScript(activeConsole - 1);
                    if (container != null) {
                        container.console.clear();
                    }
                }
            }
        }
        this.displayGuiScreen(this);
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        if (guibutton.field_146127_k == 13) {
            this.showScript = true;
            this.func_73866_w_();
        }
        if (guibutton.field_146127_k == 14) {
            this.setScript();
            this.showScript = false;
            this.func_73866_w_();
        }
        if (guibutton.field_146127_k == 15) {
            if (APIRegistry.Instance.size() == 1) {
                String url = APIRegistry.Instance.getEntries().values().iterator().next();
                this.setSubGui(new SubGuiConfirmLink(url));
            } else {
                this.setSubGui(new SubGuiAPISelect());
            }
        }
        if (guibutton.field_146127_k == 16) {
            this.close();
            GuiScriptInterface.open(this, this.script);
        }
        if (guibutton.field_146127_k == 17) {
            this.close();
        }
        if (guibutton.field_146127_k == 100) {
            NoppesStringUtils.setClipboardContents(this.getTextField(2).func_146179_b());
        }
        if (guibutton.field_146127_k == 101) {
            this.displayGuiScreen((GuiScreen)new GuiYesNo((GuiYesNoCallback)this, StatCollector.func_74838_a((String)"gui.paste"), StatCollector.func_74838_a((String)"gui.sure"), 101));
        }
        if (guibutton.field_146127_k == 102) {
            this.displayGuiScreen((GuiScreen)new GuiYesNo((GuiYesNoCallback)this, StatCollector.func_74838_a((String)"gui.clear"), StatCollector.func_74838_a((String)"gui.sure"), 102));
        }
        if (guibutton.field_146127_k == 103) {
            this.script.scriptLanguage = ((GuiNpcButton)guibutton).field_146126_j;
        }
        if (guibutton.field_146127_k == 104) {
            boolean bl = this.script.enabled = ((GuiNpcButton)guibutton).getValue() == 1;
        }
        if (guibutton.field_146127_k == 105) {
            activeConsole = ((GuiNpcButton)guibutton).getValue();
            this.func_73866_w_();
        }
        if (guibutton.field_146127_k == 106) {
            NoppesUtil.openFolder(ScriptController.Instance.dir);
        }
        if (guibutton.field_146127_k == 107) {
            ScriptContainer container = this.getCurrentContainer();
            if (container == null) {
                container = new ScriptContainer(this.script);
                container.setLanguage(this.script.getLanguage());
                this.script.setNPCScript(this.activeTab, container);
            }
            String lang = container.getLanguage();
            this.setSubGui(new GuiScriptList((List<String>)((List)this.languages.get(lang)), container));
        }
    }

    @Override
    protected void setScript() {
        if (this.showScript) {
            ScriptContainer container = this.getCurrentContainer();
            if (container == null) {
                container = new ScriptContainer(this.script);
                container.setLanguage(this.script.getLanguage());
                this.script.setNPCScript(this.activeTab, container);
            }
            String text = this.getTextField(2).func_146179_b();
            text = text.replace("\r\n", "\n");
            container.script = text = text.replace("\r", "\n");
        }
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        if (!compound.func_74764_b("ScriptLanguage") && !compound.func_74764_b("Languages")) {
            return;
        }
        this.script.readFromNBT(compound);
        this.loadLanguagesData(compound);
        this.loaded = true;
    }

    @Override
    public void save() {
        if (this.loaded && this.serverDataReceived) {
            this.setScript();
            NPCScriptPacket.Save(this.script.writeToNBT(new NBTTagCompound()));
        }
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll scroll) {
        if (scroll.id == 1) {
            this.setScript();
            this.activeTab = scroll.selected;
            this.func_73866_w_();
        }
    }
}

