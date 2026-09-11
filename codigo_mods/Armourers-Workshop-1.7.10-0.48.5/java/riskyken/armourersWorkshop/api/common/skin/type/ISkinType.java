/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.util.IIcon
 */
package riskyken.armourersWorkshop.api.common.skin.type;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinProperties;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinProperty;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;

public interface ISkinType {
    public ArrayList<ISkinPartType> getSkinParts();

    public String getRegistryName();

    public String getName();

    @SideOnly(value=Side.CLIENT)
    public void registerIcon(IIconRegister var1);

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon();

    @SideOnly(value=Side.CLIENT)
    public IIcon getEmptySlotIcon();

    public boolean showSkinOverlayCheckbox();

    public boolean showHelperCheckbox();

    public int getVanillaArmourSlotId();

    public boolean isHidden();

    public boolean enabled();

    public ArrayList<ISkinProperty<?>> getProperties();

    public boolean haveBoundsChanged(ISkinProperties var1, ISkinProperties var2);
}

