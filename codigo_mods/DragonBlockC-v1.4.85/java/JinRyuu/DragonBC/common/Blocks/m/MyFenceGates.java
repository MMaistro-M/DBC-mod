/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.BlockFenceGate
 *  net.minecraft.util.IIcon
 */
package JinRyuu.DragonBC.common.Blocks.m;

import JinRyuu.DragonBC.common.Blocks.m.ModdedBlocks;
import JinRyuu.DragonBC.common.mod_DragonBC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.util.IIcon;

public class MyFenceGates
extends BlockFenceGate {
    private int iconSourcePlankMeta;

    public MyFenceGates(int iconSourcePlankMeta) {
        this.iconSourcePlankMeta = iconSourcePlankMeta;
        this.func_149647_a(mod_DragonBC.DragonBlockC);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
        return ModdedBlocks.SakuraPlank.func_149691_a(p_149691_1_, this.iconSourcePlankMeta);
    }

    public boolean func_149686_d() {
        return false;
    }
}

