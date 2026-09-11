/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.relauncher.Side
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTSizeTracker
 *  net.minecraft.nbt.NBTTagCompound
 */
package com.tobiasmjc.dbcadditions.utils;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.server.JGPlayerMP;
import com.tobiasmjc.dbcadditions.data.DBCAPlayer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;

public class DataUtils {
    public static void writeNBT(ByteBuf buffer, NBTTagCompound tag) {
        try {
            byte[] bytes = CompressedStreamTools.func_74798_a((NBTTagCompound)tag);
            buffer.writeShort(bytes.length);
            buffer.writeBytes(bytes);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static NBTTagCompound readNBT(ByteBuf buffer) {
        try {
            short length = buffer.readShort();
            byte[] bytes = new byte[length];
            buffer.readBytes(bytes);
            return CompressedStreamTools.func_152457_a((byte[])bytes, (NBTSizeTracker)new NBTSizeTracker(20000L));
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int[] toIntArray(byte[] byteArray) {
        int[] intArray = new int[byteArray.length];
        for (int i = 0; i < byteArray.length; ++i) {
            intArray[i] = byteArray[i];
        }
        return intArray;
    }

    public static byte[] toByteArray(int[] intArray) {
        byte[] byteArray = new byte[intArray.length];
        for (int i = 0; i < intArray.length; ++i) {
            byteArray[i] = (byte)intArray[i];
        }
        return byteArray;
    }

    public static NBTTagCompound nbt(EntityPlayer p, String s) {
        NBTTagCompound nbt;
        if (s.contains("pres")) {
            if (!p.getEntityData().func_74764_b("PlayerPersisted")) {
                nbt = new NBTTagCompound();
                p.getEntityData().func_74782_a("PlayerPersisted", (NBTBase)nbt);
            } else {
                nbt = p.getEntityData().func_74775_l("PlayerPersisted");
            }
        } else {
            nbt = p.getEntityData();
        }
        return nbt;
    }

    public static byte getDBCAState(EntityPlayer p) {
        if (p == null) {
            return -1;
        }
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.SERVER) {
            return (byte)new DBCAPlayer((EntityPlayer)p).DBAForm;
        }
        if (JRMCoreH.plyrs == null) {
            return -1;
        }
        for (int i = 0; i < JRMCoreH.plyrs.length; ++i) {
            if (!JRMCoreH.plyrs[i].equals(p.func_70005_c_())) continue;
            return JRMCoreH.dat10 == null ? (byte)0 : Byte.parseByte(JRMCoreH.dat10[i].split(";")[2]);
        }
        return -1;
    }

    public static String getDBCAFormMasteries(EntityPlayer p) {
        if (p == null) {
            return "";
        }
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.SERVER) {
            return new DBCAPlayer((EntityPlayer)p).formMasteries;
        }
        if (JRMCoreH.plyrs == null) {
            return "";
        }
        for (int i = 0; i < JRMCoreH.plyrs.length; ++i) {
            if (!JRMCoreH.plyrs[i].equals(p.func_70005_c_())) continue;
            if (JRMCoreH.dat10 == null || JRMCoreH.dat10[i].split(";").length < 4) {
                return "";
            }
            return JRMCoreH.dat10 == null ? "" : JRMCoreH.dat10[i].split(";")[3];
        }
        return "";
    }

    public static String getDBCASkills(EntityPlayer p) {
        if (p == null) {
            return "";
        }
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.SERVER) {
            return new DBCAPlayer((EntityPlayer)p).Skills;
        }
        if (JRMCoreH.plyrs == null) {
            return "";
        }
        try {
            for (int i = 0; i < JRMCoreH.plyrs.length; ++i) {
                if (!JRMCoreH.plyrs[i].equals(p.func_70005_c_())) continue;
                return JRMCoreH.dat19 == null ? "" : JRMCoreH.dat19[i].split(";")[2];
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return "";
        }
        return "";
    }

    public static boolean isPotaraFusion(EntityPlayer p) {
        if (p == null) {
            return false;
        }
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.SERVER) {
            return DBCAPlayer.get((EntityPlayer)p).PotaraFusion;
        }
        if (JRMCoreH.plyrs == null) {
            return false;
        }
        for (int i = 0; i < JRMCoreH.plyrs.length; ++i) {
            if (!JRMCoreH.plyrs[i].equals(p.func_70005_c_())) continue;
            return JRMCoreH.dat10 == null ? false : Byte.parseByte(JRMCoreH.dat10[i].split(";")[5]) != 0;
        }
        return false;
    }

    public static int getPotaraCooldown(EntityPlayer p) {
        if (p == null) {
            return 0;
        }
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.SERVER) {
            return DBCAPlayer.get((EntityPlayer)p).PotaraCooldown;
        }
        if (JRMCoreH.plyrs == null) {
            return 0;
        }
        for (int i = 0; i < JRMCoreH.plyrs.length; ++i) {
            if (!JRMCoreH.plyrs[i].equals(p.func_70005_c_())) continue;
            return JRMCoreH.dat10 == null ? 0 : Integer.parseInt(JRMCoreH.dat10[i].split(";")[6]);
        }
        return 0;
    }

    public static byte getDBCARace(EntityPlayer p) {
        if (p == null) {
            return -1;
        }
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.SERVER) {
            return (byte)DBCAPlayer.get((EntityPlayer)p).DBARace;
        }
        if (JRMCoreH.plyrs == null) {
            return -1;
        }
        for (int i = 0; i < JRMCoreH.plyrs.length; ++i) {
            if (!JRMCoreH.plyrs[i].equals(p.func_70005_c_())) continue;
            return JRMCoreH.dat10 == null ? (byte)0 : Byte.parseByte(JRMCoreH.dat10[i].split(";")[4]);
        }
        return -1;
    }

    public static EntityPlayer getFusionPartner(EntityPlayer player) {
        String[] fusionData;
        if (player == null || JRMCoreH.plyrs == null || JRMCoreH.dat18 == null) {
            return null;
        }
        int pl = -1;
        for (int i = 0; i < JRMCoreH.plyrs.length; ++i) {
            if (!JRMCoreH.plyrs[i].equals(player.func_70005_c_())) continue;
            pl = i;
            break;
        }
        if (pl < 0 || pl >= JRMCoreH.dat18.length || JRMCoreH.dat18[pl] == null) {
            return null;
        }
        String[] fullFusionData = JRMCoreH.dat18[pl].split(";");
        if (fullFusionData.length >= 3 && (fusionData = fullFusionData[2].split(",")).length == 3) {
            EntityPlayer playerPartner = player.field_70170_p.func_72924_a(fusionData[0]);
            if (playerPartner != null && playerPartner.func_70005_c_().equals(player.func_70005_c_())) {
                playerPartner = player.field_70170_p.func_72924_a(fusionData[1]);
            }
            return playerPartner;
        }
        return null;
    }

    public static boolean legendary(EntityPlayer player) {
        if (player == null) {
            return false;
        }
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.SERVER) {
            JGPlayerMP jgPlayer = new JGPlayerMP(player);
            return JRMCoreH.StusEfcts(14, jgPlayer.getStatusEffects());
        }
        return JRMCoreH.StusEfctsMe(14);
    }

    public static boolean divine(EntityPlayer player) {
        if (player == null) {
            return false;
        }
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.SERVER) {
            JGPlayerMP jgPlayer = new JGPlayerMP(player);
            return JRMCoreH.StusEfcts(17, jgPlayer.getStatusEffects());
        }
        return JRMCoreH.StusEfctsMe(17);
    }

    public static int mergeColors(int color1, int color2) {
        int r1 = color1 >> 16 & 0xFF;
        int g1 = color1 >> 8 & 0xFF;
        int b1 = color1 & 0xFF;
        int r2 = color2 >> 16 & 0xFF;
        int g2 = color2 >> 8 & 0xFF;
        int b2 = color2 & 0xFF;
        int rMerged = (r1 + r2) / 2;
        int gMerged = (g1 + g2) / 2;
        int bMerged = (b1 + b2) / 2;
        return rMerged << 16 | gMerged << 8 | bMerged;
    }

    public static int mergeColors(int color1, int color2, float weight1) {
        float weight2 = 1.0f - weight1;
        int r1 = color1 >> 16 & 0xFF;
        int g1 = color1 >> 8 & 0xFF;
        int b1 = color1 & 0xFF;
        int r2 = color2 >> 16 & 0xFF;
        int g2 = color2 >> 8 & 0xFF;
        int b2 = color2 & 0xFF;
        int r = (int)((float)r1 * weight1 + (float)r2 * weight2);
        int g = (int)((float)g1 * weight1 + (float)g2 * weight2);
        int b = (int)((float)b1 * weight1 + (float)b2 * weight2);
        return r << 16 | g << 8 | b;
    }
}
