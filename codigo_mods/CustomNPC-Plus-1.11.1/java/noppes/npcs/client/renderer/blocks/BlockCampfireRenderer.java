/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderBlocks
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcs;
import noppes.npcs.blocks.BlockRotated;
import noppes.npcs.blocks.tiles.TileVariant;
import noppes.npcs.client.model.blocks.ModelCampfire;
import noppes.npcs.client.model.blocks.campfire.ModelCampfireCoals;
import noppes.npcs.client.model.blocks.campfire.ModelCampfireFlame;
import noppes.npcs.client.model.blocks.campfire.ModelCampfireLog;
import noppes.npcs.client.model.blocks.campfire.ModelCampfireStone;
import noppes.npcs.client.renderer.blocks.BlockRendererInterface;
import noppes.npcs.config.ConfigClient;
import org.lwjgl.opengl.GL11;

public class BlockCampfireRenderer
extends BlockRendererInterface {
    private final ModelCampfire legacy_model = new ModelCampfire();
    private final ModelCampfireFlame flameModel = new ModelCampfireFlame();
    private final ModelCampfireLog logsModel = new ModelCampfireLog();
    private final ModelCampfireStone stonesModel = new ModelCampfireStone();
    private final ModelCampfireCoals coalsModel = new ModelCampfireCoals();
    protected static final ResourceLocation coalsTexture = new ResourceLocation("customnpcs", "textures/models/CampfireCoals.png");

    public BlockCampfireRenderer() {
        ((BlockRotated)CustomItems.campfire_unlit).renderId = ((BlockRotated)CustomItems.campfire).renderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)this);
    }

    public void func_147500_a(TileEntity var1, double var2, double var4, double var6, float var8) {
        TileVariant tile = (TileVariant)var1;
        GL11.glDisable((int)32826);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)var2 + 0.5f), (float)((float)var4 + 1.5f), (float)((float)var6 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)(45 * tile.rotation), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        if (ConfigClient.LegacyCampfire) {
            Minecraft.func_71410_x().func_110434_K().func_110577_a(PlanksOak);
            this.legacy_model.renderLog(0.0625f);
            Minecraft.func_71410_x().func_110434_K().func_110577_a(Stone);
            this.legacy_model.renderRock(0.0625f);
        } else {
            Block block = tile.func_145838_q();
            Minecraft.func_71410_x().func_110434_K().func_110577_a(Logs);
            this.logsModel.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            Minecraft.func_71410_x().func_110434_K().func_110577_a(Cobble);
            this.stonesModel.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            if (block == CustomItems.campfire) {
                GL11.glPushAttrib((int)1048575);
                GL11.glDisable((int)2896);
                GL11.glEnable((int)3008);
                Minecraft.func_71410_x().func_110434_K().func_110577_a(Flame);
                float animTime = (float)CustomNpcs.ticks + var8;
                this.flameModel.func_78088_a(null, animTime, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
                GL11.glDisable((int)3008);
                GL11.glEnable((int)2896);
                GL11.glPopAttrib();
            }
            Minecraft.func_71410_x().func_110434_K().func_110577_a(coalsTexture);
            this.coalsModel.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        }
        GL11.glPopMatrix();
    }

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)1.2f, (float)0.0f);
        GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        if (ConfigClient.LegacyCampfire) {
            Minecraft.func_71410_x().func_110434_K().func_110577_a(PlanksOak);
            this.legacy_model.renderLog(0.0625f);
            Minecraft.func_71410_x().func_110434_K().func_110577_a(Stone);
            this.legacy_model.renderRock(0.0625f);
        } else {
            Minecraft.func_71410_x().func_110434_K().func_110577_a(Logs);
            this.logsModel.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            Minecraft.func_71410_x().func_110434_K().func_110577_a(Cobble);
            this.stonesModel.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        }
        GL11.glPopMatrix();
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return ConfigClient.LegacyCampfire;
    }

    public int getRenderId() {
        return CustomItems.campfire.func_149645_b();
    }
}

