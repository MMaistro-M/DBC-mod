/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import noppes.npcs.EventHooks;
import noppes.npcs.api.item.IItemCustomizable;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.items.ItemRenderInterface;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.event.ItemEvent;
import org.lwjgl.opengl.GL11;

public abstract class ItemCustomizable
extends Item
implements ItemRenderInterface {
    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = Items.field_151035_b.func_77617_a(0);
    }

    public int func_82790_a(ItemStack itemStack, int par2) {
        return 9127187;
    }

    public boolean onEntitySwing(EntityLivingBase entityLivingBase, ItemStack stack) {
        if (entityLivingBase.field_70170_p.field_72995_K) {
            return false;
        }
        IItemStack istack = NpcAPI.Instance().getIItemStack(stack);
        ItemEvent.AttackEvent eve = new ItemEvent.AttackEvent((IItemCustomizable)istack, NpcAPI.Instance().getIEntity((Entity)entityLivingBase), 2, null);
        return EventHooks.onScriptItemAttack((IItemCustomizable)istack, eve);
    }

    public int func_77626_a(ItemStack stack) {
        IItemCustomizable customizable = (IItemCustomizable)NpcAPI.Instance().getIItemStack(stack);
        return customizable.getMaxItemUseDuration();
    }

    public boolean showDurabilityBar(ItemStack stack) {
        IItemStack istack = NpcAPI.Instance().getIItemStack(stack);
        return istack instanceof IItemCustomizable && ((IItemCustomizable)istack).getDurabilityShow() != false;
    }

    public double getDurabilityForDisplay(ItemStack stack) {
        IItemStack istack = NpcAPI.Instance().getIItemStack(stack);
        return istack instanceof IItemCustomizable ? 1.0 - ((IItemCustomizable)istack).getDurabilityValue() : 1.0;
    }

    public int getItemStackLimit(ItemStack stack) {
        IItemStack istack = NpcAPI.Instance().getIItemStack(stack);
        return istack instanceof IItemCustomizable ? istack.getMaxStackSize() : super.getItemStackLimit(stack);
    }

    public boolean func_77616_k(ItemStack stack) {
        IItemStack istack = NpcAPI.Instance().getIItemStack(stack);
        return istack instanceof IItemCustomizable ? ((IItemCustomizable)istack).isTool() : super.func_77616_k(stack);
    }

    public float getDigSpeed(ItemStack stack, Block block, int metadata) {
        IItemStack istack = NpcAPI.Instance().getIItemStack(stack);
        return istack instanceof IItemCustomizable ? (float)((IItemCustomizable)istack).getDigSpeed() : super.getDigSpeed(stack, block, metadata);
    }

    public boolean isValidArmor(ItemStack stack, int armorType, Entity entity) {
        IItemStack istack = NpcAPI.Instance().getIItemStack(stack);
        if (istack instanceof IItemCustomizable) {
            if (((IItemCustomizable)istack).getArmorType() == -1) {
                return true;
            }
            return armorType == ((IItemCustomizable)istack).getArmorType();
        }
        return super.isValidArmor(stack, armorType, entity);
    }

    public int getItemEnchantability(ItemStack stack) {
        IItemStack istack = NpcAPI.Instance().getIItemStack(stack);
        return istack instanceof IItemCustomizable ? ((IItemCustomizable)istack).getEnchantability() : super.getItemEnchantability(stack);
    }

    public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        IItemStack istack = NpcAPI.Instance().getIItemStack(stack);
        ItemEvent.AttackEvent eve = new ItemEvent.AttackEvent((IItemCustomizable)istack, NpcAPI.Instance().getIEntity((Entity)attacker), 1, NpcAPI.Instance().getIEntity((Entity)target));
        return EventHooks.onScriptItemAttack((IItemCustomizable)istack, eve);
    }

    public boolean func_77623_v() {
        return true;
    }

    public Item func_77655_b(String name) {
        GameRegistry.registerItem((Item)this, (String)name);
        return super.func_77655_b(name);
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        player.func_71008_a(stack, this.func_77626_a(stack));
        return stack;
    }

    public EnumAction func_77661_b(ItemStack stack) {
        IItemStack istack = NpcAPI.Instance().getIItemStack(stack);
        if (istack instanceof IItemCustomizable) {
            switch (((IItemCustomizable)istack).getItemUseAction()) {
                case 0: {
                    return EnumAction.none;
                }
                case 1: {
                    return EnumAction.block;
                }
                case 2: {
                    return EnumAction.bow;
                }
                case 3: {
                    return EnumAction.eat;
                }
                case 4: {
                    return EnumAction.drink;
                }
            }
        }
        return super.func_77661_b(stack);
    }

    @Override
    public void renderSpecial() {
    }

    public void renderOffset(IItemCustomizable scriptCustomItem) {
        GL11.glTranslatef((float)(0.135f * scriptCustomItem.getScaleX().floatValue()), (float)(0.2f * scriptCustomItem.getScaleY().floatValue()), (float)(0.07f * scriptCustomItem.getScaleZ().floatValue()));
    }
}

