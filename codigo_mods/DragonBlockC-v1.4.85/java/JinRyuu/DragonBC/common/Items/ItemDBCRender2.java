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

import JinRyuu.DragonBC.common.Items.ItemDBCModel2;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreH;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemDBCRender2
implements IItemRenderer {
    protected ItemDBCModel2 model = new ItemDBCModel2();

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
                float scale = 2.0f;
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glScalef((float)scale, (float)scale, (float)scale);
                GL11.glColor3f((float)h1, (float)h1, (float)h1);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)5.5f, (float)0.5f, (float)0.0f);
                JRMCoreClient.mc.field_71446_o.func_110577_a(new ResourceLocation(JRMCoreH.tjdbcAssts + ":textures/blades/E.png"));
                this.model.render();
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
                break;
            }
            case ENTITY: {
                GL11.glPushMatrix();
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                JRMCoreClient.mc.field_71446_o.func_110577_a(new ResourceLocation(JRMCoreH.tjdbcAssts + ":textures/blades/E.png"));
                float h1 = 1.0f;
                float scale = 0.15f;
                GL11.glScalef((float)scale, (float)scale, (float)scale);
                GL11.glColor3f((float)h1, (float)h1, (float)h1);
                GL11.glRotatef((float)-140.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-1.9f, (float)-1.0f, (float)-0.2f);
                this.model.render();
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
                break;
            }
            case EQUIPPED: 
            case EQUIPPED_FIRST_PERSON: {
                GL11.glPushMatrix();
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                JRMCoreClient.mc.field_71446_o.func_110577_a(new ResourceLocation(JRMCoreH.tjdbcAssts + ":textures/blades/E.png"));
                float h1 = 1.0f;
                float scale = 0.3f;
                GL11.glScalef((float)scale, (float)scale, (float)scale);
                GL11.glColor3f((float)h1, (float)h1, (float)h1);
                GL11.glRotatef((float)-140.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-2.5f, (float)0.2f, (float)-0.2f);
                this.model.render();
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
                break;
            }
        }
    }
}

