/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.monster.IMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.Items.ItemsDBC;
import JinRyuu.DragonBC.common.Npcs.EntityDBCWildlifeA;
import JinRyuu.JRMCore.entity.EntityPrjtls_1;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityRRMecha
extends EntityDBCWildlifeA
implements IMob,
IEntityAdditionalSpawnData {
    public final int AttPow = 20;
    public final int HePo = 200;
    private byte type;

    public int getType() {
        return this.type;
    }

    protected void func_70069_a(float f) {
    }

    public EntityRRMecha(World par1World) {
        super(par1World);
        int hp;
        int dam;
        float[] sizes = new float[]{0.0f, 0.5f, 1.0f};
        this.field_70728_aV = 50;
        this.type = (byte)(Math.random() * 3.0);
        this.func_70105_a(3.0f * (sizes[this.type] + 1.0f), 4.0f * (sizes[this.type] + 1.0f));
        int n = this.type == 0 ? DBCConfig.NPC_RRMech1_Dam : (dam = this.type == 1 ? DBCConfig.NPC_RRMech2_Dam : DBCConfig.NPC_RRMech3_Dam);
        int n2 = this.type == 0 ? DBCConfig.NPC_RRMech1_HP : (hp = this.type == 1 ? DBCConfig.NPC_RRMech2_HP : DBCConfig.NPC_RRMech3_HP);
        if (dam != 20 || hp != 200) {
            this.getEntityData().func_74780_a("jrmcSpawnInitiatedCAT", (double)dam);
            this.getEntityData().func_74780_a("jrmcSpawnInitiatedCHP", (double)hp);
        }
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(200.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(20.0);
    }

    @Override
    public void func_70071_h_() {
        super.func_70071_h_();
        float range = 25 * (this.type + 1);
        if (!this.field_70170_p.field_72995_K && this.field_70789_a != null && this.field_70789_a.func_70089_S() && this.field_70789_a.func_70032_d((Entity)this) < range) {
            if (this.type == 0) {
                if (this.field_70173_aa % 100 < 31 && this.field_70173_aa % 15 == 0) {
                    EntityPrjtls_1 var8 = new EntityPrjtls_1(this.field_70170_p, (Entity)this, this.field_70789_a, 1.8f, 1.0f, 0);
                    this.field_70170_p.func_72956_a((Entity)this, "jinryuudragonbc:DBC4.rocket_shot", 0.6f, this.field_70170_p.field_73012_v.nextFloat() * 0.1f + 0.9f);
                    this.field_70170_p.func_72838_d((Entity)var8);
                }
            } else if (this.type == 1) {
                if (this.field_70173_aa % 100 == 0 && (int)(Math.random() * (double)(6 / (this.type + 1))) == 0) {
                    EntityPrjtls_1 var8 = new EntityPrjtls_1(this.field_70170_p, (Entity)this, this.field_70789_a, 1.5f, 1.0f, 1);
                    this.field_70170_p.func_72956_a((Entity)this, "jinryuudragonbc:DBC4.rocket_shot", 0.6f, this.field_70170_p.field_73012_v.nextFloat() * 0.1f + 0.9f);
                    this.field_70170_p.func_72838_d((Entity)var8);
                }
            } else if (this.type == 2 && this.field_70173_aa % 100 == 0 && (int)(Math.random() * 2.0) == 0) {
                EntityPrjtls_1 var8 = new EntityPrjtls_1(this.field_70170_p, (Entity)this, this.field_70789_a, 1.8f, 1.0f, 2);
                this.field_70170_p.func_72956_a((Entity)this, "jinryuudragonbc:DBC4.rocket_shot", 0.6f, this.field_70170_p.field_73012_v.nextFloat() * 0.1f + 0.9f);
                this.field_70170_p.func_72838_d((Entity)var8);
            }
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "jinryuudragonbc:npcs/rrmecha" + this.type + ".png";
    }

    @Override
    public boolean func_70601_bi() {
        return this.field_70170_p.func_72855_b(this.field_70121_D) && this.field_70170_p.func_72945_a((Entity)this, this.field_70121_D).isEmpty() && !this.field_70170_p.func_72953_d(this.field_70121_D);
    }

    @Override
    protected Entity func_70782_k() {
        return super.func_70782_k();
    }

    @Override
    public void func_70636_d() {
        super.func_70636_d();
    }

    protected void func_70628_a(boolean par1, int par2) {
        if (this.type == 2) {
            float[] chance = new float[]{12.0f, 5.0f, 3.0f};
            int drop_chance = (int)(Math.random() * 101.0);
            if ((float)drop_chance <= chance[2]) {
                this.func_145779_a(ItemsDBC.ItemChipTier3, 1);
            } else if ((float)drop_chance <= chance[1]) {
                this.func_145779_a(ItemsDBC.ItemChipTier2, 1);
            } else if ((float)drop_chance <= chance[0]) {
                this.func_145779_a(ItemsDBC.ItemAlienTechChipTier1, 1);
            }
        } else if (this.type == 1) {
            float[] chance = new float[]{8.0f, 4.0f};
            int drop_chance = (int)(Math.random() * 101.0);
            if ((float)drop_chance <= chance[1]) {
                this.func_145779_a(ItemsDBC.ItemChipTier2, 1);
            } else if ((float)drop_chance <= chance[0]) {
                this.func_145779_a(ItemsDBC.ItemAlienTechChipTier1, 1);
            }
        } else if (this.type == 0) {
            float[] chance = new float[]{7.0f};
            int drop_chance = (int)(Math.random() * 101.0);
            if ((float)drop_chance <= chance[0]) {
                this.func_145779_a(ItemsDBC.ItemAlienTechChipTier1, 1);
            }
        }
    }

    public boolean func_70085_c(EntityPlayer par1EntityPlayer) {
        return false;
    }

    @Override
    protected boolean func_70692_ba() {
        return true;
    }

    public void writeSpawnData(ByteBuf additionalData) {
        additionalData.writeByte((int)this.type);
    }

    public void readSpawnData(ByteBuf additionalData) {
        this.type = additionalData.readByte();
        float[] sizes = new float[]{0.0f, 0.5f, 1.0f};
        this.func_70105_a(3.0f * (sizes[this.type] + 1.0f), 4.0f * (sizes[this.type] + 1.0f));
    }

    @Override
    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.func_74774_a("type", this.type);
    }

    @Override
    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        this.type = (byte)(par1NBTTagCompound.func_74771_c("type") & 0xFF);
        float[] sizes = new float[]{0.0f, 0.5f, 1.0f};
        this.func_70105_a(3.0f * (sizes[this.type] + 1.0f), 4.0f * (sizes[this.type] + 1.0f));
    }
}

