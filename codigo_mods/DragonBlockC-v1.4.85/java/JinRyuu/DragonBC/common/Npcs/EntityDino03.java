/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.monster.IMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.Items.ItemsDBC;
import JinRyuu.DragonBC.common.Npcs.EntityDBCWildlifeA;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityDino03
extends EntityDBCWildlifeA
implements IMob {
    public int randomSoundDelay = 0;
    public final int AttPow = 40;
    public final int HePo = 500;

    public EntityDino03(World par1World) {
        super(par1World);
        this.field_70728_aV = 50;
        this.field_70129_M *= 4.0f;
        this.func_70105_a(3.0f, 2.0f);
        if (DBCConfig.NPC_Dino3_Dam != 40 || DBCConfig.NPC_Dino3_HP != 500) {
            this.getEntityData().func_74780_a("jrmcSpawnInitiatedCAT", (double)DBCConfig.NPC_Dino3_Dam);
            this.getEntityData().func_74780_a("jrmcSpawnInitiatedCHP", (double)DBCConfig.NPC_Dino3_HP);
        }
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(500.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(40.0);
    }

    @Override
    public void func_70071_h_() {
        if (this.randomSoundDelay <= 0 || --this.randomSoundDelay == 0) {
            // empty if block
        }
        super.func_70071_h_();
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "jinryuudragonbc:npcs/dino03.png";
    }

    @Override
    public boolean func_70601_bi() {
        return this.field_70170_p.func_72855_b(this.field_70121_D) && this.field_70170_p.func_72945_a((Entity)this, this.field_70121_D).isEmpty() && !this.field_70170_p.func_72953_d(this.field_70121_D);
    }

    @Override
    protected Entity func_70782_k() {
        return super.func_70782_k();
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
    }

    protected void func_70628_a(boolean par1, int par2) {
        this.func_145779_a(ItemsDBC.ItemDinoMeat, 1);
    }

    public boolean func_70085_c(EntityPlayer par1EntityPlayer) {
        return false;
    }

    @Override
    protected boolean func_70692_ba() {
        return true;
    }
}

