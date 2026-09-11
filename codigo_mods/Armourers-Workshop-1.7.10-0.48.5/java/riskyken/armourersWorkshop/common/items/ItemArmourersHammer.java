/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  buildcraft.api.tools.IToolWrench
 *  cpw.mods.fml.common.Optional$Interface
 *  cpw.mods.fml.common.Optional$Method
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBed
 *  net.minecraft.block.BlockChest
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package riskyken.armourersWorkshop.common.items;

import buildcraft.api.tools.IToolWrench;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockChest;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.items.AbstractModItem;

@Optional.Interface(iface="buildcraft.api.tools.IToolWrench", modid="BuildCraft|Core")
public class ItemArmourersHammer
extends AbstractModItem
implements IToolWrench {
    public ItemArmourersHammer() {
        super("armourersHammer");
        this.setSortPriority(9);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister register) {
        this.field_77791_bV = register.func_94245_a(LibItemResources.ARMOURERS_HAMMER);
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        Block block = world.func_147439_a(x, y, z);
        if (block != null) {
            if (block instanceof BlockBed) {
                this.rotateBed(world, x, y, z, (BlockBed)block, ForgeDirection.getOrientation((int)side));
                player.func_71038_i();
                return !world.field_72995_K;
            }
            if (block instanceof BlockChest) {
                return false;
            }
            ForgeDirection dir = ForgeDirection.getOrientation((int)side);
            if (player.func_70093_af()) {
                dir = dir.getOpposite();
            }
            if (block.rotateBlock(world, x, y, z, dir)) {
                player.func_71038_i();
                return !world.field_72995_K;
            }
        }
        return false;
    }

    private boolean rotateBed(World world, int x, int y, int z, BlockBed block, ForgeDirection axis) {
        ForgeDirection bedRot;
        int meta = world.func_72805_g(x, y, z);
        ForgeDirection[] bedRots = new ForgeDirection[]{ForgeDirection.SOUTH, ForgeDirection.WEST, ForgeDirection.NORTH, ForgeDirection.EAST};
        int bedDir = BlockBed.func_149895_l((int)meta);
        ForgeDirection otherHalf = bedRot = bedRots[bedDir];
        boolean isHead = BlockBed.func_149975_b((int)meta);
        if (isHead) {
            otherHalf = bedRot.getOpposite();
        }
        if (world.func_147439_a(x + otherHalf.offsetX, y + otherHalf.offsetY, z + otherHalf.offsetZ) == block) {
            int otherMeta = world.func_72805_g(x + otherHalf.offsetX, y + otherHalf.offsetY, z + otherHalf.offsetZ);
            int newMeta = (meta & 3) + 2;
            if (!isHead) {
                newMeta += 8;
            }
            world.func_72921_c(x, y, z, newMeta, 3);
            newMeta = (otherMeta & 3) + 2;
            if (isHead) {
                newMeta += 8;
            }
            world.func_72921_c(x + otherHalf.offsetX, y + otherHalf.offsetY, z + otherHalf.offsetZ, newMeta, 3);
            return true;
        }
        return false;
    }

    @Optional.Method(modid="BuildCraft|Core")
    public boolean canWrench(EntityPlayer player, int x, int y, int z) {
        return true;
    }

    @Optional.Method(modid="BuildCraft|Core")
    public void wrenchUsed(EntityPlayer player, int x, int y, int z) {
        player.func_71038_i();
    }

    public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player) {
        return true;
    }
}

