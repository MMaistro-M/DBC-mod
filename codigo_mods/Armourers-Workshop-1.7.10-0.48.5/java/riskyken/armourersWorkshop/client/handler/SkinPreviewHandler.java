/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.config.GuiUtils
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Post
 *  net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.player.ItemTooltipEvent
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.client.handler;

import cpw.mods.fml.client.config.GuiUtils;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.client.lib.LibGuiResources;
import riskyken.armourersWorkshop.client.render.ModRenderHelper;
import riskyken.armourersWorkshop.client.render.SkinItemRenderHelper;
import riskyken.armourersWorkshop.common.config.ConfigHandlerClient;
import riskyken.armourersWorkshop.common.skin.data.SkinPointer;
import riskyken.armourersWorkshop.utils.SkinNBTHelper;

@SideOnly(value=Side.CLIENT)
public final class SkinPreviewHandler {
    private final ResourceLocation TEXTURE = new ResourceLocation(LibGuiResources.SKIN_PREVIEW);
    private SkinPointer lastSkinPointer;
    private List<String> lastList;

    public SkinPreviewHandler() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onDrawScreenPre(GuiScreenEvent.DrawScreenEvent.Post event) {
        if (!ConfigHandlerClient.skinPreEnabled) {
            return;
        }
        if (!ConfigHandlerClient.skinPreLocFollowMouse) {
            return;
        }
        SkinPointer skinPointer = this.lastSkinPointer;
        List<String> list = this.lastList;
        this.lastSkinPointer = null;
        this.lastList = null;
        if (skinPointer != null & list != null) {
            Minecraft mc = Minecraft.func_71410_x();
            float skinPreSize = ConfigHandlerClient.skinPreSize;
            int[] toolTipSize = this.getTooltipSize(list, mc.field_71462_r.field_146294_l, mc.field_71462_r.field_146295_m, event.mouseX + 8, event.mouseY, mc.field_71466_p);
            int x = (int)((float)toolTipSize[0] - skinPreSize - 28.0f);
            int y = toolTipSize[1] - 4;
            if (this.tooltipOnLeft(list, mc.field_71462_r.field_146294_l, mc.field_71462_r.field_146295_m, event.mouseX + 8, event.mouseY, mc.field_71466_p)) {
                x = toolTipSize[0] + toolTipSize[2] + 15;
            }
            if (y < 0) {
                y = 0;
            }
            if ((float)y + skinPreSize > (float)mc.field_71462_r.field_146295_m) {
                y = mc.field_71462_r.field_146295_m - (int)skinPreSize;
            }
            this.drawSkinBox(mc, x, y, skinPreSize, skinPointer);
        }
    }

    @SubscribeEvent
    public void onDrawScreenPost(GuiScreenEvent.DrawScreenEvent.Pre event) {
        if (!ConfigHandlerClient.skinPreEnabled) {
            return;
        }
        if (ConfigHandlerClient.skinPreLocFollowMouse) {
            return;
        }
        SkinPointer skinPointer = this.lastSkinPointer;
        List<String> list = this.lastList;
        this.lastSkinPointer = null;
        this.lastList = null;
        if (skinPointer != null & list != null) {
            Minecraft mc = Minecraft.func_71410_x();
            float skinPreSize = ConfigHandlerClient.skinPreSize;
            float skinPreLocHorizontal = ConfigHandlerClient.skinPreLocHorizontal;
            float skinPreLocVertical = ConfigHandlerClient.skinPreLocVertical;
            ScaledResolution sr = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
            double widthClip = sr.func_78327_c() - (double)skinPreSize;
            double heightClip = sr.func_78324_d() - (double)skinPreSize;
            int x = MathHelper.func_76143_f((double)(widthClip * (double)skinPreLocHorizontal));
            int y = MathHelper.func_76143_f((double)(heightClip * (double)skinPreLocVertical));
            this.drawSkinBox(mc, x, y, skinPreSize, skinPointer);
            skinPointer = null;
        }
    }

    private void drawSkinBox(Minecraft mc, int x, int y, float skinPreSize, SkinPointer skinPointer) {
        boolean skinPreDrawBackground = ConfigHandlerClient.skinPreDrawBackground;
        if (skinPreDrawBackground) {
            RenderHelper.func_74518_a();
            mc.field_71446_o.func_110577_a(this.TEXTURE);
            ModRenderHelper.enableAlphaBlend();
            GuiUtils.drawContinuousTexturedBox((int)x, (int)y, (int)0, (int)0, (int)((int)skinPreSize), (int)((int)skinPreSize), (int)62, (int)62, (int)4, (float)400.0f);
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPushMatrix();
        GL11.glPushAttrib((int)1048575);
        GL11.glEnable((int)2977);
        RenderHelper.func_74520_c();
        GL11.glTranslatef((float)-10.0f, (float)-5.0f, (float)600.0f);
        GL11.glTranslatef((float)(skinPreSize / 2.0f + (float)x), (float)(skinPreSize / 2.0f + (float)y), (float)0.0f);
        GL11.glScalef((float)10.0f, (float)10.0f, (float)10.0f);
        GL11.glTranslatef((float)1.0f, (float)0.5f, (float)1.0f);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)1.0f);
        GL11.glRotatef((float)210.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        float rotation = (float)((double)System.currentTimeMillis() / 10.0 % 360.0);
        GL11.glRotatef((float)rotation, (float)0.0f, (float)1.0f, (float)0.0f);
        SkinItemRenderHelper.renderSkinAsItem(skinPointer, true, false, (int)skinPreSize, (int)skinPreSize);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }

    private int[] getTooltipSize(List<String> list, int width, int height, int mouseX, int mouseY, FontRenderer font) {
        if (!list.isEmpty()) {
            int strWidth = 0;
            for (String s : list) {
                int l = font.func_78256_a(s);
                if (l <= strWidth) continue;
                strWidth = l;
            }
            int xPos = mouseX + 12;
            int yPos = mouseY - 12;
            int strHeight = 8;
            if (list.size() > 1) {
                strHeight += 2 + (list.size() - 1) * 10;
            }
            if (xPos + strWidth > width) {
                xPos -= 28 + strWidth;
            }
            if (yPos + strHeight + 6 > height) {
                yPos = height - strHeight - 6;
            }
            if (xPos < 12) {
                xPos = 12;
            }
            if (yPos < 8) {
                yPos = 8;
            }
            return new int[]{xPos, yPos, strWidth, strHeight};
        }
        return null;
    }

    private boolean tooltipOnLeft(List<String> list, int width, int height, int mouseX, int mouseY, FontRenderer font) {
        if (!list.isEmpty()) {
            int strWidth = 0;
            for (String s : list) {
                int l = font.func_78256_a(s);
                if (l <= strWidth) continue;
                strWidth = l;
            }
            int xPos = mouseX + 12;
            int yPos = mouseY - 12;
            int strHeight = 8;
            if (list.size() > 1) {
                strHeight += 2 + (list.size() - 1) * 10;
            }
            if (xPos + strWidth > width) {
                return true;
            }
        }
        return false;
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onItemTooltipEvent(ItemTooltipEvent event) {
        if (ConfigHandlerClient.skinPreEnabled) {
            this.lastSkinPointer = SkinNBTHelper.getSkinPointerFromStack(event.itemStack);
            this.lastList = event.toolTip;
        }
    }
}

