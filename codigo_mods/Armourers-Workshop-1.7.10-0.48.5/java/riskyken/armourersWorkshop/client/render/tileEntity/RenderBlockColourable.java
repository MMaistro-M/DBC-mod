/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.render.tileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.skin.cubes.ICubeColour;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartTypeTextured;
import riskyken.armourersWorkshop.client.render.IRenderBuffer;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.client.render.RenderBridge;
import riskyken.armourersWorkshop.common.items.ModItems;
import riskyken.armourersWorkshop.common.painting.IBlockPainter;
import riskyken.armourersWorkshop.common.painting.PaintType;
import riskyken.armourersWorkshop.common.tileentities.TileEntityBoundingBox;
import riskyken.armourersWorkshop.common.tileentities.TileEntityColourable;

@SideOnly(value=Side.CLIENT)
public class RenderBlockColourable
extends TileEntitySpecialRenderer {
    private static final ResourceLocation MARKERS = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/tileEntities/markers.png");
    private final IRenderBuffer renderer = RenderBridge.INSTANCE;
    private final Minecraft mc = Minecraft.func_71410_x();
    private static float markerAlpha = 0.0f;
    private static long lastWorldTimeUpdate;

    public void func_147500_a(TileEntity tileEntity, double x, double y, double z, float partialTickTime) {
        if (lastWorldTimeUpdate != tileEntity.func_145831_w().func_82737_E()) {
            lastWorldTimeUpdate = tileEntity.func_145831_w().func_82737_E();
            if (this.isPlayerHoldingPaintingTool()) {
                if ((markerAlpha += 0.25f) > 1.0f) {
                    markerAlpha = 1.0f;
                }
            } else if ((markerAlpha -= 0.25f) < 0.0f) {
                markerAlpha = 0.0f;
            }
        }
        if (!(markerAlpha > 0.0f)) {
            return;
        }
        if (tileEntity instanceof TileEntityColourable) {
            this.renderTileEntityAt((TileEntityColourable)tileEntity, x, y, z, partialTickTime);
        } else if (tileEntity instanceof TileEntityBoundingBox) {
            this.renderTileEntityAt((TileEntityBoundingBox)tileEntity, x, y, z, partialTickTime);
        }
    }

    public void renderTileEntityAt(TileEntityColourable tileEntity, double x, double y, double z, float partialTickTime) {
        ICubeColour cubeColour = tileEntity.getColour();
        GL11.glDisable((int)2896);
        ModRenderHelper.enableAlphaBlend();
        this.renderer.startDrawingQuads();
        this.renderer.setColourRGBA_F(0.7f, 0.7f, 0.7f, markerAlpha);
        if (markerAlpha > 0.0f) {
            for (int i = 0; i < 6; ++i) {
                ForgeDirection dir = ForgeDirection.getOrientation((int)i);
                int paintType = cubeColour.getPaintType(i) & 0xFF;
                if (paintType == 255) continue;
                this.func_147499_a(MARKERS);
                GL11.glColor3f((float)0.77f, (float)0.77f, (float)0.77f);
                PaintType pt = PaintType.getPaintTypeFromUKey(paintType);
                this.renderFaceWithMarker(x, y, z, dir, pt.ordinal());
            }
        }
        this.renderer.draw();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ModRenderHelper.disableAlphaBlend();
        ModRenderHelper.enableLighting();
        RenderHelper.func_74519_b();
    }

    public void renderTileEntityAt(TileEntityBoundingBox tileEntity, double x, double y, double z, float partialTickTime) {
        if (!(tileEntity.getSkinPart() instanceof ISkinPartTypeTextured)) {
            return;
        }
        GL11.glDisable((int)2896);
        ModRenderHelper.enableAlphaBlend();
        this.renderer.startDrawingQuads();
        this.renderer.setColourRGBA_F(0.7f, 0.7f, 0.7f, markerAlpha);
        if (markerAlpha > 0.0f) {
            for (int i = 0; i < 6; ++i) {
                if (!tileEntity.isPaintableSide(i)) continue;
                ForgeDirection dir = ForgeDirection.getOrientation((int)i);
                PaintType paintType = tileEntity.getPaintType(i);
                if (paintType == PaintType.NONE) continue;
                this.func_147499_a(MARKERS);
                GL11.glColor3f((float)0.77f, (float)0.77f, (float)0.77f);
                this.renderFaceWithMarker(x, y, z, dir, paintType.ordinal());
            }
        }
        this.renderer.draw();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ModRenderHelper.disableAlphaBlend();
        ModRenderHelper.enableLighting();
        RenderHelper.func_74519_b();
    }

    private void renderFaceWithMarker(double x, double y, double z, ForgeDirection face, int marker) {
        float tileScale = 0.25f;
        float ySrc = (float)Math.floor((double)marker / 4.0);
        float xSrc = (float)marker - ySrc * 4.0f;
        float xStart = tileScale * xSrc;
        float yStart = tileScale * ySrc;
        float xEnd = xStart + tileScale * 1.0f;
        float yEnd = yStart + tileScale * 1.0f;
        float offset = 0.001f;
        switch (face) {
            case DOWN: {
                this.renderer.addVertexWithUV(x, y - (double)offset, z, xStart, yEnd);
                this.renderer.addVertexWithUV(x + 1.0, y - (double)offset, z, xEnd, yEnd);
                this.renderer.addVertexWithUV(x + 1.0, y - (double)offset, z + 1.0, xEnd, yStart);
                this.renderer.addVertexWithUV(x, y - (double)offset, z + 1.0, xStart, yStart);
                break;
            }
            case UP: {
                this.renderer.addVertexWithUV(x, y + 1.0 + (double)offset, z + 1.0, xStart, yEnd);
                this.renderer.addVertexWithUV(x + 1.0, y + 1.0 + (double)offset, z + 1.0, xEnd, yEnd);
                this.renderer.addVertexWithUV(x + 1.0, y + 1.0 + (double)offset, z, xEnd, yStart);
                this.renderer.addVertexWithUV(x, y + 1.0 + (double)offset, z, xStart, yStart);
                break;
            }
            case NORTH: {
                this.renderer.addVertexWithUV(x + 1.0, y, z - (double)offset, xStart, yEnd);
                this.renderer.addVertexWithUV(x, y, z - (double)offset, xEnd, yEnd);
                this.renderer.addVertexWithUV(x, y + 1.0, z - (double)offset, xEnd, yStart);
                this.renderer.addVertexWithUV(x + 1.0, y + 1.0, z - (double)offset, xStart, yStart);
                break;
            }
            case SOUTH: {
                this.renderer.addVertexWithUV(x, y, z + 1.0 + (double)offset, xStart, yEnd);
                this.renderer.addVertexWithUV(x + 1.0, y, z + 1.0 + (double)offset, xEnd, yEnd);
                this.renderer.addVertexWithUV(x + 1.0, y + 1.0, z + 1.0 + (double)offset, xEnd, yStart);
                this.renderer.addVertexWithUV(x, y + 1.0, z + 1.0 + (double)offset, xStart, yStart);
                break;
            }
            case WEST: {
                this.renderer.addVertexWithUV(x - (double)offset, y, z, xStart, yEnd);
                this.renderer.addVertexWithUV(x - (double)offset, y, z + 1.0, xEnd, yEnd);
                this.renderer.addVertexWithUV(x - (double)offset, y + 1.0, z + 1.0, xEnd, yStart);
                this.renderer.addVertexWithUV(x - (double)offset, y + 1.0, z, xStart, yStart);
                break;
            }
            case EAST: {
                this.renderer.addVertexWithUV(x + 1.0 + (double)offset, y, z + 1.0, xStart, yEnd);
                this.renderer.addVertexWithUV(x + 1.0 + (double)offset, y, z, xEnd, yEnd);
                this.renderer.addVertexWithUV(x + 1.0 + (double)offset, y + 1.0, z, xEnd, yStart);
                this.renderer.addVertexWithUV(x + 1.0 + (double)offset, y + 1.0, z + 1.0, xStart, yStart);
                break;
            }
        }
    }

    private boolean isPlayerHoldingPaintingTool() {
        EntityClientPlayerMP player = this.mc.field_71439_g;
        ItemStack stack = player.func_71045_bC();
        if (stack != null) {
            Item item = stack.func_77973_b();
            if (item instanceof IBlockPainter) {
                return true;
            }
            if (item == ModItems.colourPicker) {
                return true;
            }
            if (item == ModItems.blockMarker) {
                return true;
            }
        }
        return false;
    }
}

