/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.internal.FMLNetworkHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.common.items;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.items.AbstractModItem;
import riskyken.armourersWorkshop.common.wardrobe.entity.EntitySkinHandler;
import riskyken.armourersWorkshop.utils.ModLogger;

public class ItemWandOfStyle
extends AbstractModItem {
    public ItemWandOfStyle() {
        super("wandOfStyle");
        this.setSortPriority(8);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a(LibItemResources.WAND_OF_STYLE);
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        ModLogger.log(entity.getClass());
        if (EntitySkinHandler.INSTANCE.canUseWandOfStyleOnEntity(entity)) {
            if (entity.field_70170_p.field_72995_K) {
                return true;
            }
            FMLNetworkHandler.openGui((EntityPlayer)player, (Object)ArmourersWorkshop.instance, (int)9, (World)entity.field_70170_p, (int)entity.func_145782_y(), (int)0, (int)0);
        }
        return true;
    }

    public boolean func_111207_a(ItemStack itemStack, EntityPlayer entityPlayer, EntityLivingBase entityLivingBase) {
        ModLogger.log(entityLivingBase.getClass());
        if (EntitySkinHandler.INSTANCE.canUseWandOfStyleOnEntity((Entity)entityLivingBase)) {
            if (entityLivingBase.field_70170_p.field_72995_K) {
                return true;
            }
            FMLNetworkHandler.openGui((EntityPlayer)entityPlayer, (Object)ArmourersWorkshop.instance, (int)9, (World)entityLivingBase.field_70170_p, (int)entityLivingBase.func_145782_y(), (int)0, (int)0);
        }
        return false;
    }
}

