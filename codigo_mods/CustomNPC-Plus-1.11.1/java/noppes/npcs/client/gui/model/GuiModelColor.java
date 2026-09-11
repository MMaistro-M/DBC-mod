/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.gui.util.GuiModelInterface;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.data.ModelPartData;
import org.lwjgl.opengl.GL11;

public class GuiModelColor
extends GuiModelInterface
implements ITextfieldListener {
    private GuiScreen parent;
    private static final ResourceLocation color = new ResourceLocation("customnpcs:textures/gui/color.png");
    private int colorX;
    private int colorY;
    private GuiNpcTextField textfield;
    private ModelPartData data;

    public GuiModelColor(GuiScreen parent, ModelPartData data, EntityCustomNpc npc) {
        super(npc);
        this.parent = parent;
        this.data = data;
        this.xOffset = 60;
        this.ySize = 230;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.colorX = this.guiLeft + 4;
        this.colorY = this.guiTop + 50;
        this.textfield = new GuiNpcTextField(0, this, this.guiLeft + 25, this.guiTop + 20, 70, 20, this.data.getColor());
        this.addTextField(this.textfield);
        this.textfield.func_146193_g(this.data.color);
    }

    @Override
    public void func_73869_a(char c, int i) {
        String prev = this.textfield.func_146179_b();
        super.func_73869_a(c, i);
        String newText = this.textfield.func_146179_b();
        if (newText.equals(prev)) {
            return;
        }
        try {
            int color;
            this.data.color = color = Integer.parseInt(this.textfield.func_146179_b(), 16);
            this.textfield.func_146193_g(color);
        }
        catch (NumberFormatException e) {
            this.textfield.func_146180_a(prev);
        }
    }

    @Override
    protected void func_146284_a(GuiButton btn) {
        super.func_146284_a(btn);
    }

    @Override
    public void close() {
        this.field_146297_k.func_147108_a(this.parent);
    }

    @Override
    public void func_73863_a(int par1, int par2, float par3) {
        super.func_73863_a(par1, par2, par3);
        this.field_146297_k.func_110434_K().func_110577_a(color);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.func_73729_b(this.colorX, this.colorY, 0, 0, 120, 120);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void func_73864_a(int i, int j, int k) {
        super.func_73864_a(i, j, k);
        if (i < this.colorX || i > this.colorX + 120 || j < this.colorY || j > this.colorY + 120) {
            return;
        }
        InputStream stream = null;
        try {
            IResource resource = this.field_146297_k.func_110442_L().func_110536_a(color);
            stream = resource.func_110527_b();
            BufferedImage bufferedimage = ImageIO.read(stream);
            int color = bufferedimage.getRGB((i - this.guiLeft - 4) * 4, (j - this.guiTop - 50) * 4) & 0xFFFFFF;
            if (color != 0) {
                this.data.color = color;
                this.textfield.func_146193_g(color);
                this.textfield.func_146180_a(this.data.getColor());
            }
        }
        catch (IOException iOException) {
        }
        finally {
            if (stream != null) {
                try {
                    stream.close();
                }
                catch (IOException iOException) {}
            }
        }
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        int color = 0;
        try {
            color = Integer.parseInt(textfield.func_146179_b(), 16);
        }
        catch (NumberFormatException e) {
            color = 0;
        }
        this.data.color = color;
        textfield.func_146193_g(color);
    }
}

