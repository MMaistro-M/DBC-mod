/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFalling
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.util.IProgressUpdate
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.gen.NoiseGeneratorOctaves
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.terraingen.PopulateChunkEvent$Post
 */
package com.tobiasmjc.dbcadditions.dimensions.chunkprovider;

import com.tobiasmjc.dbcadditions.blocks.BlocksDBCAdditions;
import com.tobiasmjc.dbcadditions.dimensions.worldgen.UniversalArena;
import cpw.mods.fml.common.eventhandler.Event;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

public class ChunkProviderUA
implements IChunkProvider {
    private final World world;
    private final Random random;
    private final NoiseGeneratorOctaves baseNoiseGen;
    private final NoiseGeneratorOctaves mountainNoiseGen;

    public ChunkProviderUA(World world, long seed) {
        this.world = world;
        this.random = new Random(seed);
        this.baseNoiseGen = new NoiseGeneratorOctaves(this.random, 8);
        this.mountainNoiseGen = new NoiseGeneratorOctaves(this.random, 8);
    }

    public Chunk provideChunk(int chunkX, int chunkZ) {
        Block[] blocks = new Block[65536];
        int startX = chunkX * 16;
        int startZ = chunkZ * 16;
        double[] baseNoise = this.baseNoiseGen.generateNoiseOctaves(null, startX, startZ, 16, 16, 0.05, 0.05, 0.5);
        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                int worldX = startX + x;
                int worldZ = startZ + z;
                int baseIndex = x * 16 + z;
                for (int y = 50; y < 64; ++y) {
                    this.setBlock(blocks, x, y, z, BlocksDBCAdditions.RedStone);
                }
                double distanceFromCenter = Math.sqrt(Math.pow(worldX, 2.0) + Math.pow(worldZ, 2.0));
                double transitionFactor = Math.max(0.0, (distanceFromCenter - 100.0) / 30.0);
                transitionFactor = transitionFactor > 1.0 ? 1.0 : transitionFactor;
                int baseHeight = 64 + (int)Math.abs(baseNoise[baseIndex] * 3.0);
                int height = (int)Math.min(150L, Math.round((double)baseHeight * transitionFactor));
                for (int y = 0; y < height; ++y) {
                    this.setBlock(blocks, x, y, z, BlocksDBCAdditions.RedStone);
                }
            }
        }
        Chunk chunk = new Chunk(this.world, blocks, new byte[65536], chunkX, chunkZ);
        chunk.generateSkylightMap();
        return chunk;
    }

    private void setBlock(Block[] blocks, int x, int y, int z, Block block) {
        int index = (x * 16 + z) * 256 + y;
        if (index >= 0 && index < blocks.length) {
            blocks[index] = block;
        }
    }

    public boolean chunkExists(int x, int z) {
        return true;
    }

    public Chunk loadChunk(int p_73158_1_, int p_73158_2_) {
        return this.provideChunk(p_73158_1_, p_73158_2_);
    }

    public void populate(IChunkProvider provider, int x, int z) {
        BlockFalling.fallInstantly = true;
        try {
            int k = x * 16;
            int l = z * 16;
            BiomeGenBase biomegenbase = this.world.getBiomeGenForCoords(k + 16, l + 16);
            this.random.setSeed(this.world.getSeed());
            long i1 = this.random.nextLong() / 2L * 2L + 1L;
            long j1 = this.random.nextLong() / 2L * 2L + 1L;
            this.random.setSeed((long)x * i1 + (long)z * j1 ^ this.world.getSeed());
            boolean flag = false;
            biomegenbase.decorate(this.world, this.random, k, l);
            if (x == 0 && z == 0) {
                new UniversalArena().generate(this.world, x, 66, z);
            }
            MinecraftForge.EVENT_BUS.post((Event)new PopulateChunkEvent.Post(provider, this.world, this.random, x, z, flag));
        }
        finally {
            BlockFalling.fallInstantly = false;
        }
    }

    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
        return true;
    }

    public void saveExtraData() {
    }

    public boolean unloadQueuedChunks() {
        return false;
    }

    public boolean canSave() {
        return true;
    }

    public String makeString() {
        return "ACLevelSource";
    }

    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int x, int y, int z) {
        BiomeGenBase biome = this.world.getBiomeGenForCoords(x, z);
        return biome == null ? null : biome.getSpawnableList(par1EnumCreatureType);
    }

    public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_) {
        return null;
    }

    public int getLoadedChunkCount() {
        return 0;
    }

    public void recreateStructures(int p_82695_1_, int p_82695_2_) {
    }
}
