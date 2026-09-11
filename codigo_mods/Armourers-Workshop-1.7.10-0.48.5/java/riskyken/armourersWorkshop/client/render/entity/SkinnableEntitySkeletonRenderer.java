/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.entity.RenderSkeleton
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntitySkeleton
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.render.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderSkeleton;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.client.render.entity.ISkinnableEntityRenderer;
import riskyken.armourersWorkshop.api.common.skin.IEntityEquipment;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPointer;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.handler.ModClientFMLEventHandler;
import riskyken.armourersWorkshop.client.model.skin.AbstractModelSkin;
import riskyken.armourersWorkshop.client.render.SkinModelRenderer;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;

@SideOnly(value=Side.CLIENT)
public class SkinnableEntitySkeletonRenderer
implements ISkinnableEntityRenderer<EntitySkeleton> {
    @Override
    public void render(EntitySkeleton entity, RendererLivingEntity renderer, double x, double y, double z, IEntityEquipment entityEquipment) {
        GL11.glPushMatrix();
        float scale = 0.0625f;
        GL11.glTranslated((double)x, (double)y, (double)z);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)-1.0f);
        double rot = entity.field_70760_ar + (entity.field_70761_aq - entity.field_70760_ar) * ModClientFMLEventHandler.renderTickTime;
        GL11.glRotated((double)rot, (double)0.0, (double)1.0, (double)0.0);
        if (entity.field_70725_aQ > 0) {
            float angle = ((float)entity.field_70725_aQ + ModClientFMLEventHandler.renderTickTime - 1.0f) / 20.0f * 1.6f;
            if ((angle = MathHelper.func_76129_c((float)angle)) > 1.0f) {
                angle = 1.0f;
            }
            GL11.glRotatef((float)(angle * 90.0f), (float)0.0f, (float)0.0f, (float)1.0f);
        }
        GL11.glTranslated((double)0.0, (double)(-entity.field_70131_O + 4.7f * scale), (double)0.0);
        float headScale = 1.001f;
        GL11.glScalef((float)headScale, (float)headScale, (float)headScale);
        this.renderEquipmentType((EntityLivingBase)entity, renderer, SkinTypeRegistry.skinHead, entityEquipment);
        this.renderEquipmentType((EntityLivingBase)entity, renderer, SkinTypeRegistry.skinChest, entityEquipment);
        this.renderEquipmentType((EntityLivingBase)entity, renderer, SkinTypeRegistry.skinLegs, entityEquipment);
        this.renderEquipmentType((EntityLivingBase)entity, renderer, SkinTypeRegistry.oldSkinSkirt, entityEquipment);
        this.renderEquipmentType((EntityLivingBase)entity, renderer, SkinTypeRegistry.skinFeet, entityEquipment);
        this.renderEquipmentType((EntityLivingBase)entity, renderer, SkinTypeRegistry.skinWings, entityEquipment);
        GL11.glPopMatrix();
    }

    private void renderEquipmentType(EntityLivingBase entity, RendererLivingEntity renderer, ISkinType skinType, IEntityEquipment equipmentData) {
        float scale = 0.0625f;
        if (renderer instanceof RenderSkeleton) {
            RenderSkeleton rs = (RenderSkeleton)renderer;
            if (!equipmentData.haveEquipment(skinType, 0)) {
                return;
            }
            ISkinPointer skinPointer = equipmentData.getSkinPointer(skinType, 0);
            Skin skin = ClientSkinCache.INSTANCE.getSkin(skinPointer);
            if (skin == null) {
                return;
            }
            AbstractModelSkin model = SkinModelRenderer.INSTANCE.getModelForEquipmentType(skinType);
            GL11.glPushMatrix();
            model.render((Entity)entity, rs.field_77071_a, skin, false, skinPointer.getSkinDye(), null, false, 0.0, false);
            GL11.glPopMatrix();
        }
    }
}

