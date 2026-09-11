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

public class EntityMasterTrunksFuture
extends EntityDBCKami {
    public final int HePo = 80000;

    public EntityMasterTrunksFuture(World par1World) {
        super(par1World);
        this.name = "Master Future Trunks";
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(80000.0);
    }

    @Override
    public boolean func_70085_c(EntityPlayer par1EntityPlayer) {
        boolean var3;
        ItemStack var2 = par1EntityPlayer.field_71071_by.func_70448_g();
        boolean bl = var3 = var2 != null;
        if (this.func_70089_S()) {
            par1EntityPlayer.openGui((Object)mod_DragonBC.instance, 23, par1EntityPlayer.field_70170_p, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);
            return true;
        }
        return super.func_70085_c(par1EntityPlayer);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "jinryuudragonbc:npcs/trunks1.png";
    }

    @Override
    public void func_70071_h_() {
        super.func_70071_h_();
    }
}

