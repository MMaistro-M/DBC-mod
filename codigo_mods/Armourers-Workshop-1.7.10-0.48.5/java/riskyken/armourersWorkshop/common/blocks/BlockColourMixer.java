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
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.common.blocks;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.client.lib.LibBlockResources;
import riskyken.armourersWorkshop.common.blocks.AbstractModBlockContainer;
import riskyken.armourersWorkshop.common.items.block.ModItemBlock;
import riskyken.armourersWorkshop.common.tileentities.TileEntityColourMixer;
import riskyken.armourersWorkshop.proxies.ClientProxy;
import riskyken.armourersWorkshop.utils.BlockUtils;

public class BlockColourMixer
extends AbstractModBlockContainer {
    @SideOnly(value=Side.CLIENT)
    private IIcon topIcon;
    @SideOnly(value=Side.CLIENT)
    private IIcon bottomIcon;
    @SideOnly(value=Side.CLIENT)
    private IIcon sideOverlayIcon;

    public BlockColourMixer() {
        super("colourMixer");
        this.setSortPriority(124);
    }

    public Block func_149663_c(String name) {
        GameRegistry.registerBlock((Block)this, ModItemBlock.class, (String)("block." + name));
        return super.func_149663_c(name);
    }

    public int func_149692_a(int meta) {
        return meta;
    }

    public void func_149749_a(World world, int x, int y, int z, Block block, int meta) {
        BlockUtils.dropInventoryBlocks(world, x, y, z);
        super.func_149749_a(world, x, y, z, block, meta);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister register) {
        this.field_149761_L = register.func_94245_a(LibBlockResources.COLOUR_MIXER_SIDE);
        this.topIcon = register.func_94245_a(LibBlockResources.COLOUR_MIXER_TOP);
        this.bottomIcon = register.func_94245_a(LibBlockResources.COLOUR_MIXER_BOTTOM);
        this.sideOverlayIcon = register.func_94245_a(LibBlockResources.COLOUR_MIXER_SIDE_OVERLAY);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        if (side == 0) {
            return this.bottomIcon;
        }
        if (side == 1) {
            return this.topIcon;
        }
        if (ClientProxy.renderPass == 0) {
            return this.sideOverlayIcon;
        }
        return this.field_149761_L;
    }

    public boolean func_149686_d() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149720_d(IBlockAccess blockAccess, int x, int y, int z) {
        TileEntity te;
        if (ClientProxy.renderPass == 0 && (te = blockAccess.func_147438_o(x, y, z)) != null && te instanceof TileEntityColourMixer) {
            return ((TileEntityColourMixer)te).getColour(0);
        }
        return -1;
    }

    public int func_149645_b() {
        return ArmourersWorkshop.proxy.getBlockRenderType((Block)this);
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float xHit, float yHit, float zHit) {
        if (!player.func_82247_a(x, y, z, side, player.func_71045_bC())) {
            return false;
        }
        if (!world.field_72995_K) {
            FMLNetworkHandler.openGui((EntityPlayer)player, (Object)ArmourersWorkshop.instance, (int)0, (World)world, (int)x, (int)y, (int)z);
        }
        return true;
    }

    public TileEntity func_149915_a(World world, int p_149915_2_) {
        return new TileEntityColourMixer();
    }
}

