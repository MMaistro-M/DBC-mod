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
import riskyken.armourersWorkshop.client.model.armourer.ModelArrow;
import riskyken.armourersWorkshop.common.skin.type.AbstractSkinPartTypeBase;

public class SkinBowPartArrow
extends AbstractSkinPartTypeBase {
    public SkinBowPartArrow(ISkinType baseType) {
        super(baseType);
        this.buildingSpace = new Rectangle3D(-5, -5, -2, 11, 11, 16);
        this.guideSpace = new Rectangle3D(0, 0, 0, 0, 0, 0);
        this.offset = new Point3D(0, 0, 21);
    }

    @Override
    public String getPartName() {
        return "arrow";
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void renderBuildingGuide(float scale, ISkinProperties skinProps, boolean showHelper) {
        GL11.glTranslated((double)0.0, (double)((float)this.buildingSpace.getY() * scale), (double)0.0);
        GL11.glTranslated((double)0.0, (double)((float)(-this.guideSpace.getY()) * scale), (double)0.0);
        if (showHelper) {
            ModelArrow.MODEL.render(scale, true);
        }
        GL11.glTranslated((double)0.0, (double)((float)this.guideSpace.getY() * scale), (double)0.0);
        GL11.glTranslated((double)0.0, (double)((float)(-this.buildingSpace.getY()) * scale), (double)0.0);
    }
}

