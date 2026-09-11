/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package noppes.npcs.ai;

import java.util.Random;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import noppes.npcs.constants.AiMutex;

public class EntityAIFindShade
extends EntityAIBase {
    private EntityCreature theCreature;
    private double shelterX;
    private double shelterY;
    private double shelterZ;
    private World theWorld;

    public EntityAIFindShade(EntityCreature par1EntityCreature) {
        this.theCreature = par1EntityCreature;
        this.theWorld = par1EntityCreature.field_70170_p;
        this.func_75248_a(AiMutex.PASSIVE);
    }

    public boolean func_75250_a() {
        if (!this.theWorld.func_72935_r()) {
            return false;
        }
        if (!this.theWorld.func_72937_j(MathHelper.func_76128_c((double)this.theCreature.field_70165_t), (int)this.theCreature.field_70121_D.field_72338_b, MathHelper.func_76128_c((double)this.theCreature.field_70161_v))) {
            return false;
        }
        Vec3 var1 = this.findPossibleShelter();
        if (var1 == null) {
            return false;
        }
        this.shelterX = var1.field_72450_a;
        this.shelterY = var1.field_72448_b;
        this.shelterZ = var1.field_72449_c;
        return true;
    }

    public boolean func_75253_b() {
        return !this.theCreature.func_70661_as().func_75500_f();
    }

    public void func_75249_e() {
        this.theCreature.func_70661_as().func_75492_a(this.shelterX, this.shelterY, this.shelterZ, 1.0);
    }

    private Vec3 findPossibleShelter() {
        Random var1 = this.theCreature.func_70681_au();
        for (int var2 = 0; var2 < 10; ++var2) {
            int var3 = MathHelper.func_76128_c((double)(this.theCreature.field_70165_t + (double)var1.nextInt(20) - 10.0));
            int var4 = MathHelper.func_76128_c((double)(this.theCreature.field_70121_D.field_72338_b + (double)var1.nextInt(6) - 3.0));
            int var5 = MathHelper.func_76128_c((double)(this.theCreature.field_70161_v + (double)var1.nextInt(20) - 10.0));
            float light = this.theWorld.func_72801_o(var3, var4, var5) - 0.5f;
            if (this.theWorld.func_72937_j(var3, var4, var5) || !(light < 0.0f)) continue;
            return Vec3.func_72443_a((double)var3, (double)var4, (double)var5);
        }
        return null;
    }
}

