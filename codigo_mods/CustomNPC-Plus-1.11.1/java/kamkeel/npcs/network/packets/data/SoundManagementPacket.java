/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 */
package kamkeel.npcs.network.packets.data;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumDataPacket;
import kamkeel.npcs.network.enums.EnumSoundOperation;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.controllers.MusicController;
import noppes.npcs.client.controllers.ScriptClientSound;
import noppes.npcs.client.controllers.ScriptSoundController;

public final class SoundManagementPacket
extends AbstractPacket {
    private EnumSoundOperation operation;
    private String soundName;
    private float x;
    private float y;
    private float z;
    private NBTTagCompound nbt;
    private int soundId;

    public SoundManagementPacket() {
    }

    public SoundManagementPacket(EnumSoundOperation operation) {
        this.operation = operation;
    }

    public SoundManagementPacket(EnumSoundOperation operation, String soundName) {
        this.operation = operation;
        this.soundName = soundName;
    }

    public SoundManagementPacket(EnumSoundOperation operation, String soundName, float x, float y, float z) {
        this.operation = operation;
        this.soundName = soundName;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public SoundManagementPacket(EnumSoundOperation operation, int soundId, NBTTagCompound nbt) {
        this.operation = operation;
        this.soundId = soundId;
        this.nbt = nbt;
    }

    public SoundManagementPacket(EnumSoundOperation operation, NBTTagCompound nbt) {
        this.operation = operation;
        this.nbt = nbt;
    }

    public SoundManagementPacket(EnumSoundOperation operation, int soundId) {
        this.operation = operation;
        this.soundId = soundId;
    }

    @Override
    public Enum getType() {
        return EnumDataPacket.SOUND;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.DATA_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.operation.ordinal());
        switch (this.operation) {
            case PLAY_MUSIC: 
            case PLAY_SOUND: {
                ByteBufUtils.writeString(out, this.soundName);
                if (this.operation != EnumSoundOperation.PLAY_SOUND) break;
                out.writeFloat(this.x);
                out.writeFloat(this.y);
                out.writeFloat(this.z);
                break;
            }
            case PLAY_SOUND_TO: {
                out.writeInt(this.soundId);
                ByteBufUtils.writeNBT(out, this.nbt);
                break;
            }
            case PLAY_SOUND_TO_NO_ID: {
                ByteBufUtils.writeNBT(out, this.nbt);
                break;
            }
            case STOP_SOUND_FOR: {
                out.writeInt(this.soundId);
                break;
            }
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        EnumSoundOperation operation = EnumSoundOperation.values()[in.readInt()];
        switch (operation) {
            case CONTINUE_SOUNDS: {
                ScriptSoundController.Instance.continueAllSounds();
                break;
            }
            case PAUSE_SOUNDS: {
                ScriptSoundController.Instance.pauseAllSounds();
                break;
            }
            case PLAY_MUSIC: {
                String soundName = ByteBufUtils.readString(in);
                MusicController.Instance.playMusicBackground(soundName, (Entity)player, Integer.MAX_VALUE);
                break;
            }
            case PLAY_SOUND: {
                String name = ByteBufUtils.readString(in);
                float x = in.readFloat();
                float y = in.readFloat();
                float z = in.readFloat();
                MusicController.Instance.playSound(name, x, y, z);
                break;
            }
            case PLAY_SOUND_TO: {
                int id = in.readInt();
                NBTTagCompound comp = ByteBufUtils.readNBT(in);
                ScriptClientSound sound = ScriptClientSound.fromScriptSound(comp, player.field_70170_p);
                ScriptSoundController.Instance.playSound(id, sound);
                break;
            }
            case PLAY_SOUND_TO_NO_ID: {
                NBTTagCompound tagCompound = ByteBufUtils.readNBT(in);
                ScriptClientSound soundNoId = ScriptClientSound.fromScriptSound(tagCompound, player.field_70170_p);
                ScriptSoundController.Instance.playSound(soundNoId);
                break;
            }
            case STOP_SOUND_FOR: {
                int stopID = in.readInt();
                ScriptSoundController.Instance.stopSound(stopID);
                break;
            }
            case STOP_SOUNDS: {
                ScriptSoundController.Instance.stopAllSounds();
                break;
            }
        }
    }
}

