/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import noppes.npcs.client.gui.GuiNpcSelectionInterface;
import noppes.npcs.entity.EntityNPCInterface;
import org.lwjgl.opengl.GL11;

public class GuiNpcTextureCloaks
extends GuiNpcSelectionInterface {
    public GuiNpcTextureCloaks(EntityNPCInterface npc, GuiScreen parent) {
        super(npc, parent, npc.display.cloakTexture.isEmpty() ? "customnpcs:textures/cloak/" : npc.display.cloakTexture);
        this.title = "Select Cloak";
    }

    @Override
    public void func_73866_w_() {
        String asset;
        super.func_73866_w_();
        int index = this.npc.display.cloakTexture.lastIndexOf("/");
        if (index > 0 && this.npc.display.cloakTexture.equals(this.assets.getAsset(asset = this.npc.display.cloakTexture.substring(index + 1)))) {
            this.slot.selected = asset;
        }
    }

    @Override
    public void func_73863_a(int i, int j, float f) {
        this.npc.isDrawn = true;
        int l = this.field_146294_l / 2 - 180;
        int i1 = this.field_146295_m / 2 - 90;
        GL11.glEnable((int)32826);
        GL11.glEnable((int)2903);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(l + 33), (float)(i1 + 131), (float)50.0f);
        float f1 = 250.0f / (float)this.npc.display.modelSize;
        GL11.glScalef((float)(-f1), (float)f1, (float)f1);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        float f2 = this.npc.field_70761_aq;
        float f3 = this.npc.field_70177_z;
        float f4 = this.npc.field_70125_A;
        float f7 = this.npc.field_70759_as;
        float f5 = (float)(l + 33) - (float)i;
        float f6 = (float)(i1 + 131 - 50) - (float)j;
        GL11.glRotatef((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.func_74519_b();
        GL11.glRotatef((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-((float)Math.atan(f6 / 40.0f)) * 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
        this.npc.field_70761_aq = (float)Math.atan(f5 / 40.0f) * 20.0f + 180.0f;
        this.npc.field_70177_z = (float)Math.atan(f5 / 40.0f) * 40.0f + 180.0f;
        this.npc.field_70125_A = -((float)Math.atan(f6 / 40.0f)) * 20.0f;
        this.npc.field_70759_as = this.npc.field_70177_z;
        this.npc.cloakUpdate();
        GL11.glTranslatef((float)0.0f, (float)this.npc.field_70129_M, (float)0.0f);
        RenderManager.field_78727_a.field_78735_i = 180.0f;
        RenderManager.field_78727_a.func_147940_a((Entity)this.npc, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        this.npc.field_70761_aq = f2;
        this.npc.field_70177_z = f3;
        this.npc.field_70125_A = f4;
        this.npc.field_70759_as = f7;
        this.npc.isDrawn = false;
        GL11.glPopMatrix();
        RenderHelper.func_74518_a();
        GL11.glDisable((int)32826);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77476_b);
        GL11.glDisable((int)3553);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77478_a);
        super.func_73863_a(i, j, f);
    }

    @Override
    public void elementClicked() {
        if (this.dataTextures.contains(this.slot.selected) && this.slot.selected != null) {
            this.npc.display.cloakTexture = this.assets.getAsset(this.slot.selected);
        }
    }

    @Override
    public void save() {
    }

    @Override
    public String[] getExtension() {
        return new String[]{"png"};
    }
}

