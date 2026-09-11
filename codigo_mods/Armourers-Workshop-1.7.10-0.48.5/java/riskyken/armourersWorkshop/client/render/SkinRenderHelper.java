/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.IPoint3D;
import riskyken.armourersWorkshop.api.common.IRectangle3D;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.common.config.ConfigHandlerClient;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.skin.type.SkinTypeRegistry;
import riskyken.armourersWorkshop.common.skin.type.block.SkinBlock;

@SideOnly(value=Side.CLIENT)
public final class SkinRenderHelper {
    private static final ResourceLocation guideImage = new ResourceLocation("armourersWorkshop".toLowerCase(), "textures/blocks/guide.png");

    public static void renderBuildingGuide(ISkinType skinType, float scale, SkinProperties skinProps, boolean showHelper) {
        for (int i = 0; i < skinType.getSkinParts().size(); ++i) {
            ISkinPartType skinPart = skinType.getSkinParts().get(i);
            if (skinPart.isModelOverridden(skinProps)) continue;
            IPoint3D partOffset = skinPart.getOffset();
            GL11.glTranslated((double)((float)partOffset.getX() * scale), (double)((float)partOffset.getY() * scale), (double)((float)partOffset.getZ() * scale));
            skinPart.renderBuildingGuide(scale, skinProps, showHelper);
            GL11.glTranslated((double)((float)(-partOffset.getX()) * scale), (double)((float)(-partOffset.getY()) * scale), (double)((float)(-partOffset.getZ()) * scale));
        }
    }

    public static void renderBuildingGrid(ISkinType skinType, float scale, boolean showGuides, SkinProperties skinProps, boolean multiblock) {
        for (int i = 0; i < skinType.getSkinParts().size(); ++i) {
            ISkinPartType skinPartType = skinType.getSkinParts().get(i);
            IPoint3D partOffset = skinPartType.getOffset();
            GL11.glTranslated((double)((float)partOffset.getX() * scale), (double)((float)partOffset.getY() * scale), (double)((float)partOffset.getZ() * scale));
            if (skinType == SkinTypeRegistry.skinBlock) {
                if (skinPartType.getPartName().equals("multiblock") & multiblock) {
                    SkinRenderHelper.renderBuildingGrid(((SkinBlock)SkinTypeRegistry.skinBlock).partBase, scale, showGuides, skinProps, 1.0f, 1.0f, 0.0f, 0.2f);
                    GL11.glPolygonOffset((float)6.0f, (float)6.0f);
                    SkinRenderHelper.renderBuildingGrid(skinPartType, scale, showGuides, skinProps, 0.5f, 0.5f, 0.5f, 0.25f);
                } else if (skinPartType.getPartName().equals("base") & !multiblock) {
                    SkinRenderHelper.renderBuildingGrid(skinPartType, scale, showGuides, skinProps, 0.5f, 0.5f, 0.5f, 0.25f);
                }
            } else {
                SkinRenderHelper.renderBuildingGrid(skinPartType, scale, showGuides, skinProps, 0.5f, 0.5f, 0.5f, 0.25f);
            }
            GL11.glTranslated((double)((float)(-partOffset.getX()) * scale), (double)((float)(-partOffset.getY()) * scale), (double)((float)(-partOffset.getZ()) * scale));
        }
    }

    public static void renderBuildingGrid(ISkinPartType skinPartType, float scale, boolean showGuides, SkinProperties skinProps, float r, float g, float b, float a) {
        GL11.glTranslated((double)0.0, (double)((float)skinPartType.getBuildingSpace().getY() * scale), (double)0.0);
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        SkinRenderHelper.renderGuidePart(skinPartType, scale, showGuides, skinProps, r, g, b, a);
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        GL11.glTranslated((double)0.0, (double)((float)(-skinPartType.getBuildingSpace().getY()) * scale), (double)0.0);
    }

    private static void renderGuidePart(ISkinPartType part, float scale, boolean showGuides, SkinProperties skinProps, float r, float g, float b, float a) {
        Minecraft.func_71410_x().func_110434_K().func_110577_a(guideImage);
        IRectangle3D buildRec = part.getBuildingSpace();
        IRectangle3D guideRec = part.getGuideSpace();
        GL11.glPushMatrix();
        GL11.glDisable((int)2896);
        if (showGuides) {
            GL11.glColor4f((float)r, (float)g, (float)b, (float)a);
            SkinRenderHelper.renderGuideBox(buildRec.getX(), buildRec.getY(), buildRec.getZ(), buildRec.getWidth(), buildRec.getHeight(), buildRec.getDepth(), scale);
            GL11.glColor4f((float)0.0f, (float)1.0f, (float)0.0f, (float)0.5f);
            SkinRenderHelper.renderGuideBox(-0.5, -0.5, -0.5, 1, 1, 1, scale);
        }
        if (ConfigHandlerClient.showArmourerDebugRender) {
            GL11.glColor4f((float)1.0f, (float)0.0f, (float)0.0f, (float)0.25f);
            SkinRenderHelper.renderGuideBox(guideRec.getX(), guideRec.getY(), guideRec.getZ(), guideRec.getWidth(), guideRec.getHeight(), guideRec.getDepth(), scale);
        }
        if (part.isModelOverridden(skinProps)) {
            GL11.glColor4f((float)0.0f, (float)0.0f, (float)1.0f, (float)0.25f);
            SkinRenderHelper.renderGuideBox(guideRec.getX(), guideRec.getY(), guideRec.getZ(), guideRec.getWidth(), guideRec.getHeight(), guideRec.getDepth(), scale);
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)2896);
        GL11.glPopMatrix();
    }

    private static void renderGuideBox(double x, double y, double z, int width, int height, int depth, float scale) {
        SkinRenderHelper.renderGuideFace(ForgeDirection.DOWN, x, y, z, width, depth, scale);
        SkinRenderHelper.renderGuideFace(ForgeDirection.UP, x, y + (double)height, z, width, depth, scale);
        SkinRenderHelper.renderGuideFace(ForgeDirection.EAST, x + (double)width, y, z, depth, height, scale);
        SkinRenderHelper.renderGuideFace(ForgeDirection.WEST, x, y, z, depth, height, scale);
        SkinRenderHelper.renderGuideFace(ForgeDirection.NORTH, x, y, z, width, height, scale);
        SkinRenderHelper.renderGuideFace(ForgeDirection.SOUTH, x, y, z + (double)depth, width, height, scale);
    }

    private static void renderGuideFace(ForgeDirection dir, double x, double y, double z, double sizeX, double sizeY, float scale) {
        RenderManager renderManager = RenderManager.field_78727_a;
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        ModRenderHelper.enableAlphaBlend();
        float scale1 = 0.999f;
        GL11.glTranslated((double)(x * (double)scale), (double)(y * (double)scale), (double)(z * (double)scale));
        switch (dir) {
            case EAST: {
                GL11.glRotated((double)-90.0, (double)0.0, (double)1.0, (double)0.0);
                break;
            }
            case WEST: {
                GL11.glRotated((double)-90.0, (double)0.0, (double)1.0, (double)0.0);
                break;
            }
            case UP: {
                GL11.glRotated((double)90.0, (double)1.0, (double)0.0, (double)0.0);
                break;
            }
            case DOWN: {
                GL11.glRotated((double)90.0, (double)1.0, (double)0.0, (double)0.0);
                break;
            }
        }
        tessellator.func_78380_c(0xF000F0);
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)240.0f, (float)240.0f);
        tessellator.func_78382_b();
        tessellator.func_78374_a(0.0, 0.0, 0.0, 0.0, 0.0);
        tessellator.func_78374_a(0.0, sizeY * (double)scale, 0.0, sizeY, 0.0);
        tessellator.func_78374_a(sizeX * (double)scale, sizeY * (double)scale, 0.0, sizeY, sizeX);
        tessellator.func_78374_a(sizeX * (double)scale, 0.0, 0.0, 0.0, sizeX);
        tessellator.func_78381_a();
        ModRenderHelper.disableAlphaBlend();
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
    }
}

