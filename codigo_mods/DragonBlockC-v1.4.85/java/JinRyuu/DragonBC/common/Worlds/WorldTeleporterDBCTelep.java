/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.Teleporter
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package JinRyuu.DragonBC.common.Worlds;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class WorldTeleporterDBCTelep
extends Teleporter {
    private Random random;
    private WorldServer worldServerInstance;

    public WorldTeleporterDBCTelep(WorldServer par1WorldServer) {
        super(par1WorldServer);
        this.worldServerInstance = par1WorldServer;
        this.random = new Random();
    }

    public void func_77185_a(Entity entity, double par2, double par4, double par6, float par8) {
        int i = MathHelper.func_76128_c((double)(entity.field_70165_t + 1.0));
        int j = MathHelper.func_76128_c((double)entity.field_70163_u);
        int k = MathHelper.func_76128_c((double)entity.field_70161_v);
        int k1 = i;
        int l1 = j;
        int i2 = k;
        for (l1 = 250; l1 > 5; --l1) {
            if (this.worldServerInstance.func_147439_a(k1, l1, i2) == Blocks.field_150350_a) continue;
            this.placeInExistingPortal((World)this.worldServerInstance, entity, k1, l1, i2);
            return;
        }
    }

    public boolean placeInExistingPortal(World world, Entity entity, int i, int j, int k) {
        int k1 = i;
        int l1 = j;
        int i2 = k;
        double d2 = (double)k1 + 0.5;
        double d4 = (double)l1 + 0.5 + 4.0;
        double d6 = (double)i2 + 0.5;
        entity.func_70012_b(d2 + 0.0, d4 + 2.0, d6 + 0.0, entity.field_70177_z, 0.0f);
        entity.field_70179_y = 0.0;
        entity.field_70181_x = 0.0;
        entity.field_70159_w = 0.0;
        d2 -= 3.0;
        d6 -= 3.0;
        return true;
    }
}

