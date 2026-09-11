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
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.items.AbstractModItem;
import riskyken.armourersWorkshop.common.skin.cubes.CubeRegistry;

public class ItemBlockMarker
extends AbstractModItem {
    public ItemBlockMarker() {
        super("blockMarker");
        this.setSortPriority(12);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a(LibItemResources.BLOCK_MARKER);
    }

    public boolean func_77648_a(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        Block block = world.func_147439_a(x, y, z);
        if (CubeRegistry.INSTANCE.isBuildingBlock(block)) {
            if (!world.field_72995_K) {
                int newMeta = side + 1;
                int meta = world.func_72805_g(x, y, z);
                if (newMeta == meta) {
                    world.func_72921_c(x, y, z, 0, 2);
                } else {
                    world.func_72921_c(x, y, z, newMeta, 2);
                }
            }
            return true;
        }
        return false;
    }
}

