/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.world.IBlockAccess
 */
package riskyken.armourersWorkshop.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.IBlockAccess;
import riskyken.armourersWorkshop.client.lib.LibBlockResources;
import riskyken.armourersWorkshop.common.blocks.BlockColourable;

public class BlockColourableGlass
extends BlockColourable {
    public BlockColourableGlass(String name, boolean glowing) {
        super(name, glowing);
        this.setSortPriority(121);
        if (glowing) {
            this.setSortPriority(120);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister register) {
        this.field_149761_L = register.func_94245_a(LibBlockResources.COLOURABLE_GLASS);
        this.markerOverlay = register.func_94245_a(LibBlockResources.COLOURABLE_MARKER);
        this.noTexture = register.func_94245_a(LibBlockResources.COLOURABLE_NO_TEXTURE);
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149701_w() {
        return 1;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side) {
        Block sideBlock = world.func_147439_a(x, y, z);
        return sideBlock != this;
    }

    public boolean func_149686_d() {
        return false;
    }
}

