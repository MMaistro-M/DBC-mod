/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package noppes.npcs.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.containers.ContainerNpcInterface;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleTrader;

public class ContainerNPCTrader
extends ContainerNpcInterface {
    public RoleTrader role;
    private final EntityNPCInterface npc;
    public static final int COLUMN_WIDTH = 80;
    public static final int COLUMN_START_X = 8;
    public static final int ROW_HEIGHT = 22;
    public static final int ROW_START_Y = 6;
    public static final int CURRENCY1_OFFSET = 2;
    public static final int CURRENCY2_OFFSET = 20;
    public static final int OUTPUT_OFFSET = 50;

    public ContainerNPCTrader(EntityNPCInterface npc, EntityPlayer player) {
        super(player);
        this.npc = npc;
        this.role = (RoleTrader)npc.roleInterface;
        if (player instanceof EntityPlayerMP && !this.role.stock.perPlayer) {
            this.role.registerViewer((EntityPlayerMP)player);
        }
        for (int i = 0; i < 18; ++i) {
            int col = i % 3;
            int row = i / 3;
            int x = 8 + col * 80;
            int y = 6 + row * 22;
            this.func_75146_a(new Slot((IInventory)this.role.inventorySold, i, x + 50 + 1, y + 1));
        }
        for (int i1 = 0; i1 < 3; ++i1) {
            for (int l1 = 0; l1 < 9; ++l1) {
                this.func_75146_a(new Slot((IInventory)player.field_71071_by, l1 + i1 * 9 + 9, 48 + l1 * 18, 137 + i1 * 18));
            }
        }
        for (int j1 = 0; j1 < 9; ++j1) {
            this.func_75146_a(new Slot((IInventory)player.field_71071_by, j1, 48 + j1 * 18, 195));
        }
    }

    public void func_75134_a(EntityPlayer player) {
        super.func_75134_a(player);
        if (player instanceof EntityPlayerMP && !this.role.stock.perPlayer) {
            this.role.unregisterViewer((EntityPlayerMP)player);
        }
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int i) {
        return null;
    }

    public ItemStack func_75144_a(int i, int j, int par3, EntityPlayer entityplayer) {
        PlayerData data;
        if (par3 == 6) {
            par3 = 0;
        }
        if (i < 0 || i >= 18) {
            return super.func_75144_a(i, j, par3, entityplayer);
        }
        if (j == 1) {
            return null;
        }
        Slot slot = (Slot)this.field_75151_b.get(i);
        if (slot == null || slot.func_75211_c() == null) {
            return null;
        }
        ItemStack item = slot.func_75211_c();
        if (!this.canGivePlayer(item, entityplayer)) {
            return null;
        }
        if (!this.isSlotEnabled(i, entityplayer)) {
            return null;
        }
        String playerName = entityplayer.func_70005_c_();
        if (!this.role.hasStock(i, playerName, 1)) {
            return null;
        }
        if (!this.canBuy(i, entityplayer)) {
            return null;
        }
        long currencyCost = this.role.getCurrencyCost(i);
        if (currencyCost > 0L) {
            data = PlayerData.get(entityplayer);
            if (data.tradeData.getBalance() < currencyCost) {
                return null;
            }
        }
        NoppesUtilPlayer.consumeItem(entityplayer, this.role.inventoryCurrency.func_70301_a(i), this.role.ignoreDamage, this.role.ignoreNBT);
        NoppesUtilPlayer.consumeItem(entityplayer, this.role.inventoryCurrency.func_70301_a(i + 18), this.role.ignoreDamage, this.role.ignoreNBT);
        if (currencyCost > 0L) {
            data = PlayerData.get(entityplayer);
            data.tradeData.withdraw(currencyCost);
        }
        this.role.consumeStock(i, playerName, 1);
        ItemStack soldItem = item.func_77946_l();
        this.givePlayer(soldItem, entityplayer);
        this.role.addPurchase(i, entityplayer.getDisplayName());
        if (entityplayer instanceof EntityPlayerMP) {
            this.role.syncToPlayer((EntityPlayerMP)entityplayer);
        }
        return soldItem;
    }

    public boolean isSlotEnabled(int slot, EntityPlayer player) {
        return this.role.isSlotEnabled(slot, player.getDisplayName());
    }

    public boolean canBuy(int slot, EntityPlayer player) {
        ItemStack currency = this.role.inventoryCurrency.func_70301_a(slot);
        ItemStack currency2 = this.role.inventoryCurrency.func_70301_a(slot + 18);
        if (currency == null && currency2 == null) {
            return true;
        }
        if (currency == null) {
            currency = currency2;
            currency2 = null;
        }
        if (NoppesUtilPlayer.compareItems(currency, currency2, this.role.ignoreDamage, this.role.ignoreNBT)) {
            currency = currency.func_77946_l();
            currency.field_77994_a += currency2.field_77994_a;
            currency2 = null;
        }
        if (currency2 == null) {
            return NoppesUtilPlayer.compareItems(player, currency, this.role.ignoreDamage, this.role.ignoreNBT);
        }
        return NoppesUtilPlayer.compareItems(player, currency, this.role.ignoreDamage, this.role.ignoreNBT) && NoppesUtilPlayer.compareItems(player, currency2, this.role.ignoreDamage, this.role.ignoreNBT);
    }

    private boolean canGivePlayer(ItemStack item, EntityPlayer entityplayer) {
        int k1;
        ItemStack itemstack3 = entityplayer.field_71071_by.func_70445_o();
        if (itemstack3 == null) {
            return true;
        }
        return NoppesUtilPlayer.compareItems(itemstack3, item, false, false) && (k1 = item.field_77994_a) > 0 && k1 + itemstack3.field_77994_a <= itemstack3.func_77976_d();
    }

    private void givePlayer(ItemStack item, EntityPlayer entityplayer) {
        int k1;
        ItemStack itemstack3 = entityplayer.field_71071_by.func_70445_o();
        if (itemstack3 == null) {
            entityplayer.field_71071_by.func_70437_b(item);
        } else if (NoppesUtilPlayer.compareItems(itemstack3, item, false, false) && (k1 = item.field_77994_a) > 0 && k1 + itemstack3.field_77994_a <= itemstack3.func_77976_d()) {
            itemstack3.field_77994_a += k1;
        }
    }
}

