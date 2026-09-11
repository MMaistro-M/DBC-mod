/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.entity.RenderBiped
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.ForgeHooksClient
 *  net.minecraftforge.client.event.RenderPlayerEvent$SetArmorModel
 *  net.minecraftforge.client.event.RenderPlayerEvent$Specials$Post
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JBRA;

import JinRyuu.JBRA.ModelBipedDBC;
import JinRyuu.JBRA.RenderPlayerJBRA;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHJBRA;
import JinRyuu.JRMCore.JRMCoreHNC;
import JinRyuu.JRMCore.entity.ModelBipedBody;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import JinRyuu.JRMCore.items.ItemBodysuit;
import JinRyuu.JRMCore.items.ItemHeadwear;
import JinRyuu.JRMCore.items.ItemVanity;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;

public class JBRAEH {
    public ModelBiped armrMdl = JRMCoreHJBRA.ModelBipedBody(1.0f);
    public ModelBiped armrMdl2 = JRMCoreHJBRA.ModelBipedBody(0.5f);
    @SideOnly(value=Side.CLIENT)
    public static final ModelBipedDBC body = new ModelBipedDBC(0.0f);

    @SubscribeEvent
    public void onRenderLivingEvent(RenderPlayerEvent.Specials.Post event) {
        if (event.renderer instanceof RenderPlayerJBRA) {
            byte clientState;
            int i;
            ItemStack stackhead;
            boolean v;
            String[] s;
            int pwr;
            RenderPlayerJBRA r = (RenderPlayerJBRA)event.renderer;
            EntityPlayer pl = event.entityPlayer;
            ModelBipedDBC mdl = r.modelMain;
            if (JRMCoreH.NC()) {
                float f;
                float childScl;
                float gen;
                float fm;
                int idd = ExtendedPlayer.get(pl).getHandEffect();
                int idd2 = ExtendedPlayer.get(pl).getEffect_used();
                if (idd == 1) {
                    GL11.glPushMatrix();
                    fm = 0.0f;
                    gen = r.genGet();
                    childScl = r.childSclGet();
                    if (gen <= 1.0f) {
                        GL11.glScalef((float)(1.0f / childScl), (float)(1.0f / childScl), (float)(1.0f / childScl));
                        GL11.glTranslatef((float)0.0f, (float)((childScl - 1.0f) * 1.5f), (float)0.0f);
                        mdl.RA.func_78794_c(0.0625f);
                        fm = 0.0f;
                    }
                    if (gen >= 2.0f) {
                        GL11.glScalef((float)(1.0f / childScl * (gen <= 1.0f ? 1.0f : 0.7f)), (float)(1.0f / childScl), (float)(1.0f / childScl * (gen <= 1.0f ? 1.0f : 0.7f)));
                        GL11.glTranslatef((float)0.0f, (float)((childScl - 1.0f) * 1.5f), (float)0.0f);
                        mdl.RA.func_78794_c(0.0625f);
                        fm = 0.1f;
                    }
                    f = childScl;
                    GL11.glRotatef((float)6.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)-0.29f, (float)0.15f, (float)0.0f);
                    r.chakra((Entity)pl, idd2);
                    RenderPlayerJBRA.hndff((Entity)pl, false, idd, idd2);
                    GL11.glPopMatrix();
                }
                if (idd == 2) {
                    GL11.glPushMatrix();
                    fm = 0.0f;
                    gen = r.genGet();
                    childScl = r.childSclGet();
                    if (gen <= 1.0f) {
                        GL11.glScalef((float)(1.0f / childScl), (float)(1.0f / childScl), (float)(1.0f / childScl));
                        GL11.glTranslatef((float)0.0f, (float)((childScl - 1.0f) * 1.5f), (float)0.0f);
                        mdl.RA.func_78794_c(0.0625f);
                        fm = 0.0f;
                    }
                    if (gen >= 2.0f) {
                        GL11.glScalef((float)(1.0f / childScl * (gen <= 1.0f ? 1.0f : 0.7f)), (float)(1.0f / childScl), (float)(1.0f / childScl * (gen <= 1.0f ? 1.0f : 0.7f)));
                        GL11.glTranslatef((float)0.0f, (float)((childScl - 1.0f) * 1.5f), (float)0.0f);
                        mdl.RA.func_78794_c(0.0625f);
                        fm = 0.1f;
                    }
                    f = childScl;
                    GL11.glRotatef((float)6.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)-0.29f, (float)0.15f, (float)0.0f);
                    r.lightning((Entity)pl, idd2);
                    RenderPlayerJBRA.hndff((Entity)pl, false, idd, idd2);
                    GL11.glPopMatrix();
                }
            }
            if (JRMCoreH.DBC() && (pwr = Integer.parseInt((s = JRMCoreH.data(event.entity.func_70005_c_(), 1, "0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0").split(";"))[2])) == 1) {
                boolean v2;
                int skf;
                String datas = JRMCoreH.data(event.entity.func_70005_c_(), 6, "0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0");
                int sklkf = datas.contains("KF") ? 1 : 0;
                int n = skf = datas.contains("KI") ? 1 : 0;
                if (sklkf > 0 && skf > 0) {
                    String datas2 = datas.split(";")[0];
                    int lngth = datas2.split(",").length;
                    for (int i2 = 0; i2 < lngth; ++i2) {
                        String dt;
                        String datas3 = datas2.split(",")[i2];
                        if (datas3.contains("KF")) {
                            dt = datas3.replace("KF", "");
                            sklkf = Integer.parseInt(dt);
                            ++sklkf;
                            continue;
                        }
                        if (!datas3.contains("KI")) continue;
                        dt = datas3.replace("KI", "");
                        skf = Integer.parseInt(dt);
                        ++skf;
                    }
                }
                GL11.glPushMatrix();
                String ss = s[17];
                boolean bl = v2 = JRMCoreH.DBC() && !ss.equals("-1");
                if (v2 && sklkf > 0 && skf > 0) {
                    float fm = 0.0f;
                    float gen = r.genGet();
                    float childScl = r.childSclGet();
                    if (gen <= 1.0f) {
                        GL11.glScalef((float)(1.0f / childScl), (float)(1.0f / childScl), (float)(1.0f / childScl));
                        GL11.glTranslatef((float)0.0f, (float)((childScl - 1.0f) * 1.5f), (float)0.0f);
                        mdl.RA.func_78794_c(0.0625f);
                        fm = 0.0f;
                    }
                    if (gen >= 2.0f) {
                        GL11.glScalef((float)(1.0f / childScl * (gen <= 1.0f ? 1.0f : 0.7f)), (float)(1.0f / childScl), (float)(1.0f / childScl * (gen <= 1.0f ? 1.0f : 0.7f)));
                        GL11.glTranslatef((float)0.0f, (float)((childScl - 1.0f) * 1.5f), (float)0.0f);
                        mdl.RA.func_78794_c(0.0625f);
                        fm = 0.1f;
                    }
                    float f = childScl;
                    RenderPlayerJBRA.kss(event.entity, false, Integer.parseInt(ss), sklkf, skf);
                }
                GL11.glPopMatrix();
            }
            GL11.glPushMatrix();
            s = JRMCoreH.data(event.entity.func_70005_c_(), 1, "0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0").split(";")[16];
            boolean bl = v = JRMCoreH.JYC() && !s.equals("-1");
            if (v) {
                float fm = 0.0f;
                float gen = r.genGet();
                float childScl = r.childSclGet();
                if (gen <= 1.0f) {
                    GL11.glScalef((float)(1.0f / childScl), (float)(1.0f / childScl), (float)(1.0f / childScl));
                    GL11.glTranslatef((float)0.0f, (float)((childScl - 1.0f) * 1.5f), (float)0.0f);
                    mdl.RA.func_78794_c(0.0625f);
                    fm = 0.0f;
                }
                if (gen >= 2.0f) {
                    GL11.glScalef((float)(1.0f / childScl * (gen <= 1.0f ? 1.0f : 0.7f)), (float)(1.0f / childScl), (float)(1.0f / childScl * (gen <= 1.0f ? 1.0f : 0.7f)));
                    GL11.glTranslatef((float)0.0f, (float)((childScl - 1.0f) * 1.5f), (float)0.0f);
                    mdl.RA.func_78794_c(0.0625f);
                    fm = 0.1f;
                }
                float f = childScl;
                RenderPlayerJBRA.ow(false);
            }
            GL11.glPopMatrix();
            s = JRMCoreH.data(event.entity.func_70005_c_(), 1, "0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0").split(";");
            String[] sw = s[5].split(",");
            int weight = Integer.parseInt(sw[0]);
            String[] slotbody = s[6].split(",");
            int body = Integer.parseInt(slotbody[0]);
            int head = Integer.parseInt(s[7]);
            if (body > 0) {
                ItemStack stackbody = new ItemStack(Item.func_150899_d((int)body));
                int bodycol = Integer.parseInt(slotbody[1]);
                if (stackbody != null && stackbody.func_77973_b() instanceof ItemBodysuit) {
                    ((ItemBodysuit)stackbody.func_77973_b()).setColor(stackbody, bodycol);
                    int j = ((ItemBodysuit)stackbody.func_77973_b()).getColor(stackbody);
                    if (j != -1) {
                        float f1 = (float)(j >> 16 & 0xFF) / 255.0f;
                        float f2 = (float)(j >> 8 & 0xFF) / 255.0f;
                        float f3 = (float)(j & 0xFF) / 255.0f;
                        GL11.glColor3f((float)f1, (float)f2, (float)f3);
                    }
                    ResourceLocation rl = new ResourceLocation(((ItemBodysuit)stackbody.func_77973_b()).getArmorTexture(stackbody, (Entity)pl, 0, ""));
                    JRMCoreClient.mc.func_110434_K().func_110577_a(rl);
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)771);
                    ModelBipedBody m = (ModelBipedBody)JRMCoreHJBRA.GiTurtleMdl3;
                    m = (ModelBipedBody)JRMCoreHJBRA.showModel(m, (EntityLivingBase)pl, stackbody, 4);
                    m.field_78095_p = pl.func_70678_g(event.partialRenderTick);
                    m.field_78093_q = pl.func_70115_ae();
                    m.field_78091_s = pl.func_70631_g_();
                    m.field_78117_n = pl.func_70093_af();
                    ModelBipedBody.y = ModelBipedDBC.y;
                    m.func_78088_a((Entity)pl, mdl.rot1, mdl.rot2, mdl.rot3, mdl.rot4, mdl.rot5, mdl.rot6);
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                }
            }
            if (head > 0 && (stackhead = new ItemStack(Item.func_150899_d((int)head))) != null && stackhead.func_77973_b() instanceof ItemHeadwear) {
                ResourceLocation rl = new ResourceLocation(((ItemHeadwear)stackhead.func_77973_b()).getArmorTexture(stackhead, (Entity)pl, 0, ""));
                JRMCoreClient.mc.func_110434_K().func_110577_a(rl);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                ModelBipedBody m = (ModelBipedBody)JRMCoreHJBRA.GiTurtleMdl2;
                m = (ModelBipedBody)JRMCoreHJBRA.showModel(m, (EntityLivingBase)pl, stackhead, 0);
                m.field_78095_p = pl.func_70678_g(event.partialRenderTick);
                m.field_78093_q = pl.func_70115_ae();
                m.field_78091_s = pl.func_70631_g_();
                m.field_78117_n = pl.func_70093_af();
                m.func_78088_a((Entity)pl, mdl.rot1, mdl.rot2, mdl.rot3, mdl.rot4, mdl.rot5, mdl.rot6);
            }
            if (weight == 2) {
                String armor = "";
                String d = "";
                int wd = Integer.parseInt(sw[1]);
                if (wd > 50) {
                    d = "_d";
                }
                armor = "armor/weightshirt" + d + ".png";
                JRMCoreClient.mc.func_110434_K().func_110577_a(new ResourceLocation(JRMCoreH.tjdbcAssts, armor));
                GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                ModelBipedBody m = (ModelBipedBody)JRMCoreHJBRA.GiTurtleMdl4;
                m = (ModelBipedBody)JRMCoreHJBRA.showModel(m, (EntityLivingBase)pl, null, 4);
                m.field_78095_p = pl.func_70678_g(event.partialRenderTick);
                m.field_78093_q = pl.func_70115_ae();
                m.field_78091_s = pl.func_70631_g_();
                m.field_78117_n = pl.func_70093_af();
                ModelBipedBody.y = ModelBipedDBC.y;
                m.func_78088_a((Entity)pl, mdl.rot1, mdl.rot2, mdl.rot3, mdl.rot4, mdl.rot5, mdl.rot6);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            }
            if (weight == 3) {
                String armor = "";
                String d = "";
                int wd = Integer.parseInt(sw[1]);
                if (wd > 50) {
                    d = "_d";
                }
                armor = "armor/weightcape" + d + ".png";
                JRMCoreClient.mc.func_110434_K().func_110577_a(new ResourceLocation(JRMCoreH.tjdbcAssts, armor));
                GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                ModelBipedBody m = (ModelBipedBody)JRMCoreHJBRA.GiTurtleMdl1;
                m = (ModelBipedBody)JRMCoreHJBRA.showModel(m, (EntityLivingBase)pl, null, 4);
                m.field_78095_p = pl.func_70678_g(event.partialRenderTick);
                m.field_78093_q = pl.func_70115_ae();
                m.field_78091_s = pl.func_70631_g_();
                m.field_78117_n = pl.func_70093_af();
                ModelBipedBody.y = ModelBipedDBC.y;
                m.func_78088_a((Entity)pl, mdl.rot1, mdl.rot2, mdl.rot3, mdl.rot4, mdl.rot5, mdl.rot6);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            }
            if (weight == 4) {
                String armor = "";
                String d = "";
                int wd = Integer.parseInt(sw[1]);
                if (wd > 50) {
                    d = "_d";
                }
                armor = "armor/weightheavysuit" + d + ".png";
                JRMCoreClient.mc.func_110434_K().func_110577_a(new ResourceLocation(JRMCoreH.tjdbcAssts, armor));
                GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                ModelBipedBody m = (ModelBipedBody)JRMCoreHJBRA.GiTurtleMdl1;
                m = (ModelBipedBody)JRMCoreHJBRA.showModel(m, (EntityLivingBase)pl, null, 4);
                m.field_78095_p = pl.func_70678_g(event.partialRenderTick);
                m.field_78093_q = pl.func_70115_ae();
                m.field_78091_s = pl.func_70631_g_();
                m.field_78117_n = pl.func_70093_af();
                ModelBipedBody.y = ModelBipedDBC.y;
                m.func_78088_a((Entity)pl, mdl.rot1, mdl.rot2, mdl.rot3, mdl.rot4, mdl.rot5, mdl.rot6);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            }
            s = JRMCoreH.data(event.entity.func_70005_c_(), 1, "0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0").split(";");
            String[][] slot_vanity_num = new String[8][];
            int[] slot_van = new int[8];
            for (i = 0; i < 8; ++i) {
                slot_vanity_num[i] = s[8 + i].split(",");
                slot_van[i] = Integer.parseInt(slot_vanity_num[i][0]);
            }
            for (i = 0; i < 8; ++i) {
                if (slot_van[i] <= 0) continue;
                ItemStack itemstack = new ItemStack(Item.func_150899_d((int)slot_van[i]));
                int bodycol = Integer.parseInt(slot_vanity_num[i][1]);
                if (itemstack == null || !(itemstack.func_77973_b() instanceof ItemVanity)) continue;
                ((ItemVanity)itemstack.func_77973_b()).setColor(itemstack, bodycol);
                int j = ((ItemVanity)itemstack.func_77973_b()).getColor(itemstack);
                if (j != -1) {
                    float f1 = (float)(j >> 16 & 0xFF) / 255.0f;
                    float f2 = (float)(j >> 8 & 0xFF) / 255.0f;
                    float f3 = (float)(j & 0xFF) / 255.0f;
                    GL11.glColor3f((float)f1, (float)f2, (float)f3);
                }
                ResourceLocation rl = new ResourceLocation(((ItemVanity)itemstack.func_77973_b()).getArmorTexture(itemstack, (Entity)pl, 0, ""));
                JRMCoreClient.mc.func_110434_K().func_110577_a(rl);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                ModelBipedBody m = (ModelBipedBody)((ItemVanity)itemstack.func_77973_b()).giMdl(((ItemVanity)itemstack.func_77973_b()).armorType, (EntityLivingBase)pl);
                if (m == null) continue;
                m = (ModelBipedBody)JRMCoreHJBRA.showModel(m, (EntityLivingBase)pl, itemstack, ((ItemVanity)itemstack.func_77973_b()).armorType);
                m.field_78095_p = pl.func_70678_g(event.partialRenderTick);
                m.field_78093_q = pl.func_70115_ae();
                m.field_78091_s = pl.func_70631_g_();
                m.field_78117_n = pl.func_70093_af();
                ModelBipedBody.y = ModelBipedDBC.y;
                m.func_78088_a((Entity)pl, mdl.rot1, mdl.rot2, mdl.rot3, mdl.rot4, mdl.rot5, mdl.rot6);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            }
            int race = Integer.parseInt(s[0]);
            if (JRMCoreH.isRaceMajin(race)) {
                slot_vanity_num = new String[8][];
                slot_van = new int[8];
                String[] absorptionData = JRMCoreH.data(pl.func_70005_c_(), 13, "0;0;0;0,0,0+0").split(";")[3].split(",")[2].split("-");
                if (absorptionData.length > 0) {
                    int i3;
                    for (i3 = 0; i3 < absorptionData.length; ++i3) {
                        if (!absorptionData[i3].contains("+")) continue;
                        slot_vanity_num[i3] = absorptionData[i3].split("\\+");
                        slot_van[i3] = Integer.parseInt(slot_vanity_num[i3][0]);
                    }
                    for (i3 = 0; i3 < absorptionData.length; ++i3) {
                        if (slot_van[i3] <= 0) continue;
                        ItemStack itemstack = new ItemStack(Item.func_150899_d((int)slot_van[i3]));
                        int bodycol = Integer.parseInt(slot_vanity_num[i3][1]);
                        if (itemstack == null || !(itemstack.func_77973_b() instanceof ItemVanity)) continue;
                        ((ItemVanity)itemstack.func_77973_b()).setColor(itemstack, bodycol);
                        int j = ((ItemVanity)itemstack.func_77973_b()).getColor(itemstack);
                        if (j != -1) {
                            float f1 = (float)(j >> 16 & 0xFF) / 255.0f;
                            float f2 = (float)(j >> 8 & 0xFF) / 255.0f;
                            float f3 = (float)(j & 0xFF) / 255.0f;
                            GL11.glColor3f((float)f1, (float)f2, (float)f3);
                        }
                        ResourceLocation rl = new ResourceLocation(((ItemVanity)itemstack.func_77973_b()).getArmorTexture(itemstack, (Entity)pl, 0, ""));
                        JRMCoreClient.mc.func_110434_K().func_110577_a(rl);
                        GL11.glEnable((int)3042);
                        GL11.glBlendFunc((int)770, (int)771);
                        ModelBipedBody m = (ModelBipedBody)((ItemVanity)itemstack.func_77973_b()).giMdl(((ItemVanity)itemstack.func_77973_b()).armorType, (EntityLivingBase)pl);
                        if (m == null) continue;
                        m = (ModelBipedBody)JRMCoreHJBRA.showModel(m, (EntityLivingBase)pl, itemstack, ((ItemVanity)itemstack.func_77973_b()).armorType);
                        m.field_78095_p = pl.func_70678_g(event.partialRenderTick);
                        m.field_78093_q = pl.func_70115_ae();
                        m.field_78091_s = pl.func_70631_g_();
                        m.field_78117_n = pl.func_70093_af();
                        ModelBipedBody.y = ModelBipedDBC.y;
                        m.func_78088_a((Entity)pl, mdl.rot1, mdl.rot2, mdl.rot3, mdl.rot4, mdl.rot5, mdl.rot6);
                        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    }
                }
            }
            if (JRMCoreH.NC() && (clientState = JRMCoreH.State) == 1 && JRMCoreH.isPowerTypeChakra() && JRMCoreH.Class == 1 && JRMCoreHNC.renderHyuuga && JRMCoreH.isPowerTypeChakra(JRMCoreH.PlyrPwr(pl)) && JRMCoreH.dnn(14) && JRMCoreH.dnn(8) && JRMCoreH.dnn(9) && JRMCoreH.dnn(1) && JRMCoreH.dnn(5)) {
                EntityPlayer player = (EntityPlayer)event.entity;
                int hp = Integer.parseInt(JRMCoreH.data(event.entity.func_70005_c_(), 8, "200"));
                int ki = Integer.parseInt(JRMCoreH.data(event.entity.func_70005_c_(), 9, "200"));
                int[] atr = JRMCoreH.PlyrAttrbtsC((EntityPlayer)event.entity);
                int pwr2 = Integer.parseInt(s[2]);
                int cls = Integer.parseInt(s[3]);
                int maxhp = JRMCoreH.stat((Entity)player, 2, pwr2, 2, atr[2], race, cls, 0.0f);
                int maxki = JRMCoreH.stat((Entity)player, 5, pwr2, 5, atr[5], race, cls, 0.0f);
                int align = JRMCoreH.Algnmnt(Integer.parseInt(JRMCoreH.data(event.entity.func_70005_c_(), 5, "50;0").split(";")[0]));
                float mC = maxki;
                float cC = ki;
                float c = cC / mC;
                JRMCoreClient.mc.func_110434_K().func_110577_a(new ResourceLocation(JRMCoreH.tjnc, "misc/cha.png"));
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)c);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)1);
                GL11.glPushMatrix();
                ModelBipedBody ml = (ModelBipedBody)JRMCoreHJBRA.GiTurtleMdl5;
                ml = (ModelBipedBody)JRMCoreHJBRA.showModel(ml, (EntityLivingBase)pl, null, 4);
                ml.field_78095_p = pl.func_70678_g(event.partialRenderTick);
                ml.field_78093_q = pl.func_70115_ae();
                ml.field_78091_s = pl.func_70631_g_();
                ml.field_78117_n = pl.func_70093_af();
                ModelBipedBody.y = ModelBipedDBC.y;
                ml.func_78088_a((Entity)pl, mdl.rot1, mdl.rot2, mdl.rot3, mdl.rot4, mdl.rot5, mdl.rot6);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GL11.glPopMatrix();
                GL11.glDisable((int)3042);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void onEvent(RenderPlayerEvent.SetArmorModel event) {
        Item item;
        ItemStack itemstack = event.stack;
        EntityPlayer player = event.entityPlayer;
        int p_77032_2_ = 3 - event.slot;
        RenderPlayer rend = event.renderer;
        if (itemstack != null && (item = itemstack.func_77973_b()) instanceof ItemArmor) {
            ItemArmor itemarmor = (ItemArmor)item;
            JRMCoreClient.mc.func_110434_K().func_110577_a(RenderBiped.getArmorResource((Entity)player, (ItemStack)itemstack, (int)p_77032_2_, null));
            rend.field_77111_i = this.armrMdl2;
            rend.field_77108_b = this.armrMdl;
            ModelBiped modelbiped = p_77032_2_ == 2 ? rend.field_77111_i : rend.field_77108_b;
            modelbiped = JRMCoreHJBRA.showModel(modelbiped, (EntityLivingBase)player, itemstack, p_77032_2_);
            if (event.renderer instanceof RenderPlayerJBRA) {
                RenderPlayerJBRA r = (RenderPlayerJBRA)event.renderer;
                ModelBipedDBC mdl = r.modelMain;
                if (modelbiped instanceof ModelBipedBody) {
                    ModelBipedBody cfr_ignored_0 = (ModelBipedBody)modelbiped;
                    ModelBipedBody.y = ModelBipedDBC.y;
                }
            }
            modelbiped = ForgeHooksClient.getArmorModel((EntityLivingBase)player, (ItemStack)itemstack, (int)p_77032_2_, (ModelBiped)modelbiped);
            rend.func_77042_a((ModelBase)modelbiped);
            modelbiped.field_78095_p = player.func_70678_g(event.partialRenderTick);
            modelbiped.field_78093_q = player.func_70115_ae();
            modelbiped.field_78091_s = player.func_70631_g_();
            modelbiped.field_78117_n = player.func_70093_af();
            int j = itemarmor.func_82814_b(itemstack);
            if (j != -1) {
                float f1 = (float)(j >> 16 & 0xFF) / 255.0f;
                float f2 = (float)(j >> 8 & 0xFF) / 255.0f;
                float f3 = (float)(j & 0xFF) / 255.0f;
                GL11.glColor3f((float)f1, (float)f2, (float)f3);
                if (itemstack.func_77948_v()) {
                    event.result = 31;
                    return;
                }
                event.result = 16;
                return;
            }
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            if (itemstack.func_77948_v()) {
                event.result = 15;
                return;
            }
            event.result = 1;
            return;
        }
    }
}

