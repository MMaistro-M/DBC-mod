/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 */
package JinRyuu.DragonBC.common.Npcs.aai;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.Npcs.EntityDBC;
import JinRyuu.JRMCore.entity.aai.AAi;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class AAiDBCTeleport
extends AAi {
    private int timer = 0;
    private int rateMin;
    private int rateMax;
    private int rate;
    private String sound;

    public AAiDBCTeleport(int[] values) {
        this(values[0], values[1], "jinryuudragonbc:DBC2.tp");
    }

    public AAiDBCTeleport(int[] values, String sound) {
        this(values[0], values[1], sound);
    }

    public AAiDBCTeleport(int rateMin, int rateMax, String sound) {
        this.rateMin = rateMin;
        this.rateMax = rateMax < 1 ? 1 : rateMax;
        this.rate = this.generateRate();
        this.sound = sound;
    }

    @Override
    public void update() {
        EntityDBC entity = (EntityDBC)this.aaiSystem.entity;
        boolean EnemyTeleportOutOfLock = DBCConfig.EnemyTeleportOutOfLock;
        if (!(!entity.func_70089_S() || entity.field_70170_p.field_72995_K || !EnemyTeleportOutOfLock && entity.isLocked() || entity.chargingKiAttack)) {
            ++this.timer;
            List list = entity.field_70170_p.func_72872_a(EntityPlayer.class, entity.field_70121_D.func_72314_b(16.0, 16.0, 16.0));
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); ++i) {
                    Entity target = (Entity)list.get(i);
                    entity.becomeAngryAt2(target);
                    if (this.timer < this.rate) continue;
                    this.rate = this.generateRate();
                    this.timer = 0;
                    entity.func_70634_a(target.field_70165_t, target.field_70163_u + 1.0, target.field_70161_v);
                    entity.field_70170_p.func_72956_a((Entity)entity, this.sound, 0.5f, entity.field_70170_p.field_73012_v.nextFloat() * 0.1f + 0.9f);
                }
            }
        }
    }

    public int generateRate() {
        return this.rateMin + new Random().nextInt(this.rateMax + 1);
    }
}

