/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.common.blocks;

import java.util.ArrayList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.common.blocks.BlockSkinnable;
import riskyken.armourersWorkshop.common.items.ItemDebugTool;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinnableChild;

public class BlockSkinnableChild
extends BlockSkinnable
implements ItemDebugTool.IDebug {
    public BlockSkinnableChild() {
        super("skinnableChild");
    }

    public BlockSkinnableChild(String name) {
        super(name);
    }

    @Override
    public TileEntity func_149915_a(World world, int p_149915_2_) {
        return new TileEntitySkinnableChild();
    }

    @Override
    public void getDebugHoverText(World world, int x, int y, int z, ArrayList<String> textLines) {
        TileEntitySkinnableChild te = (TileEntitySkinnableChild)world.func_147438_o(x, y, z);
        super.getDebugHoverText(world, x, y, z, textLines);
        textLines.add("parent X=" + te.parentX);
        textLines.add("parent Y=" + te.parentY);
        textLines.add("parent Z=" + te.parentZ);
        textLines.add("offset X=" + (-te.parentX + te.field_145851_c));
        textLines.add("offset Y=" + (te.field_145848_d - te.parentY));
        textLines.add("offset Z=" + -(te.parentZ - te.field_145849_e));
    }
}

