/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.gui.hud.ability;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kamkeel.npcs.controllers.data.ability.Ability;
import kamkeel.npcs.controllers.data.ability.data.AbilityIconData;
import kamkeel.npcs.controllers.data.ability.data.ChainedAbility;
import kamkeel.npcs.controllers.data.ability.data.IAbilityAction;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.ClientCacheHandler;
import noppes.npcs.client.renderer.AnimationHelper;
import noppes.npcs.client.renderer.ImageData;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class AbilityIcon
extends Gui {
    private static final ResourceLocation FALLBACK_TEXTURE = new ResourceLocation("customnpcs", "textures/gui/ability_fallback.png");
    private final AbilityIconData data;
    public int width;
    public int height;
    private Ability.DefaultIconLayer[] defaultLayers = null;
    private int defaultIconWidth = 48;
    private int defaultIconHeight = 48;

    private AbilityIcon(AbilityIconData data) {
        this.data = data;
        this.width = data.width;
        this.height = data.height;
    }

    public static AbilityIcon fromAbility(Ability ability) {
        if (ability != null) {
            AbilityIcon icon = new AbilityIcon(AbilityIconData.fromAbility(ability));
            icon.defaultLayers = ability.getDefaultIconLayers();
            icon.defaultIconWidth = ability.getDefaultIconWidth();
            icon.defaultIconHeight = ability.getDefaultIconHeight();
            return icon;
        }
        return AbilityIcon.fromDefaults();
    }

    public static AbilityIcon fromChainedAbility(ChainedAbility chain) {
        if (chain != null) {
            return new AbilityIcon(AbilityIconData.fromChainedAbility(chain));
        }
        return AbilityIcon.fromDefaults();
    }

    public static AbilityIcon fromAction(IAbilityAction action) {
        if (action instanceof Ability) {
            return AbilityIcon.fromAbility((Ability)action);
        }
        if (action instanceof ChainedAbility) {
            return AbilityIcon.fromChainedAbility((ChainedAbility)action);
        }
        return AbilityIcon.fromDefaults();
    }

    private static AbilityIcon fromDefaults() {
        return new AbilityIcon(AbilityIconData.fromCustomData(new NBTTagCompound()));
    }

    public void draw() {
        this.draw(0);
    }

    public void draw(int state) {
        this.draw(state, 1.0f);
    }

    public void draw(int state, float alpha) {
        TextureManager renderEngine = Minecraft.func_71410_x().field_71446_o;
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        if (this.data.isEnabled()) {
            GL11.glScalef((float)this.data.scale, (float)this.data.scale, (float)1.0f);
            boolean drewAny = false;
            for (int i = 0; i < this.data.getLayerCount(); ++i) {
                ImageData imageData;
                AbilityIconData.Layer layer = this.data.getLayer(i);
                if (!layer.hasTexture() || (imageData = ClientCacheHandler.getImageData(layer.texture)) == null || !imageData.imageLoaded()) continue;
                int tint = layer.tintColor;
                float r = (float)(tint >> 16 & 0xFF) / 255.0f;
                float g = (float)(tint >> 8 & 0xFF) / 255.0f;
                float b = (float)(tint & 0xFF) / 255.0f;
                GL11.glColor4f((float)r, (float)g, (float)b, (float)alpha);
                renderEngine.func_110577_a(imageData.getLocation());
                Tessellator t = this.getLayerTessellator(imageData, layer, i == 0 ? state : 0);
                t.func_78381_a();
                drewAny = true;
            }
            if (!drewAny) {
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
                renderEngine.func_110577_a(FALLBACK_TEXTURE);
                this.getFallbackTessellator().func_78381_a();
            }
        } else if (this.hasDefaultLayers()) {
            for (Ability.DefaultIconLayer defLayer : this.defaultLayers) {
                ImageData imageData;
                String texPath = defLayer.getTextureForState(state);
                if (texPath == null || texPath.isEmpty() || (imageData = ClientCacheHandler.getImageData(texPath)) == null || !imageData.imageLoaded()) continue;
                int color = defLayer.getColor();
                float r = (float)(color >> 16 & 0xFF) / 255.0f;
                float g = (float)(color >> 8 & 0xFF) / 255.0f;
                float b = (float)(color & 0xFF) / 255.0f;
                GL11.glColor4f((float)r, (float)g, (float)b, (float)alpha);
                renderEngine.func_110577_a(imageData.getLocation());
                this.getDefaultIconTessellator().func_78381_a();
            }
        } else {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
            renderEngine.func_110577_a(FALLBACK_TEXTURE);
            this.getFallbackTessellator().func_78381_a();
        }
        GL11.glPopMatrix();
    }

    private boolean hasDefaultLayers() {
        return this.defaultLayers != null && this.defaultLayers.length > 0;
    }

    private Tessellator getLayerTessellator(ImageData imageData, AbilityIconData.Layer layer, int state) {
        float hw = (float)this.data.width / 2.0f;
        float hh = (float)this.data.height / 2.0f;
        float texW = imageData.getTotalWidth();
        float texH = imageData.getTotalHeight();
        int ix = state > 0 ? this.data.getIconXForState(state) : layer.iconX;
        int iy = state > 0 ? this.data.getIconYForState(state) : layer.iconY;
        float vOff = 0.0f;
        if (imageData.isAnimated()) {
            vOff = imageData.getCurrentFrameVOffset();
        } else if (this.data.isAnimated()) {
            vOff = AnimationHelper.getFrameVOffset((int)texH, this.data.getFrameCount(), this.data.getFrameTime());
        }
        float u1 = (float)ix / texW;
        float v1 = vOff + (float)iy / texH;
        float u2 = (float)(ix + this.data.width) / texW;
        float v2 = vOff + (float)(iy + this.data.height) / texH;
        Tessellator t = Tessellator.field_78398_a;
        t.func_78382_b();
        t.func_78374_a((double)(-hw), (double)hh, (double)this.field_73735_i, (double)u1, (double)v2);
        t.func_78374_a((double)hw, (double)hh, (double)this.field_73735_i, (double)u2, (double)v2);
        t.func_78374_a((double)hw, (double)(-hh), (double)this.field_73735_i, (double)u2, (double)v1);
        t.func_78374_a((double)(-hw), (double)(-hh), (double)this.field_73735_i, (double)u1, (double)v1);
        return t;
    }

    private Tessellator getDefaultIconTessellator() {
        float hw = (float)this.defaultIconWidth / 2.0f;
        float hh = (float)this.defaultIconHeight / 2.0f;
        Tessellator t = Tessellator.field_78398_a;
        t.func_78382_b();
        t.func_78374_a((double)(-hw), (double)hh, (double)this.field_73735_i, 0.0, 1.0);
        t.func_78374_a((double)hw, (double)hh, (double)this.field_73735_i, 1.0, 1.0);
        t.func_78374_a((double)hw, (double)(-hh), (double)this.field_73735_i, 1.0, 0.0);
        t.func_78374_a((double)(-hw), (double)(-hh), (double)this.field_73735_i, 0.0, 0.0);
        return t;
    }

    private Tessellator getFallbackTessellator() {
        float hw = 16.0f;
        float hh = 16.0f;
        Tessellator t = Tessellator.field_78398_a;
        t.func_78382_b();
        t.func_78374_a((double)(-hw), (double)hh, (double)this.field_73735_i, 0.0, 1.0);
        t.func_78374_a((double)hw, (double)hh, (double)this.field_73735_i, 1.0, 1.0);
        t.func_78374_a((double)hw, (double)(-hh), (double)this.field_73735_i, 1.0, 0.0);
        t.func_78374_a((double)(-hw), (double)(-hh), (double)this.field_73735_i, 0.0, 0.0);
        return t;
    }

    public boolean hasTexture() {
        return this.data.isEnabled() || this.hasDefaultLayers();
    }

    public float getDrawSize() {
        if (this.data.isEnabled()) {
            return (float)Math.max(this.width, this.height) * this.data.scale;
        }
        if (this.hasDefaultLayers()) {
            return Math.max(this.defaultIconWidth, this.defaultIconHeight);
        }
        return 32.0f;
    }
}

