/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer.items;

import kamkeel.npcs.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import noppes.npcs.client.renderer.blocks.BlockBannerRenderer;
import noppes.npcs.client.renderer.blocks.BlockWallBannerRenderer;
import noppes.npcs.config.ConfigClient;
import org.lwjgl.opengl.GL11;

public class ItemBannerWallRenderer
implements IItemRenderer {
    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        int meta = item.func_77960_j();
        int colorValue = ColorUtil.colorTableInts[15 - meta];
        if (item.func_77942_o() && item.func_77978_p().func_74764_b("BrushColor")) {
            colorValue = item.func_77978_p().func_74762_e("BrushColor");
        }
        float[] color = ColorUtil.hexToRGB(colorValue);
        TextureManager manager = Minecraft.func_71410_x().func_110434_K();
        GL11.glPushMatrix();
        if (type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
        }
        GL11.glTranslatef((float)0.0f, (float)0.26f, (float)0.3f);
        GL11.glScalef((float)0.95f, (float)0.85f, (float)0.95f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glPushAttrib((int)1048575);
        GL11.glEnable((int)3008);
        if (ConfigClient.LegacyBanner) {
            BlockWallBannerRenderer.setMaterialTexture(meta);
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            BlockWallBannerRenderer.modelLegacyWallBanner.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            manager.func_110577_a(BlockBannerRenderer.legacyFlagResource);
            GL11.glColor3f((float)color[0], (float)color[1], (float)color[2]);
            BlockWallBannerRenderer.modelLegacyWallBannerFlag.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        } else {
            BlockWallBannerRenderer.flag.BannerFlag.field_78795_f = 0.0f;
            BlockBannerRenderer.setBannerMaterial(meta);
            BlockWallBannerRenderer.model.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            manager.func_110577_a(BlockBannerRenderer.normalFlag);
            GL11.glColor3f((float)color[0], (float)color[1], (float)color[2]);
            BlockWallBannerRenderer.flag.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        }
        GL11.glDisable((int)3008);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }
}

