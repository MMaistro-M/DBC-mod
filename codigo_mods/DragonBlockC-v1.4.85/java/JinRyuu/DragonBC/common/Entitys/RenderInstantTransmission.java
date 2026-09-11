/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 */
package JinRyuu.DragonBC.common.Entitys;

import JinRyuu.DragonBC.common.Npcs.RenderDBC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;

@SideOnly(value=Side.CLIENT)
public class RenderInstantTransmission
extends RenderDBC {
    public RenderInstantTransmission() {
        super(null, 0.0f);
    }

    public void func_76979_b(Entity p_76979_1_, double p_76979_2_, double p_76979_4_, double p_76979_6_, float p_76979_8_, float p_76979_9_) {
    }

    @Override
    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
    }
}

