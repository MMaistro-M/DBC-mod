/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.effect.EntityLightningBolt
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntitySign
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.util.BlockSnapshot
 *  net.minecraftforge.common.util.FakePlayer
 *  net.minecraftforge.event.world.BlockEvent$BreakEvent
 *  net.minecraftforge.event.world.BlockEvent$PlaceEvent
 */
package noppes.npcs.scripted;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.eventhandler.Event;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import kamkeel.npcs.entity.EntityAbilityBeam;
import kamkeel.npcs.entity.EntityAbilityDisc;
import kamkeel.npcs.entity.EntityAbilityLaser;
import kamkeel.npcs.entity.EntityAbilityOrb;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.world.BlockEvent;
import noppes.npcs.CustomNpcs;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.IBlock;
import noppes.npcs.api.IParticle;
import noppes.npcs.api.IPos;
import noppes.npcs.api.ITileEntity;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IEnergyBeam;
import noppes.npcs.api.entity.IEnergyDisc;
import noppes.npcs.api.entity.IEnergyLaser;
import noppes.npcs.api.entity.IEnergyOrb;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.ISound;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.api.scoreboard.IScoreboard;
import noppes.npcs.blocks.tiles.TileBigSign;
import noppes.npcs.controllers.ServerCloneController;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.scoreboard.ScriptScoreboard;

public class ScriptWorld
implements IWorld {
    public WorldServer world;

    public ScriptWorld(WorldServer world) {
        this.world = world;
    }

    public static ScriptWorld createNew(int dimensionId) {
        WorldServer[] worlds = CustomNpcs.getServer().field_71305_c;
        WorldServer world = worlds[0];
        for (WorldServer w : worlds) {
            if (w.field_73011_w.field_76574_g != dimensionId) continue;
            world = w;
        }
        return new ScriptWorld(world);
    }

    @Override
    public long getTime() {
        return this.world.func_72820_D();
    }

    @Override
    public long getTotalTime() {
        return this.world.func_82737_E();
    }

    @Override
    public boolean areAllPlayersAsleep() {
        return this.world.func_73056_e();
    }

    @Override
    public IBlock getBlock(int x, int y, int z) {
        return NpcAPI.Instance().getIBlock(this, x, y, z);
    }

    @Override
    public IBlock getBlock(IPos pos) {
        return NpcAPI.Instance().getIBlock(this, pos);
    }

    @Override
    public boolean isBlockFreezable(IPos pos) {
        return this.isBlockFreezable(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public boolean isBlockFreezable(int x, int y, int z) {
        return this.world.func_72884_u(x, y, z);
    }

    @Override
    public boolean isBlockFreezableNaturally(IPos pos) {
        return this.isBlockFreezableNaturally(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public boolean isBlockFreezableNaturally(int x, int y, int z) {
        return this.world.func_72850_v(x, y, z);
    }

    @Override
    public boolean canBlockFreeze(IPos pos, boolean adjacentToWater) {
        return this.canBlockFreeze(pos.getX(), pos.getY(), pos.getZ(), adjacentToWater);
    }

    @Override
    public boolean canBlockFreeze(int x, int y, int z, boolean adjacentToWater) {
        return this.world.func_72834_c(x, y, z, adjacentToWater);
    }

    @Override
    public boolean canBlockFreezeBody(IPos pos, boolean adjacentToWater) {
        return this.canBlockFreezeBody(pos.getX(), pos.getY(), pos.getZ(), adjacentToWater);
    }

    @Override
    public boolean canBlockFreezeBody(int x, int y, int z, boolean adjacentToWater) {
        return this.world.canBlockFreezeBody(x, y, z, adjacentToWater);
    }

    @Override
    public boolean canSnowAt(IPos pos, boolean checkLight) {
        return this.canSnowAt(pos.getX(), pos.getY(), pos.getZ(), checkLight);
    }

    @Override
    public boolean canSnowAt(int x, int y, int z, boolean checkLight) {
        return this.world.func_147478_e(x, y, z, checkLight);
    }

    @Override
    public boolean canSnowAtBody(IPos pos, boolean checkLight) {
        return this.canSnowAtBody(pos.getX(), pos.getY(), pos.getZ(), checkLight);
    }

    @Override
    public boolean canSnowAtBody(int x, int y, int z, boolean checkLight) {
        return this.world.canSnowAtBody(x, y, z, checkLight);
    }

    @Override
    public IBlock getTopBlock(int x, int z) {
        int yAbove = this.world.func_72825_h(x, z);
        if (yAbove <= 0) {
            return null;
        }
        return NpcAPI.Instance().getIBlock(this, x, yAbove - 1, z);
    }

    @Override
    public IBlock getTopBlock(IPos pos) {
        return this.getTopBlock(pos.getX(), pos.getZ());
    }

    @Override
    public int getHeightValue(int x, int z) {
        return this.world.func_72976_f(x, z);
    }

    @Override
    public int getHeightValue(IPos pos) {
        return this.getHeightValue(pos.getX(), pos.getZ());
    }

    @Override
    public int getChunkHeightMapMinimum(int x, int z) {
        return this.world.func_82734_g(x, z);
    }

    @Override
    public int getChunkHeightMapMinimum(IPos pos) {
        return this.getChunkHeightMapMinimum(pos.getX(), pos.getZ());
    }

    @Override
    public int getBlockMetadata(int x, int y, int z) {
        return this.world.func_72805_g(x, y, z);
    }

    @Override
    public int getBlockMetadata(IPos pos) {
        return this.getBlockMetadata(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public boolean setBlockMetadataWithNotify(int x, int y, int z, int metadata, int flag) {
        return this.world.func_72921_c(x, y, z, metadata, flag);
    }

    @Override
    public boolean setBlockMetadataWithNotify(IPos pos, int metadata, int flag) {
        return this.setBlockMetadataWithNotify(pos.getX(), pos.getY(), pos.getZ(), metadata, flag);
    }

    @Override
    public boolean canSeeSky(int x, int y, int z) {
        return this.world.func_72937_j(x, y, z);
    }

    @Override
    public boolean canSeeSky(IPos pos) {
        return this.canSeeSky(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public int getFullBlockLightValue(int x, int y, int z) {
        return this.world.func_72883_k(x, y, z);
    }

    @Override
    public int getFullBlockLightValue(IPos pos) {
        return this.getFullBlockLightValue(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public int getBlockLightValue(int x, int y, int z) {
        return this.world.func_72957_l(x, y, z);
    }

    @Override
    public int getBlockLightValue(IPos pos) {
        return this.getBlockLightValue(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public void playSoundAtEntity(IEntity entity, String sound, float volume, float pitch) {
        this.world.func_72956_a(entity.getMCEntity(), sound, volume, pitch);
    }

    @Override
    public void playSoundToNearExcept(IPlayer player, String sound, float volume, float pitch) {
        this.world.func_85173_a((EntityPlayer)((EntityPlayerMP)player.getMCEntity()), sound, volume, pitch);
    }

    @Override
    public void playSound(int id, ISound sound) {
        IPlayer[] players;
        for (IPlayer player : players = this.getAllServerPlayers()) {
            if (player.getDimension() != this.getDimensionID()) continue;
            player.playSound(id, sound);
        }
    }

    @Override
    public void stopSound(int id) {
        IPlayer[] players;
        for (IPlayer player : players = this.getAllServerPlayers()) {
            if (player.getDimension() != this.getDimensionID()) continue;
            player.stopSound(id);
        }
    }

    @Override
    public void pauseSounds() {
        IPlayer[] players;
        for (IPlayer player : players = this.getAllServerPlayers()) {
            if (player.getDimension() != this.getDimensionID()) continue;
            player.pauseSounds();
        }
    }

    @Override
    public void continueSounds() {
        IPlayer[] players;
        for (IPlayer player : players = this.getAllServerPlayers()) {
            if (player.getDimension() != this.getDimensionID()) continue;
            player.continueSounds();
        }
    }

    @Override
    public void stopSounds() {
        IPlayer[] players;
        for (IPlayer player : players = this.getAllServerPlayers()) {
            if (player.getDimension() != this.getDimensionID()) continue;
            player.stopSounds();
        }
    }

    @Override
    public IEntity getEntityByID(int id) {
        return NpcAPI.Instance().getIEntity(this.world.func_73045_a(id));
    }

    @Override
    public boolean spawnEntityInWorld(IEntity entity) {
        return this.world.func_72838_d(entity.getMCEntity());
    }

    public boolean spawnEntityInWorld(IEntity entity, double x, double y, double z) {
        entity.setPosition(x, y, z);
        return this.spawnEntityInWorld(entity);
    }

    public boolean spawnEntityInWorld(IEntity entity, IPos pos) {
        return this.spawnEntityInWorld(entity, pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public IPlayer getClosestPlayerToEntity(IEntity entity, double range) {
        return (IPlayer)NpcAPI.Instance().getIEntity((Entity)this.world.func_72890_a(entity.getMCEntity(), range));
    }

    @Override
    public IPlayer getClosestPlayer(double x, double y, double z, double range) {
        return (IPlayer)NpcAPI.Instance().getIEntity((Entity)this.world.func_72977_a(x, y, z, range));
    }

    @Override
    public IPlayer getClosestPlayer(IPos pos, double range) {
        return this.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), range);
    }

    @Override
    public IPlayer getClosestVulnerablePlayerToEntity(IEntity entity, double range) {
        return (IPlayer)NpcAPI.Instance().getIEntity((Entity)this.world.func_72856_b(entity.getMCEntity(), range));
    }

    @Override
    public IPlayer getClosestVulnerablePlayer(double x, double y, double z, double range) {
        return (IPlayer)NpcAPI.Instance().getIEntity((Entity)this.world.func_72846_b(x, y, z, range));
    }

    @Override
    public IPlayer getClosestVulnerablePlayer(IPos pos, double range) {
        return this.getClosestVulnerablePlayer(pos.getX(), pos.getY(), pos.getZ(), range);
    }

    @Override
    public int countEntities(IEntity entity) {
        return this.world.func_72907_a(entity.getMCEntity().getClass());
    }

    @Override
    public IEntity[] getLoadedEntities() {
        ArrayList list = new ArrayList();
        for (Object obj : this.world.field_72996_f) {
            list.add(NpcAPI.Instance().getIEntity((Entity)obj));
        }
        return list.toArray(new IEntity[0]);
    }

    @Override
    public IEntity[] getEntitiesNear(IPos position, double range) {
        ArrayList list = new ArrayList();
        List entities = this.world.func_72872_a(Entity.class, AxisAlignedBB.func_72330_a((double)((double)position.getX() - range), (double)((double)position.getY() - range), (double)((double)position.getZ() - range), (double)((double)position.getX() + range), (double)((double)position.getY() + range), (double)((double)position.getZ() + range)));
        for (Entity entity : entities) {
            list.add(NpcAPI.Instance().getIEntity(entity));
        }
        list.sort((e1, e2) -> {
            double dist2;
            double dist1 = e1.getPosition().distanceTo(position);
            if (dist1 > (dist2 = e2.getPosition().distanceTo(position))) {
                return 1;
            }
            if (dist1 < dist2) {
                return -1;
            }
            return 0;
        });
        return list.toArray(new IEntity[0]);
    }

    @Override
    public IEntity[] getEntitiesNear(double x, double y, double z, double range) {
        return this.getEntitiesNear(NpcAPI.Instance().getIPos(x, y, z), range);
    }

    @Override
    public void setTileEntity(int x, int y, int z, ITileEntity tileEntity) {
        this.world.func_147455_a(x, y, z, tileEntity.getMCTileEntity());
    }

    @Override
    public void setTileEntity(IPos pos, ITileEntity tileEntity) {
        this.setTileEntity(pos.getX(), pos.getY(), pos.getZ(), tileEntity);
    }

    @Override
    public void removeTileEntity(int x, int y, int z) {
        this.world.func_147475_p(x, y, z);
    }

    @Override
    public void removeTileEntity(IPos pos) {
        this.removeTileEntity(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public boolean isBlockFullCube(int x, int y, int z) {
        return this.world.func_147469_q(x, y, z);
    }

    @Override
    public boolean isBlockFullCube(IPos pos) {
        return this.isBlockFullCube(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public long getSeed() {
        return this.world.func_72905_C();
    }

    @Override
    public void setSpawnLocation(int x, int y, int z) {
        this.world.func_72950_A(x, y, z);
    }

    @Override
    public void setSpawnLocation(IPos pos) {
        this.setSpawnLocation(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public boolean canLightningStrikeAt(int x, int y, int z) {
        return this.world.func_72951_B(x, y, z);
    }

    @Override
    public boolean canLightningStrikeAt(IPos pos) {
        return this.canLightningStrikeAt(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public boolean isBlockHighHumidity(int x, int y, int z) {
        return this.world.func_72958_C(x, y, z);
    }

    @Override
    public boolean isBlockHighHumidity(IPos pos) {
        return this.canLightningStrikeAt(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public String getSignText(int x, int y, int z) {
        TileEntity tile = this.world.func_147438_o(x, y, z);
        if (tile instanceof TileBigSign) {
            return ((TileBigSign)tile).getText();
        }
        if (tile instanceof TileEntitySign) {
            TileEntitySign tileSign = (TileEntitySign)tile;
            String s = tileSign.field_145915_a[0] + "\n";
            s = s + tileSign.field_145915_a[1] + "\n";
            s = s + tileSign.field_145915_a[2] + "\n";
            s = s + tileSign.field_145915_a[3];
            return s;
        }
        return null;
    }

    @Override
    public String getSignText(IPos pos) {
        return this.getSignText(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public boolean setBlock(int x, int y, int z, IBlock block) {
        if (block == null || block.getMCBlock().isAir((IBlockAccess)this.world, x, y, z)) {
            this.removeBlock(x, y, z);
            return false;
        }
        return this.world.func_147449_b(x, y, z, block.getMCBlock());
    }

    @Override
    public boolean setBlock(IPos pos, IBlock block) {
        return this.setBlock(pos.getX(), pos.getY(), pos.getZ(), block);
    }

    @Override
    public boolean setBlock(int x, int y, int z, IItemStack item) {
        if (item == null) {
            this.removeBlock(x, y, z);
            return false;
        }
        Block block = Block.func_149634_a((Item)item.getMCItemStack().func_77973_b());
        if (block == null || block == Blocks.field_150350_a) {
            return false;
        }
        return this.world.func_147449_b(x, y, z, block);
    }

    @Override
    public boolean setBlock(IPos pos, IItemStack itemStack) {
        return this.setBlock(pos.getX(), pos.getY(), pos.getZ(), itemStack);
    }

    @Override
    public void removeBlock(int x, int y, int z) {
        this.world.func_147449_b(x, y, z, Blocks.field_150350_a);
    }

    @Override
    public void removeBlock(IPos pos) {
        this.removeBlock(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public boolean isPlaceCancelled(int posX, int posY, int posZ) {
        IBlock block = this.getBlock(posX, posY, posZ);
        if (block == null) {
            return false;
        }
        Block mcBlock = block.getMCBlock();
        int metadata = this.getBlockMetadata(posX, posY, posZ);
        FakePlayer fakePlayer = new FakePlayer(this.world, (GameProfile)EntityNPCInterface.chateventProfile);
        IItemStack stack = NpcAPI.Instance().createItem("minecraft:stone", 0, 1);
        fakePlayer.func_70062_b(0, stack.getMCItemStack());
        BlockEvent.PlaceEvent placeEvent = new BlockEvent.PlaceEvent(new BlockSnapshot((World)this.world, (int)Math.floor(posX), (int)Math.floor(posY), (int)Math.floor(posZ), mcBlock, metadata), Blocks.field_150350_a, (EntityPlayer)fakePlayer);
        MinecraftForge.EVENT_BUS.post((Event)placeEvent);
        return placeEvent.isCanceled();
    }

    @Override
    public boolean isPlaceCancelled(IPos pos) {
        return this.isPlaceCancelled(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public boolean isBreakCancelled(int posX, int posY, int posZ) {
        IBlock block = this.getBlock(posX, posY, posZ);
        if (block == null) {
            return false;
        }
        Block mcBlock = block.getMCBlock();
        int metadata = this.getBlockMetadata(posX, posY, posZ);
        FakePlayer fakePlayer = new FakePlayer(this.world, (GameProfile)EntityNPCInterface.chateventProfile);
        IItemStack stack = NpcAPI.Instance().createItem("minecraft:stone", 0, 1);
        fakePlayer.func_70062_b(0, stack.getMCItemStack());
        BlockEvent.BreakEvent placeEvent = new BlockEvent.BreakEvent(posX, posY, posZ, (World)this.world, mcBlock, metadata, (EntityPlayer)fakePlayer);
        MinecraftForge.EVENT_BUS.post((Event)placeEvent);
        return placeEvent.isCanceled();
    }

    @Override
    public boolean isBreakCancelled(IPos pos) {
        return this.isBreakCancelled(pos.getX(), pos.getY(), pos.getZ());
    }

    public MovingObjectPosition rayCast(Vec3 startVec, Vec3 endVec, boolean stopOnBlock, boolean stopOnLiquid, boolean stopOnCollision) {
        if (!(Double.isNaN(startVec.field_72450_a) || Double.isNaN(startVec.field_72448_b) || Double.isNaN(startVec.field_72449_c))) {
            if (!(Double.isNaN(endVec.field_72450_a) || Double.isNaN(endVec.field_72448_b) || Double.isNaN(endVec.field_72449_c))) {
                MovingObjectPosition movingobjectposition;
                int k1;
                int j1;
                int i1;
                int endX = MathHelper.func_76128_c((double)endVec.field_72450_a);
                int endY = MathHelper.func_76128_c((double)endVec.field_72448_b);
                int endZ = MathHelper.func_76128_c((double)endVec.field_72449_c);
                int l = MathHelper.func_76128_c((double)startVec.field_72450_a);
                Block block = this.world.func_147439_a(l, i1 = MathHelper.func_76128_c((double)startVec.field_72448_b), j1 = MathHelper.func_76128_c((double)startVec.field_72449_c));
                if (block.func_149678_a(k1 = this.world.func_72805_g(l, i1, j1), false) && (movingobjectposition = block.func_149731_a((World)this.world, l, i1, j1, startVec, endVec)) != null) {
                    return movingobjectposition;
                }
                k1 = 200;
                while (k1-- >= 0) {
                    int b0;
                    if (Double.isNaN(startVec.field_72450_a) || Double.isNaN(startVec.field_72448_b) || Double.isNaN(startVec.field_72449_c)) {
                        return null;
                    }
                    if (l == endX && i1 == endY && j1 == endZ) {
                        return null;
                    }
                    boolean flag6 = true;
                    boolean flag3 = true;
                    boolean flag4 = true;
                    double d0 = 999.0;
                    double d1 = 999.0;
                    double d2 = 999.0;
                    if (endX > l) {
                        d0 = (double)l + 1.0;
                    } else if (endX < l) {
                        d0 = (double)l + 0.0;
                    } else {
                        flag6 = false;
                    }
                    if (endY > i1) {
                        d1 = (double)i1 + 1.0;
                    } else if (endY < i1) {
                        d1 = (double)i1 + 0.0;
                    } else {
                        flag3 = false;
                    }
                    if (endZ > j1) {
                        d2 = (double)j1 + 1.0;
                    } else if (endZ < j1) {
                        d2 = (double)j1 + 0.0;
                    } else {
                        flag4 = false;
                    }
                    double d3 = 999.0;
                    double d4 = 999.0;
                    double d5 = 999.0;
                    double d6 = endVec.field_72450_a - startVec.field_72450_a;
                    double d7 = endVec.field_72448_b - startVec.field_72448_b;
                    double d8 = endVec.field_72449_c - startVec.field_72449_c;
                    if (flag6) {
                        d3 = (d0 - startVec.field_72450_a) / d6;
                    }
                    if (flag3) {
                        d4 = (d1 - startVec.field_72448_b) / d7;
                    }
                    if (flag4) {
                        d5 = (d2 - startVec.field_72449_c) / d8;
                    }
                    if (d3 < d4 && d3 < d5) {
                        b0 = endX > l ? 4 : 5;
                        startVec.field_72450_a = d0;
                        startVec.field_72448_b += d7 * d3;
                        startVec.field_72449_c += d8 * d3;
                    } else if (d4 < d5) {
                        b0 = endY > i1 ? 0 : 1;
                        startVec.field_72450_a += d6 * d4;
                        startVec.field_72448_b = d1;
                        startVec.field_72449_c += d8 * d4;
                    } else {
                        b0 = endZ > j1 ? 2 : 3;
                        startVec.field_72450_a += d6 * d5;
                        startVec.field_72448_b += d7 * d5;
                        startVec.field_72449_c = d2;
                    }
                    Vec3 vec32 = Vec3.func_72443_a((double)startVec.field_72450_a, (double)startVec.field_72448_b, (double)startVec.field_72449_c);
                    vec32.field_72450_a = MathHelper.func_76128_c((double)startVec.field_72450_a);
                    l = (int)vec32.field_72450_a;
                    if (b0 == 5) {
                        --l;
                        vec32.field_72450_a += 1.0;
                    }
                    vec32.field_72448_b = MathHelper.func_76128_c((double)startVec.field_72448_b);
                    i1 = (int)vec32.field_72448_b;
                    if (b0 == 1) {
                        --i1;
                        vec32.field_72448_b += 1.0;
                    }
                    vec32.field_72449_c = MathHelper.func_76128_c((double)startVec.field_72449_c);
                    j1 = (int)vec32.field_72449_c;
                    if (b0 == 3) {
                        --j1;
                        vec32.field_72449_c += 1.0;
                    }
                    Block block1 = this.world.func_147439_a(l, i1, j1);
                    int l1 = this.world.func_72805_g(l, i1, j1);
                    MovingObjectPosition movingobjectposition1 = block1.func_149731_a((World)this.world, l, i1, j1, startVec, endVec);
                    if (movingobjectposition1 == null || !(block1.func_149678_a(l1, false) && stopOnBlock || block1.func_149688_o().func_76224_d() && stopOnLiquid) && (block1 instanceof BlockAir || block1.func_149668_a((World)this.world, l, i1, j1) != null || stopOnCollision)) continue;
                    return movingobjectposition1;
                }
                return null;
            }
            return null;
        }
        return null;
    }

    @Override
    public IPos rayCastPos(double[] startPos, double[] lookVector, int maxDistance, boolean stopOnBlock, boolean stopOnLiquid, boolean stopOnCollision) {
        if (startPos.length != 3 || lookVector.length != 3) {
            return null;
        }
        Vec3 startVec = Vec3.func_72443_a((double)startPos[0], (double)startPos[1], (double)startPos[2]);
        Vec3 endVec = startVec.func_72441_c(lookVector[0] * (double)maxDistance, lookVector[1] * (double)maxDistance, lookVector[2] * (double)maxDistance);
        IPos endPos = NpcAPI.Instance().getIPos(endVec.field_72450_a, endVec.field_72448_b, endVec.field_72449_c);
        if (!(stopOnBlock || stopOnLiquid || stopOnCollision)) {
            return endPos;
        }
        MovingObjectPosition mob = this.rayCast(startVec, endVec, stopOnBlock, stopOnLiquid, stopOnCollision);
        return mob != null ? NpcAPI.Instance().getIPos(mob.field_72311_b, mob.field_72312_c, mob.field_72309_d) : endPos;
    }

    @Override
    public IPos rayCastPos(double[] startPos, double[] lookVector, int maxDistance) {
        return this.rayCastPos(startPos, lookVector, maxDistance, true, false, false);
    }

    @Override
    public IPos rayCastPos(IPos startPos, IPos lookVector, int maxDistance, boolean stopOnBlock, boolean stopOnLiquid, boolean stopOnCollision) {
        return this.rayCastPos(new double[]{startPos.getX(), startPos.getY(), startPos.getZ()}, lookVector.normalizeDouble(), maxDistance, stopOnBlock, stopOnLiquid, stopOnCollision);
    }

    @Override
    public IPos rayCastPos(IPos startPos, IPos lookVector, int maxDistance) {
        return this.rayCastPos(new double[]{startPos.getX(), startPos.getY(), startPos.getZ()}, lookVector.normalizeDouble(), maxDistance, true, false, false);
    }

    @Override
    public IBlock rayCastBlock(double[] startPos, double[] lookVector, int maxDistance, boolean stopOnBlock, boolean stopOnLiquid, boolean stopOnCollision) {
        return this.getBlock(this.rayCastPos(startPos, lookVector, maxDistance, stopOnBlock, stopOnLiquid, stopOnCollision));
    }

    @Override
    public IBlock rayCastBlock(double[] startPos, double[] lookVector, int maxDistance) {
        return this.rayCastBlock(startPos, lookVector, maxDistance, true, false, false);
    }

    @Override
    public IBlock rayCastBlock(IPos startPos, IPos lookVector, int maxDistance, boolean stopOnBlock, boolean stopOnLiquid, boolean stopOnCollision) {
        return this.rayCastBlock(new double[]{startPos.getX(), startPos.getY(), startPos.getZ()}, lookVector.normalizeDouble(), maxDistance, stopOnBlock, stopOnLiquid, stopOnCollision);
    }

    @Override
    public IBlock rayCastBlock(IPos startPos, IPos lookVector, int maxDistance) {
        return this.rayCastBlock(new double[]{startPos.getX(), startPos.getY(), startPos.getZ()}, lookVector.normalizeDouble(), maxDistance);
    }

    @Override
    public IPos getNearestAir(IPos pos, int maxHeight) {
        if (pos == null) {
            return null;
        }
        IPos currentPos = pos;
        IBlock block = null;
        int rep = 0;
        while (rep++ < maxHeight && (block = this.getBlock(currentPos = currentPos.add(1.0, 0.0, 0.0))) != null && (block = this.getBlock(currentPos = currentPos.add(-2.0, 0.0, 0.0))) != null && (block = this.getBlock(currentPos = currentPos.add(1.0, 0.0, 1.0))) != null && (block = this.getBlock(currentPos = currentPos.add(0.0, 0.0, -2.0))) != null && (block = this.getBlock(currentPos = currentPos.add(0.0, 1.0, 1.0))) != null) {
        }
        return currentPos;
    }

    @Override
    public IEntity[] rayCastEntities(double[] startPos, double[] lookVector, int maxDistance, double offset, double range, boolean stopOnBlock, boolean stopOnLiquid, boolean stopOnCollision) {
        return this.rayCastEntities(null, startPos, lookVector, maxDistance, offset, range, stopOnBlock, stopOnLiquid, stopOnCollision);
    }

    @Override
    public IEntity[] rayCastEntities(IEntity[] ignoreEntities, double[] startPos, double[] lookVector, int maxDistance, double offset, double range, boolean stopOnBlock, boolean stopOnLiquid, boolean stopOnCollision) {
        if (ignoreEntities == null) {
            ignoreEntities = new IEntity[]{};
        }
        Vec3 startVec = Vec3.func_72443_a((double)startPos[0], (double)startPos[1], (double)startPos[2]);
        Vec3 endVec = startVec.func_72441_c(lookVector[0] * (double)maxDistance, lookVector[1] * (double)maxDistance, lookVector[2] * (double)maxDistance);
        startVec = startVec.func_72441_c(lookVector[0] * offset, lookVector[1] * offset, lookVector[2] * offset);
        LinkedHashSet<IEntity> ignoredEntitiesSet = new LinkedHashSet<IEntity>();
        Collections.addAll(ignoredEntitiesSet, ignoreEntities);
        Set<IEntity<?>> entities = this.rayCastEntities(ignoredEntitiesSet, startVec, endVec, range, stopOnBlock, stopOnLiquid, stopOnCollision);
        return entities.toArray(new IEntity[0]);
    }

    public Set<IEntity<?>> rayCastEntities(LinkedHashSet<IEntity> ignoredEntitiesSet, Vec3 startVec, Vec3 endVec, double range, boolean stopOnBlock, boolean stopOnLiquid, boolean stopOnCollision) {
        LinkedHashSet entities = new LinkedHashSet();
        if (!(Double.isNaN(startVec.field_72450_a) || Double.isNaN(startVec.field_72448_b) || Double.isNaN(startVec.field_72449_c))) {
            if (!(Double.isNaN(endVec.field_72450_a) || Double.isNaN(endVec.field_72448_b) || Double.isNaN(endVec.field_72449_c))) {
                MovingObjectPosition movingobjectposition;
                IEntity[] surrounding;
                int endX = MathHelper.func_76128_c((double)endVec.field_72450_a);
                int endY = MathHelper.func_76128_c((double)endVec.field_72448_b);
                int endZ = MathHelper.func_76128_c((double)endVec.field_72449_c);
                int l = MathHelper.func_76128_c((double)startVec.field_72450_a);
                int i1 = MathHelper.func_76128_c((double)startVec.field_72448_b);
                int j1 = MathHelper.func_76128_c((double)startVec.field_72449_c);
                Block block = this.world.func_147439_a(l, i1, j1);
                int k1 = this.world.func_72805_g(l, i1, j1);
                for (IEntity entity : surrounding = this.getEntitiesNear(l, i1, j1, range)) {
                    if (ignoredEntitiesSet.contains(entity)) continue;
                    entities.add(entity);
                }
                if (block.func_149678_a(k1, false) && (movingobjectposition = block.func_149731_a((World)this.world, l, i1, j1, startVec, endVec)) != null) {
                    return entities;
                }
                k1 = 200;
                while (k1-- >= 0) {
                    IEntity[] surroundingEntities;
                    int b0;
                    if (Double.isNaN(startVec.field_72450_a) || Double.isNaN(startVec.field_72448_b) || Double.isNaN(startVec.field_72449_c)) {
                        return entities;
                    }
                    if (l == endX && i1 == endY && j1 == endZ) {
                        return entities;
                    }
                    boolean flag6 = true;
                    boolean flag3 = true;
                    boolean flag4 = true;
                    double d0 = 999.0;
                    double d1 = 999.0;
                    double d2 = 999.0;
                    if (endX > l) {
                        d0 = (double)l + 1.0;
                    } else if (endX < l) {
                        d0 = (double)l + 0.0;
                    } else {
                        flag6 = false;
                    }
                    if (endY > i1) {
                        d1 = (double)i1 + 1.0;
                    } else if (endY < i1) {
                        d1 = (double)i1 + 0.0;
                    } else {
                        flag3 = false;
                    }
                    if (endZ > j1) {
                        d2 = (double)j1 + 1.0;
                    } else if (endZ < j1) {
                        d2 = (double)j1 + 0.0;
                    } else {
                        flag4 = false;
                    }
                    double d3 = 999.0;
                    double d4 = 999.0;
                    double d5 = 999.0;
                    double d6 = endVec.field_72450_a - startVec.field_72450_a;
                    double d7 = endVec.field_72448_b - startVec.field_72448_b;
                    double d8 = endVec.field_72449_c - startVec.field_72449_c;
                    if (flag6) {
                        d3 = (d0 - startVec.field_72450_a) / d6;
                    }
                    if (flag3) {
                        d4 = (d1 - startVec.field_72448_b) / d7;
                    }
                    if (flag4) {
                        d5 = (d2 - startVec.field_72449_c) / d8;
                    }
                    if (d3 < d4 && d3 < d5) {
                        b0 = endX > l ? 4 : 5;
                        startVec.field_72450_a = d0;
                        startVec.field_72448_b += d7 * d3;
                        startVec.field_72449_c += d8 * d3;
                    } else if (d4 < d5) {
                        b0 = endY > i1 ? 0 : 1;
                        startVec.field_72450_a += d6 * d4;
                        startVec.field_72448_b = d1;
                        startVec.field_72449_c += d8 * d4;
                    } else {
                        b0 = endZ > j1 ? 2 : 3;
                        startVec.field_72450_a += d6 * d5;
                        startVec.field_72448_b += d7 * d5;
                        startVec.field_72449_c = d2;
                    }
                    Vec3 vec32 = Vec3.func_72443_a((double)startVec.field_72450_a, (double)startVec.field_72448_b, (double)startVec.field_72449_c);
                    vec32.field_72450_a = MathHelper.func_76128_c((double)startVec.field_72450_a);
                    l = (int)vec32.field_72450_a;
                    if (b0 == 5) {
                        --l;
                        vec32.field_72450_a += 1.0;
                    }
                    vec32.field_72448_b = MathHelper.func_76128_c((double)startVec.field_72448_b);
                    i1 = (int)vec32.field_72448_b;
                    if (b0 == 1) {
                        --i1;
                        vec32.field_72448_b += 1.0;
                    }
                    vec32.field_72449_c = MathHelper.func_76128_c((double)startVec.field_72449_c);
                    j1 = (int)vec32.field_72449_c;
                    if (b0 == 3) {
                        --j1;
                        vec32.field_72449_c += 1.0;
                    }
                    Block block1 = this.world.func_147439_a(l, i1, j1);
                    int l1 = this.world.func_72805_g(l, i1, j1);
                    for (IEntity entity : surroundingEntities = this.getEntitiesNear(l, i1, j1, range)) {
                        if (ignoredEntitiesSet.contains(entity)) continue;
                        entities.add(entity);
                    }
                    MovingObjectPosition movingobjectposition1 = block1.func_149731_a((World)this.world, l, i1, j1, startVec, endVec);
                    if (movingobjectposition1 == null || !(block1.func_149678_a(l1, false) && stopOnBlock || block1.func_149688_o().func_76224_d() && stopOnLiquid) && (block1 instanceof BlockAir || block1.func_149668_a((World)this.world, l, i1, j1) != null || stopOnCollision)) continue;
                    return entities;
                }
                return entities;
            }
            return entities;
        }
        return entities;
    }

    @Override
    public IEntity[] rayCastEntities(IPos startPos, IPos lookVector, int maxDistance, double offset, double range, boolean stopOnBlock, boolean stopOnLiquid, boolean stopOnCollision) {
        return this.rayCastEntities(new double[]{startPos.getX(), startPos.getY(), startPos.getZ()}, lookVector.normalizeDouble(), maxDistance, offset, range, stopOnBlock, stopOnLiquid, stopOnCollision);
    }

    @Override
    public IEntity[] rayCastEntities(double[] startPos, double[] lookVector, int maxDistance, double offset, double range) {
        return this.rayCastEntities(startPos, lookVector, maxDistance, offset, range, true, false, true);
    }

    @Override
    public IEntity[] rayCastEntities(IPos startPos, IPos lookVector, int maxDistance, double offset, double range) {
        return this.rayCastEntities(new double[]{startPos.getX(), startPos.getY(), startPos.getZ()}, lookVector.normalizeDouble(), maxDistance, offset, range, true, false, true);
    }

    @Override
    @Deprecated
    public IPlayer getPlayer(String name) {
        EntityPlayer player = this.world.func_72924_a(name);
        if (player == null) {
            return null;
        }
        return (IPlayer)NpcAPI.Instance().getIEntity((Entity)player);
    }

    @Override
    public IPlayer getPlayerByUUID(String uuid) {
        return (IPlayer)NpcAPI.Instance().getIEntity((Entity)this.world.func_152378_a(UUID.fromString(uuid)));
    }

    @Override
    public void setTime(long time) {
        this.world.func_72877_b(time);
    }

    @Override
    public boolean isDay() {
        return this.world.func_72820_D() % 24000L < 12000L;
    }

    @Override
    public boolean isRaining() {
        return this.world.func_72912_H().func_76059_o();
    }

    @Override
    public void setRaining(boolean bo) {
        this.world.func_72912_H().func_76084_b(bo);
    }

    @Override
    public void thunderStrike(double x, double y, double z) {
        this.world.func_72942_c((Entity)new EntityLightningBolt((World)this.world, x, y, z));
    }

    @Override
    public void thunderStrike(IPos pos) {
        this.thunderStrike(pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public void spawnParticle(String particle, double x, double y, double z, double dx, double dy, double dz, double speed, int count) {
        this.world.func_147487_a(particle, x, y, z, count, dx, dy, dz, speed);
    }

    @Override
    public void spawnParticle(String particle, IPos pos, double dx, double dy, double dz, double speed, int count) {
        this.spawnParticle(particle, pos.getX(), pos.getY(), pos.getZ(), dx, dy, dz, speed, count);
    }

    @Override
    public IItemStack createItem(String id, int damage, int size) {
        return NpcAPI.Instance().createItem(id, damage, size);
    }

    @Override
    @Deprecated
    public IParticle createEntityParticle(String directory) {
        return NpcAPI.Instance().createEntityParticle(directory);
    }

    @Override
    @Deprecated
    public Object getTempData(String key) {
        return NpcAPI.Instance().getTempData(key);
    }

    @Override
    @Deprecated
    public void setTempData(String key, Object value) {
        NpcAPI.Instance().setTempData(key, value);
    }

    @Override
    @Deprecated
    public boolean hasTempData(String key) {
        return NpcAPI.Instance().hasTempData(key);
    }

    @Override
    @Deprecated
    public void removeTempData(String key) {
        NpcAPI.Instance().removeTempData(key);
    }

    @Override
    @Deprecated
    public void clearTempData() {
        NpcAPI.Instance().clearTempData();
    }

    @Override
    @Deprecated
    public String[] getTempDataKeys() {
        return NpcAPI.Instance().getTempDataKeys();
    }

    @Override
    @Deprecated
    public Object getStoredData(String key) {
        return NpcAPI.Instance().getStoredData(key);
    }

    @Override
    @Deprecated
    public void setStoredData(String key, Object value) {
        NpcAPI.Instance().setStoredData(key, value);
    }

    @Override
    @Deprecated
    public boolean hasStoredData(String key) {
        return NpcAPI.Instance().hasStoredData(key);
    }

    @Override
    @Deprecated
    public void removeStoredData(String key) {
        NpcAPI.Instance().removeStoredData(key);
    }

    @Override
    @Deprecated
    public void clearStoredData() {
        NpcAPI.Instance().clearStoredData();
    }

    @Override
    @Deprecated
    public String[] getStoredDataKeys() {
        return NpcAPI.Instance().getStoredDataKeys();
    }

    @Override
    public void explode(double x, double y, double z, float range, boolean fire, boolean grief) {
        this.world.func_72885_a(null, x, y, z, range, fire, grief);
    }

    @Override
    public void explode(IPos pos, float range, boolean fire, boolean grief) {
        this.explode(pos.getX(), pos.getY(), pos.getZ(), range, fire, grief);
    }

    @Override
    @Deprecated
    public IPlayer[] getAllServerPlayers() {
        List list = MinecraftServer.func_71276_C().func_71203_ab().field_72404_b;
        IPlayer[] arr = new IPlayer[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            arr[i] = (IPlayer)NpcAPI.Instance().getIEntity((Entity)list.get(i));
        }
        return arr;
    }

    @Override
    @Deprecated
    public String[] getPlayerNames() {
        IPlayer[] players = this.getAllServerPlayers();
        String[] names = new String[players.length];
        for (int i = 0; i < names.length; ++i) {
            names[i] = players[i].getDisplayName();
        }
        return names;
    }

    @Override
    public String getBiomeName(int x, int z) {
        return this.world.func_72807_a((int)x, (int)z).field_76791_y;
    }

    @Override
    public String getBiomeName(IPos pos) {
        return this.getBiomeName(pos.getX(), pos.getZ());
    }

    @Override
    public IEntity spawnClone(int x, int y, int z, int tab, String name, boolean ignoreProtection) {
        NBTTagCompound compound = ServerCloneController.Instance.getCloneData(null, name, tab);
        if (compound == null) {
            return null;
        }
        Entity entity = !ignoreProtection ? NoppesUtilServer.spawnCloneWithProtection(compound, x, y, z, (World)this.world) : NoppesUtilServer.spawnClone(compound, x, y, z, (World)this.world);
        return entity == null ? null : NpcAPI.Instance().getIEntity(entity);
    }

    @Override
    public IEntity spawnClone(IPos pos, int tab, String name, boolean ignoreProtection) {
        return this.spawnClone(pos.getX(), pos.getY(), pos.getZ(), tab, name, ignoreProtection);
    }

    @Override
    public IEntity spawnClone(int x, int y, int z, int tab, String name) {
        return this.spawnClone(x, y, z, tab, name, true);
    }

    @Override
    public IEntity spawnClone(IPos pos, int tab, String name) {
        return this.spawnClone(pos.getX(), pos.getY(), pos.getZ(), tab, name);
    }

    @Override
    public IScoreboard getScoreboard() {
        return new ScriptScoreboard();
    }

    @Override
    public WorldServer getMCWorld() {
        return this.world;
    }

    @Override
    public int getDimensionID() {
        return this.world.field_73011_w.field_76574_g;
    }

    public String toString() {
        return "DIM" + this.getDimensionID();
    }

    @Override
    public IEnergyOrb createEnergyOrb(IEntity owner, double x, double y, double z, float size) {
        EntityAbilityOrb entity = new EntityAbilityOrb((World)this.world);
        entity.func_70107_b(x, y, z);
        entity.setStartPosition(x, y, z);
        entity.setOwnerEntityId(owner.getMCEntity().func_145782_y());
        entity.setProjectileSize(size);
        return (IEnergyOrb)NpcAPI.Instance().getIEntity(entity);
    }

    @Override
    public IEnergyBeam createEnergyBeam(IEntity owner, double x, double y, double z, float beamWidth, float headSize) {
        EntityAbilityBeam entity = new EntityAbilityBeam((World)this.world);
        entity.func_70107_b(x, y, z);
        entity.setStartPosition(x, y, z);
        entity.setOwnerEntityId(owner.getMCEntity().func_145782_y());
        entity.setBeamWidth(beamWidth);
        entity.setHeadSize(headSize);
        return (IEnergyBeam)NpcAPI.Instance().getIEntity(entity);
    }

    @Override
    public IEnergyDisc createEnergyDisc(IEntity owner, double x, double y, double z, float radius, float thickness) {
        EntityAbilityDisc entity = new EntityAbilityDisc((World)this.world);
        entity.func_70107_b(x, y, z);
        entity.setStartPosition(x, y, z);
        entity.setOwnerEntityId(owner.getMCEntity().func_145782_y());
        entity.setDiscRadius(radius);
        entity.setDiscThickness(thickness);
        return (IEnergyDisc)NpcAPI.Instance().getIEntity(entity);
    }

    @Override
    public IEnergyLaser createEnergyLaser(IEntity owner, double x, double y, double z, float laserWidth) {
        EntityAbilityLaser entity = new EntityAbilityLaser((World)this.world);
        entity.func_70107_b(x, y, z);
        entity.setStartPosition(x, y, z);
        entity.setOwnerEntityId(owner.getMCEntity().func_145782_y());
        entity.setLaserWidth(laserWidth);
        return (IEnergyLaser)NpcAPI.Instance().getIEntity(entity);
    }

    @Override
    public void broadcast(String message) {
        this.world.func_73046_m().func_71203_ab().func_148539_a((IChatComponent)new ChatComponentText(message));
    }
}

