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
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.common.items.paintingtool;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.api.common.painting.IPantable;
import riskyken.armourersWorkshop.api.common.painting.IPantableBlock;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.blocks.BlockLocation;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.items.paintingtool.AbstractPaintingTool;
import riskyken.armourersWorkshop.common.lib.LibSounds;
import riskyken.armourersWorkshop.common.painting.PaintType;
import riskyken.armourersWorkshop.common.painting.tool.AbstractToolOption;
import riskyken.armourersWorkshop.common.painting.tool.IConfigurableTool;
import riskyken.armourersWorkshop.common.painting.tool.ToolOptions;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourer;
import riskyken.armourersWorkshop.common.undo.UndoManager;
import riskyken.armourersWorkshop.utils.TranslateUtils;

public class ItemPaintRoller
extends AbstractPaintingTool
implements IConfigurableTool {
    @SideOnly(value=Side.CLIENT)
    private IIcon tipIcon;
    @SideOnly(value=Side.CLIENT)
    private IIcon faceModeIcon;
    @SideOnly(value=Side.CLIENT)
    private IIcon faceModeTipIcon;

    public ItemPaintRoller() {
        super("paintRoller");
        this.setSortPriority(19);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister register) {
        this.field_77791_bV = register.func_94245_a(LibItemResources.PAINT_ROLLER);
        this.tipIcon = register.func_94245_a(LibItemResources.PAINT_ROLLER_TIP);
        this.faceModeIcon = register.func_94245_a(LibItemResources.PAINT_ROLLER_FACE_MODE);
        this.faceModeTipIcon = register.func_94245_a(LibItemResources.PAINT_ROLLER_FACE_MODE_TIP);
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
        if (block instanceof IPantableBlock) {
            if (!world.field_72995_K) {
                UndoManager.begin(player);
            }
            this.paintArea(world, block, player, stack, x, y, z, side);
            if (!world.field_72995_K) {
                world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, LibSounds.PAINT, 1.0f, world.field_73012_v.nextFloat() * 0.1f + 0.9f);
                UndoManager.end(player);
            }
            return true;
        }
        if (block == ModBlocks.armourerBrain & player.func_70093_af()) {
            TileEntity te;
            if (!world.field_72995_K && (te = world.func_147438_o(x, y, z)) != null && te instanceof TileEntityArmourer) {
                ((TileEntityArmourer)te).toolUsedOnArmourer(this, world, stack, player);
            }
            return true;
        }
        return false;
    }

    private void paintArea(World world, Block targetBlock, EntityPlayer player, ItemStack stack, int x, int y, int z, int side) {
        int radius = (Integer)ToolOptions.RADIUS.readFromNBT(stack.func_77978_p());
        for (int i = -radius + 1; i < radius; ++i) {
            for (int j = -radius + 1; j < radius; ++j) {
                Block block;
                BlockLocation bl = null;
                switch (side) {
                    case 0: {
                        bl = new BlockLocation(x + j, y, z + i);
                        break;
                    }
                    case 1: {
                        bl = new BlockLocation(x + j, y, z + i);
                        break;
                    }
                    case 2: {
                        bl = new BlockLocation(x + i, y + j, z);
                        break;
                    }
                    case 3: {
                        bl = new BlockLocation(x + i, y + j, z);
                        break;
                    }
                    case 4: {
                        bl = new BlockLocation(x, y + i, z + j);
                        break;
                    }
                    case 5: {
                        bl = new BlockLocation(x, y + i, z + j);
                    }
                }
                if (bl == null || !(targetBlock != ModBlocks.boundingBox & (block = world.func_147439_a(bl.x, bl.y, bl.z)) != ModBlocks.boundingBox | targetBlock == ModBlocks.boundingBox & block == ModBlocks.boundingBox)) continue;
                this.usedOnBlockSide(stack, player, world, bl, block, side);
            }
        }
    }

    @Override
    public void usedOnBlockSide(ItemStack stack, EntityPlayer player, World world, BlockLocation bl, Block block, int side) {
        if (block instanceof IPantableBlock) {
            int newColour = this.getToolColour(stack);
            PaintType paintType = this.getToolPaintType(stack);
            if (!world.field_72995_K) {
                IPantableBlock worldColourable = (IPantableBlock)block;
                if (((Boolean)ToolOptions.FULL_BLOCK_MODE.readFromNBT(stack.func_77978_p())).booleanValue()) {
                    for (int i = 0; i < 6; ++i) {
                        int oldColour = worldColourable.getColour((IBlockAccess)world, bl.x, bl.y, bl.z, i);
                        byte oldPaintType = (byte)worldColourable.getPaintType((IBlockAccess)world, bl.x, bl.y, bl.z, i).getKey();
                        UndoManager.blockPainted(player, world, bl.x, bl.y, bl.z, oldColour, oldPaintType, i);
                        ((IPantableBlock)block).setColour((IBlockAccess)world, bl.x, bl.y, bl.z, newColour, i);
                        ((IPantableBlock)block).setPaintType((IBlockAccess)world, bl.x, bl.y, bl.z, paintType, i);
                    }
                } else {
                    int oldColour = worldColourable.getColour((IBlockAccess)world, bl.x, bl.y, bl.z, side);
                    byte oldPaintType = (byte)worldColourable.getPaintType((IBlockAccess)world, bl.x, bl.y, bl.z, side).getKey();
                    UndoManager.blockPainted(player, world, bl.x, bl.y, bl.z, oldColour, oldPaintType, side);
                    ((IPantableBlock)block).setColour((IBlockAccess)world, bl.x, bl.y, bl.z, newColour, side);
                    ((IPantableBlock)block).setPaintType((IBlockAccess)world, bl.x, bl.y, bl.z, paintType, side);
                }
            } else {
                this.spawnPaintParticles(world, bl.x, bl.y, bl.z, side, newColour);
            }
        }
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (world.field_72995_K & player.func_70093_af()) {
            player.openGui((Object)ArmourersWorkshop.instance, 3, world, 0, 0, 0);
        }
        return stack;
    }

    @Override
    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        super.func_77624_a(stack, player, list, p_77624_4_);
        Color c = new Color(this.getToolColour(stack));
        PaintType paintType = this.getToolPaintType(stack);
        int radius = (Integer)ToolOptions.RADIUS.readFromNBT(stack.func_77978_p());
        String hex = String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
        String colourText = TranslateUtils.translate("item.armourersworkshop:rollover.colour", c.getRGB());
        String hexText = TranslateUtils.translate("item.armourersworkshop:rollover.hex", hex);
        String paintText = TranslateUtils.translate("item.armourersworkshop:rollover.paintType", paintType.getLocalizedName());
        String radiusText = TranslateUtils.translate("item.armourersworkshop:rollover.radius", radius * 2 - 1, radius * 2 - 1, 1);
        list.add(colourText);
        list.add(hexText);
        list.add(paintText);
        list.add(radiusText);
        list.add(TranslateUtils.translate("item.armourersworkshop:rollover.openSettings"));
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        if (((Boolean)ToolOptions.FULL_BLOCK_MODE.readFromNBT(stack.func_77978_p())).booleanValue()) {
            if (pass == 0) {
                return this.field_77791_bV;
            }
            return this.tipIcon;
        }
        if (pass == 0) {
            return this.faceModeIcon;
        }
        return this.faceModeTipIcon;
    }

    @Override
    public void getToolOptions(ArrayList<AbstractToolOption> toolOptionList) {
        toolOptionList.add(ToolOptions.FULL_BLOCK_MODE);
        toolOptionList.add(ToolOptions.RADIUS);
    }
}

