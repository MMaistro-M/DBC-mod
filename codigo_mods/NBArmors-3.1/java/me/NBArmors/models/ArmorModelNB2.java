/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package me.NBArmors.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ArmorModelNB2
extends ModelBiped {
    public ModelRenderer leftarmshoulder1;
    public ModelRenderer rightarmshoulder1;
    public ModelRenderer cape1;
    public ModelRenderer c020;
    public ModelRenderer c019;
    public ModelRenderer hornbase1;
    public ModelRenderer horn1;
    public ModelRenderer hornbase2;
    public ModelRenderer horn2;
    public ModelRenderer antenna1;
    public ModelRenderer antenna2;
    public ModelRenderer ball;
    public ModelRenderer star;
    public ModelRenderer ant1;
    public ModelRenderer ant2;
    public ModelRenderer blade;
    public ModelRenderer tail;
    public ModelRenderer jhorn1;
    public ModelRenderer jhorn2;
    public ModelRenderer jhorn3;
    public ModelRenderer jhorn4;
    public ModelRenderer hat1;
    public ModelRenderer c15;
    public ModelRenderer c151;
    public ModelRenderer cube1;
    public ModelRenderer cube2;
    public ModelRenderer cube3;
    public ModelRenderer cube4;
    public ModelRenderer cube5;
    public ModelRenderer skirt1;
    public ModelRenderer skirt2;
    public ModelRenderer skirt3;
    public ModelRenderer skirt4;
    public ModelRenderer skirt5;
    public ModelRenderer skirt6;
    public ModelRenderer wing1;
    public ModelRenderer wing2;
    public ModelRenderer rshoulder1;
    public ModelRenderer lshoulder1;
    public ModelRenderer alal;
    public ModelRenderer alar;
    public static float f = 1.0f;
    public static int g = 1;
    public static int y = 1;

    public ArmorModelNB2(float par1) {
        super(par1, 0.0f, 128, 64);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        this.c020 = new ModelRenderer((ModelBase)this, 64, 0);
        this.c020.func_78790_a(-4.0f, -12.0f, -4.0f, 8, 4, 8, par1);
        this.c020.func_78793_a(0.0f, 0.0f, 0.0f);
        this.c020.func_78787_b(128, 64);
        this.c020.field_78809_i = true;
        this.setRotation(this.c020, 0.0f, 0.0f, 0.0f);
        this.c019 = new ModelRenderer((ModelBase)this, 64, 0);
        this.c019.func_78790_a(-1.0f, -11.0f, -0.5f, 2, 4, 2, par1);
        this.c019.func_78793_a(0.0f, 0.0f, 0.0f);
        this.c019.func_78787_b(128, 64);
        this.c019.field_78809_i = true;
        this.rightarmshoulder1 = new ModelRenderer((ModelBase)this, 64, 12);
        this.rightarmshoulder1.func_78789_a(-1.0f, -4.5f, -3.0f, 7, 4, 6);
        this.rightarmshoulder1.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.rightarmshoulder1.func_78787_b(128, 64);
        this.setRotation(this.rightarmshoulder1, 0.0f, 0.0f, 0.0f);
        this.leftarmshoulder1 = new ModelRenderer((ModelBase)this, 64, 22);
        this.leftarmshoulder1.field_78809_i = true;
        this.leftarmshoulder1.func_78789_a(-6.0f, -4.5f, -3.0f, 7, 4, 6);
        this.leftarmshoulder1.func_78793_a(5.0f, 2.0f, 0.0f);
        this.leftarmshoulder1.func_78787_b(128, 64);
        this.setRotation(this.leftarmshoulder1, 0.0f, 0.0f, -0.0f);
        this.cape1 = new ModelRenderer((ModelBase)this, 100, 0);
        this.cape1.func_78790_a(-7.0f, 1.0f, 2.0f, 14, 20, 0, 0.02f);
        this.cape1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.cape1.func_78787_b(128, 64);
        this.setRotation(this.cape1, 0.1570796f, 0.0f, 0.0f);
        this.field_78116_c.func_78792_a(this.c020);
        this.field_78116_c.func_78792_a(this.c019);
        this.field_78115_e.func_78792_a(this.cape1);
        this.field_78113_g.func_78792_a(this.leftarmshoulder1);
        this.field_78112_f.func_78792_a(this.rightarmshoulder1);
        this.blade = new ModelRenderer((ModelBase)this, 104, 32);
        this.blade.func_78790_a(0.5f, -10.0f, -2.0f, 0, 8, 8, 0.0f);
        this.blade.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.blade, 0.05f, 0.0f, 0.0f);
        this.hornbase1 = new ModelRenderer((ModelBase)this, 24, 0);
        this.hornbase1.field_78809_i = true;
        this.hornbase1.func_78793_a(3.0f, -7.0f, 0.0f);
        this.hornbase1.func_78790_a(0.0f, -1.0f, -1.0f, 5, 2, 2, 0.0f);
        this.setRotation(this.hornbase1, 0.0f, 0.0f, -0.7853982f);
        this.horn1 = new ModelRenderer((ModelBase)this, 45, 0);
        this.horn1.field_78809_i = true;
        this.horn1.func_78793_a(6.5f, -10.5f, 0.0f);
        this.horn1.func_78790_a(-0.5f, -4.0f, -0.5f, 1, 4, 1, 0.0f);
        this.hornbase2 = new ModelRenderer((ModelBase)this, 24, 0);
        this.hornbase2.func_78793_a(-3.0f, -7.0f, 0.0f);
        this.hornbase2.func_78790_a(-5.0f, -1.0f, -1.0f, 5, 2, 2, 0.0f);
        this.setRotation(this.hornbase2, 0.0f, 0.0f, 0.7853982f);
        this.horn2 = new ModelRenderer((ModelBase)this, 45, 0);
        this.horn2.func_78793_a(-6.5f, -10.5f, 0.0f);
        this.horn2.func_78790_a(-0.5f, -4.0f, -0.5f, 1, 4, 1, 0.0f);
        this.antenna1 = new ModelRenderer((ModelBase)this, 32, 0);
        this.antenna1.field_78809_i = true;
        this.antenna1.func_78793_a(2.2f, -7.0f, 0.0f);
        this.antenna1.func_78790_a(-1.0f, -7.0f, -3.5f, 3, 7, 7, 0.0f);
        this.setRotation(this.antenna1, 0.0f, 0.0f, 0.17453292f);
        this.antenna2 = new ModelRenderer((ModelBase)this, 32, 0);
        this.antenna2.func_78793_a(-2.2f, -7.0f, 0.0f);
        this.antenna2.func_78790_a(-2.0f, -7.0f, -3.5f, 3, 7, 7, 0.0f);
        this.setRotation(this.antenna2, 0.0f, 0.0f, -0.17453292f);
        this.jhorn1 = new ModelRenderer((ModelBase)this, 90, 12);
        this.jhorn1.field_78809_i = true;
        this.jhorn1.func_78789_a(-1.0f, -6.0f, -2.0f, 2, 6, 3);
        this.jhorn1.func_78793_a(2.5f, -6.3f, -2.3f);
        this.setRotation(this.jhorn1, -0.7853982f, 0.0f, 0.3839724f);
        this.jhorn2 = new ModelRenderer((ModelBase)this, 90, 12);
        this.jhorn2.func_78789_a(-1.0f, -6.0f, -2.0f, 2, 6, 3);
        this.jhorn2.func_78793_a(-2.5f, -6.3f, -2.3f);
        this.setRotation(this.jhorn2, -0.7853982f, 0.0f, -0.3839724f);
        this.jhorn3 = new ModelRenderer((ModelBase)this, 88, 0);
        this.jhorn3.field_78809_i = true;
        this.jhorn3.func_78789_a(-0.5f, -4.5f, -1.0f, 1, 5, 2);
        this.jhorn3.func_78793_a(4.2f, -10.0f, 1.0f);
        this.setRotation(this.jhorn3, -0.7853982f, 0.6108652f, 0.3839724f);
        this.jhorn4 = new ModelRenderer((ModelBase)this, 88, 0);
        this.jhorn4.func_78789_a(-0.5f, -4.5f, -1.0f, 1, 5, 2);
        this.jhorn4.func_78793_a(-4.2f, -10.0f, 1.0f);
        this.setRotation(this.jhorn4, -0.7853982f, -0.6108652f, -0.3839724f);
        this.hat1 = new ModelRenderer((ModelBase)this, 24, 42);
        this.hat1.func_78789_a(-4.0f, -1.0f, 0.0f, 8, 5, 1);
        this.hat1.func_78793_a(0.0f, -5.5f, -4.0f);
        this.setRotation(this.hat1, -1.570796f, 0.0f, 0.0f);
        this.ball = new ModelRenderer((ModelBase)this, 24, 4);
        this.ball.func_78790_a(-1.0f, -2.0f, -1.0f, 2, 2, 2, 0.2f);
        this.ball.func_78793_a(0.0f, -8.0f, 0.0f);
        this.setRotation(this.ball, 0.0f, 0.0f, 0.0f);
        this.star = new ModelRenderer((ModelBase)this, 32, 5);
        this.star.func_78789_a(-0.5f, -0.5f, -0.5f, 1, 1, 1);
        this.star.func_78793_a(0.0f, -9.0f, 0.0f);
        this.setRotation(this.star, 0.0f, 0.0f, 0.0f);
        this.ant1 = new ModelRenderer((ModelBase)this, 52, 0);
        this.ant1.func_78789_a(-0.5f, -2.0f, -4.5f, 1, 5, 5);
        this.ant1.func_78793_a(-2.0f, -7.0f, -3.0f);
        this.setRotation(this.ant1, 0.0f, 0.1745329f, 0.0f);
        this.ant2 = new ModelRenderer((ModelBase)this, 52, 10);
        this.ant2.field_78809_i = true;
        this.ant2.func_78789_a(-0.5f, -2.0f, -4.5f, 1, 5, 5);
        this.ant2.func_78793_a(2.0f, -7.0f, -3.0f);
        this.setRotation(this.ant2, 0.0f, -0.1745329f, 0.0f);
        this.c15 = new ModelRenderer((ModelBase)this, 48, 46);
        this.c15.func_78789_a(-6.0f, -11.0f, -6.8f, 12, 6, 12);
        this.c15.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.c15, -0.1745329f, 0.0f, 0.0f);
        this.c151 = new ModelRenderer((ModelBase)this, 44, 44);
        this.c151.func_78789_a(-2.0f, -14.0f, -2.8f, 4, 4, 4);
        this.c151.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.c151, -0.1745329f, 0.0f, 0.0f);
        this.cube2 = new ModelRenderer((ModelBase)this, 24, 32);
        this.cube2.field_78809_i = true;
        this.cube2.func_78790_a(-2.0f, 0.0f, 0.0f, 4, 12, 1, 0.1f);
        this.cube2.func_78793_a(4.1f, 9.0f, 0.0f);
        this.setRotation(this.cube2, -0.1047198f, -1.570796f, 0.0f);
        this.cube3 = new ModelRenderer((ModelBase)this, 34, 32);
        this.cube3.func_78790_a(-2.0f, 0.0f, 0.0f, 4, 12, 1, 0.1f);
        this.cube3.func_78793_a(-4.1f, 9.0f, 0.0f);
        this.setRotation(this.cube3, -0.1047198f, 1.570796f, 0.0f);
        this.cube4 = new ModelRenderer((ModelBase)this, 44, 32);
        this.cube4.func_78789_a(-3.0f, 0.0f, 0.0f, 6, 12, 1);
        this.cube4.func_78793_a(0.1f, 9.0f, -2.1f);
        this.setRotation(this.cube4, -0.1047198f, 0.0f, 0.0f);
        this.cube5 = new ModelRenderer((ModelBase)this, 44, 32);
        this.cube5.func_78789_a(-3.0f, 0.0f, -1.0f, 6, 8, 1);
        this.cube5.func_78793_a(-0.1f, 9.0f, 2.0f);
        this.setRotation(this.cube5, 0.1047198f, 0.0f, 0.0f);
        this.skirt1 = new ModelRenderer((ModelBase)this, 0, 32);
        this.skirt1.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, 0.13f);
        this.skirt1.func_78793_a(0.0f, 9.45f, 0.0f);
        this.setRotation(this.skirt1, -0.0872665f, 0.0f, 0.0f);
        this.skirt2 = new ModelRenderer((ModelBase)this, 0, 48);
        this.skirt2.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, 0.13f);
        this.skirt2.func_78793_a(0.0f, 9.45f, 0.0f);
        this.setRotation(this.skirt2, 0.0872665f, 0.0f, 0.0f);
        this.skirt3 = new ModelRenderer((ModelBase)this, 24, 48);
        this.skirt3.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, 0.14f);
        this.skirt3.func_78793_a(0.0f, 9.45f, 0.0f);
        this.setRotation(this.skirt3, -0.0872665f, 0.0f, 0.0f);
        this.skirt4 = new ModelRenderer((ModelBase)this, 24, 48);
        this.skirt4.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 12, 4, 0.14f);
        this.skirt4.func_78793_a(0.0f, 9.45f, 0.0f);
        this.setRotation(this.skirt4, 0.0872665f, 0.0f, 0.0f);
        this.skirt5 = new ModelRenderer((ModelBase)this, 24, 48);
        this.skirt5.func_78790_a(0.0f, 0.0f, -2.0f, 8, 12, 4, 0.13f);
        this.skirt5.func_78793_a(-4.0f, 9.45f, 0.0f);
        this.setRotation(this.skirt5, 0.0f, -0.0174533f, 0.0f);
        this.skirt6 = new ModelRenderer((ModelBase)this, 24, 48);
        this.skirt6.func_78790_a(-8.0f, 0.0f, -2.0f, 8, 12, 4, 0.13f);
        this.skirt6.func_78793_a(4.0f, 9.45f, 0.0f);
        this.setRotation(this.skirt6, 0.0f, 0.0174533f, 0.0f);
        this.wing1 = new ModelRenderer((ModelBase)this, 114, 20);
        this.wing1.func_78793_a(0.0f, 1.0f, 2.0f);
        this.wing1.func_78790_a(0.0f, 1.6f, 0.0f, 7, 20, 0, 0.0f);
        this.setRotation(this.wing1, 0.08726646f, 0.0f, -0.08726646f);
        this.wing2 = new ModelRenderer((ModelBase)this, 114, 20);
        this.wing2.field_78809_i = true;
        this.wing2.func_78793_a(0.0f, 1.0f, 2.0f);
        this.wing2.func_78790_a(-7.0f, 1.6f, 0.0f, 7, 20, 0, 0.0f);
        this.setRotation(this.wing2, 0.08726646f, 0.0f, 0.08726646f);
        this.tail = new ModelRenderer((ModelBase)this, 48, 32);
        this.tail.func_78789_a(-1.0f, -1.0f, 0.0f, 2, 2, 2);
        this.tail.func_78793_a(0.0f, 10.0f, 2.0f);
        this.setRotation(this.tail, 0.0f, 0.0f, 0.0f);
        this.alal = new ModelRenderer((ModelBase)this, 90, 34);
        this.alal.field_78809_i = true;
        this.alal.func_78790_a(-8.0f, 0.0f, 1.0f, 7, 14, 0, 0.0f);
        this.alal.func_78793_a(0.0f, 0.0f, 1.0f);
        this.alal.func_78787_b(128, 64);
        this.setRotation(this.alal, 0.1309f, 0.0f, -1.5708f);
        this.alar = new ModelRenderer((ModelBase)this, 90, 34);
        this.alar.func_78790_a(1.0f, 0.0f, 0.0f, 7, 14, 0, 0.0f);
        this.alar.func_78793_a(0.0f, 0.0f, 2.0f);
        this.alar.func_78787_b(128, 64);
        this.setRotation(this.alar, 0.1309f, 0.0f, 1.5708f);
        this.field_78113_g = new ModelRenderer((ModelBase)this, 112, 48);
        this.field_78113_g.field_78809_i = true;
        this.field_78113_g.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 12, 4, par1);
        this.field_78113_g.func_78793_a(5.0f, 2.0f + par1, 0.0f);
        this.rshoulder1 = new ModelRenderer((ModelBase)this, 62, 34);
        this.rshoulder1.func_78789_a(-0.5f, -7.0f, -3.0f, 1, 8, 6);
        this.rshoulder1.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.rshoulder1.func_78787_b(128, 64);
        this.setRotation(this.rshoulder1, 0.0f, 0.0f, -0.2617994f);
        this.lshoulder1 = new ModelRenderer((ModelBase)this, 76, 34);
        this.lshoulder1.field_78809_i = true;
        this.lshoulder1.func_78789_a(-0.5f, -7.0f, -3.0f, 1, 8, 6);
        this.lshoulder1.func_78793_a(5.0f, 2.0f, 0.0f);
        this.lshoulder1.func_78787_b(128, 64);
        this.setRotation(this.lshoulder1, 0.0f, 0.0f, 0.2617994f);
        this.field_78124_i = new ModelRenderer((ModelBase)this, 96, 48);
        this.field_78124_i.field_78809_i = true;
        this.field_78124_i.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 12, 4, par1);
        this.field_78124_i.func_78793_a(1.9f, 12.0f + par1, 0.0f);
        this.field_78116_c.func_78792_a(this.blade);
        this.field_78116_c.func_78792_a(this.hornbase1);
        this.field_78116_c.func_78792_a(this.hornbase2);
        this.field_78116_c.func_78792_a(this.horn1);
        this.field_78116_c.func_78792_a(this.horn2);
        this.field_78116_c.func_78792_a(this.antenna1);
        this.field_78116_c.func_78792_a(this.antenna2);
        this.field_78116_c.func_78792_a(this.ball);
        this.field_78116_c.func_78792_a(this.star);
        this.field_78116_c.func_78792_a(this.jhorn1);
        this.field_78116_c.func_78792_a(this.jhorn2);
        this.field_78116_c.func_78792_a(this.jhorn3);
        this.field_78116_c.func_78792_a(this.jhorn4);
        this.field_78116_c.func_78792_a(this.hat1);
        this.field_78116_c.func_78792_a(this.c15);
        this.field_78116_c.func_78792_a(this.c151);
        this.field_78115_e.func_78792_a(this.wing1);
        this.field_78115_e.func_78792_a(this.wing2);
        this.field_78115_e.func_78792_a(this.cube2);
        this.field_78115_e.func_78792_a(this.cube3);
        this.field_78115_e.func_78792_a(this.cube4);
        this.field_78115_e.func_78792_a(this.skirt1);
        this.field_78115_e.func_78792_a(this.skirt2);
        this.field_78115_e.func_78792_a(this.skirt3);
        this.field_78115_e.func_78792_a(this.skirt4);
        this.field_78116_c.func_78792_a(this.c020);
        this.field_78116_c.func_78792_a(this.c019);
        this.field_78115_e.func_78792_a(this.cape1);
        this.field_78115_e.func_78792_a(this.alar);
        this.field_78115_e.func_78792_a(this.alal);
        this.field_78113_g.func_78792_a(this.leftarmshoulder1);
        this.field_78112_f.func_78792_a(this.rshoulder1);
        this.field_78113_g.func_78792_a(this.lshoulder1);
        GL11.glDisable((int)3042);
    }

    public void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void setRotationPub(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        float s3;
        float s2;
        super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
        float s = 0.1070796f;
        float d = -0.1070796f;
        if (this.cape1 != null) {
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) * 1.0f * par2;
                s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) * 1.2f * par2;
                this.cape1.field_78795_f = s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s);
            } else {
                this.cape1.field_78795_f = s;
            }
        }
        if (this.wing1 != null) {
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) * 1.0f * par2;
                s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) * 1.0f * par2;
                float f = s3 + s > s ? s3 + s : (this.wing1.field_78795_f = s2 + s > s ? s2 + s : s);
                this.wing1.field_78808_h = s3 - s < s ? s3 - s : (s2 - s < s ? s2 - s : s);
            } else {
                this.wing1.field_78795_f = s;
                this.wing1.field_78808_h = d;
            }
        }
        if (this.wing2 != null) {
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) * 1.0f * par2;
                s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) * 1.0f * par2;
                float f = s3 + s > s ? s3 + s : (this.wing2.field_78795_f = s2 + s > s ? s2 + s : s);
                this.wing2.field_78808_h = s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s);
            } else {
                this.wing2.field_78795_f = s;
                this.wing2.field_78808_h = s;
            }
        }
        if (this.cube4 != null) {
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) / 1.0f * par2;
                s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) / 1.0f * par2;
                this.cube4.field_78795_f = s3 - s < s ? s3 - s : (s2 - s < s ? s2 - s : s);
            } else {
                this.cube4.field_78795_f = s;
            }
        }
        if (this.cube5 != null) {
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) * 1.0f * par2;
                s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) * 2.3f * par2;
                this.cube5.field_78795_f = s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s);
            } else {
                this.cube5.field_78795_f = s;
            }
        }
        if (this.skirt1 != null) {
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) / 1.0f * par2;
                s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) / 1.0f * par2;
                this.skirt1.field_78795_f = s3 - s < s ? s3 - s : (s2 - s < s ? s2 - s : s);
            } else {
                this.skirt1.field_78795_f = s;
            }
        }
        if (this.skirt2 != null) {
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) * 1.0f * par2;
                s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) * 2.3f * par2;
                this.skirt2.field_78795_f = s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s);
            } else {
                this.skirt2.field_78795_f = s;
            }
        }
        if (this.skirt3 != null) {
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) / 2.6f * par2;
                s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) / 2.4f * par2;
                this.skirt3.field_78795_f = s3 - s < s ? s3 - s : (s2 - s < s ? s2 - s : s);
            } else {
                this.skirt3.field_78795_f = s;
            }
        }
        if (this.skirt4 != null) {
            if (y == 1) {
                s2 = MathHelper.func_76134_b((float)(par6 * 0.6662f)) * 0.4f * par2;
                s3 = MathHelper.func_76134_b((float)(par6 * 0.6662f + (float)Math.PI)) * 0.2f * par2;
                this.skirt4.field_78795_f = s3 + s > s ? s3 + s : (s2 + s > s ? s2 + s : s);
            } else {
                this.skirt4.field_78795_f = s;
            }
        }
    }
}

