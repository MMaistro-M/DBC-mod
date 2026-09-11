/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.WorldServer
 */
package com.tobiasmjc.dbcadditions.entities;

import JinRyuu.DragonBC.common.Npcs.EntityDBCKami;
import JinRyuu.DragonBC.common.Npcs.EntityMasterWhis;
import com.tobiasmjc.dbcadditions.DBCAConfig;
import com.tobiasmjc.dbcadditions.entities.MasterBeerus;
import com.tobiasmjc.dbcadditions.entities.MasterKibito;
import com.tobiasmjc.dbcadditions.entities.MasterOldKai;
import com.tobiasmjc.dbcadditions.entities.MasterSpawn;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class DBASpawnCheck {
    public static final int EVERY_X_TICKS = 1000;
    private static int timer;
    private static List<MasterSpawn<? extends EntityDBCKami>> MASTER_SPAWNS;

    public static void initMasters() {
        MinecraftServer server = MinecraftServer.func_71276_C();
        MASTER_SPAWNS.add(new MasterSpawn<MasterKibito>(MasterKibito.class, server.func_71218_a(0), 69.0, 217.0, 49.0).setSafeZoneRadius(0));
        MASTER_SPAWNS.add(new MasterSpawn<MasterKibito>(MasterKibito.class, server.func_71218_a(98), 69.0, 49.0, new Block[0]));
        MASTER_SPAWNS.add(new MasterSpawn<MasterOldKai>(MasterOldKai.class, server.func_71218_a(98), 60.0, 49.0, new Block[0]));
        MASTER_SPAWNS.add(new MasterSpawn<EntityMasterWhis>(EntityMasterWhis.class, server.func_71218_a(99), 7.0, -7.0, new Block[]{Blocks.field_150364_r, Blocks.field_150361_u}).setSafeZoneRadius(5));
        MASTER_SPAWNS.add(new MasterSpawn<EntityMasterWhis>(EntityMasterWhis.class, server.func_71218_a(100), 0.0, 75.0, -41.5).setSafeZoneRadius(0));
        MASTER_SPAWNS.add(new MasterSpawn<MasterBeerus>(MasterBeerus.class, server.func_71218_a(99), 7.0, 0.0, new Block[]{Blocks.field_150364_r, Blocks.field_150361_u}).setSafeZoneRadius(5));
    }

    public static void spawnCheck() {
        if (!DBCAConfig.SpawnCheck) {
            return;
        }
        if (++timer % 500 == 0) {
            for (MasterSpawn<? extends EntityDBCKami> spawn : MASTER_SPAWNS) {
                WorldServer world = spawn.getWorld();
                if (!world.func_72872_a(spawn.getClazz(), spawn.getAABB()).isEmpty()) continue;
                EntityDBCKami master = spawn.newInstance();
                master.func_70107_b(spawn.getX(), spawn.getY(), spawn.getZ());
                world.func_72838_d((Entity)master);
            }
        }
    }

    static {
        MASTER_SPAWNS = new ArrayList<MasterSpawn<? extends EntityDBCKami>>();
    }
}

