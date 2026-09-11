/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.network.packets.request.script.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumRequestPacket;
import kamkeel.npcs.network.packets.data.large.GuiDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.config.ConfigScript;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.item.ScriptCustomItem;

public final class ItemScriptPacket
extends AbstractPacket {
    public static String packetName = "Request|ItemScript";
    private Action type;
    private NBTTagCompound compound;

    public ItemScriptPacket() {
    }

    public ItemScriptPacket(Action type, NBTTagCompound compound) {
        this.type = type;
        this.compound = compound;
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.ItemScript;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    public CustomNpcsPermissions.Permission getPermission() {
        return CustomNpcsPermissions.SCRIPT_ITEM;
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
        Action requestedAction = Action.values()[in.readInt()];
        if (requestedAction == Action.GET) {
            ScriptCustomItem iw = (ScriptCustomItem)NpcAPI.Instance().getIItemStack(player.func_70694_bm());
            iw.loadScriptData();
            NBTTagCompound compound = iw.getMCNbt();
            compound.func_74782_a("Languages", (NBTBase)ScriptController.Instance.nbtLanguages());
            GuiDataPacket.sendGuiData((EntityPlayerMP)player, compound);
            NBTTagCompound loadComplete = new NBTTagCompound();
            loadComplete.func_74768_a("LoadComplete", 1);
            GuiDataPacket.sendGuiData((EntityPlayerMP)player, loadComplete);
        } else {
            if (!player.field_71075_bZ.field_75098_d) {
                return;
            }
            NBTTagCompound compound = ByteBufUtils.readNBT(in);
            ScriptCustomItem wrapper = (ScriptCustomItem)NpcAPI.Instance().getIItemStack(player.func_70694_bm());
            wrapper.setMCNbt(compound);
            wrapper.saveScriptData();
            wrapper.loaded = false;
            wrapper.errored.clear();
            wrapper.lastInited = -1L;
            ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
        }
    }

    public static void Save(NBTTagCompound compound) {
        PacketClient.sendClient(new ItemScriptPacket(Action.SAVE, compound));
    }

    public static void Get() {
        PacketClient.sendClient(new ItemScriptPacket(Action.GET, new NBTTagCompound()));
    }

    private static enum Action {
        GET,
        SAVE;

    }
}

