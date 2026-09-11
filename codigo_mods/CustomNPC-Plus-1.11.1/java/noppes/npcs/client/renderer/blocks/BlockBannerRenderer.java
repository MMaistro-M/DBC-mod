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
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.CustomItems;
import noppes.npcs.api.item.IItemCustomizable;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.blocks.BlockBanner;
import noppes.npcs.blocks.tiles.TileBanner;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.model.blocks.banner.ModelBannerFloor;
import noppes.npcs.client.model.blocks.banner.ModelBannerFloorFlag;
import noppes.npcs.client.model.blocks.legacy.ModelLegacyBanner;
import noppes.npcs.client.model.blocks.legacy.ModelLegacyBannerFlag;
import noppes.npcs.client.renderer.ImageData;
import noppes.npcs.client.renderer.blocks.BlockRendererInterface;
import noppes.npcs.config.ConfigClient;
import noppes.npcs.constants.EnumBannerVariant;
import noppes.npcs.scripted.NpcAPI;
import org.lwjgl.opengl.GL11;

public class BlockBannerRenderer
extends BlockRendererInterface {
    public static final ModelLegacyBanner legacyBanner = new ModelLegacyBanner();
    public static final ModelLegacyBannerFlag legacyFlag = new ModelLegacyBannerFlag();
    public static final ModelBannerFloor banner = new ModelBannerFloor();
    public static final ModelBannerFloorFlag flag = new ModelBannerFloorFlag();
    public static final ResourceLocation legacyFlagResource = new ResourceLocation("customnpcs", "textures/models/legacy/banner.png");
    public static final ResourceLocation normalFlag = new ResourceLocation("customnpcs", "textures/models/banner/flag/normal.png");
    public static final ResourceLocation pointyFlag = new ResourceLocation("customnpcs", "textures/models/banner/flag/pointy.png");
    public static final ResourceLocation triangleFlag = new ResourceLocation("customnpcs", "textures/models/banner/flag/triangle.png");
    public static final ResourceLocation tornFlag = new ResourceLocation("customnpcs", "textures/models/banner/flag/torn.png");
    public static final ResourceLocation curvedFlag = new ResourceLocation("customnpcs", "textures/models/banner/flag/curved.png");
    protected static final ResourceLocation BannerWood = new ResourceLocation("customnpcs", "textures/models/banner/BannerFloorWood.png");
    protected static final ResourceLocation BannerStone = new ResourceLocation("customnpcs", "textures/models/banner/BannerFloorStone.png");
    protected static final ResourceLocation BannerIron = new ResourceLocation("customnpcs", "textures/models/banner/BannerFloorIron.png");
    protected static final ResourceLocation BannerGold = new ResourceLocation("customnpcs", "textures/models/banner/BannerFloorGold.png");
    protected static final ResourceLocation BannerDiamond = new ResourceLocation("customnpcs", "textures/models/banner/BannerFloorDiamond.png");

    public BlockBannerRenderer() {
        ((BlockBanner)CustomItems.banner).renderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)this);
    }

    public void func_147500_a(TileEntity var1, double var2, double var4, double var6, float var8) {
        TileBanner tile = (TileBanner)var1;
        GL11.glPushAttrib((int)1048575);
        GL11.glDisable((int)32826);
        GL11.glEnable((int)3008);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)var2 + 0.5f), (float)((float)var4 + 1.5f), (float)((float)var6 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)(90 * tile.rotation), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        if (ConfigClient.LegacyBanner) {
            BlockBannerRenderer.setMaterialTexture(var1.func_145832_p());
            legacyBanner.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            this.func_147499_a(legacyFlagResource);
            float[] color = ColorUtil.hexToRGB(tile.color);
            GL11.glColor3f((float)color[0], (float)color[1], (float)color[2]);
            legacyFlag.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
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
            BlockBannerRenderer.flag.BannerFlag.field_78795_f = angle_x = (-0.0125f + 0.01f * MathHelper.func_76134_b((float)(f3 * 0.01f * 2.0f * (float)Math.PI))) * (float)Math.PI;
            BlockBannerRenderer.setBannerMaterial(var1.func_145832_p());
            banner.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
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
        GL11.glTranslatef((float)((float)par2 + 0.5f), (float)((float)par4 + 1.3f), (float)((float)par6 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)(90 * meta), (float)0.0f, (float)1.0f, (float)0.0f);
        if (ConfigClient.LegacyBanner) {
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.14f);
        } else {
            GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-0.075f);
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
        if (BlockBannerRenderer.renderer.field_77024_a) {
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
        return CustomItems.banner.func_149645_b();
    }

    @Override
    public int specialRenderDistance() {
        return 26;
    }

    public static void setBannerMaterial(int meta) {
        TextureManager manager = Minecraft.func_71410_x().func_110434_K();
        if (meta == 1) {
            manager.func_110577_a(BannerStone);
        } else if (meta == 2) {
            manager.func_110577_a(BannerIron);
        } else if (meta == 3) {
            manager.func_110577_a(BannerGold);
        } else if (meta == 4) {
            manager.func_110577_a(BannerDiamond);
        } else {
            manager.func_110577_a(BannerWood);
        }
    }

    public static void setFlagType(EnumBannerVariant variant) {
        TextureManager manager = Minecraft.func_71410_x().func_110434_K();
        switch (variant) {
            case Pointy: {
                manager.func_110577_a(pointyFlag);
                break;
            }
            case Triangle: {
                manager.func_110577_a(triangleFlag);
                break;
            }
            case Torn: {
                manager.func_110577_a(tornFlag);
                break;
            }
            case Curved: {
                manager.func_110577_a(curvedFlag);
                break;
            }
            default: {
                manager.func_110577_a(normalFlag);
            }
        }
    }
}

