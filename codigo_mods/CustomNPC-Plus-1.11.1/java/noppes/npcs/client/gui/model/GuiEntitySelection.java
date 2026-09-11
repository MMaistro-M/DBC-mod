/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.model;

import java.util.Collections;
import java.util.Vector;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.NPCRendererHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import noppes.npcs.client.EntityUtil;
import noppes.npcs.client.gui.model.GuiCreationScreen;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiNPCStringSlot;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityNPCGolem;
import noppes.npcs.entity.EntityNpcCrystal;
import noppes.npcs.entity.EntityNpcDragon;
import noppes.npcs.entity.EntityNpcPony;
import noppes.npcs.entity.EntityNpcSlime;
import noppes.npcs.entity.data.ModelData;
import org.lwjgl.opengl.GL11;

public class GuiEntitySelection
extends GuiNPCInterface {
    private GuiNPCStringSlot slot;
    private GuiCreationScreen parent;
    private Class<? extends EntityLivingBase> prevModel;
    private ModelData playerdata;
    private EntityCustomNpc npc;

    public GuiEntitySelection(GuiCreationScreen parent, ModelData playerdata, EntityCustomNpc npc) {
        this.parent = parent;
        this.playerdata = playerdata;
        this.npc = npc;
        this.drawDefaultBackground = false;
        this.prevModel = playerdata.getEntityClass();
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        Vector<String> list = new Vector<String>(this.parent.data.keySet());
        list.add("CustomNPC");
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        this.slot = new GuiNPCStringSlot(list, this, false, 18);
        this.slot.selected = this.playerdata.getEntityClass() != null ? (String)EntityList.field_75626_c.get(this.playerdata.getEntityClass()) : "CustomNPC";
        this.slot.func_148134_d(4, 5);
        this.field_146292_n.add(new GuiNpcButton(2, this.field_146294_l / 2 - 100, this.field_146295_m - 44, 98, 20, "gui.back"));
    }

    @Override
    public void func_73863_a(int i, int j, float f) {
        Object entity = this.playerdata.getEntity(this.npc);
        if (entity == null) {
            entity = this.npc;
        } else {
            EntityUtil.Copy((EntityLivingBase)this.npc, entity);
        }
        int l = this.field_146294_l / 2 - 180;
        int i1 = this.field_146295_m / 2 - 90;
        GL11.glEnable((int)32826);
        GL11.glEnable((int)2903);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(l + 33), (float)(i1 + 131), (float)50.0f);
        float scale = 1.0f;
        if ((double)entity.field_70131_O > 2.4) {
            scale = 2.0f / entity.field_70131_O;
        }
        GL11.glScalef((float)(-50.0f * scale), (float)(50.0f * scale), (float)(50.0f * scale));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        float f2 = entity.field_70761_aq;
        float f3 = entity.field_70177_z;
        float f4 = entity.field_70125_A;
        float f7 = entity.field_70759_as;
        float f5 = (float)(l + 33) - (float)i;
        float f6 = (float)(i1 + 131 - 50) - (float)j;
        GL11.glRotatef((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.func_74519_b();
        GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-((float)Math.atan(f6 / 40.0f)) * 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        entity.field_70761_aq = (float)Math.atan(f5 / 40.0f) * 20.0f;
        entity.field_70177_z = (float)Math.atan(f5 / 40.0f) * 40.0f;
        entity.field_70125_A = -((float)Math.atan(f6 / 40.0f)) * 20.0f;
        entity.field_70759_as = entity.field_70177_z;
        GL11.glTranslatef((float)0.0f, (float)entity.field_70129_M, (float)0.0f);
        RenderManager.field_78727_a.field_78735_i = 180.0f;
        try {
            RenderManager.field_78727_a.func_147940_a((Entity)entity, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        }
        catch (Exception e) {
            this.playerdata.setEntityClass(null);
        }
        entity.field_70761_aq = f2;
        entity.field_70177_z = f3;
        entity.field_70125_A = f4;
        entity.field_70759_as = f7;
        GL11.glPopMatrix();
        RenderHelper.func_74518_a();
        GL11.glDisable((int)32826);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77476_b);
        GL11.glDisable((int)3553);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77478_a);
        this.slot.func_148128_a(i, j, f);
        super.func_73863_a(i, j, f);
    }

    @Override
    public void elementClicked() {
        if (this.playerdata.getEntityClass() != null && this.playerdata.getEntityClass().equals(this.parent.data.get(this.slot.selected))) {
            return;
        }
        try {
            this.playerdata.setEntityClass(this.parent.data.get(this.slot.selected));
            EntityLivingBase entity = this.playerdata.getEntity(this.npc);
            if (entity != null) {
                this.npc.display.modelType = 0;
                if (entity instanceof EntityNpcCrystal) {
                    this.npc.display.texture = "customnpcs:textures/entity/crystal/EnderCrystal.png";
                } else if (entity instanceof EntityNPCGolem) {
                    this.npc.display.texture = "customnpcs:textures/entity/golem/Iron Golem.png";
                } else if (entity instanceof EntityNpcPony) {
                    this.npc.display.texture = "customnpcs:textures/entity/ponies/MineLP Derpy Hooves.png";
                } else if (entity instanceof EntityNpcSlime) {
                    this.npc.display.texture = "customnpcs:textures/entity/slime/Slime.png";
                } else if (entity instanceof EntityNpcDragon) {
                    this.npc.display.texture = "customnpcs:textures/entity/dragon/BlackDragon.png";
                } else {
                    RendererLivingEntity render = (RendererLivingEntity)RenderManager.field_78727_a.func_78713_a((Entity)entity);
                    this.npc.display.texture = NPCRendererHelper.getTexture(render, (Entity)entity);
                }
            } else {
                this.npc.display.texture = "customnpcs:textures/entity/humanmale/Steve.png";
            }
            this.npc.display.skinOverlayData.overlayList.remove(0);
            this.npc.textureLocation = null;
            this.npc.updateHitbox();
        }
        catch (Exception ex) {
            this.npc.display.texture = "customnpcs:textures/entity/humanmale/Steve.png";
        }
    }

    @Override
    public void doubleClicked() {
        this.close();
    }

    @Override
    public void func_73869_a(char par1, int par2) {
        if (par2 == 1) {
            this.close();
        }
    }

    @Override
    public void close() {
        this.field_146297_k.func_147108_a((GuiScreen)this.parent);
    }

    @Override
    protected void func_146284_a(GuiButton guibutton) {
        this.close();
    }

    @Override
    public void save() {
    }
}

