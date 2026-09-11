/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 */
package riskyken.armourersWorkshop.common.skin.type.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.renderer.texture.IIconRegister;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartType;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.skin.type.AbstractSkinTypeBase;
import riskyken.armourersWorkshop.common.skin.type.item.SkinItemPartBase;

public class SkinItem
extends AbstractSkinTypeBase {
    private final String name;
    private ArrayList<ISkinPartType> skinParts;

    public SkinItem(String name) {
        this.name = name;
        this.skinParts = new ArrayList();
        this.skinParts.add(new SkinItemPartBase(this));
    }

    @Override
    public ArrayList<ISkinPartType> getSkinParts() {
        return this.skinParts;
    }

    @Override
    public String getRegistryName() {
        return "armourers:" + this.name.toLowerCase();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerIcon(IIconRegister register) {
        this.icon = register.func_94245_a(LibItemResources.TEMPLATE_ITEM + this.name.toLowerCase());
        this.emptySlotIcon = register.func_94245_a(LibItemResources.SLOT_SKIN_ITEM + this.name.toLowerCase());
    }
}

