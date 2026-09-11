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
package hedaox.ninjinentities.entities.ZENO;

import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hedaox.ninjinentities.entities.DBZ.EntityGokuSsj3;
import hedaox.ninjinentities.entities.EntityAura;
import hedaox.ninjinentities.entities.EntityDBCNinjin;
import hedaox.ninjinentities.lib.Util;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityBlockGokuSsjR3
extends EntityDBCNinjin {
    public int randomSoundDelay = 0;

    public EntityBlockGokuSsjR3(World par1World) {
        super(par1World, 3, EntityDBCNinjin.MindState.AGGRESSIVE, false, true, new byte[]{3, 1, 5}, new byte[]{3, 3, 3}, 255.0f, 50.0f, 255.0f, false, true, 255.0f, 50.0f, 200.0f);
        this.field_70728_aV = 80;
        this.angerLevel = 500;
        this.func_70105_a(0.6f, 1.8f);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_70636_d() {
        if (this.field_70170_p.field_72995_K && JGConfigClientSettings.CLIENT_DA8) {
            for (int k = 0; k < JGConfigClientSettings.get_da1(); ++k) {
                for (int i = 0; i < 4; ++i) {
                    EntityBlockGokuSsjR3 entityGokuSsjR3 = this;
                    EntityAura EntityAura2 = new EntityAura(this.field_70170_p, (Entity)entityGokuSsjR3, 0xFF32FF, 1.0f, 0.0f, 0, false);
                    EntityAura2.setBolLighting(true);
                    EntityAura2.setLightCol(0xFF32FF);
                    List players = entityGokuSsjR3.field_70170_p.func_72872_a(EntityPlayer.class, entityGokuSsjR3.field_70121_D.func_72314_b(15.0, 15.0, 15.0));
                    for (EntityPlayer p : players) {
                        if (p.func_70005_c_().equals(Util.mc.field_71439_g.func_70005_c_())) {
                            if (EntityGokuSsj3.serverTickx >= 13) {
                                EntityAura2.field_70170_p.func_72838_d((Entity)EntityAura2);
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
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(200000.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(10000.0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "ninjinentities:textures/entity/BlockgokuSSJR3.png";
    }
}

