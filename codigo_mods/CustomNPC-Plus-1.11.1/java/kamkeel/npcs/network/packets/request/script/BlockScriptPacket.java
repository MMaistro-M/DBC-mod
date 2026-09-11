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
 *  net.minecraft.tileentity.TileEntity
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
import kamkeel.npcs.network.enums.EnumRequestPacket;
import kamkeel.npcs.network.packets.data.large.GuiDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.blocks.tiles.TileScripted;
import noppes.npcs.config.ConfigScript;
import noppes.npcs.controllers.ScriptController;

public final class BlockScriptPacket
extends AbstractPacket {
    public static String packetName = "Request|BlockScript";
    private Action type;
    private int x;
    private int y;
    private int z;
    private NBTTagCompound compound;

    public BlockScriptPacket() {
    }

    public BlockScriptPacket(Action type, int x, int y, int z, NBTTagCompound compound) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.z = z;
        this.compound = compound;
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.BlockScript;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    public CustomNpcsPermissions.Permission getPermission() {
        return CustomNpcsPermissions.SCRIPT_BLOCK;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.type.ordinal());
        out.writeInt(this.x);
        out.writeInt(this.y);
        out.writeInt(this.z);
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
            TileEntity tile = player.field_70170_p.func_147438_o(in.readInt(), in.readInt(), in.readInt());
            if (!(tile instanceof TileScripted)) {
                return;
            }
            NBTTagCompound compound = ((TileScripted)tile).getNBT(new NBTTagCompound());
            compound.func_74782_a("Languages", (NBTBase)ScriptController.Instance.nbtLanguages());
            GuiDataPacket.sendGuiData((EntityPlayerMP)player, compound);
            NBTTagCompound loadComplete = new NBTTagCompound();
            loadComplete.func_74768_a("LoadComplete", 1);
            GuiDataPacket.sendGuiData((EntityPlayerMP)player, loadComplete);
        } else {
            if (!player.field_71075_bZ.field_75098_d) {
                return;
            }
            TileEntity tile = player.field_70170_p.func_147438_o(in.readInt(), in.readInt(), in.readInt());
            if (!(tile instanceof TileScripted)) {
                return;
            }
            TileScripted script = (TileScripted)tile;
            script.setNBT(ByteBufUtils.readNBT(in));
            script.lastInited = -1L;
        }
    }

    public static void Save(int x, int y, int z, NBTTagCompound compound) {
        PacketClient.sendClient(new BlockScriptPacket(Action.SAVE, x, y, z, compound));
    }

    public static void Get(int x, int y, int z) {
        PacketClient.sendClient(new BlockScriptPacket(Action.GET, x, y, z, new NBTTagCompound()));
    }

    private static enum Action {
        GET,
        SAVE;

    }
}

