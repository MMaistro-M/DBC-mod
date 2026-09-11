/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package riskyken.armourersWorkshop.common.inventory;

import java.awt.Point;
import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinProperty;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartTypeTextured;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.inventory.ModTileContainer;
import riskyken.armourersWorkshop.common.inventory.slot.SlotOutput;
import riskyken.armourersWorkshop.common.inventory.slot.SlotSkin;
import riskyken.armourersWorkshop.common.items.ItemSkin;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiButton;
import riskyken.armourersWorkshop.common.painting.PaintType;
import riskyken.armourersWorkshop.common.painting.PaintingHelper;
import riskyken.armourersWorkshop.common.skin.cache.CommonSkinCache;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPart;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.skin.data.SkinProperty;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.tileentities.TileEntityOutfitMaker;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class ContainerOutfitMaker
extends ModTileContainer<TileEntityOutfitMaker>
implements MessageClientGuiButton.IButtonPress {
    private int indexSkinsStart = 0;
    private int indexSkinsEnd = 0;

    public ContainerOutfitMaker(EntityPlayer player, TileEntityOutfitMaker tileEntity) {
        super(player, tileEntity);
        ISkinType[] skinTypes = new ISkinType[]{SkinTypeRegistry.skinHead, SkinTypeRegistry.skinChest, SkinTypeRegistry.skinLegs, SkinTypeRegistry.skinFeet, SkinTypeRegistry.skinWings};
        this.addPlayerSlots(8, 158);
        this.func_75146_a(new SlotOutput(tileEntity, 1, 148, 88));
        this.indexSkinsEnd = this.indexSkinsStart = this.getPlayerInvEndIndex() + 1;
        for (int skinIndex = 0; skinIndex < skinTypes.length; ++skinIndex) {
            for (int i = 0; i < 4; ++i) {
                this.func_75146_a(new SlotSkin(skinTypes[skinIndex], tileEntity, skinIndex + i * 5 + 2, 36 + skinIndex * 20, 58 + i * 20));
                ++this.indexSkinsEnd;
            }
        }
    }

    private void loadOutfit() {
    }

    private void saveOutfit(EntityPlayer player) {
        ArrayList<SkinPart> skinParts = new ArrayList<SkinPart>();
        SkinProperties skinProperties = new SkinProperties();
        String partIndexs = "";
        int[] paintData = null;
        int skinIndex = 0;
        for (int i = 2; i < ((TileEntityOutfitMaker)this.tileEntity).func_70302_i_(); ++i) {
            Object part;
            int partIndex;
            Skin skin;
            ItemStack stack = ((TileEntityOutfitMaker)this.tileEntity).func_70301_a(i);
            SkinPointer descriptor = SkinNBTHelper.getSkinPointerFromStack(stack);
            if (descriptor == null || (skin = CommonSkinCache.INSTANCE.getSkin(descriptor)) == null) continue;
            for (partIndex = 0; partIndex < skin.getPartCount(); ++partIndex) {
                part = skin.getParts().get(partIndex);
                skinParts.add((SkinPart)part);
            }
            if (skin.hasPaintData()) {
                if (paintData == null) {
                    paintData = new int[2048];
                }
                for (partIndex = 0; partIndex < skin.getSkinType().getSkinParts().size(); ++partIndex) {
                    part = skin.getSkinType().getSkinParts().get(partIndex);
                    if (!(part instanceof ISkinPartTypeTextured)) continue;
                    ISkinPartTypeTextured texType = (ISkinPartTypeTextured)part;
                    paintData = this.paintPart(texType, paintData, skin.getPaintData());
                }
            }
            partIndexs = partIndexs.isEmpty() ? String.valueOf(skinParts.size()) : partIndexs + ":" + String.valueOf(skinParts.size());
            for (ISkinProperty<?> prop : skin.getSkinType().getProperties()) {
                SkinProperty p = (SkinProperty)prop;
                if (p.getKey().startsWith("wings")) {
                    p.setValue(skinProperties, p.getValue(skin.getProperties()), skinIndex);
                    continue;
                }
                p.setValue(skinProperties, p.getValue(skin.getProperties()));
            }
            ++skinIndex;
        }
        if (!skinParts.isEmpty()) {
            SkinProperties.PROP_OUTFIT_PART_INDEXS.setValue(skinProperties, partIndexs);
            SkinProperties.PROP_ALL_AUTHOR_NAME.setValue(skinProperties, player.getDisplayName());
            if (player.func_146103_bH() != null && player.func_146103_bH().getId() != null) {
                SkinProperties.PROP_ALL_AUTHOR_UUID.setValue(skinProperties, player.func_146103_bH().getId().toString());
            }
            SkinProperties.PROP_ALL_CUSTOM_NAME.setValue(skinProperties, ((TileEntityOutfitMaker)this.tileEntity).getOutfitName());
            SkinProperties.PROP_ALL_FLAVOUR_TEXT.setValue(skinProperties, ((TileEntityOutfitMaker)this.tileEntity).getOutfitFlavour());
            Skin skin = new Skin(skinProperties, SkinTypeRegistry.skinOutfit, paintData, skinParts);
            CommonSkinCache.INSTANCE.addEquipmentDataToCache(skin, null);
            ItemStack skinStack = SkinNBTHelper.makeEquipmentSkinStack(new SkinPointer(skin));
            ((TileEntityOutfitMaker)this.tileEntity).func_70299_a(1, skinStack);
        }
    }

    private int[] paintPart(ISkinPartTypeTextured texType, int[] desPaint, int[] srcPaint) {
        int textureWidth = 64;
        int textureHeight = 32;
        Point pos = texType.getTextureLocation();
        int width = texType.getTextureModelSize().getX() * 2 + texType.getTextureModelSize().getZ() * 2;
        int height = texType.getTextureModelSize().getY() + texType.getTextureModelSize().getZ();
        for (int ix = 0; ix < width; ++ix) {
            for (int iy = 0; iy < height; ++iy) {
                int x = pos.x + ix;
                int y = pos.y + iy;
                byte[] rgbt = PaintingHelper.intToBytes(srcPaint[x + y * textureWidth]);
                if ((rgbt[3] & 0xFF) == PaintType.NONE.getKey()) continue;
                desPaint[x + y * textureWidth] = srcPaint[x + y * textureWidth];
            }
        }
        return desPaint;
    }

    @Override
    protected ItemStack transferStackFromPlayer(EntityPlayer playerIn, int index) {
        Slot slot = this.func_75139_a(index);
        if (slot.func_75216_d()) {
            Slot targetSlot;
            int i;
            ItemStack stack = slot.func_75211_c();
            ItemStack result = stack.func_77946_l();
            boolean slotted = false;
            if (stack.func_77973_b() instanceof ItemSkin & SkinNBTHelper.stackHasSkinData(stack)) {
                for (i = this.indexSkinsStart; i < this.indexSkinsEnd; ++i) {
                    targetSlot = this.func_75139_a(i);
                    if (!targetSlot.func_75214_a(stack) || !this.func_75135_a(stack, i, i + 1, false)) continue;
                    slotted = true;
                    break;
                }
            }
            if (stack.func_77973_b() instanceof ItemSkin & SkinNBTHelper.stackHasSkinData(stack)) {
                for (i = this.getPlayerInvEndIndex(); i < this.getPlayerInvEndIndex() + 1; ++i) {
                    targetSlot = this.func_75139_a(i);
                    if (!targetSlot.func_75214_a(stack) || !this.func_75135_a(stack, i, i + 1, false)) continue;
                    slotted = true;
                    break;
                }
            }
            if (!slotted) {
                return null;
            }
            if (stack.field_77994_a == 0) {
                slot.func_75215_d(null);
            } else {
                slot.func_75218_e();
            }
            slot.func_82870_a(playerIn, stack);
            return result;
        }
        return null;
    }

    @Override
    public void buttonPressed(byte buttonId) {
        if (buttonId == 0) {
            this.loadOutfit();
        }
        if (buttonId == 1) {
            this.saveOutfit(this.getPlayer());
        }
    }
}

