/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.EntityRenderer
 */
package JinRyuu.JBRA;

import JinRyuu.JRMCore.JRMCoreCliTicH;
import JinRyuu.JRMCore.JRMCoreH;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;

public class JBRAEnRen
extends EntityRenderer {
    private final Minecraft mc;
    private static double lastpos;

    public JBRAEnRen(Minecraft mc) {
        super(mc, mc.func_110442_L());
        this.mc = mc;
    }

    public void func_78471_a(float par1, long par2) {
        super.func_78471_a(par1, par2);
    }

    public void func_78480_b(float partialTick) {
        if (this.mc.field_71439_g == null || this.mc.field_71439_g.func_70608_bn()) {
            super.func_78480_b(partialTick);
            return;
        }
        if (this.mc.field_71439_g.func_70094_T()) {
            this.mc.field_71439_g.eyeHeight = this.mc.field_71439_g.getDefaultEyeHeight() + JRMCoreCliTicH.clientHght * 0.9f - 1.7f;
        }
        this.mc.field_71439_g.field_70129_M -= JRMCoreCliTicH.offsetY;
        super.func_78480_b(partialTick);
        if (this.mc.field_71439_g != null) {
            this.mc.field_71439_g.eyeHeight = this.mc.field_71439_g.getDefaultEyeHeight();
            this.mc.field_71439_g.field_70129_M = 1.62f;
        }
    }

    public void func_78463_b(double par1) {
        this.mc.field_71439_g.field_70129_M = 1.62f;
        if (JRMCoreH.NC() && this.mc.field_71415_G && JRMCoreH.State == 1 && JRMCoreH.Pwrtyp == 2 && JRMCoreH.Class == 1) {
            super.func_78463_b(par1);
            super.func_78483_a(par1);
        } else {
            super.func_78463_b(par1);
        }
    }

    public void func_78483_a(double par1) {
        super.func_78483_a(par1);
    }

    public void func_78473_a(float partialTick) {
        if (this.mc.field_71439_g == null || this.mc.field_71439_g.func_70608_bn()) {
            super.func_78473_a(partialTick);
            return;
        }
        this.mc.field_71439_g.field_70163_u += (double)JRMCoreCliTicH.offsetY;
        this.mc.field_71439_g.field_70167_r += (double)JRMCoreCliTicH.offsetY;
        this.mc.field_71439_g.field_70137_T += (double)JRMCoreCliTicH.offsetY;
        super.func_78473_a(partialTick);
        this.mc.field_71439_g.field_70163_u -= (double)JRMCoreCliTicH.offsetY;
        this.mc.field_71439_g.field_70167_r -= (double)JRMCoreCliTicH.offsetY;
        this.mc.field_71439_g.field_70137_T -= (double)JRMCoreCliTicH.offsetY;
    }
}

