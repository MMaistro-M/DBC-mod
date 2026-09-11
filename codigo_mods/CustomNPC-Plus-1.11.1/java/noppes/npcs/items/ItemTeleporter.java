/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package noppes.npcs.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcsPermissions;
import noppes.npcs.NoppesUtilServer;

public class ItemTeleporter
extends Item {
    public ItemTeleporter() {
        this.field_77777_bU = 1;
        this.func_77637_a(CustomItems.tab);
    }

    public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        block3: {
            block2: {
                if (par2World.field_72995_K) break block2;
                if (CustomNpcsPermissions.hasPermission(par3EntityPlayer, CustomNpcsPermissions.TOOL_TELEPORTER)) break block3;
            }
            return par1ItemStack;
        }
        NoppesUtilServer.setTeleporterGUI((EntityPlayerMP)par3EntityPlayer);
        return par1ItemStack;
    }

    public boolean onEntitySwing(EntityLivingBase par3EntityPlayer, ItemStack stack) {
        int i;
        float f8;
        float f6;
        double d3;
        float f5;
        if (par3EntityPlayer.field_70170_p.field_72995_K) {
            return false;
        }
        float f = 1.0f;
        float f1 = par3EntityPlayer.field_70127_C + (par3EntityPlayer.field_70125_A - par3EntityPlayer.field_70127_C) * f;
        float f2 = par3EntityPlayer.field_70126_B + (par3EntityPlayer.field_70177_z - par3EntityPlayer.field_70126_B) * f;
        double d0 = par3EntityPlayer.field_70169_q + (par3EntityPlayer.field_70165_t - par3EntityPlayer.field_70169_q) * (double)f;
        double d1 = par3EntityPlayer.field_70167_r + (par3EntityPlayer.field_70163_u - par3EntityPlayer.field_70167_r) * (double)f + 1.62 - (double)par3EntityPlayer.field_70129_M;
        double d2 = par3EntityPlayer.field_70166_s + (par3EntityPlayer.field_70161_v - par3EntityPlayer.field_70166_s) * (double)f;
        Vec3 vec3 = Vec3.func_72443_a((double)d0, (double)d1, (double)d2);
        float f3 = MathHelper.func_76134_b((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
        float f4 = MathHelper.func_76126_a((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
        float f7 = f4 * (f5 = -MathHelper.func_76134_b((float)(-f1 * ((float)Math.PI / 180))));
        Vec3 vec31 = vec3.func_72441_c((double)f7 * (d3 = 80.0), (double)(f6 = MathHelper.func_76126_a((float)(-f1 * ((float)Math.PI / 180)))) * d3, (double)(f8 = f3 * f5) * d3);
        MovingObjectPosition movingobjectposition = par3EntityPlayer.field_70170_p.func_72901_a(vec3, vec31, true);
        if (movingobjectposition == null) {
            return false;
        }
        Vec3 vec32 = par3EntityPlayer.func_70676_i(f);
        boolean flag = false;
        float f9 = 1.0f;
        List list = par3EntityPlayer.field_70170_p.func_72839_b((Entity)par3EntityPlayer, par3EntityPlayer.field_70121_D.func_72321_a(vec32.field_72450_a * d3, vec32.field_72448_b * d3, vec32.field_72449_c * d3).func_72314_b((double)f9, (double)f9, (double)f9));
        for (i = 0; i < list.size(); ++i) {
            float f10;
            AxisAlignedBB axisalignedbb;
            Entity entity = (Entity)list.get(i);
            if (!entity.func_70067_L() || !(axisalignedbb = entity.field_70121_D.func_72314_b((double)(f10 = entity.func_70111_Y()), (double)f10, (double)f10)).func_72318_a(vec3)) continue;
            flag = true;
        }
        if (flag) {
            return false;
        }
        if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            i = movingobjectposition.field_72311_b;
            int j = movingobjectposition.field_72312_c;
            int k = movingobjectposition.field_72309_d;
            while (par3EntityPlayer.field_70170_p.func_147439_a(i, j, k) != Blocks.field_150350_a) {
                ++j;
            }
            par3EntityPlayer.func_70634_a((double)((float)i + 0.5f), (double)((float)j + 1.0f), (double)((float)k + 0.5f));
        }
        return true;
    }

    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        return 9127187;
    }

    public boolean func_77623_v() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.field_77791_bV = Items.field_151008_G.func_77617_a(0);
    }

    public Item func_77655_b(String name) {
        GameRegistry.registerItem((Item)this, (String)name);
        return super.func_77655_b(name);
    }
}

