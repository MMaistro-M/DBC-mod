/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ICrafting
 *  net.minecraft.item.ItemStack
 */
package riskyken.armourersWorkshop.common.inventory;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.data.MiniCube;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.server.MessageServerMiniArmourerCubeEdit;
import riskyken.armourersWorkshop.common.network.messages.server.MessageServerMiniArmourerSkinData;
import riskyken.armourersWorkshop.common.skin.data.SkinPart;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMiniArmourer;

public class ContainerMiniArmourerBuilding
extends Container {
    private TileEntityMiniArmourer tileEntity;
    private ArrayList<SkinPart> skinParts;
    ISkinType skinType;

    public ContainerMiniArmourerBuilding(TileEntityMiniArmourer tileEntity) {
        this.tileEntity = tileEntity;
        this.skinType = tileEntity.getSkinType();
    }

    public ItemStack func_82846_b(EntityPlayer entityPlayer, int slotID) {
        return null;
    }

    public void func_75132_a(ICrafting player) {
        super.func_75132_a(player);
        MessageServerMiniArmourerSkinData message = new MessageServerMiniArmourerSkinData(this.tileEntity.getSkinParts());
        if (player instanceof EntityPlayerMP) {
            PacketHandler.networkWrapper.sendTo((IMessage)message, (EntityPlayerMP)player);
        }
    }

    public void func_75142_b() {
        super.func_75142_b();
        if (this.skinType == this.tileEntity.getSkinType() & !this.tileEntity.func_145831_w().field_72995_K) {
            return;
        }
        MessageServerMiniArmourerSkinData message = new MessageServerMiniArmourerSkinData(this.tileEntity.getSkinParts());
        for (int i = 0; i < this.field_75149_d.size(); ++i) {
            ICrafting crafter = (ICrafting)this.field_75149_d.get(i);
            PacketHandler.networkWrapper.sendTo((IMessage)message, (EntityPlayerMP)crafter);
        }
        this.skinType = this.tileEntity.getSkinType();
    }

    public boolean func_75145_c(EntityPlayer entityPlayer) {
        return this.tileEntity.func_70300_a(entityPlayer);
    }

    public TileEntityMiniArmourer getTileEntity() {
        return this.tileEntity;
    }

    public void setSkinParts(ArrayList<SkinPart> skinParts) {
        this.tileEntity.setSkinParts(skinParts);
    }

    public ArrayList<SkinPart> getSkinParts() {
        return this.tileEntity.getSkinParts();
    }

    public void updateFromClientCubeEdit(ISkinPartType skinPartType, MiniCube cube, boolean remove) {
        ArrayList<SkinPart> skinParts = this.tileEntity.getSkinParts();
        for (int i = 0; i < this.field_75149_d.size(); ++i) {
            ICrafting crafter = (ICrafting)this.field_75149_d.get(i);
            MessageServerMiniArmourerCubeEdit message = new MessageServerMiniArmourerCubeEdit(skinPartType, cube, remove);
            PacketHandler.networkWrapper.sendTo((IMessage)message, (EntityPlayerMP)crafter);
        }
        this.tileEntity.func_70296_d();
    }
}

