/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 */
package riskyken.armourersWorkshop.api.common.skin.type;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import riskyken.armourersWorkshop.api.common.IPoint3D;
import riskyken.armourersWorkshop.api.common.IRectangle3D;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinProperties;

public interface ISkinPartType {
    public String getRegistryName();

    public String getPartName();

    public IRectangle3D getBuildingSpace();

    public IRectangle3D getGuideSpace();

    public IPoint3D getOffset();

    @SideOnly(value=Side.CLIENT)
    public void renderBuildingGuide(float var1, ISkinProperties var2, boolean var3);

    public int getMinimumMarkersNeeded();

    public int getMaximumMarkersNeeded();

    public boolean isPartRequired();

    @SideOnly(value=Side.CLIENT)
    public IPoint3D getItemRenderOffset();

    @SideOnly(value=Side.CLIENT)
    public IRectangle3D getItemRenderTextureBounds();

    public boolean isModelOverridden(ISkinProperties var1);

    public boolean isOverlayOverridden(ISkinProperties var1);
}

