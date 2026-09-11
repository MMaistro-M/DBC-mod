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
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import kamkeel.npcs.util.ColorUtil;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.CustomItems;
import noppes.npcs.api.item.IItemCustomizable;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.blocks.BlockWallBanner;
import noppes.npcs.blocks.tiles.TileWallBanner;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.model.blocks.banner.ModelBannerWall;
import noppes.npcs.client.model.blocks.banner.ModelBannerWallFlag;
import noppes.npcs.client.model.blocks.legacy.ModelLegacyWallBanner;
import noppes.npcs.client.model.blocks.legacy.ModelLegacyWallBannerFlag;
import noppes.npcs.client.renderer.ImageData;
import noppes.npcs.client.renderer.blocks.BlockBannerRenderer;
import noppes.npcs.client.renderer.blocks.BlockRendererInterface;
import noppes.npcs.config.ConfigClient;
import noppes.npcs.scripted.NpcAPI;
import org.lwjgl.opengl.GL11;

public class BlockWallBannerRenderer
extends BlockRendererInterface {
    public static final ModelLegacyWallBanner modelLegacyWallBanner = new ModelLegacyWallBanner();
    public static final ModelLegacyWallBannerFlag modelLegacyWallBannerFlag = new ModelLegacyWallBannerFlag();
    public static final ModelBannerWall model = new ModelBannerWall();
    public static final ModelBannerWallFlag flag = new ModelBannerWallFlag();

    public BlockWallBannerRenderer() {
        ((BlockWallBanner)CustomItems.wallBanner).renderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)this);
    }

    public void func_147500_a(TileEntity var1, double var2, double var4, double var6, float var8) {
        TileWallBanner tile = (TileWallBanner)var1;
        GL11.glPushAttrib((int)1048575);
        GL11.glDisable((int)32826);
        GL11.glEnable((int)3008);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)var2 + 0.5f), (float)((float)var4 + 0.4f), (float)((float)var6 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)(90 * tile.rotation), (float)0.0f, (float)1.0f, (float)0.0f);
        if (ConfigClient.LegacyBanner) {
            BlockWallBannerRenderer.setMaterialTexture(var1.func_145832_p());
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            modelLegacyWallBanner.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            this.func_147499_a(BlockBannerRenderer.legacyFlagResource);
            float[] color = ColorUtil.hexToRGB(tile.color);
            GL11.glColor3f((float)color[0], (float)color[1], (float)color[2]);
            modelLegacyWallBannerFlag.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            GL11.glPopMatrix();
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            if (tile.icon != null && !this.playerTooFar(tile)) {
                this.doRender(var2, var4, var6, tile.rotation, tile.icon, 0.0f);
            }
        } else {
            float angle_x;
            long worldTime = tile.func_145831_w() != null ? tile.func_145831_w().func_82737_E() : 0L;
            int animationProgress100 = (tile.field_145851_c % 100 * 7 + tile.field_145848_d % 100 * 9 + tile.field_145849_e % 100 * 13 + (int)(worldTime % 100L)) % 100;
            float f3 = (float)animationProgress100 + var8;
            BlockWallBannerRenderer.flag.BannerFlag.field_78795_f = angle_x = (-0.0125f + 0.01f * MathHelper.func_76134_b((float)(f3 * 0.01f * 2.0f * (float)Math.PI))) * (float)Math.PI;
            BlockBannerRenderer.setBannerMaterial(var1.func_145832_p());
            model.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            BlockBannerRenderer.setFlagType(tile.bannerTrim);
            float[] color = ColorUtil.hexToRGB(tile.color);
            GL11.glPushMatrix();
            GL11.glColor3f((float)color[0], (float)color[1], (float)color[2]);
            flag.func_78088_a(null, 0.0f, 0.0f, 0.0f, f3, 0.0f, 0.0625f);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            if (tile.icon != null && !this.playerTooFar(tile)) {
                this.doRender(var2, var4, var6, tile.rotation, tile.icon, angle_x);
            }
        }
        GL11.glPopAttrib();
    }

    public void doRender(double par2, double par4, double par6, int meta, ItemStack iicon, float rotate) {
        GL11.glPushMatrix();
        this.func_147499_a(TextureMap.field_110576_c);
        GL11.glTranslatef((float)((float)par2 + 0.5f), (float)((float)par4 + 0.2f), (float)((float)par6 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)(90 * meta), (float)0.0f, (float)1.0f, (float)0.0f);
        if (ConfigClient.LegacyBanner) {
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.26f);
        } else {
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.3f);
            GL11.glTranslatef((float)0.0f, (float)-0.6f, (float)0.0f);
            GL11.glRotatef((float)((float)Math.toDegrees(rotate)), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)0.0f, (float)0.6f, (float)0.0f);
        }
        GL11.glDepthMask((boolean)false);
        float f2 = 0.05f;
        Minecraft mc = Minecraft.func_71410_x();
        GL11.glScalef((float)f2, (float)f2, (float)f2);
        IItemStack iItemStack = NpcAPI.Instance().getIItemStack(iicon);
        if (iItemStack instanceof IItemCustomizable) {
            IItemCustomizable custom = (IItemCustomizable)iItemStack;
            ImageData imageData = ClientCacheHandler.getImageData(custom.getTexture());
            if (imageData.imageLoaded()) {
                imageData.bindTexture();
                int color = custom.getColor();
                float[] colors = ColorUtil.hexToRGB(color);
                this.renderCustomItemInBanner(colors[0], colors[1], colors[2]);
                GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            } else {
                this.renderItemBanner(mc.field_71446_o, iicon, -8, -8, false);
            }
        } else {
            this.renderItemBanner(mc.field_71446_o, iicon, -8, -8, false);
        }
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
    }

    private void renderCustomItemInBanner(float red, float green, float blue) {
        GL11.glDisable((int)2896);
        GL11.glTexEnvi((int)8960, (int)8704, (int)8448);
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78369_a(red, green, blue, 1.0f);
        tessellator.func_78374_a(-8.0, 8.0, 0.0, 0.0, 1.0);
        tessellator.func_78374_a(8.0, 8.0, 0.0, 1.0, 1.0);
        tessellator.func_78374_a(8.0, -8.0, 0.0, 1.0, 0.0);
        tessellator.func_78374_a(-8.0, -8.0, 0.0, 0.0, 0.0);
        tessellator.func_78381_a();
        GL11.glEnable((int)2896);
    }

    public void renderItemBanner(TextureManager txtMng, ItemStack item, int p_77015_4_, int p_77015_5_, boolean renderEffect) {
        Block block;
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
        Item loadingItem = item.func_77973_b();
        if (loadingItem instanceof ItemBlock && (block = ((ItemBlock)loadingItem).field_150939_a) != null) {
            object = block == Blocks.field_150381_bn || block == Blocks.field_150378_br ? block.func_149691_a(1, item.func_77960_j()) : (block == Blocks.field_150460_al || block == Blocks.field_150335_W ? block.func_149691_a(2, 1) : block.func_149691_a(0, item.func_77960_j()));
        }
        float f3 = (float)(l >> 16 & 0xFF) / 255.0f;
        float f4 = (float)(l >> 8 & 0xFF) / 255.0f;
        float f = (float)(l & 0xFF) / 255.0f;
        if (BlockWallBannerRenderer.renderer.field_77024_a) {
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
    }

    public int getRenderId() {
        return CustomItems.wallBanner.func_149645_b();
    }

    @Override
    public int specialRenderDistance() {
        return 26;
    }
}

