/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package hedaox.ninjinentities.entities.Movies;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hedaox.ninjinentities.entities.EntityDBCNinjin;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityBrolyZ2Ssj
extends EntityDBCNinjin {
    public int randomSoundDelay = 0;

    public EntityBrolyZ2Ssj(World par1World) {
        super(par1World, 15, EntityDBCNinjin.MindState.AGGRESSIVE, false, false, new byte[]{1, 3, 5, 6}, new byte[]{6, 6, 6, 6});
        this.field_70728_aV = 80;
        this.func_70105_a(0.66f, 1.98f);
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(11000.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1100.0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "ninjinentities:textures/entity/dbsbrolybuffssj.png";
    }
}

