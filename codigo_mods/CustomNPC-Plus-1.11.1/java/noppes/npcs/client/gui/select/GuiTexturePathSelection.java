/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.gui.select.GuiTextureSelection;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.client.renderer.ImageData;
import org.lwjgl.opengl.GL11;

public class GuiTexturePathSelection
extends SubGuiInterface
implements ICustomScrollListener {
    private final String up = "..<" + StatCollector.func_74838_a((String)"gui.up") + ">..";
    private GuiCustomScroll scrollCategories;
    private GuiCustomScroll scrollTextures;
    private String location = "";
    private String selectedDomain;
    public ResourceLocation selectedResource;
    private final HashMap<String, List<GuiTextureSelection.TextureData>> domains = new HashMap();
    private final HashMap<String, GuiTextureSelection.TextureData> textures = new HashMap();
    private String catSearch = "";
    private String texSearch = "";
    private static final int PREVIEW_SIZE = 90;

    public GuiTexturePathSelection(String texture) {
        this.drawDefaultBackground = false;
        this.title = "";
        this.setBackground("menubg.png");
        this.xSize = 366;
        this.ySize = 226;
        long now = System.currentTimeMillis();
        if (GuiTextureSelection.cachedTextures != null && !GuiTextureSelection.cachedTextures.isEmpty() && now - GuiTextureSelection.lastCacheTime() < 180000L) {
            this.domains.putAll(GuiTextureSelection.cachedDomains());
        } else {
            GuiTextureSelection temp = new GuiTextureSelection(null, null);
            this.domains.putAll(GuiTextureSelection.cachedDomains());
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
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.title = this.selectedDomain != null ? this.selectedDomain + ":" + this.location : "";
        this.addButton(new GuiNpcButton(2, this.guiLeft + this.xSize - 46, this.guiTop + this.ySize - 28, 42, 20, "gui.done"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + this.xSize - 92, this.guiTop + this.ySize - 28, 42, 20, "gui.cancel"));
        if (this.scrollCategories == null) {
            this.scrollCategories = new GuiCustomScroll(this, 0);
            this.scrollCategories.setSize(90, 163);
        }
        if (this.selectedDomain == null) {
            this.scrollCategories.setList(this.getCatSearch());
        } else {
            ArrayList<String> list = new ArrayList<String>();
            list.add(this.up);
            List<GuiTextureSelection.TextureData> data = this.domains.get(this.selectedDomain);
            if (data != null) {
                for (GuiTextureSelection.TextureData td : data) {
                    String path;
                    int i;
                    if (!this.location.isEmpty() && (!td.path.startsWith(this.location) || td.path.equals(this.location)) || (i = (path = td.path.substring(this.location.length())).indexOf(47)) < 0 || (path = path.substring(0, i)).isEmpty() || list.contains(path)) continue;
                    list.add(path);
                }
            }
            this.scrollCategories.setList(list);
        }
        this.scrollCategories.guiLeft = this.guiLeft + 4;
        this.scrollCategories.guiTop = this.guiTop + 14;
        this.addScroll(this.scrollCategories);
        this.addTextField(new GuiNpcTextField(55, this, this.field_146289_q, this.guiLeft + 4, this.guiTop + 179, 90, 20, this.catSearch));
        if (this.scrollTextures == null) {
            this.scrollTextures = new GuiCustomScroll(this, 1);
            this.scrollTextures.setSize(160, 163);
        }
        if (this.selectedDomain != null) {
            this.textures.clear();
            List<GuiTextureSelection.TextureData> data = this.domains.get(this.selectedDomain);
            ArrayList<String> allList = new ArrayList<String>();
            String loc = this.location;
            if (this.scrollCategories.hasSelected() && !this.scrollCategories.getSelected().equals(this.up)) {
                loc = loc + this.scrollCategories.getSelected() + '/';
            }
            if (data != null) {
                for (GuiTextureSelection.TextureData td : data) {
                    if (!td.path.equals(loc) || allList.contains(td.name)) continue;
                    allList.add(td.name);
                    this.textures.put(td.name, td);
                }
            }
            this.scrollTextures.setList(this.filterTexSearch(allList));
        }
        if (this.selectedResource != null) {
            this.scrollTextures.setSelected(this.selectedResource.func_110623_a());
        }
        this.scrollTextures.guiLeft = this.guiLeft + 95;
        this.scrollTextures.guiTop = this.guiTop + 14;
        this.addScroll(this.scrollTextures);
        this.addTextField(new GuiNpcTextField(66, this, this.field_146289_q, this.guiLeft + 95, this.guiTop + 179, 160, 20, this.texSearch));
    }

    @Override
    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        super.func_73863_a(mouseX, mouseY, partialTicks);
        this.drawTexturePreview();
    }

    private void drawTexturePreview() {
        int previewX = this.guiLeft + 260;
        int previewY = this.guiTop + 14;
        GuiTexturePathSelection.func_73734_a((int)(previewX - 1), (int)(previewY - 1), (int)(previewX + 90 + 1), (int)(previewY + 90 + 1), (int)-6250336);
        GuiTexturePathSelection.func_73734_a((int)previewX, (int)previewY, (int)(previewX + 90), (int)(previewY + 90), (int)-16777216);
        if (this.selectedResource == null) {
            String none = StatCollector.func_74838_a((String)"gui.none");
            int w = this.field_146289_q.func_78256_a(none);
            this.field_146289_q.func_78276_b(none, previewX + (90 - w) / 2, previewY + 45 - 4, -8355712);
            return;
        }
        ImageData imageData = ClientCacheHandler.getImageData(this.selectedResource.toString());
        if (imageData == null || !imageData.imageLoaded()) {
            String loading = "...";
            int w = this.field_146289_q.func_78256_a(loading);
            this.field_146289_q.func_78276_b(loading, previewX + (90 - w) / 2, previewY + 45 - 4, -8355712);
            return;
        }
        int texW = imageData.getTotalWidth();
        int texH = imageData.getTotalHeight();
        if (texW <= 0 || texH <= 0) {
            return;
        }
        float scale = Math.min(90.0f / (float)texW, 90.0f / (float)texH);
        int drawW = (int)((float)texW * scale);
        int drawH = (int)((float)texH * scale);
        int drawX = previewX + (90 - drawW) / 2;
        int drawY = previewY + (90 - drawH) / 2;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        imageData.bindTexture();
        this.drawScaledTexture(drawX, drawY, drawW, drawH);
        String dims = texW + " x " + texH;
        int dw = this.field_146289_q.func_78256_a(dims);
        this.field_146289_q.func_78276_b(dims, previewX + (90 - dw) / 2, previewY + 90 + 4, -3355444);
        String path = this.selectedResource.toString();
        if (this.field_146289_q.func_78256_a(path) > 94) {
            while (this.field_146289_q.func_78256_a("..." + path) > 94 && path.length() > 1) {
                path = path.substring(1);
            }
            path = "..." + path;
        }
        int pw = this.field_146289_q.func_78256_a(path);
        this.field_146289_q.func_78276_b(path, previewX + (90 - pw) / 2, previewY + 90 + 15, -6710887);
    }

    private void drawScaledTexture(int x, int y, int width, int height) {
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78382_b();
        tess.func_78374_a((double)x, (double)(y + height), (double)this.field_73735_i, 0.0, 1.0);
        tess.func_78374_a((double)(x + width), (double)(y + height), (double)this.field_73735_i, 1.0, 1.0);
        tess.func_78374_a((double)(x + width), (double)y, (double)this.field_73735_i, 1.0, 0.0);
        tess.func_78374_a((double)x, (double)y, (double)this.field_73735_i, 0.0, 0.0);
        tess.func_78381_a();
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        super.func_146284_a(guibutton);
        if (guibutton.field_146127_k == 1) {
            this.selectedResource = null;
        }
        this.close();
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll scroll) {
        if (scroll == this.scrollTextures) {
            GuiTextureSelection.TextureData data;
            if (scroll.id == 1 && (data = this.textures.get(scroll.getSelected())) != null) {
                this.selectedResource = new ResourceLocation(this.selectedDomain, data.absoluteName);
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
            this.close();
        }
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.getTextField(55) != null && this.getTextField(55).func_146206_l() && !this.catSearch.equals(this.getTextField(55).func_146179_b())) {
            this.catSearch = this.getTextField(55).func_146179_b().toLowerCase();
            this.scrollCategories.resetScroll();
            if (this.selectedDomain == null) {
                this.scrollCategories.setList(this.getCatSearch());
            }
        }
        if (this.getTextField(66) != null && this.getTextField(66).func_146206_l() && !this.texSearch.equals(this.getTextField(66).func_146179_b())) {
            this.texSearch = this.getTextField(66).func_146179_b().toLowerCase();
            this.scrollTextures.resetScroll();
            this.func_73866_w_();
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

    private List<String> filterTexSearch(List<String> source) {
        if (this.texSearch.isEmpty()) {
            return source;
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String name : source) {
            if (!name.toLowerCase().contains(this.texSearch)) continue;
            list.add(name);
        }
        return list;
    }
}

