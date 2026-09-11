/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 */
package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.entity.EntityNPC;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.List;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityNPCshadow
extends EntityNPC
implements IEntityAdditionalSpawnData {
    public int randomSoundDelay = 0;
    public int angerLevel = 0;
    private EntityPlayer summoner = null;
    private EntityLivingBase target = null;
    private int maxHealth = 1;
    private int pmje = 0;
    private int wait = 0;
    private int m = 1;
    private int age = 0;
    private String dnsSum;
    public String expValue;
    private int gen;
    private int breast;
    private float f;

    public EntityPlayer getsummoner() {
        return this.summoner;
    }

    public int getSFire() {
        return this.pmje;
    }

    public int getDam() {
        return this.dam;
    }

    public String getdnsSum() {
        return this.dnsSum;
    }

    public int getGen() {
        return this.gen;
    }

    public float getF() {
        return this.f;
    }

    public EntityNPCshadow(World world) {
        super(world);
        this.toString();
        this.expValue = String.valueOf(this.BattlePower());
        this.gen = 1;
        this.breast = 0;
        this.f = 1.0f;
        this.field_70728_aV = 30;
    }

    public EntityNPCshadow(World world, EntityPlayer summoner, int body, int dam, EntityLivingBase target) {
        super(world);
        this.toString();
        this.expValue = String.valueOf(this.BattlePower());
        this.gen = 1;
        this.breast = 0;
        this.f = 1.0f;
        this.field_70728_aV = 1;
        this.summoner = summoner;
        this.dam = dam;
        this.target = target;
        this.maxHealth = body * 2;
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((double)this.maxHealth);
    }

    @Override
    public int BattlePower() {
        int exp = this.field_70728_aV * 100;
        int BattlePower = 1200 + this.field_70146_Z.nextInt(100);
        return BattlePower;
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(10.0);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "jinryuunarutoc:npcs/ninja01.png";
    }

    @Override
    public ResourceLocation getResource() {
        return this.summoner != null && this.summoner instanceof AbstractClientPlayer ? ((AbstractClientPlayer)this.summoner).func_110306_p() : new ResourceLocation("jinryuunarutoc:npcs/ninja01.png");
    }

    public EntityPlayer getSummoner() {
        return this.summoner;
    }

    @Override
    public boolean func_70601_bi() {
        return this.field_70170_p.func_72855_b(this.field_70121_D) && this.field_70170_p.func_72945_a((Entity)this, this.field_70121_D).isEmpty() && !this.field_70170_p.func_72953_d(this.field_70121_D);
    }

    @Override
    protected Entity func_70782_k() {
        int n = 8;
        AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)(this.field_70165_t - (double)n), (double)(this.field_70163_u - (double)n), (double)(this.field_70161_v - (double)n), (double)(this.field_70165_t + (double)n), (double)(this.field_70163_u + (double)n), (double)(this.field_70161_v + (double)n));
        Entity entity = this.field_70170_p.func_72857_a(EntityCreature.class, aabb, (Entity)this);
        EntityPlayer entityplayer = this.field_70170_p.func_72856_b((Entity)this, 16.0);
        EntityLivingBase target = this.target;
        return target != null ? target : (entityplayer != null && entityplayer != this.summoner ? entityplayer : (entity != null && entity != this.summoner && entity != this ? entity : null));
    }

    @Override
    public void func_70636_d() {
        String[] pmj = JRMCoreH.pmj[Integer.parseInt(this.type)];
        this.pmje = Integer.parseInt(pmj[13]);
        if (!(this.field_70170_p.field_72995_K || this.summoner != null && this.summoner instanceof EntityPlayer && this.age <= 6000)) {
            this.func_70106_y();
            return;
        }
        double r = 10.0;
        AxisAlignedBB ab = AxisAlignedBB.func_72330_a((double)(this.field_70165_t - r), (double)(this.field_70163_u - r), (double)(this.field_70161_v - r), (double)(this.field_70165_t + r), (double)(this.field_70163_u + r), (double)(this.field_70161_v + r));
        List entityList = this.field_70170_p.func_72872_a(((Object)((Object)this)).getClass(), ab);
        if (entityList.size() > 5) {
            this.func_70106_y();
            return;
        }
        ++this.age;
        if (this.age == 1) {
            this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((double)this.maxHealth);
            this.func_70691_i(this.maxHealth);
        }
        this.teleportAndUpdate();
        super.func_70636_d();
    }

    @Override
    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        if (this.func_85032_ar()) {
            return false;
        }
        Entity var3 = par1DamageSource.func_76346_g();
        if (var3 instanceof EntityPlayer) {
            List var4 = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72314_b(32.0, 32.0, 32.0));
            for (int var5 = 0; var5 < var4.size(); ++var5) {
                Entity var6 = (Entity)var4.get(var5);
                if (!(var6 instanceof EntityNPCshadow)) continue;
                EntityNPCshadow var7 = (EntityNPCshadow)var6;
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
        return false;
    }

    @Override
    public int getAttackStrength(Entity par1Entity) {
        int dbcA = this.dam;
        return dbcA;
    }

    @Override
    public boolean func_70652_k(Entity par1Entity) {
        boolean var4;
        int var2 = this.getAttackStrength(par1Entity);
        if (this.func_70644_a(Potion.field_76420_g)) {
            var2 += 3 << this.func_70660_b(Potion.field_76420_g).func_76458_c();
        }
        if (this.func_70644_a(Potion.field_76437_t)) {
            var2 -= 2 << this.func_70660_b(Potion.field_76437_t).func_76458_c();
        }
        int var3 = 0;
        if (par1Entity instanceof EntityLiving) {
            var2 = (int)((float)var2 + EnchantmentHelper.func_77512_a((EntityLivingBase)this, (EntityLivingBase)((EntityLiving)par1Entity)));
            var3 += EnchantmentHelper.func_77507_b((EntityLivingBase)this, (EntityLivingBase)((EntityLiving)par1Entity));
        }
        if (var4 = par1Entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), (float)var2)) {
            int var5;
            if (var3 > 0) {
                par1Entity.func_70024_g((double)(-MathHelper.func_76126_a((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * (float)var3 * 0.5f), 0.1, (double)(MathHelper.func_76134_b((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * (float)var3 * 0.5f));
                this.field_70159_w *= 0.6;
                this.field_70179_y *= 0.6;
            }
            if ((var5 = EnchantmentHelper.func_90036_a((EntityLivingBase)this)) > 0) {
                par1Entity.func_70015_d(var5 * 4);
            }
        }
        return var4;
    }

    @Override
    public void func_70645_a(DamageSource par1DamageSource) {
        Entity var3 = par1DamageSource.func_76346_g();
        this.field_70170_p.func_72956_a((Entity)this, "jinryuunarutoc:NC1." + JRMCoreH.techNCSndHitPM[0], 1.0f, 1.0f);
        super.func_70645_a(par1DamageSource);
    }

    @Override
    protected void func_70785_a(Entity par1Entity, float par2) {
        if (this.field_70724_aR <= 0 && par2 < 2.0f && par1Entity.field_70121_D.field_72337_e > this.field_70121_D.field_72338_b && par1Entity.field_70121_D.field_72338_b < this.field_70121_D.field_72337_e) {
            this.field_70724_aR = 20;
            this.func_70652_k(par1Entity);
        }
    }

    public void writeSpawnData(ByteBuf data) {
        data.writeInt(this.dam);
        data.writeInt(this.maxHealth);
        ByteBufUtils.writeUTF8String((ByteBuf)data, (String)(this.summoner != null ? this.summoner.func_70005_c_() : ""));
    }

    public void readSpawnData(ByteBuf data) {
        this.dam = data.readInt();
        this.maxHealth = data.readInt();
        this.summoner = this.field_70170_p.func_72924_a(ByteBufUtils.readUTF8String((ByteBuf)data));
    }

    public void func_70014_b(NBTTagCompound nbtCompound) {
        super.func_70014_b(nbtCompound);
        nbtCompound.func_74768_a("NPCdam", this.dam);
        nbtCompound.func_74768_a("maxHealth", this.maxHealth);
        nbtCompound.func_74778_a("NPCsummoner", this.summoner != null ? this.summoner.func_70005_c_() : "");
    }

    public void func_70037_a(NBTTagCompound nbtCompound) {
        super.func_70037_a(nbtCompound);
        this.dam = nbtCompound.func_74762_e("NPCdam");
        this.maxHealth = nbtCompound.func_74762_e("maxHealth");
        this.summoner = this.field_70170_p.func_72924_a(nbtCompound.func_74779_i("NPCsummoner"));
    }

    private NBTTagCompound nbt(EntityPlayer p, String s) {
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

    private void teleportAndUpdate() {
        if (this.summoner != null) {
            int R = 32;
            ++this.wait;
            List entityList2 = this.field_70170_p.func_72872_a(EntityPlayer.class, this.field_70121_D.func_72314_b((double)R, (double)R, (double)R));
            if (!entityList2.isEmpty()) {
                for (int var5 = 0; var5 < entityList2.size(); ++var5) {
                    EntityPlayer var6 = (EntityPlayer)entityList2.get(var5);
                    if (!var6.func_70005_c_().equals(this.summoner.func_70005_c_())) continue;
                    this.becomeAngryAt((Entity)var6);
                    if (this.wait != 150 * this.m) continue;
                    boolean calculateSize = JRMCoreConfig.ShadowDummyScaleToTarget;
                    if (calculateSize) {
                        float height = this.field_70131_O;
                        float width = this.field_70130_N;
                        float heightTarget = this.summoner.field_70131_O;
                        float widthTarget = this.summoner.field_70130_N;
                        if (height != heightTarget || width != widthTarget) {
                            this.func_70105_a(widthTarget, heightTarget);
                        }
                    }
                    ++this.m;
                    if (this.field_70170_p.field_72995_K) continue;
                    this.func_70634_a(var6.field_70165_t, var6.field_70163_u + 1.5, var6.field_70161_v);
                    this.field_70170_p.func_72956_a((Entity)this, JRMCoreH.TeleportSound(JRMCoreH.getByte(var6, "jrmcPwrtyp")), 0.5f, this.field_70170_p.field_73012_v.nextFloat() * 0.1f + 0.9f);
                }
            }
        }
    }
}

