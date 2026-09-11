/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.shader.Framebuffer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GL14
 */
package riskyken.armourersWorkshop.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import riskyken.armourersWorkshop.client.gui.controls.GuiBookButton;
import riskyken.armourersWorkshop.client.guidebook.IBook;
import riskyken.armourersWorkshop.client.guidebook.IBookPage;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.utils.ModLogger;

@SideOnly(value=Side.CLIENT)
public abstract class GuiBookBase
extends GuiScreen {
    protected static final ResourceLocation bookTexture = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/gui/guideBook.png");
    protected final int guiWidth;
    protected final int guiHeight;
    protected int guiLeft;
    protected int guiTop;
    protected final IBook book;
    private int pagePanelLeft;
    private int pagePanelRight;
    private GuiBookButton buttonBack;
    private GuiBookButton buttonForward;
    float turnAmount = 0.0f;
    int turningPageNumber = 0;
    private static Framebuffer fbo;

    public GuiBookBase(IBook book, int guiWidth, int guiHeight) {
        this.book = book;
        this.guiWidth = guiWidth;
        this.guiHeight = guiHeight;
        this.pagePanelLeft = 1;
        this.pagePanelRight = 2;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.guiLeft = this.field_146294_l / 2 - this.guiWidth / 2;
        this.guiTop = this.field_146295_m / 2 - this.guiHeight / 2;
        this.field_146292_n.clear();
        this.buttonBack = new GuiBookButton(0, this.guiLeft - 20, this.guiTop + 156, 3, 207, bookTexture);
        this.buttonForward = new GuiBookButton(1, this.guiLeft + 258, this.guiTop + 156, 3, 194, bookTexture);
        this.field_146292_n.add(this.buttonBack);
        this.field_146292_n.add(this.buttonForward);
    }

    protected void func_146284_a(GuiButton button) {
        if (button.field_146127_k == 0) {
            this.startPageTurnRight();
        }
        if (button.field_146127_k == 1) {
            this.startPageTurnLeft();
        }
        if (button.field_146127_k > 1) {
            // empty if block
        }
    }

    protected void func_73869_a(char key, int keyCode) {
        super.func_73869_a(key, keyCode);
        if (keyCode == 1 || keyCode == this.field_146297_k.field_71474_y.field_151445_Q.func_151463_i()) {
            this.field_146297_k.field_71439_g.func_71053_j();
        }
    }

    private void startPageTurnRight() {
        this.pagePanelLeft -= 2;
        this.pagePanelRight -= 2;
    }

    private void startPageTurnLeft() {
        this.pagePanelLeft += 2;
        this.pagePanelRight += 2;
    }

    public void func_73863_a(int mouseX, int mouseY, float partickTime) {
        super.func_73863_a(mouseX, mouseY, partickTime);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(bookTexture);
        this.func_73729_b(this.guiLeft, this.guiTop, 0, 0, this.guiWidth, this.guiHeight);
        this.buttonBack.field_146125_m = this.pagePanelLeft != 1;
        this.buttonForward.field_146125_m = this.pagePanelRight < this.book.getTotalNumberOfPages();
        this.renderPageText(this.pagePanelLeft, 0, mouseX, mouseY, false);
        this.renderPageText(this.pagePanelRight, 118, mouseX, mouseY, false);
    }

    private void renderPageText(int page, int left, int mouseX, int mouseY, boolean turning) {
        if (page < 1 | page > this.book.getTotalNumberOfPages()) {
            return;
        }
        IBookPage bookPage = this.book.getPageNumber(page);
        if (bookPage != null) {
            GL11.glPushMatrix();
            int xOffset = this.guiLeft + left + 10;
            int yOffset = this.guiTop + 7;
            GL11.glTranslatef((float)xOffset, (float)yOffset, (float)0.0f);
            bookPage.renderPage(this.field_146289_q, mouseX, mouseY, turning, page);
            GL11.glPopMatrix();
        } else {
            ModLogger.log("page was null");
        }
    }

    private void renderTurningPage() {
        this.turningPageNumber = this.pagePanelLeft;
        if (this.turningPageNumber < 1 | this.turningPageNumber > this.book.getTotalNumberOfPages()) {
            return;
        }
        IBookPage bookPage = this.book.getPageNumber(this.turningPageNumber);
        if (bookPage != null) {
            ModRenderHelper.disableAlphaBlend();
            this.enablePageFramebuffer();
            ModRenderHelper.enableAlphaBlend(1, 771);
            bookPage.renderPage(this.field_146289_q, 0, 0, true, this.turningPageNumber);
            this.disablePageFramebuffer();
            this.bindFramebufferTexture();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glColorMask((boolean)true, (boolean)true, (boolean)true, (boolean)false);
            GL11.glDisable((int)2929);
            GL11.glDepthMask((boolean)false);
            GL11.glDisable((int)2896);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            ModRenderHelper.enableAlphaBlend();
            int xOffset = this.guiLeft + 10;
            int yOffset = this.guiTop + 7;
            this.drawFboRec(xOffset, yOffset, 256, 256);
            ModRenderHelper.disableAlphaBlend();
            this.unbindFramebufferTexture();
        }
    }

    protected void enablePageFramebuffer() {
        this.field_146297_k.func_147110_a().func_147609_e();
        ScaledResolution reso = new ScaledResolution(this.field_146297_k, this.field_146297_k.field_71443_c, this.field_146297_k.field_71440_d);
        double scaleWidth = (double)this.field_146297_k.field_71443_c / reso.func_78327_c();
        double scaleHeight = (double)this.field_146297_k.field_71440_d / reso.func_78324_d();
        int fboScaledWidth = MathHelper.func_76143_f((double)(256.0 * scaleWidth));
        int fboScaledHeight = MathHelper.func_76143_f((double)(256.0 * scaleHeight));
        if (fbo == null) {
            fbo = new Framebuffer(fboScaledWidth, fboScaledHeight, true);
            fbo.func_147604_a(0.0f, 0.0f, 0.0f, 0.0f);
        }
        if (GuiBookBase.fbo.field_147621_c != fboScaledWidth | GuiBookBase.fbo.field_147618_d != fboScaledHeight) {
            fbo.func_147605_b(fboScaledWidth, fboScaledHeight);
            ModLogger.log("resizing fbo to scale: " + scaleHeight);
        }
        OpenGlHelper.func_153171_g((int)OpenGlHelper.field_153198_e, (int)GuiBookBase.fbo.field_147616_f);
        GL11.glClearColor((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
        GL11.glClear((int)16640);
        GL11.glClearDepth((double)1.0);
        GL11.glMatrixMode((int)5889);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glOrtho((double)0.0, (double)256.0, (double)256.0, (double)0.0, (double)1000.0, (double)3000.0);
        GL11.glViewport((int)0, (int)0, (int)fboScaledWidth, (int)fboScaledHeight);
        GL11.glMatrixMode((int)5888);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-2000.0f);
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3008);
        GL11.glClearColor((float)0.0f, (float)0.0f, (float)0.0f, (float)0.0f);
        ModRenderHelper.enableAlphaBlend();
        GL14.glBlendFuncSeparate((int)770, (int)771, (int)1, (int)771);
    }

    protected void disablePageFramebuffer() {
        GL11.glPopMatrix();
        GL11.glMatrixMode((int)5889);
        GL11.glLoadIdentity();
        GL11.glPopMatrix();
        GL11.glMatrixMode((int)5888);
        ModRenderHelper.disableAlphaBlend();
        this.field_146297_k.func_147110_a().func_147610_a(true);
    }

    protected void bindFramebufferTexture() {
        fbo.func_147612_c();
    }

    protected void unbindFramebufferTexture() {
        fbo.func_147606_d();
    }

    private void drawFboRec(int x, int y, int width, int height) {
        double zLevel = 1.0;
        new Tessellator();
        Tessellator tess = Tessellator.field_78398_a;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        tess.func_78382_b();
        tess.func_78369_a(1.0f, 1.0f, 1.0f, 1.0f);
        tess.func_78378_d(-1);
        tess.func_78374_a((double)x, (double)(y + height), zLevel, 0.0, 0.0);
        tess.func_78374_a((double)(x + width), (double)(y + height), zLevel, 1.0, 0.0);
        tess.func_78374_a((double)(x + width), (double)y, zLevel, 1.0, 1.0);
        tess.func_78374_a((double)x, (double)y, zLevel, 0.0, 1.0);
        tess.func_78381_a();
    }
}

