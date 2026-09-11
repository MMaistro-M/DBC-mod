/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public final class ModRenderHelper {
    private static float lightX;
    private static float lightY;

    public static void disableLighting() {
        lightX = OpenGlHelper.lastBrightnessX;
        lightY = OpenGlHelper.lastBrightnessY;
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)240.0f, (float)240.0f);
    }

    public static void enableLighting() {
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)lightX, (float)lightY);
    }

    public static void setLightingForBlock(World world, int x, int y, int z) {
        int i = world.func_72802_i(x, y, z, 0);
        int j = i % 65536;
        int k = i / 65536;
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)j / 1.0f), (float)((float)k / 1.0f));
    }

    public static void enableAlphaBlend() {
        ModRenderHelper.enableAlphaBlend(770, 771);
    }

    public static void enableAlphaBlend(int sfactor, int dfactor) {
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)sfactor, (int)dfactor);
    }

    public static void disableAlphaBlend() {
        GL11.glDisable((int)3042);
    }

    public static void renderItemStack(ItemStack stack) {
        IIcon icon = stack.func_77973_b().getIcon(stack, 0);
        ItemRenderer.func_78439_a((Tessellator)Tessellator.field_78398_a, (float)icon.func_94212_f(), (float)icon.func_94206_g(), (float)icon.func_94209_e(), (float)icon.func_94210_h(), (int)icon.func_94211_a(), (int)icon.func_94216_b(), (float)0.0625f);
    }

    public static void enableScissorScaled(int x, int y, int width, int height) {
        Minecraft mc = Minecraft.func_71410_x();
        ScaledResolution sr = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
        double scaledWidth = (double)mc.field_71443_c / sr.func_78327_c();
        double scaledHeight = (double)mc.field_71440_d / sr.func_78324_d();
        ModRenderHelper.enableScissor(MathHelper.func_76128_c((double)((double)x * scaledWidth)), mc.field_71440_d - MathHelper.func_76128_c((double)((double)(y + height) * scaledHeight)), MathHelper.func_76128_c((double)((double)width * scaledWidth)), MathHelper.func_76128_c((double)((double)height * scaledHeight)));
    }

    public static void enableScissor(int x, int y, int width, int height) {
        GL11.glEnable((int)3089);
        GL11.glScissor((int)x, (int)y, (int)width, (int)height);
    }

    public static void disableScissor() {
        GL11.glDisable((int)3089);
    }
}

