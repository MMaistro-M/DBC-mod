/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package noppes.npcs.items;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import kamkeel.npcs.network.PacketClient;
import kamkeel.npcs.network.packets.player.item.GuiMagicBookPacket;
import kamkeel.npcs.network.packets.player.item.GuiPaintbrushPacket;
import kamkeel.npcs.network.packets.request.item.ColorBrushPacket;
import kamkeel.npcs.network.packets.request.item.ColorSetPacket;
import kamkeel.npcs.network.packets.request.item.HammerPacket;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcs;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.blocks.BlockBanner;
import noppes.npcs.blocks.BlockChair;
import noppes.npcs.blocks.BlockTallLamp;
import noppes.npcs.blocks.BlockWallBanner;
import noppes.npcs.blocks.tiles.TileBanner;
import noppes.npcs.blocks.tiles.TileChair;
import noppes.npcs.blocks.tiles.TileColorable;
import noppes.npcs.constants.EnumGuiType;

public class ItemNpcTool
extends Item {
    public static final String[] toolTypes = new String[]{"hammer", "paintbrush", "magic_book"};
    public static String BRUSH_COLOR_TAG = "BrushColor";

    public ItemNpcTool() {
        this.field_77777_bU = 1;
        this.func_77627_a(true);
        this.func_77656_e(0);
        this.func_77637_a(CustomItems.tab);
        CustomNpcs.proxy.registerItem(this);
    }

    public String func_77667_c(ItemStack stack) {
        int meta = stack.func_77960_j();
        if (meta < 0 || meta >= toolTypes.length) {
            meta = 0;
        }
        return super.func_77658_a() + "." + toolTypes[meta];
    }

    public void func_150895_a(Item item, CreativeTabs tab, List subItems) {
        for (int i = 0; i < toolTypes.length; ++i) {
            subItems.add(new ItemStack(item, 1, i));
        }
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        if (!world.field_72995_K) {
            return stack;
        }
        if (ItemNpcTool.isPaintbrush(stack) && player.func_70093_af()) {
            if (CustomNpcsPermissions.hasPermission(player, CustomNpcsPermissions.PAINTBRUSH_GUI)) {
                PacketClient.sendClient(new GuiPaintbrushPacket(EnumGuiType.Paintbrush.ordinal(), 0, 0, 0));
            }
            return stack;
        }
        if (ItemNpcTool.isMagicBook(stack)) {
            PacketClient.sendClient(new GuiMagicBookPacket(EnumGuiType.MagicBook.ordinal(), 0, 0, 0));
            return stack;
        }
        return stack;
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (!world.field_72995_K) {
            return false;
        }
        if (ItemNpcTool.isPaintbrush(stack)) {
            TileEntity tile;
            int meta;
            Block block = world.func_147439_a(x, y, z);
            if ((block instanceof BlockTallLamp || block instanceof BlockBanner) && (meta = world.func_72805_g(x, y, z)) >= 7) {
                --y;
            }
            if ((tile = world.func_147438_o(x, y, z)) instanceof TileColorable) {
                PacketClient.sendClient(new ColorSetPacket(x, y, z));
                return true;
            }
        } else if (ItemNpcTool.isHammer(stack)) {
            TileEntity tile;
            Block block = player.field_70170_p.func_147439_a(x, y, z);
            if (block instanceof BlockBanner || block instanceof BlockWallBanner) {
                TileEntity tile2;
                int meta = world.func_72805_g(x, y, z);
                if (meta >= 7) {
                    --y;
                }
                if ((tile2 = player.field_70170_p.func_147438_o(x, y, z)) instanceof TileBanner) {
                    PacketClient.sendClient(new HammerPacket(x, y, z));
                    return true;
                }
            } else if (block instanceof BlockChair && (tile = player.field_70170_p.func_147438_o(x, y, z)) instanceof TileChair) {
                PacketClient.sendClient(new HammerPacket(x, y, z));
                return true;
            }
        }
        return false;
    }

    public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer player) {
        if (!player.field_70170_p.field_72995_K) {
            return true;
        }
        if (ItemNpcTool.isPaintbrush(itemstack)) {
            TileEntity tile;
            int meta;
            Block block = player.field_70170_p.func_147439_a(x, y, z);
            if ((block instanceof BlockTallLamp || block instanceof BlockBanner) && (meta = player.field_70170_p.func_72805_g(x, y, z)) >= 7) {
                --y;
            }
            if ((tile = player.field_70170_p.func_147438_o(x, y, z)) instanceof TileColorable) {
                int color = ((TileColorable)tile).color;
                PacketClient.sendClient(new ColorBrushPacket(color));
            }
        }
        return true;
    }

    public static boolean isPaintbrush(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }
        if (itemStack.func_77973_b() == null) {
            return false;
        }
        if (!(itemStack.func_77973_b() instanceof ItemNpcTool)) {
            return false;
        }
        return itemStack.func_77960_j() == 1;
    }

    public static boolean isHammer(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }
        if (itemStack.func_77973_b() == null) {
            return false;
        }
        if (!(itemStack.func_77973_b() instanceof ItemNpcTool)) {
            return false;
        }
        return itemStack.func_77960_j() == 0;
    }

    public static boolean isMagicBook(ItemStack itemStack) {
        if (itemStack == null) {
            return false;
        }
        if (itemStack.func_77973_b() == null) {
            return false;
        }
        if (!(itemStack.func_77973_b() instanceof ItemNpcTool)) {
            return false;
        }
        return itemStack.func_77960_j() == 2;
    }

    public Item func_77655_b(String name) {
        GameRegistry.registerItem((Item)this, (String)name);
        return super.func_77655_b(name);
    }

    public static int getColor(NBTTagCompound tagCompound) {
        if (tagCompound == null || !tagCompound.func_74764_b(BRUSH_COLOR_TAG)) {
            return 0xFFFFFF;
        }
        return tagCompound.func_74762_e(BRUSH_COLOR_TAG);
    }

    public static NBTTagCompound setColor(NBTTagCompound tagCompound, int color) {
        if (tagCompound == null) {
            tagCompound = new NBTTagCompound();
        }
        tagCompound.func_74768_a(BRUSH_COLOR_TAG, color);
        return tagCompound;
    }
}

