/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.util.IIcon
 */
package riskyken.armourersWorkshop.common.skin.type;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.util.IIcon;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinProperties;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinProperty;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;

public abstract class AbstractSkinTypeBase
implements ISkinType {
    @SideOnly(value=Side.CLIENT)
    protected IIcon icon;
    @SideOnly(value=Side.CLIENT)
    protected IIcon emptySlotIcon;

    @Override
    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon() {
        return this.icon;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public IIcon getEmptySlotIcon() {
        return this.emptySlotIcon;
    }

    @Override
    public boolean showSkinOverlayCheckbox() {
        return false;
    }

    @Override
    public boolean showHelperCheckbox() {
        return false;
    }

    @Override
    public int getVanillaArmourSlotId() {
        return -1;
    }

    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public boolean enabled() {
        return true;
    }

    @Override
    public ArrayList<ISkinProperty<?>> getProperties() {
        ArrayList properties = new ArrayList();
        properties.add(SkinProperties.PROP_ALL_FLAVOUR_TEXT);
        return properties;
    }

    @Override
    public boolean haveBoundsChanged(ISkinProperties skinPropsOld, ISkinProperties skinPropsNew) {
        for (ISkinPartType partType : this.getSkinParts()) {
            if (partType.isModelOverridden(skinPropsOld) == partType.isModelOverridden(skinPropsNew)) continue;
            return true;
        }
        return false;
    }
}

