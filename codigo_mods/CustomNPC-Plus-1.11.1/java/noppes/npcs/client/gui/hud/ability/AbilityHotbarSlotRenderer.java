/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.entity.player.EntityPlayer
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.hud.ability;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.data.ChainedAbility;
import kamkeel.npcs.controllers.data.ability.data.IAbilityAction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import noppes.npcs.client.gui.hud.ability.AbilityIcon;
import noppes.npcs.config.ConfigClient;
import noppes.npcs.controllers.data.PlayerData;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class AbilityHotbarSlotRenderer
extends Gui {
    private static final int CIRCLE_SEGMENTS = 32;
    private static final long NAME_FADE_DURATION = 180L;
    public int index;
    public String abilityKey = null;
    public Ability ability = null;
    public IAbilityAction action = null;
    private AbilityIcon icon = null;
    public boolean isSelected = false;
    public float nameAlpha = 0.0f;
    private boolean nameFadingIn = false;
    private long nameFadeStartTime = 0L;

    public AbilityHotbarSlotRenderer(int index) {
        this.index = index;
    }

    public void setAbility(String key, Ability ability, IAbilityAction action) {
        this.abilityKey = key;
        this.ability = ability;
        this.action = action;
        this.icon = ability != null ? AbilityIcon.fromAbility(ability) : (action instanceof ChainedAbility ? AbilityIcon.fromChainedAbility((ChainedAbility)action) : null);
    }

    public void drawCarousel(Minecraft mc, ScaledResolution sr, float cooldownProgress, int cx, int cy, int size, boolean isCenter, float slotAlpha, boolean showText, boolean activePhase) {
        float outlineB;
        float outlineG;
        float outlineR;
        float fillB;
        float fillG;
        float fillR;
        float nameAlpha = this.nameAlpha;
        boolean isHorizontal = ConfigClient.AbilityHotbarHorizontal;
        if (size <= 0 || slotAlpha <= 0.0f) {
            return;
        }
        float radius = (float)size / 2.0f;
        float baseAlpha = isCenter ? 0.9f : 0.6f;
        float alpha = baseAlpha * slotAlpha;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)cx, (float)cy, (float)0.0f);
        boolean altTexture = ConfigClient.AbilityHotbarAltTexture;
        if (activePhase) {
            if (isCenter) {
                fillR = 0.95f;
                fillG = 0.26f;
                fillB = 0.22f;
                outlineR = 1.0f;
                outlineG = 0.52f;
                outlineB = 0.28f;
            } else {
                fillR = 0.36f;
                fillG = 0.14f;
                fillB = 0.14f;
                outlineR = 0.65f;
                outlineG = 0.22f;
                outlineB = 0.2f;
            }
        } else if (isCenter) {
            fillR = 0.8f;
            fillG = 0.8f;
            fillB = 0.2f;
            outlineR = 0.8f;
            outlineG = 0.8f;
            outlineB = 0.2f;
        } else {
            fillR = 0.4f;
            fillG = 0.4f;
            fillB = 0.4f;
            outlineR = 1.0f;
            outlineG = 1.0f;
            outlineB = 1.0f;
        }
        if (altTexture) {
            float baseR = activePhase ? 0.3f : 0.6f;
            float baseG = activePhase ? 0.12f : 0.6f;
            float baseB = activePhase ? 0.12f : 0.6f;
            this.drawRoundedRect(-radius, -radius, radius, radius, baseR, baseG, baseB, 0.75f * alpha, false);
            this.drawRoundedRect(-radius, -radius, radius, radius, fillR, fillG, fillB, alpha * 0.8f, true);
        } else {
            this.drawCircle(radius, outlineR, outlineG, outlineB, 0.66f * alpha, false);
            this.drawCircle(radius, fillR, fillG, fillB, alpha * 0.8f, true);
        }
        int toggleState = this.getToggleState();
        if (toggleState > 0) {
            float glowAlpha = 0.45f * slotAlpha;
            if (altTexture) {
                this.drawRoundedRect(-radius, -radius, radius, radius, 0.2f, 0.85f, 0.3f, glowAlpha, true);
            } else {
                this.drawCircle(radius, 0.2f, 0.85f, 0.3f, glowAlpha, true);
            }
        }
        if (this.icon != null) {
            GL11.glPushMatrix();
            float targetSize = (float)size * 0.55f;
            float iconNaturalSize = Math.max(this.icon.width, this.icon.height);
            if (iconNaturalSize <= 0.0f) {
                iconNaturalSize = 16.0f;
            }
            float iconScale = targetSize / iconNaturalSize;
            GL11.glScalef((float)iconScale, (float)iconScale, (float)1.0f);
            this.icon.draw(toggleState, slotAlpha);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glPopMatrix();
        }
        if (cooldownProgress > 0.0f && this.abilityKey != null) {
            this.drawCooldownOverlay(radius, cooldownProgress, altTexture, slotAlpha);
        }
        GL11.glPopMatrix();
        int textPos = ConfigClient.AbilityHotbarTextPosition;
        if (showText && isCenter && this.action != null && nameAlpha > 0.0f) {
            FontRenderer fr = mc.field_71466_p;
            String name = this.getAbilityName();
            if (name != null && !name.isEmpty()) {
                int nameY;
                int nameX;
                int a = (int)(nameAlpha * 255.0f) & 0xFF;
                int nameColor = a << 24 | this.getNameColor() & 0xFFFFFF;
                if (isHorizontal) {
                    nameX = cx - fr.func_78256_a(name) / 2;
                    nameY = textPos == 1 ? cy - (int)radius - fr.field_78288_b - 2 : cy + (int)radius + 2;
                } else {
                    nameY = cy - fr.field_78288_b / 2;
                    nameX = textPos == 1 ? cx - (int)radius - fr.func_78256_a(name) - 4 : cx + (int)radius + 4;
                }
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)nameAlpha);
                fr.func_78261_a(name, nameX, nameY, nameColor);
                GL11.glDisable((int)3042);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            }
        }
    }

    private void drawRoundedRect(float x1, float y1, float x2, float y2, float r, float g, float b, float a, boolean outline) {
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2884);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)r, (float)g, (float)b, (float)a);
        if (outline) {
            GL11.glLineWidth((float)2.0f);
            GL11.glBegin((int)2);
        } else {
            GL11.glBegin((int)7);
        }
        GL11.glVertex2f((float)x1, (float)y1);
        GL11.glVertex2f((float)x2, (float)y1);
        GL11.glVertex2f((float)x2, (float)y2);
        GL11.glVertex2f((float)x1, (float)y2);
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2884);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void drawCircle(float radius, float r, float g, float b, float a, boolean outline) {
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2884);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)r, (float)g, (float)b, (float)a);
        if (outline) {
            GL11.glLineWidth((float)1.5f);
            GL11.glBegin((int)2);
        } else {
            GL11.glBegin((int)6);
            GL11.glVertex2f((float)0.0f, (float)0.0f);
        }
        for (int i = 0; i <= 32; ++i) {
            double angle = Math.PI * 2 * (double)i / 32.0;
            GL11.glVertex2f((float)((float)(Math.cos(angle) * (double)radius)), (float)((float)(Math.sin(angle) * (double)radius)));
        }
        GL11.glEnd();
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2884);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void drawCooldownOverlay(float radius, float progress, boolean altTexture, float slotAlpha) {
        float cb;
        float cg;
        float cr;
        float t;
        if ((progress = Math.min(1.0f, Math.max(0.0f, progress))) <= 0.0f) {
            return;
        }
        if (progress > 0.5f) {
            t = (progress - 0.5f) * 2.0f;
            cr = 0.8f;
            cg = 0.15f + 0.25f * (1.0f - t);
            cb = 0.1f;
        } else if (progress > 0.2f) {
            t = (progress - 0.2f) / 0.3f;
            cr = 0.8f * t + 0.3f * (1.0f - t);
            cg = 0.4f * t + 0.7f * (1.0f - t);
            cb = 0.1f;
        } else {
            t = progress / 0.2f;
            cr = 0.3f * t + 0.1f * (1.0f - t);
            cg = 0.7f * t + 0.7f * (1.0f - t);
            cb = 0.1f + 0.1f * (1.0f - t);
        }
        float overlayAlpha = 0.5f * slotAlpha * Math.min(1.0f, progress * 3.0f);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2884);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)cr, (float)cg, (float)cb, (float)overlayAlpha);
        if (altTexture) {
            GL11.glBegin((int)7);
            GL11.glVertex2f((float)(-radius), (float)(-radius));
            GL11.glVertex2f((float)radius, (float)(-radius));
            GL11.glVertex2f((float)radius, (float)radius);
            GL11.glVertex2f((float)(-radius), (float)radius);
            GL11.glEnd();
        } else {
            GL11.glBegin((int)6);
            GL11.glVertex2f((float)0.0f, (float)0.0f);
            for (int i = 0; i <= 32; ++i) {
                double angle = Math.PI * 2 * (double)i / 32.0;
                GL11.glVertex2f((float)((float)(Math.cos(angle) * (double)radius)), (float)((float)(Math.sin(angle) * (double)radius)));
            }
            GL11.glEnd();
        }
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2884);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private String getAbilityName() {
        if (this.ability != null) {
            String label;
            int state = this.getToggleState();
            if (state > 0 && (label = this.ability.getToggleStateLabel(state)) != null) {
                return label;
            }
            return this.ability.getDisplayName();
        }
        if (this.action instanceof ChainedAbility) {
            return ((ChainedAbility)this.action).getDisplayName();
        }
        return this.action != null ? this.action.getName() : "";
    }

    private int getToggleState() {
        if (this.ability == null || !this.ability.isToggleable()) {
            return 0;
        }
        Minecraft mc = Minecraft.func_71410_x();
        if (mc.field_71439_g == null) {
            return 0;
        }
        PlayerData playerData = PlayerData.get((EntityPlayer)mc.field_71439_g);
        if (playerData == null || playerData.abilityData == null) {
            return 0;
        }
        return playerData.abilityData.getToggleState(this.abilityKey);
    }

    private int getNameColor() {
        if (this.ability != null && this.ability.isToggleable()) {
            return this.getToggleState() > 0 ? -11141291 : -43691;
        }
        return -1;
    }

    public void setSelectedState(boolean newSelectState) {
        if (!this.isSelected && newSelectState) {
            this.nameFadingIn = true;
            this.nameFadeStartTime = Minecraft.func_71386_F();
        }
        if (this.isSelected && !newSelectState) {
            this.nameFadingIn = false;
            this.nameFadeStartTime = Minecraft.func_71386_F();
        }
        this.isSelected = newSelectState;
    }

    public void updateNameFade() {
        long now = Minecraft.func_71386_F();
        float t = (float)(now - this.nameFadeStartTime) / 180.0f;
        t = Math.min(1.0f, Math.max(0.0f, t));
        this.nameAlpha = this.nameFadingIn ? t : 1.0f - t;
    }
}

