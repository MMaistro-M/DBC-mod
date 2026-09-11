/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.crash.CrashReport
 *  net.minecraft.crash.CrashReportCategory
 *  net.minecraft.util.ReportedException
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldType
 *  net.minecraft.world.biome.BiomeCache
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.WorldChunkManager
 *  net.minecraft.world.gen.layer.GenLayer
 *  net.minecraft.world.gen.layer.IntCache
 */
package JinRyuu.DragonBC.common.Worlds;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class x2WorldChunkManagerTC
extends WorldChunkManager {
    private GenLayer genBiomes;
    private GenLayer biomeIndexLayer;
    private BiomeCache biomeCache = new BiomeCache((WorldChunkManager)this);
    private List<BiomeGenBase> biomesToSpawnIn = new ArrayList<BiomeGenBase>();

    public x2WorldChunkManagerTC() {
        this.biomesToSpawnIn.addAll(allowedBiomes);
    }

    public x2WorldChunkManagerTC(long seed, WorldType worldType) {
        this();
    }

    public x2WorldChunkManagerTC(World world) {
        this(world.func_72905_C(), world.func_72912_H().func_76067_t());
    }

    public List<BiomeGenBase> func_76932_a() {
        return this.biomesToSpawnIn;
    }

    public BiomeGenBase func_76935_a(int x, int z) {
        return this.biomeCache.func_76837_b(x, z);
    }

    public float[] func_76936_a(float[] listToReuse, int x, int z, int width, int length) {
        IntCache.func_76446_a();
        if (listToReuse == null || listToReuse.length < width * length) {
            listToReuse = new float[width * length];
        }
        int[] aint = this.biomeIndexLayer.func_75904_a(x, z, width, length);
        for (int i1 = 0; i1 < width * length; ++i1) {
            try {
                float f = (float)BiomeGenBase.func_150568_d((int)aint[i1]).func_76744_g() / 65536.0f;
                if (f > 1.0f) {
                    f = 1.0f;
                }
                listToReuse[i1] = f;
                continue;
            }
            catch (Throwable throwable) {
                CrashReport crashreport = CrashReport.func_85055_a((Throwable)throwable, (String)"Invalid Biome id");
                CrashReportCategory crashreportcategory = crashreport.func_85058_a("DownfallBlock");
                crashreportcategory.func_71507_a("biome id", (Object)i1);
                crashreportcategory.func_71507_a("downfalls[] size", (Object)listToReuse.length);
                crashreportcategory.func_71507_a("x", (Object)x);
                crashreportcategory.func_71507_a("z", (Object)z);
                crashreportcategory.func_71507_a("w", (Object)width);
                crashreportcategory.func_71507_a("h", (Object)length);
                throw new ReportedException(crashreport);
            }
        }
        return listToReuse;
    }

    @SideOnly(value=Side.CLIENT)
    public float func_76939_a(float par1, int par2) {
        return par1;
    }

    public BiomeGenBase[] func_76937_a(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5) {
        IntCache.func_76446_a();
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5) {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }
        int[] aint = this.genBiomes.func_75904_a(par2, par3, par4, par5);
        try {
            for (int i = 0; i < par4 * par5; ++i) {
                par1ArrayOfBiomeGenBase[i] = BiomeGenBase.func_150568_d((int)aint[i]);
            }
            return par1ArrayOfBiomeGenBase;
        }
        catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.func_85055_a((Throwable)throwable, (String)"Invalid Biome id");
            CrashReportCategory crashreportcategory = crashreport.func_85058_a("RawBiomeBlock");
            crashreportcategory.func_71507_a("biomes[] size", (Object)par1ArrayOfBiomeGenBase.length);
            crashreportcategory.func_71507_a("x", (Object)par2);
            crashreportcategory.func_71507_a("z", (Object)par3);
            crashreportcategory.func_71507_a("w", (Object)par4);
            crashreportcategory.func_71507_a("h", (Object)par5);
            throw new ReportedException(crashreport);
        }
    }

    public BiomeGenBase[] func_76933_b(BiomeGenBase[] oldBiomeList, int x, int z, int width, int depth) {
        return this.func_76931_a(oldBiomeList, x, z, width, depth, true);
    }

    public BiomeGenBase[] func_76931_a(BiomeGenBase[] listToReuse, int x, int y, int width, int length, boolean cacheFlag) {
        IntCache.func_76446_a();
        if (listToReuse == null || listToReuse.length < width * length) {
            listToReuse = new BiomeGenBase[width * length];
        }
        if (cacheFlag && width == 16 && length == 16 && (x & 0xF) == 0 && (y & 0xF) == 0) {
            BiomeGenBase[] abiomegenbase1 = this.biomeCache.func_76839_e(x, y);
            System.arraycopy(abiomegenbase1, 0, listToReuse, 0, width * length);
            return listToReuse;
        }
        int[] aint = this.biomeIndexLayer.func_75904_a(x, y, width, length);
        for (int i = 0; i < width * length; ++i) {
            listToReuse[i] = BiomeGenBase.func_150568_d((int)aint[i]);
        }
        return listToReuse;
    }

    public boolean func_76940_a(int x, int y, int z, List par4List) {
        IntCache.func_76446_a();
        int l = x - z >> 2;
        int i1 = y - z >> 2;
        int j1 = x + z >> 2;
        int k1 = y + z >> 2;
        int l1 = j1 - l + 1;
        int i2 = k1 - i1 + 1;
        int[] aint = this.genBiomes.func_75904_a(l, i1, l1, i2);
        try {
            for (int j2 = 0; j2 < l1 * i2; ++j2) {
                BiomeGenBase biomegenbase = BiomeGenBase.func_150568_d((int)aint[j2]);
                if (par4List.contains(biomegenbase)) continue;
                return false;
            }
            return true;
        }
        catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.func_85055_a((Throwable)throwable, (String)"Invalid Biome id");
            CrashReportCategory crashreportcategory = crashreport.func_85058_a("Layer");
            crashreportcategory.func_71507_a("Layer", (Object)this.genBiomes.toString());
            crashreportcategory.func_71507_a("x", (Object)x);
            crashreportcategory.func_71507_a("z", (Object)y);
            crashreportcategory.func_71507_a("radius", (Object)z);
            crashreportcategory.func_71507_a("allowed", (Object)par4List);
            throw new ReportedException(crashreport);
        }
    }

    public ChunkPosition func_150795_a(int x, int y, int z, List par4List, Random random) {
        IntCache.func_76446_a();
        int l = x - z >> 2;
        int i1 = y - z >> 2;
        int j1 = x + z >> 2;
        int k1 = y + z >> 2;
        int l1 = j1 - l + 1;
        int i2 = k1 - i1 + 1;
        int[] aint = this.genBiomes.func_75904_a(l, i1, l1, i2);
        ChunkPosition chunkposition = null;
        int j2 = 0;
        for (int k2 = 0; k2 < l1 * i2; ++k2) {
            int l2 = l + k2 % l1 << 2;
            int i3 = i1 + k2 / l1 << 2;
            BiomeGenBase biomegenbase = BiomeGenBase.func_150568_d((int)aint[k2]);
            if (!par4List.contains(biomegenbase) || chunkposition != null && random.nextInt(j2 + 1) != 0) continue;
            chunkposition = new ChunkPosition(l2, 0, i3);
            ++j2;
        }
        return chunkposition;
    }

    public void func_76938_b() {
        this.biomeCache.func_76838_a();
    }
}

