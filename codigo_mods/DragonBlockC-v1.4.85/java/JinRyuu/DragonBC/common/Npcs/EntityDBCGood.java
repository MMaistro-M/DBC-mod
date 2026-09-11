/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.DragonBC.common.Npcs.EntityDBC;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreH2;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityDBCGood
extends EntityDBC {
    public EntityDBCGood(World par1World) {
        super(par1World);
    }

    @Override
    public void func_70645_a(DamageSource par1DamageSource) {
        Entity var3 = par1DamageSource.func_76346_g();
        if (var3 instanceof EntityPlayer) {
            int al = JRMCoreH.getByte((EntityPlayer)var3, "jrmcAlign");
            al = (al -= 20) < 0 ? 0 : al;
            JRMCoreH.setByte(al, (EntityPlayer)var3, "jrmcAlign");
            int kr = JRMCoreH.getInt((EntityPlayer)var3, "jrmcKarma");
            JRMCoreH.setInt(kr + 1, (EntityPlayer)var3, "jrmcKarma");
            ((EntityPlayer)var3).func_145747_a(new ChatComponentTranslation(JRMCoreH.trlai("dbc.moreevil.line1"), new Object[0]).func_150255_a(JRMCoreH2.styl_wht));
        }
        super.func_70645_a(par1DamageSource);
    }
}

