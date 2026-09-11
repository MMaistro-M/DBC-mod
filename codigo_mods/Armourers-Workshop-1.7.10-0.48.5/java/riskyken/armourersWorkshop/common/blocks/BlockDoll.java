/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.common.network.internal.FMLNetworkHandler
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.EffectRenderer
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.particle.EntitySpellParticleFX
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTUtil
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package riskyken.armourersWorkshop.common.blocks;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.client.lib.LibBlockResources;
import riskyken.armourersWorkshop.common.Contributors;
import riskyken.armourersWorkshop.common.blocks.AbstractModBlockContainer;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.config.ConfigHandler;
import riskyken.armourersWorkshop.common.items.ModItems;
import riskyken.armourersWorkshop.common.items.block.ModItemBlockNoStack;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMannequin;
import riskyken.armourersWorkshop.utils.BlockUtils;
import riskyken.armourersWorkshop.utils.HolidayHelper;
import riskyken.armourersWorkshop.utils.UtilItems;

public class BlockDoll
extends AbstractModBlockContainer {
    private static final String TAG_OWNER = "owner";
    private final boolean isValentins;

    public BlockDoll() {
        super("doll", Material.field_151576_e, field_149777_j, !ConfigHandler.hideDollFromCreativeTabs);
        this.func_149713_g(0);
        this.func_149676_a(0.2f, 0.0f, 0.2f, 0.8f, 0.95f, 0.8f);
        this.isValentins = HolidayHelper.valentins.isHolidayActive();
        this.setSortPriority(198);
    }

    public Block func_149663_c(String name) {
        GameRegistry.registerBlock((Block)this, ModItemBlockNoStack.class, (String)("block." + name));
        return super.func_149663_c(name);
    }

    public void func_149749_a(World world, int x, int y, int z, Block block, int metadata) {
        if (!world.field_72995_K) {
            TileEntity te = world.func_147438_o(x, y, z);
            if (te != null && te instanceof TileEntityMannequin) {
                ItemStack dropStack = ((TileEntityMannequin)te).getDropStack();
                UtilItems.spawnItemInWorld(world, x, y, z, dropStack);
            }
            BlockUtils.dropInventoryBlocks(world, x, y, z);
        }
        super.func_149749_a(world, x, y, z, block, metadata);
    }

    public void func_149689_a(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null && te instanceof TileEntityMannequin) {
            int l = MathHelper.func_76128_c((double)((double)(player.field_70177_z * 16.0f / 360.0f) + 0.5)) & 0xF;
            ((TileEntityMannequin)te).setRotation(l);
            if (stack.func_77942_o()) {
                NBTTagCompound compound = stack.func_77978_p();
                GameProfile gameProfile = null;
                if (compound.func_150297_b(TAG_OWNER, 10)) {
                    gameProfile = NBTUtil.func_152459_a((NBTTagCompound)compound.func_74775_l(TAG_OWNER));
                    ((TileEntityMannequin)te).setGameProfile(gameProfile);
                }
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random random) {
        Contributors.Contributor contributor;
        TileEntityMannequin te;
        TileEntity tileEntity;
        if (this.isValentins && random.nextFloat() * 100.0f > 80.0f) {
            world.func_72869_a("heart", (double)x + 0.2 + (double)(random.nextFloat() * 0.6f), (double)y + 1.0, (double)z + 0.2 + (double)(random.nextFloat() * 0.6f), 0.0, 0.0, 0.0);
        }
        if ((tileEntity = world.func_147438_o(x, y, z)) != null && tileEntity instanceof TileEntityMannequin && (te = (TileEntityMannequin)tileEntity).isRenderExtras() & te.isVisible() && (contributor = Contributors.INSTANCE.getContributor(te.getGameProfile())) != null) {
            EntitySpellParticleFX entityfx = new EntitySpellParticleFX(world, (double)((float)x + random.nextFloat() * 1.0f), (double)y, (double)((float)z + random.nextFloat() * 1.0f), 0.0, 0.0, 0.0);
            entityfx.func_70589_b(144);
            entityfx.func_70538_b((float)(contributor.r & 0xFF) / 255.0f, (float)(contributor.g & 0xFF) / 255.0f, (float)(contributor.b & 0xFF) / 255.0f);
            Minecraft.func_71410_x().field_71452_i.func_78873_a((EntityFX)entityfx);
        }
    }

    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis) {
        if (world.field_72995_K) {
            return false;
        }
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null && te instanceof TileEntityMannequin) {
            int rotation = ((TileEntityMannequin)te).getRotation();
            if (++rotation > 15) {
                rotation = 0;
            }
            ((TileEntityMannequin)te).setRotation(rotation);
        }
        return true;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        TileEntityMannequin teMan;
        TileEntity te;
        ItemStack stack = new ItemStack(ModBlocks.doll, 1);
        int meta = world.func_72805_g(x, y, z);
        int yOffset = 0;
        if (meta == 1) {
            yOffset = -1;
        }
        if ((te = world.func_147438_o(x, y + yOffset, z)) != null && te instanceof TileEntityMannequin && (teMan = (TileEntityMannequin)te).getGameProfile() != null) {
            NBTTagCompound profileTag = new NBTTagCompound();
            NBTUtil.func_152460_a((NBTTagCompound)profileTag, (GameProfile)teMan.getGameProfile());
            stack.func_77982_d(new NBTTagCompound());
            stack.func_77978_p().func_74782_a(TAG_OWNER, (NBTBase)profileTag);
        }
        return stack;
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

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float xHit, float yHit, float zHit) {
        if (!player.func_82247_a(x, y, z, side, player.func_71045_bC())) {
            return false;
        }
        if (!world.field_72995_K) {
            ItemStack stack = player.func_71045_bC();
            if (stack != null && stack.func_77973_b() == ModItems.mannequinTool) {
                return false;
            }
            if (stack != null && stack.func_77973_b() == Items.field_151057_cb) {
                TileEntity te = world.func_147438_o(x, y, z);
                if (te != null && te instanceof TileEntityMannequin && stack.func_77973_b() == Items.field_151057_cb) {
                    ((TileEntityMannequin)te).setOwner(player.func_71045_bC());
                }
            } else {
                FMLNetworkHandler.openGui((EntityPlayer)player, (Object)ArmourersWorkshop.instance, (int)6, (World)world, (int)x, (int)y, (int)z);
            }
        }
        return player.field_71071_by.func_70448_g() == null || player.field_71071_by.func_70448_g().func_77973_b() != ModItems.mannequinTool;
    }

    public int quantityDropped(int meta, int fortune, Random random) {
        return 0;
    }

    public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
        return new TileEntityMannequin(true);
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149721_r() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return -1;
    }
}

