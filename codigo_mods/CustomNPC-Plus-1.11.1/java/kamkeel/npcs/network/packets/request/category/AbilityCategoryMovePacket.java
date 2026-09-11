/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package kamkeel.npcs.network.packets.request.category;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.PacketUtil;
import kamkeel.npcs.network.enums.EnumItemPacketType;
import kamkeel.npcs.network.enums.EnumRequestPacket;
import kamkeel.npcs.network.packets.request.category.CategorySavePacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.controllers.CategoryManager;

public final class AbilityCategoryMovePacket
extends AbstractPacket {
    public static String packetName = "NPC|AbCatMove";
    private int catType;
    private String name;
    private int catId;

    public AbilityCategoryMovePacket(int catType, String name, int catId) {
        this.catType = catType;
        this.name = name;
        this.catId = catId;
    }

    public AbilityCategoryMovePacket() {
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.AbilityCategoryMoveItem;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.catType);
        ByteBufUtils.writeString(out, this.name);
        out.writeInt(this.catId);
    }

    @Override
    public void receiveData(ByteBuf in, EntityPlayer player) throws IOException {
        if (!(player instanceof EntityPlayerMP)) {
            return;
        }
        if (!PacketUtil.verifyItemPacket(packetName, EnumItemPacketType.WAND, player)) {
            return;
        }
        int type = in.readInt();
        String itemName = ByteBufUtils.readString(in);
        int cat = in.readInt();
        CategoryManager cm = CategorySavePacket.getManager(type);
        if (cm == null) {
            return;
        }
        if (cat != 0 && cm.getCategory(cat) == null) {
            return;
        }
        switch (type) {
            case 4: {
                AbilityController.Instance.moveCustomAbilityToCategory(itemName, cat);
                break;
            }
            case 5: {
                AbilityController.Instance.moveChainedAbilityToCategory(itemName, cat);
            }
        }
    }
}

