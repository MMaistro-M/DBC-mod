/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.INpc
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package noppes.npcs.api;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.EventBus;
import java.io.File;
import java.util.HashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.INpc;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import noppes.npcs.api.IBlock;
import noppes.npcs.api.ICommand;
import noppes.npcs.api.IContainer;
import noppes.npcs.api.IDamageSource;
import noppes.npcs.api.IEnergyHandler;
import noppes.npcs.api.INbt;
import noppes.npcs.api.IParticle;
import noppes.npcs.api.IPos;
import noppes.npcs.api.ISkinOverlay;
import noppes.npcs.api.ITelegraph;
import noppes.npcs.api.ITileEntity;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.ICustomNpc;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.gui.ICustomGui;
import noppes.npcs.api.handler.IAbilityHandler;
import noppes.npcs.api.handler.IActionManager;
import noppes.npcs.api.handler.IAnimationHandler;
import noppes.npcs.api.handler.IAuctionHandler;
import noppes.npcs.api.handler.ICloneHandler;
import noppes.npcs.api.handler.ICustomEffectHandler;
import noppes.npcs.api.handler.IDialogHandler;
import noppes.npcs.api.handler.IFactionHandler;
import noppes.npcs.api.handler.ILinkedItemHandler;
import noppes.npcs.api.handler.IMagicHandler;
import noppes.npcs.api.handler.INaturalSpawnsHandler;
import noppes.npcs.api.handler.IPartyHandler;
import noppes.npcs.api.handler.IProfileHandler;
import noppes.npcs.api.handler.IQuestHandler;
import noppes.npcs.api.handler.IRecipeHandler;
import noppes.npcs.api.handler.IScriptHookHandler;
import noppes.npcs.api.handler.ITelegraphHandler;
import noppes.npcs.api.handler.ITransportHandler;
import noppes.npcs.api.handler.data.IAnimation;
import noppes.npcs.api.handler.data.IFrame;
import noppes.npcs.api.handler.data.IFramePart;
import noppes.npcs.api.handler.data.ISound;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.api.overlay.ICustomOverlay;

public abstract class AbstractNpcAPI {
    private static AbstractNpcAPI instance = null;

    public abstract Object getTempData(String var1);

    public abstract void setTempData(String var1, Object var2);

    public abstract boolean hasTempData(String var1);

    public abstract void removeTempData(String var1);

    public abstract void clearTempData();

    public abstract String[] getTempDataKeys();

    public abstract Object getStoredData(String var1);

    public abstract void setStoredData(String var1, Object var2);

    public abstract boolean hasStoredData(String var1);

    public abstract void removeStoredData(String var1);

    public abstract void clearStoredData();

    public abstract String[] getStoredDataKeys();

    public abstract void registerICommand(ICommand var1);

    public abstract ICommand getICommand(String var1, int var2);

    public abstract void addGlobalObject(String var1, Object var2);

    public abstract void removeGlobalObject(String var1);

    public abstract boolean hasGlobalObject(String var1);

    public abstract HashMap<String, Object> getEngineObjects();

    public abstract long sizeOfObject(Object var1);

    public abstract void stopServer();

    public abstract int getCurrentPlayerCount();

    public abstract int getMaxPlayers();

    public abstract void kickAllPlayers();

    public abstract boolean isHardcore();

    public abstract File getFile(String var1);

    public abstract String getServerOwner();

    public abstract IFactionHandler getFactions();

    public abstract IRecipeHandler getRecipes();

    public abstract IQuestHandler getQuests();

    public abstract IDialogHandler getDialogs();

    public abstract ICloneHandler getClones();

    public abstract INaturalSpawnsHandler getNaturalSpawns();

    public abstract IProfileHandler getProfileHandler();

    public abstract ICustomEffectHandler getCustomEffectHandler();

    public abstract IMagicHandler getMagicHandler();

    public abstract IPartyHandler getPartyHandler();

    public abstract ITransportHandler getLocations();

    public abstract IAnimationHandler getAnimations();

    public abstract ILinkedItemHandler getLinkedItems();

    public abstract IScriptHookHandler getScriptHooks();

    public abstract IAbilityHandler getAbilities();

    public abstract ITelegraphHandler getTelegraphs();

    public abstract IAuctionHandler getAuctions();

    public abstract ITelegraph createTelegraph(String var1);

    public abstract String[] getAllBiomeNames();

    public abstract ICustomNpc<?> createNPC(IWorld var1);

    public abstract ICustomNpc<?> spawnNPC(IWorld var1, int var2, int var3, int var4);

    public abstract ICustomNpc<?> spawnNPC(IWorld var1, IPos var2);

    public abstract IEntity<?> getIEntity(Entity var1);

    public abstract IPlayer<?> getPlayer(String var1);

    public abstract INpc[] getChunkLoadingNPCs();

    public abstract IEntity<?>[] getLoadedEntities();

    public abstract IBlock getIBlock(IWorld var1, int var2, int var3, int var4);

    public abstract IBlock getIBlock(IWorld var1, IPos var2);

    public abstract ITileEntity getITileEntity(IWorld var1, IPos var2);

    public abstract ITileEntity getITileEntity(IWorld var1, int var2, int var3, int var4);

    public abstract ITileEntity getITileEntity(TileEntity var1);

    public abstract IPos getIPos(BlockPos var1);

    public abstract IPos getIPos(int var1, int var2, int var3);

    public abstract IPos getIPos(double var1, double var3, double var5);

    public abstract IPos getIPos(float var1, float var2, float var3);

    public abstract IPos getIPos(long var1);

    public abstract IPos[] getAllInBox(IPos var1, IPos var2, boolean var3);

    public abstract IPos[] getAllInBox(IPos var1, IPos var2);

    public abstract IContainer getIContainer(IInventory var1);

    public abstract IContainer getIContainer(Container var1);

    public abstract IItemStack getIItemStack(ItemStack var1);

    public abstract IWorld getIWorld(World var1);

    public abstract IWorld getIWorld(int var1);

    public abstract IWorld getIWorldLoad(int var1);

    public abstract IActionManager getActionManager();

    public abstract IWorld[] getIWorlds();

    public abstract IDamageSource getIDamageSource(DamageSource var1);

    public abstract IDamageSource getIDamageSource(IEntity<?> var1);

    public abstract IEnergyHandler getEnergyHandler();

    public abstract EventBus events();

    public abstract File getGlobalDir();

    public abstract File getWorldDir();

    public static boolean IsAvailable() {
        return Loader.isModLoaded((String)"customnpcs");
    }

    public static AbstractNpcAPI Instance() {
        if (instance != null) {
            return instance;
        }
        if (!AbstractNpcAPI.IsAvailable()) {
            return null;
        }
        try {
            Class<?> c = Class.forName("noppes.npcs.scripted.NpcAPI");
            instance = (AbstractNpcAPI)c.getMethod("Instance", new Class[0]).invoke(null, new Object[0]);
        }
        catch (Exception var1) {
            var1.printStackTrace();
        }
        return instance;
    }

    public abstract void executeCommand(IWorld var1, String var2);

    public abstract String getRandomName(int var1, int var2);

    public abstract INbt getINbt(NBTTagCompound var1);

    public abstract INbt stringToNbt(String var1);

    public abstract IPlayer<?>[] getAllServerPlayers();

    public abstract String[] getPlayerNames();

    public abstract IItemStack createItemFromNBT(INbt var1);

    public abstract IItemStack createItem(String var1, int var2, int var3);

    public abstract void playSoundAtEntity(IEntity<?> var1, String var2, float var3, float var4);

    public abstract void playSoundToNearExcept(IPlayer<?> var1, String var2, float var3, float var4);

    public abstract String getMOTD();

    public abstract void setMOTD(String var1);

    public abstract IParticle createParticle(String var1);

    @Deprecated
    public abstract IParticle createEntityParticle(String var1);

    public abstract ISound createSound(String var1);

    public abstract void playSound(int var1, ISound var2);

    public abstract void playSound(ISound var1);

    public abstract void stopSound(int var1);

    public abstract void pauseSounds();

    public abstract void continueSounds();

    public abstract void stopSounds();

    public abstract int getServerTime();

    public abstract boolean arePlayerScriptsEnabled();

    public abstract boolean areForgeScriptsEnabled();

    public abstract boolean areGlobalNPCScriptsEnabled();

    public abstract void enablePlayerScripts(boolean var1);

    public abstract void enableForgeScripts(boolean var1);

    public abstract void enableGlobalNPCScripts(boolean var1);

    public abstract ICustomGui createCustomGui(int var1, int var2, int var3, boolean var4);

    public abstract ICustomOverlay createCustomOverlay(int var1);

    public abstract ISkinOverlay createSkinOverlay(String var1);

    public abstract String millisToTime(long var1);

    public abstract String ticksToTime(long var1);

    public abstract IAnimation createAnimation(String var1);

    public abstract IAnimation createAnimation(String var1, float var2, byte var3);

    public abstract IFrame createFrame(int var1);

    public abstract IFrame createFrame(int var1, float var2, byte var3);

    public abstract IFramePart createPart(String var1);

    public abstract IFramePart createPart(String var1, float[] var2, float[] var3);

    public abstract IFramePart createPart(String var1, float[] var2, float[] var3, float var4, byte var5);

    public abstract IFramePart createPart(int var1);

    public abstract IFramePart createPart(int var1, float[] var2, float[] var3);

    public abstract IFramePart createPart(int var1, float[] var2, float[] var3, float var4, byte var5);
}

