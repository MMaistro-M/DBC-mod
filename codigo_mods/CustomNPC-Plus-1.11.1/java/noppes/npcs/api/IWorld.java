/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.WorldServer
 */
package noppes.npcs.api;

import net.minecraft.world.WorldServer;
import noppes.npcs.api.IBlock;
import noppes.npcs.api.IParticle;
import noppes.npcs.api.IPos;
import noppes.npcs.api.ITileEntity;
import noppes.npcs.api.entity.IEnergyBeam;
import noppes.npcs.api.entity.IEnergyDisc;
import noppes.npcs.api.entity.IEnergyLaser;
import noppes.npcs.api.entity.IEnergyOrb;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.ISound;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.api.scoreboard.IScoreboard;

public interface IWorld {
    public long getTime();

    public long getTotalTime();

    public boolean areAllPlayersAsleep();

    public IBlock getBlock(int var1, int var2, int var3);

    public IBlock getBlock(IPos var1);

    public IBlock getTopBlock(int var1, int var2);

    public IBlock getTopBlock(IPos var1);

    public boolean isBlockFreezable(IPos var1);

    public boolean isBlockFreezable(int var1, int var2, int var3);

    public boolean isBlockFreezableNaturally(IPos var1);

    public boolean isBlockFreezableNaturally(int var1, int var2, int var3);

    public boolean canBlockFreeze(IPos var1, boolean var2);

    public boolean canBlockFreeze(int var1, int var2, int var3, boolean var4);

    public boolean canBlockFreezeBody(IPos var1, boolean var2);

    public boolean canBlockFreezeBody(int var1, int var2, int var3, boolean var4);

    public boolean canSnowAt(IPos var1, boolean var2);

    public boolean canSnowAt(int var1, int var2, int var3, boolean var4);

    public boolean canSnowAtBody(IPos var1, boolean var2);

    public boolean canSnowAtBody(int var1, int var2, int var3, boolean var4);

    public int getHeightValue(int var1, int var2);

    public int getHeightValue(IPos var1);

    public int getChunkHeightMapMinimum(int var1, int var2);

    public int getChunkHeightMapMinimum(IPos var1);

    public int getBlockMetadata(int var1, int var2, int var3);

    public int getBlockMetadata(IPos var1);

    public boolean setBlockMetadataWithNotify(int var1, int var2, int var3, int var4, int var5);

    public boolean setBlockMetadataWithNotify(IPos var1, int var2, int var3);

    public boolean canSeeSky(int var1, int var2, int var3);

    public boolean canSeeSky(IPos var1);

    public int getFullBlockLightValue(int var1, int var2, int var3);

    public int getFullBlockLightValue(IPos var1);

    public int getBlockLightValue(int var1, int var2, int var3);

    public int getBlockLightValue(IPos var1);

    public void playSoundAtEntity(IEntity var1, String var2, float var3, float var4);

    public void playSoundToNearExcept(IPlayer var1, String var2, float var3, float var4);

    public void playSound(int var1, ISound var2);

    public void stopSound(int var1);

    public void pauseSounds();

    public void continueSounds();

    public void stopSounds();

    public IEntity getEntityByID(int var1);

    public boolean spawnEntityInWorld(IEntity var1);

    public IPlayer getClosestPlayerToEntity(IEntity var1, double var2);

    public IPlayer getClosestPlayer(double var1, double var3, double var5, double var7);

    public IPlayer getClosestPlayer(IPos var1, double var2);

    public IPlayer getClosestVulnerablePlayerToEntity(IEntity var1, double var2);

    public IPlayer getClosestVulnerablePlayer(double var1, double var3, double var5, double var7);

    public IPlayer getClosestVulnerablePlayer(IPos var1, double var2);

    public int countEntities(IEntity var1);

    public IEntity[] getLoadedEntities();

    public IEntity[] getEntitiesNear(IPos var1, double var2);

    public IEntity[] getEntitiesNear(double var1, double var3, double var5, double var7);

    public void setTileEntity(int var1, int var2, int var3, ITileEntity var4);

    public void setTileEntity(IPos var1, ITileEntity var2);

    public void removeTileEntity(int var1, int var2, int var3);

    public void removeTileEntity(IPos var1);

    public boolean isBlockFullCube(int var1, int var2, int var3);

    public boolean isBlockFullCube(IPos var1);

    public long getSeed();

    public void setSpawnLocation(int var1, int var2, int var3);

    public void setSpawnLocation(IPos var1);

    public boolean canLightningStrikeAt(int var1, int var2, int var3);

    public boolean canLightningStrikeAt(IPos var1);

    public boolean isBlockHighHumidity(int var1, int var2, int var3);

    public boolean isBlockHighHumidity(IPos var1);

    public String getSignText(int var1, int var2, int var3);

    public String getSignText(IPos var1);

    public boolean setBlock(int var1, int var2, int var3, IItemStack var4);

    public boolean setBlock(IPos var1, IItemStack var2);

    public boolean setBlock(int var1, int var2, int var3, IBlock var4);

    public boolean setBlock(IPos var1, IBlock var2);

    public void removeBlock(int var1, int var2, int var3);

    public void removeBlock(IPos var1);

    public boolean isPlaceCancelled(int var1, int var2, int var3);

    public boolean isPlaceCancelled(IPos var1);

    public boolean isBreakCancelled(int var1, int var2, int var3);

    public boolean isBreakCancelled(IPos var1);

    public IPos rayCastPos(double[] var1, double[] var2, int var3, boolean var4, boolean var5, boolean var6);

    public IPos rayCastPos(double[] var1, double[] var2, int var3);

    public IPos rayCastPos(IPos var1, IPos var2, int var3, boolean var4, boolean var5, boolean var6);

    public IPos rayCastPos(IPos var1, IPos var2, int var3);

    public IBlock rayCastBlock(double[] var1, double[] var2, int var3, boolean var4, boolean var5, boolean var6);

    public IBlock rayCastBlock(double[] var1, double[] var2, int var3);

    public IBlock rayCastBlock(IPos var1, IPos var2, int var3, boolean var4, boolean var5, boolean var6);

    public IBlock rayCastBlock(IPos var1, IPos var2, int var3);

    public IPos getNearestAir(IPos var1, int var2);

    public IEntity[] rayCastEntities(double[] var1, double[] var2, int var3, double var4, double var6, boolean var8, boolean var9, boolean var10);

    public IEntity[] rayCastEntities(IEntity[] var1, double[] var2, double[] var3, int var4, double var5, double var7, boolean var9, boolean var10, boolean var11);

    public IEntity[] rayCastEntities(IPos var1, IPos var2, int var3, double var4, double var6, boolean var8, boolean var9, boolean var10);

    public IEntity[] rayCastEntities(double[] var1, double[] var2, int var3, double var4, double var6);

    public IEntity[] rayCastEntities(IPos var1, IPos var2, int var3, double var4, double var6);

    public IPlayer getPlayer(String var1);

    public IPlayer getPlayerByUUID(String var1);

    public void setTime(long var1);

    public boolean isDay();

    public boolean isRaining();

    public void setRaining(boolean var1);

    public void thunderStrike(double var1, double var3, double var5);

    public void thunderStrike(IPos var1);

    public void spawnParticle(String var1, double var2, double var4, double var6, double var8, double var10, double var12, double var14, int var16);

    public void spawnParticle(String var1, IPos var2, double var3, double var5, double var7, double var9, int var11);

    public IItemStack createItem(String var1, int var2, int var3);

    @Deprecated
    public IParticle createEntityParticle(String var1);

    @Deprecated
    public Object getTempData(String var1);

    @Deprecated
    public void setTempData(String var1, Object var2);

    @Deprecated
    public boolean hasTempData(String var1);

    @Deprecated
    public void removeTempData(String var1);

    @Deprecated
    public void clearTempData();

    @Deprecated
    public String[] getTempDataKeys();

    @Deprecated
    public Object getStoredData(String var1);

    @Deprecated
    public void setStoredData(String var1, Object var2);

    @Deprecated
    public boolean hasStoredData(String var1);

    @Deprecated
    public void removeStoredData(String var1);

    @Deprecated
    public void clearStoredData();

    public String[] getStoredDataKeys();

    public void explode(double var1, double var3, double var5, float var7, boolean var8, boolean var9);

    public void explode(IPos var1, float var2, boolean var3, boolean var4);

    public IPlayer[] getAllServerPlayers();

    public String[] getPlayerNames();

    public String getBiomeName(int var1, int var2);

    public String getBiomeName(IPos var1);

    public IEntity spawnClone(int var1, int var2, int var3, int var4, String var5, boolean var6);

    public IEntity spawnClone(IPos var1, int var2, String var3, boolean var4);

    public IEntity spawnClone(int var1, int var2, int var3, int var4, String var5);

    public IEntity spawnClone(IPos var1, int var2, String var3);

    public IScoreboard getScoreboard();

    public WorldServer getMCWorld();

    public int getDimensionID();

    public IEnergyOrb createEnergyOrb(IEntity var1, double var2, double var4, double var6, float var8);

    public IEnergyBeam createEnergyBeam(IEntity var1, double var2, double var4, double var6, float var8, float var9);

    public IEnergyDisc createEnergyDisc(IEntity var1, double var2, double var4, double var6, float var8, float var9);

    public IEnergyLaser createEnergyLaser(IEntity var1, double var2, double var4, double var6, float var8);

    public void broadcast(String var1);
}

