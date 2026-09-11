/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.nbt.NBTTagCompound
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.custom.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.gui.ICustomGuiComponent;
import noppes.npcs.client.gui.custom.GuiCustom;
import noppes.npcs.client.gui.custom.interfaces.IClickListener;
import noppes.npcs.client.gui.custom.interfaces.IDataHolder;
import noppes.npcs.client.gui.custom.interfaces.IKeyListener;
import noppes.npcs.scripted.gui.ScriptGuiTextField;
import org.lwjgl.opengl.GL11;

public class CustomGuiTextField
extends GuiTextField
implements IKeyListener,
IDataHolder,
IClickListener {
    GuiCustom parent;
    String[] hoverText;
    int id;
    int color;
    float alpha;

    public CustomGuiTextField(int id, int x, int y, int width, int height) {
        super(Minecraft.func_71410_x().field_71466_p, GuiCustom.guiLeft + x, GuiCustom.guiTop + y, width, height);
        this.id = id;
        this.func_146203_f(500);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        this.func_146201_a(typedChar, keyCode);
    }

    @Override
    public void onRender(Minecraft mc, int mouseX, int mouseY, int mouseWheel, float partialTicks) {
        boolean hovered = mouseX >= this.field_146209_f && mouseY >= this.field_146210_g && mouseX < this.field_146209_f + this.field_146218_h && mouseY < this.field_146210_g + this.field_146219_i;
        float red = (float)(this.color >> 16 & 0xFF) / 255.0f;
        float green = (float)(this.color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(this.color & 0xFF) / 255.0f;
        GL11.glColor4f((float)red, (float)green, (float)blue, (float)this.alpha);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)this.id);
        this.func_146194_f();
        GL11.glPopMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (hovered && this.hoverText != null && this.hoverText.length > 0) {
            this.parent.hoverText = this.hoverText;
        }
    }

    public void setParent(GuiCustom parent) {
        this.parent = parent;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public NBTTagCompound toNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.func_74768_a("id", this.id);
        tag.func_74778_a("text", this.func_146179_b());
        return tag;
    }

    @Override
    public ICustomGuiComponent toComponent() {
        ScriptGuiTextField component = new ScriptGuiTextField(this.id, this.field_146209_f - GuiCustom.guiLeft, this.field_146210_g - GuiCustom.guiTop, this.field_146218_h, this.field_146219_i);
        component.setText(this.func_146179_b());
        component.setHoverText(this.hoverText);
        component.setColor(this.color);
        component.setAlpha(this.alpha);
        return component;
    }

    public static CustomGuiTextField fromComponent(ScriptGuiTextField component) {
        CustomGuiTextField txt = new CustomGuiTextField(component.getID(), component.getPosX(), component.getPosY(), component.getWidth(), component.getHeight());
        if (component.hasHoverText()) {
            txt.hoverText = component.getHoverText();
        }
        if (component.getText() != null && !component.getText().isEmpty()) {
            txt.func_146180_a(component.getText());
        }
        txt.color = component.getColor();
        txt.alpha = component.getAlpha();
        return txt;
    }

    @Override
    public boolean mouseClicked(GuiCustom gui, int mouseX, int mouseY, int mouseButton) {
        boolean flag;
        boolean bl = flag = mouseX >= this.field_146209_f && mouseX < this.field_146209_f + this.field_146218_h && mouseY >= this.field_146210_g && mouseY < this.field_146210_g + this.field_146219_i;
        if (this.func_146206_l() && mouseButton == 0 && !flag) {
            this.parent.onTextFieldUnfocused(this);
        }
        this.func_146192_a(mouseX, mouseY, mouseButton);
        return true;
    }
}

