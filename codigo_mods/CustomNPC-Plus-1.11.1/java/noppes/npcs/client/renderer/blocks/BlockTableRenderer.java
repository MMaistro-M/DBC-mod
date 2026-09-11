/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
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
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.CustomItems;
import noppes.npcs.blocks.BlockTable;
import noppes.npcs.blocks.tiles.TileVariant;
import noppes.npcs.client.model.blocks.ModelTable;
import noppes.npcs.client.model.blocks.legacy.ModelLegacyTable;
import noppes.npcs.client.renderer.blocks.BlockRendererInterface;
import noppes.npcs.config.ConfigClient;
import org.lwjgl.opengl.GL11;

public class BlockTableRenderer
extends BlockRendererInterface {
    private final ModelLegacyTable legacyTable = new ModelLegacyTable();
    private final ModelTable table = new ModelTable();
    private static final ResourceLocation oak = new ResourceLocation("customnpcs", "textures/models/table/oak.png");
    private static final ResourceLocation spruce = new ResourceLocation("customnpcs", "textures/models/table/spruce.png");
    private static final ResourceLocation birch = new ResourceLocation("customnpcs", "textures/models/table/birch.png");
    private static final ResourceLocation jungle = new ResourceLocation("customnpcs", "textures/models/table/jungle.png");
    private static final ResourceLocation acacia = new ResourceLocation("customnpcs", "textures/models/table/acacia.png");
    private static final ResourceLocation dark_oak = new ResourceLocation("customnpcs", "textures/models/table/dark_oak.png");

    public BlockTableRenderer() {
        ((BlockTable)CustomItems.table).renderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)this);
    }

    public void func_147500_a(TileEntity var1, double var2, double var4, double var6, float var8) {
        TileVariant tile = (TileVariant)var1;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)var2 + 0.5f), (float)((float)var4 + 1.5f), (float)((float)var6 + 0.5f));
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        boolean south = var1.func_145831_w().func_147439_a(var1.field_145851_c + 1, var1.field_145848_d, var1.field_145849_e) == CustomItems.table;
        boolean north = var1.func_145831_w().func_147439_a(var1.field_145851_c - 1, var1.field_145848_d, var1.field_145849_e) == CustomItems.table;
        boolean east = var1.func_145831_w().func_147439_a(var1.field_145851_c, var1.field_145848_d, var1.field_145849_e + 1) == CustomItems.table;
        boolean west = var1.func_145831_w().func_147439_a(var1.field_145851_c, var1.field_145848_d, var1.field_145849_e - 1) == CustomItems.table;
        this.table.Shape1.field_78806_j = !south && !east;
        this.legacyTable.Shape1.field_78806_j = this.table.Shape1.field_78806_j;
        this.table.Shape3.field_78806_j = !north && !west;
        this.legacyTable.Shape3.field_78806_j = this.table.Shape3.field_78806_j;
        this.table.Shape4.field_78806_j = !north && !east;
        this.legacyTable.Shape4.field_78806_j = this.table.Shape4.field_78806_j;
        this.table.Shape5.field_78806_j = !south && !west;
        this.legacyTable.Shape5.field_78806_j = this.table.Shape5.field_78806_j;
        GL11.glPushAttrib((int)1048575);
        GL11.glEnable((int)3008);
        if (ConfigClient.LegacyTable) {
            BlockTableRenderer.setWoodTexture(var1.func_145832_p());
            this.legacyTable.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            GL11.glRotatef((float)(90 * tile.rotation), (float)0.0f, (float)1.0f, (float)0.0f);
            this.legacyTable.Table.func_78785_a(0.0625f);
        } else {
            this.setTableTexture(var1.func_145832_p());
            this.table.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            GL11.glRotatef((float)(90 * tile.rotation), (float)0.0f, (float)1.0f, (float)0.0f);
            this.table.Table.func_78785_a(0.0625f);
        }
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.9f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        if (ConfigClient.LegacyTable) {
            BlockTableRenderer.setWoodTexture(metadata);
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            this.legacyTable.Table.func_78785_a(0.0625f);
            this.legacyTable.Shape1.field_78806_j = true;
            this.legacyTable.Shape3.field_78806_j = true;
            this.legacyTable.Shape4.field_78806_j = true;
            this.legacyTable.Shape5.field_78806_j = true;
            this.legacyTable.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        } else {
            this.setTableTexture(metadata);
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            this.table.Table.func_78785_a(0.0625f);
            this.table.Shape1.field_78806_j = true;
            this.table.Shape3.field_78806_j = true;
            this.table.Shape4.field_78806_j = true;
            this.table.Shape5.field_78806_j = true;
            this.table.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        }
        GL11.glPopMatrix();
    }

    public void setTableTexture(int meta) {
        TextureManager manager = Minecraft.func_71410_x().func_110434_K();
        if (meta == 1) {
            manager.func_110577_a(spruce);
        } else if (meta == 2) {
            manager.func_110577_a(birch);
        } else if (meta == 3) {
            manager.func_110577_a(jungle);
        } else if (meta == 4) {
            manager.func_110577_a(acacia);
        } else if (meta == 5) {
            manager.func_110577_a(dark_oak);
        } else {
            manager.func_110577_a(oak);
        }
    }

    public int getRenderId() {
        return CustomItems.table.func_149645_b();
    }
}

