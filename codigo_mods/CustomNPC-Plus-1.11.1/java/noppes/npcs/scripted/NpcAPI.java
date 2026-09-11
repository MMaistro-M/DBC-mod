/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.eventhandler.EventBus
 *  net.minecraft.block.Block
 *  net.minecraft.command.CommandHandler
 *  net.minecraft.command.ICommand
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.INpc
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.entity.projectile.EntityFishHook
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemEditableBook
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemWritableBook
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTBase$NBTPrimitive
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EntityDamageSource
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.BiomeDictionary
 *  net.minecraftforge.common.BiomeDictionary$Type
 *  net.minecraftforge.common.IExtendedEntityProperties
 */
package noppes.npcs.scripted;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.EventBus;
import foxz.command.ScriptedCommand;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.AttributeController;
import kamkeel.npcs.controllers.EnergyController;
import kamkeel.npcs.controllers.ProfileController;
import kamkeel.npcs.controllers.TelegraphController;
import kamkeel.npcs.entity.EntityAbilityBeam;
import kamkeel.npcs.entity.EntityAbilityDisc;
import kamkeel.npcs.entity.EntityAbilityLaser;
import kamkeel.npcs.entity.EntityAbilityOrb;
import kamkeel.npcs.entity.EntityAbilityZone;
import kamkeel.npcs.entity.EntityEnergyDome;
import kamkeel.npcs.entity.EntityEnergyExplosion;
import kamkeel.npcs.entity.EntityEnergyPanel;
import kamkeel.npcs.entity.EntityEnergyProjectile;
import kamkeel.npcs.entity.EntityEnergySlicer;
import kamkeel.npcs.entity.EntityEnergySweeper;
import net.minecraft.block.Block;
import net.minecraft.command.CommandHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.INpc;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWritableBook;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.IExtendedEntityProperties;
import noppes.npcs.CustomNpcs;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.AbstractNpcAPI;
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
import noppes.npcs.api.handler.IAttributeHandler;
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
import noppes.npcs.compat.PixelmonHelper;
import noppes.npcs.config.ConfigMarket;
import noppes.npcs.config.ConfigScript;
import noppes.npcs.constants.EnumAnimationPart;
import noppes.npcs.containers.ContainerNpcInterface;
import noppes.npcs.controllers.AnimationController;
import noppes.npcs.controllers.AuctionController;
import noppes.npcs.controllers.ChunkController;
import noppes.npcs.controllers.CustomEffectController;
import noppes.npcs.controllers.DialogController;
import noppes.npcs.controllers.FactionController;
import noppes.npcs.controllers.LinkedItemController;
import noppes.npcs.controllers.MagicController;
import noppes.npcs.controllers.PartyController;
import noppes.npcs.controllers.QuestController;
import noppes.npcs.controllers.RecipeController;
import noppes.npcs.controllers.ScriptController;
import noppes.npcs.controllers.ScriptEntityData;
import noppes.npcs.controllers.ScriptHookController;
import noppes.npcs.controllers.ServerCloneController;
import noppes.npcs.controllers.SpawnController;
import noppes.npcs.controllers.TransportController;
import noppes.npcs.controllers.data.Animation;
import noppes.npcs.controllers.data.Frame;
import noppes.npcs.controllers.data.FramePart;
import noppes.npcs.controllers.data.SkinOverlay;
import noppes.npcs.controllers.data.action.ActionManager;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.entity.EntityProjectile;
import noppes.npcs.items.ItemLinked;
import noppes.npcs.items.ItemScripted;
import noppes.npcs.scripted.CustomNPCsException;
import noppes.npcs.scripted.ScriptBlock;
import noppes.npcs.scripted.ScriptBlockPos;
import noppes.npcs.scripted.ScriptContainer;
import noppes.npcs.scripted.ScriptDamageSource;
import noppes.npcs.scripted.ScriptNbt;
import noppes.npcs.scripted.ScriptParticle;
import noppes.npcs.scripted.ScriptSound;
import noppes.npcs.scripted.ScriptTileEntity;
import noppes.npcs.scripted.ScriptWorld;
import noppes.npcs.scripted.entity.ScriptAnimal;
import noppes.npcs.scripted.entity.ScriptArrow;
import noppes.npcs.scripted.entity.ScriptDBCPlayer;
import noppes.npcs.scripted.entity.ScriptEnergyBeam;
import noppes.npcs.scripted.entity.ScriptEnergyDisc;
import noppes.npcs.scripted.entity.ScriptEnergyDome;
import noppes.npcs.scripted.entity.ScriptEnergyExplosion;
import noppes.npcs.scripted.entity.ScriptEnergyLaser;
import noppes.npcs.scripted.entity.ScriptEnergyOrb;
import noppes.npcs.scripted.entity.ScriptEnergyPanel;
import noppes.npcs.scripted.entity.ScriptEnergyProjectile;
import noppes.npcs.scripted.entity.ScriptEnergySlicer;
import noppes.npcs.scripted.entity.ScriptEnergySweeper;
import noppes.npcs.scripted.entity.ScriptEnergyZone;
import noppes.npcs.scripted.entity.ScriptEntity;
import noppes.npcs.scripted.entity.ScriptEntityItem;
import noppes.npcs.scripted.entity.ScriptFishHook;
import noppes.npcs.scripted.entity.ScriptLiving;
import noppes.npcs.scripted.entity.ScriptLivingBase;
import noppes.npcs.scripted.entity.ScriptMonster;
import noppes.npcs.scripted.entity.ScriptPixelmon;
import noppes.npcs.scripted.entity.ScriptPlayer;
import noppes.npcs.scripted.entity.ScriptProjectile;
import noppes.npcs.scripted.entity.ScriptThrowable;
import noppes.npcs.scripted.entity.ScriptVillager;
import noppes.npcs.scripted.gui.ScriptGui;
import noppes.npcs.scripted.item.ScriptCustomItem;
import noppes.npcs.scripted.item.ScriptItemArmor;
import noppes.npcs.scripted.item.ScriptItemBlock;
import noppes.npcs.scripted.item.ScriptItemBook;
import noppes.npcs.scripted.item.ScriptItemStack;
import noppes.npcs.scripted.item.ScriptLinkedItem;
import noppes.npcs.scripted.overlay.ScriptOverlay;
import noppes.npcs.util.CacheHashMap;
import noppes.npcs.util.JsonException;
import noppes.npcs.util.LRUHashMap;
import noppes.npcs.util.NBTJsonUtil;
import noppes.npcs.util.SizeOfObjectUtil;

public class NpcAPI
extends AbstractNpcAPI {
    private static final Map<String, Object> tempData = new HashMap<String, Object>();
    private static final Map<Integer, ScriptWorld> worldCache = new LRUHashMap<Integer, ScriptWorld>(10);
    private static final CacheHashMap<ItemStack, CacheHashMap.CachedObject<ScriptItemStack>> scriptItemCache = new CacheHashMap(60000L);
    public static final HashMap<String, Object> engineObjects = new HashMap();
    public static volatile long engineObjectsVersion = 0L;
    public static final EventBus EVENT_BUS = new EventBus();
    private static AbstractNpcAPI instance = null;
    private static final String API_USER_AGENT = "CNPC+API";
    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static Boolean dbcLoaded = null;

    private NpcAPI() {
    }

    public static void clearCache() {
        worldCache.clear();
        scriptItemCache.clear();
    }

    @Override
    public Object getTempData(String key) {
        return tempData.get(key);
    }

    @Override
    public void setTempData(String key, Object value) {
        tempData.put(key, value);
    }

    @Override
    public boolean hasTempData(String key) {
        return tempData.containsKey(key);
    }

    @Override
    public void removeTempData(String key) {
        tempData.remove(key);
    }

    @Override
    public void clearTempData() {
        tempData.clear();
    }

    @Override
    public String[] getTempDataKeys() {
        return tempData.keySet().toArray(new String[0]);
    }

    @Override
    public Object getStoredData(String key) {
        NBTTagCompound compound = ScriptController.Instance.compound;
        if (!compound.func_74764_b(key)) {
            return null;
        }
        NBTBase base = compound.func_74781_a(key);
        if (base instanceof NBTBase.NBTPrimitive) {
            return ((NBTBase.NBTPrimitive)base).func_150286_g();
        }
        return ((NBTTagString)base).func_150285_a_();
    }

    @Override
    public void setStoredData(String key, Object value) {
        NBTTagCompound compound = ScriptController.Instance.compound;
        if (value instanceof Number) {
            compound.func_74780_a(key, ((Number)value).doubleValue());
        } else if (value instanceof String) {
            compound.func_74778_a(key, (String)value);
        }
        ScriptController.Instance.shouldSave = true;
    }

    @Override
    public boolean hasStoredData(String key) {
        return ScriptController.Instance.compound.func_74764_b(key);
    }

    @Override
    public void removeStoredData(String key) {
        ScriptController.Instance.compound.func_82580_o(key);
        ScriptController.Instance.shouldSave = true;
    }

    @Override
    public void clearStoredData() {
        ScriptController.Instance.compound = new NBTTagCompound();
        ScriptController.Instance.shouldSave = true;
    }

    @Override
    public String[] getStoredDataKeys() {
        NBTTagCompound compound = ScriptController.Instance.compound;
        if (compound != null) {
            Set keySet = compound.func_150296_c();
            ArrayList<String> list = new ArrayList<String>();
            for (Object o : keySet) {
                list.add((String)o);
            }
            String[] array = list.toArray(new String[list.size()]);
            return array;
        }
        return new String[0];
    }

    @Override
    public void registerICommand(ICommand command) {
        ((CommandHandler)CustomNpcs.getServer().func_71187_D()).func_71560_a((net.minecraft.command.ICommand)((ScriptedCommand)command));
    }

    @Override
    public ICommand getICommand(String commandName, int priorityLevel) {
        return new ScriptedCommand(commandName, priorityLevel);
    }

    @Override
    public void addGlobalObject(String key, Object obj) {
        engineObjects.put(key, obj);
        ++engineObjectsVersion;
    }

    @Override
    public void removeGlobalObject(String key) {
        engineObjects.remove(key);
        ++engineObjectsVersion;
    }

    @Override
    public boolean hasGlobalObject(String key) {
        return engineObjects.containsKey(key);
    }

    @Override
    public HashMap<String, Object> getEngineObjects() {
        return engineObjects;
    }

    @Override
    public long sizeOfObject(Object obj) {
        return SizeOfObjectUtil.sizeOfObject(obj);
    }

    @Override
    public void stopServer() {
        MinecraftServer.func_71276_C().func_71263_m();
    }

    @Override
    public int getCurrentPlayerCount() {
        return MinecraftServer.func_71276_C().func_71203_ab().func_72394_k();
    }

    @Override
    public int getMaxPlayers() {
        return MinecraftServer.func_71276_C().func_71203_ab().func_72352_l();
    }

    @Override
    public void kickAllPlayers() {
        MinecraftServer.func_71276_C().func_71203_ab().func_72392_r();
    }

    @Override
    public boolean isHardcore() {
        return MinecraftServer.func_71276_C().func_71199_h();
    }

    @Override
    public File getFile(String path) {
        return MinecraftServer.func_71276_C().func_71209_f(path);
    }

    @Override
    public String getServerOwner() {
        return MinecraftServer.func_71276_C().func_71214_G();
    }

    @Override
    public IFactionHandler getFactions() {
        this.checkWorld();
        return FactionController.getInstance();
    }

    @Override
    public IRecipeHandler getRecipes() {
        this.checkWorld();
        return RecipeController.Instance;
    }

    @Override
    public IQuestHandler getQuests() {
        this.checkWorld();
        return QuestController.Instance;
    }

    @Override
    public IDialogHandler getDialogs() {
        return DialogController.Instance;
    }

    @Override
    public ICloneHandler getClones() {
        return ServerCloneController.Instance;
    }

    @Override
    public INaturalSpawnsHandler getNaturalSpawns() {
        return SpawnController.Instance;
    }

    @Override
    public IProfileHandler getProfileHandler() {
        return ProfileController.Instance;
    }

    @Override
    public ICustomEffectHandler getCustomEffectHandler() {
        return CustomEffectController.Instance;
    }

    @Override
    public IMagicHandler getMagicHandler() {
        return MagicController.getInstance();
    }

    @Override
    public IPartyHandler getPartyHandler() {
        return PartyController.Instance();
    }

    public IAttributeHandler getAttributeHandler() {
        return AttributeController.Instance;
    }

    @Override
    public ITransportHandler getLocations() {
        return TransportController.getInstance();
    }

    @Override
    public IAnimationHandler getAnimations() {
        this.checkWorld();
        return AnimationController.Instance;
    }

    @Override
    public ILinkedItemHandler getLinkedItems() {
        return LinkedItemController.getInstance();
    }

    @Override
    public IScriptHookHandler getScriptHooks() {
        return ScriptHookController.Instance;
    }

    @Override
    public IAbilityHandler getAbilities() {
        this.checkWorld();
        return AbilityController.Instance;
    }

    @Override
    public ITelegraphHandler getTelegraphs() {
        this.checkWorld();
        return TelegraphController.Instance;
    }

    @Override
    public ITelegraph createTelegraph(String type) {
        this.checkWorld();
        return TelegraphController.Instance.create(type);
    }

    @Override
    public IAuctionHandler getAuctions() {
        this.checkWorld();
        if (!ConfigMarket.AuctionEnabled) {
            return null;
        }
        return AuctionController.getInstance();
    }

    @Override
    public String[] getAllBiomeNames() {
        ArrayList<String> biomes = new ArrayList<String>();
        HashSet<BiomeDictionary.Type> allBiomes = new HashSet<BiomeDictionary.Type>();
        for (BiomeDictionary.Type type : BiomeDictionary.Type.values()) {
            Collections.addAll(allBiomes, BiomeDictionary.getBiomesForType((BiomeDictionary.Type)type));
        }
        for (BiomeDictionary.Type type : BiomeGenBase.func_150565_n()) {
            if (type == null) continue;
            allBiomes.add(type);
        }
        for (BiomeGenBase biomeGenBase : allBiomes) {
            if (biomeGenBase == null || biomeGenBase.field_76791_y == null) continue;
            biomes.add(biomeGenBase.field_76791_y);
        }
        return biomes.toArray(new String[0]);
    }

    @Override
    public IEntity<?> getIEntity(Entity entity) {
        if (entity == null) {
            return null;
        }
        if (entity instanceof EntityNPCInterface) {
            return ((EntityNPCInterface)entity).wrappedNPC;
        }
        ScriptEntityData data = (ScriptEntityData)entity.getExtendedProperties("ScriptedObject");
        if (data == null) {
            if (entity instanceof EntityPlayerMP) {
                if (dbcLoaded == null) {
                    dbcLoaded = Loader.isModLoaded((String)"jinryuujrmcore");
                }
                data = dbcLoaded != false ? new ScriptEntityData(new ScriptDBCPlayer<EntityPlayerMP>((EntityPlayerMP)entity)) : new ScriptEntityData(new ScriptPlayer<EntityPlayerMP>((EntityPlayerMP)entity));
            } else {
                if (PixelmonHelper.isPixelmon(entity)) {
                    return new ScriptPixelmon<EntityTameable>((EntityTameable)entity);
                }
                data = entity instanceof EntityAnimal ? new ScriptEntityData(new ScriptAnimal<EntityAnimal>((EntityAnimal)entity)) : (entity instanceof EntityMob ? new ScriptEntityData(new ScriptMonster<EntityMob>((EntityMob)entity)) : (entity instanceof EntityVillager ? new ScriptEntityData(new ScriptVillager<EntityVillager>((EntityVillager)entity)) : (entity instanceof EntityLiving ? new ScriptEntityData(new ScriptLiving<EntityLiving>((EntityLiving)entity)) : (entity instanceof EntityLivingBase ? new ScriptEntityData(new ScriptLivingBase<EntityLivingBase>((EntityLivingBase)entity)) : (entity instanceof EntityProjectile ? new ScriptEntityData(new ScriptProjectile<EntityProjectile>((EntityProjectile)entity)) : (entity instanceof EntityItem ? new ScriptEntityData(new ScriptEntityItem<EntityItem>((EntityItem)entity)) : (entity instanceof EntityThrowable ? new ScriptEntityData(new ScriptThrowable<EntityThrowable>((EntityThrowable)entity)) : (entity instanceof EntityArrow ? new ScriptEntityData(new ScriptArrow<EntityArrow>((EntityArrow)entity)) : (entity instanceof EntityFishHook ? new ScriptEntityData(new ScriptFishHook<EntityFishHook>((EntityFishHook)entity)) : (entity instanceof EntityAbilityOrb ? new ScriptEntityData(new ScriptEnergyOrb<EntityAbilityOrb>((EntityAbilityOrb)entity)) : (entity instanceof EntityAbilityBeam ? new ScriptEntityData(new ScriptEnergyBeam<EntityAbilityBeam>((EntityAbilityBeam)entity)) : (entity instanceof EntityAbilityDisc ? new ScriptEntityData(new ScriptEnergyDisc<EntityAbilityDisc>((EntityAbilityDisc)entity)) : (entity instanceof EntityAbilityLaser ? new ScriptEntityData(new ScriptEnergyLaser<EntityAbilityLaser>((EntityAbilityLaser)entity)) : (entity instanceof EntityEnergySlicer ? new ScriptEntityData(new ScriptEnergySlicer<EntityEnergySlicer>((EntityEnergySlicer)entity)) : (entity instanceof EntityEnergyProjectile ? new ScriptEntityData(new ScriptEnergyProjectile<EntityEnergyProjectile>((EntityEnergyProjectile)entity)) : (entity instanceof EntityEnergyDome ? new ScriptEntityData(new ScriptEnergyDome<EntityEnergyDome>((EntityEnergyDome)entity)) : (entity instanceof EntityEnergyPanel ? new ScriptEntityData(new ScriptEnergyPanel<EntityEnergyPanel>((EntityEnergyPanel)entity)) : (entity instanceof EntityEnergySweeper ? new ScriptEntityData(new ScriptEnergySweeper<EntityEnergySweeper>((EntityEnergySweeper)entity)) : (entity instanceof EntityEnergyExplosion ? new ScriptEntityData(new ScriptEnergyExplosion<EntityEnergyExplosion>((EntityEnergyExplosion)entity)) : (entity instanceof EntityAbilityZone ? new ScriptEntityData(new ScriptEnergyZone<EntityAbilityZone>((EntityAbilityZone)entity)) : new ScriptEntityData(new ScriptEntity<Entity>(entity))))))))))))))))))))));
            }
            entity.registerExtendedProperties("ScriptedObject", (IExtendedEntityProperties)data);
        }
        return data.base;
    }

    @Override
    public INpc[] getChunkLoadingNPCs() {
        ArrayList<INpc> list = new ArrayList<INpc>();
        Set<Entity> npcSet = ChunkController.Instance.tickets.keySet();
        for (Entity entity : npcSet) {
            if (!(entity instanceof EntityNPCInterface)) continue;
            list.add((INpc)NpcAPI.Instance().getIEntity(entity));
        }
        return list.toArray(new INpc[0]);
    }

    @Override
    public IEntity<?>[] getLoadedEntities() {
        ArrayList list = new ArrayList();
        for (IWorld iWorld : worldCache.values()) {
            for (Object obj : iWorld.getMCWorld().field_72996_f) {
                list.add(NpcAPI.Instance().getIEntity((Entity)obj));
            }
        }
        return list.toArray(new IEntity[0]);
    }

    @Override
    public IBlock getIBlock(IWorld world, int x, int y, int z) {
        Block block = world.getMCWorld().func_147439_a(x, y, z);
        if (block == null || block.isAir((IBlockAccess)world.getMCWorld(), x, y, z)) {
            return null;
        }
        return new ScriptBlock((World)world.getMCWorld(), world.getMCWorld().func_147439_a(x, y, z), new BlockPos(x, y, z));
    }

    @Override
    public IBlock getIBlock(IWorld world, IPos pos) {
        return pos == null ? null : this.getIBlock(world, pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public ITileEntity getITileEntity(IWorld world, IPos pos) {
        if (pos == null) {
            return null;
        }
        TileEntity tileEntity = world.getMCWorld().func_147438_o(pos.getX(), pos.getY(), pos.getZ());
        return this.getITileEntity(tileEntity);
    }

    @Override
    public ITileEntity getITileEntity(IWorld world, int x, int y, int z) {
        TileEntity tileEntity = world.getMCWorld().func_147438_o(x, y, z);
        return this.getITileEntity(tileEntity);
    }

    @Override
    public ITileEntity getITileEntity(TileEntity tileEntity) {
        return tileEntity == null ? null : new ScriptTileEntity<TileEntity>(tileEntity);
    }

    @Override
    public IPos getIPos(BlockPos pos) {
        return new ScriptBlockPos(pos);
    }

    @Override
    public IPos getIPos(double x, double y, double z) {
        return this.getIPos(new BlockPos(x, y, z));
    }

    @Override
    public IPos getIPos(int x, int y, int z) {
        return this.getIPos((double)x, (double)y, (double)z);
    }

    @Override
    public IPos getIPos(float x, float y, float z) {
        return this.getIPos((double)x, (double)y, (double)z);
    }

    @Override
    public IPos getIPos(long serializedPos) {
        return this.getIPos(BlockPos.fromLong(serializedPos));
    }

    @Override
    public IPos[] getAllInBox(IPos from, IPos to, boolean sortByDistance) {
        ArrayList<IPos> list = new ArrayList<IPos>();
        if (from != null && to != null) {
            Iterator<BlockPos> posIterable = BlockPos.getAllInBox(from.getMCPos(), to.getMCPos()).iterator();
            posIterable.forEachRemaining(BlockPos2 -> list.add(this.getIPos((BlockPos)BlockPos2)));
            if (sortByDistance) {
                list.sort(Comparator.comparingDouble(pos -> pos.distanceTo(from)));
            }
        }
        return list.toArray(new IPos[0]);
    }

    @Override
    public IPos[] getAllInBox(IPos from, IPos to) {
        return this.getAllInBox(from, to, true);
    }

    @Override
    public INbt getINbt(NBTTagCompound nbtTagCompound) {
        return nbtTagCompound == null ? new ScriptNbt(new NBTTagCompound()) : new ScriptNbt(nbtTagCompound);
    }

    @Override
    public INbt stringToNbt(String str) {
        if (str != null && !str.isEmpty()) {
            try {
                return this.getINbt(NBTJsonUtil.Convert(str));
            }
            catch (JsonException var3) {
                throw new CustomNPCsException(var3, "Failed converting " + str, new Object[0]);
            }
        }
        throw new CustomNPCsException("Cant cast empty string to nbt", new Object[0]);
    }

    @Override
    public ICustomNpc<?> createNPC(IWorld world) {
        if (world.getMCWorld().field_72995_K) {
            return null;
        }
        EntityCustomNpc npc = new EntityCustomNpc((World)world.getMCWorld());
        return npc.wrappedNPC;
    }

    @Override
    public ICustomNpc<?> spawnNPC(IWorld world, int x, int y, int z) {
        if (world.getMCWorld().field_72995_K) {
            return null;
        }
        EntityCustomNpc npc = new EntityCustomNpc((World)world.getMCWorld());
        npc.func_70080_a((double)x + 0.5, y, (double)z + 0.5, 0.0f, 0.0f);
        npc.func_70606_j(npc.func_110138_aP());
        world.getMCWorld().func_72838_d((Entity)npc);
        return npc.wrappedNPC;
    }

    @Override
    public ICustomNpc<?> spawnNPC(IWorld world, IPos pos) {
        if (pos == null) {
            return null;
        }
        return this.spawnNPC(world, pos.getX(), pos.getY(), pos.getZ());
    }

    public static AbstractNpcAPI Instance() {
        if (instance == null) {
            instance = new NpcAPI();
        }
        return instance;
    }

    @Override
    public IEnergyHandler getEnergyHandler() {
        return EnergyController.Instance;
    }

    @Override
    public EventBus events() {
        return EVENT_BUS;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public IItemStack getIItemStack(ItemStack itemstack) {
        if (itemstack == null) {
            return null;
        }
        CacheHashMap<ItemStack, CacheHashMap.CachedObject<ScriptItemStack>> cacheHashMap = scriptItemCache;
        synchronized (cacheHashMap) {
            ScriptItemStack scriptStack;
            if (scriptItemCache.containsKey(itemstack)) {
                scriptStack = (ScriptItemStack)((CacheHashMap.CachedObject)scriptItemCache.get(itemstack)).getObject();
            } else {
                scriptStack = itemstack.func_77973_b() instanceof ItemLinked ? new ScriptLinkedItem(itemstack) : (itemstack.func_77973_b() instanceof ItemScripted ? new ScriptCustomItem(itemstack) : (itemstack.func_77973_b() instanceof ItemArmor ? new ScriptItemArmor(itemstack) : (itemstack.func_77973_b() instanceof ItemWritableBook || itemstack.func_77973_b() instanceof ItemEditableBook || itemstack.func_77973_b() == Items.field_151164_bB || itemstack.func_77973_b() == Items.field_151099_bA ? new ScriptItemBook(itemstack) : (itemstack.func_77973_b() instanceof ItemBlock ? new ScriptItemBlock(itemstack) : new ScriptItemStack(itemstack)))));
                scriptItemCache.put(itemstack, new CacheHashMap.CachedObject<ScriptItemStack>(scriptStack));
            }
            return scriptStack;
        }
    }

    @Override
    public IItemStack createItemFromNBT(INbt nbt) {
        return this.getIItemStack(ItemStack.func_77949_a((NBTTagCompound)nbt.getMCNBT()));
    }

    @Override
    public IItemStack createItem(String id, int damage, int size) {
        Item item = (Item)Item.field_150901_e.func_82594_a(id);
        if (item == null) {
            return null;
        }
        return this.getIItemStack(new ItemStack(item, size, damage));
    }

    @Override
    public IWorld getIWorld(World world) {
        ScriptWorld w = worldCache.get(world.field_73011_w.field_76574_g);
        if (w != null) {
            w.world.field_73011_w.field_76574_g = world.field_73011_w.field_76574_g;
        } else {
            w = ScriptWorld.createNew(world.field_73011_w.field_76574_g);
            worldCache.put(world.field_73011_w.field_76574_g, w);
        }
        return w;
    }

    @Override
    public IWorld getIWorld(int dimensionId) {
        WorldServer[] var2;
        for (WorldServer world : var2 = CustomNpcs.getServer().field_71305_c) {
            if (world.field_73011_w.field_76574_g != dimensionId) continue;
            return this.getIWorld((World)world);
        }
        throw new CustomNPCsException("Unknown dimension id: " + dimensionId, new Object[0]);
    }

    @Override
    public IWorld getIWorldLoad(int dimensionId) {
        try {
            IWorld iWorld = this.getIWorld(dimensionId);
            if (iWorld != null) {
                return iWorld;
            }
        }
        catch (CustomNPCsException iWorld) {
            // empty catch block
        }
        WorldServer worldServer = CustomNpcs.getServer().func_71218_a(dimensionId);
        if (worldServer != null) {
            return this.getIWorld((World)worldServer);
        }
        throw new CustomNPCsException("Unknown dimension id: " + dimensionId, new Object[0]);
    }

    @Override
    public IContainer getIContainer(IInventory inventory) {
        return new ScriptContainer(inventory);
    }

    @Override
    public IContainer getIContainer(Container container) {
        return container instanceof ContainerNpcInterface ? ContainerNpcInterface.getOrCreateIContainer((ContainerNpcInterface)container) : new ScriptContainer(container);
    }

    @Override
    public IActionManager getActionManager() {
        return ActionManager.GLOBAL;
    }

    private void checkWorld() {
        if (CustomNpcs.getServer() == null || CustomNpcs.getServer().func_71241_aa()) {
            throw new CustomNPCsException("No world is loaded right now", new Object[0]);
        }
    }

    @Override
    public IWorld[] getIWorlds() {
        this.checkWorld();
        IWorld[] worlds = new IWorld[CustomNpcs.getServer().field_71305_c.length];
        for (int i = 0; i < CustomNpcs.getServer().field_71305_c.length; ++i) {
            worlds[i] = this.getIWorld((World)CustomNpcs.getServer().field_71305_c[i]);
        }
        return worlds;
    }

    @Override
    public File getGlobalDir() {
        return CustomNpcs.Dir;
    }

    @Override
    public File getWorldDir() {
        return CustomNpcs.getWorldSaveDirectory();
    }

    @Override
    public IDamageSource getIDamageSource(DamageSource damagesource) {
        return new ScriptDamageSource(damagesource);
    }

    @Override
    public IDamageSource getIDamageSource(IEntity<?> entity) {
        if (entity.getType() == 1) {
            return new ScriptDamageSource((DamageSource)new EntityDamageSource("player", entity.getMCEntity()));
        }
        return new ScriptDamageSource((DamageSource)new EntityDamageSource(entity.getTypeName(), entity.getMCEntity()));
    }

    @Override
    public void executeCommand(IWorld world, String command) {
        NoppesUtilServer.runCommand((World)world.getMCWorld(), "API", command);
    }

    @Override
    public String getRandomName(int dictionary, int gender) {
        return CustomNpcs.MARKOV_GENERATOR[dictionary].fetch(gender);
    }

    @Override
    public IPlayer<?> getPlayer(String username) {
        EntityPlayerMP player = MinecraftServer.func_71276_C().func_71203_ab().func_152612_a(username);
        return player == null ? null : (IPlayer)NpcAPI.Instance().getIEntity((Entity)player);
    }

    @Override
    public IPlayer<?>[] getAllServerPlayers() {
        List list = MinecraftServer.func_71276_C().func_71203_ab().field_72404_b;
        IPlayer[] arr = new IPlayer[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            arr[i] = (IPlayer)NpcAPI.Instance().getIEntity((Entity)((EntityPlayerMP)list.get(i)));
        }
        return arr;
    }

    @Override
    public String[] getPlayerNames() {
        return MinecraftServer.func_71276_C().func_71203_ab().func_72369_d();
    }

    @Override
    public void playSoundAtEntity(IEntity<?> entity, String sound, float volume, float pitch) {
        entity.getWorld().getMCWorld().func_72956_a(entity.getMCEntity(), sound, volume, pitch);
    }

    @Override
    public void playSoundToNearExcept(IPlayer<?> player, String sound, float volume, float pitch) {
        player.getWorld().getMCWorld().func_85173_a((EntityPlayer)player.getMCEntity(), sound, volume, pitch);
    }

    @Override
    public String getMOTD() {
        return MinecraftServer.func_71276_C().func_71273_Y();
    }

    @Override
    public void setMOTD(String motd) {
        MinecraftServer.func_71276_C().func_71205_p(motd);
    }

    @Override
    public IParticle createParticle(String directory) {
        return new ScriptParticle(directory);
    }

    @Override
    @Deprecated
    public IParticle createEntityParticle(String directory) {
        return new ScriptParticle(directory);
    }

    @Override
    public ISound createSound(String directory) {
        return new ScriptSound(directory);
    }

    @Override
    public void playSound(int id, ISound sound) {
        NoppesUtilServer.playSound(id, (ScriptSound)sound);
    }

    @Override
    public void playSound(ISound sound) {
        NoppesUtilServer.playSound((ScriptSound)sound);
    }

    @Override
    public void stopSound(int id) {
        NoppesUtilServer.stopSound(id);
    }

    @Override
    public void pauseSounds() {
        NoppesUtilServer.pauseSounds();
    }

    @Override
    public void continueSounds() {
        NoppesUtilServer.continueSounds();
    }

    @Override
    public void stopSounds() {
        NoppesUtilServer.stopSounds();
    }

    @Override
    public int getServerTime() {
        return MinecraftServer.func_71276_C().func_71259_af();
    }

    @Override
    public boolean arePlayerScriptsEnabled() {
        return ConfigScript.GlobalPlayerScripts;
    }

    @Override
    public boolean areForgeScriptsEnabled() {
        return ConfigScript.GlobalForgeScripts;
    }

    @Override
    public boolean areGlobalNPCScriptsEnabled() {
        return ConfigScript.GlobalNPCScripts;
    }

    @Override
    public void enablePlayerScripts(boolean enable) {
        ConfigScript.GlobalPlayerScripts = enable;
    }

    @Override
    public void enableForgeScripts(boolean enable) {
        ConfigScript.GlobalForgeScripts = enable;
    }

    @Override
    public void enableGlobalNPCScripts(boolean enable) {
        ConfigScript.GlobalNPCScripts = enable;
    }

    @Override
    public ICustomGui createCustomGui(int id, int width, int height, boolean pauseGame) {
        return new ScriptGui(id, width, height, pauseGame);
    }

    @Override
    public ICustomOverlay createCustomOverlay(int id) {
        return new ScriptOverlay(id);
    }

    @Override
    public ISkinOverlay createSkinOverlay(String texture) {
        return new SkinOverlay(texture);
    }

    @Override
    public String millisToTime(long millis) {
        return NoppesUtilServer.millisToTime(millis);
    }

    @Override
    public String ticksToTime(long ticks) {
        return this.millisToTime(ticks * 50L);
    }

    @Override
    public IAnimation createAnimation(String name) {
        return new Animation(-1, name);
    }

    @Override
    public IAnimation createAnimation(String name, float speed, byte smooth) {
        return new Animation(-1, name, speed, smooth);
    }

    @Override
    public IFrame createFrame(int duration) {
        return new Frame(duration);
    }

    @Override
    public IFrame createFrame(int duration, float speed, byte smooth) {
        return new Frame(duration, speed, smooth);
    }

    @Override
    public IFramePart createPart(String name) {
        try {
            return new FramePart(EnumAnimationPart.valueOf(name));
        }
        catch (IllegalArgumentException ignored) {
            throw new CustomNPCsException("Invalid frame part name: " + name, new Object[0]);
        }
    }

    @Override
    public IFramePart createPart(String name, float[] rotation, float[] pivot) {
        if (rotation.length != 3 || pivot.length != 3) {
            throw new CustomNPCsException("Rotation and pivot arrays for frame parts must have a length of 3.", new Object[0]);
        }
        FramePart part = (FramePart)this.createPart(name);
        part.setRotations(rotation);
        part.setPivots(pivot);
        return part;
    }

    @Override
    public IFramePart createPart(String name, float[] rotation, float[] pivot, float speed, byte smooth) {
        FramePart part = (FramePart)this.createPart(name, rotation, pivot);
        part.setSpeed(speed);
        part.setSmooth(smooth);
        return part;
    }

    @Override
    public IFramePart createPart(int partId) {
        for (EnumAnimationPart part : EnumAnimationPart.values()) {
            if (part.ordinal() != partId) continue;
            return new FramePart(part);
        }
        throw new CustomNPCsException("Invalid frame part ID: " + partId, new Object[0]);
    }

    @Override
    public IFramePart createPart(int partId, float[] rotation, float[] pivot) {
        if (rotation.length != 3 || pivot.length != 3) {
            throw new CustomNPCsException("Rotation and pivot arrays for frame parts must have a length of 3.", new Object[0]);
        }
        FramePart part = (FramePart)this.createPart(partId);
        part.setRotations(rotation);
        part.setPivots(pivot);
        return part;
    }

    @Override
    public IFramePart createPart(int partId, float[] rotation, float[] pivot, float speed, byte smooth) {
        FramePart part = (FramePart)this.createPart(partId, rotation, pivot);
        part.setSpeed(speed);
        part.setSmooth(smooth);
        return part;
    }

    public void postJsonHTTP(String url, String jsonPayload) {
        this.postJsonHTTP(url, jsonPayload, API_USER_AGENT);
    }

    public void postJsonHTTP(String url, String jsonPayload, String userAgent) {
        this.postHTTP(url, jsonPayload, "application/json", userAgent);
    }

    public void postHTTP(String url, String params, String contentType) {
        this.postHTTP(url, params, contentType, API_USER_AGENT);
    }

    public void postHTTP(String url, String params, String contentType, String userAgent) {
        executorService.submit(() -> {
            try {
                HttpURLConnection con = NpcAPI.getConnection("POST", url, userAgent, contentType);
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(params);
                wr.flush();
                wr.close();
                int responseCode = con.getResponseCode();
                if (responseCode == 200) {
                    String inputLine;
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        });
    }

    private static HttpURLConnection getConnection(String requestMethod, String url, String userAgent, String contentType) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection)obj.openConnection();
        con.setRequestMethod(requestMethod);
        con.setRequestProperty("User-Agent", userAgent);
        if (!contentType.isEmpty()) {
            con.setRequestProperty("Content-Type", contentType);
        }
        return con;
    }
}

