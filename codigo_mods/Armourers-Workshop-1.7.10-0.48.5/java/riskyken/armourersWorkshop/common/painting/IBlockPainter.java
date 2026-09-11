/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.common.painting;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.common.blocks.BlockLocation;

public interface IBlockPainter {
    public void usedOnBlockSide(ItemStack var1, EntityPlayer var2, World var3, BlockLocation var4, Block var5, int var6);
}

