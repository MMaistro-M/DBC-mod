/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.client.JGRenderHelper;
import JinRyuu.JRMCore.entity.ModelNPCNormal;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelNPCNormalScaled
extends ModelNPCNormal {
    @Override
    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        GL11.glPushMatrix();
        float F = par1Entity.field_70131_O / 2.0f;
        JGRenderHelper.modelScalePositionHelper(F);
        super.func_78088_a(par1Entity, par2, par3, par4, par5, par6, par7);
        GL11.glPopMatrix();
    }
}

