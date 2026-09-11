/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 */
package riskyken.armourersWorkshop.common.skin.type.bow;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.IIconRegister;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.skin.type.AbstractSkinTypeBase;
import riskyken.armourersWorkshop.common.skin.type.bow.SkinBowPartArrow;
import riskyken.armourersWorkshop.common.skin.type.bow.SkinBowPartBase;
import riskyken.armourersWorkshop.common.skin.type.bow.SkinBowPartFrame1;
import riskyken.armourersWorkshop.common.skin.type.bow.SkinBowPartFrame2;

public class SkinBow
extends AbstractSkinTypeBase {
    private ArrayList<ISkinPartType> skinParts = new ArrayList();

    public SkinBow() {
        this.skinParts.add(new SkinBowPartBase(this));
        this.skinParts.add(new SkinBowPartFrame1(this));
        this.skinParts.add(new SkinBowPartFrame2(this));
        this.skinParts.add(new SkinBowPartArrow(this));
    }

    @Override
    public ArrayList<ISkinPartType> getSkinParts() {
        return this.skinParts;
    }

    @Override
    public String getRegistryName() {
        return "armourers:bow";
    }

    @Override
    public String getName() {
        return "bow";
    }

    @Override
    public boolean showHelperCheckbox() {
        return true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerIcon(IIconRegister register) {
        this.icon = register.func_94245_a(LibItemResources.TEMPLATE_BOW);
        this.emptySlotIcon = register.func_94245_a(LibItemResources.SLOT_SKIN_BOW);
    }
}

