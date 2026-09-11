/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.inventory.Slot
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.mainmenu;

import java.util.HashMap;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.mainmenu.MainmenuInvGetPacket;
import kamkeel.npcs.network.packets.request.mainmenu.MainmenuInvSavePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Slot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.gui.util.GuiContainerNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.containers.ContainerNPCInv;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.opengl.GL11;

public class GuiNPCInv
extends GuiContainerNPCInterface2
implements IGuiData,
ITextfieldListener {
    private HashMap<Integer, Double> chances = new HashMap();
    private ContainerNPCInv container;
    private ResourceLocation slot;
    private int inventoryTab = 0;

    public GuiNPCInv(EntityNPCInterface npc, ContainerNPCInv container) {
        super(npc, container, 3);
        this.setBackground("npcinv.png");
        this.container = container;
        this.field_147000_g = 200;
        this.slot = this.getResource("slot.png");
        PacketClient.sendClient(new MainmenuInvGetPacket());
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.addLabel(new GuiNpcLabel(0, "inv.minExp", this.field_147003_i + 118, this.field_147009_r + 18));
        this.addTextField(new GuiNpcTextField(0, (GuiScreen)this, this.field_146289_q, this.field_147003_i + 108, this.field_147009_r + 29, 60, 20, this.npc.inventory.minExp + ""));
        this.getTextField((int)0).integersOnly = true;
        this.addLabel(new GuiNpcLabel(1, "inv.maxExp", this.field_147003_i + 118, this.field_147009_r + 52));
        this.addTextField(new GuiNpcTextField(1, (GuiScreen)this, this.field_146289_q, this.field_147003_i + 108, this.field_147009_r + 63, 60, 20, this.npc.inventory.maxExp + ""));
        this.getTextField((int)1).integersOnly = true;
        this.getTextField(0).setMinMaxDefault(0, this.getTextField(1).getInteger(), 0);
        this.getTextField(1).setMinMaxDefault(this.getTextField(0).getInteger(), Short.MAX_VALUE, 0);
        this.addButton(new GuiNpcButton(10, this.field_147003_i + 88, this.field_147009_r + 88, 80, 20, new String[]{"stats.normal", "inv.auto"}, this.npc.inventory.lootMode));
        this.addLabel(new GuiNpcLabel(2, "inv.npcInventory", this.field_147003_i + 191, this.field_147009_r + 5));
        this.addLabel(new GuiNpcLabel(3, "inv.inventory", this.field_147003_i + 8, this.field_147009_r + 101));
        this.addLabel(new GuiNpcLabel(4, "Tab", this.field_147003_i + 381, this.field_147009_r + 5));
        this.addButton(new GuiNpcButton(11, this.field_147003_i + 375, this.field_147009_r + 13, 30, 20, "1"));
        this.addButton(new GuiNpcButton(12, this.field_147003_i + 375, this.field_147009_r + 34, 30, 20, "2"));
        this.getButton(11 + this.inventoryTab).setEnabled(false);
        this.getButton(12 - this.inventoryTab).setEnabled(true);
        for (int c = 0; c < 4; ++c) {
            for (int r = 0; r < 9; ++r) {
                double chance = 100.0;
                if (this.npc.inventory.dropchance.containsKey(r + c * 9)) {
                    chance = this.npc.inventory.dropchance.get(r + c * 9);
                }
                if (chance <= 0.0 || chance > 100.0) {
                    chance = 100.0;
                }
                this.chances.put(r + c * 9, chance);
                if (Math.floor((float)c / 2.0f) != (double)this.inventoryTab) {
                    this.container.func_75139_a((int)(r + c * 9 + 7)).field_75223_e = 10000;
                    this.container.func_75139_a((int)(r + c * 9 + 7)).field_75221_f = 10000;
                    continue;
                }
                this.container.func_75139_a((int)(r + c * 9 + 7)).field_75223_e = 191 + (c - this.inventoryTab * 2) * 90;
                this.container.func_75139_a((int)(r + c * 9 + 7)).field_75221_f = 16 + r * 21;
                GuiNpcTextField textField = new GuiNpcTextField(2 + r + c * 9, (GuiScreen)this, this.field_146289_q, this.field_147003_i + 210 + (c - this.inventoryTab * 2) * 90, this.field_147009_r + 14 + r * 21, 60, 18, chance + "");
                this.addLabel(new GuiNpcLabel(c * 9 + r + 5, "%", this.field_147003_i + 272 + (c - this.inventoryTab * 2) * 90, this.field_147009_r + 16 + r * 21));
                textField.doublesOnly = true;
                textField.setMinMaxDefaultDouble(0.0, 100.0, 100.0);
                this.addTextField(textField);
            }
        }
    }

    private void drawSlot(int x, int y) {
        GL11.glDisable((int)2896);
        GL11.glEnable((int)3042);
        this.field_146297_k.func_110434_K().func_110577_a(this.slot);
        this.func_73729_b(x, y, 0, 0, 18, 18);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2896);
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        if (guibutton.field_146127_k == 10) {
            this.npc.inventory.lootMode = ((GuiNpcButton)guibutton).getValue();
        }
        if (guibutton.field_146127_k == 11 || guibutton.field_146127_k == 12) {
            this.inventoryTab = Integer.parseInt(guibutton.field_146126_j) - 1;
            this.func_73866_w_();
        }
    }

    @Override
    protected void func_146976_a(float f, int i, int j) {
        super.func_146976_a(f, i, j);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(this.slot);
        for (int id = 4; id <= 6; ++id) {
            Slot slot = this.container.func_75139_a(id);
            if (!slot.func_75216_d()) continue;
            this.func_73729_b(this.field_147003_i + slot.field_75223_e - 1, this.field_147009_r + slot.field_75221_f - 1, 0, 0, 18, 18);
        }
        for (int c = 0; c < 2; ++c) {
            for (int r = 0; r < 9; ++r) {
                this.drawSlot(this.field_147003_i + 190 + c * 90, this.field_147009_r + 15 + r * 21);
            }
        }
    }

    @Override
    public void func_73863_a(int i, int j, float f) {
        this.npc.isDrawn = true;
        int showname = this.npc.display.showName;
        this.npc.display.showName = 1;
        int l = this.field_147003_i + 20;
        int i1 = this.field_146295_m / 2 - 145;
        GL11.glEnable((int)32826);
        GL11.glEnable((int)2903);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(l + 33), (float)(i1 + 131), (float)50.0f);
        float f1 = 150.0f / (float)this.npc.display.modelSize;
        GL11.glScalef((float)(-f1), (float)f1, (float)f1);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        float f2 = this.npc.field_70761_aq;
        float f3 = this.npc.field_70177_z;
        float f4 = this.npc.field_70125_A;
        float f7 = this.npc.field_70759_as;
        float f5 = (float)(l + 33) - (float)i;
        float f6 = (float)(i1 + 131 - 50) - (float)j;
        GL11.glRotatef((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.func_74519_b();
        GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-((float)Math.atan(f6 / 40.0f)) * 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        this.npc.field_70761_aq = (float)Math.atan(f5 / 40.0f) * 20.0f;
        this.npc.field_70177_z = (float)Math.atan(f5 / 40.0f) * 40.0f;
        this.npc.field_70125_A = -((float)Math.atan(f6 / 40.0f)) * 20.0f;
        this.npc.field_70759_as = this.npc.field_70177_z;
        GL11.glTranslatef((float)0.0f, (float)this.npc.field_70129_M, (float)0.0f);
        RenderManager.field_78727_a.field_78735_i = 180.0f;
        RenderManager.field_78727_a.func_147940_a((Entity)this.npc, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        this.npc.field_70761_aq = f2;
        this.npc.field_70177_z = f3;
        this.npc.field_70125_A = f4;
        this.npc.field_70759_as = f7;
        GL11.glPopMatrix();
        RenderHelper.func_74518_a();
        GL11.glDisable((int)32826);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77476_b);
        GL11.glDisable((int)3553);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77478_a);
        this.npc.display.showName = showname;
        this.npc.isDrawn = false;
        super.func_73863_a(i, j, f);
    }

    @Override
    public void save() {
        this.npc.inventory.dropchance = this.chances;
        this.npc.inventory.minExp = this.getTextField(0).getInteger();
        this.npc.inventory.maxExp = this.getTextField(1).getInteger();
        PacketClient.sendClient(new MainmenuInvSavePacket(this.npc.inventory.writeEntityToNBT(new NBTTagCompound())));
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        this.npc.inventory.readEntityFromNBT(compound);
        this.func_73866_w_();
    }

    @Override
    public void unFocused(GuiNpcTextField textfield) {
        if (textfield.id >= 2) {
            this.chances.put(textfield.id - 2, Double.parseDouble(textfield.func_146179_b()));
        }
        this.getTextField(0).setMinMaxDefault(0, this.getTextField(1).getInteger(), 0);
        this.getTextField(1).setMinMaxDefault(this.getTextField(0).getInteger(), Short.MAX_VALUE, 0);
        this.save();
    }
}

