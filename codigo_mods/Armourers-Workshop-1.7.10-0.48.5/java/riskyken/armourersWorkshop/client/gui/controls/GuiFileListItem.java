/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.controls;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.gui.controls.IGuiListItem;
import riskyken.armourersWorkshop.client.gui.skinlibrary.GuiSkinLibrary;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.client.render.SkinItemRenderHelper;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.common.library.LibraryFile;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinIdentifier;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.utils.UtilColour;

@SideOnly(value=Side.CLIENT)
public class GuiFileListItem
extends Gui
implements IGuiListItem {
    private static final ResourceLocation texture = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/gui/controls/list.png");
    private final LibraryFile file;

    public GuiFileListItem(LibraryFile file) {
        this.file = file;
    }

    public LibraryFile getFile() {
        return this.file;
    }

    @Override
    public void drawListItem(FontRenderer fontRenderer, int x, int y, int mouseX, int mouseY, boolean selected, int width) {
        int iconOffset = 0;
        if (GuiSkinLibrary.showModelPreviews() | this.file.isDirectory()) {
            iconOffset = 10;
        }
        int fontColour = UtilColour.getMinecraftColor(8, UtilColour.ColourFamily.MINECRAFT);
        if (this.isHovering(fontRenderer, x, y, mouseX, mouseY, width)) {
            Gui.func_73734_a((int)x, (int)y, (int)(x + width - 3), (int)(y + 12), (int)-3355444);
            fontColour = UtilColour.getMinecraftColor(15, UtilColour.ColourFamily.MINECRAFT);
        }
        if (selected) {
            Gui.func_73734_a((int)x, (int)y, (int)(x + width - 3), (int)(y + 12), (int)-120);
            fontColour = UtilColour.getMinecraftColor(15, UtilColour.ColourFamily.MINECRAFT);
        }
        if (!this.file.isDirectory()) {
            SkinIdentifier identifier;
            Skin skin;
            GuiFileListItem item;
            fontRenderer.func_78276_b(this.file.fileName, x + 2 + iconOffset, y + 2, fontColour);
            if (GuiSkinLibrary.showModelPreviews() | this.file.isDirectory() && (item = this) != null && (skin = ClientSkinCache.INSTANCE.getSkin(identifier = new SkinIdentifier(0, new LibraryFile(this.file.getFullName()), 0, null), true)) != null) {
                SkinPointer skinPointer = new SkinPointer(identifier);
                float scale = 10.0f;
                GL11.glPushMatrix();
                GL11.glPushAttrib((int)1048575);
                GL11.glTranslatef((float)((float)x + 5.0f), (float)((float)y + 6.0f), (float)50.0f);
                GL11.glScalef((float)(-scale), (float)scale, (float)scale);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                float rotation = (float)((double)System.currentTimeMillis() / 10.0 % 360.0);
                GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
                RenderHelper.func_74519_b();
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glEnable((int)2977);
                GL11.glEnable((int)2903);
                ModRenderHelper.enableAlphaBlend();
                SkinItemRenderHelper.renderSkinAsItem(skin, skinPointer, true, false, 16, 16);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glPopAttrib();
                GL11.glPopMatrix();
            }
        } else {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            Minecraft.func_71410_x().field_71446_o.func_110577_a(texture);
            if (this.file.fileName.equals("private")) {
                this.func_73729_b(x, y, 32, 0, 12, 12);
                fontRenderer.func_78276_b(this.file.fileName, x + 2 + iconOffset, y + 2, -7829249);
            } else {
                this.func_73729_b(x, y, 16, 0, 10, 10);
                fontRenderer.func_78276_b(this.file.fileName, x + 2 + iconOffset, y + 2, -7798904);
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
    }

    @Override
    public boolean mousePressed(FontRenderer fontRenderer, int x, int y, int mouseX, int mouseY, int button, int width) {
        return this.isHovering(fontRenderer, x, y, mouseX, mouseY, width);
    }

    @Override
    public void mouseReleased(FontRenderer fontRenderer, int x, int y, int mouseX, int mouseY, int button, int width) {
    }

    private boolean isHovering(FontRenderer fontRenderer, int x, int y, int mouseX, int mouseY, int width) {
        return mouseX >= x & mouseY >= y & mouseX <= x + width - 3 & mouseY <= y + 11;
    }

    @Override
    public String getDisplayName() {
        return this.file.fileName;
    }
}

