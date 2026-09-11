/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.nbt.NBTTagCompound
 */
package noppes.npcs.client.gui.hud;

import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.nbt.NBTTagCompound;

public abstract class HudComponent {
    public float posX = 50.0f;
    public float posY = 50.0f;
    protected int scale = 100;
    protected int textAlign = 0;
    public boolean enabled = true;
    public boolean hasData = false;
    public boolean isEditting = false;
    public int overlayWidth = 200;
    public int overlayHeight = 120;

    public abstract void loadData(NBTTagCompound var1);

    public abstract void load();

    public abstract void save();

    public abstract void renderOnScreen(float var1);

    public abstract void renderEditing();

    public void addEditorButtons(List<GuiButton> buttonList) {
        String label = this.enabled ? "Enabled" : "Disabled";
        buttonList.add(new GuiButton(999, 0, 0, 120, 20, label));
    }

    public void onEditorButtonPressed(GuiButton button) {
        if (button.field_146127_k == 999) {
            this.enabled = !this.enabled;
            button.field_146126_j = this.enabled ? "Enabled" : "Disabled";
        }
    }

    public float getEffectiveScale(ScaledResolution res) {
        return (float)this.scale / 100.0f * ((float)res.func_78326_a() / 1920.0f);
    }
}

