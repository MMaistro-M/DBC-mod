/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.texture.AbstractTexture
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.util.ResourceLocation
 */
package riskyken.armourersWorkshop.client.render;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinDye;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartTypeTextured;
import riskyken.armourersWorkshop.client.model.bake.ColouredFace;
import riskyken.armourersWorkshop.common.SkinHelper;
import riskyken.armourersWorkshop.common.painting.PaintType;
import riskyken.armourersWorkshop.common.skin.data.Skin;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.utils.BitwiseUtils;

public class EntityTextureInfo {
    private final int textureWidth;
    private final int textureHeight;
    private int lastEntityTextureHash;
    private int[] lastSkinHashs;
    private int[] lastDyeHashs;
    private Skin[] skins;
    private ISkinDye[] dyes;
    private ResourceLocation normalTexture;
    private ResourceLocation replacementTexture;
    private int lastEntitySkinColour;
    private int lastEntityHairColour;
    private BufferedImage bufferedEntityImage;
    private BufferedImage bufferedEntitySkinnedImage;
    private boolean needsUpdate;
    private boolean loading;

    public EntityTextureInfo() {
        this(64, 32);
    }

    public EntityTextureInfo(int width, int height) {
        int i;
        this.textureWidth = width;
        this.textureHeight = height;
        this.lastEntityTextureHash = -1;
        this.lastSkinHashs = new int[50];
        this.lastDyeHashs = new int[50];
        this.normalTexture = null;
        this.replacementTexture = null;
        for (i = 0; i < this.lastSkinHashs.length; ++i) {
            this.lastSkinHashs[i] = -1;
        }
        for (i = 0; i < this.lastDyeHashs.length; ++i) {
            this.lastDyeHashs[i] = -1;
        }
        this.lastEntitySkinColour = -1;
        this.lastEntityHairColour = -1;
        this.bufferedEntitySkinnedImage = new BufferedImage(this.textureWidth, this.textureHeight, 2);
        this.needsUpdate = true;
        this.loading = false;
    }

    public boolean getNeedsUpdate() {
        return this.needsUpdate;
    }

    public void updateTexture(ResourceLocation resourceLocation) {
        if (this.lastEntityTextureHash != resourceLocation.hashCode()) {
            BufferedImage buff = SkinHelper.getBufferedImageSkin(resourceLocation);
            this.bufferedEntityImage = null;
            if (buff != null) {
                this.loading = false;
                this.lastEntityTextureHash = resourceLocation.hashCode();
                this.normalTexture = resourceLocation;
                this.bufferedEntityImage = buff;
                this.needsUpdate = true;
            }
        }
        if (this.bufferedEntityImage == null) {
            this.lastEntityTextureHash = AbstractClientPlayer.field_110314_b.hashCode();
            this.bufferedEntityImage = SkinHelper.getBufferedImageSkin(AbstractClientPlayer.field_110314_b);
            if (this.bufferedEntityImage != null & !this.loading) {
                this.loading = true;
                this.needsUpdate = true;
            }
        }
    }

    public void updateSkinColour(int colour) {
        if (this.lastEntitySkinColour != colour) {
            this.lastEntitySkinColour = colour;
            this.needsUpdate = true;
        }
    }

    public void updateHairColour(int colour) {
        if (this.lastEntityHairColour != colour) {
            this.lastEntityHairColour = colour;
            this.needsUpdate = true;
        }
    }

    public void updateSkins(Skin[] skins) {
        this.skins = skins;
        for (int i = 0; i < skins.length; ++i) {
            if (skins[i] != null) {
                if (skins[i].lightHash() == this.lastSkinHashs[i]) continue;
                this.lastSkinHashs[i] = skins[i].lightHash();
                this.needsUpdate = true;
                continue;
            }
            if (this.lastSkinHashs[i] == -1) continue;
            this.lastSkinHashs[i] = -1;
            this.needsUpdate = true;
        }
    }

    public void updateDyes(ISkinDye[] dyes) {
        this.dyes = dyes;
        for (int i = 0; i < this.skins.length; ++i) {
            if (dyes[i] != null) {
                if (dyes[i].hashCode() == this.lastDyeHashs[i]) continue;
                this.lastDyeHashs[i] = dyes[i].hashCode();
                this.needsUpdate = true;
                continue;
            }
            if (this.lastDyeHashs[i] == -1) continue;
            this.lastDyeHashs[i] = -1;
            this.needsUpdate = true;
        }
    }

    public void checkTexture() {
        if (this.needsUpdate) {
            this.buildTexture();
            this.needsUpdate = false;
        }
    }

    private void buildTexture() {
        this.applyPlayerToTexture();
        this.applySkinsToTexture();
        this.createReplacmentTexture();
    }

    private void applyPlayerToTexture() {
        for (int ix = 0; ix < this.textureWidth; ++ix) {
            for (int iy = 0; iy < this.textureHeight && this.bufferedEntityImage != null; ++iy) {
                this.bufferedEntitySkinnedImage.setRGB(ix, iy, this.bufferedEntityImage.getRGB(ix, iy));
            }
        }
    }

    private void applySkinsToTexture() {
        Skin skin;
        int i;
        for (i = 0; i < this.skins.length; ++i) {
            skin = this.skins[i];
            if (skin == null || !skin.hasPaintData()) continue;
            for (int ix = 0; ix < this.textureWidth; ++ix) {
                for (int iy = 0; iy < this.textureHeight; ++iy) {
                    int colour;
                    int paintColour = skin.getPaintData()[ix + iy * this.textureWidth];
                    PaintType paintType = PaintType.getPaintTypeFromColour(paintColour);
                    if (paintType == PaintType.NORMAL) {
                        this.bufferedEntitySkinnedImage.setRGB(ix, iy, BitwiseUtils.setUByteToInt(paintColour, 0, 255));
                    }
                    if (paintType == PaintType.HAIR) {
                        colour = this.dyeColour(this.lastEntityHairColour, paintColour, 9, skin);
                        this.bufferedEntitySkinnedImage.setRGB(ix, iy, colour);
                    }
                    if (paintType == PaintType.SKIN) {
                        colour = this.dyeColour(this.lastEntitySkinColour, paintColour, 8, skin);
                        this.bufferedEntitySkinnedImage.setRGB(ix, iy, colour);
                    }
                    if (paintType.getKey() < 1 || paintType.getKey() > 8) continue;
                    int dyeNumber = paintType.getKey() - 1;
                    if (this.dyes != null && this.dyes[i] != null && this.dyes[i].haveDyeInSlot(dyeNumber)) {
                        byte[] dye = this.dyes[i].getDyeColour(dyeNumber);
                        int colour2 = this.dyeColour(dye, paintColour, dyeNumber, skin);
                        this.bufferedEntitySkinnedImage.setRGB(ix, iy, colour2);
                        continue;
                    }
                    this.bufferedEntitySkinnedImage.setRGB(ix, iy, BitwiseUtils.setUByteToInt(paintColour, 0, 255));
                }
            }
        }
        for (i = 0; i < this.skins.length; ++i) {
            skin = this.skins[i];
            if (skin == null) continue;
            for (int j = 0; j < skin.getSkinType().getSkinParts().size(); ++j) {
                ISkinPartType skinPartType = skin.getSkinType().getSkinParts().get(j);
                if (!(skinPartType instanceof ISkinPartTypeTextured)) continue;
                ISkinPartTypeTextured skinPartTex = (ISkinPartTypeTextured)skinPartType;
                this.makePartBlank(skinPartTex, this.bufferedEntitySkinnedImage, skin.getProperties());
            }
        }
    }

    public void makePartBlank(ISkinPartTypeTextured skinPartTex, BufferedImage texture, SkinProperties skinProps) {
        Point posBase = skinPartTex.getTextureLocation();
        int width = skinPartTex.getTextureModelSize().getX() * 2 + skinPartTex.getTextureModelSize().getZ() * 2;
        int height = skinPartTex.getTextureModelSize().getY() + skinPartTex.getTextureModelSize().getZ();
        for (int ix = 0; ix < width; ++ix) {
            for (int iy = 0; iy < height; ++iy) {
                if (skinPartTex.isModelOverridden(skinProps)) {
                    texture.setRGB(posBase.x + ix, posBase.y + iy, 0xFFFFFF);
                }
                if (!skinPartTex.isOverlayOverridden(skinProps)) continue;
            }
        }
    }

    private int dyeColour(int dye, int colour, int dyeIndex, Skin skin) {
        byte[] dyeArray = new byte[]{(byte)(dye >>> 16 & 0xFF), (byte)(dye >>> 8 & 0xFF), (byte)(dye & 0xFF)};
        return this.dyeColour(dyeArray, colour, dyeIndex, skin);
    }

    private int dyeColour(byte[] dye, int colour, int dyeIndex, Skin skin) {
        byte r = (byte)(colour >>> 16 & 0xFF);
        byte g = (byte)(colour >>> 8 & 0xFF);
        byte b = (byte)(colour & 0xFF);
        if (dye.length > 3) {
            byte dyeB;
            byte dyeG;
            byte dyeR;
            byte t = dye[3];
            if ((t & 0xFF) == PaintType.HAIR.getKey()) {
                dyeR = (byte)(this.lastEntityHairColour >>> 16 & 0xFF);
                dyeG = (byte)(this.lastEntityHairColour >>> 8 & 0xFF);
                dyeB = (byte)(this.lastEntityHairColour & 0xFF);
                dye = new byte[]{dyeR, dyeG, dyeB};
            }
            if ((t & 0xFF) == PaintType.SKIN.getKey()) {
                dyeR = (byte)(this.lastEntitySkinColour >>> 16 & 0xFF);
                dyeG = (byte)(this.lastEntitySkinColour >>> 8 & 0xFF);
                dyeB = (byte)(this.lastEntitySkinColour & 0xFF);
                dye = new byte[]{dyeR, dyeG, dyeB};
            }
        }
        int[] average = new int[]{127, 127, 127};
        if (skin != null) {
            average = skin.getAverageDyeColour(dyeIndex);
        }
        dye = ColouredFace.dyeColour(r, g, b, dye, average);
        return -16777216 + ((dye[0] & 0xFF) << 16) + ((dye[1] & 0xFF) << 8) + (dye[2] & 0xFF);
    }

    protected void finalize() throws Throwable {
        TextureManager renderEngine = Minecraft.func_71410_x().field_71446_o;
        if (this.replacementTexture != null) {
            renderEngine.func_147645_c(this.replacementTexture);
        }
        super.finalize();
    }

    private void createReplacmentTexture() {
        TextureManager renderEngine = Minecraft.func_71410_x().field_71446_o;
        if (this.replacementTexture != null) {
            renderEngine.func_147645_c(this.replacementTexture);
        }
        SkinTextureObject sto = new SkinTextureObject(this.bufferedEntitySkinnedImage);
        this.replacementTexture = new ResourceLocation("armourersWorkshop".toLowerCase(), String.valueOf(this.bufferedEntitySkinnedImage.hashCode()));
        renderEngine.func_110579_a(this.replacementTexture, (ITextureObject)sto);
    }

    public ResourceLocation preRender() {
        this.checkTexture();
        if (this.replacementTexture != null) {
            return this.replacementTexture;
        }
        return this.normalTexture;
    }

    public ResourceLocation postRender() {
        return this.normalTexture;
    }

    private class SkinTextureObject
    extends AbstractTexture {
        private final BufferedImage texture;

        public SkinTextureObject(BufferedImage texture) {
            this.texture = texture;
        }

        public void func_110551_a(IResourceManager resourceManager) throws IOException {
            this.func_110552_b();
            TextureUtil.func_110987_a((int)this.field_110553_a, (BufferedImage)this.texture);
        }
    }
}

