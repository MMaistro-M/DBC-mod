/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.items.AbstractModItem;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.wardrobe.ExPropsPlayerSkinData;

public class ItemSkinUnlock
extends AbstractModItem {
    private final ISkinType[] VALID_SKINS = new ISkinType[]{SkinTypeRegistry.skinHead, SkinTypeRegistry.skinChest, SkinTypeRegistry.skinLegs, SkinTypeRegistry.skinFeet, SkinTypeRegistry.skinWings, SkinTypeRegistry.skinOutfit};
    @SideOnly(value=Side.CLIENT)
    IIcon iconChest;
    @SideOnly(value=Side.CLIENT)
    IIcon iconLegs;
    @SideOnly(value=Side.CLIENT)
    IIcon iconFeet;
    @SideOnly(value=Side.CLIENT)
    IIcon iconWings;
    @SideOnly(value=Side.CLIENT)
    IIcon iconOutfit;

    public ItemSkinUnlock() {
        super("skinUnlock");
        this.func_77627_a(true);
        this.setSortPriority(7);
    }

    public void func_150895_a(Item item, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < this.VALID_SKINS.length; ++i) {
            list.add(new ItemStack((Item)this, 1, i));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister register) {
        this.field_77791_bV = register.func_94245_a(LibItemResources.SKIN_UNLOCK_HEAD);
        this.iconChest = register.func_94245_a(LibItemResources.SKIN_UNLOCK_CHEST);
        this.iconLegs = register.func_94245_a(LibItemResources.SKIN_UNLOCK_LEGS);
        this.iconFeet = register.func_94245_a(LibItemResources.SKIN_UNLOCK_FEET);
        this.iconWings = register.func_94245_a(LibItemResources.SKIN_UNLOCK_WINGS);
        this.iconOutfit = register.func_94245_a(LibItemResources.SKIN_UNLOCK_OUTFIT);
    }

    public IIcon func_77617_a(int damage) {
        switch (damage) {
            case 0: {
                return this.field_77791_bV;
            }
            case 1: {
                return this.iconChest;
            }
            case 2: {
                return this.iconLegs;
            }
            case 3: {
                return this.iconFeet;
            }
            case 4: {
                return this.iconWings;
            }
            case 5: {
                return this.iconOutfit;
            }
        }
        return this.field_77791_bV;
    }

    public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer player) {
        if (world.field_72995_K) {
            return itemStack;
        }
        ISkinType skinType = this.getSkinTypeFormStack(itemStack);
        ExPropsPlayerSkinData equipmentData = ExPropsPlayerSkinData.get(player);
        int count = equipmentData.getEquipmentWardrobeData().getUnlockedSlotsForSkinType(skinType);
        String localizedSkinName = SkinTypeRegistry.INSTANCE.getLocalizedSkinTypeName(skinType);
        if (++count <= 10) {
            equipmentData.setSkinColumnCount(skinType, count);
            player.func_146105_b((IChatComponent)new ChatComponentTranslation("chat.armourersworkshop:slotUnlocked", new Object[]{localizedSkinName.toLowerCase(), Integer.toString(count)}));
            --itemStack.field_77994_a;
        } else {
            player.func_146105_b((IChatComponent)new ChatComponentTranslation("chat.armourersworkshop:slotUnlockedFailed", new Object[]{localizedSkinName}));
        }
        return itemStack;
    }

    private ISkinType getSkinTypeFormStack(ItemStack itemStack) {
        int damage = itemStack.func_77960_j();
        if (damage >= 0 & damage < this.VALID_SKINS.length) {
            return this.VALID_SKINS[damage];
        }
        return this.VALID_SKINS[0];
    }
}

