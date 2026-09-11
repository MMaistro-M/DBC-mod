/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIWatchClosest2
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.DragonBC.common.DBCH;
import JinRyuu.DragonBC.common.mod_DragonBC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityDragon2
extends EntityCreature {
    public int randomSoundDelay = 0;
    public int timeBack = 0;
    private int age;
    private int jumpTicks = 0;
    protected Entity closestEntity;
    float var1 = 8.0f;

    public EntityDragon2(World par1World) {
        super(par1World);
        this.field_70130_N = 2.0f;
        this.field_70131_O = 25.0f;
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f));
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f));
        this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f));
        this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f));
    }

    public boolean func_70085_c(EntityPlayer par1EntityPlayer) {
        boolean var3;
        ItemStack var2 = par1EntityPlayer.field_71071_by.func_70448_g();
        boolean bl = var3 = var2 != null;
        if (this.func_70089_S()) {
            return true;
        }
        return super.func_70085_c(par1EntityPlayer);
    }

    protected void func_70619_bc() {
    }

    public void func_70071_h_() {
        if (this.randomSoundDelay <= 0 || --this.randomSoundDelay == 0) {
            // empty if block
        }
        this.field_70159_w *= 0.0;
        this.field_70179_y *= 0.0;
        super.func_70071_h_();
        ++this.age;
        if (this.age == 200) {
            mod_DragonBC.logger.info("Shenron has fulfilled a wish!");
            this.func_70106_y();
        }
        if (this.field_70170_p.field_72995_K) {
            DBCH.dragonSum((Entity)this);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "jinryuudragonbc:npcs/Dragon.png";
    }

    public boolean func_70601_bi() {
        return this.field_70170_p.func_72855_b(this.field_70121_D) && this.field_70170_p.func_72945_a((Entity)this, this.field_70121_D).isEmpty() && !this.field_70170_p.func_72953_d(this.field_70121_D);
    }

    public void func_70636_d() {
        if (this.jumpTicks > 0) {
            --this.jumpTicks;
        }
        if (this.field_70716_bi > 0) {
            double d0 = this.field_70165_t + (this.field_70709_bj - this.field_70165_t) / (double)this.field_70716_bi;
            double d1 = this.field_70163_u + (this.field_70710_bk - this.field_70163_u) / (double)this.field_70716_bi;
            double d2 = this.field_70161_v + (this.field_110152_bk - this.field_70161_v) / (double)this.field_70716_bi;
            double d3 = MathHelper.func_76138_g((double)(this.field_70712_bm - (double)this.field_70177_z));
            this.field_70177_z = (float)((double)this.field_70177_z + d3 / (double)this.field_70716_bi);
            this.field_70125_A = (float)((double)this.field_70125_A + (this.field_70705_bn - (double)this.field_70125_A) / (double)this.field_70716_bi);
            --this.field_70716_bi;
            this.func_70107_b(d0, d1, d2);
            this.func_70101_b(this.field_70177_z, this.field_70125_A);
        } else if (!this.func_70613_aW()) {
            this.field_70159_w *= 0.98;
            this.field_70181_x *= 0.98;
            this.field_70179_y *= 0.98;
        }
        if (Math.abs(this.field_70159_w) < 0.005) {
            this.field_70159_w = 0.0;
        }
        if (Math.abs(this.field_70181_x) < 0.005) {
            this.field_70181_x = 0.0;
        }
        if (Math.abs(this.field_70179_y) < 0.005) {
            this.field_70179_y = 0.0;
        }
        this.field_70170_p.field_72984_F.func_76320_a("ai");
        if (this.func_70610_aX()) {
            this.field_70703_bu = false;
            this.field_70702_br = 0.0f;
            this.field_70701_bs = 0.0f;
            this.field_70704_bt = 0.0f;
        } else if (this.func_70613_aW()) {
            if (this.func_70650_aV()) {
                this.field_70170_p.field_72984_F.func_76320_a("newAi");
                this.func_70619_bc();
                this.field_70170_p.field_72984_F.func_76319_b();
            } else {
                this.field_70170_p.field_72984_F.func_76320_a("oldAi");
                this.field_70170_p.field_72984_F.func_76319_b();
                this.field_70759_as = this.field_70177_z;
            }
        }
        this.field_70170_p.field_72984_F.func_76319_b();
        this.field_70170_p.field_72984_F.func_76320_a("jump");
        if (this.field_70703_bu) {
            if (!this.func_70090_H() && !this.func_70058_J()) {
                if (this.field_70122_E && this.jumpTicks == 0) {
                    this.func_70664_aZ();
                    this.jumpTicks = 10;
                }
            } else {
                this.field_70181_x += (double)0.04f;
            }
        } else {
            this.jumpTicks = 0;
        }
        this.field_70170_p.field_72984_F.func_76319_b();
        this.field_70170_p.field_72984_F.func_76320_a("travel");
        this.field_70702_br *= 0.98f;
        this.field_70701_bs *= 0.98f;
        this.field_70704_bt *= 0.9f;
        this.field_70170_p.field_72984_F.func_76319_b();
        this.field_70170_p.field_72984_F.func_76320_a("push");
        if (!this.field_70170_p.field_72995_K) {
            this.func_85033_bc();
        }
        this.field_70170_p.field_72984_F.func_76319_b();
        this.field_70170_p.field_72984_F.func_76320_a("looting");
        if (this.field_70170_p.field_72995_K || !this.func_98052_bS() || this.field_70729_aU || this.field_70170_p.func_82736_K().func_82766_b("mobGriefing")) {
            // empty if block
        }
        this.field_70170_p.field_72984_F.func_76319_b();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(1.0E-6);
        this.field_70159_w *= 1.0E-4;
        this.field_70179_y *= 1.0E-4;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10000.0);
    }
}

