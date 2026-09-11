/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package noppes.npcs.blocks;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.EventHooks;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.blocks.tiles.TileScripted;
import noppes.npcs.constants.EnumGuiType;

public class BlockScripted
extends BlockContainer {
    public int renderId = -1;
    public static final AxisAlignedBB AABB = AxisAlignedBB.func_72330_a((double)0.001f, (double)0.001f, (double)0.001f, (double)0.998f, (double)0.998f, (double)0.998f);
    public static final AxisAlignedBB AABB_EMPTY = AxisAlignedBB.func_72330_a((double)0.0, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (double)0.0);

    public BlockScripted() {
        super(Material.field_151576_e);
        this.func_149672_a(field_149769_e);
    }

    public TileEntity func_149915_a(World worldIn, int meta) {
        return new TileScripted();
    }

    public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
        TileScripted tile = (TileScripted)world.func_147438_o(x, y, z);
        if (tile != null && tile.isPassible && (tile.blockModel == null || tile.blockModel == Blocks.field_150350_a)) {
            return AABB_EMPTY;
        }
        return AABB.func_72325_c((double)x, (double)y, (double)z);
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        TileScripted tile = (TileScripted)world.func_147438_o(x, y, z);
        if (tile != null && tile.isPassible) {
            return AABB_EMPTY;
        }
        return AABB.func_72325_c((double)x, (double)y, (double)z);
    }

    public boolean func_149655_b(IBlockAccess worldIn, int x, int y, int z) {
        TileScripted tile = (TileScripted)worldIn.func_147438_o(x, y, z);
        return tile != null && tile.isPassible;
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (world.field_72995_K) {
            return true;
        }
        ItemStack currentItem = player.field_71071_by.func_70448_g();
        if (currentItem != null && currentItem.func_77973_b() == CustomItems.scripter) {
            NoppesUtilServer.sendOpenGui(player, EnumGuiType.ScriptBlock, null, x, y, z);
            return true;
        }
        TileScripted tile = (TileScripted)world.func_147438_o(x, y, z);
        return !EventHooks.onScriptBlockInteract(tile, player, side, hitX, hitY, hitZ);
    }

    public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack) {
        if (entity instanceof EntityPlayer && !world.field_72995_K) {
            NoppesUtilServer.sendOpenGui((EntityPlayer)entity, EnumGuiType.ScriptBlock, null, x, y, z);
        }
    }

    public void func_149670_a(World world, int x, int y, int z, Entity entityIn) {
        if (world.field_72995_K) {
            return;
        }
        TileScripted tile = (TileScripted)world.func_147438_o(x, y, z);
        EventHooks.onScriptBlockCollide(tile, entityIn);
    }

    public void func_149639_l(World world, int x, int y, int z) {
        if (world.field_72995_K) {
            return;
        }
        TileScripted tile = (TileScripted)world.func_147438_o(x, y, z);
        EventHooks.onScriptBlockRainFill(tile);
    }

    public void func_149746_a(World world, int x, int y, int z, Entity entity, float fallDistance) {
        if (world.field_72995_K) {
            return;
        }
        TileScripted tile = (TileScripted)world.func_147438_o(x, y, z);
        fallDistance = EventHooks.onScriptBlockFallenUpon(tile, entity, fallDistance);
        super.func_149746_a(world, x, y, z, entity, fallDistance);
    }

    public boolean canRenderInPass(int pass) {
        return super.canRenderInPass(pass);
    }

    public int func_149645_b() {
        return this.renderId;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public void func_149699_a(World world, int x, int y, int z, EntityPlayer player) {
        if (world.field_72995_K) {
            return;
        }
        TileScripted tile = (TileScripted)world.func_147438_o(x, y, z);
        EventHooks.onScriptBlockClicked(tile, player);
    }

    public void func_149749_a(World world, int x, int y, int z, Block block, int metadata) {
        if (!world.field_72995_K) {
            TileScripted tile = (TileScripted)world.func_147438_o(x, y, z);
            EventHooks.onScriptBlockBreak(tile);
        }
        super.func_149749_a(world, x, y, z, block, metadata);
    }

    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
        TileScripted tile;
        if (!world.field_72995_K && EventHooks.onScriptBlockHarvest(tile = (TileScripted)world.func_147438_o(x, y, z), player)) {
            return false;
        }
        return super.removedByPlayer(world, player, x, y, z, willHarvest);
    }

    public Item func_149650_a(int i, Random rand, int fortune) {
        return null;
    }

    public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
        TileScripted tile;
        if (!world.field_72995_K && EventHooks.onScriptBlockExploded(tile = (TileScripted)world.func_147438_o(x, y, z))) {
            return;
        }
        super.onBlockExploded(world, x, y, z, explosion);
    }

    public boolean func_149744_f() {
        return true;
    }

    public int func_149709_b(IBlockAccess worldIn, int x, int y, int z, int side) {
        return this.func_149748_c(worldIn, x, y, z, side);
    }

    public int func_149748_c(IBlockAccess world, int x, int y, int z, int side) {
        TileScripted tile = (TileScripted)world.func_147438_o(x, y, z);
        if (tile == null) {
            return 0;
        }
        return tile.activePowering;
    }

    public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) {
        return ((TileScripted)world.func_147438_o((int)x, (int)y, (int)z)).isLadder;
    }

    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public float func_149712_f(World world, int x, int y, int z) {
        TileScripted tile = (TileScripted)world.func_147438_o(x, y, z);
        if (tile == null) {
            return 0.0f;
        }
        return tile.blockHardness;
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        TileScripted tile = (TileScripted)world.func_147438_o(x, y, z);
        if (tile == null) {
            return 0;
        }
        return tile.lightValue;
    }

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        TileScripted tile = (TileScripted)world.func_147438_o(x, y, z);
        if (tile == null) {
            return 0.0f;
        }
        return tile.blockResistance;
    }

    public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) {
        TileScripted tile = (TileScripted)world.func_147438_o(x, y, z);
        EventHooks.onScriptBlockNeighborChanged(tile, tileX, tileY, tileZ);
    }

    public void func_149695_a(World world, int x, int y, int z, Block block) {
        TileScripted tile = (TileScripted)world.func_147438_o(x, y, z);
        int power = 0;
        for (EnumFacing enumfacing : EnumFacing.values()) {
            int p = world.func_72878_l(x + enumfacing.func_82601_c(), y + enumfacing.func_82599_e(), z + enumfacing.func_82599_e(), enumfacing.ordinal());
            if (p <= power) continue;
            power = p;
        }
        if (tile.prevPower != power && tile.powering <= 0) {
            tile.newPower = power;
        }
    }
}

