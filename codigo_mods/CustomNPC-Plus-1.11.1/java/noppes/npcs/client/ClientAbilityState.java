/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 */
package noppes.npcs.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class ClientAbilityState {
    public static boolean movementLocked = false;
    public static boolean rotationLocked = false;
    public static boolean hasAbilityMovement = false;
    public static boolean positionLocked = false;
    public static boolean wasFlyingAtLock = false;
    public static boolean activePhase = false;
    public static float lockedYaw = 0.0f;
    public static float lockedPitch = 0.0f;

    public static void update(byte flags, float yaw, float pitch) {
        movementLocked = (flags & 1) != 0;
        rotationLocked = (flags & 2) != 0;
        hasAbilityMovement = (flags & 4) != 0;
        positionLocked = (flags & 8) != 0;
        wasFlyingAtLock = (flags & 0x10) != 0;
        activePhase = (flags & 0x20) != 0;
        lockedYaw = yaw;
        lockedPitch = pitch;
    }

    public static boolean shouldSuppressMovementInput() {
        return movementLocked || hasAbilityMovement || positionLocked;
    }

    public static boolean shouldLockRotation() {
        return rotationLocked;
    }

    public static void reset() {
        movementLocked = false;
        rotationLocked = false;
        hasAbilityMovement = false;
        positionLocked = false;
        wasFlyingAtLock = false;
        activePhase = false;
        lockedYaw = 0.0f;
        lockedPitch = 0.0f;
    }
}

