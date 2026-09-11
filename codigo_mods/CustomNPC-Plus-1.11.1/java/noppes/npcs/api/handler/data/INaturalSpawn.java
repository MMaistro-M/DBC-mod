/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IEntity;

public interface INaturalSpawn {
    public static final int DESPAWN_FORCE_NATURAL = 0;
    public static final int DESPAWN_PRESERVE_TEMPLATE = 1;
    public static final int DESPAWN_FORCE_PERSISTENT = 2;

    public void setName(String var1);

    public String getName();

    public void setEntity(IEntity var1, int var2);

    public IEntity getEntity(IWorld var1, int var2);

    public Integer[] getSlots();

    public void setWeight(int var1);

    public int getWeight();

    public void setMinHeight(int var1);

    public int getMinHeight();

    public void setMaxHeight(int var1);

    public int getMaxHeight();

    public void spawnsLikeAnimal(boolean var1);

    public boolean spawnsLikeAnimal();

    public void spawnsLikeMonster(boolean var1);

    public boolean spawnsLikeMonster();

    public void spawnsInLiquid(boolean var1);

    public boolean spawnsInLiquid();

    public void spawnsInAir(boolean var1);

    public boolean spawnsInAir();

    public String[] getBiomes();

    public void setBiomes(String[] var1);

    public void setMaxAlive(int var1);

    public int getMaxAlive();

    public void setCooldownTicks(int var1);

    public int getCooldownTicks();

    public void setAttemptsPerCycle(int var1);

    public int getAttemptsPerCycle();

    public void setPlayerMinDistance(int var1);

    public int getPlayerMinDistance();

    public void setDespawnMode(int var1);

    public int getDespawnMode();
}

