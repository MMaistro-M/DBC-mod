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

import JinRyuu.DragonBC.common.Items.ItemsDBC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hedaox.ninjinentities.entities.EntityDBCNinjin;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityJanembaSuper
extends EntityDBCNinjin {
    public int randomSoundDelay = 0;

    public EntityJanembaSuper(World par1World) {
        super(par1World, 0, EntityDBCNinjin.MindState.AGGRESSIVE, false, true, new byte[]{1, 3}, new byte[]{3, 2});
        this.field_70728_aV = 80;
        this.func_70105_a(0.66f, 1.98f);
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(40000.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4000.0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "ninjinentities:textures/entity/janembaSuper.png";
    }

    protected void func_70628_a(boolean par1, int par2) {
        int var3 = this.field_70146_Z.nextInt(2 + par2);
        for (int var4 = 0; var4 < var3; ++var4) {
            this.func_145779_a(ItemsDBC.ItemJanembaEssence, 1);
        }
    }
}

