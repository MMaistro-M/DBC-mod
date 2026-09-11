/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.registry.IEntityAdditionalSpawnData
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.Minecraft
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.DataWatcher
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.IRangedAttackMob
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAttackOnCollide
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAIOpenDoor
 *  net.minecraft.entity.ai.EntityAIRestrictSun
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks
 *  net.minecraft.entity.ai.EntityAITasks$EntityAITaskEntry
 *  net.minecraft.entity.ai.EntityMoveHelper
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.passive.EntityBat
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S0BPacketAnimation
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.util.FakePlayer
 *  net.minecraftforge.event.ServerChatEvent
 */
package noppes.npcs.entity;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kamkeel.npcs.addon.DBCAddon;
import kamkeel.npcs.addon.client.DBCClient;
import kamkeel.npcs.controllers.data.ability.type.AbilityCounter;
import kamkeel.npcs.controllers.data.ability.type.AbilityDefend;
import kamkeel.npcs.controllers.data.ability.type.AbilityDodge;
import kamkeel.npcs.network.PacketHandler;
import kamkeel.npcs.network.enums.EnumSoundOperation;
import kamkeel.npcs.network.packets.data.ChatBubblePacket;
import kamkeel.npcs.network.packets.data.QuestCompletionPacket;
import kamkeel.npcs.network.packets.data.SoundManagementPacket;
import kamkeel.npcs.network.packets.data.npc.UpdateNpcPacket;
import kamkeel.npcs.network.packets.data.npc.WeaponNpcPacket;
import kamkeel.npcs.util.AttributeAttackUtil;
import kamkeel.npcs.util.ByteBufUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.NPCEntityHelper;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.ServerChatEvent;
import noppes.npcs.CustomItems;
import noppes.npcs.CustomNpcs;
import noppes.npcs.DataAI;
import noppes.npcs.DataAbilities;
import noppes.npcs.DataAdvanced;
import noppes.npcs.DataDisplay;
import noppes.npcs.DataInventory;
import noppes.npcs.DataStats;
import noppes.npcs.EventHooks;
import noppes.npcs.IChatMessages;
import noppes.npcs.NBTTags;
import noppes.npcs.NoppesUtilPlayer;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.NpcDamageSource;
import noppes.npcs.VersionCompatibility;
import noppes.npcs.ai.CombatHandler;
import noppes.npcs.ai.EntityAIAmbushTarget;
import noppes.npcs.ai.EntityAIAnimation;
import noppes.npcs.ai.EntityAIAttackTarget;
import noppes.npcs.ai.EntityAIAvoidTarget;
import noppes.npcs.ai.EntityAIBustDoor;
import noppes.npcs.ai.EntityAIDodgeShoot;
import noppes.npcs.ai.EntityAIFindShade;
import noppes.npcs.ai.EntityAIFollow;
import noppes.npcs.ai.EntityAIJob;
import noppes.npcs.ai.EntityAILeapAtTargetNpc;
import noppes.npcs.ai.EntityAILook;
import noppes.npcs.ai.EntityAIMoveIndoors;
import noppes.npcs.ai.EntityAIMovingPath;
import noppes.npcs.ai.EntityAIOrbitTarget;
import noppes.npcs.ai.EntityAIPanic;
import noppes.npcs.ai.EntityAIPounceTarget;
import noppes.npcs.ai.EntityAIRangedAttack;
import noppes.npcs.ai.EntityAIReturn;
import noppes.npcs.ai.EntityAIRole;
import noppes.npcs.ai.EntityAISprintToTarget;
import noppes.npcs.ai.EntityAIStalkTarget;
import noppes.npcs.ai.EntityAITransform;
import noppes.npcs.ai.EntityAIWander;
import noppes.npcs.ai.EntityAIWatchClosest;
import noppes.npcs.ai.EntityAIWaterNav;
import noppes.npcs.ai.EntityAIWorldLines;
import noppes.npcs.ai.EntityAIZigZagTarget;
import noppes.npcs.ai.pathfinder.FlyingMoveHelper;
import noppes.npcs.ai.pathfinder.PathNavigateFlying;
import noppes.npcs.ai.selector.NPCAttackSelector;
import noppes.npcs.ai.target.EntityAIClearTarget;
import noppes.npcs.ai.target.EntityAIClosestTarget;
import noppes.npcs.ai.target.EntityAIOwnerHurtByTarget;
import noppes.npcs.ai.target.EntityAIOwnerHurtTarget;
import noppes.npcs.api.entity.ICustomNpc;
import noppes.npcs.api.handler.data.ILine;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.client.EntityUtil;
import noppes.npcs.config.ConfigMain;
import noppes.npcs.config.ConfigScript;
import noppes.npcs.constants.EnumAnimation;
import noppes.npcs.constants.EnumCombatPolicy;
import noppes.npcs.constants.EnumJobType;
import noppes.npcs.constants.EnumMovingType;
import noppes.npcs.constants.EnumNavType;
import noppes.npcs.constants.EnumPotionType;
import noppes.npcs.constants.EnumRoleType;
import noppes.npcs.constants.EnumStandingType;
import noppes.npcs.controllers.FactionController;
import noppes.npcs.controllers.LinkedNpcController;
import noppes.npcs.controllers.data.DataScript;
import noppes.npcs.controllers.data.DataTransform;
import noppes.npcs.controllers.data.Dialog;
import noppes.npcs.controllers.data.DialogOption;
import noppes.npcs.controllers.data.Faction;
import noppes.npcs.controllers.data.Line;
import noppes.npcs.controllers.data.Party;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerQuestData;
import noppes.npcs.controllers.data.QuestData;
import noppes.npcs.controllers.data.action.ActionManager;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.EntityProjectile;
import noppes.npcs.entity.data.DataTimers;
import noppes.npcs.roles.JobBard;
import noppes.npcs.roles.JobFollower;
import noppes.npcs.roles.JobInterface;
import noppes.npcs.roles.RoleCompanion;
import noppes.npcs.roles.RoleFollower;
import noppes.npcs.roles.RoleInterface;
import noppes.npcs.roles.RoleMount;
import noppes.npcs.scripted.NpcAPI;
import noppes.npcs.scripted.entity.ScriptNpc;
import noppes.npcs.scripted.event.NpcEvent;
import noppes.npcs.scripted.item.ScriptCustomizableItem;
import noppes.npcs.util.GameProfileAlt;
import noppes.npcs.util.NPCMountUtil;

public abstract class EntityNPCInterface
extends EntityCreature
implements IEntityAdditionalSpawnData,
ICommandSender,
IRangedAttackMob,
IBossDisplayData {
    public ICustomNpc wrappedNPC;
    public static final GameProfileAlt chateventProfile = new GameProfileAlt();
    public static FakePlayer chateventPlayer;
    public DataDisplay display;
    public DataStats stats;
    public DataAI ais;
    public DataAdvanced advanced;
    public DataInventory inventory;
    public DataScript script;
    public DataTransform transform;
    public DataTimers timers;
    public DataAbilities abilities;
    public CombatHandler combatHandler = new CombatHandler(this);
    public ActionManager actionManager = new ActionManager();
    public String linkedName = "";
    public long linkedLast = 0L;
    public LinkedNpcController.LinkedData linkedData;
    public float baseHeight = 1.8f;
    public float scaleX;
    public float scaleY;
    public float scaleZ;
    private boolean wasKilled = false;
    public RoleInterface roleInterface;
    public JobInterface jobInterface;
    public HashMap<Integer, DialogOption> dialogs;
    public boolean hasDied = false;
    public long killedtime = 0L;
    public long totalTicksAlive = 0L;
    private int taskCount = 1;
    public int lastInteract = 0;
    public boolean isDrawn = false;
    public Faction faction;
    private EntityAIRangedAttack aiRange;
    private EntityAIBase aiResponse;
    private EntityAIBase aiLeap;
    private EntityAIBase aiSprint;
    private EntityAIBase aiAttackTarget;
    public List<EntityLivingBase> interactingEntities = new ArrayList<EntityLivingBase>();
    public ResourceLocation textureLocation = null;
    private final NPCMountUtil.MountState mountState = new NPCMountUtil.MountState();
    private static final int NPC_STATE_FLAG_FLYING = 1;
    private static final int NPC_STATE_FLAG_JUMPING = 2;
    public EnumAnimation currentAnimation = EnumAnimation.NONE;
    public int npcVersion = VersionCompatibility.ModRev;
    public IChatMessages messages;
    public boolean updateClient = false;
    public boolean updateAI = false;
    public FlyingMoveHelper flyMoveHelper = new FlyingMoveHelper(this);
    public PathNavigate flyNavigator = new PathNavigateFlying((EntityLiving)this, this.field_70170_p);
    public double field_20066_r;
    public double field_20065_s;
    public double field_20064_t;
    public double field_20063_u;
    public double field_20062_v;
    public double field_20061_w;
    private static final ItemStack[] lastActive;

    public EntityNPCInterface(World world) {
        super(world);
        try {
            if (this.canFly()) {
                this.func_70661_as().func_75495_e(true);
                this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
            } else {
                this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAIWaterNav(this));
            }
            this.dialogs = new HashMap();
            if (!ConfigMain.DefaultInteractLine.isEmpty()) {
                this.advanced.interactLines.lines.put(0, new Line(ConfigMain.DefaultInteractLine));
            }
            this.field_70728_aV = 0;
            this.scaleZ = 0.9375f;
            this.scaleY = 0.9375f;
            this.scaleX = 0.9375f;
            this.faction = this.getFaction();
            this.setFaction(this.faction.id);
            this.func_70105_a(1.0f, 1.0f);
            this.updateTasks();
            this.syncDespawnPersistence();
            if (!this.isRemote() && this.wrappedNPC == null) {
                this.wrappedNPC = new ScriptNpc<EntityNPCInterface>(this);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.display = new DataDisplay(this);
        this.stats = new DataStats(this);
        this.ais = new DataAI(this);
        this.advanced = new DataAdvanced(this);
        this.inventory = new DataInventory(this);
        this.transform = new DataTransform(this);
        this.script = new DataScript(this);
        this.timers = new DataTimers((Object)this);
        this.abilities = new DataAbilities(this);
        this.func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(this.stats.maxHealth);
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a((double)ConfigMain.NpcNavRange);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a((double)this.getSpeed());
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a((double)this.stats.getAttackStrength());
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(13, (Object)"");
        this.field_70180_af.func_75682_a(14, (Object)0);
        this.field_70180_af.func_75682_a(15, (Object)0);
        this.field_70180_af.func_75682_a(16, (Object)"");
        this.field_70180_af.func_75682_a(17, (Object)0);
    }

    protected boolean func_70650_aV() {
        return true;
    }

    public boolean func_110167_bD() {
        return false;
    }

    public boolean func_70089_S() {
        return super.func_70089_S() && !this.isKilled();
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        this.abilities.applyRotationControl();
        this.abilities.applyLockedPosition();
        if (!this.isRemote()) {
            if (this.field_70173_aa % 10 == 0) {
                EventHooks.onNPCUpdate(this);
            }
            for (int i = 1; i < 3; ++i) {
                ItemStack itemstack = this.inventory.prevWeapons.get(i);
                ItemStack itemstack1 = this.inventory.weapons.get(i);
                if (ItemStack.func_77989_b((ItemStack)itemstack1, (ItemStack)itemstack)) continue;
                NBTTagCompound itemNBT = new NBTTagCompound();
                if (itemstack1 != null) {
                    itemstack1.func_77955_b(itemNBT);
                }
                PacketHandler.Instance.sendTracking(new WeaponNpcPacket(this.func_145782_y(), i, itemNBT), (Entity)this);
                this.inventory.prevWeapons.put(i, itemstack1 == null ? null : itemstack1.func_77946_l());
            }
            this.timers.update();
        }
    }

    public void func_70029_a(World world) {
        super.func_70029_a(world);
        this.script.setWorld(world);
    }

    public int func_82143_as() {
        return 3;
    }

    public boolean func_70652_k(Entity receiver) {
        int potionId;
        float f = this.stats.getAttackStrength();
        if (receiver instanceof EntityPlayer && !this.isRemote()) {
            f = AttributeAttackUtil.calculateDamageNPCtoPlayer(this, (EntityPlayer)receiver, f);
        }
        if (this.stats.attackSpeed < 10) {
            receiver.field_70172_ad = 0;
        }
        if (receiver instanceof EntityLivingBase && !this.isRemote()) {
            NpcEvent.MeleeAttackEvent event = new NpcEvent.MeleeAttackEvent(this.wrappedNPC, f, (EntityLivingBase)receiver);
            if (EventHooks.onNPCMeleeAttack(this, event)) {
                return false;
            }
            f = event.getDamage();
        }
        boolean didAttack = false;
        if (DBCAddon.instance.canDBCAttack(this, f, receiver)) {
            didAttack = receiver.func_70097_a((DamageSource)new NpcDamageSource("mob", (Entity)this), 0.001f);
            if (didAttack) {
                DBCAddon.instance.doDBCDamage(this, f, receiver);
            }
        } else {
            didAttack = receiver.func_70097_a((DamageSource)new NpcDamageSource("mob", (Entity)this), f);
        }
        if (didAttack) {
            if (this.getOwner() instanceof EntityPlayer) {
                NPCEntityHelper.setRecentlyHit((EntityLivingBase)receiver);
            }
            if (this.stats.knockback > 0) {
                receiver.func_70024_g((double)(-MathHelper.func_76126_a((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * (float)this.stats.knockback * 0.5f), 0.1, (double)(MathHelper.func_76134_b((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * (float)this.stats.knockback * 0.5f));
                this.field_70159_w *= 0.6;
                this.field_70179_y *= 0.6;
            }
            if (this.advanced.role == EnumRoleType.Companion) {
                ((RoleCompanion)this.roleInterface).attackedEntity(receiver);
            }
        }
        if (this.stats.potionType == EnumPotionType.Fire) {
            receiver.func_70015_d(this.stats.potionDuration);
        } else if (this.stats.potionType != EnumPotionType.None && EnumPotionType.isValidPotionId(potionId = this.stats.potionType.getResolvedPotionId(this.stats.potionManualId))) {
            ((EntityLivingBase)receiver).func_70690_d(new PotionEffect(potionId, this.stats.potionDuration * 20, this.stats.potionAmp));
        }
        return didAttack;
    }

    public void func_71038_i() {
        Item item;
        ItemStack stack = this.func_70694_bm();
        if (stack != null && stack.func_77973_b() != null && (item = stack.func_77973_b()).onEntitySwing((EntityLivingBase)this, stack)) {
            return;
        }
        if (!this.field_82175_bq || this.field_110158_av >= this.func_82166_i() / 2 || this.field_110158_av < 0) {
            this.field_110158_av = -1;
            this.field_82175_bq = true;
            if (this.field_70170_p instanceof WorldServer) {
                NpcEvent.SwingEvent event;
                if (!this.isRemote() && EventHooks.onNPCMeleeSwing(this, event = new NpcEvent.SwingEvent(this.wrappedNPC, stack))) {
                    return;
                }
                ((WorldServer)this.field_70170_p).func_73039_n().func_151247_a((Entity)this, (Packet)new S0BPacketAnimation((Entity)this, 0));
            }
        }
    }

    public void func_70636_d() {
        float f;
        if (CustomNpcs.FreezeNPCs) {
            return;
        }
        ++this.totalTicksAlive;
        this.func_82168_bl();
        if (this.field_70173_aa % 20 == 0) {
            this.faction = this.getFaction();
        }
        if (!this.field_70170_p.field_72995_K) {
            if (!this.isKilled() && this.field_70173_aa % 20 == 0) {
                if (this.func_110143_aJ() < this.func_110138_aP()) {
                    if (this.stats.healthRegen > 0.0f && !this.isAttacking()) {
                        this.func_70691_i(this.stats.healthRegen);
                    }
                    if (this.stats.combatRegen > 0.0f && this.isAttacking()) {
                        this.func_70691_i(this.stats.combatRegen);
                    }
                }
                if (this.faction.getsAttacked && !this.isAttacking()) {
                    List list = this.field_70170_p.func_72872_a(EntityMob.class, this.field_70121_D.func_72314_b(16.0, 16.0, 16.0));
                    for (EntityMob mob : list) {
                        if (mob.func_70638_az() != null || !this.canSee((Entity)mob)) continue;
                        if (mob instanceof EntityZombie && !mob.getEntityData().func_74764_b("AttackNpcs")) {
                            mob.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)mob, EntityLivingBase.class, 1.0, false));
                            mob.getEntityData().func_74757_a("AttackNpcs", true);
                        }
                        mob.func_70624_b((EntityLivingBase)this);
                    }
                }
                if (this.linkedData != null && this.linkedData.time > this.linkedLast) {
                    LinkedNpcController.Instance.loadNpcData(this);
                }
                if (this.updateClient) {
                    this.updateClient();
                }
                if (this.updateAI) {
                    this.updateTasks();
                    this.updateAI = false;
                }
            }
            if (this.func_110143_aJ() <= 0.0f) {
                this.func_70674_bp();
                this.setBoolFlag(true, 8);
                this.updateTasks();
                this.updateHitbox();
            }
            this.setBoolFlag(!this.func_70661_as().func_75500_f(), 1);
            this.setBoolFlag(this.isInteracting(), 2);
            this.combatHandler.update();
            this.onCollide();
            this.actionManager.tick();
            this.abilities.tick();
        }
        if (this.wasKilled != this.isKilled() && this.wasKilled) {
            if (!this.field_70170_p.field_72995_K) {
                this.reset();
            } else {
                this.field_70725_aQ = 0;
                this.currentAnimation = EnumAnimation.NONE;
                this.updateHitbox();
            }
        }
        this.wasKilled = this.isKilled();
        if (this.field_70170_p.func_72935_r() && !this.field_70170_p.field_72995_K && this.stats.burnInSun && (f = this.func_70013_c(1.0f)) > 0.5f && this.field_70146_Z.nextFloat() * 30.0f < (f - 0.4f) * 2.0f && this.field_70170_p.func_72937_j(MathHelper.func_76128_c((double)this.field_70165_t), MathHelper.func_76128_c((double)this.field_70163_u), MathHelper.func_76128_c((double)this.field_70161_v))) {
            this.func_70015_d(8);
        }
        super.func_70636_d();
        this.abilities.applyRotationControl();
        this.abilities.applyLockedPosition();
        this.handleMountRiderState();
        if (this.field_70170_p.field_72995_K) {
            if (this.roleInterface != null) {
                this.roleInterface.clientUpdate();
            }
            if (!this.display.cloakTexture.isEmpty()) {
                this.cloakUpdate();
            }
            if (this.currentAnimation.ordinal() != this.field_70180_af.func_75679_c(14)) {
                this.currentAnimation = EnumAnimation.values()[this.field_70180_af.func_75679_c(14)];
                this.updateHitbox();
            }
            if (this.advanced.job == EnumJobType.Bard) {
                ((JobBard)this.jobInterface).onLivingUpdate();
            }
            DBCClient.Instance.renderDBCAuras(this);
        }
    }

    protected void func_70665_d(DamageSource damageSrc, float damageAmount) {
        super.func_70665_d(damageSrc, damageAmount);
        this.combatHandler.damage(damageSrc, damageAmount);
    }

    public void updateClient() {
        NBTTagCompound compound = this.writeSpawnData();
        compound.func_74768_a("EntityId", this.func_145782_y());
        PacketHandler.Instance.sendTracking(new UpdateNpcPacket(compound), (Entity)this);
        this.updateClient = false;
    }

    public boolean func_70085_c(EntityPlayer player) {
        if (this.field_70170_p.field_72995_K) {
            return false;
        }
        ItemStack currentItem = player.field_71071_by.func_70448_g();
        if (currentItem != null) {
            Item item = currentItem.func_77973_b();
            if (item == CustomItems.cloner || item == CustomItems.wand || item == CustomItems.mount || item == CustomItems.scripter || item == CustomItems.soulstoneEmpty) {
                this.func_70624_b(null);
                this.func_70604_c(null);
                return true;
            }
            if (item == CustomItems.moving) {
                this.func_70624_b(null);
                if (currentItem.field_77990_d == null) {
                    currentItem.field_77990_d = new NBTTagCompound();
                }
                currentItem.field_77990_d.func_74768_a("NPCID", this.func_145782_y());
                player.func_145747_a((IChatComponent)new ChatComponentTranslation("Registered " + this.func_70005_c_() + " to your NPC Pather", new Object[0]));
                return true;
            }
        }
        if (EventHooks.onNPCInteract(this, player) || this.isAttacking() || this.isKilled() || this.faction.isAggressiveToPlayer(player)) {
            return false;
        }
        this.addInteract((EntityLivingBase)player);
        Dialog dialog = this.getDialog(player);
        PlayerQuestData playerdata = PlayerData.get((EntityPlayer)player).questData;
        QuestData data = playerdata.getQuestCompletion(player, this);
        Party partyCompleted = playerdata.getPartyQuestCompletion(player, this);
        if (partyCompleted != null) {
            NoppesUtilPlayer.questPartyCompletion(partyCompleted);
        } else if (data != null) {
            NoppesUtilPlayer.questCompletion((EntityPlayerMP)player, data.quest.id);
            QuestCompletionPacket.sendQuestComplete((EntityPlayerMP)player, data.quest.writeToNBT(new NBTTagCompound()));
        } else if (dialog != null) {
            NoppesUtilServer.openDialog(player, this, dialog, 0);
        } else if (this.roleInterface != null) {
            this.roleInterface.interact(player);
        } else {
            this.say(player, this.advanced.getInteractLine());
        }
        return true;
    }

    public PathNavigate func_70661_as() {
        if (this.canFly()) {
            return this.flyNavigator;
        }
        return super.func_70661_as();
    }

    public EntityMoveHelper func_70605_aq() {
        if (this.canFly()) {
            return this.flyMoveHelper;
        }
        return super.func_70605_aq();
    }

    public boolean canBreathe() {
        return this.func_70090_H() && this.stats.drowningType == 2 || !this.func_70090_H() && this.stats.drowningType == 1 || this.stats.drowningType == 0;
    }

    protected void func_70619_bc() {
        this.func_70661_as().func_75501_e();
        this.func_70605_aq().func_75641_c();
        try {
            super.func_70619_bc();
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        if (!this.canBreathe()) {
            this.func_70050_g(this.func_70682_h(this.func_70086_ai()));
            if (this.func_70086_ai() <= -20) {
                this.func_70050_g(0);
                this.func_70097_a(DamageSource.field_76369_e, 2.0f);
            }
        }
    }

    public void addInteract(EntityLivingBase entity) {
        if (!this.ais.stopAndInteract || this.isAttacking() || !entity.func_70089_S()) {
            return;
        }
        if (this.field_70173_aa - this.lastInteract < 180) {
            this.interactingEntities.clear();
        }
        this.func_70661_as().func_75499_g();
        this.lastInteract = this.field_70173_aa;
        if (!this.interactingEntities.contains(entity)) {
            this.interactingEntities.add(entity);
        }
    }

    public boolean isInteracting() {
        if (this.field_70173_aa - this.lastInteract < 40 || this.isRemote() && this.getBoolFlag(2)) {
            return true;
        }
        return this.ais.stopAndInteract && !this.interactingEntities.isEmpty() && this.field_70173_aa - this.lastInteract < 180;
    }

    private Dialog getDialog(EntityPlayer player) {
        for (DialogOption option : this.dialogs.values()) {
            if (option == null || !option.hasDialog()) continue;
            Dialog dialog = option.getDialog();
            if (!dialog.availability.isAvailable(player)) continue;
            return dialog;
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean func_70097_a(DamageSource damagesource, float i) {
        NpcEvent.DamagedEvent event;
        AbilityDefend defend;
        int attackSpeed;
        IItemStack itemStack;
        EntityPlayer player;
        ItemStack heldItem;
        if (this.field_70170_p.field_72995_K || CustomNpcs.FreezeNPCs || damagesource.field_76373_n != null && damagesource.field_76373_n.equals("inWall")) {
            return false;
        }
        if (damagesource.field_76373_n != null && damagesource.field_76373_n.equals("outOfWorld") && this.isKilled()) {
            this.reset();
        }
        if (this.abilities != null && this.abilities.isCurrentAbilityInvulnerable()) {
            return false;
        }
        Entity sourceEntity = damagesource.func_76346_g();
        if (sourceEntity instanceof EntityPlayer && (heldItem = (player = (EntityPlayer)sourceEntity).func_70694_bm()) != null && (itemStack = NpcAPI.Instance().getIItemStack(heldItem)) instanceof ScriptCustomizableItem && this.field_70172_ad <= this.field_70771_an - (attackSpeed = ((ScriptCustomizableItem)itemStack).getAttackSpeed())) {
            this.field_70172_ad = 0;
        }
        if ((float)this.field_70172_ad > (float)this.field_70771_an / 2.0f && i <= this.field_110153_bc) {
            return false;
        }
        Entity entity = NoppesUtilServer.GetDamageSource(damagesource);
        EntityLivingBase attackingEntity = null;
        if (entity instanceof EntityLivingBase) {
            attackingEntity = (EntityLivingBase)entity;
        }
        if (this.faction.isPassive) {
            return false;
        }
        if (attackingEntity != null && attackingEntity == this.getOwner()) {
            return false;
        }
        if (attackingEntity instanceof EntityNPCInterface) {
            EntityNPCInterface npc = (EntityNPCInterface)attackingEntity;
            if (npc.faction.id == this.faction.id) {
                return false;
            }
            if (npc.getOwner() instanceof EntityPlayer) {
                this.field_70718_bc = 100;
            }
        } else if (attackingEntity instanceof EntityPlayer && this.faction.isFriendlyToPlayer((EntityPlayer)attackingEntity)) {
            return false;
        }
        if (!DBCAddon.IsAvailable() && attackingEntity instanceof EntityPlayer) {
            i = AttributeAttackUtil.calculateDamagePlayerToNPC((EntityPlayer)attackingEntity, this, i);
        }
        i = this.stats.resistances.applyResistance(damagesource, i);
        AbilityDefend abilityDefend = defend = this.abilities != null ? this.abilities.getActiveDefend() : null;
        if (defend != null) {
            if (defend instanceof AbilityDodge || defend instanceof AbilityCounter) {
                float result = defend.onDefend(attackingEntity, damagesource, i);
                if (result != i) {
                    return false;
                }
            } else {
                i = defend.onDefend(attackingEntity, damagesource, i);
            }
        }
        if (EventHooks.onNPCDamaged(this, event = new NpcEvent.DamagedEvent(this.wrappedNPC, (Entity)attackingEntity, i, damagesource)) || this.isKilled()) {
            return false;
        }
        i = event.getDamage();
        if (this.isKilled()) {
            return false;
        }
        if (attackingEntity == null) {
            return super.func_70097_a(damagesource, i);
        }
        try {
            if (this.isAttacking()) {
                boolean closerTargetFound;
                if (this.func_70638_az() != null && this.ais.combatPolicy != EnumCombatPolicy.Brute) {
                    closerTargetFound = this.func_70068_e((Entity)this.func_70638_az()) > this.func_70068_e((Entity)attackingEntity);
                    switch (this.ais.combatPolicy) {
                        case Flip: {
                            if (!closerTargetFound) break;
                            this.func_70624_b(attackingEntity);
                            break;
                        }
                        case Stubborn: {
                            if (!closerTargetFound || !this.combatHandler.shouldChangeTarget(this.ais.tacticalChance)) break;
                            this.func_70624_b(attackingEntity);
                            break;
                        }
                        case Tactical: {
                            if (attackingEntity == this.func_70638_az() || !this.combatHandler.shouldSwitchTactically(this.func_70638_az(), attackingEntity, this.ais.tacticalChance > 50)) break;
                            this.func_70624_b(attackingEntity);
                            break;
                        }
                    }
                }
                closerTargetFound = super.func_70097_a(damagesource, i);
                return closerTargetFound;
            }
            if (i > 0.0f) {
                List inRange = this.field_70170_p.func_72872_a(EntityNPCInterface.class, this.field_70121_D.func_72314_b(32.0, 16.0, 32.0));
                for (EntityNPCInterface npc : inRange) {
                    if (npc.isKilled() || !npc.advanced.defendFaction || npc.faction.id != this.faction.id || !npc.canSee((Entity)this) && !npc.ais.directLOS && !npc.canSee((Entity)attackingEntity)) continue;
                    npc.onAttack(attackingEntity);
                }
                this.func_70624_b(attackingEntity);
            }
            boolean bl = super.func_70097_a(damagesource, i);
            return bl;
        }
        finally {
            if (event.getClearTarget()) {
                this.func_70624_b(null);
                this.func_70604_c(null);
            }
        }
    }

    public void onAttack(EntityLivingBase entity) {
        if (entity == null || entity == this || this.isAttacking() || this.ais.onAttack == 3 || entity == this.getOwner()) {
            return;
        }
        super.func_70624_b(entity);
    }

    public void func_70624_b(EntityLivingBase entity) {
        if (entity instanceof EntityPlayer && ((EntityPlayer)entity).field_71075_bZ.field_75102_a || entity != null && entity == this.getOwner()) {
            return;
        }
        if (entity instanceof EntityPlayer && DBCAddon.instance.isKO(this, (EntityPlayer)entity)) {
            return;
        }
        if (!this.isRemote()) {
            Line line;
            if (this.func_70638_az() != entity) {
                if (entity != null) {
                    NpcEvent.TargetEvent event = new NpcEvent.TargetEvent(this.wrappedNPC, (EntityLivingBase)entity);
                    if (EventHooks.onNPCTarget(this, event)) {
                        return;
                    }
                    entity = event.getTarget() == null ? null : event.getTarget().getMCEntity();
                }
                if (this.func_70638_az() != null && EventHooks.onNPCTargetLost(this, this.func_70638_az(), entity)) {
                    return;
                }
            }
            if (entity != null && entity != this && this.ais.onAttack != 3 && !this.isAttacking() && (line = this.advanced.getAttackLine()) != null) {
                this.saySurrounding(line.formatTarget((EntityLivingBase)entity));
            }
        }
        super.func_70624_b(entity);
    }

    public void func_82196_d(EntityLivingBase entity, float f) {
        ItemStack proj = this.inventory.getProjectile();
        if (proj == null) {
            this.updateTasks();
            return;
        }
        if (!this.isRemote()) {
            NpcEvent.RangedLaunchedEvent event = new NpcEvent.RangedLaunchedEvent(this.wrappedNPC, this.stats.pDamage, entity);
            if (EventHooks.onNPCRangedAttack(this, event)) {
                return;
            }
            for (int i = 0; i < this.stats.shotCount; ++i) {
                EntityProjectile projectile = this.shoot(entity, this.stats.accuracy, proj, f == 1.0f);
                projectile.damage = event.getDamage();
            }
            if (this.stats.playBurstSound || !this.stats.onSoundBegin) {
                this.func_85030_a(this.stats.fireSound, 1.5f, 1.0f);
                this.stats.playBurstSound = false;
            }
        }
    }

    public EntityProjectile shoot(EntityLivingBase entity, int accuracy, ItemStack proj, boolean indirect) {
        return this.shoot(entity.field_70165_t, entity.field_70121_D.field_72338_b + (double)(entity.field_70131_O / 2.0f), entity.field_70161_v, accuracy, proj, indirect);
    }

    public EntityProjectile shoot(double x, double y, double z, int accuracy, ItemStack proj, boolean indirect) {
        EntityProjectile projectile = new EntityProjectile(this.field_70170_p, (EntityLivingBase)this, proj.func_77946_l(), true);
        double throwerHeight = this.func_70047_e();
        double varX = x - this.field_70165_t;
        double varY = y - (this.field_70163_u + throwerHeight);
        double varZ = z - this.field_70161_v;
        float varF = projectile.hasGravity() ? MathHelper.func_76133_a((double)(varX * varX + varZ * varZ)) : 0.0f;
        float angle = projectile.getAngleForXYZ(varX, varY, varZ, varF, indirect);
        float acc = 20.0f - (float)MathHelper.func_76141_d((float)((float)accuracy / 5.0f));
        projectile.func_70186_c(varX, varY, varZ, angle, acc);
        this.field_70170_p.func_72838_d((Entity)projectile);
        return projectile;
    }

    private void clearTasks(EntityAITasks tasks) {
        Iterator iterator = tasks.field_75782_a.iterator();
        ArrayList list = new ArrayList(tasks.field_75782_a);
        for (EntityAITasks.EntityAITaskEntry entityaitaskentry : list) {
            try {
                tasks.func_85156_a(entityaitaskentry.field_75733_a);
            }
            catch (Throwable throwable) {}
        }
        tasks.field_75782_a = new ArrayList();
    }

    public void updateTasks() {
        if (this.field_70170_p == null || this.field_70170_p.field_72995_K) {
            return;
        }
        this.clearTasks(this.field_70714_bg);
        this.clearTasks(this.field_70715_bh);
        if (this.isKilled()) {
            return;
        }
        if (!this.faction.isPassive()) {
            NPCAttackSelector attackEntitySelector = new NPCAttackSelector(this);
            this.field_70715_bh.func_75776_a(0, (EntityAIBase)new EntityAIClearTarget(this));
            this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
            this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAIClosestTarget(this, EntityLivingBase.class, 4, this.ais.directLOS, false, attackEntitySelector));
            this.field_70715_bh.func_75776_a(3, (EntityAIBase)new EntityAIOwnerHurtByTarget(this));
            this.field_70715_bh.func_75776_a(4, (EntityAIBase)new EntityAIOwnerHurtTarget(this));
        }
        if (this.canFly()) {
            this.func_70661_as().func_75495_e(true);
        } else {
            this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAIWaterNav(this));
        }
        this.taskCount = 1;
        this.doorInteractType();
        this.seekShelter();
        this.setResponse();
        this.setMoveType();
        this.addRegularEntries();
    }

    private void removeTask(EntityAIBase task) {
        if (task != null) {
            this.field_70714_bg.func_85156_a(task);
        }
    }

    public void setResponse() {
        this.removeTask(this.aiLeap);
        this.removeTask(this.aiResponse);
        this.removeTask(this.aiSprint);
        this.removeTask(this.aiAttackTarget);
        this.removeTask(this.aiRange);
        this.aiRange = null;
        this.aiSprint = null;
        this.aiResponse = null;
        this.aiAttackTarget = null;
        this.aiLeap = null;
        if (this.ais.canSprint) {
            this.field_70714_bg.func_75776_a(this.taskCount++, (EntityAIBase)new EntityAISprintToTarget(this));
        }
        if (this.ais.onAttack == 1) {
            this.aiResponse = new EntityAIPanic(this, 1.2f);
            this.field_70714_bg.func_75776_a(this.taskCount++, this.aiResponse);
        } else if (this.ais.onAttack == 2) {
            this.aiResponse = new EntityAIAvoidTarget(this);
            this.field_70714_bg.func_75776_a(this.taskCount++, this.aiResponse);
        } else if (this.ais.onAttack == 0 && !this.faction.isPassive()) {
            this.setLeapTask();
            if (this.inventory.getProjectile() == null || this.ais.useRangeMelee == 2) {
                switch (this.ais.tacticalVariant) {
                    case Dodge: {
                        this.aiResponse = new EntityAIZigZagTarget(this, 1.2, this.ais.tacticalRadius);
                        this.field_70714_bg.func_75776_a(this.taskCount++, this.aiResponse);
                        break;
                    }
                    case Surround: {
                        this.aiResponse = new EntityAIOrbitTarget(this, 1.2, this.ais.tacticalRadius, true);
                        this.field_70714_bg.func_75776_a(this.taskCount++, this.aiResponse);
                        break;
                    }
                    case HitNRun: {
                        this.aiResponse = new EntityAIAvoidTarget(this);
                        this.field_70714_bg.func_75776_a(this.taskCount++, this.aiResponse);
                        break;
                    }
                    case Ambush: {
                        this.aiResponse = new EntityAIAmbushTarget(this, 1.2, this.ais.tacticalRadius, false);
                        this.field_70714_bg.func_75776_a(this.taskCount++, this.aiResponse);
                        break;
                    }
                    case Stalk: {
                        this.aiResponse = new EntityAIStalkTarget(this, this.ais.tacticalRadius);
                        this.field_70714_bg.func_75776_a(this.taskCount++, this.aiResponse);
                        break;
                    }
                }
            } else {
                switch (this.ais.tacticalVariant) {
                    case Dodge: {
                        this.aiResponse = new EntityAIDodgeShoot(this);
                        this.field_70714_bg.func_75776_a(this.taskCount++, this.aiResponse);
                        break;
                    }
                    case Surround: {
                        this.aiResponse = new EntityAIOrbitTarget(this, 1.2, this.stats.rangedRange, false);
                        this.field_70714_bg.func_75776_a(this.taskCount++, this.aiResponse);
                        break;
                    }
                    case HitNRun: {
                        this.aiResponse = new EntityAIAvoidTarget(this);
                        this.field_70714_bg.func_75776_a(this.taskCount++, this.aiResponse);
                        break;
                    }
                    case Ambush: {
                        this.aiResponse = new EntityAIAmbushTarget(this, 1.2, this.ais.tacticalRadius, false);
                        this.field_70714_bg.func_75776_a(this.taskCount++, this.aiResponse);
                        break;
                    }
                    case Stalk: {
                        this.aiResponse = new EntityAIStalkTarget(this, this.ais.tacticalRadius);
                        this.field_70714_bg.func_75776_a(this.taskCount++, this.aiResponse);
                        break;
                    }
                }
            }
            this.aiAttackTarget = new EntityAIAttackTarget(this);
            this.field_70714_bg.func_75776_a(this.taskCount, this.aiAttackTarget);
            ((EntityAIAttackTarget)this.aiAttackTarget).navOverride(this.ais.tacticalVariant == EnumNavType.None);
            if (this.inventory.getProjectile() != null) {
                this.aiRange = new EntityAIRangedAttack(this);
                this.field_70714_bg.func_75776_a(this.taskCount++, (EntityAIBase)this.aiRange);
                this.aiRange.navOverride(this.ais.tacticalVariant == EnumNavType.None);
            }
        } else if (this.ais.onAttack == 3) {
            // empty if block
        }
    }

    public void setMoveType() {
        if (this.ais.movingType == EnumMovingType.Wandering) {
            this.field_70714_bg.func_75776_a(this.taskCount++, (EntityAIBase)new EntityAIWander(this));
        }
        if (this.ais.movingType == EnumMovingType.MovingPath) {
            this.field_70714_bg.func_75776_a(this.taskCount++, (EntityAIBase)new EntityAIMovingPath(this));
        }
    }

    public void doorInteractType() {
        if (this.canFly()) {
            return;
        }
        Object aiDoor = null;
        if (this.ais.doorInteract == 1) {
            aiDoor = new EntityAIOpenDoor((EntityLiving)this, true);
            this.field_70714_bg.func_75776_a(this.taskCount++, (EntityAIBase)aiDoor);
        } else if (this.ais.doorInteract == 0) {
            aiDoor = new EntityAIBustDoor((EntityLiving)this);
            this.field_70714_bg.func_75776_a(this.taskCount++, (EntityAIBase)aiDoor);
        }
        this.func_70661_as().func_75498_b(aiDoor != null);
    }

    public void seekShelter() {
        if (this.ais.findShelter == 0) {
            this.field_70714_bg.func_75776_a(this.taskCount++, (EntityAIBase)new EntityAIMoveIndoors(this));
        } else if (this.ais.findShelter == 1) {
            if (!this.canFly()) {
                this.field_70714_bg.func_75776_a(this.taskCount++, (EntityAIBase)new EntityAIRestrictSun((EntityCreature)this));
            }
            this.field_70714_bg.func_75776_a(this.taskCount++, (EntityAIBase)new EntityAIFindShade(this));
        }
    }

    public void setLeapTask() {
        if (this.ais.leapType == 1) {
            this.aiLeap = new EntityAILeapAtTargetNpc(this, 0.4f);
            this.field_70714_bg.func_75776_a(this.taskCount++, this.aiLeap);
        }
        if (this.ais.leapType == 2) {
            this.aiLeap = new EntityAIPounceTarget(this);
            this.field_70714_bg.func_75776_a(this.taskCount++, this.aiLeap);
        }
    }

    public void addRegularEntries() {
        this.field_70714_bg.func_75776_a(this.taskCount++, (EntityAIBase)new EntityAIFollow(this));
        this.field_70714_bg.func_75776_a(this.taskCount++, (EntityAIBase)new EntityAIReturn(this));
        if (this.ais.standingType != EnumStandingType.NoRotation && this.ais.standingType != EnumStandingType.HeadRotation) {
            this.field_70714_bg.func_75776_a(this.taskCount++, (EntityAIBase)new EntityAIWatchClosest(this, EntityLivingBase.class, 5.0f));
        }
        this.field_70714_bg.func_75776_a(this.taskCount++, (EntityAIBase)new EntityAILook(this));
        this.field_70714_bg.func_75776_a(this.taskCount++, (EntityAIBase)new EntityAIWorldLines(this));
        this.field_70714_bg.func_75776_a(this.taskCount++, (EntityAIBase)new EntityAIJob(this));
        this.field_70714_bg.func_75776_a(this.taskCount++, (EntityAIBase)new EntityAIRole(this));
        this.field_70714_bg.func_75776_a(this.taskCount++, (EntityAIBase)new EntityAIAnimation(this));
        if (this.transform.isValid()) {
            this.field_70714_bg.func_75776_a(this.taskCount++, (EntityAIBase)new EntityAITransform(this));
        }
    }

    public float getSpeed() {
        return (float)this.ais.getWalkingSpeed() / 20.0f;
    }

    public float func_70783_a(int par1, int par2, int par3) {
        float weight = this.field_70170_p.func_72801_o(par1, par2, par3) - 0.5f;
        Block block = this.field_70170_p.func_147439_a(par1, par2, par3);
        if (block.func_149662_c()) {
            weight += 10.0f;
        }
        return weight;
    }

    public void func_70050_g(int air) {
        if (this.func_70090_H() || air < this.func_70086_ai() || this.stats.drowningType != 2) {
            super.func_70050_g(air);
        }
    }

    protected int func_70682_h(int par1) {
        if (this.stats.drowningType == 0) {
            return par1;
        }
        return super.func_70682_h(par1);
    }

    public EnumCreatureAttribute func_70668_bt() {
        return this.stats.creatureType;
    }

    protected String func_70639_aQ() {
        if (!this.func_70089_S()) {
            return null;
        }
        if (this.func_70638_az() != null) {
            return this.advanced.angrySound.isEmpty() ? null : this.advanced.angrySound;
        }
        return this.advanced.idleSound.isEmpty() ? null : this.advanced.idleSound;
    }

    public int func_70627_aG() {
        return 160;
    }

    protected String func_70621_aR() {
        if (this.advanced.hurtSound.isEmpty()) {
            return null;
        }
        return this.advanced.hurtSound;
    }

    protected String func_70673_aS() {
        if (this.advanced.deathSound.isEmpty()) {
            return null;
        }
        return this.advanced.deathSound;
    }

    protected float func_70647_i() {
        if (this.advanced.disablePitch) {
            return 1.0f;
        }
        return super.func_70647_i();
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
        if (!this.advanced.stepSound.equals("")) {
            this.func_85030_a(this.advanced.stepSound, 0.15f, 1.0f);
        } else {
            super.func_145780_a(p_145780_1_, p_145780_2_, p_145780_3_, p_145780_4_);
        }
    }

    public EntityPlayerMP getFakePlayer() {
        if (this.field_70170_p.field_72995_K) {
            return null;
        }
        if (chateventPlayer == null) {
            chateventPlayer = new FakePlayer((WorldServer)this.field_70170_p, (GameProfile)chateventProfile);
        }
        EntityUtil.Copy((EntityLivingBase)this, (EntityLivingBase)chateventPlayer);
        EntityNPCInterface.chateventProfile.npc = this;
        chateventPlayer.refreshDisplayName();
        IItemStack stack = NpcAPI.Instance().createItem("minecraft:stone", 0, 1);
        chateventPlayer.func_70062_b(0, stack.getMCItemStack());
        return chateventPlayer;
    }

    public void saySurrounding(ILine line) {
        if (line == null || line.getText() == null || this.getFakePlayer() == null) {
            return;
        }
        ServerChatEvent event = new ServerChatEvent(this.getFakePlayer(), line.getText(), new ChatComponentTranslation(line.getText().replace("%", "%%"), new Object[0]));
        if (MinecraftForge.EVENT_BUS.post((Event)event) || event.component == null) {
            return;
        }
        line.setText(event.component.func_150260_c().replace("%%", "%"));
        List inRange = this.field_70170_p.func_72872_a(EntityPlayer.class, this.field_70121_D.func_72314_b(20.0, 20.0, 20.0));
        for (EntityPlayer player : inRange) {
            this.say(player, line);
        }
    }

    public void say(EntityPlayer player, ILine line) {
        if (line == null || !this.canSee((Entity)player) || line.getText() == null) {
            return;
        }
        if (!line.getSound().isEmpty()) {
            PacketHandler.Instance.sendToPlayer(new SoundManagementPacket(EnumSoundOperation.PLAY_SOUND, line.getSound(), (float)this.field_70165_t, (float)this.field_70163_u, (float)this.field_70161_v), (EntityPlayerMP)player);
        }
        PacketHandler.Instance.sendToPlayer(new ChatBubblePacket(this.func_145782_y(), line.getText(), !line.hideText()), (EntityPlayerMP)player);
    }

    public boolean func_94059_bO() {
        return true;
    }

    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
        this.npcVersion = compound.func_74762_e("ModRev");
        VersionCompatibility.CheckNpcCompatibility(this, compound);
        this.display.readToNBT(compound);
        this.stats.readToNBT(compound);
        this.ais.readToNBT(compound);
        this.script.readFromNBT(compound);
        this.script.readEventsFromNBT(compound);
        this.timers.readFromNBT(compound);
        this.advanced.readToNBT(compound);
        this.abilities.readFromNBT(compound);
        if (this.advanced.role != EnumRoleType.None && this.roleInterface != null) {
            this.roleInterface.readFromNBT(compound);
        }
        if (this.advanced.job != EnumJobType.None && this.jobInterface != null) {
            this.jobInterface.readFromNBT(compound);
        }
        this.inventory.readEntityFromNBT(compound);
        this.transform.readToNBT(compound);
        this.killedtime = compound.func_74763_f("KilledTime");
        this.totalTicksAlive = compound.func_74763_f("TotalTicksAlive");
        this.linkedName = compound.func_74779_i("LinkedNpcName");
        if (!this.isRemote()) {
            LinkedNpcController.Instance.loadNpcData(this);
        }
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a((double)ConfigMain.NpcNavRange);
        this.updateTasks();
        this.syncDespawnPersistence();
    }

    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
        this.display.writeToNBT(compound);
        this.stats.writeToNBT(compound);
        this.ais.writeToNBT(compound);
        this.script.writeToNBT(compound);
        this.script.writeEventsToNBT(compound);
        this.timers.writeToNBT(compound);
        this.advanced.writeToNBT(compound);
        this.abilities.writeToNBT(compound);
        if (this.advanced.role != EnumRoleType.None && this.roleInterface != null) {
            this.roleInterface.writeToNBT(compound);
        }
        if (this.advanced.job != EnumJobType.None && this.jobInterface != null) {
            this.jobInterface.writeToNBT(compound);
        }
        this.inventory.writeEntityToNBT(compound);
        this.transform.writeToNBT(compound);
        compound.func_74772_a("KilledTime", this.killedtime);
        compound.func_74772_a("TotalTicksAlive", this.totalTicksAlive);
        compound.func_74768_a("ModRev", this.npcVersion);
        compound.func_74778_a("LinkedNpcName", this.linkedName);
    }

    public void updateHitbox() {
        float newHeight;
        float newWidth;
        if (this.currentAnimation == EnumAnimation.LYING || this.currentAnimation == EnumAnimation.CRAWLING) {
            newWidth = 0.8f;
            newHeight = 0.4f;
        } else if (this.func_70115_ae()) {
            newWidth = 0.6f;
            newHeight = this.baseHeight * 0.77f;
        } else {
            newWidth = 0.6f;
            newHeight = this.baseHeight;
        }
        newWidth = newWidth / 5.0f * (float)this.display.modelSize;
        newHeight = newHeight / 5.0f * (float)this.display.modelSize;
        if (this.display.hitboxData.isHitboxEnabled()) {
            newWidth *= this.display.hitboxData.getWidthScale();
            newHeight *= this.display.hitboxData.getHeightScale();
        }
        if (this.isKilled() && this.stats.hideKilledBody) {
            newWidth = 1.0E-5f;
        }
        newWidth = Math.max(newWidth, 1.0E-5f);
        newHeight = Math.max(newHeight, 1.0E-5f);
        if ((double)(newWidth / 2.0f) > World.MAX_ENTITY_RADIUS) {
            World.MAX_ENTITY_RADIUS = newWidth / 2.0f;
        }
        this.field_70130_N = newWidth;
        this.field_70131_O = newHeight;
        this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
    }

    public void func_70609_aI() {
        if (this.stats.spawnCycle == 3) {
            super.func_70609_aI();
            return;
        }
        ++this.field_70725_aQ;
        if (this.field_70170_p.field_72995_K) {
            return;
        }
        if (!this.hasDied) {
            this.func_70106_y();
        }
        if (this.killedtime < System.currentTimeMillis() && (this.stats.spawnCycle == 0 || this.field_70170_p.func_72935_r() && this.stats.spawnCycle == 1 || !this.field_70170_p.func_72935_r() && this.stats.spawnCycle == 2)) {
            this.reset();
        }
    }

    public void reset() {
        this.hasDied = false;
        this.field_70128_L = false;
        this.wasKilled = false;
        this.func_70031_b(false);
        this.func_70606_j(this.func_110138_aP());
        this.field_70180_af.func_75692_b(14, (Object)0);
        this.field_70180_af.func_75692_b(15, (Object)0);
        if (ConfigScript.ClearActionsOnDeath) {
            this.actionManager.clear();
        }
        this.combatHandler.reset();
        this.func_70624_b(null);
        this.func_70604_c(null);
        this.field_70725_aQ = 0;
        this.currentAnimation = EnumAnimation.NONE;
        this.updateHitbox();
        if (this.ais.returnToStart && !this.hasOwner() && !this.isRemote()) {
            this.func_70012_b(this.getStartXPos(), this.getStartYPos(), this.getStartZPos(), this.field_70177_z, this.field_70125_A);
        }
        this.killedtime = 0L;
        this.func_70066_B();
        this.func_70674_bp();
        this.func_70661_as().func_75499_g();
        if (this.canFly()) {
            ((PathNavigateFlying)this.func_70661_as()).targetPos = null;
            this.func_70661_as().func_75499_g();
            ((FlyingMoveHelper)this.func_70605_aq()).field_75643_f = false;
        }
        this.func_70612_e(0.0f, 0.0f);
        this.field_70140_Q = 0.0f;
        this.updateAI = true;
        this.ais.movingPos = 0;
        if (this.getOwner() != null) {
            this.getOwner().func_130011_c(null);
        }
        if (this.jobInterface != null) {
            this.jobInterface.reset();
        }
        if (!this.isRemote()) {
            EventHooks.onNPCInit(this);
        }
    }

    public void onCollide() {
        if (!this.func_70089_S() || this.field_70173_aa % 4 != 0) {
            return;
        }
        AxisAlignedBB axisalignedbb = null;
        axisalignedbb = this.field_70154_o != null && this.field_70154_o.func_70089_S() ? this.field_70121_D.func_111270_a(this.field_70154_o.field_70121_D).func_72314_b(1.0, 0.0, 1.0) : this.field_70121_D.func_72314_b(1.0, 0.5, 1.0);
        List list = this.field_70170_p.func_72839_b((Entity)this, axisalignedbb);
        if (list == null) {
            return;
        }
        if (!this.isRemote()) {
            for (int i = 0; i < list.size(); ++i) {
                Entity entity = (Entity)list.get(i);
                if (!entity.func_70089_S()) continue;
                EventHooks.onNPCCollide(this, entity);
            }
        }
    }

    public void func_70063_aa() {
    }

    public void cloakUpdate() {
        this.field_20066_r = this.field_20063_u;
        this.field_20065_s = this.field_20062_v;
        this.field_20064_t = this.field_20061_w;
        double d = this.field_70165_t - this.field_20063_u;
        double d1 = this.field_70163_u - this.field_20062_v;
        double d2 = this.field_70161_v - this.field_20061_w;
        double d3 = 10.0;
        if (d > d3) {
            this.field_20066_r = this.field_20063_u = this.field_70165_t;
        }
        if (d2 > d3) {
            this.field_20064_t = this.field_20061_w = this.field_70161_v;
        }
        if (d1 > d3) {
            this.field_20065_s = this.field_20062_v = this.field_70163_u;
        }
        if (d < -d3) {
            this.field_20066_r = this.field_20063_u = this.field_70165_t;
        }
        if (d2 < -d3) {
            this.field_20064_t = this.field_20061_w = this.field_70161_v;
        }
        if (d1 < -d3) {
            this.field_20065_s = this.field_20062_v = this.field_70163_u;
        }
        this.field_20063_u += d * 0.25;
        this.field_20061_w += d2 * 0.25;
        this.field_20062_v += d1 * 0.25;
    }

    protected void func_70623_bb() {
        this.syncDespawnPersistence();
        super.func_70623_bb();
    }

    public void syncDespawnPersistence() {
        if (this.stats == null) {
            if (!this.field_82179_bU) {
                this.func_110163_bv();
            }
            return;
        }
        if (this.stats.canDespawn) {
            this.field_82179_bU = false;
        } else if (!this.field_82179_bU) {
            this.func_110163_bv();
        }
    }

    protected boolean func_70692_ba() {
        return this.stats.canDespawn;
    }

    public ItemStack func_70694_bm() {
        if (this.isAttacking()) {
            return this.inventory.getWeapon();
        }
        if (this.advanced.role == EnumRoleType.Companion) {
            return ((RoleCompanion)this.roleInterface).getHeldItem();
        }
        if (this.jobInterface != null && this.jobInterface.overrideMainHand) {
            return this.jobInterface.mainhand;
        }
        return this.inventory.getWeapon();
    }

    public ItemStack func_71124_b(int slot) {
        if (slot == 0) {
            return this.inventory.weapons.get(0);
        }
        return this.inventory.armorItemInSlot(4 - slot);
    }

    public ItemStack func_130225_q(int slot) {
        return this.inventory.armorItemInSlot(3 - slot);
    }

    public void func_70062_b(int slot, ItemStack item) {
        if (slot == 0) {
            this.inventory.setWeapon(item);
        } else {
            this.inventory.armor.put(4 - slot, item);
        }
    }

    public ItemStack[] func_70035_c() {
        return lastActive;
    }

    protected void func_82160_b(boolean p_82160_1_, int p_82160_2_) {
    }

    public ItemStack getOffHand() {
        if (this.isAttacking()) {
            return this.inventory.getOffHand();
        }
        if (this.jobInterface != null && this.jobInterface.overrideOffHand) {
            return this.jobInterface.offhand;
        }
        return this.inventory.getOffHand();
    }

    public void func_70645_a(DamageSource damagesource) {
        this.func_70031_b(false);
        this.func_70661_as().func_75499_g();
        this.func_70066_B();
        this.func_70674_bp();
        Entity entity = damagesource.func_76346_g();
        EntityLivingBase attackingEntity = null;
        if (entity instanceof EntityLivingBase) {
            attackingEntity = (EntityLivingBase)entity;
        }
        if (entity instanceof EntityArrow && ((EntityArrow)entity).field_70250_c instanceof EntityLivingBase) {
            attackingEntity = (EntityLivingBase)((EntityArrow)entity).field_70250_c;
        } else if (entity instanceof EntityThrowable) {
            attackingEntity = ((EntityThrowable)entity).func_85052_h();
        }
        int droppedXp = this.inventory.getDroppedXp();
        ArrayList<ItemStack> droppedItems = this.inventory.getDroppedItems(damagesource);
        if (!this.isRemote()) {
            Line line;
            NpcEvent.DiedEvent event = new NpcEvent.DiedEvent(this.wrappedNPC, damagesource, entity, droppedItems, droppedXp);
            if (EventHooks.onNPCKilled(this, event)) {
                return;
            }
            droppedItems.clear();
            for (IItemStack iItemStack : event.droppedItems) {
                droppedItems.add(iItemStack.getMCItemStack());
            }
            droppedXp = event.expDropped;
            if (this.field_70718_bc > 0) {
                this.inventory.dropItems(entity, droppedItems);
                this.inventory.dropXp(entity, droppedXp);
            }
            if ((line = this.advanced.getKilledLine()) != null) {
                this.saySurrounding(line.formatTarget(attackingEntity));
            }
        }
        super.func_70645_a(damagesource);
    }

    public void func_70106_y() {
        this.hasDied = true;
        if (this.field_70170_p.field_72995_K || this.stats.spawnCycle == 3) {
            this.func_70656_aK();
            this.delete();
        } else {
            if (this.field_70153_n != null) {
                NPCMountUtil.stabilizeDismountedRider(this.field_70153_n);
                this.field_70153_n.func_70078_a(null);
                NPCMountUtil.haltMountedMotion(this, this.mountState);
            }
            if (this.field_70154_o != null) {
                this.func_70078_a(null);
            }
            this.mountState.lastRider = null;
            NPCMountUtil.resetMountedFlightState(this, this.mountState);
            this.func_70606_j(-1.0f);
            this.func_70031_b(false);
            this.func_70661_as().func_75499_g();
            if (this.killedtime <= 0L) {
                this.killedtime = (long)this.stats.respawnTime * 1000L + System.currentTimeMillis();
            }
            if (this.advanced.role != EnumRoleType.None && this.roleInterface != null) {
                this.roleInterface.killed();
            }
            if (this.advanced.job != EnumJobType.None && this.jobInterface != null) {
                this.jobInterface.killed();
            }
        }
    }

    public void delete() {
        if (this.advanced.role != EnumRoleType.None && this.roleInterface != null) {
            this.roleInterface.delete();
        }
        if (this.advanced.job != EnumJobType.None && this.jobInterface != null) {
            this.jobInterface.delete();
        }
        this.actionManager.clear();
        super.func_70106_y();
    }

    public float getStartXPos() {
        return (float)this.getStartPos()[0] + this.ais.bodyOffsetX / 10.0f;
    }

    public float getStartZPos() {
        return (float)this.getStartPos()[2] + this.ais.bodyOffsetZ / 10.0f;
    }

    public int[] getStartPos() {
        if (this.ais.startPos == null || this.ais.startPos.length != 3) {
            this.ais.startPos = new int[]{MathHelper.func_76128_c((double)this.field_70165_t), MathHelper.func_76128_c((double)this.field_70163_u), MathHelper.func_76128_c((double)this.field_70161_v)};
        }
        return this.ais.startPos;
    }

    public boolean isVeryNearAssignedPlace() {
        double xx = this.field_70165_t - (double)this.getStartXPos();
        double zz = this.field_70161_v - (double)this.getStartZPos();
        if (xx < -0.2 || xx > 0.2) {
            return false;
        }
        return !(zz < -0.2) && !(zz > 0.2);
    }

    public IIcon func_70620_b(ItemStack par1ItemStack, int par2) {
        if (par1ItemStack.func_77973_b() instanceof ItemBow) {
            return par1ItemStack.func_77973_b().getIcon(par1ItemStack, par2);
        }
        EntityPlayer player = CustomNpcs.proxy.getPlayer();
        if (player == null) {
            return super.func_70620_b(par1ItemStack, par2);
        }
        return player.func_70620_b(par1ItemStack, par2);
    }

    public double getStartYPos() {
        int i = this.getStartPos()[0];
        int j = this.getStartPos()[1];
        int k = this.getStartPos()[2];
        double yy = 0.0;
        for (int ii = j; ii >= 0; --ii) {
            AxisAlignedBB bb;
            Block block;
            if (this.canFly()) {
                if (ii < j - 1) {
                    yy = j;
                    break;
                }
                block = this.field_70170_p.func_147439_a(i, ii, k);
                bb = block.func_149668_a(this.field_70170_p, i, ii, k);
                if (bb == null) continue;
                yy = bb.field_72337_e;
                break;
            }
            block = this.field_70170_p.func_147439_a(i, ii, k);
            if (block == null || block == Blocks.field_150350_a || (bb = block.func_149668_a(this.field_70170_p, i, ii, k)) == null) continue;
            yy = bb.field_72337_e;
            break;
        }
        if (yy <= 0.0) {
            this.func_70106_y();
        }
        return yy;
    }

    public void givePlayerItem(EntityPlayer player, ItemStack item) {
        if (this.field_70170_p.field_72995_K) {
            return;
        }
        item = item.func_77946_l();
        float f = 0.7f;
        double d = (double)(this.field_70170_p.field_73012_v.nextFloat() * f) + (double)(1.0f - f);
        double d1 = (double)(this.field_70170_p.field_73012_v.nextFloat() * f) + (double)(1.0f - f);
        double d2 = (double)(this.field_70170_p.field_73012_v.nextFloat() * f) + (double)(1.0f - f);
        EntityItem entityitem = new EntityItem(this.field_70170_p, this.field_70165_t + d, this.field_70163_u + d1, this.field_70161_v + d2, item);
        entityitem.field_145804_b = 2;
        this.field_70170_p.func_72838_d((Entity)entityitem);
        int i = item.field_77994_a;
        if (player.field_71071_by.func_70441_a(item)) {
            this.field_70170_p.func_72956_a((Entity)entityitem, "random.pop", 0.2f, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7f + 1.0f) * 2.0f);
            player.func_71001_a((Entity)entityitem, i);
            if (item.field_77994_a <= 0) {
                entityitem.func_70106_y();
            }
        }
    }

    public boolean func_70608_bn() {
        return this.currentAnimation == EnumAnimation.LYING && !this.isAttacking();
    }

    public boolean func_70115_ae() {
        return this.currentAnimation == EnumAnimation.SITTING && !this.isAttacking() || this.field_70154_o != null;
    }

    public boolean isWalking() {
        return this.ais.movingType != EnumMovingType.Standing || this.isAttacking() || this.isFollower() || this.getBoolFlag(1);
    }

    public boolean isFlying() {
        return this.isNpcFlying();
    }

    public boolean isNpcFlying() {
        return (this.field_70180_af.func_75683_a(17) & 1) != 0;
    }

    public boolean isNpcJumping() {
        return (this.field_70180_af.func_75683_a(17) & 2) != 0;
    }

    private void setNpcStateFlag(int mask, boolean value) {
        byte updated;
        byte current = this.field_70180_af.func_75683_a(17);
        byte by = updated = value ? (byte)(current | mask) : (byte)(current & ~mask);
        if (current != updated) {
            this.field_70180_af.func_75692_b(17, (Object)updated);
        }
    }

    public void setNpcFlyingState(boolean flying) {
        this.setNpcStateFlag(1, flying);
    }

    public void setNpcJumpingState(boolean jumping) {
        this.setNpcStateFlag(2, jumping);
        this.field_70703_bu = jumping;
    }

    public void setBoolFlag(boolean bo, int id) {
        int i = this.field_70180_af.func_75679_c(15);
        if (bo && (i & id) == 0) {
            this.field_70180_af.func_75692_b(15, (Object)(i | id));
        }
        if (!bo && (i & id) != 0) {
            this.field_70180_af.func_75692_b(15, (Object)(i - id));
        }
    }

    public boolean getBoolFlag(int id) {
        return (this.field_70180_af.func_75679_c(15) & id) != 0;
    }

    public boolean func_70093_af() {
        return this.currentAnimation == EnumAnimation.SNEAKING;
    }

    public void func_70653_a(Entity par1Entity, float par2, double par3, double par5) {
        if (this.stats.resistances.knockback >= 2.0f) {
            return;
        }
        this.field_70160_al = true;
        float f1 = MathHelper.func_76133_a((double)(par3 * par3 + par5 * par5));
        float f2 = 0.5f * (2.0f - this.stats.resistances.knockback);
        this.field_70159_w /= 2.0;
        this.field_70181_x /= 2.0;
        this.field_70179_y /= 2.0;
        this.field_70159_w -= par3 / (double)f1 * (double)f2;
        this.field_70181_x += 0.2 + (double)(f2 / 2.0f);
        this.field_70179_y -= par5 / (double)f1 * (double)f2;
        if (this.field_70181_x > (double)0.4f) {
            this.field_70181_x = 0.4f;
        }
    }

    public void func_70024_g(double p_70024_1_, double p_70024_3_, double p_70024_5_) {
        if (this.field_70717_bb != null) {
            float f2 = 0.5f * (2.0f - this.stats.resistances.knockback);
            super.func_70024_g(p_70024_1_ * (double)f2, p_70024_3_ * (double)f2, p_70024_5_ * (double)f2);
        } else {
            super.func_70024_g(p_70024_1_, p_70024_3_, p_70024_5_);
        }
    }

    public Faction getFaction() {
        String[] split = this.field_70180_af.func_75681_e(13).split(":");
        int faction = 0;
        if (this.field_70170_p == null || split.length <= 1 && this.field_70170_p.field_72995_K) {
            return new Faction();
        }
        if (split.length > 1) {
            faction = Integer.parseInt(split[0]);
        }
        if (this.field_70170_p.field_72995_K) {
            Faction fac = new Faction();
            fac.id = faction;
            fac.color = Integer.parseInt(split[1]);
            fac.name = split[2];
            return fac;
        }
        Faction fac = FactionController.getInstance().get(faction);
        if (fac == null) {
            faction = FactionController.getInstance().getFirstFactionId();
            fac = FactionController.getInstance().get(faction);
        }
        return fac;
    }

    public boolean isRemote() {
        return this.field_70170_p == null || this.field_70170_p.field_72995_K;
    }

    public void setFaction(int integer) {
        if (integer < 0 || this.isRemote()) {
            return;
        }
        Faction faction = FactionController.getInstance().get(integer);
        if (faction == null) {
            return;
        }
        String str = faction.id + ":" + faction.color + ":" + faction.name;
        if (str.length() > 64) {
            str = str.substring(0, 64);
        }
        this.field_70180_af.func_75692_b(13, (Object)str);
    }

    public boolean func_70687_e(PotionEffect effect) {
        if (this.stats.potionImmune) {
            return false;
        }
        if (this.func_70668_bt() == EnumCreatureAttribute.ARTHROPOD && effect.func_76456_a() == Potion.field_76436_u.field_76415_H) {
            return false;
        }
        return super.func_70687_e(effect);
    }

    public boolean isAttacking() {
        return this.getBoolFlag(4);
    }

    public boolean isKilled() {
        return this.getBoolFlag(8) || this.field_70128_L;
    }

    public void writeSpawnData(ByteBuf buffer) {
        try {
            ByteBufUtils.writeNBT(buffer, this.writeSpawnData());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public NBTTagCompound writeSpawnData() {
        NBTTagCompound bard;
        NBTTagCompound compound = new NBTTagCompound();
        this.display.writeToNBT(compound);
        compound.func_74780_a("MaxHealth", this.stats.maxHealth);
        compound.func_74782_a("Armor", (NBTBase)NBTTags.nbtItemStackList(this.inventory.getArmor()));
        compound.func_74782_a("Weapons", (NBTBase)NBTTags.nbtItemStackList(this.inventory.getWeapons()));
        compound.func_74768_a("Speed", this.ais.getWalkingSpeed());
        compound.func_74757_a("DeadBody", this.stats.hideKilledBody);
        compound.func_74768_a("StandingState", this.ais.standingType.ordinal());
        compound.func_74768_a("MovingState", this.ais.movingType.ordinal());
        compound.func_74768_a("MovingType", this.ais.movementType);
        compound.func_74780_a("FlySpeed", this.ais.flySpeed);
        compound.func_74780_a("FlyGravity", this.ais.flyGravity);
        compound.func_74757_a("HasFlyLimit", this.ais.hasFlyLimit);
        compound.func_74768_a("FlyHeightLimit", this.ais.flyHeightLimit);
        compound.func_74768_a("Orientation", this.ais.orientation);
        compound.func_74776_a("OffsetY", this.ais.bodyOffsetY);
        compound.func_74768_a("Role", this.advanced.role.ordinal());
        compound.func_74768_a("Job", this.advanced.job.ordinal());
        if (this.advanced.job == EnumJobType.Bard) {
            bard = new NBTTagCompound();
            this.jobInterface.writeToNBT(bard);
            compound.func_74782_a("Bard", (NBTBase)bard);
        }
        if (this.advanced.role == EnumRoleType.Companion) {
            bard = new NBTTagCompound();
            this.roleInterface.writeToNBT(bard);
            compound.func_74782_a("Companion", (NBTBase)bard);
        }
        if (this.advanced.role == EnumRoleType.Mount) {
            NBTTagCompound mount = new NBTTagCompound();
            this.roleInterface.writeToNBT(mount);
            compound.func_74782_a("Mount", (NBTBase)mount);
        }
        if (this instanceof EntityCustomNpc) {
            compound.func_74782_a("ModelData", (NBTBase)((EntityCustomNpc)this).modelData.writeToNBT());
        }
        return compound;
    }

    public void readSpawnData(ByteBuf buf) {
        try {
            this.readSpawnData(ByteBufUtils.readNBT(buf));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readSpawnData(NBTTagCompound compound) {
        this.stats.maxHealth = compound.func_74769_h("MaxHealth");
        this.ais.setWalkingSpeed(compound.func_74762_e("Speed"));
        this.stats.hideKilledBody = compound.func_74767_n("DeadBody");
        this.ais.standingType = EnumStandingType.values()[compound.func_74762_e("StandingState") % EnumStandingType.values().length];
        this.ais.movingType = EnumMovingType.values()[compound.func_74762_e("MovingState") % EnumMovingType.values().length];
        if (compound.func_150297_b("MovingType", 3)) {
            this.ais.movementType = compound.func_74762_e("MovingType");
        }
        if (compound.func_150297_b("FlySpeed", 6)) {
            this.ais.flySpeed = compound.func_74769_h("FlySpeed");
        }
        if (compound.func_150297_b("FlyGravity", 6)) {
            this.ais.flyGravity = compound.func_74769_h("FlyGravity");
        }
        if (compound.func_150297_b("HasFlyLimit", 1)) {
            this.ais.hasFlyLimit = compound.func_74767_n("HasFlyLimit");
        }
        if (compound.func_150297_b("FlyHeightLimit", 3)) {
            this.ais.flyHeightLimit = compound.func_74762_e("FlyHeightLimit");
        }
        this.ais.orientation = compound.func_74762_e("Orientation");
        this.ais.bodyOffsetY = compound.func_74760_g("OffsetY");
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(this.stats.maxHealth);
        this.inventory.setArmor(NBTTags.getItemStackList(compound.func_150295_c("Armor", 10)));
        this.inventory.setWeapons(NBTTags.getItemStackList(compound.func_150295_c("Weapons", 10)));
        this.advanced.setRole(compound.func_74762_e("Role"));
        this.advanced.setJob(compound.func_74762_e("Job"));
        if (this.advanced.job == EnumJobType.Bard) {
            NBTTagCompound bard = compound.func_74775_l("Bard");
            this.jobInterface.readFromNBT(bard);
        }
        if (this.advanced.role == EnumRoleType.Companion) {
            NBTTagCompound companion = compound.func_74775_l("Companion");
            this.roleInterface.readFromNBT(companion);
        }
        if (this.advanced.role == EnumRoleType.Mount) {
            NBTTagCompound mount = compound.func_74775_l("Mount");
            this.roleInterface.readFromNBT(mount);
        }
        if (this instanceof EntityCustomNpc) {
            ((EntityCustomNpc)this).modelData.readFromNBT(compound.func_74775_l("ModelData"));
        }
        this.display.readToNBT(compound);
    }

    public String func_70005_c_() {
        return this.display.name;
    }

    public boolean func_70003_b(int var1, String var2) {
        if (ConfigMain.NpcUseOpCommands) {
            return true;
        }
        return var1 <= 2;
    }

    public ChunkCoordinates func_82114_b() {
        return new ChunkCoordinates(MathHelper.func_76128_c((double)this.field_70165_t), MathHelper.func_76128_c((double)this.field_70163_u), MathHelper.func_76128_c((double)this.field_70161_v));
    }

    public boolean func_70686_a(Class par1Class) {
        return EntityBat.class != par1Class;
    }

    public void setImmuneToFire(boolean immuneToFire) {
        this.field_70178_ae = immuneToFire;
        this.stats.immuneToFire = immuneToFire;
    }

    public boolean func_70058_J() {
        return !this.stats.immuneToFire && super.func_70058_J();
    }

    public boolean func_70072_I() {
        if (this.stats.drowningType != 1) {
            if (this.field_70170_p.func_72918_a(this.field_70121_D.func_72314_b(0.0, (double)(-this.field_70131_O) / 2.0, 0.0).func_72331_e(0.001, 0.001, 0.001), Material.field_151586_h, (Entity)this)) {
                this.field_70143_R = 0.0f;
                this.field_70171_ac = true;
                this.func_70015_d(0);
            } else {
                this.field_70171_ac = false;
            }
            return false;
        }
        return super.func_70072_I();
    }

    public boolean func_70648_aU() {
        return this.stats.drowningType != 1;
    }

    public void setAvoidWater(boolean avoidWater) {
        this.func_70661_as().func_75491_a(avoidWater);
        this.ais.avoidsWater = avoidWater;
    }

    protected void func_70069_a(float distance) {
        if (NPCMountUtil.isFlyingMountWithFlightEnabled(this)) {
            return;
        }
        if (this.abilities != null && this.abilities.isExecutingAbility() && this.abilities.getCurrentAbility() != null && this.abilities.getCurrentAbility().hasAbilityMovement()) {
            this.field_70143_R = 0.0f;
            return;
        }
        if (!this.stats.noFallDamage) {
            super.func_70069_a(distance);
        }
    }

    public void func_70110_aj() {
        if (!this.stats.ignoreCobweb) {
            super.func_70110_aj();
        }
    }

    public boolean isInRange(Entity entity, double range) {
        return this.isInRange(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, range);
    }

    public boolean isInRange(double posX, double posY, double posZ, double range) {
        double y = Math.abs(this.field_70163_u - posY);
        if (posY >= 0.0 && y > range) {
            return false;
        }
        double x = Math.abs(this.field_70165_t - posX);
        double z = Math.abs(this.field_70161_v - posZ);
        return x <= range && z <= range;
    }

    public boolean func_70067_L() {
        return !this.isKilled();
    }

    public boolean func_70104_M() {
        return this.stats.collidesWith == 0;
    }

    protected void func_85033_bc() {
        List list;
        if (this.stats.collidesWith != 1 && (list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72314_b((double)0.2f, 0.0, (double)0.2f))) != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); ++i) {
                Entity entity = (Entity)list.get(i);
                if (!this.func_70104_M() && (!(entity instanceof EntityNPCInterface) || this.stats.collidesWith != 2 && this.stats.collidesWith != 4) && (!(entity instanceof EntityPlayerMP) || this.stats.collidesWith != 3 && this.stats.collidesWith != 4)) continue;
                super.func_82167_n(entity);
            }
        }
    }

    public void func_70108_f(Entity entity) {
        double d1;
        double d0;
        double d2;
        if (entity.field_70153_n != this && entity.field_70154_o != this && (d2 = MathHelper.func_76132_a((double)(d0 = entity.field_70165_t - this.field_70165_t), (double)(d1 = entity.field_70161_v - this.field_70161_v))) >= (double)0.01f) {
            d2 = MathHelper.func_76133_a((double)d2);
            d0 /= d2;
            d1 /= d2;
            double d3 = 1.0 / d2;
            if (d3 > 1.0) {
                d3 = 1.0;
            }
            d0 *= d3;
            d1 *= d3;
            d0 *= (double)0.05f;
            d1 *= (double)0.05f;
            d0 *= (double)(1.0f - this.field_70144_Y);
            d1 *= (double)(1.0f - this.field_70144_Y);
            if (this.func_70104_M() || entity instanceof EntityNPCInterface && (this.stats.collidesWith == 2 || this.stats.collidesWith == 4) || entity instanceof EntityPlayerMP && (this.stats.collidesWith == 3 || this.stats.collidesWith == 4)) {
                this.func_70024_g(-d0, 0.0, -d1);
            }
            entity.func_70024_g(d0, 0.0, d1);
        }
    }

    protected void func_82167_n(Entity p_82167_1_) {
    }

    public boolean canFly() {
        return false;
    }

    public EntityAIRangedAttack getRangedTask() {
        return this.aiRange;
    }

    public String getRoleDataWatcher() {
        return this.field_70180_af.func_75681_e(16);
    }

    public void setRoleDataWatcher(String s) {
        this.field_70180_af.func_75692_b(16, (Object)s);
    }

    public World func_130014_f_() {
        return this.field_70170_p;
    }

    public boolean func_98034_c(EntityPlayer player) {
        return !(!this.scriptInvisibleToPlayer(player) && this.display.visible != 1 || player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() == CustomItems.wand);
    }

    public boolean scriptInvisibleToPlayer(EntityPlayer player) {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            return this.isInvisibleToClient(player);
        }
        return this.display.invisibleToList != null && this.display.invisibleToList.contains(player.getPersistentID());
    }

    @SideOnly(value=Side.CLIENT)
    public boolean isInvisibleToClient(EntityPlayer player) {
        return player == Minecraft.func_71410_x().field_71439_g ? this.display.isInvisibleToMe : this.display.getTempScriptInvisible(player.func_145782_y());
    }

    public boolean func_82150_aj() {
        return this.display.visible != 0;
    }

    public void func_145747_a(IChatComponent var1) {
    }

    public void setCurrentAnimation(EnumAnimation animation) {
        this.currentAnimation = animation;
        this.field_70180_af.func_75692_b(14, (Object)animation.ordinal());
    }

    public boolean canSee(Entity entity) {
        return this.func_70635_at().func_75522_a(entity);
    }

    public boolean isFollower() {
        return this.advanced.role == EnumRoleType.Follower && ((RoleFollower)this.roleInterface).isFollowing() || this.advanced.role == EnumRoleType.Companion && ((RoleCompanion)this.roleInterface).isFollowing() || this.advanced.job == EnumJobType.Follower && ((JobFollower)this.jobInterface).isFollowing();
    }

    public EntityLivingBase getOwner() {
        if (this.roleInterface instanceof RoleFollower) {
            return ((RoleFollower)this.roleInterface).owner;
        }
        if (this.roleInterface instanceof RoleCompanion) {
            return ((RoleCompanion)this.roleInterface).owner;
        }
        if (this.jobInterface instanceof JobFollower) {
            return ((JobFollower)this.jobInterface).following;
        }
        return null;
    }

    public boolean hasOwner() {
        return this.advanced.role == EnumRoleType.Follower && ((RoleFollower)this.roleInterface).hasOwner() || this.advanced.role == EnumRoleType.Companion && ((RoleCompanion)this.roleInterface).hasOwner() || this.advanced.job == EnumJobType.Follower && ((JobFollower)this.jobInterface).hasOwner();
    }

    public int followRange() {
        if (this.advanced.role == EnumRoleType.Follower && ((RoleFollower)this.roleInterface).isFollowing()) {
            return 36;
        }
        if (this.advanced.role == EnumRoleType.Companion && ((RoleCompanion)this.roleInterface).isFollowing()) {
            return ((RoleCompanion)this.roleInterface).followRange();
        }
        if (this.advanced.job == EnumJobType.Follower && ((JobFollower)this.jobInterface).isFollowing()) {
            return 16;
        }
        return 225;
    }

    public void func_110171_b(int x, int y, int z, int range) {
        super.func_110171_b(x, y, z, range);
        this.ais.startPos = new int[]{x, y, z};
    }

    protected float func_70655_b(DamageSource source, float damage) {
        if (this.advanced.role == EnumRoleType.Companion) {
            damage = ((RoleCompanion)this.roleInterface).applyArmorCalculations(source, damage);
        }
        return damage;
    }

    public boolean func_142014_c(EntityLivingBase entity) {
        if (entity instanceof EntityPlayer && !this.isRemote() && this.getFaction().isFriendlyToPlayer((EntityPlayer)entity)) {
            return true;
        }
        return super.func_142014_c(entity);
    }

    public void setDataWatcher(DataWatcher dataWatcher) {
        this.field_70180_af = dataWatcher;
    }

    public void func_70612_e(float strafe, float forward) {
        if (this.handleMountedMovement(strafe, forward)) {
            return;
        }
        double d0 = this.field_70165_t;
        double d1 = this.field_70163_u;
        double d2 = this.field_70161_v;
        super.func_70612_e(strafe, forward);
        if (this.advanced.role == EnumRoleType.Companion && !this.isRemote()) {
            ((RoleCompanion)this.roleInterface).addMovementStat(this.field_70165_t - d0, this.field_70163_u - d1, this.field_70161_v - d2);
        }
    }

    protected boolean handleMountedMovement(float strafe, float forward) {
        return NPCMountUtil.handleMountedMovement(this, this.mountState, strafe, forward);
    }

    public void performMountedMovement(float strafe, float forward, float moveSpeed) {
        this.field_70702_br = strafe;
        this.field_70701_bs = forward;
        boolean flightMode = this.isMountFlightModeActive();
        double prevMotionY = this.field_70181_x;
        if (!this.field_70170_p.field_72995_K) {
            this.field_70747_aH = this.getMountedAirStrafeFactor(moveSpeed, flightMode);
            this.func_70659_e(moveSpeed);
            super.func_70612_e(strafe, forward);
            if (flightMode) {
                this.field_70181_x = prevMotionY;
                this.field_70160_al = true;
            }
        }
        this.updateMountedLimbSwing();
    }

    protected boolean isMountFlightModeActive() {
        return NPCMountUtil.isMountInFlightMode(this.mountState);
    }

    private float getMountedAirStrafeFactor(float moveSpeed, boolean flightMode) {
        if (moveSpeed <= 0.0f) {
            return 0.0f;
        }
        if (!flightMode) {
            return moveSpeed * 0.1f;
        }
        return moveSpeed * 0.5f;
    }

    private void updateMountedLimbSwing() {
        float limbSwingSpeed;
        this.field_70722_aY = this.field_70721_aZ;
        double deltaX = this.field_70165_t - this.field_70169_q;
        double deltaZ = this.field_70161_v - this.field_70166_s;
        if (this.field_70170_p.field_72995_K && deltaX * deltaX + deltaZ * deltaZ < 1.0E-6) {
            deltaX = this.field_70159_w;
            deltaZ = this.field_70179_y;
        }
        if ((limbSwingSpeed = MathHelper.func_76133_a((double)(deltaX * deltaX + deltaZ * deltaZ)) * 4.0f) > 1.0f) {
            limbSwingSpeed = 1.0f;
        }
        this.field_70721_aZ += (limbSwingSpeed - this.field_70721_aZ) * 0.4f;
        this.field_70754_ba += this.field_70721_aZ;
    }

    public boolean func_110164_bC() {
        return false;
    }

    public boolean shouldDismountInWater(Entity rider) {
        return false;
    }

    public void func_70043_V() {
        if (this.advanced.role == EnumRoleType.Mount && this.roleInterface instanceof RoleMount && this.field_70153_n != null) {
            RoleMount mount = (RoleMount)this.roleInterface;
            double scale = Math.max(0.1, (double)this.display.modelSize / 5.0);
            Vec3 seat = Vec3.func_72443_a((double)mount.getOffsetX(), (double)0.0, (double)((double)mount.getOffsetZ() + 0.8 * scale));
            seat.func_72442_b((float)Math.toRadians(-this.field_70761_aq));
            double px = this.field_70165_t + seat.field_72450_a;
            double py = this.field_70163_u + this.func_70042_X() + this.field_70153_n.func_70033_W() + (double)mount.getOffsetY();
            double pz = this.field_70161_v + seat.field_72449_c;
            this.field_70153_n.func_70107_b(px, py, pz);
            if (this.field_70153_n instanceof EntityLivingBase) {
                EntityLivingBase rider = (EntityLivingBase)this.field_70153_n;
                rider.field_70127_C = rider.field_70125_A;
                rider.field_70126_B = rider.field_70177_z;
                rider.field_70760_ar = this.field_70761_aq;
                rider.field_70761_aq = this.field_70761_aq;
                rider.field_70758_at = this.field_70761_aq;
                rider.field_70759_as = this.field_70761_aq;
            }
        } else {
            super.func_70043_V();
        }
    }

    private void handleMountRiderState() {
        NPCMountUtil.handleMountRiderState(this, this.mountState);
    }

    public int getModelType() {
        return this.display.modelType;
    }

    public void setModelType(int val) {
        this.display.modelType = val;
    }

    protected void func_110159_bB() {
    }

    static {
        lastActive = new ItemStack[5];
    }
}

