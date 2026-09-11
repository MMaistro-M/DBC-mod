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
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.common.blocks;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.client.lib.LibBlockResources;
import riskyken.armourersWorkshop.common.blocks.AbstractModBlockContainer;
import riskyken.armourersWorkshop.common.items.block.ModItemBlockWithMetadata;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinLibrary;
import riskyken.armourersWorkshop.utils.BlockUtils;

public class BlockSkinLibrary
extends AbstractModBlockContainer {
    @SideOnly(value=Side.CLIENT)
    private IIcon blockIcon2;
    @SideOnly(value=Side.CLIENT)
    private IIcon[] iconTop;
    @SideOnly(value=Side.CLIENT)
    private IIcon[] iconBottom;

    public BlockSkinLibrary() {
        super("armourLibrary");
        this.setSortPriority(198);
    }

    public Block func_149663_c(String name) {
        GameRegistry.registerBlock((Block)this, ModItemBlockWithMetadata.class, (String)("block." + name));
        return super.func_149663_c(name);
    }

    public void func_149666_a(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 2; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public int func_149692_a(int meta) {
        return meta;
    }

    public void func_149749_a(World world, int x, int y, int z, Block block, int meta) {
        BlockUtils.dropInventoryBlocks(world, x, y, z);
        super.func_149749_a(world, x, y, z, block, meta);
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float xHit, float yHit, float zHit) {
        if (!world.field_72995_K) {
            FMLNetworkHandler.openGui((EntityPlayer)player, (Object)ArmourersWorkshop.instance, (int)4, (World)world, (int)x, (int)y, (int)z);
        }
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister register) {
        this.field_149761_L = register.func_94245_a(LibBlockResources.EQUIPMENT_LIBRARY_0_SIDE);
        this.blockIcon2 = register.func_94245_a(LibBlockResources.EQUIPMENT_LIBRARY_1_SIDE);
        this.iconTop = new IIcon[2];
        this.iconBottom = new IIcon[2];
        this.iconTop[0] = register.func_94245_a(LibBlockResources.EQUIPMENT_LIBRARY_0_TOP);
        this.iconBottom[0] = register.func_94245_a(LibBlockResources.EQUIPMENT_LIBRARY_0_BOTTOM);
        this.iconTop[1] = register.func_94245_a(LibBlockResources.EQUIPMENT_LIBRARY_1_TOP);
        this.iconBottom[1] = register.func_94245_a(LibBlockResources.EQUIPMENT_LIBRARY_1_BOTTOM);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        if (side > 1) {
            if (meta == 0) {
                return this.field_149761_L;
            }
            return this.blockIcon2;
        }
        if (side == 0 & meta == 0) {
            return this.iconBottom[0];
        }
        if (side == 0 & meta == 1) {
            return this.iconBottom[1];
        }
        if (side == 1 & meta == 0) {
            return this.iconTop[0];
        }
        if (side == 1 & meta == 1) {
            return this.iconTop[1];
        }
        return null;
    }

    public TileEntity func_149915_a(World world, int p_149915_2_) {
        return new TileEntitySkinLibrary();
    }
}

