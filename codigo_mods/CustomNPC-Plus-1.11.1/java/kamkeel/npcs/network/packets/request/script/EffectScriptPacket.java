/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.network.packets.request.script;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.PacketUtil;
import kamkeel.npcs.network.enums.EnumRequestPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.LogWriter;
import noppes.npcs.config.ConfigDebug;
import noppes.npcs.config.ConfigScript;
import noppes.npcs.controllers.CustomEffectController;
import noppes.npcs.controllers.data.CustomEffect;
import noppes.npcs.controllers.data.EffectScript;

public final class EffectScriptPacket
extends AbstractPacket {
    public static String packetName = "Request|EffectScript";
    private Action type;
    private int id;
    private int page;
    private int maxSize;
    private NBTTagCompound compound;

    public EffectScriptPacket() {
    }

    public EffectScriptPacket(Action type, int id, int page, int maxSize, NBTTagCompound compound) {
        this.type = type;
        this.id = id;
        this.page = page;
        this.maxSize = maxSize;
        this.compound = compound;
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.EffectScript;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    public CustomNpcsPermissions.Permission getPermission() {
        return CustomNpcsPermissions.SCRIPT_PLAYER;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.type.ordinal());
        out.writeInt(this.id);
        if (this.type == Action.SAVE) {
            out.writeInt(this.page);
            out.writeInt(this.maxSize);
            ByteBufUtils.writeNBT(out, this.compound);
        }
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!ConfigScript.canScript(player, CustomNpcsPermissions.SCRIPT)) {
            return;
        }
        Action requestedAction = Action.values()[in.readInt()];
        CustomEffect effect = CustomEffectController.getInstance().get(in.readInt());
        if (effect == null) {
            return;
        }
        EffectScript data = effect.getOrCreateScriptHandler();
        if (requestedAction == Action.GET) {
            PacketUtil.getScripts(data, (EntityPlayerMP)player);
        } else {
            data.saveScript(in);
            if (ConfigDebug.PlayerLogging && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
                LogWriter.script(String.format("[%s] (Player) %s SAVED EFFECT %s [%s]", "EFFECT SCRIPTS", player.func_70005_c_(), effect.getName(), effect.id));
            }
        }
    }

    public static void Save(int effectID, int id, int maxSize, NBTTagCompound compound) {
        PacketClient.sendClient(new EffectScriptPacket(Action.SAVE, effectID, id, maxSize, compound));
    }

    public static void Get(int effectID) {
        PacketClient.sendClient(new EffectScriptPacket(Action.GET, effectID, -1, -1, new NBTTagCompound()));
    }

    private static enum Action {
        GET,
        SAVE;

    }
}

