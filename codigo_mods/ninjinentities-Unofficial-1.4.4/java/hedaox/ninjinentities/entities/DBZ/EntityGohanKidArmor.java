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

public class EntityGohanKidArmor
extends EntityDBCNinjin {
    public int randomSoundDelay = 0;

    public EntityGohanKidArmor(World par1World) {
        super(par1World, 100, EntityDBCNinjin.MindState.NEUTRAL, true, false, new byte[]{3, 6, 1}, new byte[]{2, 2, 2});
        this.field_70728_aV = 80;
        this.func_70105_a(0.36f, 1.08f);
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(600.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(60.0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "ninjinentities:textures/entity/gohanKidArmor.png";
    }

    protected void func_70628_a(boolean par1, int par2) {
        int var4;
        int var3 = this.field_70146_Z.nextInt(2 + par2);
        for (var4 = 0; var4 < var3; ++var4) {
            this.func_145779_a(ItemsDBC.BattleArmorChest03, 1);
        }
        var3 = this.field_70146_Z.nextInt(2 + par2);
        for (var4 = 0; var4 < var3; ++var4) {
            this.func_145779_a(ItemsDBC.BattleArmorLegs05, 1);
        }
        var3 = this.field_70146_Z.nextInt(2 + par2);
        for (var4 = 0; var4 < var3; ++var4) {
            this.func_145779_a(ItemsDBC.BattleArmorBoots05, 1);
        }
    }
}

