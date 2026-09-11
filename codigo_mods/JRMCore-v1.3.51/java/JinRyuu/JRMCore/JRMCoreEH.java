/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.gson.Gson
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonParser
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerLoggedInEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IProjectile
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemBucket
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.ClientChatReceivedEvent
 *  net.minecraftforge.client.event.EntityViewRenderEvent$FogColors
 *  net.minecraftforge.client.event.EntityViewRenderEvent$FogDensity
 *  net.minecraftforge.client.event.RenderBlockOverlayEvent
 *  net.minecraftforge.client.event.RenderLivingEvent$Post
 *  net.minecraftforge.event.entity.EntityEvent$EntityConstructing
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.event.entity.item.ItemEvent
 *  net.minecraftforge.event.entity.living.LivingAttackEvent
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.living.LivingDropsEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.player.FillBucketEvent
 *  net.minecraftforge.event.entity.player.ItemTooltipEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$Clone
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$Action
 *  net.minecraftforge.event.entity.player.PlayerUseItemEvent
 *  net.minecraftforge.event.entity.player.PlayerUseItemEvent$Finish
 *  net.minecraftforge.event.entity.player.PlayerUseItemEvent$Start
 *  net.minecraftforge.event.world.BlockEvent$BreakEvent
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.JRMCore.Ds;
import JinRyuu.JRMCore.JRMCoreCliTicH;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreComTickH;
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreH2;
import JinRyuu.JRMCore.JRMCoreHC;
import JinRyuu.JRMCore.JRMCoreHDBC;
import JinRyuu.JRMCore.JRMCoreHNC;
import JinRyuu.JRMCore.JRMCoreHSAC;
import JinRyuu.JRMCore.JRMCoreM;
import JinRyuu.JRMCore.JRMCoreMsn;
import JinRyuu.JRMCore.JRMCoreMsnBundle;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.entity.EntityEnergyAtt;
import JinRyuu.JRMCore.entity.EntityNPCshadow;
import JinRyuu.JRMCore.entity.EntitySafeZone;
import JinRyuu.JRMCore.i.ExtendedEntity;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import JinRyuu.JRMCore.mod_JRMCore;
import JinRyuu.JRMCore.p.PD;
import JinRyuu.JRMCore.p.SyncPlayerPropsMessage;
import JinRyuu.JRMCore.server.JGPlayerMP;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCFormMastery;
import JinRyuu.JRMCore.server.config.dbc.JGConfigDBCGoD;
import JinRyuu.JRMCore.server.config.dbc.JGConfigUltraInstinct;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.world.BlockEvent;
import org.lwjgl.opengl.GL11;

public class JRMCoreEH {
    public static ChatStyle color = new ChatStyle().func_150238_a(EnumChatFormatting.DARK_GREEN);
    public static List allSafeZones = new ArrayList();
    public static boolean aw = false;
    public static boolean gk = false;
    public static boolean dt = false;
    private Map<String, String> zz5bj5 = Maps.newHashMap();
    private int kfp = 0;
    private String b5t;
    boolean jfc = JRMCoreH.JFC();
    boolean dbc = JRMCoreH.DBC();
    boolean nc = JRMCoreH.NC();
    boolean saoc = JRMCoreH.SAOC();
    float red = 1.0f;
    float green = 1.0f;
    float blue = 1.0f;
    boolean setCol = false;
    float density = 1.0f;
    boolean setDen = false;
    private static final ResourceLocation RES_UNDERMEDLIQUID_OVERLAY = new ResourceLocation("jinryuudragonbc:textures/misc/undermedicalliquid.png");
    public static ArrayList<String> currentSafeZoneNames = new ArrayList();

    @SubscribeEvent
    public void sAmkp2(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer)event.entity) == null) {
            ExtendedPlayer.register((EntityPlayer)event.entity);
        }
        if (event.entity instanceof EntityMob && JRMCoreH.SAOC() && ExtendedEntity.get((Entity)((EntityMob)event.entity)) == null) {
            ExtendedEntity.register((Entity)((EntityMob)event.entity));
        }
    }

    @SubscribeEvent
    public void UJqX2K(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.player instanceof EntityPlayerMP) {
            PD.sendTo(new SyncPlayerPropsMessage(event.player), (EntityPlayerMP)event.player);
        }
    }

    @SubscribeEvent
    public void dJ7QxA(PlayerEvent.Clone event) {
        ExtendedPlayer.get(event.entityPlayer).copy(ExtendedPlayer.get(event.original));
    }

    @SubscribeEvent
    public void MGeX4g(LivingEvent.LivingUpdateEvent event) {
        EntityPlayer player;
        if (event.entity instanceof EntityPlayer) {
            player = (EntityPlayer)event.entity;
            ExtendedPlayer.get(player).onUpdate();
        }
        if (event.entity instanceof EntityMob && JRMCoreH.SAOC()) {
            player = (EntityMob)event.entity;
            ExtendedEntity.get((Entity)player).onUpdate();
        }
        if (event.entity instanceof EntityLivingBase && !event.entity.field_70170_p.field_72995_K) {
            NBTTagCompound nbt;
            int newHealth;
            String[] aamt;
            EntityLivingBase entity = (EntityLivingBase)event.entity;
            String entityData = entity.getEntityData().func_74779_i("jrmcSpawnInitiatedCMT");
            if (entityData.length() > 1 && entity.field_70173_aa % 20 == 1 && (aamt = JRMCoreM.getMobTranNext(entityData, entity)) != null && aamt.length > 2 && !aamt[1].equals("0") && entity.field_70173_aa >= Integer.parseInt(aamt[1])) {
                double mt;
                EntityLivingBase entity2 = (EntityLivingBase)EntityList.func_75620_a((String)aamt[0], (World)entity.field_70170_p);
                entity2.func_70012_b(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, 0.0f, 0.0f);
                if (JRMCoreH.DBC()) {
                    JRMCoreHDBC.ifEvilDBCnpcs((Entity)entity2, (Entity)entity);
                }
                if (entity.func_70643_av() != null) {
                    entity2.func_70604_c(entity.func_70643_av());
                }
                if (entity instanceof EntityLiving && entity2 instanceof EntityLiving) {
                    ((EntityLiving)entity).func_70624_b(((EntityLiving)entity).func_70638_az());
                }
                if ((mt = Double.parseDouble(aamt[2])) > 0.0) {
                    entity2.getEntityData().func_74780_a("jrmcSpawnInitiatedCAT", entity.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e() * mt);
                    entity2.getEntityData().func_74780_a("jrmcSpawnInitiatedCHP", entity.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111126_e() * mt);
                    entity2.getEntityData().func_74778_a("jrmcSpawnInitiatedIMP", entity.getEntityData().func_74779_i("jrmcSpawnInitiatedIMP"));
                    entity2.getEntityData().func_74778_a("jrmcSpawnInitiatedCMT", entityData);
                }
                entity.field_70170_p.func_72838_d((Entity)entity2);
                double boxSize = 32.0;
                List pl = entity.field_70170_p.func_72872_a(EntityPlayer.class, entity.field_70121_D.func_72314_b(32.0, 32.0, 32.0));
                if (pl.size() > 0 && aamt.length > 3 && aamt[3].length() > 2) {
                    for (int v = 0; v < pl.size(); ++v) {
                        EntityPlayer va = (EntityPlayer)pl.get(v);
                        va.func_145747_a(new ChatComponentTranslation(aamt[3], new Object[0]).func_150255_a(JRMCoreH2.styl_wht));
                    }
                }
                entity.func_70106_y();
            }
            if ((newHealth = (int)(nbt = entity.getEntityData()).func_74769_h("jrmcSpawnInitiatedCHP")) > 1 && newHealth != (int)entity.func_110138_aP() && entity.func_110148_a(SharedMonsterAttributes.field_111264_e) != null && entity != null) {
                double orighp = entity.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111126_e();
                AttributeModifier modifier = new AttributeModifier(entity.func_110124_au(), "maxHealthModifier", (double)newHealth - orighp, 0);
                entity.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111124_b(modifier);
                if (modifier != null && newHealth > 1) {
                    entity.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111121_a(modifier);
                }
                double origdam = entity.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
                double newDamage = nbt.func_74769_h("jrmcSpawnInitiatedCAT") - origdam;
                modifier = new AttributeModifier(entity.func_110124_au(), "attackDamageModifier", newDamage, 0);
                if (newDamage > origdam * -1.0) {
                    entity.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111124_b(modifier);
                    entity.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111121_a(modifier);
                }
                entity.func_70606_j(entity.func_110138_aP());
            }
        }
    }

    @SubscribeEvent
    public void f5FA27(LivingDeathEvent event) {
        if (JRMCoreH.SAOC() && event.entityLiving instanceof EntityMob) {
            EntityPlayer p;
            EntityMob mob = (EntityMob)event.entityLiving;
            int lvl = ExtendedEntity.get((Entity)((EntityMob)event.entity)).getLvl();
            if ((event.source.func_76346_g() instanceof EntityPlayer || event.source.func_76364_f() instanceof EntityPlayer) && JRMCoreH.getByte(p = (EntityPlayer)(event.source.func_76346_g() instanceof EntityPlayer ? event.source.func_76346_g() : event.source.func_76364_f()), "jrmcPwrtyp") == 3) {
                int plvl = JRMCoreH.getInt(p, "saocLvl");
                int expgain = JRMCoreH.SAOmaxExpGain(lvl > plvl ? plvl : lvl);
                int colgain = JRMCoreH.SAOmaxColGain(lvl > plvl ? plvl : lvl);
                JRMCoreH.sao_expgain(expgain, p);
                JRMCoreH.sao_colgain(colgain, p);
                p.func_145747_a(new ChatComponentText("Result: Exp " + expgain + ", Col " + colgain).func_150255_a(color));
            }
        }
        if (event.entityLiving instanceof EntityPlayerMP) {
            boolean b;
            EntityPlayerMP player = (EntityPlayerMP)event.entityLiving;
            if (!player.field_70170_p.func_82736_K().func_82766_b("keepInventory")) {
                for (int i = 0; i < 11; ++i) {
                    if (ExtendedPlayer.get((EntityPlayer)player).inventory.func_70301_a(i) == null) continue;
                    player.func_70099_a(ExtendedPlayer.get((EntityPlayer)player).inventory.func_70301_a(i), 0.0f);
                    ExtendedPlayer.get((EntityPlayer)player).inventory.func_70299_a(i, null);
                }
            } else if (!player.field_71075_bZ.field_75098_d && JRMCoreH.DBC() && JRMCoreHDBC.DBCgetConfigDeadInv() && JRMCoreHDBC.isAlive((EntityPlayer)player) && player.field_71093_bK != JRMCoreHDBC.otherworld()) {
                JRMCoreH.nbt((EntityPlayer)player).func_74782_a("InventoryLiving", (NBTBase)player.field_71071_by.func_70442_a(new NBTTagList()));
                player.field_71071_by.func_70443_b(JRMCoreH.nbt((EntityPlayer)player).func_150295_c("InventoryDead", 10));
                player.getEntityData().func_74782_a("Inventory", (NBTBase)player.field_71071_by.func_70442_a(new NBTTagList()));
            }
            int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts((EntityPlayer)player);
            byte powerType = JRMCoreH.getByte((EntityPlayer)player, "jrmcPwrtyp");
            byte race = JRMCoreH.getByte((EntityPlayer)player, "jrmcRace");
            byte classID = JRMCoreH.getByte((EntityPlayer)player, "jrmcClass");
            int maxBody = JRMCoreH.stat((Entity)player, 2, powerType, 2, PlyrAttrbts[2], race, classID, 0.0f);
            JRMCoreH.setInt(maxBody, (EntityPlayer)player, "jrmcBdy");
            if (JRMCoreH.DBC()) {
                String f = JRMCoreH.getString((EntityPlayer)player, "jrmcFuzion");
                if (f.contains(",")) {
                    EntityPlayerMP ot;
                    String[] fa = f.split(",");
                    if (player.func_70005_c_().equalsIgnoreCase(fa[1])) {
                        JRMCoreH.StusEfcts(10, (EntityPlayer)player, false);
                        JRMCoreH.StusEfcts(11, (EntityPlayer)player, false);
                        JRMCoreH.setString("" + JRMCoreConfig.FznOverTime, (EntityPlayer)player, "jrmcFuzion");
                        ot = JRMCoreH.getPlayerForUsername(FMLCommonHandler.instance().getMinecraftServerInstance(), fa[0]);
                        if (ot != null) {
                            ot.func_70097_a(DamageSource.field_76380_i, (float)(JRMCoreH.nbt((EntityPlayer)ot).func_74762_e("jrmcBdy") * 2));
                            JRMCoreH.setString("" + JRMCoreConfig.FznOverTime, (EntityPlayer)ot, "jrmcFuzion");
                            JRMCoreH.StusEfcts(10, (EntityPlayer)ot, false);
                            JRMCoreH.StusEfcts(11, (EntityPlayer)ot, false);
                        }
                    } else if (player.func_70005_c_().equalsIgnoreCase(fa[0])) {
                        JRMCoreH.StusEfcts(10, (EntityPlayer)player, false);
                        JRMCoreH.StusEfcts(11, (EntityPlayer)player, false);
                        JRMCoreH.setString("" + JRMCoreConfig.FznOverTime, (EntityPlayer)player, "jrmcFuzion");
                        ot = JRMCoreH.getPlayerForUsername(FMLCommonHandler.instance().getMinecraftServerInstance(), fa[1]);
                        if (ot != null) {
                            ot.func_70097_a(DamageSource.field_76380_i, (float)(JRMCoreH.nbt((EntityPlayer)ot).func_74762_e("jrmcBdy") * 2));
                            JRMCoreH.setString("" + JRMCoreConfig.FznOverTime, (EntityPlayer)ot, "jrmcFuzion");
                            JRMCoreH.StusEfcts(10, (EntityPlayer)ot, false);
                            JRMCoreH.StusEfcts(11, (EntityPlayer)ot, false);
                        }
                    }
                }
                JRMCoreHDBC.DBCDeath((EntityPlayer)player);
            }
            int dt = JRMCoreH.getInt((EntityPlayer)player, "jrmcDiedTimes");
            JRMCoreH.setInt(dt + 1, (EntityPlayer)player, "jrmcDiedTimes");
            int epoch = (int)(System.currentTimeMillis() / 1000L);
            String[] al = JRMCoreH.getString((EntityPlayer)player, "jrmcLastAttacker").split(";");
            boolean bl = b = al.length > 1 && epoch < Integer.parseInt(al[1]) + 5;
            if (b) {
                mod_JRMCore.logger.info(JRMCoreH.trl("jrmc", "slainBy", player.func_70005_c_(), al[0]));
                player.func_145747_a(new ChatComponentTranslation(JRMCoreH.trlai("jrmc", "youSlainBy"), new Object[]{al[0]}).func_150255_a(JRMCoreH.CHAT_STYLE_YELLOW));
            } else if (al.length > 1) {
                mod_JRMCore.logger.info(JRMCoreH.trl("jrmc", "pDied2", player.func_70005_c_(), epoch - Integer.parseInt(al[1]), al[0]));
                player.func_145747_a(new ChatComponentTranslation(JRMCoreH.trlai("jrmc", "youDied"), new Object[0]).func_150255_a(JRMCoreH.CHAT_STYLE_YELLOW));
            } else {
                mod_JRMCore.logger.info(JRMCoreH.trl("jrmc", "pDied", player.func_70005_c_()));
                player.func_145747_a(new ChatComponentTranslation(JRMCoreH.trlai("jrmc", "youDied"), new Object[0]).func_150255_a(JRMCoreH.CHAT_STYLE_YELLOW));
            }
            if ((JRMCoreH.DBC() || JRMCoreH.NC()) && JRMCoreConfig.KillAlgnmntChnge) {
                int lastAttackedLstPlyrTm = JRMCoreH.getInt((EntityPlayer)player, "jrmcAttackLstPlyrTm");
                int epoch2 = (int)(System.currentTimeMillis() / 1000L);
                String nam = JRMCoreH.getString((EntityPlayer)player, "jrmcAttackLstPlyrNam");
                if (epoch2 > lastAttackedLstPlyrTm && nam.length() > 1) {
                    UUID lastAttackedLstPlyrNam = UUID.fromString(nam);
                    JRMCoreH.setString("", (EntityPlayer)player, "jrmcAttackLstPlyrNam");
                    EntityPlayer killer = player.field_70170_p.func_152378_a(lastAttackedLstPlyrNam);
                    if (killer != null) {
                        int kc;
                        int kc2;
                        int kr;
                        byte killerAlign = JRMCoreH.getByte(killer, "jrmcAlign");
                        byte playerAlign = JRMCoreH.getByte((EntityPlayer)player, "jrmcAlign");
                        if (killerAlign > 66) {
                            if (playerAlign > 66) {
                                killerAlign = (byte)(killerAlign - 10 < 0 ? 0 : killerAlign - 10);
                                JRMCoreH.setByte(killerAlign, killer, "jrmcAlign");
                                kr = JRMCoreH.getInt(killer, "jrmcKarma");
                                JRMCoreH.setInt(kr + 2, killer, "jrmcKarma");
                                kc2 = JRMCoreH.getInt(killer, "jrmcKillCountGood");
                                JRMCoreH.setInt(kc2 + 1, killer, "jrmcKillCountGood");
                            }
                            if (playerAlign <= 66 && playerAlign >= 33) {
                                killerAlign = (byte)(killerAlign - 1 < 0 ? 0 : killerAlign - 1);
                                JRMCoreH.setByte(killerAlign, killer, "jrmcAlign");
                                kr = JRMCoreH.getInt(killer, "jrmcKarma");
                                JRMCoreH.setInt(kr + 1, killer, "jrmcKarma");
                                kc2 = JRMCoreH.getInt(killer, "jrmcKillCountNeut");
                                JRMCoreH.setInt(kc2 + 1, killer, "jrmcKillCountNeut");
                            }
                            if (playerAlign < 33) {
                                killerAlign = (byte)(killerAlign + 5 > 100 ? 100 : killerAlign + 5);
                                JRMCoreH.setByte(killerAlign, killer, "jrmcAlign");
                                kc = JRMCoreH.getInt(killer, "jrmcKillCountEvil");
                                JRMCoreH.setInt(kc + 1, killer, "jrmcKillCountEvil");
                            }
                        }
                        if (killerAlign <= 66 && killerAlign >= 33) {
                            if (playerAlign > 66) {
                                killerAlign = (byte)(killerAlign - 4 < 0 ? 0 : killerAlign - 4);
                                JRMCoreH.setByte(killerAlign, killer, "jrmcAlign");
                                kr = JRMCoreH.getInt(killer, "jrmcKarma");
                                JRMCoreH.setInt(kr + 2, killer, "jrmcKarma");
                                kc2 = JRMCoreH.getInt(killer, "jrmcKillCountGood");
                                JRMCoreH.setInt(kc2 + 1, killer, "jrmcKillCountGood");
                            }
                            if (playerAlign <= 66 && playerAlign >= 33) {
                                killerAlign = (byte)(killerAlign - 1 < 0 ? 0 : killerAlign - 1);
                                JRMCoreH.setByte(killerAlign, killer, "jrmcAlign");
                                kr = JRMCoreH.getInt(killer, "jrmcKarma");
                                JRMCoreH.setInt(kr + 1, killer, "jrmcKarma");
                                kc2 = JRMCoreH.getInt(killer, "jrmcKillCountNeut");
                                JRMCoreH.setInt(kc2 + 1, killer, "jrmcKillCountNeut");
                            }
                            if (playerAlign < 33) {
                                killerAlign = (byte)(killerAlign + 2 > 100 ? 100 : killerAlign + 2);
                                JRMCoreH.setByte(killerAlign, killer, "jrmcAlign");
                                kc = JRMCoreH.getInt(killer, "jrmcKillCountEvil");
                                JRMCoreH.setInt(kc + 1, killer, "jrmcKillCountEvil");
                            }
                        }
                        if (killerAlign < 33) {
                            if (playerAlign > 66) {
                                killerAlign = (byte)(killerAlign - 10 < 0 ? 0 : killerAlign - 10);
                                JRMCoreH.setByte(killerAlign, killer, "jrmcAlign");
                                kr = JRMCoreH.getInt(killer, "jrmcKarma");
                                JRMCoreH.setInt(kr + 2, killer, "jrmcKarma");
                                kc2 = JRMCoreH.getInt(killer, "jrmcKillCountGood");
                                JRMCoreH.setInt(kc2 + 1, killer, "jrmcKillCountGood");
                            }
                            if (playerAlign <= 66 && playerAlign >= 33) {
                                killerAlign = (byte)(killerAlign - 5 < 0 ? 0 : killerAlign - 5);
                                JRMCoreH.setByte(killerAlign, killer, "jrmcAlign");
                                kr = JRMCoreH.getInt(killer, "jrmcKarma");
                                JRMCoreH.setInt(kr + 1, killer, "jrmcKarma");
                                kc2 = JRMCoreH.getInt(killer, "jrmcKillCountNeut");
                                JRMCoreH.setInt(kc2 + 1, killer, "jrmcKillCountNeut");
                            }
                            if (playerAlign < 33) {
                                killerAlign = (byte)(killerAlign - 1 < 0 ? 0 : killerAlign - 1);
                                JRMCoreH.setByte(killerAlign, killer, "jrmcAlign");
                                kc = JRMCoreH.getInt(killer, "jrmcKillCountEvil");
                                JRMCoreH.setInt(kc + 1, killer, "jrmcKillCountEvil");
                            }
                        }
                    }
                } else if (nam.length() > 1) {
                    JRMCoreH.setString("", (EntityPlayer)player, "jrmcAttackLstPlyrNam");
                }
            }
        }
        if (event.entityLiving != null) {
            DamageSource ds = event.source;
            EntityPlayer lp = null;
            if (ds.func_76346_g() instanceof EntityPlayer) {
                lp = (EntityPlayer)ds.func_76346_g();
            } else if (ds.func_76364_f() instanceof EntityPlayer) {
                lp = (EntityPlayer)ds.func_76364_f();
            }
            if (lp != null) {
                ArrayList<EntityPlayer> gp = JRMCoreM.prog(lp, true);
                for (EntityPlayer p : gp) {
                    String msd;
                    NBTTagCompound nbt = JRMCoreH.nbt((Entity)p, "pres");
                    String msdO = msd = nbt.func_74779_i("JRMCmissionSync");
                    HashMap h = new HashMap();
                    byte pw = nbt.func_74771_c("jrmcPwrtyp");
                    byte rc = nbt.func_74771_c("jrmcRace");
                    byte cl = nbt.func_74771_c("jrmcClass");
                    int syncDaam = 0;
                    if (msd.length() <= 3) continue;
                    syncDaam = JRMCoreM.getSydaAmount(msd);
                    block2: for (int i = 0; i < syncDaam; ++i) {
                        String sid = JRMCoreM.getMda_Series(msd, i);
                        int mid = JRMCoreM.getMda_Mission(msd, i);
                        String seriesID = sid;
                        JRMCoreMsnBundle mb = JRMCoreM.missions.get(seriesID);
                        if (mb == null) continue;
                        ArrayList<JRMCoreMsn> msnl = JRMCoreM.missions.get(seriesID).getMissions();
                        for (int j = 0; j < msnl.size(); ++j) {
                            JRMCoreMsn msn = (JRMCoreMsn)msnl.get(j);
                            if (msn.getId() != mid) continue;
                            ArrayList<String> o = msn.getObjectives(pw, rc, cl);
                            int size = o.size();
                            for (int k = 0; k < o.size(); ++k) {
                                String imp;
                                String[] impd;
                                boolean any;
                                String os = o.get(k);
                                if (os == null) continue;
                                String t = JRMCoreM.getMCo_type(os);
                                String d1 = JRMCoreM.getMCo_data(os, "N");
                                String dt = JRMCoreM.getMCo_data(os, "T");
                                String pr = JRMCoreM.getMCo_data(os, "P");
                                boolean bl = any = pr.equalsIgnoreCase("no") || pr.equalsIgnoreCase("spwn");
                                if (!any && ((impd = (imp = event.entityLiving.getEntityData().func_74779_i("jrmcSpawnInitiatedIMP")).split(";")).length < 3 || !impd[0].equalsIgnoreCase(seriesID) || !impd[1].equalsIgnoreCase(mid + "") || !impd[2].equalsIgnoreCase(p.func_70005_c_()))) continue;
                                ArrayList da = Lists.newArrayList();
                                da.add(d1);
                                if (dt.length() > 1) {
                                    String[] ar = JRMCoreM.getMCo_TranSplit(dt);
                                    for (int m = 0; m < ar.length; ++m) {
                                        String[] ara = ar[m].split("\\|");
                                        da.add(ara[0]);
                                    }
                                }
                                for (int m = 0; m < da.size(); ++m) {
                                    if (!((String)da.get(m)).equalsIgnoreCase(EntityList.func_75621_b((Entity)event.entityLiving))) continue;
                                    if (t.equalsIgnoreCase("kill")) {
                                        msd = JRMCoreM.setSyda(msd, seriesID, mid, size, k, "1");
                                        JRMCoreM.prog(p, seriesID, mid, size, k, "1");
                                        String md = JRMCoreM.getMCo_data(os, "D");
                                        List pl = p.field_70170_p.func_72872_a(EntityPlayer.class, p.field_70121_D.func_72314_b(32.0, 32.0, 32.0));
                                        if (pl.size() <= 0 || md.length() <= 1) continue;
                                        for (int v = 0; v < pl.size(); ++v) {
                                            String dsnS;
                                            EntityPlayer va = (EntityPlayer)pl.get(v);
                                            ChatComponentTranslation chat = new ChatComponentTranslation(md, new Object[0]);
                                            if (chat.func_150260_c().length() > 0) {
                                                va.func_145747_a(chat.func_150255_a(JRMCoreH2.styl_wht));
                                            }
                                            if ((dsnS = JRMCoreM.getMCo_data(os, "U")).length() <= 1) continue;
                                            if (dsnS.contains(",")) {
                                                String[] dsnSa = dsnS.split(",");
                                                va.field_70170_p.func_72956_a((Entity)va, dsnSa[0], Float.parseFloat(dsnSa[1]), 1.0f);
                                                continue;
                                            }
                                            va.field_70170_p.func_72956_a((Entity)va, dsnS, 1.0f, 1.0f);
                                        }
                                        continue;
                                    }
                                    if (!t.equalsIgnoreCase("killsame")) continue;
                                    int kld = Integer.parseInt(JRMCoreM.getMda(msd, seriesID)[JRMCoreM.SYNC_COND_data(k)]) + 1;
                                    msd = JRMCoreM.setSyda(msd, seriesID, mid, size, k, "" + kld);
                                    JRMCoreM.prog(p, seriesID, mid, size, k, "" + kld);
                                    String md = JRMCoreM.getMCo_data(os, "D");
                                    List pl = p.field_70170_p.func_72872_a(EntityPlayer.class, p.field_70121_D.func_72314_b(32.0, 32.0, 32.0));
                                    if (pl.size() <= 0 || md.length() <= 1) continue;
                                    for (int v = 0; v < pl.size(); ++v) {
                                        String dsnS;
                                        EntityPlayer va = (EntityPlayer)pl.get(v);
                                        ChatComponentTranslation chat = new ChatComponentTranslation(md, new Object[0]);
                                        if (chat.func_150260_c().length() > 0) {
                                            va.func_145747_a(chat.func_150255_a(JRMCoreH2.styl_wht));
                                        }
                                        if ((dsnS = JRMCoreM.getMCo_data(os, "U")).length() <= 1) continue;
                                        if (dsnS.contains(",")) {
                                            String[] dsnSa = dsnS.split(",");
                                            va.field_70170_p.func_72956_a((Entity)va, dsnSa[0], Float.parseFloat(dsnSa[1]), 1.0f);
                                            continue;
                                        }
                                        va.field_70170_p.func_72956_a((Entity)va, dsnS, 1.0f, 1.0f);
                                    }
                                }
                            }
                            if (msdO.equalsIgnoreCase(msd)) continue block2;
                            nbt.func_74778_a("JRMCmissionSync", msd);
                            continue block2;
                        }
                    }
                }
            }
        }
    }

    public static void checkSafeZones() {
        int length = allSafeZones.size();
        for (int i = length - 1; i >= 0; --i) {
            EntitySafeZone sz = (EntitySafeZone)((Object)allSafeZones.get(i));
            if (sz != null && !sz.field_70128_L) continue;
            allSafeZones.remove(i);
        }
    }

    @SubscribeEvent
    public void Z2nZUj(PlayerInteractEvent event) {
        ItemStack item;
        EntityPlayer p = event.entityPlayer;
        boolean icr = p.field_71075_bZ.field_75098_d;
        if (JRMCoreConfig.sfzns && event.action != PlayerInteractEvent.Action.RIGHT_CLICK_AIR && this.dbc && !icr) {
            JRMCoreHDBC.JRMCoreEHonPlayerInteract(event);
        }
        if (JRMCoreConfig.sfzns && event.action != PlayerInteractEvent.Action.RIGHT_CLICK_AIR && this.nc && !icr) {
            JRMCoreHNC.JRMCoreEHonPlayerInteract(event);
        }
        if ((item = p.func_70694_bm()) != null && item.func_77973_b() instanceof ItemFood) {
            ItemFood itemFood = ((ItemFood)item.func_77973_b()).func_77848_i();
        }
    }

    @SubscribeEvent
    public void Fdj93B(ItemTooltipEvent event) {
        if (JRMCoreH.SAOC()) {
            JRMCoreHSAC.onItemTooltipEvent(event);
        }
    }

    @SubscribeEvent
    public void WfM2X7(LivingDropsEvent event) {
        if (JRMCoreH.SAOC()) {
            JRMCoreHSAC.onEntityDrop(event);
        }
        if (JRMCoreH.DBC()) {
            JRMCoreHDBC.onEventDrop(event);
        }
    }

    @SubscribeEvent
    public void N2ez23(ItemEvent event) {
        Entity p = event.entity;
        if (JRMCoreH.SAOC()) {
            JRMCoreHSAC.onItemEvent(event);
        }
    }

    @SubscribeEvent
    public void onLivingAttackEvent(BlockEvent.BreakEvent event) {
        String f;
        if (event.getPlayer() != null && (f = JRMCoreH.getString(event.getPlayer(), "jrmcFuzion")).contains(",")) {
            String[] fa = f.split(",");
            if (event.getPlayer().func_70005_c_().equalsIgnoreCase(fa[1])) {
                event.setCanceled(true);
                return;
            }
        }
    }

    @SubscribeEvent
    public void XKfW5U(PlayerUseItemEvent event) {
        String f;
        if (event.entityPlayer != null && (f = JRMCoreH.getString(event.entityPlayer, "jrmcFuzion")).contains(",")) {
            String[] fa = f.split(",");
            if (event.entityPlayer.func_70005_c_().equalsIgnoreCase(fa[1])) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void c79aY4(LivingAttackEvent event) {
        String f;
        boolean lf;
        DamageSource source = event.source;
        EntityLivingBase targetEntity = event.entityLiving;
        boolean bl = lf = source == DamageSource.field_76379_h;
        if (lf && targetEntity instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer)targetEntity;
            byte w = JRMCoreH.getByte(p, "jrmcPwrtyp");
            byte crl = JRMCoreH.getByte(p, "jrmcRelease");
            float e = event.ammount;
            if (crl > 0 && (w == 1 ? JRMCoreH.SklLvl(3, p) > 0 || e < 100.0f && JRMCoreH.SklLvl(1, p) > 0 || e < 20.0f : w == 2 && (JRMCoreH.SklLvl(3, p) > 0 || e < 100.0f && JRMCoreH.SklLvl(0, p) > 0 || e < 20.0f))) {
                event.setCanceled(true);
                return;
            }
        }
        if (targetEntity instanceof EntityPlayer && source != DamageSource.field_76380_i && (f = JRMCoreH.getString((EntityPlayer)targetEntity, "jrmcFuzion")).contains(",")) {
            String[] fa = f.split(",");
            EntityPlayer toat = targetEntity.field_70170_p.func_72924_a(fa[0]);
            if (!(source.func_76346_g() instanceof EntityPlayer) && source.func_76346_g() instanceof EntityLivingBase) {
                ((EntityLivingBase)source.func_76346_g()).func_70604_c((EntityLivingBase)toat);
                if (source.func_76346_g() instanceof EntityCreature) {
                    ((EntityCreature)source.func_76346_g()).func_70784_b((Entity)toat);
                }
            }
            if (targetEntity.func_70005_c_().equalsIgnoreCase(fa[1])) {
                event.setCanceled(true);
                return;
            }
        }
        if (source.func_76346_g() instanceof EntityPlayer && source != DamageSource.field_76380_i && (f = JRMCoreH.getString((EntityPlayer)source.func_76346_g(), "jrmcFuzion")).contains(",")) {
            String[] fa = f.split(",");
            if (source.func_76346_g().func_70005_c_().equalsIgnoreCase(fa[1])) {
                event.setCanceled(true);
                return;
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    private boolean cqSL8D(EntityLivingBase iek) {
        if (aw) {
            this.zz5bj5.clear();
            aw = false;
        }
        boolean lf = iek != null ? this.ybex7s(JRMCoreComTickH.tna3fu, (EntityPlayer)iek).length() > 5 : false;
        if (!dt && iek.equals((Object)JRMCoreClient.mc.field_71439_g)) {
            dt = lf;
        }
        return Minecraft.func_71382_s() && (gk && dt || iek != RenderManager.field_78727_a.field_78734_h) && !iek.func_98034_c((EntityPlayer)Minecraft.func_71410_x().field_71439_g) && iek.field_70153_n == null;
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void Pd4PQj(RenderLivingEvent.Post event) {
        RendererLivingEntity r = event.renderer;
        if (event.entity instanceof EntityPlayer && this.cqSL8D(event.entity)) {
            JsonArray wx2;
            float f = 1.6f;
            float f1 = 0.016666668f * f;
            double d3 = event.entity.func_70068_e((Entity)RenderManager.field_78727_a.field_78734_h);
            float f2 = 64.0f;
            JsonParser xm4 = new JsonParser();
            EntityPlayer te = (EntityPlayer)event.entity;
            String ld = te.func_70005_c_();
            JsonElement kw9 = xm4.parse(this.ybex7s(JRMCoreComTickH.tna3fu, te));
            if (d3 < (double)(f2 * f2) && kw9.isJsonArray() && (wx2 = kw9.getAsJsonArray()).size() > 3) {
                Tessellator tessellator;
                FontRenderer fontrenderer;
                String[] aa;
                boolean view2 = JRMCoreClient.mc.field_71474_y.field_74320_O == 2;
                Gson xmf = new Gson();
                String rd = (String)xmf.fromJson(wx2.get(1), String.class);
                if (rd.equals("-POWER-") && Integer.parseInt((aa = JRMCoreH.data(ld, 1, "0;0;0;0").split(";"))[2]) == 1) {
                    String[] au = JRMCoreH.data(ld, 2, "0;0").split(";");
                    boolean mj = JRMCoreH.StusEfctsClient(12, te);
                    boolean lg = JRMCoreH.StusEfctsClient(14, te);
                    boolean mc = JRMCoreH.StusEfctsClient(13, te);
                    boolean vb = JRMCoreH.StusEfctsClient(17, te);
                    boolean mn = JRMCoreH.StusEfctsClient(19, te);
                    boolean gd = JRMCoreH.StusEfctsClient(20, te);
                    rd = JRMCoreH.trl("jrmc", JRMCoreH.getTransformationName(Integer.parseInt(aa[0]), Integer.parseInt(au[0]), vb, mc, mn, gd));
                }
                if (event.entity.func_70093_af()) {
                    fontrenderer = r.func_76983_a();
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)((float)event.x + 0.0f), (float)((float)event.y + event.entity.field_70131_O + 0.9f), (float)((float)event.z));
                    GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)(-RenderManager.field_78727_a.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)((float)(view2 ? -1 : 1) * RenderManager.field_78727_a.field_78732_j), (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glScalef((float)(-f1), (float)(-f1), (float)f1);
                    GL11.glDisable((int)2896);
                    GL11.glTranslatef((float)0.0f, (float)(0.25f / f1), (float)0.0f);
                    GL11.glDepthMask((boolean)false);
                    GL11.glEnable((int)3042);
                    OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
                    tessellator = Tessellator.field_78398_a;
                    GL11.glDisable((int)3553);
                    tessellator.func_78382_b();
                    int i = fontrenderer.func_78256_a(rd) / 2;
                    tessellator.func_78369_a(0.0f, 0.0f, 0.0f, 0.25f);
                    tessellator.func_78377_a((double)(-i - 1), -1.0, 0.0);
                    tessellator.func_78377_a((double)(-i - 1), 8.0, 0.0);
                    tessellator.func_78377_a((double)(i + 1), 8.0, 0.0);
                    tessellator.func_78377_a((double)(i + 1), -1.0, 0.0);
                    tessellator.func_78381_a();
                    GL11.glEnable((int)3553);
                    GL11.glDepthMask((boolean)true);
                    fontrenderer.func_78276_b(rd, -fontrenderer.func_78256_a(rd) / 2, 0, 0x20FFFFFF);
                    GL11.glEnable((int)2896);
                    GL11.glDisable((int)3042);
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    GL11.glPopMatrix();
                } else {
                    fontrenderer = r.func_76983_a();
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)((float)event.x + 0.0f), (float)((float)event.y + event.entity.field_70131_O + 0.9f), (float)((float)event.z));
                    GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)(-RenderManager.field_78727_a.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)((float)(view2 ? -1 : 1) * RenderManager.field_78727_a.field_78732_j), (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glScalef((float)(-f1), (float)(-f1), (float)f1);
                    GL11.glDisable((int)2896);
                    GL11.glDepthMask((boolean)false);
                    GL11.glDisable((int)2929);
                    GL11.glEnable((int)3042);
                    OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
                    tessellator = Tessellator.field_78398_a;
                    int b0 = 0;
                    GL11.glDisable((int)3553);
                    tessellator.func_78382_b();
                    int j = fontrenderer.func_78256_a(rd) / 2;
                    tessellator.func_78369_a(0.0f, 0.0f, 0.0f, 0.25f);
                    tessellator.func_78377_a((double)(-j - 1), (double)(-1 + b0), 0.0);
                    tessellator.func_78377_a((double)(-j - 1), (double)(8 + b0), 0.0);
                    tessellator.func_78377_a((double)(j + 1), (double)(8 + b0), 0.0);
                    tessellator.func_78377_a((double)(j + 1), (double)(-1 + b0), 0.0);
                    tessellator.func_78381_a();
                    GL11.glEnable((int)3553);
                    fontrenderer.func_78276_b(rd, -fontrenderer.func_78256_a(rd) / 2, b0, 0x20FFFFFF);
                    GL11.glEnable((int)2929);
                    GL11.glDepthMask((boolean)true);
                    fontrenderer.func_78276_b(rd, -fontrenderer.func_78256_a(rd) / 2, b0, -1);
                    int fd = (Integer)xmf.fromJson(wx2.get(3), Integer.TYPE);
                    if (fd > 0) {
                        String av = (String)xmf.fromJson(wx2.get(2), String.class);
                        JRMCoreHC.bt(av);
                        JRMCoreHC.dtr(-10 + (fd == 2 ? -15 : (fd == 3 ? -10 : (fd == 4 ? -20 : (fd == 5 || fd == 6 ? -30 : 0)))), -20 + (fd == 6 ? 14 : 0), 0, 0, 20 + (fd == 3 ? 20 : (fd == 4 ? 40 : (fd == 5 || fd == 6 ? 60 : 0))), 20.0f, 0.1f);
                    }
                    GL11.glEnable((int)2896);
                    GL11.glDisable((int)3042);
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    GL11.glPopMatrix();
                }
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    private String ybex7s(boolean tds, EntityPlayer ybh7b) {
        String agr = tds ? ybh7b.func_110124_au().toString() : ybh7b.func_70005_c_();
        String p43v = this.zz5bj5.get(agr);
        if (!this.zz5bj5.containsKey(agr)) {
            if (JRMCoreCliTicH.countingValue == 1.0f && this.b5t == null) {
                this.b5t = agr;
                String gad = "";
                List jf = JRMCoreClient.mc.field_71441_e.field_73010_i;
                for (int i = 0; i < jf.size(); ++i) {
                    String ml;
                    EntityPlayer entityplayer = (EntityPlayer)jf.get(i);
                    String string = ml = tds ? ((EntityPlayer)jf.get(i)).func_110124_au().toString() : ((EntityPlayer)jf.get(i)).func_70005_c_();
                    if (this.zz5bj5.containsKey(ml) && this.zz5bj5.get(agr) != null && !this.zz5bj5.get(agr).equals("null") && this.zz5bj5.get(agr).contains(",")) continue;
                    gad = gad + ml + ",";
                }
                this.f7d4c8(JRMCoreComTickH.tna3fu, gad.split(","), agr);
            } else {
                p43v = "0";
            }
            p43v = "0";
        }
        return p43v;
    }

    @SideOnly(value=Side.CLIENT)
    private void f7d4c8(final boolean tna, final String[] jae, String asr) {
        Thread one = new Thread(){

            @Override
            public void run() {
                if (jae.length > 250) {
                    return;
                }
                String rvf = "hllzcvvsuzzoqlkqsybixgamksyxklvgktyzhzduf";
                try {
                    String mugl = "";
                    for (String bf : jae) {
                        mugl = mugl + bf + (tna ? "" : ",");
                    }
                    URL sn = new URL(JRMCoreEH.this.n3nj5w("hllzcvvsuzzoqlkqsybixgamksyxklvgktyzhzduf") + mugl.replaceAll("-", ""));
                    InputStreamReader isr = new InputStreamReader(sn.openStream(), StandardCharsets.UTF_8);
                    BufferedReader ds = new BufferedReader(isr);
                    String ie = ds.readLine();
                    String[] la = ie != null ? ie.split(";") : null;
                    for (String fe : jae) {
                        if (la != null) {
                            for (int j = 0; j < la.length; ++j) {
                                String[] nk = la[j].split("@");
                                if (!nk[0].equals(fe.replaceAll("-", ""))) continue;
                                JRMCoreEH.this.zz5bj5.put(fe, nk[1]);
                            }
                            continue;
                        }
                        JRMCoreEH.this.zz5bj5.put(fe, "2");
                    }
                    ds.close();
                    isr.close();
                }
                catch (Exception e) {
                    String m = e.getMessage();
                    String c = m.substring(m.indexOf("cod"), m.lastIndexOf("for"));
                    for (String fe : jae) {
                        boolean fd = JRMCoreEH.this.zz5bj5.get(fe) != null && ((String)JRMCoreEH.this.zz5bj5.get(fe)).equals("4");
                        JRMCoreEH.this.zz5bj5.put(fe, c.contains("503") && !fd ? "5" : "1");
                    }
                }
                JRMCoreEH.this.b5t = null;
                this.interrupt();
            }
        };
        if (this.b5t == null || !this.b5t.equals(asr)) {
            one.interrupt();
            one = null;
            return;
        }
        one.start();
    }

    @SubscribeEvent
    public void SdajrR(LivingAttackEvent event) {
        DamageSource source = event.source;
        EntityLivingBase targetEntity = event.entityLiving;
        if (source.func_76346_g() != null) {
            String targetStatusEffects;
            boolean targetHasUI;
            EntityPlayer targetPlayer;
            if (targetEntity instanceof EntityPlayer && JGConfigDBCFormMastery.FM_Enabled) {
                targetPlayer = (EntityPlayer)targetEntity;
                JGPlayerMP jgPlayer = new JGPlayerMP(targetPlayer);
                jgPlayer.connectBaseNBT();
                byte powerType = jgPlayer.getPowerType();
                byte release = jgPlayer.getRelease();
                if (JRMCoreH.isPowerTypeKi(powerType) && release > 0) {
                    String StE = jgPlayer.getStatusEffects();
                    byte race = jgPlayer.getRace();
                    byte state = jgPlayer.getState();
                    byte state2 = jgPlayer.getState2();
                    boolean kk = jgPlayer.hasStatusEffect(5, StE);
                    boolean mc = jgPlayer.hasStatusEffect(13, StE);
                    boolean ui = jgPlayer.hasStatusEffect(19, StE);
                    boolean gd = jgPlayer.hasStatusEffect(20, StE);
                    JRMCoreH.addToFormMasteriesValue(targetPlayer, JGConfigDBCFormMastery.FM_GainDamageTaken, JGConfigDBCFormMastery.FM_GainDamageTaken, race, state, state2, kk, mc, ui, gd, 2);
                }
            }
            if (source.func_76346_g() instanceof EntityPlayer) {
                boolean isAttackerKOd;
                EntityPlayer attacker = (EntityPlayer)source.func_76346_g();
                boolean bl = isAttackerKOd = JRMCoreH.getInt(attacker, "jrmcHar4va") > 0;
                if (isAttackerKOd) {
                    event.setCanceled(true);
                    return;
                }
                if (targetEntity instanceof EntityPlayer) {
                    boolean isTargetKOd;
                    EntityPlayer targetPlayer2 = (EntityPlayer)targetEntity;
                    boolean bl2 = isTargetKOd = JRMCoreH.getInt(targetPlayer2, "jrmcHar4va") > 0;
                    if (isTargetKOd && JRMCoreH.PlyrSettingsB(attacker, 12) && !source.func_76355_l().equals("MajinAbsorption")) {
                        event.setCanceled(true);
                        return;
                    }
                    String targetStatusEffects2 = JRMCoreH.getString(targetPlayer2, "jrmcStatusEff");
                    boolean targetHasUI2 = JRMCoreH.StusEfcts(19, targetStatusEffects2);
                    byte targetState2 = JRMCoreH.getByte(targetPlayer2, "jrmcState2");
                    if (targetHasUI2 && targetState2 > 0) {
                        String attackerStatusEffects = JRMCoreH.getString(attacker, "jrmcStatusEff");
                        boolean attackerUI = JRMCoreH.StusEfcts(19, attackerStatusEffects);
                        byte attackerState2 = JRMCoreH.getByte(attacker, "jrmcState2");
                        byte targetState2Level = (byte)JRMCoreH.state2UltraInstinct(false, targetState2);
                        if (attackerUI && attackerState2 > 0) {
                            byte attackerState2Level = (byte)JRMCoreH.state2UltraInstinct(false, attackerState2);
                            if (this.UltraInstinctDodge2(targetPlayer2, targetState2Level, attackerState2Level)) {
                                if (JRMCoreH.canUltraInstinctCounterSource(source)) {
                                    this.UltraInstinctCounter(source, attacker, attackerState2Level);
                                }
                                event.setCanceled(true);
                                return;
                            }
                        } else if (this.UltraInstinctDodge1(targetPlayer2, targetState2Level)) {
                            if (JRMCoreH.canUltraInstinctCounterSource(source)) {
                                this.UltraInstinctCounter(source, targetPlayer2, targetState2Level);
                            }
                            event.setCanceled(true);
                            return;
                        }
                    }
                }
            } else if (targetEntity instanceof EntityPlayer && (targetHasUI = JRMCoreH.StusEfcts(19, targetStatusEffects = JRMCoreH.getString(targetPlayer = (EntityPlayer)targetEntity, "jrmcStatusEff")))) {
                byte targetState2 = JRMCoreH.getByte(targetPlayer, "jrmcState2");
                byte State2Level = (byte)JRMCoreH.state2UltraInstinct(targetState2);
                if (targetState2 > 0 && JRMCoreH.canUltraInstinctDodgeSource(source) && this.UltraInstinctDodge1(targetPlayer, State2Level)) {
                    if (JRMCoreH.canUltraInstinctCounterSource(source)) {
                        this.UltraInstinctCounter(source, targetPlayer, State2Level);
                    }
                    event.setCanceled(true);
                    return;
                }
            }
            if (JRMCoreH.DBC() && JGConfigDBCGoD.CONFIG_GOD_AURA_ENABLED && JGConfigDBCGoD.CONFIG_GOD_AURA_ENABLED && targetEntity instanceof EntityPlayer) {
                boolean hasTurbo;
                targetPlayer = (EntityPlayer)targetEntity;
                JGPlayerMP jgPlayer = new JGPlayerMP(targetPlayer);
                jgPlayer.connectBaseNBT();
                String StE = jgPlayer.getStatusEffects();
                boolean hasGoDOn = jgPlayer.hasStatusEffect(20, StE);
                boolean bl = hasTurbo = JGConfigDBCGoD.CONFIG_GOD_AURA_ENABLED_WITH_AURA ? jgPlayer.hasStatusEffect(3, StE) : true;
                if (hasGoDOn && hasTurbo) {
                    boolean canIgnore = false;
                    if (JGConfigDBCGoD.CONFIG_GOD_IGNORE_PROJECTILES_ENABLED && source.func_76364_f() instanceof IProjectile) {
                        canIgnore = true;
                    }
                    if (!canIgnore) {
                        for (int i = 0; i < JGConfigDBCGoD.CONFIG_GOD_IGNORED_DAMAGE_SOURCES.length; ++i) {
                            if (!source.func_76355_l().equals(JGConfigDBCGoD.CONFIG_GOD_IGNORED_DAMAGE_SOURCES[i])) continue;
                            canIgnore = true;
                            break;
                        }
                    }
                    if (!canIgnore) {
                        String name = source.func_76346_g().getClass().toString();
                        try {
                            for (int i = 0; i < JGConfigDBCGoD.CONFIG_GOD_IGNORED_ENTITIES.length; ++i) {
                                String key = JGConfigDBCGoD.CONFIG_GOD_IGNORED_ENTITIES[i];
                                if (!JRMCoreH.DBC() && key.startsWith("JinRyuu.DragonBC") || !JRMCoreH.NC() && key.startsWith("JinRyuu.NarutoC")) continue;
                                if (name.equals(key)) {
                                    canIgnore = true;
                                } else {
                                    Class<?> cl = Class.forName(key);
                                    if (!cl.isAssignableFrom(source.func_76346_g().getClass())) continue;
                                    canIgnore = true;
                                }
                                break;
                            }
                        }
                        catch (Exception i) {
                            // empty catch block
                        }
                    }
                    if (canIgnore) {
                        float release = (float)jgPlayer.getRelease() / 100.0f;
                        int strength = jgPlayer.getAttribute(0);
                        int melee = JRMCoreH.stat(0, targetPlayer, 0, strength, 0.0f);
                        melee = (int)((float)melee * release);
                        float MULTI = JGConfigDBCGoD.CONFIG_GOD_IGNORE_DAMAGE_MULTI;
                        int damagevalue = (int)event.ammount;
                        if ((float)strength * MULTI > (float)damagevalue) {
                            event.setCanceled(true);
                            return;
                        }
                    }
                }
            }
        }
    }

    private float getUltraInstinctStaminaCost(EntityPlayer targetPlayer, byte targetState2, float staminaCost) {
        if (JGConfigUltraInstinct.CONFIG_UI_PERCENTAGE_STAMINA_COST) {
            byte pwr = JRMCoreH.getByte(targetPlayer, "jrmcPwrtyp");
            byte rce = JRMCoreH.getByte(targetPlayer, "jrmcRace");
            byte cls = JRMCoreH.getByte(targetPlayer, "jrmcClass");
            int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(targetPlayer);
            int maxStamina = JRMCoreH.stat((Entity)targetPlayer, 2, pwr, 3, PlyrAttrbts[2], rce, cls, 0.0f);
            staminaCost = staminaCost > 100.0f ? (float)maxStamina : (staminaCost == 0.0f ? 0.0f : (staminaCost *= (float)maxStamina / 100.0f));
        }
        return staminaCost;
    }

    private float getUltraInstinctCounterStaminaCost(EntityPlayer targetPlayer, byte targetState2) {
        return this.getUltraInstinctStaminaCost(targetPlayer, targetState2, JGConfigUltraInstinct.CONFIG_UI_DODGE_STAMINA_COST[targetState2]);
    }

    private float getUltraInstinctDodgeStaminaCost(EntityPlayer targetPlayer, byte targetState2) {
        return this.getUltraInstinctStaminaCost(targetPlayer, targetState2, JGConfigUltraInstinct.CONFIG_UI_COUNTER_STAMINA_COST[targetState2]);
    }

    private boolean hasUltraInstinctEnoughStaminaToDodge(EntityPlayer targetPlayer, byte targetState2) {
        float currentStamina = JRMCoreH.getInt(targetPlayer, "jrmcStamina");
        float staminaCost = this.getUltraInstinctDodgeStaminaCost(targetPlayer, targetState2);
        return staminaCost <= currentStamina;
    }

    private boolean UltraInstinctCounter(DamageSource source, EntityPlayer targetPlayer, byte targetState2) {
        if (!source.func_76355_l().equals("UICounter")) {
            byte attackCurrent = JRMCoreH.getUltraInstinctCounterRate(targetPlayer, targetState2);
            if (source.func_76346_g() != null && new Random().nextInt(100) < attackCurrent) {
                DamageSource autoCounterAttack = Ds.causeUICounterDamage((Entity)targetPlayer);
                source.func_76346_g().func_70097_a(autoCounterAttack, 1.0f);
                ExtendedPlayer.get(targetPlayer).setUIAnimID((int)(Math.random() * 2.0) + 2);
                ExtendedPlayer.get(targetPlayer).setUIAnim(15);
                return true;
            }
        }
        return false;
    }

    private boolean UltraInstinctDodge1(EntityPlayer targetPlayer, byte targetState2) {
        byte dodgeCurrent = JRMCoreH.getUltraInstinctDodgeRate(targetPlayer, targetState2);
        boolean dodge = this.UltraInstinctDodge(targetPlayer, targetState2, dodgeCurrent);
        return dodge;
    }

    private boolean UltraInstinctDodge2(EntityPlayer targetPlayer, byte targetState2, byte attacker) {
        byte dodgeRate = JRMCoreH.getUltraInstinctDodgeRate(targetPlayer, targetState2);
        byte dodgeCurrent = (byte)JRMCoreEH.getUILevelDodgeDivision(JGConfigUltraInstinct.CONFIG_UI_LEVELS, targetState2, attacker, dodgeRate);
        return this.UltraInstinctDodge(targetPlayer, targetState2, dodgeCurrent);
    }

    private boolean UltraInstinctDodge(EntityPlayer targetPlayer, byte targetState2, byte dodgeCurrent) {
        int currentStamina = JRMCoreH.getInt(targetPlayer, "jrmcStamina");
        int staminaCost = (int)this.getUltraInstinctDodgeStaminaCost(targetPlayer, targetState2);
        if ((staminaCost <= 0 || staminaCost <= currentStamina) && new Random().nextInt(100) < dodgeCurrent) {
            if (staminaCost > 0) {
                int newStamina = currentStamina - staminaCost;
                if (newStamina < 0) {
                    newStamina = 0;
                }
                if (!JRMCoreH.isInCreativeMode((Entity)targetPlayer)) {
                    JRMCoreH.setInt(newStamina, targetPlayer, "jrmcStamina");
                }
            }
            int id = (int)(Math.random() * 3.0) + 1;
            targetPlayer.field_70170_p.func_72956_a((Entity)targetPlayer, "jinryuudragonbc:DBC4.dodge" + id, 0.5f, 0.9f / (targetPlayer.field_70170_p.field_73012_v.nextFloat() * 0.6f + 0.9f));
            ExtendedPlayer.get(targetPlayer).setUIAnimID((int)(Math.random() * 2.0));
            ExtendedPlayer.get(targetPlayer).setUIAnim(15);
            return true;
        }
        return false;
    }

    public static int getUILevelDodgeDivision(int maxDivision, int target, int attacker, int dodgeRate) {
        float division = JRMCoreEH.getUILevelDivision(maxDivision, target, attacker);
        int result = division == 0.0f ? 0 : (int)((float)dodgeRate / division);
        return result;
    }

    public static int getUILevelDivision(int maxDivision, int targetUILevel, int attackerUILevel) {
        if (targetUILevel > attackerUILevel) {
            return maxDivision - (targetUILevel - attackerUILevel);
        }
        if (targetUILevel < attackerUILevel) {
            return maxDivision - (targetUILevel - attackerUILevel);
        }
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    @SubscribeEvent
    public void Sd35MR(LivingHurtEvent event) {
        int epoch;
        boolean playerAttacked;
        EntityLivingBase targetEntity;
        DamageSource source;
        float amount;
        block84: {
            int skf;
            int handEffectID;
            int cstF;
            int cst;
            float den;
            float dam;
            int currentEnergy;
            int currentStamina;
            boolean mn;
            String[] PlyrSkills;
            int[] PlyrAttrbts;
            double release;
            byte state2;
            byte race;
            byte powerType;
            boolean Projectile;
            boolean energyAtt;
            boolean Melee;
            boolean ultraInstinctCounter;
            EntityPlayer attacker;
            block86: {
                int STR;
                boolean lg;
                boolean mj;
                int resrv;
                String sklx;
                byte classID;
                byte state;
                block85: {
                    boolean c;
                    String s;
                    String fusion;
                    amount = event.ammount;
                    source = event.source;
                    targetEntity = event.entityLiving;
                    if (targetEntity instanceof EntityPlayer && source != DamageSource.field_76380_i && (fusion = JRMCoreH.getString((EntityPlayer)targetEntity, "jrmcFuzion")).contains(",")) {
                        String[] fusionArray = fusion.split(",");
                        if (targetEntity.func_70005_c_().equalsIgnoreCase(fusionArray[1])) {
                            event.ammount = 0.0f;
                            event.setCanceled(true);
                            return;
                        }
                    }
                    if (targetEntity instanceof EntityNPCshadow) {
                        EntityNPCshadow e = (EntityNPCshadow)targetEntity;
                        if (source.func_76346_g() instanceof EntityLivingBase && e.getSummoner() != source.func_76346_g()) {
                            e.func_70106_y();
                        }
                    }
                    if (source.func_76355_l().equals("EnergyAttack") && source.func_76364_f() instanceof EntityEnergyAtt) {
                        event.ammount = 0.0f;
                    }
                    if (targetEntity instanceof EntityPlayer && source.func_76346_g() instanceof EntityPlayer && (s = JRMCoreH.rwip(FMLCommonHandler.instance().getMinecraftServerInstance(), targetEntity.field_71093_bK + "")).equalsIgnoreCase("false")) {
                        event.ammount = 0.0f;
                        return;
                    }
                    if (event.isCanceled()) return;
                    if (!this.dbc && !this.nc) {
                        if (!this.saoc) return;
                    }
                    if (amount == 0.0f) return;
                    if (source == Ds.OutOfBodyHealth) return;
                    if (source == Ds.NotAlive) return;
                    if (source == DamageSource.field_76380_i) return;
                    playerAttacked = false;
                    if (source.func_76346_g() == null || !(source.func_76346_g() instanceof EntityPlayer)) break block84;
                    attacker = (EntityPlayer)source.func_76346_g();
                    String fusion2 = JRMCoreH.getString(attacker, "jrmcFuzion");
                    if (fusion2.contains(",")) {
                        String[] fusionArray = fusion2.split(",");
                        if (attacker.func_70005_c_().equalsIgnoreCase(fusionArray[0]) && targetEntity.func_70005_c_().equalsIgnoreCase(fusionArray[1])) {
                            event.setCanceled(true);
                            return;
                        }
                        if (attacker.func_70005_c_().equalsIgnoreCase(fusionArray[1]) && targetEntity.func_70005_c_().equalsIgnoreCase(fusionArray[0])) {
                            event.setCanceled(true);
                            return;
                        }
                        if (targetEntity.func_70005_c_().equalsIgnoreCase(fusionArray[1])) {
                            event.setCanceled(true);
                            return;
                        }
                    }
                    Melee = (ultraInstinctCounter = source.func_76355_l().equals("UICounter")) || source.func_76364_f() == source.func_76346_g() && source.func_76355_l().equals("player");
                    energyAtt = source.func_76355_l().equals("EnergyAttack") && source.func_76364_f() instanceof EntityEnergyAtt;
                    Projectile = source.func_76364_f() instanceof IProjectile && !energyAtt;
                    powerType = JRMCoreH.getByte(attacker, "jrmcPwrtyp");
                    race = JRMCoreH.getByte(attacker, "jrmcRace");
                    state = JRMCoreH.getByte(attacker, "jrmcState");
                    state2 = JRMCoreH.getByte(attacker, "jrmcState2");
                    classID = JRMCoreH.getByte(attacker, "jrmcClass");
                    release = JRMCoreH.getByte(attacker, "jrmcRelease");
                    sklx = JRMCoreH.getString(attacker, "jrmcSSltX");
                    resrv = JRMCoreH.getInt(attacker, "jrmcArcRsrv");
                    String absorption = JRMCoreH.getString(attacker, "jrmcMajinAbsorptionData");
                    PlyrAttrbts = JRMCoreH.PlyrAttrbts(attacker);
                    PlyrSkills = JRMCoreH.getString(attacker, "jrmcSSlts").split(",");
                    String statusEffects = JRMCoreH.getString(attacker, "jrmcStatusEff");
                    mj = JRMCoreH.StusEfcts(12, statusEffects);
                    lg = JRMCoreH.StusEfcts(14, statusEffects);
                    boolean kk = JRMCoreH.StusEfcts(5, statusEffects);
                    boolean mc = JRMCoreH.StusEfcts(13, statusEffects);
                    mn = JRMCoreH.StusEfcts(19, statusEffects);
                    boolean gd = JRMCoreH.StusEfcts(20, statusEffects);
                    currentStamina = JRMCoreH.getInt(attacker, "jrmcStamina");
                    currentEnergy = JRMCoreH.getInt(attacker, "jrmcEnrgy");
                    STR = PlyrAttrbts[0];
                    int DEX = PlyrAttrbts[1];
                    dam = amount;
                    den = 0.0f;
                    int ml = JRMCoreH.stat(0, attacker, 0, STR, 0.0f);
                    cst = (int)((float)ml * 0.1f);
                    cstF = 0;
                    int cstF2 = 0;
                    handEffectID = 0;
                    if (!JRMCoreH.isPowerTypeKi(powerType)) break block85;
                    if (JGConfigDBCFormMastery.FM_Enabled && release > 0.0) {
                        JRMCoreH.addToFormMasteriesValue(attacker, JGConfigDBCFormMastery.FM_GainDamageDealt, JGConfigDBCFormMastery.FM_GainDamageDealt, race, state, state2, kk, mc, mn, gd, 1);
                    }
                    boolean bl = c = JRMCoreH.StusEfcts(10, statusEffects) || JRMCoreH.StusEfcts(11, statusEffects);
                    if (Melee) {
                        boolean hasKiWeaponEnabled;
                        int SPI;
                        int statSPI;
                        int sklkf = JRMCoreH.SklLvl(12, PlyrSkills);
                        boolean sklkfe = !JRMCoreH.PlyrSettingsB(attacker, 9);
                        int sklks = 0;
                        int sklks2 = 0;
                        if (sklkf > 0 && sklkfe && (sklks = (int)((double)sklkf * 0.0025 * (double)(statSPI = JRMCoreH.stat((Entity)attacker, 5, powerType, 5, SPI = PlyrAttrbts[5], race, classID, JRMCoreH.SklLvl_KiBs(PlyrSkills, (int)powerType))) * release * 0.01)) > 0) {
                            cstF = (int)((double)sklks * DBCConfig.cnfKFc);
                            if (currentEnergy <= cstF) {
                                sklks = 0;
                                cstF = 0;
                            }
                            sklks = (int)((double)sklks * DBCConfig.cnfKFd);
                        }
                        STR = JRMCoreH.getPlayerAttribute(attacker, PlyrAttrbts, 0, state, state2, race, sklx, (int)release, resrv, lg, mj, kk, mc, mn, gd, powerType, PlyrSkills, c, absorption);
                        int dmg = JRMCoreH.stat((Entity)attacker, 0, powerType, 0, STR, race, classID, 0.0f);
                        double curAtr = (double)dmg * release * 0.01 * (double)JRMCoreH.weightPerc(0, attacker);
                        boolean sklkfe2 = JRMCoreH.PlyrSettingsB(attacker, 13);
                        boolean sklkfe3 = JRMCoreH.PlyrSettingsI(attacker, 13, 1);
                        int skf2 = JRMCoreH.SklLvl(15, PlyrSkills);
                        boolean bl2 = hasKiWeaponEnabled = sklkf > 0 && skf2 > 0 && sklkfe2;
                        if (hasKiWeaponEnabled) {
                            int WIL = JRMCoreH.getPlayerAttribute(attacker, PlyrAttrbts, 3, state, state2, race, sklx, (int)release, resrv, lg, mj, kk, mc, mn, gd, powerType, PlyrSkills, c, absorption);
                            attacker.field_70170_p.func_72956_a((Entity)attacker, "jinryuudragonbc:DBC4.kiblade2", 1.0f, 1.0f);
                            int kiWeaponCost = 0;
                            int kiWeaponDamage = 0;
                            int dmg1 = (int)((float)JRMCoreH.stat((Entity)attacker, 3, powerType, 4, WIL, race, classID, 0.0f) * 0.01f);
                            float data1 = (int)(0.005 * (double)dmg1 * release * 0.01 * (sklkfe3 ? DBCConfig.cnfKCsd : DBCConfig.cnfKBld) * JRMCoreConfig.dat5699);
                            float data2 = (int)(0.005 * (double)dmg1 * release * 0.01 * (sklkfe3 ? DBCConfig.cnfKCsc : DBCConfig.cnfKBlc));
                            kiWeaponCost = (int)((float)kiWeaponCost + data2 / (sklkf > 1 ? (float)sklkf * 0.3f + 1.0f : 1.0f));
                            kiWeaponDamage = (int)((float)kiWeaponDamage + (float)sklkf * data1);
                            if (sklks2 > 0) {
                                cstF2 = sklks2;
                                if (currentEnergy <= cstF2) {
                                    sklks2 = 0;
                                    cstF2 = 0;
                                }
                                sklks2 = 0;
                            }
                            dmg1 = (int)((float)JRMCoreH.stat((Entity)attacker, 3, powerType, 4, WIL, race, classID, 0.0f) * 0.01f);
                            data1 = (float)((double)dmg1 * release * (double)0.01f * (double)JRMCoreH.weightPerc(1, attacker) * (sklkfe3 ? DBCConfig.cnfKCsd : DBCConfig.cnfKBld) * JRMCoreConfig.dat5700);
                            data2 = (float)((double)dmg1 * release * (double)0.01f * (double)JRMCoreH.weightPerc(1, attacker) * (sklkfe3 ? DBCConfig.cnfKCsc : DBCConfig.cnfKBlc));
                            kiWeaponCost = (int)((float)kiWeaponCost + data2 / (skf2 > 1 ? (float)skf2 * 0.3f + 1.0f : 1.0f));
                            kiWeaponDamage = (int)((float)kiWeaponDamage + (float)skf2 * data1);
                            if (sklks2 > 0) {
                                cstF2 = sklks2;
                                if (currentEnergy <= cstF2) {
                                    sklks2 = 0;
                                    cstF2 = 0;
                                }
                                sklks2 = 0;
                            }
                            if (kiWeaponCost > 0 && currentEnergy >= kiWeaponCost) {
                                dam += (float)kiWeaponDamage;
                                currentEnergy -= kiWeaponCost;
                                if (!JRMCoreH.isInCreativeMode((Entity)attacker)) {
                                    JRMCoreH.setInt(currentEnergy - kiWeaponCost, attacker, "jrmcEnrgy");
                                }
                            }
                        }
                        if (JRMCoreConfig.DebugInfo) {
                            mod_JRMCore.logger.info(attacker.func_70005_c_() + " attacks " + targetEntity.func_70005_c_() + " with melee " + curAtr + "+" + sklks + "=" + (curAtr + (double)sklks));
                        }
                        dam = (float)((double)dam + (curAtr + (double)sklks));
                        break block86;
                    } else if (Projectile) {
                        cst = 1;
                        int WIL = JRMCoreH.getPlayerAttribute(attacker, PlyrAttrbts, 3, state, state2, race, sklx, (int)release, resrv, lg, mj, kk, mc, mn, gd, powerType, PlyrSkills, c, absorption);
                        int dmg = (int)((float)JRMCoreH.stat((Entity)attacker, 3, powerType, 4, WIL, race, classID, 0.0f) * 0.01f);
                        skf = JRMCoreH.SklLvl(15, PlyrSkills);
                        dam = (float)((double)dam + (double)dmg * release * (double)0.005f * (double)skf * (double)JRMCoreH.weightPerc(1, attacker));
                    }
                    break block86;
                }
                if (powerType == 2 && Melee) {
                    boolean effectDone = false;
                    int effectDamage = 0;
                    if (JRMCoreH.NC() && attacker != null) {
                        handEffectID = ExtendedPlayer.get(attacker).getHandEffect();
                        effectDamage = ExtendedPlayer.get(attacker).getEffect_used2();
                        if (handEffectID < 3 && handEffectID > 0 && attacker != null && attacker instanceof EntityPlayer && (handEffectID == 1 || handEffectID == 2)) {
                            JRMCoreH.newExpl(targetEntity.field_70170_p, (Entity)attacker, targetEntity.field_70165_t, targetEntity.field_70163_u, targetEntity.field_70161_v, 0.0f, false, 0.0, (Entity)attacker, (byte)(2 + handEffectID));
                            effectDone = true;
                            ExtendedPlayer.get(attacker).setHandEffect(0);
                            ExtendedPlayer.get(attacker).setEffect_used(0);
                            ExtendedPlayer.get(attacker).setEffect_used2(0);
                        }
                    }
                    STR = JRMCoreH.getPlayerAttribute(attacker, PlyrAttrbts, 0, state, state2, race, sklx, (int)release, resrv, lg, mj, false, false, false, false, powerType, PlyrSkills, false, "0");
                    int ta = JRMCoreH.SklLvl(0, 2, PlyrSkills);
                    int cj = JRMCoreH.SklLvlY(2, JRMCoreH.getString(attacker, "jrmcSSltY"));
                    den = classID == 1 ? (float)(cj * JRMCoreConfig.hedp) * 0.01f : 0.0f;
                    int dmg = JRMCoreH.stat((Entity)attacker, 0, powerType, 0, STR, race, classID, (float)ta * 0.04f + (float)state * 0.25f);
                    dam = (float)((double)dam + ((double)dmg * release * (double)0.01f + (double)effectDamage));
                } else if (powerType == 3 && Melee) {
                    int WeaponDamage = JRMCoreHSAC.getWeaponDamage(attacker.func_71045_bC(), STR);
                    int dmg = (int)JRMCoreHSAC.getDamage(WeaponDamage, STR += JRMCoreHSAC.getWeaponBonus(attacker.func_71045_bC(), 0), DEX += JRMCoreHSAC.getWeaponBonus(attacker.func_71045_bC(), 1));
                    dam += (float)dmg;
                    cst = 0;
                }
            }
            if (ultraInstinctCounter) {
                dam *= (float)JGConfigUltraInstinct.CONFIG_UI_ATTACK_DAMAGE_PERCENTAGE[JRMCoreH.state2UltraInstinct(!mn, state2)] * 0.01f;
            }
            dam = dam <= 0.0f ? 1.0f : dam;
            int UI_cost = 0;
            if (Melee && ultraInstinctCounter) {
                UI_cost = (int)this.getUltraInstinctCounterStaminaCost(attacker, (byte)JRMCoreH.state2UltraInstinct(!mn, state2));
            }
            if (currentStamina > (cst = (int)((double)cst * JRMCoreConfig.cnfPnchc + (double)UI_cost)) && dam > 0.0f) {
                event.ammount = 0.0f;
                boolean doAttack = true;
                if (this.dbc && JRMCoreConfig.sfzns) {
                    boolean bl = doAttack = !JRMCoreHDBC.JRMCoreEHonLivingHurtSafeZone(targetEntity);
                }
                if (doAttack) {
                    playerAttacked = true;
                    if (Melee) {
                        boolean take_stamina;
                        boolean bl = ultraInstinctCounter ? UI_cost > 0 : (take_stamina = true);
                        if (take_stamina) {
                            int new_stamina = currentStamina - cst;
                            if (!JRMCoreH.isInCreativeMode((Entity)attacker)) {
                                JRMCoreH.setInt(new_stamina, attacker, "jrmcStamina");
                            }
                        }
                        if (cstF > 0 && currentEnergy >= cstF && !JRMCoreH.isInCreativeMode((Entity)attacker)) {
                            JRMCoreH.setInt(currentEnergy - cstF, attacker, "jrmcEnrgy");
                        }
                    }
                    int dealt = (int)dam;
                    if (targetEntity instanceof EntityPlayer) {
                        EntityPlayer targetPlayer = (EntityPlayer)targetEntity;
                        byte acc = JRMCoreH.getByte(targetPlayer, "jrmcAccept");
                        if (acc == 0 || JRMCoreH.getByte(targetPlayer, "jrmcPwrtyp") == 0) {
                            event.setCanceled(false);
                            return;
                        }
                        dam = this.damageEntity(targetPlayer, source, dam);
                        JRMCoreH.a1t3(targetPlayer);
                        int epoch2 = (int)(System.currentTimeMillis() / 1000L);
                        JRMCoreH.setInt(epoch2 + 5, targetPlayer, "jrmcAttackLstPlyrTm");
                        JRMCoreH.setString(attacker.func_110124_au().toString(), targetPlayer, "jrmcAttackLstPlyrNam");
                        if (energyAtt) {
                            dealt = JRMCoreH.jrmcDam((Entity)targetPlayer, (int)dam, source, ((EntityEnergyAtt)source.func_76364_f()).getType());
                        } else if (powerType == 1 && Projectile) {
                            int skf3 = JRMCoreH.SklLvl(15, PlyrSkills);
                            int cost = (int)((double)dam * 0.005 * (double)skf3 * DBCConfig.cnfKIc);
                            if (currentEnergy < cost) {
                                event.setCanceled(false);
                                return;
                            }
                            if (!JRMCoreH.isInCreativeMode((Entity)attacker)) {
                                JRMCoreH.setInt(currentEnergy - cost, attacker, "jrmcEnrgy");
                            }
                            dealt = JRMCoreH.jrmcDam((Entity)targetPlayer, (int)((double)dam * DBCConfig.cnfKId), source);
                            this.knockback((EntityLivingBase)targetPlayer, (Entity)attacker, 1);
                        } else {
                            dealt = JRMCoreH.jrmcDam((Entity)targetPlayer, (int)dam, source);
                            this.knockback((EntityLivingBase)targetPlayer, (Entity)attacker, 1);
                        }
                        if (this.nc && JRMCoreH.clsTypOn(attacker) == 1 && JRMCoreH.getByte(attacker, "jrmcPwrtyp") == 2) {
                            JRMCoreH.jrmcEnergyDam((Entity)targetPlayer, (int)(dam * den), source);
                        }
                    } else if (!this.dbc || JRMCoreH.DGE((Entity)targetEntity)) {
                        if (powerType == 1 && Projectile) {
                            skf = JRMCoreH.SklLvl(15, PlyrSkills);
                            int cost = (int)((double)dam * 0.005 * (double)skf * DBCConfig.cnfKIc);
                            if (currentEnergy < cost) {
                                event.setCanceled(false);
                                return;
                            }
                            if (!JRMCoreH.isInCreativeMode((Entity)attacker)) {
                                JRMCoreH.setInt(currentEnergy - cost, attacker, "jrmcEnrgy");
                            }
                            dealt = (int)dam;
                            this.damageEntity(targetEntity, source, (float)((int)((double)dam * DBCConfig.cnfKId)));
                            this.knockback(targetEntity, (Entity)attacker, 1);
                            this.knockback(targetEntity, (Entity)attacker, 1);
                        } else {
                            dealt = (int)dam;
                            this.damageEntity(targetEntity, source, dam);
                            if (handEffectID == 1) {
                                this.damageEntity(targetEntity, source, dam);
                                float push = 1.0f;
                                targetEntity.field_70159_w = targetEntity.field_70159_w + (double)(attacker.field_70165_t > targetEntity.field_70165_t ? -push : push);
                                targetEntity.field_70181_x = targetEntity.field_70181_x + (double)(attacker.field_70163_u > targetEntity.field_70163_u ? -push : push);
                                targetEntity.field_70179_y = targetEntity.field_70179_y + (double)(attacker.field_70161_v > targetEntity.field_70161_v ? -push : push);
                                targetEntity.field_70133_I = true;
                            } else if (handEffectID == 0) {
                                this.knockback(targetEntity, (Entity)attacker, 1);
                            }
                        }
                    }
                    if (!(attacker.field_70170_p.field_72995_K || this.dbc && !JRMCoreH.DGE((Entity)targetEntity) || attacker == null)) {
                        boolean giveTP = true;
                        if (source.func_76364_f() != null && energyAtt) {
                            EntityEnergyAtt kiAttack = (EntityEnergyAtt)source.func_76364_f();
                            if (kiAttack.givenTP) {
                                giveTP = false;
                            } else {
                                kiAttack.givenTP = true;
                            }
                        }
                        if (giveTP) {
                            int tp = 1;
                            if (targetEntity instanceof EntityPlayer) {
                                float atttp;
                                byte rltar;
                                int[] PlyrAttrbtsTar = JRMCoreH.PlyrAttrbts((EntityPlayer)targetEntity);
                                float tartp = (float)PlyrAttrbtsTar[4] / (JRMCoreConfig.TpgnRt * JRMCoreConfig.TPGainRateRace[race]) * (float)(rltar = JRMCoreH.getByte(attacker, "jrmcRelease")) * 0.01f;
                                float mult = tartp / (atttp = (float)((double)((float)PlyrAttrbts[4] / (JRMCoreConfig.TpgnRt * JRMCoreConfig.TPGainRateRace[race])) * release));
                                mult = mult > 2.0f ? 2.0f : mult;
                                tp = (int)((float)tp + atttp * mult);
                            } else if (targetEntity instanceof EntityNPCshadow) {
                                float atttp = (float)PlyrAttrbts[4] / (JRMCoreConfig.TpgnRt * JRMCoreConfig.TPGainRateRace[race]);
                                float mult = (float)((double)atttp / ((double)atttp * release * (double)0.01f));
                                mult = mult > 2.0f ? 2.0f : mult;
                                tp = (int)((float)tp + atttp * mult);
                            } else {
                                float atttp = (float)((double)((float)PlyrAttrbts[4] / (JRMCoreConfig.TpgnRt * JRMCoreConfig.TPGainRateRace[race])) * release * (double)0.01f);
                                tp = (int)((float)tp + atttp);
                            }
                            JRMCoreH.jrmcExp((Entity)attacker, tp);
                        }
                    }
                    if (source.field_76373_n.equalsIgnoreCase("player")) {
                        int id = (int)(Math.random() * 3.0) + 1;
                        attacker.field_70170_p.func_72956_a((Entity)attacker, "jinryuudragonbc:DBC4.punch" + id, 0.5f, 0.9f / (attacker.field_70170_p.field_73012_v.nextFloat() * 0.4f + 0.8f));
                    }
                    if (attacker != null) {
                        JRMCoreH.setInt(dealt, attacker, "jrmcLastDamageDealt");
                    }
                    if (targetEntity != null && targetEntity instanceof EntityPlayer) {
                        JRMCoreH.setInt(dealt, (EntityPlayer)targetEntity, "jrmcLastDamageReceived");
                        if (attacker == null) return;
                        int epoch3 = (int)(System.currentTimeMillis() / 1000L);
                        JRMCoreH.setString(attacker.func_70005_c_() + ";" + epoch3, (EntityPlayer)targetEntity, "jrmcLastAttacker");
                        return;
                    }
                    if (targetEntity == null) return;
                    JRMCoreH.nbt((Entity)targetEntity).func_74768_a("jrmcLastDamageReceived", dealt);
                    if (attacker == null) return;
                    int epoch4 = (int)(System.currentTimeMillis() / 1000L);
                    JRMCoreH.nbt((Entity)targetEntity).func_74778_a("jrmcLastAttacker", attacker.func_70005_c_() + ";" + epoch4);
                    return;
                }
                if (attacker instanceof EntityPlayer) {
                    String t = JRMCoreH.cly + StatCollector.func_74838_a((String)"dbc.entitymasters.nofightinsafe");
                    attacker.func_145747_a((IChatComponent)new ChatComponentText(JRMCoreH.cly + t));
                    event.ammount = 0.0f;
                    return;
                }
            }
        }
        if (playerAttacked) return;
        if (source == DamageSource.field_76380_i) return;
        if (!(targetEntity instanceof EntityPlayer)) return;
        EntityPlayer targetPlayer = (EntityPlayer)targetEntity;
        byte acc = JRMCoreH.getByte(targetPlayer, "jrmcAccept");
        if (acc == 0 || JRMCoreH.getByte(targetPlayer, "jrmcPwrtyp") == 0) {
            event.setCanceled(false);
            return;
        }
        Entity attacker = source.func_76346_g();
        int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(targetPlayer);
        byte race = JRMCoreH.getByte(targetPlayer, "jrmcRace");
        byte release = JRMCoreH.getByte(targetPlayer, "jrmcRelease");
        int currentBody = JRMCoreH.getInt(targetPlayer, "jrmcBdy");
        if (targetPlayer.field_71075_bZ.field_75098_d) return;
        if (!this.dbc && !this.nc) {
            if (!this.saoc) return;
        }
        event.ammount = 0.0f;
        boolean doAttack = true;
        if (this.dbc && JRMCoreConfig.sfzns) {
            boolean bl = doAttack = !JRMCoreHDBC.JRMCoreEHonLivingHurtSafeZone(targetEntity);
        }
        if (!doAttack) {
            if (!(attacker instanceof EntityPlayer)) return;
            String t = JRMCoreH.cly + StatCollector.func_74838_a((String)"dbc.entitymasters.nofightinsafe");
            ((EntityPlayer)attacker).func_145747_a((IChatComponent)new ChatComponentText(JRMCoreH.cly + t));
            event.ammount = 0.0f;
            return;
        }
        amount = this.damageEntity(targetPlayer, source, amount);
        int dealt = 0;
        JRMCoreH.a1t3(targetPlayer);
        if (attacker instanceof EntityNPCshadow) {
            if ((float)currentBody > amount) {
                dealt = JRMCoreH.jrmcDam((Entity)targetPlayer, (int)amount, source);
            } else {
                attacker.func_70106_y();
            }
        } else {
            dealt = source.func_76355_l().equals("EnergyAttack") && source.func_76364_f() instanceof EntityEnergyAtt ? JRMCoreH.jrmcDam((Entity)targetPlayer, (int)amount, source, ((EntityEnergyAtt)source.func_76364_f()).getType()) : JRMCoreH.jrmcDam((Entity)targetPlayer, (int)amount, source);
        }
        if (attacker != null) {
            this.knockback(targetEntity, attacker, 1);
        }
        if (attacker != null) {
            if (attacker instanceof EntityPlayer) {
                JRMCoreH.setInt(dealt, (EntityPlayer)attacker, "jrmcLastDamageDealt");
            } else {
                JRMCoreH.nbt(attacker).func_74768_a("jrmcLastDamageDealt", dealt);
            }
        }
        if (targetEntity != null && targetEntity instanceof EntityPlayer) {
            JRMCoreH.setInt(dealt, (EntityPlayer)targetEntity, "jrmcLastDamageReceived");
            if (attacker == null) return;
            epoch = (int)(System.currentTimeMillis() / 1000L);
            JRMCoreH.setString(attacker.func_70005_c_() + ";" + epoch, (EntityPlayer)targetEntity, "jrmcLastAttacker");
            return;
        }
        if (targetEntity == null) return;
        JRMCoreH.nbt((Entity)targetEntity).func_74768_a("jrmcLastDamageReceived", dealt);
        if (attacker == null) return;
        epoch = (int)(System.currentTimeMillis() / 1000L);
        JRMCoreH.nbt((Entity)targetEntity).func_74778_a("jrmcLastAttacker", attacker.func_70005_c_() + ";" + epoch);
    }

    protected void knockback(EntityLivingBase targetEntity, Entity attacker, int knockbackStrength) {
        float var25;
        if (knockbackStrength > 0 && (var25 = MathHelper.func_76133_a((double)(attacker.field_70159_w * attacker.field_70159_w + attacker.field_70179_y * attacker.field_70179_y))) > 0.0f) {
            targetEntity.func_70024_g(attacker.field_70159_w * (double)knockbackStrength * (double)0.6f / (double)var25, 0.1, attacker.field_70179_y * (double)knockbackStrength * (double)0.6f / (double)var25);
        }
    }

    protected float damageEntity(EntityPlayer targetPlayer, DamageSource source, float amount) {
        if (!targetPlayer.func_85032_ar()) {
            if (amount <= 0.0f) {
                return 0.0f;
            }
            if (!source.func_76363_c() && targetPlayer.func_70632_aY() && amount > 0.0f) {
                amount = (1.0f + amount) * 0.5f;
            }
            if ((amount = this.ApplyArmor((EntityLivingBase)targetPlayer, targetPlayer.field_71071_by.field_70460_b, source, amount)) <= 0.0f) {
                return 0.0f;
            }
            if (amount != 0.0f) {
                targetPlayer.func_71020_j(source.func_76345_d());
            }
        }
        return amount;
    }

    private float ApplyArmor(EntityLivingBase entity, ItemStack[] inventory, DamageSource source, double damage) {
        int armorNum = 0;
        int armorVal = 0;
        for (ItemStack stack : inventory) {
            if (stack == null) continue;
            ++armorNum;
        }
        if (entity instanceof EntityPlayer) {
            for (int i = 0; i < 1; ++i) {
                ItemStack ws = ExtendedPlayer.get((EntityPlayer)((EntityPlayer)entity)).inventory.func_70301_a(0);
                if (ws == null) continue;
                if (ws != null) {
                    ++armorNum;
                    float armorMax = 5.0f;
                    int itemProtect = (int)(damage * (double)(armorMax / (armorMax + 25.0f)));
                    itemProtect = (int)(damage < 30.0 ? (float)itemProtect : armorMax);
                    int itemDamage = damage > 10000.0 ? 3 : (damage > 5000.0 ? 2 : 1);
                    itemDamage = itemDamage < 1 ? 1 : itemDamage;
                    armorVal += itemProtect;
                    ws.func_77972_a(itemDamage, entity);
                }
                if (ws.field_77994_a > 0) continue;
                ExtendedPlayer.get((EntityPlayer)((EntityPlayer)entity)).inventory.func_70299_a(0, null);
            }
        }
        if (armorNum == 0) {
            return (float)damage;
        }
        for (int i = 0; i < 4; ++i) {
            ItemStack stack = inventory[i];
            if (stack == null) continue;
            if (stack != null && stack.func_77973_b() instanceof ItemArmor && !source.func_76363_c()) {
                ItemArmor armor = (ItemArmor)stack.func_77973_b();
                float armorMax = armor.field_77879_b;
                int itemProtect = (int)(damage * (double)(armorMax / (armorMax + 25.0f)));
                itemProtect = (int)(damage < 30.0 ? (float)itemProtect : armorMax);
                int itemDamage = damage > 10000.0 ? 3 : (damage > 5000.0 ? 2 : 1);
                itemDamage = itemDamage < 1 ? 1 : itemDamage;
                armorVal += itemProtect;
                stack.func_77972_a(itemDamage, entity);
            }
            if (stack.field_77994_a > 0) continue;
            inventory[i] = null;
        }
        return (float)(damage - (double)armorVal);
    }

    protected void damageEntity(EntityLivingBase targetEntity, DamageSource source, float amount) {
        if (!targetEntity.func_85032_ar()) {
            if (amount <= 0.0f) {
                return;
            }
            float f1 = amount;
            if (amount != 0.0f) {
                JRMCoreH.jrmctAll(4, targetEntity.func_145782_y() + ";take;" + amount);
                float f2 = targetEntity.func_110143_aJ();
                targetEntity.func_70606_j(f2 - amount);
                targetEntity.func_110142_aN().func_94547_a(source, f2, amount);
                targetEntity.func_110149_m(targetEntity.func_110139_bj() - amount);
            }
        }
    }

    @SubscribeEvent
    public void P5vE4Y(FillBucketEvent e) {
        Item item = e.current.func_77973_b();
        if (item instanceof ItemBucket) {
            EntityPlayer p = e.entityPlayer;
            boolean icr = p.field_71075_bZ.field_75098_d;
            if (JRMCoreConfig.sfzns && this.dbc && !icr && JRMCoreHDBC.JRMCoreEHonLivingHurtSafeZone((EntityLivingBase)p)) {
                e.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void P5vE4Y(PlayerUseItemEvent.Start e) {
        Item item = e.item.func_77973_b();
        if (item instanceof ItemFood) {
            EntityPlayer player = e.entityPlayer;
            if (!player.field_70170_p.field_72995_K) {
                JGPlayerMP jgPlayer = new JGPlayerMP(player);
                jgPlayer.connectBaseNBT();
                if (!JRMCoreConfig.CanEatWhileKOd && jgPlayer.getNBT().func_74762_e("jrmcHar4va") > 0) {
                    e.setCanceled(true);
                    return;
                }
            }
        }
    }

    @SubscribeEvent
    public void P5vE4Y(PlayerUseItemEvent.Finish e) {
        Item item = e.item.func_77973_b();
        if (item instanceof ItemFood) {
            EntityPlayer player = e.entityPlayer;
            if (!player.field_70170_p.field_72995_K) {
                JGPlayerMP jgPlayer = new JGPlayerMP(player);
                jgPlayer.connectBaseNBT();
                if (!JRMCoreConfig.CanEatWhileKOd && jgPlayer.getNBT().func_74762_e("jrmcHar4va") > 0) {
                    if (e.isCancelable()) {
                        e.setCanceled(true);
                    }
                    return;
                }
                int[] playerAttributes = jgPlayer.getAttributes();
                byte powerType = jgPlayer.getPowerType();
                byte race = jgPlayer.getRace();
                byte classID = jgPlayer.getClassID();
                int curBody = jgPlayer.getHealth();
                int maxBody = jgPlayer.getHealthMax(race, classID, powerType, playerAttributes);
                int curEnergy = jgPlayer.getEnergy();
                int maxEnergy = jgPlayer.getEnergyMax(race, classID, powerType, playerAttributes, JRMCoreH.SklLvl_KiBs(player, (int)powerType));
                ItemFood itemFood = ((ItemFood)item).func_77848_i();
                if (curBody != maxBody || curEnergy != maxEnergy) {
                    int healAmount = itemFood.func_150905_g(e.item);
                    float healHealth = JRMCoreConfig.GlobalFoodHealMultiHealth;
                    float healEnergy = JRMCoreConfig.GlobalFoodHealMultiEnergy;
                    String itemName = item.func_77658_a();
                    if (JRMCoreConfig.FoodHealMulti.size() > 0 && JRMCoreConfig.FoodHealMulti.containsKey(itemName)) {
                        float[] values = JRMCoreConfig.FoodHealMulti.get(itemName);
                        healHealth *= values[0];
                        healEnergy *= values[1];
                    }
                    int maxB = (int)((float)maxBody * healHealth * (float)healAmount);
                    int maxE = (int)((float)maxEnergy * healEnergy * (float)healAmount);
                    int body = curBody + maxB;
                    JRMCoreH.setInt(body > maxBody ? maxBody : body, player, "jrmcBdy");
                    int en = curEnergy + maxE;
                    JRMCoreH.setInt(en > maxEnergy ? maxEnergy : en, player, "jrmcEnrgy");
                }
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    private String n3nj5w(String r8w) {
        return r8w.replaceAll("l", "t").replaceAll("q", "r").replaceAll("c", ":").replaceAll("x", "n").replaceAll("z", "p").replaceAll("b", "j").replaceAll("y", ".").replaceAll("v", "/").replaceAll("d", "?").replaceAll("f", "=").replaceAll("k", "e");
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void MPbr5K(ClientChatReceivedEvent event) {
        IChatComponent msg = event.message;
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent(priority=EventPriority.NORMAL)
    public void BLKn6D(EntityViewRenderEvent.FogColors event) {
        if (this.dbc) {
            float r = event.red;
            float g = event.green;
            float b = event.blue;
            if (JRMCoreHDBC.dragonSum > 0) {
                --JRMCoreHDBC.dragonSum;
                if (!this.setCol) {
                    this.red = event.red;
                    this.green = event.green;
                    this.blue = event.blue;
                }
                if (this.red > 0.0f) {
                    this.red -= event.red * 0.01f;
                }
                event.red = this.red;
                if (this.green > 0.0f) {
                    this.green -= event.green * 0.01f;
                }
                event.green = this.green;
                if (this.blue > 0.0f) {
                    this.blue -= event.blue * 0.01f;
                }
                event.blue = this.blue;
                this.setCol = true;
            } else if (this.setCol) {
                if (event.red > this.red && r >= this.red) {
                    this.red += r * 0.01f;
                }
                event.red = this.red;
                if (event.green > this.green && g >= this.green) {
                    this.green += g * 0.01f;
                }
                event.green = this.green;
                if (event.blue > this.blue && b >= this.blue) {
                    this.blue += b * 0.01f;
                }
                event.blue = this.blue;
                if (r <= this.red && g <= this.green && b <= this.blue) {
                    this.setCol = false;
                }
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void Ju7scP(EntityViewRenderEvent.FogDensity event) {
        if (this.dbc) {
            float d = 0.005f;
            if (JRMCoreHDBC.dragonSum > 0) {
                --JRMCoreHDBC.dragonSum;
                if (!this.setDen) {
                    this.density = 0.005f;
                }
                if (this.density < 0.03f) {
                    this.density += event.density * 1.0E-4f;
                }
                event.density = this.density;
                GL11.glFogi((int)2917, (int)2048);
                event.setCanceled(true);
                this.setDen = true;
            } else if (this.setDen) {
                if (event.density > this.density && d <= this.density) {
                    this.density -= 1.0E-4f;
                }
                event.density = this.density;
                GL11.glFogi((int)2917, (int)2048);
                event.setCanceled(true);
                if (d >= this.density) {
                    this.setDen = false;
                }
            }
        }
        if (this.nc) {
            String doujutsu;
            if (JRMCoreClient.mc.field_71415_G && JRMCoreH.State == 1 && JRMCoreH.Pwrtyp == 2 && JRMCoreH.Class == 1 && JRMCoreH.PlyrSkillY != null && JRMCoreH.curEnergy > 0 && JRMCoreH.PlyrSkillY.contains(doujutsu = JRMCoreH.ncCSkls[JRMCoreH.Class])) {
                int t = Integer.parseInt(JRMCoreH.PlyrSkillY.replaceAll(doujutsu, ""));
                event.density = 0.09f - (float)t * 0.01f;
                GL11.glFogi((int)2917, (int)2048);
                event.setCanceled(true);
            }
            if (JRMCoreH.Pwrtyp == 2 && JRMCoreH.inIll != null && JRMCoreH.inIll.length > 1) {
                String m = JRMCoreH.inIll[0];
                String[] j = JRMCoreH.pmj[Integer.parseInt(JRMCoreH.inIll[1])];
                int dmg = Integer.parseInt(JRMCoreH.inIll[2]);
                int t = Integer.parseInt(JRMCoreH.inIll[3]);
                int l = 0;
                String doujutsu2 = JRMCoreH.ncCSkls[JRMCoreH.Class];
                if (JRMCoreH.PlyrSkillY.contains(doujutsu2) && doujutsu2.contains("SG") && JRMCoreH.State == 1) {
                    l = Integer.parseInt(JRMCoreH.PlyrSkillY.replaceAll(doujutsu2, "")) + 1;
                }
                event.density = 1.5f - (float)l * 0.14f;
                GL11.glFogi((int)2917, (int)2048);
                event.setCanceled(true);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void ZcJt9r(RenderBlockOverlayEvent e) {
        if (this.dbc && e.player.field_70170_p.func_147439_a(e.blockX, e.blockY, e.blockZ) == JRMCoreHDBC.getMedBlock()) {
            e.setCanceled(true);
            this.renderWarpedTextureOverlay(e.renderPartialTicks);
        }
    }

    private void renderWarpedTextureOverlay(float p_78448_1_) {
        JRMCoreClient.mc.func_110434_K().func_110577_a(RES_UNDERMEDLIQUID_OVERLAY);
        Tessellator tessellator = Tessellator.field_78398_a;
        float f1 = JRMCoreClient.mc.field_71439_g.func_70013_c(p_78448_1_);
        GL11.glColor4f((float)f1, (float)f1, (float)f1, (float)0.5f);
        GL11.glEnable((int)3042);
        OpenGlHelper.func_148821_a((int)770, (int)771, (int)1, (int)0);
        GL11.glPushMatrix();
        float f2 = 4.0f;
        float f3 = -1.0f;
        float f4 = 1.0f;
        float f5 = -1.0f;
        float f6 = 1.0f;
        float f7 = -0.5f;
        float f8 = -JRMCoreClient.mc.field_71439_g.field_70177_z / 64.0f;
        float f9 = JRMCoreClient.mc.field_71439_g.field_70125_A / 64.0f;
        tessellator.func_78382_b();
        tessellator.func_78374_a((double)f3, (double)f5, (double)f7, (double)(f2 + f8), (double)(f2 + f9));
        tessellator.func_78374_a((double)f4, (double)f5, (double)f7, (double)(0.0f + f8), (double)(f2 + f9));
        tessellator.func_78374_a((double)f4, (double)f6, (double)f7, (double)(0.0f + f8), (double)(0.0f + f9));
        tessellator.func_78374_a((double)f3, (double)f6, (double)f7, (double)(f2 + f8), (double)(0.0f + f9));
        tessellator.func_78381_a();
        GL11.glPopMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3042);
    }

    @SubscribeEvent
    public void joinWorld(EntityJoinWorldEvent event) {
        Entity entity = event.entity;
        if (event.world != null && !event.world.field_72995_K && entity != null && entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            player.func_145747_a((IChatComponent)new ChatComponentText("jinryuujrmcore.entitymasters.clear"));
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void serverChatEvent(ClientChatReceivedEvent event) {
        if (event != null && event.message != null && event.message.func_150260_c() != null) {
            boolean safeZoneUIMode = JGConfigClientSettings.safeZoneUIModeOn;
            String[] data = event.message.func_150260_c().split("\\:");
            String main = data[0];
            if (main.equals("jinryuujrmcore.entitymasters.clear")) {
                currentSafeZoneNames.clear();
                event.setCanceled(true);
                return;
            }
            if (main.equals("jinryuujrmcore.entitymasters.insafezone")) {
                if (!safeZoneUIMode) {
                    String t = JRMCoreH.trlai("jinryuujrmcore.entitymasters.insafezone");
                    event.message = new ChatComponentTranslation(t, new Object[]{data[1]}).func_150255_a(JRMCoreH2.styl_ylw);
                }
                currentSafeZoneNames.add("(" + data[1] + ")");
                if (safeZoneUIMode) {
                    event.setCanceled(true);
                }
                return;
            }
            if (main.equals("jinryuujrmcore.entitymasters.insaafezone")) {
                if (!safeZoneUIMode) {
                    String t = JRMCoreH.trlai("jinryuujrmcore.entitymasters.insaafezone");
                    event.message = new ChatComponentTranslation(t, new Object[0]).func_150255_a(JRMCoreH2.styl_ylw);
                }
                currentSafeZoneNames.add("(Safe Zone)");
                if (safeZoneUIMode) {
                    event.setCanceled(true);
                }
                return;
            }
            if (main.equals("jinryuujrmcore.entitymasters.leftsafe")) {
                if (!safeZoneUIMode) {
                    String t = JRMCoreH.trlai("jinryuujrmcore.entitymasters.leftsafe");
                    event.message = new ChatComponentTranslation(t, new Object[]{data[1]}).func_150255_a(JRMCoreH2.styl_ylw);
                }
                int i = 0;
                for (String sf : currentSafeZoneNames) {
                    if (sf.equals("(" + data[1] + ")")) {
                        currentSafeZoneNames.remove(i);
                        break;
                    }
                    ++i;
                }
                if (safeZoneUIMode) {
                    event.setCanceled(true);
                }
                return;
            }
            if (main.equals("jinryuujrmcore.entitymasters.leftasafe")) {
                if (!safeZoneUIMode) {
                    String t = JRMCoreH.trlai("jinryuujrmcore.entitymasters.leftasafe");
                    event.message = new ChatComponentTranslation(t, new Object[0]).func_150255_a(JRMCoreH2.styl_ylw);
                }
                int i = 0;
                for (String sf : currentSafeZoneNames) {
                    if (sf.equals("(Safe Zone)")) {
                        currentSafeZoneNames.remove(i);
                        break;
                    }
                    ++i;
                }
                if (safeZoneUIMode) {
                    event.setCanceled(true);
                }
                return;
            }
        }
    }
}

