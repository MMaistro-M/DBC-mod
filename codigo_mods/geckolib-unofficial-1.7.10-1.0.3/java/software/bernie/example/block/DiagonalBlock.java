/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.ITileEntityProvider
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package software.bernie.example.block;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import software.bernie.example.GeckoLibMod;
import software.bernie.example.block.tile.DiagonalTileEntity;

public class DiagonalBlock
extends Block
implements ITileEntityProvider {
    public AxisAlignedBB boundingBox;

    public DiagonalBlock() {
        super(Material.field_151576_e);
        this.func_149647_a(GeckoLibMod.getGeckolibItemGroup());
    }

    public boolean func_149686_d() {
        return false;
    }

    public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        this.func_149719_a((IBlockAccess)p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
        if (this.boundingBox == null) {
            return super.func_149668_a(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
        }
        return this.boundingBox;
    }

    public void func_149719_a(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_) {
        this.boundingBox = p_149719_1_.func_147438_o(p_149719_2_, p_149719_3_, p_149719_4_) != null ? ((DiagonalTileEntity)p_149719_1_.func_147438_o((int)p_149719_2_, (int)p_149719_3_, (int)p_149719_4_)).boundingBox : AxisAlignedBB.func_72330_a((double)((double)p_149719_2_ + this.field_149759_B), (double)((double)p_149719_3_ + this.field_149760_C), (double)((double)p_149719_4_ + this.field_149754_D), (double)((double)p_149719_2_ + this.field_149755_E), (double)((double)p_149719_3_ + this.field_149756_F), (double)((double)p_149719_4_ + this.field_149757_G));
    }

    public AxisAlignedBB func_149633_g(World p_149633_1_, int p_149633_2_, int p_149633_3_, int p_149633_4_) {
        this.func_149719_a((IBlockAccess)p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
        if (this.boundingBox == null) {
            return super.func_149633_g(p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
        }
        return this.boundingBox;
    }

    public void func_149743_a(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_) {
        if (this.boundingBox == null) {
            super.func_149743_a(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        } else if (this.boundingBox.func_72326_a(p_149743_5_)) {
            p_149743_6_.add(this.boundingBox);
        }
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return -1;
    }

    public boolean func_149716_u() {
        return true;
    }

    @Nullable
    public TileEntity func_149915_a(World worldIn, int meta) {
        return new DiagonalTileEntity();
    }
}

