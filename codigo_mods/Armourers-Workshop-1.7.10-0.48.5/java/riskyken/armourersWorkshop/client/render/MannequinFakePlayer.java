/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package riskyken.armourersWorkshop.client.render;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class MannequinFakePlayer
extends AbstractClientPlayer {
    public MannequinFakePlayer(World world, GameProfile gameProfile) {
        super(world, gameProfile);
    }

    public String func_70005_c_() {
        return "[Mannequin]";
    }

    public String getDisplayName() {
        return "[Mannequin]";
    }

    public void func_70071_h_() {
        this.field_71091_bM = this.field_71094_bP;
        this.field_71096_bN = this.field_71095_bQ;
        this.field_71097_bO = this.field_71085_bR;
        double d3 = this.field_70165_t - this.field_71094_bP;
        double d0 = this.field_70163_u - this.field_71095_bQ;
        double d1 = this.field_70161_v - this.field_71085_bR;
        double d2 = 10.0;
        if (d3 > d2) {
            this.field_71091_bM = this.field_71094_bP = this.field_70165_t;
        }
        if (d1 > d2) {
            this.field_71097_bO = this.field_71085_bR = this.field_70161_v;
        }
        if (d0 > d2) {
            this.field_71096_bN = this.field_71095_bQ = this.field_70163_u;
        }
        if (d3 < -d2) {
            this.field_71091_bM = this.field_71094_bP = this.field_70165_t;
        }
        if (d1 < -d2) {
            this.field_71097_bO = this.field_71085_bR = this.field_70161_v;
        }
        if (d0 < -d2) {
            this.field_71096_bN = this.field_71095_bQ = this.field_70163_u;
        }
        this.field_71094_bP += d3 * 0.25;
        this.field_71085_bR += d1 * 0.25;
        this.field_71095_bQ += d0 * 0.25;
    }

    public boolean func_70003_b(int i, String s) {
        return false;
    }

    public ChunkCoordinates func_82114_b() {
        return new ChunkCoordinates(0, 0, 0);
    }

    public void func_146105_b(IChatComponent chatmessagecomponent) {
    }

    public void func_71064_a(StatBase par1StatBase, int par2) {
    }

    public void openGui(Object mod, int modGuiId, World world, int x, int y, int z) {
    }

    public boolean func_85032_ar() {
        return true;
    }

    public boolean func_96122_a(EntityPlayer player) {
        return false;
    }

    public void func_70645_a(DamageSource source) {
    }

    public void func_71027_c(int dim) {
    }

    public void func_145747_a(IChatComponent p_145747_1_) {
    }

    public IIcon func_70620_b(ItemStack stack, int pass) {
        if (stack.func_77973_b().func_77623_v()) {
            return stack.func_77973_b().getIcon(stack, pass);
        }
        return stack.func_77954_c();
    }
}

