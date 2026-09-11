/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 */
package riskyken.armourersWorkshop.common.skin.type.chest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.IIconRegister;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinProperty;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.skin.type.AbstractSkinTypeBase;
import riskyken.armourersWorkshop.common.skin.type.chest.SkinChestPartBase;
import riskyken.armourersWorkshop.common.skin.type.chest.SkinChestPartLeftArm;
import riskyken.armourersWorkshop.common.skin.type.chest.SkinChestPartRightArm;

public class SkinChest
extends AbstractSkinTypeBase {
    private ArrayList<ISkinPartType> skinParts = new ArrayList();

    public SkinChest() {
        this.skinParts.add(new SkinChestPartBase(this));
        this.skinParts.add(new SkinChestPartLeftArm(this));
        this.skinParts.add(new SkinChestPartRightArm(this));
    }

    @Override
    public ArrayList<ISkinPartType> getSkinParts() {
        return this.skinParts;
    }

    @Override
    public String getRegistryName() {
        return "armourers:chest";
    }

    @Override
    public String getName() {
        return "Chest";
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerIcon(IIconRegister register) {
        this.icon = register.func_94245_a(LibItemResources.TEMPLATE_CHEST);
        this.emptySlotIcon = register.func_94245_a(LibItemResources.SLOT_SKIN_CHEST);
    }

    @Override
    public int getVanillaArmourSlotId() {
        return 1;
    }

    @Override
    public ArrayList<ISkinProperty<?>> getProperties() {
        ArrayList<ISkinProperty<?>> properties = super.getProperties();
        properties.add(SkinProperties.PROP_MODEL_OVERRIDE_CHEST);
        properties.add(SkinProperties.PROP_MODEL_OVERRIDE_ARM_LEFT);
        properties.add(SkinProperties.PROP_MODEL_OVERRIDE_ARM_RIGHT);
        properties.add(SkinProperties.PROP_MODEL_HIDE_OVERLAY_CHEST);
        properties.add(SkinProperties.PROP_MODEL_HIDE_OVERLAY_ARM_LEFT);
        properties.add(SkinProperties.PROP_MODEL_HIDE_OVERLAY_ARM_RIGHT);
        return properties;
    }
}

