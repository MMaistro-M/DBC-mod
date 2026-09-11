/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.internal.FMLNetworkHandler
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package riskyken.armourersWorkshop.common.blocks;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.client.lib.LibBlockResources;
import riskyken.armourersWorkshop.common.blocks.AbstractModBlockContainer;
import riskyken.armourersWorkshop.common.items.block.ModItemBlock;
import riskyken.armourersWorkshop.common.tileentities.TileEntityHologramProjector;
import riskyken.armourersWorkshop.utils.BlockUtils;

public class BlockHologramProjector
extends AbstractModBlockContainer {
    @SideOnly(value=Side.CLIENT)
    private IIcon iconTop;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconBottom;

    public BlockHologramProjector() {
        super("hologramProjector");
        this.setSortPriority(150);
    }

    public Block func_149663_c(String name) {
        GameRegistry.registerBlock((Block)this, ModItemBlock.class, (String)("block." + name));
        return super.func_149663_c(name);
    }

    public void func_149749_a(World world, int x, int y, int z, Block block, int meta) {
        BlockUtils.dropInventoryBlocks(world, x, y, z);
        super.func_149749_a(world, x, y, z, block, meta);
    }

    public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack) {
        int dir = BlockUtils.determineOrientation(x, y, z, entityLivingBase);
        world.func_72921_c(x, y, z, dir, 2);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister register) {
        this.field_149761_L = register.func_94245_a(LibBlockResources.HOLOGRAM_PROJECTOR_SIDE);
        this.iconTop = register.func_94245_a(LibBlockResources.HOLOGRAM_PROJECTOR_TOP);
        this.iconBottom = register.func_94245_a(LibBlockResources.HOLOGRAM_PROJECTOR_BOTTOM);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        if (side == 1) {
            return this.iconTop;
        }
        if (side == 0) {
            return this.iconBottom;
        }
        return this.field_149761_L;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int side) {
        int meta = world.func_72805_g(x, y, z);
        if (meta < 0 | meta > 5) {
            return this.field_149761_L;
        }
        ForgeDirection dir = ForgeDirection.values()[meta];
        if (side == meta) {
            return this.iconTop;
        }
        if (side == dir.getOpposite().ordinal()) {
            return this.iconBottom;
        }
        return this.field_149761_L;
    }

    public void func_149714_e(World world, int x, int y, int z, int p_149714_5_) {
        this.updatePoweredState(world, x, y, z);
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileEntityHologramProjector();
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float xHit, float yHit, float zHit) {
        if (!player.func_82247_a(x, y, z, side, player.func_71045_bC())) {
            return false;
        }
        if (!world.field_72995_K) {
            FMLNetworkHandler.openGui((EntityPlayer)player, (Object)ArmourersWorkshop.instance, (int)16, (World)world, (int)x, (int)y, (int)z);
        }
        return true;
    }

    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis) {
        world.func_72921_c(x, y, z, axis.ordinal(), 2);
        return true;
    }

    public void func_149695_a(World world, int x, int y, int z, Block neighborBlock) {
        this.updatePoweredState(world, x, y, z);
    }

    public void func_149674_a(World world, int x, int y, int z, Random random) {
        this.updatePoweredState(world, x, y, z);
    }

    private void updatePoweredState(World world, int x, int y, int z) {
        TileEntity tileEntity;
        if (!world.field_72995_K && (tileEntity = world.func_147438_o(x, y, z)) != null && tileEntity instanceof TileEntityHologramProjector) {
            ((TileEntityHologramProjector)tileEntity).updatePoweredState();
        }
    }
}

