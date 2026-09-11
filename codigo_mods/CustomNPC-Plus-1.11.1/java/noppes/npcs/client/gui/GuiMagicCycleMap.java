/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.gui.util.GuiDiagram;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.renderer.ImageData;
import noppes.npcs.constants.EnumDiagramLayout;
import noppes.npcs.controllers.MagicController;
import noppes.npcs.controllers.data.Magic;
import noppes.npcs.controllers.data.MagicAssociation;
import noppes.npcs.controllers.data.MagicCycle;
import org.lwjgl.opengl.GL11;

public class GuiMagicCycleMap
extends GuiDiagram {
    private List<Magic> cycleMagics;
    private MagicCycle cycle;

    public GuiMagicCycleMap(GuiNPCInterface parent, int x, int y, int width, int height, MagicCycle cycle) {
        super(parent, x, y, width, height);
        this.cycle = cycle;
        this.cycleMagics = new ArrayList<Magic>();
        if (this.cycle == null) {
            this.setLayout(EnumDiagramLayout.CIRCULAR_MANUAL);
            this.setCurvedArrows(true);
            this.setCurveAngle(-20);
            this.invalidateCache();
            return;
        }
        MagicController mc = MagicController.getInstance();
        if (cycle.associations != null) {
            for (MagicAssociation assoc : cycle.associations.values()) {
                Magic m = mc.getMagic(assoc.magicId);
                if (m == null) continue;
                this.cycleMagics.add(m);
            }
        }
        this.setLayout(cycle.layout != null ? cycle.layout : EnumDiagramLayout.CIRCULAR_MANUAL);
        this.setCurvedArrows(true);
        this.setCurveAngle(-25);
        this.invalidateCache();
        this.allowTwoWay = true;
        this.iconSize = 20;
        this.slotSize = this.iconSize + this.slotPadding;
    }

    public GuiMagicCycleMap(GuiNPCInterface parent, int x, int y, int width, int height, int cycleId) {
        this(parent, x, y, width, height, MagicController.getInstance().getCycle(cycleId));
    }

    @Override
    protected List<GuiDiagram.DiagramIcon> createIcons() {
        ArrayList<GuiDiagram.DiagramIcon> icons = new ArrayList<GuiDiagram.DiagramIcon>();
        if (this.cycleMagics == null || this.cycleMagics.isEmpty()) {
            return icons;
        }
        for (Magic m : this.cycleMagics) {
            if (m == null) continue;
            MagicIcon icon = new MagicIcon(m);
            icon.enabled = true;
            icon.pressable = true;
            MagicAssociation assoc = this.cycle.associations.get(m.id);
            if (assoc != null) {
                icon.index = assoc.index;
                icon.priority = assoc.priority;
            }
            icons.add(icon);
        }
        return icons;
    }

    @Override
    protected List<GuiDiagram.DiagramConnection> createConnections() {
        ArrayList<GuiDiagram.DiagramConnection> conns = new ArrayList<GuiDiagram.DiagramConnection>();
        if (this.cycleMagics == null || this.cycleMagics.isEmpty()) {
            return conns;
        }
        HashMap<Integer, Integer> orderMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < this.cycleMagics.size(); ++i) {
            orderMap.put(this.cycleMagics.get((int)i).id, i);
        }
        for (Magic m : this.cycleMagics) {
            if (m == null || m.interactions == null) continue;
            for (Map.Entry<Integer, Float> entry : m.interactions.entrySet()) {
                int targetId = entry.getKey();
                if (!orderMap.containsKey(targetId)) continue;
                float percent = entry.getValue().floatValue();
                String modifier = "";
                if ((double)percent > 0.0) {
                    modifier = modifier + "+";
                }
                String hover = modifier + (int)(percent * 100.0f) + "%";
                conns.add(new GuiDiagram.DiagramConnection(m.id, targetId, percent, hover));
            }
        }
        return conns;
    }

    @Override
    protected void renderIcon(GuiDiagram.DiagramIcon icon, int posX, int posY, GuiDiagram.IconRenderState state) {
        Magic magic = ((MagicIcon)icon).magic;
        Minecraft mc = Minecraft.func_71410_x();
        FontRenderer fontRenderer = mc.field_71466_p;
        int size = this.iconSize;
        float scale = (float)size / 16.0f;
        float renderX = (float)posX - 8.0f * scale;
        float renderY = (float)posY - 8.0f * scale;
        if (magic.item != null) {
            RenderHelper.func_74520_c();
            GL11.glPushMatrix();
            GL11.glTranslatef((float)renderX, (float)renderY, (float)0.0f);
            GL11.glScalef((float)scale, (float)scale, (float)1.0f);
            this.renderItem.func_82406_b(fontRenderer, mc.func_110434_K(), magic.item, 0, 0);
            GL11.glPopMatrix();
            RenderHelper.func_74518_a();
        } else if (magic.iconTexture != null && !magic.iconTexture.isEmpty()) {
            ImageData imageData = ClientCacheHandler.getImageData(magic.iconTexture);
            if (imageData != null && imageData.imageLoaded()) {
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glPushAttrib((int)1048575);
                GL11.glEnable((int)3008);
                imageData.bindTexture();
                int texWidth = imageData.getTotalWidth();
                int texX = posX - size / 2;
                int texY = posY - size / 2;
                GuiMagicCycleMap.func_152125_a((int)texX, (int)texY, (float)0.0f, (float)0.0f, (int)texWidth, (int)texWidth, (int)size, (int)size, (float)texWidth, (float)texWidth);
                GL11.glPopAttrib();
            }
        } else {
            RenderHelper.func_74520_c();
            GL11.glPushMatrix();
            GL11.glTranslatef((float)renderX, (float)renderY, (float)0.0f);
            GL11.glScalef((float)scale, (float)scale, (float)1.0f);
            ItemStack sword = new ItemStack(Items.field_151040_l);
            this.renderItem.func_82406_b(fontRenderer, mc.func_110434_K(), sword, 0, 0);
            GL11.glPopMatrix();
            RenderHelper.func_74518_a();
        }
        if (state == GuiDiagram.IconRenderState.NOT_HIGHLIGHTED) {
            int rectX = posX - size / 2;
            int rectY = posY - size / 2;
            GuiMagicCycleMap.func_73734_a((int)rectX, (int)rectY, (int)(rectX + size), (int)(rectY + size), (int)-2145378272);
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    @Override
    protected List<String> getIconTooltip(GuiDiagram.DiagramIcon icon) {
        Magic magic = ((MagicIcon)icon).magic;
        ArrayList<String> tooltip = new ArrayList<String>();
        tooltip.add(magic.getDisplayName());
        return tooltip;
    }

    @Override
    protected String getIconName(GuiDiagram.DiagramIcon icon) {
        Magic magic = ((MagicIcon)icon).magic;
        return magic.getName();
    }

    @Override
    protected void drawHoveringText(List<String> text, int mouseX, int mouseY, FontRenderer fontRenderer) {
        this.parent.renderHoveringText(text, mouseX, mouseY, fontRenderer);
    }

    private class MagicIcon
    extends GuiDiagram.DiagramIcon {
        public Magic magic;

        public MagicIcon(Magic magic) {
            super(magic.id, 0, 0);
            this.magic = magic;
        }
    }
}

