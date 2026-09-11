/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package riskyken.armourersWorkshop.utils;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.common.skin.cache.CommonSkinCache;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinIdentifier;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.skin.type.wings.SkinWings;
import riskyken.armourersWorkshop.utils.SkinIOUtils;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public final class SkinUtils {
    private SkinUtils() {
    }

    public static Skin getSkinDetectSide(ItemStack stack, boolean serverSoftLoad, boolean clientRequestSkin) {
        SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(stack);
        return SkinUtils.getSkinDetectSide(skinPointer, serverSoftLoad, clientRequestSkin);
    }

    public static Skin getSkinDetectSide(SkinPointer skinPointer, boolean serverSoftLoad, boolean clientRequestSkin) {
        if (skinPointer != null) {
            SkinIdentifier skinIdentifier = skinPointer.getIdentifier();
            return SkinUtils.getSkinDetectSide(skinIdentifier, serverSoftLoad, clientRequestSkin);
        }
        return null;
    }

    public static Skin getSkinDetectSide(SkinIdentifier skinIdentifier, boolean serverSoftLoad, boolean clientRequestSkin) {
        if (skinIdentifier != null) {
            if (ArmourersWorkshop.isDedicated()) {
                return SkinUtils.getSkinForSide(skinIdentifier, Side.SERVER, serverSoftLoad, clientRequestSkin);
            }
            Side side = FMLCommonHandler.instance().getEffectiveSide();
            return SkinUtils.getSkinForSide(skinIdentifier, side, serverSoftLoad, clientRequestSkin);
        }
        return null;
    }

    public static Skin getSkinForSide(SkinIdentifier skinIdentifier, Side side, boolean softLoad, boolean requestSkin) {
        if (side == Side.CLIENT) {
            return SkinUtils.getSkinOnClient(skinIdentifier, requestSkin);
        }
        return SkinUtils.getSkinOnServer(skinIdentifier, softLoad);
    }

    private static Skin getSkinOnServer(SkinIdentifier skinIdentifier, boolean softLoad) {
        if (softLoad) {
            return CommonSkinCache.INSTANCE.softGetSkin(skinIdentifier);
        }
        return CommonSkinCache.INSTANCE.getSkin(skinIdentifier);
    }

    @SideOnly(value=Side.CLIENT)
    private static Skin getSkinOnClient(SkinIdentifier skinIdentifier, boolean requestSkin) {
        return ClientSkinCache.INSTANCE.getSkin(skinIdentifier, requestSkin);
    }

    public static Skin copySkin(Skin skin) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        SkinIOUtils.saveSkinToStream(outputStream, skin);
        byte[] skinData = outputStream.toByteArray();
        Skin skinCopy = SkinIOUtils.loadSkinFromStream(new ByteArrayInputStream(skinData));
        return skinCopy;
    }

    private static int getSkinIndex(String partIndexProp, Skin skin, int partIndex) {
        String[] split = partIndexProp.split(":");
        for (int i = 0; i < split.length; ++i) {
            int count = Integer.parseInt(split[i]);
            if (partIndex >= count) continue;
            return i;
        }
        return -1;
    }

    public static double getFlapAngleForWings(Entity entity, Skin skin, int partIndex) {
        int index;
        String partIndexProp;
        double maxAngle = SkinProperties.PROP_WINGS_MAX_ANGLE.getValue(skin.getProperties());
        double minAngle = SkinProperties.PROP_WINGS_MIN_ANGLE.getValue(skin.getProperties());
        double idleSpeed = SkinProperties.PROP_WINGS_IDLE_SPEED.getValue(skin.getProperties());
        double flyingSpeed = SkinProperties.PROP_WINGS_FLYING_SPEED.getValue(skin.getProperties());
        SkinWings.MovementType movmentType = SkinWings.MovementType.valueOf(SkinProperties.PROP_WINGS_MOVMENT_TYPE.getValue(skin.getProperties()));
        if (skin.getSkinType() == SkinTypeRegistry.skinOutfit && !(partIndexProp = SkinProperties.PROP_OUTFIT_PART_INDEXS.getValue(skin.getProperties())).equals("") && (index = SkinUtils.getSkinIndex(partIndexProp, skin, partIndex)) != -1) {
            maxAngle = SkinProperties.PROP_WINGS_MAX_ANGLE.getValue(skin.getProperties(), index);
            minAngle = SkinProperties.PROP_WINGS_MIN_ANGLE.getValue(skin.getProperties(), index);
            idleSpeed = SkinProperties.PROP_WINGS_IDLE_SPEED.getValue(skin.getProperties(), index);
            flyingSpeed = SkinProperties.PROP_WINGS_FLYING_SPEED.getValue(skin.getProperties(), index);
            movmentType = SkinWings.MovementType.valueOf(SkinProperties.PROP_WINGS_MOVMENT_TYPE.getValue(skin.getProperties(), index));
        }
        double angle = 0.0;
        double flapTime = idleSpeed;
        if (entity != null) {
            if (entity.field_70160_al) {
                if (entity instanceof EntityPlayer) {
                    if (((EntityPlayer)entity).field_71075_bZ.field_75100_b) {
                        flapTime = flyingSpeed;
                    }
                } else {
                    flapTime = flyingSpeed;
                }
            }
            angle = ((double)System.currentTimeMillis() + (double)entity.func_145782_y()) % flapTime;
            if (movmentType == SkinWings.MovementType.EASE) {
                angle = Math.sin(angle / flapTime * Math.PI * 2.0);
            }
            if (movmentType == SkinWings.MovementType.LINEAR) {
                angle /= flapTime;
            }
        }
        double fullAngle = maxAngle - minAngle;
        if (movmentType == SkinWings.MovementType.LINEAR) {
            return fullAngle * angle;
        }
        return -minAngle - fullAngle * ((angle + 1.0) / 2.0);
    }
}

