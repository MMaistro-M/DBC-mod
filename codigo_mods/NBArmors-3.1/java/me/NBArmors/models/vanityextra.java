/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package me.NBArmors.models;

import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.JRMCoreHJYC;
import JinRyuu.JRMCore.entity.ModelBipedBody;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class vanityextra
extends ModelBipedBody {
    private final int VANITY_BARRIER_OF_TIME = 0;
    private final int VANITY_SCARF = 1;
    private final int VANITY_EARS1 = 2;
    private final int VANITY_MULEHORNS = 3;
    private final int VANITY_MASKG = 4;
    private final int VANITY_POTARA = 5;
    private final int VANITY_PUAR = 7;
    private final int VANITY_GGMASK = 8;
    private final int VANITY_SHUKATANA = 9;
    private final int VANITY_BRAVE = 10;
    private final int VANITY_SCARS = 11;
    private final int VANITY_AEOS = 12;
    private final int VANITY_BEARD = 13;
    public int id = -1;
    public static boolean a6P9H9B = true;
    public ModelRenderer BoTbase;
    public ModelRenderer Spike1;
    public ModelRenderer Spike2;
    public ModelRenderer BoTtop;
    public ModelRenderer BoT1;
    public ModelRenderer BoT2;
    public ModelRenderer BoT3;
    public ModelRenderer BoTRight;
    public ModelRenderer BoT4;
    public ModelRenderer BoT5;
    public ModelRenderer BoT6;
    public ModelRenderer BoTBottom;
    public ModelRenderer BoT7;
    public ModelRenderer BoT8;
    public ModelRenderer BoT9;
    public ModelRenderer BoTleft;
    public ModelRenderer BoT10;
    public ModelRenderer BoT11;
    public ModelRenderer BoT12;
    public ModelRenderer earbase;
    public ModelRenderer earleft;
    public ModelRenderer earright;
    public ModelRenderer earbase1;
    public ModelRenderer earleft1;
    public ModelRenderer earright1;
    public ModelRenderer hornbase1;
    public ModelRenderer hornbase2;
    public ModelRenderer horn1;
    public ModelRenderer horn2;
    public ModelRenderer scarfbase;
    public ModelRenderer scarf1;
    public ModelRenderer scarf2;
    public ModelRenderer scarf3;
    public ModelRenderer scarf4;
    public ModelRenderer scarf5;
    public ModelRenderer scarf6;
    public ModelRenderer mhornbr;
    public ModelRenderer mhornr1;
    public ModelRenderer mhornr2;
    public ModelRenderer mhornbl;
    public ModelRenderer mhornl1;
    public ModelRenderer mhornl2;
    public ModelRenderer mhornbase;
    public ModelRenderer eye;
    public ModelRenderer Shape1;
    public ModelRenderer Shape2;
    public ModelRenderer mask;
    public ModelRenderer xicor1;
    public ModelRenderer xicor2;
    public ModelRenderer xicor3;
    public ModelRenderer xicor4;
    public ModelRenderer xicor5;
    public ModelRenderer xicor6;
    public ModelRenderer xicorbase;
    public ModelRenderer tailjb;
    public ModelRenderer tailj1;
    public ModelRenderer tailj2;
    public ModelRenderer tailj3;
    public ModelRenderer cellmax;
    public ModelRenderer Ear1;
    public ModelRenderer Ear2;
    public ModelRenderer Phead;
    public ModelRenderer Pcheek1;
    public ModelRenderer Pcheek2;
    public ModelRenderer Pbody;
    public ModelRenderer Parm1;
    public ModelRenderer Parm2;
    public ModelRenderer Pfeet1;
    public ModelRenderer Pfeet2;
    public ModelRenderer Ptail;
    public ModelRenderer ggmask;
    public ModelRenderer hoja;
    public ModelRenderer cuna;
    public ModelRenderer handle;
    public ModelRenderer blade;
    public ModelRenderer cross;
    public ModelRenderer knob;
    private float size = 1.0f;
    private ModelRenderer tailbase;
    private ModelRenderer scarbase;
    private ModelRenderer aeoshead;
    private ModelRenderer hat1;
    private ModelRenderer hat2;
    private ModelRenderer hat3;
    private ModelRenderer handr;
    private ModelRenderer handl;
    private ModelRenderer beardbase;
    private ModelRenderer beard;

    public vanityextra(int id) {
        this(id, 0.1f, 64.0f, 32);
    }

    public vanityextra(int id, float par1, float par2, int par3) {
        super(id, 0.1f, 64, 32);
        this.id = id;
        if (id == 0) {
            this.field_78090_t = 64;
            this.field_78089_u = 32;
            this.BoTbase = new ModelRenderer((ModelBase)this, 14, 3);
            this.BoTbase.func_78789_a(-2.0f, -2.0f, 10.0f, 4, 4, 1);
            this.BoTbase.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.BoTbase, 0.0f, 0.0f, 0.0f);
            this.Spike1 = new ModelRenderer((ModelBase)this, 14, 0);
            this.Spike1.func_78789_a(-1.0f, -1.0f, 10.0f, 20, 2, 1);
            this.Spike1.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.Spike1, 0.0f, 0.0f, -0.7853982f);
            this.Spike2 = new ModelRenderer((ModelBase)this, 14, 0);
            this.Spike2.func_78789_a(-1.0f, -1.0f, 10.0f, 20, 2, 1);
            this.Spike2.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.Spike2, 0.0f, 0.0f, -2.356194f);
            this.BoTtop = new ModelRenderer((ModelBase)this, 0, 6);
            this.BoTtop.func_78789_a(-3.0f, -14.0f, 10.0f, 6, 3, 1);
            this.BoTtop.func_78793_a(0.0f, 0.0f, 0.0f);
            this.BoTtop.func_78787_b(64, 32);
            this.setRotation(this.BoTtop, 0.0f, 0.0f, 0.0f);
            this.BoT1 = new ModelRenderer((ModelBase)this, 0, 0);
            this.BoT1.func_78789_a(-3.0f, -16.0f, 10.0f, 6, 5, 1);
            this.BoT1.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.BoT1, 0.0f, 0.0f, -0.3926991f);
            this.BoT2 = new ModelRenderer((ModelBase)this, 0, 6);
            this.BoT2.func_78789_a(-3.0f, -14.0f, 10.0f, 6, 3, 1);
            this.BoT2.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.BoT2, 0.0f, 0.0f, -0.7853982f);
            this.BoT3 = new ModelRenderer((ModelBase)this, 0, 0);
            this.BoT3.func_78789_a(-3.0f, -16.0f, 10.0f, 6, 5, 1);
            this.BoT3.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.BoT3, 0.0f, 0.0f, -1.178097f);
            this.BoTRight = new ModelRenderer((ModelBase)this, 0, 6);
            this.BoTRight.func_78789_a(-3.0f, -14.0f, 10.0f, 6, 3, 1);
            this.BoTRight.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.BoTRight, 0.0f, 0.0f, -1.570796f);
            this.BoT4 = new ModelRenderer((ModelBase)this, 0, 0);
            this.BoT4.func_78789_a(-3.0f, -16.0f, 10.0f, 6, 5, 1);
            this.BoT4.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.BoT4, 0.0f, 0.0f, -1.963495f);
            this.BoT5 = new ModelRenderer((ModelBase)this, 0, 6);
            this.BoT5.func_78789_a(-3.0f, -14.0f, 10.0f, 6, 3, 1);
            this.BoT5.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.BoT5, 0.0f, 0.0f, -2.356194f);
            this.BoT6 = new ModelRenderer((ModelBase)this, 0, 0);
            this.BoT6.func_78789_a(-3.0f, -16.0f, 10.0f, 6, 5, 1);
            this.BoT6.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.BoT6, 0.0f, 0.0f, -2.748893f);
            this.BoTBottom = new ModelRenderer((ModelBase)this, 0, 6);
            this.BoTBottom.func_78789_a(-3.0f, -14.0f, 10.0f, 6, 3, 1);
            this.BoTBottom.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.BoTBottom, 0.0f, 0.0f, -3.141593f);
            this.BoT7 = new ModelRenderer((ModelBase)this, 0, 0);
            this.BoT7.func_78789_a(-3.0f, -16.0f, 10.0f, 6, 5, 1);
            this.BoT7.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.BoT7, 0.0f, 0.0f, 2.748893f);
            this.BoT8 = new ModelRenderer((ModelBase)this, 0, 6);
            this.BoT8.func_78789_a(-3.0f, -14.0f, 10.0f, 6, 3, 1);
            this.BoT8.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.BoT8, 0.0f, 0.0f, 2.356194f);
            this.BoT9 = new ModelRenderer((ModelBase)this, 0, 0);
            this.BoT9.func_78789_a(-3.0f, -16.0f, 10.0f, 6, 5, 1);
            this.BoT9.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.BoT9, 0.0f, 0.0f, 1.963495f);
            this.BoTleft = new ModelRenderer((ModelBase)this, 0, 6);
            this.BoTleft.func_78789_a(-3.0f, -14.0f, 10.0f, 6, 3, 1);
            this.BoTleft.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.BoTleft, 0.0f, 0.0f, 1.570796f);
            this.BoT10 = new ModelRenderer((ModelBase)this, 0, 0);
            this.BoT10.func_78789_a(-3.0f, -16.0f, 10.0f, 6, 5, 1);
            this.BoT10.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.BoT10, 0.0f, 0.0f, 1.178097f);
            this.BoT11 = new ModelRenderer((ModelBase)this, 0, 6);
            this.BoT11.func_78789_a(-3.0f, -14.0f, 10.0f, 6, 3, 1);
            this.BoT11.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.BoT11, 0.0f, 0.0f, 0.7853982f);
            this.BoT12 = new ModelRenderer((ModelBase)this, 0, 0);
            this.BoT12.func_78789_a(-3.0f, -16.0f, 10.0f, 6, 5, 1);
            this.BoT12.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.BoT12, 0.0f, 0.0f, 0.3926991f);
            this.BoTbase.func_78792_a(this.Spike1);
            this.BoTbase.func_78792_a(this.Spike2);
            this.BoTbase.func_78792_a(this.BoTtop);
            this.BoTbase.func_78792_a(this.BoT1);
            this.BoTbase.func_78792_a(this.BoT2);
            this.BoTbase.func_78792_a(this.BoT3);
            this.BoTbase.func_78792_a(this.BoTRight);
            this.BoTbase.func_78792_a(this.BoT4);
            this.BoTbase.func_78792_a(this.BoT5);
            this.BoTbase.func_78792_a(this.BoT6);
            this.BoTbase.func_78792_a(this.BoTBottom);
            this.BoTbase.func_78792_a(this.BoT7);
            this.BoTbase.func_78792_a(this.BoT8);
            this.BoTbase.func_78792_a(this.BoT9);
            this.BoTbase.func_78792_a(this.BoTleft);
            this.BoTbase.func_78792_a(this.BoT10);
            this.BoTbase.func_78792_a(this.BoT11);
            this.BoTbase.func_78792_a(this.BoT12);
        } else if (id == 1) {
            this.scarfbase = new ModelRenderer((ModelBase)this, 0, 0);
            this.scarfbase.func_78790_a(-4.5f, -1.0f, -4.5f, 9, 2, 9, 0.0f);
            this.scarfbase.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.scarfbase, 0.0f, 0.0f, 0.0f);
            this.scarf1 = new ModelRenderer((ModelBase)this, 18, 11);
            this.scarf1.func_78789_a(-4.5f, 1.0f, -2.5f, 9, 4, 0);
            this.scarf1.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.scarf1, -0.2f, 0.0f, 0.0f);
            this.scarf2 = new ModelRenderer((ModelBase)this, 18, 15);
            this.scarf2.func_78789_a(-4.5f, 0.0f, 0.0f, 9, 4, 0);
            this.scarf2.func_78793_a(0.0f, 5.0f, -2.5f);
            this.setRotation(this.scarf2, 0.0f, 0.0f, 0.0f);
            this.scarf3 = new ModelRenderer((ModelBase)this, 18, 19);
            this.scarf3.func_78789_a(-4.5f, 0.0f, 0.0f, 9, 4, 0);
            this.scarf3.func_78793_a(0.0f, 4.0f, 0.0f);
            this.setRotation(this.scarf3, 0.0f, 0.0f, 0.0f);
            this.scarf4 = new ModelRenderer((ModelBase)this, 0, 11);
            this.scarf4.func_78789_a(-4.5f, 1.0f, 2.5f, 9, 4, 0);
            this.scarf4.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.scarf4, 0.2f, 0.0f, 0.0f);
            this.scarf5 = new ModelRenderer((ModelBase)this, 0, 15);
            this.scarf5.func_78789_a(-4.5f, 0.0f, 0.0f, 9, 4, 0);
            this.scarf5.func_78793_a(0.0f, 5.0f, 2.5f);
            this.setRotation(this.scarf5, 0.0f, 0.0f, 0.0f);
            this.scarf6 = new ModelRenderer((ModelBase)this, 0, 19);
            this.scarf6.func_78789_a(-4.5f, 0.0f, 0.0f, 9, 4, 0);
            this.scarf6.func_78793_a(0.0f, 4.0f, 0.0f);
            this.setRotation(this.scarf6, 0.0f, 0.0f, 0.0f);
            this.scarfbase.func_78792_a(this.scarf1);
            this.scarfbase.func_78792_a(this.scarf4);
            this.scarf1.func_78792_a(this.scarf2);
            this.scarf2.func_78792_a(this.scarf3);
            this.scarf4.func_78792_a(this.scarf5);
            this.scarf5.func_78792_a(this.scarf6);
            this.field_78116_c.func_78792_a(this.scarfbase);
        } else if (id == 2) {
            this.earbase = new ModelRenderer((ModelBase)this, 0, 0);
            this.earbase.func_78790_a(-4.0f, -8.0f, -4.0f, 7, 7, 7, -0.2f);
            this.earbase.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.earbase, 0.0f, 0.0f, 0.0f);
            this.earleft = new ModelRenderer((ModelBase)this, 0, 0);
            this.earleft.func_78789_a(-1.0f, -4.0f, 0.0f, 2, 4, 0);
            this.earleft.func_78793_a(3.5f, -3.0f, -2.0f);
            this.setRotation(this.earleft, 0.0f, -0.1745329f, 0.7853982f);
            this.earright = new ModelRenderer((ModelBase)this, 0, 4);
            this.earright.func_78789_a(-1.0f, -4.0f, 0.0f, 2, 4, 0);
            this.earright.func_78793_a(-3.5f, -3.0f, -2.0f);
            this.setRotation(this.earright, 0.0f, 0.1745329f, -0.7853982f);
            this.earleft1 = new ModelRenderer((ModelBase)this, 4, 0);
            this.earleft1.func_78789_a(-1.0f, -4.0f, 0.0f, 2, 4, 0);
            this.earleft1.func_78793_a(3.5f, -3.0f, -2.0f);
            this.setRotation(this.earleft1, 0.0f, -0.1745329f, 1.396263f);
            this.earright1 = new ModelRenderer((ModelBase)this, 4, 4);
            this.earright1.func_78789_a(-1.0f, -4.0f, 0.0f, 2, 4, 0);
            this.earright1.func_78793_a(-3.5f, -3.0f, -2.0f);
            this.setRotation(this.earright1, 0.0f, 0.1745329f, -1.396263f);
            this.field_78116_c.func_78792_a(this.earbase);
            this.field_78116_c.func_78792_a(this.earbase);
            this.earbase.func_78792_a(this.earleft);
            this.earbase.func_78792_a(this.earright);
            this.earbase.func_78792_a(this.earleft1);
            this.earbase.func_78792_a(this.earright1);
        } else if (id == 3) {
            this.mhornbr = new ModelRenderer((ModelBase)this, 32, 0);
            this.mhornbr.func_78789_a(-6.5f, -8.0f, -1.5f, 4, 3, 3);
            this.mhornbr.func_78793_a(0.0f, 0.0f, 0.0f);
            this.mhornbr.func_78787_b(64, 32);
            this.setRotation(this.mhornbr, 0.0f, 0.0f, 0.0f);
            this.mhornr1 = new ModelRenderer((ModelBase)this, 32, 6);
            this.mhornr1.func_78789_a(-12.0f, -1.0f, -1.0f, 4, 2, 2);
            this.mhornr1.func_78793_a(0.0f, 0.0f, 0.0f);
            this.mhornr1.func_78787_b(64, 32);
            this.setRotation(this.mhornr1, 0.0f, 0.0f, 0.7853982f);
            this.mhornr2 = new ModelRenderer((ModelBase)this, 32, 10);
            this.mhornr2.func_78789_a(-12.0f, 8.0f, -0.5f, 4, 1, 1);
            this.mhornr2.func_78793_a(0.0f, 0.0f, 0.0f);
            this.mhornr2.func_78787_b(64, 32);
            this.setRotation(this.mhornr2, 0.0f, 0.0f, 1.570796f);
            this.mhornbl = new ModelRenderer((ModelBase)this, 32, 0);
            this.mhornbl.field_78809_i = true;
            this.mhornbl.func_78789_a(2.5f, -8.0f, -1.5f, 4, 3, 3);
            this.mhornbl.func_78793_a(0.0f, 0.0f, 0.0f);
            this.mhornbl.func_78787_b(64, 32);
            this.setRotation(this.mhornbl, 0.0f, 0.0f, 0.0f);
            this.mhornl1 = new ModelRenderer((ModelBase)this, 32, 6);
            this.mhornl1.field_78809_i = true;
            this.mhornl1.func_78789_a(8.0f, -1.0f, -1.0f, 4, 2, 2);
            this.mhornl1.func_78793_a(0.0f, 0.0f, 0.0f);
            this.mhornl1.func_78787_b(64, 32);
            this.setRotation(this.mhornl1, 0.0f, 0.0f, -0.7853982f);
            this.mhornl2 = new ModelRenderer((ModelBase)this, 32, 10);
            this.mhornl2.field_78809_i = true;
            this.mhornl2.func_78789_a(8.0f, 8.0f, -0.5f, 4, 1, 1);
            this.mhornl2.func_78793_a(0.0f, 0.0f, 0.0f);
            this.mhornl2.func_78787_b(64, 32);
            this.setRotation(this.mhornl2, 0.0f, 0.0f, -1.570796f);
            this.mhornbase = new ModelRenderer((ModelBase)this, 0, 0);
            this.mhornbase.func_78789_a(-4.0f, -8.0f, -4.0f, 8, 8, 8);
            this.mhornbase.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.mhornbase, 0.0f, 0.0f, 0.0f);
            this.field_78116_c.func_78792_a(this.mhornbase);
            this.field_78116_c.func_78792_a(this.mhornbase);
            this.mhornbase.func_78792_a(this.mhornbl);
            this.mhornbase.func_78792_a(this.mhornbr);
            this.mhornbase.func_78792_a(this.mhornr1);
            this.mhornbase.func_78792_a(this.mhornr2);
            this.mhornbase.func_78792_a(this.mhornl1);
            this.mhornbase.func_78792_a(this.mhornl2);
        } else if (id == 4) {
            this.eye = new ModelRenderer((ModelBase)this, 0, 0);
            this.eye.func_78790_a(-2.5f, -4.5f, -5.1f, 2, 2, 1, 0.1f);
            this.eye.func_78793_a(0.0f, -0.0f, 0.0f);
            this.Shape1 = new ModelRenderer((ModelBase)this, 24, 0);
            this.Shape1.func_78789_a(0.0f, -6.0f, -5.0f, 4, 4, 1);
            this.Shape1.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.Shape1, 0.0f, 1.570796f, 0.0f);
            this.Shape2 = new ModelRenderer((ModelBase)this, 0, 3);
            this.Shape2.func_78789_a(-3.0f, -5.0f, -5.0f, 3, 3, 1);
            this.Shape2.func_78793_a(0.0f, 0.0f, 0.0f);
            this.mask = new ModelRenderer((ModelBase)this, 0, 0);
            this.mask.func_78789_a(-4.0f, -8.0f, -4.0f, 8, 8, 8);
            this.mask.func_78793_a(0.0f, 0.0f, 0.0f);
            this.mask.func_78792_a(this.eye);
            this.mask.func_78792_a(this.Shape1);
            this.mask.func_78792_a(this.Shape2);
            this.field_78116_c.func_78792_a(this.mask);
            this.field_78116_c.func_78792_a(this.mask);
        } else if (id == 5) {
            this.xicor1 = new ModelRenderer((ModelBase)this, 0, 0);
            this.xicor1.field_78809_i = true;
            this.xicor1.func_78789_a(-0.5f, -0.5f, -1.5f, 1, 1, 1);
            this.xicor1.func_78793_a(2.0f, -1.0f, -3.0f);
            this.setRotation(this.xicor1, 0.7853982f, -0.2094395f, 0.0f);
            this.xicor2 = new ModelRenderer((ModelBase)this, 0, 0);
            this.xicor2.func_78789_a(-0.5f, -0.5f, -1.5f, 1, 1, 1);
            this.xicor2.func_78793_a(-2.0f, -1.0f, -3.0f);
            this.xicor2.field_78809_i = true;
            this.setRotation(this.xicor2, 0.7853982f, 0.2094395f, 0.0f);
            this.xicorbase = new ModelRenderer((ModelBase)this, 0, 0);
            this.xicorbase.func_78789_a(-4.0f, -8.0f, -4.0f, 8, 8, 8);
            this.xicorbase.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.xicorbase, 0.0f, 0.0f, 0.0f);
            this.xicor3 = new ModelRenderer((ModelBase)this, 32, 0);
            this.xicor3.field_78809_i = false;
            this.xicor3.func_78789_a(-1.0f, -3.0f, -1.0f, 2, 3, 2);
            this.xicor3.func_78793_a(-3.0f, -7.0f, -3.0f);
            this.setRotation(this.xicor3, 0.3839724f, 0.0f, -0.1919862f);
            this.xicor4 = new ModelRenderer((ModelBase)this, 32, 0);
            this.xicor4.field_78809_i = true;
            this.xicor4.func_78789_a(-1.0f, -3.0f, -1.0f, 2, 3, 2);
            this.xicor4.func_78793_a(3.0f, -7.0f, -3.0f);
            this.setRotation(this.xicor4, 0.3839724f, 0.0f, 0.1919862f);
            this.xicor5 = new ModelRenderer((ModelBase)this, 32, 5);
            this.xicor5.field_78809_i = false;
            this.xicor5.func_78790_a(-0.5f, -2.5f, -0.5f, 1, 3, 1, 0.2f);
            this.xicor5.func_78793_a(-3.5f, -10.0f, -4.0f);
            this.setRotation(this.xicor5, 0.0f, 0.0f, 0.0f);
            this.xicor6 = new ModelRenderer((ModelBase)this, 32, 5);
            this.xicor6.field_78809_i = true;
            this.xicor6.func_78790_a(-0.5f, -2.5f, -0.5f, 1, 3, 1, 0.2f);
            this.xicor6.func_78793_a(3.5f, -10.0f, -4.0f);
            this.setRotation(this.xicor6, 0.0f, 0.0f, 0.0f);
            this.field_78116_c.func_78792_a(this.xicorbase);
            this.field_78116_c.func_78792_a(this.xicorbase);
            this.xicorbase.func_78792_a(this.xicor1);
            this.xicorbase.func_78792_a(this.xicor2);
            this.xicorbase.func_78792_a(this.xicor3);
            this.xicorbase.func_78792_a(this.xicor4);
            this.xicorbase.func_78792_a(this.xicor5);
            this.xicorbase.func_78792_a(this.xicor6);
        } else if (id == 6) {
            this.tailjb = new ModelRenderer((ModelBase)this, 40, 16);
            this.tailjb.func_78790_a(-4.0f, 9.0f, -2.0f, 8, 3, 4, par1 * 0.5f);
            this.tailjb.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.tailjb, 0.0f, 0.0f, 0.0f);
            this.tailj2 = new ModelRenderer((ModelBase)this, 0, 0);
            this.tailj2.func_78793_a(0.0f, 0.5f, 7.5f);
            this.tailj2.func_78790_a(-2.0f, -1.5f, 0.0f, 4, 3, 9, -0.4f);
            this.setRotateAngle(this.tailj2, 0.34906584f, 0.0f, 0.0f);
            this.tailj1 = new ModelRenderer((ModelBase)this, 0, 0);
            this.tailj1.func_78793_a(0.0f, 10.0f, 1.0f);
            this.tailj1.func_78790_a(-2.0f, -1.5f, 0.0f, 4, 3, 9, 0.0f);
            this.setRotateAngle(this.tailj1, -0.34906584f, 0.0f, 0.0f);
            this.tailj3 = new ModelRenderer((ModelBase)this, 0, 0);
            this.tailj3.func_78793_a(0.0f, 0.5f, 7.5f);
            this.tailj3.func_78790_a(-2.0f, -1.5f, 0.0f, 4, 3, 9, -0.6f);
            this.setRotateAngle(this.tailj3, 0.34906584f, 0.0f, 0.0f);
            this.cellmax = new ModelRenderer((ModelBase)this, 0, 22);
            this.cellmax.func_78790_a(-2.5f, -2.5f, 0.0f, 5, 5, 5, 0.2f);
            this.cellmax.func_78793_a(0.0f, -0.5f, 6.0f);
            this.setRotation(this.cellmax, 0.0f, 0.0f, 0.0f);
            this.tailj1.func_78792_a(this.tailj2);
            this.tailjb.func_78792_a(this.tailj1);
            this.tailj2.func_78792_a(this.tailj3);
            this.tailj3.func_78792_a(this.cellmax);
            this.field_78115_e.func_78792_a(this.tailjb);
            this.bottom.func_78792_a(this.tailjb);
        } else if (id == 7) {
            this.Phead = new ModelRenderer((ModelBase)this, 0, 1);
            this.Phead.func_78793_a(9.0f, -7.0f, 5.0f);
            this.Phead.func_78790_a(-2.0f, -3.5f, -2.0f, 4, 3, 4, 0.5f);
            this.setRotation(this.Phead, 0.0f, 0.0f, 0.0f);
            this.Ear1 = new ModelRenderer((ModelBase)this, 15, 0);
            this.Ear1.func_78789_a(-2.0f, -3.9f, -1.0f, 3, 4, 0);
            this.Ear1.func_78793_a(-1.0f, -4.0f, 0.0f);
            this.setRotation(this.Ear1, 0.0f, 0.0f, 0.0f);
            this.Ear2 = new ModelRenderer((ModelBase)this, 15, 0);
            this.Ear2.field_78809_i = true;
            this.Ear2.func_78789_a(-1.0f, -3.9f, -1.0f, 3, 4, 0);
            this.Ear2.func_78793_a(1.0f, -4.0f, 0.0f);
            this.setRotation(this.Ear2, 0.0f, 0.0f, 0.0f);
            this.Pcheek1 = new ModelRenderer((ModelBase)this, 16, 6);
            this.Pcheek1.field_78809_i = true;
            this.Pcheek1.func_78790_a(-1.0f, -1.0f, 0.0f, 2, 2, 0, 0.01f);
            this.Pcheek1.func_78793_a(-2.5f, -1.5f, -1.0f);
            this.setRotation(this.Pcheek1, 0.0f, 0.384f, 0.7854f);
            this.Pcheek2 = new ModelRenderer((ModelBase)this, 16, 6);
            this.Pcheek2.field_78809_i = false;
            this.Pcheek2.func_78790_a(-1.0f, -1.0f, 0.0f, 2, 2, 0, 0.01f);
            this.Pcheek2.func_78793_a(2.5f, -1.5f, -1.0f);
            this.setRotation(this.Pcheek2, 0.0f, -0.384f, -0.7854f);
            this.Pbody = new ModelRenderer((ModelBase)this, 6, 10);
            this.Pbody.func_78789_a(7.0f, -7.0f, 3.0f, 4, 4, 4);
            this.Pbody.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.Pbody, 0.0f, 0.0f, 0.0f);
            this.Parm1 = new ModelRenderer((ModelBase)this, 22, 10);
            this.Parm1.func_78789_a(-1.0f, 0.0f, -1.0f, 1, 4, 2);
            this.Parm1.func_78793_a(7.0f, -6.5f, 5.0f);
            this.setRotation(this.Parm1, 0.0f, 0.0f, 0.2094f);
            this.Parm2 = new ModelRenderer((ModelBase)this, 22, 10);
            this.Parm2.field_78809_i = true;
            this.Parm2.func_78789_a(0.0f, 0.0f, -1.0f, 1, 4, 2);
            this.Parm2.func_78793_a(11.0f, -6.5f, 5.0f);
            this.setRotation(this.Parm2, 0.0f, 0.0f, -0.2094f);
            this.Pfeet1 = new ModelRenderer((ModelBase)this, 0, 10);
            this.Pfeet1.func_78789_a(-1.0f, -0.5f, -0.5f, 2, 3, 1);
            this.Pfeet1.func_78793_a(8.0f, -2.5f, 5.0f);
            this.setRotation(this.Pfeet1, -0.6108652f, 0.2617994f, 0.0f);
            this.Pfeet2 = new ModelRenderer((ModelBase)this, 0, 10);
            this.Pfeet2.field_78809_i = true;
            this.Pfeet2.func_78789_a(-1.0f, -0.5f, -0.5f, 2, 3, 1);
            this.Pfeet2.func_78793_a(10.0f, -2.5f, 5.0f);
            this.setRotation(this.Pfeet2, -0.6108652f, -0.2617994f, 0.0f);
            this.Ptail = new ModelRenderer((ModelBase)this, 21, 0);
            this.Ptail.func_78789_a(-0.5f, 0.5f, -0.5f, 1, 7, 1);
            this.Ptail.func_78793_a(9.0f, -4.5f, 7.0f);
            this.setRotation(this.Ptail, 0.2617994f, 0.0f, 0.0f);
            this.field_78115_e.func_78792_a(this.Pbody);
            this.Pbody.func_78792_a(this.Phead);
            this.Phead.func_78792_a(this.Pcheek1);
            this.Phead.func_78792_a(this.Pcheek2);
            this.Phead.func_78792_a(this.Ear1);
            this.Phead.func_78792_a(this.Ear2);
            this.Pbody.func_78792_a(this.Parm1);
            this.Pbody.func_78792_a(this.Parm2);
            this.Pbody.func_78792_a(this.Pfeet1);
            this.Pbody.func_78792_a(this.Pfeet2);
            this.Pbody.func_78792_a(this.Ptail);
        } else if (id == 8) {
            this.ggmask = new ModelRenderer((ModelBase)this, 0, 0);
            this.ggmask.func_78789_a(-3.5f, -9.0f, -4.5f, 7, 9, 0);
            this.ggmask.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.ggmask, 0.0f, 0.0f, 0.0f);
            this.field_78116_c.func_78792_a(this.ggmask);
            this.field_78116_c.func_78792_a(this.ggmask);
        } else if (id == 9) {
            this.hoja = new ModelRenderer((ModelBase)this, 0, 0);
            this.hoja.func_78790_a(-7.0f, 3.0f, 3.0f, 18, 2, 0, 0.1f);
            this.hoja.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.hoja, 0.0f, 0.0f, 0.837758f);
            this.cuna = new ModelRenderer((ModelBase)this, 0, 2);
            this.cuna.func_78790_a(-1.0f, -2.0f, 0.0f, 2, 4, 0, 0.02f);
            this.cuna.func_78793_a(-4.5f, 1.0f, 3.0f);
            this.setRotation(this.cuna, 2.303835f, 1.570796f, 0.0f);
            this.field_78115_e.func_78792_a(this.hoja);
            this.field_78115_e.func_78792_a(this.cuna);
        } else if (id == 10) {
            this.field_78090_t = 256;
            this.field_78089_u = 128;
            this.knob = new ModelRenderer((ModelBase)this, 0, 112);
            this.knob.func_78793_a(0.0f, 28.0f, 0.0f);
            this.knob.func_78790_a(-4.0f, 0.0f, -4.0f, 8, 8, 8, 0.0f);
            this.handle = new ModelRenderer((ModelBase)this, 0, 0);
            this.handle.func_78793_a(0.0f, 3.0f, 4.0f);
            this.handle.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 28, 4, 0.0f);
            this.setRotateAngle(this.handle, 1.5707964f, 0.0f, 1.5707964f);
            this.blade = new ModelRenderer((ModelBase)this, 16, 0);
            this.blade.func_78793_a(0.0f, 0.0f, 0.0f);
            this.blade.func_78790_a(-6.0f, -96.0f, 0.0f, 12, 96, 0, 0.1f);
            this.cross = new ModelRenderer((ModelBase)this, 0, 104);
            this.cross.func_78793_a(0.0f, -0.6f, 0.0f);
            this.cross.func_78790_a(-10.0f, -2.0f, -2.0f, 20, 4, 4, 0.0f);
            this.setRotateAngle(this.cross, 0.7853982f, 0.0f, 0.0f);
            this.handle.func_78792_a(this.knob);
            this.handle.func_78792_a(this.blade);
            this.handle.func_78792_a(this.cross);
            this.field_78115_e.func_78792_a(this.handle);
        } else if (id == 11) {
            this.scarbase = new ModelRenderer((ModelBase)this, 0, 0);
            this.scarbase.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, -0.11f);
            this.scarbase.func_78793_a(0.0f, 0.0f, 0.0f);
            this.scarbase.func_78787_b(64, 32);
            this.setRotation(this.scarbase, 0.0f, 0.0f, 0.0f);
            this.field_78116_c.func_78792_a(this.scarbase);
            this.field_78116_c.func_78792_a(this.scarbase);
            this.xicorbase = new ModelRenderer((ModelBase)this, 0, 0);
            this.xicorbase.func_78789_a(-4.0f, -8.0f, -4.0f, 8, 8, 8);
            this.xicorbase.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.xicorbase, 0.0f, 0.0f, 0.0f);
        } else if (id == 12) {
            this.field_78090_t = 128;
            this.field_78089_u = 64;
            this.aeoshead = new ModelRenderer((ModelBase)this, 0, 0);
            this.aeoshead.field_78809_i = false;
            this.aeoshead.func_78789_a(-4.0f, -8.0f, -4.0f, 8, 8, 8);
            this.aeoshead.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.aeoshead, 0.0f, 0.0f, 0.0f);
            this.hat1 = new ModelRenderer((ModelBase)this, 32, 0);
            this.hat1.func_78790_a(-5.0f, -9.0f, -6.9f, 10, 6, 6, 0.3f);
            this.hat1.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.hat1, -0.2617994f, 0.0f, 0.0f);
            this.hat2 = new ModelRenderer((ModelBase)this, 32, 12);
            this.hat2.func_78790_a(-5.0f, -9.5f, -7.9f, 10, 6, 6, 0.0f);
            this.hat2.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.hat2, -0.4363323f, 0.0f, 0.0f);
            this.hat3 = new ModelRenderer((ModelBase)this, 32, 24);
            this.hat3.func_78790_a(-5.0f, -11.0f, -8.9f, 10, 8, 6, -0.3f);
            this.hat3.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.hat3, -0.6981317f, 0.0f, 0.0f);
            this.handr = new ModelRenderer((ModelBase)this, 64, -2);
            this.handr.field_78809_i = true;
            this.handr.func_78789_a(-5.4f, -6.0f, -9.0f, 0, 5, 2);
            this.handr.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.handr, -0.6981317f, 0.0f, 0.0f);
            this.handl = new ModelRenderer((ModelBase)this, 64, -2);
            this.handl.field_78809_i = true;
            this.handl.func_78789_a(5.4f, -6.0f, -9.0f, 0, 5, 2);
            this.handl.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.handl, -0.6981317f, 0.0f, 0.0f);
            this.field_78116_c.func_78792_a(this.aeoshead);
            this.field_78116_c.func_78792_a(this.aeoshead);
            this.aeoshead.func_78792_a(this.hat1);
            this.aeoshead.func_78792_a(this.hat2);
            this.aeoshead.func_78792_a(this.hat3);
            this.aeoshead.func_78792_a(this.handr);
            this.aeoshead.func_78792_a(this.handl);
        } else if (id == 13) {
            this.field_78090_t = 128;
            this.field_78089_u = 64;
            this.beardbase = new ModelRenderer((ModelBase)this, 0, 0);
            this.beardbase.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 16, 8, -0.108f);
            this.beardbase.func_78793_a(0.0f, 0.0f, 0.0f);
            this.setRotation(this.beardbase, 0.0f, 0.0f, 0.0f);
            this.field_78116_c.func_78792_a(this.beardbase);
            this.field_78116_c.func_78792_a(this.beardbase);
        }
    }

    @Override
    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        float r3;
        float ex;
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        GL11.glPushMatrix();
        float f6 = this.size;
        if (JRMCoreH.JYC()) {
            float age = JRMCoreHJYC.JYCAge((EntityPlayer)entity);
            float childScl = JRMCoreHJYC.JYCsizeBasedOnAge((EntityPlayer)entity);
            this.size = childScl = 3.0f - childScl * 2.0f;
        }
        if (this.id == 0) {
            GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
            this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
            GL11.glPushMatrix();
            GL11.glScalef((float)1.2f, (float)1.2f, (float)1.2f);
            this.BoTbase.func_78785_a(f5);
            GL11.glPopMatrix();
        } else if (this.id == 1) {
            GL11.glScalef((float)(0.8f / f6), (float)(1.0f / f6), (float)(0.8f / f6));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
            this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
            GL11.glPushMatrix();
            GL11.glScalef((float)1.3f, (float)1.0f, (float)1.3f);
            this.scarfbase.func_78785_a(f5);
            GL11.glPopMatrix();
        } else if (this.id == 2) {
            float fx6 = 1.0f;
            GL11.glScalef((float)(0.5f + 0.53f / f6), (float)(0.5f + 0.53f / f6), (float)(0.5f + 0.53f / f6));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.0f), (float)0.0f);
            GL11.glPushMatrix();
            GL11.glScalef((float)((0.5f + 0.5f / fx6) * (g <= 1 ? 1.0f : 0.85f)), (float)(0.5f + 0.5f / fx6), (float)((0.5f + 0.5f / fx6) * (g <= 1 ? 1.0f : 0.85f)));
            GL11.glTranslatef((float)0.0f, (float)((fx6 - 1.0f) / fx6 * (2.0f - (fx6 >= 1.5f && fx6 <= 2.0f ? (2.0f - fx6) / 2.5f : (fx6 < 1.5f && fx6 >= 1.0f ? (fx6 * 2.0f - 2.0f) * 0.2f : 0.0f)))), (float)0.0f);
            this.earbase.func_78785_a(f5);
            GL11.glPopMatrix();
        } else if (this.id == 3) {
            float fx6 = 1.0f;
            GL11.glScalef((float)(0.5f + 0.53f / f6), (float)(0.5f + 0.53f / f6), (float)(0.5f + 0.53f / f6));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.0f), (float)0.0f);
            GL11.glPushMatrix();
            GL11.glScalef((float)((0.5f + 0.5f / fx6) * (g <= 1 ? 1.0f : 0.85f)), (float)(0.5f + 0.5f / fx6), (float)((0.5f + 0.5f / fx6) * (g <= 1 ? 1.0f : 0.85f)));
            GL11.glTranslatef((float)0.0f, (float)((fx6 - 1.0f) / fx6 * (2.0f - (fx6 >= 1.5f && fx6 <= 2.0f ? (2.0f - fx6) / 2.5f : (fx6 < 1.5f && fx6 >= 1.0f ? (fx6 * 2.0f - 2.0f) * 0.2f : 0.0f)))), (float)0.0f);
            this.mhornbase.func_78785_a(f5);
            GL11.glPopMatrix();
        } else if (this.id == 4) {
            float fx6 = 1.0f;
            GL11.glScalef((float)(0.5f + 0.53f / f6), (float)(0.5f + 0.53f / f6), (float)(0.5f + 0.53f / f6));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.0f), (float)0.0f);
            GL11.glPushMatrix();
            GL11.glScalef((float)((0.5f + 0.5f / fx6) * (g <= 1 ? 1.0f : 0.85f)), (float)(0.5f + 0.5f / fx6), (float)((0.5f + 0.5f / fx6) * (g <= 1 ? 1.0f : 0.85f)));
            GL11.glTranslatef((float)0.0f, (float)((fx6 - 1.0f) / fx6 * (2.0f - (fx6 >= 1.5f && fx6 <= 2.0f ? (2.0f - fx6) / 2.5f : (fx6 < 1.5f && fx6 >= 1.0f ? (fx6 * 2.0f - 2.0f) * 0.2f : 0.0f)))), (float)0.0f);
            this.mask.func_78785_a(f5);
            GL11.glPopMatrix();
        } else if (this.id == 5) {
            float fx6 = 1.0f;
            GL11.glScalef((float)(0.5f + 0.53f / f6), (float)(0.5f + 0.53f / f6), (float)(0.5f + 0.53f / f6));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.0f), (float)0.0f);
            GL11.glPushMatrix();
            GL11.glScalef((float)((0.5f + 0.5f / fx6) * (g <= 1 ? 1.0f : 0.85f)), (float)(0.5f + 0.5f / fx6), (float)((0.5f + 0.5f / fx6) * (g <= 1 ? 1.0f : 0.85f)));
            GL11.glTranslatef((float)0.0f, (float)((fx6 - 1.0f) / fx6 * (2.0f - (fx6 >= 1.5f && fx6 <= 2.0f ? (2.0f - fx6) / 2.5f : (fx6 < 1.5f && fx6 >= 1.0f ? (fx6 * 2.0f - 2.0f) * 0.2f : 0.0f)))), (float)0.0f);
            this.xicorbase.func_78785_a(f5);
            GL11.glPopMatrix();
        } else if (this.id == 6) {
            GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
            this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
            GL11.glPushMatrix();
            GL11.glScalef((float)1.2f, (float)1.2f, (float)1.2f);
            ex = entity.field_70173_aa;
            r3 = MathHelper.func_76134_b((float)(ex * 0.14f)) * 0.1f;
            float r4 = MathHelper.func_76134_b((float)(ex / 8.0f)) / 5.0f + 0.1f;
            r4 = MathHelper.func_76134_b((float)(ex / 8.0f)) / 3.0f - 0.2f;
            this.tailjb.field_82908_p = -0.2f;
            this.tailj1.field_78795_f = 0.2f;
            this.tailj1.field_78795_f += r4 / 0.6f;
            this.tailj1.field_78796_g = 0.2f;
            this.tailj1.field_78796_g += r3 / 0.6f;
            this.tailj2.field_78796_g = 0.0f;
            this.tailj2.field_78796_g += r3 / 0.6f;
            this.tailj3.field_78796_g = 0.2f;
            this.tailj3.field_78796_g += r3 / 0.6f;
            this.tailjb.func_78785_a(f5);
            GL11.glPopMatrix();
        } else if (this.id == 7) {
            GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
            this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
            GL11.glPushMatrix();
            GL11.glScalef((float)1.2f, (float)1.2f, (float)1.2f);
            if (entity.func_70051_ag()) {
                this.Pbody.field_82906_o = -0.05f;
                this.Pbody.field_78795_f = 0.8f;
                this.Phead.field_82908_p = 0.02f;
                this.Phead.field_78795_f = -0.8f;
                this.Pcheek1.field_78795_f = -0.8f;
                this.Pcheek2.field_78795_f = -0.8f;
                ex = entity.field_70173_aa;
                r3 = MathHelper.func_76134_b((float)(ex * 0.14f)) * 0.1f;
                float r4 = MathHelper.func_76134_b((float)(ex / 8.0f)) / 5.0f + 0.1f;
                r4 = MathHelper.func_76134_b((float)(ex / 8.0f)) / 3.0f - 0.2f;
                this.Ptail.field_78795_f = 0.6f;
                this.Ptail.field_78795_f += r4 / 4.0f;
                this.Pfeet1.field_78795_f = -0.3f;
                this.Pfeet1.field_78795_f += r4 / 1.0f;
                this.Pfeet2.field_78795_f = -0.3f;
                this.Pfeet2.field_78795_f += r4 / 1.0f;
                this.Parm1.field_78795_f = 0.4f;
                this.Parm1.field_78795_f += r4 / 4.0f;
                this.Parm2.field_78795_f = 0.4f;
                this.Parm2.field_78795_f += r4 / 4.0f;
                this.Ear1.field_78795_f = 0.2f;
                this.Ear1.field_78795_f += r4 / 2.0f;
                this.Ear2.field_78795_f = 0.2f;
                this.Ear2.field_78795_f += r4 / 2.0f;
                this.Pcheek1.field_78795_f = 0.2f;
                this.Pcheek1.field_78795_f += r4 / 2.0f;
                this.Pcheek2.field_78795_f = 0.2f;
                this.Pcheek2.field_78795_f += r4 / 2.0f;
            } else {
                ex = entity.field_70173_aa;
                r3 = MathHelper.func_76134_b((float)(ex * 0.14f)) * 0.1f;
                float r4 = MathHelper.func_76134_b((float)(ex / 8.0f)) / 5.0f + 0.1f;
                r4 = MathHelper.func_76134_b((float)(ex / 8.0f)) / 3.0f - 0.2f;
                this.Pbody.field_82908_p = 0.0f;
                this.Pbody.field_82908_p += r4 / 2.0f;
                this.Ptail.field_78795_f = 0.6f;
                this.Ptail.field_78795_f += r4 / 0.7f;
                this.Ptail.field_78808_h = 0.0f;
                this.Ptail.field_78808_h += r3 / 1.2f;
                this.Pfeet1.field_78795_f = -0.3f;
                this.Pfeet1.field_78795_f += r3 / 1.0f;
                this.Pfeet2.field_78795_f = -0.3f;
                this.Pfeet2.field_78795_f += r3 / 1.0f;
                this.Parm1.field_78795_f = -0.2f;
                this.Parm1.field_78795_f += r4 / 4.0f;
                this.Parm2.field_78795_f = -0.2f;
                this.Parm2.field_78795_f += r4 / 4.0f;
                this.Ear1.field_78795_f = 0.2f;
                this.Ear1.field_78795_f += r4 / 2.0f;
                this.Ear2.field_78795_f = 0.2f;
                this.Ear2.field_78795_f += r4 / 2.0f;
                this.Pcheek1.field_82908_p = 0.0f;
                this.Pcheek2.field_82908_p = 0.0f;
                this.Pcheek1.field_78795_f = 0.2f;
                this.Pcheek1.field_78795_f += r4 / 2.0f;
                this.Pcheek2.field_78795_f = 0.2f;
                this.Pcheek2.field_78795_f += r4 / 2.0f;
                this.setRotateAngle(this.Pbody, 0.0f, 0.0f, 0.0f);
                this.Phead.field_82908_p = 0.0f;
                this.Phead.field_78795_f = 0.0f;
            }
            this.Pbody.func_78785_a(f5);
            GL11.glPopMatrix();
        } else if (this.id == 8) {
            float fx6 = 1.0f;
            GL11.glScalef((float)(0.5f + 0.53f / f6), (float)(0.5f + 0.53f / f6), (float)(0.5f + 0.53f / f6));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.0f), (float)0.0f);
            GL11.glPushMatrix();
            GL11.glScalef((float)((0.5f + 0.5f / fx6) * (g <= 1 ? 1.0f : 0.85f)), (float)(0.5f + 0.5f / fx6), (float)((0.5f + 0.5f / fx6) * (g <= 1 ? 1.0f : 0.85f)));
            GL11.glTranslatef((float)0.0f, (float)((fx6 - 1.0f) / fx6 * (2.0f - (fx6 >= 1.5f && fx6 <= 2.0f ? (2.0f - fx6) / 2.5f : (fx6 < 1.5f && fx6 >= 1.0f ? (fx6 * 2.0f - 2.0f) * 0.2f : 0.0f)))), (float)0.0f);
            this.ggmask.func_78785_a(f5);
            GL11.glPopMatrix();
        } else if (this.id == 9) {
            GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.5f), (float)0.0f);
            this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
            GL11.glPushMatrix();
            GL11.glScalef((float)1.2f, (float)1.2f, (float)1.2f);
            this.hoja.func_78785_a(f5);
            this.cuna.func_78785_a(f5);
            GL11.glPopMatrix();
        } else if (this.id == 10) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(-this.handle.field_82906_o), (float)(-this.handle.field_82908_p), (float)(-this.handle.field_82907_q));
            GL11.glTranslatef((float)(this.handle.field_78800_c * f5), (float)(this.handle.field_78797_d * f5), (float)(this.handle.field_78798_e * f5));
            GL11.glScaled((double)0.3, (double)0.3, (double)0.3);
            GL11.glTranslatef((float)this.handle.field_82906_o, (float)this.handle.field_82908_p, (float)this.handle.field_82907_q);
            GL11.glTranslatef((float)(this.handle.field_78800_c * f5), (float)(this.handle.field_78797_d * f5), (float)(this.handle.field_78798_e * f5));
            this.handle.func_78785_a(f5);
            GL11.glPopMatrix();
        } else if (this.id == 11) {
            float fx6 = 1.0f;
            GL11.glScalef((float)(0.5f + 0.53f / f6), (float)(0.5f + 0.53f / f6), (float)(0.5f + 0.53f / f6));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.0f), (float)0.0f);
            GL11.glPushMatrix();
            GL11.glScalef((float)((0.5f + 0.5f / fx6) * (g <= 1 ? 1.0f : 0.85f)), (float)(0.5f + 0.5f / fx6), (float)((0.5f + 0.5f / fx6) * (g <= 1 ? 1.0f : 0.85f)));
            GL11.glTranslatef((float)0.0f, (float)((fx6 - 1.0f) / fx6 * (2.0f - (fx6 >= 1.5f && fx6 <= 2.0f ? (2.0f - fx6) / 2.5f : (fx6 < 1.5f && fx6 >= 1.0f ? (fx6 * 2.0f - 2.0f) * 0.2f : 0.0f)))), (float)0.0f);
            this.scarbase.func_78785_a(f5);
            GL11.glPopMatrix();
        } else if (this.id == 12) {
            float fx6 = 1.0f;
            GL11.glScalef((float)(0.5f + 0.53f / f6), (float)(0.5f + 0.53f / f6), (float)(0.5f + 0.53f / f6));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.0f), (float)0.0f);
            GL11.glPushMatrix();
            GL11.glScalef((float)((0.5f + 0.5f / fx6) * (g <= 1 ? 1.0f : 0.85f)), (float)(0.5f + 0.5f / fx6), (float)((0.5f + 0.5f / fx6) * (g <= 1 ? 1.0f : 0.85f)));
            GL11.glTranslatef((float)0.0f, (float)((fx6 - 1.0f) / fx6 * (2.0f - (fx6 >= 1.5f && fx6 <= 2.0f ? (2.0f - fx6) / 2.5f : (fx6 < 1.5f && fx6 >= 1.0f ? (fx6 * 2.0f - 2.0f) * 0.2f : 0.0f)))), (float)0.0f);
            this.aeoshead.func_78785_a(f5);
            GL11.glPopMatrix();
        } else if (this.id == 13) {
            float fx6 = 1.0f;
            GL11.glScalef((float)(0.5f + 0.53f / f6), (float)(0.5f + 0.53f / f6), (float)(0.5f + 0.53f / f6));
            GL11.glTranslatef((float)0.0f, (float)((f6 - 1.0f) * 1.0f), (float)0.0f);
            GL11.glPushMatrix();
            GL11.glScalef((float)((0.5f + 0.5f / fx6) * (g <= 1 ? 1.0f : 0.85f)), (float)(0.5f + 0.5f / fx6), (float)((0.5f + 0.5f / fx6) * (g <= 1 ? 1.0f : 0.85f)));
            GL11.glTranslatef((float)0.0f, (float)((fx6 - 1.0f) / fx6 * (2.0f - (fx6 >= 1.5f && fx6 <= 2.0f ? (2.0f - fx6) / 2.5f : (fx6 < 1.5f && fx6 >= 1.0f ? (fx6 * 2.0f - 2.0f) * 0.2f : 0.0f)))), (float)0.0f);
            this.beardbase.func_78785_a(f5);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    @Override
    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        super.func_78087_a(par1, par2, par3, par4, par5, par6, entity);
        if (this.id == 0) {
            // empty if block
        }
        if (this.id == 1) {
            float s3;
            float s2;
            float s = 0.0f;
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.0f * par2;
                s3 = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.0f * par2;
                this.scarf5.field_78795_f = (s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s)) / 2.0f;
                this.scarf6.field_78795_f = (s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s)) / 2.0f;
                this.scarf2.field_78795_f = (s3 + s < s ? s3 + s : (s2 + s < s ? s2 + s : s)) / 2.0f;
                this.scarf3.field_78795_f = (s3 + s < s ? s3 + s : (s2 + s < s ? s2 + s : s)) / 2.0f;
            } else {
                s2 = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.0f * par2;
                s3 = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.0f * par2;
                this.scarf5.field_78795_f = (s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s)) / 2.0f;
                this.scarf5.field_78795_f -= 0.1f;
                this.scarf6.field_78795_f = (s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s)) / 2.0f;
                this.scarf6.field_78795_f -= 0.1f;
                this.scarf2.field_78795_f = (s3 + s < s ? s3 + s : (s2 + s < s ? s2 + s : s)) / 2.0f;
                this.scarf2.field_78795_f -= 0.1f;
                this.scarf3.field_78795_f = (s3 + s < s ? s3 + s : (s2 + s < s ? s2 + s : s)) / 2.0f;
                this.scarf3.field_78795_f -= 0.1f;
            }
            if (entity.func_70093_af()) {
                s2 = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.0f * par2;
                s3 = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.0f * par2;
                this.scarfbase.field_78795_f = 0.5f;
            } else {
                this.setRotateAngle(this.scarfbase, 0.10995574f, 0.0f, 0.0f);
            }
        } else if (this.id == 2) {
            this.earbase.field_78798_e = this.field_78116_c.field_78798_e;
            this.earbase.field_78797_d = this.field_78116_c.field_78797_d;
            this.earbase.field_78800_c = this.field_78116_c.field_78800_c;
            this.earbase.field_78808_h = this.field_78116_c.field_78808_h;
            this.earbase.field_78796_g = this.field_78116_c.field_78796_g;
            this.earbase.field_78795_f = this.field_78116_c.field_78795_f;
        } else if (this.id == 3) {
            this.mhornbase.field_78798_e = this.field_78116_c.field_78798_e;
            this.mhornbase.field_78797_d = this.field_78116_c.field_78797_d;
            this.mhornbase.field_78800_c = this.field_78116_c.field_78800_c;
            this.mhornbase.field_78808_h = this.field_78116_c.field_78808_h;
            this.mhornbase.field_78796_g = this.field_78116_c.field_78796_g;
            this.mhornbase.field_78795_f = this.field_78116_c.field_78795_f;
        } else if (this.id == 4) {
            this.mask.field_78798_e = this.field_78116_c.field_78798_e;
            this.mask.field_78797_d = this.field_78116_c.field_78797_d;
            this.mask.field_78800_c = this.field_78116_c.field_78800_c;
            this.mask.field_78808_h = this.field_78116_c.field_78808_h;
            this.mask.field_78796_g = this.field_78116_c.field_78796_g;
            this.mask.field_78795_f = this.field_78116_c.field_78795_f;
        } else if (this.id == 5) {
            this.xicorbase.field_78798_e = this.field_78116_c.field_78798_e;
            this.xicorbase.field_78797_d = this.field_78116_c.field_78797_d;
            this.xicorbase.field_78800_c = this.field_78116_c.field_78800_c;
            this.xicorbase.field_78808_h = this.field_78116_c.field_78808_h;
            this.xicorbase.field_78796_g = this.field_78116_c.field_78796_g;
            this.xicorbase.field_78795_f = this.field_78116_c.field_78795_f;
        } else if (this.id == 6) {
            float s = 0.0f;
            if (entity.func_70093_af()) {
                this.tailjb.field_78795_f = 0.5f;
            } else {
                this.setRotateAngle(this.tailjb, 0.0f, 0.0f, 0.0f);
            }
        } else if (this.id == 7) {
            float r = 360.0f;
            float r2 = 180.0f;
            float n4 = par4;
            float n5 = par5;
            this.Phead.field_78796_g = n4 / r2 / (float)Math.PI;
            this.Phead.field_78795_f = n5 / r2 / (float)Math.PI;
        } else if (this.id == 8) {
            this.ggmask.field_78798_e = this.field_78116_c.field_78798_e;
            this.ggmask.field_78797_d = this.field_78116_c.field_78797_d;
            this.ggmask.field_78800_c = this.field_78116_c.field_78800_c;
            this.ggmask.field_78808_h = this.field_78116_c.field_78808_h;
            this.ggmask.field_78796_g = this.field_78116_c.field_78796_g;
            this.ggmask.field_78795_f = this.field_78116_c.field_78795_f;
        } else if (this.id == 9) {
            float s = 0.0f;
            if (entity.func_70093_af()) {
                this.hoja.field_78795_f = 0.6f;
            } else {
                this.setRotateAngle(this.hoja, 0.0f, 0.0f, 0.837758f);
                this.setRotateAngle(this.cuna, 2.303835f, 1.570796f, 0.0f);
            }
        } else if (this.id == 10) {
            float s = 0.0f;
            if (entity.func_70093_af()) {
                this.handle.field_78796_g = -0.5f;
                this.handle.field_82907_q = 0.5f;
                this.handle.field_82908_p = 0.6f;
            } else {
                this.handle.field_82907_q = 0.0f;
                this.handle.field_82908_p = 0.0f;
                this.handle.field_82906_o = 0.0f;
                this.setRotateAngle(this.handle, 1.5707964f, 0.0f, 1.5707964f);
            }
        } else if (this.id == 11) {
            this.scarbase.field_78798_e = this.field_78116_c.field_78798_e;
            this.scarbase.field_78797_d = this.field_78116_c.field_78797_d;
            this.scarbase.field_78800_c = this.field_78116_c.field_78800_c;
            this.scarbase.field_78808_h = this.field_78116_c.field_78808_h;
            this.scarbase.field_78796_g = this.field_78116_c.field_78796_g;
            this.scarbase.field_78795_f = this.field_78116_c.field_78795_f;
        } else if (this.id == 12) {
            this.aeoshead.field_78798_e = this.field_78116_c.field_78798_e;
            this.aeoshead.field_78797_d = this.field_78116_c.field_78797_d;
            this.aeoshead.field_78800_c = this.field_78116_c.field_78800_c;
            this.aeoshead.field_78808_h = this.field_78116_c.field_78808_h;
            this.aeoshead.field_78796_g = this.field_78116_c.field_78796_g;
            this.aeoshead.field_78795_f = this.field_78116_c.field_78795_f;
        } else if (this.id == 13) {
            this.beardbase.field_78798_e = this.field_78116_c.field_78798_e;
            this.beardbase.field_78797_d = this.field_78116_c.field_78797_d;
            this.beardbase.field_78800_c = this.field_78116_c.field_78800_c;
            this.beardbase.field_78808_h = this.field_78116_c.field_78808_h;
            this.beardbase.field_78796_g = this.field_78116_c.field_78796_g;
            this.beardbase.field_78795_f = this.field_78116_c.field_78795_f;
        }
    }
}

