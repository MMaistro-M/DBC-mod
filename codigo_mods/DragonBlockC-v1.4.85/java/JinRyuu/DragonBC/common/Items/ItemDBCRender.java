/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.DragonBC.common.Items;

import JinRyuu.DragonBC.common.Items.ItemKatanaModel;
import JinRyuu.DragonBC.common.Items.ItemKatanaModel2;
import JinRyuu.JRMCore.JRMCoreClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemDBCRender
implements IItemRenderer {
    protected ItemKatanaModel model = new ItemKatanaModel();
    protected ItemKatanaModel2 model2 = new ItemKatanaModel2();
    protected String t;

    public ItemDBCRender(String type) {
        this.t = type;
    }

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        switch (type) {
            case EQUIPPED: {
                return true;
            }
            case INVENTORY: {
                return true;
            }
            case ENTITY: {
                return true;
            }
            case EQUIPPED_FIRST_PERSON: {
                return true;
            }
        }
        return false;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return false;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        switch (type) {
            case INVENTORY: {
                GL11.glPushMatrix();
                float h1 = 1.0f;
                float scale = 10.0f;
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glScalef((float)scale, (float)scale, (float)scale);
                GL11.glColor3f((float)h1, (float)h1, (float)h1);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)1.1f, (float)-0.4f, (float)-0.0f);
                if (this.t.equals("Z")) {
                    float scl = 0.7f;
                    GL11.glTranslatef((float)0.0f, (float)0.53f, (float)-0.0f);
                    GL11.glScalef((float)scl, (float)scl, (float)scl);
                }
                JRMCoreClient.mc.field_71446_o.func_110577_a(new ResourceLocation("jinryuudragonbc:textures/blades/" + this.t + ".png"));
                if (this.t.equals("Z")) {
                    this.model2.render();
                } else {
                    this.model.render(this.t);
                }
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
                break;
            }
            case ENTITY: {
                GL11.glPushMatrix();
                GL11.glDisable((int)2884);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                JRMCoreClient.mc.field_71446_o.func_110577_a(new ResourceLocation("jinryuudragonbc:textures/blades/" + this.t + ".png"));
                float h1 = 1.0f;
                float scale = 0.65f;
                GL11.glScalef((float)scale, (float)scale, (float)scale);
                GL11.glColor3f((float)h1, (float)h1, (float)h1);
                GL11.glRotatef((float)-140.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.4f, (float)-0.7f, (float)-0.0f);
                if (this.t.equals("Z")) {
                    float scl = 0.8f;
                    GL11.glTranslatef((float)0.0f, (float)0.42999998f, (float)-0.0f);
                    GL11.glScalef((float)scl, (float)scl, (float)scl);
                }
                if (this.t.equals("Z")) {
                    this.model2.render();
                } else {
                    this.model.render(this.t);
                }
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
                break;
            }
            case EQUIPPED: 
            case EQUIPPED_FIRST_PERSON: {
                GL11.glPushMatrix();
                GL11.glDisable((int)2884);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                JRMCoreClient.mc.field_71446_o.func_110577_a(new ResourceLocation("jinryuudragonbc:textures/blades/" + this.t + ".png"));
                float h1 = 1.0f;
                float scale = 1.0f;
                GL11.glScalef((float)scale, (float)scale, (float)scale);
                GL11.glColor3f((float)h1, (float)h1, (float)h1);
                GL11.glRotatef((float)-140.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.8f, (float)-0.75f, (float)-0.0f);
                if (this.t.equals("Z")) {
                    float scl = 0.8f;
                    GL11.glTranslatef((float)0.0f, (float)0.42999998f, (float)-0.0f);
                    GL11.glScalef((float)scl, (float)scl, (float)scl);
                }
                if (this.t.equals("Z")) {
                    this.model2.render();
                } else {
                    this.model.render(this.t);
                }
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
                break;
            }
        }
    }
}

