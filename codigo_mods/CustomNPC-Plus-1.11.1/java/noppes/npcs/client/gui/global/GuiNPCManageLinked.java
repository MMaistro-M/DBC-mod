/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiYesNo
 *  net.minecraft.client.gui.GuiYesNoCallback
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.global;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.linked.LinkedGetAllPacket;
import kamkeel.npcs.network.packets.request.linked.LinkedGetPacket;
import kamkeel.npcs.network.packets.request.linked.LinkedItemBuildPacket;
import kamkeel.npcs.network.packets.request.linked.LinkedItemClonePacket;
import kamkeel.npcs.network.packets.request.linked.LinkedItemRemovePacket;
import kamkeel.npcs.network.packets.request.linked.LinkedItemSavePacket;
import kamkeel.npcs.network.packets.request.linked.LinkedNPCAddPacket;
import kamkeel.npcs.network.packets.request.linked.LinkedNPCRemovePacket;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.NoppesUtil;
import noppes.npcs.client.gui.SubGuiEditText;
import noppes.npcs.client.gui.global.GuiLinkedItemDirectory;
import noppes.npcs.client.gui.item.SubGuiLinkedItem;
import noppes.npcs.client.gui.util.GuiCustomScroll;
import noppes.npcs.client.gui.util.GuiNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ICustomScrollListener;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.IScrollData;
import noppes.npcs.client.gui.util.ISubGuiListener;
import noppes.npcs.client.gui.util.SubGuiInterface;
import noppes.npcs.client.renderer.ImageData;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.controllers.data.LinkedItem;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiNPCManageLinked
extends GuiNPCInterface2
implements IScrollData,
ISubGuiListener,
ICustomScrollListener,
IGuiData,
GuiYesNoCallback {
    private static int tab = 0;
    private boolean loadedNPC = false;
    private GuiCustomScroll scroll;
    public HashMap<String, Integer> data = new HashMap();
    private String selected = null;
    private LinkedItem linkedItem = null;
    public String originalName = "";
    private String search = "";
    private float zoomed = 36.0f;
    private float rotation;

    public GuiNPCManageLinked(EntityNPCInterface npc) {
        super(npc);
        this.resetNPC();
        if (tab == 0) {
            LinkedGetAllPacket.GetNPCs();
        } else if (tab == 1) {
            LinkedGetAllPacket.GetItems();
        }
    }

    public void resetNPC() {
        this.npc = new EntityCustomNpc((World)Minecraft.func_71410_x().field_71441_e);
        this.npc.display.name = "Linked NPC";
        this.npc.field_70131_O = 1.62f;
        this.npc.field_70130_N = 0.43f;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        int y = this.guiTop + 8;
        if (tab == 1) {
            GuiNpcButton fullBtn = new GuiNpcButton(66, this.guiLeft + 368, y, 45, 20, "gui.fullscreen");
            fullBtn.setTextColor(0x55FF55);
            fullBtn.setHoverText("gui.fullscreen.tooltip");
            this.addButton(fullBtn);
            y += 22;
        }
        this.addButton(new GuiNpcButton(10, this.guiLeft + 368, y, 45, 20, "gui.npcs"));
        this.addButton(new GuiNpcButton(11, this.guiLeft + 368, y += 22, 45, 20, "gui.items"));
        this.getButton((int)10).field_146124_l = tab == 1;
        this.getButton((int)11).field_146124_l = tab == 0;
        this.addButton(new GuiNpcButton(1, this.guiLeft + 368, y += 40, 45, 20, "gui.add"));
        this.addButton(new GuiNpcButton(2, this.guiLeft + 368, y += 22, 45, 20, "gui.remove"));
        this.addButton(new GuiNpcButton(3, this.guiLeft + 368, y += 22, 45, 20, "gui.edit"));
        this.addButton(new GuiNpcButton(4, this.guiLeft + 368, y += 22, 45, 20, "gui.copy"));
        this.addButton(new GuiNpcButton(5, this.guiLeft + 368, y += 22, 45, 20, "gui.build"));
        this.getButton((int)3).field_146124_l = tab == 1;
        this.getButton((int)4).field_146124_l = tab == 1;
        boolean bl = this.getButton((int)5).field_146124_l = tab == 1;
        if (this.scroll == null) {
            this.scroll = new GuiCustomScroll((GuiScreen)this, 0, 0);
            this.scroll.setSize(143, 185);
        }
        this.scroll.guiLeft = this.guiLeft + 220;
        this.scroll.guiTop = this.guiTop + 4;
        this.scroll.setList(this.getSearchList());
        this.addScroll(this.scroll);
        this.addTextField(new GuiNpcTextField(55, this, this.field_146289_q, this.guiLeft + 220, this.guiTop + 4 + 3 + 185, 143, 20, this.search));
    }

    @Override
    public void func_73869_a(char c, int i) {
        super.func_73869_a(c, i);
        if (this.getTextField(55) != null && this.getTextField(55).func_146206_l()) {
            if (this.search.equals(this.getTextField(55).func_146179_b())) {
                return;
            }
            this.search = this.getTextField(55).func_146179_b().toLowerCase();
            this.scroll.setList(this.getSearchList());
            this.scroll.resetScroll();
        }
    }

    @Override
    public void func_73863_a(int i, int j, float f) {
        super.func_73863_a(i, j, f);
        if (this.hasSubGui()) {
            return;
        }
        if (tab == 0) {
            if (this.isMouseOverRenderer(i, j)) {
                this.zoomed += (float)Mouse.getDWheel() * 0.035f;
                if (this.zoomed > 100.0f) {
                    this.zoomed = 100.0f;
                }
                if (this.zoomed < 10.0f) {
                    this.zoomed = 10.0f;
                }
                if (Mouse.isButtonDown((int)0) || Mouse.isButtonDown((int)1)) {
                    this.rotation -= (float)Mouse.getDX() * 0.75f;
                }
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            EntityNPCInterface entity = this.npc;
            int l = this.guiLeft + 150;
            int i1 = this.guiTop + 198;
            GL11.glEnable((int)2903);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)l, (float)i1, (float)60.0f);
            GL11.glScalef((float)(-this.zoomed), (float)this.zoomed, (float)this.zoomed);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            float f2 = ((EntityLivingBase)entity).field_70761_aq;
            float f3 = ((EntityLivingBase)entity).field_70177_z;
            float f4 = ((EntityLivingBase)entity).field_70125_A;
            float f7 = ((EntityLivingBase)entity).field_70759_as;
            float f5 = (float)l - (float)i;
            float f6 = (float)(i1 - 50) - (float)j;
            GL11.glRotatef((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            RenderHelper.func_74519_b();
            GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(-((float)Math.atan(f6 / 800.0f)) * 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            ((EntityLivingBase)entity).field_70760_ar = ((EntityLivingBase)entity).field_70761_aq = this.rotation;
            ((EntityLivingBase)entity).field_70126_B = ((EntityLivingBase)entity).field_70177_z = (float)Math.atan(f5 / 80.0f) * 40.0f + this.rotation;
            ((EntityLivingBase)entity).field_70125_A = -((float)Math.atan(f6 / 80.0f)) * 20.0f;
            ((EntityLivingBase)entity).field_70758_at = ((EntityLivingBase)entity).field_70759_as = ((EntityLivingBase)entity).field_70177_z;
            GL11.glTranslatef((float)0.0f, (float)((EntityLivingBase)entity).field_70129_M, (float)1.0f);
            RenderManager.field_78727_a.field_78735_i = 180.0f;
            GL11.glPushMatrix();
            try {
                RenderManager.field_78727_a.func_147940_a((Entity)entity, 0.0, 0.0, 0.0, 0.0f, 1.0f);
            }
            catch (Exception exception) {
                // empty catch block
            }
            GL11.glPopMatrix();
            ((EntityLivingBase)entity).field_70760_ar = ((EntityLivingBase)entity).field_70761_aq = f2;
            ((EntityLivingBase)entity).field_70126_B = ((EntityLivingBase)entity).field_70177_z = f3;
            ((EntityLivingBase)entity).field_70125_A = f4;
            ((EntityLivingBase)entity).field_70758_at = ((EntityLivingBase)entity).field_70759_as = f7;
            RenderHelper.func_74518_a();
            GL11.glDisable((int)32826);
            OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77476_b);
            GL11.glDisable((int)3553);
            OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77478_a);
            GL11.glClear((int)256);
            GL11.glPopMatrix();
        } else if (tab == 1 && this.linkedItem != null) {
            int x = this.guiLeft + 155;
            int y = this.guiTop + 30;
            int iconRenderSize = 64;
            TextureManager textureManager = this.field_146297_k.func_110434_K();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            ImageData imageData = ClientCacheHandler.getImageData(this.linkedItem.display.texture);
            if (imageData.imageLoaded()) {
                float[] colors = ColorUtil.hexToRGB(this.linkedItem.display.itemColor);
                GL11.glColor3f((float)colors[0], (float)colors[1], (float)colors[2]);
                imageData.bindTexture();
                boolean iconX = false;
                boolean iconY = false;
                int iconWidth = imageData.getTotalWidth();
                int iconHeight = imageData.getTotalHeight();
                int width = imageData.getTotalWidth();
                int height = imageData.getTotalHeight();
                GuiNPCManageLinked.func_152125_a((int)x, (int)y, (float)((float)iconX), (float)((float)iconY), (int)iconWidth, (int)iconHeight, (int)iconRenderSize, (int)iconRenderSize, (float)width, (float)height);
            } else {
                textureManager.func_110577_a(new ResourceLocation("customnpcs", "textures/marks/question.png"));
                GuiNPCManageLinked.func_152125_a((int)x, (int)y, (float)0.0f, (float)0.0f, (int)1, (int)1, (int)iconRenderSize, (int)iconRenderSize, (float)1.0f, (float)1.0f);
            }
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDisable((int)2929);
        }
    }

    private List<String> getSearchList() {
        if (this.data == null) {
            return new ArrayList<String>();
        }
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
    public void buttonEvent(GuiButton button) {
        if (button.field_146127_k == 1) {
            if (tab == 0) {
                this.setSubGui(new SubGuiEditText("New"));
            } else {
                String name = "New";
                while (this.data.containsKey(name)) {
                    name = name + "_";
                }
                LinkedItem linkedItem = new LinkedItem(name);
                PacketClient.sendClient(new LinkedItemSavePacket(linkedItem.writeToNBT(false), ""));
            }
        }
        if (button.field_146127_k == 2) {
            GuiYesNo guiyesno;
            if (tab == 0) {
                if (this.data.containsKey(this.scroll.getSelected())) {
                    guiyesno = new GuiYesNo((GuiYesNoCallback)this, this.scroll.getSelected(), StatCollector.func_74838_a((String)"gui.delete"), 0);
                    this.displayGuiScreen((GuiScreen)guiyesno);
                }
            } else if (this.data.containsKey(this.scroll.getSelected())) {
                guiyesno = new GuiYesNo((GuiYesNoCallback)this, this.scroll.getSelected(), StatCollector.func_74838_a((String)"gui.delete"), 1);
                this.displayGuiScreen((GuiScreen)guiyesno);
            }
        }
        if (button.field_146127_k == 10) {
            tab = 0;
            this.resetNPC();
            LinkedGetAllPacket.GetNPCs();
            this.scroll.setSelected("");
        }
        if (button.field_146127_k == 11) {
            tab = 1;
            LinkedGetAllPacket.GetItems();
            this.scroll.setSelected("");
        }
        if (button.field_146127_k == 66) {
            this.field_146297_k.func_147108_a((GuiScreen)new GuiLinkedItemDirectory(this.npc));
            return;
        }
        if (this.linkedItem == null) {
            return;
        }
        if (button.field_146127_k == 3) {
            this.setSubGui(new SubGuiLinkedItem(this, this.linkedItem));
        }
        if (button.field_146127_k == 4 && this.data.containsKey(this.scroll.getSelected()) && this.linkedItem != null && this.linkedItem.id >= 0) {
            PacketClient.sendClient(new LinkedItemClonePacket(this.linkedItem.id));
        }
        if (button.field_146127_k == 5 && tab == 1) {
            PacketClient.sendClient(new LinkedItemBuildPacket(this.linkedItem.getId()));
        }
    }

    public void func_73878_a(boolean result, int id) {
        NoppesUtil.openGUI((EntityPlayer)this.player, this);
        if (!result) {
            return;
        }
        if (id == 0 && this.data.containsKey(this.scroll.getSelected())) {
            PacketClient.sendClient(new LinkedNPCRemovePacket(this.scroll.getSelected()));
            this.func_73866_w_();
        }
        if (id == 1 && this.data.containsKey(this.scroll.getSelected())) {
            PacketClient.sendClient(new LinkedItemRemovePacket(this.data.get(this.scroll.getSelected())));
            this.func_73866_w_();
        }
    }

    @Override
    public void drawBackground() {
        super.drawBackground();
        this.renderScreen();
    }

    private void renderScreen() {
        int xValue;
        int xLabel;
        int y;
        int centerX;
        int textWidth;
        String topBarText;
        this.func_73733_a(this.guiLeft + 5, this.guiTop + 4, this.guiLeft + 218, this.guiTop + 24, -1072689136, -1072689136);
        this.func_73730_a(this.guiLeft + 5, this.guiLeft + 218, this.guiTop + 25, -16777216 + CustomNpcResourceListener.DefaultTextColor);
        this.func_73733_a(this.guiLeft + 5, this.guiTop + 27, this.guiLeft + 218, this.guiTop + this.ySize + 9, -1609560048, -1609560048);
        if (tab == 0 && this.loadedNPC && this.npc != null) {
            topBarText = this.npc.display.getName();
            textWidth = this.getStringWidthWithoutColor(topBarText);
            centerX = this.guiLeft + 5 + (208 - textWidth) / 2;
            this.field_146289_q.func_85187_a(topBarText, centerX, this.guiTop + 10, this.npc.getFaction().color, true);
            y = this.guiTop + 30;
            xLabel = this.guiLeft + 8;
            xValue = this.guiLeft + 120;
            int valueColor = 0xFFFFFF;
            String label = StatCollector.func_74838_a((String)"stats.health") + ": ";
            String value = "" + this.npc.stats.maxHealth;
            this.field_146289_q.func_85187_a(label, xLabel, y, 2741945, false);
            this.field_146289_q.func_85187_a(value, xValue, y, valueColor, false);
            label = StatCollector.func_74838_a((String)"stats.meleestrength") + ": ";
            value = "" + this.npc.stats.getAttackStrength();
            this.field_146289_q.func_85187_a(label, xLabel, y += 15, 16733972, false);
            this.field_146289_q.func_85187_a(value, xValue, y, valueColor, false);
            label = StatCollector.func_74838_a((String)"stats.meleespeed") + ": ";
            value = "" + this.npc.stats.attackSpeed;
            this.field_146289_q.func_85187_a(label, xLabel, y += 15, 16239144, false);
            this.field_146289_q.func_85187_a(value, xValue, y, valueColor, false);
            y += 15;
            label = StatCollector.func_74838_a((String)"menu.ai") + ": ";
            int onAttack = this.npc.ais.onAttack;
            switch (onAttack) {
                case 0: {
                    value = StatCollector.func_74838_a((String)"gui.retaliate");
                    break;
                }
                case 1: {
                    value = StatCollector.func_74838_a((String)"gui.panic");
                    break;
                }
                case 2: {
                    value = StatCollector.func_74838_a((String)"gui.retreat");
                    break;
                }
                default: {
                    value = StatCollector.func_74838_a((String)"gui.nothing");
                }
            }
            this.field_146289_q.func_85187_a(label, xLabel, y, 13530618, false);
            this.field_146289_q.func_85187_a(value, xValue, y, valueColor, false);
            label = StatCollector.func_74838_a((String)"stats.speed") + ": ";
            value = "" + this.npc.ais.getWalkingSpeed();
            this.field_146289_q.func_85187_a(label, xLabel, y += 15, 16756237, false);
            this.field_146289_q.func_85187_a(value, xValue, y, valueColor, false);
            label = StatCollector.func_74838_a((String)"movement.type") + ": ";
            int movementType = this.npc.ais.movementType;
            value = movementType == 0 ? StatCollector.func_74838_a((String)"movement.ground") : StatCollector.func_74838_a((String)"movement.flying");
            this.field_146289_q.func_85187_a(label, xLabel, y += 15, 8191828, false);
            this.field_146289_q.func_85187_a(value, xValue, y, valueColor, false);
            y += 15;
        }
        if (tab == 1 && this.linkedItem != null) {
            int useActionIndex;
            topBarText = StatCollector.func_74838_a((String)"gui.id") + ": " + this.linkedItem.id + " - " + this.linkedItem.name;
            textWidth = this.getStringWidthWithoutColor(topBarText);
            centerX = this.guiLeft + 5 + (208 - textWidth) / 2;
            this.field_146289_q.func_85187_a(topBarText, centerX, this.guiTop + 10, 0xFFFFFF, true);
            y = this.guiTop + 30;
            xLabel = this.guiLeft + 8;
            xValue = this.guiLeft + 100;
            int labelColor = 16756237;
            int valueColor = 0xFFFFFF;
            String label = StatCollector.func_74838_a((String)"display.version") + ": ";
            String value = "" + this.linkedItem.version;
            this.field_146289_q.func_85187_a(label, xLabel, y, labelColor, false);
            this.field_146289_q.func_85187_a(value, xValue, y, valueColor, false);
            labelColor = 16733972;
            label = StatCollector.func_74838_a((String)"display.maxStack") + ": ";
            value = "" + this.linkedItem.stackSize;
            this.field_146289_q.func_85187_a(label, xLabel, y += 15, labelColor, false);
            this.field_146289_q.func_85187_a(value, xValue, y, valueColor, false);
            labelColor = 16239144;
            label = StatCollector.func_74838_a((String)"display.digSpeed") + ": ";
            value = "" + this.linkedItem.digSpeed;
            this.field_146289_q.func_85187_a(label, xLabel, y += 15, labelColor, false);
            this.field_146289_q.func_85187_a(value, xValue, y, valueColor, false);
            y += 15;
            labelColor = 2741945;
            String[] useActions = new String[]{StatCollector.func_74838_a((String)"use_action.none"), StatCollector.func_74838_a((String)"use_action.block"), StatCollector.func_74838_a((String)"use_action.eat"), StatCollector.func_74838_a((String)"use_action.drink"), StatCollector.func_74838_a((String)"use_action.bow")};
            switch (this.linkedItem.itemUseAction) {
                case 0: {
                    useActionIndex = 0;
                    break;
                }
                case 1: {
                    useActionIndex = 1;
                    break;
                }
                case 2: {
                    useActionIndex = 4;
                    break;
                }
                case 3: {
                    useActionIndex = 2;
                    break;
                }
                case 4: {
                    useActionIndex = 3;
                    break;
                }
                default: {
                    useActionIndex = 0;
                }
            }
            label = StatCollector.func_74838_a((String)"display.useAction") + ": ";
            value = useActions[useActionIndex];
            this.field_146289_q.func_85187_a(label, xLabel, y, labelColor, false);
            this.field_146289_q.func_85187_a(value, xValue, y, valueColor, false);
            String[] armorOptions = new String[]{StatCollector.func_74838_a((String)"armor_type.none"), StatCollector.func_74838_a((String)"armor_type.all"), StatCollector.func_74838_a((String)"armor_type.head"), StatCollector.func_74838_a((String)"armor_type.chestplate"), StatCollector.func_74838_a((String)"armor_type.leggings"), StatCollector.func_74838_a((String)"armor_type.boots")};
            int armorIndex = this.linkedItem.armorType == -2 ? 0 : (this.linkedItem.armorType == -1 ? 1 : this.linkedItem.armorType + 2);
            label = StatCollector.func_74838_a((String)"display.armor") + ": ";
            value = armorOptions[armorIndex];
            this.field_146289_q.func_85187_a(label, xLabel, y += 15, labelColor, false);
            this.field_146289_q.func_85187_a(value, xValue, y, valueColor, false);
            labelColor = 8191828;
            label = StatCollector.func_74838_a((String)"display.isTool") + ": ";
            value = ("" + this.linkedItem.isTool).toUpperCase();
            this.field_146289_q.func_85187_a(label, xLabel, y += 15, labelColor, false);
            this.field_146289_q.func_85187_a(value, xValue, y, valueColor, false);
            label = StatCollector.func_74838_a((String)"display.isNormalItem") + ": ";
            value = ("" + this.linkedItem.isNormalItem).toUpperCase();
            this.field_146289_q.func_85187_a(label, xLabel, y += 15, labelColor, false);
            this.field_146289_q.func_85187_a(value, xValue, y, valueColor, false);
            labelColor = 13530618;
            label = StatCollector.func_74838_a((String)"model.scale") + ": ";
            value = this.linkedItem.display.scaleX + ", " + this.linkedItem.display.scaleY + ", " + this.linkedItem.display.scaleZ;
            this.field_146289_q.func_85187_a(label, xLabel, y += 15, labelColor, false);
            this.field_146289_q.func_85187_a(value, xValue, y, valueColor, false);
            label = StatCollector.func_74838_a((String)"model.rotate") + ": ";
            value = this.linkedItem.display.rotationX + ", " + this.linkedItem.display.rotationY + ", " + this.linkedItem.display.rotationZ;
            this.field_146289_q.func_85187_a(label, xLabel, y += 15, labelColor, false);
            this.field_146289_q.func_85187_a(value, xValue, y, valueColor, false);
            label = StatCollector.func_74838_a((String)"model.translate") + ": ";
            value = this.linkedItem.display.translateX + ", " + this.linkedItem.display.translateY + ", " + this.linkedItem.display.translateZ;
            this.field_146289_q.func_85187_a(label, xLabel, y += 15, labelColor, false);
            this.field_146289_q.func_85187_a(value, xValue, y, valueColor, false);
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

    @Override
    public void subGuiClosed(SubGuiInterface subgui) {
        if (subgui instanceof SubGuiEditText && !((SubGuiEditText)subgui).cancelled) {
            PacketClient.sendClient(new LinkedNPCAddPacket(((SubGuiEditText)subgui).text));
        }
    }

    @Override
    public void setData(Vector<String> list, HashMap<String, Integer> data, EnumScrollData type) {
        String name = this.scroll.getSelected();
        this.data = data;
        this.scroll.setList(this.getSearchList());
        if (name != null) {
            this.scroll.setSelected(name);
        }
        this.func_73866_w_();
    }

    @Override
    public void setSelected(String selected) {
        this.selected = selected;
        this.scroll.setSelected(selected);
        this.originalName = this.scroll.getSelected();
    }

    @Override
    public void save() {
    }

    @Override
    public boolean isMouseOverRenderer(int x, int y) {
        return x >= this.guiLeft + 10 && x <= this.guiLeft + 10 + 200 && y >= this.guiTop + 6 && y <= this.guiTop + 6 + 204;
    }

    @Override
    public void customScrollClicked(int i, int j, int k, GuiCustomScroll guiCustomScroll) {
        if (guiCustomScroll.id == 0) {
            this.loadedNPC = false;
            this.selected = this.scroll.getSelected();
            this.originalName = this.scroll.getSelected();
            if (this.selected != null && !this.selected.isEmpty()) {
                if (tab == 0) {
                    LinkedGetPacket.GetNPC(this.selected);
                } else {
                    LinkedGetPacket.GetItem(this.data.get(this.selected));
                }
            }
        }
    }

    @Override
    public void customScrollDoubleClicked(String selection, GuiCustomScroll scroll) {
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        this.loadedNPC = false;
        this.linkedItem = null;
        if (compound.func_74764_b("NPCData")) {
            this.npc.display.readToNBT(compound.func_74775_l("NPCData"));
            this.npc.stats.readToNBT(compound.func_74775_l("NPCData"));
            this.npc.ais.readToNBT(compound.func_74775_l("NPCData"));
            this.loadedNPC = true;
        } else {
            this.linkedItem = new LinkedItem();
            this.linkedItem.readFromNBT(compound);
        }
        this.func_73866_w_();
    }
}

