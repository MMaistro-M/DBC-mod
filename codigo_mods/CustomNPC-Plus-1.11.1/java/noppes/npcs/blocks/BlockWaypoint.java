/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package noppes.npcs.blocks;

import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.packets.data.gui.GuiWaypointPacket;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.blocks.tiles.TileWaypoint;

public class BlockWaypoint
extends BlockContainer {
    public BlockWaypoint() {
        super(Material.field_151573_f);
        this.func_149647_a(CustomItems.tab);
    }

    public boolean func_149727_a(World par1World, int i, int j, int k, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (par1World.field_72995_K) {
            return false;
        }
        ItemStack currentItem = player.field_71071_by.func_70448_g();
        if (currentItem != null && currentItem.func_77973_b() == CustomItems.wand && CustomNpcsPermissions.hasPermission(player, CustomNpcsPermissions.EDIT_WAYPOINT)) {
            TileEntity tile = par1World.func_147438_o(i, j, k);
            NBTTagCompound compound = new NBTTagCompound();
            tile.func_145841_b(compound);
            PacketHandler.Instance.sendToPlayer(new GuiWaypointPacket(compound), (EntityPlayerMP)player);
            return true;
        }
        return false;
    }

    public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack item) {
        if (entityliving instanceof EntityPlayer && !world.field_72995_K) {
            TileEntity tile = world.func_147438_o(i, j, k);
            NBTTagCompound compound = new NBTTagCompound();
            tile.func_145841_b(compound);
            PacketHandler.Instance.sendToPlayer(new GuiWaypointPacket(compound), (EntityPlayerMP)entityliving);
        }
    }

    public TileEntity func_149915_a(World var1, int var2) {
        return new TileWaypoint();
    }
}

