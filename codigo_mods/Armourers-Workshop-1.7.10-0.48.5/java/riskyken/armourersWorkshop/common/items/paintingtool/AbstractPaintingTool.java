/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package riskyken.armourersWorkshop.common.items.paintingtool;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import riskyken.armourersWorkshop.api.common.painting.IPaintingTool;
import riskyken.armourersWorkshop.client.particles.EntityFXPaintSplash;
import riskyken.armourersWorkshop.client.particles.ParticleManager;
import riskyken.armourersWorkshop.common.items.AbstractModItem;
import riskyken.armourersWorkshop.common.painting.IBlockPainter;
import riskyken.armourersWorkshop.common.painting.PaintType;
import riskyken.armourersWorkshop.common.painting.PaintingHelper;

public abstract class AbstractPaintingTool
extends AbstractModItem
implements IPaintingTool,
IBlockPainter {
    public AbstractPaintingTool(String name) {
        super(name);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean hasEffect(ItemStack stack, int pass) {
        PaintType paintType = PaintingHelper.getToolPaintType(stack);
        return paintType != PaintType.NORMAL;
    }

    @SideOnly(value=Side.CLIENT)
    protected void spawnPaintParticles(World world, int x, int y, int z, int side, int colour) {
        for (int i = 0; i < 3; ++i) {
            EntityFXPaintSplash particle = new EntityFXPaintSplash(world, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, colour, ForgeDirection.getOrientation((int)side));
            ParticleManager.INSTANCE.spawnParticle(world, particle);
        }
    }

    public int func_82790_a(ItemStack stack, int pass) {
        if (pass == 0) {
            return super.func_82790_a(stack, pass);
        }
        return this.getToolColour(stack);
    }

    public boolean func_77623_v() {
        return true;
    }

    @Override
    public boolean getToolHasColour(ItemStack stack) {
        return true;
    }

    @Override
    public int getToolColour(ItemStack stack) {
        return PaintingHelper.getToolPaintColourRGB(stack);
    }

    @Override
    public void setToolColour(ItemStack stack, int colour) {
        PaintingHelper.setToolPaintColour(stack, colour);
    }

    @Override
    public void setToolPaintType(ItemStack stack, PaintType paintType) {
        PaintingHelper.setToolPaint(stack, paintType);
    }

    @Override
    public PaintType getToolPaintType(ItemStack stack) {
        return PaintingHelper.getToolPaintType(stack);
    }
}

