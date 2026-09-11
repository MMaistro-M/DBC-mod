/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.gui;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.gui.GuiHelper;
import riskyken.armourersWorkshop.client.gui.controls.GuiColourSelector;
import riskyken.armourersWorkshop.client.gui.controls.GuiDropDownList;
import riskyken.armourersWorkshop.client.gui.controls.GuiHSBSlider;
import riskyken.armourersWorkshop.common.inventory.ContainerColourMixer;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiButton;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiColourUpdate;
import riskyken.armourersWorkshop.common.painting.PaintType;
import riskyken.armourersWorkshop.common.tileentities.TileEntityColourMixer;
import riskyken.armourersWorkshop.utils.UtilColour;

@SideOnly(value=Side.CLIENT)
public class GuiColourMixer
extends GuiContainer
implements GuiHSBSlider.IHSBSliderCallback,
GuiDropDownList.IDropDownListCallback {
    private TileEntityColourMixer tileEntityColourMixer;
    private static final ResourceLocation guiTexture = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/gui/colour-mixer.png");
    private Color colour;
    private GuiHSBSlider[] slidersHSB;
    private GuiTextField colourHex;
    private GuiColourSelector colourSelector;
    private GuiDropDownList colourFamilyList;
    private GuiDropDownList paintTypeDropDown;

    public GuiColourMixer(InventoryPlayer invPlayer, TileEntityColourMixer tileEntityColourMixer) {
        super((Container)new ContainerColourMixer(invPlayer, tileEntityColourMixer));
        this.tileEntityColourMixer = tileEntityColourMixer;
        this.field_146999_f = 256;
        this.field_147000_g = 240;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.colour = new Color(this.tileEntityColourMixer.getColour(0));
        float[] hsbvals = Color.RGBtoHSB(this.colour.getRed(), this.colour.getGreen(), this.colour.getBlue(), null);
        this.slidersHSB = new GuiHSBSlider[3];
        this.slidersHSB[0] = new GuiHSBSlider(0, this.field_147003_i + 5, this.field_147009_r + 30, 128, 10, this, GuiHSBSlider.HSBSliderType.HUE, hsbvals[0], hsbvals[0], hsbvals[2]);
        this.slidersHSB[1] = new GuiHSBSlider(1, this.field_147003_i + 5, this.field_147009_r + 50, 128, 10, this, GuiHSBSlider.HSBSliderType.SATURATION, hsbvals[1], hsbvals[0], hsbvals[2]);
        this.slidersHSB[2] = new GuiHSBSlider(2, this.field_147003_i + 5, this.field_147009_r + 70, 128, 10, this, GuiHSBSlider.HSBSliderType.BRIGHTNESS, hsbvals[2], hsbvals[0], hsbvals[2]);
        this.field_146292_n.add(this.slidersHSB[0]);
        this.field_146292_n.add(this.slidersHSB[1]);
        this.field_146292_n.add(this.slidersHSB[2]);
        this.colourHex = new GuiTextField(this.field_146289_q, this.field_147003_i + 5, this.field_147009_r + 90, 50, 10);
        this.colourHex.func_146203_f(7);
        this.updateHexTextbox();
        this.colourSelector = new GuiColourSelector(3, this.field_147003_i + 5, this.field_147009_r + 110, 82, 22, 10, 10, 8, guiTexture);
        this.field_146292_n.add(this.colourSelector);
        this.colourFamilyList = new GuiDropDownList(4, this.field_147003_i + 89, this.field_147009_r + 110, 82, "", this);
        for (int i = 0; i < UtilColour.ColourFamily.values().length; ++i) {
            UtilColour.ColourFamily cf = UtilColour.ColourFamily.values()[i];
            this.colourFamilyList.addListItem(cf.getLocalizedName());
        }
        UtilColour.ColourFamily cf = this.tileEntityColourMixer.getColourFamily();
        this.colourFamilyList.setListSelectedIndex(cf.ordinal());
        this.colourSelector.setColourFamily(cf);
        this.field_146292_n.add(this.colourFamilyList);
        this.paintTypeDropDown = new GuiDropDownList(5, this.field_147003_i + 170, this.field_147009_r + 30, 78, "", this);
        this.updatePaintTypeDropDown();
        this.field_146292_n.add(this.paintTypeDropDown);
    }

    private void checkForColourUpdates() {
        if (this.tileEntityColourMixer.getHasItemUpdateAndReset()) {
            this.colour = new Color(this.tileEntityColourMixer.getColour(0));
            this.updateSliders();
            this.updatePaintTypeDropDown();
        }
    }

    private void updatePaintTypeDropDown() {
        int paintCount = 0;
        this.paintTypeDropDown.clearList();
        for (int i = 0; i < PaintType.values().length; ++i) {
            PaintType paintType = PaintType.values()[i];
            if (i >= 12) continue;
            this.paintTypeDropDown.addListItem(paintType.getLocalizedName());
            if (paintType == this.tileEntityColourMixer.getPaintType(0)) {
                this.paintTypeDropDown.setListSelectedIndex(paintCount);
            }
            ++paintCount;
        }
    }

    private void updateSliders() {
        float[] hsbvals = Color.RGBtoHSB(this.colour.getRed(), this.colour.getGreen(), this.colour.getBlue(), null);
        this.slidersHSB[0].setValue(hsbvals[0]);
        this.slidersHSB[1].setValue(hsbvals[1]);
        this.slidersHSB[2].setValue(hsbvals[2]);
    }

    private void updateHexTextbox() {
        if (!this.colourHex.func_146206_l()) {
            this.colourHex.func_146180_a(String.format("#%02x%02x%02x", this.colour.getRed(), this.colour.getGreen(), this.colour.getBlue()));
        }
    }

    protected void func_146284_a(GuiButton button) {
        if (button.field_146127_k == 3) {
            this.colour = ((GuiColourSelector)button).getSelectedColour();
            this.updateHexTextbox();
            this.updateSliders();
        }
    }

    protected void func_73864_a(int mouseX, int mouseY, int button) {
        super.func_73864_a(mouseX, mouseY, button);
        this.colourHex.func_146192_a(mouseX, mouseY, button);
    }

    protected void func_146286_b(int mouseX, int mouseY, int which) {
        super.func_146286_b(mouseX, mouseY, which);
        if (which != 0) {
            return;
        }
        this.updateColour();
    }

    private void updateColour() {
        Color colourOld = new Color(this.tileEntityColourMixer.getColour(0));
        PaintType paintType = PaintType.values()[this.paintTypeDropDown.getListSelectedIndex()];
        if (this.colour.equals(colourOld) && paintType == this.tileEntityColourMixer.getPaintType(0)) {
            return;
        }
        MessageClientGuiColourUpdate message = new MessageClientGuiColourUpdate(this.colour.getRGB(), false, paintType);
        PacketHandler.networkWrapper.sendToServer((IMessage)message);
    }

    protected void func_73869_a(char key, int keyCode) {
        if (!this.colourHex.func_146201_a(key, keyCode)) {
            super.func_73869_a(key, keyCode);
        } else {
            Color newColour;
            String text = this.colourHex.func_146179_b();
            if (this.isValidHex(text) && !(newColour = Color.decode(text)).equals(this.colour)) {
                this.colour = newColour;
                this.updateSliders();
                this.updateColour();
            }
        }
    }

    private boolean isValidHex(String colorStr) {
        String hexPatten = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
        Pattern pattern = Pattern.compile(hexPatten);
        Matcher matcher = pattern.matcher(colorStr);
        return matcher.matches();
    }

    protected void func_146979_b(int mouseX, int mouseY) {
        GuiHelper.renderLocalizedGuiName(this.field_146289_q, this.field_146999_f, this.tileEntityColourMixer.func_145825_b());
        this.field_146289_q.func_78276_b(I18n.func_135052_a((String)"container.inventory", (Object[])new Object[0]), 48, this.field_147000_g - 96 + 2, 0x404040);
        String labelHue = GuiHelper.getLocalizedControlName(this.tileEntityColourMixer.func_145825_b(), "label.hue");
        String labelSaturation = GuiHelper.getLocalizedControlName(this.tileEntityColourMixer.func_145825_b(), "label.saturation");
        String labelBrightness = GuiHelper.getLocalizedControlName(this.tileEntityColourMixer.func_145825_b(), "label.brightness");
        String labelHex = GuiHelper.getLocalizedControlName(this.tileEntityColourMixer.func_145825_b(), "label.hex");
        String labelPresets = GuiHelper.getLocalizedControlName(this.tileEntityColourMixer.func_145825_b(), "label.presets");
        String labelPaintType = GuiHelper.getLocalizedControlName(this.tileEntityColourMixer.func_145825_b(), "label.paintType");
        this.field_146289_q.func_78276_b(labelHue + ":", 5, 21, 0x404040);
        this.field_146289_q.func_78276_b(labelSaturation + ":", 5, 41, 0x404040);
        this.field_146289_q.func_78276_b(labelBrightness + ":", 5, 61, 0x404040);
        this.field_146289_q.func_78276_b(labelHex + ":", 5, 81, 0x404040);
        this.field_146289_q.func_78276_b(labelPresets + ":", 5, 101, 0x404040);
        this.field_146289_q.func_78276_b(labelPaintType + ":", 171, 21, 0x404040);
        GL11.glPushMatrix();
        GL11.glTranslated((double)(-this.field_147003_i), (double)(-this.field_147009_r), (double)0.0);
        this.paintTypeDropDown.drawForeground(this.field_146297_k, mouseX, mouseY, 0.0f);
        this.colourFamilyList.drawForeground(this.field_146297_k, mouseX, mouseY, 0.0f);
        GL11.glPopMatrix();
    }

    protected void func_146976_a(float f, int x, int y) {
        this.checkForColourUpdates();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.func_71410_x().func_110434_K().func_110577_a(guiTexture);
        this.func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
        float red = (float)this.colour.getRed() / 255.0f;
        float green = (float)this.colour.getGreen() / 255.0f;
        float blue = (float)this.colour.getBlue() / 255.0f;
        GL11.glColor4f((float)red, (float)green, (float)blue, (float)1.0f);
        this.func_73729_b(this.field_147003_i + 146, this.field_147009_r + 52, 146, 52, 12, 13);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.colourHex.func_146194_f();
    }

    @Override
    public void valueUpdated(GuiHSBSlider source, double sliderValue) {
        float[] hsbvals = new float[]{(float)this.slidersHSB[0].getValue(), (float)this.slidersHSB[1].getValue(), (float)this.slidersHSB[2].getValue()};
        hsbvals[source.getType().ordinal()] = (float)sliderValue;
        this.colour = Color.getHSBColor(hsbvals[0], hsbvals[1], hsbvals[2]);
        this.updateHexTextbox();
        if (source.getType() == GuiHSBSlider.HSBSliderType.HUE) {
            this.slidersHSB[1].setHue((float)source.getValue());
        }
        if (source.getType() == GuiHSBSlider.HSBSliderType.BRIGHTNESS) {
            this.slidersHSB[1].setBrightness((float)source.getValue());
        }
    }

    @Override
    public void onDropDownListChanged(GuiDropDownList dropDownList) {
        if (dropDownList == this.colourFamilyList) {
            UtilColour.ColourFamily cf = UtilColour.ColourFamily.values()[dropDownList.getListSelectedIndex()];
            this.colourSelector.setColourFamily(cf);
            MessageClientGuiButton message = new MessageClientGuiButton((byte)cf.ordinal());
            PacketHandler.networkWrapper.sendToServer((IMessage)message);
        }
        if (dropDownList == this.paintTypeDropDown) {
            this.updateColour();
        }
    }
}

