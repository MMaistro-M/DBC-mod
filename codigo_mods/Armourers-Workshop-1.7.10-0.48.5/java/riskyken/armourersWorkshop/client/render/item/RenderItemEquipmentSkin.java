/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.render.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.client.render.SkinItemRenderHelper;
import riskyken.armourersWorkshop.client.skin.cache.ClientSkinCache;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

public class RenderItemEquipmentSkin
implements IItemRenderer {
    protected final RenderItem renderItem;
    protected final Minecraft mc;

    public RenderItemEquipmentSkin() {
        this.renderItem = (RenderItem)RenderManager.field_78727_a.field_78729_o.get(EntityItem.class);
        this.mc = Minecraft.func_71410_x();
    }

    public boolean handleRenderType(ItemStack stack, IItemRenderer.ItemRenderType type) {
        return this.canRenderModel(stack);
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack stack, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object ... data) {
        if (this.canRenderModel(stack)) {
            GL11.glPushMatrix();
            GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            switch (type) {
                case EQUIPPED: {
                    GL11.glTranslatef((float)0.6f, (float)-0.5f, (float)-0.5f);
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    break;
                }
                case ENTITY: {
                    GL11.glTranslatef((float)0.0f, (float)-0.4f, (float)0.0f);
                    break;
                }
                case EQUIPPED_FIRST_PERSON: {
                    GL11.glTranslatef((float)0.5f, (float)-0.7f, (float)-0.5f);
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    break;
                }
                case INVENTORY: {
                    break;
                }
            }
            this.mc.field_71424_I.func_76320_a("armourersItemSkin");
            GL11.glPushAttrib((int)8192);
            ModRenderHelper.enableAlphaBlend();
            GL11.glEnable((int)2884);
            SkinItemRenderHelper.renderSkinAsItem(stack, true, 16, 16);
            GL11.glPopAttrib();
            this.mc.field_71424_I.func_76319_b();
            GL11.glPopMatrix();
        } else {
            this.renderNomalIcon(stack);
        }
    }

    private boolean canRenderModel(ItemStack stack) {
        if (SkinNBTHelper.stackHasSkinData(stack)) {
            SkinPointer skinData = SkinNBTHelper.getSkinPointerFromStack(stack);
            if (ClientSkinCache.INSTANCE.isSkinInCache(skinData)) {
                return true;
            }
            ClientSkinCache.INSTANCE.requestSkinFromServer(skinData);
            return false;
        }
        return false;
    }

    private void renderNomalIcon(ItemStack stack) {
        IIcon icon = stack.func_77973_b().getIcon(stack, 0);
        this.renderItem.func_94149_a(0, 0, icon, icon.func_94211_a(), icon.func_94216_b());
        if (stack.func_77973_b().getRenderPasses(stack.func_77960_j()) > 1) {
            icon = stack.func_77973_b().getIcon(stack, 1);
            this.renderItem.func_94149_a(0, 0, icon, icon.func_94211_a(), icon.func_94216_b());
        }
    }
}

