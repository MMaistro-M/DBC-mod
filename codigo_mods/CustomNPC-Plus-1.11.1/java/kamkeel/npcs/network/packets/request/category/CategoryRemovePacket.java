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
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.PacketUtil;
import kamkeel.npcs.network.enums.EnumItemPacketType;
import kamkeel.npcs.network.enums.EnumRequestPacket;
import kamkeel.npcs.network.packets.data.large.ScrollDataPacket;
import kamkeel.npcs.network.packets.request.category.CategorySavePacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.controllers.CategoryManager;

public final class CategoryRemovePacket
extends AbstractPacket {
    public static String packetName = "NPC|CatRemove";
    private int catType;
    private int categoryId;

    public CategoryRemovePacket(int catType, int categoryId) {
        this.catType = catType;
        this.categoryId = categoryId;
    }

    public CategoryRemovePacket() {
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.CategoryRemove;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.catType);
        out.writeInt(this.categoryId);
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
        int catId = in.readInt();
        CategoryManager cm = CategorySavePacket.getManager(type);
        if (cm == null) {
            return;
        }
        cm.removeCategory(catId);
        CategorySavePacket.saveController(type);
        ScrollDataPacket.sendScrollData((EntityPlayerMP)player, cm.getCategoryScrollData(), EnumScrollData.CATEGORY_LIST);
    }
}

