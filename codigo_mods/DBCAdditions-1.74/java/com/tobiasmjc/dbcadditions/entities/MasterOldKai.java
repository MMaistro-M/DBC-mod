/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package com.tobiasmjc.dbcadditions.entities;

import JinRyuu.DragonBC.common.Npcs.EntityMasterKami;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class MasterOldKai
extends EntityMasterKami {
    public MasterOldKai(World par1World) {
        super(par1World);
        this.name = "Master Old Kai";
        this.func_70105_a(0.6f, 1.8f);
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(5000.0);
    }

    @Override
    public boolean func_70085_c(EntityPlayer par1EntityPlayer) {
        if (this.func_70089_S()) {
            par1EntityPlayer.openGui((Object)"dbcadditions", 24, par1EntityPlayer.field_70170_p, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);
            return true;
        }
        return super.func_70085_c(par1EntityPlayer);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "ninjinentities:textures/entity/eldKaioshin.png";
    }
}

