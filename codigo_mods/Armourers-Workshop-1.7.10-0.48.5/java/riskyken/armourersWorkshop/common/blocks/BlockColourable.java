/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.common.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.api.common.painting.IPantable;
import riskyken.armourersWorkshop.api.common.painting.IPantableBlock;
import riskyken.armourersWorkshop.api.common.skin.cubes.ICubeColour;
import riskyken.armourersWorkshop.client.lib.LibBlockResources;
import riskyken.armourersWorkshop.common.blocks.AbstractModBlockContainer;
import riskyken.armourersWorkshop.common.items.block.ModItemBlock;
import riskyken.armourersWorkshop.common.painting.PaintType;
import riskyken.armourersWorkshop.common.skin.cubes.CubeColour;
import riskyken.armourersWorkshop.common.tileentities.TileEntityColourable;
import riskyken.armourersWorkshop.utils.UtilColour;

public class BlockColourable
extends AbstractModBlockContainer
implements IPantableBlock {
    @SideOnly(value=Side.CLIENT)
    protected IIcon markerOverlay;
    @SideOnly(value=Side.CLIENT)
    protected IIcon noTexture;

    public BlockColourable(String name, boolean glowing) {
        super(name);
        if (glowing) {
            this.func_149715_a(1.0f);
        }
        this.func_149711_c(1.0f);
        this.func_149713_g(0);
        this.setSortPriority(123);
        if (glowing) {
            this.setSortPriority(122);
        }
    }

    public Block func_149663_c(String name) {
        GameRegistry.registerBlock((Block)this, ModItemBlock.class, (String)("block." + name));
        return super.func_149663_c(name);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister register) {
        this.field_149761_L = register.func_94245_a(LibBlockResources.COLOURABLE);
        this.markerOverlay = register.func_94245_a(LibBlockResources.COLOURABLE_MARKER);
        this.noTexture = register.func_94245_a(LibBlockResources.COLOURABLE_NO_TEXTURE);
    }

    public IIcon func_149691_a(int paintType, int meta) {
        if (meta > 0) {
            return this.markerOverlay;
        }
        if (paintType == 0) {
            return this.noTexture;
        }
        return super.func_149691_a(paintType, meta);
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float xHit, float yHit, float zHit) {
        if (!player.func_82247_a(x, y, z, side, player.func_71045_bC())) {
            return false;
        }
        if (player.func_71045_bC() != null && player.func_71045_bC().func_77973_b() == Items.field_151100_aR) {
            if (world.field_72995_K) {
                return true;
            }
            this.setColour((IBlockAccess)world, x, y, z, UtilColour.getMinecraftColor(-player.func_71045_bC().func_77960_j() + 15, UtilColour.ColourFamily.MINECRAFT), side);
            return true;
        }
        return false;
    }

    public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
        return new TileEntityColourable();
    }

    @Override
    public boolean setColour(IBlockAccess world, int x, int y, int z, int colour, int side) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null & te instanceof IPantable) {
            ((IPantable)te).setColour(colour, side);
            return true;
        }
        return false;
    }

    @Override
    public boolean setColour(IBlockAccess world, int x, int y, int z, byte[] rgb, int side) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null & te instanceof IPantable) {
            ((IPantable)te).setColour(rgb, side);
            return true;
        }
        return false;
    }

    @Override
    public int getColour(IBlockAccess world, int x, int y, int z, int side) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null & te instanceof IPantable) {
            return ((IPantable)te).getColour(side);
        }
        return 0;
    }

    @Override
    public ICubeColour getColour(IBlockAccess world, int x, int y, int z) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null & te instanceof IPantable) {
            return ((IPantable)te).getColour();
        }
        return new CubeColour();
    }

    @Override
    public void setPaintType(IBlockAccess world, int x, int y, int z, PaintType paintType, int side) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null & te instanceof IPantable) {
            ((IPantable)te).setPaintType(paintType, side);
        }
    }

    @Override
    public PaintType getPaintType(IBlockAccess world, int x, int y, int z, int side) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null & te instanceof IPantable) {
            return ((IPantable)te).getPaintType(side);
        }
        return PaintType.NORMAL;
    }

    @Override
    public boolean isRemoteOnly(IBlockAccess world, int x, int y, int z, int side) {
        return false;
    }

    public int func_149645_b() {
        return ArmourersWorkshop.proxy.getBlockRenderType((Block)this);
    }
}

