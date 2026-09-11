/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.DragonBC.common.Npcs.EntityDBCKami;
import JinRyuu.DragonBC.common.mod_DragonBC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMasterBabidi
extends EntityDBCKami {
    public final int AttPow = 300;
    public final int HePo = 1120;

    public EntityMasterBabidi(World par1World) {
        super(par1World);
        this.name = "Master Babidi";
        if (this.field_71093_bK != 22 && this.field_71093_bK != 24) {
            this.safezoneRadiusXZ = 20;
            this.safezoneRadiusY = 20;
        }
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1120.0);
    }

    @Override
    public boolean func_70085_c(EntityPlayer par1EntityPlayer) {
        boolean var3;
        ItemStack var2 = par1EntityPlayer.field_71071_by.func_70448_g();
        boolean bl = var3 = var2 != null;
        if (this.func_70089_S()) {
            par1EntityPlayer.openGui((Object)mod_DragonBC.instance, 19, par1EntityPlayer.field_70170_p, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);
            return true;
        }
        return super.func_70085_c(par1EntityPlayer);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "jinryuudragonbc:npcs/babidi.png";
    }
}

