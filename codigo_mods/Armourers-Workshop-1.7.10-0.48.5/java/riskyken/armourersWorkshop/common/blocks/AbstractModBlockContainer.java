/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 */
package riskyken.armourersWorkshop.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.common.creativetab.ISortOrder;

public abstract class AbstractModBlockContainer
extends BlockContainer
implements ISortOrder {
    private int sortPriority = 100;

    public AbstractModBlockContainer(String name) {
        super(Material.field_151573_f);
        this.func_149647_a(ArmourersWorkshop.tabArmorersWorkshop);
        this.func_149711_c(3.0f);
        this.func_149672_a(field_149777_j);
        this.func_149663_c(name);
    }

    public AbstractModBlockContainer(String name, Material material, Block.SoundType soundType, boolean addCreativeTab) {
        super(material);
        if (addCreativeTab) {
            this.func_149647_a(ArmourersWorkshop.tabArmorersWorkshop);
        }
        this.func_149711_c(3.0f);
        this.func_149672_a(soundType);
        this.func_149663_c(name);
    }

    public String func_149739_a() {
        return this.getModdedUnlocalizedName(super.func_149739_a());
    }

    protected String getModdedUnlocalizedName(String unlocalizedName) {
        String name = unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
        return "tile." + "armourersWorkshop".toLowerCase() + ":" + name;
    }

    public AbstractModBlockContainer setSortPriority(int sortPriority) {
        this.sortPriority = sortPriority;
        return this;
    }

    @Override
    public int getSortPriority() {
        return this.sortPriority;
    }
}

