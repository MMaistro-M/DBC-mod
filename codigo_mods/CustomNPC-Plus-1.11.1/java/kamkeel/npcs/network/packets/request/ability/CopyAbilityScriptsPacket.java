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
package kamkeel.npcs.network.packets.request.ability;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.data.ChainedAbility;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.PacketUtil;
import kamkeel.npcs.network.enums.EnumItemPacketType;
import kamkeel.npcs.network.enums.EnumRequestPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.controllers.data.AbilityScript;
import noppes.npcs.controllers.data.ChainedAbilityScript;

public final class CopyAbilityScriptsPacket
extends AbstractPacket {
    public static String packetName = "Request|CopyAbilityScripts";
    public static final int MODE_ABILITY = 0;
    public static final int MODE_CHAINED = 1;
    private int mode;
    private String sourceName;
    private String targetId;

    public CopyAbilityScriptsPacket() {
    }

    public CopyAbilityScriptsPacket(String sourceName, String targetId) {
        this(0, sourceName, targetId);
    }

    public CopyAbilityScriptsPacket(int mode, String sourceName, String targetId) {
        this.mode = mode;
        this.sourceName = sourceName;
        this.targetId = targetId;
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.CopyAbilityScripts;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    public CustomNpcsPermissions.Permission getPermission() {
        return CustomNpcsPermissions.GLOBAL_ABILITY;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeByte(this.mode);
        ByteBufUtils.writeString(out, this.sourceName);
        ByteBufUtils.writeString(out, this.targetId);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!PacketUtil.verifyItemPacket(packetName, EnumItemPacketType.WAND, player)) {
            return;
        }
        byte mode = in.readByte();
        String sourceName = ByteBufUtils.readString(in);
        String targetId = ByteBufUtils.readString(in);
        if (targetId == null || targetId.isEmpty()) {
            return;
        }
        if (mode == 1) {
            this.copyChainedAbilityScripts(sourceName, targetId);
        } else {
            this.copyAbilityScripts(sourceName, targetId);
        }
    }

    private void copyAbilityScripts(String sourceKey, String targetId) {
        Ability source = AbilityController.Instance.getCustomAbility(sourceKey);
        AbilityScript sourceHandler = source != null ? source.getScriptHandler() : AbilityController.Instance.abilityScriptHandlers.get(sourceKey);
        if (sourceHandler == null) {
            return;
        }
        NBTTagCompound scriptNbt = sourceHandler.writeToNBT(new NBTTagCompound());
        AbilityScript targetHandler = new AbilityScript(targetId);
        targetHandler.readFromNBT(scriptNbt);
        AbilityController.Instance.abilityScriptHandlers.put(targetId, targetHandler);
    }

    private void copyChainedAbilityScripts(String sourceName, String targetId) {
        ChainedAbility source = AbilityController.Instance.getChainedAbility(sourceName);
        if (source == null) {
            return;
        }
        ChainedAbilityScript sourceHandler = source.getScriptHandler();
        if (sourceHandler == null) {
            return;
        }
        NBTTagCompound scriptNbt = sourceHandler.writeToNBT(new NBTTagCompound());
        ChainedAbilityScript targetHandler = new ChainedAbilityScript(targetId);
        targetHandler.readFromNBT(scriptNbt);
        AbilityController.Instance.chainedAbilityScriptHandlers.put(targetId, targetHandler);
    }
}

