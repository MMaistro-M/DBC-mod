/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 */
package kamkeel.npcs.client.renderer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kamkeel.npcs.client.renderer.RenderEnergy;
import kamkeel.npcs.entity.EntityEnergyBarrier;

@SideOnly(value=Side.CLIENT)
public abstract class RenderEnergyBarrier
extends RenderEnergy {
    protected float computeFlashAlpha(EntityEnergyBarrier barrier) {
        byte flash = barrier.getHitFlash();
        if (flash <= 0) {
            return 0.0f;
        }
        return (float)flash / 4.0f * 0.3f;
    }
}

