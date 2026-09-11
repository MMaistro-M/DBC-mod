/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StringUtils
 */
package riskyken.armourersWorkshop.common.inventory;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringUtils;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.client.gui.skinlibrary.GuiSkinLibrary;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.common.inventory.slot.ISlotChanged;
import riskyken.armourersWorkshop.common.inventory.slot.SlotOutput;
import riskyken.armourersWorkshop.common.inventory.slot.SlotSkinTemplate;
import riskyken.armourersWorkshop.common.items.ItemSkin;
import riskyken.armourersWorkshop.common.items.ItemSkinTemplate;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinLibrary;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class ContainerArmourLibrary
extends Container
implements ISlotChanged {
    private TileEntitySkinLibrary tileEntity;

    public ContainerArmourLibrary(InventoryPlayer invPlayer, TileEntitySkinLibrary tileEntity) {
        this.tileEntity = tileEntity;
        for (int x = 0; x < 9; ++x) {
            this.func_75146_a(new Slot((IInventory)invPlayer, x, 6 + 18 * x, 232));
        }
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 9; ++x) {
                this.func_75146_a(new Slot((IInventory)invPlayer, x + y * 9 + 9, 6 + 18 * x, 174 + y * 18));
            }
        }
        this.func_75146_a(new SlotSkinTemplate(tileEntity, 0, 226, 101, this));
        this.func_75146_a(new SlotOutput(tileEntity, 1, 226, 137));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public ItemStack func_82846_b(EntityPlayer player, int slotID) {
        Slot slot = this.func_75139_a(slotID);
        if (slot == null || !slot.func_75216_d()) return null;
        ItemStack stack = slot.func_75211_c();
        ItemStack result = stack.func_77946_l();
        if (slotID < 36) {
            if (!(stack.func_77973_b() instanceof ItemSkinTemplate & stack.func_77960_j() == 0 | stack.func_77973_b() instanceof ItemSkin)) return null;
            if (!this.func_75135_a(stack, 36, 37, false)) {
                return null;
            }
        } else if (!this.func_75135_a(stack, 9, 36, false) && !this.func_75135_a(stack, 0, 9, false)) {
            return null;
        }
        if (stack.field_77994_a == 0) {
            slot.func_75215_d(null);
        } else {
            slot.func_75218_e();
        }
        slot.func_82870_a(player, stack);
        return result;
    }

    @Override
    public void onSlotChanged(int slotId) {
        if (!ArmourersWorkshop.isDedicated() && FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            this.updateSkinName(slotId);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void updateSkinName(int slotId) {
        Minecraft mc = Minecraft.func_71410_x();
        GuiScreen screen = mc.field_71462_r;
        if (screen != null && screen instanceof GuiSkinLibrary) {
            GuiSkinLibrary libScreen = (GuiSkinLibrary)screen;
            ItemStack stack = this.func_75139_a(36).func_75211_c();
            if (stack == null) {
                libScreen.setFileName("");
            } else {
                Skin skin;
                String skinName;
                SkinPointer skinPointer = SkinNBTHelper.getSkinPointerFromStack(stack);
                if (skinPointer != null && ClientSkinCache.INSTANCE.isSkinInCache(skinPointer) && !StringUtils.func_151246_b((String)(skinName = (skin = ClientSkinCache.INSTANCE.getSkin(skinPointer)).getCustomName()))) {
                    libScreen.setFileName(skinName);
                }
            }
        }
    }

    public boolean func_75145_c(EntityPlayer player) {
        return this.tileEntity.func_70300_a(player);
    }

    public TileEntitySkinLibrary getTileEntity() {
        return this.tileEntity;
    }

    public void func_75142_b() {
        super.func_75142_b();
        for (Object player : this.field_75149_d) {
            if (!(player instanceof EntityPlayerMP)) continue;
            EntityPlayerMP playerMp = (EntityPlayerMP)player;
            ArmourersWorkshop.proxy.libraryManager.syncLibraryWithPlayer(playerMp);
        }
    }
}

