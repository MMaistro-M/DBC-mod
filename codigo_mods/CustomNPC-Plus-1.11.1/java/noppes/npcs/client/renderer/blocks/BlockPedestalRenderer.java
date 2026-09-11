/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.CustomItems;
import noppes.npcs.blocks.BlockRotated;
import noppes.npcs.blocks.tiles.TilePedestal;
import noppes.npcs.client.model.blocks.ModelPedestal;
import noppes.npcs.client.model.blocks.legacy.ModelLegacyPedestal;
import noppes.npcs.client.renderer.blocks.BlockRendererInterface;
import noppes.npcs.config.ConfigClient;
import org.lwjgl.opengl.GL11;

public class BlockPedestalRenderer
extends BlockRendererInterface {
    private final ModelLegacyPedestal legacyModel = new ModelLegacyPedestal();
    private static final ResourceLocation resource = new ResourceLocation("customnpcs:textures/models/legacy/npcPedestal.png");
    private final ModelPedestal model = new ModelPedestal();
    private static final ResourceLocation wood = new ResourceLocation("customnpcs:textures/models/pedestal/wood.png");
    private static final ResourceLocation stone = new ResourceLocation("customnpcs:textures/models/pedestal/stone.png");
    private static final ResourceLocation iron = new ResourceLocation("customnpcs:textures/models/pedestal/iron.png");
    private static final ResourceLocation gold = new ResourceLocation("customnpcs:textures/models/pedestal/gold.png");
    private static final ResourceLocation diamond = new ResourceLocation("customnpcs:textures/models/pedestal/diamond.png");

    public BlockPedestalRenderer() {
        ((BlockRotated)CustomItems.pedestal).renderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)this);
    }

    public void func_147500_a(TileEntity var1, double var2, double var4, double var6, float var8) {
        TilePedestal tile = (TilePedestal)var1;
        GL11.glDisable((int)32826);
        GL11.glEnable((int)3008);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)var2 + 0.5f), (float)((float)var4 + 1.5f), (float)((float)var6 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)(90 * tile.rotation), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        if (ConfigClient.LegacyPedestal) {
            BlockPedestalRenderer.setMaterialTexture(var1.func_145832_p());
            this.legacyModel.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            GL11.glScalef((float)1.0f, (float)0.99f, (float)1.0f);
            TextureManager manager = Minecraft.func_71410_x().func_110434_K();
            manager.func_110577_a(resource);
            this.legacyModel.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        } else {
            this.setPedestalTexture(var1.func_145832_p());
            GL11.glPushMatrix();
            GL11.glScalef((float)1.0f, (float)1.3f, (float)1.0f);
            GL11.glTranslatef((float)0.0f, (float)-0.34f, (float)0.0f);
            this.model.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            GL11.glPopMatrix();
            GL11.glScalef((float)1.0f, (float)0.99f, (float)1.0f);
        }
        if (!this.playerTooFar(tile)) {
            this.doRender(tile.func_70301_a(0));
        }
        GL11.glPopMatrix();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void doRender(ItemStack item) {
        if (item == null || item.func_77973_b() == null || item.func_77973_b() instanceof ItemBlock) {
            return;
        }
        GL11.glPushMatrix();
        if (ConfigClient.LegacyPedestal) {
            GL11.glTranslatef((float)0.06f, (float)0.3f, (float)0.02f);
        } else {
            GL11.glTranslatef((float)0.06f, (float)0.49f, (float)0.02f);
        }
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glScalef((float)0.6f, (float)0.6f, (float)0.6f);
        if (item.func_77973_b().func_77629_n_()) {
            GL11.glTranslatef((float)0.14f, (float)0.0f, (float)0.5f);
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        } else {
            GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        GL11.glRotatef((float)-200.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)-50.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        if (item.func_77973_b().func_77623_v()) {
            for (int k = 0; k <= item.func_77973_b().getRenderPasses(item.func_77960_j()); ++k) {
                int i = item.func_77973_b().func_82790_a(item, k);
                float f12 = (float)(i >> 16 & 0xFF) / 255.0f;
                float f4 = (float)(i >> 8 & 0xFF) / 255.0f;
                float f5 = (float)(i & 0xFF) / 255.0f;
                GL11.glColor4f((float)f12, (float)f4, (float)f5, (float)1.0f);
                RenderManager.field_78727_a.field_78721_f.func_78443_a((EntityLivingBase)Minecraft.func_71410_x().field_71439_g, item, k);
            }
        } else {
            int k = item.func_77973_b().func_82790_a(item, 0);
            float f11 = (float)(k >> 16 & 0xFF) / 255.0f;
            float f12 = (float)(k >> 8 & 0xFF) / 255.0f;
            float f4 = (float)(k & 0xFF) / 255.0f;
            GL11.glColor4f((float)f11, (float)f12, (float)f4, (float)1.0f);
            RenderManager.field_78727_a.field_78721_f.func_78443_a((EntityLivingBase)Minecraft.func_71410_x().field_71439_g, item, 0);
        }
        GL11.glPopMatrix();
    }

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        GL11.glPushMatrix();
        if (ConfigClient.LegacyPedestal) {
            GL11.glTranslatef((float)0.0f, (float)0.44f, (float)0.0f);
            GL11.glScalef((float)0.76f, (float)0.66f, (float)0.76f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            BlockPedestalRenderer.setMaterialTexture(metadata);
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            this.legacyModel.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        } else {
            GL11.glTranslatef((float)0.0f, (float)1.9f, (float)0.0f);
            GL11.glScalef((float)1.0f, (float)1.4f, (float)1.0f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            this.setPedestalTexture(metadata);
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            this.model.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        }
        GL11.glPopMatrix();
    }

    public void setPedestalTexture(int meta) {
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
        return CustomItems.pedestal.func_149645_b();
    }

    @Override
    public int specialRenderDistance() {
        return 40;
    }
}

