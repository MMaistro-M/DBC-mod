/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  cpw.mods.fml.common.registry.EntityRegistry
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 */
package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.blocks.BlockBarrierRender;
import JinRyuu.JRMCore.blocks.BlockBarrierTileEntity;
import JinRyuu.JRMCore.blocks.BlockBorderRender;
import JinRyuu.JRMCore.blocks.BlockBorderTileEntity;
import JinRyuu.JRMCore.entity.EntityAuraFlat;
import JinRyuu.JRMCore.entity.EntityCusPar;
import JinRyuu.JRMCore.entity.EntityEnergyAttJ;
import JinRyuu.JRMCore.entity.EntityEnergyAttJ2;
import JinRyuu.JRMCore.entity.EntityEnergyAttJ3;
import JinRyuu.JRMCore.entity.EntityEnergyAttJ4;
import JinRyuu.JRMCore.entity.EntityEng;
import JinRyuu.JRMCore.entity.EntityJRMCexpl;
import JinRyuu.JRMCore.entity.EntityNPCshadow;
import JinRyuu.JRMCore.entity.EntityNull;
import JinRyuu.JRMCore.entity.EntitySafeZone;
import JinRyuu.JRMCore.entity.ModelAuraFlat;
import JinRyuu.JRMCore.entity.ModelNPCNormalScaled;
import JinRyuu.JRMCore.entity.ModelNPCTraining;
import JinRyuu.JRMCore.entity.RenderAuraFlat;
import JinRyuu.JRMCore.entity.RenderCusPar;
import JinRyuu.JRMCore.entity.RenderDBC;
import JinRyuu.JRMCore.entity.RenderEnergyAttJ2;
import JinRyuu.JRMCore.entity.RenderEnergyAttJ3;
import JinRyuu.JRMCore.entity.RenderEnergyAttJ4;
import JinRyuu.JRMCore.entity.RenderEnergyAttackJutsu;
import JinRyuu.JRMCore.entity.RenderEnergyAttackKiCharge;
import JinRyuu.JRMCore.entity.RenderJRMCexpl;
import JinRyuu.JRMCore.entity.RenderNull;
import JinRyuu.JRMCore.entity.RenderSafeZone;
import JinRyuu.JRMCore.mod_JRMCore;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;

public class EntitiesJRMC {
    public static int renderId = 100;
    public static int entityID = 0;

    public static int nextEntityID() {
        return ++entityID;
    }

    public static void addEntityClient(Class<? extends Entity> entityClass, Render renderer) {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, (Render)renderer);
    }

    public static void addEntityServer(Class<? extends Entity> entityClass, String entityName) {
        EntitiesJRMC.addEntityServer(entityClass, entityName, 80, 5);
    }

    public static void addEntityServer(Class<? extends Entity> entityClass, String entityName, int updateFrequency) {
        EntitiesJRMC.addEntityServer(entityClass, entityName, 80, updateFrequency, true);
    }

    public static void addEntityServer(Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency) {
        EntitiesJRMC.addEntityServer(entityClass, entityName, trackingRange, updateFrequency, true);
    }

    public static void addEntityServer(Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
        EntityRegistry.registerModEntity(entityClass, (String)entityName, (int)EntitiesJRMC.nextEntityID(), (Object)mod_JRMCore.instance, (int)trackingRange, (int)updateFrequency, (boolean)sendsVelocityUpdates);
    }

    @SideOnly(value=Side.CLIENT)
    public static void client() {
        EntitiesJRMC.addEntityClient(EntityEnergyAttJ.class, (Render)new RenderEnergyAttackJutsu());
        EntitiesJRMC.addEntityClient(EntityEnergyAttJ2.class, (Render)new RenderEnergyAttJ2());
        EntitiesJRMC.addEntityClient(EntityEnergyAttJ3.class, (Render)new RenderEnergyAttJ3());
        EntitiesJRMC.addEntityClient(EntityEnergyAttJ4.class, (Render)new RenderEnergyAttJ4());
        EntitiesJRMC.addEntityClient(EntityJRMCexpl.class, (Render)new RenderJRMCexpl());
        EntitiesJRMC.addEntityClient(EntityEng.class, (Render)new RenderEnergyAttackKiCharge());
        EntitiesJRMC.addEntityClient(EntityNPCshadow.class, (Render)new RenderDBC((ModelBase)new ModelNPCNormalScaled(), 0.5f));
        EntitiesJRMC.addEntityClient(EntityNull.class, (Render)new RenderNull(new ModelNPCTraining(), 0.5f));
        EntitiesJRMC.addEntityClient(EntityAuraFlat.class, (Render)new RenderAuraFlat(new ModelAuraFlat(), 0.5f));
        EntitiesJRMC.addEntityClient(EntityCusPar.class, (Render)new RenderCusPar());
        EntitiesJRMC.addEntityClient(EntitySafeZone.class, (Render)new RenderSafeZone());
        ClientRegistry.registerTileEntity(BlockBarrierTileEntity.class, (String)"BlockBarrierTileEntityRemder", (TileEntitySpecialRenderer)new BlockBarrierRender());
        ClientRegistry.registerTileEntity(BlockBorderTileEntity.class, (String)"BlockBorderTileEntityRemder", (TileEntitySpecialRenderer)new BlockBorderRender());
    }

    public static void common() {
        EntityRegistry.registerModEntity(EntityEnergyAttJ.class, (String)"JutsuAttack", (int)104, (Object)mod_JRMCore.instance, (int)80, (int)5, (boolean)true);
        EntitiesJRMC.addEntityServer(EntityEnergyAttJ2.class, "JutsuAttack2");
        EntitiesJRMC.addEntityServer(EntityEnergyAttJ3.class, "JutsuAttack3");
        EntitiesJRMC.addEntityServer(EntityNPCshadow.class, "TrainingShadowDummy");
        EntitiesJRMC.addEntityServer(EntitySafeZone.class, "JGMasterSafeZone");
        GameRegistry.registerTileEntity(BlockBarrierTileEntity.class, (String)"BlockBarrierTileEntity");
        GameRegistry.registerTileEntity(BlockBorderTileEntity.class, (String)"BlockBorderTileEntity");
    }
}

