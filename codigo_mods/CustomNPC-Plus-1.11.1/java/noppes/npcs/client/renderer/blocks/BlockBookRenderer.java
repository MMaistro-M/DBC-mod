/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBook
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBook;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.CustomItems;
import noppes.npcs.blocks.BlockRotated;
import noppes.npcs.blocks.tiles.TileVariant;
import noppes.npcs.client.model.blocks.ModelInk;
import noppes.npcs.client.renderer.blocks.BlockRendererInterface;
import org.lwjgl.opengl.GL11;

public class BlockBookRenderer
extends BlockRendererInterface {
    private final ModelInk ink = new ModelInk();
    private final ResourceLocation resource = new ResourceLocation("textures/entity/enchanting_table_book.png");
    private final ResourceLocation resource2 = new ResourceLocation("customnpcs:textures/models/Ink.png");
    private final ModelBook book = new ModelBook();

    public BlockBookRenderer() {
        ((BlockRotated)CustomItems.book).renderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)this);
    }

    public void func_147500_a(TileEntity var1, double var2, double var4, double var6, float var8) {
        TileVariant tile = (TileVariant)var1;
        GL11.glDisable((int)32826);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)var2 + 0.5f), (float)((float)var4 + 1.5f), (float)((float)var6 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)(90 * tile.rotation - 90), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        TextureManager manager = Minecraft.func_71410_x().func_110434_K();
        manager.func_110577_a(this.resource2);
        if (!this.playerTooFar(tile)) {
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
        }
        this.ink.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        manager.func_110577_a(this.resource);
        GL11.glRotatef((float)-90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)-1.49f, (float)-0.18f, (float)0.0f);
        this.book.func_78088_a(null, 0.0f, 0.0f, 1.0f, 1.24f, 1.0f, 0.0625f);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.2f, (float)1.7f, (float)0.0f);
        GL11.glScalef((float)1.4f, (float)1.4f, (float)1.4f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)2884);
        TextureManager manager = Minecraft.func_71410_x().func_110434_K();
        manager.func_110577_a(this.resource2);
        this.ink.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        manager.func_110577_a(this.resource);
        GL11.glRotatef((float)-90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)-1.45f, (float)-0.18f, (float)0.0f);
        this.book.func_78088_a(null, 0.0f, 0.0f, 1.0f, 1.24f, 1.0f, 0.0625f);
        GL11.glPopMatrix();
    }

    public int getRenderId() {
        return CustomItems.book.func_149645_b();
    }
}

