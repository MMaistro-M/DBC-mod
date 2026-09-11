/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.util.IIcon
 */
package JinRyuu.DragonBC.common.Blocks;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.Items.ItemsDBC;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.blocks.BlockJRMCCrops;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class BlockWildMedMoss
extends BlockJRMCCrops {
    public BlockWildMedMoss() {
        this.maxGrowthStage = 1;
        this.func_149658_d(JRMCoreH.tjdbcAssts + ":BlockCropMedMoss2");
    }

    @Override
    protected boolean func_149854_a(Block parBlock) {
        return parBlock == Blocks.field_150349_c || parBlock == BlocksDBC.BlockNamekGrass;
    }

    public int quantityDropped(int parMetadata, int parFortune, Random parRand) {
        return parRand.nextInt(2) + 1;
    }

    @Override
    public Item func_149650_a(int parMetadata, Random parRand, int parFortune) {
        return ItemsDBC.ItemMedMoss;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister parIIconRegister) {
        this.iIcon = new IIcon[this.maxGrowthStage + 1];
        this.iIcon[0] = parIIconRegister.func_94245_a(JRMCoreH.tjdbcAssts + ":BlockCropMedMoss2");
        this.iIcon[1] = parIIconRegister.func_94245_a(JRMCoreH.tjdbcAssts + ":BlockCropMedMoss3");
    }
}

