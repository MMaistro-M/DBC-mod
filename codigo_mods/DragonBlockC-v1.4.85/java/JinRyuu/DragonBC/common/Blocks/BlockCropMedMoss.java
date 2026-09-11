/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.Item
 *  net.minecraft.util.IIcon
 */
package JinRyuu.DragonBC.common.Blocks;

import JinRyuu.DragonBC.common.Items.ItemsDBC;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.blocks.BlockJRMCCrops;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class BlockCropMedMoss
extends BlockJRMCCrops {
    public BlockCropMedMoss() {
        this.func_149658_d(JRMCoreH.tjdbcAssts + ":BlockCropMedMoss0");
    }

    public int quantityDropped(int parMetadata, int parFortune, Random parRand) {
        return parMetadata / 3 > 1 ? parMetadata / 3 + parRand.nextInt(2) : parMetadata / 3;
    }

    @Override
    public Item func_149650_a(int parMetadata, Random parRand, int parFortune) {
        return ItemsDBC.ItemMedMoss;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister parIIconRegister) {
        this.iIcon = new IIcon[this.maxGrowthStage + 1];
        this.iIcon[0] = parIIconRegister.func_94245_a(JRMCoreH.tjdbcAssts + ":BlockCropMedMoss0");
        this.iIcon[1] = parIIconRegister.func_94245_a(JRMCoreH.tjdbcAssts + ":BlockCropMedMoss0");
        this.iIcon[2] = parIIconRegister.func_94245_a(JRMCoreH.tjdbcAssts + ":BlockCropMedMoss0");
        this.iIcon[3] = parIIconRegister.func_94245_a(JRMCoreH.tjdbcAssts + ":BlockCropMedMoss1");
        this.iIcon[4] = parIIconRegister.func_94245_a(JRMCoreH.tjdbcAssts + ":BlockCropMedMoss1");
        this.iIcon[5] = parIIconRegister.func_94245_a(JRMCoreH.tjdbcAssts + ":BlockCropMedMoss2");
        this.iIcon[6] = parIIconRegister.func_94245_a(JRMCoreH.tjdbcAssts + ":BlockCropMedMoss2");
        this.iIcon[7] = parIIconRegister.func_94245_a(JRMCoreH.tjdbcAssts + ":BlockCropMedMoss3");
    }
}

