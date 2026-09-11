/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.api.common.painting.IPantableBlock;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.blocks.BlockBoundingBox;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.items.AbstractModItem;
import riskyken.armourersWorkshop.common.lib.LibSounds;
import riskyken.armourersWorkshop.common.painting.PaintType;

public class ItemSoap
extends AbstractModItem {
    public ItemSoap() {
        super("soap");
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a(LibItemResources.SOAP);
    }

    public int func_82790_a(ItemStack itemStack, int pass) {
        return -32814;
    }

    public boolean func_77648_a(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        Block block = world.func_147439_a(x, y, z);
        if (block instanceof IPantableBlock) {
            IPantableBlock iPantableBlock = (IPantableBlock)block;
        }
        if (block == ModBlocks.boundingBox) {
            BlockBoundingBox bb = (BlockBoundingBox)block;
            if (!world.field_72995_K) {
                bb.setColour((IBlockAccess)world, x, y, z, 0xFFFFFF, side);
                bb.setPaintType((IBlockAccess)world, x, y, z, PaintType.NONE, side);
                world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, LibSounds.PAINT, 1.0f, world.field_73012_v.nextFloat() * 0.1f + 0.9f);
            }
            return true;
        }
        return false;
    }
}

