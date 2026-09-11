/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.network.packets.request.script;

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
import kamkeel.npcs.network.packets.data.large.GuiDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.config.ConfigScript;

public final class ScriptInfoPacket
extends AbstractPacket {
    public static String packetName = "Request|NPCScript";
    private Action type;
    private NBTTagCompound compound;

    public ScriptInfoPacket() {
    }

    public ScriptInfoPacket(Action type, NBTTagCompound compound) {
        this.type = type;
        this.compound = compound;
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.ScriptInfo;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    public CustomNpcsPermissions.Permission getPermission() {
        return CustomNpcsPermissions.SCRIPT_GLOBAL;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.type.ordinal());
        if (this.type == Action.SAVE) {
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
        if (requestedAction == Action.GET) {
            NBTTagCompound compound = new NBTTagCompound();
            compound.func_74757_a("ScriptsEnabled", ConfigScript.ScriptingEnabled);
            compound.func_74757_a("PlayerScriptsEnabled", ConfigScript.GlobalPlayerScripts);
            compound.func_74757_a("GlobalNPCScriptsEnabled", ConfigScript.GlobalNPCScripts);
            compound.func_74757_a("ForgeScriptsEnabled", ConfigScript.GlobalForgeScripts);
            GuiDataPacket.sendGuiData((EntityPlayerMP)player, compound);
        } else {
            NBTTagCompound compound = ByteBufUtils.readNBT(in);
            ConfigScript.ScriptingEnabled = compound.func_74767_n("ScriptsEnabled");
            ConfigScript.GlobalPlayerScripts = compound.func_74767_n("PlayerScriptsEnabled");
            ConfigScript.GlobalNPCScripts = compound.func_74767_n("GlobalNPCScriptsEnabled");
            ConfigScript.GlobalForgeScripts = compound.func_74767_n("ForgeScriptsEnabled");
        }
    }

    public static void Save(NBTTagCompound compound) {
        PacketClient.sendClient(new ScriptInfoPacket(Action.SAVE, compound));
    }

    public static void Get() {
        PacketClient.sendClient(new ScriptInfoPacket(Action.GET, new NBTTagCompound()));
    }

    private static enum Action {
        GET,
        SAVE;

    }
}

