/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package hedaox.ninjinentities.entities.DBGT;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hedaox.ninjinentities.entities.EntityDBCNinjin;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityVegetaSsj4
extends EntityDBCNinjin {
    public int randomSoundDelay = 0;

    public EntityVegetaSsj4(World par1World) {
        super(par1World, 70, EntityDBCNinjin.MindState.AGGRESSIVE, true, false, new byte[]{3, 6, 3, 5}, new byte[]{3, 7, 7, 7});
        this.field_70728_aV = 80;
        this.func_70105_a(0.6f, 2.2f);
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(300000.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(30000.0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "ninjinentities:textures/entity/vegetaSsj4.png";
    }
}

