/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 */
package JinRyuu.DragonBC.common.Render;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class MedPodDoor1TileEntity
extends TileEntity {
    private int cb = 0;

    public boolean canUpdate() {
        return true;
    }

    public void func_145841_b(NBTTagCompound par1) {
        super.func_145841_b(par1);
        par1.func_74768_a("cb", this.cb);
    }

    public void func_145839_a(NBTTagCompound par1) {
        super.func_145839_a(par1);
        this.cb = par1.func_74762_e("cb");
    }

    public int getCb() {
        return this.cb;
    }

    public void setCb(int cb) {
        this.cb = cb;
    }

    public boolean shouldRenderInPass(int pass) {
        return pass == 1;
    }

    public void func_145845_h() {
        this.updateSound();
        super.func_145845_h();
    }

    private void updateSound() {
        int meta = this.func_145832_p();
        if (meta > 3 && this.cb <= 20) {
            ++this.cb;
        }
        if (meta < 4 && this.cb > 0) {
            --this.cb;
        }
    }
}

