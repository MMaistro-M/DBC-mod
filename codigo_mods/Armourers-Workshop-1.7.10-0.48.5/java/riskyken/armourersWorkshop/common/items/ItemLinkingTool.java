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
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.client.lib.LibItemResources;
import riskyken.armourersWorkshop.common.blocks.BlockLocation;
import riskyken.armourersWorkshop.common.blocks.BlockSkinnable;
import riskyken.armourersWorkshop.common.items.AbstractModItem;
import riskyken.armourersWorkshop.common.tileentities.TileEntitySkinnable;

public class ItemLinkingTool
extends AbstractModItem {
    private static final String TAG_LINK_LOCATION = "linkLocation";
    @SideOnly(value=Side.CLIENT)
    private IIcon linkIcon;

    public ItemLinkingTool() {
        super("linkingTool");
        this.setSortPriority(7);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister iconRegister) {
        this.field_77791_bV = iconRegister.func_94245_a(LibItemResources.LINKING_TOOL);
        this.linkIcon = iconRegister.func_94245_a(LibItemResources.LINKING_TOOL_LINK);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77650_f(ItemStack stack) {
        if (this.hasLinkLocation(stack)) {
            return this.linkIcon;
        }
        return this.field_77791_bV;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(ItemStack stack, int pass) {
        if (this.hasLinkLocation(stack)) {
            return this.linkIcon;
        }
        return this.field_77791_bV;
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (world.field_72995_K) {
            return false;
        }
        if (!world.field_72995_K) {
            TileEntity te;
            if (!this.hasLinkLocation(stack)) {
                Block block = world.func_147439_a(x, y, z);
                if (!(block instanceof BlockSkinnable)) {
                    this.setLinkLocation(stack, new BlockLocation(x, y, z));
                    player.func_145747_a((IChatComponent)new ChatComponentTranslation("chat.armourersworkshop:linkingTool.start", new Object[]{null}));
                    return true;
                }
                player.func_145747_a((IChatComponent)new ChatComponentTranslation("chat.armourersworkshop:linkingTool.linkedToSkinnable", new Object[]{null}));
                return true;
            }
            BlockLocation loc = this.getLinkLocation(stack);
            Block block = world.func_147439_a(x, y, z);
            if (block instanceof BlockSkinnable && (te = world.func_147438_o(x, y, z)) != null && te instanceof TileEntitySkinnable) {
                ((TileEntitySkinnable)te).getParent().setLinkedBlock(loc);
                player.func_145747_a((IChatComponent)new ChatComponentTranslation("chat.armourersworkshop:linkingTool.finish", new Object[]{null}));
                this.removeLinkLocation(stack);
                return true;
            }
            this.removeLinkLocation(stack);
            player.func_145747_a((IChatComponent)new ChatComponentTranslation("chat.armourersworkshop:linkingTool.fail", new Object[]{null}));
        }
        return true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack itemStack, EntityPlayer player, List list, boolean par4) {
        super.func_77624_a(itemStack, player, list, par4);
    }

    private void setLinkLocation(ItemStack stack, BlockLocation loc) {
        if (!stack.func_77942_o()) {
            stack.func_77982_d(new NBTTagCompound());
        }
        stack.func_77978_p().func_74783_a(TAG_LINK_LOCATION, new int[]{loc.x, loc.y, loc.z});
    }

    private void removeLinkLocation(ItemStack stack) {
        if (stack.func_77942_o()) {
            stack.func_77978_p().func_82580_o(TAG_LINK_LOCATION);
        }
    }

    private boolean hasLinkLocation(ItemStack stack) {
        if (stack.func_77942_o()) {
            return stack.func_77978_p().func_150297_b(TAG_LINK_LOCATION, 11);
        }
        return false;
    }

    private BlockLocation getLinkLocation(ItemStack stack) {
        if (this.hasLinkLocation(stack)) {
            int[] loc = stack.func_77978_p().func_74759_k(TAG_LINK_LOCATION);
            return new BlockLocation(loc[0], loc[1], loc[2]);
        }
        return new BlockLocation(0, 0, 0);
    }
}

