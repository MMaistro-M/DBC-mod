/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.nbt.NBTTagCompound
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.customoverlay;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.api.overlay.ICustomOverlayComponent;
import noppes.npcs.client.gui.customoverlay.components.CustomOverlayLabel;
import noppes.npcs.client.gui.customoverlay.components.CustomOverlayLine;
import noppes.npcs.client.gui.customoverlay.components.CustomOverlayTexturedRect;
import noppes.npcs.client.gui.customoverlay.interfaces.IOverlayComponent;
import noppes.npcs.scripted.overlay.ScriptOverlay;
import noppes.npcs.scripted.overlay.ScriptOverlayComponent;
import noppes.npcs.scripted.overlay.ScriptOverlayLabel;
import noppes.npcs.scripted.overlay.ScriptOverlayLine;
import noppes.npcs.scripted.overlay.ScriptOverlayTexturedRect;
import org.lwjgl.opengl.GL11;

public class OverlayCustom
extends Gui {
    protected final Minecraft mc;
    private ScaledResolution res = null;
    public static int scaledWidth = 0;
    public static int scaledHeight = 0;
    public ScriptOverlay overlay;
    Map<Integer, IOverlayComponent> components = new HashMap<Integer, IOverlayComponent>();

    public OverlayCustom(Minecraft mc) {
        this.mc = mc;
    }

    public void initOverlay() {
        this.res = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
        if (this.overlay != null) {
            scaledWidth = this.res.func_78326_a();
            scaledHeight = this.res.func_78328_b();
            this.components.clear();
            for (ICustomOverlayComponent c : this.overlay.getComponents()) {
                this.addComponent(c);
            }
        }
    }

    private void addComponent(ICustomOverlayComponent component) {
        ScriptOverlayComponent c = (ScriptOverlayComponent)component;
        switch (c.getType()) {
            case 0: {
                CustomOverlayTexturedRect rect = CustomOverlayTexturedRect.fromComponent((ScriptOverlayTexturedRect)component);
                rect.setParent(this);
                this.components.put(rect.getID(), rect);
                break;
            }
            case 1: {
                CustomOverlayLabel lbl = CustomOverlayLabel.fromComponent((ScriptOverlayLabel)component);
                lbl.setParent(this);
                this.components.put(lbl.getID(), lbl);
                break;
            }
            case 2: {
                CustomOverlayLine line = CustomOverlayLine.fromComponent((ScriptOverlayLine)component);
                line.setParent(this);
                this.components.put(line.getID(), line);
            }
        }
    }

    public void renderGameOverlay(float partialTicks) {
        this.res = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
        scaledWidth = this.res.func_78326_a();
        scaledHeight = this.res.func_78328_b();
        Iterator<IOverlayComponent> var4 = this.components.values().iterator();
        while (var4.hasNext()) {
            GL11.glEnable((int)3042);
            OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
            GL11.glDisable((int)3008);
            IOverlayComponent component = var4.next();
            component.onRender(this.mc, partialTicks);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDisable((int)2896);
            GL11.glEnable((int)3008);
        }
    }

    public void setOverlayData(NBTTagCompound compound) {
        this.overlay = (ScriptOverlay)new ScriptOverlay().fromNBT(compound);
        this.initOverlay();
    }
}

