/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.common.skin.type.wings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.api.common.skin.Point3D;
import riskyken.armourersWorkshop.api.common.skin.Rectangle3D;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinProperties;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.model.armourer.ModelChest;
import riskyken.armourersWorkshop.common.skin.type.AbstractSkinPartTypeBase;

public class SkinWingsPartLeftWing
extends AbstractSkinPartTypeBase {
    public SkinWingsPartLeftWing(ISkinType baseType) {
        super(baseType);
        this.buildingSpace = new Rectangle3D(-32, -24, -20, 32, 48, 48);
        this.guideSpace = new Rectangle3D(-4, -12, -4, 8, 12, 4);
        this.offset = new Point3D(0, -1, 2);
    }

    @Override
    public String getPartName() {
        return "leftWing";
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void renderBuildingGuide(float scale, ISkinProperties skinProps, boolean showHelper) {
        GL11.glTranslated((double)0.0, (double)((float)this.buildingSpace.getY() * scale), (double)0.0);
        GL11.glTranslated((double)0.0, (double)((float)(-this.guideSpace.getY()) * scale), (double)0.0);
        GL11.glTranslated((double)0.0, (double)0.0, (double)(-2.0f * scale));
        ModelChest.MODEL.renderChest(scale);
        GL11.glTranslated((double)0.0, (double)0.0, (double)(2.0f * scale));
        GL11.glTranslated((double)0.0, (double)((float)this.guideSpace.getY() * scale), (double)0.0);
        GL11.glTranslated((double)0.0, (double)((float)(-this.buildingSpace.getY()) * scale), (double)0.0);
    }

    @Override
    public int getMaximumMarkersNeeded() {
        return 1;
    }

    @Override
    public int getMinimumMarkersNeeded() {
        return 1;
    }
}

