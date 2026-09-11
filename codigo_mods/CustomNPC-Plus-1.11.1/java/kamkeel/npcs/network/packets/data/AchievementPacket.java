/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.ObfuscationReflectionHelper
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.achievement.GuiAchievement
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Items
 *  net.minecraft.stats.Achievement
 *  net.minecraft.util.StatCollector
 */
package kamkeel.npcs.network.packets.data;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.achievement.GuiAchievement;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.stats.Achievement;
import net.minecraft.util.StatCollector;
import noppes.npcs.CustomItems;
import noppes.npcs.client.MessageAchievement;
import noppes.npcs.config.ConfigClient;

public final class AchievementPacket
extends AbstractPacket {
    public static final String packetName = "Data|Achievement";
    private boolean isParty;
    private String description;
    private String message;

    public AchievementPacket() {
    }

    public AchievementPacket(boolean isParty, String description, String message) {
        this.isParty = isParty;
        this.description = description;
        this.message = message;
    }

    public static void sendAchievement(EntityPlayerMP playerMP, boolean isParty, String description, String message) {
        AchievementPacket packet = new AchievementPacket(isParty, description, message);
        PacketHandler.Instance.sendToPlayer(packet, playerMP);
    }

    @Override
    public Enum getType() {
        return EnumDataPacket.ACHIEVEMENT;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.DATA_PACKET;
    }

    @Override
    public void sendData(ByteBuf out) throws IOException {
        out.writeBoolean(this.isParty);
        ByteBufUtils.writeString(out, this.description);
        ByteBufUtils.writeString(out, this.message);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!ConfigClient.BannerAlerts) {
            return;
        }
        boolean isNotParty = !in.readBoolean();
        String description = StatCollector.func_74838_a((String)ByteBufUtils.readString(in));
        String message = ByteBufUtils.readString(in);
        MessageAchievement ach = isNotParty ? new MessageAchievement(message, description) : new MessageAchievement(CustomItems.bag == null ? Items.field_151121_aF : CustomItems.bag, message, description);
        Minecraft.func_71410_x().field_71458_u.func_146256_a((Achievement)ach);
        ObfuscationReflectionHelper.setPrivateValue(GuiAchievement.class, (Object)Minecraft.func_71410_x().field_71458_u, (Object)ach.func_75989_e(), (int)4);
    }
}

