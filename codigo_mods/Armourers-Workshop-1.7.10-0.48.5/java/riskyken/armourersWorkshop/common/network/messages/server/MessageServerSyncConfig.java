/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 */
package riskyken.armourersWorkshop.common.network.messages.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.Arrays;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import riskyken.armourersWorkshop.common.addons.ModAddonManager;
import riskyken.armourersWorkshop.common.config.ConfigHandler;
import riskyken.armourersWorkshop.common.network.ByteBufHelper;

public class MessageServerSyncConfig
implements IMessage,
IMessageHandler<MessageServerSyncConfig, IMessage> {
    private boolean allowClientsToDownloadSkins = ConfigHandler.allowClientsToDownloadSkins;
    private boolean allowClientsToUploadSkins = ConfigHandler.allowClientsToUploadSkins;
    private String[] itemOverrides = ModAddonManager.itemOverrides.toArray(new String[ModAddonManager.itemOverrides.size()]);
    private boolean libraryShowsModelPreviews = ConfigHandler.libraryShowsModelPreviews;
    private boolean lockDyesOnSkins = ConfigHandler.lockDyesOnSkins;
    private boolean instancedDyeTable = ConfigHandler.instancedDyeTable;
    private boolean enableRecoveringSkins = ConfigHandler.enableRecoveringSkins;
    private UUID playerId;

    public MessageServerSyncConfig(EntityPlayer player) {
        this();
        this.playerId = player.func_110124_au();
    }

    public MessageServerSyncConfig() {
    }

    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.allowClientsToDownloadSkins);
        buf.writeBoolean(this.allowClientsToUploadSkins);
        ByteBufHelper.writeStringArrayToBuf(buf, this.itemOverrides);
        buf.writeBoolean(this.libraryShowsModelPreviews);
        buf.writeBoolean(this.lockDyesOnSkins);
        buf.writeBoolean(this.instancedDyeTable);
        buf.writeBoolean(this.enableRecoveringSkins);
        if (this.playerId == null) {
            buf.writeBoolean(false);
        } else {
            buf.writeBoolean(true);
            ByteBufHelper.writeUUID(buf, this.playerId);
        }
    }

    public void fromBytes(ByteBuf buf) {
        this.allowClientsToDownloadSkins = buf.readBoolean();
        this.allowClientsToUploadSkins = buf.readBoolean();
        this.itemOverrides = ByteBufHelper.readStringArrayFromBuf(buf);
        this.libraryShowsModelPreviews = buf.readBoolean();
        this.lockDyesOnSkins = buf.readBoolean();
        this.instancedDyeTable = buf.readBoolean();
        this.enableRecoveringSkins = buf.readBoolean();
        if (buf.readBoolean()) {
            this.playerId = ByteBufHelper.readUUID(buf);
        }
    }

    public IMessage onMessage(MessageServerSyncConfig message, MessageContext ctx) {
        this.setConfigsOnClient(message);
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    private void setConfigsOnClient(MessageServerSyncConfig message) {
        ConfigHandler.allowClientsToDownloadSkins = message.allowClientsToDownloadSkins;
        ConfigHandler.allowClientsToUploadSkins = message.allowClientsToUploadSkins;
        ModAddonManager.itemOverrides.clear();
        ModAddonManager.itemOverrides.addAll(Arrays.asList(message.itemOverrides));
        ConfigHandler.libraryShowsModelPreviews = message.libraryShowsModelPreviews;
        ConfigHandler.lockDyesOnSkins = message.lockDyesOnSkins;
        ConfigHandler.remotePlayerId = message.playerId;
        ConfigHandler.instancedDyeTable = message.instancedDyeTable;
        ConfigHandler.enableRecoveringSkins = message.enableRecoveringSkins;
    }
}

