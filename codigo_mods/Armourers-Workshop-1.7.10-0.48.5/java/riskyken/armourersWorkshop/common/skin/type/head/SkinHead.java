/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 */
package riskyken.armourersWorkshop.common.skin.type.head;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.IIconRegister;
import riskyken.armourersWorkshop.api.common.skin.data.ISkinProperty;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.skin.data.SkinProperties;
import riskyken.armourersWorkshop.common.skin.type.AbstractSkinTypeBase;
import riskyken.armourersWorkshop.common.skin.type.head.SkinHeadPartBase;

public class SkinHead
extends AbstractSkinTypeBase {
    private ArrayList<ISkinPartType> skinParts = new ArrayList();

    public SkinHead() {
        this.skinParts.add(new SkinHeadPartBase(this));
    }

    @Override
    public ArrayList<ISkinPartType> getSkinParts() {
        return this.skinParts;
    }

    @Override
    public String getRegistryName() {
        return "armourers:head";
    }

    @Override
    public String getName() {
        return "Head";
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerIcon(IIconRegister register) {
        this.icon = register.func_94245_a(LibItemResources.TEMPLATE_HEAD);
        this.emptySlotIcon = register.func_94245_a(LibItemResources.SLOT_SKIN_HEAD);
    }

    @Override
    public int getVanillaArmourSlotId() {
        return 0;
    }

    @Override
    public ArrayList<ISkinProperty<?>> getProperties() {
        ArrayList<ISkinProperty<?>> properties = super.getProperties();
        properties.add(SkinProperties.PROP_MODEL_OVERRIDE_HEAD);
        properties.add(SkinProperties.PROP_MODEL_HIDE_OVERLAY_HEAD);
        return properties;
    }
}

