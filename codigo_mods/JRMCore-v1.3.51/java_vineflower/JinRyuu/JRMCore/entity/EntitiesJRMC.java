package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.mod_JRMCore;
import JinRyuu.JRMCore.blocks.BlockBarrierRender;
import JinRyuu.JRMCore.blocks.BlockBarrierTileEntity;
import JinRyuu.JRMCore.blocks.BlockBorderRender;
import JinRyuu.JRMCore.blocks.BlockBorderTileEntity;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;

public class EntitiesJRMC {
   public static int renderId = 100;
   public static int entityID = 0;

   public static int nextEntityID() {
      entityID++;
      return entityID;
   }

   public static void addEntityClient(Class<? extends Entity> entityClass, Render renderer) {
      RenderingRegistry.registerEntityRenderingHandler(entityClass, renderer);
   }

   public static void addEntityServer(Class<? extends Entity> entityClass, String entityName) {
      addEntityServer(entityClass, entityName, 80, 5);
   }

   public static void addEntityServer(Class<? extends Entity> entityClass, String entityName, int updateFrequency) {
      addEntityServer(entityClass, entityName, 80, updateFrequency, true);
   }

   public static void addEntityServer(Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency) {
      addEntityServer(entityClass, entityName, trackingRange, updateFrequency, true);
   }

   public static void addEntityServer(
      Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates
   ) {
      EntityRegistry.registerModEntity(entityClass, entityName, nextEntityID(), mod_JRMCore.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
   }

   @SideOnly(Side.CLIENT)
   public static void client() {
      addEntityClient(EntityEnergyAttJ.class, new RenderEnergyAttackJutsu());
      addEntityClient(EntityEnergyAttJ2.class, new RenderEnergyAttJ2());
      addEntityClient(EntityEnergyAttJ3.class, new RenderEnergyAttJ3());
      addEntityClient(EntityEnergyAttJ4.class, new RenderEnergyAttJ4());
      addEntityClient(EntityJRMCexpl.class, new RenderJRMCexpl());
      addEntityClient(EntityEng.class, new RenderEnergyAttackKiCharge());
      addEntityClient(EntityNPCshadow.class, new RenderDBC(new ModelNPCNormalScaled(), 0.5F));
      addEntityClient(EntityNull.class, new RenderNull(new ModelNPCTraining(), 0.5F));
      addEntityClient(EntityAuraFlat.class, new RenderAuraFlat(new ModelAuraFlat(), 0.5F));
      addEntityClient(EntityCusPar.class, new RenderCusPar());
      addEntityClient(EntitySafeZone.class, new RenderSafeZone());
      ClientRegistry.registerTileEntity(BlockBarrierTileEntity.class, "BlockBarrierTileEntityRemder", new BlockBarrierRender());
      ClientRegistry.registerTileEntity(BlockBorderTileEntity.class, "BlockBorderTileEntityRemder", new BlockBorderRender());
   }

   public static void common() {
      EntityRegistry.registerModEntity(EntityEnergyAttJ.class, "JutsuAttack", 104, mod_JRMCore.instance, 80, 5, true);
      addEntityServer(EntityEnergyAttJ2.class, "JutsuAttack2");
      addEntityServer(EntityEnergyAttJ3.class, "JutsuAttack3");
      addEntityServer(EntityNPCshadow.class, "TrainingShadowDummy");
      addEntityServer(EntitySafeZone.class, "JGMasterSafeZone");
      GameRegistry.registerTileEntity(BlockBarrierTileEntity.class, "BlockBarrierTileEntity");
      GameRegistry.registerTileEntity(BlockBorderTileEntity.class, "BlockBorderTileEntity");
   }
}
