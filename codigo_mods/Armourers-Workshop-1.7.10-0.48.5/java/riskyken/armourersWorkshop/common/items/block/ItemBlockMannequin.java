/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTUtil
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.common.items.block;

import com.mojang.authlib.GameProfile;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import riskyken.armourersWorkshop.common.items.block.ModItemBlock;
import riskyken.armourersWorkshop.utils.TranslateUtils;

public class ItemBlockMannequin
extends ModItemBlock {
    private static final String TAG_OWNER = "owner";
    private static final String TAG_IMAGE_URL = "imageUrl";

    public ItemBlockMannequin(Block block) {
        super(block);
        this.func_77625_d(1);
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        return this.canPlaceBlockHere(stack, player, world, x, y, z, side, hitX, hitY, hitZ, false) && this.canPlaceBlockHere(stack, player, world, x, y, z, side, hitX, hitY, hitZ, true);
    }

    @Override
    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        if (stack.func_77942_o()) {
            NBTTagCompound compound = stack.func_77978_p();
            GameProfile gameProfile = null;
            if (compound.func_150297_b(TAG_OWNER, 10)) {
                gameProfile = NBTUtil.func_152459_a((NBTTagCompound)compound.func_74775_l(TAG_OWNER));
                String user = TranslateUtils.translate("item.armourersworkshop:rollover.user", gameProfile.getName());
                list.add(user);
            }
            if (compound.func_150297_b(TAG_IMAGE_URL, 8)) {
                String imageUrl = compound.func_74779_i(TAG_IMAGE_URL);
                String urlLine = TranslateUtils.translate("item.armourersworkshop:rollover.url", imageUrl);
                list.add(urlLine);
            }
        }
        super.func_77624_a(stack, player, list, par4);
    }

    private boolean canPlaceBlockHere(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, boolean place) {
        Block block = world.func_147439_a(x, y, z);
        if (block == Blocks.field_150431_aC && (world.func_72805_g(x, y, z) & 7) < 1) {
            side = 1;
        } else if (block != Blocks.field_150395_bd && block != Blocks.field_150329_H && block != Blocks.field_150330_I && !block.isReplaceable((IBlockAccess)world, x, y, z)) {
            if (side == 0) {
                --y;
            }
            if (side == 1) {
                ++y;
            }
            if (side == 2) {
                --z;
            }
            if (side == 3) {
                ++z;
            }
            if (side == 4) {
                --x;
            }
            if (side == 5) {
                ++x;
            }
        }
        if (!place) {
            ++y;
        }
        if (stack.field_77994_a == 0) {
            return false;
        }
        if (!player.func_82247_a(x, y, z, side, stack)) {
            return false;
        }
        if (y == 255 && this.field_150939_a.func_149688_o().func_76220_a()) {
            return false;
        }
        if (world.func_147472_a(this.field_150939_a, x, y, z, false, side, (Entity)player, stack)) {
            int i1 = this.func_77647_b(stack.func_77960_j());
            int j1 = this.field_150939_a.func_149660_a(world, x, y, z, side, hitX, hitY, hitZ, i1);
            if (place && this.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, j1)) {
                world.func_72908_a((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), this.field_150939_a.field_149762_H.func_150496_b(), (this.field_150939_a.field_149762_H.func_150497_c() + 1.0f) / 2.0f, this.field_150939_a.field_149762_H.func_150494_d() * 0.8f);
                --stack.field_77994_a;
            }
            return true;
        }
        return false;
    }
}

