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
 *  net.minecraft.tileentity.TileEntity
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
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.CustomItems;
import noppes.npcs.blocks.BlockShortLamp;
import noppes.npcs.blocks.tiles.TileShortLamp;
import noppes.npcs.client.model.blocks.lamp.ModelShortLamp;
import noppes.npcs.client.renderer.blocks.BlockRendererInterface;
import org.lwjgl.opengl.GL11;

public class BlockShortLampRenderer
extends BlockRendererInterface {
    public static final ModelShortLamp model = new ModelShortLamp();
    public static final ResourceLocation wood = new ResourceLocation("customnpcs", "textures/models/lamp/short/wood.png");
    public static final ResourceLocation stone = new ResourceLocation("customnpcs", "textures/models/lamp/short/stone.png");
    public static final ResourceLocation iron = new ResourceLocation("customnpcs", "textures/models/lamp/short/iron.png");
    public static final ResourceLocation gold = new ResourceLocation("customnpcs", "textures/models/lamp/short/gold.png");
    public static final ResourceLocation diamond = new ResourceLocation("customnpcs", "textures/models/lamp/short/diamond.png");

    public BlockShortLampRenderer() {
        ((BlockShortLamp)CustomItems.shortLamp).renderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)this);
    }

    public void func_147500_a(TileEntity tileEntity, double x, double y, double z, float partialTicks) {
        TileShortLamp tile = (TileShortLamp)tileEntity;
        GL11.glPushAttrib((int)1048575);
        GL11.glDisable((int)32826);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 1.5f), (float)((float)z + 0.5f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)(90 * tile.rotation), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        BlockShortLampRenderer.setLampTexture(tileEntity.func_145832_p());
        BlockShortLampRenderer.model.Lamp.func_78785_a(0.0625f);
        int light = tileEntity.func_145831_w().func_72802_i(tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e, 0);
        int brightX = light % 65536;
        int brightY = light / 65536;
        int fullBright = 0xF000F0;
        int fullBrightX = fullBright % 65536;
        int fullBrightY = fullBright / 65536;
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)fullBrightX, (float)fullBrightY);
        BlockShortLampRenderer.model.Light.func_78785_a(0.0625f);
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)brightX, (float)brightY);
        float[] color = ColorUtil.hexToRGB(tile.color);
        GL11.glColor3f((float)color[0], (float)color[1], (float)color[2]);
        BlockShortLampRenderer.model.Shade.func_78785_a(0.0625f);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    }

    public static void setLampTexture(int meta) {
        TextureManager manager = Minecraft.func_71410_x().func_110434_K();
        if (meta == 1) {
            manager.func_110577_a(stone);
        } else if (meta == 2) {
            manager.func_110577_a(iron);
        } else if (meta == 3) {
            manager.func_110577_a(gold);
        } else if (meta == 4) {
            manager.func_110577_a(diamond);
        } else {
            manager.func_110577_a(wood);
        }
    }

    public int getRenderId() {
        return CustomItems.shortLamp.func_149645_b();
    }
}

