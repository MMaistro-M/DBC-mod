/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.common.skin.type.bow;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.skin.Point3D;
import riskyken.armourersWorkshop.api.common.skin.Rectangle3D;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinProperties;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.model.armourer.ModelHand;
import riskyken.armourersWorkshop.common.skin.type.AbstractSkinPartTypeBase;

public class SkinBowPartFrame2
extends AbstractSkinPartTypeBase {
    public SkinBowPartFrame2(ISkinType baseType) {
        super(baseType);
        this.buildingSpace = new Rectangle3D(-10, -20, -46, 20, 62, 64);
        this.guideSpace = new Rectangle3D(-2, -2, 2, 4, 4, 8);
        this.offset = new Point3D(21, 0, 0);
    }

    @Override
    public String getPartName() {
        return "frame3";
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void renderBuildingGuide(float scale, ISkinProperties skinProps, boolean showHelper) {
        GL11.glTranslated((double)0.0, (double)((float)this.buildingSpace.getY() * scale), (double)0.0);
        GL11.glTranslated((double)0.0, (double)((float)(-this.guideSpace.getY()) * scale), (double)0.0);
        ModelHand.MODEL.render(scale);
        GL11.glTranslated((double)0.0, (double)((float)this.guideSpace.getY() * scale), (double)0.0);
        GL11.glTranslated((double)0.0, (double)((float)(-this.buildingSpace.getY()) * scale), (double)0.0);
    }

    @Override
    public int getMinimumMarkersNeeded() {
        return 1;
    }

    @Override
    public int getMaximumMarkersNeeded() {
        return 1;
    }

    @Override
    public boolean isPartRequired() {
        return true;
    }
}

