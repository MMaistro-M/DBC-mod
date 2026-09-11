/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package hedaox.ninjinentities.entities.DBZ;

import JinRyuu.DragonBC.common.Items.ItemsDBC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hedaox.ninjinentities.entities.EntityDBCNinjin;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityNappa
extends EntityDBCNinjin {
    public int randomSoundDelay = 0;

    public EntityNappa(World par1World) {
        super(par1World, 3, EntityDBCNinjin.MindState.AGGRESSIVE, false, false, new byte[]{3, 5}, new byte[]{7, 7});
        this.field_70728_aV = 80;
        this.func_70105_a(0.675f, 1.98f);
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(350.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(35.0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "ninjinentities:textures/entity/nappa.png";
    }

    protected void func_70628_a(boolean par1, int par2) {
        int k;
        int j = this.field_70146_Z.nextInt(2 + par2);
        for (k = 0; k < j; ++k) {
            this.func_145779_a(ItemsDBC.BattleArmorChest02, 1);
        }
        j = this.field_70146_Z.nextInt(2 + par2);
        for (k = 0; k < j; ++k) {
            this.func_145779_a(ItemsDBC.BattleArmorLegs02, 1);
        }
        j = this.field_70146_Z.nextInt(2 + par2);
        for (k = 0; k < j; ++k) {
            this.func_145779_a(ItemsDBC.BattleArmorBoots02, 1);
        }
        j = this.field_70146_Z.nextInt(2) + this.field_70146_Z.nextInt(1 + par2);
        for (k = 0; k < j; ++k) {
            this.func_145779_a(ItemsDBC.BattleArmorHelmet03, 1);
        }
    }
}

