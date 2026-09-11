/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package hedaox.ninjinentities.entities.DB;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hedaox.ninjinentities.entities.EntityDBCNinjin;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityRoshiBuff
extends EntityDBCNinjin {
    public int randomSoundDelay = 0;
    private int angerLevel = 0;

    public EntityRoshiBuff(World par1World) {
        super(par1World, 80, EntityDBCNinjin.MindState.AGGRESSIVE, true, false, new byte[]{3}, new byte[]{2});
        this.field_70728_aV = 80;
        this.func_70105_a(0.66f, 1.98f);
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(230.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(23.0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "ninjinentities:textures/entity/roshiBuff.png";
    }
}

