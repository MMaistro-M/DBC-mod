/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  cpw.mods.fml.common.registry.EntityRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 */
package com.tobiasmjc.dbcadditions.entities;

import JinRyuu.DragonBC.common.Npcs.ModelBeerus;
import JinRyuu.DragonBC.common.Npcs.RenderDBC;
import com.tobiasmjc.dbcadditions.entities.AuraEntity;
import com.tobiasmjc.dbcadditions.entities.MasterBeerus;
import com.tobiasmjc.dbcadditions.entities.MasterKibito;
import com.tobiasmjc.dbcadditions.entities.MasterOldKai;
import com.tobiasmjc.dbcadditions.entities.render.AuraEntityRender;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import hedaox.ninjinentities.models.ModelEldKaioshin;
import hedaox.ninjinentities.models.ModelKibito;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;

public class EntitiesDBCA {
    public static void addEntityServer(Class<? extends Entity> entityClass, int entityId, String entityName) {
        EntityRegistry.registerModEntity(entityClass, (String)entityName, (int)entityId, (Object)"dbcadditions", (int)80, (int)5, (boolean)true);
    }

    @SideOnly(value=Side.CLIENT)
    public static void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(MasterKibito.class, (Render)new RenderDBC(new ModelKibito(1.0f), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MasterOldKai.class, (Render)new RenderDBC(new ModelEldKaioshin(1.0f), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(MasterBeerus.class, (Render)new RenderDBC(new ModelBeerus(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(AuraEntity.class, (Render)new AuraEntityRender());
    }

    public static void registerEntities() {
        EntitiesDBCA.addEntityServer(MasterOldKai.class, 100, "MasterOldKai");
        EntitiesDBCA.addEntityServer(MasterKibito.class, 101, "MasterKibito");
        EntitiesDBCA.addEntityServer(MasterBeerus.class, 102, "MasterBeerus");
    }
}

