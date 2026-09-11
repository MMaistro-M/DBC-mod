/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.custom.components;

import java.util.Arrays;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import noppes.npcs.api.gui.ICustomGuiComponent;
import noppes.npcs.client.gui.custom.GuiCustom;
import noppes.npcs.client.gui.custom.interfaces.IClickListener;
import noppes.npcs.client.gui.custom.interfaces.IDataHolder;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.scripted.gui.ScriptGuiScroll;
import org.lwjgl.opengl.GL11;

public class CustomGuiScrollComponent
extends GuiCustomScroll
implements IDataHolder,
IClickListener {
    GuiCustom parent;
    String[] hoverText;
    public boolean multiSelect;
    int color;
    float alpha;

    public CustomGuiScrollComponent(Minecraft mc, GuiScreen parent, int id, boolean multiSelect) {
        super(parent, id, multiSelect);
        this.field_146297_k = mc;
        this.field_146289_q = mc.field_71466_p;
        this.multiSelect = multiSelect;
    }

    public void setParent(GuiCustom parent) {
        this.parent = parent;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void onRender(Minecraft mc, int mouseX, int mouseY, int mouseWheel, float partialTicks) {
        GL11.glPushMatrix();
        float red = (float)(this.color >> 16 & 0xFF) / 255.0f;
        float green = (float)(this.color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(this.color & 0xFF) / 255.0f;
        GL11.glColor4f((float)red, (float)green, (float)blue, (float)this.alpha);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)this.id);
        boolean hovered = mouseX >= this.guiLeft && mouseY >= this.guiTop && mouseX < this.guiLeft + this.xSize && mouseY < this.guiTop + this.ySize;
        super.drawScreen(mouseX, mouseY, partialTicks, mouseWheel);
        if (hovered && this.hoverText != null && this.hoverText.length > 0) {
            this.parent.hoverText = this.hoverText;
        }
        GL11.glPopMatrix();
    }

    @Override
    public boolean mouseClicked(GuiCustom gui, int mouseX, int mouseY, int mouseButton) {
        super.func_73864_a(mouseX, mouseY, mouseButton);
        return this.isMouseOver(mouseX, mouseY);
    }

    public void fromComponent(ScriptGuiScroll component) {
        int defaultSelect;
        this.guiLeft = GuiCustom.guiLeft + component.getPosX();
        this.guiTop = GuiCustom.guiTop + component.getPosY();
        this.setSize(component.getWidth(), component.getHeight());
        this.setUnsortedList(Arrays.asList(component.getList()));
        if (component.getDefaultSelection() >= 0 && (defaultSelect = component.getDefaultSelection()) < this.getList().size()) {
            this.selected = defaultSelect;
        }
        if (component.hasHoverText()) {
            this.hoverText = component.getHoverText();
        }
        this.color = component.getColor();
        this.alpha = component.getAlpha();
    }

    @Override
    public ICustomGuiComponent toComponent() {
        ScriptGuiScroll component = new ScriptGuiScroll(this.id, this.guiLeft - GuiCustom.guiLeft, this.guiTop - GuiCustom.guiTop, this.xSize, this.ySize, this.getList().toArray(new String[0]));
        component.setHoverText(this.hoverText);
        component.setColor(this.color);
        component.setAlpha(this.alpha);
        return component;
    }

    @Override
    public NBTTagCompound toNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.func_74768_a("id", this.id);
        if (!this.getSelectedList().isEmpty()) {
            NBTTagList tagList = new NBTTagList();
            for (String s : this.getSelectedList()) {
                tagList.func_74742_a((NBTBase)new NBTTagString(s));
            }
            nbt.func_74782_a("selectedList", (NBTBase)tagList);
        } else if (this.getSelected() != null && !this.getSelected().isEmpty()) {
            nbt.func_74778_a("selected", this.getSelected());
        } else {
            nbt.func_74778_a("selected", "Null");
        }
        return nbt;
    }
}

