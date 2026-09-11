/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$Type
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 */
package JinRyuu.FamilyC;

import JinRyuu.FamilyC.FamilyCClient;
import JinRyuu.FamilyC.FamilyCKeyHandler;
import JinRyuu.FamilyC.mod_FamilyC;
import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.MovingObjectPosition;

public class FamilyCCliTicH {
    public Minecraft mc = FamilyCClient.mc;
    public int testing = 0;
    public int check = 0;
    private static int gen = JRMCoreH.pg;
    public static String[] mp = JRMCoreH.p;

    public void onRenderTickInGUI(GuiScreen guiscreen) {
        if (this.mc.field_71439_g != null) {
            // empty if block
        }
        if (this.mc.field_71439_g == null || this.testing == 1) {
            // empty if block
        }
    }

    public static boolean onHotbar(Item item, EntityPlayer player) {
        for (int i = 0; i < 9; ++i) {
            if (player.field_71071_by.func_70301_a(i) == null || player.field_71071_by.func_70301_a(i).func_77973_b() != item) continue;
            return true;
        }
        return false;
    }

    public void onRenderTick() {
        if (this.mc.field_71415_G) {
            FamilyCCliTicH familyCCliTicH = this;
            if (familyCCliTicH.mc.func_71382_s()) {
                // empty if block
            }
        }
    }

    public void onTickInGUI(GuiScreen guiscreen) {
        EntityClientPlayerMP plyr = this.mc.field_71439_g;
        if (plyr != null && !plyr.field_70128_L && this.mc.field_71441_e != null && this.check >= 10) {
            FamilyCCliTicH.jfct(1);
            this.check = 2;
        }
    }

    public static void jfct(int tick) {
    }

    public void onTickInGame() {
        EntityClientPlayerMP plyr = this.mc.field_71439_g;
        if (plyr != null && !plyr.field_70128_L && this.mc.field_71441_e != null) {
            if (FamilyCKeyHandler.Interact.func_151470_d()) {
                JRMCoreH.targ = this.mc.field_71476_x != null ? (this.mc.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY ? this.mc.field_71476_x.field_72308_g : null) : null;
                plyr.openGui((Object)mod_FamilyC.instance, 1, plyr.field_70170_p, (int)plyr.field_70165_t, (int)plyr.field_70163_u, (int)plyr.field_70161_v);
            }
            if (this.check >= 10) {
                FamilyCCliTicH.jfct(1);
                this.check = 2;
            }
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase.equals((Object)TickEvent.Phase.START)) {
            EntityClientPlayerMP Cplayer = FMLClientHandler.instance().getClientPlayerEntity();
            this.onTickInGame();
        }
        if (event.phase.equals((Object)TickEvent.Phase.END) && event.type.equals((Object)TickEvent.Type.RENDER)) {
            this.onRenderTick();
        }
    }
}

