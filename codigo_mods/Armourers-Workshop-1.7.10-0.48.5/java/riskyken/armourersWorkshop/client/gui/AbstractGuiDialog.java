/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiUtils
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui;

import cpw.mods.fml.client.config.GuiUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiIconButton;

@SideOnly(value=Side.CLIENT)
public abstract class AbstractGuiDialog
extends Gui {
    protected static final ResourceLocation texture = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/gui/dialog.png");
    protected final GuiScreen parent;
    protected final String name;
    protected final IDialogCallback callback;
    protected final Minecraft mc;
    protected final FontRenderer fontRenderer;
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected ArrayList<GuiButton> buttonList;
    private GuiButton selectedButton;

    public AbstractGuiDialog(GuiScreen parent, String name, IDialogCallback callback, int width, int height) {
        this.parent = parent;
        this.name = name;
        this.callback = callback;
        this.mc = Minecraft.func_71410_x();
        this.fontRenderer = this.mc.field_71466_p;
        this.width = width;
        this.height = height;
        this.buttonList = new ArrayList();
    }

    public void initGui() {
        this.x = this.parent.field_146294_l / 2 - this.width / 2;
        this.y = this.parent.field_146295_m / 2 - this.height / 2;
    }

    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (mouseX < this.x | mouseX >= this.x + this.width | mouseY < this.y | mouseY >= this.y + this.height) {
            // empty if block
        }
        if (button == 0) {
            for (int i = 0; i < this.buttonList.size(); ++i) {
                GuiButton guiButton = this.buttonList.get(i);
                if (!guiButton.func_146116_c(this.mc, mouseX, mouseY)) continue;
                this.selectedButton = guiButton;
                guiButton.func_146113_a(this.mc.func_147118_V());
                this.actionPerformed(guiButton);
            }
        }
    }

    protected void actionPerformed(GuiButton button) {
    }

    public void mouseMovedOrUp(int mouseX, int mouseY, int button) {
        if (this.selectedButton != null && button == 0) {
            this.selectedButton.func_146118_a(mouseX, mouseY);
            this.selectedButton = null;
        }
    }

    public void mouseClickMove(int mouseX, int mouseY, int lastButtonClicked, long timeSinceMouseClick) {
    }

    public void returnDialogResult(DialogResult result) {
        if (this.callback != null) {
            this.callback.dialogResult(this, result);
        }
    }

    public boolean keyTyped(char c, int keycode) {
        if (keycode == 1 || keycode == this.mc.field_71474_y.field_151445_Q.func_151463_i()) {
            this.returnDialogResult(DialogResult.CANCEL);
            return true;
        }
        return false;
    }

    protected void drawParentCoverBackground() {
        this.func_73733_a(0, 0, this.parent.field_146294_l, this.parent.field_146295_m, -1072689136, -804253680);
    }

    protected void drawbuttons(int mouseX, int mouseY) {
        int i;
        for (i = 0; i < this.buttonList.size(); ++i) {
            this.buttonList.get(i).func_146112_a(this.mc, mouseX, mouseY);
        }
        for (i = 0; i < this.buttonList.size(); ++i) {
            if (!(this.buttonList.get(i) instanceof GuiIconButton)) continue;
            ((GuiIconButton)this.buttonList.get(i)).drawRollover(this.mc, mouseX, mouseY);
        }
    }

    public void draw(int mouseX, int mouseY, float partialTickTime) {
        RenderHelper.func_74520_c();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPushAttrib((int)8192);
        GL11.glDisable((int)32826);
        RenderHelper.func_74518_a();
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2929);
        this.drawBackground(mouseX, mouseY, partialTickTime);
        this.drawForeground(mouseX, mouseY, partialTickTime);
        GL11.glPopAttrib();
    }

    public void drawBackground(int mouseX, int mouseY, float partialTickTime) {
        this.drawParentCoverBackground();
        int textureWidth = 176;
        int textureHeight = 62;
        int borderSize = 4;
        this.mc.field_71446_o.func_110577_a(texture);
        GuiUtils.drawContinuousTexturedBox((int)this.x, (int)this.y, (int)0, (int)0, (int)this.width, (int)this.height, (int)textureWidth, (int)textureHeight, (int)borderSize, (float)this.field_73735_i);
    }

    public void drawForeground(int mouseX, int mouseY, float partialTickTime) {
        this.drawbuttons(mouseX, mouseY);
    }

    protected void drawTitle() {
        String title = GuiHelper.getLocalizedControlName(this.name, "title");
        int titleWidth = this.fontRenderer.func_78256_a(title);
        this.fontRenderer.func_78276_b(title, this.x + this.width / 2 - titleWidth / 2, this.y + 6, 0x404040);
    }

    public void update() {
    }

    public static enum DialogResult {
        OK,
        CANCEL;

    }

    public static interface IDialogCallback {
        public void dialogResult(AbstractGuiDialog var1, DialogResult var2);
    }
}

