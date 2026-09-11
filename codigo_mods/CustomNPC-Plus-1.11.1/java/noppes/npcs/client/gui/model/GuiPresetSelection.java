/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.model;

import java.util.Collections;
import java.util.Vector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import noppes.npcs.client.EntityUtil;
import noppes.npcs.client.controllers.Preset;
import noppes.npcs.client.controllers.PresetController;
import noppes.npcs.client.gui.model.GuiCreationScreen;
import noppes.npcs.client.gui.util.GuiNPCInterface;
import noppes.npcs.client.gui.util.GuiNPCStringSlot;
import noppes.npcs.client.gui.util.GuiNpcButton;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.data.ModelData;
import org.lwjgl.opengl.GL11;

public class GuiPresetSelection
extends GuiNPCInterface {
    private GuiNPCStringSlot slot;
    private GuiCreationScreen parent;
    private NBTTagCompound prevData;
    private ModelData playerdata;
    private EntityCustomNpc npc;

    public GuiPresetSelection(GuiCreationScreen parent, ModelData playerdata) {
        this.parent = parent;
        this.playerdata = playerdata;
        this.prevData = playerdata.writeToNBT();
        this.drawDefaultBackground = false;
        this.npc = new EntityCustomNpc((World)Minecraft.func_71410_x().field_71441_e);
        this.npc.modelData = playerdata.copy();
        PresetController.instance.load();
    }

    @Override
    public void func_73866_w_() {
        super.func_73866_w_();
        Vector<String> list = new Vector<String>();
        for (Preset preset : PresetController.instance.presets.values()) {
            list.add(preset.name);
        }
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        this.slot = new GuiNPCStringSlot(list, this, false, 18);
        this.slot.func_148134_d(4, 5);
        this.field_146292_n.add(new GuiNpcButton(2, this.field_146294_l / 2 - 100, this.field_146295_m - 44, 98, 20, "Back"));
        this.field_146292_n.add(new GuiNpcButton(3, this.field_146294_l / 2 + 2, this.field_146295_m - 44, 98, 20, "Load"));
        this.field_146292_n.add(new GuiNpcButton(4, this.field_146294_l / 2 - 49, this.field_146295_m - 22, 98, 20, "Remove"));
    }

    @Override
    public void func_73863_a(int i, int j, float f) {
        Object entity = this.npc.modelData.getEntity(this.npc);
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
        GL11.glScalef((float)-50.0f, (float)50.0f, (float)50.0f);
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
        RenderManager.field_78727_a.func_147940_a((Entity)entity, 0.0, 0.0, 0.0, 0.0f, 1.0f);
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
        Preset preset = PresetController.instance.getPreset(this.slot.selected);
        this.npc.modelData.readFromNBT(preset.data.writeToNBT());
    }

    @Override
    public void doubleClicked() {
        this.playerdata.readFromNBT(this.npc.modelData.writeToNBT());
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
    public FontRenderer getFontRenderer() {
        return this.field_146289_q;
    }

    @Override
    protected void func_146284_a(GuiButton button) {
        GuiNpcButton guibutton = (GuiNpcButton)button;
        if (guibutton.field_146127_k == 2) {
            this.close();
        }
        if (guibutton.field_146127_k == 3) {
            this.playerdata.readFromNBT(this.npc.modelData.writeToNBT());
            this.close();
        }
        if (guibutton.field_146127_k == 4) {
            PresetController.instance.removePreset(this.slot.selected);
            Vector<String> list = new Vector<String>();
            for (Preset preset : PresetController.instance.presets.values()) {
                list.add(preset.name);
            }
            Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
            this.slot.setList(list);
            this.slot.selected = "";
        }
    }

    @Override
    public void save() {
    }
}

