/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.common.items.paintingtool;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
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

public class ItemPaintballGun
extends AbstractModItem
implements IPaintingTool {
    @SideOnly(value=Side.CLIENT)
    private IIcon tipIcon;

    public ItemPaintballGun() {
        super("paintballGun");
        this.setSortPriority(15);
    }

    public ItemStack func_77659_a(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        world.func_72956_a((Entity)entityPlayer, "random.bow", 0.5f, 0.4f / (field_77697_d.nextFloat() * 0.4f + 0.8f));
        return itemStack;
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

    @SideOnly(value=Side.CLIENT)
    public boolean hasEffect(ItemStack stack, int pass) {
        PaintType paintType = PaintingHelper.getToolPaintType(stack);
        return paintType != PaintType.NORMAL;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister register) {
        this.field_77791_bV = register.func_94245_a(LibItemResources.PAINTBALL_GUN);
        this.tipIcon = register.func_94245_a(LibItemResources.PAINTBALL_GUN_TIP);
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        if (pass == 0) {
            return this.field_77791_bV;
        }
        return this.tipIcon;
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

