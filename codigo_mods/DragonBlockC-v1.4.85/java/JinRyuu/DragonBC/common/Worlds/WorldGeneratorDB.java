/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.IWorldGenerator
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.gen.feature.WorldGenMinable
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package JinRyuu.DragonBC.common.Worlds;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.DBCH;
import JinRyuu.DragonBC.common.Npcs.EntityMasterEnma;
import JinRyuu.DragonBC.common.Npcs.EntityMasterJin;
import JinRyuu.DragonBC.common.Npcs.EntityMasterKaio;
import JinRyuu.DragonBC.common.Npcs.EntityMasterKami;
import JinRyuu.DragonBC.common.Npcs.EntityMasterKarin;
import JinRyuu.DragonBC.common.Npcs.EntityMasterPiccolo;
import JinRyuu.DragonBC.common.Npcs.EntityMasterVegeta;
import JinRyuu.DragonBC.common.Villages.ChkInSt;
import JinRyuu.DragonBC.common.Villages.KiLt;
import JinRyuu.DragonBC.common.Villages.KnTr;
import JinRyuu.DragonBC.common.Villages.SnkWy;
import JinRyuu.DragonBC.common.Villages.TiCha;
import JinRyuu.DragonBC.common.Villages.bs;
import JinRyuu.DragonBC.common.Villages.builds;
import JinRyuu.DragonBC.common.Villages.ca;
import JinRyuu.DragonBC.common.Villages.fs;
import JinRyuu.DragonBC.common.Villages.gh;
import JinRyuu.DragonBC.common.Villages.kh;
import JinRyuu.DragonBC.common.Worlds.BiomeGenBaseDBC;
import JinRyuu.DragonBC.common.Worlds.structures.StructureGuru;
import JinRyuu.DragonBC.common.Worlds.structures.StructureTOP1;
import JinRyuu.DragonBC.common.Worlds.structures.StructureTOP2;
import JinRyuu.DragonBC.common.Worlds.structures.StructureZenoExpo;
import JinRyuu.JRMCore.JRMCoreComTickH;
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHDBC;
import JinRyuu.JRMCore.blocks.BlocksJRMC;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IWorldGenerator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGeneratorDB
implements IWorldGenerator {
    protected WorldGenerator ChkInSt;
    protected WorldGenerator KiLt;
    protected WorldGenerator SnkWy;
    protected WorldGenerator TiCha;
    protected WorldGenerator KnTr;
    protected WorldGenerator WorldGen_TOPArena;
    protected WorldGenerator WorldGen_TOPZeno;
    protected WorldGenerator WorldGen_ZenoExpo;
    public static boolean KiLtSpawn = true;
    public static boolean KiLtTCSpawn = true;
    public static boolean ChkInStSpawn = true;
    public static boolean SnkWySpawn = true;
    public static boolean TiChaSpawn = true;
    public static boolean TiChaKLSpawn = true;
    public static boolean KnTrSpawn = true;
    public static boolean WorldGen_TOPArena_Done = true;
    public static boolean WorldGen_TOPZeno_Done = true;
    public static boolean WorldGen_Zeno_Done = true;
    public static boolean FSSpawn = true;
    public static boolean KHSpawn = true;
    public static boolean CASpawn = true;
    public static boolean GHSpawn = true;
    public static boolean BSSpawn = true;
    public static boolean WorldGen_GuruHouse_Done = true;
    public static builds[] DBbuilds = new builds[]{new ca(), new gh(), new fs(), new bs(), new StructureGuru()};
    public static int[] DBbuildsdim = new int[]{0, 0, DBCConfig.planetNamek, 0, DBCConfig.planetNamek};

    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.field_73011_w.field_76574_g) {
            case -1: {
                this.generateNether(world, random, chunkX * 16, chunkZ * 16);
                break;
            }
            case 0: {
                this.generateSurface(world, random, chunkX * 16, chunkZ * 16);
                break;
            }
            case 20: {
                this.generateNamek(world, random, chunkX * 16, chunkZ * 16);
                break;
            }
            case 22: {
                this.generateOW(world, random, chunkX * 16, chunkZ * 16);
                break;
            }
            case 23: {
                this.generateTC(world, random, chunkX * 16, chunkZ * 16);
                break;
            }
            case 24: {
                this.generateNR(world, random, chunkX * 16, chunkZ * 16);
            }
        }
    }

    private void generateSurface(World world, Random random, int BlockX, int BlockZ) {
        if (world.field_73011_w.field_76574_g == 0) {
            for (int i = 0; i < 10; ++i) {
                int RPX = BlockX + random.nextInt(16);
                int RPZ = BlockZ + random.nextInt(16);
                int RPY = 10 + random.nextInt(127);
                new WorldGenMinable(BlocksDBC.BlockOreWrenai, 8).func_76484_a(world, random, RPX, RPY, RPZ);
            }
            if (JRMCoreConfig.BuildingSpawnCheck && world.func_147439_a(86, 216, 50) != BlocksJRMC.BlockColoredStone && KiLtSpawn) {
                KiLtSpawn = false;
                this.KiLt = new KiLt();
                this.KiLt.func_76484_a(world, world.field_73012_v, 0, 0, 0);
                world.func_147465_d(43, 216, 37, BlocksJRMC.BlockColoredStone, 0, 3);
                world.func_147465_d(77, 167, 42, BlocksJRMC.BlockColoredStone, 4, 3);
                world.func_147449_b(78, 217, 32, (Block)BlocksJRMC.stoneSingleSlab);
                world.func_147449_b(76, 217, 32, (Block)BlocksJRMC.stoneSingleSlab);
                world.func_147465_d(78, 218, 32, Blocks.field_150350_a, 0, 3);
                world.func_147465_d(78, 219, 32, Blocks.field_150350_a, 0, 3);
                world.func_147465_d(78, 220, 32, Blocks.field_150350_a, 0, 3);
                world.func_147465_d(76, 218, 32, Blocks.field_150350_a, 0, 3);
                world.func_147465_d(76, 219, 32, Blocks.field_150350_a, 0, 3);
                world.func_147465_d(76, 220, 32, Blocks.field_150350_a, 0, 3);
                world.func_147465_d(79, 218, 32, BlocksJRMC.BlockColoredStone, 4, 3);
                world.func_147465_d(79, 219, 32, BlocksJRMC.BlockColoredStone, 4, 3);
                world.func_147465_d(79, 220, 32, BlocksJRMC.BlockColoredStone, 4, 3);
                world.func_147465_d(79, 221, 32, BlocksJRMC.BlockColoredStone, 4, 3);
                world.func_147465_d(75, 218, 32, BlocksJRMC.BlockColoredStone, 4, 3);
                world.func_147465_d(75, 219, 32, BlocksJRMC.BlockColoredStone, 4, 3);
                world.func_147465_d(75, 220, 32, BlocksJRMC.BlockColoredStone, 4, 3);
                world.func_147465_d(75, 221, 32, BlocksJRMC.BlockColoredStone, 4, 3);
                if (world.func_147439_a(80, 134, 34) != BlocksJRMC.BlockFence && KnTrSpawn) {
                    KnTrSpawn = false;
                    this.KnTr = new KnTr();
                    this.KnTr.func_76484_a(world, world.field_73012_v, 0, 0, 0);
                    world.func_147465_d(77, 133, 45, BlocksJRMC.BlockColoredStone, 0, 3);
                    world.func_147465_d(77, 134, 45, BlocksJRMC.BlockColoredStone, 0, 3);
                }
                if (world.func_147439_a(77, 219, 8) != BlocksDBC.BlockTCPort && KiLtTCSpawn) {
                    KiLtTCSpawn = false;
                    world.func_147465_d(77, 217, 8, BlocksDBC.BlockTCPort, 0, 3);
                    world.func_147465_d(77, 218, 8, BlocksDBC.BlockTCPort, 0, 3);
                    world.func_147465_d(77, 219, 8, BlocksDBC.BlockTCPort, 0, 3);
                    world.func_147465_d(76, 217, 8, BlocksDBC.BlockTCPort, 0, 3);
                    world.func_147465_d(76, 218, 8, BlocksDBC.BlockTCPort, 0, 3);
                    world.func_147465_d(78, 217, 8, BlocksDBC.BlockTCPort, 0, 3);
                    world.func_147465_d(78, 218, 8, BlocksDBC.BlockTCPort, 0, 3);
                    world.func_147465_d(78, 219, 8, BlocksDBC.BlockTCPort, 0, 3);
                    world.func_147465_d(76, 219, 8, BlocksDBC.BlockTCPort, 0, 3);
                    world.func_147465_d(76, 220, 8, BlocksJRMC.BlockColoredStone, 0, 3);
                    world.func_147465_d(77, 220, 8, BlocksJRMC.BlockColoredStone, 0, 3);
                    world.func_147465_d(78, 220, 8, BlocksJRMC.BlockColoredStone, 0, 3);
                    world.func_147465_d(75, 220, 8, BlocksJRMC.BlockColoredStone, 0, 3);
                    world.func_147465_d(75, 219, 8, BlocksJRMC.BlockColoredStone, 0, 3);
                    world.func_147465_d(75, 218, 8, BlocksJRMC.BlockColoredStone, 0, 3);
                    world.func_147465_d(75, 217, 8, BlocksJRMC.BlockColoredStone, 0, 3);
                    world.func_147465_d(79, 220, 8, BlocksJRMC.BlockColoredStone, 0, 3);
                    world.func_147465_d(79, 219, 8, BlocksJRMC.BlockColoredStone, 0, 3);
                    world.func_147465_d(79, 218, 8, BlocksJRMC.BlockColoredStone, 0, 3);
                    world.func_147465_d(79, 217, 8, BlocksJRMC.BlockColoredStone, 0, 3);
                }
            }
            if (JRMCoreConfig.NPCSpawnCheck) {
                AxisAlignedBB knaabb;
                List kn;
                AxisAlignedBB par2AxisAlignedBB = AxisAlignedBB.func_72330_a((double)56.0, (double)10.0, (double)20.0, (double)116.0, (double)240.0, (double)80.0);
                List enma = world.func_72872_a(EntityMasterKami.class, par2AxisAlignedBB);
                if (enma.isEmpty()) {
                    EntityMasterKami namekian03 = new EntityMasterKami(world);
                    namekian03.func_70012_b(86.0, 217.0, 50.0, 0.0f, 0.0f);
                    world.func_72838_d((Entity)namekian03);
                }
                if ((enma = world.func_72872_a(EntityMasterPiccolo.class, par2AxisAlignedBB = AxisAlignedBB.func_72330_a((double)85.0, (double)10.0, (double)91.0, (double)89.0, (double)240.0, (double)95.0))).isEmpty()) {
                    EntityMasterPiccolo namekian03 = new EntityMasterPiccolo(world);
                    namekian03.func_70012_b(87.0, 217.0, 93.0, 0.0f, 0.0f);
                    world.func_72838_d((Entity)namekian03);
                }
                if ((enma = world.func_72872_a(EntityMasterVegeta.class, par2AxisAlignedBB = AxisAlignedBB.func_72330_a((double)71.0, (double)10.0, (double)8.0, (double)75.0, (double)240.0, (double)12.0))).isEmpty()) {
                    EntityMasterVegeta namekian03 = new EntityMasterVegeta(world);
                    namekian03.func_70012_b(73.0, 217.0, 10.0, 0.0f, 0.0f);
                    world.func_72838_d((Entity)namekian03);
                }
                if ((kn = world.func_72872_a(EntityMasterKarin.class, knaabb = AxisAlignedBB.func_72330_a((double)70.0, (double)120.0, (double)30.0, (double)90.0, (double)140.0, (double)50.0))).isEmpty()) {
                    EntityMasterKarin k = new EntityMasterKarin(world);
                    k.func_70012_b(80.0, 133.0, 40.0, 0.0f, 0.0f);
                    world.func_72838_d((Entity)k);
                }
            }
        }
        int RPX = BlockX + random.nextInt(16);
        int RPZ = BlockZ + random.nextInt(16);
        int RPY = 62;
        if (world.func_72807_a(RPX, RPZ) == BiomeGenBase.field_76771_b || world.func_72807_a(RPX, RPZ) == BiomeGenBase.field_150575_M || DBCH.genKH.length() < 3) {
            boolean may = true;
            if (KHSpawn && world.func_147439_a(RPX, RPY, RPZ) == Blocks.field_150355_j && world.func_147439_a(RPX, RPY + 1, RPZ).func_149688_o() == Material.field_151579_a) {
                int testWaterDepth = 62;
                for (int i1 = RPY; i1 > 35; --i1) {
                    int depth = i1;
                    if (depth < testWaterDepth) {
                        testWaterDepth = depth;
                    }
                    if (world.func_147439_a(RPX, i1, RPZ) == Blocks.field_150355_j) continue;
                    may = false;
                    break;
                }
                if (may) {
                    int s = JRMCoreConfig.buildingSpawnAreaSize;
                    int s2 = 30;
                    AxisAlignedBB ab = AxisAlignedBB.func_72330_a((double)((double)RPX - (double)s), (double)64.0, (double)((double)RPZ - (double)s), (double)((double)RPX + (double)s), (double)(63 + s > 200 ? 200 : 63 + s), (double)((double)RPZ + (double)s));
                    List list = world.func_147461_a(ab);
                    boolean bo = true;
                    for (int i1 = 0; i1 < 8 && bo; ++i1) {
                        bo = world.func_72807_a(RPX + (i1 == 0 || i1 == 2 || i1 == 6 ? s * 2 : (i1 == 4 || i1 == 5 ? 0 : -s * 2)), RPZ + (i1 == 0 || i1 == 1 || i1 == 5 ? s * 2 : (i1 == 6 || i1 == 7 ? 0 : -s * 2))) == BiomeGenBase.field_76771_b || world.func_72807_a(RPX + (i1 == 0 || i1 == 2 || i1 == 6 ? s * 2 : (i1 == 4 || i1 == 5 ? 0 : -s * 2)), RPZ + (i1 == 0 || i1 == 1 || i1 == 5 ? s * 2 : (i1 == 6 || i1 == 7 ? 0 : -s * 2))) == BiomeGenBase.field_150575_M;
                    }
                    if (list.size() == 0 && bo && DBCH.khrwi(FMLCommonHandler.instance().getMinecraftServerInstance()).length() < 3) {
                        KHSpawn = false;
                        String d = RPX + ";" + (RPY + 1) + ";" + RPZ;
                        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
                        DBCH.khwwi(server, d, false);
                        DBCH.genKH = d;
                        kh kh2 = new kh();
                        kh2.func_76484_a(world, random, RPX - s2, RPY - 3, RPZ - s2);
                        int cur = server.func_71233_x();
                        for (int pl = 0; pl < cur; ++pl) {
                            EntityPlayerMP p = JRMCoreH.getPlayerForUsername(server, server.func_71213_z()[pl]);
                            String t1 = JRMCoreH.cly + JRMCoreH.trl("dbc.worldgen.buildings.beenfound");
                            String t2 = JRMCoreH.cly + JRMCoreH.trl("dbc.com.loc.kame");
                            String tf = String.format(t1, t2);
                            p.func_145747_a((IChatComponent)new ChatComponentText(JRMCoreH.cly + tf));
                        }
                    }
                }
            }
        }
        int num = random.nextInt(15);
        int buildingID = 1;
        if (this.DBbuildsSpawn(buildingID) && num == 0) {
            int i = BlockX + random.nextInt(16);
            int k = BlockZ + random.nextInt(16);
            this.buildingSpawn(world, random, i, k, buildingID, BiomeGenBase.field_76772_c, Type.NORMAL);
        }
        if (this.DBbuildsSpawn(buildingID = 0) && num == 1) {
            int i = BlockX + random.nextInt(16);
            int k = BlockZ + random.nextInt(16);
            this.buildingSpawn(world, random, i, k, buildingID, BiomeGenBase.field_76772_c, Type.NORMAL);
        }
        if (this.DBbuildsSpawn(buildingID = 3) && num == 3) {
            int i = BlockX + random.nextInt(16);
            int k = BlockZ + random.nextInt(16);
            this.buildingSpawn(world, random, i, k, buildingID, BiomeGenBase.field_76772_c, Type.UNDER);
        }
    }

    public static String DBbuildsNams(int i) {
        if (i == 0) {
            return DBCH.ca;
        }
        if (i == 1) {
            return DBCH.gh;
        }
        if (i == 2) {
            return DBCH.fs;
        }
        if (i == 3) {
            return DBCH.bs;
        }
        if (i == 4) {
            return DBCH.guruh;
        }
        return "";
    }

    public static String DBbuildsNams2(int i) {
        if (i == 0) {
            return "dbc.com.loc.cell";
        }
        if (i == 1) {
            return "dbc.com.loc.goku";
        }
        if (i == 2) {
            return "dbc.com.loc.freeza";
        }
        if (i == 3) {
            return "dbc.com.loc.babidi";
        }
        if (i == 4) {
            return "dbc.com.loc.guruhouse";
        }
        return "";
    }

    public static String DBbuildsGen(int i) {
        if (i == 0) {
            DBCH.genCA = DBCH.carwi(FMLCommonHandler.instance().getMinecraftServerInstance());
            return DBCH.genCA;
        }
        if (i == 1) {
            DBCH.genGH = DBCH.ghrwi(FMLCommonHandler.instance().getMinecraftServerInstance());
            return DBCH.genGH;
        }
        if (i == 2) {
            DBCH.genFS = DBCH.fsrwi(FMLCommonHandler.instance().getMinecraftServerInstance());
            return DBCH.genFS;
        }
        if (i == 3) {
            DBCH.genBS = DBCH.bsrwi(FMLCommonHandler.instance().getMinecraftServerInstance());
            return DBCH.genBS;
        }
        if (i == 4) {
            DBCH.genGuru = DBCH.guruhrwi(FMLCommonHandler.instance().getMinecraftServerInstance());
            return DBCH.genGuru;
        }
        return "";
    }

    public static void DBbuildsGen(int i, String d) {
        if (i == 0) {
            DBCH.genCA = d;
            DBCH.cawwi(FMLCommonHandler.instance().getMinecraftServerInstance(), d, false);
        } else if (i == 1) {
            DBCH.genGH = d;
            DBCH.ghwwi(FMLCommonHandler.instance().getMinecraftServerInstance(), d, false);
        } else if (i == 2) {
            DBCH.genFS = d;
            DBCH.fswwi(FMLCommonHandler.instance().getMinecraftServerInstance(), d, false);
        } else if (i == 3) {
            DBCH.genBS = d;
            DBCH.bswwi(FMLCommonHandler.instance().getMinecraftServerInstance(), d, false);
        } else if (i == 4) {
            DBCH.genGuru = d;
            DBCH.guruhwwi(FMLCommonHandler.instance().getMinecraftServerInstance(), d, false);
        }
    }

    public boolean DBbuildsSpawn(int i) {
        if (i == 0) {
            return CASpawn;
        }
        if (i == 1) {
            return GHSpawn;
        }
        if (i == 2) {
            return FSSpawn;
        }
        if (i == 3) {
            return BSSpawn;
        }
        if (i == 4) {
            return WorldGen_GuruHouse_Done;
        }
        return false;
    }

    public static void DBbuildsSpawn(int i, boolean d) {
        if (i == 0) {
            CASpawn = d;
        } else if (i == 1) {
            GHSpawn = d;
        } else if (i == 2) {
            FSSpawn = d;
        } else if (i == 3) {
            BSSpawn = d;
        } else if (i == 4) {
            WorldGen_GuruHouse_Done = d;
        }
    }

    public void buildingSpawn(World world, Random random, int i, int k, int gen, BiomeGenBase biome, Type type) {
        if (this.DBbuildsSpawn(gen) && world.field_73011_w.field_76574_g == DBbuildsdim[gen] && world.func_72807_a(i, k) == biome) {
            String bg;
            if (world.func_147439_a(86, 216, 50) == BlocksJRMC.BlockColoredStone) {
                // empty if block
            }
            if ((bg = WorldGeneratorDB.DBbuildsGen(gen)).length() > 2) {
                WorldGeneratorDB.DBbuildsSpawn(gen, false);
            } else if (bg.length() == 1) {
                int j;
                builds v = DBbuilds[gen];
                int s = JRMCoreConfig.buildingSpawnAreaSize;
                boolean bo = JRMCoreHDBC.checkForBuildsAound(world, i, k, s);
                for (int i1 = 0; i1 < 5; ++i1) {
                    if (world.func_72807_a(i + (i1 == 0 || i1 == 1 ? 0 : builds.SizeX), k + (i1 == 0 || i1 == 2 ? 0 : builds.SizeZ)) == biome) continue;
                    bo = false;
                    break;
                }
                if (bo && (j = world.func_72976_f(i, k)) > 67) {
                    for (int i1 = 0; i1 < 5; ++i1) {
                        int j2 = world.func_72976_f(i + (i1 == 0 || i1 == 1 ? 0 : builds.SizeX), k + (i1 == 0 || i1 == 2 ? 0 : builds.SizeZ));
                        if (world.func_147439_a(i + (i1 == 0 || i1 == 1 ? 0 : builds.SizeX), j2 - 1, k + (i1 == 0 || i1 == 2 ? 0 : builds.SizeZ)) != Blocks.field_150355_j) continue;
                        bo = false;
                        break;
                    }
                    if (bo && type == Type.UNDER) {
                        int j2 = world.func_72976_f(i + builds.SizeX / 2, k + builds.SizeZ / 2);
                        if (world.func_147439_a(i + builds.SizeX / 2, j2, k + builds.SizeZ / 2) == Blocks.field_150355_j || j2 < 60) {
                            bo = false;
                        }
                        j = j2 - builds.SizeY;
                    }
                    if (bo) {
                        WorldGeneratorDB.DBbuildsSpawn(gen, true);
                        String d = i + ";" + j + ";" + k;
                        WorldGeneratorDB.DBbuildsGen(gen, d);
                        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
                        int cur = server.func_71233_x();
                        for (int pl = 0; pl < cur; ++pl) {
                            EntityPlayerMP p = JRMCoreH.getPlayerForUsername(server, server.func_71213_z()[pl]);
                            String t1 = JRMCoreH.cly + JRMCoreH.trl("dbc.worldgen.buildings.beenfound");
                            String t2 = JRMCoreH.cly + JRMCoreH.trl(WorldGeneratorDB.DBbuildsNams(gen));
                            String tf = String.format(t1, t2);
                            p.func_145747_a((IChatComponent)new ChatComponentText(JRMCoreH.cly + tf));
                        }
                        v.setWorld(world);
                        JRMCoreComTickH.bldngsChecker = 300;
                        JRMCoreComTickH.bldngChkr = true;
                    }
                }
            }
        }
    }

    private void generateNether(World world, Random random, int i, int j) {
    }

    private void generateNamek(World world, Random random, int BlockX, int BlockZ) {
        if (world.field_73011_w.field_76574_g == DBCConfig.planetNamek) {
            int k;
            int i;
            int buildingID = 2;
            if (this.DBbuildsSpawn(buildingID)) {
                i = BlockX + random.nextInt(16);
                k = BlockZ + random.nextInt(16);
                this.buildingSpawn(world, random, i, k, buildingID, BiomeGenBaseDBC.Namek, Type.NORMAL);
            }
            if (this.DBbuildsSpawn(buildingID = 4)) {
                i = BlockX + random.nextInt(16);
                k = BlockZ + random.nextInt(16);
                this.buildingSpawn(world, random, i, k, buildingID, BiomeGenBaseDBC.Namek, Type.NORMAL);
            }
            for (int x = 0; x < 10; ++x) {
                int RPX = BlockX + random.nextInt(16);
                int RPZ = BlockZ + random.nextInt(16);
                int RPY = 10 + random.nextInt(127);
                new WorldGenMinable(BlocksDBC.BlockOreWrenai, 6).func_76484_a(world, random, RPX, RPY, RPZ);
            }
        }
    }

    private void generateNR(World w, Random randomGenerator, int chunk_X, int chunk_Z) {
        if (w.field_73011_w.field_76574_g == 24 && JRMCoreConfig.BuildingSpawnCheck) {
            if (w.func_147439_a(179, 136, 71) != BlocksDBC.BlockKachiKachin[4] && WorldGen_TOPZeno_Done) {
                WorldGen_TOPZeno_Done = false;
                this.WorldGen_TOPZeno = new StructureTOP2();
                this.WorldGen_TOPZeno.func_76484_a(w, w.field_73012_v, 0, 0, 0);
            }
            if (w.func_147439_a(6, 135, 70) != BlocksDBC.BlockKachiKachin[1] && WorldGen_TOPArena_Done) {
                WorldGen_TOPArena_Done = false;
                this.WorldGen_TOPArena = new StructureTOP1();
                this.WorldGen_TOPArena.func_76484_a(w, w.field_73012_v, 0, 0, 0);
            }
            if (w.func_147439_a(386, 55, 87) != BlocksDBC.BlockKachiKachin[4] && WorldGen_Zeno_Done) {
                WorldGen_Zeno_Done = false;
                this.WorldGen_ZenoExpo = new StructureZenoExpo();
                this.WorldGen_ZenoExpo.func_76484_a(w, w.field_73012_v, 0, 0, 0);
            }
        }
    }

    private void generateTC(World w, Random randomGenerator, int chunk_X, int chunk_Z) {
        if (w.field_73011_w.field_76574_g == 23 && JRMCoreConfig.BuildingSpawnCheck && w.func_147439_a(55, 34, 8) != BlocksJRMC.BlockColoredStone && TiChaSpawn) {
            TiChaSpawn = false;
            this.TiCha = new TiCha();
            this.TiCha.func_76484_a(w, w.field_73012_v, 0, 0, 0);
            if (w.func_147439_a(53, 37, 8) != BlocksDBC.BlockTCPort && TiChaKLSpawn) {
                TiChaKLSpawn = false;
                w.func_147465_d(54, 35, 8, BlocksDBC.BlockTCPort, 0, 3);
                w.func_147465_d(55, 35, 8, BlocksDBC.BlockTCPort, 0, 3);
                w.func_147465_d(54, 36, 8, BlocksDBC.BlockTCPort, 0, 3);
                w.func_147465_d(55, 36, 8, BlocksDBC.BlockTCPort, 0, 3);
                w.func_147465_d(53, 36, 8, BlocksDBC.BlockTCPort, 0, 3);
                w.func_147465_d(53, 35, 8, BlocksDBC.BlockTCPort, 0, 3);
                w.func_147465_d(53, 37, 8, BlocksDBC.BlockTCPort, 0, 3);
                w.func_147465_d(54, 37, 8, BlocksDBC.BlockTCPort, 0, 3);
                w.func_147465_d(55, 37, 8, BlocksDBC.BlockTCPort, 0, 3);
            }
        }
    }

    private void generateOW(World w, Random randomGenerator, int chunk_X, int chunk_Z) {
        if (w.field_73011_w.field_76574_g == 22) {
            int firstBlockZCoord;
            int firstBlockYCoord;
            int firstBlockXCoord;
            int k;
            for (k = 0; k < 10; ++k) {
                firstBlockXCoord = chunk_X + randomGenerator.nextInt(16);
                firstBlockYCoord = randomGenerator.nextInt(40);
                firstBlockZCoord = chunk_Z + randomGenerator.nextInt(16);
                new WorldGenMinable(BlocksDBC.BlockOreJJay, 12).func_76484_a(w, randomGenerator, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
            }
            for (k = 0; k < 10; ++k) {
                firstBlockXCoord = chunk_X + randomGenerator.nextInt(16);
                firstBlockYCoord = randomGenerator.nextInt(40);
                firstBlockZCoord = chunk_Z + randomGenerator.nextInt(16);
                new WorldGenMinable(BlocksDBC.BlockOreDlog, 8).func_76484_a(w, randomGenerator, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
            }
            for (k = 0; k < 10; ++k) {
                firstBlockXCoord = chunk_X + randomGenerator.nextInt(16);
                firstBlockYCoord = randomGenerator.nextInt(40);
                firstBlockZCoord = chunk_Z + randomGenerator.nextInt(16);
                new WorldGenMinable(BlocksDBC.BlockOreLehnori, 8).func_76484_a(w, randomGenerator, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
            }
            for (k = 0; k < 10; ++k) {
                firstBlockXCoord = chunk_X + randomGenerator.nextInt(16);
                firstBlockYCoord = randomGenerator.nextInt(40);
                firstBlockZCoord = chunk_Z + randomGenerator.nextInt(16);
                new WorldGenMinable(BlocksDBC.BlockOreDnomaid, 7, Blocks.field_150353_l).func_76484_a(w, randomGenerator, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
            }
            if (JRMCoreConfig.BuildingSpawnCheck) {
                if (w.func_147439_a(75, 90, 127) != BlocksJRMC.BlockColoredStone && ChkInStSpawn) {
                    ChkInStSpawn = false;
                    this.ChkInSt = new ChkInSt();
                    this.ChkInSt.func_76484_a(w, w.field_73012_v, 0, 0, 0);
                }
                if (w.func_147439_a(75, 90, -5) != BlocksJRMC.BlockColoredStone && SnkWySpawn) {
                    SnkWySpawn = false;
                    this.SnkWy = new SnkWy();
                    this.SnkWy.func_76484_a(w, w.field_73012_v, 0, 0, 0);
                }
            }
            if (JRMCoreConfig.NPCSpawnCheck) {
                AxisAlignedBB aabbjin;
                List jin;
                AxisAlignedBB aabbkaio;
                List kaio;
                AxisAlignedBB par2AxisAlignedBB = AxisAlignedBB.func_72330_a((double)60.0, (double)10.0, (double)35.0, (double)90.0, (double)110.0, (double)65.0);
                List enma = w.func_72872_a(EntityMasterEnma.class, par2AxisAlignedBB);
                if (enma.isEmpty()) {
                    EntityMasterEnma namekian03 = new EntityMasterEnma(w);
                    namekian03.func_70012_b(75.0, 91.0, 53.0, 0.0f, 0.0f);
                    w.func_72838_d((Entity)namekian03);
                }
                if ((kaio = w.func_72872_a(EntityMasterKaio.class, aabbkaio = AxisAlignedBB.func_72330_a((double)87.0, (double)1.0, (double)-3739.0, (double)127.0, (double)140.0, (double)-3699.0))).isEmpty()) {
                    EntityMasterKaio namekian03 = new EntityMasterKaio(w);
                    namekian03.func_70012_b(107.0, 116.0, -3719.0, 0.0f, 0.0f);
                    w.func_72838_d((Entity)namekian03);
                }
                if ((jin = w.func_72872_a(EntityMasterJin.class, aabbjin = AxisAlignedBB.func_72330_a((double)87.0, (double)1.0, (double)-3742.0, (double)127.0, (double)140.0, (double)-3702.0))).isEmpty()) {
                    EntityMasterJin namekian03 = new EntityMasterJin(w);
                    namekian03.func_70012_b(107.0, 116.0, -3722.0, 0.0f, 0.0f);
                    w.func_72838_d((Entity)namekian03);
                }
            }
        }
    }

    public static enum Type {
        NORMAL,
        UNDER,
        WATER;

    }
}

