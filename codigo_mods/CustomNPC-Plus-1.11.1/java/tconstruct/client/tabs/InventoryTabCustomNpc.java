/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 */
package tconstruct.client.tabs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import noppes.npcs.CustomItems;
import noppes.npcs.client.gui.player.inventory.GuiAbilities;
import noppes.npcs.client.gui.player.inventory.GuiCNPCInventory;
import noppes.npcs.client.gui.player.inventory.GuiFaction;
import noppes.npcs.client.gui.player.inventory.GuiParty;
import noppes.npcs.client.gui.player.inventory.GuiProfiles;
import noppes.npcs.client.gui.player.inventory.GuiQuestLog;
import noppes.npcs.client.gui.player.inventory.GuiSettings;
import tconstruct.client.tabs.AbstractTab;

public class InventoryTabCustomNpc
extends AbstractTab {
    public InventoryTabCustomNpc() {
        super(0, 0, 0, new ItemStack(CustomItems.letter));
        if (CustomItems.letter == null) {
            this.renderStack = new ItemStack(Items.field_151122_aG);
        }
    }

    @Override
    public void onTabClicked() {
        Thread t = new Thread(){

            @Override
            public void run() {
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                InventoryTabCustomNpc.tabHelper();
            }
        };
        t.start();
    }

    @Override
    public boolean shouldAddToList() {
        return true;
    }

    public static void tabHelper() {
        Minecraft mc = Minecraft.func_71410_x();
        int tab = GuiCNPCInventory.activeTab;
        if (tab == -100) {
            mc.func_147108_a((GuiScreen)new GuiQuestLog());
        }
        if (tab == -101) {
            mc.func_147108_a((GuiScreen)new GuiParty());
        }
        if (tab == -102) {
            mc.func_147108_a((GuiScreen)new GuiFaction());
        }
        if (tab == -103) {
            mc.func_147108_a((GuiScreen)new GuiSettings());
        }
        if (tab == -104) {
            mc.func_147108_a((GuiScreen)new GuiProfiles());
        }
        if (tab == -105) {
            mc.func_147108_a((GuiScreen)new GuiAbilities());
        }
    }
}

