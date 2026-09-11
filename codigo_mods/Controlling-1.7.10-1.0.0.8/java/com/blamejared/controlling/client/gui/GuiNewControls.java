/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiCheckBox
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiControls
 *  net.minecraft.client.gui.GuiListExtended$IGuiListEntry
 *  net.minecraft.client.gui.GuiOptionButton
 *  net.minecraft.client.gui.GuiOptionSlider
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.client.settings.GameSettings$Options
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.client.event.GuiScreenEvent$ActionPerformedEvent$Post
 *  net.minecraftforge.client.event.GuiScreenEvent$ActionPerformedEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 */
package com.blamejared.controlling.client.gui;

import com.blamejared.controlling.client.gui.DisplayMode;
import com.blamejared.controlling.client.gui.GuiNewKeyBindingList;
import com.blamejared.controlling.client.gui.SearchType;
import com.blamejared.controlling.client.gui.SortOrder;
import cpw.mods.fml.client.config.GuiCheckBox;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.function.Predicate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;

@SideOnly(value=Side.CLIENT)
public class GuiNewControls
extends GuiControls {
    private static final GameSettings.Options[] OPTIONS_ARR = new GameSettings.Options[]{GameSettings.Options.INVERT_MOUSE, GameSettings.Options.SENSITIVITY, GameSettings.Options.TOUCHSCREEN};
    private final GuiScreen parentScreen;
    private final GameSettings options;
    private GuiButton buttonReset;
    private String lastSearch;
    private GuiTextField search;
    private DisplayMode displayMode;
    private SearchType searchType;
    private SortOrder sortOrder;
    private GuiButton buttonNone;
    private GuiButton buttonConflicting;
    private GuiCheckBox buttonKey;
    private GuiCheckBox buttonCat;
    private GuiButton sortOrderButton;
    private boolean confirmingReset = false;

    public GuiNewControls(GuiScreen screen, GameSettings settings) {
        super(screen, settings);
        this.parentScreen = screen;
        this.options = settings;
    }

    public void func_73866_w_() {
        this.field_146495_a = StatCollector.func_74838_a((String)"controls.title");
        int i = 0;
        for (GameSettings.Options gameOption : OPTIONS_ARR) {
            if (gameOption.func_74380_a()) {
                this.field_146292_n.add(new GuiOptionSlider(gameOption.func_74381_c(), this.field_146294_l / 2 - 155 + i % 2 * 160, 18 + 24 * (i >> 1), gameOption));
            } else {
                this.field_146292_n.add(new GuiOptionButton(gameOption.func_74381_c(), this.field_146294_l / 2 - 155 + i % 2 * 160, 18 + 24 * (i >> 1), gameOption, this.options.func_74297_c(gameOption)));
            }
            ++i;
        }
        this.field_146494_r = new GuiNewKeyBindingList(this, this.field_146297_k);
        this.field_146292_n.add(new GuiButton(1001, this.field_146294_l / 2 - 155 + 160, this.field_146295_m - 29, 150, 20, StatCollector.func_74838_a((String)"gui.done")));
        this.buttonReset = new GuiButton(1002, this.field_146294_l / 2 - 155, this.field_146295_m - 29, 150, 20, StatCollector.func_74838_a((String)"controls.resetAll"));
        this.field_146292_n.add(this.buttonReset);
        this.buttonNone = new GuiButton(1003, this.field_146294_l / 2 - 155 + 160 + 76, this.field_146295_m - 29 - 24, 75, 20, StatCollector.func_74838_a((String)"options.showNone"));
        this.field_146292_n.add(this.buttonNone);
        this.buttonConflicting = new GuiButton(1004, this.field_146294_l / 2 - 155 + 160, this.field_146295_m - 29 - 24, 75, 20, StatCollector.func_74838_a((String)"options.showConflicts"));
        this.field_146292_n.add(this.buttonConflicting);
        this.search = new GuiTextField(this.field_146289_q, this.field_146294_l / 2 - 154, this.field_146295_m - 29 - 23, 148, 18);
        this.search.func_146205_d(true);
        this.buttonKey = new GuiCheckBox(1005, this.field_146294_l / 2 - 77, this.field_146295_m - 29 - 37, StatCollector.func_74838_a((String)"options.key"), false);
        this.field_146292_n.add(this.buttonKey);
        this.buttonCat = new GuiCheckBox(1006, this.field_146294_l / 2 - 77, this.field_146295_m - 29 - 50, StatCollector.func_74838_a((String)"options.category"), false);
        this.field_146292_n.add(this.buttonCat);
        this.sortOrderButton = new GuiButton(1008, this.field_146294_l / 2 - 155 + 160 + 76, this.field_146295_m - 29 - 24 - 24, 75, 20, StatCollector.func_74838_a((String)"options.sort"));
        this.field_146292_n.add(this.sortOrderButton);
        this.sortOrder = SortOrder.NONE;
        this.lastSearch = "";
        this.displayMode = DisplayMode.ALL;
        this.searchType = SearchType.NAME;
    }

    public void func_73876_c() {
        this.search.func_146178_a();
        if (!this.lastSearch.equals(this.search.func_146179_b())) {
            this.filterKeys();
        }
    }

    public void filterKeys() {
        this.lastSearch = this.search.func_146179_b();
        if (this.lastSearch.isEmpty() && this.displayMode == DisplayMode.ALL && this.sortOrder == SortOrder.NONE && this.searchType != SearchType.NAME) {
            return;
        }
        this.field_146494_r.func_148145_f(-this.field_146494_r.func_148148_g());
        Predicate<GuiNewKeyBindingList.KeyEntry> filters = this.displayMode.getPredicate();
        switch (this.searchType) {
            case NAME: {
                filters = filters.and(keyEntry -> keyEntry.getKeyDesc().toLowerCase().contains(this.lastSearch.toLowerCase()));
                break;
            }
            case CATEGORY: {
                filters = filters.and(keyEntry -> StatCollector.func_74838_a((String)keyEntry.getKeybinding().func_151466_e()).toLowerCase().contains(this.lastSearch.toLowerCase()));
                break;
            }
            case KEY: {
                filters = filters.and(keyEntry -> GameSettings.func_74298_c((int)keyEntry.getKeybinding().func_151463_i()).toLowerCase().contains(this.lastSearch.toLowerCase()));
            }
        }
        LinkedList<GuiListExtended.IGuiListEntry> workingList = new LinkedList<GuiListExtended.IGuiListEntry>();
        for (GuiListExtended.IGuiListEntry entry : ((GuiNewKeyBindingList)this.field_146494_r).getAllEntries()) {
            GuiNewKeyBindingList.KeyEntry keyEntry2;
            if (this.searchType == SearchType.CATEGORY && this.sortOrder == SortOrder.NONE && this.displayMode == DisplayMode.ALL) {
                if (entry instanceof GuiNewKeyBindingList.KeyEntry) {
                    keyEntry2 = (GuiNewKeyBindingList.KeyEntry)entry;
                    if (!filters.test(keyEntry2)) continue;
                    workingList.add(entry);
                    continue;
                }
                workingList.add(entry);
                continue;
            }
            if (!(entry instanceof GuiNewKeyBindingList.KeyEntry) || !filters.test(keyEntry2 = (GuiNewKeyBindingList.KeyEntry)entry)) continue;
            workingList.add(entry);
        }
        if (this.searchType == SearchType.CATEGORY && this.sortOrder == SortOrder.NONE && this.displayMode == DisplayMode.ALL) {
            LinkedHashSet<GuiNewKeyBindingList.CategoryEntry> categories = new LinkedHashSet<GuiNewKeyBindingList.CategoryEntry>();
            for (GuiListExtended.IGuiListEntry entry : workingList) {
                if (!(entry instanceof GuiNewKeyBindingList.CategoryEntry)) continue;
                GuiNewKeyBindingList.CategoryEntry categoryEntry = (GuiNewKeyBindingList.CategoryEntry)entry;
                categories.add(categoryEntry);
                for (GuiListExtended.IGuiListEntry child : workingList) {
                    GuiNewKeyBindingList.KeyEntry childEntry;
                    if (!(child instanceof GuiNewKeyBindingList.KeyEntry) || !(childEntry = (GuiNewKeyBindingList.KeyEntry)child).getKeybinding().func_151466_e().equals(categoryEntry.getName())) continue;
                    categories.remove(categoryEntry);
                }
            }
            workingList.removeAll(categories);
        }
        this.sortOrder.sort(workingList);
        ((GuiNewKeyBindingList)this.field_146494_r).setListEntries(workingList);
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        this.func_146276_q_();
        this.field_146494_r.func_148128_a(mouseX, mouseY, partialTicks);
        this.func_73732_a(this.field_146289_q, this.field_146495_a, this.field_146294_l / 2, 8, 0xFFFFFF);
        boolean flag = false;
        for (KeyBinding keybinding : this.options.field_74324_K) {
            if (keybinding.func_151463_i() == keybinding.func_151469_h()) continue;
            flag = true;
            break;
        }
        this.search.func_146194_f();
        this.buttonReset.field_146124_l = flag;
        if (!flag) {
            this.confirmingReset = false;
            this.buttonReset.field_146126_j = StatCollector.func_74838_a((String)"controls.resetAll");
        }
        for (GuiButton guiButton : this.field_146292_n) {
            guiButton.func_146112_a(this.field_146297_k, mouseX, mouseY);
        }
        String text = StatCollector.func_74838_a((String)"options.search");
        this.func_73732_a(this.field_146289_q, text, this.field_146294_l / 2 - 77 - this.field_146289_q.func_78256_a(text) - 5, this.field_146295_m - 29 - 42, 0xFFFFFF);
    }

    protected void func_146284_a(GuiButton button) {
        if (button.field_146127_k < 100 && button instanceof GuiOptionButton) {
            this.options.func_74306_a(((GuiOptionButton)button).func_146136_c(), 1);
            button.field_146126_j = this.options.func_74297_c(GameSettings.Options.func_74379_a((int)button.field_146127_k));
        } else if (button.field_146127_k == 1001) {
            this.field_146297_k.func_147108_a(this.parentScreen);
        } else if (button.field_146127_k == 1002) {
            if (!this.confirmingReset) {
                this.confirmingReset = true;
                button.field_146126_j = StatCollector.func_74838_a((String)"options.confirmReset");
                return;
            }
            this.confirmingReset = false;
            button.field_146126_j = StatCollector.func_74838_a((String)"controls.resetAll");
            for (KeyBinding keyBinding : this.field_146297_k.field_71474_y.field_74324_K) {
                keyBinding.func_151462_b(keyBinding.func_151469_h());
            }
            KeyBinding.func_74508_b();
        } else if (button.field_146127_k == 1003) {
            if (this.displayMode == DisplayMode.NONE) {
                this.buttonNone.field_146126_j = StatCollector.func_74838_a((String)"options.showNone");
                this.displayMode = DisplayMode.ALL;
            } else {
                this.displayMode = DisplayMode.NONE;
                this.buttonNone.field_146126_j = StatCollector.func_74838_a((String)"options.showAll");
                this.buttonConflicting.field_146126_j = StatCollector.func_74838_a((String)"options.showConflicts");
            }
            this.filterKeys();
        } else if (button.field_146127_k == 1004) {
            if (this.displayMode == DisplayMode.CONFLICTING) {
                this.buttonConflicting.field_146126_j = StatCollector.func_74838_a((String)"options.showConflicts");
                this.displayMode = DisplayMode.ALL;
            } else {
                this.displayMode = DisplayMode.CONFLICTING;
                this.buttonConflicting.field_146126_j = StatCollector.func_74838_a((String)"options.showAll");
                this.buttonNone.field_146126_j = StatCollector.func_74838_a((String)"options.showNone");
            }
            this.filterKeys();
        } else if (button.field_146127_k == 1005) {
            this.buttonCat.setIsChecked(false);
            this.searchType = this.buttonKey.isChecked() ? SearchType.KEY : SearchType.NAME;
            this.filterKeys();
        } else if (button.field_146127_k == 1006) {
            this.buttonKey.setIsChecked(false);
            this.searchType = this.buttonCat.isChecked() ? SearchType.CATEGORY : SearchType.NAME;
            this.filterKeys();
        } else if (button.field_146127_k == 1008) {
            this.sortOrder = this.sortOrder.cycle();
            button.field_146126_j = StatCollector.func_74838_a((String)"options.sort") + ": " + this.sortOrder.getName();
            this.filterKeys();
        }
    }

    public void func_73864_a(int mx, int my, int mb) {
        if (this.field_146491_f != null) {
            this.options.func_151440_a(this.field_146491_f, -100 + mb);
            this.field_146491_f = null;
            KeyBinding.func_74508_b();
            this.search.func_146195_b(false);
        } else if (mb == 0 && !this.field_146494_r.func_148179_a(mx, my, mb)) {
            try {
                this.superSuperMouseClicked(mx, my, mb);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.search.func_146192_a(mx, my, mb);
        if (this.search.func_146206_l() && mb == 1) {
            this.search.func_146180_a("");
        }
    }

    protected void superSuperMouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (mouseButton == 0) {
            for (int i = 0; i < this.field_146292_n.size(); ++i) {
                GuiButton guibutton = (GuiButton)this.field_146292_n.get(i);
                if (!guibutton.func_146116_c(this.field_146297_k, mouseX, mouseY)) continue;
                GuiScreenEvent.ActionPerformedEvent.Pre event = new GuiScreenEvent.ActionPerformedEvent.Pre((GuiScreen)this, guibutton, this.field_146292_n);
                if (MinecraftForge.EVENT_BUS.post((Event)event)) break;
                this.field_146290_a = guibutton = event.button;
                guibutton.func_146113_a(this.field_146297_k.func_147118_V());
                this.func_146284_a(guibutton);
                if (!((Object)((Object)this)).equals(this.field_146297_k.field_71462_r)) continue;
                MinecraftForge.EVENT_BUS.post((Event)new GuiScreenEvent.ActionPerformedEvent.Post((GuiScreen)this, event.button, this.field_146292_n));
            }
        }
    }

    public void func_146286_b(int mouseX, int mouseY, int state) {
        if (state != 0 || !this.field_146494_r.func_148181_b(mouseX, mouseY, state)) {
            this.superSuperMouseReleased(mouseX, mouseY, state);
        }
    }

    protected void superSuperMouseReleased(int mouseX, int mouseY, int state) {
        if (this.field_146290_a != null && state == 0) {
            this.field_146290_a.func_146118_a(mouseX, mouseY);
            this.field_146290_a = null;
        }
    }

    public void func_73869_a(char typedChar, int keyCode) {
        if (this.field_146491_f != null) {
            if (keyCode == 1) {
                this.options.func_151440_a(this.field_146491_f, 0);
            } else if (keyCode != 0) {
                this.options.func_151440_a(this.field_146491_f, keyCode);
            } else if (typedChar > '\u0000') {
                this.options.func_151440_a(this.field_146491_f, typedChar + 256);
            }
            this.field_146491_f = null;
            this.field_152177_g = Minecraft.func_71386_F();
            KeyBinding.func_74508_b();
        } else if (this.search.func_146206_l()) {
            this.search.func_146201_a(typedChar, keyCode);
        } else {
            this.superSuperKeyTyped(typedChar, keyCode);
        }
    }

    protected void superSuperKeyTyped(char typedChar, int keyCode) {
        if (keyCode == 1) {
            this.field_146297_k.func_147108_a(null);
            if (this.field_146297_k.field_71462_r == null) {
                this.field_146297_k.func_71381_h();
            }
        }
    }
}

