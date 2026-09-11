/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.monster.IMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.EnumSkyBlock
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.Npcs.EntityDBCWildlife;
import JinRyuu.DragonBC.common.Npcs.aai.AAiDBCFlyingCharge;
import JinRyuu.DragonBC.common.Npcs.aai.AAiDBCGroundDash;
import JinRyuu.DragonBC.common.Npcs.aai.AAiDBCGroundJump;
import JinRyuu.DragonBC.common.Npcs.aai.AAiDBCKiAttackCharge;
import JinRyuu.DragonBC.common.Npcs.aai.AAiDBCTeleport;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.client.JGRenderHelper;
import JinRyuu.JRMCore.entity.EntityEnergyAtt;
import JinRyuu.JRMCore.entity.aai.AAi;
import JinRyuu.JRMCore.entity.aai.AAiSystem;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCAAiDifficulty;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityDBC
extends EntityCreature
implements IMob {
    public static final double DISTANCE_LENGTH_MAX = 64.0;
    public static final double DISTANCE_LENGTH_MIN = 4.0;
    protected String texture;
    private double moveSpeed = DBCConfig.EnemyDefaultMoveSpeed;
    private int rang = 0;
    private boolean updtd = false;
    public int angerLevel = 0;
    private int aggroCooldown = 0;
    public int prevAttackCounter = 0;
    public int attackCounter = 0;
    private Entity targetedEntity = null;
    private byte data1 = 1;
    private byte data2 = 0;
    private byte data3 = 0;
    private byte data4 = 0;
    private boolean blst = false;
    public boolean canFly = true;
    public boolean canFireKiAttacks = true;
    public boolean kiBarrageType0 = true;
    public int kiAttackTimer = 80;
    public int kiAttackTimerMin = 5;
    public boolean chargingKiAttack = false;
    public int chargingKiAttackTimer = 0;
    public int chargingKiAttackTimerMax = 0;
    public AAiSystem aaiSystem;
    public boolean hasAAiKiChargeSystem = false;
    public Entity lockedBy = null;
    public int difficultyID = 0;
    public boolean aggressive = true;
    public static final int STYLE_EASY = 0;
    public static final int STYLE_MEDIUM = 1;
    public static final int STYLE_HARD = 2;
    public static final int STYLE_INSANE = 3;

    public void setData1(int data) {
        this.data1 = (byte)data;
    }

    public void setData2(int data) {
        this.data2 = (byte)data;
    }

    public void setData3(int data) {
        this.data3 = (byte)data;
    }

    public void setData4(int data) {
        this.data4 = (byte)data;
    }

    public boolean doBlst() {
        if (this.blst) {
            this.blst = false;
            return true;
        }
        return false;
    }

    public EntityDBC(World par1World) {
        super(par1World);
        this.field_70728_aV = 5;
        this.func_94058_c("");
        this.func_94061_f(false);
        this.moveSpeed = DBCConfig.EnemyDefaultMoveSpeed;
    }

    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return this.texture;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1000.0);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(10.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(23, (Object)new Integer(0));
    }

    protected boolean func_70650_aV() {
        return false;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        Entity var3 = par1DamageSource.func_76346_g();
        if (var3 instanceof EntityPlayer) {
            int e = 1;
            if (var3 instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)var3;
                JRMCoreH.expPls(player, e);
            }
        }
        super.func_70645_a(par1DamageSource);
    }

    public int getAttackStrength(Entity par1Entity, int AttPow) {
        ItemStack var2 = this.func_70694_bm();
        boolean var3 = false;
        int value = (int)((float)(AttPow + 1) * 0.5f);
        int dbcA = AttPow - this.field_70146_Z.nextInt(value);
        if (dbcA < 0) {
            dbcA = 0;
        }
        return dbcA;
    }

    protected void func_70626_be() {
        boolean doSuperActionUpdate = true;
        if (!(this instanceof EntityDBCWildlife) && this.angerLevel > 0) {
            doSuperActionUpdate = false;
            this.field_70143_R = 0.0f;
            this.prevAttackCounter = this.attackCounter;
            if (this.targetedEntity != null && this.targetedEntity.field_70128_L) {
                this.targetedEntity = null;
                this.field_70180_af.func_75692_b(23, (Object)0);
                super.func_70626_be();
            }
            if (this.targetedEntity == null || this.aggroCooldown-- <= 0) {
                this.targetedEntity = this.field_70170_p.func_72856_b((Entity)this, 100.0);
                super.func_70626_be();
                if (this.targetedEntity != null) {
                    this.field_70180_af.func_75692_b(23, (Object)this.targetedEntity.func_145782_y());
                    this.aggroCooldown = 20;
                }
            }
            float r = this.field_70130_N / 2.0f + 3.5f;
            if (this.field_70789_a != null && this.field_70789_a.func_70089_S() && this.field_70789_a.func_70032_d((Entity)this) < r) {
                AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)(this.field_70165_t - (double)r), (double)(this.field_70163_u - (double)r), (double)(this.field_70161_v - (double)r), (double)(this.field_70165_t + (double)r), (double)(this.field_70163_u + (double)r), (double)(this.field_70161_v + (double)r));
                List list = this.field_70170_p.func_72872_a(EntityPlayer.class, aabb);
                double distance = this.getXZDistanceToEntity(this.targetedEntity);
                if (this.field_70724_aR - (!DBCConfig.AaiDisabled && distance < 0.5 ? DBCConfig.EnemyDefaultAttackTimer - DBCConfig.EnemyDefaultShortRangeAttackTimer : 0) <= 0) {
                    for (int i = 0; i < list.size(); ++i) {
                        EntityPlayer player = (EntityPlayer)list.get(i);
                        this.func_70652_k((Entity)player);
                    }
                    this.field_70724_aR = DBCConfig.EnemyDefaultAttackTimer;
                    this.field_70170_p.func_72956_a((Entity)this, "jinryuudragonbc:DBC3.force", 0.5f, this.field_70170_p.field_73012_v.nextFloat() * 0.1f + 0.9f);
                }
            }
            if (this.targetedEntity != null && this.targetedEntity.func_70089_S() && this.targetedEntity.func_70068_e((Entity)this) < 4096.0) {
                double distanceMulti;
                double ogTimer = this.kiAttackTimer >= 10 ? this.kiAttackTimer : 80;
                int fireAttackRate = (int)(ogTimer / (distanceMulti = this.targetedEntity.func_70068_e((Entity)this) / 50.0 * 0.1 + 1.0));
                if (fireAttackRate < this.kiAttackTimerMin) {
                    fireAttackRate = this.kiAttackTimerMin;
                }
                double d5 = this.targetedEntity.field_70165_t - this.field_70165_t;
                double d6 = this.targetedEntity.field_70121_D.field_72338_b + (double)(this.targetedEntity.field_70131_O / 2.0f) - (this.field_70163_u + (double)(this.field_70131_O / 2.0f));
                double d7 = this.targetedEntity.field_70161_v - this.field_70161_v;
                this.field_70761_aq = this.field_70177_z = -((float)Math.atan2(d5, d7)) * 180.0f / (float)Math.PI;
                if (this.canFireKiAttacks && this.func_70685_l(this.targetedEntity)) {
                    if (!this.hasAAiKiChargeSystem || this.chargingKiAttack) {
                        ++this.attackCounter;
                        if (this.attackCounter >= fireAttackRate) {
                            this.field_70170_p.func_72956_a((Entity)this, "jinryuudragonbc:DBC2.basicbeam_fire", 0.5f, 1.0f);
                            byte type = this.data1;
                            byte speed = 1;
                            byte effect = 1;
                            byte color = this.data2;
                            byte density = 1;
                            byte sincantation = 0;
                            byte sfire = 0;
                            byte smove = 0;
                            byte[] sts = JRMCoreH.techDBCstatsDefault;
                            int dmg = (int)(50.0f / (this.data1 == 6 ? 5.0f : 1.0f));
                            int f = (int)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
                            int dmg1 = (int)((float)f * 1.2f / (this.data1 == 6 ? 5.0f : 1.0f));
                            int cst = dmg1 / 2;
                            EntityEnergyAtt kiAttack = this.data3 == 0 ? new EntityEnergyAtt((EntityLivingBase)this, type, speed, dmg, effect, color, density, sincantation, sfire, smove, 50, dmg1, cst, sts, 0) : new EntityEnergyAtt((EntityLivingBase)this, type, speed, dmg, effect, color, this.data4, density, sincantation, sfire, smove, 50, dmg1, cst, sts, 0);
                            double d8 = (double)this.field_70130_N + 0.5;
                            Vec3 vec3 = this.func_70676_i(1.0f);
                            kiAttack.field_70165_t = this.field_70165_t + vec3.field_72450_a * d8;
                            kiAttack.field_70163_u = this.field_70163_u + (double)(this.field_70131_O / 2.0f) + 0.5;
                            kiAttack.field_70161_v = this.field_70161_v + vec3.field_72449_c * d8;
                            this.field_70170_p.func_72838_d((Entity)kiAttack);
                            if (this.data1 != 6 || (int)(Math.random() * 8.0) == 0) {
                                this.attackCounter = -40;
                                this.blst = true;
                            } else {
                                this.attackCounter = this.kiBarrageType0 ? fireAttackRate - 10 : fireAttackRate;
                                this.blst = false;
                            }
                        }
                    }
                } else if (this.attackCounter > 0) {
                    --this.attackCounter;
                }
            } else if (this.attackCounter > 0) {
                --this.attackCounter;
            }
        }
        if (doSuperActionUpdate) {
            super.func_70626_be();
        }
    }

    public void lookForTarget() {
        if (this.angerLevel <= 0 && (this.targetedEntity == null || this.aggroCooldown-- <= 0)) {
            this.targetedEntity = this.field_70170_p.func_72856_b((Entity)this, 100.0);
            super.func_70626_be();
            if (this.targetedEntity != null) {
                this.field_70180_af.func_75692_b(23, (Object)this.targetedEntity.func_145782_y());
                this.aggroCooldown = 20;
                this.becomeAngryAt(this.targetedEntity);
            }
        }
    }

    public int updateDataInt(int i) {
        return this.field_70180_af.func_75679_c(i);
    }

    public void func_70636_d() {
        this.func_82168_bl();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(this.moveSpeed);
        super.func_70636_d();
    }

    public void func_70071_h_() {
        if (this.field_70170_p.field_72995_K) {
            ++this.rang;
        }
        if (this.field_70170_p.field_72995_K && this.rang > 100) {
            this.rang = 0;
            int i = this.updateDataInt(23);
            Entity entity = this.targetedEntity = i > 0 ? this.field_70170_p.func_73045_a(i) : null;
        }
        if (!this.field_70170_p.field_72995_K && !this.updtd) {
            this.updtd = true;
        }
        if (!(this instanceof EntityDBCWildlife) && this.targetedEntity != null && this.func_70685_l(this.targetedEntity) && this.canFly) {
            boolean client = this.targetedEntity.field_70170_p.field_72995_K;
            double posYTarget = this.targetedEntity.field_70163_u - (client ? (JGRenderHelper.isClientPlayer(this.targetedEntity) ? 1.6 : 0.0) : 0.0);
            if (!(!(posYTarget - this.field_70163_u > 5.0) && this.targetedEntity.field_70122_E || this.field_70703_bu)) {
                double yDistance = 0.0;
                double posY = this.field_70163_u - (client ? (JGRenderHelper.isClientPlayer((Entity)this) ? 1.6 : 0.0) : 0.0);
                double d1 = posYTarget - posY;
                if (d1 < 0.0) {
                    d1 *= -1.0;
                }
                yDistance = d1;
                double clientPlayerPosDiff = this.field_70170_p.field_72995_K ? (JGRenderHelper.isClientPlayer(this.targetedEntity) ? 1.6f : 0.0f) : 0.0f;
                double targetPos = this.targetedEntity.field_70163_u - clientPlayerPosDiff;
                this.field_70143_R = 0.0f;
                if (yDistance > 0.5) {
                    if (targetPos > this.field_70163_u - 0.5) {
                        this.field_70181_x += yDistance > 0.1 ? 0.1 : (yDistance < -0.01 ? -0.01 : yDistance);
                    }
                } else {
                    this.field_70181_x = 0.01;
                }
            }
        }
        if (this.aaiSystem != null) {
            this.aaiSystem.update();
        }
        if (this.lockedBy != null && !this.lockedBy.func_70089_S()) {
            this.lockedBy = null;
        }
        super.func_70071_h_();
    }

    protected Entity func_70782_k() {
        EntityPlayer entityplayer = this.field_70170_p.func_72856_b((Entity)this, 16.0);
        return entityplayer != null && this.func_70685_l((Entity)entityplayer) ? entityplayer : null;
    }

    public boolean func_70652_k(Entity entity) {
        if (!(entity instanceof EntityDBC)) {
            boolean flag;
            int f = (int)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
            f = this.getAttackStrength(entity, f);
            int i = 0;
            if (entity instanceof EntityLivingBase) {
                f = (int)((float)f + EnchantmentHelper.func_77512_a((EntityLivingBase)this, (EntityLivingBase)((EntityLivingBase)entity)));
                i += EnchantmentHelper.func_77507_b((EntityLivingBase)this, (EntityLivingBase)((EntityLivingBase)entity));
            }
            if (flag = entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), (float)f)) {
                int j;
                if (i > 0) {
                    entity.func_70024_g((double)(-MathHelper.func_76126_a((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * (float)i * 0.5f), 0.1, (double)(MathHelper.func_76134_b((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * (float)i * 0.5f));
                    this.field_70159_w *= 0.6;
                    this.field_70179_y *= 0.6;
                }
                if ((j = EnchantmentHelper.func_90036_a((EntityLivingBase)this)) > 0) {
                    entity.func_70015_d(j * 4);
                }
                if (entity instanceof EntityLivingBase) {
                    EnchantmentHelper.func_151384_a((EntityLivingBase)((EntityLivingBase)entity), (Entity)this);
                }
                EnchantmentHelper.func_151385_b((EntityLivingBase)this, (Entity)entity);
            }
            return flag;
        }
        return false;
    }

    protected void func_70785_a(Entity par1Entity, float par2) {
        if (this.field_70724_aR <= 0 && par2 < this.field_70130_N / 2.0f + 2.5f && par1Entity.field_70121_D.field_72337_e > this.field_70121_D.field_72338_b && par1Entity.field_70121_D.field_72338_b < this.field_70121_D.field_72337_e) {
            this.field_70724_aR = DBCConfig.EnemyDefaultAttackTimer;
            this.func_70652_k(par1Entity);
        }
    }

    public float func_70783_a(int par1, int par2, int par3) {
        return 0.5f - this.field_70170_p.func_72801_o(par1, par2, par3);
    }

    protected boolean isValidLightLevel() {
        int k;
        int j;
        int i = MathHelper.func_76128_c((double)this.field_70165_t);
        if (this.field_70170_p.func_72972_b(EnumSkyBlock.Sky, i, j = MathHelper.func_76128_c((double)this.field_70121_D.field_72338_b), k = MathHelper.func_76128_c((double)this.field_70161_v)) > this.field_70146_Z.nextInt(32)) {
            return false;
        }
        int l = this.field_70170_p.func_72957_l(i, j, k);
        if (this.field_70170_p.func_72911_I()) {
            int i1 = this.field_70170_p.field_73008_k;
            this.field_70170_p.field_73008_k = 10;
            l = this.field_70170_p.func_72957_l(i, j, k);
            this.field_70170_p.field_73008_k = i1;
        }
        return l <= this.field_70146_Z.nextInt(8);
    }

    public boolean func_70601_bi() {
        return true;
    }

    public Entity getTargetedEntity() {
        return this.targetedEntity;
    }

    public boolean getupdtd() {
        return this.updtd;
    }

    public void becomeAngryAtAPlayer() {
        List list = this.field_70170_p.func_72872_a(EntityPlayer.class, this.field_70121_D.func_72314_b(16.0, 16.0, 16.0));
        if (!list.isEmpty()) {
            Entity entity = (Entity)list.get(0);
            this.becomeAngryAt(entity);
        }
    }

    public void becomeAngryAtAllPlayer() {
        List list = this.field_70170_p.func_72872_a(EntityPlayer.class, this.field_70121_D.func_72314_b(16.0, 16.0, 16.0));
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); ++i) {
                Entity entity = (Entity)list.get(i);
                this.becomeAngryAt(entity);
            }
        }
    }

    public void becomeAngryAtClosestPlayer() {
        List list = this.field_70170_p.func_72872_a(EntityPlayer.class, this.field_70121_D.func_72314_b(16.0, 16.0, 16.0));
        if (!list.isEmpty()) {
            Entity entityClosest = null;
            int rangeClosest = -1;
            for (int i = 0; i < list.size(); ++i) {
                Entity entity = (Entity)list.get(i);
                int range = (int)(entity != null && entity.func_70089_S() ? entity.func_70032_d((Entity)this) : 0.0f);
                if (range <= rangeClosest) continue;
                entityClosest = entity;
                rangeClosest = range;
            }
            if (entityClosest != null) {
                this.becomeAngryAt(entityClosest);
            }
        }
    }

    private void becomeAngryAt(Entity entity) {
        this.field_70789_a = entity;
        this.angerLevel = 400 + this.field_70146_Z.nextInt(400);
    }

    public void becomeAngryAt2(Entity entity) {
        this.becomeAngryAt(entity);
    }

    public void setAttributes(int damage, int health, int AttPow, int HePo) {
        if (damage != AttPow || health != HePo) {
            this.getEntityData().func_74780_a("jrmcSpawnInitiatedCAT", (double)damage);
            this.getEntityData().func_74780_a("jrmcSpawnInitiatedCHP", (double)health);
        }
    }

    public double getXZDistanceToEntity(Entity targetEntity) {
        double d2;
        double d0 = targetEntity.field_70165_t - this.field_70165_t;
        if (d0 < 0.0) {
            d0 *= -1.0;
        }
        if ((d2 = targetEntity.field_70161_v - this.field_70161_v) < 0.0) {
            d2 *= -1.0;
        }
        return d0 + d2;
    }

    public double getYDistanceToEntity(Entity targetEntity) {
        double d1 = targetEntity.field_70163_u - this.field_70163_u;
        if (d1 < 0.0) {
            d1 *= -1.0;
        }
        return d1;
    }

    public double getYDistanceToEntityWithClientDiff(Entity targetEntity) {
        boolean client = targetEntity.field_70170_p.field_72995_K;
        double posYTarget = targetEntity.field_70163_u - (client ? (JGRenderHelper.isClientPlayer(targetEntity) ? 1.6 : 0.0) : 0.0);
        double posY = this.field_70163_u - (client ? (JGRenderHelper.isClientPlayer((Entity)this) ? 1.6 : 0.0) : 0.0);
        double d1 = posYTarget - posY;
        if (d1 < 0.0) {
            d1 *= -1.0;
        }
        return d1;
    }

    public boolean isJumping() {
        return this.field_70703_bu;
    }

    public void useJump() {
        this.func_70664_aZ();
    }

    public static double[] values(double ... values) {
        return values;
    }

    public void setBattleStyle(double movementSpeed, double[] highJump, double[] dash, double[] flyingDash, double[] backawayKiCharge) {
        if (!DBCConfig.AaiDisabled) {
            if (movementSpeed != -1.0) {
                this.moveSpeed *= movementSpeed;
            }
            this.aaiSystem = new AAiSystem((Entity)this, new AAi[0]);
            if (highJump != null) {
                this.aaiSystem.addAAi(new AAiDBCGroundJump(highJump));
            }
            if (dash != null) {
                this.aaiSystem.addAAi(new AAiDBCGroundDash(dash));
            }
            if (flyingDash != null && this.canFly) {
                this.aaiSystem.addAAi(new AAiDBCFlyingCharge(flyingDash));
            }
            if (backawayKiCharge != null && this.canFireKiAttacks) {
                this.aaiSystem.addAAi(new AAiDBCKiAttackCharge(backawayKiCharge));
                this.hasAAiKiChargeSystem = true;
            }
        }
    }

    public void setEasyDifficulty() {
        this.setDifficulty(0);
    }

    public void setMediumDifficulty() {
        this.setDifficulty(1);
    }

    public void setHardDifficulty() {
        this.setDifficulty(2);
    }

    public void setInsaneDifficulty() {
        this.setDifficulty(3);
    }

    public void setDifficulty(int difficulty) {
        if (this.aaiSystem != null) {
            this.aaiSystem.aais.clear();
        } else {
            this.aaiSystem = new AAiSystem((Entity)this, new AAi[0]);
        }
        if (DBCConfig.AaiForceDifficulty != -1) {
            difficulty = DBCConfig.AaiForceDifficulty;
        }
        this.difficultyID = difficulty;
        this.setBattleStyle(JGConfigDBCAAiDifficulty.SpeedMulti[this.difficultyID], EntityDBC.values(JGConfigDBCAAiDifficulty.JumpMulti[this.difficultyID], JGConfigDBCAAiDifficulty.JumpRate[this.difficultyID], JGConfigDBCAAiDifficulty.JumpMulti2[this.difficultyID], JGConfigDBCAAiDifficulty.JumpLimit[this.difficultyID], JGConfigDBCAAiDifficulty.JumpLimit2[this.difficultyID]), EntityDBC.values(JGConfigDBCAAiDifficulty.GroundDashSpeedMulti[this.difficultyID], 1.0, JGConfigDBCAAiDifficulty.GroundDashSpeedMulti2[this.difficultyID], JGConfigDBCAAiDifficulty.GroundDashLimit[this.difficultyID]), EntityDBC.values(JGConfigDBCAAiDifficulty.FlyingDashMulti[this.difficultyID], 1.0, JGConfigDBCAAiDifficulty.FlyingDashLimit[this.difficultyID]), EntityDBC.values(JGConfigDBCAAiDifficulty.KiAttackChargeMulti[this.difficultyID], 0.1, JGConfigDBCAAiDifficulty.KiAttackChargeLimit[this.difficultyID]));
    }

    public void setKiUsage(boolean canFly, boolean canFireKiAttacks) {
        this.canFly = canFly;
        this.canFireKiAttacks = canFireKiAttacks;
    }

    public void setKiUsageAndDifficulty(boolean canFly, boolean canFireKiAttacks) {
        this.setKiUsageAndDifficulty(canFly, canFireKiAttacks, -1);
    }

    public void setKiUsageAndDifficulty(boolean canFly, boolean canFireKiAttacks, int difficulty) {
        this.setKiUsage(canFly, canFireKiAttacks);
        if (difficulty != -1) {
            this.setDifficulty(difficulty);
        }
    }

    public boolean isLocked() {
        return this.lockedBy != null;
    }

    public void addAAiTeleport(int rateMin, int rateMax, String sound) {
        if (this.aaiSystem == null) {
            this.aaiSystem = new AAiSystem((Entity)this, new AAi[0]);
        }
        this.aaiSystem.addAAi(new AAiDBCTeleport(rateMin, rateMax, sound));
    }

    public void addAAiTeleport(int rateMin, int rateMax) {
        if (this.aaiSystem == null) {
            this.aaiSystem = new AAiSystem((Entity)this, new AAi[0]);
        }
        this.aaiSystem.addAAi(new AAiDBCTeleport(new int[]{rateMin, rateMax}));
    }

    public void addAAiTeleport() {
        if (this.aaiSystem == null) {
            this.aaiSystem = new AAiSystem((Entity)this, new AAi[0]);
        }
        this.aaiSystem.addAAi(new AAiDBCTeleport(new int[]{JGConfigDBCAAiDifficulty.TeleportRateMin[this.difficultyID], JGConfigDBCAAiDifficulty.TeleportRateMax[this.difficultyID]}));
    }

    public void addAAiTeleport(String sound) {
        if (this.aaiSystem == null) {
            this.aaiSystem = new AAiSystem((Entity)this, new AAi[0]);
        }
        this.aaiSystem.addAAi(new AAiDBCTeleport(JGConfigDBCAAiDifficulty.TeleportRateMin[this.difficultyID], JGConfigDBCAAiDifficulty.TeleportRateMax[this.difficultyID], sound));
    }

    public boolean func_70097_a(DamageSource ds, float f) {
        boolean ret = super.func_70097_a(ds, f);
        Entity target = ds.func_76346_g();
        if (target != null && target.func_70089_S() && this.targetedEntity == null) {
            this.lookForTarget();
        }
        return ret;
    }
}

