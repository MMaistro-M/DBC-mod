/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 */
package riskyken.armourersWorkshop.common.skin.type;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import riskyken.armourersWorkshop.api.common.IPoint3D;
import riskyken.armourersWorkshop.api.common.IRectangle3D;
import riskyken.armourersWorkshop.api.common.skin.Point3D;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinProperties;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;

public abstract class AbstractSkinPartTypeBase
implements ISkinPartType {
    private ISkinType baseType;
    protected IRectangle3D buildingSpace;
    protected IRectangle3D guideSpace;
    protected IPoint3D offset;

    public AbstractSkinPartTypeBase(ISkinType baseType) {
        this.baseType = baseType;
    }

    @Override
    public IRectangle3D getBuildingSpace() {
        return this.buildingSpace;
    }

    @Override
    public IRectangle3D getGuideSpace() {
        return this.guideSpace;
    }

    @Override
    public IPoint3D getOffset() {
        return this.offset;
    }

    @Override
    public String getRegistryName() {
        return this.baseType.getRegistryName() + "." + this.getPartName();
    }

    @Override
    public int getMinimumMarkersNeeded() {
        return 0;
    }

    @Override
    public int getMaximumMarkersNeeded() {
        return 0;
    }

    @Override
    public boolean isPartRequired() {
        return false;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public IPoint3D getItemRenderOffset() {
        return new Point3D(0, 0, 0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public IRectangle3D getItemRenderTextureBounds() {
        return null;
    }

    @Override
    public boolean isModelOverridden(ISkinProperties skinProps) {
        return false;
    }

    @Override
    public boolean isOverlayOverridden(ISkinProperties skinProps) {
        return false;
    }
}

