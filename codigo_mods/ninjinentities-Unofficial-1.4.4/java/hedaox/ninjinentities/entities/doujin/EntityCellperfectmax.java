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

public class EntityCellperfectmax
extends EntityDBCNinjin {
    public int randomSoundDelay = 0;

    public EntityCellperfectmax(World par1World) {
        super(par1World, 85, EntityDBCNinjin.MindState.AGGRESSIVE, false, true, new byte[]{1, 2, 3, 4, 5, 6}, new byte[]{5, 5, 3, 3, 5, 5});
        this.field_70728_aV = 80;
        this.func_70105_a(1.0f, 1.2f);
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(3.0E7);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(300000.0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "ninjinentities:textures/entity/cellperfectmax.png";
    }
}

