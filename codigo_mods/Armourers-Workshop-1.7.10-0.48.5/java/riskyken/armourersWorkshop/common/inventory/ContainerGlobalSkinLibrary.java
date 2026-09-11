/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package riskyken.armourersWorkshop.common.inventory;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.common.inventory.ModInventory;
import riskyken.armourersWorkshop.common.inventory.slot.SlotHidable;
import riskyken.armourersWorkshop.common.inventory.slot.SlotOutput;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiButton;
import riskyken.armourersWorkshop.common.network.messages.server.MessageServerLibrarySendSkin;
import riskyken.armourersWorkshop.common.skin.cache.CommonSkinCache;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.tileentities.TileEntityGlobalSkinLibrary;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class ContainerGlobalSkinLibrary
extends Container
implements MessageClientGuiButton.IButtonPress {
    private TileEntityGlobalSkinLibrary tileEntity;
    private EntityPlayer player;
    private IInventory inventory;

    public ContainerGlobalSkinLibrary(InventoryPlayer invPlayer, TileEntityGlobalSkinLibrary tileEntity) {
        this.tileEntity = tileEntity;
        this.player = invPlayer.field_70458_d;
        this.inventory = new ModInventory("fakeInventory", 2);
        int playerInvY = 20;
        int hotBarY = playerInvY + 58;
        for (int x = 0; x < 9; ++x) {
            this.func_75146_a(new SlotHidable((IInventory)invPlayer, x, 5 + 18 * x, hotBarY));
        }
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.func_75146_a(new SlotHidable((IInventory)invPlayer, x + y * 9 + 9, 5 + 18 * x, playerInvY + y * 18));
            }
        }
        this.func_75146_a(new SlotHidable(this.inventory, 0, 5, 5));
        this.func_75146_a(new SlotOutput(this.inventory, 1, 5, 5));
    }

    public TileEntityGlobalSkinLibrary getTileEntity() {
        return this.tileEntity;
    }

    public void func_75134_a(EntityPlayer entityPlayer) {
        super.func_75134_a(entityPlayer);
        if (!this.tileEntity.func_145831_w().field_72995_K) {
            Slot slot = this.func_75139_a(36);
            if (slot.func_75216_d()) {
                entityPlayer.func_71019_a(slot.func_75211_c(), false);
            }
            if ((slot = this.func_75139_a(37)).func_75216_d()) {
                entityPlayer.func_71019_a(slot.func_75211_c(), false);
            }
        }
    }

    public void onSkinUploaded() {
        if (!this.tileEntity.func_145831_w().field_72995_K) {
            ItemStack stack = this.func_75139_a(36).func_75211_c();
            this.func_75139_a(36).func_75215_d(null);
            this.func_75139_a(37).func_75215_d(stack);
        }
    }

    public boolean func_75145_c(EntityPlayer entityPlayer) {
        return entityPlayer.func_70092_e((double)this.tileEntity.field_145851_c + 0.5, (double)this.tileEntity.field_145848_d + 0.5, (double)this.tileEntity.field_145849_e + 0.5) <= 64.0 & !entityPlayer.field_70128_L;
    }

    public ItemStack func_82846_b(EntityPlayer entityPlayer, int slotId) {
        Slot slot = this.func_75139_a(slotId);
        if (slot != null && slot.func_75216_d()) {
            ItemStack stack = slot.func_75211_c();
            ItemStack result = stack.func_77946_l();
            if (slotId > 35 ? !this.func_75135_a(stack, 9, 36, false) && !this.func_75135_a(stack, 0, 9, false) : !this.func_75135_a(stack, 36, 37, false)) {
                return null;
            }
            if (stack.field_77994_a == 0) {
                slot.func_75215_d(null);
            } else {
                slot.func_75218_e();
            }
            slot.func_82870_a(entityPlayer, stack);
            return result;
        }
        return null;
    }

    @Override
    public void buttonPressed(byte buttonId) {
        Skin skin;
        ItemStack itemStack;
        SkinPointer skinPointer;
        if (buttonId == 0 && !this.tileEntity.func_145831_w().field_72995_K && !this.func_75139_a(37).func_75216_d() && (skinPointer = SkinNBTHelper.getSkinPointerFromStack(itemStack = this.func_75139_a(36).func_75211_c())) != null && (skin = CommonSkinCache.INSTANCE.getSkin(skinPointer)) != null) {
            this.onSkinUploaded();
            MessageServerLibrarySendSkin message = new MessageServerLibrarySendSkin(null, null, skin, MessageServerLibrarySendSkin.SendType.GLOBAL_UPLOAD);
            PacketHandler.networkWrapper.sendTo((IMessage)message, (EntityPlayerMP)this.player);
        }
    }
}

