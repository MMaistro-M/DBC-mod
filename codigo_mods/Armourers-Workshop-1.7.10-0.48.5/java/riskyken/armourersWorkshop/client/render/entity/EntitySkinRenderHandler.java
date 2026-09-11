/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.client.event.RenderLivingEvent$Post
 *  net.minecraftforge.common.MinecraftForge
 */
package riskyken.armourersWorkshop.client.render.entity;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import riskyken.armourersWorkshop.api.client.render.entity.ISkinnableEntityRenderer;
import riskyken.armourersWorkshop.api.common.skin.entity.ISkinnableEntity;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.common.wardrobe.entity.EntitySkinHandler;
import riskyken.armourersWorkshop.common.wardrobe.entity.ExPropsEntityEquipmentData;

@SideOnly(value=Side.CLIENT)
public final class EntitySkinRenderHandler {
    public static EntitySkinRenderHandler INSTANCE;
    private HashMap<Class<? extends EntityLivingBase>, ISkinnableEntityRenderer> entityRenderer;

    public static void init() {
        INSTANCE = new EntitySkinRenderHandler();
    }

    public EntitySkinRenderHandler() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.entityRenderer = new HashMap();
    }

    public void initRenderer() {
        this.loadNpcRenderers();
    }

    private void loadNpcRenderers() {
        ArrayList<ISkinnableEntity> skinnableEntities = EntitySkinHandler.INSTANCE.getRegisteredEntities();
        for (int i = 0; i < skinnableEntities.size(); ++i) {
            ISkinnableEntity skinnableEntity = skinnableEntities.get(i);
            if (skinnableEntity.getRendererClass() == null) continue;
            this.registerRendererForNpc(skinnableEntity.getEntityClass(), skinnableEntity.getRendererClass());
        }
    }

    private void registerRendererForNpc(Class<? extends EntityLivingBase> entity, Class<? extends ISkinnableEntityRenderer> renderClass) {
        try {
            this.entityRenderer.put(entity, renderClass.newInstance());
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onRenderLivingEvent(RenderLivingEvent.Post event) {
        EntityLivingBase entity = event.entity;
        if (entity instanceof EntityPlayer) {
            return;
        }
        if (this.entityRenderer.containsKey(entity.getClass())) {
            ISkinnableEntityRenderer renderer = this.entityRenderer.get(entity.getClass());
            ExPropsEntityEquipmentData props = ExPropsEntityEquipmentData.getExtendedPropsForEntity((Entity)entity);
            if (props == null) {
                return;
            }
            Minecraft.func_71410_x().field_71424_I.func_76320_a("wandOfStyleRender");
            ModRenderHelper.enableAlphaBlend();
            renderer.render(entity, event.renderer, event.x, event.y, event.z, props.getEquipmentData());
            ModRenderHelper.disableAlphaBlend();
            Minecraft.func_71410_x().field_71424_I.func_76319_b();
        }
    }
}

