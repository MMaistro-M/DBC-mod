/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.SoundHandler
 *  net.minecraft.client.audio.SoundRegistry
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.util.ResourceLocation
 */
package noppes.npcs.client.gui.select;

import com.google.common.collect.Lists;
import cpw.mods.fml.relauncher.ReflectionHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.controllers.MusicController;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.SubGuiInterface;

public class GuiSoundSelection
extends SubGuiInterface
implements ICustomScrollListener {
    private GuiCustomScroll scrollCategories;
    private GuiCustomScroll scrollSounds;
    private String selectedDomain;
    public ResourceLocation selectedResource;
    private String catSearch = "";
    private String soundSearch = "";
    private final HashMap<String, List<String>> domains = new HashMap();
    private static final long CACHE_DURATION = 180000L;
    private static long lastCacheTime = 0L;
    public static HashMap<String, List<String>> cachedDomains = new HashMap();

    public GuiSoundSelection(String sound) {
        this.title = "";
        this.setBackground("menubg.png");
        this.xSize = 366;
        this.ySize = 226;
        long now = System.currentTimeMillis();
        if (now - lastCacheTime < 180000L && !cachedDomains.isEmpty()) {
            this.domains.putAll(cachedDomains);
        } else {
            SoundHandler handler = Minecraft.func_71410_x().func_147118_V();
            SoundRegistry registry = (SoundRegistry)ReflectionHelper.getPrivateValue(SoundHandler.class, (Object)handler, (int)4);
            Set set = registry.func_148742_b();
            for (ResourceLocation location : set) {
                List<String> list = this.domains.get(location.func_110624_b());
                if (list == null) {
                    list = new ArrayList<String>();
                    this.domains.put(location.func_110624_b(), list);
                }
                list.add(location.func_110623_a());
            }
            cachedDomains.clear();
            cachedDomains.putAll(this.domains);
            lastCacheTime = now;
        }
        if (sound != null && !sound.isEmpty()) {
            this.selectedResource = new ResourceLocation(sound);
            this.selectedDomain = this.selectedResource.func_110624_b();
            if (!this.domains.containsKey(this.selectedDomain)) {
                this.selectedDomain = null;
            }
        }
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addButton(new GuiNpcButton(2, this.guiLeft + this.xSize - 45, this.guiTop + this.ySize - 35, 40, 20, "gui.done"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 4, this.guiTop + this.ySize - 35, 70, 20, "gui.play"));
        boolean bl = this.getButton((int)1).field_146124_l = this.selectedResource != null;
        if (this.scrollCategories == null) {
            this.scrollCategories = new GuiCustomScroll(this, 0);
            this.scrollCategories.setSize(90, 163);
        }
        this.scrollCategories.setList(Lists.newArrayList(this.domains.keySet()));
        if (this.selectedDomain != null) {
            this.scrollCategories.setSelected(this.selectedDomain);
        }
        this.scrollCategories.guiLeft = this.guiLeft + 4;
        this.scrollCategories.guiTop = this.guiTop + 4;
        this.addScroll(this.scrollCategories);
        this.addTextField(new GuiNpcTextField(55, this, this.field_146289_q, this.guiLeft + 4, this.guiTop + 169, 90, 20, this.catSearch));
        if (this.scrollSounds == null) {
            this.scrollSounds = new GuiCustomScroll(this, 1);
            this.scrollSounds.setSize(265, 163);
        }
        if (this.selectedDomain != null) {
            this.scrollSounds.setList(this.domains.get(this.selectedDomain));
        }
        if (this.selectedResource != null) {
            this.scrollSounds.setSelected(this.selectedResource.func_110623_a());
        }
        this.scrollSounds.guiLeft = this.guiLeft + 95;
        this.scrollSounds.guiTop = this.guiTop + 4;
        this.addScroll(this.scrollSounds);
        this.addTextField(new GuiNpcTextField(66, this, this.field_146289_q, this.guiLeft + 95, this.guiTop + 169, 265, 20, this.soundSearch));
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        super.func_146284_a(guibutton);
        if (guibutton.field_146127_k == 1) {
            MusicController.Instance.stopMusic();
            MusicController.Instance.playSound(this.selectedResource.toString(), (float)this.player.field_70165_t, (float)this.player.field_70163_u, (float)this.player.field_70161_v);
        }
        if (guibutton.field_146127_k == 2) {
            this.close();
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll scroll) {
        if (this.selectedResource == null) {
            return;
        }
        this.close();
    }

    @Override
    public void close() {
        super.close();
        MusicController.Instance.stopAllSounds();
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        if (guiCustomScroll.id == 0) {
            this.selectedDomain = guiCustomScroll.getSelected();
            this.selectedResource = null;
            this.scrollSounds.selected = -1;
            this.scrollSounds.resetScroll();
            this.getTextField(66).func_146180_a("");
            this.soundSearch = "";
            this.scrollSounds.setList(this.domains.get(this.selectedDomain));
            this.getButton((int)1).field_146124_l = false;
        }
        if (guiCustomScroll.id == 1) {
            this.selectedResource = new ResourceLocation(this.selectedDomain, guiCustomScroll.getSelected());
            this.getButton((int)1).field_146124_l = true;
        }
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.getTextField(55) != null && this.getTextField(55).func_146206_l()) {
            if (this.catSearch.equals(this.getTextField(55).func_146179_b())) {
                return;
            }
            this.catSearch = this.getTextField(55).func_146179_b().toLowerCase();
            this.scrollCategories.resetScroll();
            this.scrollCategories.setList(this.getCatSearch());
        }
        if (this.getTextField(66) != null && this.getTextField(66).func_146206_l()) {
            if (this.soundSearch.equals(this.getTextField(66).func_146179_b())) {
                return;
            }
            this.soundSearch = this.getTextField(66).func_146179_b().toLowerCase();
            this.scrollSounds.resetScroll();
            this.scrollSounds.setList(this.getSoundSearch());
        }
    }

    private List<String> getCatSearch() {
        if (this.catSearch.isEmpty()) {
            return new ArrayList<String>(this.domains.keySet());
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String name : this.domains.keySet()) {
            if (!name.toLowerCase().contains(this.catSearch)) continue;
            list.add(name);
        }
        return list;
    }

    private List<String> getSoundSearch() {
        if (this.selectedDomain == null) {
            return new ArrayList<String>();
        }
        if (this.soundSearch.isEmpty()) {
            return new ArrayList<String>((Collection)this.domains.get(this.selectedDomain));
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String name : this.domains.get(this.selectedDomain)) {
            if (!name.toLowerCase().contains(this.soundSearch)) continue;
            list.add(name);
        }
        return list;
    }
}

