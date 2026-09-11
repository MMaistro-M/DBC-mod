/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.event.RenderPlayerEvent$SetArmorModel
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.util.FakePlayer
 */
package riskyken.armourersWorkshop.client.handler;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import riskyken.armourersWorkshop.client.render.MannequinFakePlayer;
import riskyken.armourersWorkshop.common.data.PlayerPointer;
import riskyken.armourersWorkshop.common.wardrobe.EquipmentWardrobeData;
import riskyken.armourersWorkshop.common.wardrobe.ExPropsPlayerSkinData;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

@SideOnly(value=Side.CLIENT)
public final class EquipmentWardrobeHandler {
    private final HashMap<PlayerPointer, EquipmentWardrobeData> equipmentWardrobeMap;
    private final Object threadLock;

    public EquipmentWardrobeHandler() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.equipmentWardrobeMap = new HashMap();
        this.threadLock = new Object();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setEquipmentWardrobeData(PlayerPointer playerPointer, EquipmentWardrobeData ewd) {
        Object object = this.threadLock;
        synchronized (object) {
            if (this.equipmentWardrobeMap.containsKey(playerPointer)) {
                this.equipmentWardrobeMap.remove(playerPointer);
            }
            this.equipmentWardrobeMap.put(playerPointer, ewd);
        }
        EntityClientPlayerMP localPlayer = Minecraft.func_71410_x().field_71439_g;
        PlayerPointer localPointer = new PlayerPointer((EntityPlayer)localPlayer);
        if (playerPointer.equals(localPointer)) {
            ExPropsPlayerSkinData.get((EntityPlayer)localPlayer).setSkinInfo(ewd, false);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public EquipmentWardrobeData getEquipmentWardrobeData(PlayerPointer playerPointer) {
        EquipmentWardrobeData ewd = null;
        Object object = this.threadLock;
        synchronized (object) {
            ewd = this.equipmentWardrobeMap.get(playerPointer);
        }
        return ewd;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void removeEquipmentWardrobeData(PlayerPointer playerPointer) {
        Object object = this.threadLock;
        synchronized (object) {
            if (this.equipmentWardrobeMap.containsKey(playerPointer)) {
                this.equipmentWardrobeMap.remove(playerPointer);
            }
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGH)
    public void onRender(RenderPlayerEvent.SetArmorModel event) {
        PlayerPointer playerPointer;
        int slot = -event.slot + 3;
        if (slot > 3) {
            return;
        }
        EntityPlayer player = event.entityPlayer;
        if (player instanceof MannequinFakePlayer) {
            return;
        }
        if (player.func_146103_bH() == null) {
            return;
        }
        if (player instanceof FakePlayer) {
            return;
        }
        int result = -1;
        ItemStack stack = player.func_82169_q(event.slot);
        if (SkinNBTHelper.stackHasSkinData(stack)) {
            result = -2;
        }
        if (this.equipmentWardrobeMap.containsKey(playerPointer = new PlayerPointer(player))) {
            EquipmentWardrobeData ewd = this.equipmentWardrobeMap.get(playerPointer);
            if (ewd.armourOverride.get(slot)) {
                result = -2;
            }
        }
        event.result = result;
    }
}

