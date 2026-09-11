/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  cpw.mods.fml.common.eventhandler.Event$Result
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.ChunkCoordIntPair
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraftforge.event.ForgeEventFactory
 */
package noppes.npcs;

import com.google.common.collect.Sets;
import cpw.mods.fml.common.eventhandler.Event;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.ForgeEventFactory;
import noppes.npcs.EventHooks;
import noppes.npcs.controllers.SpawnController;
import noppes.npcs.controllers.data.SpawnData;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.event.CustomNPCsEvent;

public class NPCSpawning {
    private static Set<ChunkCoordIntPair> eligibleChunksForSpawning = Sets.newHashSet();
    private static final byte CHUNK_SPAWN_RADIUS = 7;
    private static final int SPAWN_ATTEMPTS_PER_CHUNK = 3;
    private static final int NATURAL_CAP_PER_256_CHUNKS = 70;
    private static final long RUNTIME_SPAWN_TICK_INTERVAL = 20L;
    private static final int RUNTIME_SPAWN_CYCLE_TICKS = 400;
    private static final String NATURAL_SPAWN_ID_TAG = "CNPCNaturalSpawnId";
    private static final String NATURAL_SPAWN_NAME_TAG = "CNPCNaturalSpawnName";
    private static final String NATURAL_SPAWN_TIME_TAG = "CNPCNaturalSpawnTime";
    private static final Map<Long, Long> SPAWN_COOLDOWNS = new HashMap<Long, Long>();
    private static final Map<Integer, RuntimeChunkState> RUNTIME_CHUNK_STATES = new HashMap<Integer, RuntimeChunkState>();
    private static boolean animalSpawn;
    private static boolean monsterSpawn;
    private static boolean airSpawn;
    private static boolean liquidSpawn;

    public static void findChunksForSpawning(WorldServer world) {
        if (SpawnController.Instance.data.isEmpty() || world.func_72912_H().func_82573_f() % 20L != 0L) {
            return;
        }
        eligibleChunksForSpawning.clear();
        for (int i = 0; i < world.field_73010_i.size(); ++i) {
            EntityPlayer entityplayer = (EntityPlayer)world.field_73010_i.get(i);
            int j = MathHelper.func_76128_c((double)(entityplayer.field_70165_t / 16.0));
            int k = MathHelper.func_76128_c((double)(entityplayer.field_70161_v / 16.0));
            int size = 7;
            for (int x = -size; x <= size; ++x) {
                for (int z = -size; z <= size; ++z) {
                    ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(x + j, z + k);
                    if (eligibleChunksForSpawning.contains(chunkcoordintpair)) continue;
                    eligibleChunksForSpawning.add(chunkcoordintpair);
                }
            }
        }
        int cap = NPCSpawning.getScaledNaturalCap(eligibleChunksForSpawning.size());
        int npcCount = NPCSpawning.countNaturalSpawnedNPCs((World)world);
        if (npcCount >= cap) {
            return;
        }
        HashMap<Integer, Integer> spawnEntryCounts = NPCSpawning.getNaturalSpawnEntryCounts((World)world);
        int chunkBudget = NPCSpawning.getRuntimeChunkBudget(eligibleChunksForSpawning.size());
        List<ChunkCoordIntPair> chunksToProcess = NPCSpawning.getRuntimeChunkBatch((World)world, chunkBudget);
        for (ChunkCoordIntPair chunkcoordintpair1 : chunksToProcess) {
            if (npcCount >= cap) {
                return;
            }
            ChunkPosition chunkposition = NPCSpawning.getChunk((World)world, chunkcoordintpair1.field_77276_a, chunkcoordintpair1.field_77275_b);
            int j1 = chunkposition.field_151329_a;
            int y = chunkposition.field_151327_b;
            int l1 = chunkposition.field_151328_c;
            for (int i = 0; i < 3; ++i) {
                if (npcCount >= cap) {
                    return;
                }
                int x = j1;
                int z = l1;
                int b1 = 6;
                String name = world.func_72807_a((int)(x += world.field_73012_v.nextInt((int)b1) - world.field_73012_v.nextInt((int)b1)), (int)(z += world.field_73012_v.nextInt((int)b1) - world.field_73012_v.nextInt((int)b1))).field_76791_y;
                SpawnData data = SpawnController.Instance.getRandomSpawnData(name, world.field_73011_w.field_76574_g);
                if (data == null || !NPCSpawning.trySpawnWithEntryConfig(data, (World)world, x, y, z, world.field_73012_v, spawnEntryCounts)) continue;
                ++npcCount;
            }
        }
    }

    private static int getRuntimeChunkBudget(int eligibleChunkCount) {
        if (eligibleChunkCount <= 0) {
            return 0;
        }
        int runsPerCycle = Math.max(1, 20);
        return Math.max(1, (int)Math.ceil((double)eligibleChunkCount / (double)runsPerCycle));
    }

    private static List<ChunkCoordIntPair> getRuntimeChunkBatch(World world, int chunkBudget) {
        ArrayList<ChunkCoordIntPair> result = new ArrayList<ChunkCoordIntPair>();
        if (chunkBudget <= 0 || eligibleChunksForSpawning.isEmpty()) {
            return result;
        }
        int dimensionId = world.field_73011_w.field_76574_g;
        RuntimeChunkState state = RUNTIME_CHUNK_STATES.get(dimensionId);
        if (state == null) {
            state = new RuntimeChunkState();
            RUNTIME_CHUNK_STATES.put(dimensionId, state);
        }
        int signature = eligibleChunksForSpawning.hashCode();
        if (state.chunks.isEmpty() || state.signature != signature || state.index >= state.chunks.size()) {
            state.chunks = new ArrayList<ChunkCoordIntPair>(eligibleChunksForSpawning);
            Collections.shuffle(state.chunks);
            state.index = 0;
            state.signature = signature;
        }
        int remaining = state.chunks.size() - state.index;
        int count = Math.min(chunkBudget, Math.max(0, remaining));
        for (int i = 0; i < count; ++i) {
            result.add(state.chunks.get(state.index++));
        }
        return result;
    }

    private static int countNaturalSpawnedNPCs(World world) {
        int count = 0;
        List list = world.field_72996_f;
        for (Entity entity : list) {
            if (!(entity instanceof EntityNPCInterface) || entity.field_70128_L || !entity.getEntityData().func_74764_b(NATURAL_SPAWN_ID_TAG)) continue;
            ++count;
        }
        return count;
    }

    private static int getScaledNaturalCap(int eligibleChunkCount) {
        if (eligibleChunkCount <= 0) {
            return 0;
        }
        return Math.max(1, 70 * eligibleChunkCount / 256);
    }

    protected static ChunkPosition getChunk(World world, int x, int z) {
        Chunk chunk = world.func_72964_e(x, z);
        int k = x * 16 + world.field_73012_v.nextInt(16);
        int l = z * 16 + world.field_73012_v.nextInt(16);
        int i1 = world.field_73012_v.nextInt(chunk == null ? world.func_72940_L() : chunk.func_76625_h() + 16 - 1);
        return new ChunkPosition(k, i1, l);
    }

    private static int getCap(World world) {
        HashSet chunkSet = Sets.newHashSet();
        for (int i = 0; i < world.field_73010_i.size(); ++i) {
            EntityPlayer entityplayer = (EntityPlayer)world.field_73010_i.get(i);
            int j = MathHelper.func_76128_c((double)(entityplayer.field_70165_t / 16.0));
            int k = MathHelper.func_76128_c((double)(entityplayer.field_70161_v / 16.0));
            int size = 7;
            for (int x = -size; x <= size; ++x) {
                for (int z = -size; z <= size; ++z) {
                    chunkSet.add(new ChunkCoordIntPair(x + j, z + k));
                }
            }
        }
        return NPCSpawning.getScaledNaturalCap(chunkSet.size());
    }

    private static HashMap<Integer, Integer> getNaturalSpawnEntryCounts(World world) {
        HashMap<Integer, Integer> counts = new HashMap<Integer, Integer>();
        List list = world.field_72996_f;
        for (Entity entity : list) {
            NBTTagCompound entityData;
            if (entity == null || entity.field_70128_L || !(entity instanceof EntityNPCInterface) || !(entityData = entity.getEntityData()).func_74764_b(NATURAL_SPAWN_ID_TAG)) continue;
            int spawnId = entityData.func_74762_e(NATURAL_SPAWN_ID_TAG);
            Integer count = counts.get(spawnId);
            counts.put(spawnId, count == null ? 1 : count + 1);
        }
        return counts;
    }

    private static long getSpawnCooldownKey(World world, int spawnId) {
        return (long)world.field_73011_w.field_76574_g << 32 ^ (long)spawnId & 0xFFFFFFFFL;
    }

    private static boolean isSpawnOnCooldown(World world, SpawnData data) {
        if (data.cooldownTicks <= 0 || data.id < 0) {
            return false;
        }
        long key = NPCSpawning.getSpawnCooldownKey(world, data.id);
        Long lastSpawnTick = SPAWN_COOLDOWNS.get(key);
        if (lastSpawnTick == null) {
            return false;
        }
        long tickDelta = world.func_72912_H().func_82573_f() - lastSpawnTick;
        if (tickDelta < 0L) {
            SPAWN_COOLDOWNS.remove(key);
            return false;
        }
        return tickDelta < (long)data.cooldownTicks;
    }

    private static void markSpawnCooldown(World world, SpawnData data) {
        if (data.cooldownTicks <= 0 || data.id < 0) {
            return;
        }
        long key = NPCSpawning.getSpawnCooldownKey(world, data.id);
        SPAWN_COOLDOWNS.put(key, world.func_72912_H().func_82573_f());
    }

    private static boolean isSpawnEntryAtCapacity(SpawnData data, Map<Integer, Integer> spawnEntryCounts) {
        if (data.maxAlive <= 0 || data.id < 0) {
            return false;
        }
        Integer currentCount = spawnEntryCounts.get(data.id);
        return currentCount != null && currentCount >= data.maxAlive;
    }

    private static void incrementSpawnEntryCount(SpawnData data, Map<Integer, Integer> spawnEntryCounts) {
        if (data.id < 0) {
            return;
        }
        Integer currentCount = spawnEntryCounts.get(data.id);
        spawnEntryCounts.put(data.id, currentCount == null ? 1 : currentCount + 1);
    }

    private static boolean hasNearbyPlayer(SpawnData data, World world, int x, int y, int z) {
        if (data.playerMinDistance <= 0) {
            return false;
        }
        return world.func_72977_a((double)x, (double)y, (double)z, (double)data.playerMinDistance) != null;
    }

    private static boolean trySpawnWithEntryConfig(SpawnData data, World world, int x, int y, int z, Random rand, Map<Integer, Integer> spawnEntryCounts) {
        if (y < data.spawnHeightMin || y > data.spawnHeightMax) {
            return false;
        }
        if (NPCSpawning.isSpawnEntryAtCapacity(data, spawnEntryCounts) || NPCSpawning.isSpawnOnCooldown(world, data)) {
            return false;
        }
        int attempts = Math.max(1, data.attemptsPerCycle);
        for (int attempt = 0; attempt < attempts; ++attempt) {
            int attemptX = x;
            int attemptZ = z;
            if (attempt > 0) {
                attemptX += rand.nextInt(5) - rand.nextInt(5);
                attemptZ += rand.nextInt(5) - rand.nextInt(5);
            }
            if (!NPCSpawning.canCreatureTypeSpawnAtLocation(data, world, attemptX, y, attemptZ) || NPCSpawning.hasNearbyPlayer(data, world, attemptX, y, attemptZ) || !NPCSpawning.spawnData(data, world, attemptX, y, attemptZ)) continue;
            NPCSpawning.incrementSpawnEntryCount(data, spawnEntryCounts);
            NPCSpawning.markSpawnCooldown(world, data);
            return true;
        }
        return false;
    }

    public static void performWorldGenSpawning(World world, int x, int z, Random rand) {
        int cap = NPCSpawning.getCap(world);
        int npcCount = NPCSpawning.countNaturalSpawnedNPCs(world);
        if (npcCount >= cap) {
            return;
        }
        HashMap<Integer, Integer> spawnEntryCounts = NPCSpawning.getNaturalSpawnEntryCounts(world);
        BiomeGenBase biome = world.func_72807_a(x + 8, z + 8);
        block0: while (rand.nextFloat() < biome.func_76741_f()) {
            if (npcCount >= cap) {
                return;
            }
            SpawnData data = SpawnController.Instance.getRandomSpawnData(biome.field_76791_y, world.field_73011_w.field_76574_g);
            if (data == null) continue;
            int size = 16;
            int j1 = x + rand.nextInt(size);
            int k1 = z + rand.nextInt(size);
            int l1 = j1;
            int i2 = k1;
            for (int k2 = 0; k2 < 4; ++k2) {
                if (npcCount >= cap) {
                    return;
                }
                int l2 = world.func_72825_h(j1, k1);
                if (l2 > data.spawnHeightMax || l2 < data.spawnHeightMin) continue;
                if (data.airSpawning && l2 < data.spawnHeightMax) {
                    l2 += (int)((double)(data.spawnHeightMax - l2) * Math.random());
                }
                if (!NPCSpawning.trySpawnWithEntryConfig(data, world, j1, l2, k1, rand, spawnEntryCounts)) {
                    j1 += rand.nextInt(5) - rand.nextInt(5);
                    k1 += rand.nextInt(5) - rand.nextInt(5);
                    while (j1 < x || j1 >= x + size || k1 < z || k1 >= z + size) {
                        j1 = l1 + rand.nextInt(5) - rand.nextInt(5);
                        k1 = i2 + rand.nextInt(5) - rand.nextInt(5);
                    }
                    continue;
                }
                ++npcCount;
                continue block0;
            }
        }
    }

    private static boolean spawnData(SpawnData data, World world, int x, int y, int z) {
        EntityLiving entityliving;
        try {
            NBTTagCompound[] allCompoundList = data.spawnCompounds.values().toArray(new NBTTagCompound[0]);
            ArrayList<Entity> entities = new ArrayList<Entity>();
            for (NBTTagCompound compound : allCompoundList) {
                Entity entity;
                try {
                    Class oclass = (Class)EntityList.field_75625_b.get(compound.func_74779_i("id"));
                    if (oclass == null) continue;
                    entity = EntityList.func_75615_a((NBTTagCompound)compound, (World)world);
                }
                catch (Exception e) {
                    continue;
                }
                if (entity == null) continue;
                entities.add(entity);
            }
            if (entities.size() == 0) {
                return false;
            }
            Entity spawnEntity = (Entity)entities.get((int)Math.floor(Math.random() * (double)entities.size()));
            if (!(spawnEntity instanceof EntityLiving)) {
                return false;
            }
            entityliving = (EntityLiving)spawnEntity;
            if (spawnEntity instanceof EntityCustomNpc) {
                EntityCustomNpc npc = (EntityCustomNpc)spawnEntity;
                npc.stats.spawnCycle = 3;
                if (data.despawnMode == 2) {
                    npc.stats.canDespawn = false;
                    npc.stats.playerSetCanDespawn = false;
                } else if (data.despawnMode == 0) {
                    npc.stats.canDespawn = true;
                    npc.stats.playerSetCanDespawn = true;
                }
                npc.syncDespawnPersistence();
                npc.ais.returnToStart = false;
                npc.ais.startPos = new int[]{x, y, z};
                npc.updateAI = true;
                npc.updateClient = true;
                npc.func_70661_as().func_75499_g();
            }
            entityliving.getEntityData().func_74768_a(NATURAL_SPAWN_ID_TAG, data.id);
            entityliving.getEntityData().func_74778_a(NATURAL_SPAWN_NAME_TAG, data.name);
            entityliving.getEntityData().func_74772_a(NATURAL_SPAWN_TIME_TAG, world.func_72912_H().func_82573_f());
            spawnEntity.func_70012_b((double)x + 0.5, (double)y, (double)z + 0.5, world.field_73012_v.nextFloat() * 360.0f, 0.0f);
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
        Event.Result canSpawn = ForgeEventFactory.canEntitySpawn((EntityLiving)entityliving, (World)world, (float)((float)x + 0.5f), (float)y, (float)((float)z + 0.5f));
        if (canSpawn == Event.Result.DENY || canSpawn == Event.Result.DEFAULT && !NPCSpawning.canEntitySpawn(data, entityliving)) {
            return false;
        }
        CustomNPCsEvent.CNPCNaturalSpawnEvent event = new CustomNPCsEvent.CNPCNaturalSpawnEvent((EntityLivingBase)entityliving, data, NpcAPI.Instance().getIPos(new BlockPos(x, y, z)), animalSpawn, monsterSpawn, liquidSpawn, airSpawn);
        if (EventHooks.onCNPCNaturalSpawn(event)) {
            return false;
        }
        entityliving.func_70107_b((double)event.attemptPosition.getX(), (double)event.attemptPosition.getY(), (double)event.attemptPosition.getZ());
        world.func_72838_d((Entity)entityliving);
        return true;
    }

    public static boolean canEntitySpawn(SpawnData data, EntityLiving entityLiving) {
        if (!data.liquidSpawning) {
            return entityLiving.func_70601_bi();
        }
        return entityLiving.field_70170_p.func_72855_b(entityLiving.field_70121_D) && entityLiving.field_70170_p.func_72945_a((Entity)entityLiving, entityLiving.field_70121_D).isEmpty();
    }

    public static boolean canCreatureTypeSpawnAtLocation(SpawnData data, World world, int x, int y, int z) {
        Block block = world.func_147439_a(x, y - 1, z);
        boolean hasSolidSurface = World.func_147466_a((IBlockAccess)world, (int)x, (int)(y - 1), (int)z);
        boolean spawnBlockCreature = block.canCreatureSpawn(EnumCreatureType.creature, (IBlockAccess)world, x, y - 1, z);
        boolean spawnBlockMonster = block.canCreatureSpawn(EnumCreatureType.monster, (IBlockAccess)world, x, y - 1, z);
        boolean animalSpawn = data.animalSpawning && hasSolidSurface && spawnBlockCreature && !world.func_147439_a(x, y, z).func_149721_r() && !world.func_147439_a(x, y, z).func_149688_o().func_76224_d() && !world.func_147439_a(x, y + 1, z).func_149721_r();
        boolean monsterSpawn = data.monsterSpawning && hasSolidSurface && spawnBlockMonster && !world.func_147439_a(x, y, z).func_149721_r() && !world.func_147439_a(x, y, z).func_149688_o().func_76224_d() && !world.func_147439_a(x, y + 1, z).func_149721_r();
        boolean liquidSpawn = data.liquidSpawning && world.func_147439_a(x, y - 1, z).func_149688_o().func_76224_d() && world.func_147439_a(x, y, z).func_149688_o().func_76224_d();
        boolean caveSpawn = data.airSpawning && world.func_147439_a(x, y - 1, z) == Blocks.field_150350_a && world.func_147439_a(x, y, z) == Blocks.field_150350_a && world.func_147439_a(x, y + 1, z) == Blocks.field_150350_a;
        NPCSpawning.animalSpawn = animalSpawn;
        NPCSpawning.monsterSpawn = monsterSpawn;
        NPCSpawning.liquidSpawn = liquidSpawn;
        airSpawn = caveSpawn;
        return animalSpawn || monsterSpawn || caveSpawn || liquidSpawn;
    }

    private static class RuntimeChunkState {
        public ArrayList<ChunkCoordIntPair> chunks = new ArrayList();
        public int index = 0;
        public int signature = 0;

        private RuntimeChunkState() {
        }
    }
}

