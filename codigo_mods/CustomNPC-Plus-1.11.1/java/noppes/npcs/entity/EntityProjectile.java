/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S27PacketExplosion
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package noppes.npcs.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kamkeel.npcs.addon.DBCAddon;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.packets.data.script.ScriptedParticlePacket;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import noppes.npcs.DataStats;
import noppes.npcs.EventHooks;
import noppes.npcs.api.IPos;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IProjectile;
import noppes.npcs.constants.EnumParticleType;
import noppes.npcs.constants.EnumPotionType;
import noppes.npcs.controllers.ScriptContainer;
import noppes.npcs.entity.EntityNPCInterface;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.ScriptParticle;
import noppes.npcs.scripted.event.ProjectileEvent;
import noppes.npcs.util.IProjectileCallback;

public class EntityProjectile
extends EntityThrowable {
    private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private Block inTile;
    protected boolean field_70193_a = false;
    private int inData = 0;
    public int field_70191_b = 0;
    public int arrowShake = 0;
    public boolean canBePickedUp = false;
    public boolean destroyedOnEntityHit = true;
    private boolean ignoreInvincibility = false;
    private EntityLivingBase thrower;
    private EntityNPCInterface npc;
    public EntityItem entityitem;
    private String throwerName = null;
    private int ticksInGround;
    public int field_70195_i = 0;
    private double accelerationX;
    private double accelerationY;
    private double accelerationZ;
    public float damage = 5.0f;
    public int punch = 0;
    public boolean accelerate = false;
    public boolean explosive = false;
    public boolean explosiveDamage = true;
    public boolean destroyTerrain = true;
    public int explosiveRadius = 0;
    public EnumPotionType effect = EnumPotionType.None;
    public int manualPotionId = 0;
    public boolean burnItem = true;
    public int duration = 5;
    public int amplify = 0;
    public int accuracy = 60;
    public IProjectileCallback callback;
    public ItemStack callbackItem;
    public List<ScriptContainer> scripts = new ArrayList<ScriptContainer>();

    public EntityProjectile(World par1World) {
        super(par1World);
        this.func_70105_a(0.25f, 0.25f);
    }

    protected void func_70088_a() {
        this.field_70180_af.func_82709_a(21, 5);
        this.field_70180_af.func_75682_a(22, (Object)String.valueOf(""));
        this.field_70180_af.func_75682_a(23, (Object)5);
        this.field_70180_af.func_75682_a(24, (Object)0);
        this.field_70180_af.func_75682_a(25, (Object)10);
        this.field_70180_af.func_75682_a(26, (Object)0);
        this.field_70180_af.func_75682_a(27, (Object)0);
        this.field_70180_af.func_75682_a(28, (Object)0);
        this.field_70180_af.func_75682_a(29, (Object)0);
        this.field_70180_af.func_75682_a(30, (Object)0);
        this.field_70180_af.func_75682_a(31, (Object)0);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_70112_a(double par1) {
        double d1 = this.field_70121_D.func_72320_b() * 4.0;
        return par1 < (d1 *= 64.0) * d1;
    }

    public EntityProjectile(World par1World, EntityLivingBase par2EntityLiving, ItemStack item, boolean isNPC) {
        super(par1World);
        this.thrower = par2EntityLiving;
        if (this.thrower != null) {
            this.throwerName = this.thrower.func_110124_au().toString();
        }
        this.setThrownItem(item);
        this.field_70180_af.func_75692_b(27, (Object)((byte)(this.getItem() == Items.field_151032_g ? 1 : 0)));
        this.func_70105_a(this.field_70180_af.func_75679_c(23) / 10, this.field_70180_af.func_75679_c(23) / 10);
        this.func_70012_b(par2EntityLiving.field_70165_t, par2EntityLiving.field_70163_u + (double)par2EntityLiving.func_70047_e(), par2EntityLiving.field_70161_v, par2EntityLiving.field_70177_z, par2EntityLiving.field_70125_A);
        this.field_70165_t -= (double)(MathHelper.func_76134_b((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * 0.1f);
        this.field_70163_u -= (double)0.1f;
        this.field_70161_v -= (double)(MathHelper.func_76126_a((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * 0.1f);
        this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.field_70129_M = 0.0f;
        if (isNPC) {
            this.npc = (EntityNPCInterface)this.thrower;
            this.getStatProperties(this.npc.stats);
            this.destroyTerrain = !this.npc.stats.projectilesKeepTerrain;
        }
    }

    public void setThrownItem(ItemStack item) {
        this.field_70180_af.func_75692_b(21, (Object)item);
    }

    public void func_70186_c(double par1, double par3, double par5, float par7, float par8) {
        float f2 = MathHelper.func_76133_a((double)(par1 * par1 + par3 * par3 + par5 * par5));
        float f3 = MathHelper.func_76133_a((double)(par1 * par1 + par5 * par5));
        float yaw = (float)(Math.atan2(par1, par5) * 180.0 / Math.PI);
        float pitch = this.hasGravity() ? par7 : (float)(Math.atan2(par3, f3) * 180.0 / Math.PI);
        this.field_70126_B = this.field_70177_z = yaw;
        this.field_70127_C = this.field_70125_A = pitch;
        this.field_70159_w = MathHelper.func_76126_a((float)(yaw / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(pitch / 180.0f * (float)Math.PI));
        this.field_70179_y = MathHelper.func_76134_b((float)(yaw / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(pitch / 180.0f * (float)Math.PI));
        this.field_70181_x = MathHelper.func_76126_a((float)((pitch + 1.0f) / 180.0f * (float)Math.PI));
        this.field_70159_w += this.field_70146_Z.nextGaussian() * (double)0.0075f * (double)par8;
        this.field_70179_y += this.field_70146_Z.nextGaussian() * (double)0.0075f * (double)par8;
        this.field_70181_x += this.field_70146_Z.nextGaussian() * (double)0.0075f * (double)par8;
        this.field_70159_w *= (double)this.getSpeed();
        this.field_70179_y *= (double)this.getSpeed();
        this.field_70181_x *= (double)this.getSpeed();
        this.accelerationX = par1 / (double)f2 * 0.1;
        this.accelerationY = par3 / (double)f2 * 0.1;
        this.accelerationZ = par5 / (double)f2 * 0.1;
        this.ticksInGround = 0;
    }

    public float getAngleForXYZ(double varX, double varY, double varZ, double horiDist, boolean arc) {
        float g = this.func_70185_h();
        float var1 = this.getSpeed() * this.getSpeed();
        double var2 = (double)g * horiDist;
        double var3 = (double)g * horiDist * horiDist + 2.0 * varY * (double)var1;
        double var4 = (double)(var1 * var1) - (double)g * var3;
        if (var4 < 0.0) {
            return 30.0f;
        }
        float var6 = arc ? var1 + MathHelper.func_76133_a((double)var4) : var1 - MathHelper.func_76133_a((double)var4);
        float var7 = (float)(Math.atan2(var6, var2) * 180.0 / Math.PI);
        return var7;
    }

    public void shoot(float speed) {
        double varX = -MathHelper.func_76126_a((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        double varZ = MathHelper.func_76134_b((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        double varY = -MathHelper.func_76126_a((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.func_70186_c(varX, varY, varZ, -this.field_70125_A, speed);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70056_a(double par1, double par3, double par5, float par7, float par8, int par9) {
        if (this.field_70170_p.field_72995_K && this.field_70193_a) {
            return;
        }
        this.func_70107_b(par1, par3, par5);
        this.func_70101_b(par7, par8);
    }

    public void func_70071_h_() {
        super.func_70030_z();
        if (this.field_70173_aa >= 1200) {
            this.func_70106_y();
        }
        if (++this.field_70173_aa % 10 == 0) {
            EventHooks.onProjectileTick(this);
        }
        if (this.field_70127_C == 0.0f && this.field_70126_B == 0.0f) {
            float f = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y));
            this.field_70126_B = this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0 / Math.PI);
            this.field_70127_C = this.field_70125_A = (float)(Math.atan2(this.field_70181_x, f) * 180.0 / Math.PI);
            if (this.isRotating()) {
                this.field_70125_A -= 20.0f;
            }
        }
        if (this.effect == EnumPotionType.Fire && !this.field_70193_a && this.burnItem) {
            this.func_70015_d(1);
        }
        Block block = this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile);
        if ((this.isArrow() || this.sticksToWalls()) && block != null) {
            block.func_149719_a((IBlockAccess)this.field_70170_p, this.xTile, this.yTile, this.zTile);
            AxisAlignedBB axisalignedbb = block.func_149668_a(this.field_70170_p, this.xTile, this.yTile, this.zTile);
            if (axisalignedbb != null && axisalignedbb.func_72318_a(Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v))) {
                this.field_70193_a = true;
            }
        }
        if (this.arrowShake > 0) {
            --this.arrowShake;
        }
        if (this.field_70193_a) {
            int j = this.field_70170_p.func_72805_g(this.xTile, this.yTile, this.zTile);
            if (block == this.inTile && j == this.inData) {
                ++this.ticksInGround;
                if (this.ticksInGround >= 1200) {
                    this.func_70106_y();
                }
            } else {
                this.field_70193_a = false;
                this.field_70159_w *= (double)(this.field_70146_Z.nextFloat() * 0.2f);
                this.field_70181_x *= (double)(this.field_70146_Z.nextFloat() * 0.2f);
                this.field_70179_y *= (double)(this.field_70146_Z.nextFloat() * 0.2f);
                this.ticksInGround = 0;
                this.field_70195_i = 0;
            }
        } else {
            ++this.field_70195_i;
            if (this.field_70195_i >= 1200) {
                this.func_70106_y();
            }
            Vec3 vec3 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            Vec3 vec31 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
            MovingObjectPosition movingobjectposition = this.field_70170_p.func_147447_a(vec3, vec31, false, true, false);
            vec3 = Vec3.func_72443_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v);
            vec31 = Vec3.func_72443_a((double)(this.field_70165_t + this.field_70159_w), (double)(this.field_70163_u + this.field_70181_x), (double)(this.field_70161_v + this.field_70179_y));
            if (movingobjectposition != null) {
                vec31 = Vec3.func_72443_a((double)movingobjectposition.field_72307_f.field_72450_a, (double)movingobjectposition.field_72307_f.field_72448_b, (double)movingobjectposition.field_72307_f.field_72449_c);
            }
            if (!this.field_70170_p.field_72995_K) {
                EntityPlayer entityplayer;
                Entity entity = null;
                List list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0, 1.0, 1.0));
                double d0 = 0.0;
                EntityLivingBase entityliving = this.func_85052_h();
                for (int k = 0; k < list.size(); ++k) {
                    double d1;
                    float f;
                    AxisAlignedBB axisalignedbb;
                    MovingObjectPosition movingobjectposition1;
                    Entity entity1 = (Entity)list.get(k);
                    if (!entity1.func_70067_L() || entity1.func_70028_i((Entity)this.thrower) && this.field_70195_i < 25 || (movingobjectposition1 = (axisalignedbb = entity1.field_70121_D.func_72314_b((double)(f = 0.3f), (double)f, (double)f)).func_72327_a(vec3, vec31)) == null || !((d1 = vec3.func_72438_d(movingobjectposition1.field_72307_f)) < d0) && d0 != 0.0) continue;
                    entity = entity1;
                    d0 = d1;
                }
                if (entity != null) {
                    movingobjectposition = new MovingObjectPosition(entity);
                }
                if (this.npc != null && movingobjectposition != null && movingobjectposition.field_72308_g != null && movingobjectposition.field_72308_g instanceof EntityPlayer && this.npc.faction.isFriendlyToPlayer(entityplayer = (EntityPlayer)movingobjectposition.field_72308_g)) {
                    movingobjectposition = null;
                }
            }
            if (movingobjectposition != null) {
                if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && this.field_70170_p.func_147439_a(movingobjectposition.field_72311_b, movingobjectposition.field_72312_c, movingobjectposition.field_72309_d) == Blocks.field_150427_aO) {
                    this.func_70063_aa();
                } else {
                    this.field_70180_af.func_75692_b(29, (Object)0);
                    this.func_70184_a(movingobjectposition);
                }
            }
            this.field_70165_t += this.field_70159_w;
            this.field_70163_u += this.field_70181_x;
            this.field_70161_v += this.field_70179_y;
            float f1 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y));
            this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0 / Math.PI);
            this.field_70125_A = (float)(Math.atan2(this.field_70181_x, f1) * 180.0 / Math.PI);
            while (this.field_70125_A - this.field_70127_C < -180.0f) {
                this.field_70127_C -= 360.0f;
            }
            while (this.field_70125_A - this.field_70127_C >= 180.0f) {
                this.field_70127_C += 360.0f;
            }
            while (this.field_70177_z - this.field_70126_B < -180.0f) {
                this.field_70126_B -= 360.0f;
            }
            while (this.field_70177_z - this.field_70126_B >= 180.0f) {
                this.field_70126_B += 360.0f;
            }
            float f = this.isArrow() ? 0.0f : 225.0f;
            this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) + f * 0.2f;
            this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2f;
            if (this.isRotating()) {
                int spin = this.isBlock() ? 10 : 20;
                this.field_70125_A -= (float)(this.field_70195_i % 15 * spin) * this.getSpeed();
            }
            float f2 = this.getMotionFactor();
            float f3 = this.func_70185_h();
            if (this.func_70090_H()) {
                if (this.field_70170_p.field_72995_K) {
                    for (int k = 0; k < 4; ++k) {
                        float f4 = 0.25f;
                        this.field_70170_p.func_72869_a("bubble", this.field_70165_t - this.field_70159_w * (double)f4, this.field_70163_u - this.field_70181_x * (double)f4, this.field_70161_v - this.field_70179_y * (double)f4, this.field_70159_w, this.field_70181_x, this.field_70179_y);
                    }
                }
                f2 = 0.8f;
            }
            this.field_70159_w *= (double)f2;
            this.field_70181_x *= (double)f2;
            this.field_70179_y *= (double)f2;
            if (this.hasGravity()) {
                this.field_70181_x -= (double)f3;
            }
            if (this.accelerate) {
                this.field_70159_w += this.accelerationX;
                this.field_70181_x += this.accelerationY;
                this.field_70179_y += this.accelerationZ;
            }
            if (this.field_70170_p.field_72995_K && !this.field_70180_af.func_75681_e(22).isEmpty()) {
                String particle = this.field_70180_af.func_75681_e(22);
                if (!particle.equals("custom")) {
                    this.field_70170_p.func_72869_a(particle, this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0, 0.0, 0.0);
                }
            } else if (!this.field_70170_p.field_72995_K && this.npc != null && this.npc.stats.pTrail == EnumParticleType.Custom) {
                this.npc.stats.pCustom.setPosition(this.field_70165_t, this.field_70163_u, this.field_70161_v);
                EntityProjectile.spawnScriptedParticle(this.npc.stats.pCustom.writeToNBT(), this.npc);
            }
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            this.func_145775_I();
        }
    }

    public static void spawnScriptedParticle(NBTTagCompound compound, EntityNPCInterface entity) {
        PacketHandler.Instance.sendTracking(new ScriptedParticlePacket(compound), (Entity)entity);
    }

    public boolean isBlock() {
        ItemStack item = this.getItemDisplay();
        if (item == null) {
            return false;
        }
        return item.func_77973_b() instanceof ItemBlock;
    }

    private Item getItem() {
        ItemStack item = this.getItemDisplay();
        if (item == null) {
            return null;
        }
        return item.func_77973_b();
    }

    protected float getMotionFactor() {
        return this.accelerate ? 0.95f : 1.0f;
    }

    protected void func_70184_a(MovingObjectPosition movingobjectposition) {
        block52: {
            block49: {
                block50: {
                    int potionId;
                    EntityLivingBase entityliving;
                    block51: {
                        float f3;
                        boolean attacked;
                        if (!this.field_70170_p.field_72995_K) {
                            ProjectileEvent.ImpactEvent event;
                            if (movingobjectposition.field_72308_g != null) {
                                IEntity<?> target = NpcAPI.Instance().getIEntity(movingobjectposition.field_72308_g);
                                event = new ProjectileEvent.ImpactEvent((IProjectile)((Object)NpcAPI.Instance().getIEntity((Entity)this)), 0, target);
                            } else {
                                IPos pos = NpcAPI.Instance().getIPos(movingobjectposition.field_72311_b, movingobjectposition.field_72312_c, movingobjectposition.field_72309_d);
                                IWorld world = NpcAPI.Instance().getIWorld(this.field_70170_p);
                                event = new ProjectileEvent.ImpactEvent((IProjectile)((Object)NpcAPI.Instance().getIEntity((Entity)this)), 1, NpcAPI.Instance().getIBlock(world, pos));
                            }
                            if (movingobjectposition.field_72308_g != null && this.callback != null && this.callbackItem != null && movingobjectposition.field_72308_g instanceof EntityLivingBase && this.callback.onImpact(this, (EntityLivingBase)movingobjectposition.field_72308_g, this.callbackItem)) {
                                return;
                            }
                            EventHooks.onProjectileImpact(this, event);
                        }
                        if (movingobjectposition.field_72308_g == null) break block49;
                        float damage = this.damage;
                        if (damage == 0.0f) {
                            damage = 0.001f;
                        }
                        boolean didDBCAttack = false;
                        if (this.npc != null && DBCAddon.instance.canDBCAttack(this.npc, this.damage, movingobjectposition.field_72308_g)) {
                            damage = 0.001f;
                            didDBCAttack = true;
                        }
                        entityliving = movingobjectposition.field_72308_g instanceof EntityLivingBase ? (EntityLivingBase)movingobjectposition.field_72308_g : null;
                        int prevHurtResistantTime = -1;
                        if (this.ignoreInvincibility && entityliving != null) {
                            prevHurtResistantTime = entityliving.field_70172_ad;
                            entityliving.field_70172_ad = 0;
                        }
                        if (!(attacked = movingobjectposition.field_72308_g.func_70097_a(DamageSource.func_76356_a((Entity)this, (Entity)this.func_85052_h()), damage)) && this.ignoreInvincibility && entityliving != null) {
                            entityliving.field_70172_ad = prevHurtResistantTime;
                        }
                        if (!attacked) break block50;
                        if (didDBCAttack) {
                            DBCAddon.instance.doDBCDamage(this.npc, this.damage, movingobjectposition.field_72308_g);
                        }
                        if (entityliving != null && (this.isArrow() || this.sticksToWalls())) {
                            if (!this.field_70170_p.field_72995_K) {
                                entityliving.func_85034_r(entityliving.func_85035_bI() + 1);
                            }
                            if (this.destroyedOnEntityHit && !(movingobjectposition.field_72308_g instanceof EntityEnderman)) {
                                this.func_70106_y();
                            }
                        }
                        if (this.isBlock()) {
                            this.field_70170_p.func_72926_e(2001, (int)movingobjectposition.field_72308_g.field_70165_t, (int)movingobjectposition.field_72308_g.field_70163_u, (int)movingobjectposition.field_72308_g.field_70161_v, Item.func_150891_b((Item)this.getItem()));
                        } else if (!this.isArrow() && !this.sticksToWalls()) {
                            for (int i = 0; i < 8; ++i) {
                                this.field_70170_p.func_72869_a("iconcrack_" + Item.func_150891_b((Item)this.getItem()), this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70146_Z.nextGaussian() * 0.15, this.field_70146_Z.nextGaussian() * 0.2, this.field_70146_Z.nextGaussian() * 0.15);
                            }
                        }
                        if (this.punch > 0 && (f3 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y))) > 0.0f) {
                            movingobjectposition.field_72308_g.func_70024_g(this.field_70159_w * (double)this.punch * (double)0.6f / (double)f3, 0.1, this.field_70179_y * (double)this.punch * (double)0.6f / (double)f3);
                        }
                        if (this.effect != EnumPotionType.Fire || entityliving == null) break block51;
                        movingobjectposition.field_72308_g.func_70015_d(this.duration);
                        break block52;
                    }
                    if (this.effect == EnumPotionType.None || entityliving == null || !EnumPotionType.isValidPotionId(potionId = this.effect.getResolvedPotionId(this.manualPotionId))) break block52;
                    entityliving.func_70690_d(new PotionEffect(potionId, this.duration * 20, this.amplify));
                    break block52;
                }
                if (!this.hasGravity() || !this.isArrow() && !this.sticksToWalls()) break block52;
                this.field_70159_w *= (double)-0.1f;
                this.field_70181_x *= (double)-0.1f;
                this.field_70179_y *= (double)-0.1f;
                this.field_70177_z += 180.0f;
                this.field_70126_B += 180.0f;
                this.field_70195_i = 0;
                break block52;
            }
            if (this.isArrow() || this.sticksToWalls()) {
                this.xTile = movingobjectposition.field_72311_b;
                this.yTile = movingobjectposition.field_72312_c;
                this.zTile = movingobjectposition.field_72309_d;
                this.inTile = this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile);
                this.inData = this.field_70170_p.func_72805_g(this.xTile, this.yTile, this.zTile);
                this.field_70159_w = (float)(movingobjectposition.field_72307_f.field_72450_a - this.field_70165_t);
                this.field_70181_x = (float)(movingobjectposition.field_72307_f.field_72448_b - this.field_70163_u);
                this.field_70179_y = (float)(movingobjectposition.field_72307_f.field_72449_c - this.field_70161_v);
                float f2 = MathHelper.func_76133_a((double)(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y));
                this.field_70165_t -= this.field_70159_w / (double)f2 * (double)0.05f;
                this.field_70163_u -= this.field_70181_x / (double)f2 * (double)0.05f;
                this.field_70161_v -= this.field_70179_y / (double)f2 * (double)0.05f;
                this.field_70193_a = true;
                if (this.isArrow()) {
                    this.func_85030_a("random.bowhit", 1.0f, 1.2f / (this.field_70146_Z.nextFloat() * 0.2f + 0.9f));
                } else {
                    this.func_85030_a("random.break", 1.0f, 1.2f / (this.field_70146_Z.nextFloat() * 0.2f + 0.9f));
                }
                this.arrowShake = 7;
                if (!this.hasGravity()) {
                    this.field_70180_af.func_75692_b(26, (Object)1);
                }
                if (this.inTile != null) {
                    this.inTile.func_149670_a(this.field_70170_p, this.xTile, this.yTile, this.zTile, (Entity)this);
                }
            } else if (this.isBlock()) {
                this.field_70170_p.func_72926_e(2001, MathHelper.func_76128_c((double)this.field_70165_t), MathHelper.func_76128_c((double)this.field_70163_u), MathHelper.func_76128_c((double)this.field_70161_v), Item.func_150891_b((Item)this.getItem()));
            } else {
                for (int i = 0; i < 8; ++i) {
                    this.field_70170_p.func_72869_a("iconcrack_" + Item.func_150891_b((Item)this.getItem()), this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70146_Z.nextGaussian() * 0.15, this.field_70146_Z.nextGaussian() * 0.2, this.field_70146_Z.nextGaussian() * 0.15);
                }
            }
        }
        if (this.explosive) {
            if (this.explosiveRadius != 0 || this.effect == EnumPotionType.None) {
                boolean terraindamage = this.field_70170_p.func_82736_K().func_82766_b("mobGriefing") && this.explosiveDamage && this.destroyTerrain;
                Explosion explosion = new Explosion(this.field_70170_p, (Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, (float)this.explosiveRadius);
                explosion.field_77286_a = this.effect == EnumPotionType.Fire;
                explosion.field_82755_b = terraindamage;
                if (terraindamage) {
                    explosion.func_77278_a();
                }
                explosion.func_77279_a(this.field_70170_p.field_72995_K);
                if (!this.field_70170_p.field_72995_K) {
                    for (EntityPlayer entityplayer : this.field_70170_p.field_73010_i) {
                        if (!(entityplayer.func_70092_e(this.field_70165_t, this.field_70163_u, this.field_70161_v) < 4096.0)) continue;
                        ((EntityPlayerMP)entityplayer).field_71135_a.func_147359_a((Packet)new S27PacketExplosion(this.field_70165_t, this.field_70163_u, this.field_70161_v, (float)this.explosiveRadius, explosion.field_77281_g, (Vec3)explosion.func_77277_b().get(entityplayer)));
                    }
                }
                if (this.explosiveRadius != 0 && (this.isArrow() || this.sticksToWalls())) {
                    this.func_70106_y();
                }
            } else if (this.effect == EnumPotionType.Fire) {
                int i = movingobjectposition.field_72311_b;
                int j = movingobjectposition.field_72312_c;
                int k = movingobjectposition.field_72309_d;
                switch (movingobjectposition.field_72310_e) {
                    case 0: {
                        --j;
                        break;
                    }
                    case 1: {
                        ++j;
                        break;
                    }
                    case 2: {
                        --k;
                        break;
                    }
                    case 3: {
                        ++k;
                        break;
                    }
                    case 4: {
                        --i;
                        break;
                    }
                    case 5: {
                        ++i;
                    }
                }
                if (this.field_70170_p.func_147437_c(i, j, k)) {
                    this.field_70170_p.func_147449_b(i, j, k, (Block)Blocks.field_150480_ab);
                }
            } else {
                AxisAlignedBB axisalignedbb = this.field_70121_D.func_72314_b(4.0, 2.0, 4.0);
                List list1 = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
                if (list1 != null && !list1.isEmpty()) {
                    for (EntityLivingBase entitylivingbase : list1) {
                        int potionId;
                        double d0 = this.func_70068_e((Entity)entitylivingbase);
                        if (!(d0 < 16.0)) continue;
                        double d1 = 1.0 - Math.sqrt(d0) / 4.0;
                        if (entitylivingbase == movingobjectposition.field_72308_g) {
                            d1 = 1.0;
                        }
                        if (!EnumPotionType.isValidPotionId(potionId = this.effect.getResolvedPotionId(this.manualPotionId))) continue;
                        if (Potion.field_76425_a[potionId].func_76403_b()) {
                            Potion.field_76425_a[potionId].func_76402_a(this.func_85052_h(), entitylivingbase, this.amplify, d1);
                            continue;
                        }
                        int j = (int)(d1 * (double)this.duration + 0.5);
                        if (j <= 20) continue;
                        entitylivingbase.func_70690_d(new PotionEffect(potionId, j, this.amplify));
                    }
                }
                this.field_70170_p.func_72926_e(2002, (int)Math.round(this.field_70165_t), (int)Math.round(this.field_70163_u), (int)Math.round(this.field_70161_v), this.getPotionColor());
            }
        }
        if (!(this.field_70170_p.field_72995_K || this.isArrow() || this.sticksToWalls())) {
            this.func_70106_y();
        }
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.func_74777_a("xTile", (short)this.xTile);
        par1NBTTagCompound.func_74777_a("yTile", (short)this.yTile);
        par1NBTTagCompound.func_74777_a("zTile", (short)this.zTile);
        par1NBTTagCompound.func_74774_a("inTile", (byte)Block.func_149682_b((Block)this.inTile));
        par1NBTTagCompound.func_74774_a("inData", (byte)this.inData);
        par1NBTTagCompound.func_74774_a("shake", (byte)this.field_70191_b);
        par1NBTTagCompound.func_74774_a("inGround", (byte)(this.field_70193_a ? 1 : 0));
        par1NBTTagCompound.func_74774_a("isArrow", (byte)(this.isArrow() ? 1 : 0));
        par1NBTTagCompound.func_74782_a("direction", (NBTBase)this.func_70087_a(new double[]{this.field_70159_w, this.field_70181_x, this.field_70179_y}));
        par1NBTTagCompound.func_74757_a("canBePickedUp", this.canBePickedUp);
        if ((this.throwerName == null || this.throwerName.length() == 0) && this.thrower != null && this.thrower instanceof EntityPlayer) {
            this.throwerName = this.thrower.func_110124_au().toString();
        }
        par1NBTTagCompound.func_74778_a("ownerName", this.throwerName == null ? "" : this.throwerName);
        if (this.getItemDisplay() != null) {
            par1NBTTagCompound.func_74782_a("Item", (NBTBase)this.getItemDisplay().func_77955_b(new NBTTagCompound()));
        }
        par1NBTTagCompound.func_74776_a("damagev2", this.damage);
        par1NBTTagCompound.func_74768_a("punch", this.punch);
        par1NBTTagCompound.func_74768_a("size", this.field_70180_af.func_75679_c(23));
        par1NBTTagCompound.func_74768_a("velocity", this.field_70180_af.func_75679_c(25));
        par1NBTTagCompound.func_74768_a("explosiveRadius", this.explosiveRadius);
        par1NBTTagCompound.func_74768_a("effectDuration", this.duration);
        par1NBTTagCompound.func_74757_a("gravity", this.hasGravity());
        par1NBTTagCompound.func_74757_a("accelerate", this.accelerate);
        par1NBTTagCompound.func_74774_a("glows", this.field_70180_af.func_75683_a(24));
        par1NBTTagCompound.func_74757_a("explosive", this.explosive);
        par1NBTTagCompound.func_74768_a("accuracy", this.accuracy);
        par1NBTTagCompound.func_74768_a("PotionEffect", this.effect.ordinal());
        if (this.effect == EnumPotionType.Manual) {
            par1NBTTagCompound.func_74768_a("PotionManualId", this.manualPotionId);
        }
        par1NBTTagCompound.func_74778_a("trail", this.field_70180_af.func_75681_e(22));
        par1NBTTagCompound.func_74774_a("Render3D", this.field_70180_af.func_75683_a(28));
        par1NBTTagCompound.func_74774_a("Spins", this.field_70180_af.func_75683_a(29));
        par1NBTTagCompound.func_74774_a("Sticks", this.field_70180_af.func_75683_a(30));
        par1NBTTagCompound.func_74774_a("trailCustom", this.field_70180_af.func_75683_a(31));
        par1NBTTagCompound.func_74757_a("IgnoreInvincibility", this.ignoreInvincibility);
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        NBTTagCompound var2;
        ItemStack item;
        this.xTile = par1NBTTagCompound.func_74765_d("xTile");
        this.yTile = par1NBTTagCompound.func_74765_d("yTile");
        this.zTile = par1NBTTagCompound.func_74765_d("zTile");
        this.inTile = Block.func_149729_e((int)(par1NBTTagCompound.func_74771_c("inTile") & 0xFF));
        this.inData = par1NBTTagCompound.func_74771_c("inData") & 0xFF;
        this.field_70191_b = par1NBTTagCompound.func_74771_c("shake") & 0xFF;
        this.field_70193_a = par1NBTTagCompound.func_74771_c("inGround") == 1;
        this.field_70180_af.func_75692_b(27, (Object)par1NBTTagCompound.func_74771_c("isArrow"));
        this.throwerName = par1NBTTagCompound.func_74779_i("ownerName");
        this.canBePickedUp = par1NBTTagCompound.func_74767_n("canBePickedUp");
        this.damage = par1NBTTagCompound.func_74760_g("damagev2");
        this.punch = par1NBTTagCompound.func_74762_e("punch");
        this.explosiveRadius = par1NBTTagCompound.func_74762_e("explosiveRadius");
        this.duration = par1NBTTagCompound.func_74762_e("effectDuration");
        this.accelerate = par1NBTTagCompound.func_74767_n("accelerate");
        this.explosive = par1NBTTagCompound.func_74767_n("explosive");
        this.accuracy = par1NBTTagCompound.func_74762_e("accuracy");
        this.effect = EnumPotionType.fromOrdinal(par1NBTTagCompound.func_74762_e("PotionEffect"));
        if (this.effect == EnumPotionType.Manual) {
            this.manualPotionId = par1NBTTagCompound.func_74762_e("PotionManualId");
        }
        this.field_70180_af.func_75692_b(22, (Object)par1NBTTagCompound.func_74779_i("trail"));
        this.field_70180_af.func_75692_b(23, (Object)par1NBTTagCompound.func_74762_e("size"));
        this.field_70180_af.func_75692_b(24, (Object)((byte)(par1NBTTagCompound.func_74767_n("glows") ? 1 : 0)));
        this.field_70180_af.func_75692_b(25, (Object)par1NBTTagCompound.func_74762_e("velocity"));
        this.field_70180_af.func_75692_b(26, (Object)((byte)(par1NBTTagCompound.func_74767_n("gravity") ? 1 : 0)));
        this.field_70180_af.func_75692_b(28, (Object)((byte)(par1NBTTagCompound.func_74767_n("Render3D") ? 1 : 0)));
        this.field_70180_af.func_75692_b(29, (Object)((byte)(par1NBTTagCompound.func_74767_n("Spins") ? 1 : 0)));
        this.field_70180_af.func_75692_b(30, (Object)((byte)(par1NBTTagCompound.func_74767_n("Sticks") ? 1 : 0)));
        if (par1NBTTagCompound.func_74764_b("trailCustom")) {
            byte trailCustom = par1NBTTagCompound.func_74771_c("trailCustom");
            this.field_70180_af.func_75692_b(31, (Object)trailCustom);
        }
        if (par1NBTTagCompound.func_74764_b("IgnoreInvincibility")) {
            this.ignoreInvincibility = par1NBTTagCompound.func_74767_n("IgnoreInvincibility");
        }
        if (this.throwerName != null && this.throwerName.length() == 0) {
            this.throwerName = null;
        }
        if (par1NBTTagCompound.func_74764_b("direction")) {
            NBTTagList nbttaglist = par1NBTTagCompound.func_150295_c("direction", 6);
            this.field_70159_w = nbttaglist.func_150309_d(0);
            this.field_70181_x = nbttaglist.func_150309_d(1);
            this.field_70179_y = nbttaglist.func_150309_d(2);
        }
        if ((item = ItemStack.func_77949_a((NBTTagCompound)(var2 = par1NBTTagCompound.func_74775_l("Item")))) == null) {
            this.func_70106_y();
        } else {
            this.field_70180_af.func_75692_b(21, (Object)item);
        }
    }

    public EntityLivingBase func_85052_h() {
        if (this.throwerName == null || this.throwerName.isEmpty()) {
            return null;
        }
        try {
            UUID uuid = UUID.fromString(this.throwerName);
            if (this.thrower == null && uuid != null) {
                this.thrower = this.field_70170_p.func_152378_a(uuid);
            }
        }
        catch (IllegalArgumentException illegalArgumentException) {
            // empty catch block
        }
        return this.thrower;
    }

    private int getPotionColor() {
        int potionId = this.effect.getResolvedPotionId(this.manualPotionId);
        if (EnumPotionType.isValidPotionId(potionId)) {
            return Potion.field_76425_a[potionId].func_76401_j();
        }
        return 0;
    }

    public void getStatProperties(DataStats stats) {
        this.damage = stats.pDamage;
        this.punch = stats.pImpact;
        this.accelerate = stats.pXlr8;
        this.explosive = stats.pExplode;
        this.explosiveRadius = stats.pArea;
        this.effect = stats.pEffect;
        this.manualPotionId = stats.pManualId;
        this.burnItem = stats.pBurnItem;
        this.duration = stats.pDur;
        this.amplify = stats.pEffAmp;
        this.setParticleEffect(stats.pTrail);
        this.field_70180_af.func_75692_b(23, (Object)stats.pSize);
        this.field_70180_af.func_75692_b(24, (Object)((byte)(stats.pGlows ? 1 : 0)));
        this.setSpeed(stats.pSpeed);
        this.setHasGravity(stats.pPhysics);
        this.setIs3D(stats.pRender3D);
        this.setRotating(stats.pSpin);
        this.setStickInWall(stats.pStick);
        this.ignoreInvincibility = !stats.projectileInvincibility;
    }

    public void setParticleEffect(EnumParticleType type) {
        this.field_70180_af.func_75692_b(22, (Object)type.particleName);
    }

    public void setCustomParticle(ScriptParticle particle) {
    }

    public void setHasGravity(boolean bo) {
        this.field_70180_af.func_75692_b(26, (Object)((byte)(bo ? 1 : 0)));
    }

    public void setIs3D(boolean bo) {
        this.field_70180_af.func_75692_b(28, (Object)((byte)(bo ? 1 : 0)));
    }

    public void setStickInWall(boolean bo) {
        this.field_70180_af.func_75692_b(30, (Object)((byte)(bo ? 1 : 0)));
    }

    public ItemStack getItemDisplay() {
        return this.field_70180_af.func_82710_f(21);
    }

    public float func_70013_c(float par1) {
        return this.field_70180_af.func_75683_a(24) == 1 ? 1.0f : super.func_70013_c(par1);
    }

    @SideOnly(value=Side.CLIENT)
    public int func_70070_b(float par1) {
        return this.field_70180_af.func_75683_a(24) == 1 ? 0xF000F0 : super.func_70070_b(par1);
    }

    public boolean hasGravity() {
        return this.field_70180_af.func_75683_a(26) == 1;
    }

    public void setSpeed(int speed) {
        this.field_70180_af.func_75692_b(25, (Object)speed);
    }

    public float getSpeed() {
        return (float)this.field_70180_af.func_75679_c(25) / 10.0f;
    }

    public boolean isArrow() {
        return this.field_70180_af.func_75683_a(27) == 1;
    }

    public void setRotating(boolean bo) {
        this.field_70180_af.func_75692_b(29, (Object)((byte)(bo ? 1 : 0)));
    }

    public boolean isRotating() {
        return this.field_70180_af.func_75683_a(29) == 1;
    }

    public boolean glows() {
        return this.field_70180_af.func_75683_a(24) == 1;
    }

    public boolean is3D() {
        return this.field_70180_af.func_75683_a(28) == 1 || this.isBlock();
    }

    public boolean sticksToWalls() {
        return this.is3D() && this.field_70180_af.func_75683_a(30) == 1;
    }

    public void func_70100_b_(EntityPlayer par1EntityPlayer) {
        if (this.field_70170_p.field_72995_K || !this.canBePickedUp || !this.field_70193_a || this.arrowShake > 0) {
            return;
        }
        if (par1EntityPlayer.field_71071_by.func_70441_a(this.getItemDisplay())) {
            this.field_70193_a = false;
            this.func_85030_a("random.pop", 0.2f, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7f + 1.0f) * 2.0f);
            par1EntityPlayer.func_71001_a((Entity)this, 1);
            this.func_70106_y();
        }
    }

    protected boolean func_70041_e_() {
        return false;
    }

    public IChatComponent func_145748_c_() {
        if (this.getItemDisplay() != null) {
            return new ChatComponentTranslation(this.getItemDisplay().func_82833_r(), new Object[0]);
        }
        return super.func_145748_c_();
    }
}

