/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.VillagerRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.IMerchant
 *  net.minecraft.entity.INpc
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAvoidEntity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIFollowGolem
 *  net.minecraft.entity.ai.EntityAILookAtTradePlayer
 *  net.minecraft.entity.ai.EntityAIMoveIndoors
 *  net.minecraft.entity.ai.EntityAIOpenDoor
 *  net.minecraft.entity.ai.EntityAIPlay
 *  net.minecraft.entity.ai.EntityAIRestrictOpenDoor
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITradePlayer
 *  net.minecraft.entity.ai.EntityAIVillagerMate
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.ai.EntityAIWatchClosest2
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.monster.IMob
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Tuple
 *  net.minecraft.village.MerchantRecipe
 *  net.minecraft.village.MerchantRecipeList
 *  net.minecraft.village.Village
 *  net.minecraft.world.World
 */
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.DragonBC.common.Npcs.EntityNamekian01;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreH2;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.INpc;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollowGolem;
import net.minecraft.entity.ai.EntityAILookAtTradePlayer;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIPlay;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITradePlayer;
import net.minecraft.entity.ai.EntityAIVillagerMate;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Tuple;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.village.Village;
import net.minecraft.world.World;

public class EntityNamekian03
extends EntityVillager
implements INpc,
IMerchant {
    public final int AttPow = 30;
    public final int HePo = 300;
    private int randomTickDivider = 0;
    private boolean isMating = false;
    private boolean isPlaying = false;
    Village field_70954_d = null;
    private EntityPlayer buyingPlayer;
    private MerchantRecipeList buyingList;
    private int timeUntilReset;
    private boolean needsInitilization;
    private int wealth;
    private String lastBuyingPlayer;
    private boolean field_82190_bM;
    private float field_82191_bN;
    public static final Map villagerStockList = new HashMap();
    public static final Map blacksmithSellingList = new HashMap();

    public EntityNamekian03(World par1World) {
        this(par1World, 0);
    }

    public EntityNamekian03(World par1World, int par2) {
        super(par1World);
        this.func_70938_b(par2);
        this.func_70661_as().func_75498_b(true);
        this.func_70661_as().func_75491_a(true);
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIAvoidEntity((EntityCreature)this, EntityZombie.class, 8.0f, (double)0.3f, (double)0.35f));
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAITradePlayer((EntityVillager)this));
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAILookAtTradePlayer((EntityVillager)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIMoveIndoors((EntityCreature)this));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIRestrictOpenDoor((EntityCreature)this));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIOpenDoor((EntityLiving)this, true));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIVillagerMate((EntityVillager)this));
        this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIFollowGolem((EntityVillager)this));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIPlay((EntityVillager)this, (double)0.32f));
        this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityPlayer.class, 3.0f, 1.0f));
        this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIWatchClosest2((EntityLiving)this, EntityVillager.class, 5.0f, 0.02f));
        this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIWander((EntityCreature)this, (double)0.3f));
        this.field_70714_bg.func_75776_a(10, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityLiving.class, 8.0f));
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
                if (!(var6 instanceof EntityNamekian01)) continue;
                EntityNamekian01 var7 = (EntityNamekian01)var6;
                var7.becomeAngryAt(var3);
            }
        }
        return super.func_70097_a(par1DamageSource, par2);
    }

    public boolean func_70650_aV() {
        return true;
    }

    protected void func_70629_bd() {
        if (--this.randomTickDivider <= 0) {
            this.field_70170_p.field_72982_D.func_75551_a(MathHelper.func_76128_c((double)this.field_70165_t), MathHelper.func_76128_c((double)this.field_70163_u), MathHelper.func_76128_c((double)this.field_70161_v));
            this.randomTickDivider = 70 + this.field_70146_Z.nextInt(50);
            this.field_70954_d = this.field_70170_p.field_72982_D.func_75550_a(MathHelper.func_76128_c((double)this.field_70165_t), MathHelper.func_76128_c((double)this.field_70163_u), MathHelper.func_76128_c((double)this.field_70161_v), 32);
            if (this.field_70954_d != null) {
                ChunkCoordinates var1 = this.field_70954_d.func_75577_a();
                if (this.field_82190_bM) {
                    this.field_82190_bM = false;
                }
            }
        }
        if (!this.func_70940_q() && this.timeUntilReset > 0) {
            --this.timeUntilReset;
            if (this.timeUntilReset <= 0) {
                if (this.needsInitilization) {
                    if (this.buyingList.size() > 1) {
                        for (MerchantRecipe merchantRecipe : this.buyingList) {
                        }
                    }
                    this.addDefaultEquipmentAndRecipies(1);
                    this.needsInitilization = false;
                    if (this.field_70954_d != null && this.lastBuyingPlayer != null) {
                        this.field_70170_p.func_72960_a((Entity)this, (byte)14);
                        this.field_70954_d.func_82688_a(this.lastBuyingPlayer, 1);
                    }
                }
                this.func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 200, 0));
            }
        }
        super.func_70629_bd();
    }

    public boolean func_70085_c(EntityPlayer par1EntityPlayer) {
        return true;
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(20, (Object)0);
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(300.0);
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("Profession", this.func_70946_n());
        par1NBTTagCompound.func_74768_a("Riches", this.wealth);
        if (this.buyingList != null) {
            par1NBTTagCompound.func_74782_a("Offers", (NBTBase)this.buyingList.func_77202_a());
        }
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        this.func_70938_b(par1NBTTagCompound.func_74762_e("Profession"));
        this.wealth = par1NBTTagCompound.func_74762_e("Riches");
        if (par1NBTTagCompound.func_74764_b("Offers")) {
            NBTTagCompound var2 = par1NBTTagCompound.func_74775_l("Offers");
            this.buyingList = new MerchantRecipeList(var2);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public String getTexture() {
        return "jinryuudragonbc:npcs/namek02.png";
    }

    protected boolean func_70692_ba() {
        return false;
    }

    public void func_70938_b(int par1) {
        this.field_70180_af.func_75692_b(16, (Object)par1);
    }

    public int func_70946_n() {
        return this.field_70180_af.func_75679_c(16);
    }

    public boolean func_70941_o() {
        return this.isMating;
    }

    public void func_70947_e(boolean par1) {
        this.isMating = par1;
    }

    public void func_70939_f(boolean par1) {
        this.isPlaying = par1;
    }

    public boolean func_70945_p() {
        return this.isPlaying;
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        if (this.field_70954_d != null) {
            EntityPlayer var3;
            Entity var2 = par1DamageSource.func_76346_g();
            if (var2 != null) {
                if (var2 instanceof EntityPlayer) {
                    int al = JRMCoreH.getByte((EntityPlayer)var2, "jrmcAlign");
                    al = (al -= 20) < 0 ? 0 : al;
                    JRMCoreH.setByte(al, (EntityPlayer)var2, "jrmcAlign");
                    int kr = JRMCoreH.getInt((EntityPlayer)var2, "jrmcKarma");
                    JRMCoreH.setInt(kr + 1, (EntityPlayer)var2, "jrmcKarma");
                    ((EntityPlayer)var2).func_145747_a(new ChatComponentTranslation(JRMCoreH.trlai("dbc.moreevil.line1"), new Object[0]).func_150255_a(JRMCoreH2.styl_wht));
                    this.field_70954_d.func_82688_a(((EntityPlayer)var2).func_70005_c_(), -2);
                } else if (var2 instanceof IMob) {
                    this.field_70954_d.func_82692_h();
                }
            } else if (var2 == null && (var3 = this.field_70170_p.func_72890_a((Entity)this, 16.0)) != null) {
                this.field_70954_d.func_82692_h();
            }
        }
        super.func_70645_a(par1DamageSource);
    }

    public void func_70932_a_(EntityPlayer par1EntityPlayer) {
        this.buyingPlayer = par1EntityPlayer;
    }

    public EntityPlayer func_70931_l_() {
        return this.buyingPlayer;
    }

    public boolean func_70940_q() {
        return this.buyingPlayer != null;
    }

    public void func_70933_a(MerchantRecipe par1MerchantRecipe) {
        par1MerchantRecipe.func_77399_f();
        if (par1MerchantRecipe.func_77393_a((MerchantRecipe)this.buyingList.get(this.buyingList.size() - 1))) {
            this.timeUntilReset = 40;
            this.needsInitilization = true;
            this.lastBuyingPlayer = this.buyingPlayer != null ? this.buyingPlayer.func_70005_c_() : null;
        }
    }

    public MerchantRecipeList func_70934_b(EntityPlayer par1EntityPlayer) {
        if (this.buyingList == null) {
            this.addDefaultEquipmentAndRecipies(1);
        }
        return this.buyingList;
    }

    private float adjustProbability(float par1) {
        float var2 = par1 + this.field_82191_bN;
        return var2 > 0.9f ? 0.9f - (var2 - 0.9f) : var2;
    }

    private void addDefaultEquipmentAndRecipies(int par1) {
        this.field_82191_bN = this.buyingList != null ? MathHelper.func_76129_c((float)this.buyingList.size()) * 0.2f : 0.0f;
        MerchantRecipeList var2 = new MerchantRecipeList();
        VillagerRegistry.manageVillagerTrades((MerchantRecipeList)var2, (EntityVillager)this, (int)this.func_70946_n(), (Random)this.field_70146_Z);
        if (var2.isEmpty()) {
            // empty if block
        }
        Collections.shuffle(var2);
        if (this.buyingList == null) {
            this.buyingList = new MerchantRecipeList();
        }
        for (int var9 = 0; var9 < par1 && var9 < var2.size(); ++var9) {
            this.buyingList.func_77205_a((MerchantRecipe)var2.get(var9));
        }
    }

    private static int getRandomCountForItem(int par0, Random par1Random) {
        Tuple var2 = (Tuple)villagerStockList.get(par0);
        return var2 == null ? 1 : ((Integer)var2.func_76341_a() >= (Integer)var2.func_76340_b() ? (Integer)var2.func_76341_a() : (Integer)var2.func_76341_a() + par1Random.nextInt((Integer)var2.func_76340_b() - (Integer)var2.func_76341_a()));
    }

    private static int getRandomCountForBlacksmithItem(int par0, Random par1Random) {
        Tuple var2 = (Tuple)blacksmithSellingList.get(par0);
        return var2 == null ? 1 : ((Integer)var2.func_76341_a() >= (Integer)var2.func_76340_b() ? (Integer)var2.func_76341_a() : (Integer)var2.func_76341_a() + par1Random.nextInt((Integer)var2.func_76340_b() - (Integer)var2.func_76341_a()));
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte par1) {
        if (par1 == 12) {
            this.generateRandomParticles("heart");
        } else if (par1 == 13) {
            this.generateRandomParticles("angryVillager");
        } else if (par1 == 14) {
            this.generateRandomParticles("happyVillager");
        } else {
            super.func_70103_a(par1);
        }
    }

    @SideOnly(value=Side.CLIENT)
    private void generateRandomParticles(String par1Str) {
        for (int var2 = 0; var2 < 5; ++var2) {
            double var3 = this.field_70146_Z.nextGaussian() * 0.02;
            double var5 = this.field_70146_Z.nextGaussian() * 0.02;
            double var7 = this.field_70146_Z.nextGaussian() * 0.02;
            this.field_70170_p.func_72869_a(par1Str, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u + 1.0 + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, var3, var5, var7);
        }
    }

    public void initCreature() {
        VillagerRegistry.applyRandomTrade((EntityVillager)this, (Random)this.field_70170_p.field_73012_v);
    }

    public void func_82187_q() {
        this.field_82190_bM = true;
    }

    public EntityVillager func_90012_b(EntityAgeable par1EntityAgeable) {
        EntityVillager var2 = new EntityVillager(this.field_70170_p);
        return var2;
    }
}

