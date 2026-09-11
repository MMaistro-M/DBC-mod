/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.tobiasmjc.dbcadditions.mixin.late.jbra;

import JinRyuu.JBRA.JBRAClient;
import JinRyuu.JBRA.ModelBipedDBC;
import JinRyuu.JBRA.RenderPlayerJBRA;
import JinRyuu.JBRA.mod_JBRA;
import JinRyuu.JRMCore.JRMCoreGuiScreen;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.entity.ModelBipedBody;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.tobiasmjc.dbcadditions.DBCAConfig;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForm;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForms;
import com.tobiasmjc.dbcadditions.data.forms.display.HairType;
import com.tobiasmjc.dbcadditions.event.DBCAClientHandler;
import com.tobiasmjc.dbcadditions.mixin.late.jbra.MixinModelBipedBody;
import com.tobiasmjc.dbcadditions.utils.DBCAUtils;
import com.tobiasmjc.dbcadditions.utils.DataUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ModelBipedDBC.class}, remap=false)
public class MixinModelBipedDBC
extends ModelBipedBody {
    private static String getDNSFromNormal(String hair) {
        switch (hair) {
            case "0": {
                return "025050545050210250505450501801505045505021025050475050180147507467503248505072675043255250726750360150505667501922475071675038255050716750380152507167503202475071675032025250716750300050507167503000505047655036205250276550362250502765503620475027655036225250306550363150503065503622475030655034015250276550250147502765503000505027655036175050505050803150505050508028505050505080225050505050801750505050508022505050505080255050505050801750505050508000505050505080005050505050800050505050508000505050505080005050505050800050505050508000505050505080005050505050803154508067504931545080615028285450766150472854506561506551525080675038655250806150786052507861503451525069615050625050806950528250508061503485505078615030625050696150585149508069506157495080615080624950786150805149506961504920";
            }
            case "1": {
                return "225067556150391150675561502311503245615023205032456150361750505450507117505054505071175050545050711750505450507101505054505071025050545050710150505450507100505054505071005050545050710050505450507122505045505071225050455050722250504550507222505045505072015050455050720150504550507201505045505072005050455050720050504550507200505045505072225445505050712254475050507122545250505071225454505050710154455050507101544950505071015450505050710154545050507100545050505071005450505050710054505050507100545050505071005450505050710054505050507100545050505071005450505050712850505650507465505074801930655050251819303750504550507437505056505074685050748018326850502519183234505045505074345050565050746850507480183868505025181838345050455050743450505650507480505074801843805050251819433450504550507420";
            }
            case "2": {
                return "005050545050210260506950501901605030505018005050475050191452508050501848545080505018255450805050182265586180501828525076505018315250785050180152507650501802475076505018025250745050180050508050501820675871185019375450195050183754501850501842525018505018225450255050183754612550501822545627505018015452275050180150502550501800505027505018017650505050193176505050501928765050505019177650505050190276505050501911765050505019227650505050191476505050501911765050505019207650505050191176505050501905765050505019007650505050190076505050501900765050505019007650505050193154501950501831545080505018285450215050182854508050501951545019505018685450194750186054501950501851545080525018625450215050188254501950501885545018505018625450805050185154502150501857545021505018625450215050185154508050501820";
            }
            case "3": {
                return "314338673450211443386339501825434780635021224141526771187750501947503257505080475043575250184750362250507847501954475078475038345050784750386252508047503222475074475032345250764750300050507167503028505080475036605250194750367450501847503648505021475036625050214750364850502347503660475023475034485250234750252547502347503000505018505036748050525050808280505050508080805050505080808050495050808280505250508091805050505080918050495050808880504950508082805052505080828050505050808280505050508080805049505080005050505050800050505050508000505050505080005050505050804552501949504951545080495028575250185250474552508050506565545019495038855450184950786854501852503488525080505050715450284950528854508049503497525080505030945254805050588052502549506180525018495080855050185250808052508049504920";
            }
            case "4": {
                return "025050545050210250505450501801505045505021025050475050180147507467503248505072675043255250726750360150505667501922475071675038255050716750380152507167503202475071675032025250716750300050507167503000505047655036205250276550362250502765503620475027655036225250306550363150503065503622475030655034015250276550250147502765503000505027655036175050505050803150505050508028505050505080225050505050801750505050508022505050505080255050505050801750505050508000505050505080005050505050800050505050508000505050505080005050505050800050505050508000505050505080005050505050803154508067504931545080615028285450766150472854506561506551525080675038655250806150786052507861503451525069615050625050806950528250508061503485505078615030625050696150585149508069506157495080615080624950786150805149506961504920";
            }
            case "5": {
                return "025050545050210250505450501801505045505021025050475050180147507467503248505072675043255250726750360150505667501922475071675038255050716750380152507167503202475071675032025250716750300050507167503000505047655036205250276550362250502765503620475027655036225250306550363150503065503622475030655034015250276550250147502765503000505027655036175050505050803150505050508028505050505080225050505050801750505050508022505050505080255050505050801750505050508000505050505080005050505050800050505050508000505050505080005050505050800050505050508000505050505080005050505050803154508067504931545080615028285450766150472854506561506551525080675038655250806150786052507861503451525069615050625050806950528250508061503485505078615030625050696150585149508069506157495080615080624950786150805149506961504920";
            }
            case "6": {
                return "025050545050210250505450501801505045505021025050475050180147507467503248505072675043255250726750360150505667501922475071675038255050716750380152507167503202475071675032025250716750300050507167503000505047655036205250276550362250502765503620475027655036225250306550363150503065503622475030655034015250276550250147502765503000505027655036175050505050803150505050508028505050505080225050505050801750505050508022505050505080255050505050801750505050508000505050505080005050505050800050505050508000505050505080005050505050800050505050508000505050505080005050505050803154508067504931545080615028285450766150472854506561506551525080675038655250806150786052507861503451525069615050625050806950528250508061503485505078615030625050696150585149508069506157495080615080624950786150805149506961504920";
            }
            case "7": {
                return "025050545050210250505450501801505045505021025050475050180147507467503248505072675043255250726750360150505667501922475071675038255050716750380152507167503202475071675032025250716750300050507167503000505047655036205250276550362250502765503620475027655036225250306550363150503065503622475030655034015250276550250147502765503000505027655036175050505050803150505050508028505050505080225050505050801750505050508022505050505080255050505050801750505050508000505050505080005050505050800050505050508000505050505080005050505050800050505050508000505050505080005050505050803154508067504931545080615028285450766150472854506561506551525080675038655250806150786052507861503451525069615050625050806950528250508061503485505078615030625050696150585149508069506157495080615080624950786150805149506961504920";
            }
            case "8": {
                return "225067556150391150675561502311503245615023205032456150361750505450507117505054505071175050545050711750505450507101505054505071025050545050710150505450507100505054505071005050545050710050505450507122505045505071225050455050722250504550507222505045505072015050455050720150504550507201505045505072005050455050720050504550507200505045505072225445505050712254475050507122545250505071225454505050710154455050507101544950505071015450505050710154545050507100545050505071005450505050710054505050507100545050505071005450505050710054505050507100545050505071005450505050712850505650507465505074801930655050251819303750504550507437505056505074685050748018326850502519183234505045505074345050565050746850507480183868505025181838345050455050743450505650507480505074801843805050251819433450504550507420";
            }
            case "9": {
                return "225067556150391150675561502311503245615023205032456150361750505450507117505054505071175050545050711750505450507101505054505071025050545050710150505450507100505054505071005050545050710050505450507122505045505071225050455050722250504550507222505045505072015050455050720150504550507201505045505072005050455050720050504550507200505045505072225445505050712254475050507122545250505071225454505050710154455050507101544950505071015450505050710154545050507100545050505071005450505050710054505050507100545050505071005450505050710054505050507100545050505071005450505050712850505650507465505074801930655050251819303750504550507437505056505074685050748018326850502519183234505045505074345050565050746850507480183868505025181838345050455050743450505650507480505074801843805050251819433450504550507420";
            }
            case "10": {
                return "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
            }
            case "11": {
                return "008080185050000018191950500017781874508000001819195050000050505550500000803441801900977634438018009780344369180097743455761800978030388019009480343871180097802834781900977627367619009725182158180097806758711900977867588018000050504550500000505045505000978065637418009780675880180097807165801900978072677618009780716080180097258018581800973878476080009736784756800097362152568000973821546080009741255250190097381850568000973818505680009741256150180097411949568000974118505080009741185050800094411952568000003878415880009741764554800097412354548000003821605880009780344767180097803445671900977834586718009778345667191897783454651800978034506519009780345265180097803450651800978034416919009780345260500097803450605000978034616918009778345080180097783450801800972134508019009780345680190020";
            }
        }
        return "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
    }

    private static boolean isFusionUsingHairs(String h1, String h2, String h3, String h4) {
        return h1.equals(h3) && h2.equals(h4) || h1.equals(h4) && h2.equals(h3);
    }

    @Inject(method={"renderHairsV2(FLjava/lang/String;FIIIILJinRyuu/JBRA/RenderPlayerJBRA;Lnet/minecraft/client/entity/AbstractClientPlayer;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private static void combineFusionHair(float par1, String h, float hl, int s, int rg, int pl, int rc, RenderPlayerJBRA rp, AbstractClientPlayer abstractClientPlayer, CallbackInfo ci, @Local(ordinal=0) LocalRef<String> hair) {
        int i;
        if (!DBCAConfig.FusionModifyAppearance) {
            return;
        }
        boolean isPotaraFusion = DataUtils.isPotaraFusion((EntityPlayer)abstractClientPlayer);
        EntityPlayer playerPartner = DataUtils.getFusionPartner((EntityPlayer)abstractClientPlayer);
        if (playerPartner == null) {
            return;
        }
        String hair1 = ExtendedPlayer.get((EntityPlayer)abstractClientPlayer).getHairCode();
        String hair2 = ExtendedPlayer.get(playerPartner).getHairCode();
        int pl2 = 0;
        for (int i2 = 0; i2 < JRMCoreH.plyrs.length; ++i2) {
            if (!JRMCoreH.plyrs[i2].equals(playerPartner.func_70005_c_())) continue;
            pl2 = i2;
        }
        String[] s1 = JRMCoreH.data1[pl].split(";");
        String[] s2 = JRMCoreH.data1[pl2].split(";");
        String dns = s1[1];
        String dns2 = s2[1];
        int hairback1 = JRMCoreH.dnsHairB(dns);
        int hairback2 = JRMCoreH.dnsHairB(dns2);
        if (hairback1 != 12) {
            hair1 = MixinModelBipedDBC.getDNSFromNormal(hairback1 + "");
        }
        if (hairback2 != 12) {
            hair2 = MixinModelBipedDBC.getDNSFromNormal(hairback2 + "");
        }
        String hair3 = "";
        String longestHair = "";
        String shortestHair = "";
        int size1 = 0;
        int size2 = 0;
        for (i = 0; i < hair1.length(); ++i) {
            if ((i + 1) % 14 != 1 && (i + 1) % 14 != 2) continue;
            size1 += Integer.parseInt(hair1.charAt(i) + "");
        }
        for (i = 0; i < hair2.length(); ++i) {
            if ((i + 1) % 14 != 1 && (i + 1) % 14 != 2) continue;
            size2 += Integer.parseInt(hair2.charAt(i) + "");
        }
        if (size1 >= size2) {
            longestHair = hair1;
            shortestHair = hair2;
        } else {
            longestHair = hair2;
            shortestHair = hair1;
        }
        if (MixinModelBipedDBC.isFusionUsingHairs(hair1, hair2, MixinModelBipedDBC.getDNSFromNormal("0"), MixinModelBipedDBC.getDNSFromNormal("2"))) {
            hair3 = "004952185650302021492339503025235076395078005850185650800143307650500001473078505000015030765050000150307850500001473074505000014930765050000150307650500001473074505000014930765050000047307650500001413036505000011830525050000118305250500001183052505000011830565050000118305650500001183054505000011830565050000118305650500000183058505000235050765050003450507650500034525078505000235250785050001245494950500023454949505000234549495050001245494950500022502180505000227649495050002580494750500022804950505000004550505050000045505050500000455050505000004550505050000249508050500034545080505000345450805050002354508050500034545080505000345450805050003454508050500023545080505000345450805050005452507639500034545080505000345450805050003454508050500068545080505000745450785050003454508050500020";
        } else {
            for (i = 0; i < longestHair.length(); ++i) {
                hair3 = shortestHair.length() <= i ? hair3 + longestHair.charAt(i) : (i + 1 < (isPotaraFusion ? 32 : 48) || i + 1 > 171 && i + 1 < 352 ? hair3 + shortestHair.charAt(i) : ((i + 1) % 2 == 1 || (i + 1) % 3 == 0 ? hair3 + longestHair.charAt(i) : hair3 + shortestHair.charAt(i)));
            }
        }
        hair.set(hair3);
    }

    @Inject(method={"renderHairsV2(FLjava/lang/String;FIIIILJinRyuu/JBRA/RenderPlayerJBRA;Lnet/minecraft/client/entity/AbstractClientPlayer;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void customFormHairDNSRender(float par1, String h, float hl, int s, int rg, int pl, int rc, RenderPlayerJBRA rp, AbstractClientPlayer abstractClientPlayer, CallbackInfo ci, @Local(ordinal=0) LocalRef<String> hair) {
        if (!DBCAConfig.CustomForms) {
            return;
        }
        byte state = DataUtils.getDBCAState((EntityPlayer)abstractClientPlayer);
        DBCAForm form = DBCAForms.getForm(state);
        String playerName = JRMCoreH.plyrs[pl];
        if (DataUtils.getFusionPartner((EntityPlayer)abstractClientPlayer) != null) {
            if (DBCAUtils.isBaseDBC((EntityPlayer)abstractClientPlayer)) {
                MixinModelBipedDBC.changePlayerColorFusion((EntityPlayer)abstractClientPlayer, "DNS");
            }
            if (!(s != 0 && s != 9 || form != null && form.getHairType() != HairType.BASE)) {
                rp.setStateChange(200, playerName);
                rp.setState2Change(0, playerName);
                rp.setState(HairType.getIDFromType(HairType.SS1), playerName);
            }
        }
        if (rc == 1 || rc == 2 || rc == 0 || rc == 5) {
            if (state > 0) {
                if (form == null) {
                    return;
                }
                int hairColor = form.getHairColor(DataUtils.legendary((EntityPlayer)abstractClientPlayer), DataUtils.divine((EntityPlayer)abstractClientPlayer));
                if (hairColor != -1) {
                    RenderPlayerJBRA.glColor3f(hairColor);
                }
                if (form.getHairType() == HairType.SS4) {
                    hair.set("373852546750347428545480193462285654801934283647478050340147507467501848505072675018255250726750183760656580501822475071675018255050716750189730327158501802475071675018973225673850189765616160501820414547655019545654216550195754542165501920475027655019943669346576193161503065231900475030655019406534276538199465393460501997654138655019976345453950189760494941501897615252415018976354563850189763494736501897614949395018976152523950189763525234501897584749395018976150493850189760545234501897585250415018885445474550189754475041501897545250435018885454523950185143607861501897415874585018514369196150185147768078391865525680565018974356806150188843567861501868396374615018975056805650189750568056501885582374615018975823726150187149568054501877495680565018774950785650189163236961501820");
                    return;
                }
                if (form.getHairType() == HairType.SS3) {
                    ci.cancel();
                    return;
                }
                if (!form.getCustomHair().isEmpty() && form.getHairType() == HairType.CUSTOM) {
                    hair.set(form.getCustomHair());
                    rp.setState(1, playerName);
                    rp.setStateChange(0, playerName);
                } else {
                    rp.setStateChange(form.getHairType() == HairType.BASE ? 0 : 200, playerName);
                    rp.setState2Change(form.getHairType() != HairType.SS2 ? 0 : 200, playerName);
                    rp.setState(HairType.getIDFromType(form.getHairType()), playerName);
                }
            } else if (rc == 0 || rc == 5) {
                rp.setState(0, playerName);
                rp.setStateChange(0, playerName);
            }
        }
    }

    @Inject(method={"renderHairs(FLjava/lang/String;Ljava/lang/String;)Ljava/lang/String;"}, at={@At(value="TAIL")}, cancellable=true)
    public void customRaceRender(float par1, String hair, String anim, CallbackInfoReturnable<String> ci, @Local(ordinal=0) LocalRef<String> Hair) {
        float r3;
        float r2;
        float r;
        if (!DBCAConfig.CustomRaces) {
            return;
        }
        float f6 = ModelBipedDBC.f;
        GL11.glPushMatrix();
        GL11.glScalef((float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.7f)), (float)(1.0f / f6), (float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.7f)));
        GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
        MixinModelBipedBody mixin = (MixinModelBipedBody)((Object)this);
        if (hair.equals("biotailmax2")) {
            GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
            this.transRot(par1, this.B1);
            mixin.BioTM.func_78785_a(par1);
        }
        if (hair.equals("biotail2")) {
            GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
            this.transRot(par1, this.B1);
            mixin.BioT.func_78785_a(par1);
        }
        if (hair.equals("biotailmax")) {
            this.transRot(par1, this.B1);
            GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
            mixin.BioTM.func_78785_a(par1);
            r = MathHelper.func_76126_a((float)(this.rot3 * 0.02f)) * 0.1f;
            r2 = MathHelper.func_76134_b((float)(this.rot3 * 0.02f)) * 0.1f;
            r3 = MathHelper.func_76134_b((float)(this.rot3 * 0.14f)) * 0.1f;
            mixin.btailS1M.field_78796_g = 0.2f;
            if (mod_JBRA.a6P9H9B) {
                mixin.btailS1M.field_78796_g += MathHelper.func_76134_b((float)(this.rot3 * 0.09f)) * 0.2f - 0.2f + r;
            }
            mixin.btailS1M.field_78795_f = -0.3f;
            mixin.btailS2M.field_78796_g = 0.2f;
            if (mod_JBRA.a6P9H9B) {
                mixin.btailS2M.field_78796_g += MathHelper.func_76134_b((float)(this.rot3 * 0.09f)) * 0.2f - 0.2f + r2 + r3;
            }
            mixin.btailS2M.field_78795_f = 0.4f;
            mixin.btailS3M.field_78796_g = 0.1f;
            if (mod_JBRA.a6P9H9B) {
                mixin.btailS3M.field_78796_g += MathHelper.func_76134_b((float)(this.rot3 * 0.09f)) * 0.1f - 0.1f + r + r3;
            }
            mixin.btailS3M.field_78795_f = 0.6f;
            if (mod_JBRA.a6P9H9B) {
                mixin.btailS3M.field_78795_f += MathHelper.func_76126_a((float)(this.rot3 * 0.09f)) * 0.4f + 0.3f;
            }
            mixin.btailS4M.field_78796_g = 0.1f;
            if (mod_JBRA.a6P9H9B) {
                mixin.btailS4M.field_78796_g += MathHelper.func_76134_b((float)(this.rot3 * 0.09f)) * 0.4f - 0.1f + r2;
            }
            mixin.btailS4M.field_78795_f = 0.3f;
            if (mod_JBRA.a6P9H9B) {
                mixin.btailS4M.field_78795_f += MathHelper.func_76126_a((float)(this.rot3 * 0.09f)) * 0.1f - 0.2f;
            }
            mixin.btailS5M.field_78796_g = 0.2f;
            if (mod_JBRA.a6P9H9B) {
                mixin.btailS5M.field_78796_g += MathHelper.func_76134_b((float)(this.rot3 * 0.09f)) * 0.4f - 0.2f + r + r3;
            }
            mixin.btailS5M.field_78795_f = -0.2f;
            if (mod_JBRA.a6P9H9B) {
                mixin.btailS5M.field_78795_f += MathHelper.func_76126_a((float)(this.rot3 * 0.09f)) * 0.1f - 0.3f;
            }
            mixin.btailS6M.field_78796_g = 0.2f;
            if (mod_JBRA.a6P9H9B) {
                mixin.btailS6M.field_78796_g += MathHelper.func_76134_b((float)(this.rot3 * 0.09f)) * 0.4f - 0.2f + r + r3;
            }
            mixin.btailS6M.field_78795_f = -0.2f;
            if (mod_JBRA.a6P9H9B) {
                mixin.btailS6M.field_78795_f += MathHelper.func_76126_a((float)(this.rot3 * 0.09f)) * 0.1f - 0.3f;
            }
        }
        if (hair.equals("biotail")) {
            this.transRot(par1, this.B1);
            GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
            mixin.BioT.func_78785_a(par1);
            r = MathHelper.func_76126_a((float)(this.rot3 * 0.02f)) * 0.1f;
            r2 = MathHelper.func_76134_b((float)(this.rot3 * 0.02f)) * 0.1f;
            r3 = MathHelper.func_76134_b((float)(this.rot3 * 0.14f)) * 0.1f;
            mixin.btailS1.field_78796_g = 0.2f;
            if (mod_JBRA.a6P9H9B) {
                mixin.btailS1.field_78796_g += MathHelper.func_76134_b((float)(this.rot3 * 0.09f)) * 0.2f - 0.2f + r;
            }
            mixin.btailS1.field_78795_f = -0.3f;
            mixin.btailS2.field_78796_g = 0.2f;
            if (mod_JBRA.a6P9H9B) {
                mixin.btailS2.field_78796_g += MathHelper.func_76134_b((float)(this.rot3 * 0.09f)) * 0.2f - 0.2f + r2 + r3;
            }
            mixin.btailS2.field_78795_f = 0.4f;
            mixin.btailS3.field_78796_g = 0.1f;
            if (mod_JBRA.a6P9H9B) {
                mixin.btailS3.field_78796_g += MathHelper.func_76134_b((float)(this.rot3 * 0.09f)) * 0.1f - 0.1f + r + r3;
            }
            mixin.btailS3.field_78795_f = 0.6f;
            if (mod_JBRA.a6P9H9B) {
                mixin.btailS3.field_78795_f += MathHelper.func_76126_a((float)(this.rot3 * 0.09f)) * 0.4f + 0.3f;
            }
            mixin.btailS4.field_78796_g = 0.1f;
            if (mod_JBRA.a6P9H9B) {
                mixin.btailS4.field_78796_g += MathHelper.func_76134_b((float)(this.rot3 * 0.09f)) * 0.4f - 0.1f + r2;
            }
            mixin.btailS4.field_78795_f = 0.3f;
            if (mod_JBRA.a6P9H9B) {
                mixin.btailS4.field_78795_f += MathHelper.func_76126_a((float)(this.rot3 * 0.09f)) * 0.1f - 0.2f;
            }
            mixin.btailS5.field_78796_g = 0.2f;
            if (mod_JBRA.a6P9H9B) {
                mixin.btailS5.field_78796_g += MathHelper.func_76134_b((float)(this.rot3 * 0.09f)) * 0.4f - 0.2f + r + r3;
            }
            mixin.btailS5.field_78795_f = -0.2f;
            if (mod_JBRA.a6P9H9B) {
                mixin.btailS5.field_78795_f += MathHelper.func_76126_a((float)(this.rot3 * 0.09f)) * 0.1f - 0.3f;
            }
            mixin.btailS6.field_78796_g = 0.2f;
            if (mod_JBRA.a6P9H9B) {
                mixin.btailS6.field_78796_g += MathHelper.func_76134_b((float)(this.rot3 * 0.09f)) * 0.4f - 0.2f + r + r3;
            }
            mixin.btailS6.field_78795_f = -0.2f;
            if (mod_JBRA.a6P9H9B) {
                mixin.btailS6.field_78795_f += MathHelper.func_76126_a((float)(this.rot3 * 0.09f)) * 0.1f - 0.3f;
            }
        }
        if (hair.startsWith("biowings")) {
            mixin.wing.field_78796_g = Math.abs(this.field_78113_g.field_78796_g / 7.0f) + this.field_78115_e.field_78796_g;
            mixin.wing.field_78795_f = Math.abs(this.field_78113_g.field_78795_f / 7.0f) + this.field_78115_e.field_78795_f;
            mixin.wing.field_78800_c = this.field_78115_e.field_78800_c;
            mixin.wing.field_78797_d = this.field_78115_e.field_78797_d;
            mixin.wing.func_78785_a(par1);
            mixin.wing2.field_78796_g = Math.abs(this.field_78113_g.field_78796_g / 7.0f) + this.field_78115_e.field_78796_g;
            mixin.wing2.field_78795_f = Math.abs(this.field_78113_g.field_78795_f / 7.0f) + this.field_78115_e.field_78795_f;
            mixin.wing2.field_78800_c = this.field_78115_e.field_78800_c;
            mixin.wing2.field_78797_d = this.field_78115_e.field_78797_d;
            mixin.wing2.func_78785_a(par1);
        }
        if (hair.startsWith("bioheadsemi")) {
            mixin.biohead.field_78796_g = this.field_78116_c.field_78796_g;
            mixin.biohead.field_78795_f = this.field_78116_c.field_78795_f;
            mixin.biohead.field_78800_c = this.field_78116_c.field_78800_c;
            mixin.biohead.field_78797_d = this.field_78116_c.field_78797_d;
            mixin.biohead.func_78785_a(par1);
        }
        GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }

    private void transRot(float f5, ModelRenderer m) {
        GL11.glTranslatef((float)(m.field_78800_c * f5), (float)(m.field_78797_d * f5), (float)(m.field_78798_e * f5));
        if (m.field_78808_h != 0.0f) {
            GL11.glRotatef((float)(m.field_78808_h * 57.295776f), (float)0.0f, (float)0.0f, (float)1.0f);
        }
        if (m.field_78796_g != 0.0f) {
            GL11.glRotatef((float)(m.field_78796_g * 57.295776f), (float)0.0f, (float)1.0f, (float)0.0f);
        }
        if (m.field_78795_f != 0.0f) {
            GL11.glRotatef((float)(m.field_78795_f * 57.295776f), (float)1.0f, (float)0.0f, (float)0.0f);
        }
    }

    private static void changePlayerColorFusion(EntityPlayer player, String hair) {
        int i;
        if (!DBCAConfig.FusionModifyAppearance) {
            return;
        }
        int pl = 0;
        int pl2 = 0;
        EntityPlayer playerPartner = DataUtils.getFusionPartner(player);
        if (playerPartner == null) {
            return;
        }
        for (i = 0; i < JRMCoreH.plyrs.length; ++i) {
            if (!JRMCoreH.plyrs[i].equals(player.func_70005_c_())) continue;
            pl = i;
        }
        for (i = 0; i < JRMCoreH.plyrs.length; ++i) {
            if (!JRMCoreH.plyrs[i].equals(playerPartner.func_70005_c_())) continue;
            pl2 = i;
        }
        String[] s1 = JRMCoreH.data1[pl].split(";");
        String[] s2 = JRMCoreH.data1[pl2].split(";");
        String dns = s1[1];
        String dns2 = s2[1];
        int p1haircol = JRMCoreH.dnsHairC(dns);
        int p1eyec1 = JRMCoreH.dnsEyeC1(dns);
        int p1eyec2 = JRMCoreH.dnsEyeC2(dns);
        int p1TailColor = JRMCoreH.dnsBodyT(dns);
        int p2haircol = JRMCoreH.dnsHairC(dns2);
        int p2eyec1 = JRMCoreH.dnsEyeC1(dns2);
        int p2eyec2 = JRMCoreH.dnsEyeC2(dns2);
        int p2TailColor = JRMCoreH.dnsBodyT(dns2);
        p1TailColor = p1TailColor != 0 ? p1TailColor : 6498048;
        int n = p2TailColor = p2TailColor != 0 ? p2TailColor : 6498048;
        if (hair.contains("EYELEFT")) {
            RenderPlayerJBRA.glColor3f(DataUtils.mergeColors(p1eyec1, p2eyec1));
        }
        if (hair.contains("EYERIGHT")) {
            RenderPlayerJBRA.glColor3f(DataUtils.mergeColors(p1eyec2, p2eyec2));
        }
        if (hair.startsWith("SJT")) {
            RenderPlayerJBRA.glColor3f(DataUtils.mergeColors(p1TailColor, p2TailColor));
        }
        if (hair.contains("EYEBROW") || hair.startsWith("A") || hair.startsWith("N") || hair.equals("DNS")) {
            RenderPlayerJBRA.glColor3f(DataUtils.mergeColors(p1haircol, p2haircol));
        }
    }

    @Inject(method={"renderHairs(FLjava/lang/String;Ljava/lang/String;)Ljava/lang/String;"}, at={@At(value="HEAD")}, cancellable=true)
    public void fusionHairRender(float par1, String hair, String anim, CallbackInfoReturnable<String> ci, @Local(ordinal=0) LocalRef<String> Hair) {
        EntityPlayer fusionPartner;
        int st;
        String[] stringArray;
        int rg;
        EntityPlayer player = DBCAClientHandler.currentRenderPlayer;
        int pl = 0;
        for (int i = 0; i < JRMCoreH.plyrs.length; ++i) {
            if (!player.func_70005_c_().equals(JRMCoreH.plyrs[i])) continue;
            pl = i;
        }
        String[] s = JRMCoreH.data1[pl].split(";");
        int race = Integer.parseInt(s[0]);
        int n = rg = JRMCoreH.data4 == null ? 0 : Integer.parseInt(JRMCoreH.data4[pl].split(";")[0]);
        if (JRMCoreH.data2 == null) {
            String[] stringArray2 = new String[3];
            stringArray2[0] = "0";
            stringArray2[1] = "0";
            stringArray = stringArray2;
            stringArray2[2] = "0";
        } else {
            stringArray = JRMCoreH.data2[pl].split(";");
        }
        String[] state = stringArray;
        int powerType = Integer.parseInt(s[2]);
        int n2 = JRMCoreH.rc_arc(race) && JRMCoreGuiScreen.ufc ? 6 : (st = powerType == 2 || race == 0 ? 0 : (int)Byte.parseByte(state[0]));
        if (player == JBRAClient.mc.field_71439_g && JRMCoreGuiScreen.hairPreview > 0) {
            st = JRMCoreGuiScreen.hairPreviewStates[JRMCoreGuiScreen.hairPreview];
        }
        if ((fusionPartner = DataUtils.getFusionPartner(player)) != null) {
            RenderPlayerJBRA renderer = (RenderPlayerJBRA)RenderManager.field_78727_a.func_78713_a((Entity)player);
            ModelBipedDBC dbc = (ModelBipedDBC)((Object)this);
            if (hair.startsWith("A") || hair.startsWith("B") || hair.startsWith("C") || hair.startsWith("N")) {
                dbc.renderHairsV2(par1, hair, 0.0f, st, rg, pl, race, renderer, (AbstractClientPlayer)player);
                ci.cancel();
            }
        }
    }

    @Inject(method={"renderHairs(FLjava/lang/String;Ljava/lang/String;)Ljava/lang/String;"}, at={@At(value="HEAD")}, cancellable=true)
    public void formColorRender(float par1, String hair, String anim, CallbackInfoReturnable<String> ci, @Local(ordinal=0) LocalRef<String> Hair) {
        if (!DBCAConfig.CustomForms) {
            return;
        }
        EntityPlayer player = DBCAClientHandler.currentRenderPlayer;
        int pl = -1;
        int eyes = -1;
        int gen = -1;
        for (int i = 0; i < JRMCoreH.plyrs.length; ++i) {
            String dns;
            int skintype;
            if (!player.func_70005_c_().equals(JRMCoreH.plyrs[i])) continue;
            pl = i;
            String[] s = JRMCoreH.data1[pl].split(";");
            if (DBCAUtils.isBaseDBC(player)) {
                MixinModelBipedDBC.changePlayerColorFusion(player, hair);
            }
            eyes = (skintype = JRMCoreH.dnsSkinT(dns = s[1])) == 0 ? 0 : JRMCoreH.dnsEyes(dns);
            gen = JRMCoreH.dnsGender(dns);
        }
        byte state = DataUtils.getDBCAState(player);
        if (state > 0) {
            int tailColor;
            DBCAForm form = DBCAForms.getForm(state);
            if (form == null) {
                return;
            }
            int hairColor = form.getHairColor(DataUtils.legendary(player), DataUtils.divine(player));
            if (hair.startsWith("N") && hairColor != -1) {
                RenderPlayerJBRA.glColor3f(hairColor);
            }
            int eyeColor = form.getEyeColor(DataUtils.legendary(player), DataUtils.divine(player));
            if ((hair.contains("EYELEFT") || hair.contains("EYERIGHT")) && (eyeColor != -1 || form.IsBerserk())) {
                if (form.IsBerserk()) {
                    ci.cancel();
                } else {
                    RenderPlayerJBRA.glColor3f(eyeColor);
                }
            }
            if (hair.contains("EYEBROW")) {
                if (!form.hasEyeBrow()) {
                    Minecraft.func_71410_x().field_71446_o.func_110577_a(new ResourceLocation("jinryuumodscore", "cc/ssj3eyebrow/" + (gen == 1 ? "f" : "") + "humw" + eyes + ".png"));
                } else if (hairColor != -1) {
                    RenderPlayerJBRA.glColor3f(hairColor);
                }
            }
            if ((hair.contains("SJT1") || hair.contains("SJT2")) && (tailColor = form.getTailColor(DataUtils.legendary(player), DataUtils.divine(player))) != -1) {
                RenderPlayerJBRA.glColor3f(tailColor);
            }
            if (hair.startsWith("A")) {
                if (hairColor != -1) {
                    RenderPlayerJBRA.glColor3f(hairColor);
                }
                Hair.set(HairType.getIDFromType2(form.getHairType()) + hair.split("A")[1]);
            }
        }
    }
}

