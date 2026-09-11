/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.entity.RenderArrow
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.render.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.skin.IEntityEquipment;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinPointer;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.client.render.SkinModelRenderer;
import riskyken.armourersWorkshop.client.render.SkinPartRenderData;
import riskyken.armourersWorkshop.client.render.SkinPartRenderer;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinPart;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;

@SideOnly(value=Side.CLIENT)
public class RenderSkinnedArrow
extends RenderArrow {
    private final SkinModelRenderer equipmentModelRenderer = SkinModelRenderer.INSTANCE;

    public void func_76986_a(EntityArrow entityArrow, double x, double y, double z, float yaw, float partialTickTime) {
        EntityClientPlayerMP player;
        IEntityEquipment entityEquipment;
        if (entityArrow.field_70250_c != null && entityArrow.field_70250_c instanceof EntityClientPlayerMP && (entityEquipment = this.equipmentModelRenderer.getPlayerCustomEquipmentData((Entity)(player = (EntityClientPlayerMP)entityArrow.field_70250_c))) != null && entityEquipment.haveEquipment(SkinTypeRegistry.skinBow, 0)) {
            ISkinPointer skinPointer = entityEquipment.getSkinPointer(SkinTypeRegistry.skinBow, 0);
            if (ClientSkinCache.INSTANCE.isSkinInCache(skinPointer)) {
                SkinPart skinPart;
                Skin skin = ClientSkinCache.INSTANCE.getSkin(skinPointer);
                if (skin != null && (skinPart = skin.getPart("armourers:bow.arrow")) != null) {
                    ModRenderHelper.enableAlphaBlend();
                    this.renderArrowSkin(entityArrow, x, y, z, partialTickTime, skinPart, skinPointer.getSkinDye());
                    ModRenderHelper.disableAlphaBlend();
                    return;
                }
            } else {
                ClientSkinCache.INSTANCE.requestSkinFromServer(skinPointer);
            }
        }
        super.func_76986_a(entityArrow, x, y, z, yaw, partialTickTime);
    }

    private void renderArrowSkin(EntityArrow entityArrow, double x, double y, double z, float partialTickTime, SkinPart skinPart, ISkinDye skinDye) {
        float scale = 0.0625f;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x), (float)((float)y), (float)((float)z));
        GL11.glRotatef((float)(entityArrow.field_70126_B + (entityArrow.field_70177_z - entityArrow.field_70126_B) * partialTickTime - 90.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(entityArrow.field_70127_C + (entityArrow.field_70125_A - entityArrow.field_70127_C) * partialTickTime), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)(2.5f * scale), (float)(0.5f * scale), (float)(0.5f * scale));
        float f10 = 0.05625f;
        float f11 = (float)entityArrow.field_70249_b - partialTickTime;
        if (f11 > 0.0f) {
            float f12 = -MathHelper.func_76126_a((float)(f11 * 3.0f)) * f11;
            GL11.glRotatef((float)f12, (float)0.0f, (float)0.0f, (float)1.0f);
        }
        GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        SkinPartRenderData renderData = new SkinPartRenderData(skinPart, 0.0625f, skinDye, null, 0.0, true, false, false, null);
        SkinPartRenderer.INSTANCE.renderPart(renderData);
        GL11.glPopMatrix();
    }
}

