/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.FamilyC;

import JinRyuu.FamilyC.EntityNPC;
import JinRyuu.FamilyC.ModelRendererJBRA;
import JinRyuu.FamilyC.RenderJFC;
import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ModelBipedJFC
extends ModelBiped {
    private static final int hTOP = 4;
    private static final int hRIGHT = 1;
    private static final int hLeft = 2;
    public ModelRenderer field_78116_c;
    public ModelRenderer field_78114_d;
    public ModelRenderer field_78115_e;
    public ModelRenderer field_78112_f;
    public ModelRenderer field_78113_g;
    public ModelRenderer field_78123_h;
    public ModelRenderer field_78124_i;
    public ModelRenderer field_78121_j;
    public ModelRenderer field_78122_k;
    public ModelRenderer bipedHeadAll;
    public ModelRenderer bipedHeadg;
    public ModelRenderer bipedHeadt;
    public ModelRenderer bipedHeadsg;
    public ModelRenderer bipedHeadssg;
    public ModelRenderer bipedHeadst;
    public ModelRenderer bipedHeadsst;
    public ModelRenderer bipedHeadv;
    public ModelRenderer bipedHeadsv;
    public ModelRenderer bipedHeadssv;
    public ModelRenderer bipedHeadgh;
    public ModelRenderer bipedHeadsgh;
    public ModelRenderer bipedHeadssgh;
    public ModelRenderer bipedHeadnull;
    public ModelRenderer bipedHeadg2;
    public ModelRenderer bipedHeadght;
    public ModelRenderer bipedHeadgt;
    public ModelRenderer bipedHeadgtt;
    public ModelRenderer bipedHeadrad;
    public ModelRenderer bipedHeadradl2;
    public ModelRenderer bipedHeadradl;
    public ModelRenderer bipedHeadc7;
    public ModelRenderer bipedHeadc8;
    public ModelRenderer bipedHeadssj3;
    public ModelRenderer bipedHeadssj3l;
    public ModelRenderer bipedHeadssj3t;
    public ModelRenderer bipedHeadssj3l2;
    public ModelRenderer goku1;
    public ModelRenderer goku2;
    public ModelRenderer goku3;
    public ModelRenderer goku4;
    public ModelRenderer goku5;
    public ModelRenderer goku6;
    public ModelRenderer goku7;
    public ModelRenderer goku8;
    public ModelRenderer goku9;
    public ModelRenderer goku10;
    public ModelRenderer goku11;
    public ModelRenderer goku12;
    public ModelRenderer goku13;
    public ModelRenderer goku14;
    public ModelRenderer goku15;
    public ModelRenderer goku16;
    public ModelRenderer sgoku1;
    public ModelRenderer sgoku2;
    public ModelRenderer sgoku3;
    public ModelRenderer sgoku4;
    public ModelRenderer sgoku5;
    public ModelRenderer sgoku6;
    public ModelRenderer sgoku7;
    public ModelRenderer sgoku8;
    public ModelRenderer sgoku9;
    public ModelRenderer sgoku10;
    public ModelRenderer sgoku11;
    public ModelRenderer sgoku12;
    public ModelRenderer sgoku13;
    public ModelRenderer sgoku14;
    public ModelRenderer sgoku15;
    public ModelRenderer sgoku16;
    public ModelRenderer sgoku17;
    public ModelRenderer sgoku18;
    public ModelRenderer sgoku19;
    public ModelRenderer sgoku20;
    public ModelRenderer sgoku21;
    public ModelRenderer sgoku22;
    public ModelRenderer sgoku23;
    public ModelRenderer sgoku24;
    public ModelRenderer sgoku25;
    public ModelRenderer sgoku26;
    public ModelRenderer ssgoku1;
    public ModelRenderer ssgoku2;
    public ModelRenderer ssgoku3;
    public ModelRenderer ssgoku4;
    public ModelRenderer ssgoku5;
    public ModelRenderer ssgoku6;
    public ModelRenderer ssgoku7;
    public ModelRenderer ssgoku8;
    public ModelRenderer ssgoku9;
    public ModelRenderer ssgoku10;
    public ModelRenderer ssgoku11;
    public ModelRenderer ssgoku12;
    public ModelRenderer ssgoku13;
    public ModelRenderer ssgoku14;
    public ModelRenderer ssgoku15;
    public ModelRenderer ssgoku16;
    public ModelRenderer ssgoku17;
    public ModelRenderer ssgoku18;
    public ModelRenderer ssgoku19;
    public ModelRenderer ssgoku20;
    public ModelRenderer ssgoku21;
    public ModelRenderer ssgoku22;
    public ModelRenderer ssgoku23;
    public ModelRenderer ssgoku24;
    public ModelRenderer ssgoku25;
    public ModelRenderer ssgoku26;
    public ModelRenderer trunk1;
    public ModelRenderer trunk2;
    public ModelRenderer trunk3;
    public ModelRenderer trunk4;
    public ModelRenderer trunk5;
    public ModelRenderer trunk6;
    public ModelRenderer trunk7;
    public ModelRenderer trunk8;
    public ModelRenderer trunk9;
    public ModelRenderer strunk1;
    public ModelRenderer strunk2;
    public ModelRenderer strunk3;
    public ModelRenderer strunk4;
    public ModelRenderer strunk5;
    public ModelRenderer strunk6;
    public ModelRenderer strunk7;
    public ModelRenderer strunk8;
    public ModelRenderer strunk9;
    public ModelRenderer strunk10;
    public ModelRenderer strunk11;
    public ModelRenderer strunk12;
    public ModelRenderer strunk13;
    public ModelRenderer strunk14;
    public ModelRenderer strunk15;
    public ModelRenderer strunk16;
    public ModelRenderer strunk17;
    public ModelRenderer sstrunk1;
    public ModelRenderer sstrunk2;
    public ModelRenderer sstrunk3;
    public ModelRenderer sstrunk4;
    public ModelRenderer sstrunk5;
    public ModelRenderer sstrunk6;
    public ModelRenderer sstrunk7;
    public ModelRenderer sstrunk8;
    public ModelRenderer sstrunk9;
    public ModelRenderer sstrunk10;
    public ModelRenderer sstrunk11;
    public ModelRenderer sstrunk12;
    public ModelRenderer sstrunk13;
    public ModelRenderer sstrunk14;
    public ModelRenderer sstrunk15;
    public ModelRenderer sstrunk16;
    public ModelRenderer sstrunk17;
    public ModelRenderer vegeta1;
    public ModelRenderer vegeta2;
    public ModelRenderer vegeta3;
    public ModelRenderer vegeta4;
    public ModelRenderer vegeta5;
    public ModelRenderer vegeta6;
    public ModelRenderer vegeta7;
    public ModelRenderer vegeta8;
    public ModelRenderer vegeta9;
    public ModelRenderer vegeta10;
    public ModelRenderer vegeta11;
    public ModelRenderer vegeta12;
    public ModelRenderer vegeta13;
    public ModelRenderer vegeta14;
    public ModelRenderer vegeta15;
    public ModelRenderer vegeta16;
    public ModelRenderer vegeta17;
    public ModelRenderer vegeta18;
    public ModelRenderer vegeta19;
    public ModelRenderer vegeta20;
    public ModelRenderer vegeta21;
    public ModelRenderer vegeta22;
    public ModelRenderer svegeta1;
    public ModelRenderer svegeta2;
    public ModelRenderer svegeta3;
    public ModelRenderer svegeta4;
    public ModelRenderer svegeta5;
    public ModelRenderer svegeta6;
    public ModelRenderer svegeta7;
    public ModelRenderer svegeta8;
    public ModelRenderer svegeta9;
    public ModelRenderer svegeta10;
    public ModelRenderer svegeta11;
    public ModelRenderer svegeta12;
    public ModelRenderer svegeta13;
    public ModelRenderer svegeta14;
    public ModelRenderer svegeta15;
    public ModelRenderer svegeta16;
    public ModelRenderer svegeta17;
    public ModelRenderer svegeta18;
    public ModelRenderer svegeta19;
    public ModelRenderer svegeta20;
    public ModelRenderer svegeta21;
    public ModelRenderer svegeta22;
    public ModelRenderer ssvegeta1;
    public ModelRenderer ssvegeta2;
    public ModelRenderer ssvegeta3;
    public ModelRenderer ssvegeta4;
    public ModelRenderer ssvegeta5;
    public ModelRenderer ssvegeta6;
    public ModelRenderer ssvegeta7;
    public ModelRenderer ssvegeta8;
    public ModelRenderer ssvegeta9;
    public ModelRenderer ssvegeta10;
    public ModelRenderer ssvegeta11;
    public ModelRenderer ssvegeta12;
    public ModelRenderer ssvegeta13;
    public ModelRenderer ssvegeta14;
    public ModelRenderer ssvegeta15;
    public ModelRenderer ssvegeta16;
    public ModelRenderer ssvegeta17;
    public ModelRenderer ssvegeta18;
    public ModelRenderer ssvegeta19;
    public ModelRenderer ssvegeta20;
    public ModelRenderer ssvegeta21;
    public ModelRenderer ssvegeta22;
    public ModelRenderer gohan1;
    public ModelRenderer gohan7;
    public ModelRenderer gohan8;
    public ModelRenderer gohan10;
    public ModelRenderer gohan11;
    public ModelRenderer gohan12;
    public ModelRenderer gohan13;
    public ModelRenderer gohan14;
    public ModelRenderer gohan15;
    public ModelRenderer gohan16;
    public ModelRenderer gohan17;
    public ModelRenderer gohan18;
    public ModelRenderer gohan19;
    public ModelRenderer gohan20;
    public ModelRenderer gohan21;
    public ModelRenderer gohan22;
    public ModelRenderer gohan26;
    public ModelRenderer sgohan1;
    public ModelRenderer sgohan7;
    public ModelRenderer sgohan8;
    public ModelRenderer sgohan10;
    public ModelRenderer sgohan11;
    public ModelRenderer sgohan12;
    public ModelRenderer sgohan13;
    public ModelRenderer sgohan14;
    public ModelRenderer sgohan15;
    public ModelRenderer sgohan16;
    public ModelRenderer sgohan17;
    public ModelRenderer sgohan18;
    public ModelRenderer sgohan19;
    public ModelRenderer sgohan20;
    public ModelRenderer sgohan21;
    public ModelRenderer sgohan22;
    public ModelRenderer sgohan26;
    public ModelRenderer ssgohan1;
    public ModelRenderer ssgohan7;
    public ModelRenderer ssgohan8;
    public ModelRenderer ssgohan10;
    public ModelRenderer ssgohan11;
    public ModelRenderer ssgohan12;
    public ModelRenderer ssgohan13;
    public ModelRenderer ssgohan14;
    public ModelRenderer ssgohan15;
    public ModelRenderer ssgohan16;
    public ModelRenderer ssgohan17;
    public ModelRenderer ssgohan18;
    public ModelRenderer ssgohan19;
    public ModelRenderer ssgohan20;
    public ModelRenderer ssgohan21;
    public ModelRenderer ssgohan22;
    public ModelRenderer ssgohan26;
    ModelRenderer gokuni1;
    ModelRenderer gokuni2;
    ModelRenderer gokuni3;
    ModelRenderer gokuni4;
    ModelRenderer gokuni5;
    ModelRenderer gokuni6;
    ModelRenderer gokuni7;
    ModelRenderer gokuni8;
    ModelRenderer gokuni9;
    ModelRenderer gokuni10;
    ModelRenderer gokuni11;
    ModelRenderer gokuni12;
    ModelRenderer ght1;
    ModelRenderer ght2;
    ModelRenderer ght3;
    ModelRenderer ght4;
    ModelRenderer ght5;
    ModelRenderer ght6;
    ModelRenderer ght7;
    ModelRenderer ght8;
    ModelRenderer ght9;
    ModelRenderer ght11;
    ModelRenderer ght14;
    ModelRenderer ght16;
    ModelRenderer goten2;
    ModelRenderer goten3;
    ModelRenderer goten4;
    ModelRenderer goten5;
    ModelRenderer goten6;
    ModelRenderer goten9;
    ModelRenderer goten14;
    ModelRenderer goten16;
    ModelRenderer gotent1;
    ModelRenderer gotent2;
    ModelRenderer gotent3;
    ModelRenderer gotent5;
    ModelRenderer gotent6;
    ModelRenderer gotent7;
    ModelRenderer gotent8;
    ModelRenderer gotent9;
    ModelRenderer gotent11;
    ModelRenderer gotent16;
    ModelRenderer hairc71;
    ModelRenderer hairc72;
    ModelRenderer hairc81;
    ModelRenderer hairc82;
    ModelRenderer hairc83;
    ModelRenderer radlike1;
    ModelRenderer radlike2;
    ModelRenderer radlike3;
    ModelRenderer radlike4;
    ModelRenderer radlike5;
    ModelRenderer radlike7;
    ModelRenderer radlike8;
    ModelRenderer radlike10;
    ModelRenderer radlike11;
    ModelRenderer radlike12;
    ModelRenderer radlike13;
    ModelRenderer radlike14;
    ModelRenderer radlike15;
    ModelRenderer radlike16;
    ModelRenderer radlike17;
    ModelRenderer radlike18;
    ModelRenderer radlike19;
    ModelRenderer radlike20;
    ModelRenderer radlike21;
    ModelRenderer radlike22;
    ModelRenderer radlike23;
    ModelRenderer radlike24;
    ModelRenderer radlike25;
    ModelRenderer radlike26;
    ModelRenderer radlike27;
    ModelRenderer radlike28;
    ModelRenderer radlike29;
    ModelRenderer radlike30;
    ModelRenderer radlike31;
    ModelRenderer radlike32;
    ModelRenderer radlik6;
    ModelRenderer radlik7;
    ModelRenderer radlik15;
    ModelRenderer radlik1;
    ModelRenderer radlik2;
    ModelRenderer radlik3;
    ModelRenderer radlik4;
    ModelRenderer radlik5;
    ModelRenderer radlik8;
    ModelRenderer radlik9;
    ModelRenderer radlik10;
    ModelRenderer radlik11;
    ModelRenderer radlik12;
    ModelRenderer radlik13;
    ModelRenderer radlik14;
    ModelRenderer radlik16;
    ModelRenderer radlik17;
    ModelRenderer radlik18;
    ModelRenderer ssjsan1;
    ModelRenderer ssjsan2;
    ModelRenderer ssjsan3;
    ModelRenderer ssjsan4;
    ModelRenderer ssjsan5;
    ModelRenderer ssjsan7;
    ModelRenderer ssjsan8;
    ModelRenderer ssjsan10;
    ModelRenderer ssjsan11;
    ModelRenderer ssjsan12;
    ModelRenderer ssjsan13;
    ModelRenderer ssjsan14;
    ModelRenderer ssjsan15;
    ModelRenderer ssjsan16;
    ModelRenderer ssjsan17;
    ModelRenderer ssjsan18;
    ModelRenderer ssjsan19;
    ModelRenderer ssjsan20;
    ModelRenderer ssjsan21;
    ModelRenderer ssjsan22;
    ModelRenderer ssjsan23;
    ModelRenderer ssjsan24;
    ModelRenderer ssjsan25;
    ModelRenderer ssjsan26;
    ModelRenderer ssjsan27;
    ModelRenderer ssjsan28;
    ModelRenderer ssjsan29;
    ModelRenderer ssjsan30;
    ModelRenderer ssjsan31;
    ModelRenderer ssjsan32;
    ModelRenderer long6;
    ModelRenderer long7;
    ModelRenderer long15;
    ModelRenderer long1;
    ModelRenderer long2;
    ModelRenderer long3;
    ModelRenderer long4;
    ModelRenderer long5;
    ModelRenderer long8;
    ModelRenderer long9;
    ModelRenderer long10;
    ModelRenderer long11;
    ModelRenderer long12;
    ModelRenderer long13;
    ModelRenderer long14;
    ModelRenderer long16;
    ModelRenderer long17;
    ModelRenderer long18;
    ModelRenderer tincs1;
    public ModelRenderer halo;
    public ModelRenderer halo1;
    public ModelRenderer halo2;
    public ModelRenderer halo3;
    public ModelRenderer halo4;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer Brightarm;
    ModelRenderer Bleftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer skirt1;
    ModelRenderer skirt2;
    ModelRenderer body;
    ModelRenderer hip;
    ModelRenderer waist;
    ModelRenderer Bbreast;
    ModelRenderer breast;
    ModelRenderer bottom;
    ModelRenderer breast2;
    ModelRenderer Bbreast2;
    public ModelRenderer S1bipedHead;
    public ModelRenderer S1bipedBody;
    public ModelRenderer S1bipedRightArm;
    public ModelRenderer S1bipedLeftArm;
    public ModelRenderer S1bipedRightLeg;
    public ModelRenderer S1bipedLeftLeg;
    ModelRenderer S1rightarm;
    ModelRenderer S1leftarm;
    ModelRenderer S1Brightarm;
    ModelRenderer S1Bleftarm;
    ModelRenderer S1rightleg;
    ModelRenderer S1leftleg;
    ModelRenderer S1skirt1;
    ModelRenderer S1skirt2;
    ModelRenderer S1body;
    ModelRenderer S1hip;
    ModelRenderer S1waist;
    ModelRenderer S1Bbreast;
    ModelRenderer S1breast;
    ModelRenderer S1bottom;
    ModelRenderer S1breast2;
    ModelRenderer S1Bbreast2;
    ModelRenderer Nam;
    ModelRenderer near1;
    ModelRenderer near2;
    ModelRenderer ant1;
    ModelRenderer ant2;
    ModelRenderer ant3;
    ModelRenderer ant4;
    public ModelRenderer Fro;
    public ModelRenderer Fro0;
    public ModelRenderer Fro1;
    public ModelRenderer Fro2;
    public ModelRenderer Fro5;
    public ModelRenderer Fro5b;
    public ModelRenderer Fro5l;
    public ModelRenderer Fro5r;
    public ModelRenderer FroB;
    public ModelRenderer appule;
    public ModelRenderer Fhorn2;
    public ModelRenderer Fhorn1;
    public ModelRenderer Fhorn3;
    public ModelRenderer Fhorn4;
    public ModelRenderer F2horn1;
    public ModelRenderer F2horn2;
    public ModelRenderer F5horn1;
    public ModelRenderer F5horn2;
    public ModelRenderer F5horn3;
    public ModelRenderer F5horn4;
    public ModelRenderer F5horn5;
    public ModelRenderer F5spike1;
    public ModelRenderer F5spike2;
    public ModelRenderer F5spike3;
    public ModelRenderer F5spike4;
    public ModelRenderer ftail1;
    public ModelRenderer ftail2;
    public ModelRenderer fear1;
    public ModelRenderer fear2;
    public ModelRenderer leftarmshoulder;
    public ModelRenderer rightarmshoulder;
    public ModelRenderer ftailS1;
    public ModelRenderer ftailS2;
    public ModelRenderer ftailS3;
    public ModelRenderer ftailS4;
    public ModelRenderer ftailS5;
    public ModelRenderer ftailS6;
    public ModelRenderer SaiE;
    public ModelRenderer kao;
    public ModelRenderer SaiT1;
    public ModelRenderer SaiT2;
    public ModelRenderer tail1;
    public ModelRenderer tail2;
    public ModelRenderer tailS3;
    public ModelRenderer tailS4;
    public ModelRenderer tailS5;
    public ModelRenderer tailS6;
    public ModelRenderer tail3;
    public ModelRenderer tail4;
    public ModelRenderer tail5;
    public ModelRenderer tail6;
    ModelRendererJBRA[] hairall;
    ModelRenderer face1;
    ModelRenderer nose;
    ModelRenderer face2;
    ModelRenderer mouth;
    ModelRenderer face3;
    ModelRenderer eyel;
    ModelRenderer face4;
    ModelRenderer eyer;
    ModelRenderer face5;
    ModelRenderer eyeb;
    ModelRenderer face6;
    ModelRenderer eyew;
    public int field_78119_l = 0;
    public int field_78120_m = 0;
    public boolean field_78117_n = false;
    public boolean field_78118_o = false;
    private Entity Entity;
    private String name;
    private String dns;
    private float age;
    public static float f = 1.0f;
    public static int g = 1;
    public static int y = 1;
    public static int p = 0;
    public static float rot1;
    public static float rot4;
    public static float rot3;
    public static float rot2;
    public static float rot5;
    public static float rot6;
    ModelRenderer H;
    ModelRenderer RA;
    ModelRenderer LA;
    ModelRenderer RL;
    ModelRenderer LL;
    ModelRenderer B;
    ModelRenderer B1;
    ModelRenderer B2;
    ModelRenderer B3;
    ModelRenderer B4;
    ModelRenderer B5;
    ModelRenderer B7;
    ModelRenderer B9;
    public int b = 0;
    public static boolean detail;

    public ModelBipedJFC() {
        this(0.0f);
    }

    public ModelBipedJFC(float par1) {
        this(par1, 0.0f, 64, 32);
    }

    /*
     * Opcode count of 19183 triggered aggressive code reduction.  Override with --aggressivesizethreshold.
     */
    public ModelBipedJFC(float par1, float par2, int par3, int par4) {
        this.field_78090_t = par3;
        this.field_78089_u = par4;
        this.field_78122_k = new ModelRenderer((ModelBase)this, 0, 0);
        this.field_78122_k.func_78790_a(-5.0f, 0.0f, -1.0f, 10, 16, 1, par1);
        this.field_78121_j = new ModelRenderer((ModelBase)this, 24, 0);
        this.field_78121_j.func_78790_a(-3.0f, -6.0f, -1.0f, 6, 6, 1, par1);
        this.field_78116_c = new ModelRenderer((ModelBase)this, 0, 0);
        this.field_78116_c.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, par1);
        this.field_78116_c.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.field_78114_d = new ModelRenderer((ModelBase)this, 32, 0);
        this.field_78114_d.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, par1 + 0.5f);
        this.field_78114_d.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.field_78115_e = new ModelRenderer((ModelBase)this, 16, 16);
        this.field_78115_e.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, par1);
        this.field_78115_e.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.field_78112_f = new ModelRenderer((ModelBase)this, 40, 16);
        this.field_78112_f.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 12, 4, par1);
        this.field_78112_f.func_78793_a(-5.0f, 2.0f + par2, 0.0f);
        this.field_78113_g = new ModelRenderer((ModelBase)this, 40, 16);
        this.field_78113_g.field_78809_i = true;
        this.field_78113_g.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, par1);
        this.field_78113_g.func_78793_a(5.0f, 2.0f + par2, 0.0f);
        this.field_78123_h = new ModelRenderer((ModelBase)this, 0, 16);
        this.field_78123_h.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, par1);
        this.field_78123_h.func_78793_a(-1.9f, 12.0f + par2, 0.0f);
        this.field_78124_i = new ModelRenderer((ModelBase)this, 0, 16);
        this.field_78124_i.field_78809_i = true;
        this.field_78124_i.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, par1);
        this.field_78124_i.func_78793_a(1.9f, 12.0f + par2, 0.0f);
        this.bipedHeadg = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadg.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadg.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadt = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadt.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadt.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadsg = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadsg.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadsg.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadssg = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadssg.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadssg.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadst = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadst.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadst.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadsst = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadsst.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadsst.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadv = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadv.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadv.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadsv = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadsv.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadsv.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadssv = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadssv.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadssv.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadgh = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadgh.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadgh.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadsgh = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadsgh.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadsgh.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadssgh = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadssgh.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadssgh.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadnull = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadnull.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadnull.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadg2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadg2.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadg2.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadght = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadght.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadght.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadgt = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadgt.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadgt.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadgtt = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadgtt.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadgtt.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadc7 = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadc7.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadc7.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadc8 = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadc8.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadc8.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadrad = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadrad.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadrad.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadradl2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadradl2.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadradl2.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadradl = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadradl.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadradl.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadssj3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadssj3.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadssj3.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadssj3l = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadssj3l.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadssj3l.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadssj3t = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadssj3t.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadssj3t.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadssj3l2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadssj3l2.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.bipedHeadssj3l2.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.bipedHeadAll = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHeadAll.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, par1 + 0.01f);
        this.bipedHeadAll.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.goku1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goku1.func_78789_a(-1.0f, -10.0f, 0.0f, 4, 4, 4);
        this.goku1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.goku1.func_78787_b(128, 64);
        this.goku1.field_78809_i = true;
        this.setRotation(this.goku1, 0.1745329f, 0.0f, -0.4363323f);
        this.goku2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goku2.func_78789_a(-8.0f, -4.5f, 0.0f, 4, 3, 3);
        this.goku2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.goku2.func_78787_b(128, 64);
        this.goku2.field_78809_i = true;
        this.setRotation(this.goku2, 0.0f, -0.1745329f, 0.3490659f);
        this.goku3 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goku3.func_78789_a(-7.0f, -2.6f, 1.0f, 4, 2, 2);
        this.goku3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.goku3.func_78787_b(128, 64);
        this.goku3.field_78809_i = true;
        this.setRotation(this.goku3, 0.0f, -0.2617994f, 0.1943133f);
        this.goku4 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goku4.func_78789_a(3.0f, -4.0f, 0.0f, 4, 3, 3);
        this.goku4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.goku4.func_78787_b(128, 64);
        this.goku4.field_78809_i = true;
        this.setRotation(this.goku4, 0.0f, 0.1745329f, -0.3490659f);
        this.goku5 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goku5.func_78789_a(3.0f, -2.3f, 0.7f, 3, 2, 2);
        this.goku5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.goku5.func_78787_b(128, 64);
        this.goku5.field_78809_i = true;
        this.setRotation(this.goku5, 0.0f, 0.1745329f, -0.1151917f);
        this.goku6 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goku6.func_78789_a(5.0f, -4.3f, 1.5f, 3, 2, 2);
        this.goku6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.goku6.func_78787_b(128, 64);
        this.goku6.field_78809_i = true;
        this.setRotation(this.goku6, 0.0f, 0.3490659f, -0.2617994f);
        this.goku7 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goku7.func_78789_a(1.0f, -11.0f, 2.0f, 3, 3, 3);
        this.goku7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.goku7.func_78787_b(128, 64);
        this.goku7.field_78809_i = true;
        this.setRotation(this.goku7, 0.3490659f, 0.0f, -0.6108652f);
        this.goku8 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goku8.func_78789_a(3.0f, -12.0f, 4.0f, 2, 3, 2);
        this.goku8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.goku8.func_78787_b(128, 64);
        this.goku8.field_78809_i = true;
        this.setRotation(this.goku8, 0.5235988f, 0.0f, -0.7853982f);
        this.goku9 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goku9.func_78789_a(-9.0f, -4.7f, 1.5f, 3, 2, 2);
        this.goku9.func_78793_a(0.0f, 0.0f, 0.0f);
        this.goku9.func_78787_b(128, 64);
        this.goku9.field_78809_i = true;
        this.setRotation(this.goku9, 0.0f, -0.3490659f, 0.2617994f);
        this.goku10 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goku10.func_78789_a(-10.0f, -4.8f, 1.0f, 5, 2, 2);
        this.goku10.func_78793_a(0.0f, 0.0f, 0.0f);
        this.goku10.func_78787_b(128, 64);
        this.goku10.field_78809_i = true;
        this.setRotation(this.goku10, 0.0f, -0.3839724f, 0.5270894f);
        this.goku11 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goku11.func_78789_a(1.0f, -8.0f, 5.0f, 1, 4, 1);
        this.goku11.func_78793_a(0.0f, 0.0f, 0.0f);
        this.goku11.func_78787_b(128, 64);
        this.goku11.field_78809_i = true;
        this.setRotation(this.goku11, 0.6806784f, 0.0f, -0.1745329f);
        this.goku12 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goku12.func_78789_a(-3.5f, -7.0f, -5.0f, 2, 3, 3);
        this.goku12.func_78793_a(0.0f, 0.0f, 0.0f);
        this.goku12.func_78787_b(128, 64);
        this.goku12.field_78809_i = true;
        this.setRotation(this.goku12, 0.0f, 0.0f, 0.4014257f);
        this.goku13 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goku13.func_78789_a(-6.2f, -5.5f, -5.0f, 2, 3, 2);
        this.goku13.func_78793_a(0.0f, 0.0f, 0.0f);
        this.goku13.func_78787_b(128, 64);
        this.goku13.field_78809_i = true;
        this.setRotation(this.goku13, 0.0f, 0.0f, 0.5235988f);
        this.goku14 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goku14.func_78789_a(-7.5f, -4.0f, -5.0f, 1, 3, 2);
        this.goku14.func_78793_a(0.0f, 0.0f, 0.0f);
        this.goku14.func_78787_b(128, 64);
        this.goku14.field_78809_i = true;
        this.setRotation(this.goku14, 0.0f, 0.0f, 0.6108652f);
        this.goku15 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goku15.func_78789_a(3.2f, -6.5f, -5.0f, 2, 3, 2);
        this.goku15.func_78793_a(0.0f, 0.0f, 0.0f);
        this.goku15.func_78787_b(128, 64);
        this.goku15.field_78809_i = true;
        this.setRotation(this.goku15, 0.0f, 0.0f, -0.3490659f);
        this.goku16 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goku16.func_78789_a(6.5f, -4.5f, -5.0f, 1, 3, 2);
        this.goku16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.goku16.func_78787_b(128, 64);
        this.goku16.field_78809_i = true;
        this.setRotation(this.goku16, 0.0f, 0.0f, -0.6108652f);
        this.sgoku1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku1.func_78789_a(-1.0f, -10.0f, -6.0f, 4, 4, 4);
        this.sgoku1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku1.func_78787_b(128, 64);
        this.sgoku1.field_78809_i = true;
        this.setRotation(this.sgoku1, -0.3141593f, 0.0f, 0.0f);
        this.sgoku2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku2.func_78789_a(-8.0f, -4.5f, -1.0f, 4, 3, 3);
        this.sgoku2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku2.func_78787_b(128, 64);
        this.sgoku2.field_78809_i = true;
        this.setRotation(this.sgoku2, 0.0f, 0.1745329f, 0.5759587f);
        this.sgoku3 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku3.func_78789_a(-7.0f, -2.0f, 0.0f, 4, 2, 2);
        this.sgoku3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku3.func_78787_b(128, 64);
        this.sgoku3.field_78809_i = true;
        this.setRotation(this.sgoku3, 0.0f, 0.2617994f, 0.5061455f);
        this.sgoku4 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku4.func_78789_a(4.0f, -4.0f, -1.0f, 4, 3, 3);
        this.sgoku4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku4.func_78787_b(128, 64);
        this.sgoku4.field_78809_i = true;
        this.setRotation(this.sgoku4, 0.0f, -0.1745329f, -0.6108652f);
        this.sgoku5 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku5.func_78789_a(3.0f, -2.0f, 0.7f, 4, 2, 2);
        this.sgoku5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku5.func_78787_b(128, 64);
        this.sgoku5.field_78809_i = true;
        this.setRotation(this.sgoku5, 0.0f, -0.1745329f, -0.5061455f);
        this.sgoku6 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku6.func_78789_a(7.0f, -2.0f, -1.5f, 3, 2, 2);
        this.sgoku6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku6.func_78787_b(128, 64);
        this.sgoku6.field_78809_i = true;
        this.setRotation(this.sgoku6, 0.0f, -0.3490659f, -0.9250245f);
        this.sgoku7 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku7.func_78789_a(-0.5f, -12.0f, -6.0f, 3, 3, 3);
        this.sgoku7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku7.func_78787_b(128, 64);
        this.sgoku7.field_78809_i = true;
        this.setRotation(this.sgoku7, -0.4363323f, 0.0f, 0.0f);
        this.sgoku8 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku8.func_78789_a(0.0f, -14.0f, -7.0f, 2, 3, 2);
        this.sgoku8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku8.func_78787_b(128, 64);
        this.sgoku8.field_78809_i = true;
        this.setRotation(this.sgoku8, -0.5934119f, 0.0f, 0.0f);
        this.sgoku9 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku9.func_78789_a(-10.0f, -2.166667f, -1.5f, 3, 2, 2);
        this.sgoku9.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku9.func_78787_b(128, 64);
        this.sgoku9.field_78809_i = true;
        this.setRotation(this.sgoku9, 0.0f, 0.3490659f, 0.8901179f);
        this.sgoku10 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku10.func_78789_a(-1.0f, -10.0f, -6.0f, 4, 6, 4);
        this.sgoku10.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku10.func_78787_b(128, 64);
        this.sgoku10.field_78809_i = true;
        this.setRotation(this.sgoku10, -0.4363323f, 0.0f, -0.4014257f);
        this.sgoku11 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku11.func_78789_a(-0.5f, -12.0f, -6.0f, 5, 4, 3);
        this.sgoku11.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku11.func_78787_b(128, 64);
        this.sgoku11.field_78809_i = true;
        this.setRotation(this.sgoku11, -0.5410521f, 0.0f, -0.3665191f);
        this.sgoku12 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku12.func_78789_a(-0.5f, -14.0f, -6.0f, 3, 3, 3);
        this.sgoku12.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku12.func_78787_b(128, 64);
        this.sgoku12.field_78809_i = true;
        this.setRotation(this.sgoku12, -0.6108652f, 0.0f, -0.2443461f);
        this.sgoku13 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku13.func_78789_a(0.0f, -15.4f, -7.0f, 2, 5, 2);
        this.sgoku13.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku13.func_78787_b(128, 64);
        this.sgoku13.field_78809_i = true;
        this.setRotation(this.sgoku13, -0.6981317f, 0.0f, -0.122173f);
        this.sgoku14 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku14.func_78789_a(-1.5f, -9.0f, -5.0f, 3, 5, 3);
        this.sgoku14.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku14.func_78787_b(128, 64);
        this.sgoku14.field_78809_i = true;
        this.setRotation(this.sgoku14, -0.3665191f, 0.0f, 0.4363323f);
        this.sgoku15 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku15.func_78789_a(-0.5f, -10.0f, -6.0f, 3, 3, 3);
        this.sgoku15.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku15.func_78787_b(128, 64);
        this.sgoku15.field_78809_i = true;
        this.setRotation(this.sgoku15, -0.5410521f, 0.0f, 0.2455096f);
        this.sgoku16 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku16.func_78789_a(-1.0f, -12.0f, -6.0f, 3, 3, 3);
        this.sgoku16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku16.func_78787_b(128, 64);
        this.sgoku16.field_78809_i = true;
        this.setRotation(this.sgoku16, -0.5759587f, 0.0f, 0.1396263f);
        this.sgoku17 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku17.func_78789_a(-2.0f, -9.0f, -1.0f, 4, 5, 4);
        this.sgoku17.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku17.func_78787_b(128, 64);
        this.sgoku17.field_78809_i = true;
        this.setRotation(this.sgoku17, -0.2792527f, 0.0f, 0.0f);
        this.sgoku18 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku18.func_78789_a(-1.0f, -10.0f, -1.0f, 4, 5, 4);
        this.sgoku18.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku18.func_78787_b(128, 64);
        this.sgoku18.field_78809_i = true;
        this.setRotation(this.sgoku18, -0.2443461f, 0.2617994f, 0.0174533f);
        this.sgoku19 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku19.func_78789_a(-4.0f, -11.0f, -1.0f, 4, 6, 4);
        this.sgoku19.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku19.func_78787_b(128, 64);
        this.sgoku19.field_78809_i = true;
        this.setRotation(this.sgoku19, -0.2443461f, -0.2617994f, 0.0174533f);
        this.sgoku20 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku20.func_78789_a(-2.0f, -13.0f, -1.0f, 3, 5, 4);
        this.sgoku20.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku20.func_78787_b(128, 64);
        this.sgoku20.field_78809_i = true;
        this.setRotation(this.sgoku20, -0.1396263f, 0.0f, 0.0f);
        this.sgoku21 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku21.func_78789_a(-1.0f, -14.0f, 0.0f, 3, 5, 3);
        this.sgoku21.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku21.func_78787_b(128, 64);
        this.sgoku21.field_78809_i = true;
        this.setRotation(this.sgoku21, -0.122173f, 0.1745329f, 0.0f);
        this.sgoku22 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku22.func_78789_a(-2.866667f, -13.2f, -0.6666667f, 3, 4, 3);
        this.sgoku22.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku22.func_78787_b(128, 64);
        this.sgoku22.field_78809_i = true;
        this.setRotation(this.sgoku22, -0.2443461f, -0.2617994f, 0.0174533f);
        this.sgoku23 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku23.func_78789_a(2.466667f, -6.5f, -5.333333f, 2, 3, 3);
        this.sgoku23.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku23.func_78787_b(128, 64);
        this.sgoku23.field_78809_i = true;
        this.setRotation(this.sgoku23, 0.0f, 0.0f, -0.4014257f);
        this.sgoku24 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku24.func_78789_a(-3.7f, -6.7f, -5.533333f, 2, 3, 3);
        this.sgoku24.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku24.func_78787_b(128, 64);
        this.sgoku24.field_78809_i = true;
        this.setRotation(this.sgoku24, 0.0f, 0.0f, 0.3665191f);
        this.sgoku25 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku25.func_78789_a(-7.0f, -4.5f, -5.0f, 2, 3, 3);
        this.sgoku25.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku25.func_78787_b(128, 64);
        this.sgoku25.field_78809_i = true;
        this.setRotation(this.sgoku25, 0.0f, 0.0f, 0.6806784f);
        this.sgoku26 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgoku26.func_78789_a(5.3f, -4.5f, -5.266667f, 2, 3, 3);
        this.sgoku26.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sgoku26.func_78787_b(128, 64);
        this.sgoku26.field_78809_i = true;
        this.setRotation(this.sgoku26, 0.0f, 0.0f, -0.5934119f);
        this.ssgoku1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku1.func_78789_a(-1.0f, -10.0f, -6.0f, 4, 4, 4);
        this.ssgoku1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku1, -0.3141593f, 0.0f, 0.0f);
        this.ssgoku2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku2.func_78789_a(-8.8f, -4.5f, -1.0f, 4, 3, 3);
        this.ssgoku2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku2, 0.0f, 0.1745329f, 0.6108652f);
        this.ssgoku3 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku3.func_78789_a(-7.3f, -2.0f, 0.0f, 4, 2, 2);
        this.ssgoku3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku3, 0.0f, 0.2617994f, 0.5410521f);
        this.ssgoku4 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku4.func_78789_a(4.8f, -4.0f, -1.0f, 4, 3, 3);
        this.ssgoku4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku4, 0.0f, -0.1745329f, -0.6806784f);
        this.ssgoku5 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku5.func_78789_a(3.8f, -2.0f, 0.7f, 4, 2, 2);
        this.ssgoku5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku5, 0.0f, -0.1745329f, -0.6108652f);
        this.ssgoku6 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku6.func_78789_a(7.8f, -2.0f, -1.5f, 3, 2, 2);
        this.ssgoku6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku6, 0.0f, -0.3490659f, -0.9599311f);
        this.ssgoku7 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku7.func_78789_a(-0.5f, -12.0f, -6.0f, 3, 3, 3);
        this.ssgoku7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku7, -0.4014257f, 0.0f, 0.0f);
        this.ssgoku8 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku8.func_78789_a(0.0f, -14.0f, -7.0f, 2, 3, 2);
        this.ssgoku8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku8, -0.5410521f, 0.0f, 0.0f);
        this.ssgoku9 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku9.func_78789_a(-10.8f, -2.166667f, -1.5f, 3, 2, 2);
        this.ssgoku9.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku9, 0.0f, 0.3490659f, 0.9250245f);
        this.ssgoku10 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku10.func_78789_a(-1.0f, -10.3f, -6.0f, 4, 6, 4);
        this.ssgoku10.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku10, -0.4363323f, 0.0f, -0.3665191f);
        this.ssgoku11 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku11.func_78789_a(-0.5f, -12.3f, -6.0f, 5, 4, 3);
        this.ssgoku11.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku11, -0.5410521f, 0.0f, -0.3316126f);
        this.ssgoku12 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku12.func_78789_a(-0.5f, -14.5f, -6.0f, 3, 3, 3);
        this.ssgoku12.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku12, -0.5934119f, 0.0f, -0.2268928f);
        this.ssgoku13 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku13.func_78789_a(0.0f, -15.4f, -7.0f, 2, 5, 2);
        this.ssgoku13.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku13, -0.6806784f, 0.0f, -0.0698132f);
        this.ssgoku14 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku14.func_78789_a(-1.3f, -9.3f, -5.0f, 3, 5, 3);
        this.ssgoku14.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku14, -0.3665191f, 0.0f, 0.4014257f);
        this.ssgoku15 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku15.func_78789_a(-0.5f, -10.8f, -6.0f, 3, 3, 3);
        this.ssgoku15.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku15, -0.5410521f, 0.0f, 0.2617994f);
        this.ssgoku16 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku16.func_78789_a(-1.0f, -12.8f, -6.0f, 3, 3, 3);
        this.ssgoku16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku16, -0.5759587f, 0.0f, 0.1745329f);
        this.ssgoku17 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku17.func_78789_a(-2.0f, -9.0f, -1.0f, 4, 5, 4);
        this.ssgoku17.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku17, -0.2792527f, 0.0f, 0.0f);
        this.ssgoku18 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku18.func_78789_a(-1.0f, -10.0f, -1.0f, 4, 5, 4);
        this.ssgoku18.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku18, -0.2443461f, 0.2617994f, 0.0174533f);
        this.ssgoku19 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku19.func_78789_a(-4.0f, -11.0f, -1.0f, 4, 6, 4);
        this.ssgoku19.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku19, -0.2443461f, -0.2617994f, 0.0174533f);
        this.ssgoku20 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku20.func_78789_a(-2.0f, -13.0f, -1.0f, 3, 5, 4);
        this.ssgoku20.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku20, -0.1396263f, 0.0f, 0.0f);
        this.ssgoku21 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku21.func_78789_a(-0.6f, -14.5f, 0.0f, 3, 5, 3);
        this.ssgoku21.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku21, -0.122173f, 0.1745329f, 0.0f);
        this.ssgoku22 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku22.func_78789_a(-2.866667f, -13.2f, -0.6666667f, 3, 4, 3);
        this.ssgoku22.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku22, -0.2443461f, -0.2617994f, 0.0174533f);
        this.ssgoku23 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku23.func_78789_a(-0.5333334f, -9.0f, -6.333333f, 2, 3, 3);
        this.ssgoku23.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku23, -0.1745329f, 0.0f, 0.1919862f);
        this.ssgoku24 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku24.func_78789_a(-3.7f, -6.7f, -5.533333f, 2, 3, 3);
        this.ssgoku24.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku24, 0.0f, 0.0f, 0.3665191f);
        this.ssgoku25 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku25.func_78789_a(-8.2f, -4.1f, -5.0f, 2, 3, 3);
        this.ssgoku25.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku25, 0.0f, 0.0f, 0.6806784f);
        this.ssgoku26 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgoku26.func_78789_a(5.433333f, -4.5f, -5.266667f, 2, 3, 3);
        this.ssgoku26.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgoku26, 0.0f, 0.0f, -0.5934119f);
        this.trunk1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.trunk1.func_78789_a(4.7f, -6.4f, -4.2f, 4, 6, 3);
        this.trunk1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.trunk1.func_78787_b(128, 64);
        this.trunk1.field_78809_i = true;
        this.setRotation(this.trunk1, 0.1745329f, 0.0f, -0.8028515f);
        this.trunk2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.trunk2.func_78789_a(-8.733334f, -6.4f, -4.0f, 4, 6, 3);
        this.trunk2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.trunk2.func_78787_b(128, 64);
        this.trunk2.field_78809_i = true;
        this.setRotation(this.trunk2, 0.1745329f, 0.0f, 0.8028515f);
        this.trunk3 = new ModelRenderer((ModelBase)this, 32, 0);
        this.trunk3.func_78789_a(3.0f, -8.0f, -1.2f, 4, 6, 3);
        this.trunk3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.trunk3.func_78787_b(128, 64);
        this.trunk3.field_78809_i = true;
        this.setRotation(this.trunk3, 0.1745329f, -0.0872665f, -0.4014257f);
        this.trunk4 = new ModelRenderer((ModelBase)this, 32, 0);
        this.trunk4.func_78789_a(3.0f, -7.6f, 1.6f, 4, 6, 3);
        this.trunk4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.trunk4.func_78787_b(128, 64);
        this.trunk4.field_78809_i = true;
        this.setRotation(this.trunk4, 0.1745329f, -0.0174533f, -0.4014257f);
        this.trunk5 = new ModelRenderer((ModelBase)this, 32, 0);
        this.trunk5.func_78789_a(-7.0f, -7.6f, 1.8f, 4, 6, 3);
        this.trunk5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.trunk5.func_78787_b(128, 64);
        this.trunk5.field_78809_i = true;
        this.setRotation(this.trunk5, 0.1745329f, -0.0174533f, 0.4014257f);
        this.trunk6 = new ModelRenderer((ModelBase)this, 32, 0);
        this.trunk6.func_78789_a(-7.0f, -8.0f, -1.2f, 4, 6, 3);
        this.trunk6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.trunk6.func_78787_b(128, 64);
        this.trunk6.field_78809_i = true;
        this.setRotation(this.trunk6, 0.1745329f, 0.0872665f, 0.4014257f);
        this.trunk7 = new ModelRenderer((ModelBase)this, 32, 0);
        this.trunk7.func_78789_a(4.4f, -7.0f, 0.6f, 4, 5, 3);
        this.trunk7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.trunk7.func_78787_b(128, 64);
        this.trunk7.field_78809_i = true;
        this.setRotation(this.trunk7, 0.0f, -0.6457718f, -0.3665191f);
        this.trunk8 = new ModelRenderer((ModelBase)this, 32, 0);
        this.trunk8.func_78789_a(-8.4f, -7.0f, 0.6f, 4, 5, 3);
        this.trunk8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.trunk8.func_78787_b(128, 64);
        this.trunk8.field_78809_i = true;
        this.setRotation(this.trunk8, 0.0f, 0.6457718f, 0.3665191f);
        this.trunk9 = new ModelRenderer((ModelBase)this, 32, 0);
        this.trunk9.func_78789_a(-2.5f, -7.0f, 4.0f, 5, 4, 3);
        this.trunk9.func_78793_a(0.0f, 0.0f, 0.0f);
        this.trunk9.func_78787_b(128, 64);
        this.trunk9.field_78809_i = true;
        this.setRotation(this.trunk9, 0.08f, 0.0f, 0.0f);
        this.strunk1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.strunk1.func_78789_a(-2.0f, -9.0f, -4.933333f, 6, 3, 4);
        this.strunk1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.strunk1.func_78787_b(128, 64);
        this.strunk1.field_78809_i = true;
        this.setRotation(this.strunk1, -0.0872665f, 0.0f, 0.2443461f);
        this.strunk2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.strunk2.func_78789_a(-4.0f, -9.0f, -5.0f, 6, 3, 4);
        this.strunk2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.strunk2.func_78787_b(128, 64);
        this.strunk2.field_78809_i = true;
        this.setRotation(this.strunk2, -0.0872665f, 0.0f, -0.2443461f);
        this.strunk3 = new ModelRenderer((ModelBase)this, 32, 0);
        this.strunk3.func_78789_a(-7.0f, -9.0f, -2.0f, 6, 3, 3);
        this.strunk3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.strunk3.func_78787_b(128, 64);
        this.strunk3.field_78809_i = true;
        this.setRotation(this.strunk3, -0.0872665f, 0.0f, 0.1745329f);
        this.strunk4 = new ModelRenderer((ModelBase)this, 32, 0);
        this.strunk4.func_78789_a(1.0f, -9.0f, -2.0f, 6, 3, 3);
        this.strunk4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.strunk4.func_78787_b(128, 64);
        this.strunk4.field_78809_i = true;
        this.setRotation(this.strunk4, -0.0872665f, 0.0f, -0.1745329f);
        this.strunk5 = new ModelRenderer((ModelBase)this, 32, 0);
        this.strunk5.func_78789_a(3.0f, -9.0f, 1.0f, 6, 3, 3);
        this.strunk5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.strunk5.func_78787_b(128, 64);
        this.strunk5.field_78809_i = true;
        this.setRotation(this.strunk5, -0.0872665f, 0.0f, -0.3490659f);
        this.strunk6 = new ModelRenderer((ModelBase)this, 32, 0);
        this.strunk6.func_78789_a(-9.0f, -9.0f, 1.0f, 6, 3, 3);
        this.strunk6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.strunk6.func_78787_b(128, 64);
        this.strunk6.field_78809_i = true;
        this.setRotation(this.strunk6, -0.0872665f, 0.0f, 0.3490659f);
        this.strunk7 = new ModelRenderer((ModelBase)this, 32, 0);
        this.strunk7.func_78789_a(-1.0f, -11.46667f, -2.0f, 3, 6, 3);
        this.strunk7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.strunk7.func_78787_b(128, 64);
        this.strunk7.field_78809_i = true;
        this.setRotation(this.strunk7, -0.1745329f, 0.0f, -0.5235988f);
        this.strunk8 = new ModelRenderer((ModelBase)this, 32, 0);
        this.strunk8.func_78789_a(-2.0f, -11.46667f, -2.0f, 3, 6, 3);
        this.strunk8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.strunk8.func_78787_b(128, 64);
        this.strunk8.field_78809_i = true;
        this.setRotation(this.strunk8, -0.1745329f, 0.0f, 0.5235988f);
        this.strunk9 = new ModelRenderer((ModelBase)this, 32, 0);
        this.strunk9.func_78789_a(-1.0f, -13.46667f, 0.0f, 3, 8, 3);
        this.strunk9.func_78793_a(0.0f, 0.0f, 0.0f);
        this.strunk9.func_78787_b(128, 64);
        this.strunk9.field_78809_i = true;
        this.setRotation(this.strunk9, -0.1745329f, 0.0f, 0.3490659f);
        this.strunk10 = new ModelRenderer((ModelBase)this, 32, 0);
        this.strunk10.func_78789_a(-2.0f, -13.46667f, 0.0f, 3, 8, 3);
        this.strunk10.func_78793_a(0.0f, 0.0f, 0.0f);
        this.strunk10.func_78787_b(128, 64);
        this.strunk10.field_78809_i = true;
        this.setRotation(this.strunk10, -0.1745329f, 0.0f, -0.3490659f);
        this.strunk11 = new ModelRenderer((ModelBase)this, 32, 0);
        this.strunk11.func_78789_a(-3.0f, -4.0f, 5.2f, 4, 3, 3);
        this.strunk11.func_78793_a(0.0f, 0.0f, 0.0f);
        this.strunk11.func_78787_b(128, 64);
        this.strunk11.field_78809_i = true;
        this.setRotation(this.strunk11, 0.5934119f, -0.6108652f, 0.0f);
        this.strunk12 = new ModelRenderer((ModelBase)this, 32, 0);
        this.strunk12.func_78789_a(-7.0f, -7.0f, -0.9333333f, 3, 3, 4);
        this.strunk12.func_78793_a(0.0f, 0.0f, 0.0f);
        this.strunk12.func_78787_b(128, 64);
        this.strunk12.field_78809_i = true;
        this.setRotation(this.strunk12, -0.0872665f, 0.0f, 0.2094395f);
        this.strunk13 = new ModelRenderer((ModelBase)this, 32, 0);
        this.strunk13.func_78789_a(4.133333f, -7.0f, -1.0f, 3, 3, 4);
        this.strunk13.func_78793_a(0.0f, 0.0f, 0.0f);
        this.strunk13.func_78787_b(128, 64);
        this.strunk13.field_78809_i = true;
        this.setRotation(this.strunk13, -0.0872665f, 0.0f, -0.2443461f);
        this.strunk14 = new ModelRenderer((ModelBase)this, 32, 0);
        this.strunk14.func_78789_a(-1.133333f, -4.0f, 5.2f, 4, 3, 3);
        this.strunk14.func_78793_a(0.0f, 0.0f, 0.0f);
        this.strunk14.func_78787_b(128, 64);
        this.strunk14.field_78809_i = true;
        this.setRotation(this.strunk14, 0.5934119f, 0.6108652f, 0.0f);
        this.strunk15 = new ModelRenderer((ModelBase)this, 32, 0);
        this.strunk15.func_78789_a(-3.133333f, -4.466667f, 4.933333f, 6, 3, 3);
        this.strunk15.func_78793_a(0.0f, 0.0f, 0.0f);
        this.strunk15.func_78787_b(128, 64);
        this.strunk15.field_78809_i = true;
        this.setRotation(this.strunk15, 0.5934119f, 0.0f, 0.0f);
        this.strunk16 = new ModelRenderer((ModelBase)this, 32, 0);
        this.strunk16.func_78789_a(-1.6f, -11.86667f, 1.0f, 2, 4, 2);
        this.strunk16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.strunk16.func_78787_b(128, 64);
        this.strunk16.field_78809_i = true;
        this.setRotation(this.strunk16, -0.2792527f, 0.0f, 0.5235988f);
        this.strunk17 = new ModelRenderer((ModelBase)this, 32, 0);
        this.strunk17.func_78789_a(-0.4666667f, -11.86667f, 1.0f, 2, 4, 2);
        this.strunk17.func_78793_a(0.0f, 0.0f, 0.0f);
        this.strunk17.func_78787_b(128, 64);
        this.strunk17.field_78809_i = true;
        this.setRotation(this.strunk17, -0.2617994f, 0.0f, -0.5235988f);
        this.sstrunk1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sstrunk1.func_78789_a(-2.0f, -9.0f, -4.933333f, 6, 3, 4);
        this.sstrunk1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sstrunk1, -0.1047198f, 0.0f, 0.2268928f);
        this.sstrunk2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sstrunk2.func_78789_a(-4.0f, -9.0f, -5.0f, 6, 3, 4);
        this.sstrunk2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sstrunk2, -0.1047198f, 0.0f, -0.2268928f);
        this.sstrunk3 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sstrunk3.func_78789_a(-11.0f, -7.0f, -2.0f, 6, 3, 3);
        this.sstrunk3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sstrunk3, -0.0872665f, 0.0f, 0.6981317f);
        this.sstrunk4 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sstrunk4.func_78789_a(5.0f, -7.0f, -2.0f, 6, 3, 3);
        this.sstrunk4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sstrunk4, -0.0872665f, 0.0f, -0.6981317f);
        this.sstrunk5 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sstrunk5.func_78789_a(6.0f, -2.3f, 1.0f, 6, 3, 3);
        this.sstrunk5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sstrunk5, -0.0872665f, 0.0f, -1.37881f);
        this.sstrunk6 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sstrunk6.func_78789_a(-12.0f, -2.333333f, 1.0f, 6, 3, 3);
        this.sstrunk6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sstrunk6, -0.0872665f, 0.0f, 1.37881f);
        this.sstrunk7 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sstrunk7.func_78789_a(-1.0f, -13.46667f, -2.0f, 3, 6, 3);
        this.sstrunk7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sstrunk7, -0.1745329f, 0.0f, -0.2268928f);
        this.sstrunk8 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sstrunk8.func_78789_a(-2.0f, -13.46667f, -2.0f, 3, 6, 3);
        this.sstrunk8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sstrunk8, -0.1570796f, 0.0f, 0.2268928f);
        this.sstrunk9 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sstrunk9.func_78789_a(-1.0f, -13.46667f, -1.0f, 3, 8, 3);
        this.sstrunk9.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sstrunk9, -0.4363323f, 0.0f, 0.3490659f);
        this.sstrunk10 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sstrunk10.func_78789_a(-2.0f, -13.46667f, -1.0f, 3, 8, 3);
        this.sstrunk10.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sstrunk10, -0.4363323f, 0.0f, -0.3490659f);
        this.sstrunk11 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sstrunk11.func_78789_a(-3.0f, -4.0f, 5.2f, 4, 3, 3);
        this.sstrunk11.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sstrunk11, 0.5934119f, -0.6108652f, 0.0f);
        this.sstrunk12 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sstrunk12.func_78789_a(-9.0f, -7.0f, -0.9333333f, 3, 3, 4);
        this.sstrunk12.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sstrunk12, -0.0872665f, 0.0f, 0.3490659f);
        this.sstrunk13 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sstrunk13.func_78789_a(6.0f, -7.0f, -1.0f, 3, 3, 4);
        this.sstrunk13.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sstrunk13, -0.0872665f, 0.0f, -0.3490659f);
        this.sstrunk14 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sstrunk14.func_78789_a(-1.133333f, -4.0f, 5.2f, 4, 3, 3);
        this.sstrunk14.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sstrunk14, 0.5934119f, 0.6108652f, 0.0f);
        this.sstrunk15 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sstrunk15.func_78789_a(-3.133333f, -4.466667f, 4.933333f, 6, 3, 3);
        this.sstrunk15.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sstrunk15, 0.5934119f, 0.0f, 0.0f);
        this.sstrunk16 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sstrunk16.func_78789_a(-2.0f, -11.86667f, 0.0f, 2, 4, 2);
        this.sstrunk16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sstrunk16, -0.5235988f, 0.0f, 0.2617994f);
        this.sstrunk17 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sstrunk17.func_78789_a(0.0f, -11.86667f, 0.0f, 2, 4, 2);
        this.sstrunk17.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sstrunk17, -0.5235988f, 0.0f, -0.2617994f);
        this.vegeta1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.vegeta1.func_78789_a(-1.0f, -10.0f, -6.05f, 4, 4, 4);
        this.vegeta1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.vegeta1, -0.3141593f, 0.0f, 0.0f);
        this.vegeta2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.vegeta2.func_78789_a(-8.0f, -4.5f, -1.0f, 4, 3, 3);
        this.vegeta2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.vegeta2, 0.0f, 0.1745329f, 0.5759587f);
        this.vegeta3 = new ModelRenderer((ModelBase)this, 32, 0);
        this.vegeta3.func_78789_a(-7.0f, -2.0f, 0.0f, 4, 2, 2);
        this.vegeta3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.vegeta3, 0.0f, 0.2617994f, 0.5061455f);
        this.vegeta4 = new ModelRenderer((ModelBase)this, 32, 0);
        this.vegeta4.func_78789_a(4.0f, -4.0f, -1.0f, 4, 3, 3);
        this.vegeta4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.vegeta4, 0.0f, -0.1745329f, -0.6108652f);
        this.vegeta5 = new ModelRenderer((ModelBase)this, 32, 0);
        this.vegeta5.func_78789_a(3.0f, -2.0f, 0.7f, 4, 2, 2);
        this.vegeta5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.vegeta5, 0.0f, -0.1745329f, -0.5061455f);
        this.vegeta6 = new ModelRenderer((ModelBase)this, 32, 0);
        this.vegeta6.func_78789_a(7.0f, -2.0f, -1.5f, 3, 2, 2);
        this.vegeta6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.vegeta6, 0.0f, -0.3490659f, -0.9250245f);
        this.vegeta7 = new ModelRenderer((ModelBase)this, 32, 0);
        this.vegeta7.func_78789_a(-0.5f, -12.0f, -6.0f, 3, 3, 3);
        this.vegeta7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.vegeta7, -0.4363323f, 0.0f, 0.0f);
        this.vegeta8 = new ModelRenderer((ModelBase)this, 32, 0);
        this.vegeta8.func_78789_a(0.0f, -14.0f, -7.0f, 2, 3, 2);
        this.vegeta8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.vegeta8, -0.5934119f, 0.0f, 0.0f);
        this.vegeta9 = new ModelRenderer((ModelBase)this, 32, 0);
        this.vegeta9.func_78789_a(-10.0f, -2.166667f, -1.5f, 3, 2, 2);
        this.vegeta9.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.vegeta9, 0.0f, 0.3490659f, 0.8901179f);
        this.vegeta10 = new ModelRenderer((ModelBase)this, 32, 0);
        this.vegeta10.func_78789_a(-1.0f, -10.0f, -6.2f, 4, 6, 4);
        this.vegeta10.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.vegeta10, -0.4363323f, 0.0f, -0.4014257f);
        this.vegeta11 = new ModelRenderer((ModelBase)this, 32, 0);
        this.vegeta11.func_78789_a(-0.5f, -12.0f, -6.0f, 5, 4, 3);
        this.vegeta11.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.vegeta11, -0.5410521f, 0.0f, -0.3665191f);
        this.vegeta12 = new ModelRenderer((ModelBase)this, 32, 0);
        this.vegeta12.func_78789_a(-0.5f, -14.0f, -6.0f, 3, 3, 3);
        this.vegeta12.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.vegeta12, -0.6108652f, 0.0f, -0.2443461f);
        this.vegeta13 = new ModelRenderer((ModelBase)this, 32, 0);
        this.vegeta13.func_78789_a(0.0f, -15.4f, -7.0f, 2, 5, 2);
        this.vegeta13.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.vegeta13, -0.6981317f, 0.0f, -0.122173f);
        this.vegeta14 = new ModelRenderer((ModelBase)this, 32, 0);
        this.vegeta14.func_78789_a(-1.5f, -9.0f, -5.5f, 3, 5, 3);
        this.vegeta14.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.vegeta14, -0.3665191f, 0.0f, 0.4363323f);
        this.vegeta15 = new ModelRenderer((ModelBase)this, 32, 0);
        this.vegeta15.func_78789_a(-0.5f, -10.0f, -6.0f, 3, 3, 3);
        this.vegeta15.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.vegeta15, -0.5410521f, 0.0f, 0.2455096f);
        this.vegeta16 = new ModelRenderer((ModelBase)this, 32, 0);
        this.vegeta16.func_78789_a(-1.0f, -12.0f, -6.0f, 3, 3, 3);
        this.vegeta16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.vegeta16, -0.5759587f, 0.0f, 0.1396263f);
        this.vegeta17 = new ModelRenderer((ModelBase)this, 32, 0);
        this.vegeta17.func_78789_a(-2.0f, -9.0f, -1.0f, 4, 5, 4);
        this.vegeta17.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.vegeta17, -0.2792527f, 0.0f, 0.0f);
        this.vegeta18 = new ModelRenderer((ModelBase)this, 32, 0);
        this.vegeta18.func_78789_a(-1.0f, -10.0f, -1.0f, 4, 5, 4);
        this.vegeta18.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.vegeta18, -0.2443461f, 0.2617994f, 0.0174533f);
        this.vegeta19 = new ModelRenderer((ModelBase)this, 32, 0);
        this.vegeta19.func_78789_a(-4.0f, -11.0f, -1.0f, 4, 6, 4);
        this.vegeta19.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.vegeta19, -0.2443461f, -0.2617994f, 0.0174533f);
        this.vegeta20 = new ModelRenderer((ModelBase)this, 32, 0);
        this.vegeta20.func_78789_a(-2.0f, -13.0f, -1.0f, 3, 5, 4);
        this.vegeta20.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.vegeta20, -0.1396263f, 0.0f, 0.0f);
        this.vegeta21 = new ModelRenderer((ModelBase)this, 32, 0);
        this.vegeta21.func_78789_a(-1.0f, -14.0f, 0.0f, 3, 5, 3);
        this.vegeta21.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.vegeta21, -0.122173f, 0.1745329f, 0.0f);
        this.vegeta22 = new ModelRenderer((ModelBase)this, 32, 0);
        this.vegeta22.func_78789_a(-2.866667f, -13.2f, -0.6666667f, 3, 4, 3);
        this.vegeta22.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.vegeta22, -0.2443461f, -0.2617994f, 0.0174533f);
        this.svegeta1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.svegeta1.func_78789_a(-1.0f, -10.0f, -6.05f, 4, 4, 4);
        this.svegeta1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.svegeta1, -0.3141593f, 0.0f, 0.0f);
        this.svegeta2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.svegeta2.func_78789_a(-8.0f, -4.5f, -1.0f, 4, 3, 3);
        this.svegeta2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.svegeta2, 0.0f, 0.1745329f, 0.5759587f);
        this.svegeta3 = new ModelRenderer((ModelBase)this, 32, 0);
        this.svegeta3.func_78789_a(-7.0f, -2.0f, 0.0f, 4, 2, 2);
        this.svegeta3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.svegeta3, 0.0f, 0.2617994f, 0.5061455f);
        this.svegeta4 = new ModelRenderer((ModelBase)this, 32, 0);
        this.svegeta4.func_78789_a(4.0f, -4.0f, -1.0f, 4, 3, 3);
        this.svegeta4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.svegeta4, 0.0f, -0.1745329f, -0.6108652f);
        this.svegeta5 = new ModelRenderer((ModelBase)this, 32, 0);
        this.svegeta5.func_78789_a(3.0f, -2.0f, 0.7f, 4, 2, 2);
        this.svegeta5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.svegeta5, 0.0f, -0.1745329f, -0.5061455f);
        this.svegeta6 = new ModelRenderer((ModelBase)this, 32, 0);
        this.svegeta6.func_78789_a(7.0f, -2.0f, -1.5f, 3, 2, 2);
        this.svegeta6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.svegeta6, 0.0f, -0.3490659f, -0.9250245f);
        this.svegeta7 = new ModelRenderer((ModelBase)this, 32, 0);
        this.svegeta7.func_78789_a(-0.5f, -12.0f, -6.0f, 3, 3, 3);
        this.svegeta7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.svegeta7, -0.4363323f, 0.0f, 0.0f);
        this.svegeta8 = new ModelRenderer((ModelBase)this, 32, 0);
        this.svegeta8.func_78789_a(0.0f, -14.0f, -7.0f, 2, 3, 2);
        this.svegeta8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.svegeta8, -0.5934119f, 0.0f, 0.0f);
        this.svegeta9 = new ModelRenderer((ModelBase)this, 32, 0);
        this.svegeta9.func_78789_a(-10.0f, -2.166667f, -1.5f, 3, 2, 2);
        this.svegeta9.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.svegeta9, 0.0f, 0.3490659f, 0.8901179f);
        this.svegeta10 = new ModelRenderer((ModelBase)this, 32, 0);
        this.svegeta10.func_78789_a(-1.0f, -10.0f, -6.2f, 4, 6, 4);
        this.svegeta10.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.svegeta10, -0.4363323f, 0.0f, -0.4014257f);
        this.svegeta11 = new ModelRenderer((ModelBase)this, 32, 0);
        this.svegeta11.func_78789_a(-0.5f, -12.0f, -6.0f, 5, 4, 3);
        this.svegeta11.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.svegeta11, -0.5410521f, 0.0f, -0.3665191f);
        this.svegeta12 = new ModelRenderer((ModelBase)this, 32, 0);
        this.svegeta12.func_78789_a(-0.5f, -14.0f, -6.0f, 3, 3, 3);
        this.svegeta12.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.svegeta12, -0.6108652f, 0.0f, -0.2443461f);
        this.svegeta13 = new ModelRenderer((ModelBase)this, 32, 0);
        this.svegeta13.func_78789_a(0.0f, -15.4f, -7.0f, 2, 5, 2);
        this.svegeta13.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.svegeta13, -0.6981317f, 0.0f, -0.122173f);
        this.svegeta14 = new ModelRenderer((ModelBase)this, 32, 0);
        this.svegeta14.func_78789_a(-1.5f, -9.0f, -5.5f, 3, 5, 3);
        this.svegeta14.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.svegeta14, -0.3665191f, 0.0f, 0.4363323f);
        this.svegeta15 = new ModelRenderer((ModelBase)this, 32, 0);
        this.svegeta15.func_78789_a(-0.5f, -10.0f, -6.0f, 3, 3, 3);
        this.svegeta15.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.svegeta15, -0.5410521f, 0.0f, 0.2455096f);
        this.svegeta16 = new ModelRenderer((ModelBase)this, 32, 0);
        this.svegeta16.func_78789_a(-1.0f, -12.0f, -6.0f, 3, 3, 3);
        this.svegeta16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.svegeta16, -0.5759587f, 0.0f, 0.1396263f);
        this.svegeta17 = new ModelRenderer((ModelBase)this, 32, 0);
        this.svegeta17.func_78789_a(-2.0f, -9.0f, -1.0f, 4, 5, 4);
        this.svegeta17.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.svegeta17, -0.2792527f, 0.0f, 0.0f);
        this.svegeta18 = new ModelRenderer((ModelBase)this, 32, 0);
        this.svegeta18.func_78789_a(-1.0f, -10.0f, -1.0f, 4, 5, 4);
        this.svegeta18.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.svegeta18, -0.2443461f, 0.2617994f, 0.0174533f);
        this.svegeta19 = new ModelRenderer((ModelBase)this, 32, 0);
        this.svegeta19.func_78789_a(-4.0f, -11.0f, -1.0f, 4, 6, 4);
        this.svegeta19.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.svegeta19, -0.2443461f, -0.2617994f, 0.0174533f);
        this.svegeta20 = new ModelRenderer((ModelBase)this, 32, 0);
        this.svegeta20.func_78789_a(-2.0f, -13.0f, -1.0f, 3, 5, 4);
        this.svegeta20.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.svegeta20, -0.1396263f, 0.0f, 0.0f);
        this.svegeta21 = new ModelRenderer((ModelBase)this, 32, 0);
        this.svegeta21.func_78789_a(-1.0f, -14.0f, 0.0f, 3, 5, 3);
        this.svegeta21.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.svegeta21, -0.122173f, 0.1745329f, 0.0f);
        this.svegeta22 = new ModelRenderer((ModelBase)this, 32, 0);
        this.svegeta22.func_78789_a(-2.866667f, -13.2f, -0.6666667f, 3, 4, 3);
        this.svegeta22.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.svegeta22, -0.2443461f, -0.2617994f, 0.0174533f);
        this.ssvegeta1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssvegeta1.func_78789_a(-1.0f, -10.0f, -6.05f, 4, 4, 4);
        this.ssvegeta1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssvegeta1, -0.3141593f, 0.0f, 0.0f);
        this.ssvegeta2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssvegeta2.func_78789_a(-8.8f, -4.5f, -1.0f, 4, 3, 3);
        this.ssvegeta2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssvegeta2, 0.0f, 0.1745329f, 0.6108652f);
        this.ssvegeta3 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssvegeta3.func_78789_a(-7.3f, -2.0f, 0.0f, 4, 2, 2);
        this.ssvegeta3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssvegeta3, 0.0f, 0.2617994f, 0.5410521f);
        this.ssvegeta4 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssvegeta4.func_78789_a(4.8f, -4.0f, -1.0f, 4, 3, 3);
        this.ssvegeta4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssvegeta4, 0.0f, -0.1745329f, -0.6806784f);
        this.ssvegeta5 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssvegeta5.func_78789_a(3.8f, -2.0f, 0.7f, 4, 2, 2);
        this.ssvegeta5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssvegeta5, 0.0f, -0.1745329f, -0.6108652f);
        this.ssvegeta6 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssvegeta6.func_78789_a(7.8f, -2.0f, -1.5f, 3, 2, 2);
        this.ssvegeta6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssvegeta6, 0.0f, -0.3490659f, -0.9599311f);
        this.ssvegeta7 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssvegeta7.func_78789_a(-0.5f, -12.0f, -6.0f, 3, 3, 3);
        this.ssvegeta7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssvegeta7, -0.4014257f, 0.0f, 0.0f);
        this.ssvegeta8 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssvegeta8.func_78789_a(0.0f, -14.0f, -7.0f, 2, 3, 2);
        this.ssvegeta8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssvegeta8, -0.5410521f, 0.0f, 0.0f);
        this.ssvegeta9 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssvegeta9.func_78789_a(-10.8f, -2.166667f, -1.5f, 3, 2, 2);
        this.ssvegeta9.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssvegeta9, 0.0f, 0.3490659f, 0.9250245f);
        this.ssvegeta10 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssvegeta10.func_78789_a(-1.0f, -10.3f, -6.3f, 4, 6, 4);
        this.ssvegeta10.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssvegeta10, -0.4363323f, 0.0f, -0.3665191f);
        this.ssvegeta11 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssvegeta11.func_78789_a(-0.5f, -12.3f, -6.0f, 5, 4, 3);
        this.ssvegeta11.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssvegeta11, -0.5410521f, 0.0f, -0.3316126f);
        this.ssvegeta12 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssvegeta12.func_78789_a(-0.5f, -14.5f, -6.0f, 3, 3, 3);
        this.ssvegeta12.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssvegeta12, -0.5934119f, 0.0f, -0.2268928f);
        this.ssvegeta13 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssvegeta13.func_78789_a(0.0f, -15.4f, -7.0f, 2, 5, 2);
        this.ssvegeta13.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssvegeta13, -0.6806784f, 0.0f, -0.0698132f);
        this.ssvegeta14 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssvegeta14.func_78789_a(-1.3f, -9.3f, -5.5f, 3, 5, 3);
        this.ssvegeta14.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssvegeta14, -0.3665191f, 0.0f, 0.4014257f);
        this.ssvegeta15 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssvegeta15.func_78789_a(-0.5f, -10.8f, -6.0f, 3, 3, 3);
        this.ssvegeta15.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssvegeta15, -0.5410521f, 0.0f, 0.2617994f);
        this.ssvegeta16 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssvegeta16.func_78789_a(-1.0f, -12.8f, -6.0f, 3, 3, 3);
        this.ssvegeta16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssvegeta16, -0.5759587f, 0.0f, 0.1745329f);
        this.ssvegeta17 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssvegeta17.func_78789_a(-2.0f, -9.0f, -1.0f, 4, 5, 4);
        this.ssvegeta17.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssvegeta17, -0.2792527f, 0.0f, 0.0f);
        this.ssvegeta18 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssvegeta18.func_78789_a(-1.0f, -10.0f, -1.0f, 4, 5, 4);
        this.ssvegeta18.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssvegeta18, -0.2443461f, 0.2617994f, 0.0174533f);
        this.ssvegeta19 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssvegeta19.func_78789_a(-4.0f, -11.0f, -1.0f, 4, 6, 4);
        this.ssvegeta19.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssvegeta19, -0.2443461f, -0.2617994f, 0.0174533f);
        this.ssvegeta20 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssvegeta20.func_78789_a(-2.0f, -13.0f, -1.0f, 3, 5, 4);
        this.ssvegeta20.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssvegeta20, -0.1396263f, 0.0f, 0.0f);
        this.ssvegeta21 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssvegeta21.func_78789_a(-0.6f, -14.5f, 0.0f, 3, 5, 3);
        this.ssvegeta21.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssvegeta21, -0.122173f, 0.1745329f, 0.0f);
        this.ssvegeta22 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssvegeta22.func_78789_a(-2.866667f, -13.2f, -0.6666667f, 3, 4, 3);
        this.ssvegeta22.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssvegeta22, -0.2443461f, -0.2617994f, 0.0174533f);
        this.gohan1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gohan1.func_78789_a(-1.0f, -10.0f, -5.066667f, 4, 4, 4);
        this.gohan1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gohan1, -0.1745329f, 0.0f, 0.0f);
        this.gohan7 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gohan7.func_78789_a(-0.5f, -11.0f, -6.0f, 3, 2, 3);
        this.gohan7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gohan7, -0.3665191f, 0.0f, 0.0f);
        this.gohan8 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gohan8.func_78789_a(0.0f, -11.0f, -7.0f, 2, 2, 2);
        this.gohan8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gohan8, -0.5585054f, 0.0f, 0.0f);
        this.gohan10 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gohan10.func_78789_a(-1.533333f, -10.3f, -5.466667f, 4, 5, 4);
        this.gohan10.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gohan10, -0.2617994f, 0.0f, -0.3665191f);
        this.gohan11 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gohan11.func_78789_a(-0.5f, -11.3f, -6.0f, 5, 4, 4);
        this.gohan11.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gohan11, -0.418879f, 0.0f, -0.3316126f);
        this.gohan12 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gohan12.func_78789_a(-0.5f, -12.5f, -6.0f, 3, 3, 3);
        this.gohan12.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gohan12, -0.5235988f, 0.0f, -0.2268928f);
        this.gohan13 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gohan13.func_78789_a(0.0f, -12.66667f, -7.0f, 2, 4, 2);
        this.gohan13.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gohan13, -0.6283185f, 0.0f, -0.0698132f);
        this.gohan14 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gohan14.func_78789_a(-1.3f, -9.3f, -5.0f, 3, 5, 3);
        this.gohan14.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gohan14, -0.2268928f, 0.0f, 0.4014257f);
        this.gohan15 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gohan15.func_78789_a(-0.8333333f, -10.8f, -6.0f, 3, 4, 4);
        this.gohan15.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gohan15, -0.4537856f, 0.0f, 0.2617994f);
        this.gohan16 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gohan16.func_78789_a(-1.0f, -12.46667f, -6.0f, 3, 4, 3);
        this.gohan16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gohan16, -0.5410521f, 0.0f, 0.1745329f);
        this.gohan17 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gohan17.func_78789_a(-2.0f, -9.0f, -1.0f, 4, 5, 4);
        this.gohan17.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gohan17, -0.2792527f, 0.0f, 0.0f);
        this.gohan18 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gohan18.func_78789_a(-0.8f, -10.0f, -1.0f, 4, 5, 4);
        this.gohan18.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gohan18, -0.2443461f, 0.2617994f, 0.0174533f);
        this.gohan19 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gohan19.func_78789_a(-3.266667f, -10.0f, -1.0f, 4, 4, 4);
        this.gohan19.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gohan19, -0.2443461f, -0.2617994f, 0.0174533f);
        this.gohan20 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gohan20.func_78789_a(-2.0f, -12.0f, -1.0f, 3, 4, 4);
        this.gohan20.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gohan20, -0.1396263f, 0.0f, 0.0f);
        this.gohan21 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gohan21.func_78789_a(-0.6f, -11.5f, 0.0f, 3, 2, 3);
        this.gohan21.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gohan21, -0.122173f, 0.1745329f, 0.0f);
        this.gohan22 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gohan22.func_78789_a(-2.866667f, -11.53333f, -0.6666667f, 3, 4, 3);
        this.gohan22.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gohan22, -0.2443461f, -0.2617994f, 0.0174533f);
        this.gohan26 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gohan26.func_78789_a(4.433333f, -6.5f, -5.266667f, 2, 3, 3);
        this.gohan26.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gohan26, 0.0f, 0.0f, -0.5934119f);
        this.sgohan1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgohan1.func_78789_a(-1.0f, -10.0f, -5.066667f, 4, 4, 4);
        this.sgohan1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sgohan1, -0.1745329f, 0.0f, 0.0f);
        this.sgohan7 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgohan7.func_78789_a(-0.5f, -11.0f, -6.0f, 3, 2, 3);
        this.sgohan7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sgohan7, -0.3665191f, 0.0f, 0.0f);
        this.sgohan8 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgohan8.func_78789_a(0.0f, -11.0f, -7.0f, 2, 2, 2);
        this.sgohan8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sgohan8, -0.5585054f, 0.0f, 0.0f);
        this.sgohan10 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgohan10.func_78789_a(-1.533333f, -10.3f, -5.466667f, 4, 5, 4);
        this.sgohan10.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sgohan10, -0.2617994f, 0.0f, -0.3665191f);
        this.sgohan11 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgohan11.func_78789_a(-0.5f, -11.3f, -6.0f, 5, 4, 4);
        this.sgohan11.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sgohan11, -0.418879f, 0.0f, -0.3316126f);
        this.sgohan12 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgohan12.func_78789_a(-0.5f, -12.5f, -6.0f, 3, 3, 3);
        this.sgohan12.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sgohan12, -0.5235988f, 0.0f, -0.2268928f);
        this.sgohan13 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgohan13.func_78789_a(0.0f, -12.66667f, -7.0f, 2, 4, 2);
        this.sgohan13.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sgohan13, -0.6283185f, 0.0f, -0.0698132f);
        this.sgohan14 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgohan14.func_78789_a(-1.3f, -9.3f, -5.0f, 3, 5, 3);
        this.sgohan14.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sgohan14, -0.2268928f, 0.0f, 0.4014257f);
        this.sgohan15 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgohan15.func_78789_a(-0.8333333f, -10.8f, -6.0f, 3, 4, 4);
        this.sgohan15.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sgohan15, -0.4537856f, 0.0f, 0.2617994f);
        this.sgohan16 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgohan16.func_78789_a(-1.0f, -12.46667f, -6.0f, 3, 4, 3);
        this.sgohan16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sgohan16, -0.5410521f, 0.0f, 0.1745329f);
        this.sgohan17 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgohan17.func_78789_a(-2.0f, -9.0f, -1.0f, 4, 5, 4);
        this.sgohan17.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sgohan17, -0.2792527f, 0.0f, 0.0f);
        this.sgohan18 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgohan18.func_78789_a(-0.8f, -10.0f, -1.0f, 4, 5, 4);
        this.sgohan18.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sgohan18, -0.2443461f, 0.2617994f, 0.0174533f);
        this.sgohan19 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgohan19.func_78789_a(-3.266667f, -10.0f, -1.0f, 4, 4, 4);
        this.sgohan19.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sgohan19, -0.2443461f, -0.2617994f, 0.0174533f);
        this.sgohan20 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgohan20.func_78789_a(-2.0f, -12.0f, -1.0f, 3, 4, 4);
        this.sgohan20.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sgohan20, -0.1396263f, 0.0f, 0.0f);
        this.sgohan21 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgohan21.func_78789_a(-0.6f, -11.5f, 0.0f, 3, 2, 3);
        this.sgohan21.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sgohan21, -0.122173f, 0.1745329f, 0.0f);
        this.sgohan22 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgohan22.func_78789_a(-2.866667f, -11.53333f, -0.6666667f, 3, 4, 3);
        this.sgohan22.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sgohan22, -0.2443461f, -0.2617994f, 0.0174533f);
        this.sgohan26 = new ModelRenderer((ModelBase)this, 32, 0);
        this.sgohan26.func_78789_a(4.433333f, -6.5f, -5.266667f, 2, 3, 3);
        this.sgohan26.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.sgohan26, 0.0f, 0.0f, -0.5934119f);
        this.ssgohan1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgohan1.func_78789_a(-1.0f, -10.5f, -5.066667f, 4, 4, 4);
        this.ssgohan1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgohan1, -0.1745329f, 0.0f, 0.0f);
        this.ssgohan7 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgohan7.func_78789_a(-0.5f, -11.5f, -6.0f, 3, 3, 3);
        this.ssgohan7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgohan7, -0.3665191f, 0.0f, 0.0f);
        this.ssgohan8 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgohan8.func_78789_a(0.0f, -12.0f, -7.0f, 2, 3, 2);
        this.ssgohan8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgohan8, -0.5585054f, 0.0f, 0.0f);
        this.ssgohan10 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgohan10.func_78789_a(-1.533333f, -10.8f, -5.466667f, 4, 5, 4);
        this.ssgohan10.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgohan10, -0.2617994f, 0.0f, -0.3665191f);
        this.ssgohan11 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgohan11.func_78789_a(-0.5f, -11.8f, -6.0f, 5, 4, 4);
        this.ssgohan11.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgohan11, -0.418879f, 0.0f, -0.3316126f);
        this.ssgohan12 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgohan12.func_78789_a(-0.5f, -13.0f, -6.0f, 3, 3, 3);
        this.ssgohan12.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgohan12, -0.5235988f, 0.0f, -0.2268928f);
        this.ssgohan13 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgohan13.func_78789_a(0.0f, -13.2f, -7.0f, 2, 4, 2);
        this.ssgohan13.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgohan13, -0.6283185f, 0.0f, -0.0698132f);
        this.ssgohan14 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgohan14.func_78789_a(-1.3f, -9.8f, -5.0f, 3, 5, 3);
        this.ssgohan14.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgohan14, -0.2268928f, 0.0f, 0.4014257f);
        this.ssgohan15 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgohan15.func_78789_a(-0.8333333f, -11.3f, -6.0f, 3, 4, 4);
        this.ssgohan15.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgohan15, -0.4537856f, 0.0f, 0.2617994f);
        this.ssgohan16 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgohan16.func_78789_a(-1.0f, -13.0f, -6.0f, 3, 4, 3);
        this.ssgohan16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgohan16, -0.5410521f, 0.0f, 0.1745329f);
        this.ssgohan17 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgohan17.func_78789_a(-2.0f, -9.0f, -1.0f, 4, 5, 4);
        this.ssgohan17.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgohan17, -0.2792527f, 0.0f, 0.0f);
        this.ssgohan18 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgohan18.func_78789_a(-0.8f, -10.0f, -1.0f, 4, 5, 4);
        this.ssgohan18.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgohan18, -0.2443461f, 0.2617994f, 0.0174533f);
        this.ssgohan19 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgohan19.func_78789_a(-3.266667f, -10.0f, -1.0f, 4, 4, 4);
        this.ssgohan19.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgohan19, -0.2443461f, -0.2617994f, 0.0174533f);
        this.ssgohan20 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgohan20.func_78789_a(-2.0f, -12.5f, -1.0f, 3, 4, 4);
        this.ssgohan20.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgohan20, -0.1396263f, 0.0f, 0.0f);
        this.ssgohan21 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgohan21.func_78789_a(-0.6f, -12.0f, 0.0f, 3, 3, 3);
        this.ssgohan21.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgohan21, -0.122173f, 0.1745329f, 0.0f);
        this.ssgohan22 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgohan22.func_78789_a(-2.866667f, -11.53333f, -0.6666667f, 3, 4, 3);
        this.ssgohan22.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgohan22, -0.2443461f, -0.2617994f, 0.0174533f);
        this.ssgohan26 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssgohan26.func_78789_a(4.0f, -6.5f, -5.266667f, 2, 4, 3);
        this.ssgohan26.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssgohan26, 0.0f, 0.0f, -0.5235988f);
        this.gokuni1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gokuni1.func_78789_a(-1.0f, -11.0f, -2.0f, 4, 4, 4);
        this.gokuni1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gokuni1, 0.2268928f, 0.0f, -0.4363323f);
        this.gokuni2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gokuni2.func_78789_a(-8.0f, -5.1f, -1.0f, 4, 2, 2);
        this.gokuni2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gokuni2, 0.0f, -0.2617994f, 0.1745329f);
        this.gokuni3 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gokuni3.func_78789_a(-6.0f, -4.6f, -1.0f, 4, 2, 2);
        this.gokuni3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gokuni3, 0.0f, 0.0f, -0.1396263f);
        this.gokuni4 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gokuni4.func_78789_a(3.0f, -4.0f, 0.0f, 4, 3, 3);
        this.gokuni4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gokuni4, 0.0f, 0.0f, -0.3490659f);
        this.gokuni5 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gokuni5.func_78789_a(3.0f, -3.8f, 0.7f, 3, 2, 2);
        this.gokuni5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gokuni5, 0.0f, 0.0349066f, 0.1815142f);
        this.gokuni6 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gokuni6.func_78789_a(6.0f, -4.3f, 0.5f, 3, 2, 2);
        this.gokuni6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gokuni6, 0.0f, 0.0f, -0.2617994f);
        this.gokuni7 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gokuni7.func_78789_a(1.0f, -12.0f, 1.266667f, 3, 3, 3);
        this.gokuni7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gokuni7, 0.5235988f, 0.0f, -0.6108652f);
        this.gokuni8 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gokuni8.func_78789_a(3.266667f, -13.0f, 4.0f, 2, 3, 2);
        this.gokuni8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gokuni8, 0.7853982f, 0.0f, -0.7853982f);
        this.gokuni9 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gokuni9.func_78789_a(-8.733334f, -5.7f, 0.1f, 2, 1, 1);
        this.gokuni9.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gokuni9, 0.0f, -0.3490659f, 0.0174533f);
        this.gokuni10 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gokuni10.func_78789_a(-11.0f, -4.133333f, 0.0f, 5, 2, 2);
        this.gokuni10.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gokuni10, 0.0f, -0.6981317f, 0.4921828f);
        this.gokuni11 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gokuni11.func_78789_a(1.066667f, -9.866667f, 1.6f, 1, 3, 1);
        this.gokuni11.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gokuni11, 0.1745329f, 0.0f, -0.1745329f);
        this.gokuni12 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gokuni12.func_78789_a(5.0f, -4.333333f, 1.7f, 3, 1, 1);
        this.gokuni12.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gokuni12, 0.0f, 0.1745329f, -0.4991642f);
        this.ght1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ght1.func_78789_a(-0.4666667f, -10.0f, -1.533333f, 3, 3, 3);
        this.ght1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ght1, 0.2443461f, 0.0f, -0.4363323f);
        this.ght2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ght2.func_78789_a(-8.533334f, -6.8f, 0.6f, 5, 2, 2);
        this.ght2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ght2, 0.0f, -0.8726646f, 0.2094395f);
        this.ght3 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ght3.func_78789_a(-6.0f, -6.6f, 0.0f, 4, 2, 2);
        this.ght3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ght3, 0.0f, -0.6867716f, -0.1745329f);
        this.ght4 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ght4.func_78789_a(-0.01f, -6.0001f, 2.0f, 4, 5, 4);
        this.ght4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ght4, 0.296706f, 0.0f, 0.0f);
        this.ght5 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ght5.func_78789_a(4.6f, -7.666667f, 2.7f, 1, 2, 1);
        this.ght5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ght5, 0.0f, 0.1745329f, -0.3490659f);
        this.ght6 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ght6.func_78789_a(0.5333334f, -8.566667f, 2.1f, 2, 1, 1);
        this.ght6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ght6, 0.0f, -0.0349066f, 0.3490659f);
        this.ght7 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ght7.func_78789_a(1.2f, -11.0f, 0.1333333f, 2, 3, 2);
        this.ght7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ght7, 0.418879f, 0.0f, -0.5934119f);
        this.ght8 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ght8.func_78789_a(3.0f, -11.8f, 2.2f, 1, 3, 1);
        this.ght8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ght8, 0.6108652f, 0.0f, -0.7679449f);
        this.ght9 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ght9.func_78789_a(-8.066667f, -7.6f, 1.833333f, 3, 1, 1);
        this.ght9.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ght9, 0.0f, -1.047198f, 0.0f);
        this.ght11 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ght11.func_78789_a(0.4f, -10.0f, 0.06666667f, 1, 4, 1);
        this.ght11.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ght11, 0.0523599f, 0.0f, -0.1745329f);
        this.ght14 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ght14.func_78789_a(-3.99f, -6.0001f, 2.001f, 4, 5, 4);
        this.ght14.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ght14, 0.296706f, 0.0f, 0.0f);
        this.ght16 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ght16.func_78789_a(7.0f, -5.1f, -0.3666667f, 2, 1, 1);
        this.ght16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ght16, 0.0f, -0.7853982f, -0.4363323f);
        this.goten2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goten2.func_78789_a(-8.533334f, -6.8f, 0.6f, 5, 2, 2);
        this.goten2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.goten2, 0.0f, -0.8726646f, 0.3141593f);
        this.goten3 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goten3.func_78789_a(-6.0f, -6.933333f, 0.0f, 4, 2, 2);
        this.goten3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.goten3, 0.0f, -0.6867716f, -0.1745329f);
        this.goten4 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goten4.func_78789_a(-0.01f, -6.0001f, 1.1f, 4, 5, 4);
        this.goten4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.goten4, 0.1745329f, 0.0f, 0.0f);
        this.goten5 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goten5.func_78789_a(4.6f, -7.666667f, 2.7f, 1, 2, 1);
        this.goten5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.goten5, 0.0f, 0.1745329f, -0.3490659f);
        this.goten6 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goten6.func_78789_a(0.5333334f, -8.566667f, 2.1f, 2, 1, 1);
        this.goten6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.goten6, 0.0f, -0.0349066f, 0.3490659f);
        this.goten9 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goten9.func_78789_a(-9.066667f, -7.6f, 1.833333f, 3, 1, 1);
        this.goten9.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.goten9, 0.0f, -1.047198f, 0.122173f);
        this.goten14 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goten14.func_78789_a(-3.99f, -6.0001f, 1.1f, 4, 5, 4);
        this.goten14.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.goten14, 0.1745329f, 0.0f, 0.0f);
        this.goten16 = new ModelRenderer((ModelBase)this, 32, 0);
        this.goten16.func_78789_a(7.0f, -5.1f, -0.3666667f, 2, 1, 1);
        this.goten16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.goten16, 0.0f, -0.7853982f, -0.4363323f);
        this.gotent1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gotent1.func_78789_a(-0.4666667f, -11.33333f, -1.533333f, 2, 3, 2);
        this.gotent1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gotent1, 0.296706f, 0.0f, -0.2792527f);
        this.gotent2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gotent2.func_78789_a(-10.53333f, -0.8f, -3.4f, 5, 2, 2);
        this.gotent2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gotent2, 0.0f, 0.0f, 1.239184f);
        this.gotent3 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gotent3.func_78789_a(-3.133333f, -9.133333f, 3.8f, 4, 4, 2);
        this.gotent3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gotent3, 0.8726646f, 0.2094395f, 0.0f);
        this.gotent5 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gotent5.func_78789_a(4.6f, -7.666667f, 2.7f, 1, 2, 1);
        this.gotent5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gotent5, 0.0f, 0.1745329f, -0.3490659f);
        this.gotent6 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gotent6.func_78789_a(0.5333334f, -8.566667f, 2.1f, 2, 1, 1);
        this.gotent6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gotent6, 0.0f, -0.0349066f, 0.3490659f);
        this.gotent7 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gotent7.func_78789_a(1.2f, -11.0f, 0.1333333f, 2, 4, 2);
        this.gotent7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gotent7, 0.5235988f, 0.0f, -0.2617994f);
        this.gotent8 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gotent8.func_78789_a(3.0f, -9.8f, 2.2f, 2, 3, 2);
        this.gotent8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gotent8, 0.8203047f, 0.0f, -0.9250245f);
        this.gotent9 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gotent9.func_78789_a(-9.066667f, -4.6f, 0.5f, 5, 2, 2);
        this.gotent9.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gotent9, 0.0f, -0.8901179f, 0.4712389f);
        this.gotent11 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gotent11.func_78789_a(0.4f, -10.0f, 0.06666667f, 2, 5, 2);
        this.gotent11.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gotent11, 0.3665191f, 0.0f, -0.5934119f);
        this.gotent16 = new ModelRenderer((ModelBase)this, 32, 0);
        this.gotent16.func_78789_a(7.0f, -5.1f, -0.3666667f, 2, 1, 1);
        this.gotent16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.gotent16, 0.0f, -0.7853982f, -0.4363323f);
        this.hairc71 = new ModelRenderer((ModelBase)this, 32, 15);
        this.hairc71.func_78789_a(2.0f, -8.0f, -4.5f, 4, 8, 9);
        this.hairc71.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.hairc71, 0.0f, 0.0174533f, -0.2617994f);
        this.hairc72 = new ModelRenderer((ModelBase)this, 36, 0);
        this.hairc72.func_78789_a(-6.0f, -8.0f, -4.5f, 4, 8, 9);
        this.hairc72.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.hairc72, 0.0f, 0.0f, 0.2617994f);
        this.hairc81 = new ModelRenderer((ModelBase)this, 32, 15);
        this.hairc81.func_78789_a(2.133333f, -8.066667f, -4.5f, 3, 8, 9);
        this.hairc81.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.hairc81, 0.0f, 0.0174533f, -0.0872665f);
        this.hairc82 = new ModelRenderer((ModelBase)this, 34, 0);
        this.hairc82.func_78789_a(-6.0f, -8.0f, -4.5f, 4, 8, 9);
        this.hairc82.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.hairc82, 0.0f, 0.0f, 0.2617994f);
        this.hairc83 = new ModelRenderer((ModelBase)this, 0, 22);
        this.hairc83.func_78789_a(-5.0f, -8.466666f, -4.5f, 6, 1, 9);
        this.hairc83.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.hairc83, 0.0f, 0.0174533f, 0.1745329f);
        this.radlike1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike1.func_78789_a(-1.0f, -10.0f, -6.05f, 4, 4, 4);
        this.radlike1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike1, -0.3141593f, 0.0f, 0.0f);
        this.radlike2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike2.func_78789_a(-6.8f, -6.5f, -1.0f, 4, 3, 3);
        this.radlike2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike2, 0.0f, 0.1745329f, -0.1396263f);
        this.radlike3 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike3.func_78789_a(-6.3f, -4.0f, 0.0f, 3, 2, 2);
        this.radlike3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike3, 0.0f, 0.2617994f, -0.1919862f);
        this.radlike4 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike4.func_78789_a(2.8f, -7.0f, -1.0f, 4, 3, 3);
        this.radlike4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike4, 0.0f, -0.1745329f, 0.1919862f);
        this.radlike5 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike5.func_78789_a(2.8f, -4.0f, 0.7f, 3, 2, 2);
        this.radlike5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike5, 0.0f, -0.1745329f, 0.1570796f);
        this.radlike7 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike7.func_78789_a(-1.5f, -11.0f, -8.0f, 3, 3, 3);
        this.radlike7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike7, -0.5934119f, 0.0f, 0.1047198f);
        this.radlike8 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike8.func_78789_a(-5.0f, -12.0f, -8.0f, 2, 3, 2);
        this.radlike8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike8, -0.6981317f, 0.0f, 0.4363323f);
        this.radlike10 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike10.func_78789_a(-1.0f, -10.3f, -6.3f, 4, 6, 4);
        this.radlike10.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike10, -0.4363323f, 0.0f, -0.3665191f);
        this.radlike11 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike11.func_78789_a(1.0f, -11.3f, -6.0f, 5, 4, 3);
        this.radlike11.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike11, -0.5410521f, 0.0f, -0.4886922f);
        this.radlike12 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike12.func_78789_a(3.5f, -11.5f, -8.0f, 3, 3, 3);
        this.radlike12.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike12, -0.8552113f, 0.0f, -0.6108652f);
        this.radlike13 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike13.func_78789_a(6.0f, -12.4f, -8.0f, 2, 3, 2);
        this.radlike13.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike13, -0.9948377f, 0.0f, -0.7679449f);
        this.radlike14 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike14.func_78789_a(-1.3f, -9.3f, -5.5f, 3, 5, 3);
        this.radlike14.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike14, -0.3665191f, 0.0f, 0.4014257f);
        this.radlike15 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike15.func_78789_a(-5.5f, -9.8f, -6.0f, 3, 3, 3);
        this.radlike15.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike15, -0.5410521f, 0.0f, 0.837758f);
        this.radlike16 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike16.func_78789_a(-9.0f, -8.533334f, -6.0f, 2, 3, 2);
        this.radlike16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike16, -0.837758f, 0.0f, 1.27409f);
        this.radlike17 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike17.func_78789_a(-2.0f, -2.0f, 4.0f, 4, 5, 4);
        this.radlike17.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike17, 0.4886922f, 0.0f, 0.0f);
        this.radlike18 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike18.func_78789_a(-1.0f, -5.0f, 5.0f, 4, 5, 4);
        this.radlike18.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike18, 0.5061455f, 0.2617994f, 0.0174533f);
        this.radlike19 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike19.func_78789_a(-4.0f, -6.0f, 5.0f, 4, 6, 4);
        this.radlike19.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike19, 0.5235988f, -0.2617994f, 0.0f);
        this.radlike20 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike20.func_78789_a(-2.4f, -5.2f, 7.0f, 4, 5, 4);
        this.radlike20.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike20, 0.6981317f, 0.0f, 0.0f);
        this.radlike21 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike21.func_78789_a(0.1333333f, -6.5f, 7.533333f, 3, 5, 3);
        this.radlike21.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike21, 0.7679449f, 0.1745329f, 0.0f);
        this.radlike22 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike22.func_78789_a(-2.866667f, -7.2f, 7.333333f, 3, 4, 3);
        this.radlike22.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike22, 0.5934119f, -0.2617994f, 0.0f);
        this.radlike23 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike23.func_78789_a(1.0f, -9.0f, -4.05f, 3, 4, 4);
        this.radlike23.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike23, -0.3141593f, 0.0f, -0.8726646f);
        this.radlike24 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike24.func_78789_a(3.533333f, -10.0f, -4.716667f, 3, 4, 3);
        this.radlike24.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike24, -0.5585054f, 0.0f, -1.082104f);
        this.radlike25 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike25.func_78789_a(3.533333f, -12.0f, -4.716667f, 2, 4, 2);
        this.radlike25.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike25, -0.5934119f, 0.0f, -0.8203047f);
        this.radlike26 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike26.func_78789_a(3.533333f, -9.666667f, -3.116667f, 3, 4, 3);
        this.radlike26.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike26, -0.5585054f, 0.0f, -1.396263f);
        this.radlike27 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike27.func_78789_a(-4.5f, -6.8f, -5.0f, 3, 4, 3);
        this.radlike27.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike27, -0.5410521f, 0.0f, 1.047198f);
        this.radlike28 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike28.func_78789_a(-6.8f, -7.533333f, -5.0f, 3, 4, 3);
        this.radlike28.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike28, -0.837758f, 0.0f, 1.308997f);
        this.radlike29 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike29.func_78789_a(6.0f, -10.2f, -5.0f, 2, 3, 2);
        this.radlike29.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike29, -0.7679449f, 0.0f, -1.291544f);
        this.radlike30 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike30.func_78789_a(-2.433333f, -10.6f, -7.666667f, 3, 3, 3);
        this.radlike30.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike30, -0.7330383f, 0.0f, 0.3839724f);
        this.radlike31 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike31.func_78789_a(-5.466667f, -11.0f, -8.333333f, 2, 3, 2);
        this.radlike31.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike31, -0.9424778f, 0.0f, 0.6806784f);
        this.radlike32 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlike32.func_78789_a(-1.4f, -14.0f, -3.0f, 3, 4, 3);
        this.radlike32.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlike32, -0.4363323f, 0.0f, -0.0349066f);
        this.radlik6 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlik6.func_78789_a(-6.8f, -1.733333f, 3.2f, 3, 6, 3);
        this.radlik6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlik6, 0.4363323f, 0.0f, 0.3490659f);
        this.radlik7 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlik7.func_78789_a(4.0f, -3.066667f, 2.6f, 3, 6, 3);
        this.radlik7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlik7, 0.4363323f, 0.0f, -0.3490659f);
        this.radlik15 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlik15.func_78789_a(-2.266667f, -3.2f, 5.4f, 4, 4, 4);
        this.radlik15.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlik15, 0.4363323f, 0.0f, 0.0f);
        this.radlik1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlik1.func_78789_a(-4.466667f, 6.2f, 4.0f, 3, 3, 2);
        this.radlik1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlik1, 0.0872665f, 0.0f, 0.0698132f);
        this.radlik2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlik2.func_78789_a(2.533333f, 4.2f, 3.0f, 3, 3, 3);
        this.radlik2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlik2, 0.1396263f, 0.0f, -0.0872665f);
        this.radlik3 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlik3.func_78789_a(-5.466667f, 4.2f, 3.0f, 3, 3, 3);
        this.radlik3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlik3, 0.1396263f, 0.0f, 0.0872665f);
        this.radlik4 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlik4.func_78789_a(-6.133333f, 0.7333333f, 3.0f, 3, 5, 3);
        this.radlik4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlik4, 0.2268928f, 0.0f, 0.2094395f);
        this.radlik5 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlik5.func_78789_a(3.266667f, 0.7333333f, 3.0f, 3, 5, 3);
        this.radlik5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlik5, 0.2268928f, 0.0f, -0.2094395f);
        this.radlik8 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlik8.func_78789_a(-1.466667f, 6.0f, 4.0f, 3, 5, 4);
        this.radlik8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlik8, 0.0872665f, -0.2617994f, 0.0f);
        this.radlik9 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlik9.func_78789_a(-2.466667f, 2.0f, 4.0f, 4, 5, 4);
        this.radlik9.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlik9, 0.1570796f, -0.2617994f, 0.0f);
        this.radlik10 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlik10.func_78789_a(-2.0f, 7.266667f, 4.0f, 4, 4, 4);
        this.radlik10.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlik10, 0.0698132f, 0.2617994f, 0.0f);
        this.radlik11 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlik11.func_78789_a(-1.0f, 4.266667f, 4.0f, 4, 4, 4);
        this.radlik11.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlik11, 0.1047198f, 0.2617994f, 0.0f);
        this.radlik12 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlik12.func_78789_a(-0.9f, 1.266667f, 4.0f, 4, 4, 4);
        this.radlik12.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlik12, 0.1745329f, 0.2617994f, 0.0f);
        this.radlik13 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlik13.func_78789_a(-1.933333f, 5.0f, 4.0f, 4, 5, 4);
        this.radlik13.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlik13, 0.1745329f, 0.0f, 0.0f);
        this.radlik14 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlik14.func_78789_a(-1.4f, 8.0f, 5.6f, 3, 5, 3);
        this.radlik14.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlik14, 0.0872665f, 0.0f, 0.0f);
        this.radlik16 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlik16.func_78789_a(-2.533333f, -2.0f, 3.333333f, 4, 6, 4);
        this.radlik16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlik16, 0.3490659f, -0.2617994f, 0.0f);
        this.radlik17 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlik17.func_78789_a(-1.0f, -2.0f, 4.0f, 4, 5, 4);
        this.radlik17.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlik17, 0.3316126f, 0.2617994f, 0.0f);
        this.radlik18 = new ModelRenderer((ModelBase)this, 32, 0);
        this.radlik18.func_78789_a(-2.0f, 1.0f, 4.0f, 4, 5, 4);
        this.radlik18.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.radlik18, 0.2792527f, 0.0f, 0.0f);
        this.ssjsan1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan1.func_78789_a(-1.0f, -10.0f, -6.05f, 4, 4, 4);
        this.ssjsan1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan1, -0.3141593f, 0.0f, 0.0f);
        this.ssjsan2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan2.func_78789_a(-6.8f, -6.5f, -1.0f, 4, 3, 3);
        this.ssjsan2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan2, 0.0f, 0.1745329f, -0.1396263f);
        this.ssjsan3 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan3.func_78789_a(-6.3f, -4.0f, 0.0f, 3, 2, 2);
        this.ssjsan3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan3, 0.0f, 0.2617994f, -0.1919862f);
        this.ssjsan4 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan4.func_78789_a(2.8f, -7.0f, -1.0f, 4, 3, 3);
        this.ssjsan4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan4, 0.0f, -0.1745329f, 0.1919862f);
        this.ssjsan5 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan5.func_78789_a(2.8f, -4.0f, 0.7f, 3, 2, 2);
        this.ssjsan5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan5, 0.0f, -0.1745329f, 0.1570796f);
        this.ssjsan7 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan7.func_78789_a(-1.5f, -11.0f, -8.0f, 3, 3, 3);
        this.ssjsan7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan7, -0.5934119f, 0.0f, 0.1047198f);
        this.ssjsan8 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan8.func_78789_a(-5.0f, -12.0f, -8.0f, 2, 3, 2);
        this.ssjsan8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan8, -0.6981317f, 0.0f, 0.4363323f);
        this.ssjsan10 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan10.func_78789_a(-1.0f, -10.3f, -6.3f, 4, 6, 4);
        this.ssjsan10.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan10, -0.4363323f, 0.0f, -0.3665191f);
        this.ssjsan11 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan11.func_78789_a(1.0f, -11.3f, -6.0f, 5, 4, 3);
        this.ssjsan11.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan11, -0.5410521f, 0.0f, -0.4886922f);
        this.ssjsan12 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan12.func_78789_a(3.5f, -11.5f, -8.0f, 3, 3, 3);
        this.ssjsan12.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan12, -0.8552113f, 0.0f, -0.6108652f);
        this.ssjsan13 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan13.func_78789_a(6.0f, -12.4f, -8.0f, 2, 3, 2);
        this.ssjsan13.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan13, -0.9948377f, 0.0f, -0.7679449f);
        this.ssjsan14 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan14.func_78789_a(-1.3f, -9.3f, -5.5f, 3, 5, 3);
        this.ssjsan14.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan14, -0.3665191f, 0.0f, 0.4014257f);
        this.ssjsan15 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan15.func_78789_a(-5.5f, -9.8f, -6.0f, 3, 3, 3);
        this.ssjsan15.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan15, -0.5410521f, 0.0f, 0.837758f);
        this.ssjsan16 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan16.func_78789_a(-9.0f, -8.533334f, -6.0f, 2, 3, 2);
        this.ssjsan16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan16, -0.837758f, 0.0f, 1.27409f);
        this.ssjsan17 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan17.func_78789_a(-2.0f, -2.0f, 4.0f, 4, 5, 4);
        this.ssjsan17.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan17, 0.4886922f, 0.0f, 0.0f);
        this.ssjsan18 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan18.func_78789_a(-1.0f, -5.0f, 5.0f, 4, 5, 4);
        this.ssjsan18.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan18, 0.5061455f, 0.2617994f, 0.0174533f);
        this.ssjsan19 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan19.func_78789_a(-4.0f, -6.0f, 5.0f, 4, 6, 4);
        this.ssjsan19.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan19, 0.5235988f, -0.2617994f, 0.0f);
        this.ssjsan20 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan20.func_78789_a(-2.4f, -5.2f, 7.0f, 4, 5, 4);
        this.ssjsan20.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan20, 0.6981317f, 0.0f, 0.0f);
        this.ssjsan21 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan21.func_78789_a(0.1333333f, -6.5f, 7.533333f, 3, 5, 3);
        this.ssjsan21.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan21, 0.7679449f, 0.1745329f, 0.0f);
        this.ssjsan22 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan22.func_78789_a(-2.866667f, -7.2f, 7.333333f, 3, 4, 3);
        this.ssjsan22.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan22, 0.5934119f, -0.2617994f, 0.0f);
        this.ssjsan23 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan23.func_78789_a(1.0f, -9.0f, -4.05f, 3, 4, 4);
        this.ssjsan23.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan23, -0.3141593f, 0.0f, -0.8726646f);
        this.ssjsan24 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan24.func_78789_a(3.533333f, -10.0f, -4.716667f, 3, 4, 3);
        this.ssjsan24.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan24, -0.5585054f, 0.0f, -1.082104f);
        this.ssjsan25 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan25.func_78789_a(3.533333f, -12.0f, -4.716667f, 2, 4, 2);
        this.ssjsan25.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan25, -0.5934119f, 0.0f, -0.8203047f);
        this.ssjsan26 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan26.func_78789_a(3.533333f, -9.666667f, -3.116667f, 3, 4, 3);
        this.ssjsan26.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan26, -0.5585054f, 0.0f, -1.396263f);
        this.ssjsan27 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan27.func_78789_a(-4.5f, -6.8f, -5.0f, 3, 4, 3);
        this.ssjsan27.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan27, -0.5410521f, 0.0f, 1.047198f);
        this.ssjsan28 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan28.func_78789_a(-6.8f, -7.533333f, -5.0f, 3, 4, 3);
        this.ssjsan28.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan28, -0.837758f, 0.0f, 1.308997f);
        this.ssjsan29 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan29.func_78789_a(6.0f, -10.2f, -5.0f, 2, 3, 2);
        this.ssjsan29.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan29, -0.7679449f, 0.0f, -1.291544f);
        this.ssjsan30 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan30.func_78789_a(-2.433333f, -10.6f, -7.666667f, 3, 3, 3);
        this.ssjsan30.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan30, -0.7330383f, 0.0f, 0.3839724f);
        this.ssjsan31 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan31.func_78789_a(-5.466667f, -11.0f, -8.333333f, 2, 3, 2);
        this.ssjsan31.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan31, -0.9424778f, 0.0f, 0.6806784f);
        this.ssjsan32 = new ModelRenderer((ModelBase)this, 32, 0);
        this.ssjsan32.func_78789_a(-1.4f, -14.0f, -3.0f, 3, 4, 3);
        this.ssjsan32.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ssjsan32, -0.4363323f, 0.0f, -0.0349066f);
        this.long6 = new ModelRenderer((ModelBase)this, 32, 0);
        this.long6.func_78789_a(-6.8f, -1.733333f, 3.2f, 3, 6, 3);
        this.long6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.long6, 0.4363323f, 0.0f, 0.3490659f);
        this.long7 = new ModelRenderer((ModelBase)this, 32, 0);
        this.long7.func_78789_a(4.0f, -3.066667f, 2.6f, 3, 6, 3);
        this.long7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.long7, 0.4363323f, 0.0f, -0.3490659f);
        this.long15 = new ModelRenderer((ModelBase)this, 32, 0);
        this.long15.func_78789_a(-2.266667f, -3.2f, 5.4f, 4, 4, 4);
        this.long15.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.long15, 0.4363323f, 0.0f, 0.0f);
        this.long1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.long1.func_78789_a(-4.466667f, 8.2f, 4.0f, 3, 4, 2);
        this.long1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.long1, 0.0872665f, 0.0f, 0.0698132f);
        this.long2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.long2.func_78789_a(2.533333f, 4.2f, 3.0f, 3, 5, 3);
        this.long2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.long2, 0.1396263f, 0.0f, -0.0872665f);
        this.long3 = new ModelRenderer((ModelBase)this, 32, 0);
        this.long3.func_78789_a(-5.466667f, 4.2f, 3.0f, 3, 5, 3);
        this.long3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.long3, 0.1396263f, 0.0f, 0.0872665f);
        this.long4 = new ModelRenderer((ModelBase)this, 32, 0);
        this.long4.func_78789_a(-6.133333f, 0.7333333f, 3.0f, 3, 5, 3);
        this.long4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.long4, 0.2268928f, 0.0f, 0.2094395f);
        this.long5 = new ModelRenderer((ModelBase)this, 32, 0);
        this.long5.func_78789_a(3.266667f, 0.7333333f, 3.0f, 3, 5, 3);
        this.long5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.long5, 0.2268928f, 0.0f, -0.2094395f);
        this.long8 = new ModelRenderer((ModelBase)this, 32, 0);
        this.long8.func_78789_a(-1.466667f, 7.0f, 4.0f, 3, 7, 4);
        this.long8.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.long8, 0.0872665f, -0.2617994f, 0.0f);
        this.long9 = new ModelRenderer((ModelBase)this, 32, 0);
        this.long9.func_78789_a(-2.466667f, 2.0f, 4.0f, 4, 6, 4);
        this.long9.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.long9, 0.1570796f, -0.2617994f, 0.0f);
        this.long10 = new ModelRenderer((ModelBase)this, 32, 0);
        this.long10.func_78789_a(-2.0f, 9.266666f, 4.0f, 4, 5, 4);
        this.long10.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.long10, 0.0698132f, 0.2617994f, 0.0f);
        this.long11 = new ModelRenderer((ModelBase)this, 32, 0);
        this.long11.func_78789_a(-1.0f, 5.266667f, 4.0f, 4, 5, 4);
        this.long11.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.long11, 0.1047198f, 0.2617994f, 0.0f);
        this.long12 = new ModelRenderer((ModelBase)this, 32, 0);
        this.long12.func_78789_a(-0.9f, 1.266667f, 4.0f, 4, 5, 4);
        this.long12.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.long12, 0.1745329f, 0.2617994f, 0.0f);
        this.long13 = new ModelRenderer((ModelBase)this, 32, 0);
        this.long13.func_78789_a(-1.933333f, 6.0f, 4.0f, 4, 6, 4);
        this.long13.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.long13, 0.1745329f, 0.0f, 0.0f);
        this.long14 = new ModelRenderer((ModelBase)this, 32, 0);
        this.long14.func_78789_a(-1.4f, 11.0f, 5.6f, 3, 6, 3);
        this.long14.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.long14, 0.0872665f, 0.0f, 0.0f);
        this.long16 = new ModelRenderer((ModelBase)this, 32, 0);
        this.long16.func_78789_a(-2.533333f, -2.0f, 3.333333f, 4, 6, 4);
        this.long16.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.long16, 0.3490659f, -0.2617994f, 0.0f);
        this.long17 = new ModelRenderer((ModelBase)this, 32, 0);
        this.long17.func_78789_a(-1.0f, -2.0f, 4.0f, 4, 5, 4);
        this.long17.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.long17, 0.3316126f, 0.2617994f, 0.0f);
        this.long18 = new ModelRenderer((ModelBase)this, 32, 0);
        this.long18.func_78789_a(-2.0f, 1.0f, 4.0f, 4, 6, 4);
        this.long18.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.long18, 0.2792527f, 0.0f, 0.0f);
        this.tincs1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.tincs1.func_78789_a(2.866667f, -5.533333f, -6.25f, 2, 4, 1);
        this.tincs1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.tincs1, -0.3141593f, 0.0f, -0.4712389f);
        this.bipedHeadg.func_78792_a(this.bipedHeadAll);
        this.bipedHeadg.func_78792_a(this.goku1);
        this.bipedHeadg.func_78792_a(this.goku2);
        this.bipedHeadg.func_78792_a(this.goku3);
        this.bipedHeadg.func_78792_a(this.goku4);
        this.bipedHeadg.func_78792_a(this.goku5);
        this.bipedHeadg.func_78792_a(this.goku6);
        this.bipedHeadg.func_78792_a(this.goku7);
        this.bipedHeadg.func_78792_a(this.goku8);
        this.bipedHeadg.func_78792_a(this.goku9);
        this.bipedHeadg.func_78792_a(this.goku10);
        this.bipedHeadg.func_78792_a(this.goku11);
        this.bipedHeadg.func_78792_a(this.goku12);
        this.bipedHeadg.func_78792_a(this.goku13);
        this.bipedHeadg.func_78792_a(this.goku14);
        this.bipedHeadg.func_78792_a(this.goku15);
        this.bipedHeadg.func_78792_a(this.goku16);
        this.bipedHeadsg.func_78792_a(this.bipedHeadAll);
        this.bipedHeadsg.func_78792_a(this.sgoku1);
        this.bipedHeadsg.func_78792_a(this.sgoku2);
        this.bipedHeadsg.func_78792_a(this.sgoku3);
        this.bipedHeadsg.func_78792_a(this.sgoku4);
        this.bipedHeadsg.func_78792_a(this.sgoku5);
        this.bipedHeadsg.func_78792_a(this.sgoku6);
        this.bipedHeadsg.func_78792_a(this.sgoku7);
        this.bipedHeadsg.func_78792_a(this.sgoku8);
        this.bipedHeadsg.func_78792_a(this.sgoku9);
        this.bipedHeadsg.func_78792_a(this.sgoku10);
        this.bipedHeadsg.func_78792_a(this.sgoku11);
        this.bipedHeadsg.func_78792_a(this.sgoku12);
        this.bipedHeadsg.func_78792_a(this.sgoku13);
        this.bipedHeadsg.func_78792_a(this.sgoku14);
        this.bipedHeadsg.func_78792_a(this.sgoku15);
        this.bipedHeadsg.func_78792_a(this.sgoku16);
        this.bipedHeadsg.func_78792_a(this.sgoku17);
        this.bipedHeadsg.func_78792_a(this.sgoku18);
        this.bipedHeadsg.func_78792_a(this.sgoku19);
        this.bipedHeadsg.func_78792_a(this.sgoku20);
        this.bipedHeadsg.func_78792_a(this.sgoku21);
        this.bipedHeadsg.func_78792_a(this.sgoku22);
        this.bipedHeadsg.func_78792_a(this.sgoku23);
        this.bipedHeadsg.func_78792_a(this.sgoku24);
        this.bipedHeadsg.func_78792_a(this.sgoku25);
        this.bipedHeadsg.func_78792_a(this.sgoku26);
        this.bipedHeadssg.func_78792_a(this.bipedHeadAll);
        this.bipedHeadssg.func_78792_a(this.ssgoku1);
        this.bipedHeadssg.func_78792_a(this.ssgoku2);
        this.bipedHeadssg.func_78792_a(this.ssgoku3);
        this.bipedHeadssg.func_78792_a(this.ssgoku4);
        this.bipedHeadssg.func_78792_a(this.ssgoku5);
        this.bipedHeadssg.func_78792_a(this.ssgoku6);
        this.bipedHeadssg.func_78792_a(this.ssgoku7);
        this.bipedHeadssg.func_78792_a(this.ssgoku8);
        this.bipedHeadssg.func_78792_a(this.ssgoku9);
        this.bipedHeadssg.func_78792_a(this.ssgoku10);
        this.bipedHeadssg.func_78792_a(this.ssgoku11);
        this.bipedHeadssg.func_78792_a(this.ssgoku12);
        this.bipedHeadssg.func_78792_a(this.ssgoku13);
        this.bipedHeadssg.func_78792_a(this.ssgoku14);
        this.bipedHeadssg.func_78792_a(this.ssgoku15);
        this.bipedHeadssg.func_78792_a(this.ssgoku16);
        this.bipedHeadssg.func_78792_a(this.ssgoku17);
        this.bipedHeadssg.func_78792_a(this.ssgoku18);
        this.bipedHeadssg.func_78792_a(this.ssgoku19);
        this.bipedHeadssg.func_78792_a(this.ssgoku20);
        this.bipedHeadssg.func_78792_a(this.ssgoku21);
        this.bipedHeadssg.func_78792_a(this.ssgoku22);
        this.bipedHeadssg.func_78792_a(this.ssgoku23);
        this.bipedHeadssg.func_78792_a(this.ssgoku24);
        this.bipedHeadssg.func_78792_a(this.ssgoku25);
        this.bipedHeadssg.func_78792_a(this.ssgoku26);
        this.bipedHeadt.func_78792_a(this.bipedHeadAll);
        this.bipedHeadt.func_78792_a(this.trunk1);
        this.bipedHeadt.func_78792_a(this.trunk2);
        this.bipedHeadt.func_78792_a(this.trunk3);
        this.bipedHeadt.func_78792_a(this.trunk4);
        this.bipedHeadt.func_78792_a(this.trunk5);
        this.bipedHeadt.func_78792_a(this.trunk6);
        this.bipedHeadt.func_78792_a(this.trunk7);
        this.bipedHeadt.func_78792_a(this.trunk8);
        this.bipedHeadt.func_78792_a(this.trunk9);
        this.bipedHeadst.func_78792_a(this.bipedHeadAll);
        this.bipedHeadst.func_78792_a(this.strunk1);
        this.bipedHeadst.func_78792_a(this.strunk2);
        this.bipedHeadst.func_78792_a(this.strunk3);
        this.bipedHeadst.func_78792_a(this.strunk4);
        this.bipedHeadst.func_78792_a(this.strunk5);
        this.bipedHeadst.func_78792_a(this.strunk6);
        this.bipedHeadst.func_78792_a(this.strunk7);
        this.bipedHeadst.func_78792_a(this.strunk8);
        this.bipedHeadst.func_78792_a(this.strunk9);
        this.bipedHeadst.func_78792_a(this.strunk10);
        this.bipedHeadst.func_78792_a(this.strunk11);
        this.bipedHeadst.func_78792_a(this.strunk12);
        this.bipedHeadst.func_78792_a(this.strunk13);
        this.bipedHeadst.func_78792_a(this.strunk14);
        this.bipedHeadst.func_78792_a(this.strunk15);
        this.bipedHeadst.func_78792_a(this.strunk16);
        this.bipedHeadst.func_78792_a(this.strunk17);
        this.bipedHeadsst.func_78792_a(this.bipedHeadAll);
        this.bipedHeadsst.func_78792_a(this.sstrunk1);
        this.bipedHeadsst.func_78792_a(this.sstrunk2);
        this.bipedHeadsst.func_78792_a(this.sstrunk3);
        this.bipedHeadsst.func_78792_a(this.sstrunk4);
        this.bipedHeadsst.func_78792_a(this.sstrunk5);
        this.bipedHeadsst.func_78792_a(this.sstrunk6);
        this.bipedHeadsst.func_78792_a(this.sstrunk7);
        this.bipedHeadsst.func_78792_a(this.sstrunk8);
        this.bipedHeadsst.func_78792_a(this.sstrunk9);
        this.bipedHeadsst.func_78792_a(this.sstrunk10);
        this.bipedHeadsst.func_78792_a(this.sstrunk11);
        this.bipedHeadsst.func_78792_a(this.sstrunk12);
        this.bipedHeadsst.func_78792_a(this.sstrunk13);
        this.bipedHeadsst.func_78792_a(this.sstrunk14);
        this.bipedHeadsst.func_78792_a(this.sstrunk15);
        this.bipedHeadsst.func_78792_a(this.sstrunk16);
        this.bipedHeadsst.func_78792_a(this.sstrunk17);
        this.bipedHeadv.func_78792_a(this.bipedHeadAll);
        this.bipedHeadv.func_78792_a(this.vegeta1);
        this.bipedHeadv.func_78792_a(this.vegeta2);
        this.bipedHeadv.func_78792_a(this.vegeta3);
        this.bipedHeadv.func_78792_a(this.vegeta4);
        this.bipedHeadv.func_78792_a(this.vegeta5);
        this.bipedHeadv.func_78792_a(this.vegeta6);
        this.bipedHeadv.func_78792_a(this.vegeta7);
        this.bipedHeadv.func_78792_a(this.vegeta8);
        this.bipedHeadv.func_78792_a(this.vegeta9);
        this.bipedHeadv.func_78792_a(this.vegeta10);
        this.bipedHeadv.func_78792_a(this.vegeta11);
        this.bipedHeadv.func_78792_a(this.vegeta12);
        this.bipedHeadv.func_78792_a(this.vegeta13);
        this.bipedHeadv.func_78792_a(this.vegeta14);
        this.bipedHeadv.func_78792_a(this.vegeta15);
        this.bipedHeadv.func_78792_a(this.vegeta16);
        this.bipedHeadv.func_78792_a(this.vegeta17);
        this.bipedHeadv.func_78792_a(this.vegeta18);
        this.bipedHeadv.func_78792_a(this.vegeta19);
        this.bipedHeadv.func_78792_a(this.vegeta20);
        this.bipedHeadv.func_78792_a(this.vegeta21);
        this.bipedHeadv.func_78792_a(this.vegeta22);
        this.bipedHeadsv.func_78792_a(this.bipedHeadAll);
        this.bipedHeadsv.func_78792_a(this.svegeta1);
        this.bipedHeadsv.func_78792_a(this.svegeta2);
        this.bipedHeadsv.func_78792_a(this.svegeta3);
        this.bipedHeadsv.func_78792_a(this.svegeta4);
        this.bipedHeadsv.func_78792_a(this.svegeta5);
        this.bipedHeadsv.func_78792_a(this.svegeta6);
        this.bipedHeadsv.func_78792_a(this.svegeta7);
        this.bipedHeadsv.func_78792_a(this.svegeta8);
        this.bipedHeadsv.func_78792_a(this.svegeta9);
        this.bipedHeadsv.func_78792_a(this.svegeta10);
        this.bipedHeadsv.func_78792_a(this.svegeta11);
        this.bipedHeadsv.func_78792_a(this.svegeta12);
        this.bipedHeadsv.func_78792_a(this.svegeta13);
        this.bipedHeadsv.func_78792_a(this.svegeta14);
        this.bipedHeadsv.func_78792_a(this.svegeta15);
        this.bipedHeadsv.func_78792_a(this.svegeta16);
        this.bipedHeadsv.func_78792_a(this.svegeta17);
        this.bipedHeadsv.func_78792_a(this.svegeta18);
        this.bipedHeadsv.func_78792_a(this.svegeta19);
        this.bipedHeadsv.func_78792_a(this.svegeta20);
        this.bipedHeadsv.func_78792_a(this.svegeta21);
        this.bipedHeadsv.func_78792_a(this.svegeta22);
        this.bipedHeadssv.func_78792_a(this.bipedHeadAll);
        this.bipedHeadssv.func_78792_a(this.ssvegeta1);
        this.bipedHeadssv.func_78792_a(this.ssvegeta2);
        this.bipedHeadssv.func_78792_a(this.ssvegeta3);
        this.bipedHeadssv.func_78792_a(this.ssvegeta4);
        this.bipedHeadssv.func_78792_a(this.ssvegeta5);
        this.bipedHeadssv.func_78792_a(this.ssvegeta6);
        this.bipedHeadssv.func_78792_a(this.ssvegeta7);
        this.bipedHeadssv.func_78792_a(this.ssvegeta8);
        this.bipedHeadssv.func_78792_a(this.ssvegeta9);
        this.bipedHeadssv.func_78792_a(this.ssvegeta10);
        this.bipedHeadssv.func_78792_a(this.ssvegeta11);
        this.bipedHeadssv.func_78792_a(this.ssvegeta12);
        this.bipedHeadssv.func_78792_a(this.ssvegeta13);
        this.bipedHeadssv.func_78792_a(this.ssvegeta14);
        this.bipedHeadssv.func_78792_a(this.ssvegeta15);
        this.bipedHeadssv.func_78792_a(this.ssvegeta16);
        this.bipedHeadssv.func_78792_a(this.ssvegeta17);
        this.bipedHeadssv.func_78792_a(this.ssvegeta18);
        this.bipedHeadssv.func_78792_a(this.ssvegeta19);
        this.bipedHeadssv.func_78792_a(this.ssvegeta20);
        this.bipedHeadssv.func_78792_a(this.ssvegeta21);
        this.bipedHeadssv.func_78792_a(this.ssvegeta22);
        this.bipedHeadgh.func_78792_a(this.bipedHeadAll);
        this.bipedHeadgh.func_78792_a(this.gohan1);
        this.bipedHeadgh.func_78792_a(this.gohan7);
        this.bipedHeadgh.func_78792_a(this.gohan8);
        this.bipedHeadgh.func_78792_a(this.gohan10);
        this.bipedHeadgh.func_78792_a(this.gohan11);
        this.bipedHeadgh.func_78792_a(this.gohan12);
        this.bipedHeadgh.func_78792_a(this.gohan13);
        this.bipedHeadgh.func_78792_a(this.gohan14);
        this.bipedHeadgh.func_78792_a(this.gohan15);
        this.bipedHeadgh.func_78792_a(this.gohan16);
        this.bipedHeadgh.func_78792_a(this.gohan17);
        this.bipedHeadgh.func_78792_a(this.gohan18);
        this.bipedHeadgh.func_78792_a(this.gohan19);
        this.bipedHeadgh.func_78792_a(this.gohan20);
        this.bipedHeadgh.func_78792_a(this.gohan21);
        this.bipedHeadgh.func_78792_a(this.gohan22);
        this.bipedHeadgh.func_78792_a(this.gohan26);
        this.bipedHeadsgh.func_78792_a(this.bipedHeadAll);
        this.bipedHeadsgh.func_78792_a(this.sgohan1);
        this.bipedHeadsgh.func_78792_a(this.sgohan7);
        this.bipedHeadsgh.func_78792_a(this.sgohan8);
        this.bipedHeadsgh.func_78792_a(this.sgohan10);
        this.bipedHeadsgh.func_78792_a(this.sgohan11);
        this.bipedHeadsgh.func_78792_a(this.sgohan12);
        this.bipedHeadsgh.func_78792_a(this.sgohan13);
        this.bipedHeadsgh.func_78792_a(this.sgohan14);
        this.bipedHeadsgh.func_78792_a(this.sgohan15);
        this.bipedHeadsgh.func_78792_a(this.sgohan16);
        this.bipedHeadsgh.func_78792_a(this.sgohan17);
        this.bipedHeadsgh.func_78792_a(this.sgohan18);
        this.bipedHeadsgh.func_78792_a(this.sgohan19);
        this.bipedHeadsgh.func_78792_a(this.sgohan20);
        this.bipedHeadsgh.func_78792_a(this.sgohan21);
        this.bipedHeadsgh.func_78792_a(this.sgohan22);
        this.bipedHeadsgh.func_78792_a(this.sgohan26);
        this.bipedHeadssgh.func_78792_a(this.bipedHeadAll);
        this.bipedHeadssgh.func_78792_a(this.ssgohan1);
        this.bipedHeadssgh.func_78792_a(this.ssgohan7);
        this.bipedHeadssgh.func_78792_a(this.ssgohan8);
        this.bipedHeadssgh.func_78792_a(this.ssgohan10);
        this.bipedHeadssgh.func_78792_a(this.ssgohan11);
        this.bipedHeadssgh.func_78792_a(this.ssgohan12);
        this.bipedHeadssgh.func_78792_a(this.ssgohan13);
        this.bipedHeadssgh.func_78792_a(this.ssgohan14);
        this.bipedHeadssgh.func_78792_a(this.ssgohan15);
        this.bipedHeadssgh.func_78792_a(this.ssgohan16);
        this.bipedHeadssgh.func_78792_a(this.ssgohan17);
        this.bipedHeadssgh.func_78792_a(this.ssgohan18);
        this.bipedHeadssgh.func_78792_a(this.ssgohan19);
        this.bipedHeadssgh.func_78792_a(this.ssgohan20);
        this.bipedHeadssgh.func_78792_a(this.ssgohan21);
        this.bipedHeadssgh.func_78792_a(this.ssgohan22);
        this.bipedHeadssgh.func_78792_a(this.ssgohan26);
        this.bipedHeadg2.func_78792_a(this.bipedHeadAll);
        this.bipedHeadg2.func_78792_a(this.gokuni1);
        this.bipedHeadg2.func_78792_a(this.gokuni2);
        this.bipedHeadg2.func_78792_a(this.gokuni3);
        this.bipedHeadg2.func_78792_a(this.gokuni4);
        this.bipedHeadg2.func_78792_a(this.gokuni5);
        this.bipedHeadg2.func_78792_a(this.gokuni6);
        this.bipedHeadg2.func_78792_a(this.gokuni7);
        this.bipedHeadg2.func_78792_a(this.gokuni8);
        this.bipedHeadg2.func_78792_a(this.gokuni9);
        this.bipedHeadg2.func_78792_a(this.gokuni10);
        this.bipedHeadg2.func_78792_a(this.gokuni11);
        this.bipedHeadg2.func_78792_a(this.gokuni12);
        this.bipedHeadght.func_78792_a(this.bipedHeadAll);
        this.bipedHeadght.func_78792_a(this.ght1);
        this.bipedHeadght.func_78792_a(this.ght2);
        this.bipedHeadght.func_78792_a(this.ght3);
        this.bipedHeadght.func_78792_a(this.ght4);
        this.bipedHeadght.func_78792_a(this.ght5);
        this.bipedHeadght.func_78792_a(this.ght6);
        this.bipedHeadght.func_78792_a(this.ght7);
        this.bipedHeadght.func_78792_a(this.ght8);
        this.bipedHeadght.func_78792_a(this.ght9);
        this.bipedHeadght.func_78792_a(this.ght11);
        this.bipedHeadght.func_78792_a(this.ght14);
        this.bipedHeadght.func_78792_a(this.ght16);
        this.bipedHeadgt.func_78792_a(this.bipedHeadAll);
        this.bipedHeadgt.func_78792_a(this.goten2);
        this.bipedHeadgt.func_78792_a(this.goten3);
        this.bipedHeadgt.func_78792_a(this.goten4);
        this.bipedHeadgt.func_78792_a(this.goten5);
        this.bipedHeadgt.func_78792_a(this.goten6);
        this.bipedHeadgt.func_78792_a(this.goten9);
        this.bipedHeadgt.func_78792_a(this.goten14);
        this.bipedHeadgt.func_78792_a(this.goten16);
        this.bipedHeadgtt.func_78792_a(this.bipedHeadAll);
        this.bipedHeadgtt.func_78792_a(this.gotent1);
        this.bipedHeadgtt.func_78792_a(this.gotent2);
        this.bipedHeadgtt.func_78792_a(this.gotent3);
        this.bipedHeadgtt.func_78792_a(this.gotent5);
        this.bipedHeadgtt.func_78792_a(this.gotent6);
        this.bipedHeadgtt.func_78792_a(this.gotent7);
        this.bipedHeadgtt.func_78792_a(this.gotent8);
        this.bipedHeadgtt.func_78792_a(this.gotent9);
        this.bipedHeadgtt.func_78792_a(this.gotent11);
        this.bipedHeadgtt.func_78792_a(this.gotent16);
        this.bipedHeadc7.func_78792_a(this.bipedHeadAll);
        this.bipedHeadc7.func_78792_a(this.hairc71);
        this.bipedHeadc7.func_78792_a(this.hairc72);
        this.bipedHeadc8.func_78792_a(this.bipedHeadAll);
        this.bipedHeadc8.func_78792_a(this.hairc81);
        this.bipedHeadc8.func_78792_a(this.hairc82);
        this.bipedHeadc8.func_78792_a(this.hairc83);
        this.bipedHeadrad.func_78792_a(this.bipedHeadAll);
        this.bipedHeadrad.func_78792_a(this.radlike1);
        this.bipedHeadrad.func_78792_a(this.radlike2);
        this.bipedHeadrad.func_78792_a(this.radlike3);
        this.bipedHeadrad.func_78792_a(this.radlike4);
        this.bipedHeadrad.func_78792_a(this.radlike5);
        this.bipedHeadrad.func_78792_a(this.radlike7);
        this.bipedHeadrad.func_78792_a(this.radlike8);
        this.bipedHeadrad.func_78792_a(this.radlike10);
        this.bipedHeadrad.func_78792_a(this.radlike11);
        this.bipedHeadrad.func_78792_a(this.radlike12);
        this.bipedHeadrad.func_78792_a(this.radlike13);
        this.bipedHeadrad.func_78792_a(this.radlike14);
        this.bipedHeadrad.func_78792_a(this.radlike15);
        this.bipedHeadrad.func_78792_a(this.radlike16);
        this.bipedHeadrad.func_78792_a(this.radlike18);
        this.bipedHeadrad.func_78792_a(this.radlike19);
        this.bipedHeadrad.func_78792_a(this.radlike20);
        this.bipedHeadrad.func_78792_a(this.radlike21);
        this.bipedHeadrad.func_78792_a(this.radlike22);
        this.bipedHeadrad.func_78792_a(this.radlike23);
        this.bipedHeadrad.func_78792_a(this.radlike24);
        this.bipedHeadrad.func_78792_a(this.radlike25);
        this.bipedHeadrad.func_78792_a(this.radlike26);
        this.bipedHeadrad.func_78792_a(this.radlike27);
        this.bipedHeadrad.func_78792_a(this.radlike28);
        this.bipedHeadrad.func_78792_a(this.radlike29);
        this.bipedHeadrad.func_78792_a(this.radlike30);
        this.bipedHeadrad.func_78792_a(this.radlike31);
        this.bipedHeadrad.func_78792_a(this.radlike32);
        this.bipedHeadradl.func_78792_a(this.radlik1);
        this.bipedHeadradl.func_78792_a(this.radlik2);
        this.bipedHeadradl.func_78792_a(this.radlik3);
        this.bipedHeadradl.func_78792_a(this.radlik4);
        this.bipedHeadradl.func_78792_a(this.radlik5);
        this.bipedHeadradl.func_78792_a(this.radlik8);
        this.bipedHeadradl.func_78792_a(this.radlik9);
        this.bipedHeadradl.func_78792_a(this.radlik10);
        this.bipedHeadradl.func_78792_a(this.radlik11);
        this.bipedHeadradl.func_78792_a(this.radlik12);
        this.bipedHeadradl.func_78792_a(this.radlik13);
        this.bipedHeadradl.func_78792_a(this.radlik14);
        this.bipedHeadradl.func_78792_a(this.radlik18);
        this.bipedHeadradl2.func_78792_a(this.radlik6);
        this.bipedHeadradl2.func_78792_a(this.radlik7);
        this.bipedHeadradl2.func_78792_a(this.radlik15);
        this.bipedHeadradl2.func_78792_a(this.radlike17);
        this.bipedHeadradl2.func_78792_a(this.radlik16);
        this.bipedHeadradl2.func_78792_a(this.radlik17);
        this.bipedHeadssj3.func_78792_a(this.bipedHeadAll);
        this.bipedHeadssj3.func_78792_a(this.ssjsan1);
        this.bipedHeadssj3.func_78792_a(this.ssjsan2);
        this.bipedHeadssj3.func_78792_a(this.ssjsan3);
        this.bipedHeadssj3.func_78792_a(this.ssjsan4);
        this.bipedHeadssj3.func_78792_a(this.ssjsan5);
        this.bipedHeadssj3.func_78792_a(this.ssjsan7);
        this.bipedHeadssj3.func_78792_a(this.ssjsan8);
        this.bipedHeadssj3.func_78792_a(this.ssjsan10);
        this.bipedHeadssj3.func_78792_a(this.ssjsan11);
        this.bipedHeadssj3.func_78792_a(this.ssjsan12);
        this.bipedHeadssj3.func_78792_a(this.ssjsan13);
        this.bipedHeadssj3.func_78792_a(this.ssjsan14);
        this.bipedHeadssj3.func_78792_a(this.ssjsan15);
        this.bipedHeadssj3.func_78792_a(this.ssjsan16);
        this.bipedHeadssj3.func_78792_a(this.ssjsan18);
        this.bipedHeadssj3.func_78792_a(this.ssjsan19);
        this.bipedHeadssj3.func_78792_a(this.ssjsan20);
        this.bipedHeadssj3.func_78792_a(this.ssjsan21);
        this.bipedHeadssj3.func_78792_a(this.ssjsan22);
        this.bipedHeadssj3.func_78792_a(this.ssjsan23);
        this.bipedHeadssj3.func_78792_a(this.ssjsan24);
        this.bipedHeadssj3.func_78792_a(this.ssjsan25);
        this.bipedHeadssj3.func_78792_a(this.ssjsan26);
        this.bipedHeadssj3.func_78792_a(this.ssjsan27);
        this.bipedHeadssj3.func_78792_a(this.ssjsan28);
        this.bipedHeadssj3.func_78792_a(this.ssjsan29);
        this.bipedHeadssj3.func_78792_a(this.ssjsan30);
        this.bipedHeadssj3.func_78792_a(this.ssjsan31);
        this.bipedHeadssj3.func_78792_a(this.ssjsan32);
        this.bipedHeadssj3l2.func_78792_a(this.long6);
        this.bipedHeadssj3l2.func_78792_a(this.long7);
        this.bipedHeadssj3l2.func_78792_a(this.long15);
        this.bipedHeadssj3l2.func_78792_a(this.ssjsan17);
        this.bipedHeadssj3l2.func_78792_a(this.long16);
        this.bipedHeadssj3l2.func_78792_a(this.long17);
        this.bipedHeadssj3l.func_78792_a(this.long1);
        this.bipedHeadssj3l.func_78792_a(this.long2);
        this.bipedHeadssj3l.func_78792_a(this.long3);
        this.bipedHeadssj3l.func_78792_a(this.long4);
        this.bipedHeadssj3l.func_78792_a(this.long5);
        this.bipedHeadssj3l.func_78792_a(this.long8);
        this.bipedHeadssj3l.func_78792_a(this.long9);
        this.bipedHeadssj3l.func_78792_a(this.long10);
        this.bipedHeadssj3l.func_78792_a(this.long11);
        this.bipedHeadssj3l.func_78792_a(this.long12);
        this.bipedHeadssj3l.func_78792_a(this.long13);
        this.bipedHeadssj3l.func_78792_a(this.long14);
        this.bipedHeadssj3l.func_78792_a(this.long18);
        this.bipedHeadssj3t.func_78792_a(this.tincs1);
        this.halo = new ModelRenderer((ModelBase)this, 32, 0);
        this.halo.func_78789_a(-0.0f, -0.0f, -0.0f, 0, 0, 0);
        this.halo.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.halo, 0.0f, 0.0f, 0.0f);
        this.halo1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.halo1.func_78789_a(-4.0f, -13.0f, -5.0f, 9, 1, 1);
        this.halo1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.halo1, 0.0f, 0.0f, 0.0f);
        this.halo2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.halo2.func_78789_a(-5.0f, -13.0f, -5.0f, 1, 1, 9);
        this.halo2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.halo2, 0.0f, 0.0f, 0.0f);
        this.halo3 = new ModelRenderer((ModelBase)this, 32, 0);
        this.halo3.func_78789_a(4.0f, -13.0f, -4.0f, 1, 1, 9);
        this.halo3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.halo3, 0.0f, 0.0f, 0.0f);
        this.halo4 = new ModelRenderer((ModelBase)this, 32, 0);
        this.halo4.func_78789_a(-5.0f, -13.0f, 4.0f, 9, 1, 1);
        this.halo4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.halo4, 0.0f, 0.0f, 0.0f);
        this.halo.func_78792_a(this.halo1);
        this.halo.func_78792_a(this.halo2);
        this.halo.func_78792_a(this.halo3);
        this.halo.func_78792_a(this.halo4);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 12, 4, par1 * 0.5f);
        this.rightarm.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.rightarm, 0.0f, 0.0f, 0.122173f);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm.field_78809_i = true;
        this.leftarm.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, par1 * 0.5f);
        this.leftarm.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.leftarm, 0.0f, 0.0f, -0.122173f);
        this.Brightarm = new ModelRenderer((ModelBase)this, 0, 0);
        this.Brightarm.func_78790_a(-3.0f, -2.0f, -2.0f, 0, 0, 0, par1 * 0.5f);
        this.Brightarm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.Bleftarm = new ModelRenderer((ModelBase)this, 0, 0);
        this.Bleftarm.field_78809_i = true;
        this.Bleftarm.func_78790_a(-1.0f, -2.0f, -2.0f, 0, 0, 0, par1 * 0.5f);
        this.Bleftarm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, par1 * 0.5f);
        this.rightleg.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.field_78809_i = true;
        this.leftleg.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, par1 * 0.5f);
        this.leftleg.func_78793_a(2.0f, 12.0f, 0.0f);
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.skirt1 = new ModelRenderer((ModelBase)this, 16, 18);
        this.skirt1.func_78790_a(-4.0f, 9.0f, -2.0f, 8, 2, 4, par1 * 0.5f);
        this.skirt1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.skirt1, 0.0f, 0.0f, 0.0f);
        this.skirt2 = new ModelRenderer((ModelBase)this, 16, 20);
        this.skirt2.func_78790_a(-4.0f, 11.0f, -2.0f, 8, 1, 4, par1 * 0.5f);
        this.skirt2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.skirt2, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 16, 16);
        this.body.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 4, 4, par1 * 0.5f);
        this.body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.hip = new ModelRenderer((ModelBase)this, 16, 23);
        this.hip.func_78790_a(-4.0f, 7.0f, -2.0f, 8, 2, 4, par1 * 0.5f);
        this.hip.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.hip, 0.0f, 0.0f, 0.0f);
        this.waist = new ModelRenderer((ModelBase)this, 16, 20);
        this.waist.func_78790_a(-4.0f, 4.0f, -2.0f, 8, 3, 4, par1 * 0.5f);
        this.waist.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.waist, 0.0f, 0.0f, 0.0f);
        this.Bbreast = new ModelRenderer((ModelBase)this, 0, 0);
        this.Bbreast.func_78790_a(-4.0f, 2.266667f, -1.0f, 0, 0, 0, par1 * 0.5f);
        this.Bbreast.func_78793_a(0.0f, 0.0f, 0.0f);
        this.breast = new ModelRenderer((ModelBase)this, 17, 18);
        this.breast.func_78790_a(-4.0f, 2.266667f, -1.0f, 8, 3, 3, par1 * 0.5f);
        this.breast.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.breast, -0.5235988f, 0.0f, 0.0f);
        this.Bbreast2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Bbreast2.func_78790_a(-4.0f, 2.266667f, -1.0f, 0, 0, 0, par1 * 0.5f);
        this.Bbreast2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.breast2 = new ModelRenderer((ModelBase)this, 9, 23);
        this.breast2.field_78809_i = true;
        this.breast2.func_78790_a(-4.0f, 2.266667f, -2.0f, 8, 3, 3, par1 * 0.5f);
        this.breast2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.breast2, 0.5235988f, 3.141593f, 0.0f);
        this.bottom = new ModelRenderer((ModelBase)this, 16, 25);
        this.bottom.func_78790_a(-4.0f, 9.0f, -2.0f, 8, 3, 4, par1 * 0.5f);
        this.bottom.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.bottom, 0.0f, 0.0f, 0.0f);
        this.Bbreast.func_78792_a(this.breast);
        this.Bbreast2.func_78792_a(this.breast2);
        this.Bleftarm.func_78792_a(this.leftarm);
        this.Brightarm.func_78792_a(this.rightarm);
        this.S1rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.S1rightarm.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 12, 4, par1 * 0.5f * 1.001f);
        this.S1rightarm.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.S1rightarm, 0.0f, 0.0f, 0.122173f);
        this.S1leftarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.S1leftarm.field_78809_i = true;
        this.S1leftarm.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, par1 * 0.5f * 1.001f);
        this.S1leftarm.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.S1leftarm, 0.0f, 0.0f, -0.122173f);
        this.S1Brightarm = new ModelRenderer((ModelBase)this, 0, 0);
        this.S1Brightarm.func_78790_a(-3.0f, -2.0f, -2.0f, 0, 0, 0, par1 * 0.5f * 1.001f);
        this.S1Brightarm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.S1Bleftarm = new ModelRenderer((ModelBase)this, 0, 0);
        this.S1Bleftarm.field_78809_i = true;
        this.S1Bleftarm.func_78790_a(-1.0f, -2.0f, -2.0f, 0, 0, 0, par1 * 0.5f * 1.001f);
        this.S1Bleftarm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.S1rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.S1rightleg.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, par1 * 0.5f * 1.001f);
        this.S1rightleg.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.setRotation(this.S1rightleg, 0.0f, 0.0f, 0.0f);
        this.S1leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.S1leftleg.field_78809_i = true;
        this.S1leftleg.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, par1 * 0.5f * 1.001f);
        this.S1leftleg.func_78793_a(2.0f, 12.0f, 0.0f);
        this.setRotation(this.S1leftleg, 0.0f, 0.0f, 0.0f);
        this.S1skirt1 = new ModelRenderer((ModelBase)this, 16, 18);
        this.S1skirt1.func_78790_a(-4.0f, 9.0f, -2.0f, 8, 2, 4, par1 * 0.5f * 1.001f);
        this.S1skirt1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.S1skirt1, 0.0f, 0.0f, 0.0f);
        this.S1skirt2 = new ModelRenderer((ModelBase)this, 16, 20);
        this.S1skirt2.func_78790_a(-4.0f, 11.0f, -2.0f, 8, 1, 4, par1 * 0.5f * 1.001f);
        this.S1skirt2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.S1skirt2, 0.0f, 0.0f, 0.0f);
        this.S1body = new ModelRenderer((ModelBase)this, 16, 16);
        this.S1body.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 4, 4, par1 * 0.5f * 1.001f);
        this.S1body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.S1body, 0.0f, 0.0f, 0.0f);
        this.S1hip = new ModelRenderer((ModelBase)this, 16, 23);
        this.S1hip.func_78790_a(-4.0f, 7.0f, -2.0f, 8, 2, 4, par1 * 0.5f * 1.001f);
        this.S1hip.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.S1hip, 0.0f, 0.0f, 0.0f);
        this.S1waist = new ModelRenderer((ModelBase)this, 16, 20);
        this.S1waist.func_78790_a(-4.0f, 4.0f, -2.0f, 8, 3, 4, par1 * 0.5f * 1.001f);
        this.S1waist.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.S1waist, 0.0f, 0.0f, 0.0f);
        this.S1Bbreast = new ModelRenderer((ModelBase)this, 0, 0);
        this.S1Bbreast.func_78790_a(-4.0f, 2.266667f, -1.0f, 0, 0, 0, par1 * 0.5f * 1.001f);
        this.S1Bbreast.func_78793_a(0.0f, 0.0f, 0.0f);
        this.S1breast = new ModelRenderer((ModelBase)this, 17, 18);
        this.S1breast.func_78790_a(-4.0f, 2.266667f, -1.0f, 8, 3, 3, par1 * 0.5f * 1.001f);
        this.S1breast.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.S1breast, -0.5235988f, 0.0f, 0.0f);
        this.S1Bbreast2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.S1Bbreast2.func_78790_a(-4.0f, 2.266667f, -1.0f, 0, 0, 0, par1 * 0.5f * 1.001f);
        this.S1Bbreast2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.S1breast2 = new ModelRenderer((ModelBase)this, 9, 23);
        this.S1breast2.field_78809_i = true;
        this.S1breast2.func_78790_a(-4.0f, 2.266667f, -2.0f, 8, 3, 3, par1 * 0.5f * 1.001f);
        this.S1breast2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.S1breast2, 0.5235988f, 3.141593f, 0.0f);
        this.S1bottom = new ModelRenderer((ModelBase)this, 16, 25);
        this.S1bottom.func_78790_a(-4.0f, 9.0f, -2.0f, 8, 3, 4, par1 * 0.5f * 1.001f);
        this.S1bottom.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.S1bottom, 0.0f, 0.0f, 0.0f);
        this.S1Bbreast.func_78792_a(this.S1breast);
        this.S1Bbreast2.func_78792_a(this.S1breast2);
        this.S1Bleftarm.func_78792_a(this.S1leftarm);
        this.S1Brightarm.func_78792_a(this.S1rightarm);
        this.S1bipedHead = new ModelRenderer((ModelBase)this, 0, 0);
        this.S1bipedHead.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, par1 * 1.001f);
        this.S1bipedHead.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.S1bipedBody = new ModelRenderer((ModelBase)this, 16, 16);
        this.S1bipedBody.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, par1 * 1.001f);
        this.S1bipedBody.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.S1bipedRightArm = new ModelRenderer((ModelBase)this, 40, 16);
        this.S1bipedRightArm.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 12, 4, par1 * 1.001f);
        this.S1bipedRightArm.func_78793_a(-5.0f, 2.0f + par2, 0.0f);
        this.S1bipedLeftArm = new ModelRenderer((ModelBase)this, 40, 16);
        this.S1bipedLeftArm.field_78809_i = true;
        this.S1bipedLeftArm.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, par1 * 1.001f);
        this.S1bipedLeftArm.func_78793_a(5.0f, 2.0f + par2, 0.0f);
        this.S1bipedRightLeg = new ModelRenderer((ModelBase)this, 0, 16);
        this.S1bipedRightLeg.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, par1 * 1.001f);
        this.S1bipedRightLeg.func_78793_a(-1.9f, 12.0f + par2, 0.0f);
        this.S1bipedLeftLeg = new ModelRenderer((ModelBase)this, 0, 16);
        this.S1bipedLeftLeg.field_78809_i = true;
        this.S1bipedLeftLeg.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, par1 * 1.001f);
        this.S1bipedLeftLeg.func_78793_a(1.9f, 12.0f + par2, 0.0f);
        this.Nam = new ModelRenderer((ModelBase)this, 0, 0);
        this.Nam.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.Nam.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.near1 = new ModelRenderer((ModelBase)this, 24, -2);
        this.near1.func_78789_a(-3.5f, -6.0f, -4.0f, 0, 4, 2);
        this.near1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.near1, -0.4014257f, 0.0f, -0.1745329f);
        this.near2 = new ModelRenderer((ModelBase)this, 24, -2);
        this.near2.func_78789_a(3.466667f, -6.0f, -4.0f, 0, 4, 2);
        this.near2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.near2, -0.4014257f, 0.0f, 0.1745329f);
        this.ant1 = new ModelRenderer((ModelBase)this, 24, 4);
        this.ant1.func_78789_a(0.0f, -5.0f, -8.0f, 1, 1, 2);
        this.ant1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ant1, -0.3490659f, -0.4363323f, 0.0f);
        this.ant2 = new ModelRenderer((ModelBase)this, 24, 4);
        this.ant2.func_78789_a(0.0f, -8.533334f, -6.2f, 1, 1, 2);
        this.ant2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ant2, 0.2094395f, -0.4364196f, 0.0f);
        this.ant3 = new ModelRenderer((ModelBase)this, 24, 4);
        this.ant3.func_78789_a(-1.0f, -5.0f, -8.0f, 1, 1, 2);
        this.ant3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ant3, -0.3490659f, 0.4363323f, 0.0f);
        this.ant4 = new ModelRenderer((ModelBase)this, 24, 4);
        this.ant4.func_78789_a(-1.0f, -8.533334f, -6.2f, 1, 1, 2);
        this.ant4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ant4, 0.2094395f, 0.4364196f, 0.0f);
        this.Nam.func_78792_a(this.ant1);
        this.Nam.func_78792_a(this.ant2);
        this.Nam.func_78792_a(this.ant3);
        this.Nam.func_78792_a(this.ant4);
        this.Fro = new ModelRenderer((ModelBase)this, 0, 0);
        this.Fro.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.Fro.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.Fro0 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Fro0.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.Fro0.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.Fro1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Fro1.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.Fro1.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.Fro2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Fro2.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.Fro2.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.Fro5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Fro5.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.Fro5.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.Fro5b = new ModelRenderer((ModelBase)this, 0, 0);
        this.Fro5b.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.Fro5b.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.Fro5r = new ModelRenderer((ModelBase)this, 0, 0);
        this.Fro5r.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.Fro5r.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.Fro5l = new ModelRenderer((ModelBase)this, 0, 0);
        this.Fro5l.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.Fro5l.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.FroB = new ModelRenderer((ModelBase)this, 0, 0);
        this.FroB.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 12, 0, 0.02f);
        this.FroB.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.appule = new ModelRenderer((ModelBase)this, 0, 16);
        this.appule.func_78789_a(-4.0f, -8.0f, 4.0f, 8, 8, 8);
        this.appule.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.appule, 0.0f, 0.0f, 0.0f);
        this.Fhorn2 = new ModelRenderer((ModelBase)this, 8, 6);
        this.Fhorn2.func_78789_a(1.5f, -11.0f, -3.5f, 2, 4, 2);
        this.Fhorn2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.Fhorn2, 0.0f, 0.0f, -0.7853982f);
        this.Fhorn1 = new ModelRenderer((ModelBase)this, 8, 6);
        this.Fhorn1.func_78789_a(-3.5f, -11.0f, -3.5f, 2, 4, 2);
        this.Fhorn1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.Fhorn1, 0.0f, 0.0f, 0.7853982f);
        this.Fhorn3 = new ModelRenderer((ModelBase)this, 8, 6);
        this.Fhorn3.func_78789_a(2.5f, -14.0f, -3.5f, 2, 4, 2);
        this.Fhorn3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.Fhorn3, 0.0f, 0.0f, 0.2094395f);
        this.Fhorn4 = new ModelRenderer((ModelBase)this, 8, 6);
        this.Fhorn4.func_78789_a(-4.5f, -14.0f, -3.5f, 2, 4, 2);
        this.Fhorn4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.Fhorn4, 0.0f, 0.0f, -0.2094395f);
        this.F2horn1 = new ModelRenderer((ModelBase)this, 16, 6);
        this.F2horn1.func_78789_a(-3.5f, -11.0f, 6.5f, 2, 4, 2);
        this.F2horn1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.F2horn1, 0.0f, 0.0f, 0.7853982f);
        this.F2horn2 = new ModelRenderer((ModelBase)this, 16, 6);
        this.F2horn2.func_78789_a(1.5f, -11.0f, 6.5f, 2, 4, 2);
        this.F2horn2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.F2horn2, 0.0f, 0.0f, -0.7853982f);
        this.ftail1 = new ModelRenderer((ModelBase)this, 32, 16);
        this.ftail1.func_78789_a(-2.0f, 7.0f, 4.0f, 4, 4, 12);
        this.ftail1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ftail1, -0.3490659f, 0.0f, 0.0f);
        this.ftail2 = new ModelRenderer((ModelBase)this, 32, 16);
        this.ftail2.func_78789_a(-2.0f, 15.0f, 2.0f, 4, 4, 12);
        this.ftail2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ftail2, 0.5235988f, 1.33E-5f, 0.0f);
        this.F5horn1 = new ModelRenderer((ModelBase)this, 8, 6);
        this.F5horn1.func_78789_a(-4.5f, -8.0f, -6.5f, 2, 6, 2);
        this.F5horn1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.F5horn1, -0.6981317f, 0.0f, 1.047198f);
        this.F5horn2 = new ModelRenderer((ModelBase)this, 8, 6);
        this.F5horn2.func_78789_a(2.5f, -8.0f, -6.5f, 2, 6, 2);
        this.F5horn2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.F5horn2, -0.6981317f, 0.0f, -1.047198f);
        this.F5horn3 = new ModelRenderer((ModelBase)this, 8, 6);
        this.F5horn3.func_78789_a(-0.5f, -10.0f, -8.0f, 2, 6, 2);
        this.F5horn3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.F5horn3, -0.6981317f, 0.0f, 0.2094395f);
        this.F5horn4 = new ModelRenderer((ModelBase)this, 8, 6);
        this.F5horn4.func_78789_a(-1.5f, -10.0f, -8.0f, 2, 6, 2);
        this.F5horn4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.F5horn4, -0.6981317f, 0.0f, -0.2094395f);
        this.F5horn5 = new ModelRenderer((ModelBase)this, 8, 6);
        this.F5horn5.func_78789_a(-2.5f, -7.0f, -7.2f, 5, 2, 2);
        this.F5horn5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.F5horn5, -0.5235988f, 0.0f, 0.0f);
        this.F5spike1 = new ModelRenderer((ModelBase)this, 0, 6);
        this.F5spike1.func_78789_a(-6.0f, 1.0f, -1.0f, 1, 5, 2);
        this.F5spike1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.F5spike1, 0.0f, 0.0f, -0.5235988f);
        this.F5spike2 = new ModelRenderer((ModelBase)this, 0, 6);
        this.F5spike2.func_78789_a(5.0f, 1.0f, -1.0f, 1, 5, 2);
        this.F5spike2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.F5spike2, 0.0f, 0.0f, 0.5235988f);
        this.F5spike3 = new ModelRenderer((ModelBase)this, 8, 38);
        this.F5spike3.func_78789_a(2.0f, -4.0f, 3.0f, 2, 6, 2);
        this.F5spike3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.F5spike3, -0.9773844f, 0.0f, 0.2094395f);
        this.F5spike4 = new ModelRenderer((ModelBase)this, 8, 38);
        this.F5spike4.func_78789_a(-4.0f, -4.0f, 3.0f, 2, 6, 2);
        this.F5spike4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.F5spike4, -0.9773844f, 0.0f, -0.2094395f);
        this.ftailS1 = new ModelRenderer((ModelBase)this, 38, 54);
        this.ftailS1.func_78789_a(-2.0f, -2.0f, 0.0f, 4, 4, 6);
        this.ftailS1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ftailS1, -0.5235988f, 0.0f, 0.0f);
        this.ftailS2 = new ModelRenderer((ModelBase)this, 38, 54);
        this.ftailS2.func_78789_a(-2.0f, -2.0f, 0.0f, 4, 4, 6);
        this.ftailS2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ftailS2, 0.5235988f, 8.727E-4f, 0.0f);
        this.ftailS3 = new ModelRenderer((ModelBase)this, 38, 54);
        this.ftailS3.func_78789_a(-2.0f, -2.0f, 0.0f, 4, 4, 6);
        this.ftailS3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ftailS3, 0.0f, 0.0f, 0.0f);
        this.ftailS4 = new ModelRenderer((ModelBase)this, 38, 54);
        this.ftailS4.func_78789_a(-2.0f, -2.0f, 0.0f, 4, 4, 6);
        this.ftailS4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ftailS4, 0.0f, 0.0f, 0.0f);
        this.ftailS5 = new ModelRenderer((ModelBase)this, 38, 54);
        this.ftailS5.func_78789_a(-2.0f, -2.0f, 0.0f, 4, 4, 6);
        this.ftailS5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ftailS5, 0.0f, 0.0f, 0.0f);
        this.ftailS6 = new ModelRenderer((ModelBase)this, 38, 54);
        this.ftailS6.func_78789_a(-2.0f, -2.0f, 0.0f, 4, 4, 6);
        this.ftailS6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.ftailS6, 0.0f, 0.0f, 0.0f);
        this.ftailS5.func_78792_a(this.ftailS6);
        this.ftailS4.func_78792_a(this.ftailS5);
        this.ftailS3.func_78792_a(this.ftailS4);
        this.ftailS2.func_78792_a(this.ftailS3);
        this.ftailS1.func_78792_a(this.ftailS2);
        this.FroB.func_78792_a(this.ftailS1);
        this.FroB.field_78800_c = 2.0f;
        this.FroB.field_78797_d = 10.0f;
        this.FroB.field_78798_e = 2.0f;
        this.ftailS1.field_78800_c = -2.0f;
        this.ftailS1.field_78797_d = -2.0f;
        this.ftailS1.field_78798_e = 0.0f;
        this.ftailS2.field_78800_c = 0.0f;
        this.ftailS2.field_78797_d = 0.0f;
        this.ftailS2.field_78798_e = 5.0f;
        this.ftailS3.field_78800_c = 0.0f;
        this.ftailS3.field_78797_d = 0.0f;
        this.ftailS3.field_78798_e = 5.0f;
        this.ftailS4.field_78800_c = 0.0f;
        this.ftailS4.field_78797_d = 0.0f;
        this.ftailS4.field_78798_e = 5.0f;
        this.ftailS5.field_78800_c = 0.0f;
        this.ftailS5.field_78797_d = 0.0f;
        this.ftailS5.field_78798_e = 5.0f;
        this.ftailS6.field_78800_c = 0.0f;
        this.ftailS6.field_78797_d = 0.0f;
        this.ftailS6.field_78798_e = 5.0f;
        this.fear1 = new ModelRenderer((ModelBase)this, 12, 0);
        this.fear1.func_78789_a(-5.0f, -5.0f, -3.0f, 1, 3, 2);
        this.fear1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.fear1, -0.4014257f, 0.0f, 0.0f);
        this.fear2 = new ModelRenderer((ModelBase)this, 12, 0);
        this.fear2.field_78809_i = true;
        this.fear2.func_78789_a(4.0f, -5.0f, -3.0f, 1, 3, 2);
        this.fear2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.fear2, -0.4014257f, 0.0f, 0.0f);
        this.rightarmshoulder = new ModelRenderer((ModelBase)this, 38, 0);
        this.rightarmshoulder.func_78789_a(-6.0f, -3.0f, -3.0f, 7, 4, 6);
        this.rightarmshoulder.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.rightarmshoulder.func_78787_b(128, 64);
        this.leftarmshoulder = new ModelRenderer((ModelBase)this, 38, 0);
        this.leftarmshoulder.field_78809_i = true;
        this.leftarmshoulder.func_78789_a(-1.0f, -3.0f, -3.0f, 7, 4, 6);
        this.leftarmshoulder.func_78793_a(5.0f, 2.0f, 0.0f);
        this.leftarmshoulder.func_78787_b(128, 64);
        this.Fro0.func_78792_a(this.Fhorn2);
        this.Fro0.func_78792_a(this.Fhorn1);
        this.Fro1.func_78792_a(this.Fhorn3);
        this.Fro1.func_78792_a(this.Fhorn4);
        this.Fro2.func_78792_a(this.appule);
        this.Fro2.func_78792_a(this.F2horn1);
        this.Fro2.func_78792_a(this.F2horn2);
        this.Fro.func_78792_a(this.fear1);
        this.Fro.func_78792_a(this.fear2);
        this.Fro5.func_78792_a(this.F5horn1);
        this.Fro5.func_78792_a(this.F5horn2);
        this.Fro5.func_78792_a(this.F5horn3);
        this.Fro5.func_78792_a(this.F5horn4);
        this.Fro5.func_78792_a(this.F5horn5);
        this.Fro5r.func_78792_a(this.F5spike1);
        this.Fro5l.func_78792_a(this.F5spike2);
        this.Fro5b.func_78792_a(this.F5spike3);
        this.Fro5b.func_78792_a(this.F5spike4);
        this.SaiE = new ModelRenderer((ModelBase)this, 0, 0);
        this.SaiE.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.SaiE.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.kao = new ModelRenderer((ModelBase)this, 0, 0);
        this.kao.func_78789_a(-4.0f, -8.0f, -4.005f, 8, 8, 0);
        this.kao.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.kao, 0.0f, 0.0f, 0.0f);
        this.SaiE.func_78792_a(this.kao);
        this.face1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.face1.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.face1.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.nose = new ModelRenderer((ModelBase)this, 0, 0);
        this.nose.func_78789_a(-4.0f, -8.0f, -4.006f, 8, 8, 0);
        this.nose.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.nose, 0.0f, 0.0f, 0.0f);
        this.face1.func_78792_a(this.nose);
        this.face2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.face2.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.face2.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.mouth = new ModelRenderer((ModelBase)this, 0, 0);
        this.mouth.func_78789_a(-4.0f, -8.0f, -4.007f, 8, 8, 0);
        this.mouth.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.mouth, 0.0f, 0.0f, 0.0f);
        this.face2.func_78792_a(this.mouth);
        this.face5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.face5.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.face5.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.eyeb = new ModelRenderer((ModelBase)this, 0, 0);
        this.eyeb.func_78789_a(-4.0f, -8.0f, -4.008f, 8, 8, 0);
        this.eyeb.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.eyeb, 0.0f, 0.0f, 0.0f);
        this.face5.func_78792_a(this.eyeb);
        this.face3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.face3.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.face3.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.eyel = new ModelRenderer((ModelBase)this, 0, 0);
        this.eyel.func_78789_a(-4.0f, -8.0f, -4.009f, 8, 8, 0);
        this.eyel.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.eyel, 0.0f, 0.0f, 0.0f);
        this.face3.func_78792_a(this.eyel);
        this.face4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.face4.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.face4.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.eyer = new ModelRenderer((ModelBase)this, 0, 0);
        this.eyer.func_78789_a(-4.0f, -8.0f, -4.01f, 8, 8, 0);
        this.eyer.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.eyer, 0.0f, 0.0f, 0.0f);
        this.face4.func_78792_a(this.eyer);
        this.face6 = new ModelRenderer((ModelBase)this, 0, 0);
        this.face6.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.face6.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.eyew = new ModelRenderer((ModelBase)this, 0, 0);
        this.eyew.func_78789_a(-4.0f, -8.0f, -4.01f, 8, 8, 0);
        this.eyew.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.eyew, 0.0f, 0.0f, 0.0f);
        this.face6.func_78792_a(this.eyew);
        this.SaiT1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.SaiT1.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.SaiT1.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.SaiT2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.SaiT2.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.SaiT2.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.tail1 = new ModelRenderer((ModelBase)this, 32, 48);
        this.tail1.func_78789_a(-1.0f, -1.0f, 0.0f, 2, 2, 4);
        this.tail1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.tail1, -0.5235988f, 0.0f, 0.0f);
        this.tail2 = new ModelRenderer((ModelBase)this, 32, 48);
        this.tail2.func_78789_a(-1.0f, -1.0f, 0.0f, 2, 2, 4);
        this.tail2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.tail2, 0.5235988f, 8.727E-4f, 0.0f);
        this.tailS3 = new ModelRenderer((ModelBase)this, 32, 48);
        this.tailS3.func_78789_a(-1.0f, -1.0f, 0.0f, 2, 2, 4);
        this.tailS3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.tailS3, 0.0f, 0.0f, 0.0f);
        this.tailS4 = new ModelRenderer((ModelBase)this, 32, 48);
        this.tailS4.func_78789_a(-1.0f, -1.0f, 0.0f, 2, 2, 4);
        this.tailS4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.tailS4, 0.0f, 0.0f, 0.0f);
        this.tailS5 = new ModelRenderer((ModelBase)this, 32, 48);
        this.tailS5.func_78789_a(-1.0f, -1.0f, 0.0f, 2, 2, 4);
        this.tailS5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.tailS5, 0.0f, 0.0f, 0.0f);
        this.tailS6 = new ModelRenderer((ModelBase)this, 32, 48);
        this.tailS6.func_78789_a(-1.0f, -1.0f, 0.0f, 2, 2, 4);
        this.tailS6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.tailS6, 0.0f, 0.0f, 0.0f);
        this.tail3 = new ModelRenderer((ModelBase)this, 32, 48);
        this.tail3.func_78789_a(3.5f, 8.0f, -2.5f, 1, 2, 5);
        this.tail3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.tail3, 0.0f, 0.0f, 0.0f);
        this.tail4 = new ModelRenderer((ModelBase)this, 32, 48);
        this.tail4.func_78789_a(-4.433333f, 8.0f, -2.5f, 1, 2, 5);
        this.tail4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.tail4, 0.0f, 0.0f, 0.0f);
        this.tail5 = new ModelRenderer((ModelBase)this, 32, 48);
        this.tail5.func_78789_a(-3.433333f, 8.0f, 1.5f, 7, 2, 1);
        this.tail5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.tail5, 0.0f, 0.0f, 0.0f);
        this.tail6 = new ModelRenderer((ModelBase)this, 32, 48);
        this.tail6.func_78789_a(-3.433333f, 8.0f, -2.5f, 7, 2, 1);
        this.tail6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.tail6, 0.0f, 0.0f, 0.0f);
        this.tailS5.func_78792_a(this.tailS6);
        this.tailS4.func_78792_a(this.tailS5);
        this.tailS3.func_78792_a(this.tailS4);
        this.tail2.func_78792_a(this.tailS3);
        this.tail1.func_78792_a(this.tail2);
        this.SaiT1.func_78792_a(this.tail1);
        this.SaiT2.func_78792_a(this.tail3);
        this.SaiT2.func_78792_a(this.tail4);
        this.SaiT2.func_78792_a(this.tail5);
        this.SaiT2.func_78792_a(this.tail6);
        this.SaiT1.field_78800_c = 1.0f;
        this.SaiT1.field_78797_d = 10.0f;
        this.SaiT1.field_78798_e = 2.0f;
        this.tail1.field_78800_c = -1.0f;
        this.tail1.field_78797_d = -1.0f;
        this.tail1.field_78798_e = 0.0f;
        this.tail2.field_78800_c = 0.0f;
        this.tail2.field_78797_d = 0.0f;
        this.tail2.field_78798_e = 4.0f;
        this.tailS3.field_78800_c = 0.0f;
        this.tailS3.field_78797_d = 0.0f;
        this.tailS3.field_78798_e = 4.0f;
        this.tailS4.field_78800_c = 0.0f;
        this.tailS4.field_78797_d = 0.0f;
        this.tailS4.field_78798_e = 4.0f;
        this.tailS5.field_78800_c = 0.0f;
        this.tailS5.field_78797_d = 0.0f;
        this.tailS5.field_78798_e = 4.0f;
        this.tailS6.field_78800_c = 0.0f;
        this.tailS6.field_78797_d = 0.0f;
        this.tailS6.field_78798_e = 4.0f;
        if (this.hairall == null) {
            int face;
            int hossz;
            this.hairall = new ModelRendererJBRA[224];
            for (hossz = 0; hossz < 4; ++hossz) {
                for (face = 0; face < 56; ++face) {
                    if (this.hairall[hossz + face * 4] != null) continue;
                    this.hairall[hossz + face * 4] = new ModelRendererJBRA((ModelBase)this, 32, 0);
                    this.hairall[hossz + face * 4].addBox(-1.0f, hossz == 0 ? -1.0f : 0.0f, -1.0f, 2, 3, 2);
                    this.hairall[hossz + face * 4].setRotationPoint(0.0f, 0.0f, 0.0f);
                    this.setRotation(this.hairall[hossz + face * 4], 0.0f, 0.0f, 0.0f);
                }
            }
            for (hossz = 0; hossz < 4; ++hossz) {
                for (face = 0; face < 56; ++face) {
                    if (hossz == 3) continue;
                    this.hairall[hossz + face * 4].addChild(this.hairall[hossz + 1 + face * 4]);
                }
            }
        }
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    private void setRotation(ModelRendererJBRA model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setF(float f) {
        ModelBipedJFC.f = f;
    }

    public void setG(int g) {
        ModelBipedJFC.g = g;
    }

    public float getF() {
        return f;
    }

    public int getG() {
        return g;
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        rot1 = par2;
        rot2 = par3;
        rot3 = par4;
        rot4 = par5;
        rot5 = par6;
        rot6 = par7;
        this.Entity = par1Entity;
        if (par1Entity instanceof EntityNPC) {
            EntityNPC e = (EntityNPC)par1Entity;
            this.dns = e.getDNS();
            this.b = JRMCoreH.dnsBreast(this.dns);
            if (this.dns.length() > 5) {
                g = JRMCoreH.dnsGender(this.dns) + 1;
            }
            this.age = e.getNPCgrw();
            this.b = JRMCoreH.dnsBreast(this.dns);
            f = this.age;
        }
        this.func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
    }

    public void renderBody(float par7, int skn) {
        this.renderBody(par7, skn, 4);
    }

    public void renderBody(float par7, int skn, int b) {
        if (g <= 1) {
            if (this.field_78091_s) {
                float f6 = 2.0f;
                GL11.glPushMatrix();
                GL11.glScalef((float)(1.5f / f6), (float)(1.5f / f6), (float)(1.5f / f6));
                GL11.glTranslatef((float)0.0f, (float)(16.0f * par7), (float)0.0f);
                if (skn == 0) {
                    this.field_78116_c.func_78785_a(par7);
                } else if (skn == 1) {
                    this.S1bipedHead.func_78785_a(par7);
                }
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
                GL11.glTranslatef((float)0.0f, (float)(24.0f * par7), (float)0.0f);
                if (skn == 0) {
                    this.field_78115_e.func_78785_a(par7);
                } else if (skn == 1) {
                    this.S1bipedBody.func_78785_a(par7);
                }
                if (skn == 0) {
                    this.field_78112_f.func_78785_a(par7);
                } else if (skn == 1) {
                    this.S1bipedRightArm.func_78785_a(par7);
                }
                if (skn == 0) {
                    this.field_78113_g.func_78785_a(par7);
                } else if (skn == 1) {
                    this.S1bipedLeftArm.func_78785_a(par7);
                }
                if (skn == 0) {
                    this.field_78123_h.func_78785_a(par7);
                } else if (skn == 1) {
                    this.S1bipedRightLeg.func_78785_a(par7);
                }
                if (skn == 0) {
                    this.field_78124_i.func_78785_a(par7);
                } else if (skn == 1) {
                    this.S1bipedLeftLeg.func_78785_a(par7);
                }
                GL11.glPopMatrix();
            } else {
                float f6 = f;
                GL11.glPushMatrix();
                GL11.glScalef((float)(0.5f + 0.5f / f6), (float)(0.5f + 0.5f / f6), (float)(0.5f + 0.5f / f6));
                GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) / f6 * (2.0f - (f6 >= 1.5f && f6 <= 2.0f ? (2.0f - f6) / 2.5f : (f6 < 1.5f && f6 >= 1.0f ? (f6 * 2.0f - 2.0f) * 0.2f : 0.0f)))), (float)0.0f);
                if (skn == 0) {
                    this.field_78116_c.func_78785_a(par7);
                } else if (skn == 1) {
                    this.S1bipedHead.func_78785_a(par7);
                }
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
                GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
                if (skn == 0) {
                    this.field_78115_e.func_78785_a(par7);
                } else if (skn == 1) {
                    this.S1bipedBody.func_78785_a(par7);
                }
                if (skn == 0) {
                    this.field_78112_f.func_78785_a(par7);
                } else if (skn == 1) {
                    this.S1bipedRightArm.func_78785_a(par7);
                }
                if (skn == 0) {
                    this.field_78113_g.func_78785_a(par7);
                } else if (skn == 1) {
                    this.S1bipedLeftArm.func_78785_a(par7);
                }
                if (skn == 0) {
                    this.field_78123_h.func_78785_a(par7);
                } else if (skn == 1) {
                    this.S1bipedRightLeg.func_78785_a(par7);
                }
                if (skn == 0) {
                    this.field_78124_i.func_78785_a(par7);
                } else if (skn == 1) {
                    this.S1bipedLeftLeg.func_78785_a(par7);
                }
                GL11.glPopMatrix();
            }
        } else {
            float bspeed;
            boolean bounce;
            float f5 = par7;
            float f6 = f;
            GL11.glPushMatrix();
            GL11.glScalef((float)((0.5f + 0.5f / f6) * (g <= 1 ? 1.0f : 0.85f)), (float)(0.5f + 0.5f / f6), (float)((0.5f + 0.5f / f6) * (g <= 1 ? 1.0f : 0.85f)));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) / f6 * (2.0f - (f6 >= 1.5f && f6 <= 2.0f ? (2.0f - f6) / 2.5f : (f6 < 1.5f && f6 >= 1.0f ? (f6 * 2.0f - 2.0f) * 0.2f : 0.0f)))), (float)0.0f);
            if (skn == 0) {
                this.field_78116_c.func_78785_a(f5);
            } else if (skn == 1) {
                this.S1bipedHead.func_78785_a(f5);
            }
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.7f)), (float)(1.0f / f6), (float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.7f)));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
            if (skn == 0) {
                this.Brightarm.func_78785_a(f5);
            } else if (skn == 1) {
                this.S1Brightarm.func_78785_a(f5);
            }
            if (skn == 0) {
                this.Bleftarm.func_78785_a(f5);
            } else if (skn == 1) {
                this.S1Bleftarm.func_78785_a(f5);
            }
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.85f)), (float)(1.0f / f6), (float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.775f)));
            if (this.field_78117_n) {
                GL11.glTranslatef((float)-0.015f, (float)((f6 - 1.0f) * 1.5f), (float)-0.0f);
            } else {
                GL11.glTranslatef((float)-0.015f, (float)((f6 - 1.0f) * 1.5f), (float)-0.015f);
            }
            if (skn == 0) {
                this.rightleg.func_78785_a(f5);
            } else if (skn == 1) {
                this.S1rightleg.func_78785_a(f5);
            }
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.85f)), (float)(1.0f / f6), (float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.775f)));
            if (this.field_78117_n) {
                GL11.glTranslatef((float)0.015f, (float)((f6 - 1.0f) * 1.5f), (float)-0.0f);
            } else {
                GL11.glTranslatef((float)0.015f, (float)((f6 - 1.0f) * 1.5f), (float)-0.015f);
            }
            if (skn == 0) {
                this.leftleg.func_78785_a(f5);
            } else if (skn == 1) {
                this.S1leftleg.func_78785_a(f5);
            }
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.675f)), (float)(1.0f / f6), (float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.8f)));
            float scale = (float)b * 0.03f;
            float br = 0.4235988f + scale;
            float bs = 0.8f + scale;
            float bsY = 0.85f + scale * 0.5f;
            float bt = 0.1f * scale;
            boolean bl = bounce = this.Entity.field_70122_E || this.Entity.func_70090_H();
            float f = this.Entity.func_70051_ag() ? 1.5f : (bspeed = this.Entity.func_70093_af() ? 0.5f : 1.0f);
            float bbY = (bounce ? MathHelper.func_76126_a((float)(rot1 * 0.6662f * bspeed * 1.5f + (float)Math.PI)) * rot2 * 0.03f : 0.0f) * ((float)b * 0.1119f);
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f + bbY), (float)(0.015f + bt));
            GL11.glScalef((float)1.0f, (float)bsY, (float)bs);
            this.setRotation(this.breast, -br, 0.0f, 0.0f);
            this.setRotation(this.S1breast, -br, 0.0f, 0.0f);
            this.setRotation(this.breast2, br, 3.141593f, 0.0f);
            this.setRotation(this.S1breast2, br, 3.141593f, 0.0f);
            if (bounce) {
                this.breast.field_78795_f += -MathHelper.func_76134_b((float)(rot1 * 0.6662f * bspeed + (float)Math.PI)) * rot2 * 0.05f * ((float)b * 0.1119f);
                this.breast.field_78796_g += MathHelper.func_76134_b((float)(rot1 * 0.6662f * bspeed + (float)Math.PI)) * rot2 * 0.02f * ((float)b * 0.1119f);
                this.breast2.field_78795_f += MathHelper.func_76134_b((float)(rot1 * 0.6662f * bspeed + (float)Math.PI)) * rot2 * 0.05f * ((float)b * 0.1119f);
                this.breast2.field_78796_g += MathHelper.func_76134_b((float)(rot1 * 0.6662f * bspeed + (float)Math.PI)) * rot2 * 0.02f * ((float)b * 0.1119f);
                this.S1breast.field_78795_f += -MathHelper.func_76134_b((float)(rot1 * 0.6662f * bspeed + (float)Math.PI)) * rot2 * 0.05f * ((float)b * 0.1119f);
                this.S1breast.field_78796_g += MathHelper.func_76134_b((float)(rot1 * 0.6662f * bspeed + (float)Math.PI)) * rot2 * 0.02f * ((float)b * 0.1119f);
                this.S1breast2.field_78795_f += MathHelper.func_76134_b((float)(rot1 * 0.6662f * bspeed + (float)Math.PI)) * rot2 * 0.05f * ((float)b * 0.1119f);
                this.S1breast2.field_78796_g += MathHelper.func_76134_b((float)(rot1 * 0.6662f * bspeed + (float)Math.PI)) * rot2 * 0.02f * ((float)b * 0.1119f);
            }
            if (skn == 0) {
                this.Bbreast.func_78785_a(f5);
            } else if (skn == 1) {
                this.S1Bbreast.func_78785_a(f5);
            }
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.7f)), (float)(1.0f / f6), (float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.7f)));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
            if (skn == 0) {
                this.body.func_78785_a(f5);
            } else if (skn == 1) {
                this.S1body.func_78785_a(f5);
            }
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.75f)), (float)(1.0f / f6), (float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.75f) * (1.0f + 0.005f * (float)p)));
            if (this.field_78117_n) {
                GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
            } else {
                GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)(-0.018f - 5.0E-4f * (float)p));
            }
            if (skn == 0) {
                this.hip.func_78785_a(f5);
            } else if (skn == 1) {
                this.S1hip.func_78785_a(f5);
            }
            GL11.glPopMatrix();
            if (p >= 30) {
                // empty if block
            }
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.65f)), (float)(1.0f / f6), (float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.65f) * (1.0f + 0.001f * (float)p)));
            if (this.field_78117_n) {
                GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
            } else {
                GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)(-0.04f - 1.0E-4f * (float)p));
            }
            if (skn == 0) {
                this.waist.func_78785_a(f5);
            } else if (skn == 1) {
                this.S1waist.func_78785_a(f5);
            }
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.85f)), (float)(1.0f / f6), (float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.85f) * (1.0f + 0.005f * (float)p)));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)(0.001f - 5.0E-4f * (float)p));
            if (skn == 0) {
                this.bottom.func_78785_a(f5);
            } else if (skn == 1) {
                this.S1bottom.func_78785_a(f5);
            }
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.675f) - 0.001f), (float)(1.0f / f6), (float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.8f) - 0.001f));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f + 0.001f + bbY), (float)(0.015f + bt));
            GL11.glScalef((float)1.0f, (float)bsY, (float)bs);
            if (skn == 0) {
                this.Bbreast2.func_78785_a(f5);
            } else if (skn == 1) {
                this.S1Bbreast2.func_78785_a(f5);
            }
            GL11.glPopMatrix();
        }
    }

    public void modelCheck() {
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

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        float f7;
        float f6;
        if (g >= 2) {
            this.H = this.field_78116_c = this.S1bipedHead;
            this.RA = this.Brightarm = this.S1Brightarm;
            this.LA = this.Bleftarm = this.S1Bleftarm;
            this.RL = this.rightleg = this.S1rightleg;
            this.LL = this.leftleg = this.S1leftleg;
            this.B = this.Bbreast = this.S1Bbreast;
            this.B1 = this.body = this.S1body;
            this.B2 = this.hip = this.S1hip;
            this.B3 = this.waist = this.S1waist;
            this.B4 = this.bottom = this.S1bottom;
            this.B5 = this.Bbreast2 = this.S1Bbreast2;
            this.B7 = this.Bbreast2 = this.S1Bbreast2;
            this.B9 = this.Bbreast2 = this.S1Bbreast2;
        } else {
            this.H = this.field_78116_c = this.S1bipedHead;
            this.RA = this.field_78112_f = this.S1bipedRightArm;
            this.LA = this.field_78113_g = this.S1bipedLeftArm;
            this.RL = this.field_78123_h = this.S1bipedRightLeg;
            this.LL = this.field_78124_i = this.S1bipedLeftLeg;
            this.B9 = this.field_78115_e = this.S1bipedBody;
            this.B7 = this.field_78115_e;
            this.B5 = this.field_78115_e;
            this.B4 = this.field_78115_e;
            this.B3 = this.field_78115_e;
            this.B2 = this.field_78115_e;
            this.B1 = this.field_78115_e;
            this.B = this.field_78115_e;
        }
        this.H.field_78796_g = par4 / 57.295776f;
        if (y == 1) {
            this.H.field_78795_f = par5 / 57.295776f;
            this.RA.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.5f;
            this.LA.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.5f;
        } else {
            this.H.field_78795_f = par5 / 57.295776f;
            this.RA.field_78795_f = 0.0f;
            this.LA.field_78795_f = 0.0f;
        }
        this.RA.field_78808_h = 0.0f;
        this.LA.field_78808_h = 0.0f;
        if (y == 1) {
            this.RL.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.4f * par2;
            this.LL.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
        } else {
            this.RL.field_78795_f = 0.0f;
            this.LL.field_78795_f = 0.0f;
        }
        this.RL.field_78796_g = 0.0f;
        this.LL.field_78796_g = 0.0f;
        if (this.field_78093_q) {
            this.RA.field_78795_f += -0.62831855f;
            this.LA.field_78795_f += -0.62831855f;
            this.RL.field_78795_f = -1.2566371f;
            this.LL.field_78795_f = -1.2566371f;
            this.RL.field_78796_g = 0.31415927f;
            this.LL.field_78796_g = -0.31415927f;
        }
        if (this.field_78119_l != 0) {
            this.LA.field_78795_f = this.LA.field_78795_f * 0.5f - 0.31415927f * (float)this.field_78119_l;
        }
        if (this.field_78120_m != 0) {
            this.RA.field_78795_f = this.RA.field_78795_f * 0.5f - 0.31415927f * (float)this.field_78120_m;
        }
        this.RA.field_78796_g = 0.0f;
        this.LA.field_78796_g = 0.0f;
        if (this.field_78095_p > -9990.0f) {
            f6 = this.field_78095_p;
            this.B7.field_78796_g = this.B9.field_78796_g = (this.B.field_78796_g = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)f6) * (float)Math.PI * 2.0f)) * 0.2f);
            this.B5.field_78796_g = this.B9.field_78796_g;
            this.B4.field_78796_g = this.B9.field_78796_g;
            this.B3.field_78796_g = this.B9.field_78796_g;
            this.B2.field_78796_g = this.B9.field_78796_g;
            this.B1.field_78796_g = this.B9.field_78796_g;
            this.RA.field_78798_e = MathHelper.func_76126_a((float)this.B.field_78796_g) * 5.0f;
            this.RA.field_78800_c = -MathHelper.func_76134_b((float)this.B.field_78796_g) * 5.0f;
            this.LA.field_78798_e = -MathHelper.func_76126_a((float)this.B.field_78796_g) * 5.0f;
            this.LA.field_78800_c = MathHelper.func_76134_b((float)this.B.field_78796_g) * 5.0f;
            this.RA.field_78796_g += this.B.field_78796_g;
            this.LA.field_78796_g += this.B.field_78796_g;
            this.LA.field_78795_f += this.B.field_78795_f;
            f6 = 1.0f - this.field_78095_p;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0f - f6;
            f7 = MathHelper.func_76126_a((float)(f6 * (float)Math.PI));
            float f8 = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -(this.H.field_78795_f - 0.7f) * 0.75f;
            this.RA.field_78795_f = (float)((double)this.RA.field_78795_f - ((double)f7 * 1.2 + (double)f8));
            this.RA.field_78796_g += this.B.field_78796_g * 2.0f;
            this.RA.field_78808_h = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -0.4f;
        }
        if (this.field_78117_n) {
            this.B7.field_78795_f = this.B9.field_78795_f = (this.B.field_78795_f = 0.5f);
            this.B5.field_78795_f = this.B9.field_78795_f;
            this.B4.field_78795_f = this.B9.field_78795_f;
            this.B3.field_78795_f = this.B9.field_78795_f;
            this.B2.field_78795_f = this.B9.field_78795_f;
            this.B1.field_78795_f = this.B9.field_78795_f;
            this.RA.field_78795_f += 0.4f;
            this.LA.field_78795_f += 0.4f;
            this.RL.field_78798_e = 4.0f;
            this.LL.field_78798_e = 4.0f;
            this.RL.field_78797_d = 9.0f;
            this.LL.field_78797_d = 9.0f;
            this.H.field_78797_d = 1.0f;
        } else {
            this.B7.field_78795_f = this.B9.field_78795_f = (this.B.field_78795_f = 0.0f);
            this.B5.field_78795_f = this.B9.field_78795_f;
            this.B4.field_78795_f = this.B9.field_78795_f;
            this.B3.field_78795_f = this.B9.field_78795_f;
            this.B2.field_78795_f = this.B9.field_78795_f;
            this.B1.field_78795_f = this.B9.field_78795_f;
            this.RL.field_78798_e = 0.1f;
            this.LL.field_78798_e = 0.1f;
            this.RL.field_78797_d = 12.0f;
            this.LL.field_78797_d = 12.0f;
            this.H.field_78797_d = 0.0f;
        }
        this.RA.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.LA.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.RA.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        this.LA.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        if (this.field_78118_o) {
            f6 = 0.0f;
            f7 = 0.0f;
            this.RA.field_78808_h = 0.0f;
            this.LA.field_78808_h = 0.0f;
            this.RA.field_78796_g = -(0.1f - f6 * 0.6f) + this.H.field_78796_g;
            this.LA.field_78796_g = 0.1f - f6 * 0.6f + this.H.field_78796_g + 0.4f;
            this.RA.field_78795_f = -1.5707964f + this.H.field_78795_f;
            this.LA.field_78795_f = -1.5707964f + this.H.field_78795_f;
            this.RA.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
            this.LA.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
            this.RA.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.LA.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.RA.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
            this.LA.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        }
        this.field_78118_o = false;
    }

    public static String sa(String s1, int s2) {
        return s1.charAt(s2) + "";
    }

    public static int dnsHair1(String s, int n) {
        return s.length() > 3 ? Integer.parseInt(ModelBipedJFC.sa(s, n)) : 0;
    }

    public static int dnsHair2(String s, int n) {
        return s.length() > 3 ? Integer.parseInt(ModelBipedJFC.sa(s, n) + ModelBipedJFC.sa(s, n + 1)) : 0;
    }

    public static String dnsHair1set(String s, int n, String w) {
        return s.length() > 3 ? s.substring(0, n) + w + s.substring(n + 1) : "";
    }

    public static String dnsHair2set(String s, int n, String w) {
        return s.length() > 3 ? s.substring(0, n) + w + s.substring(n + 2) : "";
    }

    public void renderHairsV2(float par1, String hair, float hl, int state, int rg, int pl, int race, RenderJFC renderJFC) {
        int arTime;
        boolean pstrty = false;
        boolean aura = false;
        boolean trbo = false;
        boolean kken = false;
        boolean trty = false;
        int trTime = detail ? 2 : 200;
        int n = arTime = detail ? 2 : 200;
        if (race == 1 || race == 2) {
            if (renderJFC.getState(pl) == 0 && state >= 1) {
                if (renderJFC.getState(pl) != state && renderJFC.getStateChange(pl) < 200) {
                    renderJFC.setStateChange(renderJFC.getStateChange(pl) + trTime, pl);
                }
                if (renderJFC.getStateChange(pl) >= 200) {
                    renderJFC.setStateChange(200, pl);
                    renderJFC.setState(state, pl);
                }
            } else if (renderJFC.getState(pl) >= 1 && state == 0) {
                if ((renderJFC.getState(pl) != state || rg == 0) && renderJFC.getStateChange(pl) > 0) {
                    renderJFC.setStateChange(renderJFC.getStateChange(pl) - trTime, pl);
                }
                if (renderJFC.getStateChange(pl) <= 0) {
                    renderJFC.setStateChange(0, pl);
                    renderJFC.setState(state, pl);
                }
            } else if (renderJFC.getState(pl) != state && (renderJFC.getState(pl) == 1 || renderJFC.getState(pl) == 4) && state == 2) {
                renderJFC.setState(state, pl);
            } else if (renderJFC.getState(pl) == 0) {
                if (!detail && renderJFC.getState(pl) == state && rg > 90) {
                    renderJFC.setStateChange(renderJFC.getStateChange(pl) + trTime, pl);
                    if (renderJFC.getStateChange(pl) > 200) {
                        renderJFC.setStateChange(200, pl);
                    }
                } else if (detail && renderJFC.getState(pl) == state && rg > 0 && renderJFC.getStateChange(pl) < rg * 2) {
                    renderJFC.setStateChange(renderJFC.getStateChange(pl) + trTime, pl);
                } else if (renderJFC.getState(pl) == state) {
                    if (renderJFC.getStateChange(pl) > 0) {
                        renderJFC.setStateChange(renderJFC.getStateChange(pl) - trTime, pl);
                    } else {
                        renderJFC.setStateChange(0, pl);
                    }
                    if (renderJFC.getState2Change(pl) > 0) {
                        renderJFC.setState2Change(renderJFC.getState2Change(pl) - trTime, pl);
                    } else {
                        renderJFC.setState2Change(0, pl);
                    }
                }
            } else if (state == 4 && pstrty || state == 2) {
                if (!detail && renderJFC.getState(pl) == state && rg > 90) {
                    renderJFC.setState2Change(renderJFC.getState2Change(pl) + trTime, pl);
                    if (renderJFC.getState2Change(pl) > 200) {
                        renderJFC.setState2Change(200, pl);
                    }
                } else if (detail && renderJFC.getState(pl) == state && rg > 0 && renderJFC.getState2Change(pl) < rg * 2) {
                    renderJFC.setState2Change(renderJFC.getState2Change(pl) + trTime, pl);
                } else if (renderJFC.getState2Change(pl) > 200) {
                    renderJFC.setState2Change(200, pl);
                    renderJFC.setState(state, pl);
                } else if (renderJFC.getState2Change(pl) > 0) {
                    renderJFC.setState2Change(renderJFC.getState2Change(pl) - trTime, pl);
                } else if (renderJFC.getState2Change(pl) != 0) {
                    renderJFC.setState2Change(0, pl);
                }
            } else if (renderJFC.getState(pl) != state && (state == 5 || state == 3)) {
                if (renderJFC.getState2Change(pl) < 200) {
                    renderJFC.setState2Change(renderJFC.getState2Change(pl) + trTime, pl);
                }
                if (renderJFC.getState2Change(pl) >= 200) {
                    renderJFC.setState2Change(200, pl);
                    renderJFC.setState(state, pl);
                }
            }
        }
        if ((aura || trty || kken || trbo) && detail) {
            if (renderJFC.getState(pl) == state && renderJFC.getAuratime(pl) < 50) {
                if (renderJFC.getAuratime(pl) < 50 && renderJFC.getAuratype(pl) == 0) {
                    renderJFC.setAuratime(renderJFC.getAuratime(pl) + arTime, pl);
                }
                if (renderJFC.getAuratime(pl) >= 50) {
                    renderJFC.setAuratype(1, pl);
                }
                if (renderJFC.getAuratime(pl) < 20 && renderJFC.getAuratype(pl) == 1) {
                    renderJFC.setAuratype(0, pl);
                }
                if (renderJFC.getAuratime(pl) > 0 && renderJFC.getAuratype(pl) == 1) {
                    renderJFC.setAuratime(renderJFC.getAuratime(pl) - arTime, pl);
                }
            } else if (renderJFC.getState(pl) == state && state >= 1) {
                if (renderJFC.getAuratype(pl) < 2) {
                    renderJFC.setAuratype(2, pl);
                }
                if (renderJFC.getBendtime(pl) < 50 && renderJFC.getAuratype(pl) == 2) {
                    renderJFC.setBendtime(renderJFC.getBendtime(pl) + arTime, pl);
                }
                if (renderJFC.getBendtime(pl) >= 50) {
                    renderJFC.setAuratype(3, pl);
                }
                if (renderJFC.getBendtime(pl) < 20 && renderJFC.getAuratype(pl) == 3) {
                    renderJFC.setAuratype(2, pl);
                }
                if (renderJFC.getBendtime(pl) > 0 && renderJFC.getAuratype(pl) == 3) {
                    renderJFC.setBendtime(renderJFC.getBendtime(pl) - arTime, pl);
                }
            }
        } else {
            if (renderJFC.getAuratype(pl) > 0) {
                renderJFC.setAuratype(0, pl);
            }
            if (renderJFC.getBendtime(pl) > 0) {
                renderJFC.setBendtime(renderJFC.getBendtime(pl) - 1, pl);
            }
            if (renderJFC.getAuratime(pl) > 0) {
                renderJFC.setAuratime(renderJFC.getAuratime(pl) - 1, pl);
            }
        }
        GL11.glPushMatrix();
        GL11.glScalef((float)((0.5f + 0.5f / f) * (g <= 1 ? 1.0f : 0.85f)), (float)(0.5f + 0.5f / f), (float)((0.5f + 0.5f / f) * (g <= 1 ? 1.0f : 0.85f)));
        GL11.glTranslatef((float)0.0f, (float)((f - 1.0f) / f * (2.0f - (f >= 1.5f && f <= 2.0f ? (2.0f - f) / 2.5f : (f < 1.5f && f >= 1.0f ? (f * 2.0f - 2.0f) * 0.2f : 0.0f)))), (float)0.0f);
        float[] front = new float[]{0.6f, 0.5f, 0.4f, -0.5f};
        float[] front2 = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
        int[] hairRightPosZ = new int[]{3, 2, 1, 0, 3, 2, 1, 3, 2, 3};
        int[] hairRightPosY = new int[]{0, 0, 0, 0, 1, 1, 1, 2, 2, 3};
        int[] hairLeftPosZ = new int[]{0, 1, 2, 3, 1, 2, 3, 2, 3, 3};
        int[] hairLeftPosY = new int[]{0, 0, 0, 0, 1, 1, 1, 2, 2, 3};
        int[] hairBackPosX = new int[]{0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3};
        int[] hairBackPosY = new int[]{0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3};
        int[] hairTopPosX = new int[]{0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3};
        int[] hairTopPosZ = new int[]{0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3};
        int[] hairPos = new int[]{0, 4, 14, 24, 40, 56};
        String hairdns = hair;
        for (int face = 0; face < 56; ++face) {
            float tincs2;
            float tincs1;
            boolean hpBack;
            int l = ModelBipedJFC.dnsHair2(hairdns, face * 14);
            if (l == 0) continue;
            int X = ModelBipedJFC.dnsHair2(hairdns, face * 14 + 2);
            int Y = ModelBipedJFC.dnsHair2(hairdns, face * 14 + 4);
            int Z = ModelBipedJFC.dnsHair2(hairdns, face * 14 + 6);
            int B = ModelBipedJFC.dnsHair2(hairdns, face * 14 + 8);
            int P = ModelBipedJFC.dnsHair2(hairdns, face * 14 + 10);
            int T = ModelBipedJFC.dnsHair2(hairdns, face * 14 + 12);
            int n2 = X > 82 ? 82 : (X = X < 18 ? 18 : X);
            int n3 = Y > 82 ? 82 : (Y = Y < 18 ? 18 : Y);
            int n4 = Z > 82 ? 82 : (Z = Z < 18 ? 18 : Z);
            int n5 = B > 82 ? 82 : (B = B < 18 ? 18 : B);
            int n6 = P > 82 ? 82 : (P = P < 18 ? 18 : P);
            T = T > 82 ? 82 : (T < 18 ? 18 : T);
            float x = (float)(X - 50) * 0.1f;
            float y = (float)(Y - 50) * 0.1f;
            float z = (float)(Z - 50) * 0.1f;
            float b = (float)(B - 50) * 0.1f;
            float p = (float)(P - 50) * 0.1f;
            int t = (int)((float)(T - 18) * 1.62f);
            float Int = (float)t * 0.01f;
            float pb = b;
            boolean hpFront = face >= hairPos[0] && face < hairPos[1];
            boolean hpTop = face >= hairPos[4] && face < hairPos[5];
            boolean hpRight = face >= hairPos[1] && face < hairPos[2];
            boolean hpLeft = face >= hairPos[2] && face < hairPos[3];
            boolean bl = hpBack = face >= hairPos[3] && face < hairPos[4];
            if (renderJFC.getStateChange(pl) > 0 && l > 0) {
                if (y > -1.0f && y < 1.0f && z > -1.0f && z < 1.0f && hpBack) {
                    x = x > 3.0f ? 3.0f : (x += (float)renderJFC.getStateChange(pl) * Int * (x < 0.0f ? -0.01f : 0.01f) * ((float)l * 0.01f));
                    float f = x = x < -3.0f ? -3.0f : x;
                }
                if (y > -1.0f && y < 1.0f && x > -1.0f && x < 1.0f && !hpBack) {
                    boolean add;
                    z = z > 3.2f ? 3.2f : (z += (float)renderJFC.getStateChange(pl) * Int * (z < 0.0f ? -0.01f : 0.01f));
                    float f = z = z < -3.2f ? -3.2f : z;
                    if (!hpFront || x < 0.0f) {
                        x = (x += (float)renderJFC.getStateChange(pl) * Int * 0.01f) > 0.4f ? 0.4f : x;
                        float f2 = x = x < -0.4f ? -0.4f : x;
                    }
                    if (z > 0.0f) {
                        boolean bl2 = hpTop ? hairTopPosZ[face - hairPos[4]] == 0 || hairTopPosZ[face - hairPos[4]] == 2 : (add = false);
                        boolean add2 = hpTop ? face % 4 == 0 || face % 4 == 3 : false;
                        b = b < (add && add2 ? 0.0f : -0.2f) ? (add && add2 ? 0.0f : -0.2f) : (b += (float)renderJFC.getStateChange(pl) * Int * -0.02f);
                    } else if (z < 0.0f) {
                        boolean bl3 = hpTop ? hairTopPosZ[face - hairPos[4]] == 0 || hairTopPosZ[face - hairPos[4]] == 2 : (add = false);
                        boolean add2 = hpTop ? face % 4 == 0 || face % 4 == 3 : false;
                        b = b > (add && add2 ? 0.0f : 0.2f) ? (add && add2 ? 0.0f : 0.2f) : (b += (float)renderJFC.getStateChange(pl) * Int * 0.02f);
                    }
                } else if (y > -1.0f && y < 1.0f) {
                    x = x > 2.8f ? 2.8f : (x += (float)renderJFC.getStateChange(pl) * Int * (x < 0.0f ? -0.01f : 0.01f));
                    float f = x = x < -2.8f ? -2.8f : x;
                    if (b > 1.5f) {
                        x = x > 1.5f ? 1.5f : x;
                        x = x < -1.5f ? -1.5f : x;
                        b = b > 2.8f ? 2.8f : (b += (float)renderJFC.getStateChange(pl) * Int * (b < 0.0f ? 0.03f : -0.03f));
                        b = b < -2.8f ? -2.8f : b;
                    }
                } else if (x > -1.0f && x < 1.0f) {
                    z = z > 2.8f ? 2.8f : (z += (float)renderJFC.getStateChange(pl) * Int * (z < 0.0f ? -0.01f : 0.01f));
                    float f = z = z < -2.8f ? -2.8f : z;
                    if (b > 0.0f && z > 0.0f && y < 1.6f) {
                        z = z > 2.2f ? 2.2f : z;
                        z = z < -2.2f ? -2.2f : z;
                        b = b > pb ? pb : (b += (float)renderJFC.getStateChange(pl) * Int * -0.02f);
                        b = b < -pb ? -pb : b;
                    } else if (b > 0.0f && z < 0.0f && y > 0.0f) {
                        z = z > 2.2f ? 2.2f : z;
                        z = z < -2.2f ? -2.2f : z;
                        b = b > pb ? pb : (b += (float)renderJFC.getStateChange(pl) * Int * -0.02f);
                        b = b < -pb ? -pb : b;
                    } else if (y < -1.3f && b > 0.0f) {
                        z = z > 2.2f ? 2.2f : z;
                        z = z < -2.2f ? -2.2f : z;
                        b += (float)renderJFC.getStateChange(pl) * Int * -0.02f;
                        float f3 = b = b < 0.5f ? 0.5f : b;
                    }
                }
            }
            if (renderJFC.getState2Change(pl) > 0) {
                if (y > -1.0f && y < 1.0f && x > -1.0f && x < 1.0f && hpFront) {
                    float Int2 = Int > 0.02f ? 0.6f : Int;
                    x = x > 0.2f ? 0.2f : (x += (float)renderJFC.getState2Change(pl) * Int2 * 0.01f);
                    x = x < -0.2f ? -0.2f : x;
                    z = z > 2.8f ? 2.8f : (z += (float)renderJFC.getState2Change(pl) * Int2 * (z < 0.0f ? -0.02f : 0.02f));
                    z = z < -2.8f ? -2.8f : z;
                }
                l = (int)((float)l + (float)renderJFC.getState2Change(pl) * 0.1f);
                if (b < 0.0f) {
                    float f = b = (b += (float)renderJFC.getState2Change(pl) * 5.0E-4f) >= 0.0f ? 0.2f : b;
                }
                if (b > 0.0f) {
                    float f = b = (b += (float)renderJFC.getState2Change(pl) * -5.0E-4f) <= 0.0f ? -0.2f : b;
                }
            }
            if (renderJFC.getBendtime(pl) > 0) {
                b += (float)renderJFC.getBendtime(pl) * (b > 0.0f ? -0.005f : 0.005f);
                z = z > 3.2f ? 3.2f : (z += (float)renderJFC.getBendtime(pl) * (z < 0.0f ? -0.0025f : 0.0025f));
                float f = z = z < -3.2f ? -3.2f : z;
            }
            if (renderJFC.getAuratime(pl) > 0) {
                b += (float)renderJFC.getAuratime(pl) * (b > 0.0f ? -0.005f : 0.005f);
                z = z > 3.2f ? 3.2f : (z += (float)renderJFC.getAuratime(pl) * (z < 0.0f ? -0.0025f : 0.0025f));
                z = z < -3.2f ? -3.2f : z;
            }
            int lng = 0;
            this.setRotation(this.hairall[lng + face * 4], x, y, z);
            this.hairall[lng + face * 4].rotationPointX = -2.999f + (float)(face < 4 ? face * 2 : (face >= 14 && face < 24 ? 7 : (face >= 24 && face < 40 ? hairBackPosX[face - 4 - 10 - 10] * 2 : (face >= 40 && face < 56 ? hairTopPosX[face - 4 - 10 - 10 - 16] * 2 : -1))));
            this.hairall[lng + face * 4].rotationPointZ = -3.999f + (face >= 4 && face < 14 ? (float)(hairRightPosZ[face - 4] * 2 + 1) : (face >= 14 && face < 24 ? (float)(hairLeftPosZ[face - 4 - 10] * 2 + 1) : (face >= 24 && face < 40 ? 8.0f : (face >= 40 && face < 56 ? (float)(hairTopPosZ[face - 4 - 10 - 10 - 16] * 2) + 0.9f : 0.0f))));
            this.hairall[lng + face * 4].rotationPointY = -7.0f + (face >= 4 && face < 14 ? (float)(hairRightPosY[face - 4] * 2) : (face >= 14 && face < 24 ? (float)(hairLeftPosY[face - 4 - 10] * 2) : (face >= 24 && face < 40 ? (float)(hairBackPosY[face - 4 - 10 - 10] * 2) : -0.5f)));
            float f = 1.57f;
            float r = MathHelper.func_76126_a((float)(rot3 * 0.02f)) * 0.1f;
            float r2 = MathHelper.func_76134_b((float)(rot3 * 0.02f)) * 0.1f;
            float r3 = MathHelper.func_76134_b((float)(rot3 * 0.14f)) * 0.1f;
            this.hairall[1 + face * 4].rotateAngleY = 0.0f;
            this.hairall[1 + face * 4].rotateAngleX = -0.0f;
            this.hairall[2 + face * 4].rotateAngleY = 0.0f;
            this.hairall[2 + face * 4].rotateAngleX = 0.0f;
            this.hairall[3 + face * 4].rotateAngleY = 0.0f;
            this.hairall[3 + face * 4].rotateAngleX = 0.0f;
            if (hpTop || hpRight || hpLeft) {
                int min = hpLeft ? 1 : -1;
                this.hairall[1 + face * 4].rotateAngleZ = (float)min * b * (0.3f * (p > 0.5f ? 1.0f - p * 0.3f : (p < -0.5f ? 1.0f + -p * 0.1f : 1.0f)));
                this.hairall[2 + face * 4].rotateAngleZ = (float)min * b * 0.3f;
                this.hairall[3 + face * 4].rotateAngleZ = (float)min * b * (0.3f * (p > 0.5f ? 1.0f + p * 0.1f : (p < -0.5f ? 1.0f - -p * 0.3f : 1.0f)));
            } else {
                this.hairall[1 + face * 4].rotateAngleX = b * (0.3f * (p > 0.5f ? 1.0f - p * 0.3f : (p < -0.5f ? 1.0f + -p * 0.1f : 1.0f)));
                this.hairall[2 + face * 4].rotateAngleX = b * 0.3f;
                this.hairall[3 + face * 4].rotateAngleX = b * (0.3f * (p > 0.5f ? 1.0f + p * 0.1f : (p < -0.5f ? 1.0f - -p * 0.3f : 1.0f)));
            }
            this.hairall[1 + face * 4].rotationPointX = 0.0f;
            this.hairall[1 + face * 4].rotationPointZ = 0.0f;
            this.hairall[1 + face * 4].rotationPointY = 1.5f;
            this.hairall[2 + face * 4].rotationPointX = 0.0f;
            this.hairall[2 + face * 4].rotationPointZ = 0.0f;
            this.hairall[2 + face * 4].rotationPointY = 2.5f;
            this.hairall[3 + face * 4].rotationPointX = 0.0f;
            this.hairall[3 + face * 4].rotationPointZ = 0.0f;
            this.hairall[3 + face * 4].rotationPointY = 2.5f;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(this.field_78116_c.field_78800_c * par1), (float)(this.field_78116_c.field_78797_d * par1), (float)(this.field_78116_c.field_78798_e * par1));
            if (this.field_78116_c.field_78808_h != 0.0f) {
                GL11.glRotatef((float)(this.field_78116_c.field_78808_h * 57.295776f), (float)0.0f, (float)0.0f, (float)1.0f);
            }
            if (this.field_78116_c.field_78796_g != 0.0f) {
                GL11.glRotatef((float)(this.field_78116_c.field_78796_g * 57.295776f), (float)0.0f, (float)1.0f, (float)0.0f);
            }
            if (this.field_78116_c.field_78795_f != 0.0f) {
                GL11.glRotatef((float)(this.field_78116_c.field_78795_f * 57.295776f), (float)1.0f, (float)0.0f, (float)0.0f);
            }
            GL11.glPushMatrix();
            float[] TypL1 = new float[]{4.0f, 2.0f, 1.5f, 1.0f, 1.0f};
            boolean[] TypS1 = new boolean[]{false, true, true, true, true};
            boolean[] TypS2 = new boolean[]{false, false, true, true, true};
            boolean[] TypS3 = new boolean[]{false, false, false, true, true};
            float f4 = tincs1 = (float)l < 33.0f ? (float)l / 33.0f : 1.0f;
            float f5 = (float)l > 33.0f && (float)l < 66.0f ? ((float)l - 33.0f) / 33.0f : (tincs2 = (float)l < 33.0f ? 0.0f : 1.0f);
            float tincs3 = (float)l > 66.0f ? ((float)l - 66.0f) / 33.0f : ((float)l < 66.0f ? 0.0f : 1.0f);
            this.hairall[lng + face * 4].lengthY = 1.0f;
            this.hairall[1 + face * 4].lengthY = tincs1;
            this.hairall[2 + face * 4].lengthY = tincs2;
            this.hairall[3 + face * 4].lengthY = tincs3;
            this.hairall[0 + face * 4].sizeXZ = 1.1f;
            this.hairall[1 + face * 4].sizeXZ = 1.0f;
            this.hairall[2 + face * 4].sizeXZ = 0.9f;
            this.hairall[3 + face * 4].sizeXZ = 0.8f;
            this.hairall[1 + face * 4].showModel = (float)l > 0.0f;
            this.hairall[2 + face * 4].showModel = (float)l > 33.0f;
            this.hairall[3 + face * 4].showModel = (float)l > 66.0f;
            this.hairall[lng + face * 4].render(par1);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
        }
        GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }

    public void renderHairs(float par1, String hair) {
        float f6 = f;
        GL11.glPushMatrix();
        GL11.glScalef((float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.7f)), (float)(1.0f / f6), (float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.7f)));
        GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
        if (hair.contains("FR") && hair.contains("2")) {
            this.leftarmshoulder.field_78798_e = this.LA.field_78798_e;
            this.leftarmshoulder.field_78797_d = this.LA.field_78797_d;
            this.leftarmshoulder.field_78800_c = this.LA.field_78800_c;
            this.leftarmshoulder.field_78796_g = this.LA.field_78796_g;
            this.leftarmshoulder.field_78795_f = this.LA.field_78795_f;
            this.leftarmshoulder.field_78808_h = this.LA.field_78808_h;
            this.leftarmshoulder.func_78785_a(par1);
            this.rightarmshoulder.field_78798_e = this.RA.field_78798_e;
            this.rightarmshoulder.field_78797_d = this.RA.field_78797_d;
            this.rightarmshoulder.field_78800_c = this.RA.field_78800_c;
            this.rightarmshoulder.field_78796_g = this.RA.field_78796_g;
            this.rightarmshoulder.field_78795_f = this.RA.field_78795_f;
            this.rightarmshoulder.field_78808_h = this.RA.field_78808_h;
            this.rightarmshoulder.func_78785_a(par1);
        }
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glScalef((float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.7f)), (float)(1.0f / f6), (float)(1.0f / f6 * (g <= 1 ? 1.0f : 0.7f)));
        GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
        if (hair.contains("FR")) {
            if (!hair.contains("nFR")) {
                GL11.glPushMatrix();
                this.transRot(par1, this.B1);
                GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
                this.FroB.func_78785_a(par1);
                float f = 1.57f;
                float r = MathHelper.func_76126_a((float)(rot3 * 0.02f)) * 0.1f;
                float r2 = MathHelper.func_76134_b((float)(rot3 * 0.02f)) * 0.1f;
                float r3 = MathHelper.func_76134_b((float)(rot3 * 0.14f)) * 0.1f;
                this.ftailS1.field_78796_g = 0.2f;
                if (detail) {
                    this.ftailS1.field_78796_g += MathHelper.func_76134_b((float)(rot3 * 0.09f)) * 0.2f - 0.2f + r;
                }
                this.ftailS1.field_78795_f = -0.3f;
                this.ftailS2.field_78796_g = 0.2f;
                if (detail) {
                    this.ftailS2.field_78796_g += MathHelper.func_76134_b((float)(rot3 * 0.09f)) * 0.2f - 0.2f + r2 + r3;
                }
                this.ftailS2.field_78795_f = 0.4f;
                this.ftailS3.field_78796_g = 0.1f;
                if (detail) {
                    this.ftailS3.field_78796_g += MathHelper.func_76134_b((float)(rot3 * 0.09f)) * 0.1f - 0.1f + r + r3;
                }
                this.ftailS3.field_78795_f = 0.6f;
                if (detail) {
                    this.ftailS3.field_78795_f += MathHelper.func_76126_a((float)(rot3 * 0.09f)) * 0.4f + 0.3f;
                }
                this.ftailS4.field_78796_g = 0.1f;
                if (detail) {
                    this.ftailS4.field_78796_g += MathHelper.func_76134_b((float)(rot3 * 0.09f)) * 0.4f - 0.1f + r2;
                }
                this.ftailS4.field_78795_f = 0.3f;
                if (detail) {
                    this.ftailS4.field_78795_f += MathHelper.func_76126_a((float)(rot3 * 0.09f)) * 0.1f - 0.2f;
                }
                this.ftailS5.field_78796_g = 0.2f;
                if (detail) {
                    this.ftailS5.field_78796_g += MathHelper.func_76134_b((float)(rot3 * 0.09f)) * 0.4f - 0.2f + r + r3;
                }
                this.ftailS5.field_78795_f = -0.2f;
                if (detail) {
                    this.ftailS5.field_78795_f += MathHelper.func_76126_a((float)(rot3 * 0.09f)) * 0.1f - 0.3f;
                }
                this.ftailS6.field_78796_g = 0.2f;
                if (detail) {
                    this.ftailS6.field_78796_g += MathHelper.func_76134_b((float)(rot3 * 0.09f)) * 0.4f - 0.2f + r2 + r3;
                }
                this.ftailS6.field_78795_f = -0.4f;
                if (detail) {
                    this.ftailS6.field_78795_f += MathHelper.func_76126_a((float)(rot3 * 0.09f)) * 0.4f - 0.4f;
                }
                GL11.glPopMatrix();
            }
            if (hair.contains("4")) {
                this.Fro5b.field_78796_g = this.B.field_78796_g;
                this.Fro5b.field_78795_f = this.B.field_78795_f;
                this.Fro5b.field_78800_c = this.B.field_78800_c;
                this.Fro5b.field_78797_d = this.B.field_78797_d;
                this.Fro5b.func_78785_a(par1);
                this.Fro5r.field_78800_c = this.RA.field_78800_c;
                this.Fro5r.field_78797_d = this.RA.field_78797_d;
                this.Fro5r.field_78798_e = this.RA.field_78798_e;
                this.Fro5r.field_78796_g = this.RA.field_78796_g;
                this.Fro5r.field_78795_f = this.RA.field_78795_f;
                this.Fro5r.field_78808_h = this.RA.field_78808_h;
                this.Fro5r.func_78785_a(par1);
                this.Fro5l.field_78800_c = this.LA.field_78800_c;
                this.Fro5l.field_78797_d = this.LA.field_78797_d;
                this.Fro5l.field_78798_e = this.LA.field_78798_e;
                this.Fro5l.field_78796_g = this.LA.field_78796_g;
                this.Fro5l.field_78795_f = this.LA.field_78795_f;
                this.Fro5l.field_78808_h = this.LA.field_78808_h;
                this.Fro5l.func_78785_a(par1);
            }
        }
        if (hair.contains("SJT1")) {
            GL11.glPushMatrix();
            this.transRot(par1, this.B1);
            GL11.glScalef((float)1.0f, (float)1.0f, (float)1.0f);
            this.SaiT1.func_78785_a(par1);
            float r = MathHelper.func_76126_a((float)(rot3 * 0.02f)) * 0.1f;
            float r2 = MathHelper.func_76134_b((float)(rot3 * 0.02f)) * 0.1f;
            float r3 = MathHelper.func_76134_b((float)(rot3 * 0.14f)) * 0.1f;
            this.tail1.field_78796_g = 0.2f;
            if (detail) {
                this.tail1.field_78796_g += MathHelper.func_76134_b((float)(rot3 * 0.09f)) * 0.2f - 0.2f + r;
            }
            this.tail1.field_78795_f = -0.3f;
            this.tail2.field_78796_g = 0.2f;
            if (detail) {
                this.tail2.field_78796_g += MathHelper.func_76134_b((float)(rot3 * 0.09f)) * 0.2f - 0.2f + r2 + r3;
            }
            this.tail2.field_78795_f = 0.4f;
            this.tailS3.field_78796_g = 0.1f;
            if (detail) {
                this.tailS3.field_78796_g += MathHelper.func_76134_b((float)(rot3 * 0.09f)) * 0.1f - 0.1f + r + r3;
            }
            this.tailS3.field_78795_f = 0.6f;
            if (detail) {
                this.tailS3.field_78795_f += MathHelper.func_76126_a((float)(rot3 * 0.09f)) * 0.4f + 0.3f;
            }
            this.tailS4.field_78796_g = 0.1f;
            if (detail) {
                this.tailS4.field_78796_g += MathHelper.func_76134_b((float)(rot3 * 0.09f)) * 0.4f - 0.1f + r2;
            }
            this.tailS4.field_78795_f = 0.3f;
            if (detail) {
                this.tailS4.field_78795_f += MathHelper.func_76126_a((float)(rot3 * 0.09f)) * 0.1f - 0.2f;
            }
            this.tailS5.field_78796_g = 0.2f;
            if (detail) {
                this.tailS5.field_78796_g += MathHelper.func_76134_b((float)(rot3 * 0.09f)) * 0.4f - 0.2f + r + r3;
            }
            this.tailS5.field_78795_f = -0.2f;
            if (detail) {
                this.tailS5.field_78795_f += MathHelper.func_76126_a((float)(rot3 * 0.09f)) * 0.1f - 0.3f;
            }
            this.tailS6.field_78796_g = 0.2f;
            if (detail) {
                this.tailS6.field_78796_g += MathHelper.func_76134_b((float)(rot3 * 0.09f)) * 0.4f - 0.2f + r2 + r3;
            }
            this.tailS6.field_78795_f = -0.4f;
            if (detail) {
                this.tailS6.field_78795_f += MathHelper.func_76126_a((float)(rot3 * 0.09f)) * 0.4f - 0.4f;
            }
            GL11.glPopMatrix();
        }
        if (hair.contains("SJT2")) {
            this.transRot(par1, this.B1);
            this.SaiT2.field_78796_g = this.B1.field_78796_g;
            this.SaiT2.func_78785_a(par1);
        }
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glScalef((float)((0.5f + 0.5f / f6) * (g <= 1 ? 1.0f : 0.85f)), (float)(0.5f + 0.5f / f6), (float)((0.5f + 0.5f / f6) * (g <= 1 ? 1.0f : 0.85f)));
        GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) / f6 * (2.0f - (f6 >= 1.5f && f6 <= 2.0f ? (2.0f - f6) / 2.5f : (f6 < 1.5f && f6 >= 1.0f ? (f6 * 2.0f - 2.0f) * 0.2f : 0.0f)))), (float)0.0f);
        if (hair.contains("FR")) {
            this.Fro.field_78796_g = this.field_78116_c.field_78796_g;
            this.Fro.field_78795_f = this.field_78116_c.field_78795_f;
            this.Fro.field_78800_c = this.field_78116_c.field_78800_c;
            this.Fro.field_78797_d = this.field_78116_c.field_78797_d;
            this.Fro.func_78785_a(par1);
            if (hair.contains("0") || hair.contains("2") || hair.contains("1")) {
                this.Fro0.field_78796_g = this.field_78116_c.field_78796_g;
                this.Fro0.field_78795_f = this.field_78116_c.field_78795_f;
                this.Fro0.field_78800_c = this.field_78116_c.field_78800_c;
                this.Fro0.field_78797_d = this.field_78116_c.field_78797_d;
                this.Fro0.func_78785_a(par1);
            }
            if (hair.contains("1") || hair.contains("2")) {
                this.Fro1.field_78796_g = this.field_78116_c.field_78796_g;
                this.Fro1.field_78795_f = this.field_78116_c.field_78795_f;
                this.Fro1.field_78800_c = this.field_78116_c.field_78800_c;
                this.Fro1.field_78797_d = this.field_78116_c.field_78797_d;
                this.Fro1.func_78785_a(par1);
            }
            if (hair.contains("2")) {
                this.Fro2.field_78796_g = this.field_78116_c.field_78796_g;
                this.Fro2.field_78795_f = this.field_78116_c.field_78795_f;
                this.Fro2.field_78800_c = this.field_78116_c.field_78800_c;
                this.Fro2.field_78797_d = this.field_78116_c.field_78797_d;
                this.Fro2.func_78785_a(par1);
            }
            if (hair.contains("4")) {
                this.Fro5.field_78796_g = this.field_78116_c.field_78796_g;
                this.Fro5.field_78795_f = this.field_78116_c.field_78795_f;
                this.Fro5.field_78800_c = this.field_78116_c.field_78800_c;
                this.Fro5.field_78797_d = this.field_78116_c.field_78797_d;
                this.Fro5.func_78785_a(par1);
            }
        }
        if (hair.contains("N")) {
            this.Nam.field_78796_g = this.field_78116_c.field_78796_g;
            this.Nam.field_78795_f = this.field_78116_c.field_78795_f;
            this.Nam.field_78800_c = this.field_78116_c.field_78800_c;
            this.Nam.field_78797_d = this.field_78116_c.field_78797_d;
            this.Nam.func_78785_a(par1);
        }
        if (hair.contains("SJE")) {
            this.SaiE.field_78796_g = this.field_78116_c.field_78796_g;
            this.SaiE.field_78795_f = this.field_78116_c.field_78795_f;
            this.SaiE.field_78800_c = this.field_78116_c.field_78800_c;
            this.SaiE.field_78797_d = this.field_78116_c.field_78797_d;
            this.SaiE.func_78785_a(par1);
        }
        if (hair.contains("FACENOSE")) {
            this.face1.field_78796_g = this.field_78116_c.field_78796_g;
            this.face1.field_78795_f = this.field_78116_c.field_78795_f;
            this.face1.field_78800_c = this.field_78116_c.field_78800_c;
            this.face1.field_78797_d = this.field_78116_c.field_78797_d;
            this.face1.func_78785_a(par1);
        }
        if (hair.contains("FACEMOUTH")) {
            this.face2.field_78796_g = this.field_78116_c.field_78796_g;
            this.face2.field_78795_f = this.field_78116_c.field_78795_f;
            this.face2.field_78800_c = this.field_78116_c.field_78800_c;
            this.face2.field_78797_d = this.field_78116_c.field_78797_d;
            this.face2.func_78785_a(par1);
        }
        if (hair.contains("EYEBROW")) {
            this.face6.field_78796_g = this.field_78116_c.field_78796_g;
            this.face6.field_78795_f = this.field_78116_c.field_78795_f;
            this.face6.field_78800_c = this.field_78116_c.field_78800_c;
            this.face6.field_78797_d = this.field_78116_c.field_78797_d;
            this.face6.func_78785_a(par1);
        }
        if (hair.contains("EYEBASE")) {
            this.face5.field_78796_g = this.field_78116_c.field_78796_g;
            this.face5.field_78795_f = this.field_78116_c.field_78795_f;
            this.face5.field_78800_c = this.field_78116_c.field_78800_c;
            this.face5.field_78797_d = this.field_78116_c.field_78797_d;
            this.face5.func_78785_a(par1);
        }
        if (hair.contains("EYELEFT")) {
            this.face3.field_78796_g = this.field_78116_c.field_78796_g;
            this.face3.field_78795_f = this.field_78116_c.field_78795_f;
            this.face3.field_78800_c = this.field_78116_c.field_78800_c;
            this.face3.field_78797_d = this.field_78116_c.field_78797_d;
            this.face3.func_78785_a(par1);
        }
        if (hair.contains("EYERIGHT")) {
            this.face4.field_78796_g = this.field_78116_c.field_78796_g;
            this.face4.field_78795_f = this.field_78116_c.field_78795_f;
            this.face4.field_78800_c = this.field_78116_c.field_78800_c;
            this.face4.field_78797_d = this.field_78116_c.field_78797_d;
            this.face4.func_78785_a(par1);
        }
        if (hair.contains("A11") || hair.contains("B11") || hair.contains("C11") || hair.contains("D11")) {
            // empty if block
        }
        if (hair.contains("A01")) {
            this.bipedHeadg.field_78796_g = this.field_78116_c.field_78796_g;
            this.bipedHeadg.field_78795_f = this.field_78116_c.field_78795_f;
            this.bipedHeadg.field_78800_c = this.field_78116_c.field_78800_c;
            this.bipedHeadg.field_78797_d = this.field_78116_c.field_78797_d;
            this.bipedHeadg.func_78785_a(par1);
        }
        if (hair.contains("A02")) {
            this.bipedHeadt.field_78796_g = this.field_78116_c.field_78796_g;
            this.bipedHeadt.field_78795_f = this.field_78116_c.field_78795_f;
            this.bipedHeadt.field_78800_c = this.field_78116_c.field_78800_c;
            this.bipedHeadt.field_78797_d = this.field_78116_c.field_78797_d;
            this.bipedHeadt.func_78785_a(par1);
        }
        if (hair.contains("A03")) {
            this.bipedHeadv.field_78796_g = this.field_78116_c.field_78796_g;
            this.bipedHeadv.field_78795_f = this.field_78116_c.field_78795_f;
            this.bipedHeadv.field_78800_c = this.field_78116_c.field_78800_c;
            this.bipedHeadv.field_78797_d = this.field_78116_c.field_78797_d;
            this.bipedHeadv.func_78785_a(par1);
        }
        if (hair.contains("A04")) {
            this.bipedHeadgh.field_78796_g = this.field_78116_c.field_78796_g;
            this.bipedHeadgh.field_78795_f = this.field_78116_c.field_78795_f;
            this.bipedHeadgh.field_78800_c = this.field_78116_c.field_78800_c;
            this.bipedHeadgh.field_78797_d = this.field_78116_c.field_78797_d;
            this.bipedHeadgh.func_78785_a(par1);
        }
        if (hair.contains("A05")) {
            this.bipedHeadg2.field_78796_g = this.field_78116_c.field_78796_g;
            this.bipedHeadg2.field_78795_f = this.field_78116_c.field_78795_f;
            this.bipedHeadg2.field_78800_c = this.field_78116_c.field_78800_c;
            this.bipedHeadg2.field_78797_d = this.field_78116_c.field_78797_d;
            this.bipedHeadg2.func_78785_a(par1);
        }
        if (hair.contains("A06") || hair.contains("B06") || hair.contains("C06")) {
            this.bipedHeadght.field_78796_g = this.field_78116_c.field_78796_g;
            this.bipedHeadght.field_78795_f = this.field_78116_c.field_78795_f;
            this.bipedHeadght.field_78800_c = this.field_78116_c.field_78800_c;
            this.bipedHeadght.field_78797_d = this.field_78116_c.field_78797_d;
            this.bipedHeadght.func_78785_a(par1);
        }
        if (hair.contains("A07") || hair.contains("B07") || hair.contains("C07")) {
            this.bipedHeadgt.field_78796_g = this.field_78116_c.field_78796_g;
            this.bipedHeadgt.field_78795_f = this.field_78116_c.field_78795_f;
            this.bipedHeadgt.field_78800_c = this.field_78116_c.field_78800_c;
            this.bipedHeadgt.field_78797_d = this.field_78116_c.field_78797_d;
            this.bipedHeadgt.func_78785_a(par1);
        }
        if (hair.contains("A08") || hair.contains("B08") || hair.contains("C08")) {
            this.bipedHeadgtt.field_78796_g = this.field_78116_c.field_78796_g;
            this.bipedHeadgtt.field_78795_f = this.field_78116_c.field_78795_f;
            this.bipedHeadgtt.field_78800_c = this.field_78116_c.field_78800_c;
            this.bipedHeadgtt.field_78797_d = this.field_78116_c.field_78797_d;
            this.bipedHeadgtt.func_78785_a(par1);
        }
        if (hair.contains("A09")) {
            this.bipedHeadc7.field_78796_g = this.field_78116_c.field_78796_g;
            this.bipedHeadc7.field_78795_f = this.field_78116_c.field_78795_f;
            this.bipedHeadc7.field_78800_c = this.field_78116_c.field_78800_c;
            this.bipedHeadc7.field_78797_d = this.field_78116_c.field_78797_d;
            this.bipedHeadc7.func_78785_a(par1);
        }
        if (hair.contains("A10")) {
            this.bipedHeadc8.field_78796_g = this.field_78116_c.field_78796_g;
            this.bipedHeadc8.field_78795_f = this.field_78116_c.field_78795_f;
            this.bipedHeadc8.field_78800_c = this.field_78116_c.field_78800_c;
            this.bipedHeadc8.field_78797_d = this.field_78116_c.field_78797_d;
            this.bipedHeadc8.func_78785_a(par1);
        }
        if (hair.contains("12") || hair.contains("D")) {
            this.bipedHeadrad.field_78796_g = this.field_78116_c.field_78796_g;
            this.bipedHeadrad.field_78795_f = this.field_78116_c.field_78795_f;
            this.bipedHeadrad.field_78800_c = this.field_78116_c.field_78800_c;
            this.bipedHeadrad.field_78797_d = this.field_78116_c.field_78797_d;
            this.bipedHeadrad.func_78785_a(par1);
            this.bipedHeadradl.field_78796_g = this.field_78116_c.field_78796_g;
            this.bipedHeadradl.field_78795_f = this.field_78116_c.field_78795_f / 4.0f;
            this.bipedHeadradl.field_78800_c = this.field_78116_c.field_78800_c;
            this.bipedHeadradl.field_78797_d = this.field_78116_c.field_78797_d;
            this.bipedHeadradl.func_78785_a(par1);
            this.bipedHeadradl2.field_78796_g = this.field_78116_c.field_78796_g;
            this.bipedHeadradl2.field_78795_f = this.field_78116_c.field_78795_f / 2.0f;
            this.bipedHeadradl2.field_78800_c = this.field_78116_c.field_78800_c;
            this.bipedHeadradl2.field_78797_d = this.field_78116_c.field_78797_d;
            this.bipedHeadradl2.func_78785_a(par1);
            this.bipedHeadradl2.field_78796_g = this.field_78116_c.field_78796_g;
            this.bipedHeadradl2.field_78795_f = this.field_78116_c.field_78795_f / 1.2f;
            this.bipedHeadradl2.field_78800_c = this.field_78116_c.field_78800_c;
            this.bipedHeadradl2.field_78797_d = this.field_78116_c.field_78797_d;
            this.bipedHeadradl2.func_78785_a(par1);
            if (hair.contains("01") || hair.contains("02") || hair.contains("05")) {
                this.bipedHeadssj3t.field_78796_g = this.field_78116_c.field_78796_g;
                this.bipedHeadssj3t.field_78795_f = this.field_78116_c.field_78795_f;
                this.bipedHeadssj3t.field_78800_c = this.field_78116_c.field_78800_c;
                this.bipedHeadssj3t.field_78797_d = this.field_78116_c.field_78797_d;
                this.bipedHeadssj3t.func_78785_a(par1);
            }
        }
        if (hair.contains("B01") || hair.contains("B05")) {
            this.bipedHeadsg.field_78796_g = this.field_78116_c.field_78796_g;
            this.bipedHeadsg.field_78795_f = this.field_78116_c.field_78795_f;
            this.bipedHeadsg.field_78800_c = this.field_78116_c.field_78800_c;
            this.bipedHeadsg.field_78797_d = this.field_78116_c.field_78797_d;
            this.bipedHeadsg.func_78785_a(par1);
        }
        if (hair.contains("B02") || hair.contains("B09") || hair.contains("B10")) {
            this.bipedHeadst.field_78796_g = this.field_78116_c.field_78796_g;
            this.bipedHeadst.field_78795_f = this.field_78116_c.field_78795_f;
            this.bipedHeadst.field_78800_c = this.field_78116_c.field_78800_c;
            this.bipedHeadst.field_78797_d = this.field_78116_c.field_78797_d;
            this.bipedHeadst.func_78785_a(par1);
        }
        if (hair.contains("B03")) {
            this.bipedHeadsv.field_78796_g = this.field_78116_c.field_78796_g;
            this.bipedHeadsv.field_78795_f = this.field_78116_c.field_78795_f;
            this.bipedHeadsv.field_78800_c = this.field_78116_c.field_78800_c;
            this.bipedHeadsv.field_78797_d = this.field_78116_c.field_78797_d;
            this.bipedHeadsv.func_78785_a(par1);
        }
        if (hair.contains("B04")) {
            this.bipedHeadsgh.field_78796_g = this.field_78116_c.field_78796_g;
            this.bipedHeadsgh.field_78795_f = this.field_78116_c.field_78795_f;
            this.bipedHeadsgh.field_78800_c = this.field_78116_c.field_78800_c;
            this.bipedHeadsgh.field_78797_d = this.field_78116_c.field_78797_d;
            this.bipedHeadsgh.func_78785_a(par1);
        }
        if (hair.contains("C01") || hair.contains("C05")) {
            this.bipedHeadssg.field_78796_g = this.field_78116_c.field_78796_g;
            this.bipedHeadssg.field_78795_f = this.field_78116_c.field_78795_f;
            this.bipedHeadssg.field_78800_c = this.field_78116_c.field_78800_c;
            this.bipedHeadssg.field_78797_d = this.field_78116_c.field_78797_d;
            this.bipedHeadssg.func_78785_a(par1);
        }
        if (hair.contains("C02") || hair.contains("C09") || hair.contains("C10")) {
            this.bipedHeadsst.field_78796_g = this.field_78116_c.field_78796_g;
            this.bipedHeadsst.field_78795_f = this.field_78116_c.field_78795_f;
            this.bipedHeadsst.field_78800_c = this.field_78116_c.field_78800_c;
            this.bipedHeadsst.field_78797_d = this.field_78116_c.field_78797_d;
            this.bipedHeadsst.func_78785_a(par1);
        }
        if (hair.contains("C03")) {
            this.bipedHeadssv.field_78796_g = this.field_78116_c.field_78796_g;
            this.bipedHeadssv.field_78795_f = this.field_78116_c.field_78795_f;
            this.bipedHeadssv.field_78800_c = this.field_78116_c.field_78800_c;
            this.bipedHeadssv.field_78797_d = this.field_78116_c.field_78797_d;
            this.bipedHeadssv.func_78785_a(par1);
        }
        if (hair.contains("C04")) {
            this.bipedHeadssgh.field_78796_g = this.field_78116_c.field_78796_g;
            this.bipedHeadssgh.field_78795_f = this.field_78116_c.field_78795_f;
            this.bipedHeadssgh.field_78800_c = this.field_78116_c.field_78800_c;
            this.bipedHeadssgh.field_78797_d = this.field_78116_c.field_78797_d;
            this.bipedHeadssgh.func_78785_a(par1);
        }
        GL11.glPopMatrix();
    }

    public void renderHeadwear(float par1) {
        float f6 = f;
        GL11.glPushMatrix();
        GL11.glScalef((float)((0.5f + 0.5f / f6) * (g <= 1 ? 1.0f : 0.85f)), (float)(0.5f + 0.5f / f6), (float)((0.5f + 0.5f / f6) * (g <= 1 ? 1.0f : 0.85f)));
        GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) / f6 * (2.0f - (f6 >= 1.5f && f6 <= 2.0f ? (2.0f - f6) / 2.5f : (f6 < 1.5f && f6 >= 1.0f ? (f6 * 2.0f - 2.0f) * 0.2f : 0.0f)))), (float)0.0f);
        this.field_78114_d.field_78796_g = this.field_78116_c.field_78796_g;
        this.field_78114_d.field_78795_f = this.field_78116_c.field_78795_f;
        this.field_78114_d.field_78800_c = this.field_78116_c.field_78800_c;
        this.field_78114_d.field_78797_d = this.field_78116_c.field_78797_d;
        this.field_78114_d.func_78785_a(par1);
        GL11.glPopMatrix();
    }

    public void renderHalo(float par1) {
        float f6 = f;
        GL11.glPushMatrix();
        GL11.glScalef((float)((0.5f + 0.5f / f6) * (g <= 1 ? 1.0f : 0.85f)), (float)(0.5f + 0.5f / f6), (float)((0.5f + 0.5f / f6) * (g <= 1 ? 1.0f : 0.85f)));
        GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) / f6 * (2.0f - (f6 >= 1.5f && f6 <= 2.0f ? (2.0f - f6) / 2.5f : (f6 < 1.5f && f6 >= 1.0f ? (f6 * 2.0f - 2.0f) * 0.2f : 0.0f)))), (float)0.0f);
        this.halo.field_78796_g = this.field_78116_c.field_78796_g;
        this.halo.field_78795_f = this.field_78116_c.field_78795_f;
        this.halo.field_78800_c = this.field_78116_c.field_78800_c;
        this.halo.field_78797_d = this.field_78116_c.field_78797_d;
        this.halo.func_78785_a(par1);
        GL11.glPopMatrix();
    }

    public void func_78110_b(float par1) {
        float f6 = f;
        GL11.glPushMatrix();
        GL11.glScalef((float)((0.5f + 0.5f / f6) * (g <= 1 ? 1.0f : 0.85f)), (float)(0.5f + 0.5f / f6), (float)((0.5f + 0.5f / f6) * (g <= 1 ? 1.0f : 0.85f)));
        GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) / f6 * (2.0f - (f6 >= 1.5f && f6 <= 2.0f ? (2.0f - f6) / 2.5f : (f6 < 1.5f && f6 >= 1.0f ? (f6 * 2.0f - 2.0f) * 0.2f : 0.0f)))), (float)0.0f);
        this.field_78121_j.field_78796_g = this.field_78116_c.field_78796_g;
        this.field_78121_j.field_78795_f = this.field_78116_c.field_78795_f;
        this.field_78121_j.field_78800_c = 0.0f;
        this.field_78121_j.field_78797_d = 0.0f;
        this.field_78121_j.func_78785_a(par1);
        GL11.glPopMatrix();
    }

    public void func_78111_c(float par1) {
        this.field_78122_k.func_78785_a(par1);
    }

    static {
        detail = false;
    }
}

