/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package noppes.npcs.api.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.api.IBlock;
import noppes.npcs.api.IContainer;
import noppes.npcs.api.IPixelmonPlayerData;
import noppes.npcs.api.IPos;
import noppes.npcs.api.IScreenSize;
import noppes.npcs.api.ITimers;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IAnimatable;
import noppes.npcs.api.entity.IDBCPlayer;
import noppes.npcs.api.entity.IEnergyProjectile;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IEntityLivingBase;
import noppes.npcs.api.gui.ICustomGui;
import noppes.npcs.api.handler.IActionManager;
import noppes.npcs.api.handler.IOverlayHandler;
import noppes.npcs.api.handler.IPlayerData;
import noppes.npcs.api.handler.data.IAnimationData;
import noppes.npcs.api.handler.data.IDialog;
import noppes.npcs.api.handler.data.IMagicData;
import noppes.npcs.api.handler.data.IPlayerAttributes;
import noppes.npcs.api.handler.data.IQuest;
import noppes.npcs.api.handler.data.ISound;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.api.overlay.ICustomOverlay;

public interface IPlayer<T extends EntityPlayerMP>
extends IEntityLivingBase<T>,
IAnimatable {
    public String getDisplayName();

    public String getName();

    public void kick(String var1);

    @Override
    public void setPosition(double var1, double var3, double var5);

    @Override
    public void setPosition(IPos var1);

    public void setPosition(double var1, double var3, double var5, int var7);

    public void setPosition(IPos var1, int var2);

    public void setPosition(double var1, double var3, double var5, IWorld var7);

    public void setPosition(IPos var1, IWorld var2);

    @Override
    public void setDimension(int var1);

    public int getHunger();

    public void setHunger(int var1);

    public float getSaturation();

    public void setSaturation(float var1);

    public void showDialog(IDialog var1);

    public boolean hasReadDialog(IDialog var1);

    public void readDialog(IDialog var1);

    public void unreadDialog(IDialog var1);

    public void showDialog(int var1);

    public boolean hasReadDialog(int var1);

    public void readDialog(int var1);

    public void unreadDialog(int var1);

    public boolean hasFinishedQuest(IQuest var1);

    public boolean hasActiveQuest(IQuest var1);

    public void startQuest(IQuest var1);

    public void finishQuest(IQuest var1);

    public void stopQuest(IQuest var1);

    public void removeQuest(IQuest var1);

    public boolean hasFinishedQuest(int var1);

    public boolean hasActiveQuest(int var1);

    public void startQuest(int var1);

    public void finishQuest(int var1);

    public void stopQuest(int var1);

    public void removeQuest(int var1);

    public IQuest[] getFinishedQuests();

    @Override
    public int getType();

    @Override
    public boolean typeOf(int var1);

    public void addFactionPoints(int var1, int var2);

    public void setFactionPoints(int var1, int var2);

    public int getFactionPoints(int var1);

    public void sendMessage(String var1);

    public int getMode();

    public void setMode(int var1);

    public IItemStack[] getInventory();

    public int inventoryItemCount(IItemStack var1, boolean var2, boolean var3);

    public boolean removeItem(String var1, int var2, int var3);

    public boolean removeItem(IItemStack var1, int var2, boolean var3, boolean var4);

    public int removeAllItems(IItemStack var1, boolean var2, boolean var3);

    public boolean giveItem(IItemStack var1, int var2);

    public boolean giveItem(String var1, int var2, int var3);

    public void setSpawnpoint(int var1, int var2, int var3);

    public void setSpawnpoint(IPos var1);

    public void resetSpawnpoint();

    @Override
    public void setRotation(float var1, float var2);

    public void disableMouseInput(long var1, int ... var3);

    public void stopUsingItem();

    public void clearItemInUse();

    public void clearInventory();

    public void playSound(String var1, float var2, float var3);

    public void playSound(int var1, ISound var2);

    public void playSound(ISound var1);

    public void stopSound(int var1);

    public void pauseSounds();

    public void continueSounds();

    public void stopSounds();

    public void mountEntity(Entity var1);

    public IEntity dropOneItem(boolean var1);

    public boolean canHarvestBlock(IBlock var1);

    public boolean interactWith(IEntity var1);

    public boolean hasAchievement(String var1);

    public boolean hasBukkitPermission(String var1);

    public int getExpLevel();

    public void setExpLevel(int var1);

    public IPixelmonPlayerData getPixelmonData();

    public ITimers getTimers();

    public void updatePlayerInventory();

    public IDBCPlayer getDBCPlayer();

    public boolean blocking();

    public IPlayerData getData();

    public boolean isScriptingDev();

    public IQuest[] getActiveQuests();

    public IContainer getOpenContainer();

    public void showCustomGui(ICustomGui var1);

    public ICustomGui getCustomGui();

    public void closeGui();

    public void showCustomOverlay(ICustomOverlay var1);

    public void closeOverlay(int var1);

    public IOverlayHandler getOverlays();

    @Override
    public IAnimationData getAnimationData();

    public void setConqueredEnd(boolean var1);

    public boolean conqueredEnd();

    public IScreenSize getScreenSize();

    public IMagicData getMagicData();

    public IPlayerAttributes getAttributes();

    public IActionManager getActionManager();

    public IPlayer[] getPartyMembers();

    public long getCurrencyBalance();

    public void setCurrencyBalance(long var1);

    public boolean depositCurrency(long var1);

    public boolean withdrawCurrency(long var1);

    public boolean canAffordCurrency(long var1);

    public boolean isUsingVaultCurrency();

    public String getFormattedCurrencyBalance();

    public IEnergyProjectile[] getActiveEnergyProjectiles();
}

