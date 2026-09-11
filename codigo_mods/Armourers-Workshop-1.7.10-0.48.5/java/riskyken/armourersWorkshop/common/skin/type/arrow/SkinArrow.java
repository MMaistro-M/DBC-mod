/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 */
package riskyken.armourersWorkshop.common.skin.type.arrow;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.IIconRegister;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.skin.type.AbstractSkinTypeBase;
import riskyken.armourersWorkshop.common.skin.type.arrow.SkinArrowPartBase;

public class SkinArrow
extends AbstractSkinTypeBase {
    public final ISkinPartType partBase;
    private ArrayList<ISkinPartType> skinParts = new ArrayList();

    public SkinArrow() {
        this.partBase = new SkinArrowPartBase(this);
        this.skinParts.add(this.partBase);
    }

    @Override
    public ArrayList<ISkinPartType> getSkinParts() {
        return this.skinParts;
    }

    @Override
    public String getRegistryName() {
        return "armourers:arrow";
    }

    @Override
    public String getName() {
        return "arrow";
    }

    @Override
    public boolean showHelperCheckbox() {
        return true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerIcon(IIconRegister register) {
        this.icon = register.func_94245_a(LibItemResources.TEMPLATE_ARROW);
        this.emptySlotIcon = register.func_94245_a(LibItemResources.SLOT_SKIN_ARROW);
    }
}

