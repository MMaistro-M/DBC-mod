/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraftforge.common.ForgeHooks
 */
package hedaox.ninjinentities.entities;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.Npcs.EntityDBCNeut;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.entity.EntityCusPar;
import JinRyuu.JRMCore.entity.EntityEnergyAtt;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import hedaox.ninjinentities.Main;
import hedaox.ninjinentities.config.ModConfig;
import hedaox.ninjinentities.network.MessageSendEntityToSpark;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class EntityDBCNinjin
extends EntityDBCNeut {
    private EntityPlayer spwner = null;
    private int noSpwnr = DBCConfig.mdat;
    public int angerLevel = 0;
    protected int aggroCooldown = 0;
    public int prevAttackCounter = 0;
    public int attackCounter = 0;
    protected Entity targetedEntity = null;
    private byte data1 = 1;
    private byte data2 = 0;
    private byte data3 = 0;
    private byte data4 = 0;
    private byte[] attacksType = new byte[]{1};
    private byte[] attacksColor = new byte[]{0};
    private boolean blst = false;
    public int alignment = 83;
    public boolean isTrainer = false;
    public boolean hasAnAura = false;
    public float auraRed = 100.0f;
    public float auraGreen = 125.0f;
    public float auraBlue = 255.0f;
    public float auraRed2 = 100.0f;
    public float auraGreen2 = 125.0f;
    public float auraBlue2 = 255.0f;
    public MindState mindState = MindState.NEUTRAL;
    public boolean canTeleport = false;
    private int wait = 0;
    private int m = 1;
    private int rang = 0;
    public boolean auraLightning = false;
    private int lightningCount = 0;
    private static double random = Math.random();
    public boolean hasAGodAura = false;
    private boolean updtd = false;
    private final double moveSpeed = DBCConfig.EnemyDefaultMoveSpeed;
    private int outOfRangeHits = 0;
    private static final double MELEE_RANGE = 3.5;
    private static final int TELEPORT_TRIGGER_HITS = 5;
    private int nextTeleportTick = 100;
    private int teleportCooldown = 160;
    private Map<EntityLivingBase, Integer> hateMap = new HashMap<EntityLivingBase, Integer>();

    public boolean isBlst() {
        return this.blst;
    }

    public EntityDBCNinjin(World par1World) {
        super(par1World);
    }

    public EntityDBCNinjin(World par1World, int _alignment, MindState _mindState, boolean _isTrainer, boolean _canTeleport, byte[] _attacksType, byte[] _attacksColors) {
        super(par1World);
        this.setMediumDifficulty();
        this.alignment = _alignment;
        this.isTrainer = _isTrainer;
        this.mindState = _mindState;
        this.canTeleport = _canTeleport;
        this.attacksType = _attacksType;
        this.attacksColor = _attacksColors;
        if (this.mindState == MindState.AGGRESSIVE) {
            this.angerLevel = 400;
        }
    }

    public EntityDBCNinjin(World par1World, int _alignment, MindState _mindState, boolean _isTrainer, boolean _canTeleport, byte[] _attacksType, byte[] _attacksColors, boolean _hasAnAura) {
        super(par1World);
        this.setMediumDifficulty();
        this.alignment = _alignment;
        this.isTrainer = _isTrainer;
        this.mindState = _mindState;
        this.canTeleport = _canTeleport;
        this.attacksType = _attacksType;
        this.attacksColor = _attacksColors;
        if (this.mindState == MindState.AGGRESSIVE) {
            this.angerLevel = 400;
        }
        this.hasAnAura = _hasAnAura;
    }

    public EntityDBCNinjin(World par1World, int _alignment, MindState _mindState, boolean _isTrainer, boolean _canTeleport, byte[] _attacksType, byte[] _attacksColors, float _auraRed, float _auraGreen, float _auraBlue) {
        super(par1World);
        this.setMediumDifficulty();
        this.alignment = _alignment;
        this.isTrainer = _isTrainer;
        this.mindState = _mindState;
        this.canTeleport = _canTeleport;
        this.attacksType = _attacksType;
        this.attacksColor = _attacksColors;
        if (this.mindState == MindState.AGGRESSIVE) {
            this.angerLevel = 400;
        }
        this.auraRed = _auraRed;
        this.auraGreen = _auraGreen;
        this.auraBlue = _auraBlue;
        this.hasAnAura = true;
    }

    public EntityDBCNinjin(World par1World, int _alignment, MindState _mindState, boolean _isTrainer, boolean _canTeleport, byte[] _attacksType, byte[] _attacksColors, float _auraRed, float _auraGreen, float _auraBlue, boolean _auraLightning) {
        super(par1World);
        this.setMediumDifficulty();
        this.alignment = _alignment;
        this.isTrainer = _isTrainer;
        this.mindState = _mindState;
        this.canTeleport = _canTeleport;
        this.attacksType = _attacksType;
        this.attacksColor = _attacksColors;
        if (this.mindState == MindState.AGGRESSIVE) {
            this.angerLevel = 400;
        }
        this.auraRed = _auraRed;
        this.auraGreen = _auraGreen;
        this.auraBlue = _auraBlue;
        this.hasAnAura = true;
        this.auraLightning = _auraLightning;
    }

    public EntityDBCNinjin(World par1World, int _alignment, MindState _mindState, boolean _isTrainer, boolean _canTeleport, byte[] _attacksType, byte[] _attacksColors, float _auraRed, float _auraGreen, float _auraBlue, boolean _auraLightning, boolean _auraGod) {
        super(par1World);
        this.setMediumDifficulty();
        this.alignment = _alignment;
        this.isTrainer = _isTrainer;
        this.mindState = _mindState;
        this.canTeleport = _canTeleport;
        this.attacksType = _attacksType;
        this.attacksColor = _attacksColors;
        if (this.mindState == MindState.AGGRESSIVE) {
            this.angerLevel = 400;
        }
        this.auraRed = _auraRed;
        this.auraGreen = _auraGreen;
        this.auraBlue = _auraBlue;
        this.hasAnAura = false;
        this.auraLightning = _auraLightning;
        this.hasAGodAura = _auraGod;
    }

    public EntityDBCNinjin(World par1World, int _alignment, MindState _mindState, boolean _isTrainer, boolean _canTeleport, byte[] _attacksType, byte[] _attacksColors, float _auraRed, float _auraGreen, float _auraBlue, boolean _auraLightning, boolean _auraGod, float _auraRed2, float _auraGreen2, float _auraBlue2) {
        super(par1World);
        this.setMediumDifficulty();
        this.alignment = _alignment;
        this.isTrainer = _isTrainer;
        this.mindState = _mindState;
        this.canTeleport = _canTeleport;
        this.attacksType = _attacksType;
        this.attacksColor = _attacksColors;
        if (this.mindState == MindState.AGGRESSIVE) {
            this.angerLevel = 400;
        }
        this.auraRed = _auraRed;
        this.auraGreen = _auraGreen;
        this.auraBlue = _auraBlue;
        this.auraRed2 = _auraRed2;
        this.auraGreen2 = _auraGreen2;
        this.auraBlue2 = _auraBlue2;
        this.hasAnAura = false;
        this.auraLightning = _auraLightning;
        this.hasAGodAura = _auraGod;
    }

    public EntityDBCNinjin(World par1World, int _alignment, MindState _mindState, boolean _isTrainer, boolean _canTeleport, byte[] _attacksType, byte[] _attacksColors, float _auraRed, float _auraGreen, float _auraBlue, boolean _auraLightning, boolean _auraGod, float _auraRed2, float _auraGreen2, float _auraBlue2, boolean _hasAnAura) {
        super(par1World);
        this.setMediumDifficulty();
        this.alignment = _alignment;
        this.isTrainer = _isTrainer;
        this.mindState = _mindState;
        this.canTeleport = _canTeleport;
        this.attacksType = _attacksType;
        this.attacksColor = _attacksColors;
        if (this.mindState == MindState.AGGRESSIVE) {
            this.angerLevel = 400;
        }
        this.auraRed = _auraRed;
        this.auraGreen = _auraGreen;
        this.auraBlue = _auraBlue;
        this.auraRed2 = _auraRed2;
        this.auraGreen2 = _auraGreen2;
        this.auraBlue2 = _auraBlue2;
        this.hasAnAura = _hasAnAura;
        this.auraLightning = _auraLightning;
        this.hasAGodAura = _auraGod;
    }

    @Override
    protected void func_70626_be() {
        if (this.mindState == MindState.PACIFIC) {
            this.angerLevel = 0;
        } else if (this.angerLevel >= 400) {
            this.becomeAngryAtAllPlayer();
        }
        if (this.angerLevel > 0) {
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
            boolean isInMeleeRange = false;
            if (this.targetedEntity != null) {
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
                    double d6 = this.targetedEntity.field_70163_u + (double)this.targetedEntity.func_70047_e() - (this.field_70163_u + (double)(this.field_70131_O / 2.0f));
                    double d7 = this.targetedEntity.field_70161_v - this.field_70161_v;
                    this.field_70761_aq = this.field_70177_z = -((float)Math.atan2(d5, d7)) * 180.0f / (float)Math.PI;
                    double horizontalDist = Math.hypot(d5, d7);
                    this.field_70125_A = (float)(-Math.atan2(d6, horizontalDist) * 180.0 / Math.PI);
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
            } else {
                super.func_70626_be();
            }
        }
    }

    @Override
    protected NBTTagCompound nbt(EntityPlayer p, String s) {
        NBTTagCompound nbt;
        if (s.contains("pres")) {
            if (!p.getEntityData().func_74764_b("PlayerPersisted")) {
                nbt = new NBTTagCompound();
                p.getEntityData().func_74782_a("PlayerPersisted", (NBTBase)nbt);
            } else {
                nbt = p.getEntityData().func_74775_l("PlayerPersisted");
            }
        } else {
            nbt = p.getEntityData();
        }
        return nbt;
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.7);
    }

    @Override
    public void func_70645_a(DamageSource par1DamageSource) {
        Entity entitySource = par1DamageSource.func_76346_g();
        if (entitySource instanceof EntityPlayer) {
            List listCloseEntities = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72314_b(32.0, 32.0, 32.0));
            for (int i = 0; i < listCloseEntities.size(); ++i) {
                Entity entity = (Entity)listCloseEntities.get(i);
                if (!(entity instanceof EntityDBCNinjin)) continue;
                EntityDBCNinjin entityTrain = (EntityDBCNinjin)entity;
                entityTrain.becomeAngryAt(entitySource);
            }
            this.becomeAngryAt(entitySource);
        }
        if (entitySource instanceof EntityPlayer) {
            int e = 1;
            if (entitySource instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)entitySource;
                JRMCoreH.expPls(player, e);
            }
        }
        if (ForgeHooks.onLivingDeath((EntityLivingBase)this, (DamageSource)par1DamageSource)) {
            return;
        }
        Entity entity = par1DamageSource.func_76346_g();
        EntityLivingBase entitylivingbase = this.func_94060_bK();
        if (this.field_70744_aE >= 0 && entitylivingbase != null) {
            entitylivingbase.func_70084_c((Entity)this, this.field_70744_aE);
        }
        if (entity != null) {
            entity.func_70074_a((EntityLivingBase)this);
        }
        this.field_70729_aU = true;
        this.func_110142_aN().func_94549_h();
        if (!this.field_70170_p.field_72995_K) {
            int i = 0;
            if (entity instanceof EntityPlayer) {
                i = EnchantmentHelper.func_77519_f((EntityLivingBase)((EntityLivingBase)entity));
            }
            this.captureDrops = true;
            this.capturedDrops.clear();
            int j = 0;
            if (this.func_146066_aG() && this.field_70170_p.func_82736_K().func_82766_b("doMobLoot")) {
                this.func_70628_a(this.field_70718_bc > 0, i);
                this.func_82160_b(this.field_70718_bc > 0, i);
                if (this.field_70718_bc > 0 && (j = this.field_70146_Z.nextInt(200) - i) < 5) {
                    this.func_70600_l(j <= 0 ? 1 : 0);
                }
            }
            this.captureDrops = false;
            if (!ForgeHooks.onLivingDrops((EntityLivingBase)this, (DamageSource)par1DamageSource, (ArrayList)this.capturedDrops, (int)i, (this.field_70718_bc > 0 ? 1 : 0) != 0, (int)j)) {
                for (EntityItem item : this.capturedDrops) {
                    this.field_70170_p.func_72838_d((Entity)item);
                }
            }
        }
        this.field_70170_p.func_72960_a((Entity)this, (byte)3);
    }

    @Override
    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74777_a("Anger", (short)this.angerLevel);
    }

    @Override
    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.angerLevel = par1NBTTagCompound.func_74765_d("Anger");
    }

    @Override
    protected Entity func_70782_k() {
        return this.angerLevel == 0 ? null : super.func_70782_k();
    }

    @Override
    public boolean func_70097_a(DamageSource source, float amount) {
        boolean result = super.func_70097_a(source, amount);
        if (source.func_76346_g() instanceof EntityPlayer) {
            EntityPlayer attacker = (EntityPlayer)source.func_76346_g();
            double distance = this.func_70032_d((Entity)attacker);
            if (distance > 3.5) {
                ++this.outOfRangeHits;
                if (this.outOfRangeHits >= 4) {
                    // empty if block
                }
            } else {
                this.outOfRangeHits = 0;
            }
        }
        if (result && source.func_76346_g() instanceof EntityLivingBase) {
            this.addHate((EntityLivingBase)source.func_76346_g(), (int)amount * 2);
        }
        return result;
    }

    @Override
    public void func_70636_d() {
        if (this.field_70170_p.field_72995_K && JGConfigClientSettings.CLIENT_DA8 && this.angerLevel >= 400 && (this.hasAnAura || this.auraLightning || this.hasAGodAura)) {
            for (int k = 0; k < JGConfigClientSettings.get_da1(); ++k) {
                EntityDBCNinjin EntityDBCNinjin2 = this;
                EntityDBCNinjin pl = this;
                float red = this.auraRed;
                float green = this.auraGreen;
                float blue = this.auraBlue;
                float red2 = this.auraRed2;
                float green2 = this.auraGreen2;
                float blue2 = this.auraBlue2;
                float life = 0.8f * ((Entity)pl).field_70131_O;
                float extra_scale = 1.0f + (((Entity)pl).field_70131_O > 2.1f ? ((Entity)pl).field_70131_O / 2.0f : 0.0f) / 5.0f;
                double x = (Math.random() - 0.5) * (double)(this.field_70130_N * 1.2f);
                double y = Math.random() * (double)(this.field_70131_O * 1.4f) - (double)(this.field_70131_O / 2.0f) - (double)0.3f;
                double z = (Math.random() - 0.5) * (double)(this.field_70130_N * 1.2f);
                double motx = Math.random() * (double)0.02f - (double)0.01f;
                double moty = (Math.random() * (double)0.9f + (double)0.9f) * ((double)(life * extra_scale) * 0.07);
                double motz = Math.random() * (double)0.02f - (double)0.01f;
                for (int i = 0; i < 5; ++i) {
                    x = (Math.random() - 0.5) * (double)(this.field_70130_N * 1.2f);
                    y = Math.random() * (double)(this.field_70131_O * 1.4f) - (double)(this.field_70131_O / 2.0f) - (double)0.3f;
                    z = (Math.random() - 0.5) * (double)(this.field_70130_N * 1.2f);
                    motx = Math.random() * (double)0.02f - (double)0.01f;
                    moty = (Math.random() * (double)0.9f + (double)0.9f) * ((double)(life * extra_scale) * 0.07);
                    motz = Math.random() * (double)0.02f - (double)0.01f;
                    if (!this.hasAnAura) continue;
                    EntityCusPar entity = new EntityCusPar("jinryuumodscore:bens_particles.png", ((Entity)pl).field_70170_p, 0.2f, 0.2f, ((Entity)pl).field_70165_t, ((Entity)pl).field_70163_u + (double)(pl instanceof EntityPlayerSP ? -1.6f : 0.0f), ((Entity)pl).field_70161_v, x, y, z, motx, moty, motz, 0.0f, (int)(Math.random() * 3.0) + 32, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", (int)(30.0f * life * 0.5f), 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * extra_scale, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * extra_scale, 0.2f * life * extra_scale, 0, red, green, blue, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.08f, false, -1, true, (Entity)pl);
                    ((Entity)pl).field_70170_p.func_72838_d((Entity)entity);
                    EntityCusPar entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", ((Entity)pl).field_70170_p, 0.2f, 0.2f, ((Entity)pl).field_70165_t, ((Entity)pl).field_70163_u + (double)(pl instanceof EntityPlayerSP ? -1.6f : 0.0f), ((Entity)pl).field_70161_v, x, y, z, motx, moty, motz, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", (int)(30.0f * life * 0.5f), 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * extra_scale, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * extra_scale, 0.1f * life * extra_scale, 0, red, green, blue, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.08f, false, -1, true, (Entity)pl);
                    ((Entity)pl).field_70170_p.func_72838_d((Entity)entity2);
                }
                if (this.hasAGodAura) {
                    float out = 1.6f;
                    float in = 1.0f;
                    extra_scale = 0.5f;
                    int dea = 50;
                    for (int gh = 0; gh < 2; ++gh) {
                        x = Math.random() * (double)out - (double)(out / 2.0f);
                        y = Math.random() * (double)this.field_70131_O - 0.5;
                        z = Math.random() * (double)out - (double)(out / 2.0f);
                        EntityCusPar entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, ((Entity)pl).field_70165_t, ((Entity)pl).field_70163_u, ((Entity)pl).field_70161_v, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, red2, green2, blue2, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, false, (Entity)pl);
                        x = Math.random() * (double)out - (double)(out / 2.0f);
                        y = Math.random() * (double)this.field_70131_O - 0.5;
                        z = Math.random() * (double)out - (double)(out / 2.0f);
                        entity.field_70170_p.func_72838_d((Entity)entity);
                        EntityCusPar entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, ((Entity)pl).field_70165_t, ((Entity)pl).field_70163_u, ((Entity)pl).field_70161_v, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, red2, green2, blue2, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, false, (Entity)pl);
                        entity.field_70170_p.func_72838_d((Entity)entity2);
                    }
                    x = Math.random() * (double)(out *= 1.4f) - (double)(out / 2.0f);
                    y = Math.random() * (double)this.field_70131_O - 0.5;
                    z = Math.random() * (double)out - (double)(out / 2.0f);
                    EntityCusPar entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, ((Entity)pl).field_70165_t, ((Entity)pl).field_70163_u, ((Entity)pl).field_70161_v, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, red, green, blue, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, false, (Entity)pl);
                    x = Math.random() * (double)out - (double)(out / 2.0f);
                    y = Math.random() * (double)this.field_70131_O - 0.5;
                    z = Math.random() * (double)out - (double)(out / 2.0f);
                    entity.field_70170_p.func_72838_d((Entity)entity);
                    EntityCusPar entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, ((Entity)pl).field_70165_t, ((Entity)pl).field_70163_u, ((Entity)pl).field_70161_v, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, red, green, blue, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, false, (Entity)pl);
                    entity.field_70170_p.func_72838_d((Entity)entity2);
                    x = Math.random() * (double)in - (double)(in / 2.0f);
                    y = (Math.random() * (double)this.field_70131_O - 0.5) * (double)0.8f;
                    z = Math.random() * (double)in - (double)(in / 2.0f);
                    entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, ((Entity)pl).field_70165_t, ((Entity)pl).field_70163_u, ((Entity)pl).field_70161_v, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 180.0f, 180.0f, 180.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, false, (Entity)pl);
                    x = Math.random() * (double)in - (double)(in / 2.0f);
                    y = (Math.random() * (double)this.field_70131_O - 0.5) * (double)0.8f;
                    z = Math.random() * (double)in - (double)(in / 2.0f);
                    entity.field_70170_p.func_72838_d((Entity)entity);
                    entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, ((Entity)pl).field_70165_t, ((Entity)pl).field_70163_u, ((Entity)pl).field_70161_v, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, 180.0f, 180.0f, 180.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, false, (Entity)pl);
                    entity.field_70170_p.func_72838_d((Entity)entity2);
                    x = Math.random() * (double)(in *= 1.2f) - (double)(in / 2.0f);
                    y = (Math.random() * (double)this.field_70131_O - 0.5) * (double)0.8f;
                    z = Math.random() * (double)in - (double)(in / 2.0f);
                    entity = new EntityCusPar("jinryuumodscore:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, ((Entity)pl).field_70165_t, ((Entity)pl).field_70163_u, ((Entity)pl).field_70161_v, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 3.0) + 8, 8, 3, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, red2, green2, blue2, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, false, (Entity)pl);
                    x = Math.random() * (double)in - (double)(in / 2.0f);
                    y = (Math.random() * (double)this.field_70131_O - 0.5) * (double)0.8f;
                    z = Math.random() * (double)in - (double)(in / 2.0f);
                    entity.field_70170_p.func_72838_d((Entity)entity);
                    entity2 = new EntityCusPar("jinryuudragonbc:bens_particles.png", this.field_70170_p, 0.2f, 0.2f, ((Entity)pl).field_70165_t, ((Entity)pl).field_70163_u, ((Entity)pl).field_70161_v, x, y, z, 0.0, Math.random() * (double)0.05f, 0.0, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", 50, 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * 0.5f, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * 0.5f, 0.2f * life * 0.5f, 0, red2, green2, blue2, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.015f, false, -1, false, (Entity)pl);
                    entity.field_70170_p.func_72838_d((Entity)entity2);
                }
                if (this.auraLightning && this.lightningCount == 1) {
                    EntityCusPar entity3 = new EntityCusPar("ninjinentities:textures/effect/lightning.png", ((Entity)pl).field_70170_p, 0.2f, 0.2f, ((Entity)pl).field_70165_t, ((Entity)pl).field_70163_u + (double)(pl instanceof EntityPlayerSP ? -1.6f : 0.0f), ((Entity)pl).field_70161_v, x, y, z, motx, moty, motz, 0.0f, (int)(Math.random() * 8.0) + 32, 32, 8, 32, false, 0.0f, false, 0.0f, 1, "", (int)(30.0f * life * 0.5f), 2, ((float)(Math.random() * (double)0.03f) + 0.03f) * life * extra_scale, ((float)(Math.random() * (double)0.01f) + 0.02f) * life * extra_scale, 0.1f * life * extra_scale, 0, 255.0f, 255.0f, 255.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 2, 0.0f, 0.0f, 0.4f, 0.45f, 0.08f, false, -1, true, (Entity)pl);
                    ((Entity)pl).field_70170_p.func_72838_d((Entity)entity3);
                    if (((Entity)pl).field_70173_aa % 25 == (int)random * 25 || ((Entity)pl).field_70173_aa % 70 == 1) {
                        Main.network.sendToServer((IMessage)new MessageSendEntityToSpark(this.func_145782_y()));
                        random = Math.random();
                    }
                    this.lightningCount = 0;
                    continue;
                }
                ++this.lightningCount;
            }
        }
        ++this.wait;
        List players = this.field_70170_p.func_72872_a(EntityPlayer.class, this.field_70121_D.func_72314_b(16.0, 16.0, 16.0));
        if (!players.isEmpty()) {
            for (EntityPlayer player : players) {
                this.becomeAngryAt((Entity)player);
                double distSq = this.func_70068_e((Entity)player);
                int minInterval = 800;
                int maxInterval = 2000;
                if (distSq > 256.0) {
                    minInterval = 40;
                    maxInterval = 80;
                } else if (distSq < 64.0) {
                    minInterval = 120;
                    maxInterval = 240;
                } else {
                    minInterval = 80;
                    maxInterval = 200;
                }
                if (this.wait < this.nextTeleportTick) continue;
                this.wait = 0;
                this.nextTeleportTick = minInterval + this.field_70146_Z.nextInt(maxInterval - minInterval);
                if (this.field_70170_p.field_72995_K || !this.canTeleport) continue;
                this.targetedEntity = player;
                this.teleportToTarget();
            }
        }
        if (this.func_70638_az() != null) {
            EntityLivingBase target = this.func_70638_az();
            double dx = target.field_70165_t - this.field_70165_t;
            double dy = target.field_70163_u - this.field_70163_u;
            double dz = target.field_70161_v - this.field_70161_v;
            double distanceSq = dx * dx + dy * dy + dz * dz;
            boolean shouldFly = false;
            if (distanceSq > 80.0) {
                shouldFly = true;
            }
            if (Math.abs(dy) > 3.0) {
                shouldFly = true;
            }
            if (shouldFly) {
                if (ModConfig.useNewFlightAI) {
                    this.handleTargetTracking(4.0, 1.0);
                } else {
                    this.handleOldFlight();
                }
                this.field_70122_E = false;
            } else {
                this.func_70661_as().func_75497_a((Entity)target, 1.0);
            }
        }
        this.func_82168_bl();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(this.moveSpeed);
        super.func_70636_d();
        if (!this.field_70170_p.field_72995_K && this.field_70173_aa % 20 == 0) {
            this.updateTargetFromHate();
        }
    }

    @Override
    public void func_70071_h_() {
        double distSq;
        double SPAWNER_SEARCH_RADIUS = DBCConfig.mdal;
        double VERTICAL_CHASE_THRESHOLD = 4.0;
        double VERTICAL_SNAP_THRESHOLD = 1.6;
        int CLIENT_UPDATE_INTERVAL = 20;
        double MIN_TELEPORT_DIST_SQ = 4.0;
        this.handleSpawnerDetection(SPAWNER_SEARCH_RADIUS);
        this.validateSpawnerPresence(SPAWNER_SEARCH_RADIUS);
        if (this.field_70170_p.field_72995_K) {
            this.handleClientUpdates(20);
        } else if (!this.updtd) {
            this.updtd = true;
        }
        if (ModConfig.useNewFlightAI) {
            this.handleTargetTracking(4.0, 1.6);
        } else {
            this.handleOldFlight();
        }
        super.func_70071_h_();
        if (this.teleportCooldown > 0) {
            --this.teleportCooldown;
        }
        if (this.outOfRangeHits >= 5 && this.targetedEntity != null) {
            if ((!(this.targetedEntity instanceof EntityPlayer) || !((EntityPlayer)this.targetedEntity).field_71075_bZ.field_75098_d) && (distSq = this.func_70068_e(this.targetedEntity)) > 4.0 && this.canTeleport) {
                this.teleportToTarget();
                if (this.targetedEntity instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer)this.targetedEntity;
                    String mobName = this.func_70005_c_();
                    String msg = StatCollector.func_74838_a((String)"message.ninjin.teleportwarn");
                    player.func_145747_a((IChatComponent)new ChatComponentText(mobName + "\uff1a" + msg));
                }
            }
            this.outOfRangeHits = 0;
        }
        if (this.teleportCooldown <= 0 && this.targetedEntity != null) {
            if ((!(this.targetedEntity instanceof EntityPlayer) || !((EntityPlayer)this.targetedEntity).field_71075_bZ.field_75098_d) && (distSq = this.func_70068_e(this.targetedEntity)) > 4.0 && this.canTeleport) {
                this.teleportToTarget();
            }
            this.teleportCooldown = 300;
        }
    }

    private void teleportToTarget() {
        double targetTopY;
        if (this.targetedEntity == null || this.field_70170_p.field_72995_K) {
            return;
        }
        if (this.targetedEntity instanceof EntityPlayer && ((EntityPlayer)this.targetedEntity).field_71075_bZ.field_75098_d) {
            return;
        }
        this.field_70170_p.func_72956_a((Entity)this, "jinryuudragonbc:DBC3.teleport", 1.0f, 1.0f);
        Vec3 lookVec = this.targetedEntity.func_70040_Z();
        double teleportDistance = 2.0;
        double newX = this.targetedEntity.field_70165_t - lookVec.field_72450_a * teleportDistance;
        double newZ = this.targetedEntity.field_70161_v - lookVec.field_72449_c * teleportDistance;
        double newY = targetTopY = this.targetedEntity.field_70121_D.field_72337_e;
        this.func_70634_a(newX, newY, newZ);
        this.attackCounter = 0;
    }

    private void handleSpawnerDetection(double radius) {
        AxisAlignedBB searchArea;
        List nearbyPlayers;
        if (this.spwner == null && radius > 0.0 && !(nearbyPlayers = this.field_70170_p.func_72872_a(EntityPlayer.class, searchArea = this.createBoundingBox(radius))).isEmpty()) {
            this.spwner = (EntityPlayer)nearbyPlayers.get(0);
        }
    }

    private void validateSpawnerPresence(double radius) {
        if (this.spwner != null && radius > 0.0) {
            AxisAlignedBB checkArea = this.createBoundingBox(radius);
            List nearbyPlayers = this.field_70170_p.func_72872_a(EntityPlayer.class, checkArea);
            int validSpawnerCount = this.countValidSpawners(nearbyPlayers);
            if (validSpawnerCount == 0) {
                if (--this.noSpwnr <= 0) {
                    this.func_70106_y();
                }
            } else if (this.noSpwnr != DBCConfig.mdat) {
                this.noSpwnr = DBCConfig.mdat;
            }
        } else if (!this.field_70170_p.field_72995_K && this.spwner == null) {
            this.func_70106_y();
        }
    }

    private int countValidSpawners(List<EntityPlayer> players) {
        int count = 0;
        int spawnerGroupId = JRMCoreH.getInt(this.spwner, "JRMCGID");
        for (EntityPlayer player : players) {
            int playerGroupId = JRMCoreH.getInt(player, "JRMCGID");
            if (this.spwner.func_145782_y() != player.func_145782_y() && (spawnerGroupId == 0 || spawnerGroupId != playerGroupId)) continue;
            ++count;
        }
        if (this.func_145782_y() == this.spwner.func_145782_y()) {
            ++count;
        }
        return count;
    }

    private void handleClientUpdates(int updateInterval) {
        if (++this.rang > updateInterval) {
            this.rang = 0;
            int entityId = this.updateDataInt(23);
            this.targetedEntity = entityId > 0 ? this.field_70170_p.func_73045_a(entityId) : null;
        }
    }

    private void handleTargetTracking(double chaseThreshold, double snapThreshold) {
        if (this.targetedEntity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)this.targetedEntity;
            if (player.field_71075_bZ.field_75098_d) {
                return;
            }
        }
        if (this.targetedEntity != null && this.func_70685_l(this.targetedEntity)) {
            double dx = this.targetedEntity.field_70165_t - this.field_70165_t;
            double dy = this.targetedEntity.field_70163_u - this.field_70163_u;
            double dz = this.targetedEntity.field_70161_v - this.field_70161_v;
            double distanceSq = dx * dx + dy * dy + dz * dz;
            double accel = 0.1 + Math.min(0.4, distanceSq * 0.005);
            double maxSpeed = 0.35 + Math.min(0.5, distanceSq * 0.005);
            Vec3 vecToTarget = Vec3.func_72443_a((double)dx, (double)dy, (double)dz).func_72432_b();
            this.field_70159_w += vecToTarget.field_72450_a * accel;
            this.field_70181_x += vecToTarget.field_72448_b * accel;
            this.field_70179_y += vecToTarget.field_72449_c * accel;
            double totalSpeedSq = this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y;
            if (totalSpeedSq > maxSpeed * maxSpeed) {
                double scale = maxSpeed / Math.sqrt(totalSpeedSq);
                this.field_70159_w *= scale;
                this.field_70181_x *= scale;
                this.field_70179_y *= scale;
            }
            this.field_70143_R = 0.0f;
        }
    }

    private void handleOldFlight() {
        if (this.targetedEntity != null && this.func_70685_l(this.targetedEntity)) {
            if (this.targetedEntity.field_70163_u - 4.0 > this.field_70163_u) {
                this.field_70181_x += 0.1;
            } else if (this.targetedEntity.field_70163_u - 1.6 >= this.field_70163_u) {
                this.field_70163_u = this.targetedEntity.field_70163_u - 1.6;
                this.field_70181_x = 0.0;
                this.field_70143_R = 0.0f;
            }
        }
    }

    private AxisAlignedBB createBoundingBox(double radius) {
        return AxisAlignedBB.func_72330_a((double)(this.field_70165_t - radius), (double)(this.field_70163_u - radius), (double)(this.field_70161_v - radius), (double)(this.field_70165_t + radius), (double)(this.field_70163_u + radius), (double)(this.field_70161_v + radius));
    }

    private void becomeAngryAt(Entity par1Entity) {
        this.field_70789_a = par1Entity;
        this.angerLevel = 400 + this.field_70146_Z.nextInt(400);
    }

    public void addHate(EntityLivingBase entity, int amount) {
        if (entity == null || entity.field_70128_L) {
            return;
        }
        this.hateMap.put(entity, this.hateMap.getOrDefault(entity, 0) + amount);
    }

    private void updateTargetFromHate() {
        EntityLivingBase highest = null;
        int maxHate = -1;
        for (Map.Entry<EntityLivingBase, Integer> entry : this.hateMap.entrySet()) {
            EntityLivingBase e = entry.getKey();
            if (e == null || e.field_70128_L || this.func_70032_d((Entity)e) > 40.0f || entry.getValue() <= maxHate) continue;
            maxHate = entry.getValue();
            highest = e;
        }
        if (highest != null && this.func_70638_az() != highest) {
            this.func_70624_b(highest);
        }
    }

    @Override
    public boolean func_70652_k(Entity entityIn) {
        boolean result = super.func_70652_k(entityIn);
        if (result && entityIn instanceof EntityLivingBase) {
            this.addHate((EntityLivingBase)entityIn, 5);
        }
        return result;
    }

    public static enum MindState {
        NEUTRAL,
        AGGRESSIVE,
        PACIFIC;

    }
}

