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
import kamkeel.npcs.network.packets.data.large.ScrollDataPacket;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.constants.EnumScrollData;
import noppes.npcs.controllers.AnimationController;
import noppes.npcs.controllers.CategoryManager;
import noppes.npcs.controllers.CustomEffectController;
import noppes.npcs.controllers.LinkedItemController;
import noppes.npcs.controllers.data.Category;

public final class CategorySavePacket
extends AbstractPacket {
    public static String packetName = "NPC|CatSave";
    private int catType;
    private NBTTagCompound categoryNBT;

    public CategorySavePacket(int catType, NBTTagCompound categoryNBT) {
        this.catType = catType;
        this.categoryNBT = categoryNBT;
    }

    public CategorySavePacket() {
    }

    @Override
    public Enum getType() {
        return EnumRequestPacket.CategorySave;
    }

    @Override
    public PacketChannel getChannel() {
        return PacketHandler.REQUEST_PACKET;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void sendData(ByteBuf out) throws IOException {
        out.writeInt(this.catType);
        ByteBufUtils.writeNBT(out, this.categoryNBT);
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
        NBTTagCompound compound = ByteBufUtils.readNBT(in);
        CategoryManager cm = CategorySavePacket.getManager(type);
        if (cm == null) {
            return;
        }
        Category cat = new Category();
        cat.readNBT(compound);
        if (cat.id <= 0) {
            cm.createCategory(cat.title);
        } else {
            cm.saveCategory(cat);
        }
        CategorySavePacket.saveController(type);
        ScrollDataPacket.sendScrollData((EntityPlayerMP)player, cm.getCategoryScrollData(), EnumScrollData.CATEGORY_LIST);
    }

    public static CategoryManager getManager(int type) {
        switch (type) {
            case 1: {
                return CustomEffectController.getInstance().categoryManager;
            }
            case 2: {
                return AnimationController.getInstance().categoryManager;
            }
            case 3: {
                return LinkedItemController.getInstance().categoryManager;
            }
            case 4: {
                return AbilityController.Instance.customAbilityCategories;
            }
            case 5: {
                return AbilityController.Instance.chainedAbilityCategories;
            }
        }
        return null;
    }

    public static void saveController(int type) {
        switch (type) {
            case 1: {
                CustomEffectController.getInstance().saveEffectLoadMap();
                break;
            }
            case 2: {
                AnimationController.getInstance().saveAnimationMap();
                break;
            }
            case 3: {
                LinkedItemController.getInstance().saveLinkedItemsMap();
                break;
            }
            case 4: {
                break;
            }
        }
    }
}

