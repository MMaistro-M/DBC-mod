/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiUtils
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.audio.SoundHandler
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.controls;

import cpw.mods.fml.client.config.GuiUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.gui.controls.IGuiListItem;

@SideOnly(value=Side.CLIENT)
public class GuiList
extends Gui {
    private static final ResourceLocation texture = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/gui/controls/list.png");
    protected final Minecraft mc = Minecraft.func_71410_x();
    protected final FontRenderer fontRenderer;
    protected final int x;
    protected final int y;
    protected final int width;
    protected final int height;
    protected final int slotHeight;
    public final boolean enabled;
    public boolean visible;
    protected int scrollAmount;
    protected int selectedIndex;
    protected List<IGuiListItem> listItems;

    public GuiList(int x, int y, int width, int height, int slotHeight) {
        this.fontRenderer = this.mc.field_71466_p;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.slotHeight = slotHeight;
        this.listItems = new ArrayList<IGuiListItem>();
        this.enabled = true;
        this.visible = true;
        this.selectedIndex = -1;
    }

    public void clearList() {
        this.listItems.clear();
    }

    public void addListItem(IGuiListItem item) {
        this.listItems.add(item);
    }

    public void drawList(int mouseX, int mouseY, float tickTime) {
        if (!this.visible) {
            return;
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.mc.field_71446_o.func_110577_a(texture);
        GuiUtils.drawContinuousTexturedBox((ResourceLocation)texture, (int)this.x, (int)this.y, (int)0, (int)0, (int)this.width, (int)this.height, (int)11, (int)11, (int)1, (float)this.field_73735_i);
        ScaledResolution reso = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
        double scaleWidth = (double)this.mc.field_71443_c / reso.func_78327_c();
        double scaleHeight = (double)this.mc.field_71440_d / reso.func_78324_d();
        GL11.glEnable((int)3089);
        GL11.glScissor((int)((int)((double)(this.x + 1) * scaleWidth)), (int)(this.mc.field_71440_d - (int)((double)(this.y + this.height - 1) * scaleHeight)), (int)((int)((double)(this.width - 2) * scaleWidth)), (int)((int)((double)(this.height - 2) * scaleHeight)));
        for (int i = 0; i < this.listItems.size(); ++i) {
            int yLocation = this.y - this.scrollAmount + 2 + i * this.slotHeight;
            if (!(yLocation + 6 >= this.y & yLocation <= this.y + this.height + 1)) continue;
            this.listItems.get(i).drawListItem(this.fontRenderer, this.x + 2, yLocation, mouseX, mouseY, i == this.selectedIndex, this.width);
        }
        GL11.glDisable((int)3089);
    }

    public boolean mouseClicked(int mouseX, int mouseY, int button) {
        if (!this.visible) {
            return false;
        }
        for (int i = 0; i < this.listItems.size(); ++i) {
            int yLocation = this.y - this.scrollAmount + 2 + i * this.slotHeight;
            if (!(mouseY >= this.y & mouseY <= this.y + this.height - 2) || !this.listItems.get(i).mousePressed(this.fontRenderer, this.x + 2, yLocation, mouseX, mouseY, button, this.width)) continue;
            SoundHandler sh = this.mc.func_147118_V();
            sh.func_147682_a((ISound)PositionedSoundRecord.func_147674_a((ResourceLocation)new ResourceLocation("gui.button.press"), (float)1.0f));
            this.selectedIndex = i;
            return true;
        }
        return false;
    }

    public void mouseMovedOrUp(int mouseX, int mouseY, int button) {
        if (!this.visible) {
            return;
        }
        for (int i = 0; i < this.listItems.size(); ++i) {
            this.listItems.get(i).mouseReleased(this.fontRenderer, this.x, this.y, mouseX, mouseY, button, this.width);
        }
    }

    public IGuiListItem getSelectedListEntry() {
        if (this.selectedIndex >= 0 && this.selectedIndex < this.listItems.size()) {
            return this.listItems.get(this.selectedIndex);
        }
        return null;
    }

    public IGuiListItem getListEntry(int index) {
        return this.listItems.get(index);
    }

    public int getSelectedIndex() {
        return this.selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public void setScrollPercentage(int amount) {
        int listHeight = this.listItems.size() * this.slotHeight;
        int scrollRange = listHeight - this.height;
        if (scrollRange <= 0) {
            this.scrollAmount = 0;
            return;
        }
        this.scrollAmount = (int)((float)scrollRange / 100.0f * (float)amount);
    }

    public int getSize() {
        return this.listItems.size();
    }
}

