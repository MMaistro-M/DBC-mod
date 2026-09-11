/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 */
package riskyken.armourersWorkshop.common.skin.type.legs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.IIconRegister;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinProperty;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.skin.type.AbstractSkinTypeBase;
import riskyken.armourersWorkshop.common.skin.type.legs.SkinLegsPartLeftLeg;
import riskyken.armourersWorkshop.common.skin.type.legs.SkinLegsPartRightLeg;
import riskyken.armourersWorkshop.common.skin.type.legs.SkinLegsPartSkirt;

public class SkinLegs
extends AbstractSkinTypeBase {
    private ArrayList<ISkinPartType> skinParts = new ArrayList();

    public SkinLegs() {
        this.skinParts.add(new SkinLegsPartLeftLeg(this));
        this.skinParts.add(new SkinLegsPartRightLeg(this));
        this.skinParts.add(new SkinLegsPartSkirt(this));
    }

    @Override
    public ArrayList<ISkinPartType> getSkinParts() {
        return this.skinParts;
    }

    @Override
    public String getRegistryName() {
        return "armourers:legs";
    }

    @Override
    public String getName() {
        return "Legs";
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerIcon(IIconRegister register) {
        this.icon = register.func_94245_a(LibItemResources.TEMPLATE_LEGS);
        this.emptySlotIcon = register.func_94245_a(LibItemResources.SLOT_SKIN_LEGS);
    }

    @Override
    public int getVanillaArmourSlotId() {
        return 2;
    }

    @Override
    public ArrayList<ISkinProperty<?>> getProperties() {
        ArrayList<ISkinProperty<?>> properties = super.getProperties();
        properties.add(SkinProperties.PROP_MODEL_OVERRIDE_LEG_LEFT);
        properties.add(SkinProperties.PROP_MODEL_OVERRIDE_LEG_RIGHT);
        properties.add(SkinProperties.PROP_MODEL_HIDE_OVERLAY_LEG_LEFT);
        properties.add(SkinProperties.PROP_MODEL_HIDE_OVERLAY_LEG_RIGHT);
        properties.add(SkinProperties.PROP_MODEL_LEGS_LIMIT_LIMBS);
        return properties;
    }
}

