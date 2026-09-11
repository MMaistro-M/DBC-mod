/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package tconstruct.client.tabs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public abstract class AbstractTab
extends GuiButton {
    ResourceLocation texture = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
    ItemStack renderStack;
    RenderItem itemRenderer = new RenderItem();

    public AbstractTab(int id, int posX, int posY, ItemStack renderStack) {
        super(id, posX, posY, 28, 32, "");
        this.renderStack = renderStack;
    }

    public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
        if (this.field_146125_m) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            int yTexPos = this.field_146124_l ? 3 : 32;
            int ySize = this.field_146124_l ? 25 : 32;
            int xOffset = this.field_146127_k == 2 ? 0 : 1;
            int yPos = this.field_146129_i + (this.field_146124_l ? 3 : 0);
            mc.field_71446_o.func_110577_a(this.texture);
            this.func_73729_b(this.field_146128_h, yPos, xOffset * 28, yTexPos, 28, ySize);
            RenderHelper.func_74520_c();
            this.field_73735_i = 100.0f;
            this.itemRenderer.field_77023_b = 100.0f;
            GL11.glEnable((int)2896);
            GL11.glEnable((int)32826);
            this.itemRenderer.func_82406_b(mc.field_71466_p, mc.field_71446_o, this.renderStack, this.field_146128_h + 6, this.field_146129_i + 8);
            this.itemRenderer.func_77021_b(mc.field_71466_p, mc.field_71446_o, this.renderStack, this.field_146128_h + 6, this.field_146129_i + 8);
            GL11.glDisable((int)2896);
            GL11.glEnable((int)3042);
            this.itemRenderer.field_77023_b = 0.0f;
            this.field_73735_i = 0.0f;
            RenderHelper.func_74518_a();
        }
    }

    public boolean func_146116_c(Minecraft mc, int mouseX, int mouseY) {
        boolean inWindow;
        boolean bl = inWindow = this.field_146124_l && this.field_146125_m && mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
        if (inWindow) {
            this.onTabClicked();
        }
        return inWindow;
    }

    public abstract void onTabClicked();

    public abstract boolean shouldAddToList();
}

