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
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package riskyken.armourersWorkshop.common.blocks;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.client.lib.LibBlockResources;
import riskyken.armourersWorkshop.client.texture.PlayerTexture;
import riskyken.armourersWorkshop.common.blocks.AbstractModBlockContainer;
import riskyken.armourersWorkshop.common.data.TextureType;
import riskyken.armourersWorkshop.common.items.block.ModItemBlock;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourer;
import riskyken.armourersWorkshop.utils.BlockUtils;

public class BlockArmourer
extends AbstractModBlockContainer {
    @SideOnly(value=Side.CLIENT)
    private IIcon iconTop;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconBottom;

    public BlockArmourer() {
        super("armourerBrain");
        this.setSortPriority(200);
    }

    public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            TileEntity te = world.func_147438_o(x, y, z);
            if (te != null && te instanceof TileEntityArmourer) {
                ForgeDirection direction = BlockUtils.determineDirectionSide(entity).getOpposite();
                ((TileEntityArmourer)te).setDirection(ForgeDirection.NORTH);
                if (!world.field_72995_K) {
                    ((TileEntityArmourer)te).setTexture(new PlayerTexture(player.func_70005_c_(), TextureType.USER));
                    ((TileEntityArmourer)te).onPlaced();
                }
            }
        }
    }

    public void func_149749_a(World world, int x, int y, int z, Block block, int meta) {
        BlockUtils.dropInventoryBlocks(world, x, y, z);
        super.func_149749_a(world, x, y, z, block, meta);
    }

    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null & te instanceof TileEntityArmourer) {
            ((TileEntityArmourer)te).preRemove();
        }
        return super.removedByPlayer(world, player, x, y, z, willHarvest);
    }

    public Block func_149663_c(String name) {
        GameRegistry.registerBlock((Block)this, ModItemBlock.class, (String)("block." + name));
        return super.func_149663_c(name);
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float xHit, float yHit, float zHit) {
        if (!player.func_82247_a(x, y, z, side, player.func_71045_bC())) {
            return false;
        }
        if (!world.field_72995_K) {
            FMLNetworkHandler.openGui((EntityPlayer)player, (Object)ArmourersWorkshop.instance, (int)1, (World)world, (int)x, (int)y, (int)z);
        }
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister register) {
        this.field_149761_L = register.func_94245_a(LibBlockResources.ARMOURER_SIDE);
        this.iconTop = register.func_94245_a(LibBlockResources.ARMOURER_TOP);
        this.iconBottom = register.func_94245_a(LibBlockResources.ARMOURER_BOTTOM);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        if (side == 0) {
            return this.iconBottom;
        }
        if (side == 1) {
            return this.iconTop;
        }
        return this.field_149761_L;
    }

    public TileEntity func_149915_a(World world, int p_149915_2_) {
        return null;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntityArmourer();
    }
}

