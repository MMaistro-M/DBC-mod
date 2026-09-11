/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.item;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.item.ColorBrushPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.IResource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.items.ItemNpcTool;
import org.lwjgl.opengl.GL11;

public class GuiNpcPaintbrush
extends GuiNPCInterface {
    private GuiNpcTextField textfield;
    private int color = 0xFFFFFF;
    private int colorX;
    private int colorY;
    private static final ResourceLocation resource = new ResourceLocation("customnpcs:textures/gui/color.png");

    public GuiNpcPaintbrush() {
        this.xSize = 256;
        this.setBackground("menubg.png");
        ItemStack brush = this.player.func_70694_bm();
        if (brush == null) {
            return;
        }
        this.color = ItemNpcTool.getColor(brush.func_77978_p());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.colorX = this.guiLeft + 20;
        this.colorY = this.guiTop + 50;
        this.textfield = new GuiNpcTextField(0, this, this.guiLeft + 43, this.guiTop + 20, 70, 20, this.getColor());
        this.addTextField(this.textfield);
        this.textfield.func_146193_g(this.color);
        int margin = 10;
        this.addButton(new GuiNpcButton(66, this.guiLeft + this.xSize - 55 - margin, this.guiTop + this.ySize - 20 - margin, 60, 20, "gui.done"));
    }

    public String getColor() {
        String str = Integer.toHexString(this.color);
        while (str.length() < 6) {
            str = "0" + str;
        }
        return str;
    }

    @Override
    public void func_73869_a(char c, int i) {
        String prev = this.textfield.func_146179_b();
        super.func_73869_a(c, i);
        String newText = this.textfield.func_146179_b();
        if (!newText.equals(prev)) {
            try {
                this.color = Integer.parseInt(newText, 16);
                this.textfield.func_146193_g(this.color);
            }
            catch (NumberFormatException e) {
                this.textfield.func_146180_a(prev);
            }
        }
        if (i == 1) {
            this.close();
        }
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        if (guibutton.field_146127_k == 66) {
            this.close();
        }
    }

    @Override
    public void close() {
        PacketClient.sendClient(new ColorBrushPacket(this.color));
        super.close();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void func_73864_a(int i, int j, int k) {
        super.func_73864_a(i, j, k);
        if (i < this.colorX || i > this.colorX + 117 || j < this.colorY || j > this.colorY + 117) {
            return;
        }
        InputStream stream = null;
        try {
            IResource iresource = this.field_146297_k.func_110442_L().func_110536_a(resource);
            stream = iresource.func_110527_b();
            BufferedImage bufferedimage = ImageIO.read(stream);
            int imgX = (i - this.guiLeft - 20) * 4;
            int imgY = (j - this.guiTop - 50) * 4;
            this.color = bufferedimage.getRGB(imgX, imgY) & 0xFFFFFF;
            this.textfield.func_146193_g(this.color);
            this.textfield.func_146180_a(this.getColor());
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
    public void func_73863_a(int par1, int par2, float par3) {
        super.func_73863_a(par1, par2, par3);
        this.field_146297_k.func_110434_K().func_110577_a(resource);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.func_73729_b(this.colorX, this.colorY, 0, 0, 120, 120);
    }

    @Override
    public void save() {
    }
}

