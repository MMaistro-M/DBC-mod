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
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.DragonBC.common.DBCH;
import JinRyuu.DragonBC.common.Npcs.EntityPorunga2;
import JinRyuu.DragonBC.common.mod_DragonBC;
import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityPorunga
extends EntityCreature {
    public int randomSoundDelay = 0;
    private int time = 0;
    public int timeBack = 0;
    private boolean granted = false;
    protected Entity closestEntity;
    private float maxDistanceForPlayer = 4.0f;
    private int lookTime;
    private Class watchedClass;
    float var1 = 8.0f;
    private int jumpTicks = 0;

    public EntityPorunga(World par1World) {
        super(par1World);
        this.field_70728_aV = 0;
        this.field_70130_N = 1.0f;
        this.field_70131_O = 32.0f;
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0f));
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0f));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0f));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0f));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0f));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0f));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0f));
        this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0f));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0f));
        this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0f));
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0f));
    }

    protected void func_70619_bc() {
    }

    public boolean func_70085_c(EntityPlayer par1EntityPlayer) {
        boolean var3;
        ItemStack var2 = par1EntityPlayer.field_71071_by.func_70448_g();
        boolean bl = var3 = var2 != null;
        if (this.func_70089_S()) {
            if (!this.field_70170_p.field_72995_K) {
                if (!this.granted) {
                    this.granted = true;
                    JRMCoreH.setInt(3, par1EntityPlayer, "jrmcWishes");
                    JRMCoreH.setInt(1, par1EntityPlayer, "jrmcDrgn");
                }
            } else if (!this.granted) {
                this.granted = true;
                par1EntityPlayer.openGui((Object)mod_DragonBC.instance, 3, par1EntityPlayer.field_70170_p, (int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v);
            }
            if (!this.field_70170_p.field_72995_K) {
                EntityPorunga2 Dragon2 = new EntityPorunga2(par1EntityPlayer.field_70170_p);
                Dragon2.func_70012_b((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v, this.field_70177_z, 0.0f);
                Dragon2.timeBack = this.timeBack;
                this.field_70170_p.func_72838_d((Entity)Dragon2);
            }
            this.func_70106_y();
            return true;
        }
        return super.func_70085_c(par1EntityPlayer);
    }

    public void shouldExecute() {
        this.closestEntity = this.field_70170_p.func_72890_a((Entity)this, (double)this.maxDistanceForPlayer);
        if (this.closestEntity != null) {
            this.watchedClass = EntityPlayer.class;
            this.func_70671_ap().func_75650_a(this.closestEntity.field_70165_t, this.closestEntity.field_70163_u + 2.0, this.closestEntity.field_70161_v, 10.0f, (float)this.func_70646_bf());
        }
    }

    public void func_70071_h_() {
        float r;
        AxisAlignedBB aabb;
        List list;
        if (this.randomSoundDelay <= 0 || --this.randomSoundDelay == 0) {
            // empty if block
        }
        ++this.time;
        if (this.time == 1) {
            mod_DragonBC.logger.info("Porunga is Summoned!");
        }
        if (this.field_70170_p.field_72995_K) {
            DBCH.dragonSum((Entity)this);
        }
        if ((list = this.field_70170_p.func_72872_a(EntityPlayer.class, aabb = AxisAlignedBB.func_72330_a((double)(this.field_70165_t - (double)(r = 30.0f)), (double)(this.field_70163_u - (double)r), (double)(this.field_70161_v - (double)r), (double)(this.field_70165_t + (double)r), (double)(this.field_70163_u + (double)r), (double)(this.field_70161_v + (double)r)))).size() == 0) {
            this.func_70106_y();
        }
        super.func_70071_h_();
        this.shouldExecute();
    }

    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "jinryuudragonbc:npcs/Porunga.png";
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
        EntityPlayer var2 = this.field_70170_p.func_72890_a((Entity)this, (double)this.var1);
        if (var2 != null) {
            this.closestEntity = var2;
            this.field_70700_bx = 10 + this.field_70146_Z.nextInt(20);
        } else {
            this.field_70704_bt = (this.field_70146_Z.nextFloat() - 0.5f) * 20.0f;
        }
        if (this.closestEntity != null) {
            this.func_70625_a(this.closestEntity, 10.0f, this.func_70646_bf());
            if (this.field_70700_bx-- <= 0 || this.closestEntity.field_70128_L || this.closestEntity.func_70068_e((Entity)this) > (double)(this.var1 * this.var1)) {
                this.closestEntity = null;
            }
        }
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50000.0);
    }
}

