/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.player;

import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.player.GetTraderData;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.client.CustomNpcResourceListener;
import noppes.npcs.client.gui.util.GuiContainerNPCInterface;
import noppes.npcs.client.gui.util.IGuiData;
import noppes.npcs.containers.ContainerNPCTrader;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleTrader;
import org.lwjgl.opengl.GL11;

public class GuiNPCTrader
extends GuiContainerNPCInterface
implements IGuiData {
    private final ResourceLocation resource = new ResourceLocation("customnpcs", "textures/gui/tradersetup.png");
    private final ResourceLocation slot = new ResourceLocation("customnpcs", "textures/gui/slot.png");
    private RoleTrader role;
    private ContainerNPCTrader container;
    private static final int COLUMN_WIDTH = 80;
    private static final int COLUMN_START_X = 8;
    private static final int ROW_HEIGHT = 22;
    private static final int ROW_START_Y = 6;
    private static final int CURRENCY1_OFFSET = 2;
    private static final int CURRENCY2_OFFSET = 20;
    private static final int OUTPUT_OFFSET = 50;
    private long playerBalance = 0L;
    private int[] availableStock = new int[18];
    private long[] currencyCost = new long[18];
    private boolean stockEnabled = false;
    private long resetTargetTime = -1L;
    private String statusMessage = "";
    private int statusColor = 0xFFFFFF;

    public GuiNPCTrader(EntityNPCInterface npc, ContainerNPCTrader container) {
        super(npc, container);
        this.container = container;
        this.role = (RoleTrader)npc.roleInterface;
        this.closeOnEsc = true;
        this.field_147000_g = 216;
        this.field_146999_f = 256;
        this.title = "";
        for (int i = 0; i < 18; ++i) {
            this.availableStock[i] = Integer.MAX_VALUE;
            this.currencyCost[i] = 0L;
        }
        PacketClient.sendClient(new GetTraderData());
    }

    @Override
    public void setGuiData(NBTTagCompound compound) {
        this.playerBalance = compound.func_74763_f("Balance");
        this.stockEnabled = compound.func_74767_n("StockEnabled");
        long remainingMillis = compound.func_74763_f("ResetTimeMillis");
        this.resetTargetTime = remainingMillis > 0L ? System.currentTimeMillis() + remainingMillis : -1L;
        int[] stock = compound.func_74759_k("Stock");
        if (stock != null && stock.length == 18) {
            this.availableStock = stock;
        }
        for (int i = 0; i < 18; ++i) {
            this.currencyCost[i] = compound.func_74763_f("Cost" + i);
        }
    }

    @Override
    protected void func_146976_a(float f, int mouseX, int mouseY) {
        this.statusMessage = "";
        this.statusColor = 0xFFFFFF;
        this.func_146270_b(0);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.field_146297_k.field_71446_o.func_110577_a(this.resource);
        this.func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
        String traderLabel = StatCollector.func_74838_a((String)"role.trader");
        this.field_146289_q.func_78276_b(traderLabel, this.field_147003_i + 4, this.field_147009_r - 8, 0xFFFFFF);
        if (this.stockEnabled && this.resetTargetTime > 0L) {
            String timerText = this.getResetCountdownText();
            int timerWidth = this.field_146289_q.func_78256_a(timerText);
            int timerColor = timerText.equals(StatCollector.func_74838_a((String)"trader.reopentrader")) ? 0xFFAA00 : 0xFFFFFF;
            this.field_146289_q.func_78276_b(timerText, this.field_147003_i + this.field_146999_f - timerWidth - 4, this.field_147009_r - 8, timerColor);
        }
        RenderHelper.func_74520_c();
        GL11.glEnable((int)32826);
        GL11.glEnable((int)2903);
        GL11.glEnable((int)2896);
        for (int slotIdx = 0; slotIdx < 18; ++slotIdx) {
            int col = slotIdx % 3;
            int row = slotIdx / 3;
            int x = this.field_147003_i + 8 + col * 80;
            int y = this.field_147009_r + 6 + row * 22;
            ItemStack item = this.role.inventoryCurrency.items.get(slotIdx);
            ItemStack item2 = this.role.inventoryCurrency.items.get(slotIdx + 18);
            if (item == null) {
                item = item2;
                item2 = null;
            }
            if (item != null && item2 != null && NoppesUtilPlayer.compareItems(item, item2, this.role.ignoreDamage, this.role.ignoreNBT)) {
                item = item.func_77946_l();
                item.field_77994_a += item2.field_77994_a;
                item2 = null;
            }
            ItemStack sold = this.role.inventorySold.items.get(slotIdx);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.field_146297_k.field_71446_o.func_110577_a(this.slot);
            this.func_73729_b(x + 2, y, 0, 0, 18, 18);
            this.func_73729_b(x + 20, y, 0, 0, 18, 18);
            this.func_73729_b(x + 50, y, 0, 0, 18, 18);
            this.field_146289_q.func_78276_b("=", x + 40, y + 5, CustomNpcResourceListener.DefaultTextColor);
            if (sold == null) continue;
            RenderHelper.func_74520_c();
            if (item2 != null) {
                field_146296_j.func_82406_b(this.field_146289_q, this.field_146297_k.field_71446_o, item2, x + 2 + 1, y + 1);
                field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.field_71446_o, item2, x + 2 + 1, y + 1);
            }
            if (item != null) {
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                field_146296_j.func_82406_b(this.field_146289_q, this.field_146297_k.field_71446_o, item, x + 20 + 1, y + 1);
                field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.field_71446_o, item, x + 20 + 1, y + 1);
            }
            RenderHelper.func_74518_a();
            if (this.stockEnabled && this.availableStock[slotIdx] < Integer.MAX_VALUE) {
                String stockText = "" + this.availableStock[slotIdx];
                int stockColor = this.availableStock[slotIdx] > 0 ? 43520 : 0xAA0000;
                this.field_146289_q.func_78276_b(stockText, x + 50 + 20, y + 5, stockColor);
            }
            if (!this.stockEnabled || this.availableStock[slotIdx] > 0) continue;
            this.func_73733_a(x + 50 + 1, y + 1, x + 50 + 17, y + 17, -1072689136, -1072689136);
        }
        GL11.glEnable((int)2896);
        GL11.glEnable((int)2929);
        RenderHelper.func_74519_b();
        super.func_146976_a(f, mouseX, mouseY);
        int infoY = this.field_147009_r + this.field_147000_g + 2;
        String balanceText = StatCollector.func_74838_a((String)"trader.balance") + ": $" + this.formatCurrency(this.playerBalance);
        int balanceWidth = this.field_146289_q.func_78256_a(balanceText);
        this.field_146289_q.func_78276_b(balanceText, this.field_147003_i + this.field_146999_f - balanceWidth - 4, infoY, 3598378);
    }

    @Override
    protected void func_146979_b(int mouseX, int mouseY) {
        for (int slotIdx = 0; slotIdx < 18; ++slotIdx) {
            ItemStack sold;
            int col = slotIdx % 3;
            int row = slotIdx / 3;
            int x = 8 + col * 80;
            int y = 6 + row * 22;
            ItemStack item = this.role.inventoryCurrency.items.get(slotIdx);
            ItemStack item2 = this.role.inventoryCurrency.items.get(slotIdx + 18);
            if (item == null) {
                item = item2;
                item2 = null;
            }
            if (item != null && item2 != null && NoppesUtilPlayer.compareItems(item, item2, this.role.ignoreDamage, this.role.ignoreNBT)) {
                item = item.func_77946_l();
                item.field_77994_a += item2.field_77994_a;
                item2 = null;
            }
            if ((sold = this.role.inventorySold.items.get(slotIdx)) == null) continue;
            if (this.func_146978_c(x + 50, y, 18, 18, mouseX, mouseY)) {
                this.updateSlotStatus(slotIdx, item, item2, x, y);
            }
            if (this.func_146978_c(x + 2, y, 18, 18, mouseX, mouseY) && item2 != null) {
                this.func_146285_a(item2, mouseX - this.field_147003_i, mouseY - this.field_147009_r);
            }
            if (!this.func_146978_c(x + 20, y, 18, 18, mouseX, mouseY) || item == null) continue;
            this.func_146285_a(item, mouseX - this.field_147003_i, mouseY - this.field_147009_r);
        }
        if (!this.statusMessage.isEmpty()) {
            this.field_146289_q.func_78276_b(this.statusMessage, 4, this.field_147000_g + 4, this.statusColor);
        }
    }

    private void updateSlotStatus(int slotIdx, ItemStack item, ItemStack item2, int x, int y) {
        String costSuffix;
        long cost = this.currencyCost[slotIdx];
        String string = costSuffix = cost > 0L ? " ($" + this.formatCurrency(cost) + ")" : "";
        if (this.stockEnabled && this.availableStock[slotIdx] <= 0) {
            this.statusMessage = StatCollector.func_74838_a((String)"trader.outofstock") + costSuffix;
            this.statusColor = 0xDD0000;
        } else if (cost > 0L && this.playerBalance < cost) {
            this.statusMessage = StatCollector.func_74838_a((String)"trader.insufficient.currency") + costSuffix;
            this.statusColor = 0xDD0000;
        } else if (!this.container.canBuy(slotIdx, (EntityPlayer)this.player)) {
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)300.0f);
            if (item != null && !NoppesUtilPlayer.compareItems((EntityPlayer)this.player, item, this.role.ignoreDamage, this.role.ignoreNBT)) {
                this.func_73733_a(x + 20, y, x + 20 + 18, y + 18, 0x70771010, 0x70771010);
            }
            if (item2 != null && !NoppesUtilPlayer.compareItems((EntityPlayer)this.player, item2, this.role.ignoreDamage, this.role.ignoreNBT)) {
                this.func_73733_a(x + 2, y, x + 2 + 18, y + 18, 0x70771010, 0x70771010);
            }
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-300.0f);
            this.statusMessage = StatCollector.func_74838_a((String)"trader.unavailable") + costSuffix;
            this.statusColor = 0xDD0000;
        } else if (!this.container.isSlotEnabled(slotIdx, (EntityPlayer)this.player)) {
            this.statusMessage = StatCollector.func_74838_a((String)"trader.slotdisabled") + costSuffix;
            this.statusColor = 0xFF4000;
        } else {
            this.statusMessage = StatCollector.func_74838_a((String)"trader.available") + costSuffix;
            this.statusColor = 56576;
        }
    }

    private String getResetCountdownText() {
        if (this.resetTargetTime <= 0L) {
            return "";
        }
        long remaining = this.resetTargetTime - System.currentTimeMillis();
        if (remaining <= 0L) {
            return StatCollector.func_74838_a((String)"trader.reopentrader");
        }
        long seconds = remaining / 1000L % 60L;
        long minutes = remaining / 60000L % 60L;
        long hours = remaining / 3600000L % 24L;
        long days = remaining / 86400000L;
        StringBuilder sb = new StringBuilder();
        sb.append(StatCollector.func_74838_a((String)"trader.restock")).append(": ");
        if (days > 0L) {
            sb.append(days).append("d ");
        }
        sb.append(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        return sb.toString();
    }

    private String formatCurrency(long amount) {
        if (amount < 1000L) {
            return "" + amount;
        }
        StringBuilder sb = new StringBuilder();
        String str = "" + amount;
        int count = 0;
        for (int i = str.length() - 1; i >= 0; --i) {
            if (count > 0 && count % 3 == 0) {
                sb.insert(0, ',');
            }
            sb.insert(0, str.charAt(i));
            ++count;
        }
        return sb.toString();
    }

    protected void func_146984_a(Slot slot, int slotId, int mouseButton, int modifier) {
        ItemStack sold;
        long cost;
        if (slotId >= 0 && slotId < 18 && mouseButton == 0 && (cost = this.currencyCost[slotId]) > 0L && this.playerBalance >= cost && (sold = this.role.inventorySold.items.get(slotId)) != null && this.canGivePlayerClient(sold) && this.container.isSlotEnabled(slotId, (EntityPlayer)this.player) && (!this.stockEnabled || this.availableStock[slotId] > 0) && this.container.canBuy(slotId, (EntityPlayer)this.player)) {
            this.playerBalance -= cost;
            if (this.stockEnabled && this.availableStock[slotId] < Integer.MAX_VALUE) {
                int n = slotId;
                this.availableStock[n] = this.availableStock[n] - 1;
            }
        }
        super.func_146984_a(slot, slotId, mouseButton, modifier);
    }

    private boolean canGivePlayerClient(ItemStack item) {
        int k1;
        ItemStack held = this.player.field_71071_by.func_70445_o();
        if (held == null) {
            return true;
        }
        return NoppesUtilPlayer.compareItems(held, item, false, false) && (k1 = item.field_77994_a) > 0 && k1 + held.field_77994_a <= held.func_77976_d();
    }

    @Override
    public void save() {
    }
}

