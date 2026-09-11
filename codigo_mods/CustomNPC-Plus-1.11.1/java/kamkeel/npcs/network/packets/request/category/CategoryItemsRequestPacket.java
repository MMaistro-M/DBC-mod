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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.network.AbstractPacket;
import kamkeel.npcs.network.PacketChannel;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.PacketUtil;
import kamkeel.npcs.network.enums.EnumItemPacketType;
import kamkeel.npcs.network.enums.EnumRequestPacket;
import kamkeel.npcs.network.packets.data.large.ScrollDataPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.controllers.AnimationController;
import noppes.npcs.controllers.CustomEffectController;
import noppes.npcs.controllers.LinkedItemController;
import noppes.npcs.controllers.TagController;

public final class CategoryItemsRequestPacket
extends AbstractPacket {
    public static String packetName = "NPC|CatItems";
    private int catType;
    private int catId;

    public CategoryItemsRequestPacket(int catType, int catId) {
        this.catType = catType;
        this.catId = catId;
    }

    public CategoryItemsRequestPacket() {
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.CategoryItemsRequest;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.catType);
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
        int categoryId = in.readInt();
        Map<String, Integer> items = CategoryItemsRequestPacket.getItemsByCat(type, categoryId);
        ScrollDataPacket.sendScrollData((EntityPlayerMP)player, items, EnumScrollData.CATEGORY_GROUP);
        HashMap<String, HashSet<UUID>> tagMap = CategoryItemsRequestPacket.getTagMapByCat(type, categoryId);
        if (tagMap != null && !tagMap.isEmpty()) {
            TagController.sendCategoryTagMap((EntityPlayerMP)player, tagMap);
        }
    }

    private static Map<String, Integer> getItemsByCat(int type, int catId) {
        switch (type) {
            case 1: {
                return CustomEffectController.getInstance().getItemsByCategoryScrollData(catId);
            }
            case 2: {
                return AnimationController.getInstance().getItemsByCategoryScrollData(catId);
            }
            case 3: {
                return LinkedItemController.getInstance().getItemsByCategoryScrollData(catId);
            }
            case 4: {
                return AbilityController.Instance.getCustomAbilityItemsByCategoryScrollData(catId);
            }
            case 5: {
                return AbilityController.Instance.getChainedAbilityItemsByCategoryScrollData(catId);
            }
        }
        return new HashMap<String, Integer>();
    }

    private static HashMap<String, HashSet<UUID>> getTagMapByCat(int type, int catId) {
        switch (type) {
            case 1: {
                return CustomEffectController.getInstance().getItemTagMapForCategory(catId);
            }
            case 3: {
                return LinkedItemController.getInstance().getItemTagMapForCategory(catId);
            }
            case 4: {
                return AbilityController.Instance.getCustomAbilityTagMapForCategory(catId);
            }
        }
        return null;
    }
}

