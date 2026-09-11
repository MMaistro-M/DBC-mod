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
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTUtil
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.StringUtils
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package riskyken.armourersWorkshop.common.blocks;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StringUtils;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import riskyken.armourersWorkshop.ArmourersWorkshop;
import riskyken.armourersWorkshop.client.lib.LibBlockResources;
import riskyken.armourersWorkshop.common.Contributors;
import riskyken.armourersWorkshop.common.blocks.AbstractModBlockContainer;
import riskyken.armourersWorkshop.common.blocks.ModBlocks;
import riskyken.armourersWorkshop.common.items.ItemDebugTool;
import riskyken.armourersWorkshop.common.items.ModItems;
import riskyken.armourersWorkshop.common.items.block.ItemBlockMannequin;
import riskyken.armourersWorkshop.common.tileentities.TileEntityMannequin;
import riskyken.armourersWorkshop.utils.BlockUtils;
import riskyken.armourersWorkshop.utils.HolidayHelper;
import riskyken.armourersWorkshop.utils.UtilItems;

public class BlockMannequin
extends AbstractModBlockContainer
implements ItemDebugTool.IDebug {
    public static DamageSource victoriousDamage = new DamageSource("victorious");
    public static GameProfile vicProfile = new GameProfile(UUID.fromString("b027a4f4-d480-426c-84a3-a9cb029f4b72"), "VicNightfall");
    private static final String TAG_OWNER = "owner";
    private static final String TAG_IMAGE_URL = "imageUrl";
    private final boolean isValentins;

    public BlockMannequin() {
        super("mannequin", Material.field_151576_e, field_149777_j, true);
        this.func_149713_g(0);
        this.func_149676_a(0.1f, 0.0f, 0.1f, 0.9f, 0.9f, 0.9f);
        this.isValentins = HolidayHelper.valentins.isHolidayActive();
        this.setSortPriority(199);
    }

    public Block func_149663_c(String name) {
        GameRegistry.registerBlock((Block)this, ItemBlockMannequin.class, (String)("block." + name));
        return super.func_149663_c(name);
    }

    public void func_149749_a(World world, int x, int y, int z, Block block, int metadata) {
        TileEntityMannequin te;
        if (!world.field_72995_K && (te = this.getMannequinTileEntity(world, x, y, z)) != null && te.getDropItems()) {
            ItemStack dropStack = te.getDropStack();
            UtilItems.spawnItemInWorld(world, x, y, z, dropStack);
            BlockUtils.dropInventoryBlocks(world, x, y, z);
        }
        super.func_149749_a(world, x, y, z, block, metadata);
    }

    public void func_149689_a(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null && te instanceof TileEntityMannequin) {
            int l = MathHelper.func_76128_c((double)((double)(player.field_70177_z * 16.0f / 360.0f) + 0.5)) & 0xF;
            ((TileEntityMannequin)te).setRotation(l);
            if (!world.field_72995_K && stack.func_77942_o()) {
                NBTTagCompound compound = stack.func_77978_p();
                GameProfile gameProfile = null;
                if (compound.func_150297_b(TAG_OWNER, 10)) {
                    gameProfile = NBTUtil.func_152459_a((NBTTagCompound)compound.func_74775_l(TAG_OWNER));
                    ((TileEntityMannequin)te).setGameProfile(gameProfile);
                }
                if (compound.func_150297_b(TAG_IMAGE_URL, 8)) {
                    ((TileEntityMannequin)te).setImageUrl(compound.func_74779_i(TAG_IMAGE_URL));
                }
            }
        }
        world.func_147465_d(x, y + 1, z, (Block)this, 1, 2);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random random) {
        if (this.isTopOfMannequin(world, x, y, z)) {
            Contributors.Contributor contributor;
            TileEntityMannequin te;
            if (this.isValentins && random.nextFloat() * 100.0f > 75.0f) {
                world.func_72869_a("heart", (double)x + 0.2 + (double)(random.nextFloat() * 0.6f), (double)y + 1.0, (double)z + 0.2 + (double)(random.nextFloat() * 0.6f), 0.0, 0.0, 0.0);
            }
            if ((te = this.getMannequinTileEntity(world, x, y, z)) != null && te.isRenderExtras() && (contributor = Contributors.INSTANCE.getContributor(te.getGameProfile())) != null & te.isVisible()) {
                for (int i = 0; i < 4; ++i) {
                    EntitySpellParticleFX entityfx = new EntitySpellParticleFX(world, (double)((float)(x - 1) + random.nextFloat() * 3.0f), (double)y - 1.0, (double)((float)(z - 1) + random.nextFloat() * 3.0f), 0.0, 0.0, 0.0);
                    entityfx.func_70589_b(144);
                    entityfx.func_70538_b((float)(contributor.r & 0xFF) / 255.0f, (float)(contributor.g & 0xFF) / 255.0f, (float)(contributor.b & 0xFF) / 255.0f);
                    Minecraft.func_71410_x().field_71452_i.func_78873_a((EntityFX)entityfx);
                }
            }
        }
    }

    public void convertToDoll(World world, int x, int y, int z) {
        TileEntityMannequin te;
        if (this.isTopOfMannequin(world, x, y, z)) {
            Block block = world.func_147439_a(x, y - 1, z);
            if (block == this) {
                ((BlockMannequin)block).convertToDoll(world, x, y - 1, z);
            }
            return;
        }
        if (world.func_147439_a(x, y + 1, z) == this && (te = this.getMannequinTileEntity(world, x, y, z)) != null) {
            te.setDropItems(false);
            NBTTagCompound compound = new NBTTagCompound();
            te.writeCommonToNBT(compound);
            te.writeItemsToNBT(compound);
            world.func_147468_f(x, y + 1, z);
            world.func_147465_d(x, y, z, ModBlocks.doll, 0, 3);
            TileEntity newTe = world.func_147438_o(x, y, z);
            if (newTe != null && newTe instanceof TileEntityMannequin) {
                ((TileEntityMannequin)newTe).readCommonFromNBT(compound);
                ((TileEntityMannequin)newTe).readItemsFromNBT(compound);
                ((TileEntityMannequin)newTe).setDoll(true);
            }
        }
    }

    public TileEntityMannequin getMannequinTileEntity(World world, int x, int y, int z) {
        TileEntity te;
        int offset = 0;
        if (this.isTopOfMannequin(world, x, y, z)) {
            offset = -1;
        }
        if ((te = world.func_147438_o(x, y + offset, z)) != null && te instanceof TileEntityMannequin) {
            return (TileEntityMannequin)te;
        }
        return null;
    }

    public boolean isTopOfMannequin(World world, int x, int y, int z) {
        return this.isTopOfMannequin(world.func_72805_g(x, y, z));
    }

    public boolean isTopOfMannequin(int meta) {
        return meta == 1;
    }

    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis) {
        TileEntity te;
        if (world.field_72995_K) {
            return false;
        }
        int meta = world.func_72805_g(x, y, z);
        int yOffset = 0;
        if (meta == 1) {
            yOffset = -1;
        }
        if ((te = world.func_147438_o(x, y + yOffset, z)) != null && te instanceof TileEntityMannequin) {
            int rotation = ((TileEntityMannequin)te).getRotation();
            if (++rotation > 15) {
                rotation = 0;
            }
            ((TileEntityMannequin)te).setRotation(rotation);
        }
        return true;
    }

    public void func_149670_a(World world, int x, int y, int z, Entity entity) {
        TileEntityMannequin teMan;
        super.func_149670_a(world, x, y, z, entity);
        if (world.field_72995_K) {
            return;
        }
        if (!(entity instanceof EntityLivingBase)) {
            return;
        }
        EntityLivingBase entityLiving = (EntityLivingBase)entity;
        int meta = world.func_72805_g(x, y, z);
        if (meta != 1) {
            return;
        }
        if (entityLiving.field_70163_u != (double)y + (double)0.9f) {
            return;
        }
        if (entityLiving.field_70165_t < (double)((float)x + 0.2f) | entityLiving.field_70165_t > (double)((float)x + 0.8f)) {
            return;
        }
        if (entityLiving.field_70161_v < (double)((float)z + 0.2f) | entityLiving.field_70161_v > (double)((float)z + 0.8f)) {
            return;
        }
        TileEntity te = world.func_147438_o(x, y - 1, z);
        if (te != null && te instanceof TileEntityMannequin && (teMan = (TileEntityMannequin)te).getGameProfile() != null && teMan.getGameProfile().getId() == vicProfile.getId()) {
            entityLiving.func_70097_a(victoriousDamage, 2.0f);
        }
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        TileEntity te;
        ItemStack stack = new ItemStack(ModBlocks.mannequin, 1);
        int meta = world.func_72805_g(x, y, z);
        int yOffset = 0;
        if (meta == 1) {
            yOffset = -1;
        }
        if ((te = world.func_147438_o(x, y + yOffset, z)) != null && te instanceof TileEntityMannequin) {
            TileEntityMannequin teMan = (TileEntityMannequin)te;
            if (teMan.getGameProfile() != null) {
                NBTTagCompound profileTag = new NBTTagCompound();
                NBTUtil.func_152460_a((NBTTagCompound)profileTag, (GameProfile)teMan.getGameProfile());
                stack.func_77982_d(new NBTTagCompound());
                stack.func_77978_p().func_74782_a(TAG_OWNER, (NBTBase)profileTag);
            }
            if (!StringUtils.func_151246_b((String)teMan.getImageUrl())) {
                stack.func_77982_d(new NBTTagCompound());
                stack.func_77978_p().func_74778_a(TAG_IMAGE_URL, teMan.getImageUrl());
            }
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

    public void func_149719_a(IBlockAccess world, int x, int y, int z) {
        int meta = world.func_72805_g(x, y, z);
        if (meta == 0) {
            this.func_149676_a(0.1f, 0.0f, 0.1f, 0.9f, 0.9f, 0.9f);
        } else {
            this.func_149676_a(0.1f, 0.0f, 0.1f, 0.9f, 0.9f, 0.9f);
        }
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float xHit, float yHit, float zHit) {
        if (!player.func_82247_a(x, y, z, side, player.func_71045_bC())) {
            return false;
        }
        if (!world.field_72995_K) {
            ItemStack stack;
            if (player.field_71071_by.func_70448_g() != null) {
                if (player.field_71071_by.func_70448_g().func_77973_b() == ModItems.mannequinTool) {
                    return false;
                }
                if (player.field_71071_by.func_70448_g().func_77973_b() == ModItems.paintbrush) {
                    return false;
                }
            }
            int meta = world.func_72805_g(x, y, z);
            int yOffset = 0;
            if (meta == 1) {
                yOffset = -1;
            }
            if ((stack = player.func_71045_bC()) != null && stack.func_77973_b() == Items.field_151057_cb) {
                TileEntity te = world.func_147438_o(x, y + yOffset, z);
                if (te != null && te instanceof TileEntityMannequin && stack.func_77973_b() == Items.field_151057_cb) {
                    ((TileEntityMannequin)te).setOwner(player.func_71045_bC());
                }
            } else {
                FMLNetworkHandler.openGui((EntityPlayer)player, (Object)ArmourersWorkshop.instance, (int)6, (World)world, (int)x, (int)(y + yOffset), (int)z);
            }
        }
        return player.field_71071_by.func_70448_g() == null || player.field_71071_by.func_70448_g().func_77973_b() != ModItems.mannequinTool;
    }

    public void func_149695_a(World world, int x, int y, int z, Block block) {
        int meta = world.func_72805_g(x, y, z);
        if (meta == 0) {
            if (world.func_147439_a(x, y + 1, z) != ModBlocks.mannequin) {
                world.func_147468_f(x, y, z);
            }
        } else if (world.func_147439_a(x, y - 1, z) != ModBlocks.mannequin) {
            world.func_147468_f(x, y, z);
        }
    }

    public int quantityDropped(int meta, int fortune, Random random) {
        return 0;
    }

    public TileEntity func_149915_a(World world, int p_149915_2_) {
        return null;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        if (metadata == 0) {
            return new TileEntityMannequin(false);
        }
        return null;
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

    @Override
    public void getDebugHoverText(World world, int x, int y, int z, ArrayList<String> textLines) {
        textLines.add("top=" + this.isTopOfMannequin(world, x, y, z));
        TileEntityMannequin te = this.getMannequinTileEntity(world, x, y, z);
        if (te != null && te.getGameProfile() != null) {
            textLines.add("profile=" + te.getGameProfile().getName() + ":" + te.getGameProfile().getId());
        } else {
            textLines.add("profile=null");
        }
    }
}

