/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer;

import java.lang.reflect.Method;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.NPCRendererHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import noppes.npcs.client.model.ModelMPM;
import noppes.npcs.client.model.util.ModelRenderPassHelper;
import noppes.npcs.client.renderer.RenderNPCHumanMale;
import noppes.npcs.compat.PixelmonHelper;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.opengl.GL11;

public class RenderCustomNpc
extends RenderNPCHumanMale {
    private RendererLivingEntity renderEntity;
    public static EntityLivingBase entity;
    private ModelRenderPassHelper renderpass = new ModelRenderPassHelper();

    public RenderCustomNpc() {
        super(new ModelMPM(0.0f, 0), new ModelMPM(1.0f, 0), new ModelMPM(0.5f, 0));
    }

    @Override
    public void renderPlayer(EntityNPCInterface npcInterface, double d, double d1, double d2, float f, float f1) {
        EntityCustomNpc npc = (EntityCustomNpc)npcInterface;
        entity = npc.modelData.getEntity(npc);
        ModelBase model = null;
        this.renderEntity = null;
        if (entity != null) {
            this.renderEntity = (RendererLivingEntity)RenderManager.field_78727_a.func_78713_a((Entity)entity);
            model = NPCRendererHelper.getMainModel(this.renderEntity);
            if (PixelmonHelper.isPixelmon((Entity)entity)) {
                try {
                    Class<?> c = Class.forName("com.pixelmonmod.pixelmon.entities.pixelmon.Entity2HasModel");
                    Method m = c.getMethod("getModel", new Class[0]);
                    model = (ModelBase)m.invoke((Object)entity, new Object[0]);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (EntityList.func_75621_b((Entity)entity).equals("doggystyle.Dog")) {
                try {
                    Method m = entity.getClass().getMethod("getBreed", new Class[0]);
                    Object breed = m.invoke((Object)entity, new Object[0]);
                    m = breed.getClass().getMethod("getModel", new Class[0]);
                    model = (ModelBase)m.invoke(breed, new Object[0]);
                    model.getClass().getMethod("setPosition", Integer.TYPE).invoke((Object)model, 0);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.field_77046_h = this.renderpass;
            this.renderpass.renderer = this.renderEntity;
            this.renderpass.entity = entity;
        }
        ((ModelMPM)this.modelArmor).entityModel = model;
        ((ModelMPM)this.modelArmor).entity = entity;
        ((ModelMPM)this.modelArmorChestplate).entityModel = model;
        ((ModelMPM)this.modelArmorChestplate).entity = entity;
        ((ModelMPM)this.field_77045_g).entityModel = model;
        ((ModelMPM)this.field_77045_g).entity = entity;
        super.renderPlayer(npc, d, d1, d2, f, f1);
    }

    @Override
    protected void func_77029_c(EntityLivingBase entityliving, float f) {
        if (this.renderEntity != null) {
            NPCRendererHelper.renderEquippedItems(entity, f, this.renderEntity);
        } else {
            super.func_77029_c(entityliving, f);
        }
    }

    @Override
    protected int func_77032_a(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
        if (this.renderEntity != null) {
            return NPCRendererHelper.shouldRenderPass(entity, par2, par3, this.renderEntity);
        }
        return this.func_130006_a((EntityLiving)par1EntityLivingBase, par2, par3);
    }

    @Override
    protected void func_77041_b(EntityLivingBase entityliving, float f) {
        if (this.renderEntity != null) {
            EntityNPCInterface npc = (EntityNPCInterface)entityliving;
            int size = npc.display.modelSize;
            if (entity instanceof EntityNPCInterface) {
                ((EntityNPCInterface)RenderCustomNpc.entity).display.modelSize = 5;
            }
            NPCRendererHelper.preRenderCallback(entity, f, this.renderEntity);
            npc.display.modelSize = size;
            GL11.glScalef((float)(0.2f * (float)npc.display.modelSize), (float)(0.2f * (float)npc.display.modelSize), (float)(0.2f * (float)npc.display.modelSize));
        } else {
            super.func_77041_b(entityliving, f);
        }
    }

    @Override
    public void func_76986_a(EntityLiving entityliving, double d, double d1, double d2, float f, float f1) {
        super.func_76986_a(entityliving, d, d1, d2, f, f1);
    }

    @Override
    protected float func_77044_a(EntityLivingBase par1EntityLivingBase, float par2) {
        if (this.renderEntity != null) {
            return NPCRendererHelper.handleRotationFloat(entity, par2, this.renderEntity);
        }
        return super.func_77044_a(par1EntityLivingBase, par2);
    }
}

