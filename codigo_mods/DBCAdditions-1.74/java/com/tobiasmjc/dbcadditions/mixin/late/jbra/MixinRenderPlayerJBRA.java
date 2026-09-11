/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.entity.RenderBiped
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.tobiasmjc.dbcadditions.mixin.late.jbra;

import JinRyuu.JBRA.JBRAClient;
import JinRyuu.JBRA.ModelBipedDBC;
import JinRyuu.JBRA.RenderPlayerJBRA;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreGuiScreen;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHC;
import JinRyuu.JRMCore.JRMCoreHDBC;
import JinRyuu.JRMCore.JRMCoreHJYC;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.tobiasmjc.dbcadditions.DBCAConfig;
import com.tobiasmjc.dbcadditions.common.CommonProxy;
import com.tobiasmjc.dbcadditions.data.ability.DBCAAbilities;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForm;
import com.tobiasmjc.dbcadditions.data.forms.DBCAForms;
import com.tobiasmjc.dbcadditions.data.forms.display.HairType;
import com.tobiasmjc.dbcadditions.data.forms.display.Tattoo;
import com.tobiasmjc.dbcadditions.data.races.DBCARace;
import com.tobiasmjc.dbcadditions.data.races.DBCARaces;
import com.tobiasmjc.dbcadditions.utils.DataUtils;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={RenderPlayerJBRA.class}, remap=false)
public class MixinRenderPlayerJBRA
extends RenderPlayer {
    @Shadow
    public ModelBipedDBC modelMain;
    @Shadow
    private static float r = 0.0f;
    @Shadow
    private static float g = 0.0f;
    @Shadow
    private static float b = 0.0f;
    @Shadow
    private static float r2 = 0.0f;
    @Shadow
    private static float g2 = 0.0f;
    @Shadow
    private static float b2 = 0.0f;
    private static int currSkinType;

    @Redirect(method={"kss"}, at=@At(value="INVOKE", target="LJinRyuu/JRMCore/JRMCoreHDBC;getPlayerColor2(IIIIIZZZZ)I"))
    private static int fixKiBladeColor(int t, int d, int p, int r, int s, boolean v, boolean y, boolean ui, boolean gd, @Local(name={"e"}) Entity entity) {
        if (!(entity instanceof EntityPlayer) || !DBCAConfig.CustomForms) {
            return JRMCoreHDBC.getPlayerColor2(t, d, p, r, s, v, y, ui, gd);
        }
        EntityPlayer player = (EntityPlayer)entity;
        DBCAForm form = DBCAForms.getForm(DataUtils.getDBCAState(player));
        if (form == null) {
            return JRMCoreHDBC.getPlayerColor2(t, d, p, r, s, v, y, ui, gd);
        }
        return form.getKiColor(DataUtils.legendary(player), DataUtils.divine(player));
    }

    @Inject(method={"renderEquippedItemsJBRA"}, at={@At(value="INVOKE", target="Lorg/lwjgl/opengl/GL11;glPopMatrix()V", ordinal=0, shift=At.Shift.BEFORE)}, cancellable=true)
    private void renderDBCAThings(AbstractClientPlayer p, float par2, CallbackInfo ci, @Local(name={"hairback"}) LocalIntRef hairback, @Local(name={"race"}) LocalIntRef race, @Local(name={"rg"}) LocalIntRef rg, @Local(name={"st"}) LocalIntRef st, @Local(name={"skintype"}) LocalIntRef skintype, @Local(name={"bodycm"}) LocalIntRef bodyCM, @Local(name={"bodyc1"}) LocalIntRef bodyC1, @Local(name={"bodyc2"}) LocalIntRef bodyC2, @Local(name={"bodyc3"}) LocalIntRef bodyC3, @Local(name={"msk"}) LocalBooleanRef mask, @Local(name={"plyrSpc"}) LocalIntRef plyrSpc, @Local(name={"supecoll"}) LocalIntRef supecoll, @Local(name={"supecolr"}) LocalIntRef supecolr, @Local(name={"eyec1"}) LocalIntRef eyec1, @Local(name={"eyec2"}) LocalIntRef eyec2, @Local(name={"stY"}) LocalIntRef stY, @Local(name={"gd"}) LocalBooleanRef gd, @Local(name={"v"}) LocalBooleanRef v, @Local(name={"l"}) LocalBooleanRef l, @Local(name={"dnsau"}) LocalRef<String> dnsau, @Local(name={"weight"}) LocalIntRef weight, @Local(name={"pl"}) LocalIntRef pl) {
        this.renderTattoos(p, skintype.get());
    }

    private void renderTattoos(AbstractClientPlayer p, int skintype) {
        if (!DBCAConfig.CustomForms || skintype == 0) {
            return;
        }
        DBCAForm form = DBCAForms.getForm(DataUtils.getDBCAState((EntityPlayer)p));
        if (form == null) {
            return;
        }
        String tattoo = "";
        for (Tattoo t : form.getTattoos()) {
            switch (t) {
                case NONE: {
                    break;
                }
                case NAMEKIAN_ORANGE: {
                    tattoo = "namekian";
                    break;
                }
                case GOD: {
                    tattoo = "godofdestruction";
                    break;
                }
                case OMNI: {
                    tattoo = "omni";
                    break;
                }
                case INFINITY: {
                    tattoo = "infinity";
                    break;
                }
                case SUKUNA: {
                    tattoo = "sukuna";
                    break;
                }
                case DRAGONBALLS: {
                    tattoo = "dragonballs";
                    break;
                }
                case DAIMA_SSJ4: {
                    tattoo = "daimassj4";
                }
            }
            float h1 = 1.0f;
            if (tattoo.isEmpty()) continue;
            int tattooColor = form.getTattooColor(DataUtils.legendary((EntityPlayer)p), DataUtils.divine((EntityPlayer)p));
            if (tattooColor != -1) {
                RenderPlayerJBRA.glColor3f(tattooColor);
            } else {
                GL11.glColor3f((float)(h1 + MixinRenderPlayerJBRA.getR1()), (float)(h1 + MixinRenderPlayerJBRA.getG1()), (float)(h1 + MixinRenderPlayerJBRA.getB1()));
            }
            this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/tattoo/" + tattoo + ".png"));
            this.modelMain.renderBody(0.0625f);
        }
    }

    @Inject(method={"renderEquippedItemsJBRA"}, at={@At(value="INVOKE", target="Lorg/lwjgl/opengl/GL11;glPushMatrix()V", ordinal=0, shift=At.Shift.AFTER)}, cancellable=true)
    private void renderCustomRace(AbstractClientPlayer p, float par2, CallbackInfo ci, @Local(name={"hairback"}) LocalIntRef hairback, @Local(name={"race"}) LocalIntRef race, @Local(name={"rg"}) LocalIntRef rg, @Local(name={"st"}) LocalIntRef st, @Local(name={"skintype"}) LocalIntRef skintype, @Local(name={"bodycm"}) LocalIntRef bodyCM, @Local(name={"bodyc1"}) LocalIntRef bodyC1, @Local(name={"bodyc2"}) LocalIntRef bodyC2, @Local(name={"bodyc3"}) LocalIntRef bodyC3, @Local(name={"msk"}) LocalBooleanRef mask, @Local(name={"plyrSpc"}) LocalIntRef plyrSpc, @Local(name={"supecoll"}) LocalIntRef supecoll, @Local(name={"supecolr"}) LocalIntRef supecolr, @Local(name={"eyec1"}) LocalIntRef eyec1, @Local(name={"eyec2"}) LocalIntRef eyec2, @Local(name={"stY"}) LocalIntRef stY, @Local(name={"gd"}) LocalBooleanRef gd, @Local(name={"v"}) LocalBooleanRef v, @Local(name={"l"}) LocalBooleanRef l, @Local(name={"dnsau"}) LocalRef<String> dnsau, @Local(name={"weight"}) LocalIntRef weight, @Local(name={"pl"}) LocalIntRef pl) {
        if (!DBCAConfig.CustomRaces || skintype.get() == 0) {
            return;
        }
        byte dbarace = DataUtils.getDBCARace((EntityPlayer)p);
        byte dbaform = DataUtils.getDBCAState((EntityPlayer)p);
        boolean render = false;
        boolean isInMystic = JRMCoreH.StusEfctsClient(13, (EntityPlayer)p);
        if (race.get() == 0 && dbarace == DBCARaces.BIO_ANDROID.ID) {
            float h1 = 1.0f;
            int bodycm = bodyCM.get();
            int bodyc1 = bodyC1.get();
            int bodyc2 = bodyC2.get();
            ci.cancel();
            DBCAForm form = DBCAForms.getForm(dbaform);
            if (dbaform > 0 || JRMCoreGuiScreen.ufc || gd.get() || l.get() || isInMystic) {
                if (form != null) {
                    int[] bodyColors = form.getBodyColors(DataUtils.legendary((EntityPlayer)p), DataUtils.divine((EntityPlayer)p));
                    if (bodyColors[0] != -1) {
                        bodycm = bodyColors[0];
                    }
                    if (bodyColors[1] != -1) {
                        bodyc1 = bodyColors[1];
                    }
                    if (bodyColors[2] != -1) {
                        bodyc2 = bodyColors[2];
                    }
                }
                int stateRender = 0;
                if (form != null) {
                    stateRender = form.getStateRender();
                } else if (gd.get() || l.get() || isInMystic) {
                    stateRender = DBCAForms.Perfect.getStateRender();
                }
                if (JRMCoreGuiScreen.ufc || stateRender == DBCAForms.UncontrolledMax.getStateRender()) {
                    render = true;
                    int color = JRMCoreH.dnsauCM(dnsau.get());
                    if (dnsau.get().length() <= 6) {
                        color = 16719381;
                    }
                    if (form != null) {
                        int[] bodyColors = form.getBodyColors(DataUtils.legendary((EntityPlayer)p), DataUtils.divine((EntityPlayer)p));
                        if (!JRMCoreGuiScreen.ufc && form.getID() != DBCAForms.UncontrolledMax.getID() && bodyColors[0] != -1) {
                            color = bodyColors[0];
                        }
                    }
                    RenderPlayerJBRA.glColor3f(color);
                    this.modelMain.renderBody(0.0625f);
                    ResourceLocation bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio2UM.png");
                    this.func_110776_a(bdyskn);
                    this.modelMain.renderBody(0.0625f);
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/bioheadUM.png"));
                    this.modelMain.renderHairs(0.0625f, "bioheadsemi");
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/biotailUM.png"));
                    this.modelMain.renderHairs(0.0625f, "biotailmax");
                    GL11.glColor3f((float)(h1 + MixinRenderPlayerJBRA.getR1()), (float)(h1 + MixinRenderPlayerJBRA.getG1()), (float)(h1 + MixinRenderPlayerJBRA.getB1()));
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/biotailmax2.png"));
                    this.modelMain.renderHairs(0.0625f, "biotailmax2");
                    bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio1UM.png");
                    this.func_110776_a(bdyskn);
                    this.modelMain.renderBody(0.0625f);
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/biowingsP.png"));
                    this.modelMain.renderHairs(0.0625f, "biowings");
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/bioeyesbaseUM.png"));
                    this.modelMain.renderHairs(0.0625f, "EYEBASE");
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/bioeyeleftUM.png"));
                    this.modelMain.renderHairs(0.0625f, "EYELEFT");
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/bioeyerightUM.png"));
                    this.modelMain.renderHairs(0.0625f, "EYERIGHT");
                } else if (stateRender == DBCAForms.SemiPerfect.getStateRender()) {
                    render = true;
                    ResourceLocation bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio3S.png");
                    this.func_110776_a(bdyskn);
                    RenderPlayerJBRA.glColor3f(bodycm);
                    this.modelMain.renderBody(0.0625f);
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/bioskinsemi.png"));
                    this.modelMain.renderHairs(0.0625f, "bioheadsemi");
                    bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio1S.png");
                    this.func_110776_a(bdyskn);
                    RenderPlayerJBRA.glColor3f(bodyc1);
                    this.modelMain.renderBody(0.0625f);
                    GL11.glColor3f((float)(h1 + MixinRenderPlayerJBRA.getR1()), (float)(h1 + MixinRenderPlayerJBRA.getG1()), (float)(h1 + MixinRenderPlayerJBRA.getB1()));
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/biotail2.png"));
                    this.modelMain.renderHairs(0.0625f, "biotail2");
                    bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio2S.png");
                    this.func_110776_a(bdyskn);
                    RenderPlayerJBRA.glColor3f(bodyc2);
                    this.modelMain.renderBody(0.0625f);
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/biotailS.png"));
                    this.modelMain.renderHairs(0.0625f, "biotail");
                    bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio4S.png");
                    this.func_110776_a(bdyskn);
                    GL11.glColor3f((float)(h1 + MixinRenderPlayerJBRA.getR1()), (float)(h1 + MixinRenderPlayerJBRA.getG1()), (float)(h1 + MixinRenderPlayerJBRA.getB1()));
                    this.modelMain.renderBody(0.0625f);
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/bioeyesbaseS.png"));
                    GL11.glColor3f((float)(1.0f + MixinRenderPlayerJBRA.getR1()), (float)(1.0f + MixinRenderPlayerJBRA.getG1()), (float)(1.0f + MixinRenderPlayerJBRA.getB1()));
                    this.modelMain.renderHairs(0.0625f, "EYEBASE");
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/bioeyeleftS.png"));
                    RenderPlayerJBRA.glColor3f(JRMCoreH.rc_sai(race.get()) || gd.get() || JRMCoreHDBC.godKiUserBase(race.get(), stY.get()) || l.get() ? supecoll.get() : eyec1.get());
                    this.modelMain.renderHairs(0.0625f, "EYELEFT");
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/bioeyerightS.png"));
                    RenderPlayerJBRA.glColor3f(JRMCoreH.rc_sai(race.get()) || gd.get() || JRMCoreHDBC.godKiUserBase(race.get(), stY.get()) || l.get() ? supecolr.get() : eyec2.get());
                    this.modelMain.renderHairs(0.0625f, "EYERIGHT");
                } else if (stateRender == DBCAForms.Perfect.getStateRender()) {
                    render = true;
                    ResourceLocation bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio2P.png");
                    this.func_110776_a(bdyskn);
                    RenderPlayerJBRA.glColor3f(bodycm);
                    this.modelMain.renderBody(0.0625f);
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/bioskin.png"));
                    this.modelMain.renderHairs(0.0625f, "bioheadsemi");
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/biowingsP.png"));
                    this.modelMain.renderHairs(0.0625f, "biowings");
                    bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio1P.png");
                    GL11.glColor3f((float)(1.0f + MixinRenderPlayerJBRA.getR1()), (float)(1.0f + MixinRenderPlayerJBRA.getG1()), (float)(1.0f + MixinRenderPlayerJBRA.getB1()));
                    this.func_110776_a(bdyskn);
                    this.modelMain.renderBody(0.0625f);
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/bioeyesbaseS.png"));
                    GL11.glColor3f((float)(1.0f + MixinRenderPlayerJBRA.getR1()), (float)(1.0f + MixinRenderPlayerJBRA.getG1()), (float)(1.0f + MixinRenderPlayerJBRA.getB1()));
                    this.modelMain.renderHairs(0.0625f, "EYEBASE");
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/bioeyeleftS.png"));
                    RenderPlayerJBRA.glColor3f(JRMCoreH.rc_sai(race.get()) || gd.get() || JRMCoreHDBC.godKiUserBase(race.get(), stY.get()) || l.get() ? supecoll.get() : eyec1.get());
                    this.modelMain.renderHairs(0.0625f, "EYELEFT");
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/bioeyerightS.png"));
                    RenderPlayerJBRA.glColor3f(JRMCoreH.rc_sai(race.get()) || gd.get() || JRMCoreHDBC.godKiUserBase(race.get(), stY.get()) || l.get() ? supecolr.get() : eyec2.get());
                    this.modelMain.renderHairs(0.0625f, "EYERIGHT");
                } else if (stateRender == DBCAForms.PerfectMax.getStateRender()) {
                    render = true;
                    ResourceLocation bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio2M.png");
                    GL11.glColor3f((float)(h1 + MixinRenderPlayerJBRA.getR1()), (float)(h1 + MixinRenderPlayerJBRA.getG1()), (float)(h1 + MixinRenderPlayerJBRA.getB1()));
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/biotailmax2.png"));
                    this.modelMain.renderHairs(0.0625f, "biotailmax2");
                    int color = JRMCoreH.dnsauCM(dnsau.get());
                    if (dnsau.get().length() < 5) {
                        color = 16719381;
                    }
                    int[] bodyColors = form.getBodyColors(DataUtils.legendary((EntityPlayer)p), DataUtils.divine((EntityPlayer)p));
                    if (form.getID() != DBCAForms.PerfectMax.getID() && bodyColors[0] != -1) {
                        color = bodyColors[0];
                    }
                    RenderPlayerJBRA.glColor3f(color);
                    this.func_110776_a(bdyskn);
                    this.modelMain.renderBody(0.0625f);
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/bioheadM.png"));
                    this.modelMain.renderHairs(0.0625f, "bioheadsemi");
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/biotailmax.png"));
                    this.modelMain.renderHairs(0.0625f, "biotailmax");
                    GL11.glColor3f((float)(h1 + MixinRenderPlayerJBRA.getR1()), (float)(h1 + MixinRenderPlayerJBRA.getG1()), (float)(h1 + MixinRenderPlayerJBRA.getB1()));
                    bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio1M.png");
                    this.func_110776_a(bdyskn);
                    this.modelMain.renderBody(0.0625f);
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/biowingsP.png"));
                    this.modelMain.renderHairs(0.0625f, "biowings");
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/bioeyesbaseS.png"));
                    this.modelMain.renderHairs(0.0625f, "EYEBASE");
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/bioeyeleftS.png"));
                    RenderPlayerJBRA.glColor3f(JRMCoreH.rc_sai(race.get()) || gd.get() || JRMCoreHDBC.godKiUserBase(race.get(), stY.get()) || l.get() ? supecoll.get() : eyec1.get());
                    this.modelMain.renderHairs(0.0625f, "EYELEFT");
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/bioeyerightS.png"));
                    RenderPlayerJBRA.glColor3f(JRMCoreH.rc_sai(race.get()) || gd.get() || JRMCoreHDBC.godKiUserBase(race.get(), stY.get()) || l.get() ? supecolr.get() : eyec2.get());
                    this.modelMain.renderHairs(0.0625f, "EYERIGHT");
                }
            }
            if (!render) {
                GL11.glColor3f((float)(h1 + MixinRenderPlayerJBRA.getR1()), (float)(h1 + MixinRenderPlayerJBRA.getG1()), (float)(h1 + MixinRenderPlayerJBRA.getB1()));
                this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/biotail2.png"));
                this.modelMain.renderHairs(0.0625f, "biotail2");
                if (!DBCAAbilities.isAbsorbing((EntityPlayer)p)) {
                    RenderPlayerJBRA.glColor3f(bodycm);
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/biotail.png"));
                    this.modelMain.renderHairs(0.0625f, "biotail", DBCAAbilities.isAbsorbing((EntityPlayer)p) ? "" : "");
                } else {
                    RenderPlayerJBRA.glColor3f(bodycm);
                    this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/biotail.png"));
                    this.modelMain.renderHairs(0.0625f, "biotail");
                    this.modelMain.instantTransmission = true;
                }
                RenderPlayerJBRA.glColor3f(bodycm);
                this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/biowings.png"));
                this.modelMain.renderHairs(0.0625f, "biowings");
                ResourceLocation bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio3.png");
                this.func_110776_a(bdyskn);
                RenderPlayerJBRA.glColor3f(bodycm);
                this.modelMain.renderBody(0.0625f);
                bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio1.png");
                this.func_110776_a(bdyskn);
                RenderPlayerJBRA.glColor3f(bodyc1);
                this.modelMain.renderBody(0.0625f);
                bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio2.png");
                this.func_110776_a(bdyskn);
                RenderPlayerJBRA.glColor3f(bodyc2);
                this.modelMain.renderBody(0.0625f);
                this.func_110776_a(new ResourceLocation("jinryuudragonbc:gui/allw.png"));
                GL11.glColor3f((float)(h1 + MixinRenderPlayerJBRA.getR1()), (float)(h1 + MixinRenderPlayerJBRA.getG1()), (float)(h1 + MixinRenderPlayerJBRA.getB1()));
                this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/bioeyesbase.png"));
                GL11.glColor3f((float)(1.0f + MixinRenderPlayerJBRA.getR1()), (float)(1.0f + MixinRenderPlayerJBRA.getG1()), (float)(1.0f + MixinRenderPlayerJBRA.getB1()));
                this.modelMain.renderHairs(0.0625f, "EYEBASE");
                this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/bioeyeleft.png"));
                RenderPlayerJBRA.glColor3f(JRMCoreH.rc_sai(race.get()) || gd.get() || JRMCoreHDBC.godKiUserBase(race.get(), stY.get()) || l.get() ? supecoll.get() : eyec1.get());
                this.modelMain.renderHairs(0.0625f, "EYELEFT");
                this.func_110776_a(new ResourceLocation("dbcadditions:textures/body/bio/bioeyeright.png"));
                RenderPlayerJBRA.glColor3f(JRMCoreH.rc_sai(race.get()) || gd.get() || JRMCoreHDBC.godKiUserBase(race.get(), stY.get()) || l.get() ? supecolr.get() : eyec2.get());
                this.modelMain.renderHairs(0.0625f, "EYERIGHT");
            }
            int w = weight.get();
            String[] wnam = new String[]{"wshell", "whandleg"};
            if (w >= 0 && w < wnam.length) {
                String[] wloc = new String[]{"roshiShell", "weightBands"};
                this.func_110776_a(new ResourceLocation("jinryuudragonbc:textures/misc/" + wloc[w] + ".png"));
                GL11.glColor3f((float)(1.0f + r + r2), (float)(1.0f + g + g2), (float)(1.0f + b + b2));
                this.modelMain.renderHairs(0.0625f, wnam[w]);
            }
            for (int i = 0; i < JRMCoreH.plyrs.length; ++i) {
                if (!p.func_70005_c_().equals(JRMCoreH.plyrs[i]) || !JRMCoreH.aliveState(i)) continue;
                this.func_110776_a(new ResourceLocation("jinryuudragonbc:armor/halo.png"));
                GL11.glColor3f((float)(1.0f + MixinRenderPlayerJBRA.getR1()), (float)(1.0f + MixinRenderPlayerJBRA.getG1()), (float)(1.0f + MixinRenderPlayerJBRA.getB1()));
                this.modelMain.renderHalo(0.0625f);
            }
            this.renderTattoos(p, skintype.get());
            GL11.glDepthMask((boolean)true);
            GL11.glEnable((int)2896);
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
        }
    }

    private static float getR1() {
        return r + r2;
    }

    private static float getG1() {
        return g + g2;
    }

    private static float getB1() {
        return b + b2;
    }

    @Inject(method={"renderEquippedItemsJBRA"}, at={@At(value="TAIL")})
    private void renderFur(AbstractClientPlayer p, float par2, CallbackInfo ci) {
        if (!DBCAConfig.CustomForms) {
            return;
        }
        byte formID = DataUtils.getDBCAState((EntityPlayer)p);
        if (formID <= 0) {
            return;
        }
        DBCAForm form = DBCAForms.getForm(formID);
        if (form == null) {
            return;
        }
        int furColor = form.getFurColor(DataUtils.legendary((EntityPlayer)p), DataUtils.divine((EntityPlayer)p));
        if (furColor != -1 && currSkinType != -1) {
            this.func_110776_a(new ResourceLocation("jinryuudragonbc:cc/ss4" + (currSkinType == 0 ? "a" : "b") + ".png"));
            RenderPlayerJBRA.glColor3f(furColor);
            this.modelMain.renderBody(0.0625f);
        }
        currSkinType = -1;
    }

    @Redirect(method={"renderEquippedItemsJBRA"}, at=@At(value="INVOKE", target="LJinRyuu/JBRA/ModelBipedDBC;renderHairs(FLjava/lang/String;)V"))
    private void changeFormHair(ModelBipedDBC model, float par1, String hair) {
        if (!DBCAConfig.CustomForms || !hair.startsWith("A")) {
            this.modelMain.renderHairs(par1, hair);
            return;
        }
        byte formID = DataUtils.getDBCAState(CommonProxy.CurrentPlayerDBC);
        if (formID <= 0) {
            this.modelMain.renderHairs(par1, hair);
            return;
        }
        DBCAForm form = DBCAForms.getForm(formID);
        if (form == null) {
            this.modelMain.renderHairs(par1, hair);
            return;
        }
        if (form.getHairType() != HairType.SS4 && form.getCustomHair().isEmpty()) {
            this.modelMain.renderHairs(par1, hair);
        }
    }

    @Inject(method={"renderEquippedItemsJBRA"}, at={@At(value="INVOKE", target="Lorg/lwjgl/opengl/GL11;glPushMatrix()V", ordinal=0, shift=At.Shift.AFTER)})
    private void changeFormData(AbstractClientPlayer p, float par2, CallbackInfo ci, @Local(name={"hairback"}) LocalIntRef hairback, @Local(name={"race"}) LocalIntRef race, @Local(name={"rg"}) LocalIntRef rg, @Local(name={"st"}) LocalIntRef st, @Local(name={"skintype"}) LocalIntRef skintype, @Local(name={"bodycm"}) LocalIntRef bodyCM, @Local(name={"bodyc1"}) LocalIntRef bodyC1, @Local(name={"bodyc2"}) LocalIntRef bodyC2, @Local(name={"bodyc3"}) LocalIntRef bodyC3, @Local(name={"bodytype"}) LocalIntRef bodytype, @Local(name={"pl"}) LocalIntRef pl, @Local(name={"plyrSpc"}) LocalIntRef plyrSpc, @Local(name={"msk"}) LocalBooleanRef mask) {
        if (!DBCAConfig.CustomForms) {
            return;
        }
        EntityPlayer fusionPartner = DataUtils.getFusionPartner((EntityPlayer)p);
        if (fusionPartner != null && race.get() == 4 && st.get() != 6) {
            for (int pl1 = 0; pl1 < JRMCoreH.plyrs.length; ++pl1) {
                if (!JRMCoreH.plyrs[pl1].equals(fusionPartner.func_70005_c_())) continue;
                String[] s = JRMCoreH.data1[pl1].split(";");
                String partnerDNS = s[1];
                int skintype1 = JRMCoreH.dnsSkinT(partnerDNS);
                int bodytype2 = skintype1 == 0 ? JRMCoreH.dnsBodyC1_0(partnerDNS) : JRMCoreH.dnsBodyT(partnerDNS);
                int bodycm2 = skintype1 == 0 ? 0 : JRMCoreH.dnsBodyCM(partnerDNS);
                int bodyc12 = skintype1 == 0 ? 0 : JRMCoreH.dnsBodyC1(partnerDNS);
                int bodyc22 = skintype1 == 0 ? 0 : JRMCoreH.dnsBodyC2(partnerDNS);
                int bodyc32 = skintype1 == 0 ? 0 : JRMCoreH.dnsBodyC3(partnerDNS);
                boolean greater = false;
                if (bodytype2 > bodytype.get()) {
                    greater = true;
                }
                plyrSpc.set(!greater ? bodytype.get() : bodytype2);
                if (greater) {
                    bodyCM.set(bodycm2);
                    bodyC1.set(bodyC1.get());
                    bodyC2.set(bodyC2.get());
                    bodyC3.set(bodyc32);
                    continue;
                }
                bodyCM.set(bodyCM.get());
                bodyC1.set(bodyc12);
                bodyC2.set(bodyc22);
                bodyC3.set(bodyC3.get());
            }
        }
        RenderPlayerJBRA rp = (RenderPlayerJBRA)((Object)this);
        currSkinType = skintype.get();
        byte formID = DataUtils.getDBCAState((EntityPlayer)p);
        if (formID <= 0) {
            return;
        }
        DBCAForm form = DBCAForms.getForm(formID);
        if (form == null) {
            return;
        }
        int hair = hairback.get();
        int hairColor = form.getHairColor(DataUtils.legendary((EntityPlayer)p), DataUtils.divine((EntityPlayer)p));
        if (hair < 12 && hair != 10) {
            if (!form.getCustomHair().isEmpty() && form.getHairType() == HairType.CUSTOM) {
                this.func_110776_a(new ResourceLocation("jinryuumodscore:gui/normall.png"));
                RenderPlayerJBRA.glColor3f(hairColor);
                this.modelMain.renderHairsV2(0.0625f, form.getCustomHair(), 0.0f, 0, 0, pl.get(), race.get(), rp, p);
            } else if (form.getHairType() == HairType.SS4) {
                this.func_110776_a(new ResourceLocation("jinryuumodscore:gui/normall.png"));
                RenderPlayerJBRA.glColor3f(hairColor);
                this.modelMain.renderHairsV2(0.0625f, "373852546750347428545480193462285654801934283647478050340147507467501848505072675018255250726750183760656580501822475071675018255050716750189730327158501802475071675018973225673850189765616160501820414547655019545654216550195754542165501920475027655019943669346576193161503065231900475030655019406534276538199465393460501997654138655019976345453950189760494941501897615252415018976354563850189763494736501897614949395018976152523950189763525234501897584749395018976150493850189760545234501897585250415018885445474550189754475041501897545250435018885454523950185143607861501897415874585018514369196150185147768078391865525680565018974356806150188843567861501868396374615018975056805650189750568056501885582374615018975823726150187149568054501877495680565018774950785650189163236961501820", 0.0f, 0, 0, pl.get(), race.get(), rp, p);
            }
        }
        if ((hair == 12 || JRMCoreH.isRaceMajin(race.get())) && form.getHairType() == HairType.SS3) {
            this.func_110776_a(new ResourceLocation("jinryuumodscore:gui/normall.png"));
            RenderPlayerJBRA.glColor3f(hairColor);
            this.modelMain.renderHairs(0.0625f, "" + JRMCoreH.HairsT[6] + JRMCoreH.Hairs[0]);
        }
        int[] bodyColors = form.getBodyColors(DataUtils.legendary((EntityPlayer)p), DataUtils.divine((EntityPlayer)p));
        int bodycm = bodyColors[0];
        int bodyc1 = bodyColors[1];
        int bodyc2 = bodyColors[2];
        int bodyc3 = bodyColors[3];
        if (form.getBodyType() != -1) {
            plyrSpc.set(form.getBodyType());
        }
        if (bodycm != -1) {
            bodyCM.set(bodycm);
        }
        if (bodyc1 != -1) {
            bodyC1.set(bodyc1);
        }
        if (bodyc2 != -1) {
            bodyC2.set(bodyc2);
        }
        if (bodyc3 != -1) {
            bodyC3.set(bodyc3);
        }
        if (JRMCoreH.isRaceArcosian(race.get())) {
            st.set(form.getStateRender());
        }
    }

    @Inject(method={"preRenderCallback(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V"}, at={@At(value="INVOKE", target="LJinRyuu/JRMCore/JRMCoreHDBC;DBCsizeBasedOnRace(IIZ)F", shift=At.Shift.BEFORE)}, remap=true)
    public void setDBCPlayer(AbstractClientPlayer p, float p_77041_2_, CallbackInfo ci) {
        CommonProxy.CurrentPlayerDBC = p;
    }

    @Inject(method={"renderFirstPersonArm"}, at={@At(value="INVOKE", target="LJinRyuu/JRMCore/JRMCoreH;DBC()Z", ordinal=0, shift=At.Shift.AFTER)})
    private void changeFormArmData(EntityPlayer p, CallbackInfo ci, @Local(name={"race"}) LocalIntRef race, @Local(name={"State"}) LocalIntRef st, @Local(name={"bodycm"}) LocalIntRef bodyCM, @Local(name={"bodyc1"}) LocalIntRef bodyC1, @Local(name={"bodyc2"}) LocalIntRef bodyC2, @Local(name={"bodyc3"}) LocalIntRef bodyC3, @Local(name={"bodytype"}) LocalIntRef bodytype, @Local(name={"plyrSpc"}) LocalIntRef plyrSpc) {
        int[] bodyColors;
        byte formID;
        if (!DBCAConfig.CustomForms) {
            return;
        }
        EntityPlayer fusionPartner = DataUtils.getFusionPartner(p);
        if (fusionPartner != null) {
            String partnerDNS;
            String[] s;
            int pl1;
            if (race.get() == 4 && st.get() != 6) {
                for (pl1 = 0; pl1 < JRMCoreH.plyrs.length; ++pl1) {
                    if (!JRMCoreH.plyrs[pl1].equals(fusionPartner.func_70005_c_())) continue;
                    s = JRMCoreH.data1[pl1].split(";");
                    partnerDNS = s[1];
                    int skintype1 = JRMCoreH.dnsSkinT(partnerDNS);
                    int bodytype2 = skintype1 == 0 ? JRMCoreH.dnsBodyC1_0(partnerDNS) : JRMCoreH.dnsBodyT(partnerDNS);
                    int bodycm2 = skintype1 == 0 ? 0 : JRMCoreH.dnsBodyCM(partnerDNS);
                    int bodyc12 = skintype1 == 0 ? 0 : JRMCoreH.dnsBodyC1(partnerDNS);
                    int bodyc22 = skintype1 == 0 ? 0 : JRMCoreH.dnsBodyC2(partnerDNS);
                    int bodyc32 = skintype1 == 0 ? 0 : JRMCoreH.dnsBodyC3(partnerDNS);
                    boolean greater = false;
                    if (bodytype2 > bodytype.get()) {
                        greater = true;
                    }
                    plyrSpc.set(!greater ? bodytype.get() : bodytype2);
                    if (greater) {
                        bodyCM.set(bodycm2);
                        bodyC1.set(bodyC1.get());
                        bodyC2.set(bodyC2.get());
                        bodyC3.set(bodyc32);
                        continue;
                    }
                    bodyCM.set(bodyCM.get());
                    bodyC1.set(bodyc12);
                    bodyC2.set(bodyc22);
                    bodyC3.set(bodyC3.get());
                }
            } else if (JRMCoreH.isRaceSaiyan(race.get()) || JRMCoreH.isRaceHuman(race.get())) {
                for (pl1 = 0; pl1 < JRMCoreH.plyrs.length; ++pl1) {
                    if (!JRMCoreH.plyrs[pl1].equals(fusionPartner.func_70005_c_())) continue;
                    s = JRMCoreH.data1[pl1].split(";");
                    partnerDNS = s[1];
                    int skintype1 = JRMCoreH.dnsSkinT(partnerDNS);
                    int bodycm2 = skintype1 == 0 ? 0 : JRMCoreH.dnsBodyCM(partnerDNS);
                    bodyCM.set(DataUtils.mergeColors(bodycm2, bodyCM.get()));
                }
            }
        }
        if ((formID = DataUtils.getDBCAState(p)) <= 0) {
            return;
        }
        DBCAForm form = DBCAForms.getForm(formID);
        if (form == null) {
            return;
        }
        if (form.getBodyType() != -1) {
            plyrSpc.set(form.getBodyType());
        }
        if ((bodyColors = form.getBodyColors(DataUtils.legendary(p), DataUtils.divine(p)))[0] != -1) {
            bodyCM.set(bodyColors[0]);
        }
        if (bodyColors[1] != -1) {
            bodyC1.set(bodyColors[1]);
        }
        if (bodyColors[2] != -1) {
            bodyC2.set(bodyColors[2]);
        }
        if (bodyColors[3] != -1) {
            bodyC3.set(bodyColors[3]);
        }
        if (JRMCoreH.isRaceArcosian(race.get())) {
            st.set(form.getStateRender());
        }
    }

    @Inject(method={"renderFirstPersonArm"}, at={@At(value="INVOKE", target="LJinRyuu/JRMCore/JRMCoreH;DBC()Z", ordinal=0, shift=At.Shift.AFTER)}, cancellable=true, remap=true)
    private void renderCustomRaceArm(EntityPlayer p, CallbackInfo ci, @Local(name={"bodycm"}) LocalIntRef bodyCM, @Local(name={"bodyc1"}) LocalIntRef bodyC1, @Local(name={"bodyc2"}) LocalIntRef bodyC2, @Local(name={"bodyc3"}) LocalIntRef bodyC3, @Local(name={"skintype"}) LocalIntRef skintype, @Local(name={"dnsau"}) LocalRef<String> dnsau) {
        if (!DBCAConfig.CustomRaces || skintype.get() == 0) {
            return;
        }
        boolean isInMystic = JRMCoreH.StusEfctsClient(13, p);
        boolean l = JRMCoreH.StusEfctsClient(19, p);
        boolean gd = JRMCoreH.StusEfctsClient(20, p);
        DBCARace customRace = DBCARaces.getRace(DataUtils.getDBCARace(p));
        if (customRace == null) {
            return;
        }
        byte formID = DataUtils.getDBCAState(p);
        DBCAForm form = DBCAForms.getForm(formID);
        if (customRace.ID == DBCARaces.BIO_ANDROID.ID) {
            Item item;
            ItemStack itemstack;
            String[] s;
            int pwr;
            int id;
            boolean instantTransmission;
            ci.cancel();
            int[] an = new int[]{1, 0, 2, 0, 0, 3, 0, 1, 1};
            EntityClientPlayerMP acp = JBRAClient.mc.field_71439_g;
            boolean bl = instantTransmission = ExtendedPlayer.get((EntityPlayer)acp).getBlocking() == 2;
            int n = ExtendedPlayer.get((EntityPlayer)acp).getBlocking() != 0 ? (instantTransmission ? 6 : 0) : (id = ExtendedPlayer.get((EntityPlayer)acp).getAnimKiShoot() != 0 ? an[ExtendedPlayer.get((EntityPlayer)acp).getAnimKiShoot() - 1] + 2 : -1);
            if (JRMCoreH.JYC() && p.field_71071_by.func_146028_b(JRMCoreHJYC.JYCgetItemWatch())) {
                GL11.glPushMatrix();
                if (id > -1) {
                    MixinRenderPlayerJBRA.func_aam1(id, true, true);
                }
                GL11.glRotatef((float)6.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.29f, (float)0.15f, (float)0.0f);
                MixinRenderPlayerJBRA.ow1(true);
                GL11.glPopMatrix();
            }
            if (JRMCoreH.DBC() && (pwr = Integer.parseInt((s = JRMCoreH.data(acp.func_70005_c_(), 1, "0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0").split(";"))[2])) == 1) {
                String[] PlyrSkills = JRMCoreH.PlyrSkills((EntityPlayer)acp);
                int sklkf = JRMCoreH.SklLvl(12, PlyrSkills);
                int skf = JRMCoreH.SklLvl(15, PlyrSkills);
                String ss = s[17];
                boolean v = JRMCoreH.DBC() && !ss.equals("-1");
                GL11.glPushMatrix();
                if (v && (sklkf > 0 || skf > 0)) {
                    if (id > -1) {
                        MixinRenderPlayerJBRA.func_aam1(id, true, true);
                    }
                    GL11.glRotatef((float)6.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                    GL11.glTranslatef((float)-0.29f, (float)0.15f, (float)0.0f);
                    RenderPlayerJBRA.kss((Entity)acp, false, Integer.parseInt(ss), sklkf, skf);
                }
                GL11.glPopMatrix();
            }
            ModelBipedDBC modelMain = (ModelBipedDBC)this.field_77045_g;
            if (form != null && form.getID() == DBCAForms.PerfectMax.getID()) {
                ResourceLocation bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio2M.png");
                int color = JRMCoreH.dnsauCM(dnsau.get());
                if (dnsau.get().length() < 5) {
                    color = 16719381;
                }
                RenderPlayerJBRA.glColor3f(color);
                JRMCoreClient.mc.func_110434_K().func_110577_a(bdyskn);
                if (id == -1) {
                    modelMain.RA.func_78785_a(0.0625f);
                } else {
                    MixinRenderPlayerJBRA.func_aam1(modelMain.RA, modelMain.LA, id, true);
                }
                GL11.glColor3f((float)(1.0f + MixinRenderPlayerJBRA.getR1()), (float)(1.0f + MixinRenderPlayerJBRA.getG1()), (float)(1.0f + MixinRenderPlayerJBRA.getB1()));
                bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio1M.png");
                JRMCoreClient.mc.func_110434_K().func_110577_a(bdyskn);
                if (id == -1) {
                    modelMain.RA.func_78785_a(0.0625f);
                } else {
                    MixinRenderPlayerJBRA.func_aam1(modelMain.RA, modelMain.LA, id, true);
                }
            } else if (form != null && form.getID() == DBCAForms.UncontrolledMax.getID()) {
                int color = JRMCoreH.dnsauCM(dnsau.get());
                if (dnsau.get().length() <= 6) {
                    color = 16719381;
                }
                RenderPlayerJBRA.glColor3f(color);
                ResourceLocation bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio2UM.png");
                JRMCoreClient.mc.func_110434_K().func_110577_a(bdyskn);
                if (id == -1) {
                    modelMain.RA.func_78785_a(0.0625f);
                } else {
                    MixinRenderPlayerJBRA.func_aam1(modelMain.RA, modelMain.LA, id, true);
                }
                GL11.glColor3f((float)(1.0f + MixinRenderPlayerJBRA.getR1()), (float)(1.0f + MixinRenderPlayerJBRA.getG1()), (float)(1.0f + MixinRenderPlayerJBRA.getB1()));
                bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio1UM.png");
                JRMCoreClient.mc.func_110434_K().func_110577_a(bdyskn);
                if (id == -1) {
                    modelMain.RA.func_78785_a(0.0625f);
                } else {
                    MixinRenderPlayerJBRA.func_aam1(modelMain.RA, modelMain.LA, id, true);
                }
            } else if (form != null && (form.getID() == DBCAForms.Perfect.getID() || form.getID() == DBCAForms.SuperPerfect.getID() || form.getID() == DBCAForms.PerfectGod.getID()) || l || gd || isInMystic) {
                ResourceLocation bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio2P.png");
                JRMCoreClient.mc.func_110434_K().func_110577_a(bdyskn);
                RenderPlayerJBRA.glColor3f(bodyCM.get());
                if (id == -1) {
                    modelMain.RA.func_78785_a(0.0625f);
                } else {
                    MixinRenderPlayerJBRA.func_aam1(modelMain.RA, modelMain.LA, id, true);
                }
                bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio1P.png");
                GL11.glColor3f((float)(1.0f + MixinRenderPlayerJBRA.getR1()), (float)(1.0f + MixinRenderPlayerJBRA.getG1()), (float)(1.0f + MixinRenderPlayerJBRA.getB1()));
                JRMCoreClient.mc.func_110434_K().func_110577_a(bdyskn);
                if (id == -1) {
                    modelMain.RA.func_78785_a(0.0625f);
                } else {
                    MixinRenderPlayerJBRA.func_aam1(modelMain.RA, modelMain.LA, id, true);
                }
            } else if (form != null && form.getID() == DBCAForms.SemiPerfect.getID()) {
                ResourceLocation bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio3S.png");
                JRMCoreClient.mc.func_110434_K().func_110577_a(bdyskn);
                RenderPlayerJBRA.glColor3f(bodyCM.get());
                if (id == -1) {
                    modelMain.RA.func_78785_a(0.0625f);
                } else {
                    MixinRenderPlayerJBRA.func_aam1(modelMain.RA, modelMain.LA, id, true);
                }
                bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio1S.png");
                JRMCoreClient.mc.func_110434_K().func_110577_a(bdyskn);
                RenderPlayerJBRA.glColor3f(bodyC1.get());
                if (id == -1) {
                    modelMain.RA.func_78785_a(0.0625f);
                } else {
                    MixinRenderPlayerJBRA.func_aam1(modelMain.RA, modelMain.LA, id, true);
                }
                bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio2S.png");
                JRMCoreClient.mc.func_110434_K().func_110577_a(bdyskn);
                RenderPlayerJBRA.glColor3f(bodyC2.get());
                if (id == -1) {
                    modelMain.RA.func_78785_a(0.0625f);
                } else {
                    MixinRenderPlayerJBRA.func_aam1(modelMain.RA, modelMain.LA, id, true);
                }
                bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio4S.png");
                JRMCoreClient.mc.func_110434_K().func_110577_a(bdyskn);
                GL11.glColor3f((float)(1.0f + MixinRenderPlayerJBRA.getR1()), (float)(1.0f + MixinRenderPlayerJBRA.getG1()), (float)(1.0f + MixinRenderPlayerJBRA.getB1()));
                if (id == -1) {
                    modelMain.RA.func_78785_a(0.0625f);
                } else {
                    MixinRenderPlayerJBRA.func_aam1(modelMain.RA, modelMain.LA, id, true);
                }
            } else {
                ResourceLocation bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio1.png");
                JRMCoreClient.mc.func_110434_K().func_110577_a(bdyskn);
                RenderPlayerJBRA.glColor3f(bodyC1.get());
                if (id == -1) {
                    modelMain.RA.func_78785_a(0.0625f);
                } else {
                    MixinRenderPlayerJBRA.func_aam1(modelMain.RA, modelMain.LA, id, true);
                }
                bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio2.png");
                JRMCoreClient.mc.func_110434_K().func_110577_a(bdyskn);
                RenderPlayerJBRA.glColor3f(bodyC2.get());
                if (id == -1) {
                    modelMain.RA.func_78785_a(0.0625f);
                } else {
                    MixinRenderPlayerJBRA.func_aam1(modelMain.RA, modelMain.LA, id, true);
                }
                bdyskn = new ResourceLocation("dbcadditions:textures/body/bio/bio3.png");
                JRMCoreClient.mc.func_110434_K().func_110577_a(bdyskn);
                RenderPlayerJBRA.glColor3f(bodyCM.get());
                if (id == -1) {
                    modelMain.RA.func_78785_a(0.0625f);
                } else {
                    MixinRenderPlayerJBRA.func_aam1(modelMain.RA, modelMain.LA, id, true);
                }
            }
            if (JGConfigClientSettings.CLIENT_DA19) {
                GL11.glPushMatrix();
                GL11.glEnable((int)3042);
                GL11.glDisable((int)2896);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glAlphaFunc((int)516, (float)0.003921569f);
                GL11.glDepthMask((boolean)false);
                int maxBody = JRMCoreH.stat((Entity)p, 2, JRMCoreH.Pwrtyp, 2, JRMCoreH.PlyrAttrbts[2], JRMCoreH.Race, JRMCoreH.Class, 0.0f);
                int curBody = Integer.parseInt(JRMCoreH.data(p.func_70005_c_(), 8, "200"));
                float one = (float)maxBody / 100.0f;
                int perc = (int)((float)curBody / one);
                if (perc < 70) {
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    JRMCoreClient.mc.func_110434_K().func_110577_a(new ResourceLocation("jinryuumodscore:cc/bruises1.png"));
                    if (id != -1) {
                        MixinRenderPlayerJBRA.func_aam3(modelMain.RA, modelMain.LA, id, true);
                    }
                }
                if (perc < 55) {
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    JRMCoreClient.mc.func_110434_K().func_110577_a(new ResourceLocation("jinryuumodscore:cc/bruises2.png"));
                    if (id != -1) {
                        MixinRenderPlayerJBRA.func_aam3(modelMain.RA, modelMain.LA, id, true);
                    }
                }
                if (perc < 35) {
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    JRMCoreClient.mc.func_110434_K().func_110577_a(new ResourceLocation("jinryuumodscore:cc/bruises3.png"));
                    if (id != -1) {
                        MixinRenderPlayerJBRA.func_aam3(modelMain.RA, modelMain.LA, id, true);
                    }
                }
                if (perc < 20) {
                    GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                    JRMCoreClient.mc.func_110434_K().func_110577_a(new ResourceLocation("jinryuumodscore:cc/bruises4.png"));
                    if (id != -1) {
                        MixinRenderPlayerJBRA.func_aam3(modelMain.RA, modelMain.LA, id, true);
                    }
                }
                GL11.glDepthMask((boolean)true);
                GL11.glEnable((int)2896);
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
            }
            if ((itemstack = p.field_71071_by.func_70440_f(3)) != null && (item = itemstack.func_77973_b()) instanceof ItemArmor) {
                ItemArmor itemarmor = (ItemArmor)item;
                GL11.glPushMatrix();
                String dbcarmor = itemarmor.getArmorTexture(itemstack, (Entity)p, 2, null);
                ResourceLocation mcarmor = RenderBiped.getArmorResource((Entity)p, (ItemStack)itemstack, (int)1, null);
                if (dbcarmor != null) {
                    dbcarmor = dbcarmor.replace("jbra", "").replace("_dam", "");
                }
                ResourceLocation armor = dbcarmor != null ? new ResourceLocation(dbcarmor) : mcarmor;
                JRMCoreClient.mc.func_110434_K().func_110577_a(armor);
                if (id == 0 || id == 3 || id == 5) {
                    if (id == 0) {
                        if (JGConfigClientSettings.CLIENT_DA18) {
                            GL11.glPushMatrix();
                            MixinRenderPlayerJBRA.func_aam1(id, false, true);
                            GL11.glColor3f((float)(1.0f + r + r2), (float)(1.0f + g + g2), (float)(1.0f + b + b2));
                            GL11.glScalef((float)1.0001f, (float)1.0001f, (float)1.0001f);
                            if (dbcarmor != null) {
                                modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)p);
                                modelMain.field_78089_u = 64;
                                modelMain.field_78090_t = 128;
                                modelMain.LA.func_78785_a(0.0625f);
                            } else {
                                modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)p);
                                modelMain.LA.func_78785_a(0.0625f);
                            }
                            GL11.glPopMatrix();
                        }
                    } else {
                        GL11.glPushMatrix();
                        MixinRenderPlayerJBRA.func_aam1(id, false, true);
                        GL11.glColor3f((float)(1.0f + r + r2), (float)(1.0f + g + g2), (float)(1.0f + b + b2));
                        GL11.glScalef((float)1.0001f, (float)1.0001f, (float)1.0001f);
                        if (dbcarmor != null) {
                            modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)p);
                            modelMain.field_78089_u = 64;
                            modelMain.field_78090_t = 128;
                            modelMain.LA.func_78785_a(0.0625f);
                        } else {
                            modelMain.func_78087_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, (Entity)p);
                            modelMain.LA.func_78785_a(0.0625f);
                        }
                        GL11.glPopMatrix();
                    }
                }
                GL11.glPopMatrix();
            }
            GL11.glPopMatrix();
        }
    }

    private static void func_aam3(ModelRenderer ra, ModelRenderer la, int id, boolean fp) {
        if (id == 0) {
            if (JGConfigClientSettings.CLIENT_DA18) {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)-0.2f, (float)-0.4f, (float)-0.8f);
                GL11.glRotatef((float)50.0f, (float)1.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                la.func_78785_a(0.0625f);
                GL11.glPopMatrix();
            }
        } else if (id == 3) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)0.1f, (float)-0.2f, (float)-0.5f);
            GL11.glTranslatef((float)-0.2f, (float)0.0f, (float)-0.1f);
            GL11.glRotatef((float)10.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)-1.0f);
            GL11.glRotatef((float)115.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            la.func_78785_a(0.0625f);
            GL11.glPopMatrix();
        } else if (id == 5) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)-0.2f, (float)-0.4f, (float)-0.8f);
            GL11.glTranslatef((float)-0.4f, (float)0.1f, (float)-0.1f);
            GL11.glRotatef((float)42.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)10.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)115.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.6f, (float)0.08f, (float)0.3f);
            la.func_78785_a(0.0625f);
            GL11.glPopMatrix();
        }
    }

    private static void ow1(boolean b) {
        GL11.glPushMatrix();
        float scale = 1.0f;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        float f1 = 0.0020714286f;
        GL11.glScalef((float)f1, (float)f1, (float)f1);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (b) {
            JRMCoreClient.mc.func_110434_K().func_110577_a(new ResourceLocation("jinryuujyearsc:watch/hw0.png"));
            GL11.glNormal3f((float)0.0f, (float)0.0f, (float)-1.0f);
        } else {
            JRMCoreClient.mc.field_71446_o.func_110577_a(new ResourceLocation("jinryuujyearsc:watch/hw0.png"));
        }
        GL11.glTranslatef((float)-33.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)32.0f, (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)0.0f, (float)150.0f, (float)0.0f);
        JRMCoreHC.dtm(-96.0f, 0.0f, 0, 0, 128.0f, 128.0f, -64.0f);
        GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        JRMCoreHC.dtm(-64.0f, 0.0f, 128, 0, 128.0f, 128.0f, -32.0f);
        GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        JRMCoreHC.dtm(-32.0f, 0.0f, 128, 0, 128.0f, 128.0f, -64.0f);
        GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        JRMCoreHC.dtm(-64.0f, 0.0f, 128, 0, 128.0f, 128.0f, -96.0f);
        GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-104.0f, (float)-70.0f, (float)-65.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        scale = 2.0f;
        float scaley = 4.0f;
        GL11.glScalef((float)scale, (float)scaley, (float)scale);
        int s = (int)(JRMCoreClient.mc.field_71439_g.field_70170_p.func_72820_D() % 24000L / 1000L) + 6;
        int w = s > 24 ? s - 24 : s;
        w = w == 24 ? 0 : w;
        int m = (int)(JRMCoreClient.mc.field_71439_g.field_70170_p.func_72820_D() % 24000L - (long)((int)(JRMCoreClient.mc.field_71439_g.field_70170_p.func_72820_D() % 24000L / 1000L) * 1000));
        float mi = (float)m / 16.67f;
        int min = (int)mi;
        String var34 = (w < 10 ? "0" + w : Integer.valueOf(w)) + ":" + (min < 10 ? "0" + min : Integer.valueOf(min));
        FontRenderer fontRenderer = JRMCoreClient.mc.field_71466_p;
        String n = "" + var34;
        fontRenderer.func_78276_b(n, (int)(-96.0f / scale), -((int)(150.0f / scaley)), 0);
        GL11.glPopMatrix();
    }

    private static void func_aam1(int id, boolean s, boolean fp) {
        if (s) {
            if (id == 0 || id == 6) {
                if (id == 0 ? JGConfigClientSettings.CLIENT_DA18 : JGConfigClientSettings.instantTransmissionFirstPerson) {
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)771);
                    GL11.glAlphaFunc((int)516, (float)0.003921569f);
                    GL11.glDepthMask((boolean)false);
                    GL11.glTranslatef((float)-0.5f, (float)-0.1f, (float)-0.1f);
                    GL11.glRotatef((float)40.0f, (float)0.0f, (float)0.0f, (float)-1.0f);
                    GL11.glRotatef((float)80.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
                    GL11.glRotatef((float)(id == 0 ? -20 : 30), (float)0.0f, (float)0.0f, (float)1.0f);
                }
            } else if (id == 1) {
                GL11.glTranslatef((float)-0.2f, (float)-0.4f, (float)-0.8f);
                GL11.glRotatef((float)50.0f, (float)1.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            } else if (id == 2 || id == 3) {
                GL11.glTranslatef((float)-0.2f, (float)0.0f, (float)-0.1f);
                GL11.glRotatef((float)10.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)-1.0f);
            } else if (id == 4 || id == 5) {
                GL11.glTranslatef((float)-0.2f, (float)0.4f, (float)-0.1f);
                GL11.glRotatef((float)10.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)-1.0f);
                GL11.glRotatef((float)40.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
        } else if (id == 0) {
            if (JGConfigClientSettings.CLIENT_DA18) {
                GL11.glTranslatef((float)-0.2f, (float)-0.4f, (float)-0.8f);
                GL11.glRotatef((float)50.0f, (float)1.0f, (float)0.0f, (float)1.0f);
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
        } else if (id == 3) {
            GL11.glTranslatef((float)0.1f, (float)-0.2f, (float)-0.5f);
            GL11.glTranslatef((float)-0.2f, (float)0.0f, (float)-0.1f);
            GL11.glRotatef((float)10.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)-1.0f);
            GL11.glRotatef((float)115.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        } else if (id == 5) {
            GL11.glTranslatef((float)-0.2f, (float)-0.4f, (float)-0.8f);
            GL11.glTranslatef((float)-0.4f, (float)0.1f, (float)-0.1f);
            GL11.glRotatef((float)42.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)10.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)115.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.6f, (float)0.08f, (float)0.3f);
        }
    }

    private static void func_aam1(ModelRenderer ra, ModelRenderer la, int id, boolean fp) {
        if (id == 0 || id == 6) {
            if (id == 0 ? JGConfigClientSettings.CLIENT_DA18 : JGConfigClientSettings.instantTransmissionFirstPerson) {
                GL11.glPushMatrix();
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                GL11.glAlphaFunc((int)516, (float)0.003921569f);
                GL11.glDepthMask((boolean)false);
                GL11.glTranslatef((float)-0.5f, (float)-0.1f, (float)-0.1f);
                GL11.glRotatef((float)40.0f, (float)0.0f, (float)0.0f, (float)-1.0f);
                GL11.glRotatef((float)80.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
                GL11.glRotatef((float)(id == 0 ? -20 : 30), (float)0.0f, (float)0.0f, (float)1.0f);
            }
            ra.func_78785_a(0.0625f);
            if (id == 0 ? JGConfigClientSettings.CLIENT_DA18 : JGConfigClientSettings.instantTransmissionFirstPerson) {
                GL11.glPopMatrix();
            }
        } else if (id == 2 || id == 3) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)-0.2f, (float)0.0f, (float)-0.1f);
            GL11.glRotatef((float)10.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)-1.0f);
            ra.func_78785_a(0.0625f);
            GL11.glPopMatrix();
        } else if (id == 4 || id == 5) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)-0.2f, (float)0.4f, (float)-0.1f);
            GL11.glRotatef((float)10.0f, (float)-1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)20.0f, (float)0.0f, (float)0.0f, (float)-1.0f);
            GL11.glRotatef((float)40.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            ra.func_78785_a(0.0625f);
            GL11.glPopMatrix();
        }
        MixinRenderPlayerJBRA.func_aam3(ra, la, id, fp);
    }
}

