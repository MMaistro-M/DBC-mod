/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 */
package noppes.npcs.ai;

import java.util.Random;
import net.minecraft.entity.EntityCreature;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class RandomPositionGeneratorAlt {
    private static Vec3 staticVector = Vec3.func_72443_a((double)0.0, (double)0.0, (double)0.0);

    public static Vec3 findRandomTarget(EntityCreature par0EntityCreature, int par1, int par2) {
        return RandomPositionGeneratorAlt.findRandomTargetBlock(par0EntityCreature, par1, par2, null);
    }

    public static Vec3 findRandomTargetBlockTowards(EntityCreature par0EntityCreature, int par1, int par2, Vec3 par3Vec3) {
        RandomPositionGeneratorAlt.staticVector.field_72450_a = par3Vec3.field_72450_a - par0EntityCreature.field_70165_t;
        RandomPositionGeneratorAlt.staticVector.field_72448_b = par3Vec3.field_72448_b - par0EntityCreature.field_70163_u;
        RandomPositionGeneratorAlt.staticVector.field_72449_c = par3Vec3.field_72449_c - par0EntityCreature.field_70161_v;
        return RandomPositionGeneratorAlt.findRandomTargetBlock(par0EntityCreature, par1, par2, staticVector);
    }

    public static Vec3 findRandomTargetBlockAwayFrom(EntityCreature par0EntityCreature, int par1, int par2, Vec3 par3Vec3) {
        RandomPositionGeneratorAlt.staticVector.field_72450_a = par0EntityCreature.field_70165_t - par3Vec3.field_72450_a;
        RandomPositionGeneratorAlt.staticVector.field_72448_b = par0EntityCreature.field_70163_u - par3Vec3.field_72448_b;
        RandomPositionGeneratorAlt.staticVector.field_72449_c = par0EntityCreature.field_70161_v - par3Vec3.field_72449_c;
        return RandomPositionGeneratorAlt.findRandomTargetBlock(par0EntityCreature, par1, par2, staticVector);
    }

    private static Vec3 findRandomTargetBlock(EntityCreature par0EntityCreature, int par1, int par2, Vec3 par3Vec3) {
        double d1;
        double d0;
        if (par1 <= 0) {
            par1 = 1;
        }
        if (par2 <= 0) {
            par2 = 1;
        }
        Random random = par0EntityCreature.func_70681_au();
        boolean flag = false;
        int k = 0;
        int l = 0;
        int i1 = 0;
        float f = -99999.0f;
        boolean flag1 = par0EntityCreature.func_110175_bO() ? (d0 = (double)(par0EntityCreature.func_110172_bL().func_71569_e(MathHelper.func_76128_c((double)par0EntityCreature.field_70165_t), MathHelper.func_76128_c((double)par0EntityCreature.field_70163_u), MathHelper.func_76128_c((double)par0EntityCreature.field_70161_v)) + 4.0f)) < (d1 = (double)(par0EntityCreature.func_110174_bM() + (float)par1)) * d1 : false;
        for (int l1 = 0; l1 < 10; ++l1) {
            float f1;
            int j1 = random.nextInt(2 * par1) - par1;
            int i2 = random.nextInt(2 * par2) - par2;
            int k1 = random.nextInt(2 * par1) - par1;
            if (par3Vec3 != null && !((double)j1 * par3Vec3.field_72450_a + (double)k1 * par3Vec3.field_72449_c >= 0.0)) continue;
            if (random.nextBoolean()) {
                j1 += MathHelper.func_76128_c((double)par0EntityCreature.field_70165_t);
                i2 += MathHelper.func_76128_c((double)par0EntityCreature.field_70163_u);
                k1 += MathHelper.func_76128_c((double)par0EntityCreature.field_70161_v);
            } else {
                j1 += MathHelper.func_76143_f((double)par0EntityCreature.field_70165_t);
                i2 += MathHelper.func_76143_f((double)par0EntityCreature.field_70163_u);
                k1 += MathHelper.func_76143_f((double)par0EntityCreature.field_70161_v);
            }
            if (flag1 && !par0EntityCreature.func_110176_b(j1, i2, k1) || !((f1 = par0EntityCreature.func_70783_a(j1, i2, k1)) > f)) continue;
            f = f1;
            k = j1;
            l = i2;
            i1 = k1;
            flag = true;
        }
        if (flag) {
            return Vec3.func_72443_a((double)k, (double)l, (double)i1);
        }
        return null;
    }
}

