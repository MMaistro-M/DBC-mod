/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs.dbredribbon;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityRedRibbon2;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityMajorMetallitron3
extends EntityRedRibbon2 {
    public final int AttPow = 50;
    public final int HePo = 200;

    public EntityMajorMetallitron3(World world) {
        super(world);
        this.func_70105_a(1.2f, 5.0f);
        this.texture = "major_metallitron3";
        this.setAttributes(DBCConfig.RRMajorDAM, DBCConfig.RRMajorHP, 50, 200);
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(200.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(50.0);
    }
}

