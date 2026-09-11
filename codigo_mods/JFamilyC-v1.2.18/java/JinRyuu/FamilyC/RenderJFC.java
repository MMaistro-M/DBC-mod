/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderBiped
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTUtil
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StringUtils
 *  net.minecraftforge.client.ForgeHooksClient
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  net.minecraftforge.client.MinecraftForgeClient
 *  net.minecraftforge.client.event.RenderLivingEvent$Post
 *  net.minecraftforge.client.event.RenderLivingEvent$Pre
 *  net.minecraftforge.client.event.RenderLivingEvent$Specials$Post
 *  net.minecraftforge.client.event.RenderLivingEvent$Specials$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.FamilyC;

import JinRyuu.DragonBC.common.Items.ItemsDBC;
import JinRyuu.FamilyC.EntityNPC;
import JinRyuu.FamilyC.FamilyCConfig;
import JinRyuu.FamilyC.ModelBipedJFC;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreGuiScreen;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHJBRA;
import JinRyuu.JRMCore.JRMCoreHJYC;
import JinRyuu.JRMCore.entity.ModelBipedBody;
import JinRyuu.JYearsC.JYearsCConfig;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.HashMap;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderJFC
extends RenderBiped {
    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    private ModelBipedJFC modelMain;
    private ModelBiped bra;
    private ModelBiped others;
    private ModelBiped pants;
    private ModelBiped modelArmorChestplateDBC;
    private ModelBiped modelArmorDBC;
    private ModelBiped modelArmor;
    private ModelBiped modelArmorChestplate;
    protected ResourceLocation curSkin = null;
    public ModelBiped armrMdl = JRMCoreH.JBRA() ? JRMCoreHJBRA.ModelBipedBody(1.0f) : null;
    public ModelBiped armrMdl2 = JRMCoreH.JBRA() ? JRMCoreHJBRA.ModelBipedBody(0.5f) : null;
    boolean b = true;
    private float age;
    private boolean curSkinUp;
    private int gen = 0;
    private int breast = 0;
    private String dns = "";
    private String dnsH = "";
    private String name = "Child";
    public static HashMap<Integer, Integer> state = new HashMap();
    public static HashMap<Integer, Integer> stateChange = new HashMap();
    public static HashMap<Integer, Integer> state2Change = new HashMap();
    public static HashMap<Integer, Integer> auratype = new HashMap();
    public static HashMap<Integer, Integer> auratime = new HashMap();
    public static HashMap<Integer, Integer> bendtime = new HashMap();

    public RenderJFC() {
        super(new ModelBiped(0.0f), 0.5f);
        this.modelMain = new ModelBipedJFC(0.0f);
        this.bra = new ModelBipedBody(0.001f);
        this.modelArmorChestplate = new ModelBipedBody(1.0f, 0.0f, 64, 32);
        this.modelArmor = new ModelBipedBody(0.5f, 0.0f, 64, 32);
        this.modelArmorChestplateDBC = new ModelBipedBody(0.205f);
        this.modelArmorDBC = new ModelBipedBody(0.11f);
        this.field_77045_g = this.modelMain;
        this.field_77071_a = this.modelMain;
    }

    protected int func_77032_a(EntityLiving par1EntityLiving, int par2, float par3) {
        Item item;
        ItemStack itemstack = par1EntityLiving.func_130225_q(3 - par2);
        if (itemstack != null && JRMCoreH.JBRA() && (item = itemstack.func_77973_b()) instanceof ItemArmor) {
            ItemArmor itemarmor = (ItemArmor)item;
            JRMCoreClient.mc.func_110434_K().func_110577_a(RenderBiped.getArmorResource((Entity)par1EntityLiving, (ItemStack)itemstack, (int)par2, null));
            this.modelArmor = this.armrMdl2;
            this.modelArmorChestplate = this.armrMdl;
            ModelBiped modelbiped = par2 == 2 ? this.modelArmor : this.modelArmorChestplate;
            modelbiped = ForgeHooksClient.getArmorModel((EntityLivingBase)par1EntityLiving, (ItemStack)itemstack, (int)par2, (ModelBiped)modelbiped);
            modelbiped = JRMCoreHJBRA.showModel(modelbiped, (EntityLivingBase)par1EntityLiving, itemstack, par2);
            this.func_77042_a((ModelBase)modelbiped);
            modelbiped.field_78093_q = par1EntityLiving.func_70115_ae();
            modelbiped.field_78091_s = par1EntityLiving.func_70631_g_();
            modelbiped.field_78117_n = par1EntityLiving.func_70093_af();
            int j = itemarmor.func_82814_b(itemstack);
            if (j != -1) {
                float f1 = (float)(j >> 16 & 0xFF) / 255.0f;
                float f2 = (float)(j >> 8 & 0xFF) / 255.0f;
                float f3 = (float)(j & 0xFF) / 255.0f;
                GL11.glColor3f((float)f1, (float)f2, (float)f3);
                if (itemstack.func_77948_v()) {
                    return 31;
                }
                return 16;
            }
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            if (itemstack.func_77948_v()) {
                return 15;
            }
            return 1;
        }
        return -1;
    }

    public void func_76986_a(Entity entity, double d0, double d1, double d2, float f, float f1) {
        this.doRenderLiving((EntityLivingBase)entity, d0, d1, d2, f, f1);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return this.curSkin == null ? new ResourceLocation("jinryuufamilyc:npcs/" + (this.gen == 1 ? "f" : "") + "child.png") : this.curSkin;
    }

    byte b(int n) {
        return (byte)n;
    }

    byte b(String n) {
        return Byte.parseByte(n);
    }

    int i(String n) {
        return Integer.parseInt(n);
    }

    private int JFCgetConfigpt() {
        return FamilyCConfig.pt;
    }

    private float JYCgetConfigpgut() {
        return JYearsCConfig.pgut;
    }

    protected void func_77029_c(EntityLivingBase par1EntityLivingBase, float par2) {
        float f1;
        boolean is3D;
        IItemRenderer customRenderer;
        Item item;
        float childScl;
        int gen;
        if (par1EntityLivingBase instanceof EntityNPC) {
            EntityNPC e = (EntityNPC)par1EntityLivingBase;
            this.dns = e.getDNS();
            this.dnsH = e.getDNSH();
            if (this.dns.length() > 5) {
                this.gen = JRMCoreH.dnsGender(this.dns);
            }
            this.name = e.getNam();
            this.age = e.getNPCgrw();
            this.renderSkins(par1EntityLivingBase, par2);
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        ItemStack itemstack = par1EntityLivingBase.func_70694_bm();
        ItemStack itemstack1 = ((EntityLiving)par1EntityLivingBase).func_130225_q(3);
        if (itemstack1 != null) {
            GL11.glPushMatrix();
            gen = this.gen + 1;
            childScl = this.age;
            if (gen <= 1) {
                GL11.glScalef((float)(1.0f / childScl), (float)(1.0f / childScl), (float)(1.0f / childScl));
                GL11.glTranslatef((float)-0.1f, (float)((childScl - 1.0f) * 1.5f), (float)0.0f);
                this.modelMain.field_78112_f.func_78794_c(0.0625f);
            }
            if (gen >= 2) {
                GL11.glScalef((float)(1.0f / childScl * (gen <= 1 ? 1.0f : 0.7f)), (float)(1.0f / childScl), (float)(1.0f / childScl * (gen <= 1 ? 1.0f : 0.7f)));
                GL11.glTranslatef((float)-0.1f, (float)((childScl - 1.0f) * 1.5f), (float)0.0f);
                this.modelMain.Brightarm.func_78794_c(0.0625f);
            }
            item = itemstack1.func_77973_b();
            customRenderer = MinecraftForgeClient.getItemRenderer((ItemStack)itemstack1, (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.EQUIPPED);
            boolean bl = is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack1, IItemRenderer.ItemRendererHelper.BLOCK_3D);
            if (item instanceof ItemBlock) {
                if (is3D || RenderBlocks.func_147739_a((int)Block.func_149634_a((Item)item).func_149645_b())) {
                    f1 = 0.625f;
                    GL11.glTranslatef((float)0.0f, (float)-0.25f, (float)0.0f);
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glScalef((float)f1, (float)(-f1), (float)(-f1));
                }
                this.field_76990_c.field_78721_f.func_78443_a(par1EntityLivingBase, itemstack1, 0);
            } else if (item == Items.field_151144_bL) {
                f1 = 1.0625f;
                GL11.glScalef((float)f1, (float)(-f1), (float)(-f1));
                GameProfile gameprofile = null;
                if (itemstack1.func_77942_o()) {
                    NBTTagCompound nbttagcompound = itemstack1.func_77978_p();
                    if (nbttagcompound.func_150297_b("SkullOwner", 10)) {
                        gameprofile = NBTUtil.func_152459_a((NBTTagCompound)nbttagcompound.func_74775_l("SkullOwner"));
                    } else if (nbttagcompound.func_150297_b("SkullOwner", 8) && !StringUtils.func_151246_b((String)nbttagcompound.func_74779_i("SkullOwner"))) {
                        gameprofile = new GameProfile((UUID)null, nbttagcompound.func_74779_i("SkullOwner"));
                    }
                }
                TileEntitySkullRenderer.field_147536_b.func_152674_a(-0.5f, 0.0f, -0.5f, 1, 180.0f, itemstack1.func_77960_j(), gameprofile);
            }
            GL11.glPopMatrix();
        }
        if (itemstack != null && itemstack.func_77973_b() != null) {
            item = itemstack.func_77973_b();
            GL11.glPushMatrix();
            if (this.field_77045_g.field_78091_s) {
                f1 = 0.5f;
                GL11.glTranslatef((float)0.0f, (float)0.625f, (float)0.0f);
                GL11.glRotatef((float)-20.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
                GL11.glScalef((float)f1, (float)f1, (float)f1);
            }
            gen = this.gen + 1;
            childScl = this.age;
            if (gen <= 1) {
                GL11.glScalef((float)(1.0f / childScl), (float)(1.0f / childScl), (float)(1.0f / childScl));
                GL11.glTranslatef((float)-0.1f, (float)((childScl - 1.0f) * 1.5f), (float)0.0f);
                this.modelMain.field_78112_f.func_78794_c(0.0625f);
            }
            if (gen >= 2) {
                GL11.glScalef((float)(1.0f / childScl * (gen <= 1 ? 1.0f : 0.7f)), (float)(1.0f / childScl), (float)(1.0f / childScl * (gen <= 1 ? 1.0f : 0.7f)));
                GL11.glTranslatef((float)-0.1f, (float)((childScl - 1.0f) * 1.5f), (float)0.0f);
                this.modelMain.Brightarm.func_78794_c(0.0625f);
            }
            GL11.glTranslatef((float)-0.0625f, (float)0.4375f, (float)0.0625f);
            customRenderer = MinecraftForgeClient.getItemRenderer((ItemStack)itemstack, (IItemRenderer.ItemRenderType)IItemRenderer.ItemRenderType.EQUIPPED);
            boolean bl = is3D = customRenderer != null && customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, IItemRenderer.ItemRendererHelper.BLOCK_3D);
            if (item instanceof ItemBlock && (is3D || RenderBlocks.func_147739_a((int)Block.func_149634_a((Item)item).func_149645_b()))) {
                f1 = 0.5f;
                GL11.glTranslatef((float)0.0f, (float)0.1875f, (float)-0.3125f);
                GL11.glRotatef((float)20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)(-(f1 *= 0.75f)), (float)(-f1), (float)f1);
            } else if (item == Items.field_151031_f) {
                f1 = 0.625f;
                GL11.glTranslatef((float)0.0f, (float)0.125f, (float)0.3125f);
                GL11.glRotatef((float)-20.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glScalef((float)f1, (float)(-f1), (float)f1);
                GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else if (item.func_77662_d()) {
                f1 = 0.625f;
                if (item.func_77629_n_()) {
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)0.0f, (float)-0.125f, (float)0.0f);
                }
                this.func_82422_c();
                GL11.glScalef((float)f1, (float)(-f1), (float)f1);
                GL11.glRotatef((float)-100.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            } else {
                f1 = 0.375f;
                GL11.glTranslatef((float)0.25f, (float)0.1875f, (float)-0.1875f);
                GL11.glScalef((float)f1, (float)f1, (float)f1);
                GL11.glRotatef((float)60.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
            if (itemstack.func_77973_b().func_77623_v()) {
                for (int i = 0; i < itemstack.func_77973_b().getRenderPasses(itemstack.func_77960_j()); ++i) {
                    int j = itemstack.func_77973_b().func_82790_a(itemstack, i);
                    float f5 = (float)(j >> 16 & 0xFF) / 255.0f;
                    float f2 = (float)(j >> 8 & 0xFF) / 255.0f;
                    float f3 = (float)(j & 0xFF) / 255.0f;
                    GL11.glColor4f((float)f5, (float)f2, (float)f3, (float)1.0f);
                    this.field_76990_c.field_78721_f.func_78443_a(par1EntityLivingBase, itemstack, i);
                }
            } else {
                int i = itemstack.func_77973_b().func_82790_a(itemstack, 0);
                float f4 = (float)(i >> 16 & 0xFF) / 255.0f;
                float f5 = (float)(i >> 8 & 0xFF) / 255.0f;
                float f2 = (float)(i & 0xFF) / 255.0f;
                GL11.glColor4f((float)f4, (float)f5, (float)f2, (float)1.0f);
                this.field_76990_c.field_78721_f.func_78443_a(par1EntityLivingBase, itemstack, 0);
            }
            GL11.glPopMatrix();
        }
    }

    public void glColor3f(int c) {
        float h2 = (float)(c >> 16 & 0xFF) / 255.0f;
        float h3 = (float)(c >> 8 & 0xFF) / 255.0f;
        float h4 = (float)(c & 0xFF) / 255.0f;
        float h1 = 1.0f;
        GL11.glColor3f((float)(h1 * h2), (float)(h1 * h3), (float)(h1 * h4));
    }

    public void glColor3f(int c, float a) {
        float h2 = (float)(c >> 16 & 0xFF) / 255.0f;
        float h3 = (float)(c >> 8 & 0xFF) / 255.0f;
        float h4 = (float)(c & 0xFF) / 255.0f;
        float h1 = 1.0f;
        if (JRMCoreH.JYC() && a > (float)(JRMCoreHJYC.JYCgetConfigpls() / 2)) {
            float lifespan = (float)JRMCoreHJYC.JYCgetConfigpls() * 0.25f;
            float age = a - (float)JRMCoreHJYC.JYCgetConfigpls() * 0.5f;
            float grey = 0.8627451f;
            float percentComplete = age / lifespan;
            percentComplete = percentComplete > 1.0f ? 1.0f : percentComplete;
            float percentGone = 1.0f - percentComplete;
            float red = h2 * percentGone + grey * percentComplete;
            float green = h3 * percentGone + grey * percentComplete;
            float blue = h4 * percentGone + grey * percentComplete;
            h2 = red;
            h3 = green;
            h4 = blue;
        }
        GL11.glColor3f((float)(h1 * h2), (float)(h1 * h3), (float)(h1 * h4));
    }

    private void renderSkins(EntityLivingBase par1EntityLivingBase, float par2) {
        boolean dead;
        boolean dbc = JRMCoreH.DBC();
        if (par1EntityLivingBase instanceof EntityNPC) {
            EntityNPC e = (EntityNPC)par1EntityLivingBase;
            this.dns = e.getDNS();
            this.dnsH = e.getDNSH();
            this.dnsH = JRMCoreH.dnsHairG1toG2(this.dnsH);
            if (this.dns.length() > 5) {
                String s1;
                int State = 0;
                int ts = 0;
                int race = JRMCoreH.dnsRace(this.dns);
                int gen = JRMCoreH.dnsGender(this.dns);
                int haircol = JRMCoreH.dnsHairC(this.dns);
                int hairback = JRMCoreH.dnsHairB(this.dns);
                int breast = JRMCoreH.dnsBreast(this.dns);
                int skintype = JRMCoreH.dnsSkinT(this.dns);
                int bodytype = skintype == 0 ? 0 : JRMCoreH.dnsBodyT(this.dns);
                int bodycm = skintype == 0 ? 0 : JRMCoreH.dnsBodyCM(this.dns);
                int bodyc1 = skintype == 0 ? 0 : JRMCoreH.dnsBodyC1(this.dns);
                int bodyc2 = skintype == 0 ? 0 : JRMCoreH.dnsBodyC2(this.dns);
                int bodyc3 = skintype == 0 ? 0 : JRMCoreH.dnsBodyC3(this.dns);
                int facen = skintype == 0 ? 0 : JRMCoreH.dnsFaceN(this.dns);
                int facem = skintype == 0 ? 0 : JRMCoreH.dnsFaceM(this.dns);
                int eyes = skintype == 0 ? 0 : JRMCoreH.dnsEyes(this.dns);
                int eyec1 = skintype == 0 ? 0 : JRMCoreH.dnsEyeC1(this.dns);
                int eyec2 = skintype == 0 ? 0 : JRMCoreH.dnsEyeC2(this.dns);
                int superhcol = 16574610;
                int superecol = 2988684;
                int plyrSpc = skintype == 0 ? 0 : (JRMCoreH.RaceCustomSkin[race] == 0 ? 0 : (bodytype >= JRMCoreH.Specials[race] ? JRMCoreH.Specials[race] - 1 : bodytype));
                float f5 = e.field_70126_B + (e.field_70177_z - e.field_70126_B) * par2 - (e.field_70760_ar + (e.field_70761_aq - e.field_70760_ar) * par2);
                float f3 = e.field_70127_C + (e.field_70125_A - e.field_70127_C) * par2;
                GL11.glPushMatrix();
                if (JRMCoreH.isRaceMajin(race) && dbc) {
                    int j;
                    haircol = bodycm;
                    if (skintype != 0) {
                        ResourceLocation bdyskn;
                        this.curSkin = bdyskn = new ResourceLocation("jinryuudragonbc:cc/majin/" + (gen == 1 ? "f" : "") + "majin.png");
                        this.func_110776_a(bdyskn);
                        this.glColor3f(bodycm);
                        this.modelMain.renderBody(0.0625f, 1, breast);
                        this.func_110776_a(new ResourceLocation("jinryuudragonbc:cc/majin/" + (gen == 1 ? "f" : "") + "majinn" + facen + ".png"));
                        this.glColor3f(bodycm);
                        this.modelMain.renderHairs(0.0625f, "FACENOSE");
                        this.func_110776_a(new ResourceLocation("jinryuudragonbc:cc/majin/" + (gen == 1 ? "f" : "") + "majinm" + facem + ".png"));
                        this.glColor3f(bodycm);
                        this.modelMain.renderHairs(0.0625f, "FACEMOUTH");
                        this.func_110776_a(new ResourceLocation("jinryuudragonbc:cc/majin/" + (gen == 1 ? "f" : "") + "majinb" + eyes + ".png"));
                        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
                        this.modelMain.renderHairs(0.0625f, "EYEBASE");
                        this.func_110776_a(new ResourceLocation("jinryuudragonbc:cc/majin/" + (gen == 1 ? "f" : "") + "majinl" + eyes + ".png"));
                        this.glColor3f((race == 1 || race == 2) && State != 0 ? superecol : eyec1);
                        this.modelMain.renderHairs(0.0625f, "EYELEFT");
                        this.func_110776_a(new ResourceLocation("jinryuudragonbc:cc/majin/" + (gen == 1 ? "f" : "") + "majinr" + eyes + ".png"));
                        this.glColor3f((race == 1 || race == 2) && State != 0 ? superecol : eyec2);
                        this.modelMain.renderHairs(0.0625f, "EYERIGHT");
                    }
                    if ((race == 1 || race == 2) && dbc) {
                        float h1 = 1.0f;
                        j = State == 0 ? (skintype == 1 ? bodyc1 : 6498048) : 16574610;
                        this.func_110776_a(new ResourceLocation("jinryuudragonbc:gui/allw.png"));
                        this.glColor3f(j);
                        this.modelMain.renderHairs(0.0625f, ts == 0 || ts == -1 ? "SJT1" : (ts == 1 ? "SJT2" : ""));
                    }
                    float h1 = 1.0f;
                    j = haircol;
                    int Hair = hairback;
                    if (!(Hair != 8 && Hair != 9 || State != 0 && State != 1)) {
                        j = State != 0 ? 16574610 : j;
                        s1 = Hair == 8 ? "c2" : "c1";
                        this.func_110776_a(new ResourceLocation("jinryuumodscore:gui/" + s1 + ".png"));
                    } else if (Hair >= 0 && Hair <= 12) {
                        j = State != 0 ? 16574610 : j;
                        s1 = State == 0 ? "normall" : "superall";
                        this.func_110776_a(new ResourceLocation("jinryuumodscore:gui/" + s1 + ".png"));
                    }
                    this.glColor3f(j, haircol);
                    if (Hair < 10) {
                        int n = Hair = Hair % 2 == 0 ? 10 : 11;
                    }
                    if (Hair == 10) {
                        this.func_110776_a(new ResourceLocation("jinryuumodscore:gui/normallmajin.png"));
                        this.glColor3f(haircol);
                        this.modelMain.renderHairsV2(0.0625f, "005050555050000050505550500000505055505000005050455050000050505250500000505052505000005050555050000050505450500000505052505000005050525050000150433450500000505055505000005050525050000054395050500000505045505000005050475050000050504750500000505047505000015043655050000050504750500000505047505000005050475050000050504750500000544545505000005250505050000052505050500000525050505000005250505050000050505050500000505050505000005050505050000052505050500000525050505000005250505050000052505050500000525050505000005245505050000054505050500000525050505000005252505050000070505050500000705050505000007050505050000070505050500000705050505000347050505050003470505050500000705050505000007050505050000069505050500000695050505000007050505050000070505050500000705050505000007050505050000070505050500020", 0.0f, State, 0, e.func_145782_y(), race, this);
                    } else if (Hair == 11) {
                        this.func_110776_a(new ResourceLocation("jinryuumodscore:gui/normallmajin.png"));
                        this.glColor3f(haircol);
                        this.modelMain.renderHairsV2(0.0625f, "345052545050001250545650500023505041505000345056455050000150505250500001505052505000015050555050000150505450500001505052505000015050525050000150433450500001505055505000015050525050000154395050500001505045505000015050475050000150504750500001505047505000015043655050000150504750500001505047505000015050475050000150504750500001544545505000015250505050003450505050500034505050505000015250505050000150505050500001505050505000015050505050000150505050500001525050505000015050505050000150505050500001525050505000235250505050003450505050500034505050505000235250505050000180501850500034695050505000346950505050000180501950500001805019505000345850505050003463505050500001805018505000018050185050003476505050500034765050505000018050195050003480501850500034505050505000345050505050003480501950500020", 0.0f, State, 0, e.func_145782_y(), race, this);
                    } else if (Hair == 12) {
                        this.func_110776_a(new ResourceLocation("jinryuumodscore:gui/normallmajin.png"));
                        if (JRMCoreGuiScreen.hairPreview > 0) {
                            State = JRMCoreGuiScreen.hairPreviewStates[JRMCoreGuiScreen.hairPreview];
                        }
                        if (State == 6) {
                            this.modelMain.renderHairs(0.0625f, "" + JRMCoreH.HairsT[6] + JRMCoreH.Hairs[0]);
                        } else {
                            this.modelMain.renderHairsV2(0.0625f, this.dnsH, 0.0f, State, 0, e.func_145782_y(), race, this);
                        }
                    } else {
                        this.modelMain.renderHairs(0.0625f, "" + JRMCoreH.HairsT[State] + JRMCoreH.Hairs[Hair]);
                    }
                } else if (race == 3 && dbc) {
                    ResourceLocation bdyskn;
                    int j = 5095183;
                    this.func_110776_a(new ResourceLocation("jinryuudragonbc:gui/allw.png"));
                    float h1 = 1.0f;
                    this.glColor3f(bodycm);
                    this.modelMain.renderHairs(0.0625f, "N");
                    this.curSkin = bdyskn = new ResourceLocation("jinryuudragonbc:cc/nam/0nam" + plyrSpc + ".png");
                    this.func_110776_a(bdyskn);
                    this.glColor3f(bodycm);
                    this.modelMain.renderBody(0.0625f, 1);
                    this.curSkin = bdyskn = new ResourceLocation("jinryuudragonbc:cc/nam/1nam" + plyrSpc + ".png");
                    this.func_110776_a(bdyskn);
                    this.glColor3f(bodyc1);
                    this.modelMain.renderBody(0.0625f, 1);
                    this.curSkin = bdyskn = new ResourceLocation("jinryuudragonbc:cc/nam/2nam" + plyrSpc + ".png");
                    this.func_110776_a(bdyskn);
                    this.glColor3f(bodyc2);
                    this.modelMain.renderBody(0.0625f, 1);
                    this.curSkin = bdyskn = new ResourceLocation("jinryuudragonbc:cc/nam/3nam" + plyrSpc + ".png");
                    this.func_110776_a(bdyskn);
                    GL11.glColor3f((float)h1, (float)h1, (float)h1);
                    this.modelMain.renderBody(0.0625f, 1, breast);
                    this.func_110776_a(new ResourceLocation("jinryuudragonbc", "cc/nam/4namn" + facen + ".png"));
                    this.glColor3f(bodycm);
                    this.modelMain.renderHairs(0.0625f, "FACENOSE");
                    this.func_110776_a(new ResourceLocation("jinryuudragonbc", "cc/nam/4namm" + facem + ".png"));
                    this.glColor3f(bodycm);
                    this.modelMain.renderHairs(0.0625f, "FACEMOUTH");
                    this.func_110776_a(new ResourceLocation("jinryuudragonbc", "cc/nam/4namb" + eyes + ".png"));
                    GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
                    this.modelMain.renderHairs(0.0625f, "EYEBASE");
                    this.func_110776_a(new ResourceLocation("jinryuudragonbc", "cc/nam/4naml" + eyes + ".png"));
                    this.glColor3f(eyec1);
                    this.modelMain.renderHairs(0.0625f, "EYELEFT");
                    this.func_110776_a(new ResourceLocation("jinryuudragonbc", "cc/nam/4namr" + eyes + ".png"));
                    this.glColor3f(eyec2);
                    this.modelMain.renderHairs(0.0625f, "EYERIGHT");
                    this.func_110776_a(new ResourceLocation("jinryuudragonbc", "cc/nam/4namw" + eyes + ".png"));
                    this.glColor3f(bodycm);
                    this.modelMain.renderHairs(0.0625f, "EYEBROW");
                } else if (race == 4 && dbc) {
                    ResourceLocation bdyskn;
                    this.func_110776_a(new ResourceLocation("jinryuudragonbc:cc/arc/" + (gen == 1 ? "f" : "m") + "/0B" + JRMCoreH.TransFrSkn2[State] + plyrSpc + ".png"));
                    this.glColor3f(bodycm);
                    this.modelMain.renderHairs(0.0625f, "FR" + JRMCoreH.TransFrHrn[State]);
                    this.curSkin = bdyskn = new ResourceLocation("jinryuudragonbc:cc/arc/" + (gen == 1 ? "f" : "m") + "/0A" + JRMCoreH.TransFrSkn[State] + plyrSpc + ".png");
                    this.func_110776_a(bdyskn);
                    this.glColor3f(bodycm);
                    this.modelMain.renderBody(0.0625f, 1, breast);
                    this.func_110776_a(new ResourceLocation("jinryuudragonbc:cc/arc/" + (gen == 1 ? "f" : "m") + "/1B" + JRMCoreH.TransFrSkn2[State] + plyrSpc + ".png"));
                    this.glColor3f(bodyc1);
                    this.modelMain.renderHairs(0.0625f, "FR" + JRMCoreH.TransFrHrn[State]);
                    this.curSkin = bdyskn = new ResourceLocation("jinryuudragonbc:cc/arc/" + (gen == 1 ? "f" : "m") + "/1A" + JRMCoreH.TransFrSkn[State] + plyrSpc + ".png");
                    this.func_110776_a(bdyskn);
                    this.glColor3f(bodyc1);
                    this.modelMain.renderBody(0.0625f, 1, breast);
                    this.func_110776_a(new ResourceLocation("jinryuudragonbc:cc/arc/" + (gen == 1 ? "f" : "m") + "/2B" + JRMCoreH.TransFrSkn2[State] + plyrSpc + ".png"));
                    this.glColor3f(bodyc2);
                    this.modelMain.renderHairs(0.0625f, "FR" + JRMCoreH.TransFrHrn[State]);
                    this.curSkin = bdyskn = new ResourceLocation("jinryuudragonbc:cc/arc/" + (gen == 1 ? "f" : "m") + "/2A" + JRMCoreH.TransFrSkn[State] + plyrSpc + ".png");
                    this.func_110776_a(bdyskn);
                    this.glColor3f(bodyc2);
                    this.modelMain.renderBody(0.0625f, 1, breast);
                    this.func_110776_a(new ResourceLocation("jinryuudragonbc:cc/arc/" + (gen == 1 ? "f" : "m") + "/3B" + JRMCoreH.TransFrSkn2[State] + plyrSpc + ".png"));
                    this.glColor3f(bodyc3);
                    this.modelMain.renderHairs(0.0625f, "FR" + JRMCoreH.TransFrHrn[State]);
                    this.curSkin = bdyskn = new ResourceLocation("jinryuudragonbc:cc/arc/" + (gen == 1 ? "f" : "m") + "/3A" + JRMCoreH.TransFrSkn[State] + plyrSpc + ".png");
                    this.func_110776_a(bdyskn);
                    this.glColor3f(bodyc3);
                    this.modelMain.renderBody(0.0625f, 1, breast);
                    this.func_110776_a(new ResourceLocation("jinryuudragonbc:cc/arc/" + (gen == 1 ? "f" : "m") + "/4B" + JRMCoreH.TransFrSkn2[State] + plyrSpc + ".png"));
                    float h1 = 1.0f;
                    GL11.glColor3f((float)h1, (float)h1, (float)h1);
                    this.modelMain.renderHairs(0.0625f, "FR" + JRMCoreH.TransFrHrn[State]);
                    this.curSkin = bdyskn = new ResourceLocation("jinryuudragonbc:cc/arc/" + (gen == 1 ? "f" : "m") + "/4A" + JRMCoreH.TransFrSkn[State] + plyrSpc + ".png");
                    this.func_110776_a(bdyskn);
                    GL11.glColor3f((float)h1, (float)h1, (float)h1);
                    this.modelMain.renderBody(0.0625f, 1, breast);
                    this.func_110776_a(new ResourceLocation("jinryuudragonbc", "cc/arc/" + (gen == 1 ? "f" : "m") + "/4A" + JRMCoreH.TransFrSkn[State] + plyrSpc + "n" + facen + ".png"));
                    this.glColor3f(bodyc1);
                    this.modelMain.renderHairs(0.0625f, "FACENOSE");
                    this.func_110776_a(new ResourceLocation("jinryuudragonbc", "cc/arc/" + (gen == 1 ? "f" : "m") + "/4A" + JRMCoreH.TransFrSkn[State] + plyrSpc + "m" + facem + ".png"));
                    this.glColor3f(bodyc1);
                    this.modelMain.renderHairs(0.0625f, "FACEMOUTH");
                    this.func_110776_a(new ResourceLocation("jinryuudragonbc", "cc/arc/" + (gen == 1 ? "f" : "m") + "/4A" + JRMCoreH.TransFrSkn[State] + plyrSpc + "b" + eyes + ".png"));
                    GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
                    this.modelMain.renderHairs(0.0625f, "EYEBASE");
                    this.func_110776_a(new ResourceLocation("jinryuudragonbc", "cc/arc/" + (gen == 1 ? "f" : "m") + "/4A" + JRMCoreH.TransFrSkn[State] + plyrSpc + "l" + eyes + ".png"));
                    this.glColor3f(eyec1);
                    this.modelMain.renderHairs(0.0625f, "EYELEFT");
                    this.func_110776_a(new ResourceLocation("jinryuudragonbc", "cc/arc/" + (gen == 1 ? "f" : "m") + "/4A" + JRMCoreH.TransFrSkn[State] + plyrSpc + "r" + eyes + ".png"));
                    this.glColor3f(eyec2);
                    this.modelMain.renderHairs(0.0625f, "EYERIGHT");
                } else {
                    int j;
                    if (skintype != 0) {
                        ResourceLocation bdyskn;
                        this.curSkin = bdyskn = new ResourceLocation("jinryuumodscore:cc/" + (gen == 1 ? "f" : "") + "hum.png");
                        this.func_110776_a(bdyskn);
                        this.glColor3f(bodycm);
                        this.modelMain.renderBody(0.0625f, 1, breast);
                        this.func_110776_a(new ResourceLocation("jinryuumodscore", "cc/" + (gen == 1 ? "f" : "") + "humn" + facen + ".png"));
                        this.glColor3f(bodycm);
                        this.modelMain.renderHairs(0.0625f, "FACENOSE");
                        this.func_110776_a(new ResourceLocation("jinryuumodscore", "cc/" + (gen == 1 ? "f" : "") + "humm" + facem + ".png"));
                        this.glColor3f(bodycm);
                        this.modelMain.renderHairs(0.0625f, "FACEMOUTH");
                        this.func_110776_a(new ResourceLocation("jinryuumodscore", "cc/" + (gen == 1 ? "f" : "") + "humb" + eyes + ".png"));
                        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
                        this.modelMain.renderHairs(0.0625f, "EYEBASE");
                        this.func_110776_a(new ResourceLocation("jinryuumodscore", "cc/" + (gen == 1 ? "f" : "") + "huml" + eyes + ".png"));
                        this.glColor3f((race == 1 || race == 2) && State != 0 ? superecol : eyec1);
                        this.modelMain.renderHairs(0.0625f, "EYELEFT");
                        this.func_110776_a(new ResourceLocation("jinryuumodscore", "cc/" + (gen == 1 ? "f" : "") + "humr" + eyes + ".png"));
                        this.glColor3f((race == 1 || race == 2) && State != 0 ? superecol : eyec2);
                        this.modelMain.renderHairs(0.0625f, "EYERIGHT");
                        this.func_110776_a(new ResourceLocation("jinryuumodscore", "cc/" + (gen == 1 ? "f" : "") + "humw" + eyes + ".png"));
                        if ((race == 1 || race == 2) && State != 0) {
                            this.glColor3f(superhcol);
                        } else {
                            this.glColor3f(haircol, this.age);
                        }
                        this.modelMain.renderHairs(0.0625f, "EYEBROW");
                    }
                    if ((race == 1 || race == 2) && dbc) {
                        float h1 = 1.0f;
                        j = State == 0 ? (skintype == 1 ? bodyc1 : 6498048) : 16574610;
                        this.func_110776_a(new ResourceLocation("jinryuudragonbc:gui/allw.png"));
                        this.glColor3f(j);
                        this.modelMain.renderHairs(0.0625f, ts == 0 || ts == -1 ? "SJT1" : (ts == 1 ? "SJT2" : ""));
                    }
                    float h1 = 1.0f;
                    j = haircol;
                    int Hair = hairback;
                    if (!(Hair != 8 && Hair != 9 || State != 0 && State != 1)) {
                        j = State != 0 ? 16574610 : j;
                        s1 = Hair == 8 ? "c2" : "c1";
                        this.func_110776_a(new ResourceLocation("jinryuumodscore:gui/" + s1 + ".png"));
                    } else if (Hair >= 0 && Hair <= 12) {
                        j = State != 0 ? 16574610 : j;
                        s1 = State == 0 ? "normall" : "superall";
                        this.func_110776_a(new ResourceLocation("jinryuumodscore:gui/" + s1 + ".png"));
                    }
                    if (State != 0) {
                        this.glColor3f(j);
                    } else {
                        this.glColor3f(j, this.age);
                    }
                    if (Hair == 12) {
                        this.func_110776_a(new ResourceLocation("jinryuumodscore:gui/normall.png"));
                        if (JRMCoreGuiScreen.hairPreview > 0) {
                            State = JRMCoreGuiScreen.hairPreviewStates[JRMCoreGuiScreen.hairPreview];
                        }
                        if (State == 6) {
                            this.modelMain.renderHairs(0.0625f, "" + JRMCoreH.HairsT[6] + JRMCoreH.Hairs[0]);
                        } else {
                            this.modelMain.renderHairsV2(0.0625f, this.dnsH, 0.0f, State, 0, e.func_145782_y(), race, this);
                        }
                    } else if (Hair != 10) {
                        this.modelMain.renderHairs(0.0625f, "" + JRMCoreH.HairsT[State] + JRMCoreH.Hairs[Hair]);
                    }
                }
                GL11.glPopMatrix();
                if (dbc) {
                    Item item;
                    int armr = 0;
                    ItemStack itemstack2 = e.func_130225_q(3 - armr);
                    GL11.glPushMatrix();
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)771);
                    if (itemstack2 != null && (item = itemstack2.func_77973_b()) instanceof ItemArmor) {
                        ItemArmor itemarmor;
                        ItemArmor var6 = itemarmor = (ItemArmor)item;
                        ItemStack par1 = itemstack2;
                        boolean normalRender = false;
                        if (JRMCoreH.DBC()) {
                            ItemArmor.ArmorMaterial all = null;
                            ItemArmor.ArmorMaterial scouter1 = this.DBCArmorMatscouter1();
                            ItemArmor.ArmorMaterial scouter2 = this.DBCArmorMatscouter2();
                            ItemArmor.ArmorMaterial scouter3 = this.DBCArmorMatscouter3();
                            if (var6.func_82812_d() == scouter1) {
                                all = scouter1;
                            }
                            if (var6.func_82812_d() == scouter2) {
                                all = scouter2;
                            }
                            if (var6.func_82812_d() == scouter3) {
                                all = scouter3;
                            }
                            ItemArmor.ArmorMaterial loaded = all;
                            if (var6.func_82812_d() == all) {
                                this.func_110776_a(RenderBiped.getArmorResource((Entity)e, (ItemStack)itemstack2, (int)armr, null));
                                this.modelMain.field_78116_c.field_78806_j = armr == 0;
                                this.modelMain.renderHairs(0.0625f, "SC");
                            }
                        }
                    }
                    GL11.glPopMatrix();
                }
                boolean dead2 = false;
                if (dbc && dead2) {
                    GL11.glPushMatrix();
                    this.func_110776_a(new ResourceLocation("jinryuudragonbc:armor/halo.png"));
                    GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
                    this.modelMain.renderHalo(0.0625f);
                    GL11.glPopMatrix();
                }
            }
        }
        if (dead = false) {
            GL11.glPushMatrix();
            this.func_110776_a(new ResourceLocation("jinryuudragonbc:armor/halo.png"));
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            this.modelMain.renderHalo(0.0625f);
            GL11.glPopMatrix();
        }
    }

    private ItemArmor.ArmorMaterial DBCArmorMatGI() {
        return ItemsDBC.GI;
    }

    private ItemArmor.ArmorMaterial DBCArmorMattier0() {
        return ItemsDBC.tier0;
    }

    private ItemArmor.ArmorMaterial DBCArmorMattier1() {
        return ItemsDBC.tier1;
    }

    private ItemArmor.ArmorMaterial DBCArmorMattier2() {
        return ItemsDBC.tier2;
    }

    private ItemArmor.ArmorMaterial DBCArmorMattier3() {
        return ItemsDBC.tier3;
    }

    private ItemArmor.ArmorMaterial DBCArmorMatscouter1() {
        return ItemsDBC.scouter1;
    }

    private ItemArmor.ArmorMaterial DBCArmorMatscouter2() {
        return ItemsDBC.scouter2;
    }

    private ItemArmor.ArmorMaterial DBCArmorMatscouter3() {
        return ItemsDBC.scouter3;
    }

    protected void func_77033_b(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6) {
        if (MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Specials.Pre(par1EntityLivingBase, (RendererLivingEntity)this, par2, par4, par6))) {
            return;
        }
        if (this.b) {
            float f2;
            float f = 1.6f;
            float f1 = 0.016666668f * f;
            double d3 = par1EntityLivingBase.func_70068_e((Entity)this.field_76990_c.field_78734_h);
            float f3 = f2 = par1EntityLivingBase.func_70093_af() ? NAME_TAG_RANGE_SNEAK : NAME_TAG_RANGE;
            if (d3 < (double)(f2 * f2)) {
                String s = ((EntityNPC)par1EntityLivingBase).getNam();
                if (par1EntityLivingBase.func_70093_af()) {
                    FontRenderer fontrenderer = this.func_76983_a();
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)((float)par2 + 0.0f), (float)((float)par4 + par1EntityLivingBase.field_70131_O + 0.5f), (float)((float)par6));
                    GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)(-this.field_76990_c.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glRotatef((float)this.field_76990_c.field_78732_j, (float)1.0f, (float)0.0f, (float)0.0f);
                    GL11.glScalef((float)(-f1), (float)(-f1), (float)f1);
                    GL11.glDisable((int)2896);
                    GL11.glTranslatef((float)0.0f, (float)(0.25f / f1), (float)0.0f);
                    GL11.glDepthMask((boolean)false);
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)771);
                    Tessellator tessellator = Tessellator.field_78398_a;
                    GL11.glDisable((int)3553);
                    tessellator.func_78382_b();
                    int i = fontrenderer.func_78256_a(s) / 2;
                    tessellator.func_78369_a(0.0f, 0.0f, 0.0f, 0.25f);
                    tessellator.func_78377_a((double)(-i - 1), -1.0, 0.0);
                    tessellator.func_78377_a((double)(-i - 1), 8.0, 0.0);
                    tessellator.func_78377_a((double)(i + 1), 8.0, 0.0);
                    tessellator.func_78377_a((double)(i + 1), -1.0, 0.0);
                    tessellator.func_78381_a();
                    GL11.glEnable((int)3553);
                    GL11.glDepthMask((boolean)true);
                    fontrenderer.func_78276_b(s, -fontrenderer.func_78256_a(s) / 2, 0, 0x20FFFFFF);
                    GL11.glEnable((int)2896);
                    GL11.glDisable((int)3042);
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    GL11.glPopMatrix();
                } else {
                    this.func_96449_a(par1EntityLivingBase, par2, par4, par6, s, f1, d3);
                }
            }
        }
        MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Specials.Post(par1EntityLivingBase, (RendererLivingEntity)this, par2, par4, par6));
    }

    protected void renderLivingLabel(EntityLivingBase par1EntityLivingBase, String par2Str, double par3, double par5, double par7, int par9) {
        double d3 = par1EntityLivingBase.func_70068_e((Entity)this.field_76990_c.field_78734_h);
        if (d3 <= (double)(par9 * par9)) {
            FontRenderer fontrenderer = this.func_76983_a();
            float f = 1.6f;
            float f1 = 0.016666668f * f;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)par3 + 0.0f), (float)((float)par5 + par1EntityLivingBase.field_70131_O + 0.7f), (float)((float)par7));
            GL11.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(-this.field_76990_c.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)this.field_76990_c.field_78732_j, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glScalef((float)(-f1), (float)(-f1), (float)f1);
            GL11.glDisable((int)2896);
            GL11.glDepthMask((boolean)false);
            GL11.glDisable((int)2929);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            Tessellator tessellator = Tessellator.field_78398_a;
            int b0 = 0;
            if (par2Str.equals("deadmau5")) {
                b0 = -10;
            }
            GL11.glDisable((int)3553);
            tessellator.func_78382_b();
            int j = fontrenderer.func_78256_a(par2Str) / 2;
            tessellator.func_78369_a(0.0f, 0.0f, 0.0f, 0.25f);
            tessellator.func_78377_a((double)(-j - 1), (double)(-1 + b0), 0.0);
            tessellator.func_78377_a((double)(-j - 1), (double)(8 + b0), 0.0);
            tessellator.func_78377_a((double)(j + 1), (double)(8 + b0), 0.0);
            tessellator.func_78377_a((double)(j + 1), (double)(-1 + b0), 0.0);
            tessellator.func_78381_a();
            GL11.glEnable((int)3553);
            fontrenderer.func_78276_b(par2Str, -fontrenderer.func_78256_a(par2Str) / 2, b0, 0x20FFFFFF);
            GL11.glEnable((int)2929);
            GL11.glDepthMask((boolean)true);
            fontrenderer.func_78276_b(par2Str, -fontrenderer.func_78256_a(par2Str) / 2, b0, -1);
            GL11.glEnable((int)2896);
            GL11.glDisable((int)3042);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glPopMatrix();
        }
    }

    public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
        if (MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Pre(par1EntityLivingBase, (RendererLivingEntity)this, par2, par4, par6))) {
            return;
        }
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        this.modelMain.field_78095_p = this.func_77040_d(par1EntityLivingBase, par9);
        if (this.field_77046_h != null) {
            this.field_77046_h.field_78095_p = this.modelMain.field_78095_p;
        }
        this.modelMain.field_78093_q = par1EntityLivingBase.func_70115_ae();
        if (this.field_77046_h != null) {
            this.field_77046_h.field_78093_q = this.modelMain.field_78093_q;
        }
        this.modelMain.field_78091_s = par1EntityLivingBase.func_70631_g_();
        if (this.field_77046_h != null) {
            this.field_77046_h.field_78091_s = this.modelMain.field_78091_s;
        }
        try {
            float f11;
            float f10;
            float f9;
            int i;
            float f4;
            float f2 = this.interpolateRotation(par1EntityLivingBase.field_70760_ar, par1EntityLivingBase.field_70761_aq, par9);
            float f3 = this.interpolateRotation(par1EntityLivingBase.field_70758_at, par1EntityLivingBase.field_70759_as, par9);
            if (par1EntityLivingBase.func_70115_ae() && par1EntityLivingBase.field_70154_o instanceof EntityLivingBase) {
                EntityLivingBase entitylivingbase1 = (EntityLivingBase)par1EntityLivingBase.field_70154_o;
                f2 = this.interpolateRotation(entitylivingbase1.field_70760_ar, entitylivingbase1.field_70761_aq, par9);
                f4 = MathHelper.func_76142_g((float)(f3 - f2));
                if (f4 < -85.0f) {
                    f4 = -85.0f;
                }
                if (f4 >= 85.0f) {
                    f4 = 85.0f;
                }
                f2 = f3 - f4;
                if (f4 * f4 > 2500.0f) {
                    f2 += f4 * 0.2f;
                }
            }
            float f5 = par1EntityLivingBase.field_70127_C + (par1EntityLivingBase.field_70125_A - par1EntityLivingBase.field_70127_C) * par9;
            this.func_77039_a(par1EntityLivingBase, par2, par4, par6);
            f4 = this.func_77044_a(par1EntityLivingBase, par9);
            this.func_77043_a(par1EntityLivingBase, f4, f2, par9);
            float f6 = 0.0625f;
            GL11.glEnable((int)32826);
            GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
            this.func_77041_b(par1EntityLivingBase, par9);
            GL11.glTranslatef((float)0.0f, (float)(-24.0f * f6 - 0.0078125f), (float)0.0f);
            float f7 = par1EntityLivingBase.field_70722_aY + (par1EntityLivingBase.field_70721_aZ - par1EntityLivingBase.field_70722_aY) * par9;
            float f8 = par1EntityLivingBase.field_70754_ba - par1EntityLivingBase.field_70721_aZ * (1.0f - par9);
            if (par1EntityLivingBase.func_70631_g_()) {
                f8 *= 3.0f;
            }
            if (f7 > 1.0f) {
                f7 = 1.0f;
            }
            GL11.glEnable((int)3008);
            this.modelMain.func_78086_a(par1EntityLivingBase, f8, f7, par9);
            this.func_77036_a(par1EntityLivingBase, f8, f7, f4, f3 - f2, f5, f6);
            for (int j = 0; j < 4; ++j) {
                i = this.func_77032_a(par1EntityLivingBase, j, par9);
                if (i <= 0) continue;
                this.field_77046_h.func_78086_a(par1EntityLivingBase, f8, f7, par9);
                this.field_77046_h.func_78088_a((Entity)par1EntityLivingBase, f8, f7, f4, f3 - f2, f5, f6);
                if ((i & 0xF0) == 16) {
                    this.func_82408_c(par1EntityLivingBase, j, par9);
                    this.field_77046_h.func_78088_a((Entity)par1EntityLivingBase, f8, f7, f4, f3 - f2, f5, f6);
                }
                if ((i & 0xF) == 15) {
                    f9 = (float)par1EntityLivingBase.field_70173_aa + par9;
                    this.func_110776_a(RES_ITEM_GLINT);
                    GL11.glEnable((int)3042);
                    f10 = 0.5f;
                    GL11.glColor4f((float)f10, (float)f10, (float)f10, (float)1.0f);
                    GL11.glDepthFunc((int)514);
                    GL11.glDepthMask((boolean)false);
                    for (int k = 0; k < 2; ++k) {
                        GL11.glDisable((int)2896);
                        f11 = 0.76f;
                        GL11.glColor4f((float)(0.5f * f11), (float)(0.25f * f11), (float)(0.8f * f11), (float)1.0f);
                        GL11.glBlendFunc((int)768, (int)1);
                        GL11.glMatrixMode((int)5890);
                        GL11.glLoadIdentity();
                        float f12 = f9 * (0.001f + (float)k * 0.003f) * 20.0f;
                        float f13 = 0.33333334f;
                        GL11.glScalef((float)f13, (float)f13, (float)f13);
                        GL11.glRotatef((float)(30.0f - (float)k * 60.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                        GL11.glTranslatef((float)0.0f, (float)f12, (float)0.0f);
                        GL11.glMatrixMode((int)5888);
                        this.field_77046_h.func_78088_a((Entity)par1EntityLivingBase, f8, f7, f4, f3 - f2, f5, f6);
                    }
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    GL11.glMatrixMode((int)5890);
                    GL11.glDepthMask((boolean)true);
                    GL11.glLoadIdentity();
                    GL11.glMatrixMode((int)5888);
                    GL11.glEnable((int)2896);
                    GL11.glDisable((int)3042);
                    GL11.glDepthFunc((int)515);
                }
                GL11.glDisable((int)3042);
                GL11.glEnable((int)3008);
            }
            GL11.glDepthMask((boolean)true);
            this.func_77029_c(par1EntityLivingBase, par9);
            float f14 = par1EntityLivingBase.func_70013_c(par9);
            i = this.func_77030_a(par1EntityLivingBase, f14, par9);
            OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77476_b);
            GL11.glDisable((int)3553);
            OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77478_a);
            if ((i >> 24 & 0xFF) > 0 || par1EntityLivingBase.field_70737_aN > 0 || par1EntityLivingBase.field_70725_aQ > 0) {
                GL11.glDisable((int)3553);
                GL11.glDisable((int)3008);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glDepthFunc((int)514);
                if (par1EntityLivingBase.field_70737_aN > 0 || par1EntityLivingBase.field_70725_aQ > 0) {
                    GL11.glColor4f((float)f14, (float)0.0f, (float)0.0f, (float)0.4f);
                    this.modelMain.func_78088_a((Entity)par1EntityLivingBase, f8, f7, f4, f3 - f2, f5, f6);
                    for (int l = 0; l < 4; ++l) {
                        if (this.func_77035_b(par1EntityLivingBase, l, par9) < 0) continue;
                        GL11.glColor4f((float)f14, (float)0.0f, (float)0.0f, (float)0.4f);
                        this.field_77046_h.func_78088_a((Entity)par1EntityLivingBase, f8, f7, f4, f3 - f2, f5, f6);
                    }
                }
                if ((i >> 24 & 0xFF) > 0) {
                    f9 = (float)(i >> 16 & 0xFF) / 255.0f;
                    f10 = (float)(i >> 8 & 0xFF) / 255.0f;
                    float f15 = (float)(i & 0xFF) / 255.0f;
                    f11 = (float)(i >> 24 & 0xFF) / 255.0f;
                    GL11.glColor4f((float)f9, (float)f10, (float)f15, (float)f11);
                    this.modelMain.func_78088_a((Entity)par1EntityLivingBase, f8, f7, f4, f3 - f2, f5, f6);
                    for (int i1 = 0; i1 < 4; ++i1) {
                        if (this.func_77035_b(par1EntityLivingBase, i1, par9) < 0) continue;
                        GL11.glColor4f((float)f9, (float)f10, (float)f15, (float)f11);
                        this.field_77046_h.func_78088_a((Entity)par1EntityLivingBase, f8, f7, f4, f3 - f2, f5, f6);
                    }
                }
                GL11.glDepthFunc((int)515);
                GL11.glDisable((int)3042);
                GL11.glEnable((int)3008);
                GL11.glEnable((int)3553);
            }
            GL11.glDisable((int)32826);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77476_b);
        GL11.glEnable((int)3553);
        OpenGlHelper.func_77473_a((int)OpenGlHelper.field_77478_a);
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
        this.func_77033_b(par1EntityLivingBase, par2, par4, par6);
        MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Post(par1EntityLivingBase, (RendererLivingEntity)this, par2, par4, par6));
    }

    private float interpolateRotation(float par1, float par2, float par3) {
        float f3;
        for (f3 = par2 - par1; f3 < -180.0f; f3 += 360.0f) {
        }
        while (f3 >= 180.0f) {
            f3 -= 360.0f;
        }
        return par1 + par3 * f3;
    }

    public int getState(int pl) {
        return state.get(pl) == null ? 0 : state.get(pl);
    }

    public void setState(int state, int pl) {
        RenderJFC.state.put(pl, state);
    }

    public int getStateChange(int pl) {
        return stateChange.get(pl) == null ? 0 : stateChange.get(pl);
    }

    public void setStateChange(int stateChange, int pl) {
        RenderJFC.stateChange.put(pl, stateChange);
    }

    public int getState2Change(int pl) {
        return state2Change.get(pl) == null ? 0 : state2Change.get(pl);
    }

    public void setState2Change(int state2Change, int pl) {
        RenderJFC.state2Change.put(pl, state2Change);
    }

    public int getAuratype(int pl) {
        return auratype.get(pl) == null ? 0 : auratype.get(pl);
    }

    public void setAuratype(int auratype, int pl) {
        RenderJFC.auratype.put(pl, auratype);
    }

    public int getAuratime(int pl) {
        return auratime.get(pl) == null ? 0 : auratime.get(pl);
    }

    public void setAuratime(int auratime, int pl) {
        RenderJFC.auratime.put(pl, auratime);
    }

    public int getBendtime(int pl) {
        return bendtime.get(pl) == null ? 0 : bendtime.get(pl);
    }

    public void setBendtime(int bendtime, int pl) {
        RenderJFC.bendtime.put(pl, bendtime);
    }
}

