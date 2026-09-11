/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.Container
 */
package riskyken.armourersWorkshop.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import org.apache.commons.lang3.ArrayUtils;
import riskyken.armourersWorkshop.common.config.ConfigHandler;
import riskyken.armourersWorkshop.common.inventory.ContainerArmourLibrary;
import riskyken.armourersWorkshop.common.network.ByteBufHelper;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientSkinPart;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinIdentifier;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinLibrary;
import riskyken.armourersWorkshop.utils.ModLogger;

public final class SkinUploadHelper {
    private static final HashMap<Integer, byte[]> unfinishedSkins = new HashMap();
    private static final int MAX_PACKET_SIZE = 30000;

    public static void uploadSkinToServer(Skin skin) {
        int i;
        if (!ConfigHandler.allowClientsToUploadSkins) {
            return;
        }
        skin.requestId = new SkinIdentifier(skin);
        ModLogger.log("Uploading skin to server: " + skin);
        byte[] skinData = ByteBufHelper.convertSkinToByteArray(skin);
        ArrayList<MessageClientSkinPart> packetQueue = new ArrayList<MessageClientSkinPart>();
        int packetsNeeded = (int)Math.ceil((double)skinData.length / 30000.0);
        int bytesLeftToSend = skinData.length;
        int bytesSent = 0;
        for (i = 0; i < packetsNeeded; ++i) {
            boolean lastPacket = i == packetsNeeded - 1;
            byte[] messageData = lastPacket ? new byte[bytesLeftToSend] : new byte[30000];
            System.arraycopy(skinData, bytesSent, messageData, 0, messageData.length);
            MessageClientSkinPart skinMessage = new MessageClientSkinPart(skin.lightHash(), (byte)i, messageData);
            packetQueue.add(skinMessage);
            bytesLeftToSend -= messageData.length;
            bytesSent += messageData.length;
        }
        for (i = 0; i < packetQueue.size(); ++i) {
            PacketHandler.networkWrapper.sendToServer((IMessage)packetQueue.get(i));
        }
    }

    public static void gotSkinPartFromClient(int skinId, byte packetId, byte[] skinData, EntityPlayerMP player) {
        boolean lastPacket = skinData.length < 30000;
        byte[] oldSkinData = unfinishedSkins.get(skinId);
        byte[] newSkinData = null;
        if (oldSkinData != null) {
            newSkinData = ArrayUtils.addAll(oldSkinData, skinData);
            unfinishedSkins.remove(skinId);
        } else {
            newSkinData = skinData;
        }
        if (!lastPacket) {
            unfinishedSkins.put(skinId, newSkinData);
        } else {
            Skin skin = ByteBufHelper.convertByteArrayToSkin(newSkinData);
            ModLogger.log("Downloaded skin " + skin + " from client " + player);
            Container container = player.field_71070_bA;
            if (!ConfigHandler.allowClientsToUploadSkins) {
                return;
            }
            if (container != null && container instanceof ContainerArmourLibrary) {
                TileEntitySkinLibrary te = ((ContainerArmourLibrary)container).getTileEntity();
                te.loadArmour(skin, player);
            }
        }
    }
}

