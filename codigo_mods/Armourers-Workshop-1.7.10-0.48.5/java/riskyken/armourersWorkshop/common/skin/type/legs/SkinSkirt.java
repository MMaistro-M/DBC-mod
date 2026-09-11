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
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.skin.type.AbstractSkinTypeBase;
import riskyken.armourersWorkshop.common.skin.type.legs.SkinSkirtPartBase;

public class SkinSkirt
extends AbstractSkinTypeBase {
    private ArrayList<ISkinPartType> skinParts = new ArrayList();

    public SkinSkirt() {
        this.skinParts.add(new SkinSkirtPartBase(this));
    }

    @Override
    public ArrayList<ISkinPartType> getSkinParts() {
        return this.skinParts;
    }

    @Override
    public String getRegistryName() {
        return "armourers:skirt";
    }

    @Override
    public String getName() {
        return "Skirt";
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerIcon(IIconRegister register) {
        this.icon = register.func_94245_a(LibItemResources.TEMPLATE_SKIRT);
        this.emptySlotIcon = register.func_94245_a(LibItemResources.SLOT_SKIN_SKIRT);
    }

    @Override
    public int getVanillaArmourSlotId() {
        return 2;
    }
}

