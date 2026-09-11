/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package JinRyuu.DragonBC.common.Worlds;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.Npcs.EntityFreezaSoldier1;
import JinRyuu.DragonBC.common.Npcs.EntityFreezaSoldier2;
import JinRyuu.DragonBC.common.Npcs.EntityFreezaSoldier3;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenFreezaSoldiers
extends WorldGenerator {
    public boolean func_76484_a(World par1World, Random par2Random, int par3, int par4, int par5) {
        for (int var6 = 0; var6 < 1; ++var6) {
            int var9;
            int var8;
            int var7 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
            if (!par1World.func_147437_c(var7, var8 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4), var9 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8)) || par1World.func_147439_a(var7, var8 - 1, var9) != BlocksDBC.BlockNamekGrass) continue;
            EntityFreezaSoldier1 Soldier1 = new EntityFreezaSoldier1(par1World);
            EntityFreezaSoldier2 Soldier2 = new EntityFreezaSoldier2(par1World);
            EntityFreezaSoldier3 Soldier3 = new EntityFreezaSoldier3(par1World);
            Soldier1.func_70012_b(var7, (double)var8 + 3.0, var9, 0.0f, 0.0f);
            Soldier2.func_70012_b(var7, (double)var8 + 3.0, (double)var9 + 1.0, 0.0f, 0.0f);
            Soldier3.func_70012_b((double)var7 + 1.0, (double)var8 + 3.0, var9, 0.0f, 0.0f);
            Soldier1.setSpwner((Entity)Soldier1);
            Soldier2.setSpwner((Entity)Soldier2);
            Soldier3.setSpwner((Entity)Soldier3);
            par1World.func_72838_d((Entity)Soldier1);
            par1World.func_72838_d((Entity)Soldier2);
            par1World.func_72838_d((Entity)Soldier3);
        }
        return true;
    }
}

