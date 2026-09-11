/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.DrawBlockHighlightEvent
 *  net.minecraftforge.common.MinecraftForge
 *  org.lwjgl.opengl.GL11
 */
package riskyken.armourersWorkshop.common.items.paintingtool;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.api.common.painting.IPantableBlock;
import riskyken.armourersWorkshop.api.common.skin.cubes.ICubeColour;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.blocks.BlockColourable;
import riskyken.armourersWorkshop.common.blocks.BlockLocation;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.items.AbstractModItem;
import riskyken.armourersWorkshop.common.lib.LibSounds;
import riskyken.armourersWorkshop.common.painting.IBlockPainter;
import riskyken.armourersWorkshop.common.painting.tool.AbstractToolOption;
import riskyken.armourersWorkshop.common.painting.tool.IConfigurableTool;
import riskyken.armourersWorkshop.common.painting.tool.ToolOptions;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourer;
import riskyken.armourersWorkshop.common.undo.UndoManager;
import riskyken.armourersWorkshop.utils.BlockUtils;
import riskyken.armourersWorkshop.utils.TranslateUtils;
import riskyken.armourersWorkshop.utils.UtilItems;

public class ItemBlendingTool
extends AbstractModItem
implements IConfigurableTool,
IBlockPainter {
    public ItemBlendingTool() {
        super("blendingTool");
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.setSortPriority(14);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister register) {
        this.field_77791_bV = register.func_94245_a(LibItemResources.BLEND_TOOL);
    }

    @SubscribeEvent
    @SideOnly(value=Side.CLIENT)
    public void onDrawBlockHighlightEvent(DrawBlockHighlightEvent event) {
        int i;
        EntityPlayer player = event.player;
        World world = event.player.field_70170_p;
        MovingObjectPosition target = event.target;
        if (target != null && target.field_72313_a != MovingObjectPosition.MovingObjectType.BLOCK) {
            return;
        }
        int x = target.field_72311_b;
        int y = target.field_72312_c;
        int z = target.field_72309_d;
        int side = target.field_72310_e;
        Block block = world.func_147439_a(x, y, z);
        ItemStack stack = player.func_71045_bC();
        if (stack == null || stack.func_77973_b() != this) {
            return;
        }
        if (!(block instanceof BlockColourable)) {
            return;
        }
        int radiusSample = (Integer)ToolOptions.RADIUS_SAMPLE.readFromNBT(stack.func_77978_p(), 2);
        int radiusEffect = (Integer)ToolOptions.RADIUS_EFFECT.readFromNBT(stack.func_77978_p(), 1);
        ArrayList<BlockLocation> blockSamples = BlockUtils.findTouchingBlockFaces(world, x, y, z, side, radiusSample);
        ArrayList<BlockLocation> blockEffects = BlockUtils.findTouchingBlockFaces(world, x, y, z, side, radiusEffect);
        double xOff = player.field_70142_S + (player.field_70165_t - player.field_70142_S) * (double)event.partialTicks;
        double yOff = player.field_70137_T + (player.field_70163_u - player.field_70137_T) * (double)event.partialTicks;
        double zOff = player.field_70136_U + (player.field_70161_v - player.field_70136_U) * (double)event.partialTicks;
        float f1 = 0.002f;
        for (i = 0; i < blockSamples.size(); ++i) {
            int colour = 0xFF0000;
            BlockLocation blockLoc = blockSamples.get(i);
            AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)blockLoc.x, (double)blockLoc.y, (double)blockLoc.z, (double)(blockLoc.x + 1), (double)(blockLoc.y + 1), (double)(blockLoc.z + 1));
            aabb.func_72317_d(-xOff, -yOff, -zOff);
            GL11.glEnable((int)3042);
            OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
            GL11.glColor4f((float)0.0f, (float)0.0f, (float)0.0f, (float)0.2f);
            GL11.glLineWidth((float)2.0f);
            GL11.glDisable((int)3553);
            GL11.glDepthMask((boolean)false);
            GL11.glDisable((int)2929);
            RenderGlobal.func_147590_a((AxisAlignedBB)aabb.func_72314_b((double)f1, (double)f1, (double)f1), (int)colour);
            GL11.glEnable((int)2929);
            GL11.glDepthMask((boolean)true);
            GL11.glEnable((int)3553);
            GL11.glDisable((int)3042);
        }
        for (i = 0; i < blockEffects.size(); ++i) {
            BlockLocation blockLoc = blockEffects.get(i);
            AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)((float)blockLoc.x + 0.1f), (double)((float)blockLoc.y + 0.1f), (double)((float)blockLoc.z + 0.1f), (double)((float)blockLoc.x + 0.9f), (double)((float)blockLoc.y + 0.9f), (double)((float)blockLoc.z + 0.9f));
            aabb.func_72317_d(-xOff, -yOff, -zOff);
            GL11.glEnable((int)3042);
            OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
            GL11.glColor4f((float)0.0f, (float)0.0f, (float)0.0f, (float)0.2f);
            GL11.glLineWidth((float)2.0f);
            GL11.glDisable((int)3553);
            GL11.glDisable((int)2929);
            RenderGlobal.func_147590_a((AxisAlignedBB)aabb.func_72314_b((double)f1, (double)f1, (double)f1), (int)65280);
            GL11.glEnable((int)2929);
            GL11.glEnable((int)3553);
            GL11.glDisable((int)3042);
        }
        event.setCanceled(true);
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        Block block = world.func_147439_a(x, y, z);
        if (block instanceof IPantableBlock) {
            if (!world.field_72995_K) {
                UndoManager.begin(player);
                this.usedOnBlockSide(stack, player, world, new BlockLocation(x, y, z), block, side);
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
        Block tarBlock;
        BlockLocation loc;
        int i;
        int intensity = UtilItems.getIntensityFromStack(stack, 16);
        int radiusSample = (Integer)ToolOptions.RADIUS_SAMPLE.readFromNBT(stack.func_77978_p(), 2);
        int radiusEffect = (Integer)ToolOptions.RADIUS_EFFECT.readFromNBT(stack.func_77978_p(), 1);
        ArrayList<BlockLocation> blockSamples = BlockUtils.findTouchingBlockFaces(world, bl.x, bl.y, bl.z, side, radiusSample);
        ArrayList<BlockLocation> blockEffects = BlockUtils.findTouchingBlockFaces(world, bl.x, bl.y, bl.z, side, radiusEffect);
        if (blockSamples.size() == 0 | blockEffects.size() == 0) {
            return;
        }
        int r = 0;
        int g = 0;
        int b = 0;
        for (i = 0; i < blockSamples.size(); ++i) {
            loc = blockSamples.get(i);
            tarBlock = world.func_147439_a(loc.x, loc.y, loc.z);
            if (!(tarBlock instanceof IPantableBlock)) continue;
            IPantableBlock pBlock = (IPantableBlock)tarBlock;
            ICubeColour c = pBlock.getColour((IBlockAccess)world, loc.x, loc.y, loc.z);
            r += c.getRed(side) & 0xFF;
            g += c.getGreen(side) & 0xFF;
            b += c.getBlue(side) & 0xFF;
        }
        r /= blockSamples.size();
        g /= blockSamples.size();
        b /= blockSamples.size();
        for (i = 0; i < blockEffects.size(); ++i) {
            loc = blockEffects.get(i);
            tarBlock = world.func_147439_a(loc.x, loc.y, loc.z);
            if (!(tarBlock instanceof IPantableBlock)) continue;
            IPantableBlock worldColourable = (IPantableBlock)tarBlock;
            int oldColour = worldColourable.getColour((IBlockAccess)world, loc.x, loc.y, loc.z, side);
            byte oldPaintType = (byte)worldColourable.getPaintType((IBlockAccess)world, loc.x, loc.y, loc.z, side).getKey();
            Color oldC = new Color(oldColour);
            int oldR = oldC.getRed();
            int oldG = oldC.getGreen();
            int oldB = oldC.getBlue();
            float newR = (float)r / 100.0f * (float)intensity;
            newR += (float)oldR / 100.0f * (float)(100 - intensity);
            newR = MathHelper.func_76125_a((int)((int)newR), (int)0, (int)255);
            float newG = (float)g / 100.0f * (float)intensity;
            newG += (float)oldG / 100.0f * (float)(100 - intensity);
            newG = MathHelper.func_76125_a((int)((int)newG), (int)0, (int)255);
            float newB = (float)b / 100.0f * (float)intensity;
            newB += (float)oldB / 100.0f * (float)(100 - intensity);
            newB = MathHelper.func_76125_a((int)((int)newB), (int)0, (int)255);
            Color newC = new Color((int)newR, (int)newG, (int)newB);
            UndoManager.blockPainted(player, world, loc.x, loc.y, loc.z, oldColour, oldPaintType, side);
            ((IPantableBlock)block).setColour((IBlockAccess)world, loc.x, loc.y, loc.z, newC.getRGB(), side);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean p_77624_4_) {
        super.func_77624_a(stack, player, list, p_77624_4_);
        int intensity = UtilItems.getIntensityFromStack(stack, 16);
        int radiusSample = (Integer)ToolOptions.RADIUS_SAMPLE.readFromNBT(stack.func_77978_p(), 2);
        int radiusEffect = (Integer)ToolOptions.RADIUS_EFFECT.readFromNBT(stack.func_77978_p(), 1);
        list.add(TranslateUtils.translate("item.armourersworkshop:rollover.intensity", intensity));
        list.add(TranslateUtils.translate("item.armourersworkshop:rollover.radius", radiusSample, radiusSample, 1));
        list.add(TranslateUtils.translate("item.armourersworkshop:rollover.radius", radiusEffect, radiusEffect, 1));
        list.add(TranslateUtils.translate("item.armourersworkshop:rollover.openSettings"));
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (world.field_72995_K & player.func_70093_af()) {
            player.openGui((Object)ArmourersWorkshop.instance, 3, world, 0, 0, 0);
        }
        return stack;
    }

    @Override
    public void getToolOptions(ArrayList<AbstractToolOption> toolOptionList) {
        toolOptionList.add(ToolOptions.INTENSITY);
        toolOptionList.add(ToolOptions.RADIUS_SAMPLE);
        toolOptionList.add(ToolOptions.RADIUS_EFFECT);
    }
}

