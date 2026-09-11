/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.player;

import kamkeel.npcs.network.packets.player.BankActionPacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.gui.util.GuiContainerNPCInterface;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.containers.ContainerNPCBankInterface;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.opengl.GL11;

public class GuiNPCBankChest
extends GuiContainerNPCInterface
implements IGuiData {
    private final ResourceLocation resource = new ResourceLocation("customnpcs", "textures/gui/bankchest.png");
    private ContainerNPCBankInterface container;
    private int availableSlots = 0;
    private int maxSlots = 1;
    private int unlockedSlots = 1;
    private ItemStack currency;

    public GuiNPCBankChest(EntityNPCInterface npc, ContainerNPCBankInterface container) {
        super(npc, container);
        this.container = container;
        this.title = "";
        this.field_146291_p = false;
        this.field_147000_g = 235;
        this.closeOnEsc = true;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.availableSlots = 0;
        if (this.maxSlots > 1) {
            for (int i = 0; i < this.maxSlots; ++i) {
                GuiNpcButton button = new GuiNpcButton(i, this.field_147003_i - 50, this.field_147009_r + 10 + i * 24, 50, 20, "Tab " + (i + 1));
                if (i > this.unlockedSlots) {
                    button.setEnabled(false);
                }
                this.addButton(button);
                ++this.availableSlots;
            }
            if (this.availableSlots == 1) {
                this.field_146292_n.clear();
            }
        }
        if (!this.container.isAvailable()) {
            this.addButton(new GuiNpcButton(8, this.field_147003_i + 48, this.field_147009_r + 48, 80, 20, StatCollector.func_74838_a((String)"bank.unlock")));
        } else if (this.container.canBeUpgraded()) {
            this.addButton(new GuiNpcButton(9, this.field_147003_i + 48, this.field_147009_r + 48, 80, 20, StatCollector.func_74838_a((String)"bank.upgrade")));
        }
        if (this.maxSlots > 1) {
            this.getButton((int)this.container.slot).field_146125_m = false;
            this.getButton(this.container.slot).setEnabled(false);
        }
    }

    @Override
    public void func_146284_a(GuiButton guibutton) {
        super.func_146284_a(guibutton);
        int id = guibutton.field_146127_k;
        if (id < 6) {
            this.close();
            BankActionPacket.Open(this.container.bankid, id);
        }
        if (id == 8) {
            BankActionPacket.Unlock();
        }
        if (id == 9) {
            BankActionPacket.Upgrade();
        }
    }

    @Override
    protected void func_146976_a(float f, int i, int j) {
        int y;
        int x;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(this.resource);
        int l = (this.field_146294_l - this.field_146999_f) / 2;
        int i1 = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(l, i1, 0, 0, this.field_146999_f, 6);
        if (!this.container.isAvailable()) {
            this.func_73729_b(l, i1 + 6, 0, 6, this.field_146999_f, 64);
            this.func_73729_b(l, i1 + 70, 0, 124, this.field_146999_f, 98);
            x = this.field_147003_i + 30;
            y = this.field_147009_r + 8;
            this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"bank.unlockCosts") + ":", x, y + 4, CustomNpcResourceListener.DefaultTextColor);
            this.drawItem(x + 90, y, this.currency, i, j);
        } else if (this.container.isUpgraded()) {
            this.func_73729_b(l, i1 + 60, 0, 60, this.field_146999_f, 162);
            this.func_73729_b(l, i1 + 6, 0, 60, this.field_146999_f, 64);
        } else if (this.container.canBeUpgraded()) {
            this.func_73729_b(l, i1 + 6, 0, 6, this.field_146999_f, 216);
            x = this.field_147003_i + 30;
            y = this.field_147009_r + 8;
            this.field_146289_q.func_78276_b(StatCollector.func_74838_a((String)"bank.upgradeCosts") + ":", x, y + 4, CustomNpcResourceListener.DefaultTextColor);
            this.drawItem(x + 90, y, this.currency, i, j);
        } else {
            this.func_73729_b(l, i1 + 6, 0, 60, this.field_146999_f, 162);
        }
        if (this.maxSlots > 1) {
            for (int ii = 0; ii < this.maxSlots && this.availableSlots != ii; ++ii) {
                this.field_146289_q.func_78276_b("Tab " + (ii + 1), this.field_147003_i - 40, this.field_147009_r + 16 + ii * 24, 0xFFFFFF);
            }
        }
        super.func_146976_a(f, i, j);
    }

    private void drawItem(int x, int y, ItemStack item, int mouseX, int mouseY) {
        if (item == null) {
            return;
        }
        RenderHelper.func_74520_c();
        GL11.glEnable((int)32826);
        GL11.glEnable((int)2903);
        GL11.glEnable((int)2896);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        field_146296_j.func_82406_b(this.field_146289_q, this.field_146297_k.field_71446_o, item, x, y);
        field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.field_71446_o, item, x, y);
        if (this.func_146978_c(x - this.field_147003_i, y - this.field_147009_r, 16, 16, mouseX, mouseY)) {
            this.func_146285_a(item, mouseX, mouseY);
        }
        RenderHelper.func_74518_a();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    @Override
    public void save() {
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        this.maxSlots = compound.func_74762_e("MaxSlots");
        this.unlockedSlots = compound.func_74762_e("UnlockedSlots");
        this.currency = compound.func_74764_b("Currency") ? NoppesUtilServer.readItem(compound.func_74775_l("Currency")) : null;
        if (this.container.currency != null) {
            this.container.currency.item = this.currency;
        }
        this.func_73866_w_();
    }
}

