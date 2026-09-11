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
import kamkeel.npcs.network.enums.EnumItemPacketType;
import kamkeel.npcs.network.enums.EnumRequestPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.LogWriter;
import noppes.npcs.config.ConfigDebug;
import noppes.npcs.config.ConfigScript;
import noppes.npcs.controllers.data.DataScript;

public final class EventScriptPacket
extends AbstractPacket {
    public static String packetName = "Request|EventScript";
    private Action type;
    private int page;
    private int maxSize;
    private NBTTagCompound compound;

    public EventScriptPacket() {
    }

    public EventScriptPacket(Action type, int page, int maxSize, NBTTagCompound compound) {
        this.type = type;
        this.page = page;
        this.maxSize = maxSize;
        this.compound = compound;
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.EventScript;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    public boolean needsNPC() {
        return true;
    }

    @Override
    public CustomNpcsPermissions.Permission getPermission() {
        return CustomNpcsPermissions.SCRIPT_NPC;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.type.ordinal());
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
        if (!PacketUtil.verifyItemPacket(packetName, player, EnumItemPacketType.SCRIPTER)) {
            return;
        }
        Action requestedAction = Action.values()[in.readInt()];
        DataScript data = this.npc.script;
        if (requestedAction == Action.GET) {
            PacketUtil.getScripts(data, (EntityPlayerMP)player);
        } else {
            PacketUtil.saveScripts(data, in);
            this.npc.updateAI = true;
            this.npc.script.hasInited = false;
            if (ConfigDebug.PlayerLogging && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
                LogWriter.script(String.format("[%s] (Player) %s SAVED NPC %s (%s, %s, %s) [%s]", "SCRIPTER", player.func_70005_c_(), this.npc.display.getName(), (int)this.npc.field_70165_t, (int)this.npc.field_70163_u, (int)this.npc.field_70161_v, this.npc.field_70170_p.func_72912_H().func_76065_j()));
            }
        }
    }

    public static void Save(int id, int maxSize, NBTTagCompound compound) {
        PacketClient.sendClient(new EventScriptPacket(Action.SAVE, id, maxSize, compound));
    }

    public static void Get() {
        PacketClient.sendClient(new EventScriptPacket(Action.GET, -1, -1, new NBTTagCompound()));
    }

    private static enum Action {
        GET,
        SAVE;

    }
}

