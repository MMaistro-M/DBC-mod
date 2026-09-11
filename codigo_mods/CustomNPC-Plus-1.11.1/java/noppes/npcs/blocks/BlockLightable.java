/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package noppes.npcs.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import noppes.npcs.blocks.BlockRotated;

public abstract class BlockLightable
extends BlockRotated {
    protected BlockLightable(Block block, boolean lit) {
        super(block);
        if (lit) {
            this.func_149715_a(1.0f);
        }
    }

    public abstract Block unlitBlock();

    public abstract Block litBlock();

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        TileEntity oldTile = world.func_147438_o(x, y, z);
        NBTTagCompound compound = new NBTTagCompound();
        oldTile.func_145841_b(compound);
        Block newBlock = this.litBlock() == this ? this.unlitBlock() : this.litBlock();
        int meta = world.func_72805_g(x, y, z);
        world.func_147465_d(x, y, z, newBlock, meta, 2);
        TileEntity newTile = world.func_147438_o(x, y, z);
        if (newTile != null) {
            newTile.func_145839_a(compound);
            newTile.func_70296_d();
        }
        world.func_147471_g(x, y, z);
        world.func_147460_e(x, y, z, newBlock);
        return true;
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.func_150898_a((Block)this.litBlock());
    }

    @SideOnly(value=Side.CLIENT)
    public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return Item.func_150898_a((Block)this.litBlock());
    }

    protected ItemStack func_149644_j(int p_149644_1_) {
        return new ItemStack(this.litBlock());
    }
}

