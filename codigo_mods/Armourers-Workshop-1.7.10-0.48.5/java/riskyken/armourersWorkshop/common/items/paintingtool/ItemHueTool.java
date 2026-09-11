/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
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

import cpw.mods.fml.common.network.simpleimpl.IMessage;
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
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientToolPaintBlock;
import riskyken.armourersWorkshop.common.painting.PaintType;
import riskyken.armourersWorkshop.common.painting.tool.AbstractToolOption;
import riskyken.armourersWorkshop.common.painting.tool.IConfigurableTool;
import riskyken.armourersWorkshop.common.painting.tool.ToolOptions;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourer;
import riskyken.armourersWorkshop.common.undo.UndoManager;
import riskyken.armourersWorkshop.utils.TranslateUtils;

public class ItemHueTool
extends AbstractPaintingTool
implements IConfigurableTool {
    @SideOnly(value=Side.CLIENT)
    private IIcon tipIcon;

    public ItemHueTool() {
        super("hueTool");
        this.setSortPriority(13);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister register) {
        this.field_77791_bV = register.func_94245_a(LibItemResources.HUE_TOOL);
        this.tipIcon = register.func_94245_a(LibItemResources.HUE_TOOL_TIP);
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
            if (((Boolean)ToolOptions.FULL_BLOCK_MODE.readFromNBT(stack.func_77978_p())).booleanValue()) {
                for (int i = 0; i < 6; ++i) {
                    this.usedOnBlockSide(stack, player, world, new BlockLocation(x, y, z), block, i);
                }
            } else {
                this.usedOnBlockSide(stack, player, world, new BlockLocation(x, y, z), block, side);
            }
            if (!world.field_72995_K) {
                UndoManager.end(player);
                world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, LibSounds.BURN, 1.0f, 1.0f);
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

    @Override
    public void usedOnBlockSide(ItemStack stack, EntityPlayer player, World world, BlockLocation bl, Block block, int side) {
        boolean changeHue = ToolOptions.CHANGE_HUE.readFromNBTBool(stack.field_77990_d);
        boolean changeSaturation = ToolOptions.CHANGE_SATURATION.readFromNBTBool(stack.field_77990_d);
        boolean changeBrightness = ToolOptions.CHANGE_BRIGHTNESS.readFromNBTBool(stack.field_77990_d);
        boolean changePaintType = ToolOptions.CHANGE_PAINT_TYPE.readFromNBTBool(stack.field_77990_d);
        Color toolColour = new Color(this.getToolColour(stack));
        PaintType paintType = this.getToolPaintType(stack);
        float[] toolhsb = Color.RGBtoHSB(toolColour.getRed(), toolColour.getGreen(), toolColour.getBlue(), null);
        IPantableBlock worldColourable = (IPantableBlock)block;
        if (worldColourable.isRemoteOnly((IBlockAccess)world, bl.x, bl.y, bl.z, side) & world.field_72995_K) {
            int oldColour = worldColourable.getColour((IBlockAccess)world, bl.x, bl.y, bl.z, side);
            byte oldPaintType = (byte)worldColourable.getPaintType((IBlockAccess)world, bl.x, bl.y, bl.z, side).getKey();
            Color blockColour = new Color(oldColour);
            float[] blockhsb = Color.RGBtoHSB(blockColour.getRed(), blockColour.getGreen(), blockColour.getBlue(), null);
            float[] recolour = new float[]{blockhsb[0], blockhsb[1], blockhsb[2]};
            if (changeHue) {
                recolour[0] = toolhsb[0];
            }
            if (changeSaturation) {
                recolour[1] = toolhsb[1];
            }
            if (changeBrightness) {
                recolour[2] = toolhsb[2];
            }
            int newColour = Color.HSBtoRGB(recolour[0], recolour[1], recolour[2]);
            Color c = new Color(newColour);
            byte[] rgbt = new byte[]{(byte)c.getRed(), (byte)c.getGreen(), (byte)c.getBlue(), oldPaintType};
            if (changePaintType) {
                rgbt[3] = (byte)paintType.getKey();
            }
            MessageClientToolPaintBlock message = new MessageClientToolPaintBlock(bl.x, bl.y, bl.z, (byte)side, rgbt);
            PacketHandler.networkWrapper.sendToServer((IMessage)message);
        } else if (!worldColourable.isRemoteOnly((IBlockAccess)world, bl.x, bl.y, bl.z, side) & !world.field_72995_K) {
            int oldColour = worldColourable.getColour((IBlockAccess)world, bl.x, bl.y, bl.z, side);
            byte oldPaintType = (byte)worldColourable.getPaintType((IBlockAccess)world, bl.x, bl.y, bl.z, side).getKey();
            Color blockColour = new Color(oldColour);
            float[] blockhsb = Color.RGBtoHSB(blockColour.getRed(), blockColour.getGreen(), blockColour.getBlue(), null);
            float[] recolour = new float[]{blockhsb[0], blockhsb[1], blockhsb[2]};
            if (changeHue) {
                recolour[0] = toolhsb[0];
            }
            if (changeSaturation) {
                recolour[1] = toolhsb[1];
            }
            if (changeBrightness) {
                recolour[2] = toolhsb[2];
            }
            int newColour = Color.HSBtoRGB(recolour[0], recolour[1], recolour[2]);
            UndoManager.blockPainted(player, world, bl.x, bl.y, bl.z, oldColour, oldPaintType, side);
            ((IPantableBlock)block).setColour((IBlockAccess)world, bl.x, bl.y, bl.z, newColour, side);
            if (changePaintType) {
                ((IPantableBlock)block).setPaintType((IBlockAccess)world, bl.x, bl.y, bl.z, paintType, side);
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
        String hex = String.format("#%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());
        String colourText = TranslateUtils.translate("item.armourersworkshop:rollover.colour", c.getRGB());
        String hexText = TranslateUtils.translate("item.armourersworkshop:rollover.hex", hex);
        String paintText = TranslateUtils.translate("item.armourersworkshop:rollover.paintType", paintType.getLocalizedName());
        list.add(colourText);
        list.add(hexText);
        list.add(paintText);
        list.add(TranslateUtils.translate("item.armourersworkshop:rollover.openSettings"));
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        if (pass == 0) {
            return this.field_77791_bV;
        }
        return this.tipIcon;
    }

    @Override
    public void getToolOptions(ArrayList<AbstractToolOption> toolOptionList) {
        toolOptionList.add(ToolOptions.CHANGE_HUE);
        toolOptionList.add(ToolOptions.CHANGE_SATURATION);
        toolOptionList.add(ToolOptions.CHANGE_BRIGHTNESS);
        toolOptionList.add(ToolOptions.CHANGE_PAINT_TYPE);
        toolOptionList.add(ToolOptions.FULL_BLOCK_MODE);
    }
}

