/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.inventory.Slot
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui.wardrobe.tab;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.gui.controls.GuiTabPanel;
import riskyken.armourersWorkshop.client.gui.wardrobe.GuiWardrobe;
import riskyken.armourersWorkshop.common.inventory.ContainerSkinWardrobe;

@SideOnly(value=Side.CLIENT)
public class GuiTabWardrobeOutfits
extends GuiTabPanel {
    public GuiTabWardrobeOutfits(int tabId, GuiScreen parent) {
        super(tabId, parent, false);
    }

    @Override
    public void drawBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int sloImageSize = 18;
        GuiContainer guiContainer = (GuiContainer)this.parent;
        ContainerSkinWardrobe skinWardrobe = (ContainerSkinWardrobe)guiContainer.field_147002_h;
        for (int i = skinWardrobe.getIndexOutfitStart(); i < skinWardrobe.getIndexOutfitEnd(); ++i) {
            Slot slot = (Slot)skinWardrobe.field_75151_b.get(i);
            this.func_73729_b(this.x + slot.field_75223_e - 1, this.y + slot.field_75221_f - 1, 238, 194, sloImageSize, sloImageSize);
        }
    }

    @Override
    public void drawForegroundLayer(int mouseX, int mouseY) {
        super.drawForegroundLayer(mouseX, mouseY);
        GL11.glPushMatrix();
        GL11.glTranslated((double)(-this.x), (double)(-this.y), (double)0.0);
        ((GuiWardrobe)this.parent).drawPlayerPreview(this.x, this.y, mouseX, mouseY);
        GL11.glPopMatrix();
    }
}

