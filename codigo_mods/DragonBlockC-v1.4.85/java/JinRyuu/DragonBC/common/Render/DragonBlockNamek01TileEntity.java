/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntityChest
 */
package JinRyuu.DragonBC.common.Render;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityChest;

public class DragonBlockNamek01TileEntity
extends TileEntityChest {
    private int cb = 100;
    private int cb2 = 100;

    public boolean canUpdate() {
        return true;
    }

    public void func_145841_b(NBTTagCompound par1) {
        super.func_145841_b(par1);
    }

    public void func_145839_a(NBTTagCompound par1) {
        super.func_145839_a(par1);
    }

    public boolean shouldRenderInPass(int pass) {
        return pass == 0;
    }

    public int getcb2() {
        return this.cb2;
    }

    public void func_145845_h() {
        this.updateSound();
        super.func_145845_h();
    }

    private void updateSound() {
        --this.cb;
        if (this.cb <= 0) {
            this.cb = 100;
            int par2 = this.field_145851_c;
            int par3 = this.field_145848_d;
            int par4 = this.field_145849_e;
            Block bi = this.field_145850_b.func_147439_a(par2, par3, par4);
            if (!this.field_145850_b.field_72995_K) {
                if (this.field_145850_b.func_147439_a(par2 + 1, par3, par4) == bi) {
                    if (this.field_145850_b.func_147439_a(par2 + 1, par3, par4 + 1) == bi && this.field_145850_b.func_147439_a(par2 + 1, par3, par4 - 1) == bi && this.field_145850_b.func_147439_a(par2 - 1, par3, par4) == bi && this.field_145850_b.func_147439_a(par2 - 1, par3, par4 - 1) == bi && this.field_145850_b.func_147439_a(par2 - 1, par3, par4 + 1) == bi) {
                        this.field_145850_b.func_72908_a((double)par2, (double)par3, (double)par4, "jinryuudragonbc:dragon.glow", 1.0f, 1.0f);
                    }
                } else if (this.field_145850_b.func_147439_a(par2, par3, par4 + 1) == bi && this.field_145850_b.func_147439_a(par2 + 1, par3, par4 + 1) == bi && this.field_145850_b.func_147439_a(par2 + 1, par3, par4 - 1) == bi && this.field_145850_b.func_147439_a(par2, par3, par4 - 1) == bi && this.field_145850_b.func_147439_a(par2 - 1, par3, par4 - 1) == bi && this.field_145850_b.func_147439_a(par2 - 1, par3, par4 + 1) == bi) {
                    this.field_145850_b.func_72908_a((double)par2, (double)par3, (double)par4, "jinryuudragonbc:dragon.glow", 1.0f, 1.0f);
                }
            }
        }
    }
}

