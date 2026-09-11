/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  cpw.mods.fml.relauncher.ReflectionHelper$UnableToAccessFieldException
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.common.addons;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.client.render.entity.ISkinnableEntityRenderer;
import riskyken.armourersWorkshop.api.common.skin.IEntityEquipment;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPointer;
import riskyken.armourersWorkshop.api.common.skin.entity.ISkinnableEntity;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.handler.ModClientFMLEventHandler;
import riskyken.armourersWorkshop.client.model.skin.AbstractModelSkin;
import riskyken.armourersWorkshop.client.render.SkinModelRenderer;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.common.addons.ModAddon;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.wardrobe.entity.EntitySkinHandler;

public class AddonMinecraftComesAlive
extends ModAddon {
    public AddonMinecraftComesAlive() {
        super("MCA", "Minecraft Comes Alive");
    }

    @Override
    public void init() {
        if (this.isModLoaded()) {
            EntitySkinHandler.INSTANCE.registerEntity(new SkinnableEntityEntityHuman());
        }
    }

    @SideOnly(value=Side.CLIENT)
    public static class SkinnableEntityEntityHumanRenderer
    implements ISkinnableEntityRenderer {
        public void render(EntityLivingBase entity, RendererLivingEntity renderer, double x, double y, double z, IEntityEquipment entityEquipment) {
            float scale = 0.0625f;
            GL11.glPushMatrix();
            GL11.glTranslated((double)x, (double)y, (double)z);
            GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
            GL11.glScalef((float)0.94f, (float)0.94f, (float)0.94f);
            GL11.glTranslated((double)0.0, (double)(-24.5 * (double)scale), (double)0.0);
            entity.func_70047_e();
            double rot = entity.field_70760_ar + (entity.field_70761_aq - entity.field_70760_ar) * ModClientFMLEventHandler.renderTickTime;
            GL11.glRotated((double)rot, (double)0.0, (double)1.0, (double)0.0);
            this.renderEquipmentType(entity, renderer, SkinTypeRegistry.skinHead, entityEquipment);
            this.renderEquipmentType(entity, renderer, SkinTypeRegistry.skinChest, entityEquipment);
            this.renderEquipmentType(entity, renderer, SkinTypeRegistry.skinLegs, entityEquipment);
            this.renderEquipmentType(entity, renderer, SkinTypeRegistry.skinFeet, entityEquipment);
            this.renderEquipmentType(entity, renderer, SkinTypeRegistry.skinWings, entityEquipment);
            GL11.glPopMatrix();
        }

        private void renderEquipmentType(EntityLivingBase entity, RendererLivingEntity renderer, ISkinType skinType, IEntityEquipment equipmentData) {
            if (!equipmentData.haveEquipment(skinType, 0)) {
                return;
            }
            ISkinPointer skinPointer = equipmentData.getSkinPointer(skinType, 0);
            Skin skin = ClientSkinCache.INSTANCE.getSkin(skinPointer);
            if (skin == null) {
                return;
            }
            Object object = null;
            try {
                object = ReflectionHelper.getPrivateValue(RendererLivingEntity.class, (Object)renderer, (String[])new String[]{"field_77045_g", "mainModel"});
            }
            catch (ReflectionHelper.UnableToAccessFieldException e) {
                e.printStackTrace();
            }
            AbstractModelSkin model = SkinModelRenderer.INSTANCE.getModelForEquipmentType(skinType);
            if (object != null && object instanceof ModelBiped) {
                model.render((Entity)entity, (ModelBiped)object, skin, false, skinPointer.getSkinDye(), null, false, 0.0, false);
            } else {
                model.render((Entity)entity, null, skin, false, skinPointer.getSkinDye(), null, false, 0.0, false);
            }
        }
    }

    public static class SkinnableEntityEntityHuman
    implements ISkinnableEntity {
        @Override
        public Class<? extends EntityLivingBase> getEntityClass() {
            try {
                return Class.forName("mca.entity.EntityHuman");
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        @SideOnly(value=Side.CLIENT)
        public Class<? extends ISkinnableEntityRenderer> getRendererClass() {
            return SkinnableEntityEntityHumanRenderer.class;
        }

        @Override
        public boolean canUseWandOfStyle() {
            return true;
        }

        @Override
        public boolean canUseSkinsOnEntity() {
            return false;
        }

        @Override
        public void getValidSkinTypes(ArrayList<ISkinType> skinTypes) {
            skinTypes.add(SkinTypeRegistry.skinHead);
            skinTypes.add(SkinTypeRegistry.skinChest);
            skinTypes.add(SkinTypeRegistry.skinLegs);
            skinTypes.add(SkinTypeRegistry.skinFeet);
            skinTypes.add(SkinTypeRegistry.skinWings);
        }
    }
}

