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
import kamkeel.npcs.network.packets.request.category.CategorySavePacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.controllers.AnimationController;
import noppes.npcs.controllers.CategoryManager;
import noppes.npcs.controllers.CustomEffectController;
import noppes.npcs.controllers.LinkedItemController;

public final class CategoryMoveItemPacket
extends AbstractPacket {
    public static String packetName = "NPC|CatMove";
    private int catType;
    private int itemId;
    private int catId;

    public CategoryMoveItemPacket(int catType, int itemId, int catId) {
        this.catType = catType;
        this.itemId = itemId;
        this.catId = catId;
    }

    public CategoryMoveItemPacket() {
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.CategoryMoveItem;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.catType);
        out.writeInt(this.itemId);
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
        int item = in.readInt();
        int cat = in.readInt();
        CategoryManager cm = CategorySavePacket.getManager(type);
        if (cm == null) {
            return;
        }
        if (cat != 0 && cm.getCategory(cat) == null) {
            return;
        }
        switch (type) {
            case 1: {
                CustomEffectController.getInstance().moveItemToCategory(item, cat);
                break;
            }
            case 2: {
                AnimationController.getInstance().moveItemToCategory(item, cat);
                break;
            }
            case 3: {
                LinkedItemController.getInstance().moveItemToCategory(item, cat);
            }
        }
    }
}

