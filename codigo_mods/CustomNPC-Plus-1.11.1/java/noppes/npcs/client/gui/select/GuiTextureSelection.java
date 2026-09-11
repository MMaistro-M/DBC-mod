/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.ModContainer
 *  cpw.mods.fml.common.ObfuscationReflectionHelper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.resources.AbstractResourcePack
 *  net.minecraft.client.resources.DefaultResourcePack
 *  net.minecraft.client.resources.FallbackResourceManager
 *  net.minecraft.client.resources.IResourcePack
 *  net.minecraft.client.resources.ResourcePackRepository
 *  net.minecraft.client.resources.ResourcePackRepository$Entry
 *  net.minecraft.client.resources.SimpleReloadableResourceManager
 *  net.minecraft.item.crafting.CraftingManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 */
package noppes.npcs.client.gui.select;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.AbstractResourcePack;
import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.client.resources.FallbackResourceManager;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.NPCResourceHelper;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.CustomNpcs;
import noppes.npcs.LogWriter;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.entity.EntityNPCInterface;

public class GuiTextureSelection
extends SubGuiInterface
implements ICustomScrollListener {
    private final String up = "..<" + StatCollector.func_74838_a((String)"gui.up") + ">..";
    private GuiCustomScroll scrollCategories;
    private GuiCustomScroll scrollTextures;
    private String location = "";
    private String selectedDomain;
    public ResourceLocation selectedResource;
    public boolean setNPCSkin = true;
    private final HashMap<String, List<TextureData>> domains = new HashMap();
    private final HashMap<String, TextureData> textures = new HashMap();
    static final long CACHE_DURATION = 180000L;
    private static long lastCacheTime = 0L;
    private static HashMap<String, List<TextureData>> cachedDomains = new HashMap();
    public static HashMap<String, TextureData> cachedTextures = new HashMap();
    public static final long CACHE_DURATION_MS = 180000L;

    public static long lastCacheTime() {
        return lastCacheTime;
    }

    public static HashMap<String, List<TextureData>> cachedDomains() {
        return cachedDomains;
    }

    public GuiTextureSelection(EntityNPCInterface npc, String texture) {
        this.npc = npc;
        this.drawDefaultBackground = false;
        this.title = "";
        this.setBackground("menubg.png");
        this.xSize = 366;
        this.ySize = 226;
        long now = System.currentTimeMillis();
        if (cachedDomains != null && now - lastCacheTime < 180000L) {
            this.domains.putAll(cachedDomains);
            this.textures.putAll(cachedTextures);
        } else {
            File f;
            SimpleReloadableResourceManager simplemanager = (SimpleReloadableResourceManager)Minecraft.func_71410_x().func_110442_L();
            Map map = (Map)ObfuscationReflectionHelper.getPrivateValue(SimpleReloadableResourceManager.class, (Object)simplemanager, (int)2);
            HashSet<String> set = new HashSet<String>();
            for (String name : map.keySet()) {
                if (!(map.get(name) instanceof FallbackResourceManager)) continue;
                FallbackResourceManager manager = (FallbackResourceManager)map.get(name);
                List list = (List)ObfuscationReflectionHelper.getPrivateValue(FallbackResourceManager.class, (Object)manager, (int)0);
                for (IResourcePack pack : list) {
                    AbstractResourcePack p;
                    File file;
                    if (!(pack instanceof AbstractResourcePack) || (file = NPCResourceHelper.getPackFile(p = (AbstractResourcePack)pack)) == null) continue;
                    set.add(file.getAbsolutePath());
                }
            }
            for (String file : set) {
                File f2 = new File(file);
                if (f2.isDirectory()) {
                    this.checkFolder(new File(f2, "assets"), f2.getAbsolutePath().length());
                    continue;
                }
                this.progressFile(f2);
            }
            for (ModContainer mod : Loader.instance().getModList()) {
                if (!mod.getSource().exists()) continue;
                this.progressFile(mod.getSource());
            }
            ResourcePackRepository repos = Minecraft.func_71410_x().func_110438_M();
            repos.func_110611_a();
            List list = repos.func_110613_c();
            for (ResourcePackRepository.Entry entry : list) {
                File file = new File(repos.func_110612_e(), entry.func_110515_d());
                if (!file.exists()) continue;
                this.progressFile(file);
            }
            this.checkFolder(new File(CustomNpcs.Dir, "assets"), CustomNpcs.Dir.getAbsolutePath().length());
            URL url = DefaultResourcePack.class.getResource("/");
            if (url != null) {
                f = this.decodeFile(url.getFile());
                if (f.isDirectory()) {
                    this.checkFolder(new File(f, "assets"), url.getFile().length());
                } else {
                    this.progressFile(f);
                }
            }
            if ((url = CraftingManager.class.getResource("/assets/.mcassetsroot")) != null) {
                f = this.decodeFile(url.getFile());
                if (f.isDirectory()) {
                    this.checkFolder(new File(f, "assets"), url.getFile().length());
                } else {
                    this.progressFile(f);
                }
            }
            cachedDomains = new HashMap<String, List<TextureData>>(this.domains);
            cachedTextures = new HashMap<String, TextureData>(this.textures);
            lastCacheTime = now;
        }
        if (texture != null && !texture.isEmpty()) {
            this.selectedResource = new ResourceLocation(texture);
            this.selectedDomain = this.selectedResource.func_110624_b();
            if (!this.domains.containsKey(this.selectedDomain)) {
                this.selectedDomain = null;
            }
            int i = this.selectedResource.func_110623_a().lastIndexOf(47);
            this.location = this.selectedResource.func_110623_a().substring(0, i + 1);
        }
        this.drawNpc = true;
        this.xOffsetNpc = 307;
        this.yOffsetNpc = 150;
        this.zoom = 1.5f;
        this.defaultZoom = 1.5f;
    }

    private File decodeFile(String url) {
        int i;
        if (url.startsWith("file:")) {
            url = url.substring(5);
        }
        if ((i = (url = url.replace('/', File.separatorChar)).indexOf(33)) > 0) {
            url = url.substring(0, i);
        }
        try {
            url = URLDecoder.decode(url, StandardCharsets.UTF_8.name());
        }
        catch (UnsupportedEncodingException unsupportedEncodingException) {
            // empty catch block
        }
        return new File(url);
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.title = this.selectedDomain != null ? this.selectedDomain + ":" + this.location : "";
        this.addButton(new GuiNpcButton(2, this.guiLeft + 264, this.guiTop + 170, 90, 20, "gui.done"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 264, this.guiTop + 190, 90, 20, "gui.cancel"));
        if (this.scrollCategories == null) {
            this.scrollCategories = new GuiCustomScroll(this, 0);
            this.scrollCategories.setSize(120, 200);
        }
        if (this.selectedDomain == null) {
            this.scrollCategories.setList(Lists.newArrayList(this.domains.keySet()));
            if (this.selectedDomain != null) {
                this.scrollCategories.setSelected(this.selectedDomain);
            }
        } else {
            ArrayList<String> list = new ArrayList<String>();
            list.add(this.up);
            List<TextureData> data = this.domains.get(this.selectedDomain);
            for (TextureData td : data) {
                String path;
                int i;
                if (!this.location.isEmpty() && (!td.path.startsWith(this.location) || td.path.equals(this.location)) || (i = (path = td.path.substring(this.location.length())).indexOf(47)) < 0 || (path = path.substring(0, i)).isEmpty() || list.contains(path)) continue;
                list.add(path);
            }
            this.scrollCategories.setList(list);
        }
        this.scrollCategories.guiLeft = this.guiLeft + 4;
        this.scrollCategories.guiTop = this.guiTop + 14;
        this.addScroll(this.scrollCategories);
        if (this.scrollTextures == null) {
            this.scrollTextures = new GuiCustomScroll(this, 1);
            this.scrollTextures.setSize(130, 200);
        }
        if (this.selectedDomain != null) {
            this.textures.clear();
            List<TextureData> data = this.domains.get(this.selectedDomain);
            ArrayList<String> list = new ArrayList<String>();
            String loc = this.location;
            if (this.scrollCategories.hasSelected() && !this.scrollCategories.getSelected().equals(this.up)) {
                loc = loc + this.scrollCategories.getSelected() + '/';
            }
            for (TextureData td : data) {
                if (!td.path.equals(loc) || list.contains(td.name)) continue;
                list.add(td.name);
                this.textures.put(td.name, td);
            }
            this.scrollTextures.setList(list);
        }
        if (this.selectedResource != null) {
            this.scrollTextures.setSelected(this.selectedResource.func_110623_a());
        }
        this.scrollTextures.guiLeft = this.guiLeft + 125;
        this.scrollTextures.guiTop = this.guiTop + 14;
        this.addScroll(this.scrollTextures);
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        super.func_146284_a(guibutton);
        if (guibutton.field_146127_k == 2 && this.selectedResource != null && this.setNPCSkin) {
            this.npc.display.setSkinTexture(this.selectedResource.toString());
        }
        this.npc.textureLocation = null;
        this.close();
        this.parent.func_73866_w_();
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll scroll) {
        if (scroll == this.scrollTextures) {
            if (scroll.id == 1) {
                TextureData data = this.textures.get(scroll.getSelected());
                this.selectedResource = new ResourceLocation(this.selectedDomain, data.absoluteName);
                if (this.setNPCSkin) {
                    this.npc.textureLocation = this.selectedResource;
                }
            }
        } else {
            this.func_73866_w_();
            this.scrollTextures.resetScroll();
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll scroll) {
        if (scroll == this.scrollCategories) {
            if (this.selectedDomain == null) {
                this.selectedDomain = selection;
            } else if (selection.equals(this.up)) {
                int i = this.location.lastIndexOf(47, this.location.length() - 2);
                if (i < 0) {
                    if (this.location.isEmpty()) {
                        this.selectedDomain = null;
                    }
                    this.location = "";
                } else {
                    this.location = this.location.substring(0, i + 1);
                }
            } else {
                this.location = this.location + selection + '/';
            }
            this.scrollCategories.selected = -1;
            this.scrollTextures.selected = -1;
            this.func_73866_w_();
        } else {
            if (this.setNPCSkin) {
                this.npc.display.setSkinTexture(this.selectedResource.toString());
            }
            this.close();
            this.parent.func_73866_w_();
        }
    }

    private void progressFile(File file) {
        try {
            if (!file.isDirectory() && (file.getName().endsWith(".jar") || file.getName().endsWith(".zip"))) {
                ZipFile zip = new ZipFile(file);
                Enumeration<? extends ZipEntry> entries = zip.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry zipentry = entries.nextElement();
                    String entryName = zipentry.getName();
                    this.addFile(entryName);
                }
                zip.close();
            } else if (file.isDirectory()) {
                int length = file.getAbsolutePath().length();
                this.checkFolder(file, length);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkFolder(File file, int length) {
        File[] files = file.listFiles();
        if (files == null) {
            return;
        }
        for (File f : files) {
            String name = null;
            try {
                name = f.getAbsolutePath().substring(length);
                name = name.replace("\\", "/");
                if (!name.startsWith("/")) {
                    name = "/" + name;
                }
                if (f.isDirectory()) {
                    this.addFile(name + "/");
                    this.checkFolder(f, length);
                    continue;
                }
                this.addFile(name);
            }
            catch (Throwable e) {
                LogWriter.error("error with: " + name);
            }
        }
    }

    private void addFile(String name) {
        if (name.startsWith("/")) {
            name = name.substring(1);
        }
        if (!name.startsWith("assets/") || !name.endsWith(".png")) {
            return;
        }
        name = name.substring(7);
        int i = name.indexOf(47);
        String domain = name.substring(0, i);
        name = name.substring(i + 1);
        List<TextureData> list = this.domains.get(domain);
        if (list == null) {
            list = new ArrayList<TextureData>();
            this.domains.put(domain, list);
        }
        boolean contains = false;
        for (TextureData data : list) {
            if (!data.absoluteName.equals(name)) continue;
            contains = true;
            break;
        }
        if (!contains) {
            list.add(new TextureData(domain, name));
        }
    }

    public void setLocation(String domain, String location) {
        this.selectedDomain = domain;
        this.location = location;
    }

    static class TextureData {
        String domain;
        String absoluteName;
        String name;
        String path;

        public TextureData(String domain, String absoluteName) {
            this.domain = domain;
            int i = absoluteName.lastIndexOf(47);
            this.name = absoluteName.substring(i + 1);
            this.path = absoluteName.substring(0, i + 1);
            this.absoluteName = absoluteName;
        }
    }
}

