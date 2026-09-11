/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import noppes.npcs.CustomItems;
import noppes.npcs.blocks.BlockCarpentryBench;
import noppes.npcs.client.model.blocks.ModelAnvil;
import noppes.npcs.client.model.blocks.ModelCarpentryBench;
import noppes.npcs.client.model.blocks.legacy.ModelLegacyAnvil;
import noppes.npcs.client.model.blocks.legacy.ModelLegacyCarpentryBench;
import noppes.npcs.config.ConfigClient;
import org.lwjgl.opengl.GL11;

public class BlockCarpentryBenchRenderer
extends TileEntitySpecialRenderer
implements ISimpleBlockRenderingHandler {
    private final ModelCarpentryBench modelCarpentryBench = new ModelCarpentryBench();
    private static final ResourceLocation carpentryBenchTexture = new ResourceLocation("customnpcs", "textures/models/CarpentryBench.png");
    private static final ResourceLocation legacyAnvilTexture = new ResourceLocation("customnpcs", "textures/models/legacy/Steel.png");
    private static final ResourceLocation anvilTexture = new ResourceLocation("customnpcs", "textures/models/anvil.png");
    private final ModelLegacyCarpentryBench legacyBench = new ModelLegacyCarpentryBench();
    private static final ResourceLocation legacyCarpentryBench = new ResourceLocation("customnpcs", "textures/models/legacy/bench.png");
    private final ModelLegacyAnvil legacyAnvil = new ModelLegacyAnvil();
    private final ModelAnvil anvil = new ModelAnvil();

    public BlockCarpentryBenchRenderer() {
        ((BlockCarpentryBench)CustomItems.carpentyBench).renderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)this);
    }

    public void func_147500_a(TileEntity var1, double var2, double var4, double var6, float var8) {
        int meta = var1.func_145832_p();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)var2 + 0.5f), (float)((float)var4 + 1.4f), (float)((float)var6 + 0.5f));
        GL11.glScalef((float)0.99f, (float)0.99f, (float)0.99f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)(90 * (meta % 4)), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glPushAttrib((int)1048575);
        if (meta >= 4) {
            if (ConfigClient.LegacyAnvil) {
                this.func_147499_a(legacyAnvilTexture);
                this.legacyAnvil.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            } else {
                GL11.glEnable((int)3008);
                this.func_147499_a(anvilTexture);
                int light = var1.func_145831_w().func_72802_i(var1.field_145851_c, var1.field_145848_d, var1.field_145849_e, 0);
                int brightX = light % 65536;
                int brightY = light / 65536;
                int fullBright = 0xF000F0;
                int fullBrightX = fullBright % 65536;
                int fullBrightY = fullBright / 65536;
                OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)fullBrightX, (float)fullBrightY);
                this.anvil.Lava.func_78785_a(0.0625f);
                OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)brightX, (float)brightY);
                this.anvil.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
                GL11.glDisable((int)3008);
            }
        } else {
            GL11.glEnable((int)3008);
            if (ConfigClient.LegacyCarpentryBench) {
                this.func_147499_a(legacyCarpentryBench);
                this.legacyBench.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            } else {
                this.func_147499_a(carpentryBenchTexture);
                this.modelCarpentryBench.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            }
            GL11.glDisable((int)3008);
        }
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.85f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        if (metadata == 0) {
            if (ConfigClient.LegacyCarpentryBench) {
                this.func_147499_a(legacyCarpentryBench);
                this.legacyBench.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            } else {
                this.func_147499_a(carpentryBenchTexture);
                this.modelCarpentryBench.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            }
        } else if (ConfigClient.LegacyAnvil) {
            this.func_147499_a(legacyAnvilTexture);
            this.legacyAnvil.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        } else {
            this.func_147499_a(anvilTexture);
            this.anvil.Lava.func_78785_a(0.0625f);
            this.anvil.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        }
        GL11.glPopMatrix();
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return CustomItems.carpentyBench.func_149645_b();
    }
}

