/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
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
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import noppes.npcs.CustomItems;
import noppes.npcs.blocks.BlockLantern;
import noppes.npcs.blocks.tiles.TileLamp;
import noppes.npcs.client.model.blocks.lantern.LanternCeiling;
import noppes.npcs.client.model.blocks.lantern.LanternFloor;
import noppes.npcs.client.model.blocks.lantern.LanternWall;
import noppes.npcs.client.model.blocks.legacy.ModelLegacyCeiling;
import noppes.npcs.client.model.blocks.legacy.ModelLegacyLantern;
import noppes.npcs.client.model.blocks.legacy.ModelLegacyWall;
import noppes.npcs.config.ConfigClient;
import org.lwjgl.opengl.GL11;

public class BlockLanternRenderer
extends TileEntitySpecialRenderer
implements ISimpleBlockRenderingHandler {
    private final ModelLegacyLantern modelLegacyLantern = new ModelLegacyLantern();
    private final ModelLegacyCeiling modelLegacyCeiling = new ModelLegacyCeiling();
    private final ModelLegacyWall modelLegacyWall = new ModelLegacyWall();
    private static final ResourceLocation legacyTexture = new ResourceLocation("customnpcs", "textures/models/legacy/Lamp.png");
    private final LanternFloor modelLantern = new LanternFloor();
    private final LanternWall modelLanternWall = new LanternWall();
    private final LanternCeiling modelLanternCeiling = new LanternCeiling();
    private static final ResourceLocation texture = new ResourceLocation("customnpcs", "textures/models/lantern.png");

    public BlockLanternRenderer() {
        ((BlockLantern)CustomItems.lantern_unlit).renderId = ((BlockLantern)CustomItems.lantern).renderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)this);
    }

    public void func_147500_a(TileEntity var1, double var2, double var4, double var6, float var8) {
        TileLamp tile = (TileLamp)var1;
        GL11.glDisable((int)32826);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)var2 + 0.5f), (float)((float)var4 + 1.5f), (float)((float)var6 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPushAttrib((int)1048575);
        GL11.glEnable((int)3008);
        if (ConfigClient.LegacyLantern) {
            GL11.glRotatef((float)(45 * tile.rotation), (float)0.0f, (float)1.0f, (float)0.0f);
            Minecraft.func_71410_x().func_110434_K().func_110577_a(legacyTexture);
            if (tile.variant == 0) {
                this.modelLegacyLantern.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            } else if (tile.variant == 1) {
                this.modelLegacyCeiling.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            } else {
                this.modelLegacyWall.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            }
        } else {
            Minecraft.func_71410_x().func_110434_K().func_110577_a(texture);
            if (tile.variant == 0) {
                GL11.glRotatef((float)(45 * tile.rotation), (float)0.0f, (float)1.0f, (float)0.0f);
                this.modelLantern.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            } else if (tile.variant == 1) {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                this.modelLanternCeiling.Chain.func_78785_a(0.0625f);
                GL11.glRotatef((float)(45 * tile.rotation), (float)0.0f, (float)1.0f, (float)0.0f);
                this.modelLanternCeiling.Lantern.func_78785_a(0.0625f);
                this.modelLanternCeiling.Light.func_78785_a(0.0625f);
            } else {
                GL11.glRotatef((float)(45 * tile.rotation), (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)0.0f, (float)0.3f, (float)0.0f);
                this.modelLanternWall.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            }
        }
        GL11.glDisable((int)3008);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)2.2f, (float)0.0f);
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        if (ConfigClient.LegacyLantern) {
            Minecraft.func_71410_x().func_110434_K().func_110577_a(legacyTexture);
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            this.modelLegacyLantern.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        } else {
            GL11.glScalef((float)0.7f, (float)0.7f, (float)0.7f);
            GL11.glTranslatef((float)0.0f, (float)0.4f, (float)0.0f);
            Minecraft.func_71410_x().func_110434_K().func_110577_a(texture);
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            this.modelLantern.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        }
        GL11.glPopMatrix();
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return ConfigClient.LegacyLantern;
    }

    public int getRenderId() {
        return CustomItems.lantern.func_149645_b();
    }
}

