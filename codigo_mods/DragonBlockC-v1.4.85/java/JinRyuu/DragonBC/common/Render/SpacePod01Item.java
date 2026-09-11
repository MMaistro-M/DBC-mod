/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Render;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.Render.SpacePod01Entity;
import JinRyuu.DragonBC.common.mod_DragonBC;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class SpacePod01Item
extends Item {
    private Block spawnID = BlocksDBC.SpacePod01Block;

    public SpacePod01Item(Block par2Block, ItemArmor.ArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
        this.func_77637_a(mod_DragonBC.DragonBlockC);
        this.field_77777_bU = 1;
    }

    public String getTextureFile() {
        return "jinryuudragonbc:dragonitems1.png";
    }

    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a("jinryuudragonbc:" + this.func_77658_a());
    }

    public boolean onItemUse1(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        Block var11 = par3World.func_147439_a(par4, par5, par6);
        return true;
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if (par7 == 0) {
            --par5;
        }
        if (par7 == 1) {
            ++par5;
        }
        if (par7 == 2) {
            --par6;
        }
        if (par7 == 3) {
            ++par6;
        }
        if (par7 == 4) {
            --par4;
        }
        if (par7 == 5) {
            ++par4;
        }
        if (!player.func_82247_a(par4, par5, par6, par7, stack)) {
            return false;
        }
        if (!world.field_72995_K) {
            player.field_71071_by.func_146026_a((Item)this);
            player.field_71071_by.field_70459_e = true;
            player.field_71071_by.field_70459_e = false;
            SpacePod01Entity SpacePod01Entity2 = new SpacePod01Entity(world);
            SpacePod01Entity2.func_70012_b(par4, par5 + 1, par6, player.field_70177_z, 0.0f);
            world.func_72838_d((Entity)SpacePod01Entity2);
        }
        return true;
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (!world.field_72995_K) {
            Vec3 vec = player.func_70040_Z();
            SpacePod01Entity SpacePod01Entity2 = new SpacePod01Entity(world);
            SpacePod01Entity2.func_70012_b(player.field_70165_t + vec.field_72450_a * 1.5, player.field_70163_u + 1.0 + vec.field_72448_b * 1.5, player.field_70161_v + vec.field_72449_c * 1.5, player.field_70177_z, 0.0f);
            world.func_72838_d((Entity)SpacePod01Entity2);
            ItemStack[] inv = player.field_71071_by.field_70462_a;
            int id = 0;
            for (ItemStack invStack : inv) {
                if (invStack != null && invStack.equals(stack)) {
                    player.field_71071_by.field_70462_a[id] = null;
                    break;
                }
                ++id;
            }
        }
        return stack;
    }
}

