/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.api.common.painting.IPaintingTool;
import riskyken.armourersWorkshop.api.common.painting.IPantable;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.items.AbstractModItem;
import riskyken.armourersWorkshop.common.painting.PaintType;
import riskyken.armourersWorkshop.common.painting.PaintingHelper;
import riskyken.armourersWorkshop.utils.TranslateUtils;

public class ItemDyeBottle
extends AbstractModItem
implements IPaintingTool {
    private IIcon paintIcon;

    public ItemDyeBottle() {
        super("dyeBottle");
        this.setSortPriority(11);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister register) {
        this.field_77791_bV = register.func_94245_a(LibItemResources.DYE_BOTTLE);
        this.paintIcon = register.func_94245_a(LibItemResources.DYE_BOTTLE_PAINT);
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        Block block = world.func_147439_a(x, y, z);
        if (player.func_70093_af() & block == ModBlocks.colourMixer) {
            TileEntity te = world.func_147438_o(x, y, z);
            if (te != null && te instanceof IPantable && !world.field_72995_K) {
                int colour = ((IPantable)te).getColour(0);
                PaintType paintType = ((IPantable)te).getPaintType(0);
                this.setToolColour(stack, colour);
                this.setToolPaintType(stack, paintType);
            }
            return true;
        }
        return false;
    }

    public boolean hasEffect(ItemStack stack, int pass) {
        PaintType paintType = PaintingHelper.getToolPaintType(stack);
        return paintType != PaintType.NORMAL;
    }

    public boolean func_77623_v() {
        return true;
    }

    @Override
    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        super.func_77624_a(stack, player, list, p_77624_4_);
        if (this.getToolHasColour(stack)) {
            Color c = new Color(this.getToolColour(stack));
            PaintType paintType = this.getToolPaintType(stack);
            String hex = String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
            String colourText = TranslateUtils.translate("item.armourersworkshop:rollover.colour", c.getRGB());
            String hexText = TranslateUtils.translate("item.armourersworkshop:rollover.hex", hex);
            String paintText = TranslateUtils.translate("item.armourersworkshop:rollover.paintType", paintType.getLocalizedName());
            list.add(colourText);
            list.add(hexText);
            list.add(paintText);
        } else {
            String emptyText = TranslateUtils.translate("item.armourersworkshop:rollover.empty");
            list.add(emptyText);
        }
    }

    public int func_82790_a(ItemStack stack, int pass) {
        if (pass == 0) {
            return PaintingHelper.getToolPaintColourRGB(stack);
        }
        return super.func_82790_a(stack, pass);
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        if (pass == 0) {
            if (PaintingHelper.getToolHasPaint(stack)) {
                return this.paintIcon;
            }
            return this.field_77791_bV;
        }
        return this.field_77791_bV;
    }

    @Override
    public boolean getToolHasColour(ItemStack stack) {
        return PaintingHelper.getToolHasPaint(stack);
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

