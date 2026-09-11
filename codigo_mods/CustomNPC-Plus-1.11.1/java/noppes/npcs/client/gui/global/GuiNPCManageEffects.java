/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiYesNo
 *  net.minecraft.client.gui.GuiYesNoCallback
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.effects.EffectClonePacket;
import kamkeel.npcs.network.packets.request.effects.EffectGetPacket;
import kamkeel.npcs.network.packets.request.effects.EffectRemovePacket;
import kamkeel.npcs.network.packets.request.effects.EffectSavePacket;
import kamkeel.npcs.network.packets.request.effects.EffectsGetPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.SubGuiEffectGeneral;
import noppes.npcs.client.gui.global.GuiEffectDirectory;
import noppes.npcs.client.gui.player.inventory.GuiCNPCInventory;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.client.renderer.ImageData;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.controllers.CustomEffectController;
import noppes.npcs.controllers.data.CustomEffect;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.opengl.GL11;

public class GuiNPCManageEffects
extends GuiNPCInterface2
implements ICustomScrollListener,
IScrollData,
IGuiData,
ISubGuiListener,
GuiYesNoCallback {
    public GuiCustomScroll scrollEffects;
    public HashMap<String, Integer> data = new HashMap();
    public CustomEffect effect = new CustomEffect();
    public String selected = null;
    public String search = "";
    public String originalName = "";
    boolean setNormalSound = true;

    public GuiNPCManageEffects(EntityNPCInterface npc) {
        super(npc);
        PacketClient.sendClient(new EffectsGetPacket(-1));
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        GuiNpcButton fullBtn = new GuiNpcButton(66, this.guiLeft + 368, this.guiTop + 8, 45, 20, "gui.fullscreen");
        fullBtn.setTextColor(0x55FF55);
        fullBtn.setHoverText("gui.fullscreen.tooltip");
        this.addButton(fullBtn);
        this.addButton(new GuiNpcButton(0, this.guiLeft + 368, this.guiTop + 36, 45, 20, "gui.add"));
        this.addButton(new GuiNpcButton(1, this.guiLeft + 368, this.guiTop + 60, 45, 20, "gui.remove"));
        this.getButton((int)1).field_146124_l = this.effect != null && this.effect.id != -1;
        this.addButton(new GuiNpcButton(2, this.guiLeft + 368, this.guiTop + 84, 45, 20, "gui.copy"));
        this.getButton((int)2).field_146124_l = this.effect != null && this.effect.id != -1;
        this.addButton(new GuiNpcButton(3, this.guiLeft + 368, this.guiTop + 108, 45, 20, "gui.edit"));
        boolean bl = this.getButton((int)3).field_146124_l = this.effect != null && this.effect.id != -1;
        if (this.scrollEffects == null) {
            this.scrollEffects = new GuiCustomScroll((GuiScreen)this, 0, 0);
            this.scrollEffects.setSize(143, 185);
        }
        this.scrollEffects.guiLeft = this.guiLeft + 220;
        this.scrollEffects.guiTop = this.guiTop + 4;
        this.addScroll(this.scrollEffects);
        this.scrollEffects.setList(this.getSearchList());
        this.addTextField(new GuiNpcTextField(55, this, this.field_146289_q, this.guiLeft + 220, this.guiTop + 4 + 3 + 185, 143, 20, this.search));
        if (this.effect != null && this.effect.id != -1) {
            this.addLabel(new GuiNpcLabel(10, "ID", this.guiLeft + 368, this.guiTop + 4 + 3 + 185));
            this.addLabel(new GuiNpcLabel(11, this.effect.id + "", this.guiLeft + 368, this.guiTop + 4 + 3 + 195));
        }
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        GuiNpcButton button = (GuiNpcButton)guibutton;
        if (button.field_146127_k == 0) {
            this.save();
            String name = "New";
            while (this.data.containsKey(name)) {
                name = name + "_";
            }
            CustomEffect effect = new CustomEffect(-1, name);
            PacketClient.sendClient(new EffectSavePacket(effect.writeToNBT(false), ""));
        } else if (button.field_146127_k == 1) {
            if (this.data.containsKey(this.scrollEffects.getSelected())) {
                GuiYesNo guiyesno = new GuiYesNo((GuiYesNoCallback)this, this.scrollEffects.getSelected(), StatCollector.func_74838_a((String)"gui.delete"), 1);
                this.displayGuiScreen((GuiScreen)guiyesno);
            }
        } else if (button.field_146127_k == 2 && this.effect != null && this.effect.id >= 0) {
            PacketClient.sendClient(new EffectClonePacket(this.effect.id));
        }
        if (button.field_146127_k == 66) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiEffectDirectory(this.npc));
            return;
        }
        if (this.effect == null) {
            return;
        }
        if (button.field_146127_k == 3 && this.data.containsKey(this.scrollEffects.getSelected()) && this.effect != null && this.effect.id >= 0) {
            this.setSubGui(new SubGuiEffectGeneral(this, this.effect));
        }
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        this.effect = new CustomEffect();
        this.effect.readFromNBT(compound);
        this.setSelected(this.effect.name);
        if (this.effect.id != -1) {
            CustomEffectController.getInstance().getCustomEffects().replace(this.effect.id, this.effect);
        }
        this.func_73866_w_();
    }

    @Override
    public void func_73863_a(int i, int j, float f) {
        super.func_73863_a(i, j, f);
    }

    @Override
    public void drawBackground() {
        super.drawBackground();
        this.renderScreen();
    }

    private void renderScreen() {
        this.func_73733_a(this.guiLeft + 5, this.guiTop + 4, this.guiLeft + 218, this.guiTop + 24, -1072689136, -1072689136);
        this.func_73730_a(this.guiLeft + 5, this.guiLeft + 218, this.guiTop + 25, -16777216 + CustomNpcResourceListener.DefaultTextColor);
        this.func_73733_a(this.guiLeft + 5, this.guiTop + 27, this.guiLeft + 218, this.guiTop + this.ySize + 9, -1609560048, -1609560048);
        if (this.effect == null) {
            return;
        }
        if (this.effect.id == -1) {
            return;
        }
        String drawString = this.effect.getMenuName();
        int textWidth = this.getStringWidthWithoutColor(drawString);
        int centerX = this.guiLeft + 5 + (208 - textWidth) / 2;
        this.field_146289_q.func_85187_a(drawString, centerX, this.guiTop + 10, CustomNpcResourceListener.DefaultTextColor, true);
        int y = this.guiTop + 33;
        int x = this.guiLeft + 12;
        int iconRenderSize = 48;
        TextureManager textureManager = this.field_146297_k.func_110434_K();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ImageData data = ClientCacheHandler.getImageData(this.effect.icon);
        if (data.imageLoaded()) {
            data.bindTexture();
            int iconX = this.effect.iconX;
            int iconY = this.effect.iconY;
            int iconWidth = this.effect.getWidth();
            int iconHeight = this.effect.getHeight();
            int width = data.getTotalWidth();
            int height = data.getTotalHeight();
            GuiNPCManageEffects.func_152125_a((int)x, (int)y, (float)iconX, (float)iconY, (int)iconWidth, (int)iconHeight, (int)iconRenderSize, (int)iconRenderSize, (float)width, (float)height);
        } else {
            textureManager.func_110577_a(new ResourceLocation("customnpcs", "textures/marks/question.png"));
            GuiNPCManageEffects.func_152125_a((int)x, (int)y, (float)0.0f, (float)0.0f, (int)1, (int)1, (int)iconRenderSize, (int)iconRenderSize, (float)1.0f, (float)1.0f);
        }
        GL11.glDisable((int)2929);
        textureManager.func_110577_a(GuiCNPCInventory.specialIcons);
        GuiNPCManageEffects.func_152125_a((int)x, (int)y, (float)0.0f, (float)224.0f, (int)16, (int)16, (int)iconRenderSize, (int)iconRenderSize, (float)256.0f, (float)256.0f);
        GL11.glEnable((int)2929);
        String translated = StatCollector.func_74838_a((String)"gui.name") + ": " + this.effect.name;
        this.field_146289_q.func_85187_a(translated, x += iconRenderSize + 3, y += 2, 0xFFFFFF, false);
        int transLength = this.getStringWidthWithoutColor(translated);
        this.field_146289_q.func_85187_a(" (ID: " + this.effect.id + ")", x + transLength, y, 0xB5B5B5, false);
        this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"general.menuName") + ": " + this.effect.menuName, x, y += 12, 0xFFFFFF, false);
        this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"effect.runsEveryX") + ": " + this.effect.everyXTick + "t", x, y += 12, 0xB5B5B5, false);
        this.field_146289_q.func_85187_a(StatCollector.func_74838_a((String)"effect.defaultLength") + ": " + this.effect.length + "s", x, y += 12, 0xB5B5B5, false);
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.getTextField(55) != null && this.getTextField(55).func_146206_l()) {
            if (this.search.equals(this.getTextField(55).func_146179_b())) {
                return;
            }
            this.search = this.getTextField(55).func_146179_b().toLowerCase();
            this.scrollEffects.resetScroll();
            this.scrollEffects.setList(this.getSearchList());
        }
    }

    private List<String> getSearchList() {
        if (this.search.isEmpty()) {
            return new ArrayList<String>(this.data.keySet());
        }
        ArrayList<String> list = new ArrayList<String>();
        for (String name : this.data.keySet()) {
            if (!name.toLowerCase().contains(this.search)) continue;
            list.add(name);
        }
        return list;
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        String name = this.scrollEffects.getSelected();
        this.data = data;
        this.scrollEffects.setList(this.getSearchList());
        if (name != null) {
            this.scrollEffects.setSelected(name);
        }
    }

    @Override
    public void setSelected(String selected) {
        this.selected = selected;
        this.scrollEffects.setSelected(selected);
        this.originalName = this.scrollEffects.getSelected();
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        if (guiCustomScroll.id == 0) {
            this.save();
            this.effect = null;
            this.selected = this.scrollEffects.getSelected();
            this.originalName = this.scrollEffects.getSelected();
            if (this.selected != null && !this.selected.isEmpty()) {
                PacketClient.sendClient(new EffectGetPacket(this.data.get(this.selected)));
            }
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll scroll) {
    }

    @Override
    public void save() {
        if (this.selected != null && this.data.containsKey(this.selected) && this.effect != null) {
            PacketClient.sendClient(new EffectSavePacket(this.effect.writeToNBT(false), this.originalName));
        }
    }

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
    }

    public void func_73878_a(boolean result, int id) {
        NoppesUtil.openGUI((EntityPlayer)this.player, this);
        if (!result) {
            return;
        }
        if (id == 1 && this.data.containsKey(this.scrollEffects.getSelected())) {
            PacketClient.sendClient(new EffectRemovePacket(this.data.get(this.scrollEffects.getSelected())));
            this.scrollEffects.clear();
            this.effect = new CustomEffect();
            this.func_73866_w_();
        }
    }

    public int getStringWidthWithoutColor(String text) {
        int width = 0;
        for (int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            if (c == '\u00a7') {
                if (i >= text.length() - 1) continue;
                ++i;
                continue;
            }
            width += this.field_146289_q.func_78263_a(c);
        }
        return width;
    }
}

