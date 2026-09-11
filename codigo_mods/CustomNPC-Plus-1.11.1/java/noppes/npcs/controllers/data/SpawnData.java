/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.WeightedRandom$Item
 *  net.minecraft.world.World
 *  net.minecraftforge.common.DimensionManager
 */
package noppes.npcs.controllers.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import noppes.npcs.NBTTags;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.handler.data.INaturalSpawn;
import noppes.npcs.scripted.CustomNPCsException;
import noppes.npcs.scripted.NpcAPI;

public class SpawnData
extends WeightedRandom.Item
implements INaturalSpawn {
    public static final int DESPAWN_FORCE_NATURAL = 0;
    public static final int DESPAWN_PRESERVE_TEMPLATE = 1;
    public static final int DESPAWN_FORCE_PERSISTENT = 2;
    public List<String> biomes = new ArrayList<String>();
    public HashSet<Integer> dimensions = new HashSet();
    public int id = -1;
    public String name = "";
    public HashMap<Integer, NBTTagCompound> spawnCompounds = new HashMap();
    public boolean animalSpawning = true;
    public boolean monsterSpawning = false;
    public boolean liquidSpawning = false;
    public boolean airSpawning = false;
    public int spawnHeightMin;
    public int spawnHeightMax = 100;
    public int maxAlive = 0;
    public int cooldownTicks = 0;
    public int attemptsPerCycle = 1;
    public int playerMinDistance = 24;
    public int despawnMode = 0;

    public SpawnData() {
        super(10);
    }

    public void readNBT(NBTTagCompound compound) {
        this.id = compound.func_74762_e("SpawnId");
        this.name = compound.func_74779_i("SpawnName");
        this.field_76292_a = compound.func_74762_e("SpawnWeight");
        if (this.field_76292_a == 0) {
            this.field_76292_a = 1;
        }
        this.biomes = NBTTags.getStringList(compound.func_150295_c("SpawnBiomes", 10));
        if (!compound.func_74764_b("SpawnDimensions")) {
            this.dimensions.addAll(Arrays.asList(DimensionManager.getStaticDimensionIDs()));
        } else {
            this.dimensions = NBTTags.getIntegerSet(compound.func_150295_c("SpawnDimensions", 10));
        }
        this.spawnCompounds.clear();
        Set keys = compound.func_150296_c();
        for (Object key : keys) {
            if (!((String)key).startsWith("SpawnCompound")) continue;
            int i = Integer.parseInt(((String)key).replace("SpawnCompound", ""));
            this.spawnCompounds.put(i, compound.func_74775_l("SpawnCompound" + i));
        }
        this.animalSpawning = compound.func_74767_n("AnimalSpawning");
        this.monsterSpawning = compound.func_74767_n("MonsterSpawning");
        this.liquidSpawning = compound.func_74767_n("LiquidSpawning");
        this.airSpawning = compound.func_74767_n("CaveSpawning");
        this.spawnHeightMin = compound.func_74762_e("HeightMin");
        this.spawnHeightMax = !compound.func_74764_b("HeightMax") ? 100 : compound.func_74762_e("HeightMax");
        this.maxAlive = compound.func_74764_b("MaxAlive") ? Math.max(0, compound.func_74762_e("MaxAlive")) : 0;
        this.cooldownTicks = compound.func_74764_b("CooldownTicks") ? Math.max(0, compound.func_74762_e("CooldownTicks")) : 0;
        this.attemptsPerCycle = compound.func_74764_b("AttemptsPerCycle") ? Math.max(1, compound.func_74762_e("AttemptsPerCycle")) : 1;
        this.playerMinDistance = compound.func_74764_b("PlayerMinDistance") ? Math.max(0, compound.func_74762_e("PlayerMinDistance")) : 24;
        this.despawnMode = compound.func_74764_b("DespawnMode") ? compound.func_74762_e("DespawnMode") : 0;
        if (this.despawnMode < 0 || this.despawnMode > 2) {
            this.despawnMode = 0;
        }
    }

    public NBTTagCompound writeNBT(NBTTagCompound compound) {
        compound.func_74768_a("SpawnId", this.id);
        compound.func_74778_a("SpawnName", this.name);
        compound.func_74768_a("SpawnWeight", this.field_76292_a);
        compound.func_74782_a("SpawnBiomes", (NBTBase)NBTTags.nbtStringList(this.biomes));
        if (!this.dimensions.isEmpty()) {
            compound.func_74782_a("SpawnDimensions", (NBTBase)NBTTags.nbtIntegerSet(this.dimensions));
        }
        Set<Map.Entry<Integer, NBTTagCompound>> entries = this.spawnCompounds.entrySet();
        for (Map.Entry<Integer, NBTTagCompound> entry : entries) {
            compound.func_74782_a("SpawnCompound" + entry.getKey(), (NBTBase)(entry.getValue() != null ? (NBTBase)entry.getValue() : new NBTTagCompound()));
        }
        compound.func_74757_a("AnimalSpawning", this.animalSpawning);
        compound.func_74757_a("MonsterSpawning", this.monsterSpawning);
        compound.func_74757_a("LiquidSpawning", this.liquidSpawning);
        compound.func_74757_a("CaveSpawning", this.airSpawning);
        compound.func_74768_a("HeightMin", this.spawnHeightMin);
        compound.func_74768_a("HeightMax", this.spawnHeightMax);
        compound.func_74768_a("MaxAlive", this.maxAlive);
        compound.func_74768_a("CooldownTicks", this.cooldownTicks);
        compound.func_74768_a("AttemptsPerCycle", this.attemptsPerCycle);
        compound.func_74768_a("PlayerMinDistance", this.playerMinDistance);
        compound.func_74768_a("DespawnMode", this.despawnMode);
        return compound;
    }

    @Override
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setEntity(IEntity entity, int slot) {
        NBTTagCompound compound = new NBTTagCompound();
        if (entity != null && !entity.getMCEntity().func_70039_c(compound)) {
            throw new CustomNPCsException("Entity could not be written to NBT", new Object[0]);
        }
        this.spawnCompounds.put(slot, compound);
    }

    @Override
    public IEntity getEntity(IWorld world, int slot) {
        if (!this.spawnCompounds.containsKey(slot)) {
            return null;
        }
        try {
            Entity entity = EntityList.func_75615_a((NBTTagCompound)this.spawnCompounds.get(slot), (World)world.getMCWorld());
            return NpcAPI.Instance().getIEntity(entity);
        }
        catch (Exception e) {
            throw new CustomNPCsException("Error creating entity from spawn data:\n" + e.getMessage(), new Object[0]);
        }
    }

    @Override
    public Integer[] getSlots() {
        return this.spawnCompounds.keySet().toArray(new Integer[0]);
    }

    @Override
    public void setWeight(int weight) {
        if (weight < 1) {
            weight = 1;
        }
        if (weight > 100) {
            weight = 100;
        }
        this.field_76292_a = weight;
    }

    @Override
    public int getWeight() {
        return this.field_76292_a;
    }

    @Override
    public void setMinHeight(int height) {
        this.spawnHeightMin = height;
    }

    @Override
    public int getMinHeight() {
        return this.spawnHeightMin;
    }

    @Override
    public void setMaxHeight(int height) {
        this.spawnHeightMax = height;
    }

    @Override
    public int getMaxHeight() {
        return this.spawnHeightMax;
    }

    @Override
    public void spawnsLikeAnimal(boolean spawns) {
        this.animalSpawning = spawns;
    }

    @Override
    public boolean spawnsLikeAnimal() {
        return this.animalSpawning;
    }

    @Override
    public void spawnsLikeMonster(boolean spawns) {
        this.monsterSpawning = spawns;
    }

    @Override
    public boolean spawnsLikeMonster() {
        return this.monsterSpawning;
    }

    @Override
    public void spawnsInLiquid(boolean spawns) {
        this.liquidSpawning = spawns;
    }

    @Override
    public boolean spawnsInLiquid() {
        return this.liquidSpawning;
    }

    @Override
    public void spawnsInAir(boolean spawns) {
        this.airSpawning = spawns;
    }

    @Override
    public boolean spawnsInAir() {
        return this.airSpawning;
    }

    @Override
    public String[] getBiomes() {
        return this.biomes.toArray(new String[0]);
    }

    @Override
    public void setBiomes(String[] biomes) {
        this.biomes = new ArrayList<String>(Arrays.asList(biomes));
    }

    @Override
    public void setMaxAlive(int maxAlive) {
        this.maxAlive = Math.max(0, maxAlive);
    }

    @Override
    public int getMaxAlive() {
        return this.maxAlive;
    }

    @Override
    public void setCooldownTicks(int ticks) {
        this.cooldownTicks = Math.max(0, ticks);
    }

    @Override
    public int getCooldownTicks() {
        return this.cooldownTicks;
    }

    @Override
    public void setAttemptsPerCycle(int attempts) {
        this.attemptsPerCycle = Math.max(1, attempts);
    }

    @Override
    public int getAttemptsPerCycle() {
        return this.attemptsPerCycle;
    }

    @Override
    public void setPlayerMinDistance(int distance) {
        this.playerMinDistance = Math.max(0, distance);
    }

    @Override
    public int getPlayerMinDistance() {
        return this.playerMinDistance;
    }

    @Override
    public void setDespawnMode(int mode) {
        if (mode < 0 || mode > 2) {
            mode = 0;
        }
        this.despawnMode = mode;
    }

    @Override
    public int getDespawnMode() {
        return this.despawnMode;
    }
}

