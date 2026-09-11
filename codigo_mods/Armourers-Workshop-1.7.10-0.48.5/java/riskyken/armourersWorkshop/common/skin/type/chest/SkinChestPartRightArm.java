/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.common.skin.type.chest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Point;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.IPoint3D;
import riskyken.armourersWorkshop.api.common.IRectangle3D;
import riskyken.armourersWorkshop.api.common.skin.Point3D;
import riskyken.armourersWorkshop.api.common.skin.Rectangle3D;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinProperties;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartTypeTextured;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.model.armourer.ModelChest;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.skin.type.AbstractSkinPartTypeBase;

public class SkinChestPartRightArm
extends AbstractSkinPartTypeBase
implements ISkinPartTypeTextured {
    public SkinChestPartRightArm(ISkinType baseType) {
        super(baseType);
        this.buildingSpace = new Rectangle3D(-3, -16, -14, 14, 32, 28);
        this.guideSpace = new Rectangle3D(-1, -10, -2, 4, 12, 4);
        this.offset = new Point3D(-10, -7, 0);
    }

    @Override
    public String getPartName() {
        return "rightArm";
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void renderBuildingGuide(float scale, ISkinProperties skinProps, boolean showHelper) {
        GL11.glTranslated((double)0.0, (double)((float)this.buildingSpace.getY() * scale), (double)0.0);
        GL11.glTranslated((double)0.0, (double)((float)(-this.guideSpace.getY()) * scale), (double)0.0);
        ModelChest.MODEL.renderRightArm(scale);
        GL11.glTranslated((double)0.0, (double)((float)this.guideSpace.getY() * scale), (double)0.0);
        GL11.glTranslated((double)0.0, (double)((float)(-this.buildingSpace.getY()) * scale), (double)0.0);
    }

    @Override
    public Point getTextureLocation() {
        return new Point(40, 16);
    }

    @Override
    public boolean isTextureMirrored() {
        return false;
    }

    @Override
    public IPoint3D getTextureModelSize() {
        return new Point3D(4, 12, 4);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public IPoint3D getItemRenderOffset() {
        return new Point3D(-5, 2, 0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public IRectangle3D getItemRenderTextureBounds() {
        return new Rectangle3D(4, 0, -2, 4, 12, 4);
    }

    @Override
    public boolean isModelOverridden(ISkinProperties skinProps) {
        return SkinProperties.PROP_MODEL_OVERRIDE_ARM_RIGHT.getValue(skinProps);
    }

    @Override
    public boolean isOverlayOverridden(ISkinProperties skinProps) {
        return SkinProperties.PROP_MODEL_HIDE_OVERLAY_ARM_RIGHT.getValue(skinProps);
    }
}

