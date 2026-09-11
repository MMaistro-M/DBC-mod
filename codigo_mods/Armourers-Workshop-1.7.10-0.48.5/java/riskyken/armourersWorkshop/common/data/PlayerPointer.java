/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 */
package riskyken.armourersWorkshop.common.data;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerPointer {
    private static final boolean USE_UUID_TO_SYNC = false;
    private UUID uuid = null;
    private String name = null;

    public PlayerPointer(EntityPlayer entityPlayer) {
        this.name = entityPlayer.func_146103_bH().getName();
    }

    public PlayerPointer(GameProfile gameProfile) {
        this.name = gameProfile.getName();
    }

    public PlayerPointer(ByteBuf buf) {
        this.readFromByteBuffer(buf);
    }

    private void readFromByteBuffer(ByteBuf buf) {
        this.name = ByteBufUtils.readUTF8String((ByteBuf)buf);
    }

    public void writeToByteBuffer(ByteBuf buf) {
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.name);
    }

    public String toString() {
        return "PlayerPointer [uuid=" + this.uuid + ", name=" + this.name + "]";
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.name == null ? 0 : this.name.hashCode());
        result = 31 * result + (this.uuid == null ? 0 : this.uuid.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        PlayerPointer other = (PlayerPointer)obj;
        return !(this.name == null ? other.name != null : !this.name.equals(other.name));
    }
}

