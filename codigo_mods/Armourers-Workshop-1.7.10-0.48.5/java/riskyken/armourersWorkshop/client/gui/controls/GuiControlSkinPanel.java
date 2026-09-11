/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  cpw.mods.fml.client.config.GuiButtonExt
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.controls;

import com.google.gson.JsonObject;
import cpw.mods.fml.client.config.GuiButtonExt;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.client.render.SkinItemRenderHelper;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinIdentifier;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;

@SideOnly(value=Side.CLIENT)
public class GuiControlSkinPanel
extends GuiButtonExt {
    private static final ResourceLocation TEXTURE = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/gui/controls/skin-panel.png");
    private final ArrayList<SkinIcon> iconList = new ArrayList();
    private int panelPadding;
    private int iconPadding;
    private int iconSize;
    private int iconCount;
    private int rowCount;
    private int colCount;
    private boolean showName;
    private SkinIcon lastPressedSkinIcon;

    public GuiControlSkinPanel() {
        this(0, 0, 0, 0);
    }

    public GuiControlSkinPanel(int xPos, int yPos, int width, int height) {
        super(0, xPos, yPos, width, height, "");
    }

    public void init(int x, int y, int width, int height) {
        this.field_146128_h = x;
        this.field_146129_i = y;
        this.field_146120_f = width;
        this.field_146121_g = height;
        this.panelPadding = 2;
        this.iconPadding = 5;
        this.iconSize = 50;
        this.showName = false;
        this.lastPressedSkinIcon = null;
        this.updateIconCount();
    }

    public void setIconSize(int iconSize) {
        this.iconSize = iconSize;
        this.updateIconCount();
    }

    public void setPanelPadding(int panelPadding) {
        this.panelPadding = panelPadding;
    }

    public void setShowName(boolean showName) {
        this.showName = showName;
    }

    public void updateIconCount() {
        int boxW = this.field_146120_f + this.iconPadding - this.panelPadding * 2;
        int boxH = this.field_146121_g + this.iconPadding - this.panelPadding * 2;
        this.rowCount = Math.max(1, (int)Math.floor(boxW / (this.iconSize + this.iconPadding)));
        this.colCount = Math.max(1, (int)Math.floor(boxH / (this.iconSize + this.iconPadding)));
        this.iconCount = this.rowCount * this.colCount;
    }

    public int getIconCount() {
        return this.iconCount;
    }

    public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
        if (this.field_146125_m) {
            this.field_146123_n = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
            int hover = this.func_146114_a(this.field_146123_n);
            this.func_73733_a(this.field_146128_h, this.field_146129_i, this.field_146128_h + this.field_146120_f, this.field_146129_i + this.field_146121_g, -1071504862, -801950925);
            for (int i = 0; i < this.iconList.size(); ++i) {
                int x = i % this.rowCount;
                int y = i / this.rowCount;
                int iconX = this.field_146128_h + x * (this.iconSize + this.iconPadding) + this.panelPadding;
                int iconY = this.field_146129_i + y * (this.iconSize + this.iconPadding) + this.panelPadding;
                SkinIcon skinIcon = this.iconList.get(i);
                if (y >= this.colCount) continue;
                skinIcon.drawIcon(iconX, iconY, mouseX, mouseY, this.iconSize, this.showName);
            }
        }
    }

    public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY) {
        for (int i = 0; i < this.iconList.size(); ++i) {
            int x = i % this.rowCount;
            int y = i / this.rowCount;
            int iconX = this.field_146128_h + x * (this.iconSize + this.iconPadding) + this.panelPadding;
            int iconY = this.field_146129_i + y * (this.iconSize + this.iconPadding) + this.panelPadding;
            SkinIcon skinIcon = this.iconList.get(i);
            if (y >= this.colCount || !skinIcon.mouseOver(iconX, iconY, mouseX, mouseY, this.iconSize)) continue;
            this.lastPressedSkinIcon = skinIcon;
            return true;
        }
        return false;
    }

    public SkinIcon getLastPressedSkinIcon() {
        return this.lastPressedSkinIcon;
    }

    public void clearIcons() {
        this.iconList.clear();
    }

    public void addIcon(JsonObject skinJson) {
        this.iconList.add(new SkinIcon(skinJson));
    }

    public class SkinIcon {
        private final JsonObject skinJson;
        private final int id;

        public SkinIcon(JsonObject skinJson) {
            this.skinJson = skinJson;
            this.id = skinJson.get("id").getAsInt();
        }

        public JsonObject getSkinJson() {
            return this.skinJson;
        }

        public void drawIcon(int x, int y, int mouseX, int mouseY, int iconSize, boolean showName) {
            if (this.mouseOver(x, y, mouseX, mouseY, iconSize)) {
                Gui.func_73734_a((int)x, (int)y, (int)(x + iconSize), (int)(y + iconSize), (int)-1065912559);
            } else {
                Gui.func_73734_a((int)x, (int)y, (int)(x + iconSize), (int)(y + iconSize), (int)0x22FFFFFF);
            }
            SkinIdentifier identifier = new SkinIdentifier(0, null, this.id, null);
            Skin skin = ClientSkinCache.INSTANCE.getSkin(identifier);
            if (skin != null) {
                IIcon skinIcon;
                if (showName) {
                    String name = this.skinJson.get("name").getAsString();
                    FontRenderer fontRenderer = Minecraft.func_71410_x().field_71466_p;
                    int size = fontRenderer.func_78256_a(skin.getCustomName());
                    List list = fontRenderer.func_78271_c(name, iconSize - 2);
                    int textY = y + iconSize;
                    fontRenderer.func_78279_b(name, x + 1, textY -= fontRenderer.field_78288_b * list.size(), iconSize - 2, -1118482);
                }
                if ((skinIcon = skin.getSkinType().getIcon()) != null) {
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    Minecraft mc = Minecraft.func_71410_x();
                    GL11.glDisable((int)2896);
                    GL11.glEnable((int)3042);
                    mc.func_110434_K().func_110577_a(TextureMap.field_110576_c);
                    GuiControlSkinPanel.this.func_94065_a(x, y, skinIcon, 10, 10);
                }
                float scale = iconSize / 2;
                GL11.glPushMatrix();
                GL11.glPushAttrib((int)8192);
                if (showName) {
                    GL11.glTranslatef((float)(x + iconSize / 2), (float)(y + iconSize / 2 - 4), (float)200.0f);
                } else {
                    GL11.glTranslatef((float)(x + iconSize / 2), (float)(y + iconSize / 2), (float)200.0f);
                }
                GL11.glScalef((float)-10.0f, (float)10.0f, (float)10.0f);
                if (this.mouseOver(x, y, mouseX, mouseY, iconSize)) {
                    GL11.glScalef((float)1.5f, (float)1.5f, (float)1.5f);
                }
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                float rotation = (float)((double)System.currentTimeMillis() / 10.0 % 360.0);
                GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
                RenderHelper.func_74519_b();
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glEnable((int)2977);
                GL11.glEnable((int)2903);
                ModRenderHelper.enableAlphaBlend();
                SkinItemRenderHelper.renderSkinAsItem(skin, new SkinPointer(skin), true, false, iconSize, iconSize);
                GL11.glPopAttrib();
                GL11.glPopMatrix();
            } else {
                Minecraft.func_71410_x().field_71446_o.func_110577_a(TEXTURE);
                int speed = 60;
                int frames = 18;
                int frame = (int)(System.currentTimeMillis() / (long)speed % (long)frames);
                int u = MathHelper.func_76128_c((double)(frame / 9));
                int v = frame - u * 9;
                Gui.func_152125_a((int)(x + 8), (int)(y + 8), (float)(u * 28), (float)(v * 28), (int)27, (int)27, (int)(iconSize - 16), (int)(iconSize - 16), (float)256.0f, (float)256.0f);
            }
        }

        public boolean mouseOver(int x, int y, int mouseX, int mouseY, int iconSize) {
            return mouseX >= x & mouseY >= y & mouseX < x + iconSize & mouseY < y + iconSize;
        }
    }
}

