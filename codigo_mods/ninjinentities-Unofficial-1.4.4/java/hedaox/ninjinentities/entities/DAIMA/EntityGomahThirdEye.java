/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package hedaox.ninjinentities.entities.DAIMA;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hedaox.ninjinentities.entities.EntityDBCNinjin;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityGomahThirdEye
extends EntityDBCNinjin {
    public int randomSoundDelay = 0;

    public EntityGomahThirdEye(World par1World) {
        super(par1World, 100, EntityDBCNinjin.MindState.AGGRESSIVE, false, false, new byte[]{3, 3, 1, 6, 5, 2}, new byte[]{2, 2, 2, 2, 2, 7}, 255.0f, 217.0f, 25.0f);
        this.field_70728_aV = 80;
        this.func_70105_a(0.6f, 1.8f);
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(9000.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(900.0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "ninjinentities:textures/entity/GomahThirdEye.png";
    }
}

