/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.DragonBC.common.Npcs.EntityDBCKami;
import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.entity.EntityCusPar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityMasterJin
extends EntityDBCKami {
    public final int HePo = 80000;

    public EntityMasterJin(World par1World) {
        super(par1World);
        this.name = "Master Jin";
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
            par1EntityPlayer.openGui((Object)mod_DragonBC.instance, 9000, par1EntityPlayer.field_70170_p, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);
            return true;
        }
        return super.func_70085_c(par1EntityPlayer);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "jinryuudragonbc:npcs/jin.png";
    }

    @Override
    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            for (int k = 0; k < JGConfigClientSettings.get_da1(); ++k) {
                if (!JGConfigClientSettings.CLIENT_DA8) continue;
                EntityMasterJin pl = this;
                double posXOth = ((Entity)pl).field_70165_t;
                double posYOth = ((Entity)pl).field_70163_u;
                double posZOth = ((Entity)pl).field_70161_v;
                float red = 141.0f;
                float green = 158.0f;
                float blue = 210.0f;
                float red2 = 215.0f;
                float green2 = 152.0f;
                float blue2 = 219.0f;
                float out = 1.6f;
                float in = 1.5f;
                float life = 0.8f * ((Entity)pl).field_70131_O;
                float extra_scale = 0.5f;
                int dea = 50;
                double x = Math.random() * (double)1.6f - (double)0.8f;
                double y = Math.random() * (double)((Entity)pl).field_70131_O - 0.5;
                double z = Math.random() * (double)1.6f - (double)0.8f;
                EntityCusPar entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 141.0f, 158.0f, 210.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                x = Math.random() * (double)1.6f - (double)0.8f;
                y = Math.random() * (double)((Entity)pl).field_70131_O - 0.5;
                z = Math.random() * (double)1.6f - (double)0.8f;
                entity.field_70170_p.func_72838_d((Entity)entity);
                EntityCusPar entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 141.0f, 158.0f, 210.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                entity.field_70170_p.func_72838_d((Entity)entity2);
                x = Math.random() * 1.5 - 0.75;
                y = (Math.random() * (double)((Entity)pl).field_70131_O - 0.5) * (double)0.8f;
                z = Math.random() * 1.5 - 0.75;
                entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 215.0f, 152.0f, 219.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                x = Math.random() * 1.5 - 0.75;
                y = (Math.random() * (double)((Entity)pl).field_70131_O - 0.5) * (double)0.8f;
                z = Math.random() * 1.5 - 0.75;
                entity.field_70170_p.func_72838_d((Entity)entity);
                entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 215.0f, 152.0f, 219.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                entity.field_70170_p.func_72838_d((Entity)entity2);
                float in2 = 0.6f;
                x = Math.random() * (double)0.6f - (double)0.3f;
                y = (Math.random() * (double)((Entity)pl).field_70131_O - 0.5) * (double)0.8f * (double)0.6f;
                z = Math.random() * (double)0.6f - (double)0.3f;
                EntityCusPar entity3 = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 243.0f, 247.0f, 250.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                x = Math.random() * (double)0.6f - (double)0.3f;
                y = (Math.random() * (double)((Entity)pl).field_70131_O - 0.5) * (double)0.8f;
                z = Math.random() * (double)0.6f - (double)0.3f;
                entity3.field_70170_p.func_72838_d((Entity)entity3);
                EntityCusPar entity22 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, posXOth, posYOth, posZOth, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 243.0f, 247.0f, 250.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, true, (Entity)pl);
                entity3.field_70170_p.func_72838_d((Entity)entity22);
            }
        }
    }
}

