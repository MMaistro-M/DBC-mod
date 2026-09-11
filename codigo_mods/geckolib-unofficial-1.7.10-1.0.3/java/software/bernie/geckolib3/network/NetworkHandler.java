/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 */
package software.bernie.geckolib3.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import software.bernie.geckolib3.network.PacketRemoveAnimation;
import software.bernie.geckolib3.network.PacketRemoveModel;
import software.bernie.geckolib3.network.PacketSendAnimation;
import software.bernie.geckolib3.network.PacketSendModel;

public class NetworkHandler {
    private static final SimpleNetworkWrapper wrapper = new SimpleNetworkWrapper("geckolib3");

    public static void init() {
        wrapper.registerMessage(PacketSendModel.class, PacketSendModel.class, 0, Side.CLIENT);
        wrapper.registerMessage(PacketRemoveModel.class, PacketRemoveModel.class, 1, Side.CLIENT);
        wrapper.registerMessage(PacketSendAnimation.class, PacketSendAnimation.class, 2, Side.CLIENT);
        wrapper.registerMessage(PacketRemoveAnimation.class, PacketRemoveAnimation.class, 3, Side.CLIENT);
    }

    public static void sendToPlayer(IMessage message, EntityPlayer player) {
        if (player == null) {
            return;
        }
        wrapper.sendTo(message, (EntityPlayerMP)player);
    }

    public static void sendToAll(IMessage message) {
        if (MinecraftServer.func_71276_C() == null || MinecraftServer.func_71276_C().func_71203_ab() == null || MinecraftServer.func_71276_C().func_71203_ab().field_72404_b.isEmpty()) {
            return;
        }
        wrapper.sendToAll(message);
    }

    public static void sendToServer(IMessage message) {
        wrapper.sendToServer(message);
    }
}

