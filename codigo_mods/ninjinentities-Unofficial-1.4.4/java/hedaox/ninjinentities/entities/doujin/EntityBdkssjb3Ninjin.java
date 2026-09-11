/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package hedaox.ninjinentities.entities.doujin;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hedaox.ninjinentities.entities.EntityDBCNinjin;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityBdkssjb3Ninjin
extends EntityDBCNinjin {
    public int randomSoundDelay = 0;

    public EntityBdkssjb3Ninjin(World par1World) {
        super(par1World, 85, EntityDBCNinjin.MindState.AGGRESSIVE, true, true, new byte[]{1, 3, 4, 5, 6}, new byte[]{2, 2, 2, 2, 2}, 50.0f, 255.0f, 255.0f, false, true, 50.0f, 200.0f, 255.0f);
        this.field_70728_aV = 80;
        this.func_70105_a(0.5f, 2.0f);
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(18500.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(1850.0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "ninjinentities:textures/entity/bdkssjb3.png";
    }
}

