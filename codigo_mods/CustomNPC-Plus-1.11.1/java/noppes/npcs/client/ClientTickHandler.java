/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ClientTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ContainerPlayer
 *  net.minecraft.world.World
 */
package noppes.npcs.client;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import java.util.ArrayList;
import kamkeel.npcs.client.renderer.lightning.LightningBolt;
import kamkeel.npcs.controllers.data.energycharge.EnergyChargePreviewManager;
import kamkeel.npcs.controllers.data.telegraph.TelegraphManager;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.player.CheckPlayerValue;
import kamkeel.npcs.network.packets.player.ScreenSizePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.world.World;
import noppes.npcs.CustomNpcs;
import noppes.npcs.client.ClientAbilityState;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.ClientEventHandler;
import noppes.npcs.client.KeyPressHandler;
import noppes.npcs.client.controllers.MusicController;
import noppes.npcs.client.controllers.ScriptSoundController;
import noppes.npcs.client.gui.hud.ClientHudManager;
import noppes.npcs.client.gui.hud.CompassHudComponent;
import noppes.npcs.client.gui.hud.EnumHudComponent;
import noppes.npcs.client.gui.hud.HudComponent;
import noppes.npcs.client.renderer.RenderNPCInterface;
import noppes.npcs.constants.EnumRoleType;
import noppes.npcs.controllers.data.MarkData;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleMount;

public class ClientTickHandler {
    private World prevWorld;
    private int prevWidth = 0;
    private int prevHeight = 0;
    private boolean otherContainer = false;
    private boolean wasMovementSuppressed = false;
    private final int SCAN_RANGE = 128;

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onClientTick(TickEvent.ClientTickEvent event) {
        EntityClientPlayerMP player;
        Minecraft mc = Minecraft.func_71410_x();
        if ((this.prevWorld == null || mc.field_71441_e == null) && this.prevWorld != mc.field_71441_e) {
            if (mc.field_71441_e == null) {
                ClientCacheHandler.clearCache();
                ClientAbilityState.reset();
                if (EnergyChargePreviewManager.ClientInstance != null) {
                    EnergyChargePreviewManager.ClientInstance.clear();
                }
            }
            this.prevWorld = mc.field_71441_e;
        }
        if (event.phase == TickEvent.Phase.START && (player = mc.field_71439_g) != null) {
            boolean suppressInput;
            boolean shouldSuppress = ClientAbilityState.shouldSuppressMovementInput();
            boolean bl = suppressInput = mc.field_71462_r == null && shouldSuppress;
            if (!suppressInput && this.wasMovementSuppressed) {
                this.syncMovementKeyStates(mc);
            }
            this.wasMovementSuppressed = suppressInput;
            if (suppressInput) {
                KeyBinding.func_74510_a((int)mc.field_71474_y.field_74351_w.func_151463_i(), (boolean)false);
                KeyBinding.func_74510_a((int)mc.field_71474_y.field_74368_y.func_151463_i(), (boolean)false);
                KeyBinding.func_74510_a((int)mc.field_71474_y.field_74370_x.func_151463_i(), (boolean)false);
                KeyBinding.func_74510_a((int)mc.field_71474_y.field_74366_z.func_151463_i(), (boolean)false);
                KeyBinding.func_74510_a((int)mc.field_71474_y.field_74314_A.func_151463_i(), (boolean)false);
                KeyBinding.func_74510_a((int)mc.field_71474_y.field_74311_E.func_151463_i(), (boolean)false);
                KeyBinding.func_74510_a((int)mc.field_71474_y.field_151444_V.func_151463_i(), (boolean)false);
            }
            if (shouldSuppress) {
                mc.field_71439_g.field_71158_b.field_78900_b = 0.0f;
                mc.field_71439_g.field_71158_b.field_78902_a = 0.0f;
                mc.field_71439_g.field_71158_b.field_78901_c = false;
                mc.field_71439_g.field_71158_b.field_78899_d = false;
            }
            if (ClientAbilityState.shouldLockRotation()) {
                mc.field_71439_g.field_70177_z = ClientAbilityState.lockedYaw;
                mc.field_71439_g.field_70125_A = ClientAbilityState.lockedPitch;
                mc.field_71439_g.field_70126_B = ClientAbilityState.lockedYaw;
                mc.field_71439_g.field_70127_C = ClientAbilityState.lockedPitch;
            }
            if (player.field_70154_o instanceof EntityNPCInterface) {
                RoleMount role;
                EntityNPCInterface mount = (EntityNPCInterface)player.field_70154_o;
                if (mount.advanced.role == EnumRoleType.Mount && mount.roleInterface instanceof RoleMount && !(role = (RoleMount)mount.roleInterface).isSprintAllowed() && player.func_70051_ag()) {
                    player.func_70031_b(false);
                }
            }
        }
        if (event.phase == TickEvent.Phase.END) {
            if (mc.field_71439_g != null) {
                if (ClientAbilityState.shouldSuppressMovementInput()) {
                    mc.field_71439_g.field_71158_b.field_78900_b = 0.0f;
                    mc.field_71439_g.field_71158_b.field_78902_a = 0.0f;
                    mc.field_71439_g.field_71158_b.field_78901_c = false;
                    mc.field_71439_g.field_71158_b.field_78899_d = false;
                    if (!ClientAbilityState.hasAbilityMovement) {
                        mc.field_71439_g.field_70159_w = 0.0;
                        mc.field_71439_g.field_70179_y = 0.0;
                    }
                    if ((ClientAbilityState.movementLocked || ClientAbilityState.positionLocked) && !ClientAbilityState.hasAbilityMovement) {
                        mc.field_71439_g.field_70181_x = ClientAbilityState.wasFlyingAtLock ? 0.0 : Math.min(mc.field_71439_g.field_70181_x, 0.0);
                    }
                }
                if (ClientAbilityState.shouldLockRotation()) {
                    mc.field_71439_g.field_70177_z = ClientAbilityState.lockedYaw;
                    mc.field_71439_g.field_70125_A = ClientAbilityState.lockedPitch;
                    mc.field_71439_g.field_70126_B = ClientAbilityState.lockedYaw;
                    mc.field_71439_g.field_70127_C = ClientAbilityState.lockedPitch;
                    mc.field_71439_g.field_70759_as = ClientAbilityState.lockedYaw;
                }
                if (mc.field_71441_e != null && !mc.func_147113_T() && ClientEventHandler.hasOverlays((EntityPlayer)mc.field_71439_g)) {
                    ClientEventHandler.renderCNPCPlayer.itemRenderer.func_78441_a();
                }
            }
            if (TelegraphManager.ClientInstance != null) {
                TelegraphManager.ClientInstance.tick((World)mc.field_71441_e);
            }
            if (EnergyChargePreviewManager.ClientInstance != null) {
                EnergyChargePreviewManager.ClientInstance.tick((World)mc.field_71441_e);
            }
            LightningBolt.updateAll();
            return;
        }
        if (mc.field_71439_g != null && mc.field_71439_g.field_71070_bA instanceof ContainerPlayer) {
            if (this.otherContainer) {
                PacketClient.sendClient(new CheckPlayerValue(CheckPlayerValue.Type.CheckQuestCompletion));
                this.otherContainer = false;
            }
        } else {
            this.otherContainer = true;
        }
        ++CustomNpcs.ticks;
        ++RenderNPCInterface.LastTextureTick;
        if (MusicController.Instance.isPlaying() && MusicController.Instance.getEntity() != null) {
            Entity entity = MusicController.Instance.getEntity();
            if (MusicController.Instance.getOffRange() > 0 && (Minecraft.func_71410_x().field_71439_g == null || Minecraft.func_71410_x().field_71439_g.func_70032_d(entity) > (float)MusicController.Instance.getOffRange() || entity.field_71093_bK != Minecraft.func_71410_x().field_71439_g.field_71093_bK)) {
                MusicController.Instance.stopMusic();
            }
        }
        MusicController.Instance.onUpdate();
        ScriptSoundController.Instance.onUpdate();
        if (Minecraft.func_71410_x().field_71439_g != null && (this.prevWidth != mc.field_71443_c || this.prevHeight != mc.field_71440_d)) {
            this.prevWidth = mc.field_71443_c;
            this.prevHeight = mc.field_71440_d;
            PacketClient.sendClient(new ScreenSizePacket(mc.field_71443_c, mc.field_71440_d));
        }
        if (mc.field_71441_e == null) {
            return;
        }
        if (mc.field_71441_e.func_82737_E() % 20L == 0L) {
            this.updateCompassMarks();
        }
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.START || event.side != Side.CLIENT) {
            return;
        }
        Minecraft mc = Minecraft.func_71410_x();
        if (mc.field_71439_g == null) {
            return;
        }
        if (!ClientAbilityState.shouldSuppressMovementInput()) {
            return;
        }
        if (!ClientAbilityState.hasAbilityMovement) {
            mc.field_71439_g.field_70159_w = 0.0;
            mc.field_71439_g.field_70179_y = 0.0;
        }
        if ((ClientAbilityState.movementLocked || ClientAbilityState.positionLocked) && !ClientAbilityState.hasAbilityMovement) {
            mc.field_71439_g.field_70181_x = ClientAbilityState.wasFlyingAtLock ? 0.0 : Math.min(mc.field_71439_g.field_70181_x, 0.0);
        }
    }

    private void syncMovementKeyStates(Minecraft mc) {
        this.syncKeyBindingState(mc.field_71474_y.field_74351_w);
        this.syncKeyBindingState(mc.field_71474_y.field_74368_y);
        this.syncKeyBindingState(mc.field_71474_y.field_74370_x);
        this.syncKeyBindingState(mc.field_71474_y.field_74366_z);
        this.syncKeyBindingState(mc.field_71474_y.field_74314_A);
        this.syncKeyBindingState(mc.field_71474_y.field_74311_E);
        this.syncKeyBindingState(mc.field_71474_y.field_151444_V);
    }

    private void syncKeyBindingState(KeyBinding keyBinding) {
        KeyBinding.func_74510_a((int)keyBinding.func_151463_i(), (boolean)KeyPressHandler.isKeyBindDown(keyBinding));
    }

    private void updateCompassMarks() {
        Minecraft mc = Minecraft.func_71410_x();
        EntityClientPlayerMP player = mc.field_71439_g;
        if (player == null || mc.field_71441_e == null) {
            return;
        }
        if (ClientHudManager.getInstance() == null || ClientHudManager.getInstance().getHudComponents() == null) {
            return;
        }
        HudComponent compass = ClientHudManager.getInstance().getHudComponents().get((Object)EnumHudComponent.QuestCompass);
        if (!(compass instanceof CompassHudComponent)) {
            return;
        }
        if (!compass.enabled) {
            return;
        }
        ArrayList<CompassHudComponent.MarkTargetEntry> marks = new ArrayList<CompassHudComponent.MarkTargetEntry>();
        block0: for (Object entity : mc.field_71441_e.field_72996_f) {
            if (!(entity instanceof EntityNPCInterface)) continue;
            EntityNPCInterface npc = (EntityNPCInterface)((Object)entity);
            if (npc.field_71093_bK != player.field_71093_bK || player.func_70032_d((Entity)npc) > 128.0f) continue;
            MarkData markData = MarkData.get(npc);
            for (MarkData.Mark mark : markData.marks) {
                if (mark.getType() == 0 || !mark.availability.isAvailable((EntityPlayer)player)) continue;
                marks.add(new CompassHudComponent.MarkTargetEntry(npc.field_70165_t, npc.field_70161_v, mark.getType(), mark.color));
                continue block0;
            }
        }
        ((CompassHudComponent)compass).updateMarkTargets(marks);
    }
}

