/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.Vec3
 */
package kamkeel.npcs.util;

import kamkeel.npcs.controllers.data.ability.data.energy.EnergyAnchorData;
import kamkeel.npcs.controllers.data.ability.enums.AnchorPoint;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;
import noppes.npcs.constants.EnumAnimationPart;
import noppes.npcs.controllers.data.AnimationData;
import noppes.npcs.controllers.data.Frame;
import noppes.npcs.controllers.data.FramePart;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.entity.data.ModelScalePart;

public class AnchorPointHelper {
    private static final float CLIENT_PLAYER_Y_OFFSET = -1.6f;
    private static final float MODEL_SCALE = 0.0625f;
    private static final float SHOULDER_Y = 2.0f;
    private static final float SHOULDER_X_OFFSET = 5.0f;
    private static final float PALM_DOWN = 11.0f;
    private static final float PALM_FORWARD = 2.0f;
    private static final float PALM_INWARD = 0.0f;
    private static final float FRONT_HEIGHT = 0.7f;
    private static final float CENTER_HEIGHT = 0.5f;
    private static final float ARM_HEIGHT = 0.75f;
    private static final float ABOVE_HEAD_HEIGHT = 1.2f;
    private static final float CHEST_HEIGHT = 0.65f;
    private static final float EYE_HEIGHT = 0.85f;
    private static final float DEFAULT_FRONT_DISTANCE = 1.0f;
    private static final float FALLBACK_LATERAL = 0.35f;
    private static final float FALLBACK_FORWARD = 0.1f;
    private static final float FALLBACK_ARM_HEIGHT = 0.45f;
    private static final ModelScalePart DEFAULT_ARM_SCALE = new ModelScalePart();

    public static Vec3 calculateAnchorPosition(EntityLivingBase entity, EnergyAnchorData anchorData) {
        return AnchorPointHelper.calculateAnchorPosition(entity, anchorData, 1.0f);
    }

    public static Vec3 calculateAnchorPosition(EntityLivingBase entity, EnergyAnchorData anchorData, float frontOffset) {
        float scale = AnchorPointHelper.getModelScale(entity);
        float height = entity.field_70131_O;
        AnchorPoint anchor = anchorData.anchorPoint;
        float anchorX = anchorData.anchorOffsetX;
        float anchorY = anchorData.anchorOffsetY;
        float anchorZ = anchorData.anchorOffsetZ;
        double x = entity.field_70165_t;
        double y = entity.field_70163_u + (anchor == AnchorPoint.EYE ? 0.0 : AnchorPointHelper.getClientPlayerYCorrection(entity));
        double z = entity.field_70161_v;
        float bodyYaw = (float)Math.toRadians(entity.field_70761_aq);
        switch (anchor) {
            case FRONT: {
                Vec3 look = entity.func_70040_Z();
                x += look.field_72450_a * (double)frontOffset * (double)scale;
                y += (double)(height * 0.7f) + look.field_72448_b * (double)frontOffset * (double)scale;
                z += look.field_72449_c * (double)frontOffset * (double)scale;
                break;
            }
            case CENTER: {
                y += (double)(height * 0.5f);
                break;
            }
            case RIGHT_HAND: {
                return AnchorPointHelper.calculateHandPosition(entity, anchorData, true, scale);
            }
            case LEFT_HAND: {
                return AnchorPointHelper.calculateHandPosition(entity, anchorData, false, scale);
            }
            case ABOVE_HEAD: {
                y += (double)(height * 1.2f);
                break;
            }
            case CHEST: {
                y += (double)(height * 0.65f);
                break;
            }
            case EYE: {
                return AnchorPointHelper.calculateEyePosition(entity, anchorData, scale);
            }
        }
        Vec3 rotatedOffset = AnchorPointHelper.rotateOffsetByYaw(anchorX * scale, anchorY * scale, anchorZ * scale, bodyYaw);
        x += rotatedOffset.field_72450_a;
        y += rotatedOffset.field_72448_b;
        z += rotatedOffset.field_72449_c;
        FramePart fullModel = AnchorPointHelper.getFullModelPart(entity);
        if (fullModel != null) {
            double dx = x - entity.field_70165_t;
            double dy = y - entity.field_70163_u;
            double dz = z - entity.field_70161_v;
            Vec3 rotated = AnchorPointHelper.applyFullModelToWorldOffset(fullModel, entity, dx, dy, dz, bodyYaw, scale);
            return Vec3.func_72443_a((double)(entity.field_70165_t + rotated.field_72450_a), (double)(entity.field_70163_u + rotated.field_72448_b), (double)(entity.field_70161_v + rotated.field_72449_c));
        }
        return Vec3.func_72443_a((double)x, (double)y, (double)z);
    }

    private static Vec3 calculateEyePosition(EntityLivingBase entity, EnergyAnchorData anchorData, float scale) {
        double pivotWorldX = entity.field_70165_t;
        double pivotWorldY = entity.field_70163_u + (double)entity.func_70047_e();
        double pivotWorldZ = entity.field_70161_v;
        float bodyYaw = (float)Math.toRadians(entity.field_70761_aq);
        float[] rotations = AnchorPointHelper.getHeadBoneRotations(entity);
        double ox = -anchorData.anchorOffsetX * scale;
        double oy = -anchorData.anchorOffsetY * scale;
        double oz = -anchorData.anchorOffsetZ * scale;
        double cosX = Math.cos(rotations[0]);
        double sinX = Math.sin(rotations[0]);
        double ry = oy * cosX - oz * sinX;
        double rz = oy * sinX + oz * cosX;
        double rx = ox;
        oy = ry;
        oz = rz;
        double cosY = Math.cos(rotations[1]);
        double sinY = Math.sin(rotations[1]);
        double rx2 = rx * cosY + oz * sinY;
        double rz2 = -rx * sinY + oz * cosY;
        rx = rx2;
        oz = rz2;
        double cosZ = Math.cos(rotations[2]);
        double sinZ = Math.sin(rotations[2]);
        double rx3 = rx * cosZ - oy * sinZ;
        double ry3 = rx * sinZ + oy * cosZ;
        rx = rx3;
        oy = ry3;
        double cosYaw = Math.cos(bodyYaw);
        double sinYaw = Math.sin(bodyYaw);
        double worldOffsetX = rx * cosYaw + oz * sinYaw;
        double worldOffsetZ = rx * sinYaw - oz * cosYaw;
        double worldOffsetY = -oy;
        double x = pivotWorldX + worldOffsetX;
        double y = pivotWorldY + worldOffsetY;
        double z = pivotWorldZ + worldOffsetZ;
        FramePart fullModel = AnchorPointHelper.getFullModelPart(entity);
        if (fullModel != null) {
            double dx = x - entity.field_70165_t;
            double dy = y - entity.field_70163_u;
            double dz = z - entity.field_70161_v;
            Vec3 rotatedFull = AnchorPointHelper.applyFullModelToWorldOffset(fullModel, entity, dx, dy, dz, bodyYaw, scale);
            return Vec3.func_72443_a((double)(entity.field_70165_t + rotatedFull.field_72450_a), (double)(entity.field_70163_u + rotatedFull.field_72448_b), (double)(entity.field_70161_v + rotatedFull.field_72449_c));
        }
        return Vec3.func_72443_a((double)x, (double)y, (double)z);
    }

    private static float[] getHeadBoneRotations(EntityLivingBase entity) {
        FramePart headPart;
        Frame frame;
        AnimationData animData = AnimationData.getData((Entity)entity);
        if (animData != null && animData.isActive() && animData.animation != null && (frame = (Frame)animData.animation.currentFrame()) != null && (headPart = frame.frameParts.get((Object)EnumAnimationPart.HEAD)) != null) {
            return AnchorPointHelper.getRotations(headPart, entity);
        }
        float pitch = (float)Math.toRadians(entity.field_70125_A);
        float yawDelta = (float)Math.toRadians(entity.field_70759_as - entity.field_70761_aq);
        return new float[]{pitch, yawDelta, 0.0f};
    }

    private static float getModelScale(EntityLivingBase entity) {
        if (entity instanceof EntityNPCInterface) {
            int modelSize = ((EntityNPCInterface)entity).display.modelSize;
            return (float)modelSize / 5.0f;
        }
        return 1.0f;
    }

    private static double getClientPlayerYCorrection(EntityLivingBase entity) {
        if (entity.field_70170_p.field_72995_K && entity instanceof EntityPlayerSP) {
            return -1.6f;
        }
        return 0.0;
    }

    private static ModelScalePart getArmScale(EntityLivingBase entity) {
        if (entity instanceof EntityCustomNpc) {
            return ((EntityCustomNpc)entity).modelData.modelScale.arms;
        }
        return DEFAULT_ARM_SCALE;
    }

    private static Vec3 calculateHandPosition(EntityLivingBase entity, EnergyAnchorData anchor, boolean rightHand, float scale) {
        FramePart fullModel;
        Vec3 pos = AnchorPointHelper.getAnimatedHandPosition(entity, anchor, rightHand, scale);
        if (pos == null) {
            pos = AnchorPointHelper.calculateFallbackHandPosition(entity, anchor, rightHand, scale);
        }
        if ((fullModel = AnchorPointHelper.getFullModelPart(entity)) != null) {
            double dx = pos.field_72450_a - entity.field_70165_t;
            double dy = pos.field_72448_b - entity.field_70163_u;
            double dz = pos.field_72449_c - entity.field_70161_v;
            float bodyYaw = (float)Math.toRadians(entity.field_70761_aq);
            Vec3 rotated = AnchorPointHelper.applyFullModelToWorldOffset(fullModel, entity, dx, dy, dz, bodyYaw, scale);
            return Vec3.func_72443_a((double)(entity.field_70165_t + rotated.field_72450_a), (double)(entity.field_70163_u + rotated.field_72448_b), (double)(entity.field_70161_v + rotated.field_72449_c));
        }
        return pos;
    }

    private static Vec3 getAnimatedHandPosition(EntityLivingBase entity, EnergyAnchorData anchor, boolean rightHand, float scale) {
        AnimationData animData = AnimationData.getData((Entity)entity);
        if (animData == null || !animData.isActive() || animData.animation == null) {
            return null;
        }
        Frame frame = (Frame)animData.animation.currentFrame();
        if (frame == null) {
            return null;
        }
        EnumAnimationPart armPart = rightHand ? EnumAnimationPart.RIGHT_ARM : EnumAnimationPart.LEFT_ARM;
        FramePart part = frame.frameParts.get((Object)armPart);
        if (part == null) {
            return null;
        }
        ModelScalePart armScale = AnchorPointHelper.getArmScale(entity);
        float[] rotations = AnchorPointHelper.getRotations(part, entity);
        float[] pivots = AnchorPointHelper.getPivots(part, entity);
        float shoulderX = rightHand ? -5.0f : 5.0f;
        float shoulderY = 2.0f;
        float shoulderZ = 0.0f;
        shoulderX += pivots[0];
        shoulderY += pivots[1];
        shoulderZ += pivots[2];
        float palmX = (rightHand ? -0.0f : 0.0f) * armScale.scaleX;
        float palmY = 11.0f * armScale.scaleY;
        float palmZ = 2.0f * armScale.scaleZ;
        double hx = palmX;
        double hy = palmY;
        double hz = palmZ;
        double cosX = Math.cos(rotations[0]);
        double sinX = Math.sin(rotations[0]);
        double newY = hy * cosX - hz * sinX;
        double newZ = hy * sinX + hz * cosX;
        hy = newY;
        hz = newZ;
        double cosY = Math.cos(rotations[1]);
        double sinY = Math.sin(rotations[1]);
        double newX = hx * cosY + hz * sinY;
        newZ = -hx * sinY + hz * cosY;
        hx = newX;
        hz = newZ;
        double cosZ = Math.cos(rotations[2]);
        double sinZ = Math.sin(rotations[2]);
        newX = hx * cosZ - hy * sinZ;
        newY = hx * sinZ + hy * cosZ;
        hx = newX;
        hy = newY;
        double modelX = (double)shoulderX + hx;
        double modelY = (double)shoulderY + hy;
        double modelZ = (double)shoulderZ + hz;
        double blockX = modelX * 0.0625 * (double)scale;
        double blockY = modelY * 0.0625 * (double)scale;
        double blockZ = modelZ * 0.0625 * (double)scale;
        float bodyYaw = (float)Math.toRadians(entity.field_70761_aq);
        double cosYaw = Math.cos(bodyYaw);
        double sinYaw = Math.sin(bodyYaw);
        double worldOffsetX = blockX * cosYaw + blockZ * sinYaw;
        double worldOffsetZ = blockX * sinYaw - blockZ * cosYaw;
        float shoulderHeight = entity.field_70131_O * 0.75f;
        double worldX = entity.field_70165_t + worldOffsetX;
        double worldY = entity.field_70163_u + AnchorPointHelper.getClientPlayerYCorrection(entity) + (double)shoulderHeight - blockY;
        double worldZ = entity.field_70161_v + worldOffsetZ;
        Vec3 rotatedOffset = AnchorPointHelper.rotateOffsetByYaw(anchor.anchorOffsetX * scale, anchor.anchorOffsetY * scale, anchor.anchorOffsetZ * scale, bodyYaw);
        return Vec3.func_72443_a((double)(worldX += rotatedOffset.field_72450_a), (double)(worldY += rotatedOffset.field_72448_b), (double)(worldZ += rotatedOffset.field_72449_c));
    }

    private static float[] getRotations(FramePart part, EntityLivingBase entity) {
        if (entity.field_70170_p.field_72995_K) {
            part.interpolateAngles();
            return part.prevRotations;
        }
        float pi = (float)Math.PI / 180;
        return new float[]{part.rotation[0] * pi, part.rotation[1] * pi, part.rotation[2] * pi};
    }

    private static float[] getPivots(FramePart part, EntityLivingBase entity) {
        if (entity.field_70170_p.field_72995_K) {
            part.interpolateOffset();
            return part.prevPivots;
        }
        return part.pivot;
    }

    private static Vec3 calculateFallbackHandPosition(EntityLivingBase entity, EnergyAnchorData anchor, boolean rightHand, float scale) {
        float bodyYaw = (float)Math.toRadians(entity.field_70761_aq);
        ModelScalePart armScale = AnchorPointHelper.getArmScale(entity);
        float perpYaw = bodyYaw + (rightHand ? 1.5707964f : -1.5707964f);
        double lateralX = -Math.sin(perpYaw) * (double)0.35f * (double)scale;
        double lateralZ = Math.cos(perpYaw) * (double)0.35f * (double)scale;
        double forwardX = -Math.sin(bodyYaw) * (double)0.1f * (double)scale;
        double forwardZ = Math.cos(bodyYaw) * (double)0.1f * (double)scale;
        double armLengthOffset = (double)armScale.scaleY * 0.3 * (double)scale;
        double x = entity.field_70165_t + lateralX + forwardX;
        double y = entity.field_70163_u + AnchorPointHelper.getClientPlayerYCorrection(entity) + (double)(entity.field_70131_O * 0.45f) + armLengthOffset;
        double z = entity.field_70161_v + lateralZ + forwardZ;
        Vec3 rotatedOffset = AnchorPointHelper.rotateOffsetByYaw(anchor.anchorOffsetX * scale, anchor.anchorOffsetY * scale, anchor.anchorOffsetZ * scale, bodyYaw);
        return Vec3.func_72443_a((double)(x += rotatedOffset.field_72450_a), (double)(y += rotatedOffset.field_72448_b), (double)(z += rotatedOffset.field_72449_c));
    }

    public static float getHeightMultiplier(AnchorPoint anchor) {
        switch (anchor) {
            case FRONT: {
                return 0.7f;
            }
            case CENTER: {
                return 0.5f;
            }
            case RIGHT_HAND: 
            case LEFT_HAND: {
                return 0.75f;
            }
            case ABOVE_HEAD: {
                return 1.2f;
            }
            case CHEST: {
                return 0.65f;
            }
            case EYE: {
                return 0.85f;
            }
        }
        return 0.7f;
    }

    private static Vec3 rotateOffsetByYaw(double offsetX, double offsetY, double offsetZ, float bodyYawRad) {
        double cos = Math.cos(bodyYawRad);
        double sin = Math.sin(bodyYawRad);
        double worldX = -offsetX * cos - offsetZ * sin;
        double worldZ = -offsetX * sin + offsetZ * cos;
        return Vec3.func_72443_a((double)worldX, (double)offsetY, (double)worldZ);
    }

    private static FramePart getFullModelPart(EntityLivingBase entity) {
        AnimationData animData = AnimationData.getData((Entity)entity);
        if (animData == null || !animData.isActive() || animData.animation == null) {
            return null;
        }
        Frame frame = (Frame)animData.animation.currentFrame();
        if (frame == null) {
            return null;
        }
        FramePart part = frame.frameParts.get((Object)EnumAnimationPart.FULL_MODEL);
        return part;
    }

    private static Vec3 applyFullModelToWorldOffset(FramePart fullModel, EntityLivingBase entity, double worldDX, double worldDY, double worldDZ, float bodyYaw, float scale) {
        boolean hasPivot;
        float[] rotations = AnchorPointHelper.getRotations(fullModel, entity);
        float[] pivots = AnchorPointHelper.getPivots(fullModel, entity);
        boolean hasRotation = rotations[0] != 0.0f || rotations[1] != 0.0f || rotations[2] != 0.0f;
        boolean bl = hasPivot = pivots[0] != 0.0f || pivots[1] != 0.0f || pivots[2] != 0.0f;
        if (!hasRotation && !hasPivot) {
            return Vec3.func_72443_a((double)worldDX, (double)worldDY, (double)worldDZ);
        }
        double cosYaw = Math.cos(bodyYaw);
        double sinYaw = Math.sin(bodyYaw);
        double mx = worldDX * cosYaw + worldDZ * sinYaw;
        double mz = worldDX * sinYaw - worldDZ * cosYaw;
        double my = -worldDY;
        double cosZ = Math.cos(rotations[2]);
        double sinZ = Math.sin(rotations[2]);
        double nx = mx * cosZ - my * sinZ;
        double ny = mx * sinZ + my * cosZ;
        mx = nx;
        my = ny;
        double cosY = Math.cos(rotations[1]);
        double sinY = Math.sin(rotations[1]);
        nx = mx * cosY + mz * sinY;
        double nz = -mx * sinY + mz * cosY;
        mx = nx;
        mz = nz;
        double cosX = Math.cos(rotations[0]);
        double sinX = Math.sin(rotations[0]);
        ny = my * cosX - mz * sinX;
        nz = my * sinX + mz * cosX;
        my = ny;
        mz = nz;
        double newDX = (mx += (double)(pivots[0] * 0.0625f * scale)) * cosYaw + (mz += (double)(pivots[2] * 0.0625f * scale)) * sinYaw;
        double newDZ = mx * sinYaw - mz * cosYaw;
        double newDY = -(my -= (double)(pivots[1] * 0.0625f * scale));
        return Vec3.func_72443_a((double)newDX, (double)newDY, (double)newDZ);
    }
}

