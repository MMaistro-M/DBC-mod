/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.hud.ability;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import kamkeel.npcs.controllers.AbilityController;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.data.IAbilityAction;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.packets.player.ability.AbilityHotbarSelectPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import noppes.npcs.client.ClientAbilityState;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.KeyPressHandler;
import noppes.npcs.client.gui.hud.HudComponent;
import noppes.npcs.client.gui.hud.ability.AbilityHotbarSlotRenderer;
import noppes.npcs.config.ConfigClient;
import noppes.npcs.controllers.data.AbilityHotbarData;
import noppes.npcs.controllers.data.PlayerAbilityHotbarData;
import noppes.npcs.controllers.data.PlayerData;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class AbilityHotbarComponent
extends HudComponent {
    public static final int TOTAL_SLOTS = 12;
    private static final int BASE_SLOT_SIZE = 24;
    private static final int BASE_SPACING = 28;
    private static final int EDIT_HANDLE_SIZE = 10;
    private AbilityHotbarSlotRenderer[] slots = new AbilityHotbarSlotRenderer[12];
    private int[] activeIndices = new int[0];
    private int activeCenter = 0;
    private float scrollOffset = 0.0f;
    private long scrollStartTime = 0L;
    private static final long SCROLL_DURATION = 150L;
    private long lastUpdateTime = 0L;
    private static final long UPDATE_INTERVAL = 500L;
    private String pendingSelectKey = null;
    private long pendingSelectTime = 0L;
    private static final long SELECT_DEBOUNCE = 300L;
    private long lastUserCycleTime = 0L;
    private static final long USER_CYCLE_GRACE = 1000L;
    private float fadeAlpha = 0.0f;
    private long lastFadeTime = 0L;
    private static final float FADE_SPEED = 3.0f;
    private Minecraft mc;

    public AbilityHotbarComponent(Minecraft mc) {
        this.mc = mc;
        this.updateOverlayDimensions();
        for (int i = 0; i < 12; ++i) {
            this.slots[i] = new AbilityHotbarSlotRenderer(i);
        }
        this.load();
    }

    private int getEffectiveVisibleSlots() {
        int filledCount;
        int configMax = ConfigClient.AbilityHotbarVisibleSlots;
        if (configMax != 3 && configMax != 5 && configMax != 7) {
            configMax = 5;
        }
        if ((filledCount = this.activeIndices.length - 1) < 0) {
            filledCount = 0;
        }
        if (filledCount < 5) {
            return Math.min(configMax, 3);
        }
        if (filledCount < 7) {
            return Math.min(configMax, 5);
        }
        return configMax;
    }

    private void updateOverlayDimensions() {
        boolean isHorizontal = ConfigClient.AbilityHotbarHorizontal;
        int visibleSlots = this.getEffectiveVisibleSlots();
        int span = 28 * (visibleSlots - 1) + 24;
        int thick = 28;
        if (isHorizontal) {
            this.overlayWidth = span;
            this.overlayHeight = thick;
        } else {
            this.overlayWidth = thick;
            this.overlayHeight = span;
        }
    }

    @Override
    public void loadData(NBTTagCompound compound) {
    }

    @Override
    public void load() {
        this.posX = ConfigClient.AbilityHotbarX;
        this.posY = ConfigClient.AbilityHotbarY;
        this.scale = ConfigClient.AbilityHotbarScale;
        this.enabled = ConfigClient.AbilityHotbarEnabled;
    }

    @Override
    public void save() {
        ConfigClient.AbilityHotbarX = this.posX;
        ConfigClient.AbilityHotbarXProperty.set((double)this.posX);
        ConfigClient.AbilityHotbarY = this.posY;
        ConfigClient.AbilityHotbarYProperty.set((double)this.posY);
        ConfigClient.AbilityHotbarScale = this.scale;
        ConfigClient.AbilityHotbarScaleProperty.set(this.scale);
        ConfigClient.AbilityHotbarEnabled = this.enabled;
        ConfigClient.AbilityHotbarEnabledProperty.set(this.enabled);
        ConfigClient.AbilityHotbarHorizontalProperty.set(ConfigClient.AbilityHotbarHorizontal);
        ConfigClient.AbilityHotbarAltTextureProperty.set(ConfigClient.AbilityHotbarAltTexture);
        ConfigClient.AbilityHotbarTextPositionProperty.set(ConfigClient.AbilityHotbarTextPosition);
        ConfigClient.AbilityHotbarVisibleSlotsProperty.set(ConfigClient.AbilityHotbarVisibleSlots);
        ConfigClient.AbilityHotbarShowAlwaysProperty.set(ConfigClient.AbilityHotbarShowAlways);
        ConfigClient.AbilityHotbarTextVisibilityProperty.set(ConfigClient.AbilityHotbarTextVisibility);
        if (ConfigClient.config.hasChanged()) {
            ConfigClient.config.save();
        }
    }

    @Override
    public void renderOnScreen(float partialTicks) {
        long currentTime;
        if (!this.enabled || this.isEditting) {
            return;
        }
        if (this.mc.field_71439_g == null || this.mc.field_71462_r != null) {
            return;
        }
        if (!this.hasAnyAbilities()) {
            return;
        }
        boolean showAlways = ConfigClient.AbilityHotbarShowAlways;
        long now = Minecraft.func_71386_F();
        if (!showAlways) {
            boolean hudKeyHeld = this.isHudKeyHeld();
            float dt = this.lastFadeTime > 0L ? (float)(now - this.lastFadeTime) / 1000.0f : 0.0f;
            dt = Math.min(dt, 0.1f);
            this.lastFadeTime = now;
            this.fadeAlpha = hudKeyHeld || this.pendingSelectKey != null || this.scrollOffset != 0.0f ? Math.min(1.0f, this.fadeAlpha + 3.0f * dt) : Math.max(0.0f, this.fadeAlpha - 3.0f * dt);
            if (this.fadeAlpha <= 0.0f) {
                return;
            }
        } else {
            this.fadeAlpha = 1.0f;
            this.lastFadeTime = now;
        }
        if ((currentTime = Minecraft.func_71386_F()) - this.lastUpdateTime > 500L) {
            this.updateAbilities();
            this.lastUpdateTime = currentTime;
        }
        if (this.pendingSelectKey != null && currentTime - this.pendingSelectTime >= 300L) {
            PacketHandler.Instance.sendToServer(new AbilityHotbarSelectPacket(this.pendingSelectKey));
            this.pendingSelectKey = null;
        }
        this.updateScrollAnimation();
        this.updateOverlayDimensions();
        if (this.activeIndices.length == 0) {
            return;
        }
        ScaledResolution sr = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
        boolean isHorizontal = ConfigClient.AbilityHotbarHorizontal;
        float effectiveScale = this.getEffectiveScale(sr);
        int visibleSlots = this.getEffectiveVisibleSlots();
        int half = visibleSlots / 2;
        int actualX = (int)(this.posX / 100.0f * (float)sr.func_78326_a());
        int actualY = (int)(this.posY / 100.0f * (float)sr.func_78328_b());
        GL11.glPushMatrix();
        GL11.glTranslatef((float)actualX, (float)actualY, (float)0.0f);
        GL11.glScalef((float)effectiveScale, (float)effectiveScale, (float)1.0f);
        int centerX = this.overlayWidth / 2;
        int centerY = this.overlayHeight / 2;
        boolean activePhase = ClientAbilityState.activePhase;
        int textVis = ConfigClient.AbilityHotbarTextVisibility;
        boolean showText = textVis == 1 ? false : (textVis == 2 ? this.isHudKeyHeld() : true);
        for (int i = 0; i < 12; ++i) {
            this.slots[i].updateNameFade();
        }
        for (int offset = -half; offset <= half; ++offset) {
            int cy;
            int cx;
            int activeIdx = ((this.activeCenter + offset) % this.activeIndices.length + this.activeIndices.length) % this.activeIndices.length;
            int rawSlotIndex = this.activeIndices[activeIdx];
            float visualPos = (float)offset + this.scrollOffset;
            float absDist = Math.abs(visualPos);
            float slotScale = absDist <= 1.0f ? 1.0f - 0.25f * absDist : 0.75f - 0.15f * (absDist - 1.0f);
            slotScale = Math.max(0.1f, slotScale);
            int scaledSize = Math.max(1, (int)(24.0f * slotScale));
            if (isHorizontal) {
                cx = centerX + (int)(visualPos * 28.0f);
                cy = centerY;
            } else {
                cx = centerX;
                cy = centerY + (int)(visualPos * 28.0f);
            }
            float maxDist = (float)half + 0.5f;
            if (absDist > maxDist) continue;
            float edgeAlpha = absDist > (float)half - 0.5f ? 1.0f - (absDist - ((float)half - 0.5f)) : 1.0f;
            float slotAlpha = edgeAlpha * this.fadeAlpha;
            boolean isCenter = offset == 0 && this.scrollOffset == 0.0f;
            float slotCooldown = 0.0f;
            if (rawSlotIndex >= 0 && this.slots[rawSlotIndex].abilityKey != null) {
                slotCooldown = this.getCooldownProgressForSlot(rawSlotIndex);
            }
            if (rawSlotIndex == -1) {
                this.drawDeselectSlot(cx, cy, scaledSize, isCenter, slotAlpha, activePhase);
                continue;
            }
            this.slots[rawSlotIndex].drawCarousel(this.mc, sr, slotCooldown, cx, cy, scaledSize, isCenter, slotAlpha, showText, activePhase);
        }
        GL11.glPopMatrix();
    }

    private void drawDeselectSlot(int cx, int cy, int size, boolean isCenter, float alpha, boolean activePhase) {
        float radius = (float)size / 2.0f;
        float baseAlpha = isCenter ? 0.9f : 0.6f;
        float a = baseAlpha * alpha;
        boolean altTexture = ConfigClient.AbilityHotbarAltTexture;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)cx, (float)cy, (float)0.0f);
        GL11.glDisable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        if (activePhase) {
            if (isCenter) {
                GL11.glColor4f((float)0.65f, (float)0.14f, (float)0.14f, (float)a);
            } else {
                GL11.glColor4f((float)0.38f, (float)0.1f, (float)0.1f, (float)a);
            }
        } else if (isCenter) {
            GL11.glColor4f((float)0.5f, (float)0.2f, (float)0.2f, (float)a);
        } else {
            GL11.glColor4f((float)0.3f, (float)0.3f, (float)0.3f, (float)a);
        }
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
        if (activePhase) {
            if (isCenter) {
                GL11.glColor4f((float)1.0f, (float)0.45f, (float)0.35f, (float)alpha);
            } else {
                GL11.glColor4f((float)0.75f, (float)0.3f, (float)0.3f, (float)(alpha * 0.8f));
            }
        } else if (isCenter) {
            GL11.glColor4f((float)0.8f, (float)0.4f, (float)0.4f, (float)alpha);
        } else {
            GL11.glColor4f((float)0.5f, (float)0.5f, (float)0.5f, (float)(alpha * 0.8f));
        }
        float xSize = radius * 0.4f;
        GL11.glLineWidth((float)2.0f);
        GL11.glBegin((int)1);
        GL11.glVertex2f((float)(-xSize), (float)(-xSize));
        GL11.glVertex2f((float)xSize, (float)xSize);
        GL11.glVertex2f((float)(-xSize), (float)xSize);
        GL11.glVertex2f((float)xSize, (float)(-xSize));
        GL11.glEnd();
        GL11.glEnable((int)3553);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }

    @Override
    public void renderEditing() {
        this.isEditting = true;
        this.updateOverlayDimensions();
        ScaledResolution sr = new ScaledResolution(this.mc, this.mc.field_71443_c, this.mc.field_71440_d);
        float effectiveScale = this.getEffectiveScale(sr);
        boolean isHorizontal = ConfigClient.AbilityHotbarHorizontal;
        FontRenderer fr = this.mc.field_71466_p;
        int visibleSlots = this.getEffectiveVisibleSlots();
        int half = visibleSlots / 2;
        int actualX = (int)(this.posX / 100.0f * (float)sr.func_78326_a());
        int actualY = (int)(this.posY / 100.0f * (float)sr.func_78328_b());
        GL11.glPushMatrix();
        GL11.glTranslatef((float)actualX, (float)actualY, (float)0.0f);
        GL11.glScalef((float)effectiveScale, (float)effectiveScale, (float)1.0f);
        int centerX = this.overlayWidth / 2;
        int centerY = this.overlayHeight / 2;
        int[] slotCx = new int[visibleSlots];
        int[] slotCy = new int[visibleSlots];
        int[] slotSizes = new int[visibleSlots];
        for (int offset = -half; offset <= half; ++offset) {
            float alpha;
            int cy;
            int cx;
            float absDist = Math.abs(offset);
            float slotScale = absDist <= 1.0f ? 1.0f - 0.25f * absDist : 0.75f - 0.15f * (absDist - 1.0f);
            slotScale = Math.max(0.1f, slotScale);
            int scaledSize = Math.max(1, (int)(24.0f * slotScale));
            if (isHorizontal) {
                cx = centerX + offset * 28;
                cy = centerY;
            } else {
                cx = centerX;
                cy = centerY + offset * 28;
            }
            int idx = offset + half;
            slotCx[idx] = cx;
            slotCy[idx] = cy;
            slotSizes[idx] = scaledSize;
            float radius = (float)scaledSize / 2.0f;
            boolean altTexture = ConfigClient.AbilityHotbarAltTexture;
            GL11.glDisable((int)3553);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            float f = alpha = offset == 0 ? 0.9f : 0.6f;
            if (offset == 0) {
                GL11.glColor4f((float)0.8f, (float)0.8f, (float)0.2f, (float)alpha);
            } else {
                GL11.glColor4f((float)0.4f, (float)0.4f, (float)0.4f, (float)alpha);
            }
            if (altTexture) {
                GL11.glBegin((int)7);
                GL11.glVertex2f((float)((float)cx - radius), (float)((float)cy - radius));
                GL11.glVertex2f((float)((float)cx + radius), (float)((float)cy - radius));
                GL11.glVertex2f((float)((float)cx + radius), (float)((float)cy + radius));
                GL11.glVertex2f((float)((float)cx - radius), (float)((float)cy + radius));
                GL11.glEnd();
                continue;
            }
            GL11.glBegin((int)6);
            GL11.glVertex2f((float)cx, (float)cy);
            for (int i = 0; i <= 32; ++i) {
                double angle = Math.PI * 2 * (double)i / 32.0;
                GL11.glVertex2f((float)((float)cx + (float)(Math.cos(angle) * (double)radius)), (float)((float)cy + (float)(Math.sin(angle) * (double)radius)));
            }
            GL11.glEnd();
        }
        GL11.glEnable((int)3553);
        GL11.glEnable((int)3042);
        for (int i = 0; i < visibleSlots; ++i) {
            String label = String.valueOf(i + 1);
            float labelScale = (float)slotSizes[i] / 24.0f;
            if (labelScale < 0.4f) continue;
            int textColor = i == half ? -1 : -1430537285;
            int textW = fr.func_78256_a(label);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)slotCx[i], (float)slotCy[i], (float)0.0f);
            GL11.glScalef((float)labelScale, (float)labelScale, (float)1.0f);
            fr.func_78276_b(label, -textW / 2, -fr.field_78288_b / 2, textColor);
            GL11.glPopMatrix();
        }
        boolean altTexture = ConfigClient.AbilityHotbarAltTexture;
        String modeLabel = altTexture ? "Square" : "Circle";
        int modeLabelW = fr.func_78256_a(modeLabel);
        fr.func_78261_a(modeLabel, this.overlayWidth / 2 - modeLabelW / 2, this.overlayHeight + 2, -1433892728);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        Gui.func_73734_a((int)0, (int)0, (int)this.overlayWidth, (int)this.overlayHeight, (int)0x22000000);
        this.drawRectOutline(0, 0, this.overlayWidth, this.overlayHeight, -865678234);
        int handleLeft = this.overlayWidth - 10;
        int handleTop = this.overlayHeight - 10;
        Gui.func_73734_a((int)handleLeft, (int)handleTop, (int)this.overlayWidth, (int)this.overlayHeight, (int)-2039584);
        this.drawRectOutline(handleLeft, handleTop, this.overlayWidth, this.overlayHeight, -10855846);
        Gui.func_73734_a((int)(handleLeft + 2), (int)(handleTop + 7), (int)(handleLeft + 8), (int)(handleTop + 8), (int)-1436129690);
        Gui.func_73734_a((int)(handleLeft + 4), (int)(handleTop + 5), (int)(handleLeft + 8), (int)(handleTop + 6), (int)-1436129690);
        Gui.func_73734_a((int)(handleLeft + 6), (int)(handleTop + 3), (int)(handleLeft + 8), (int)(handleTop + 4), (int)-1436129690);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3553);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }

    @Override
    public void addEditorButtons(List<GuiButton> buttonList) {
        super.addEditorButtons(buttonList);
        String orientLabel = ConfigClient.AbilityHotbarHorizontal ? "Horizontal" : "Vertical";
        buttonList.add(new GuiButton(1, 0, 0, 120, 20, orientLabel));
        String texLabel = ConfigClient.AbilityHotbarAltTexture ? "Square" : "Circle";
        buttonList.add(new GuiButton(2, 0, 0, 120, 20, texLabel));
        buttonList.add(new GuiButton(3, 0, 0, 120, 20, this.getTextPositionLabel()));
        buttonList.add(new GuiButton(4, 0, 0, 120, 20, "Slots: " + ConfigClient.AbilityHotbarVisibleSlots));
        buttonList.add(new GuiButton(5, 0, 0, 120, 20, ConfigClient.AbilityHotbarShowAlways ? "Show: Always" : "Show: Hold Key"));
        buttonList.add(new GuiButton(6, 0, 0, 120, 20, this.getTextVisibilityLabel()));
    }

    @Override
    public void onEditorButtonPressed(GuiButton button) {
        if (button.field_146127_k == 1) {
            ConfigClient.AbilityHotbarHorizontal = !ConfigClient.AbilityHotbarHorizontal;
            button.field_146126_j = ConfigClient.AbilityHotbarHorizontal ? "Horizontal" : "Vertical";
            this.updateOverlayDimensions();
        } else if (button.field_146127_k == 2) {
            ConfigClient.AbilityHotbarAltTexture = !ConfigClient.AbilityHotbarAltTexture;
            button.field_146126_j = ConfigClient.AbilityHotbarAltTexture ? "Square" : "Circle";
        } else if (button.field_146127_k == 3) {
            ConfigClient.AbilityHotbarTextPosition = ConfigClient.AbilityHotbarTextPosition == 1 ? 2 : 1;
            button.field_146126_j = this.getTextPositionLabel();
        } else if (button.field_146127_k == 4) {
            int current = ConfigClient.AbilityHotbarVisibleSlots;
            ConfigClient.AbilityHotbarVisibleSlots = current <= 3 ? 5 : (current <= 5 ? 7 : 3);
            button.field_146126_j = "Slots: " + ConfigClient.AbilityHotbarVisibleSlots;
            this.updateOverlayDimensions();
        } else if (button.field_146127_k == 5) {
            ConfigClient.AbilityHotbarShowAlways = !ConfigClient.AbilityHotbarShowAlways;
            button.field_146126_j = ConfigClient.AbilityHotbarShowAlways ? "Show: Always" : "Show: Hold Key";
        } else if (button.field_146127_k == 6) {
            ConfigClient.AbilityHotbarTextVisibility = (ConfigClient.AbilityHotbarTextVisibility + 1) % 3;
            button.field_146126_j = this.getTextVisibilityLabel();
        } else {
            super.onEditorButtonPressed(button);
        }
    }

    private String getTextPositionLabel() {
        boolean h = ConfigClient.AbilityHotbarHorizontal;
        switch (ConfigClient.AbilityHotbarTextPosition) {
            case 1: {
                return h ? "Text: Above" : "Text: Left";
            }
            case 2: {
                return h ? "Text: Below" : "Text: Right";
            }
        }
        return h ? "Text: Below" : "Text: Right";
    }

    private String getTextVisibilityLabel() {
        switch (ConfigClient.AbilityHotbarTextVisibility) {
            case 0: {
                return "Text: Shown";
            }
            case 1: {
                return "Text: Hidden";
            }
            case 2: {
                return "Text: Held";
            }
        }
        return "Text: Shown";
    }

    private void updateAbilities() {
        int i;
        PlayerData playerData = ClientCacheHandler.playerData;
        if (playerData == null) {
            return;
        }
        PlayerAbilityHotbarData hotbarData = playerData.hotbarData;
        if (hotbarData == null) {
            return;
        }
        EntityClientPlayerMP clientPlayer = Minecraft.func_71410_x().field_71439_g;
        boolean globalBlock = clientPlayer != null && AbilityController.Instance != null && !AbilityController.Instance.canPlayerActivate((EntityPlayer)clientPlayer);
        for (int i2 = 0; i2 < 12; ++i2) {
            AbilityHotbarData slotData = hotbarData.slots[i2];
            String key = slotData.isEmpty() ? null : slotData.abilityKey;
            Ability ability = null;
            noppes.npcs.api.ability.IAbilityAction action = null;
            if (key != null && AbilityController.Instance != null) {
                if (globalBlock) {
                    key = null;
                } else {
                    if (slotData.isChainKey()) {
                        action = AbilityController.Instance.resolveChainedAbility(slotData.getResolveKey());
                    } else {
                        ability = AbilityController.Instance.resolveAbility(key);
                        action = ability;
                    }
                    if (ability == null && action == null) {
                        key = null;
                    }
                    if (action != null && clientPlayer != null && !action.isAvailableFor((EntityPlayer)clientPlayer)) {
                        key = null;
                        ability = null;
                        action = null;
                    }
                }
            }
            this.slots[i2].setAbility(key, ability, (IAbilityAction)((Object)action));
        }
        ArrayList<Integer> active = new ArrayList<Integer>();
        for (i = 0; i < 12; ++i) {
            if (this.slots[i].abilityKey == null) continue;
            active.add(i);
        }
        active.add(-1);
        this.activeIndices = new int[active.size()];
        for (i = 0; i < active.size(); ++i) {
            this.activeIndices[i] = (Integer)active.get(i);
        }
        if (this.activeCenter >= this.activeIndices.length) {
            this.activeCenter = 0;
        }
        long now = Minecraft.func_71386_F();
        if (this.pendingSelectKey != null || this.scrollOffset != 0.0f || now - this.lastUserCycleTime < 1000L) {
            return;
        }
        if (playerData.abilityData != null) {
            String selectedKey = playerData.abilityData.getSelectedAbilityKey();
            boolean foundSelected = false;
            block3: for (int i3 = 0; i3 < 12; ++i3) {
                AbilityHotbarData slotData = hotbarData.slots[i3];
                boolean sel = selectedKey != null && !slotData.isEmpty() && selectedKey.equals(slotData.abilityKey);
                this.slots[i3].setSelectedState(sel);
                if (!sel) continue;
                for (int j = 0; j < this.activeIndices.length; ++j) {
                    if (this.activeIndices[j] != i3) continue;
                    this.activeCenter = j;
                    foundSelected = true;
                    continue block3;
                }
            }
            if (!foundSelected) {
                for (int j = 0; j < this.activeIndices.length; ++j) {
                    if (this.activeIndices[j] != -1) continue;
                    this.activeCenter = j;
                    break;
                }
            }
        }
    }

    private boolean isHudKeyHeld() {
        return KeyPressHandler.isHudKeyHeld();
    }

    public boolean hasAnyAbilities() {
        PlayerData playerData = ClientCacheHandler.playerData;
        if (playerData == null || playerData.hotbarData == null) {
            return false;
        }
        return playerData.hotbarData.hasAnyAbilities();
    }

    private float getCooldownProgressForSlot(int slotIndex) {
        PlayerData playerData = ClientCacheHandler.playerData;
        if (playerData == null || playerData.abilityData == null) {
            return 0.0f;
        }
        String key = this.slots[slotIndex].abilityKey;
        Ability ability = this.slots[slotIndex].ability;
        if (ability != null && ability.isPerAbilityCooldown()) {
            return playerData.abilityData.getPerAbilityCooldownProgress(key);
        }
        return playerData.abilityData.getGlobalCooldownProgress();
    }

    public void onCycleNext() {
        this.cycle(1);
    }

    public void onCyclePrev() {
        this.cycle(-1);
    }

    private void cycle(int direction) {
        String keyToSend;
        long now;
        if (!this.enabled) {
            return;
        }
        if (this.mc.field_71439_g == null || this.mc.field_71462_r != null) {
            return;
        }
        if (this.activeIndices.length == 0) {
            return;
        }
        this.activeCenter = ((this.activeCenter + direction) % this.activeIndices.length + this.activeIndices.length) % this.activeIndices.length;
        this.scrollOffset = direction;
        this.scrollStartTime = now = Minecraft.func_71386_F();
        this.lastUserCycleTime = now;
        int rawSlotIndex = this.activeIndices[this.activeCenter];
        for (int i = 0; i < 12; ++i) {
            this.slots[i].setSelectedState(i == rawSlotIndex);
        }
        this.pendingSelectKey = keyToSend = rawSlotIndex >= 0 && this.slots[rawSlotIndex].abilityKey != null ? this.slots[rawSlotIndex].abilityKey : "";
        this.pendingSelectTime = Minecraft.func_71386_F();
    }

    private void updateScrollAnimation() {
        if (this.scrollOffset == 0.0f) {
            return;
        }
        long now = Minecraft.func_71386_F();
        float t = (float)(now - this.scrollStartTime) / 150.0f;
        t = Math.min(t, 1.0f);
        float eased = this.easeOutCubic(t);
        float initialOffset = this.scrollOffset < 0.0f ? -1.0f : 1.0f;
        this.scrollOffset = initialOffset * (1.0f - eased);
        if (t >= 1.0f) {
            this.scrollOffset = 0.0f;
        }
    }

    private float easeOutCubic(float x) {
        float v = 1.0f - x;
        return 1.0f - v * v * v;
    }

    public void refresh() {
        this.updateAbilities();
        this.lastUpdateTime = Minecraft.func_71386_F();
    }

    private void drawRectOutline(int left, int top, int right, int bottom, int color) {
        Gui.func_73734_a((int)left, (int)top, (int)right, (int)(top + 1), (int)color);
        Gui.func_73734_a((int)left, (int)(bottom - 1), (int)right, (int)bottom, (int)color);
        Gui.func_73734_a((int)left, (int)top, (int)(left + 1), (int)bottom, (int)color);
        Gui.func_73734_a((int)(right - 1), (int)top, (int)right, (int)bottom, (int)color);
    }
}

