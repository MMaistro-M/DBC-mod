/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemHoe
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.item.ItemTool
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 */
package JinRyuu.DragonBC.common.Render;

import JinRyuu.DragonBC.common.Blocks.BlocksDBC;
import JinRyuu.DragonBC.common.Render.ArtGravDevBlock;
import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class ArtGravDevTileEntity
extends TileEntity
implements ISidedInventory {
    private static final int[] slotsBottom = new int[]{0};
    private ItemStack[] furnaceItemStacks = new ItemStack[1];
    public int furnaceBurnTime;
    public int currentItemBurnTime;
    private String tileEntityName;
    public static final int itemFuel = 0;
    private float gravity = 1.0f;
    public int gravityDevCB;

    public boolean canUpdate() {
        return true;
    }

    private void writeSyncableDataToNBT(NBTTagCompound syncData) {
        syncData.func_74776_a("gravity", this.gravity);
    }

    private void readSyncableDataFromNBT(NBTTagCompound par1) {
        this.gravity = par1.func_74760_g("gravity");
    }

    public float getGravity() {
        return this.gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public Packet func_145844_m() {
        NBTTagCompound syncData = new NBTTagCompound();
        this.writeSyncableDataToNBT(syncData);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, syncData);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        this.readSyncableDataFromNBT(pkt.func_148857_g());
    }

    public int func_70302_i_() {
        return this.furnaceItemStacks.length;
    }

    public ItemStack func_70301_a(int p_70301_1_) {
        return this.furnaceItemStacks[p_70301_1_];
    }

    public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
        if (this.furnaceItemStacks[p_70298_1_] != null) {
            if (this.furnaceItemStacks[p_70298_1_].field_77994_a <= p_70298_2_) {
                ItemStack itemstack = this.furnaceItemStacks[p_70298_1_];
                this.furnaceItemStacks[p_70298_1_] = null;
                return itemstack;
            }
            ItemStack itemstack = this.furnaceItemStacks[p_70298_1_].func_77979_a(p_70298_2_);
            if (this.furnaceItemStacks[p_70298_1_].field_77994_a == 0) {
                this.furnaceItemStacks[p_70298_1_] = null;
            }
            return itemstack;
        }
        return null;
    }

    public ItemStack func_70304_b(int p_70304_1_) {
        if (this.furnaceItemStacks[p_70304_1_] != null) {
            ItemStack itemstack = this.furnaceItemStacks[p_70304_1_];
            this.furnaceItemStacks[p_70304_1_] = null;
            return itemstack;
        }
        return null;
    }

    public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {
        this.furnaceItemStacks[p_70299_1_] = p_70299_2_;
        if (p_70299_2_ != null && p_70299_2_.field_77994_a > this.func_70297_j_()) {
            p_70299_2_.field_77994_a = this.func_70297_j_();
        }
    }

    public String func_145825_b() {
        return this.func_145818_k_() ? this.tileEntityName : BlocksDBC.ArtGravDevBlock.func_149739_a() + ".name";
    }

    public boolean func_145818_k_() {
        return this.tileEntityName != null && this.tileEntityName.length() > 0;
    }

    public void func_145951_a(String p_145951_1_) {
        this.tileEntityName = p_145951_1_;
    }

    public void func_145839_a(NBTTagCompound p_145839_1_) {
        super.func_145839_a(p_145839_1_);
        this.readSyncableDataFromNBT(p_145839_1_);
        NBTTagList nbttaglist = p_145839_1_.func_150295_c("Items", 10);
        this.furnaceItemStacks = new ItemStack[this.func_70302_i_()];
        for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            byte b0 = nbttagcompound1.func_74771_c("Slot");
            if (b0 < 0 || b0 >= this.furnaceItemStacks.length) continue;
            this.furnaceItemStacks[b0] = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound1);
        }
        this.furnaceBurnTime = p_145839_1_.func_74765_d("BurnTime");
        this.currentItemBurnTime = ArtGravDevTileEntity.getItemBurnTime(this.furnaceItemStacks[0]);
        if (p_145839_1_.func_150297_b("CustomName", 8)) {
            this.tileEntityName = p_145839_1_.func_74779_i("CustomName");
        }
    }

    public void func_145841_b(NBTTagCompound p_145841_1_) {
        super.func_145841_b(p_145841_1_);
        this.writeSyncableDataToNBT(p_145841_1_);
        p_145841_1_.func_74777_a("BurnTime", (short)this.furnaceBurnTime);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.furnaceItemStacks.length; ++i) {
            if (this.furnaceItemStacks[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.func_74774_a("Slot", (byte)i);
            this.furnaceItemStacks[i].func_77955_b(nbttagcompound1);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        }
        p_145841_1_.func_74782_a("Items", (NBTBase)nbttaglist);
        if (this.func_145818_k_()) {
            p_145841_1_.func_74778_a("CustomName", this.tileEntityName);
        }
    }

    public int func_70297_j_() {
        return 64;
    }

    @SideOnly(value=Side.CLIENT)
    public int getBurnTimeRemainingScaled(int p_145955_1_) {
        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = 200;
        }
        return this.furnaceBurnTime * p_145955_1_ / this.currentItemBurnTime;
    }

    public boolean isBurning() {
        return this.furnaceBurnTime > 0;
    }

    public void func_145845_h() {
        boolean flag = this.furnaceBurnTime > 0;
        boolean flag1 = false;
        if (this.furnaceBurnTime > 0) {
            --this.furnaceBurnTime;
        }
        if (!this.field_145850_b.field_72995_K) {
            if (this.gravity > 1.0f && this.isBurning()) {
                --this.gravityDevCB;
                if (this.gravityDevCB <= 0) {
                    this.gravityDevCB = 20;
                    int n = 10;
                    AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)((double)this.field_145851_c - (double)n), (double)((double)this.field_145848_d - (double)n), (double)((double)this.field_145849_e - (double)n), (double)((double)this.field_145851_c + (double)n), (double)((double)this.field_145848_d + (double)n), (double)((double)this.field_145849_e + (double)n));
                    List list = this.field_145850_b.func_72872_a(EntityPlayer.class, aabb);
                    if (!list.isEmpty()) {
                        for (int i = 0; i < list.size(); ++i) {
                            EntityPlayer player = (EntityPlayer)list.get(i);
                            int epoch = (int)(System.currentTimeMillis() / 1000L) + 1;
                            JRMCoreH.setInt(epoch, player, "jrmcGravZoneLast");
                            float G = JRMCoreH.getFloat(player, "jrmcGravForce");
                            if (!(this.gravity > G)) continue;
                            JRMCoreH.setFloat(this.gravity, player, "jrmcGravForce");
                        }
                    }
                }
            }
            if ((this.furnaceBurnTime != 0 || this.furnaceItemStacks[0] != null) && this.furnaceBurnTime == 0 && this.canSmelt()) {
                this.currentItemBurnTime = this.furnaceBurnTime = ArtGravDevTileEntity.getItemBurnTime(this.furnaceItemStacks[0]);
                if (this.furnaceBurnTime > 0) {
                    flag1 = true;
                    if (this.furnaceItemStacks[0] != null) {
                        --this.furnaceItemStacks[0].field_77994_a;
                        if (this.furnaceItemStacks[0].field_77994_a == 0) {
                            this.furnaceItemStacks[0] = this.furnaceItemStacks[0].func_77973_b().getContainerItem(this.furnaceItemStacks[0]);
                        }
                    }
                }
            }
            if (flag != this.furnaceBurnTime > 0) {
                flag1 = true;
                ArtGravDevBlock.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }
        if (flag1) {
            this.func_70296_d();
        }
    }

    private boolean canSmelt() {
        if (this.furnaceItemStacks[0] == null) {
            return false;
        }
        return this.gravity > 1.0f;
    }

    public void smeltItem() {
        if (this.canSmelt() && this.gravity > 1.0f) {
            --this.gravityDevCB;
            if (this.gravityDevCB <= 0) {
                this.gravityDevCB = 20;
                int n = 10;
                AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)((double)this.field_145851_c - (double)n), (double)((double)this.field_145848_d - (double)n), (double)((double)this.field_145849_e - (double)n), (double)((double)this.field_145851_c + (double)n), (double)((double)this.field_145848_d + (double)n), (double)((double)this.field_145849_e + (double)n));
                List list = this.field_145850_b.func_72872_a(EntityPlayer.class, aabb);
                if (!list.isEmpty()) {
                    for (int i = 0; i < list.size(); ++i) {
                        EntityPlayer player = (EntityPlayer)list.get(i);
                        int epoch = (int)(System.currentTimeMillis() / 1000L) + 1;
                        JRMCoreH.setInt(epoch, player, "jrmcGravZoneLast");
                        float G = JRMCoreH.getFloat(player, "jrmcGravForce");
                        if (!(this.gravity > G)) continue;
                        JRMCoreH.setFloat(this.gravity, player, "jrmcGravForce");
                    }
                }
            }
        }
    }

    public static int getItemBurnTime(ItemStack p_145952_0_) {
        if (p_145952_0_ == null) {
            return 0;
        }
        Item item = p_145952_0_.func_77973_b();
        if (item instanceof ItemBlock && Block.func_149634_a((Item)item) != Blocks.field_150350_a) {
            Block block = Block.func_149634_a((Item)item);
            if (block == Blocks.field_150376_bx) {
                return 150;
            }
            if (block.func_149688_o() == Material.field_151575_d) {
                return 300;
            }
            if (block == Blocks.field_150402_ci) {
                return 16000;
            }
        }
        if (item instanceof ItemTool && ((ItemTool)item).func_77861_e().equals("WOOD")) {
            return 200;
        }
        if (item instanceof ItemSword && ((ItemSword)item).func_150932_j().equals("WOOD")) {
            return 200;
        }
        if (item instanceof ItemHoe && ((ItemHoe)item).func_77842_f().equals("WOOD")) {
            return 200;
        }
        if (item == Items.field_151055_y) {
            return 100;
        }
        if (item == Items.field_151044_h) {
            return 1600;
        }
        if (item == Items.field_151129_at) {
            return 20000;
        }
        if (item == Item.func_150898_a((Block)Blocks.field_150345_g)) {
            return 100;
        }
        if (item == Items.field_151072_bj) {
            return 2400;
        }
        return GameRegistry.getFuelValue((ItemStack)p_145952_0_);
    }

    public static boolean isItemFuel(ItemStack p_145954_0_) {
        return ArtGravDevTileEntity.getItemBurnTime(p_145954_0_) > 0;
    }

    public boolean func_70300_a(EntityPlayer p_70300_1_) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : p_70300_1_.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
        return p_94041_1_ == 2 ? false : (p_94041_1_ == 1 ? ArtGravDevTileEntity.isItemFuel(p_94041_2_) : true);
    }

    public int[] func_94128_d(int p_94128_1_) {
        return slotsBottom;
    }

    public boolean func_102007_a(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        return this.func_94041_b(p_102007_1_, p_102007_2_);
    }

    public boolean func_102008_b(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return p_102008_3_ != 0 || p_102008_1_ != 1 || p_102008_2_.func_77973_b() == Items.field_151133_ar;
    }
}

