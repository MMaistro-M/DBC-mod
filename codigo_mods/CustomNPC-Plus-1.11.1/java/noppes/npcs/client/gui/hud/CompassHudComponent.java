/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.hud;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.gui.hud.HudComponent;
import noppes.npcs.config.ConfigClient;
import org.lwjgl.opengl.GL11;

public class CompassHudComponent
extends HudComponent {
    private final Minecraft mc;
    private final List<MarkTargetEntry> markTargets = new ArrayList<MarkTargetEntry>();
    private final int BAR_HEIGHT = 20;
    private final int BASE_ICON_SIZE = 8;
    private final int MAX_ICON_SIZE = 16;
    private final int SCALE_DISTANCE_MIN = 10;
    private final int SCALE_DISTANCE_MAX = 40;
    private static final int HANDLE_SIZE = 10;
    private static final int WIDTH_BAR_SIZE = 6;
    private final int SCAN_RANGE = 128;
    public boolean resizingWidth = false;

    public CompassHudComponent(Minecraft minecraft) {
        this.mc = minecraft;
        this.overlayWidth = 200;
        this.overlayHeight = 20;
        this.load();
    }

    @Override
    public void loadData(NBTTagCompound compound) {
        this.markTargets.clear();
        NBTTagList list = compound.func_150295_c("MarkTargets", 10);
        for (int i = 0; i < list.func_74745_c(); ++i) {
            NBTTagCompound entry = list.func_150305_b(i);
            this.markTargets.add(new MarkTargetEntry(entry.func_74762_e("x"), entry.func_74762_e("z"), entry.func_74762_e("type"), entry.func_74762_e("color")));
        }
        this.hasData = !this.markTargets.isEmpty();
    }

    @Override
    public void load() {
        this.enabled = ConfigClient.CompassEnabled;
        this.posX = ConfigClient.CompassOverlayX;
        this.posY = ConfigClient.CompassOverlayY;
        this.scale = ConfigClient.CompassOverlayScale;
        this.overlayWidth = ConfigClient.CompassOverlayWidth;
    }

    @Override
    public void save() {
        ConfigClient.CompassEnabled = this.enabled;
        ConfigClient.CompassEnabledProperty.set(ConfigClient.CompassEnabled);
        ConfigClient.CompassOverlayX = this.posX;
        ConfigClient.CompassOverlayXProperty.set((double)ConfigClient.CompassOverlayX);
        ConfigClient.CompassOverlayY = this.posY;
        ConfigClient.CompassOverlayYProperty.set((double)ConfigClient.CompassOverlayY);
        ConfigClient.CompassOverlayScale = this.scale;
        ConfigClient.CompassOverlayScaleProperty.set(ConfigClient.CompassOverlayScale);
        ConfigClient.CompassOverlayWidth = this.overlayWidth;
        ConfigClient.CompassOverlayWidthProperty.set(ConfigClient.CompassOverlayWidth);
        if (ConfigClient.config.hasChanged()) {
            ConfigClient.config.save();
        }
    }

    @Override
    public void renderOnScreen(float partialTicks) {
        if (!this.hasData || this.isEditting) {
            return;
        }
        ScaledResolution res = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
        int actualX = (int)(this.posX / 100.0f * (float)res.func_78326_a());
        int actualY = (int)(this.posY / 100.0f * (float)res.func_78328_b());
        float effectiveScale = this.getEffectiveScale(res);
        double interpPosX = this.mc.field_71439_g.field_70142_S + (this.mc.field_71439_g.field_70165_t - this.mc.field_71439_g.field_70142_S) * (double)partialTicks;
        double interpPosY = this.mc.field_71439_g.field_70137_T + (this.mc.field_71439_g.field_70163_u - this.mc.field_71439_g.field_70137_T) * (double)partialTicks;
        double interpPosZ = this.mc.field_71439_g.field_70136_U + (this.mc.field_71439_g.field_70161_v - this.mc.field_71439_g.field_70136_U) * (double)partialTicks;
        float interpYaw = this.mc.field_71439_g.field_70126_B + (this.mc.field_71439_g.field_70177_z - this.mc.field_71439_g.field_70126_B) * partialTicks;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)actualX, (float)actualY, (float)0.0f);
        GL11.glScalef((float)effectiveScale, (float)effectiveScale, (float)effectiveScale);
        this.drawRect(0, 0, this.overlayWidth, 20, Integer.MIN_VALUE);
        this.markTargets.sort(Comparator.comparingDouble(mark -> (interpPosX - mark.x) * (interpPosX - mark.x) + (interpPosZ - mark.z) * (interpPosZ - mark.z)).reversed());
        for (MarkTargetEntry mark2 : this.markTargets) {
            this.renderMarkIcon(mark2, interpPosX, interpPosY, interpPosZ, interpYaw);
        }
        GL11.glPopMatrix();
    }

    private void renderMarkIcon(MarkTargetEntry mark, double playerX, double playerY, double playerZ, float playerYaw) {
        if (this.mc.field_71439_g == null) {
            return;
        }
        Float iconPos = this.calculateIconPosition(mark.x, mark.z, playerX, playerZ, playerYaw);
        if (iconPos == null) {
            return;
        }
        int barWidth = this.overlayWidth;
        float distance = (float)Math.sqrt(Math.pow(mark.x - playerX, 2.0) + Math.pow(mark.z - playerZ, 2.0));
        int iconSize = this.calculateIconSize(distance);
        int iconX = MathHelper.func_76125_a((int)((int)iconPos.floatValue()), (int)(iconSize / 2), (int)(barWidth - iconSize / 2));
        int iconY = (20 - iconSize) / 2;
        ResourceLocation texture = this.getTextureForMark(mark.type);
        if (texture != null) {
            this.renderTextureIcon(iconX - iconSize / 2, iconY, iconSize, texture, mark.color);
        }
    }

    private ResourceLocation getTextureForMark(int type) {
        switch (type) {
            case 2: {
                return new ResourceLocation("customnpcs", "textures/marks/exclamation.png");
            }
            case 1: {
                return new ResourceLocation("customnpcs", "textures/marks/question.png");
            }
            case 3: {
                return new ResourceLocation("customnpcs", "textures/marks/pointer.png");
            }
            case 5: {
                return new ResourceLocation("customnpcs", "textures/marks/cross.png");
            }
            case 4: {
                return new ResourceLocation("customnpcs", "textures/marks/skull.png");
            }
            case 6: {
                return new ResourceLocation("customnpcs", "textures/marks/star.png");
            }
        }
        return null;
    }

    private void renderTextureIcon(int x, int y, int size, ResourceLocation texture, int color) {
        float red = (float)(color >> 16 & 0xFF) / 255.0f;
        float green = (float)(color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(color & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glColor4f((float)red, (float)green, (float)blue, (float)1.0f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        this.mc.func_110434_K().func_110577_a(texture);
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78374_a((double)x, (double)(y + size), 0.0, 0.0, 1.0);
        tessellator.func_78374_a((double)(x + size), (double)(y + size), 0.0, 1.0, 1.0);
        tessellator.func_78374_a((double)(x + size), (double)y, 0.0, 1.0, 0.0);
        tessellator.func_78374_a((double)x, (double)y, 0.0, 0.0, 0.0);
        tessellator.func_78381_a();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    private Float calculateIconPosition(double targetX, double targetZ, double playerX, double playerZ, float playerYaw) {
        double relativeAngle;
        double dx = targetX - playerX;
        double dz = targetZ - playerZ;
        double angleToTarget = Math.toDegrees(Math.atan2(dz, dx));
        double adjustedPlayerYaw = playerYaw + 90.0f;
        for (relativeAngle = angleToTarget - adjustedPlayerYaw; relativeAngle < -180.0; relativeAngle += 360.0) {
        }
        while (relativeAngle > 180.0) {
            relativeAngle -= 360.0;
        }
        if (Math.abs(relativeAngle) > 90.0) {
            return null;
        }
        return Float.valueOf((float)((double)this.overlayWidth / 2.0 + relativeAngle / 90.0 * ((double)this.overlayWidth / 2.0)));
    }

    private int calculateIconSize(float distance) {
        if (distance <= 10.0f) {
            return 16;
        }
        if (distance >= 40.0f) {
            return 8;
        }
        float t = (distance - 10.0f) / 30.0f;
        return (int)(16.0f - 8.0f * t);
    }

    @Override
    public void renderEditing() {
        this.isEditting = true;
        ScaledResolution res = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
        int actualX = (int)(this.posX / 100.0f * (float)res.func_78326_a());
        int actualY = (int)(this.posY / 100.0f * (float)res.func_78328_b());
        float effectiveScale = this.getEffectiveScale(res);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)actualX, (float)actualY, (float)0.0f);
        GL11.glScalef((float)effectiveScale, (float)effectiveScale, (float)effectiveScale);
        this.drawRect(0, 0, this.overlayWidth, this.overlayHeight, Integer.MIN_VALUE);
        this.drawRectOutline(0, 0, this.overlayWidth, this.overlayHeight, -16711936);
        this.drawRect(this.overlayWidth - 10, this.overlayHeight - 10, this.overlayWidth, this.overlayHeight, -3355444);
        int margin = 5;
        int barX = this.overlayWidth + margin;
        int barY = (this.overlayHeight - 20) / 2;
        this.drawRect(barX, barY, barX + 6, barY + 20, -7829368);
        this.renderDemoIcon((float)this.overlayWidth * 0.25f, 2, -16711936);
        this.renderDemoIcon((float)this.overlayWidth * 0.75f, 1, -65536);
        GL11.glPopMatrix();
    }

    private void renderDemoIcon(float position, int type, int color) {
        float pulse = (float)(System.currentTimeMillis() % 1000L) / 1000.0f;
        int size = (int)(8.0f + 8.0f * pulse);
        ResourceLocation texture = this.getTextureForMark(type);
        if (texture != null) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(position - (float)(size / 2)), (float)((20 - size) / 2), (float)0.0f);
            this.renderTextureIcon(0, 0, size, texture, color);
            GL11.glPopMatrix();
        }
    }

    public void updateMarkTargets(List<MarkTargetEntry> newMarks) {
        this.markTargets.clear();
        for (MarkTargetEntry mark : newMarks) {
            this.markTargets.add(new MarkTargetEntry(mark.x, mark.z, mark.type, mark.color));
        }
        this.hasData = !this.markTargets.isEmpty();
    }

    private void drawRect(int left, int top, int right, int bottom, int color) {
        Gui.func_73734_a((int)left, (int)top, (int)right, (int)bottom, (int)color);
    }

    private void drawRectOutline(int left, int top, int right, int bottom, int color) {
        this.drawHorizontalLine(left, right, top, color);
        this.drawHorizontalLine(left, right, bottom - 1, color);
        Gui.func_73734_a((int)left, (int)top, (int)(left + 1), (int)bottom, (int)color);
        Gui.func_73734_a((int)(right - 1), (int)top, (int)right, (int)bottom, (int)color);
    }

    private void drawHorizontalLine(int left, int right, int y, int color) {
        Gui.func_73734_a((int)left, (int)y, (int)right, (int)(y + 1), (int)color);
    }

    public static class MarkTargetEntry {
        public double x;
        public double z;
        public int type;
        public int color;

        public MarkTargetEntry(double x, double z, int type, int color) {
            this.x = x;
            this.z = z;
            this.type = type;
            this.color = color;
        }
    }
}

