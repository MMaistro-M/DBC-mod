/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAttackOnCollide
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILeapAtTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.monster.EntitySlime
 *  net.minecraft.entity.monster.IMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.stats.AchievementList
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package JinRyuu.FamilyC;

import JinRyuu.FamilyC.FamilyCConfig;
import JinRyuu.FamilyC.mod_FamilyC;
import JinRyuu.JRMCore.FamilyCH;
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.entity.AIFollowOwner;
import JinRyuu.JRMCore.entity.AINearestAttackableTarget;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import java.util.List;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityNPC
extends EntityCreature
implements IMob,
IEntityAdditionalSpawnData {
    public int randomSoundDelay = 0;
    private float age = 0.25f;
    private float grw = 2.0f;
    private boolean doNamUpdt = false;
    private int maxHealth = 30;
    private int energy = 0;
    private int maxEnergy = 0;
    private byte cnam = 0;
    private String dns = "0";
    private String dnsH = "0";
    private String nam = "Child";
    private String mom = "";
    private String dad = "";
    private int cid = 0;
    private boolean aggr = false;
    private int follow = 2;
    private int followTarget = 0;
    private int equipmentDropped = 0;
    private String attrbts = "1:1:1:1:1:1";
    private String skills = "";
    private String techs = "";
    private String bonuses = "";
    private int npcExp = 0;
    private int npcTp = 0;
    private int npcSt = 0;
    public String expValue;
    private int angerLevel;
    private Entity target;
    private int tick20;

    public EntityNPC(World par1World) {
        super(par1World);
        this.toString();
        this.expValue = String.valueOf(this.BattlePower());
        this.tick20 = 200;
        this.runTasks();
        this.field_70728_aV = 0;
    }

    public void runTasks() {
        this.func_70661_as().func_75491_a(true);
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.4f));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, 1.0, true));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new AIFollowOwner(this, 1.0, 10.0f, 2.0f));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new AINearestAttackableTarget(this, EntityMob.class, 0, false));
        this.field_70715_bh.func_75776_a(3, (EntityAIBase)new AINearestAttackableTarget(this, EntitySlime.class, 0, false));
        this.field_70715_bh.func_75776_a(4, (EntityAIBase)new AINearestAttackableTarget(this, EntityCreeper.class, 0, false));
    }

    public void setNamUpdt(boolean b) {
        this.doNamUpdt = b;
    }

    public void setCnam(byte i) {
        this.cnam = i;
    }

    public void setDNS(String i) {
        this.dns = i;
    }

    public void setDNSH(String i) {
        this.dnsH = i;
    }

    public void setNam(String i) {
        this.nam = i;
    }

    public void setMom(String i) {
        this.mom = i;
    }

    public void setDad(String i) {
        this.dad = i;
    }

    public void setNPCAge(float i) {
        this.age = i;
    }

    public void setFollow(int i) {
        this.follow = i;
    }

    public void setAggr(boolean i) {
        this.aggr = i;
    }

    public void setFollowTarget(int i) {
        this.followTarget = i;
    }

    public int getCnam() {
        return this.cnam;
    }

    public String getDNS() {
        return this.dns;
    }

    public String getDNSH() {
        return this.dnsH;
    }

    public String getNam() {
        return this.nam;
    }

    public String getMom() {
        return this.mom;
    }

    public String getDad() {
        return this.dad;
    }

    public int getCid() {
        return this.cid;
    }

    public float getNPCAge() {
        return this.age;
    }

    public float getNPCgrw() {
        return this.grw;
    }

    public int getFollow() {
        return this.follow;
    }

    public boolean getAggr() {
        return this.aggr;
    }

    public int getFollowTarget() {
        return this.followTarget;
    }

    public String getAttrbts() {
        return this.attrbts;
    }

    public int getExp() {
        return this.npcExp;
    }

    public int getTp() {
        return this.npcTp;
    }

    public int getSt() {
        return this.npcSt;
    }

    public boolean stopMoving() {
        return this.follow == 0;
    }

    public void newborn() {
        this.age = 0.25f;
        this.grw = 2.0f;
        this.doNamUpdt = false;
        this.maxHealth = 30;
        this.energy = 0;
        this.maxEnergy = 0;
        this.cnam = 0;
        this.dns = "0";
        this.dnsH = "0";
        this.nam = "Child";
        this.mom = "";
        this.dad = "";
        this.cid = 0;
        this.aggr = false;
        this.follow = 2;
        this.followTarget = 0;
        this.equipmentDropped = 0;
        this.attrbts = "1:1:1:1:1:1";
        this.skills = "";
        this.techs = "";
        this.bonuses = "";
        this.npcExp = 0;
        this.npcTp = 0;
        this.npcSt = 0;
    }

    public EntityNPC(World worldObj, String dns, String mom, String dad, String nam, int id, String dnsH) {
        super(worldObj);
        this.toString();
        this.expValue = String.valueOf(this.BattlePower());
        this.tick20 = 200;
        this.newborn();
        if (nam.length() > 30) {
            nam = nam.substring(0, 30);
        }
        this.func_98053_h(true);
        if (dns.length() > 2) {
            this.dns = dns;
            this.dnsH = dnsH;
            this.cid = id;
            this.nam = nam;
            this.dad = dad;
            this.mom = mom;
        } else {
            this.func_70106_y();
        }
        this.runTasks();
    }

    protected boolean func_70650_aV() {
        return true;
    }

    protected boolean func_70692_ba() {
        return false;
    }

    protected void func_70619_bc() {
        super.func_70619_bc();
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        double d = this.maxHealth != 0 ? this.maxHealth : 30;
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(d);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a((double)0.23f);
    }

    public long BattlePower() {
        int exp = this.field_70728_aV * 100;
        long BattlePower = 1200 + this.field_70146_Z.nextInt(100);
        return BattlePower;
    }

    public void func_82160_b(boolean par1, int par2) {
        for (int j = 0; j < this.func_70035_c().length; ++j) {
            ItemStack itemstack = this.func_71124_b(j);
            if (itemstack == null || !par1) continue;
            this.func_70099_a(itemstack, 0.0f);
            this.func_70062_b(j, null);
            this.equipmentDropped = 100;
            this.func_98053_h(false);
        }
    }

    public float updateData(int i) {
        return this.field_70180_af.func_111145_d(i);
    }

    public String updateDataString(int i) {
        return this.field_70180_af.func_75681_e(i);
    }

    public int updateDataInt(int i) {
        return this.field_70180_af.func_75679_c(i);
    }

    public void updateDataNam() {
        this.nam = this.field_70180_af.func_75681_e(21);
    }

    protected void func_70629_bd() {
        this.field_70180_af.func_75692_b(18, (Object)Float.valueOf(this.func_110143_aJ()));
        this.field_70180_af.func_75692_b(19, (Object)Float.valueOf(this.getNPCAge()));
        this.field_70180_af.func_75692_b(20, (Object)Float.valueOf(this.getNPCgrw()));
        this.field_70180_af.func_75692_b(21, (Object)String.valueOf(this.getNam()));
        this.field_70180_af.func_75692_b(22, (Object)String.valueOf(this.attrbts));
        this.field_70180_af.func_75692_b(23, (Object)this.npcExp);
        this.field_70180_af.func_75692_b(24, (Object)this.npcTp);
        this.field_70180_af.func_75692_b(25, (Object)String.valueOf(this.dns));
        this.field_70180_af.func_75692_b(26, (Object)String.valueOf(this.dnsH));
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(18, (Object)new Float(this.func_110143_aJ()));
        this.field_70180_af.func_75682_a(19, (Object)new Float(this.getNPCAge()));
        this.field_70180_af.func_75682_a(20, (Object)new Float(this.getNPCgrw()));
        this.field_70180_af.func_75682_a(21, (Object)String.valueOf(this.getNam()));
        this.field_70180_af.func_75682_a(22, (Object)String.valueOf(this.attrbts));
        this.field_70180_af.func_75682_a(23, (Object)new Integer(this.npcExp));
        this.field_70180_af.func_75682_a(24, (Object)new Integer(this.npcTp));
        this.field_70180_af.func_75682_a(25, (Object)String.valueOf(this.dns));
        this.field_70180_af.func_75682_a(26, (Object)String.valueOf(this.dnsH));
    }

    private void update() {
        if (this.equipmentDropped > 0) {
            --this.equipmentDropped;
        } else if (this.equipmentDropped == 0 && !this.func_98052_bS()) {
            this.func_98053_h(true);
        }
        if (!this.field_70170_p.field_72995_K) {
            int i;
            String[] s1;
            int[] attrbts;
            if (FamilyCConfig.dcr) {
                this.func_70106_y();
            }
            this.field_70143_R = 0.0f;
            float gu = FamilyCConfig.gut;
            if (this.age <= 5.0f) {
                this.grw = 2.0f;
            }
            if (this.age > 5.0f && this.age <= gu) {
                this.grw = 1.0f + (1.0f - (this.age - 5.0f) / (gu - 5.0f));
            }
            if (this.age > gu + 1.0f) {
                this.grw = 1.0f;
            }
            if (this.tick20 == 200) {
                attrbts = new int[6];
                s1 = this.attrbts.split(":");
                for (i = 0; i < 6; ++i) {
                    attrbts[i] = Integer.parseInt(s1[i]);
                }
                this.maxHealth = attrbts[2] * (JRMCoreH.DBC() || JRMCoreH.NC() ? 40 : 5);
                if (this.maxHealth != 0 && (int)this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111126_e() != this.maxHealth) {
                    this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((double)this.maxHealth);
                }
                int a = attrbts[0] + attrbts[1] + attrbts[2] + attrbts[3] + attrbts[4] + attrbts[5];
                if (this.npcTp >= JRMCoreH.attrCst(attrbts, 0)) {
                    this.npcTp -= JRMCoreH.attrCst(attrbts, 0);
                    int r = this.field_70170_p.field_73012_v.nextInt(6);
                    int n = attrbts[r] < 120 ? attrbts[r] + 1 : 120;
                    attrbts[r] = (byte)n;
                    String s = "";
                    for (int i2 = 0; i2 < 6; ++i2) {
                        s = s + ":" + attrbts[i2];
                    }
                    this.attrbts = s.substring(1);
                }
            }
            if (this.tick20 == 150) {
                FamilyCH.wcpd(FMLCommonHandler.instance().getMinecraftServerInstance(), (int)this.field_70165_t + "," + (int)this.field_70163_u + "," + (int)this.field_70161_v, "" + this.cid, false);
            }
            if (this.tick20 == 200 || this.tick20 == 100) {
                attrbts = new int[6];
                s1 = this.attrbts.split(":");
                for (i = 0; i < 6; ++i) {
                    attrbts[i] = Integer.parseInt(s1[i]);
                }
                int Stamina = attrbts[2] * 2;
                float curBody = this.func_110143_aJ();
                if (curBody < (float)this.maxHealth && this.npcSt == 0) {
                    float add = (float)Stamina * ((float)JRMCoreConfig.hRgnRt * 0.5f);
                    float all = curBody + (add < 1.0f ? 1.0f : add);
                    this.func_70691_i(all > (float)this.maxHealth ? (float)this.maxHealth : all);
                }
            }
        } else {
            if (this.doNamUpdt) {
                String prev = this.nam;
                this.updateDataNam();
                if (this.nam.compareTo(prev) != 0) {
                    this.doNamUpdt = false;
                }
            }
            if (this.tick20 == 200 || this.age == 0.0f) {
                this.age = this.updateData(19);
                this.grw = this.updateData(20);
                this.nam = this.updateDataString(21);
                this.attrbts = this.updateDataString(22);
                this.npcExp = this.updateDataInt(23);
                this.npcTp = this.updateDataInt(24);
                this.dns = this.updateDataString(25);
                this.dnsH = this.updateDataString(26);
            }
        }
        --this.tick20;
        if (this.tick20 <= 0) {
            this.tick20 = 200;
        }
        if (!this.field_70170_p.field_72995_K) {
            WorldServer dim0 = FMLCommonHandler.instance().getMinecraftServerInstance().func_71218_a(0);
            if (dim0.func_72820_D() % 24000L == 1L || dim0.func_72820_D() % 24000L == 6001L || dim0.func_72820_D() % 24000L == 12001L || dim0.func_72820_D() % 24000L == 18001L) {
                this.age += 0.25f;
                int mls = FamilyCConfig.cls;
                int n = mls = mls < 20 ? 20 : mls;
                if (dim0.func_72820_D() % 24000L == 6001L && this.age > (float)mls) {
                    if (this.field_70170_p.field_73012_v.nextInt(5) == 0) {
                        this.func_70097_a(DamageSource.field_76377_j, 20000.0f);
                        this.age = 0.0f;
                    } else {
                        this.func_70097_a(DamageSource.field_76377_j, 4.0f);
                        EntityPlayerMP dad = JRMCoreH.getPlayerForUsername(FMLCommonHandler.instance().getMinecraftServerInstance(), this.dad);
                        EntityPlayerMP mom = JRMCoreH.getPlayerForUsername(FMLCommonHandler.instance().getMinecraftServerInstance(), this.mom);
                        String msg = "\u00a7eYour child is getting very Old. " + this.nam + " will die soon.";
                        if (dad != null) {
                            dad.func_145747_a((IChatComponent)new ChatComponentText(msg));
                        }
                        if (mom != null && (dad == null || mom != dad)) {
                            mom.func_145747_a((IChatComponent)new ChatComponentText(msg));
                        }
                    }
                }
            }
            if (JRMCoreH.DBC() && this.field_71093_bK == 23) {
                for (int i = 0; i < 24; ++i) {
                    if (dim0.func_72820_D() % 24000L != (long)(i * 1000)) continue;
                    this.age += 4.0f;
                }
            }
        }
    }

    public void func_70071_h_() {
        this.update();
        if (this.randomSoundDelay <= 0 || --this.randomSoundDelay == 0) {
            // empty if block
        }
        super.func_70071_h_();
    }

    public boolean func_70601_bi() {
        return this.field_70170_p.func_72855_b(this.field_70121_D) && this.field_70170_p.func_72945_a((Entity)this, this.field_70121_D).isEmpty() && !this.field_70170_p.func_72953_d(this.field_70121_D);
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("maxHealth", this.maxHealth);
        par1NBTTagCompound.func_74774_a("cnam", this.cnam);
        par1NBTTagCompound.func_74777_a("Anger", (short)this.angerLevel);
        par1NBTTagCompound.func_74778_a("nam", this.nam);
        par1NBTTagCompound.func_74778_a("DNS", this.dns);
        par1NBTTagCompound.func_74778_a("DNSH", this.dnsH);
        par1NBTTagCompound.func_74778_a("mom", this.mom);
        par1NBTTagCompound.func_74778_a("dad", this.dad);
        par1NBTTagCompound.func_74768_a("cid", this.cid);
        par1NBTTagCompound.func_74776_a("age", this.age);
        par1NBTTagCompound.func_74776_a("grw", this.grw);
        par1NBTTagCompound.func_74757_a("aggr", this.aggr);
        par1NBTTagCompound.func_74768_a("follow", this.follow);
        par1NBTTagCompound.func_74768_a("followTarget", this.followTarget);
        par1NBTTagCompound.func_74778_a("attrbts", this.attrbts);
        par1NBTTagCompound.func_74778_a("skills", this.skills);
        par1NBTTagCompound.func_74778_a("techs", this.techs);
        par1NBTTagCompound.func_74778_a("bonuses", this.bonuses);
        par1NBTTagCompound.func_74768_a("npcExp", this.npcExp);
        par1NBTTagCompound.func_74768_a("npcTp", this.npcTp);
        par1NBTTagCompound.func_74768_a("npcSt", this.npcSt);
        par1NBTTagCompound.func_74768_a("energy", this.energy);
        par1NBTTagCompound.func_74768_a("maxEnergy", this.maxEnergy);
    }

    private String watt(byte[] b) {
        String s = "";
        for (int i = 0; i < 6; ++i) {
            s = s + ":" + b[i];
        }
        return s.substring(1);
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.maxHealth = par1NBTTagCompound.func_74762_e("maxHealth");
        this.cnam = par1NBTTagCompound.func_74771_c("cnam");
        this.angerLevel = par1NBTTagCompound.func_74765_d("Anger");
        this.nam = par1NBTTagCompound.func_74779_i("nam");
        this.dns = par1NBTTagCompound.func_74779_i("DNS");
        this.dnsH = par1NBTTagCompound.func_74779_i("DNSH");
        this.mom = par1NBTTagCompound.func_74779_i("mom");
        this.dad = par1NBTTagCompound.func_74779_i("dad");
        this.cid = par1NBTTagCompound.func_74762_e("cid");
        this.age = par1NBTTagCompound.func_74760_g("age");
        this.grw = par1NBTTagCompound.func_74760_g("grw");
        this.aggr = par1NBTTagCompound.func_74767_n("aggr");
        this.follow = par1NBTTagCompound.func_74762_e("follow");
        this.followTarget = par1NBTTagCompound.func_74762_e("followTarget");
        this.attrbts = par1NBTTagCompound.func_74779_i("attrbts");
        this.skills = par1NBTTagCompound.func_74779_i("skills");
        this.techs = par1NBTTagCompound.func_74779_i("techs");
        this.bonuses = par1NBTTagCompound.func_74779_i("bonuses");
        this.npcExp = par1NBTTagCompound.func_74762_e("npcExp");
        this.npcTp = par1NBTTagCompound.func_74762_e("npcTp");
        this.npcSt = par1NBTTagCompound.func_74762_e("npcSt");
        this.energy = par1NBTTagCompound.func_74762_e("energy");
        this.maxEnergy = par1NBTTagCompound.func_74762_e("maxEnergy");
    }

    private byte[] ratt(String s) {
        byte[] att = new byte[6];
        String[] s1 = s.split(":");
        for (int i = 0; i < 6; ++i) {
            att[i] = Byte.parseByte(s1[i]);
        }
        return att;
    }

    protected Entity func_70782_k() {
        return this.target != null ? this.target : (this.angerLevel == 0 ? null : super.func_70782_k());
    }

    public void func_70636_d() {
        if (!this.field_70170_p.field_72995_K && this.func_98052_bS() && !this.field_70729_aU && !this.field_70170_p.func_82736_K().func_82766_b("mobGriefing")) {
            List list = this.field_70170_p.func_72872_a(EntityItem.class, this.field_70121_D.func_72314_b(1.0, 0.0, 1.0));
            for (EntityItem entityitem : list) {
                EntityPlayer entityplayer;
                ItemStack itemstack;
                int i;
                if (entityitem.field_70128_L || entityitem.func_92059_d() == null || (i = EntityNPC.func_82159_b((ItemStack)(itemstack = entityitem.func_92059_d()))) <= -1) continue;
                boolean flag = true;
                ItemStack itemstack1 = this.func_71124_b(i);
                if (itemstack1 != null) {
                    if (i == 0) {
                        if (itemstack.func_77973_b() instanceof ItemSword && !(itemstack1.func_77973_b() instanceof ItemSword)) {
                            flag = true;
                        } else if (itemstack.func_77973_b() instanceof ItemSword && itemstack1.func_77973_b() instanceof ItemSword) {
                            ItemSword itemsword = (ItemSword)itemstack.func_77973_b();
                            ItemSword itemsword1 = (ItemSword)itemstack1.func_77973_b();
                            flag = itemsword.func_150931_i() == itemsword1.func_150931_i() ? itemstack.func_77960_j() > itemstack1.func_77960_j() || itemstack.func_77942_o() && !itemstack1.func_77942_o() : itemsword.func_150931_i() > itemsword1.func_150931_i();
                        } else {
                            flag = false;
                        }
                    } else if (itemstack.func_77973_b() instanceof ItemArmor && !(itemstack1.func_77973_b() instanceof ItemArmor)) {
                        flag = true;
                    } else if (itemstack.func_77973_b() instanceof ItemArmor && itemstack1.func_77973_b() instanceof ItemArmor) {
                        ItemArmor itemarmor = (ItemArmor)itemstack.func_77973_b();
                        ItemArmor itemarmor1 = (ItemArmor)itemstack1.func_77973_b();
                        flag = itemarmor.field_77879_b == itemarmor1.field_77879_b ? itemstack.func_77960_j() > itemstack1.func_77960_j() || itemstack.func_77942_o() && !itemstack1.func_77942_o() : itemarmor.field_77879_b > itemarmor1.field_77879_b;
                    } else {
                        flag = false;
                    }
                }
                if (!flag) continue;
                if (itemstack1 != null && this.field_70146_Z.nextFloat() - 0.1f < this.field_82174_bp[i]) {
                    this.func_70099_a(itemstack1, 0.0f);
                }
                if (itemstack.func_77973_b() == Items.field_151045_i && entityitem.func_145800_j() != null && (entityplayer = this.field_70170_p.func_72924_a(entityitem.func_145800_j())) != null) {
                    entityplayer.func_71029_a((StatBase)AchievementList.field_150966_x);
                }
                this.func_70062_b(i, itemstack);
                this.field_82174_bp[i] = 2.0f;
                this.func_110163_bv();
                this.func_71001_a((Entity)entityitem, 1);
                entityitem.func_70106_y();
            }
        }
        super.func_70636_d();
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        if (this.func_85032_ar()) {
            return false;
        }
        Entity var3 = par1DamageSource.func_76346_g();
        if (var3 instanceof EntityPlayer) {
            List var4 = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72314_b(32.0, 32.0, 32.0));
            for (int var5 = 0; var5 < var4.size(); ++var5) {
                Entity var6 = (Entity)var4.get(var5);
                if (!(var6 instanceof EntityNPC)) continue;
                EntityNPC var7 = (EntityNPC)var6;
                var7.becomeAngryAt(var3);
            }
            this.becomeAngryAt(var3);
        }
        return super.func_70097_a(par1DamageSource, par2);
    }

    private void becomeAngryAt(Entity par1Entity) {
        this.field_70789_a = par1Entity;
        this.angerLevel = 400 + this.field_70146_Z.nextInt(400);
        this.randomSoundDelay = this.field_70146_Z.nextInt(40);
    }

    protected void func_70628_a(boolean par1, int par2) {
    }

    public boolean func_70085_c(EntityPlayer par1EntityPlayer) {
        boolean flag;
        ItemStack itemstack = par1EntityPlayer.field_71071_by.func_70448_g();
        boolean bl = flag = itemstack != null && itemstack.func_77973_b() == Items.field_151063_bx;
        if (!flag && this.func_70089_S() && !par1EntityPlayer.func_70093_af()) {
            if (this.field_70170_p.field_72995_K) {
                JRMCoreH.targNPC = this;
                par1EntityPlayer.openGui((Object)mod_FamilyC.instance, 2, par1EntityPlayer.field_70170_p, (int)par1EntityPlayer.field_70165_t, (int)par1EntityPlayer.field_70163_u, (int)par1EntityPlayer.field_70161_v);
            } else {
                FamilyCH.jfcd(20, this.func_145782_y() + ":" + this.getFollow() + ":" + (this.getAggr() ? "1" : "0") + ":" + this.getFollowTarget() + ":" + this.getDad() + ":" + this.getMom() + ":" + this.getCnam(), par1EntityPlayer);
            }
            return true;
        }
        return super.func_70085_c(par1EntityPlayer);
    }

    protected void expPls(int e) {
        int[] attrbts = new int[6];
        String[] s1 = this.attrbts.split(":");
        for (int i1 = 0; i1 < 6; ++i1) {
            attrbts[i1] = Integer.parseInt(s1[i1]);
        }
        int mnd = attrbts[4];
        int xplmt = 5;
        int tpgn = 1;
        if (JRMCoreH.DBC()) {
            float p = 1.0f;
            xplmt = (int)(5.0f * p);
            tpgn = JRMCoreConfig.tpgn * ((int)((float)mnd / JRMCoreConfig.TpgnRt) + 1);
        }
        int exp = this.npcExp;
        int tp = this.npcTp;
        int add = e;
        if (tp < JRMCoreH.getMaxTP()) {
            if (exp + e >= xplmt) {
                for (int i = 0; i < (exp + e) / xplmt; ++i) {
                    this.npcTp = tp + tpgn;
                }
            }
            this.npcExp = add = exp + e - (exp + e) / xplmt * xplmt;
        }
    }

    public boolean func_70652_k(Entity par1Entity) {
        float f = 0.0f;
        int i = 0;
        if (par1Entity instanceof EntityLivingBase) {
            f += EnchantmentHelper.func_77512_a((EntityLivingBase)this, (EntityLivingBase)((EntityLivingBase)par1Entity));
            i += EnchantmentHelper.func_77507_b((EntityLivingBase)this, (EntityLivingBase)((EntityLivingBase)par1Entity));
        }
        this.expPls(1);
        int[] attrbts = new int[6];
        String[] s1 = this.attrbts.split(":");
        for (int i1 = 0; i1 < 6; ++i1) {
            attrbts[i1] = Integer.parseInt(s1[i1]);
        }
        float dam = (float)(attrbts[0] * (this.field_70170_p.field_73012_v.nextInt(3) + 1)) + (float)attrbts[3] * 0.5f * 50.0f * 0.02f;
        int dbcA = (int)(f + dam);
        f = dbcA;
        boolean flag = par1Entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), f);
        if (flag) {
            int j;
            if (i > 0) {
                par1Entity.func_70024_g((double)(-MathHelper.func_76126_a((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * (float)i * 0.5f), 0.1, (double)(MathHelper.func_76134_b((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * (float)i * 0.5f));
                this.field_70159_w *= 0.6;
                this.field_70179_y *= 0.6;
            }
            if ((j = EnchantmentHelper.func_90036_a((EntityLivingBase)this)) > 0) {
                par1Entity.func_70015_d(j * 4);
            }
        }
        return flag;
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        if (!this.field_70170_p.field_72995_K) {
            this.func_82160_b(true, 0);
            MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
            String cd = FamilyCH.rcfd(server, this.cid + "");
            FamilyCH.wcfd(server, "d", this.cid, true);
            String pm = FamilyCH.rpfd(server, this.mom);
            String[] pmd = pm.split(";");
            String pmdn = "d";
            for (int i = 0; i < pmd.length; ++i) {
                if (pmd[i].equalsIgnoreCase(this.cid + ":" + this.dad)) continue;
                pmdn = pmdn + ";" + pmd[i];
            }
            pmdn = pmdn.length() > 1 ? pmdn.substring(2) : pmdn;
            FamilyCH.wpfd(server, pmdn, this.mom, pmdn.length() < 2 && pmdn.startsWith("d"));
            EntityPlayer mom = this.field_70170_p.func_72924_a(this.mom);
            if (mom != null) {
                Entity var3 = par1DamageSource.func_76346_g();
                mom.func_145747_a((IChatComponent)new ChatComponentText("\u00a7eYour child " + this.nam + " has died" + (var3 != null && var3.func_70005_c_() != null ? " because of " + var3.func_70005_c_() : "") + "."));
            }
            if (!this.mom.equalsIgnoreCase(this.dad)) {
                String pd = FamilyCH.rpfd(server, this.dad);
                String[] pdd = pm.split(";");
                String pddn = "d";
                for (int i = 0; i < pdd.length; ++i) {
                    if (pdd[i].equalsIgnoreCase(this.cid + ":" + this.dad)) continue;
                    pddn = pddn + ";" + pdd[i];
                }
                pddn = pddn.length() > 1 ? pddn.substring(2) : pddn;
                FamilyCH.wpfd(server, pddn, this.dad, pddn.length() < 2 && pddn.startsWith("d"));
                EntityPlayer dad = this.field_70170_p.func_72924_a(this.dad);
                if (dad != null) {
                    Entity var3 = par1DamageSource.func_76346_g();
                    dad.func_145747_a((IChatComponent)new ChatComponentText("\u00a7eYour child " + this.nam + " has died" + (var3 != null && var3.func_70005_c_() != null ? " because of " + var3.func_70005_c_() : "") + "."));
                }
            }
        }
        super.func_70645_a(par1DamageSource);
    }

    protected void func_70785_a(Entity par1Entity, float par2) {
        if (this.field_70724_aR <= 0 && par2 < 2.0f && par1Entity.field_70121_D.field_72337_e > this.field_70121_D.field_72338_b && par1Entity.field_70121_D.field_72338_b < this.field_70121_D.field_72337_e) {
            this.field_70724_aR = 20;
            this.func_70652_k(par1Entity);
        }
    }

    public void writeSpawnData(ByteBuf data) {
        data.writeInt(this.maxHealth);
        data.writeByte((int)this.cnam);
        ByteBufUtils.writeUTF8String((ByteBuf)data, (String)this.dns);
        ByteBufUtils.writeUTF8String((ByteBuf)data, (String)this.dnsH);
        ByteBufUtils.writeUTF8String((ByteBuf)data, (String)this.nam);
        ByteBufUtils.writeUTF8String((ByteBuf)data, (String)this.mom);
        ByteBufUtils.writeUTF8String((ByteBuf)data, (String)this.dad);
        data.writeInt(this.cid);
        data.writeFloat(this.age);
        data.writeFloat(this.grw);
        data.writeBoolean(this.aggr);
        data.writeInt(this.follow);
        data.writeInt(this.followTarget);
        ByteBufUtils.writeUTF8String((ByteBuf)data, (String)this.attrbts);
        ByteBufUtils.writeUTF8String((ByteBuf)data, (String)this.skills);
        ByteBufUtils.writeUTF8String((ByteBuf)data, (String)this.techs);
        ByteBufUtils.writeUTF8String((ByteBuf)data, (String)this.bonuses);
        data.writeInt(this.npcExp);
        data.writeInt(this.npcTp);
        data.writeInt(this.npcSt);
        data.writeInt(this.energy);
        data.writeInt(this.maxEnergy);
    }

    public void readSpawnData(ByteBuf data) {
        this.maxHealth = data.readInt();
        this.cnam = data.readByte();
        this.dns = ByteBufUtils.readUTF8String((ByteBuf)data);
        this.dnsH = ByteBufUtils.readUTF8String((ByteBuf)data);
        this.nam = ByteBufUtils.readUTF8String((ByteBuf)data);
        this.mom = ByteBufUtils.readUTF8String((ByteBuf)data);
        this.dad = ByteBufUtils.readUTF8String((ByteBuf)data);
        this.cid = data.readInt();
        this.age = data.readFloat();
        this.grw = data.readFloat();
        this.aggr = data.readBoolean();
        this.follow = data.readInt();
        this.followTarget = data.readInt();
        this.attrbts = ByteBufUtils.readUTF8String((ByteBuf)data);
        this.techs = ByteBufUtils.readUTF8String((ByteBuf)data);
        this.bonuses = ByteBufUtils.readUTF8String((ByteBuf)data);
        this.npcExp = data.readInt();
        this.npcTp = data.readInt();
        this.npcSt = data.readInt();
        this.energy = data.readInt();
        this.maxEnergy = data.readInt();
    }
}

