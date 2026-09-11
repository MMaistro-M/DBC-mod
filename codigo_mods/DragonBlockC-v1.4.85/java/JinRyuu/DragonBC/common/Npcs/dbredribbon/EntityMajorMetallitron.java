/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs.dbredribbon;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.Npcs.dbredribbon.EntityRedRibbon2;
import JinRyuu.JRMCore.entity.EntityPrjtls_1;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityMajorMetallitron
extends EntityRedRibbon2 {
    public final int AttPow = 50;
    public final int HePo = 200;
    private byte damageCategory;

    public EntityMajorMetallitron(World world) {
        super(world);
        this.func_70105_a(1.2f, 5.0f);
        this.texture = "major_metallitron";
        this.setAttributes(DBCConfig.RRMajorDAM, DBCConfig.RRMajorHP, 50, 200);
        this.damageCategory = 0;
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(200.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(50.0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "jinryuudragonbc:npcs/major_metallitron" + (this.damageCategory > 0 ? Integer.valueOf(this.damageCategory + 1) : "") + ".png";
    }

    @Override
    public void writeSpawnData(ByteBuf additionalData) {
        additionalData.writeByte((int)this.damageCategory);
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        this.damageCategory = additionalData.readByte();
    }

    @Override
    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.func_74774_a("damageCategory", this.damageCategory);
    }

    @Override
    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        this.damageCategory = (byte)(par1NBTTagCompound.func_74771_c("damageCategory") & 0xFF);
    }

    @Override
    public void func_70071_h_() {
        super.func_70071_h_();
        int currentHealth = (int)this.func_110143_aJ();
        int maxHealth = (int)this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111126_e();
        this.damageCategory = (byte)((maxHealth - currentHealth) / (maxHealth / 3));
        if (this.damageCategory < 0) {
            this.damageCategory = 0;
        } else if (this.damageCategory > 2) {
            this.damageCategory = (byte)2;
        }
        if (!this.field_70170_p.field_72995_K && !this.field_70128_L && this.field_70789_a != null && this.field_70789_a.func_70089_S() && this.field_70789_a.func_70032_d((Entity)this) < 25.0f && (this.field_70173_aa + 200) % 400 < 30) {
            EntityPrjtls_1 var8 = new EntityPrjtls_1(this.field_70170_p, (Entity)this, this.field_70789_a, 1.8f, 1.0f, 6);
            this.field_70170_p.func_72956_a((Entity)this, "jinryuudragonbc:DBC5.gun_shot_single", 0.2f, this.field_70170_p.field_73012_v.nextFloat() * 0.1f + 0.9f);
            this.field_70170_p.func_72838_d((Entity)var8);
        }
    }
}

