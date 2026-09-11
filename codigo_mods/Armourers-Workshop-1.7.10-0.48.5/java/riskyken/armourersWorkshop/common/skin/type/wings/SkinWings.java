/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 */
package riskyken.armourersWorkshop.common.skin.type.wings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.IIconRegister;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinProperty;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.skin.type.AbstractSkinTypeBase;
import riskyken.armourersWorkshop.common.skin.type.wings.SkinWingsPartLeftWing;
import riskyken.armourersWorkshop.common.skin.type.wings.SkinWingsPartRightWing;

public class SkinWings
extends AbstractSkinTypeBase {
    private ArrayList<ISkinPartType> skinParts = new ArrayList();

    public SkinWings() {
        this.skinParts.add(new SkinWingsPartLeftWing(this));
        this.skinParts.add(new SkinWingsPartRightWing(this));
    }

    @Override
    public ArrayList<ISkinPartType> getSkinParts() {
        return this.skinParts;
    }

    @Override
    public String getRegistryName() {
        return "armourers:wings";
    }

    @Override
    public String getName() {
        return "wings";
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerIcon(IIconRegister register) {
        this.icon = register.func_94245_a(LibItemResources.TEMPLATE_WINGS);
        this.emptySlotIcon = register.func_94245_a(LibItemResources.SLOT_SKIN_WINGS);
    }

    @Override
    public ArrayList<ISkinProperty<?>> getProperties() {
        ArrayList<ISkinProperty<?>> properties = super.getProperties();
        properties.add(SkinProperties.PROP_WINGS_FLYING_SPEED);
        properties.add(SkinProperties.PROP_WINGS_IDLE_SPEED);
        properties.add(SkinProperties.PROP_WINGS_MAX_ANGLE);
        properties.add(SkinProperties.PROP_WINGS_MIN_ANGLE);
        properties.add(SkinProperties.PROP_WINGS_MOVMENT_TYPE);
        return properties;
    }

    public static enum MovementType {
        EASE,
        LINEAR;

    }
}

