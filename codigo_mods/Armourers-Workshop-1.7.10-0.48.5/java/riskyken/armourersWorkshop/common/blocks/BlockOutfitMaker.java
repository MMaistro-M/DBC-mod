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
import net.minecraft.world.World;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.client.lib.LibBlockResources;
import riskyken.armourersWorkshop.common.blocks.AbstractModBlockContainer;
import riskyken.armourersWorkshop.common.items.block.ModItemBlock;
import riskyken.armourersWorkshop.common.tileentities.TileEntityOutfitMaker;
import riskyken.armourersWorkshop.utils.BlockUtils;

public class BlockOutfitMaker
extends AbstractModBlockContainer {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] iconSides;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconBottom;

    public BlockOutfitMaker() {
        super("outfit_maker");
    }

    public Block func_149663_c(String name) {
        GameRegistry.registerBlock((Block)this, ModItemBlock.class, (String)("block." + name));
        return super.func_149663_c(name);
    }

    public void func_149749_a(World world, int x, int y, int z, Block block, int meta) {
        BlockUtils.dropInventoryBlocks(world, x, y, z);
        super.func_149749_a(world, x, y, z, block, meta);
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileEntityOutfitMaker();
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float xHit, float yHit, float zHit) {
        if (!player.func_82247_a(x, y, z, side, player.func_71045_bC())) {
            return false;
        }
        if (!world.field_72995_K) {
            FMLNetworkHandler.openGui((EntityPlayer)player, (Object)ArmourersWorkshop.getInstance(), (int)17, (World)world, (int)x, (int)y, (int)z);
        }
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister register) {
        this.field_149761_L = register.func_94245_a(LibBlockResources.OUTFIT_MAKER_TOP);
        this.iconBottom = register.func_94245_a(LibBlockResources.OUTFIT_MAKER_BOTTOM);
        this.iconSides = new IIcon[4];
        this.iconSides[0] = register.func_94245_a(LibBlockResources.OUTFIT_MAKER_SIDE_1);
        this.iconSides[1] = register.func_94245_a(LibBlockResources.OUTFIT_MAKER_SIDE_2);
        this.iconSides[2] = register.func_94245_a(LibBlockResources.OUTFIT_MAKER_SIDE_3);
        this.iconSides[3] = register.func_94245_a(LibBlockResources.OUTFIT_MAKER_SIDE_4);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        if (side == 0) {
            return this.iconBottom;
        }
        if (side == 1) {
            return this.field_149761_L;
        }
        if (side > 1 & side < 6) {
            return this.iconSides[side - 2];
        }
        return this.field_149761_L;
    }
}

