/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.CustomItems;
import noppes.npcs.blocks.BlockRotated;
import noppes.npcs.blocks.tiles.TileSign;
import noppes.npcs.client.model.blocks.ModelSign;
import noppes.npcs.client.renderer.blocks.BlockRendererInterface;
import org.lwjgl.opengl.GL11;

public class BlockSignRenderer
extends BlockRendererInterface {
    private final ModelSign model = new ModelSign();

    public BlockSignRenderer() {
        ((BlockRotated)CustomItems.sign).renderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)this);
    }

    public void func_147500_a(TileEntity var1, double var2, double var4, double var6, float var8) {
        TileSign tile = (TileSign)var1;
        GL11.glDisable((int)32826);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)var2 + 0.5f), (float)((float)var4 + 1.62f), (float)((float)var6 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)(90 * tile.rotation + 90), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.func_71410_x().func_110434_K().func_110577_a(Steel);
        this.model.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        BlockSignRenderer.setWoodTexture(tile.func_145832_p());
        this.model.Sign.func_78785_a(0.0625f);
        if (tile.icon != null && !this.playerTooFar(tile)) {
            this.doRender(var2, var4, var6, tile.rotation, tile.icon);
        }
        GL11.glPopMatrix();
    }

    public void doRender(double par2, double par4, double par6, int meta, ItemStack iicon) {
        GL11.glPushMatrix();
        this.func_147499_a(TextureMap.field_110576_c);
        GL11.glTranslatef((float)0.0f, (float)1.02f, (float)-0.03f);
        GL11.glDepthMask((boolean)false);
        float f2 = 0.024f;
        Minecraft mc = Minecraft.func_71410_x();
        GL11.glScalef((float)f2, (float)f2, (float)f2);
        this.renderItemBanner(mc.field_71446_o, iicon, -8, -8, false);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-2.9f);
        this.renderItemBanner(mc.field_71446_o, iicon, -8, -8, false);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
    }

    public void renderItemBanner(TextureManager txtMng, ItemStack item, int p_77015_4_, int p_77015_5_, boolean renderEffect) {
        IIcon object = item.func_77954_c();
        GL11.glDisable((int)2896);
        GL11.glEnable((int)3042);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        ResourceLocation resourcelocation = txtMng.func_130087_a(item.func_94608_d());
        txtMng.func_110577_a(resourcelocation);
        if (object == null) {
            object = ((TextureMap)Minecraft.func_71410_x().func_110434_K().func_110581_b(resourcelocation)).func_110572_b("missingno");
        }
        int l = item.func_77973_b().func_82790_a(item, 0);
        float f3 = (float)(l >> 16 & 0xFF) / 255.0f;
        float f4 = (float)(l >> 8 & 0xFF) / 255.0f;
        float f = (float)(l & 0xFF) / 255.0f;
        if (BlockSignRenderer.renderer.field_77024_a) {
            GL11.glColor4f((float)f3, (float)f4, (float)f, (float)1.0f);
        }
        GL11.glDisable((int)2896);
        GL11.glEnable((int)3008);
        GL11.glEnable((int)3042);
        renderer.func_94149_a(p_77015_4_, p_77015_5_, object, 16, 16);
        GL11.glEnable((int)2896);
        GL11.glDisable((int)3008);
        GL11.glDisable((int)3042);
        if (renderEffect && item.hasEffect(0)) {
            renderer.renderEffect(txtMng, p_77015_4_, p_77015_5_);
        }
        GL11.glEnable((int)2896);
        GL11.glEnable((int)2884);
    }

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.6f, (float)0.0f);
        GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.func_71410_x().func_110434_K().func_110577_a(Steel);
        this.model.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        BlockSignRenderer.setWoodTexture(metadata);
        this.model.Sign.func_78785_a(0.0625f);
        GL11.glPopMatrix();
    }

    public int getRenderId() {
        return CustomItems.sign.func_149645_b();
    }
}

