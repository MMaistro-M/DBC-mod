/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package hedaox.ninjinentities.entities.doujin;

import JinRyuu.DragonBC.common.Items.ItemsDBC;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hedaox.ninjinentities.entities.DBZ.EntityGokuSsj3;
import hedaox.ninjinentities.entities.EntityAura;
import hedaox.ninjinentities.entities.EntityDBCNinjin;
import hedaox.ninjinentities.lib.Colors;
import hedaox.ninjinentities.lib.Util;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityGogetaSsj3
extends EntityDBCNinjin {
    public int randomSoundDelay = 0;

    public EntityGogetaSsj3(World par1World) {
        super(par1World, 85, EntityDBCNinjin.MindState.AGGRESSIVE, true, true, new byte[]{1, 3, 4, 5, 6}, new byte[]{2, 2, 2, 2, 2}, 255.0f, 217.0f, 25.0f, true);
        this.field_70728_aV = 80;
        this.func_70105_a(0.6f, 1.8f);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_70636_d() {
        if (this.field_70170_p.field_72995_K && JGConfigClientSettings.CLIENT_DA8) {
            for (int k = 0; k < JGConfigClientSettings.get_da1(); ++k) {
                for (int i = 0; i < 4; ++i) {
                    EntityGogetaSsj3 pl = this;
                    EntityAura aura = new EntityAura(this.field_70170_p, (Entity)pl, 16767232, 1.0f, 0.0f, 0, false);
                    int clrx = Colors.getRGB(255, 217, 0);
                    aura.setSpd(30);
                    aura.setAlp(0.285f);
                    aura.setCol(clrx);
                    aura.setColL2(16767232);
                    aura.setBolLighting(true);
                    aura.setLightCol(6251715);
                    List players = ((Entity)pl).field_70170_p.func_72872_a(EntityPlayer.class, ((Entity)pl).field_70121_D.func_72314_b(15.0, 15.0, 15.0));
                    for (EntityPlayer p : players) {
                        if (p.func_70005_c_().equals(Util.mc.field_71439_g.func_70005_c_())) {
                            if (EntityGokuSsj3.serverTickx >= 10) {
                                aura.field_70170_p.func_72838_d((Entity)aura);
                                EntityGokuSsj3.serverTickx = 0;
                                continue;
                            }
                            ++EntityGokuSsj3.serverTickx;
                            continue;
                        }
                        this.func_70106_y();
                    }
                }
            }
        }
        super.func_70636_d();
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(90000.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(9000.0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "ninjinentities:textures/entity/gogetaSsj3.png";
    }

    @Override
    public boolean func_70601_bi() {
        return this.field_70170_p.func_72855_b(this.field_70121_D) && this.field_70170_p.func_72945_a((Entity)this, this.field_70121_D).isEmpty() && !this.field_70170_p.func_72953_d(this.field_70121_D);
    }

    protected void func_70628_a(boolean par1, int par2) {
        int var4;
        int var3 = this.field_70146_Z.nextInt(2 + par2);
        for (var4 = 0; var4 < var3; ++var4) {
            this.func_145779_a(ItemsDBC.ItemsOutfit1[17], 1);
        }
        var3 = this.field_70146_Z.nextInt(2 + par2);
        for (var4 = 0; var4 < var3; ++var4) {
            this.func_145779_a(ItemsDBC.ItemsOutfit2[17], 1);
        }
        var3 = this.field_70146_Z.nextInt(2 + par2);
        for (var4 = 0; var4 < var3; ++var4) {
            this.func_145779_a(ItemsDBC.ItemsOutfit3[17], 1);
        }
    }
}

