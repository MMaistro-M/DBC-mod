/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.EntityEvent$EntityConstructing
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$StartTracking
 */
package riskyken.armourersWorkshop.common.wardrobe.entity;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPointer;
import riskyken.armourersWorkshop.api.common.skin.entity.IEntitySkinHandler;
import riskyken.armourersWorkshop.api.common.skin.entity.ISkinnableEntity;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.config.ConfigHandler;
import riskyken.armourersWorkshop.common.library.ILibraryManager;
import riskyken.armourersWorkshop.common.library.LibraryFile;
import riskyken.armourersWorkshop.common.library.LibraryFileList;
import riskyken.armourersWorkshop.common.skin.data.SkinIdentifier;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.common.wardrobe.EntityEquipmentData;
import riskyken.armourersWorkshop.common.wardrobe.entity.ExPropsEntityEquipmentData;
import riskyken.armourersWorkshop.common.wardrobe.entity.SkinnableEntityChicken;
import riskyken.armourersWorkshop.common.wardrobe.entity.SkinnableEntityCreeper;
import riskyken.armourersWorkshop.common.wardrobe.entity.SkinnableEntityGhast;
import riskyken.armourersWorkshop.common.wardrobe.entity.SkinnableEntitySkeleton;
import riskyken.armourersWorkshop.common.wardrobe.entity.SkinnableEntitySlime;
import riskyken.armourersWorkshop.common.wardrobe.entity.SkinnableEntityZombie;
import riskyken.armourersWorkshop.utils.ModLogger;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;
import riskyken.armourersWorkshop.utils.UtilItems;

public final class EntitySkinHandler
implements IEntitySkinHandler {
    public static EntitySkinHandler INSTANCE;
    private HashMap<Class<? extends EntityLivingBase>, ISkinnableEntity> entityMap;

    public static void init() {
        INSTANCE = new EntitySkinHandler();
    }

    public EntitySkinHandler() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.entityMap = new HashMap();
        this.registerEntities();
    }

    private void registerEntities() {
        this.registerEntity(new SkinnableEntityChicken());
        this.registerEntity(new SkinnableEntityCreeper());
        this.registerEntity(new SkinnableEntityGhast());
        this.registerEntity(new SkinnableEntitySkeleton());
        this.registerEntity(new SkinnableEntitySlime());
        this.registerEntity(new SkinnableEntityZombie());
    }

    @Override
    public void registerEntity(ISkinnableEntity skinnableEntity) {
        if (skinnableEntity == null) {
            return;
        }
        if (skinnableEntity.getEntityClass() == null) {
            return;
        }
        ModLogger.log(String.format("Registering %s as a skinnable entity.", skinnableEntity.getEntityClass()));
        this.entityMap.put(skinnableEntity.getEntityClass(), skinnableEntity);
    }

    @SubscribeEvent
    public void onStartTracking(PlayerEvent.StartTracking event) {
        if (event.entity.field_70170_p.field_72995_K) {
            return;
        }
        Entity entity = event.target;
        ExPropsEntityEquipmentData props = ExPropsEntityEquipmentData.getExtendedPropsForEntity(entity);
        if (props != null) {
            props.sendEquipmentDataToPlayer((EntityPlayerMP)event.entityPlayer);
        }
    }

    @Override
    public boolean isValidEntity(Entity entity) {
        return entity instanceof EntityLivingBase && this.entityMap.containsKey(entity.getClass());
    }

    public boolean canUseWandOfStyleOnEntity(Entity entity) {
        if (this.isValidEntity(entity)) {
            ISkinnableEntity skinnableEntity = this.entityMap.get(entity.getClass());
            return skinnableEntity.canUseWandOfStyle();
        }
        return false;
    }

    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event) {
        Entity entity = event.entity;
        if (this.isValidEntity(entity)) {
            ISkinnableEntity skinnableEntity = this.entityMap.get(entity.getClass());
            ExPropsEntityEquipmentData.register(entity, skinnableEntity);
        }
    }

    @SubscribeEvent
    public void onLivingDeathEvent(LivingDeathEvent event) {
        Entity entity = event.entity;
        if (this.isValidEntity(entity) && entity.field_70170_p != null && !entity.field_70170_p.field_72995_K) {
            this.dropEntitySkins(entity);
        }
    }

    private void dropEntitySkins(Entity entity) {
        ExPropsEntityEquipmentData entityEquipmentData;
        if (ConfigHandler.entityDropSkinChance <= 0) {
            return;
        }
        int rnd = entity.field_70170_p.field_73012_v.nextInt(99) + 1;
        if (rnd <= ConfigHandler.entityDropSkinChance && (entityEquipmentData = ExPropsEntityEquipmentData.getExtendedPropsForEntity(entity)) != null) {
            ArrayList<ISkinType> skinTypes = entityEquipmentData.getSkinInventory().getSkinTypes();
            for (int i = 0; i < skinTypes.size(); ++i) {
                ISkinPointer skinPointer = entityEquipmentData.getEquipmentData().getSkinPointer(skinTypes.get(i), 0);
                if (skinPointer == null) continue;
                ItemStack stack = SkinNBTHelper.makeEquipmentSkinStack((SkinPointer)skinPointer);
                UtilItems.spawnItemAtEntity(entity, stack);
            }
        }
    }

    public void giveRandomSkin(Entity entity) {
        this.giveRandomSkin(ExPropsEntityEquipmentData.getExtendedPropsForEntity(entity));
    }

    public void giveRandomSkin(ExPropsEntityEquipmentData entityEquipmentData) {
        if (entityEquipmentData == null) {
            return;
        }
        if (ConfigHandler.enitiySpawnWithSkinsChance <= 0) {
            return;
        }
        ArrayList<ISkinType> skinTypes = entityEquipmentData.getSkinInventory().getSkinTypes();
        for (int i = 0; i < skinTypes.size(); ++i) {
            SkinIdentifier identifier;
            ItemStack skinStack;
            ISkinType skinType;
            LibraryFile libraryFile;
            int rnd = entityEquipmentData.getEntity().field_70170_p.field_73012_v.nextInt(99) + 1;
            if (rnd >= ConfigHandler.enitiySpawnWithSkinsChance || (libraryFile = this.getRandomSkinOfType(skinType = skinTypes.get(i))) == null || (skinStack = SkinNBTHelper.makeEquipmentSkinStack(new SkinPointer(identifier = new SkinIdentifier(0, libraryFile, 0, skinType)))) == null) continue;
            entityEquipmentData.getSkinInventory().func_70299_a(i, skinStack);
        }
    }

    public LibraryFile getRandomSkinOfType(ISkinType skinType) {
        ILibraryManager libraryManager = ArmourersWorkshop.getProxy().libraryManager;
        LibraryFileList fileList = null;
        fileList = ArmourersWorkshop.isDedicated() ? libraryManager.getServerPublicFileList() : libraryManager.getClientPublicFileList();
        ArrayList<LibraryFile> typeList = fileList.getCachedFileListForSkinType(skinType);
        if (typeList == null) {
            return null;
        }
        ArrayList<LibraryFile> validFiles = new ArrayList<LibraryFile>();
        for (int i = 0; i < typeList.size(); ++i) {
            if (!typeList.get((int)i).filePath.startsWith(ConfigHandler.enitiySpawnSkinTargetPath)) continue;
            validFiles.add(typeList.get(i));
        }
        Random random = new Random();
        if (!validFiles.isEmpty()) {
            return (LibraryFile)validFiles.get(random.nextInt(validFiles.size()));
        }
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    public void receivedEquipmentData(EntityEquipmentData equipmentData, int entityId) {
        ExPropsEntityEquipmentData props;
        WorldClient world = Minecraft.func_71410_x().field_71441_e;
        Entity entity = world.func_73045_a(entityId);
        if (entity != null && (props = ExPropsEntityEquipmentData.getExtendedPropsForEntity(entity)) != null) {
            props.setEquipmentData(equipmentData);
        }
    }

    public ArrayList<ISkinnableEntity> getRegisteredEntities() {
        ArrayList<ISkinnableEntity> entityList = new ArrayList<ISkinnableEntity>();
        for (int i = 0; i < this.entityMap.size(); ++i) {
            Class entityClass = (Class)this.entityMap.keySet().toArray()[i];
            ISkinnableEntity entity = this.entityMap.get(entityClass);
            if (entity == null) continue;
            entityList.add(entity);
        }
        return entityList;
    }
}

