/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiControls
 *  net.minecraft.client.gui.GuiKeyBindingList
 *  net.minecraft.client.gui.GuiListExtended$IGuiListEntry
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.util.EnumChatFormatting
 */
package com.blamejared.controlling.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiKeyBindingList;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumChatFormatting;
import org.apache.commons.lang3.ArrayUtils;

@SideOnly(value=Side.CLIENT)
public class GuiNewKeyBindingList
extends GuiKeyBindingList {
    private final GuiControls controlsScreen;
    private final Minecraft mc;
    public List<GuiListExtended.IGuiListEntry> field_148190_m;
    public List<GuiListExtended.IGuiListEntry> allEntries = new ArrayList<GuiListExtended.IGuiListEntry>();
    private int maxListLabelWidth;

    public GuiNewKeyBindingList(GuiControls controls, Minecraft mcIn) {
        super(controls, mcIn);
        this.field_148155_a = controls.field_146294_l + 45;
        this.field_148158_l = controls.field_146295_m;
        this.field_148153_b = 63;
        this.field_148154_c = controls.field_146295_m - 80;
        this.field_148151_d = controls.field_146294_l + 45;
        this.controlsScreen = controls;
        this.mc = mcIn;
        Object[] keyBindings = ArrayUtils.clone(mcIn.field_71474_y.field_74324_K);
        Arrays.sort(keyBindings);
        String s = null;
        for (Object keybinding : keyBindings) {
            int i;
            String s1 = keybinding.func_151466_e();
            if (!s1.equals(s)) {
                s = s1;
                if (!s1.endsWith(".hidden")) {
                    this.allEntries.add(new CategoryEntry(s1));
                }
            }
            if ((i = mcIn.field_71466_p.func_78256_a(I18n.func_135052_a((String)keybinding.func_151464_g(), (Object[])new Object[0]))) > this.maxListLabelWidth) {
                this.maxListLabelWidth = i;
            }
            if (s1.endsWith(".hidden")) continue;
            this.allEntries.add(new KeyEntry((KeyBinding)keybinding));
        }
        this.field_148190_m = this.allEntries;
    }

    protected int func_148127_b() {
        return this.field_148190_m.size();
    }

    public GuiListExtended.IGuiListEntry func_148180_b(int index) {
        return this.field_148190_m.get(index);
    }

    public List<GuiListExtended.IGuiListEntry> getAllEntries() {
        return this.allEntries;
    }

    protected int func_148137_d() {
        return super.func_148137_d() + 15 + 20;
    }

    public int func_148139_c() {
        return super.func_148139_c() + 32;
    }

    public void setListEntries(List<GuiListExtended.IGuiListEntry> listEntries) {
        this.field_148190_m = listEntries;
    }

    @SideOnly(value=Side.CLIENT)
    public class KeyEntry
    implements GuiListExtended.IGuiListEntry {
        private final KeyBinding keybinding;
        private final String keyDesc;
        private final GuiButton btnChangeKeyBinding;
        private final GuiButton btnResetKeyBinding;

        private KeyEntry(KeyBinding name) {
            this.keybinding = name;
            this.keyDesc = I18n.func_135052_a((String)name.func_151464_g(), (Object[])new Object[0]);
            this.btnChangeKeyBinding = new GuiButton(2000, 0, 0, 95, 20, this.keyDesc);
            this.btnResetKeyBinding = new GuiButton(2001, 0, 0, 50, 20, I18n.func_135052_a((String)"controls.reset", (Object[])new Object[0]));
        }

        public void func_148279_a(int slotIndex, int x, int y, int listWidth, int slotHeight, Tessellator tessellator, int mouseX, int mouseY, boolean isSelected) {
            boolean flag = ((GuiNewKeyBindingList)GuiNewKeyBindingList.this).controlsScreen.field_146491_f == this.keybinding;
            ((GuiNewKeyBindingList)GuiNewKeyBindingList.this).mc.field_71466_p.func_78276_b(this.keyDesc, x + 90 - GuiNewKeyBindingList.this.maxListLabelWidth, y + slotHeight / 2 - ((GuiNewKeyBindingList)GuiNewKeyBindingList.this).mc.field_71466_p.field_78288_b / 2, 0xFFFFFF);
            this.btnResetKeyBinding.field_146128_h = x + 190 + 20;
            this.btnResetKeyBinding.field_146129_i = y;
            this.btnResetKeyBinding.field_146124_l = this.keybinding.func_151463_i() != this.keybinding.func_151469_h();
            this.btnResetKeyBinding.func_146112_a(GuiNewKeyBindingList.this.mc, mouseX, mouseY);
            this.btnChangeKeyBinding.field_146128_h = x + 105;
            this.btnChangeKeyBinding.field_146129_i = y;
            this.btnChangeKeyBinding.field_146126_j = GameSettings.func_74298_c((int)this.keybinding.func_151463_i());
            boolean flag1 = false;
            if (this.keybinding.func_151463_i() != 0) {
                for (KeyBinding keybinding : ((GuiNewKeyBindingList)GuiNewKeyBindingList.this).mc.field_71474_y.field_74324_K) {
                    if (keybinding == this.keybinding || this.keybinding.func_151463_i() != keybinding.func_151463_i()) continue;
                    flag1 = true;
                    break;
                }
            }
            if (flag) {
                this.btnChangeKeyBinding.field_146126_j = EnumChatFormatting.WHITE + "> " + EnumChatFormatting.YELLOW + this.btnChangeKeyBinding.field_146126_j + EnumChatFormatting.WHITE + " <";
            } else if (flag1) {
                this.btnChangeKeyBinding.field_146126_j = EnumChatFormatting.RED + this.btnChangeKeyBinding.field_146126_j;
            }
            this.btnChangeKeyBinding.func_146112_a(GuiNewKeyBindingList.this.mc, mouseX, mouseY);
            if (mouseY >= y && mouseY <= y + slotHeight) {
                ((GuiNewKeyBindingList)GuiNewKeyBindingList.this).mc.field_71466_p.func_78276_b(I18n.func_135052_a((String)this.keybinding.func_151466_e(), (Object[])new Object[0]), mouseX + 10, mouseY, 0xFFFFFF);
            }
        }

        public boolean func_148278_a(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY) {
            if (this.btnChangeKeyBinding.func_146116_c(GuiNewKeyBindingList.this.mc, mouseX, mouseY)) {
                ((GuiNewKeyBindingList)GuiNewKeyBindingList.this).controlsScreen.field_146491_f = this.keybinding;
                return true;
            }
            if (this.btnResetKeyBinding.func_146116_c(GuiNewKeyBindingList.this.mc, mouseX, mouseY)) {
                this.keybinding.func_151462_b(this.keybinding.func_151469_h());
                ((GuiNewKeyBindingList)GuiNewKeyBindingList.this).mc.field_71474_y.func_151440_a(this.keybinding, this.keybinding.func_151469_h());
                KeyBinding.func_74508_b();
                return true;
            }
            return false;
        }

        public void func_148277_b(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
            this.btnChangeKeyBinding.func_146118_a(x, y);
            this.btnResetKeyBinding.func_146118_a(x, y);
        }

        public KeyBinding getKeybinding() {
            return this.keybinding;
        }

        public String getKeyDesc() {
            return this.keyDesc;
        }
    }

    @SideOnly(value=Side.CLIENT)
    public class CategoryEntry
    implements GuiListExtended.IGuiListEntry {
        private final String labelText;
        private final int labelWidth;
        private final String name;

        public CategoryEntry(String name) {
            this.labelText = I18n.func_135052_a((String)name, (Object[])new Object[0]);
            this.labelWidth = ((GuiNewKeyBindingList)GuiNewKeyBindingList.this).mc.field_71466_p.func_78256_a(this.labelText);
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void func_148279_a(int slotIndex, int x, int y, int listWidth, int slotHeight, Tessellator tessellator, int mouseX, int mouseY, boolean isSelected) {
            ((GuiNewKeyBindingList)GuiNewKeyBindingList.this).mc.field_71466_p.func_78276_b(this.labelText, ((GuiNewKeyBindingList)GuiNewKeyBindingList.this).mc.field_71462_r.field_146294_l / 2 - this.labelWidth / 2, y + slotHeight - ((GuiNewKeyBindingList)GuiNewKeyBindingList.this).mc.field_71466_p.field_78288_b - 1, 0xFFFFFF);
        }

        public boolean func_148278_a(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY) {
            return false;
        }

        public void func_148277_b(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
        }
    }
}

