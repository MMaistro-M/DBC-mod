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
 *  net.minecraft.nbt.NBTTagCompound
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.api.common.painting.IPaintingTool;
import riskyken.armourersWorkshop.api.common.painting.IPantable;
import riskyken.armourersWorkshop.api.common.painting.IPantableBlock;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.items.AbstractModItem;
import riskyken.armourersWorkshop.common.lib.LibSounds;
import riskyken.armourersWorkshop.common.network.PacketHandler;
import riskyken.armourersWorkshop.common.network.messages.client.MessageClientGuiToolOptionUpdate;
import riskyken.armourersWorkshop.common.painting.PaintType;
import riskyken.armourersWorkshop.common.painting.PaintingHelper;
import riskyken.armourersWorkshop.common.painting.tool.AbstractToolOption;
import riskyken.armourersWorkshop.common.painting.tool.IConfigurableTool;
import riskyken.armourersWorkshop.common.painting.tool.ToolOptions;
import riskyken.armourersWorkshop.utils.TranslateUtils;

public class ItemColourPicker
extends AbstractModItem
implements IPaintingTool,
IConfigurableTool {
    @SideOnly(value=Side.CLIENT)
    private IIcon tipIcon;

    public ItemColourPicker() {
        super("colourPicker");
        this.setSortPriority(12);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister register) {
        this.field_77791_bV = register.func_94245_a(LibItemResources.COLOUR_PICKER);
        this.tipIcon = register.func_94245_a(LibItemResources.COLOUR_PICKER_TIP);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean hasEffect(ItemStack stack, int pass) {
        PaintType paintType = PaintingHelper.getToolPaintType(stack);
        return paintType != PaintType.NORMAL;
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        Block block = world.func_147439_a(x, y, z);
        boolean changePaintType = ToolOptions.CHANGE_PAINT_TYPE.readFromNBTBool(stack.field_77990_d);
        PaintType paintType = this.getToolPaintType(stack);
        if (player.func_70093_af() & block == ModBlocks.colourMixer & this.getToolHasColour(stack)) {
            TileEntity te = world.func_147438_o(x, y, z);
            if (te != null && te instanceof IPantable && !world.field_72995_K) {
                int colour = this.getToolColour(stack);
                ((IPantable)te).setColour(colour);
                ((IPantable)te).setPaintType(paintType, 0);
            }
            return true;
        }
        if (block instanceof IPantableBlock) {
            IPantableBlock paintable = (IPantableBlock)block;
            PaintType targetPaintType = paintable.getPaintType((IBlockAccess)world, x, y, z, side);
            if (paintable.isRemoteOnly((IBlockAccess)world, x, y, z, side) & world.field_72995_K) {
                int colour = paintable.getColour((IBlockAccess)world, x, y, z, side);
                NBTTagCompound compound = new NBTTagCompound();
                byte[] paintData = new byte[4];
                Color c = new Color(colour);
                paintData[0] = (byte)c.getRed();
                paintData[1] = (byte)c.getGreen();
                paintData[2] = (byte)c.getBlue();
                paintData[3] = changePaintType ? (byte)targetPaintType.getKey() : (byte)paintType.getKey();
                PaintingHelper.setPaintData(compound, paintData);
                PacketHandler.networkWrapper.sendToServer((IMessage)new MessageClientGuiToolOptionUpdate(compound));
            } else if (!paintable.isRemoteOnly((IBlockAccess)world, x, y, z, side) & !world.field_72995_K) {
                this.setToolColour(stack, ((IPantableBlock)block).getColour((IBlockAccess)world, x, y, z, side));
                if (changePaintType) {
                    this.setToolPaintType(stack, targetPaintType);
                } else {
                    this.setToolPaintType(stack, paintType);
                }
            }
            if (!world.field_72995_K) {
                world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, LibSounds.PICKER, 1.0f, world.field_73012_v.nextFloat() * 0.1f + 0.9f);
            }
            return true;
        }
        return false;
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
            String noPaint = TranslateUtils.translate("item.armourersworkshop:rollover.nopaint");
            list.add(noPaint);
        }
    }

    public boolean func_77623_v() {
        return true;
    }

    public int getRenderPasses(int metadata) {
        return 2;
    }

    public int func_82790_a(ItemStack stack, int pass) {
        if (!this.getToolHasColour(stack)) {
            return super.func_82790_a(stack, pass);
        }
        if (pass == 0) {
            return super.func_82790_a(stack, pass);
        }
        return this.getToolColour(stack);
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        if (!this.getToolHasColour(stack)) {
            return this.field_77791_bV;
        }
        if (pass == 0) {
            return this.field_77791_bV;
        }
        return this.tipIcon;
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

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (world.field_72995_K & player.func_70093_af()) {
            player.openGui((Object)ArmourersWorkshop.instance, 3, world, 0, 0, 0);
        }
        return stack;
    }

    @Override
    public void getToolOptions(ArrayList<AbstractToolOption> toolOptionList) {
        toolOptionList.add(ToolOptions.CHANGE_PAINT_TYPE);
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

