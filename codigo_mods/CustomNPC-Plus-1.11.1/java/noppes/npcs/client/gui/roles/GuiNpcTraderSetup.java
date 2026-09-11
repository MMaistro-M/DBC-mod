/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.roles;

import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.request.TraderMarketSavePacket;
import kamkeel.npcs.network.packets.request.role.RoleSavePacket;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.gui.roles.SubGuiNpcTraderSettings;
import noppes.npcs.client.gui.roles.SubGuiNpcTraderStock;
import noppes.npcs.client.gui.util.GuiContainerNPCInterface2;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.client.gui.util.GuiNpcLabel;
import noppes.npcs.client.gui.util.GuiNpcTextField;
import noppes.npcs.client.gui.util.ITextfieldListener;
import noppes.npcs.containers.ContainerNPCTraderSetup;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleTrader;
import org.lwjgl.opengl.GL11;

public class GuiNpcTraderSetup
extends GuiContainerNPCInterface2
implements ITextfieldListener {
    private final ResourceLocation slot = new ResourceLocation("customnpcs", "textures/gui/slot.png");
    private RoleTrader role;
    private static final int CURRENCY_FIELD_ID_START = 100;

    public GuiNpcTraderSetup(EntityNPCInterface npc, ContainerNPCTraderSetup container) {
        super(npc, container);
        this.field_147000_g = 220;
        this.menuYOffset = 10;
        this.role = container.role;
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        this.field_146292_n.clear();
        this.setBackground("tradersetup.png");
        this.addLabel(new GuiNpcLabel(0, "role.marketname", this.field_147003_i + 214, this.field_147009_r + 150));
        this.addTextField(new GuiNpcTextField(0, (GuiScreen)this, this.field_147003_i + 214, this.field_147009_r + 160, 180, 20, this.role.marketName));
        this.addButton(new GuiNpcButton(1, this.field_147003_i + 214, this.field_147009_r + 184, 88, 20, "gui.settings"));
        this.getButton(1).setHoverText("gui.settings.hover");
        this.addButton(new GuiNpcButton(2, this.field_147003_i + 306, this.field_147009_r + 184, 88, 20, "stock.options"));
        this.getButton(2).setHoverText("stock.options.hover");
        for (int i = 0; i < 18; ++i) {
            int x = this.field_147003_i + i % 3 * 130 + 15;
            int y = this.field_147009_r + i / 3 * 22 + 14;
            long cost = this.role.getCurrencyCost(i);
            GuiNpcTextField field = new GuiNpcTextField(100 + i, (GuiScreen)this, x, y, 36, 16, cost > 0L ? "" + cost : "");
            field.func_146203_f(8);
            field.setIntegersOnly();
            this.addTextField(field);
        }
    }

    @Override
    public void func_73863_a(int i, int j, float f) {
        this.field_147009_r += 10;
        super.func_73863_a(i, j, f);
        this.field_147009_r -= 10;
    }

    @Override
    public void func_146284_a(GuiButton guibutton) {
        if (guibutton.field_146127_k == 1) {
            this.setSubGui(new SubGuiNpcTraderSettings(this.role));
        }
        if (guibutton.field_146127_k == 2) {
            this.setSubGui(new SubGuiNpcTraderStock(this.role));
        }
    }

    @Override
    protected void func_146976_a(float f, int i, int j) {
        super.func_146976_a(f, i, j);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        for (int slot = 0; slot < 18; ++slot) {
            int x = this.field_147003_i + slot % 3 * 130 + 7;
            int y = this.field_147009_r + slot / 3 * 22 + 4;
            this.field_146289_q.func_78276_b("$", x, y + 5, CustomNpcResourceListener.DefaultTextColor);
            this.field_146297_k.field_71446_o.func_110577_a(this.slot);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.func_73729_b(x + 50, y, 0, 0, 18, 18);
            this.func_73729_b(x + 68, y, 0, 0, 18, 18);
            this.field_146289_q.func_78276_b("=", x + 88, y + 5, CustomNpcResourceListener.DefaultTextColor);
            this.field_146297_k.field_71446_o.func_110577_a(this.slot);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.func_73729_b(x + 104, y, 0, 0, 18, 18);
        }
    }

    @Override
    public void save() {
        PacketClient.sendClient(new TraderMarketSavePacket(this.role.marketName, false));
        PacketClient.sendClient(new RoleSavePacket(this.role.writeToNBT(new NBTTagCompound())));
    }

    @Override
    public void unFocused(GuiNpcTextField guiNpcTextField) {
        int id = guiNpcTextField.id;
        if (id == 0) {
            String name = guiNpcTextField.func_146179_b();
            if (!name.equalsIgnoreCase(this.role.marketName)) {
                this.role.marketName = name;
                PacketClient.sendClient(new TraderMarketSavePacket(this.role.marketName, true));
            }
            return;
        }
        if (id >= 100 && id < 118) {
            int slot = id - 100;
            long cost = guiNpcTextField.getInteger();
            this.role.setCurrencyCost(slot, cost);
        }
    }
}

