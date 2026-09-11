/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Render;

import JinRyuu.DragonBC.common.Npcs.EntityDBC;
import JinRyuu.DragonBC.common.Npcs.EntitySaibaiman;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public abstract class DBCSpawn {
    public int spawnDelay = 100;
    private String mobID = "Saibaman";
    public double field_98287_c;
    public double field_98284_d = 0.0;
    private int minSpawnDelay = 200;
    private int maxSpawnDelay = 800;
    private int spawnCount = 1;
    private Entity field_98291_j;
    private int maxNearbyEntities = 1;
    private int activatingRangeFromPlayer = 8;
    private int spawnRange = 8;

    public String getEntityNameToSpawn() {
        return this.mobID;
    }

    public void setMobID(String par1Str) {
        this.mobID = par1Str;
    }

    public boolean canRun() {
        return this.getSpawnerWorld().func_72977_a((double)this.getSpawnerX() + 0.5, (double)this.getSpawnerY() + 0.5, (double)this.getSpawnerZ() + 0.5, (double)this.activatingRangeFromPlayer) != null;
    }

    public void updateSpawner() {
        int n = 4;
        AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)((double)this.getSpawnerX() - (double)n), (double)((double)this.getSpawnerY() - (double)n), (double)((double)this.getSpawnerZ() - (double)n), (double)((double)this.getSpawnerX() + (double)n), (double)((double)this.getSpawnerY() + (double)n), (double)((double)this.getSpawnerZ() + (double)n));
        int p = this.getSpawnerWorld().func_72872_a(EntityPlayer.class, aabb).size();
        if (this.canRun() && p != 0) {
            if (this.getSpawnerWorld().field_72995_K) {
                double d1 = (float)this.getSpawnerX() + this.getSpawnerWorld().field_73012_v.nextFloat();
                double d2 = (float)this.getSpawnerY() + this.getSpawnerWorld().field_73012_v.nextFloat();
                double d0 = (float)this.getSpawnerZ() + this.getSpawnerWorld().field_73012_v.nextFloat();
                if (this.spawnDelay > 0) {
                    --this.spawnDelay;
                }
                this.field_98284_d = this.field_98287_c;
                this.field_98287_c = (this.field_98287_c + (double)(1000.0f / ((float)this.spawnDelay + 200.0f))) % 360.0;
            } else {
                if (this.spawnDelay == -1) {
                    this.func_98273_j();
                }
                if (this.spawnDelay > 0) {
                    --this.spawnDelay;
                    return;
                }
                boolean flag = false;
                for (int i = 0; i < this.spawnCount; ++i) {
                    EntitySaibaiman entity = new EntitySaibaiman(this.getSpawnerWorld());
                    n = 16;
                    aabb = AxisAlignedBB.func_72330_a((double)((double)this.getSpawnerX() - (double)n), (double)((double)this.getSpawnerY() - (double)n), (double)((double)this.getSpawnerZ() - (double)n), (double)((double)this.getSpawnerX() + (double)n), (double)((double)this.getSpawnerY() + (double)n), (double)((double)this.getSpawnerZ() + (double)n));
                    int j = this.getSpawnerWorld().func_72872_a(EntityDBC.class, aabb).size();
                    if (j >= 1) {
                        this.func_98273_j();
                        return;
                    }
                    double d0 = (double)this.getSpawnerX() + (this.getSpawnerWorld().field_73012_v.nextDouble() - this.getSpawnerWorld().field_73012_v.nextDouble()) * (double)this.spawnRange;
                    double d3 = this.getSpawnerY() + this.getSpawnerWorld().field_73012_v.nextInt(3) - 1;
                    double d4 = (double)this.getSpawnerZ() + (this.getSpawnerWorld().field_73012_v.nextDouble() - this.getSpawnerWorld().field_73012_v.nextDouble()) * (double)this.spawnRange;
                    EntityLiving entityliving = entity instanceof EntityLiving ? (EntityLiving)entity : null;
                    entity.func_70012_b(d0, d3, d4, this.getSpawnerWorld().field_73012_v.nextFloat() * 360.0f, 0.0f);
                    if (entityliving != null && !entityliving.func_70601_bi()) continue;
                    this.func_98265_a((Entity)entity);
                    this.getSpawnerWorld().func_72926_e(2004, this.getSpawnerX(), this.getSpawnerY(), this.getSpawnerZ(), 0);
                    if (entityliving != null) {
                        entityliving.func_70656_aK();
                    }
                    flag = true;
                }
                if (flag) {
                    this.func_98273_j();
                }
            }
        }
    }

    public Entity func_98265_a(Entity par1Entity) {
        if (par1Entity instanceof EntityLiving && par1Entity.field_70170_p != null) {
            this.getSpawnerWorld().func_72838_d(par1Entity);
        }
        return par1Entity;
    }

    private void func_98273_j() {
        if (this.maxSpawnDelay <= this.minSpawnDelay) {
            this.spawnDelay = this.minSpawnDelay;
        } else {
            int i = this.maxSpawnDelay - this.minSpawnDelay;
            this.spawnDelay = this.minSpawnDelay + this.getSpawnerWorld().field_73012_v.nextInt(i);
        }
        this.func_98267_a(1);
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        this.mobID = par1NBTTagCompound.func_74779_i("EntityId");
        this.spawnDelay = par1NBTTagCompound.func_74765_d("Delay");
        if (par1NBTTagCompound.func_74764_b("MinSpawnDelay")) {
            this.minSpawnDelay = par1NBTTagCompound.func_74765_d("MinSpawnDelay");
            this.maxSpawnDelay = par1NBTTagCompound.func_74765_d("MaxSpawnDelay");
            this.spawnCount = par1NBTTagCompound.func_74765_d("SpawnCount");
        }
        if (par1NBTTagCompound.func_74764_b("MaxNearbyEntities")) {
            this.maxNearbyEntities = par1NBTTagCompound.func_74765_d("MaxNearbyEntities");
            this.activatingRangeFromPlayer = par1NBTTagCompound.func_74765_d("RequiredPlayerRange");
        }
        if (par1NBTTagCompound.func_74764_b("SpawnRange")) {
            this.spawnRange = par1NBTTagCompound.func_74765_d("SpawnRange");
        }
        if (this.getSpawnerWorld() != null && this.getSpawnerWorld().field_72995_K) {
            this.field_98291_j = null;
        }
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.func_74778_a("EntityId", this.getEntityNameToSpawn());
        par1NBTTagCompound.func_74777_a("Delay", (short)this.spawnDelay);
        par1NBTTagCompound.func_74777_a("MinSpawnDelay", (short)this.minSpawnDelay);
        par1NBTTagCompound.func_74777_a("MaxSpawnDelay", (short)this.maxSpawnDelay);
        par1NBTTagCompound.func_74777_a("SpawnCount", (short)this.spawnCount);
        par1NBTTagCompound.func_74777_a("MaxNearbyEntities", (short)this.maxNearbyEntities);
        par1NBTTagCompound.func_74777_a("RequiredPlayerRange", (short)this.activatingRangeFromPlayer);
        par1NBTTagCompound.func_74777_a("SpawnRange", (short)this.spawnRange);
    }

    public boolean setDelayToMin(int par1) {
        if (par1 == 1 && this.getSpawnerWorld().field_72995_K) {
            this.spawnDelay = this.minSpawnDelay;
            return true;
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public Entity func_98281_h() {
        if (this.field_98291_j == null) {
            Entity entity = EntityList.func_75620_a((String)this.getEntityNameToSpawn(), (World)null);
            this.field_98291_j = entity = this.func_98265_a(entity);
        }
        return this.field_98291_j;
    }

    public abstract void func_98267_a(int var1);

    public abstract World getSpawnerWorld();

    public abstract int getSpawnerX();

    public abstract int getSpawnerY();

    public abstract int getSpawnerZ();
}

