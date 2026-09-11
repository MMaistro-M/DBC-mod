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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.api.common.painting.IPantableBlock;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.blocks.BlockLocation;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.items.AbstractModItem;
import riskyken.armourersWorkshop.common.lib.LibSounds;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientToolPaintBlock;
import riskyken.armourersWorkshop.common.painting.IBlockPainter;
import riskyken.armourersWorkshop.common.painting.PaintType;
import riskyken.armourersWorkshop.common.painting.tool.AbstractToolOption;
import riskyken.armourersWorkshop.common.painting.tool.IConfigurableTool;
import riskyken.armourersWorkshop.common.painting.tool.ToolOptions;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourer;
import riskyken.armourersWorkshop.common.undo.UndoManager;
import riskyken.armourersWorkshop.utils.TranslateUtils;
import riskyken.armourersWorkshop.utils.UtilColour;
import riskyken.armourersWorkshop.utils.UtilItems;

public class ItemDodgeTool
extends AbstractModItem
implements IConfigurableTool,
IBlockPainter {
    public ItemDodgeTool() {
        super("dodgeTool");
        this.setSortPriority(17);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister register) {
        this.field_77791_bV = register.func_94245_a(LibItemResources.DODGE_TOOL);
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        Block block = world.func_147439_a(x, y, z);
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
                world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, LibSounds.DODGE, 1.0f, 1.0f);
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
        int intensity = (Integer)ToolOptions.INTENSITY.readFromNBT(stack.func_77978_p());
        IPantableBlock worldColourable = (IPantableBlock)block;
        if (worldColourable.isRemoteOnly((IBlockAccess)world, bl.x, bl.y, bl.z, side) & world.field_72995_K) {
            byte[] rgbt = new byte[4];
            int oldColour = worldColourable.getColour((IBlockAccess)world, bl.x, bl.y, bl.z, side);
            PaintType oldPaintType = worldColourable.getPaintType((IBlockAccess)world, bl.x, bl.y, bl.z, side);
            Color c = UtilColour.makeColourBighter(new Color(oldColour), intensity);
            rgbt[0] = (byte)c.getRed();
            rgbt[1] = (byte)c.getGreen();
            rgbt[2] = (byte)c.getBlue();
            rgbt[3] = (byte)oldPaintType.getKey();
            if (block == ModBlocks.boundingBox && oldPaintType == PaintType.NONE) {
                rgbt[3] = (byte)PaintType.NORMAL.getKey();
            }
            MessageClientToolPaintBlock message = new MessageClientToolPaintBlock(bl.x, bl.y, bl.z, (byte)side, rgbt);
            PacketHandler.networkWrapper.sendToServer((IMessage)message);
        } else if (!worldColourable.isRemoteOnly((IBlockAccess)world, bl.x, bl.y, bl.z, side) & !world.field_72995_K) {
            int oldColour = worldColourable.getColour((IBlockAccess)world, bl.x, bl.y, bl.z, side);
            byte oldPaintType = (byte)worldColourable.getPaintType((IBlockAccess)world, bl.x, bl.y, bl.z, side).getKey();
            int newColour = UtilColour.makeColourBighter(new Color(oldColour), intensity).getRGB();
            UndoManager.blockPainted(player, world, bl.x, bl.y, bl.z, oldColour, oldPaintType, side);
            ((IPantableBlock)block).setColour((IBlockAccess)world, bl.x, bl.y, bl.z, newColour, side);
        }
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (world.field_72995_K & player.func_70093_af()) {
            player.openGui((Object)ArmourersWorkshop.instance, 3, world, 0, 0, 0);
        }
        return stack;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        super.func_77624_a(stack, player, list, p_77624_4_);
        int intensity = UtilItems.getIntensityFromStack(stack, 16);
        String rollover = TranslateUtils.translate("item.armourersworkshop:rollover.intensity", intensity);
        list.add(rollover);
        list.add(TranslateUtils.translate("item.armourersworkshop:rollover.openSettings"));
    }

    @Override
    public void getToolOptions(ArrayList<AbstractToolOption> toolOptionList) {
        toolOptionList.add(ToolOptions.FULL_BLOCK_MODE);
        toolOptionList.add(ToolOptions.INTENSITY);
    }
}

