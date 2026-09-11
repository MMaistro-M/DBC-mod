/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.entity.RenderBiped
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package kamkeel.npcs.addon.client;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.AnimationMixinFunctions;
import noppes.npcs.client.ClientEventHandler;
import org.lwjgl.opengl.GL11;

public class DBCClientAnimations {
    public static void doDBCRender(EntityPlayer par1EntityPlayer) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Minecraft mc = Minecraft.func_71410_x();
        Class<?> JBRAH = Class.forName("JinRyuu.JBRA.JBRAH");
        Class<?> JRMCoreHDBC = Class.forName("JinRyuu.JRMCore.JRMCoreHDBC");
        Class<?> JRMCoreH = Class.forName("JinRyuu.JRMCore.JRMCoreH");
        Class<?> ExtendedPlayer = Class.forName("JinRyuu.JRMCore.i.ExtendedPlayer");
        Class<?> JGConfigClientSettings = Class.forName("JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings");
        Class<?> RenderPlayerJBRA = Class.forName("JinRyuu.JBRA.RenderPlayerJBRA");
        Class<?> JGConfigRaces = Class.forName("JinRyuu.JRMCore.server.config.dbc.JGConfigRaces");
        Method func_aam = RenderPlayerJBRA.getDeclaredMethod("func_aam", Integer.TYPE, Boolean.TYPE, Boolean.TYPE);
        func_aam.setAccessible(true);
        boolean jhdsBool = (Boolean)JBRAH.getMethod("JHDS", new Class[0]).invoke(null, new Object[0]);
        boolean dbcBool = (Boolean)JRMCoreH.getMethod("DBC", new Class[0]).invoke(null, new Object[0]);
        RenderPlayer renderPlayer = (RenderPlayer)RenderManager.field_78727_a.func_78713_a((Entity)par1EntityPlayer);
        ModelBiped modelMain = (ModelBiped)RenderPlayerJBRA.getField("modelMain").get(renderPlayer);
        EntityClientPlayerMP acp = mc.field_71439_g;
        Object data = null;
        if (jhdsBool) {
            data = JBRAH.getMethod("skinData", EntityPlayer.class).invoke(null, acp);
        }
        float f = 1.0f;
        GL11.glColor3f((float)(f + DBCClientAnimations.getR()), (float)(f + DBCClientAnimations.getG()), (float)(f + DBCClientAnimations.getB()));
        GL11.glPushMatrix();
        modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
        String dns = (String)JRMCoreH.getField("dns").get(null);
        if (dns.length() > 3) {
            ResourceLocation armor;
            String dbcarmor;
            ItemArmor itemarmor;
            Item item;
            ItemStack itemstack;
            ResourceLocation bdyskn;
            boolean v;
            int j;
            int jx;
            String[] s;
            int tailCol;
            int id;
            boolean instantTransmission;
            int bodyc3;
            int bodyc2;
            int bodyc1;
            int bodycm;
            int bodytype;
            int State2 = ((Byte)JRMCoreH.getField("State").get(null)).byteValue();
            int race = (Integer)JRMCoreH.getMethod("dnsRace", String.class).invoke(null, dns);
            boolean saiOozar = (Boolean)JRMCoreH.getMethod("rSai", Integer.TYPE).invoke(null, race) != false && (State2 == 7 || State2 == 8);
            int gen = (Integer)JRMCoreH.getMethod("dnsGender", String.class).invoke(null, dns);
            int skintype = (Integer)JRMCoreH.getMethod("dnsSkinT", String.class).invoke(null, dns);
            boolean lg = (Boolean)JRMCoreH.getMethod("lgndb", EntityPlayer.class, Integer.TYPE, Integer.TYPE).invoke(null, par1EntityPlayer, race, State2);
            boolean iau = (Boolean)JRMCoreH.getMethod("rc_arc", Integer.TYPE).invoke(null, race) != false && State2 == 6;
            String dnsau = (String)JRMCoreH.getMethod("data", Integer.TYPE, String.class).invoke(null, 16, "");
            dnsau = dnsau.contains(";") ? dnsau.substring(1) : (par1EntityPlayer.func_70005_c_().equals(mc.field_71439_g.func_70005_c_()) ? dnsau : "");
            int n = bodytype = skintype == 0 ? ((Integer)JRMCoreH.getMethod("dnsBodyC1_0", String.class).invoke(null, dns)).intValue() : ((Integer)JRMCoreH.getMethod("dnsBodyT", String.class).invoke(null, dns)).intValue();
            int n2 = skintype == 0 ? 0 : (bodycm = iau ? ((Integer)JRMCoreH.getMethod("dnsauCM", String.class).invoke(null, dns)).intValue() : ((Integer)JRMCoreH.getMethod("dnsBodyCM", String.class).invoke(null, dns)).intValue());
            int n3 = skintype == 0 ? 0 : (bodyc1 = iau ? ((Integer)JRMCoreH.getMethod("dnsauC1", String.class).invoke(null, dns)).intValue() : ((Integer)JRMCoreH.getMethod("dnsBodyC1", String.class).invoke(null, dns)).intValue());
            int n4 = skintype == 0 ? 0 : (bodyc2 = iau ? ((Integer)JRMCoreH.getMethod("dnsauC2", String.class).invoke(null, dns)).intValue() : ((Integer)JRMCoreH.getMethod("dnsBodyC2", String.class).invoke(null, dns)).intValue());
            int n5 = skintype == 0 ? 0 : (bodyc3 = iau ? ((Integer)JRMCoreH.getMethod("dnsauC3", String.class).invoke(null, dns)).intValue() : ((Integer)JRMCoreH.getMethod("dnsBodyC3", String.class).invoke(null, dns)).intValue());
            int plyrSpc = skintype == 0 ? 0 : (((int[])JRMCoreH.getField("RaceCustomSkin").get(null))[race] == 0 ? 0 : (bodytype >= ((int[])JRMCoreH.getField("Specials").get(null))[race] ? ((int[])JRMCoreH.getField("Specials").get(null))[race] - 1 : bodytype));
            int[] an = new int[]{1, 0, 2, 0, 0, 3, 0, 1, 1};
            Object ep = ExtendedPlayer.getMethod("get", EntityPlayer.class).invoke(null, acp);
            int animKiShoot = (Integer)ExtendedPlayer.getMethod("getAnimKiShoot", new Class[0]).invoke(ep, new Object[0]);
            int blocking = (Integer)ExtendedPlayer.getMethod("getBlocking", new Class[0]).invoke(ep, new Object[0]);
            boolean bl = instantTransmission = blocking == 2;
            int n6 = blocking != 0 ? (instantTransmission ? 6 : 0) : (id = animKiShoot != 0 ? an[animKiShoot - 1] + 2 : -1);
            if (!((Boolean)JGConfigClientSettings.getField("CLIENT_DA4").get(null)).booleanValue()) {
                id = -1;
            }
            if (dbcBool && (tailCol = Integer.parseInt((s = ((String)JRMCoreH.getMethod("data", String.class, Integer.TYPE, String.class).invoke(null, acp.func_70005_c_(), 1, "0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0")).split(";"))[2])) == 1) {
                String[] PlyrSkills = (String[])JRMCoreH.getMethod("PlyrSkills", EntityPlayer.class).invoke(null, acp);
                jx = (Integer)JRMCoreH.getMethod("SklLvl", Integer.TYPE, String[].class).invoke(null, 12, PlyrSkills);
                j = (Integer)JRMCoreH.getMethod("SklLvl", Integer.TYPE, String[].class).invoke(null, 15, PlyrSkills);
                String ss = s[17];
                v = dbcBool && !ss.equals("-1");
                GL11.glPushMatrix();
                if (v && (jx > 0 || j > 0)) {
                    if (id > -1) {
                        func_aam.invoke((Object)renderPlayer, id, false, true);
                    }
                    GL11.glRotatef((float)6.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)-0.29f, (float)0.15f, (float)0.0f);
                    RenderPlayerJBRA.getMethod("kss", Entity.class, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE).invoke(null, acp, false, Integer.parseInt(ss), jx, j);
                }
                GL11.glPopMatrix();
            }
            float h1 = 1.0f;
            if (race == 5 && dbcBool) {
                String[] stringArray;
                boolean ssg;
                v = State2 == 1;
                boolean bl2 = ssg = State2 == 3 && (Boolean)JGConfigRaces.getField("CONFIG_MAJIN_PURE_PINK_SKIN").get(null) != false;
                if (v) {
                    bodycm = 12561588;
                } else if (ssg) {
                    bodycm = 16757199;
                }
                bdyskn = new ResourceLocation("jinryuudragonbc:cc/majin/" + (gen == 1 ? "f" : "") + "majin.png");
                mc.func_110434_K().func_110577_a(bdyskn);
                DBCClientAnimations.glColor3f(bodycm);
                modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                AnimationMixinFunctions.renderLimbs();
                String[] playerData13 = ((String)JRMCoreH.getMethod("data", String.class, Integer.TYPE, String.class).invoke(null, par1EntityPlayer.func_70005_c_(), 13, "0;0;0;0,0,0+0")).split(";");
                String[] absorptionData = playerData13.length > 3 ? playerData13[3].split(",") : "0;0;0;0,0,0+0".split(",");
                if (absorptionData[1].contains("+")) {
                    stringArray = absorptionData[1].split("\\+");
                } else {
                    String[] stringArray2 = new String[1];
                    stringArray = stringArray2;
                    stringArray2[0] = absorptionData[1];
                }
                String[] absorptionVisuals = stringArray;
                int absorbedRace = Integer.parseInt(absorptionVisuals[0]);
                if (((Boolean)JRMCoreH.getMethod("isRaceArcosian", Integer.TYPE).invoke(null, absorbedRace)).booleanValue() || ((Boolean)JRMCoreH.getMethod("isRaceNamekian", Integer.TYPE).invoke(null, absorbedRace)).booleanValue()) {
                    bdyskn = new ResourceLocation("jinryuudragonbc:cc/majin/" + (gen == 1 ? "f" : "") + "majin_" + ((Boolean)JRMCoreH.getMethod("isRaceArcosian", Integer.TYPE).invoke(null, absorbedRace) != false ? "arco" : "namek") + ".png");
                    mc.func_110434_K().func_110577_a(bdyskn);
                    DBCClientAnimations.glColor3f(bodycm);
                    modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                    AnimationMixinFunctions.renderLimbs();
                }
                if (!saiOozar) {
                    if (skintype == 0) {
                        ResourceLocation resourceLocation = acp.func_110306_p().equals((Object)ClientEventHandler.steveTextures) ? (gen >= 1 ? ClientEventHandler.fem : ClientEventHandler.steveTextures) : (bdyskn = acp.func_110306_p());
                        if (jhdsBool && ((Boolean)JBRAH.getMethod("getSkinHas", Object.class).invoke(null, data)).booleanValue()) {
                            mc.func_110434_K().func_110577_a((ResourceLocation)JBRAH.getMethod("getSkinLoc", Object.class).invoke(null, data));
                        } else {
                            mc.func_110434_K().func_110577_a(bdyskn);
                        }
                        GL11.glColor3f((float)(h1 + DBCClientAnimations.getR()), (float)(h1 + DBCClientAnimations.getG()), (float)(h1 + DBCClientAnimations.getB()));
                        modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                        AnimationMixinFunctions.renderLimbs();
                    } else if (jhdsBool && ((Boolean)JBRAH.getMethod("getSkinHas", Object.class).invoke(null, data)).booleanValue() && skintype == 0) {
                        GL11.glColor3f((float)(h1 + DBCClientAnimations.getR()), (float)(h1 + DBCClientAnimations.getG()), (float)(h1 + DBCClientAnimations.getB()));
                        mc.func_110434_K().func_110577_a((ResourceLocation)JBRAH.getMethod("getSkinLoc", Object.class).invoke(null, data));
                        modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                        AnimationMixinFunctions.renderLimbs();
                    }
                }
            } else if (race == 3 && dbcBool) {
                v = (Boolean)JRMCoreH.getMethod("StusEfctsMe", Integer.TYPE).invoke(null, 17);
                boolean ssg = (Boolean)JRMCoreHDBC.getMethod("godKiUserBase", Integer.TYPE, Integer.TYPE).invoke(null, race, State2);
                if (ssg && v) {
                    bodycm = 16744999;
                    bodyc1 = 15524763;
                    bodyc2 = 12854822;
                    bodyc3 = 0;
                }
                bdyskn = new ResourceLocation("jinryuudragonbc:cc/nam/0nam" + plyrSpc + ".png");
                mc.func_110434_K().func_110577_a(bdyskn);
                DBCClientAnimations.glColor3f(bodycm);
                modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                AnimationMixinFunctions.renderLimbs();
                bdyskn = new ResourceLocation("jinryuudragonbc:cc/nam/1nam" + plyrSpc + ".png");
                mc.func_110434_K().func_110577_a(bdyskn);
                DBCClientAnimations.glColor3f(bodyc1);
                modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                AnimationMixinFunctions.renderLimbs();
                bdyskn = new ResourceLocation("jinryuudragonbc:cc/nam/2nam" + plyrSpc + ".png");
                mc.func_110434_K().func_110577_a(bdyskn);
                DBCClientAnimations.glColor3f(bodyc2);
                modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                AnimationMixinFunctions.renderLimbs();
                bdyskn = new ResourceLocation("jinryuudragonbc:cc/nam/3nam" + plyrSpc + ".png");
                mc.func_110434_K().func_110577_a(bdyskn);
                GL11.glColor3f((float)(h1 + DBCClientAnimations.getR()), (float)(h1 + DBCClientAnimations.getG()), (float)(h1 + DBCClientAnimations.getB()));
                modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                AnimationMixinFunctions.renderLimbs();
            } else if (race == 4 && dbcBool) {
                v = (Boolean)JRMCoreH.getMethod("StusEfctsMe", Integer.TYPE).invoke(null, 17);
                boolean ssg = (Boolean)JRMCoreHDBC.getMethod("godKiUserBase", Integer.TYPE, Integer.TYPE).invoke(null, race, State2);
                if (ssg && v) {
                    State2 = 6;
                    bodycm = 0x545454;
                    bodyc1 = 0xC3C3C3;
                    bodyc3 = 0x171717;
                }
                bdyskn = new ResourceLocation("jinryuudragonbc:cc/arc/" + (gen == 1 ? "f" : "m") + "/0A" + ((short[])JRMCoreH.getField("TransFrSkn").get(null))[State2] + plyrSpc + ".png");
                mc.func_110434_K().func_110577_a(bdyskn);
                DBCClientAnimations.glColor3f(bodycm);
                modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                AnimationMixinFunctions.renderLimbs();
                bdyskn = new ResourceLocation("jinryuudragonbc:cc/arc/" + (gen == 1 ? "f" : "m") + "/1A" + ((short[])JRMCoreH.getField("TransFrSkn").get(null))[State2] + plyrSpc + ".png");
                mc.func_110434_K().func_110577_a(bdyskn);
                DBCClientAnimations.glColor3f(bodyc1);
                modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                AnimationMixinFunctions.renderLimbs();
                bdyskn = new ResourceLocation("jinryuudragonbc:cc/arc/" + (gen == 1 ? "f" : "m") + "/2A" + ((short[])JRMCoreH.getField("TransFrSkn").get(null))[State2] + plyrSpc + ".png");
                mc.func_110434_K().func_110577_a(bdyskn);
                DBCClientAnimations.glColor3f(bodyc2);
                modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                AnimationMixinFunctions.renderLimbs();
                bdyskn = new ResourceLocation("jinryuudragonbc:cc/arc/" + (gen == 1 ? "f" : "m") + "/3A" + ((short[])JRMCoreH.getField("TransFrSkn").get(null))[State2] + plyrSpc + ".png");
                mc.func_110434_K().func_110577_a(bdyskn);
                DBCClientAnimations.glColor3f(bodyc3);
                modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                AnimationMixinFunctions.renderLimbs();
                bdyskn = new ResourceLocation("jinryuudragonbc:cc/arc/" + (gen == 1 ? "f" : "m") + "/4A" + ((short[])JRMCoreH.getField("TransFrSkn").get(null))[State2] + plyrSpc + ".png");
                mc.func_110434_K().func_110577_a(bdyskn);
                GL11.glColor3f((float)(h1 + DBCClientAnimations.getR()), (float)(h1 + DBCClientAnimations.getG()), (float)(h1 + DBCClientAnimations.getB()));
                modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                AnimationMixinFunctions.renderLimbs();
            } else {
                if (saiOozar) {
                    bdyskn = new ResourceLocation("jinryuudragonbc:cc/oozaru1.png");
                    mc.func_110434_K().func_110577_a(bdyskn);
                    DBCClientAnimations.glColor3f(skintype != 0 ? bodycm : 11374471);
                    modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                    AnimationMixinFunctions.renderLimbs();
                    int n7 = tailCol = race != 2 && bodytype == 0 ? 6498048 : bodytype;
                    jx = State2 != 0 && State2 != 7 ? (lg ? 0x99FF66 : 16574610) : (skintype == 1 ? bodyc1 : tailCol);
                    bdyskn = new ResourceLocation("jinryuudragonbc:cc/oozaru2.png");
                    mc.func_110434_K().func_110577_a(bdyskn);
                    DBCClientAnimations.glColor3f(jx);
                    modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                    AnimationMixinFunctions.renderLimbs();
                } else if (skintype != 0) {
                    bdyskn = new ResourceLocation("jinryuumodscore:cc/" + (gen == 1 ? "f" : "") + "hum.png");
                    mc.func_110434_K().func_110577_a(bdyskn);
                    DBCClientAnimations.glColor3f(bodycm);
                    modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                    AnimationMixinFunctions.renderLimbs();
                }
                if (!saiOozar) {
                    if (skintype == 0) {
                        ResourceLocation resourceLocation = acp.func_110306_p().equals((Object)ClientEventHandler.steveTextures) ? (gen >= 1 ? ClientEventHandler.fem : ClientEventHandler.steveTextures) : (bdyskn = acp.func_110306_p());
                        if (jhdsBool && ((Boolean)JBRAH.getMethod("getSkinHas", Object.class).invoke(null, data)).booleanValue()) {
                            mc.func_110434_K().func_110577_a((ResourceLocation)JBRAH.getMethod("getSkinLoc", Object.class).invoke(null, data));
                        } else {
                            mc.func_110434_K().func_110577_a(bdyskn);
                        }
                        GL11.glColor3f((float)(h1 + DBCClientAnimations.getR()), (float)(h1 + DBCClientAnimations.getG()), (float)(h1 + DBCClientAnimations.getB()));
                        modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                        AnimationMixinFunctions.renderLimbs();
                    } else if (jhdsBool && ((Boolean)JBRAH.getMethod("getSkinHas", Object.class).invoke(null, data)).booleanValue() && skintype == 0) {
                        GL11.glColor3f((float)(h1 + DBCClientAnimations.getR()), (float)(h1 + DBCClientAnimations.getG()), (float)(h1 + DBCClientAnimations.getB()));
                        mc.func_110434_K().func_110577_a((ResourceLocation)JBRAH.getMethod("getSkinLoc", Object.class).invoke(null, data));
                        modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                        AnimationMixinFunctions.renderLimbs();
                    }
                    if (State2 == 14) {
                        tailCol = race != 2 && bodytype == 0 ? 6498048 : bodytype;
                        tailCol = (Boolean)JRMCoreH.getMethod("isAprilFoolsModeOn", new Class[0]).invoke(null, new Object[0]) != false ? 13292516 : tailCol;
                        int n8 = tailCol = skintype == 1 ? bodyc1 : tailCol;
                        if (((Boolean)JRMCoreH.getMethod("rSai", Integer.TYPE).invoke(null, race)).booleanValue() && tailCol == 6498048 && State2 == 14) {
                            tailCol = (Boolean)JRMCoreH.getMethod("isAprilFoolsModeOn", new Class[0]).invoke(null, new Object[0]) != false ? 13292516 : 14292268;
                        }
                        mc.func_110434_K().func_110577_a(new ResourceLocation("jinryuudragonbc:cc/ss4" + (skintype == 0 ? "a" : "b") + ".png"));
                        DBCClientAnimations.glColor3f(tailCol);
                        modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                        AnimationMixinFunctions.renderLimbs();
                    }
                }
            }
            if (((Boolean)JGConfigClientSettings.getField("CLIENT_DA19").get(null)).booleanValue() && (dbcBool || ((Boolean)JRMCoreH.getMethod("NC", new Class[0]).invoke(null, new Object[0])).booleanValue())) {
                GL11.glPushMatrix();
                GL11.glEnable((int)3042);
                GL11.glDisable((int)2896);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glAlphaFunc((int)516, (float)0.003921569f);
                GL11.glDepthMask((boolean)false);
                tailCol = Integer.parseInt((String)JRMCoreH.getMethod("data", String.class, Integer.TYPE, String.class).invoke(null, par1EntityPlayer.func_70005_c_(), 8, "200"));
                float one = (float)tailCol / 100.0f;
                j = (int)((float)tailCol / one);
                if (j < 70) {
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    mc.func_110434_K().func_110577_a(new ResourceLocation("jinryuumodscore:cc/bruises1.png"));
                    AnimationMixinFunctions.renderLimbs();
                }
                if (j < 55) {
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    mc.func_110434_K().func_110577_a(new ResourceLocation("jinryuumodscore:cc/bruises2.png"));
                    AnimationMixinFunctions.renderLimbs();
                }
                if (j < 35) {
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    mc.func_110434_K().func_110577_a(new ResourceLocation("jinryuumodscore:cc/bruises3.png"));
                    AnimationMixinFunctions.renderLimbs();
                }
                if (j < 20) {
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    mc.func_110434_K().func_110577_a(new ResourceLocation("jinryuumodscore:cc/bruises4.png"));
                    AnimationMixinFunctions.renderLimbs();
                }
                GL11.glDepthMask((boolean)true);
                GL11.glEnable((int)2896);
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
            }
            if ((itemstack = par1EntityPlayer.field_71071_by.func_70440_f(2)) != null && (item = itemstack.func_77973_b()) instanceof ItemArmor) {
                itemarmor = (ItemArmor)item;
                GL11.glPushMatrix();
                dbcarmor = itemarmor.getArmorTexture(itemstack, (Entity)par1EntityPlayer, 2, null);
                ResourceLocation mcarmor = RenderBiped.getArmorResource((Entity)par1EntityPlayer, (ItemStack)itemstack, (int)1, null);
                if (dbcarmor != null) {
                    dbcarmor = dbcarmor.replace("jbra", "").replace("_dam", "");
                }
                armor = dbcarmor != null ? new ResourceLocation(dbcarmor) : mcarmor;
                mc.func_110434_K().func_110577_a(armor);
                GL11.glPushMatrix();
                if (id > -1) {
                    func_aam.invoke((Object)renderPlayer, id, false, true);
                }
                GL11.glColor3f((float)(1.0f + DBCClientAnimations.getR()), (float)(1.0f + DBCClientAnimations.getG()), (float)(1.0f + DBCClientAnimations.getB()));
                GL11.glScalef((float)1.0001f, (float)1.0001f, (float)1.0001f);
                if (dbcarmor != null) {
                    modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                    modelMain.field_78089_u = 64;
                    modelMain.field_78090_t = 128;
                    AnimationMixinFunctions.renderLimbs();
                } else {
                    modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                    AnimationMixinFunctions.renderLimbs();
                }
                GL11.glPopMatrix();
                GL11.glPopMatrix();
            }
            if (race == 3 && dbcBool) {
                bdyskn = new ResourceLocation("jinryuudragonbc:cc/nam/0nam" + plyrSpc + ".png");
                mc.func_110434_K().func_110577_a(bdyskn);
                DBCClientAnimations.glColor3f(bodycm);
                modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                AnimationMixinFunctions.renderLimbs();
                bdyskn = new ResourceLocation("jinryuudragonbc:cc/nam/1nam" + plyrSpc + ".png");
                mc.func_110434_K().func_110577_a(bdyskn);
                DBCClientAnimations.glColor3f(bodyc1);
                modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                AnimationMixinFunctions.renderLimbs();
                bdyskn = new ResourceLocation("jinryuudragonbc:cc/nam/2nam" + plyrSpc + ".png");
                mc.func_110434_K().func_110577_a(bdyskn);
                DBCClientAnimations.glColor3f(bodyc2);
                modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                AnimationMixinFunctions.renderLimbs();
                bdyskn = new ResourceLocation("jinryuudragonbc:cc/nam/3nam" + plyrSpc + ".png");
                mc.func_110434_K().func_110577_a(bdyskn);
                GL11.glColor3f((float)(h1 + DBCClientAnimations.getR()), (float)(h1 + DBCClientAnimations.getG()), (float)(h1 + DBCClientAnimations.getB()));
                modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                AnimationMixinFunctions.renderLimbs();
            } else if (race == 4 && dbcBool) {
                bdyskn = new ResourceLocation("jinryuudragonbc:cc/arc/" + (gen == 1 ? "f" : "m") + "/0A" + ((short[])JRMCoreH.getField("TransFrSkn").get(null))[State2] + plyrSpc + ".png");
                mc.func_110434_K().func_110577_a(bdyskn);
                DBCClientAnimations.glColor3f(bodycm);
                modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                AnimationMixinFunctions.renderLimbs();
                bdyskn = new ResourceLocation("jinryuudragonbc:cc/arc/" + (gen == 1 ? "f" : "m") + "/1A" + ((short[])JRMCoreH.getField("TransFrSkn").get(null))[State2] + plyrSpc + ".png");
                mc.func_110434_K().func_110577_a(bdyskn);
                DBCClientAnimations.glColor3f(bodyc1);
                modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                AnimationMixinFunctions.renderLimbs();
                bdyskn = new ResourceLocation("jinryuudragonbc:cc/arc/" + (gen == 1 ? "f" : "m") + "/2A" + ((short[])JRMCoreH.getField("TransFrSkn").get(null))[State2] + plyrSpc + ".png");
                mc.func_110434_K().func_110577_a(bdyskn);
                DBCClientAnimations.glColor3f(bodyc2);
                modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                AnimationMixinFunctions.renderLimbs();
                bdyskn = new ResourceLocation("jinryuudragonbc:cc/arc/" + (gen == 1 ? "f" : "m") + "/3A" + ((short[])JRMCoreH.getField("TransFrSkn").get(null))[State2] + plyrSpc + ".png");
                mc.func_110434_K().func_110577_a(bdyskn);
                DBCClientAnimations.glColor3f(bodyc3);
                modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                AnimationMixinFunctions.renderLimbs();
                bdyskn = new ResourceLocation("jinryuudragonbc:cc/arc/" + (gen == 1 ? "f" : "m") + "/4A" + ((short[])JRMCoreH.getField("TransFrSkn").get(null))[State2] + plyrSpc + ".png");
                mc.func_110434_K().func_110577_a(bdyskn);
                GL11.glColor3f((float)(h1 + DBCClientAnimations.getR()), (float)(h1 + DBCClientAnimations.getG()), (float)(h1 + DBCClientAnimations.getB()));
                modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                AnimationMixinFunctions.renderLimbs();
            } else {
                if (saiOozar) {
                    bdyskn = new ResourceLocation("jinryuudragonbc:cc/oozaru1.png");
                    mc.func_110434_K().func_110577_a(bdyskn);
                    DBCClientAnimations.glColor3f(skintype != 0 ? bodycm : 11374471);
                    modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                    AnimationMixinFunctions.renderLimbs();
                    int n9 = jx = race != 2 && bodytype == 0 ? 6498048 : bodytype;
                    j = State2 != 0 && State2 != 7 ? (lg ? 0x99FF66 : 16574610) : (skintype == 1 ? bodyc1 : jx);
                    bdyskn = new ResourceLocation("jinryuudragonbc:cc/oozaru2.png");
                    mc.func_110434_K().func_110577_a(bdyskn);
                    DBCClientAnimations.glColor3f(j);
                    modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                    AnimationMixinFunctions.renderLimbs();
                } else if (skintype != 0) {
                    bdyskn = new ResourceLocation("jinryuumodscore:cc/" + (gen == 1 ? "f" : "") + "hum.png");
                    mc.func_110434_K().func_110577_a(bdyskn);
                    DBCClientAnimations.glColor3f(bodycm);
                    modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                    AnimationMixinFunctions.renderLimbs();
                }
                if (!saiOozar) {
                    if (skintype == 0) {
                        ResourceLocation resourceLocation = acp.func_110306_p().equals((Object)ClientEventHandler.steveTextures) ? (gen >= 1 ? ClientEventHandler.fem : ClientEventHandler.steveTextures) : (bdyskn = acp.func_110306_p());
                        if (jhdsBool && ((Boolean)JBRAH.getMethod("getSkinHas", Object.class).invoke(null, data)).booleanValue()) {
                            mc.func_110434_K().func_110577_a((ResourceLocation)JBRAH.getMethod("getSkinLoc", Object.class).invoke(null, data));
                        } else {
                            mc.func_110434_K().func_110577_a(bdyskn);
                        }
                        GL11.glColor3f((float)(h1 + DBCClientAnimations.getR()), (float)(h1 + DBCClientAnimations.getG()), (float)(h1 + DBCClientAnimations.getB()));
                        modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                        AnimationMixinFunctions.renderLimbs();
                    } else if (jhdsBool && ((Boolean)JBRAH.getMethod("getSkinHas", Object.class).invoke(null, data)).booleanValue() && skintype == 0) {
                        GL11.glColor3f((float)(h1 + DBCClientAnimations.getR()), (float)(h1 + DBCClientAnimations.getG()), (float)(h1 + DBCClientAnimations.getB()));
                        mc.func_110434_K().func_110577_a((ResourceLocation)JBRAH.getMethod("getSkinLoc", Object.class).invoke(null, data));
                        modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                        AnimationMixinFunctions.renderLimbs();
                    }
                    if (State2 == 14) {
                        tailCol = race != 2 && bodytype == 0 ? 6498048 : bodytype;
                        tailCol = (Boolean)JRMCoreH.getMethod("isAprilFoolsModeOn", new Class[0]).invoke(null, new Object[0]) != false ? 13292516 : tailCol;
                        int n10 = jx = skintype == 1 ? bodyc1 : tailCol;
                        if (((Boolean)JRMCoreH.getMethod("rSai", Integer.TYPE).invoke(null, race)).booleanValue() && jx == 6498048 && State2 == 14) {
                            jx = (Boolean)JRMCoreH.getMethod("isAprilFoolsModeOn", new Class[0]).invoke(null, new Object[0]) != false ? 13292516 : 14292268;
                        }
                        mc.func_110434_K().func_110577_a(new ResourceLocation("jinryuudragonbc:cc/ss4" + (skintype == 0 ? "a" : "b") + ".png"));
                        DBCClientAnimations.glColor3f(jx);
                        modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                        AnimationMixinFunctions.renderLimbs();
                    }
                }
            }
            if (((Boolean)JGConfigClientSettings.getField("CLIENT_DA19").get(null)).booleanValue()) {
                GL11.glPushMatrix();
                GL11.glEnable((int)3042);
                GL11.glDisable((int)2896);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glAlphaFunc((int)516, (float)0.003921569f);
                GL11.glDepthMask((boolean)false);
                tailCol = (Integer)JRMCoreH.getMethod("stat", Entity.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Float.TYPE).invoke(null, par1EntityPlayer, 2, JRMCoreH.getField("Pwrtyp").get(null), 2, ((int[])JRMCoreH.getField("PlyrAttrbts").get(null))[2], race, JRMCoreH.getField("Class").get(null), Float.valueOf(0.0f));
                jx = Integer.parseInt((String)JRMCoreH.getMethod("data", String.class, Integer.TYPE, String.class).invoke(null, par1EntityPlayer.func_70005_c_(), 8, "200"));
                float one = (float)tailCol / 100.0f;
                int perc = (int)((float)jx / one);
                if (perc < 70) {
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    mc.func_110434_K().func_110577_a(new ResourceLocation("jinryuumodscore:cc/bruises1.png"));
                    AnimationMixinFunctions.renderLimbs();
                }
                if (perc < 55) {
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    mc.func_110434_K().func_110577_a(new ResourceLocation("jinryuumodscore:cc/bruises2.png"));
                    AnimationMixinFunctions.renderLimbs();
                }
                if (perc < 35) {
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    mc.func_110434_K().func_110577_a(new ResourceLocation("jinryuumodscore:cc/bruises3.png"));
                    AnimationMixinFunctions.renderLimbs();
                }
                if (perc < 20) {
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    mc.func_110434_K().func_110577_a(new ResourceLocation("jinryuumodscore:cc/bruises4.png"));
                    AnimationMixinFunctions.renderLimbs();
                }
                GL11.glDepthMask((boolean)true);
                GL11.glEnable((int)2896);
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
            }
            if (itemstack != null && (item = itemstack.func_77973_b()) instanceof ItemArmor) {
                itemarmor = (ItemArmor)item;
                GL11.glPushMatrix();
                dbcarmor = itemarmor.getArmorTexture(itemstack, (Entity)par1EntityPlayer, 2, null);
                ResourceLocation mcarmor = RenderBiped.getArmorResource((Entity)par1EntityPlayer, (ItemStack)itemstack, (int)1, null);
                if (dbcarmor != null) {
                    dbcarmor = dbcarmor.replace("jbra", "").replace("_dam", "");
                }
                armor = dbcarmor != null ? new ResourceLocation(dbcarmor) : mcarmor;
                mc.func_110434_K().func_110577_a(armor);
                if (id == 0 || id == 3 || id == 5) {
                    if (id == 0) {
                        if (((Boolean)JGConfigClientSettings.getField("CLIENT_DA18").get(null)).booleanValue()) {
                            GL11.glPushMatrix();
                            func_aam.invoke((Object)renderPlayer, id, false, true);
                            GL11.glColor3f((float)(1.0f + DBCClientAnimations.getR()), (float)(1.0f + DBCClientAnimations.getG()), (float)(1.0f + DBCClientAnimations.getB()));
                            GL11.glScalef((float)1.0001f, (float)1.0001f, (float)1.0001f);
                            if (dbcarmor != null) {
                                modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                                modelMain.field_78089_u = 64;
                                modelMain.field_78090_t = 128;
                                AnimationMixinFunctions.renderLimbs();
                            } else {
                                modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                                AnimationMixinFunctions.renderLimbs();
                            }
                            GL11.glPopMatrix();
                        }
                    } else {
                        GL11.glPushMatrix();
                        func_aam.invoke((Object)renderPlayer, id, false, true);
                        GL11.glColor3f((float)(1.0f + DBCClientAnimations.getR()), (float)(1.0f + DBCClientAnimations.getG()), (float)(1.0f + DBCClientAnimations.getB()));
                        GL11.glScalef((float)1.0001f, (float)1.0001f, (float)1.0001f);
                        if (dbcarmor != null) {
                            modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                            modelMain.field_78089_u = 64;
                            modelMain.field_78090_t = 128;
                            AnimationMixinFunctions.renderLimbs();
                        } else {
                            modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)par1EntityPlayer);
                            AnimationMixinFunctions.renderLimbs();
                        }
                        GL11.glPopMatrix();
                    }
                }
                GL11.glPopMatrix();
            }
        }
        GL11.glPopMatrix();
    }

    private static float getR() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> RenderPlayerJBRA = Class.forName("JinRyuu.JBRA.RenderPlayerJBRA");
        Method method = RenderPlayerJBRA.getDeclaredMethod("getR", new Class[0]);
        method.setAccessible(true);
        return ((Float)method.invoke(null, new Object[0])).floatValue();
    }

    private static float getB() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> RenderPlayerJBRA = Class.forName("JinRyuu.JBRA.RenderPlayerJBRA");
        Method method = RenderPlayerJBRA.getDeclaredMethod("getB", new Class[0]);
        method.setAccessible(true);
        return ((Float)method.invoke(null, new Object[0])).floatValue();
    }

    private static float getG() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> RenderPlayerJBRA = Class.forName("JinRyuu.JBRA.RenderPlayerJBRA");
        Method method = RenderPlayerJBRA.getDeclaredMethod("getG", new Class[0]);
        method.setAccessible(true);
        return ((Float)method.invoke(null, new Object[0])).floatValue();
    }

    private static void glColor3f(int c) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        float h2 = (float)(c >> 16 & 0xFF) / 255.0f;
        float h3 = (float)(c >> 8 & 0xFF) / 255.0f;
        float h4 = (float)(c & 0xFF) / 255.0f;
        float h1 = 1.0f;
        float r = h1 * h2;
        float g = h1 * h3;
        float b = h1 * h4;
        GL11.glColor3f((float)(r + DBCClientAnimations.getR()), (float)(g + DBCClientAnimations.getG()), (float)(b + DBCClientAnimations.getB()));
    }
}

