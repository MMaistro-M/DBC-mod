/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package noppes.npcs.blocks;

import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.packets.data.gui.GuiRedstonePacket;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcs;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.blocks.tiles.TileRedstoneBlock;
import noppes.npcs.constants.EnumGuiType;

public class BlockNpcRedstone
extends BlockContainer {
    public BlockNpcRedstone() {
        super(Material.field_151576_e);
    }

    public boolean func_149727_a(World par1World, int i, int j, int k, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (par1World.field_72995_K) {
            return false;
        }
        ItemStack currentItem = player.field_71071_by.func_70448_g();
        if (currentItem != null && currentItem.func_77973_b() == CustomItems.wand && CustomNpcsPermissions.hasPermission(player, CustomNpcsPermissions.EDIT_REDSTONE)) {
            TileEntity tile = par1World.func_147438_o(i, j, k);
            NBTTagCompound compound = new NBTTagCompound();
            tile.func_145841_b(compound);
            PacketHandler.Instance.sendToPlayer(new GuiRedstonePacket(compound), (EntityPlayerMP)player);
            return true;
        }
        return false;
    }

    public void func_149726_b(World par1World, int par2, int par3, int par4) {
        par1World.func_147459_d(par2, par3, par4, (Block)this);
        par1World.func_147459_d(par2, par3 - 1, par4, (Block)this);
        par1World.func_147459_d(par2, par3 + 1, par4, (Block)this);
        par1World.func_147459_d(par2 - 1, par3, par4, (Block)this);
        par1World.func_147459_d(par2 + 1, par3, par4, (Block)this);
        par1World.func_147459_d(par2, par3, par4 - 1, (Block)this);
        par1World.func_147459_d(par2, par3, par4 + 1, (Block)this);
    }

    public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack item) {
        if (entityliving instanceof EntityPlayer && world.field_72995_K) {
            CustomNpcs.proxy.openGui(i, j, k, EnumGuiType.RedstoneBlock, (EntityPlayer)entityliving);
        }
    }

    public void func_149664_b(World par1World, int par2, int par3, int par4, int par5) {
        this.func_149726_b(par1World, par2, par3, par4);
    }

    public int func_149720_d(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        if (this.isActivated(par1IBlockAccess, par2, par3, par4) > 0) {
            return 16739176;
        }
        return super.func_149720_d(par1IBlockAccess, par2, par3, par4);
    }

    public int func_149709_b(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return this.isActivated(par1IBlockAccess, par2, par3, par4);
    }

    public int func_149748_c(IBlockAccess par1World, int par2, int par3, int par4, int par5) {
        return this.isActivated(par1World, par2, par3, par4);
    }

    public boolean func_149744_f() {
        return true;
    }

    public int isActivated(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        return par1IBlockAccess.func_72805_g(par2, par3, par4) == 1 ? 15 : 0;
    }

    public TileEntity func_149915_a(World var1, int var2) {
        return new TileRedstoneBlock();
    }
}

