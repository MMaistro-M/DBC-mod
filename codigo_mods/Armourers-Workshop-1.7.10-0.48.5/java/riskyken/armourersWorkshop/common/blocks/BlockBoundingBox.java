/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.particle.EffectRenderer
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package riskyken.armourersWorkshop.common.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import riskyken.armourersWorkshop.api.common.painting.IPantableBlock;
import riskyken.armourersWorkshop.api.common.skin.cubes.ICubeColour;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinPartTypeTextured;
import riskyken.armourersWorkshop.api.common.skin.type.ISkinType;
import riskyken.armourersWorkshop.client.lib.LibBlockResources;
import riskyken.armourersWorkshop.client.texture.PlayerTexture;
import riskyken.armourersWorkshop.common.SkinHelper;
import riskyken.armourersWorkshop.common.blocks.AbstractModBlockContainer;
import riskyken.armourersWorkshop.common.painting.PaintType;
import riskyken.armourersWorkshop.common.skin.SkinTextureHelper;
import riskyken.armourersWorkshop.common.tileentities.TileEntityArmourer;
import riskyken.armourersWorkshop.common.tileentities.TileEntityBoundingBox;
import riskyken.armourersWorkshop.proxies.ClientProxy;
import riskyken.armourersWorkshop.utils.BitwiseUtils;

public class BlockBoundingBox
extends AbstractModBlockContainer
implements IPantableBlock {
    protected BlockBoundingBox() {
        super("awBoundingBox6", Material.field_151580_n, field_149775_l, false);
        this.func_149722_s();
        this.func_149752_b(6000000.0f);
        this.func_149713_g(0);
    }

    public void func_149666_a(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_) {
    }

    public boolean func_149659_a(Explosion explosion) {
        return false;
    }

    public void func_149689_a(World world, int x, int y, int z, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
        world.func_147468_f(x, y, z);
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return null;
    }

    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z, boolean willHarvest) {
        if (world.field_72995_K) {
            return true;
        }
        TileEntity tileEntity = world.func_147438_o(x, y, z);
        if (tileEntity != null && tileEntity instanceof TileEntityBoundingBox) {
            if (((TileEntityBoundingBox)tileEntity).isParentValid()) {
                tileEntity.func_70296_d();
                world.func_147471_g(x, y, z);
                return false;
            }
            world.func_147468_f(x, y, z);
            return true;
        }
        world.func_147468_f(x, y, z);
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister register) {
        this.field_149761_L = register.func_94245_a(LibBlockResources.COLOURABLE);
    }

    public int func_149645_b() {
        return -1;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public Block func_149663_c(String name) {
        GameRegistry.registerBlock((Block)this, (String)("block." + name));
        return super.func_149663_c(name);
    }

    @Override
    public String func_149739_a() {
        return this.getModdedUnlocalizedName(super.func_149739_a());
    }

    @Override
    protected String getModdedUnlocalizedName(String unlocalizedName) {
        String name = unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
        return "tile." + "armourersWorkshop".toLowerCase() + ":" + name;
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        return null;
    }

    public TileEntity func_149915_a(World world, int p_149915_2_) {
        return new TileEntityBoundingBox();
    }

    @Override
    public boolean setColour(IBlockAccess world, int x, int y, int z, int colour, int side) {
        ForgeDirection sideBlock = ForgeDirection.getOrientation((int)side);
        if (world.func_147439_a(x + sideBlock.offsetX, y + sideBlock.offsetY, z + sideBlock.offsetZ) == this) {
            return false;
        }
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null && te instanceof TileEntityBoundingBox) {
            TileEntityArmourer parent = ((TileEntityBoundingBox)te).getParent();
            if (((TileEntityBoundingBox)te).getSkinPart() instanceof ISkinPartTypeTextured && parent != null) {
                ISkinType skinType = parent.getSkinType();
                Point texturePoint = SkinTextureHelper.getTextureLocationFromWorldBlock((TileEntityBoundingBox)te, side);
                int oldColour = parent.getPaintData(texturePoint.x, texturePoint.y);
                int paintType = BitwiseUtils.getUByteFromInt(oldColour, 0);
                int newColour = BitwiseUtils.setUByteToInt(colour, 0, paintType);
                parent.updatePaintData(texturePoint.x, texturePoint.y, newColour);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setColour(IBlockAccess world, int x, int y, int z, byte[] rgb, int side) {
        int colour = new Color(rgb[0] & 0xFF, rgb[1] & 0xFF, rgb[2] & 0xFF).getRGB();
        return this.setColour(world, x, y, z, colour, side);
    }

    @Override
    public int getColour(IBlockAccess world, int x, int y, int z, int side) {
        TileEntityArmourer parent;
        ForgeDirection sideBlock = ForgeDirection.getOrientation((int)side);
        if (world.func_147439_a(x + sideBlock.offsetX, y + sideBlock.offsetY, z + sideBlock.offsetZ) == this) {
            return 0xFFFFFF;
        }
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null && te instanceof TileEntityBoundingBox && (parent = ((TileEntityBoundingBox)te).getParent()) != null && ((TileEntityBoundingBox)te).getSkinPart() instanceof ISkinPartTypeTextured) {
            PlayerTexture playerTexture;
            BufferedImage playerSkin;
            Point texturePoint = SkinTextureHelper.getTextureLocationFromWorldBlock((TileEntityBoundingBox)te, side);
            int colour = parent.getPaintData(texturePoint.x, texturePoint.y);
            int paintType = BitwiseUtils.getUByteFromInt(colour, 0);
            if (paintType != 0) {
                return colour;
            }
            if (te.func_145831_w().field_72995_K && (playerSkin = SkinHelper.getBufferedImageSkin((playerTexture = ClientProxy.playerTextureDownloader.getPlayerTexture(parent.getTexture())).getResourceLocation())) != null) {
                colour = playerSkin.getRGB(texturePoint.x, texturePoint.y);
                return colour;
            }
        }
        return 0xFFFFFF;
    }

    @Override
    public boolean isRemoteOnly(IBlockAccess world, int x, int y, int z, int side) {
        TileEntityArmourer parent;
        ForgeDirection sideBlock = ForgeDirection.getOrientation((int)side);
        if (world.func_147439_a(x + sideBlock.offsetX, y + sideBlock.offsetY, z + sideBlock.offsetZ) == this) {
            return false;
        }
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null && te instanceof TileEntityBoundingBox && (parent = ((TileEntityBoundingBox)te).getParent()) != null && ((TileEntityBoundingBox)te).getSkinPart() instanceof ISkinPartTypeTextured) {
            Point texturePoint = SkinTextureHelper.getTextureLocationFromWorldBlock((TileEntityBoundingBox)te, side);
            int colour = parent.getPaintData(texturePoint.x, texturePoint.y);
            int paintType = BitwiseUtils.getUByteFromInt(colour, 0);
            return paintType == 0;
        }
        return false;
    }

    @Override
    public void setPaintType(IBlockAccess world, int x, int y, int z, PaintType paintType, int side) {
        ForgeDirection sideBlock = ForgeDirection.getOrientation((int)side);
        if (world.func_147439_a(x + sideBlock.offsetX, y + sideBlock.offsetY, z + sideBlock.offsetZ) == this) {
            return;
        }
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null && te instanceof TileEntityBoundingBox) {
            TileEntityArmourer parent = ((TileEntityBoundingBox)te).getParent();
            if (((TileEntityBoundingBox)te).getSkinPart() instanceof ISkinPartTypeTextured && parent != null) {
                ISkinType skinType = parent.getSkinType();
                Point texturePoint = SkinTextureHelper.getTextureLocationFromWorldBlock((TileEntityBoundingBox)te, side);
                int oldColour = parent.getPaintData(texturePoint.x, texturePoint.y);
                int newColour = PaintType.setPaintTypeOnColour(paintType, oldColour);
                parent.updatePaintData(texturePoint.x, texturePoint.y, newColour);
            }
        }
    }

    @Override
    public PaintType getPaintType(IBlockAccess world, int x, int y, int z, int side) {
        TileEntityArmourer parent;
        ForgeDirection sideBlock = ForgeDirection.getOrientation((int)side);
        if (world.func_147439_a(x + sideBlock.offsetX, y + sideBlock.offsetY, z + sideBlock.offsetZ) == this) {
            return PaintType.NORMAL;
        }
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null && te instanceof TileEntityBoundingBox && (parent = ((TileEntityBoundingBox)te).getParent()) != null && ((TileEntityBoundingBox)te).getSkinPart() instanceof ISkinPartTypeTextured) {
            Point texturePoint = SkinTextureHelper.getTextureLocationFromWorldBlock((TileEntityBoundingBox)te, side);
            int colour = parent.getPaintData(texturePoint.x, texturePoint.y);
            return PaintType.getPaintTypeFromColour(colour);
        }
        return PaintType.NORMAL;
    }

    @Override
    public ICubeColour getColour(IBlockAccess world, int x, int y, int z) {
        return null;
    }
}

