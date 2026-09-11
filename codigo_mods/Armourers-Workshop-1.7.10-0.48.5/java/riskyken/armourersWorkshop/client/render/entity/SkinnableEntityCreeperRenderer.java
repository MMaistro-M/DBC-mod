/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.render.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.client.render.entity.ISkinnableEntityRenderer;
import riskyken.armourersWorkshop.api.common.skin.IEntityEquipment;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPointer;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.handler.ModClientFMLEventHandler;
import riskyken.armourersWorkshop.client.render.SkinPartRenderData;
import riskyken.armourersWorkshop.client.render.SkinPartRenderer;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;

@SideOnly(value=Side.CLIENT)
public class SkinnableEntityCreeperRenderer
implements ISkinnableEntityRenderer<EntityCreeper> {
    @Override
    public void render(EntityCreeper entity, RendererLivingEntity renderer, double x, double y, double z, IEntityEquipment entityEquipment) {
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
        GL11.glTranslated((double)0.0, (double)(-20.2f * scale), (double)0.0);
        double headRot = entity.field_70758_at + (entity.field_70759_as - entity.field_70758_at) * ModClientFMLEventHandler.renderTickTime;
        GL11.glRotated((double)(headRot - rot), (double)0.0, (double)1.0, (double)0.0);
        GL11.glRotatef((float)entity.field_70125_A, (float)1.0f, (float)0.0f, (float)0.0f);
        float headScale = 1.001f;
        GL11.glScalef((float)headScale, (float)headScale, (float)headScale);
        this.renderEquipmentType((EntityLivingBase)entity, renderer, SkinTypeRegistry.skinHead, entityEquipment);
        GL11.glPopMatrix();
    }

    private void renderEquipmentType(EntityLivingBase entity, RendererLivingEntity renderer, ISkinType skinType, IEntityEquipment equipmentData) {
        if (equipmentData.haveEquipment(skinType, 0)) {
            ISkinPointer skinPointer = equipmentData.getSkinPointer(skinType, 0);
            Skin skin = ClientSkinCache.INSTANCE.getSkin(skinPointer);
            if (skin == null) {
                return;
            }
            GL11.glEnable((int)2977);
            float scale = 0.0625f;
            for (int i = 0; i < skin.getParts().size(); ++i) {
                SkinPartRenderData renderData = new SkinPartRenderData(skin.getParts().get(i), scale, skinPointer.getSkinDye(), null, 0.0, false, false, false, null);
                SkinPartRenderer.INSTANCE.renderPart(renderData);
            }
            GL11.glDisable((int)2977);
        }
    }
}

