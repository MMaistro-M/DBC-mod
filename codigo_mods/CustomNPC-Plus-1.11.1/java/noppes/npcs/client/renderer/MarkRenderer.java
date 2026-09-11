/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.model.util.Model2DRenderer;
import noppes.npcs.controllers.data.MarkData;
import org.lwjgl.opengl.GL11;

public class MarkRenderer {
    public static ResourceLocation markExclamation = new ResourceLocation("customnpcs", "textures/marks/exclamation.png");
    public static ResourceLocation markQuestion = new ResourceLocation("customnpcs", "textures/marks/question.png");
    public static ResourceLocation markPointer = new ResourceLocation("customnpcs", "textures/marks/pointer.png");
    public static ResourceLocation markCross = new ResourceLocation("customnpcs", "textures/marks/cross.png");
    public static ResourceLocation markSkull = new ResourceLocation("customnpcs", "textures/marks/skull.png");
    public static ResourceLocation markStar = new ResourceLocation("customnpcs", "textures/marks/star.png");
    public static int displayList = -1;

    public static void render(EntityLivingBase entity, double x, double y, double z, MarkData.Mark mark) {
        GL11.glPushMatrix();
        int color = mark.color;
        float red = (float)(color >> 16 & 0xFF) / 255.0f;
        float blue = (float)(color >> 8 & 0xFF) / 255.0f;
        float green = (float)(color & 0xFF) / 255.0f;
        GL11.glColor4f((float)red, (float)blue, (float)green, (float)1.0f);
        GL11.glTranslatef((float)((float)x), (float)((float)(y + (double)entity.field_70131_O + 0.6)), (float)((float)z));
        GL11.glRotatef((float)(-entity.field_70759_as), (float)0.0f, (float)1.0f, (float)0.0f);
        if (mark.type == 2) {
            Minecraft.func_71410_x().func_110434_K().func_110577_a(markExclamation);
        } else if (mark.type == 1) {
            Minecraft.func_71410_x().func_110434_K().func_110577_a(markQuestion);
        } else if (mark.type == 3) {
            Minecraft.func_71410_x().func_110434_K().func_110577_a(markPointer);
        } else if (mark.type == 5) {
            Minecraft.func_71410_x().func_110434_K().func_110577_a(markCross);
        } else if (mark.type == 4) {
            Minecraft.func_71410_x().func_110434_K().func_110577_a(markSkull);
        } else if (mark.type == 6) {
            Minecraft.func_71410_x().func_110434_K().func_110577_a(markStar);
        }
        if (displayList >= 0) {
            GL11.glCallList((int)displayList);
        } else {
            displayList = GLAllocation.func_74526_a((int)1);
            GL11.glNewList((int)displayList, (int)4864);
            GL11.glTranslatef((float)-0.5f, (float)0.0f, (float)0.0f);
            Model2DRenderer.renderItemIn2D(0.0f, 0.0f, 1.0f, 1.0f, 32, 32, 0.0625f);
            GL11.glEndList();
        }
        GL11.glPopMatrix();
    }
}

