/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.network.packets.request.script;

import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumRequestPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.NBTTags;
import noppes.npcs.client.ScriptClientConfig;
import noppes.npcs.config.ConfigScript;
import noppes.npcs.controllers.ScriptController;

public final class ScriptFilesPacket
extends AbstractPacket {
    public static String packetName = "Request|ScriptFiles";
    private String lang;

    public ScriptFilesPacket() {
    }

    public ScriptFilesPacket(String lang) {
        this.lang = lang;
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.ScriptFiles;
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
    public void sendData(ByteBuf out) throws IOException {
        ByteBufUtils.writeNBT(out, this.getScriptsNbt(this.lang));
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        NBTTagCompound compound = ByteBufUtils.readNBT(in);
        String lang = compound.func_74779_i("Lang");
        String ext = compound.func_74779_i("Ext");
        HashMap<String, String> scripts = NBTTags.getStringStringMap(compound.func_150295_c("Scripts", 10));
        ScriptController cont = ScriptController.Instance;
        if (!cont.languages.containsKey(lang) && ext != null) {
            cont.languages.put(lang, ext);
        }
        for (Map.Entry script : scripts.entrySet()) {
            cont.scripts.put((String)script.getKey(), (String)script.getValue());
        }
        ScriptClientConfig.setScriptingEnabled(compound.func_74767_n("ScriptingEnabled"));
        ScriptClientConfig.setRunLoadedScriptsFirst(compound.func_74767_n("LoadedFirst"));
        ScriptController.Instance.globalRevision = compound.func_74762_e("GlobalRevision");
    }

    public NBTTagCompound getScriptsNbt(String lang) {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74778_a("Lang", lang);
        HashMap<String, String> scriptss = new HashMap<String, String>();
        String ext = ScriptController.Instance.languages.get(lang);
        if (ext != null) {
            for (Map.Entry<String, String> script : ScriptController.Instance.scripts.entrySet()) {
                if (!script.getKey().endsWith(ext)) continue;
                scriptss.put(script.getKey(), script.getValue());
            }
            compound.func_74778_a("Ext", ext);
        }
        compound.func_74757_a("ScriptingEnabled", ConfigScript.ScriptingEnabled);
        compound.func_74757_a("LoadedFirst", ConfigScript.RunLoadedScriptsFirst);
        compound.func_74768_a("GlobalRevision", ScriptController.Instance.globalRevision);
        compound.func_74782_a("Scripts", (NBTBase)NBTTags.nbtStringStringMap(scriptss));
        return compound;
    }

    public static void sendToAll(String lang) {
        PacketHandler.Instance.sendToAll(new ScriptFilesPacket(lang));
    }

    public static void sendToPlayer(EntityPlayerMP player, String lang) {
        PacketHandler.Instance.sendToPlayer(new ScriptFilesPacket(lang), player);
    }
}

