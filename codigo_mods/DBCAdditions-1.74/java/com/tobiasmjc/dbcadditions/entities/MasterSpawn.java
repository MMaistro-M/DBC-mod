/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package com.tobiasmjc.dbcadditions.entities;

import JinRyuu.DragonBC.common.Npcs.EntityDBCKami;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

class MasterSpawn<T extends EntityDBCKami> {
    private Class<T> masterClazz;
    private WorldServer world;
    private double x;
    private double y;
    private double z;
    private int safeZoneRadius = 50;
    private List<Block> bannedBlocks;

    public MasterSpawn(Class<T> clazz, WorldServer world, double x, double y, double z) {
        this.masterClazz = clazz;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public MasterSpawn(Class<T> clazz, WorldServer world, double x, double z, Block ... bannedBlock) {
        this.masterClazz = clazz;
        this.world = world;
        this.x = x;
        this.z = z;
        this.bannedBlocks = new ArrayList<Block>();
        for (Block block : bannedBlock) {
            this.bannedBlocks.add(block);
        }
        for (int y = world.func_72800_K(); y >= 0; --y) {
            Block block = world.func_147439_a((int)x, y, (int)z);
            if (block.func_149688_o() == Material.field_151579_a || this.bannedBlocks.contains(block)) continue;
            this.y = y + 1;
            break;
        }
    }

    public Class<T> getClazz() {
        return this.masterClazz;
    }

    public T newInstance() {
        try {
            EntityDBCKami obj = (EntityDBCKami)((Object)this.masterClazz.getDeclaredConstructor(World.class).newInstance(this.world));
            obj.safezoneRadiusXZ = this.safeZoneRadius;
            return (T)((Object)obj);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public MasterSpawn<T> setSafeZoneRadius(int radius) {
        this.safeZoneRadius = radius;
        return this;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public WorldServer getWorld() {
        return this.world;
    }

    public AxisAlignedBB getAABB() {
        return AxisAlignedBB.func_72330_a((double)(this.x - 2.0), (double)(this.y - 40.0), (double)(this.z - 2.0), (double)(this.x + 2.0), (double)(this.y + 40.0), (double)(this.z + 2.0));
    }
}

