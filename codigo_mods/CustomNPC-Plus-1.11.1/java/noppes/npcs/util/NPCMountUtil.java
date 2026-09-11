/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  net.minecraftforge.common.ForgeHooks
 */
package noppes.npcs.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.ForgeHooks;
import noppes.npcs.CustomNpcs;
import noppes.npcs.constants.EnumRoleType;
import noppes.npcs.controllers.PlayerDataController;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.roles.RoleMount;

public final class NPCMountUtil {
    private static final int MOUNT_FLIGHT_TOGGLE_WINDOW = 7;

    private NPCMountUtil() {
    }

    public static boolean handleMountedMovement(EntityNPCInterface npc, MountState state, float strafe, float forward) {
        boolean riderSprinting;
        if (npc.advanced.role != EnumRoleType.Mount || !(npc.roleInterface instanceof RoleMount)) {
            NPCMountUtil.resetMountedFlightState(npc, state);
            return false;
        }
        if (npc.field_70153_n != null && !(npc.field_70153_n instanceof EntityPlayer)) {
            npc.field_70153_n.func_70078_a(null);
            return false;
        }
        if (!(npc.field_70153_n instanceof EntityPlayer)) {
            NPCMountUtil.resetMountedFlightState(npc, state);
            return false;
        }
        RoleMount mount = (RoleMount)npc.roleInterface;
        EntityPlayer rider = (EntityPlayer)npc.field_70153_n;
        NPCMountUtil.updateMountedFlightState(npc, state, mount, rider);
        npc.field_70126_B = npc.field_70177_z = rider.field_70177_z;
        npc.field_70125_A = rider.field_70125_A * 0.5f;
        npc.func_70080_a(npc.field_70165_t, npc.field_70163_u, npc.field_70161_v, npc.field_70177_z, npc.field_70125_A);
        npc.field_70761_aq = npc.field_70177_z;
        npc.field_70759_as = npc.field_70177_z;
        float controlledStrafe = rider.field_70702_br;
        float controlledForward = rider.field_70701_bs;
        if (controlledForward <= 0.0f) {
            controlledForward *= 0.25f;
        }
        float moveSpeed = NPCMountUtil.getMountMoveSpeed(npc);
        boolean bl = riderSprinting = rider.func_70051_ag() && mount.isSprintAllowed();
        if (riderSprinting) {
            moveSpeed *= 1.2f;
        }
        if (!mount.isSprintAllowed() && rider.func_70051_ag()) {
            rider.func_70031_b(false);
        }
        if (moveSpeed <= 0.0f) {
            controlledStrafe = 0.0f;
            controlledForward = 0.0f;
        }
        npc.func_70031_b(riderSprinting);
        npc.field_70138_W = 1.0f;
        npc.func_70661_as().func_75499_g();
        npc.func_70624_b(null);
        npc.func_70604_c(null);
        npc.field_70143_R = 0.0f;
        rider.field_70143_R = 0.0f;
        NPCMountUtil.applyMountedVerticalMotion(npc, state, mount, (EntityLivingBase)rider, moveSpeed, controlledForward);
        npc.performMountedMovement(controlledStrafe, controlledForward, moveSpeed);
        return true;
    }

    private static float getMountMoveSpeed(EntityNPCInterface npc) {
        return Math.max(0.0f, npc.getSpeed());
    }

    private static void applyMountedVerticalMotion(EntityNPCInterface npc, MountState state, RoleMount mount, EntityLivingBase rider, float moveSpeed, float forward) {
        if (mount == null) {
            return;
        }
        double jumpFactor = MathHelper.func_151237_a((double)mount.getJumpStrength(), (double)0.1, (double)3.0);
        boolean flyingEnabled = mount.isFlyingMountEnabled();
        npc.setNpcJumpingState(false);
        if (flyingEnabled) {
            boolean descendPressed;
            double ascendSpeed = MathHelper.func_151237_a((double)mount.getFlyingAscendSpeed(), (double)0.1, (double)3.0);
            double descendSpeed = MathHelper.func_151237_a((double)mount.getFlyingDescendSpeed(), (double)0.05, (double)3.0);
            boolean flightMode = NPCMountUtil.isMountInFlightMode(state);
            boolean jumpPressed = rider.field_70703_bu;
            boolean bl = descendPressed = rider instanceof EntityPlayer && NPCMountUtil.isSpecialKeyDown((EntityPlayer)rider);
            if (flightMode) {
                double previousMotionY = npc.field_70181_x;
                double newMotionY = 0.0;
                if (jumpPressed) {
                    newMotionY += ascendSpeed;
                } else if (descendPressed) {
                    newMotionY -= descendSpeed;
                }
                npc.field_70181_x = newMotionY = MathHelper.func_151237_a((double)newMotionY, (double)(-descendSpeed), (double)ascendSpeed);
                npc.setNpcFlyingState(true);
                npc.setNpcJumpingState(jumpPressed);
                npc.field_70160_al = true;
                npc.field_70143_R = 0.0f;
                return;
            }
            if (jumpPressed && npc.field_70122_E) {
                npc.field_70181_x = jumpFactor;
                npc.setNpcJumpingState(true);
                npc.field_70160_al = true;
                npc.field_70143_R = 0.0f;
                if (!npc.field_70170_p.field_72995_K) {
                    ForgeHooks.onLivingJump((EntityLivingBase)npc);
                }
                return;
            }
            if (!npc.field_70122_E) {
                npc.field_70181_x = Math.max(npc.field_70181_x, -descendSpeed);
                npc.setNpcFlyingState(!npc.func_70090_H());
            } else {
                npc.setNpcFlyingState(false);
            }
            return;
        }
        if (npc.field_70122_E) {
            if (rider.field_70703_bu) {
                npc.field_70181_x = jumpFactor;
                npc.setNpcJumpingState(true);
                npc.field_70160_al = true;
                npc.field_70143_R = 0.0f;
                if (!npc.field_70170_p.field_72995_K) {
                    ForgeHooks.onLivingJump((EntityLivingBase)npc);
                }
            }
            return;
        }
        npc.setNpcFlyingState(false);
    }

    public static void handleMountRiderState(EntityNPCInterface npc, MountState state) {
        if (npc.field_70170_p.field_72995_K) {
            if (npc.advanced.role != EnumRoleType.Mount || !(npc.roleInterface instanceof RoleMount)) {
                state.lastRider = null;
            }
            return;
        }
        if (npc.advanced.role != EnumRoleType.Mount || !(npc.roleInterface instanceof RoleMount)) {
            state.lastRider = null;
            return;
        }
        RoleMount mount = (RoleMount)npc.roleInterface;
        Entity currentRider = npc.field_70153_n;
        if (currentRider != null && !currentRider.func_70089_S()) {
            currentRider.func_70078_a(null);
            NPCMountUtil.stabilizeDismountedRider(currentRider);
            NPCMountUtil.haltMountedMotion(npc, state);
            NPCMountUtil.applyUnriddenFlightDescent(npc, state, mount);
            currentRider = null;
        }
        if (currentRider != null && !(currentRider instanceof EntityPlayer)) {
            Entity dismount = currentRider;
            dismount.func_70078_a(null);
            NPCMountUtil.stabilizeDismountedRider(dismount);
            NPCMountUtil.haltMountedMotion(npc, state);
            NPCMountUtil.applyUnriddenFlightDescent(npc, state, mount);
            currentRider = null;
        }
        if (currentRider != state.lastRider) {
            if (state.lastRider != null) {
                NPCMountUtil.stabilizeDismountedRider(state.lastRider);
            }
            if (currentRider == null) {
                NPCMountUtil.haltMountedMotion(npc, state);
                NPCMountUtil.applyUnriddenFlightDescent(npc, state, mount);
            } else {
                NPCMountUtil.resetMountedFlightState(npc, state);
            }
            state.lastRider = currentRider;
        } else if (currentRider == null) {
            NPCMountUtil.applyUnriddenFlightDescent(npc, state, mount);
        }
    }

    public static boolean isFlyingMountWithFlightEnabled(EntityNPCInterface npc) {
        return npc.advanced.role == EnumRoleType.Mount && npc.roleInterface instanceof RoleMount && ((RoleMount)npc.roleInterface).isFlyingMountEnabled();
    }

    public static void haltMountedMotion(EntityNPCInterface npc, MountState state) {
        npc.field_70159_w = 0.0;
        npc.field_70181_x = 0.0;
        npc.field_70179_y = 0.0;
        npc.field_70160_al = false;
        npc.func_70031_b(false);
        npc.field_70701_bs = 0.0f;
        npc.field_70702_br = 0.0f;
        npc.func_70659_e(0.0f);
        npc.field_70721_aZ = 0.0f;
        npc.field_70754_ba = 0.0f;
        npc.func_70661_as().func_75499_g();
        npc.field_70143_R = 0.0f;
        NPCMountUtil.resetMountedFlightState(npc, state);
    }

    public static void applyUnriddenFlightDescent(EntityNPCInterface npc, MountState state, RoleMount mount) {
        if (mount == null || !mount.isFlyingMountEnabled()) {
            npc.setNpcFlyingState(false);
            npc.setNpcJumpingState(false);
            return;
        }
        if (npc.canFly()) {
            return;
        }
        if (npc.field_70122_E) {
            npc.field_70181_x = 0.0;
            npc.setNpcFlyingState(false);
            npc.setNpcJumpingState(false);
            return;
        }
        double descendSpeed = MathHelper.func_151237_a((double)mount.getFlyingDescendSpeed(), (double)0.05, (double)3.0);
        if (descendSpeed <= 0.0) {
            npc.setNpcFlyingState(false);
            npc.setNpcJumpingState(false);
            return;
        }
        npc.field_70181_x = -descendSpeed;
        npc.field_70160_al = true;
        npc.field_70143_R = 0.0f;
        npc.setNpcFlyingState(true);
        npc.setNpcJumpingState(false);
    }

    public static void stabilizeDismountedRider(Entity rider) {
        boolean wasOnGround = rider.field_70122_E;
        double previousMotionX = rider.field_70159_w;
        double previousMotionY = rider.field_70181_x;
        double previousMotionZ = rider.field_70179_y;
        float previousFall = rider.field_70143_R;
        if (wasOnGround) {
            rider.field_70159_w = 0.0;
            rider.field_70181_x = 0.0;
            rider.field_70179_y = 0.0;
            rider.field_70143_R = 0.0f;
            rider.field_70122_E = true;
        } else {
            rider.field_70159_w = previousMotionX;
            rider.field_70181_x = previousMotionY;
            rider.field_70179_y = previousMotionZ;
            rider.field_70143_R = previousFall;
        }
    }

    private static void updateMountedFlightState(EntityNPCInterface npc, MountState state, RoleMount mount, EntityPlayer rider) {
        boolean jumpPressed;
        if (mount == null || rider == null || !mount.isFlyingMountEnabled()) {
            NPCMountUtil.resetMountedFlightState(npc, state);
            return;
        }
        if (npc.field_70122_E) {
            state.flightMode = false;
        }
        if ((jumpPressed = rider.field_70703_bu) && !state.jumpPressed) {
            if (state.flightToggleTimer > 0) {
                state.flightMode = !state.flightMode;
                state.flightToggleTimer = 0;
            } else {
                state.flightToggleTimer = 7;
            }
        }
        if (state.flightToggleTimer > 0) {
            --state.flightToggleTimer;
        }
        state.jumpPressed = jumpPressed;
    }

    public static boolean isMountInFlightMode(MountState state) {
        return state.flightMode;
    }

    public static void resetMountedFlightState(EntityNPCInterface npc, MountState state) {
        state.flightMode = false;
        state.jumpPressed = false;
        state.flightToggleTimer = 0;
        npc.setNpcFlyingState(false);
        npc.setNpcJumpingState(false);
    }

    private static boolean isSpecialKeyDown(EntityPlayer rider) {
        if (rider == null) {
            return false;
        }
        if (rider.field_70170_p.field_72995_K) {
            PlayerData data = CustomNpcs.proxy.getPlayerData(rider);
            return data != null && data.isSpecialKeyDown();
        }
        PlayerData data = PlayerDataController.Instance.getPlayerData(rider);
        return data != null && data.isSpecialKeyDown();
    }

    public static class MountState {
        public Entity lastRider;
        public boolean flightMode;
        public boolean jumpPressed;
        public int flightToggleTimer;
    }
}

