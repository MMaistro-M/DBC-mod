/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 */
package riskyken.armourersWorkshop.common.skin.type.outfit;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.IIconRegister;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.skin.type.AbstractSkinTypeBase;

public class SkinOutfit
extends AbstractSkinTypeBase {
    private final ISkinType[] skinTypes;
    private ArrayList<ISkinPartType> skinParts;

    public SkinOutfit(ISkinType ... skinTypes) {
        this.skinTypes = skinTypes;
        this.skinParts = new ArrayList();
        for (int i = 0; i < skinTypes.length; ++i) {
            this.skinParts.addAll(skinTypes[i].getSkinParts());
        }
    }

    @Override
    public ArrayList<ISkinPartType> getSkinParts() {
        return this.skinParts;
    }

    @Override
    public String getRegistryName() {
        return "armourers:outfit";
    }

    @Override
    public String getName() {
        return "Outfit";
    }

    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerIcon(IIconRegister register) {
        this.icon = register.func_94245_a(LibItemResources.TEMPLATE_OUTFIT);
        this.emptySlotIcon = register.func_94245_a(LibItemResources.SLOT_SKIN_OUTFIT);
    }
}

