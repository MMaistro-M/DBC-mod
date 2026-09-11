/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiInventory
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.C0DPacketCloseWindow
 *  net.minecraftforge.client.event.GuiScreenEvent$InitGuiEvent$Post
 */
package tconstruct.client.tabs;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import net.minecraftforge.client.event.GuiScreenEvent;
import tconstruct.client.tabs.AbstractTab;
import tconstruct.client.tabs.InventoryTabVanilla;

public class TabRegistry {
    private static ArrayList<AbstractTab> tabList = new ArrayList();
    private static Minecraft mc = FMLClientHandler.instance().getClient();

    public static void registerTab(AbstractTab tab) {
        tabList.add(tab);
    }

    public static ArrayList<AbstractTab> getTabList() {
        return tabList;
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void guiPostInit(GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.gui instanceof GuiInventory) {
            int xSize = 176;
            int ySize = 166;
            int guiLeft = (event.gui.field_146294_l - xSize) / 2;
            int guiTop = (event.gui.field_146295_m - ySize) / 2;
            TabRegistry.updateTabValues(guiLeft += TabRegistry.getPotionOffset(), guiTop, InventoryTabVanilla.class);
            TabRegistry.addTabsToList(event.buttonList);
        }
    }

    public static void openInventoryGui() {
        TabRegistry.mc.field_71439_g.field_71174_a.func_147297_a((Packet)new C0DPacketCloseWindow(TabRegistry.mc.field_71439_g.field_71070_bA.field_75152_c));
        GuiInventory inventory = new GuiInventory((EntityPlayer)TabRegistry.mc.field_71439_g);
        mc.func_147108_a((GuiScreen)inventory);
    }

    public static void updateTabValues(int cornerX, int cornerY, Class<?> selectedButton) {
        int count = 2;
        for (int i = 0; i < tabList.size(); ++i) {
            AbstractTab t = tabList.get(i);
            if (!t.shouldAddToList()) continue;
            t.field_146127_k = count;
            t.field_146128_h = cornerX + (count - 2) * 28;
            t.field_146129_i = cornerY - 28;
            t.field_146124_l = !((Object)((Object)t)).getClass().equals(selectedButton);
            ++count;
        }
    }

    public static void addTabsToList(List buttonList) {
        for (AbstractTab tab : tabList) {
            if (!tab.shouldAddToList()) continue;
            buttonList.add(tab);
        }
    }

    public static int getPotionOffset() {
        if (!TabRegistry.mc.field_71439_g.func_70651_bq().isEmpty()) {
            if (Loader.isModLoaded((String)"NotEnoughItems")) {
                try {
                    Class<?> c = Class.forName("codechicken.nei.NEIClientConfig");
                    Object hidden = c.getMethod("isHidden", new Class[0]).invoke(null, new Object[0]);
                    Object enabled = c.getMethod("isEnabled", new Class[0]).invoke(null, new Object[0]);
                    if (hidden != null && hidden instanceof Boolean && enabled != null && enabled instanceof Boolean && (((Boolean)hidden).booleanValue() || !((Boolean)enabled).booleanValue())) {
                        return 60;
                    }
                }
                catch (Exception exception) {}
            } else {
                return 60;
            }
        }
        return 0;
    }
}

