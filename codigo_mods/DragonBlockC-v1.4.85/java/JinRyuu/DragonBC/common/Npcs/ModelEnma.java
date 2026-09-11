/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.DragonBC.common.Npcs;

import JinRyuu.JRMCore.client.JGRenderHelper;
import JinRyuu.JRMCore.client.config.jrmc.JGConfigClientSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelEnma
extends ModelBase {
    public ModelRenderer Chair1;
    public ModelRenderer YemmaBody1;
    public ModelRenderer Desk1;
    public ModelRenderer Chair2;
    public ModelRenderer ChairBack1;
    public ModelRenderer ChairBack2;
    public ModelRenderer ChairBack3;
    public ModelRenderer ChairSideL1;
    public ModelRenderer ChairSideR1;
    public ModelRenderer ChairLeg1;
    public ModelRenderer ChairLeg2;
    public ModelRenderer ChairLeg3;
    public ModelRenderer ChairLeg4;
    public ModelRenderer ChairLegFoot1;
    public ModelRenderer ChairLegFoot2;
    public ModelRenderer ChairLegFoot3;
    public ModelRenderer ChairLegFoot4;
    public ModelRenderer ChairSideL2;
    public ModelRenderer ChairSideL3;
    public ModelRenderer ChairSideR2;
    public ModelRenderer ChairSideR3;
    public ModelRenderer YemmaBody2;
    public ModelRenderer YLeg1R;
    public ModelRenderer YLeg1L;
    public ModelRenderer YemmaBody3;
    public ModelRenderer YArm1L;
    public ModelRenderer YArm1R;
    public ModelRenderer Body4;
    public ModelRenderer YArm2L;
    public ModelRenderer YArm2R;
    public ModelRenderer Pen;
    public ModelRenderer Head;
    public ModelRenderer Beard1;
    public ModelRenderer Horn1L;
    public ModelRenderer Hair1;
    public ModelRenderer Horn1R;
    public ModelRenderer Beard2R;
    public ModelRenderer Beard3L;
    public ModelRenderer Horn2L;
    public ModelRenderer Hair2;
    public ModelRenderer Horn2R;
    public ModelRenderer YLeg2R;
    public ModelRenderer YLeg3R;
    public ModelRenderer YLeg2L;
    public ModelRenderer YLeg3L;
    public ModelRenderer Desk2;
    public ModelRenderer DeskLegFR;
    public ModelRenderer DeskLegFL;
    public ModelRenderer DeskLegBR;
    public ModelRenderer DeskLegBL;
    private float animationPen1 = 0.0f;
    private float animationPen2 = 0.0f;

    public ModelEnma() {
        this.field_78090_t = 256;
        this.field_78089_u = 128;
        this.ChairBack3 = new ModelRenderer((ModelBase)this, 227, 40);
        this.ChairBack3.func_78793_a(0.0f, -5.8f, 6.0f);
        this.ChairBack3.func_78790_a(-6.0f, -6.6f, -0.8f, 12, 7, 2, 0.0f);
        this.setRotateAngle(this.ChairBack3, (float)(-Math.PI) / 90, 0.0f, 0.0f);
        this.DeskLegBL = new ModelRenderer((ModelBase)this, 134, 103);
        this.DeskLegBL.field_78809_i = true;
        this.DeskLegBL.func_78793_a(16.3f, 1.5f, 2.8f);
        this.DeskLegBL.func_78790_a(-1.5f, 0.0f, -1.5f, 3, 15, 3, 0.0f);
        this.YLeg2L = new ModelRenderer((ModelBase)this, 32, 98);
        this.YLeg2L.field_78809_i = true;
        this.YLeg2L.func_78793_a(0.3f, -0.2f, -3.9f);
        this.YLeg2L.func_78790_a(-2.6f, 0.1f, -2.2f, 5, 8, 4, 0.0f);
        this.setRotateAngle(this.YLeg2L, -0.2443461f, 0.0f, 0.0f);
        this.YArm2L = new ModelRenderer((ModelBase)this, 62, 57);
        this.YArm2L.field_78809_i = true;
        this.YArm2L.func_78793_a(0.0f, 4.6f, 0.0f);
        this.YArm2L.func_78790_a(-0.2f, 0.1f, -1.2f, 3, 9, 3, 0.0f);
        this.setRotateAngle(this.YArm2L, -0.9669124f, 0.04886922f, 0.2687807f);
        this.Beard2R = new ModelRenderer((ModelBase)this, 60, 30);
        this.Beard2R.func_78793_a(0.0f, -0.6f, 0.0f);
        this.Beard2R.func_78790_a(-5.0f, 1.5f, -1.0f, 4, 2, 2, 0.0f);
        this.ChairLegFoot2 = new ModelRenderer((ModelBase)this, 241, 87);
        this.ChairLegFoot2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ChairLegFoot2.func_78790_a(-1.0f, 1.0f, 5.8f, 2, 2, 2, 0.0f);
        this.setRotateAngle(this.ChairLegFoot2, 0.108210415f, 0.0f, 0.0f);
        this.Body4 = new ModelRenderer((ModelBase)this, 8, 32);
        this.Body4.func_78793_a(0.0f, -4.0f, -1.1f);
        this.Body4.func_78790_a(-6.0f, -0.4f, -2.3f, 12, 2, 6, 0.0f);
        this.ChairSideL1 = new ModelRenderer((ModelBase)this, 206, 52);
        this.ChairSideL1.func_78793_a(7.1f, 1.4f, 1.6f);
        this.ChairSideL1.func_78790_a(-0.5f, -5.7f, -0.5f, 1, 6, 1, 0.0f);
        this.setRotateAngle(this.ChairSideL1, 0.0f, 0.0f, 0.13962634f);
        this.Beard1 = new ModelRenderer((ModelBase)this, 59, 23);
        this.Beard1.func_78793_a(0.0f, 0.2f, -2.3f);
        this.Beard1.func_78790_a(-4.5f, -2.2f, -0.9f, 9, 4, 2, 0.0f);
        this.setRotateAngle(this.Beard1, -0.68294734f, 0.0f, 0.0f);
        this.Hair1 = new ModelRenderer((ModelBase)this, 22, 6);
        this.Hair1.func_78793_a(0.0f, -2.5f, 3.2f);
        this.Hair1.func_78790_a(-4.5f, 0.0f, -1.8f, 9, 4, 3, 0.0f);
        this.setRotateAngle(this.Hair1, 0.54454273f, 0.0f, 0.0f);
        this.Chair1 = new ModelRenderer((ModelBase)this, 203, 51);
        this.Chair1.func_78793_a(0.0f, 14.5f, 14.0f);
        this.Chair1.func_78790_a(-7.0f, 0.0f, -7.1f, 14, 2, 12, 0.0f);
        this.ChairSideL2 = new ModelRenderer((ModelBase)this, 206, 52);
        this.ChairSideL2.func_78793_a(0.0f, 0.0f, -5.2f);
        this.ChairSideL2.func_78790_a(-0.5f, -5.7f, -0.5f, 1, 6, 1, 0.0f);
        this.Chair2 = new ModelRenderer((ModelBase)this, 240, 66);
        this.Chair2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Chair2.func_78790_a(-1.0f, 2.0f, -1.0f, 2, 4, 2, 0.0f);
        this.ChairLegFoot4 = new ModelRenderer((ModelBase)this, 241, 87);
        this.ChairLegFoot4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ChairLegFoot4.func_78790_a(-1.0f, 1.0f, 5.8f, 2, 2, 2, 0.0f);
        this.setRotateAngle(this.ChairLegFoot4, 0.108210415f, 0.0f, 0.0f);
        this.Desk2 = new ModelRenderer((ModelBase)this, 137, 73);
        this.Desk2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Desk2.func_78790_a(-18.5f, 1.9f, -19.0f, 36, 2, 23, 0.0f);
        this.YArm1R = new ModelRenderer((ModelBase)this, 62, 44);
        this.YArm1R.func_78793_a(-8.7f, -1.5f, 0.0f);
        this.YArm1R.func_78790_a(-1.2f, -1.7f, -2.0f, 4, 7, 4, 0.0f);
        this.setRotateAngle(this.YArm1R, -0.62831855f, (float)Math.PI / 180, 0.2687807f);
        this.YLeg3R = new ModelRenderer((ModelBase)this, 52, 101);
        this.YLeg3R.func_78793_a(0.3f, 8.0f, -0.5f);
        this.YLeg3R.func_78790_a(-2.6f, 0.1f, -4.0f, 4, 3, 6, 0.0f);
        this.setRotateAngle(this.YLeg3R, 0.04712389f, 0.0f, 0.0f);
        this.Horn2R = new ModelRenderer((ModelBase)this, 48, 3);
        this.Horn2R.func_78793_a(-1.9f, 0.3f, 0.0f);
        this.Horn2R.func_78790_a(-2.7f, -0.8f, -0.5f, 3, 2, 2, 0.0f);
        this.setRotateAngle(this.Horn2R, 0.0f, 0.0f, 1.1838568f);
        this.Horn1R = new ModelRenderer((ModelBase)this, 47, 9);
        this.Horn1R.func_78793_a(-3.7f, -5.7f, 0.0f);
        this.Horn1R.func_78790_a(-2.7f, -0.8f, -0.5f, 4, 2, 2, 0.0f);
        this.setRotateAngle(this.Horn1R, 0.0f, 0.0f, 0.4712389f);
        this.Beard3L = new ModelRenderer((ModelBase)this, 60, 30);
        this.Beard3L.field_78809_i = true;
        this.Beard3L.func_78793_a(0.0f, -0.6f, 0.0f);
        this.Beard3L.func_78790_a(1.1f, 1.5f, -1.0f, 4, 2, 2, 0.0f);
        this.ChairLeg3 = new ModelRenderer((ModelBase)this, 234, 74);
        this.ChairLeg3.func_78793_a(0.0f, 6.5f, 0.0f);
        this.ChairLeg3.func_78790_a(-1.0f, -0.7f, -0.5f, 2, 1, 8, 0.0f);
        this.setRotateAngle(this.ChairLeg3, -0.108210415f, 2.3675392f, 0.0f);
        this.ChairSideR1 = new ModelRenderer((ModelBase)this, 206, 52);
        this.ChairSideR1.func_78793_a(-7.1f, 1.4f, 1.6f);
        this.ChairSideR1.func_78790_a(-0.5f, -5.7f, -0.5f, 1, 6, 1, 0.0f);
        this.setRotateAngle(this.ChairSideR1, 0.0f, 0.0f, -0.13962634f);
        this.DeskLegFL = new ModelRenderer((ModelBase)this, 134, 103);
        this.DeskLegFL.field_78809_i = true;
        this.DeskLegFL.func_78793_a(16.3f, 1.5f, -17.8f);
        this.DeskLegFL.func_78790_a(-1.5f, 0.0f, -1.5f, 3, 15, 3, 0.0f);
        this.ChairSideR2 = new ModelRenderer((ModelBase)this, 206, 52);
        this.ChairSideR2.func_78793_a(0.0f, 0.0f, -5.2f);
        this.ChairSideR2.func_78790_a(-0.5f, -5.7f, -0.5f, 1, 6, 1, 0.0f);
        this.Horn2L = new ModelRenderer((ModelBase)this, 48, 3);
        this.Horn2L.field_78809_i = true;
        this.Horn2L.func_78793_a(2.4f, 0.5f, 0.0f);
        this.Horn2L.func_78790_a(-2.7f, -0.8f, -0.5f, 3, 2, 2, 0.0f);
        this.setRotateAngle(this.Horn2L, 0.0f, 0.0f, 1.9488347f);
        this.ChairLeg1 = new ModelRenderer((ModelBase)this, 234, 74);
        this.ChairLeg1.func_78793_a(0.0f, 6.5f, 0.0f);
        this.ChairLeg1.func_78790_a(-1.0f, -0.7f, -0.5f, 2, 1, 8, 0.0f);
        this.setRotateAngle(this.ChairLeg1, -0.108210415f, 1.2217305f, 0.0f);
        this.YArm1L = new ModelRenderer((ModelBase)this, 62, 44);
        this.YArm1L.field_78809_i = true;
        this.YArm1L.func_78793_a(7.2f, -1.5f, 0.0f);
        this.YArm1L.func_78790_a(-1.2f, -1.7f, -2.0f, 4, 7, 4, 0.0f);
        this.setRotateAngle(this.YArm1L, -0.62831855f, (float)Math.PI / 180, -0.2687807f);
        this.ChairLeg2 = new ModelRenderer((ModelBase)this, 234, 74);
        this.ChairLeg2.func_78793_a(0.0f, 6.5f, 0.0f);
        this.ChairLeg2.func_78790_a(-1.0f, -0.7f, -0.5f, 2, 1, 8, 0.0f);
        this.setRotateAngle(this.ChairLeg2, -0.108210415f, -1.2217305f, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 53, 8);
        this.Head.func_78793_a(0.0f, -0.6f, -0.1f);
        this.Head.func_78790_a(-4.0f, -7.5f, -3.0f, 8, 8, 7, 0.0f);
        this.YLeg3L = new ModelRenderer((ModelBase)this, 52, 101);
        this.YLeg3L.field_78809_i = true;
        this.YLeg3L.func_78793_a(0.3f, 8.0f, -0.5f);
        this.YLeg3L.func_78790_a(-2.6f, 0.1f, -4.0f, 4, 3, 6, 0.0f);
        this.setRotateAngle(this.YLeg3L, 0.04712389f, 0.0f, 0.0f);
        this.ChairSideL3 = new ModelRenderer((ModelBase)this, 199, 38);
        this.ChairSideL3.func_78793_a(-0.2f, -6.0f, 0.0f);
        this.ChairSideL3.func_78790_a(-0.5f, -0.5f, -2.2f, 2, 1, 9, 0.0f);
        this.setRotateAngle(this.ChairSideL3, 0.0f, 0.0f, -0.10471976f);
        this.Horn1L = new ModelRenderer((ModelBase)this, 47, 9);
        this.Horn1L.field_78809_i = true;
        this.Horn1L.func_78793_a(3.6f, -5.8f, 0.0f);
        this.Horn1L.func_78790_a(-1.2f, -0.8f, -0.5f, 4, 2, 2, 0.0f);
        this.setRotateAngle(this.Horn1L, 0.0f, 0.0f, -0.4712389f);
        this.Desk1 = new ModelRenderer((ModelBase)this, 123, 99);
        this.Desk1.func_78793_a(0.0f, 7.4f, 0.0f);
        this.Desk1.func_78790_a(-20.5f, -0.1f, -20.4f, 40, 2, 26, 0.0f);
        this.ChairBack2 = new ModelRenderer((ModelBase)this, 246, 50);
        this.ChairBack2.func_78793_a(3.0f, 1.1f, 5.1f);
        this.ChairBack2.func_78790_a(-1.0f, -6.6f, -0.3f, 2, 7, 1, 0.0f);
        this.setRotateAngle(this.ChairBack2, -0.13962634f, 0.0f, 0.0f);
        this.YLeg1R = new ModelRenderer((ModelBase)this, 5, 100);
        this.YLeg1R.func_78793_a(-3.8f, 0.0f, -4.6f);
        this.YLeg1R.func_78790_a(-2.6f, -1.9f, -5.8f, 6, 4, 6, 0.0f);
        this.setRotateAngle(this.YLeg1R, 0.20071286f, (float)Math.PI / 90, 0.0f);
        this.YemmaBody1 = new ModelRenderer((ModelBase)this, 7, 79);
        this.YemmaBody1.func_78793_a(0.0f, 12.5f, 12.2f);
        this.YemmaBody1.func_78790_a(-6.5f, -2.0f, -5.0f, 13, 4, 12, 0.0f);
        this.ChairSideR3 = new ModelRenderer((ModelBase)this, 199, 38);
        this.ChairSideR3.func_78793_a(-0.2f, -6.0f, 0.0f);
        this.ChairSideR3.func_78790_a(-1.0f, -0.5f, -2.2f, 2, 1, 9, 0.0f);
        this.setRotateAngle(this.ChairSideR3, 0.0f, 0.0f, 0.10471976f);
        this.YLeg1L = new ModelRenderer((ModelBase)this, 5, 100);
        this.YLeg1L.field_78809_i = true;
        this.YLeg1L.func_78793_a(3.4f, 0.0f, -4.6f);
        this.YLeg1L.func_78790_a(-3.0f, -1.9f, -5.8f, 6, 4, 6, 0.0f);
        this.setRotateAngle(this.YLeg1L, 0.20071286f, (float)(-Math.PI) / 90, 0.0f);
        this.ChairLegFoot3 = new ModelRenderer((ModelBase)this, 241, 87);
        this.ChairLegFoot3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ChairLegFoot3.func_78790_a(-1.0f, 1.0f, 5.8f, 2, 2, 2, 0.0f);
        this.setRotateAngle(this.ChairLegFoot3, 0.108210415f, 0.0f, 0.0f);
        this.YemmaBody3 = new ModelRenderer((ModelBase)this, 5, 41);
        this.YemmaBody3.func_78793_a(0.0f, -5.0f, -0.4f);
        this.YemmaBody3.func_78790_a(-6.5f, -3.2f, -5.6f, 13, 5, 11, 0.0f);
        this.YArm2R = new ModelRenderer((ModelBase)this, 62, 57);
        this.YArm2R.func_78793_a(0.0f, 4.6f, 0.0f);
        this.YArm2R.func_78790_a(-1.0f, 0.1f, -1.2f, 3, 9, 3, 0.0f);
        this.setRotateAngle(this.YArm2R, -0.9669124f, 0.04886922f, -0.2687807f);
        this.Pen = new ModelRenderer((ModelBase)this, 63, 69);
        this.Pen.func_78793_a(1.2f, 7.9f, -1.2f);
        this.Pen.func_78790_a(-0.5f, -0.5f, -2.3f, 1, 1, 5, 0.0f);
        this.setRotateAngle(this.Pen, 0.0f, 0.30543262f, 0.0f);
        this.YLeg2R = new ModelRenderer((ModelBase)this, 32, 98);
        this.YLeg2R.func_78793_a(0.3f, -0.2f, -3.9f);
        this.YLeg2R.func_78790_a(-2.6f, 0.1f, -2.2f, 5, 8, 4, 0.0f);
        this.setRotateAngle(this.YLeg2R, -0.2443461f, 0.0f, 0.0f);
        this.ChairBack1 = new ModelRenderer((ModelBase)this, 246, 50);
        this.ChairBack1.func_78793_a(-3.0f, 1.1f, 5.1f);
        this.ChairBack1.func_78790_a(-1.0f, -6.6f, -0.3f, 2, 7, 1, 0.0f);
        this.setRotateAngle(this.ChairBack1, -0.13962634f, 0.0f, 0.0f);
        this.DeskLegBR = new ModelRenderer((ModelBase)this, 134, 103);
        this.DeskLegBR.func_78793_a(-17.3f, 1.5f, 2.8f);
        this.DeskLegBR.func_78790_a(-1.5f, 0.0f, -1.5f, 3, 15, 3, 0.0f);
        this.ChairLegFoot1 = new ModelRenderer((ModelBase)this, 241, 87);
        this.ChairLegFoot1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ChairLegFoot1.func_78790_a(-1.0f, 1.0f, 5.8f, 2, 2, 2, 0.0f);
        this.setRotateAngle(this.ChairLegFoot1, 0.108210415f, 0.0f, 0.0f);
        this.YemmaBody2 = new ModelRenderer((ModelBase)this, 6, 59);
        this.YemmaBody2.func_78793_a(0.0f, -3.7f, 0.6f);
        this.YemmaBody2.func_78790_a(-7.0f, -3.2f, -7.1f, 14, 5, 13, 0.0f);
        this.ChairLeg4 = new ModelRenderer((ModelBase)this, 234, 74);
        this.ChairLeg4.func_78793_a(0.0f, 6.5f, 0.0f);
        this.ChairLeg4.func_78790_a(-1.0f, -0.7f, -0.5f, 2, 1, 8, 0.0f);
        this.setRotateAngle(this.ChairLeg4, -0.108210415f, -2.3675392f, 0.0f);
        this.Hair2 = new ModelRenderer((ModelBase)this, 3, 7);
        this.Hair2.func_78793_a(0.0f, 3.6f, 0.4f);
        this.Hair2.func_78790_a(-3.5f, 0.0f, -0.9f, 7, 3, 2, 0.0f);
        this.setRotateAngle(this.Hair2, -0.24609143f, 0.0f, 0.0f);
        this.DeskLegFR = new ModelRenderer((ModelBase)this, 134, 103);
        this.DeskLegFR.func_78793_a(-17.3f, 1.5f, -17.8f);
        this.DeskLegFR.func_78790_a(-1.5f, 0.0f, -1.5f, 3, 15, 3, 0.0f);
        this.Chair1.func_78792_a(this.ChairBack3);
        this.Desk2.func_78792_a(this.DeskLegBL);
        this.YLeg1L.func_78792_a(this.YLeg2L);
        this.YArm1L.func_78792_a(this.YArm2L);
        this.Beard1.func_78792_a(this.Beard2R);
        this.ChairLeg2.func_78792_a(this.ChairLegFoot2);
        this.YemmaBody3.func_78792_a(this.Body4);
        this.Chair1.func_78792_a(this.ChairSideL1);
        this.Head.func_78792_a(this.Beard1);
        this.Head.func_78792_a(this.Hair1);
        this.ChairSideL1.func_78792_a(this.ChairSideL2);
        this.Chair1.func_78792_a(this.Chair2);
        this.ChairLeg4.func_78792_a(this.ChairLegFoot4);
        this.Desk1.func_78792_a(this.Desk2);
        this.YemmaBody3.func_78792_a(this.YArm1R);
        this.YLeg2R.func_78792_a(this.YLeg3R);
        this.Horn1R.func_78792_a(this.Horn2R);
        this.Head.func_78792_a(this.Horn1R);
        this.Beard1.func_78792_a(this.Beard3L);
        this.Chair2.func_78792_a(this.ChairLeg3);
        this.Chair1.func_78792_a(this.ChairSideR1);
        this.Desk2.func_78792_a(this.DeskLegFL);
        this.ChairSideR1.func_78792_a(this.ChairSideR2);
        this.Horn1L.func_78792_a(this.Horn2L);
        this.Chair2.func_78792_a(this.ChairLeg1);
        this.YemmaBody3.func_78792_a(this.YArm1L);
        this.Chair2.func_78792_a(this.ChairLeg2);
        this.Body4.func_78792_a(this.Head);
        this.YLeg2L.func_78792_a(this.YLeg3L);
        this.ChairSideL2.func_78792_a(this.ChairSideL3);
        this.Head.func_78792_a(this.Horn1L);
        this.Chair1.func_78792_a(this.ChairBack2);
        this.YemmaBody1.func_78792_a(this.YLeg1R);
        this.ChairSideR2.func_78792_a(this.ChairSideR3);
        this.YemmaBody1.func_78792_a(this.YLeg1L);
        this.ChairLeg3.func_78792_a(this.ChairLegFoot3);
        this.YemmaBody2.func_78792_a(this.YemmaBody3);
        this.YArm1R.func_78792_a(this.YArm2R);
        this.YArm2R.func_78792_a(this.Pen);
        this.YLeg1R.func_78792_a(this.YLeg2R);
        this.Chair1.func_78792_a(this.ChairBack1);
        this.Desk2.func_78792_a(this.DeskLegBR);
        this.ChairLeg1.func_78792_a(this.ChairLegFoot1);
        this.YemmaBody1.func_78792_a(this.YemmaBody2);
        this.Chair2.func_78792_a(this.ChairLeg4);
        this.Hair1.func_78792_a(this.Hair2);
        this.Desk2.func_78792_a(this.DeskLegFR);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        float n4 = f3;
        float n5 = f4;
        float r2 = 180.0f;
        float headY = n4 / (r2 / (float)Math.PI);
        float headX = n5 / (r2 / (float)Math.PI) * 0.3f;
        int tick = entity.field_70173_aa;
        int animation = tick / 100;
        int animationTick = tick - animation * 100;
        if (!Minecraft.func_71410_x().func_147113_T()) {
            if (animationTick < 50) {
                this.animationPen1 += (-0.0f - MathHelper.func_76134_b((float)((float)tick * 1.0f)) * 1.2f) * 0.02f;
                this.animationPen2 += (-0.0f - MathHelper.func_76134_b((float)((float)tick * 0.4f)) * 1.2f) * 0.02f;
            } else {
                if (this.animationPen1 != 0.0f) {
                    this.animationPen1 += -(this.animationPen1 / 10.0f);
                }
                if (this.animationPen2 != 0.0f) {
                    this.animationPen2 += -(this.animationPen2 / 10.0f);
                }
            }
            this.Pen.field_78795_f = this.animationPen1;
            this.YArm2R.field_78796_g = 0.04886922f + -this.animationPen2 * 0.1f;
            this.YArm2R.field_78808_h = -0.2687807f + -this.animationPen2 * 0.1f;
        }
        if (animationTick < 50) {
            this.Head.field_78795_f = 0.2f;
            this.Head.field_78796_g = 0.0f;
        } else {
            this.Head.field_78795_f = headX;
            this.Head.field_78796_g = headY;
        }
        this.Beard1.field_78795_f = this.Head.field_78795_f > 0.0f ? -0.68294734f - this.Head.field_78795_f * 3.0f : -0.68294734f;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-1.0f);
        float F = entity.field_70131_O / 2.0f;
        JGRenderHelper.modelScalePositionHelper(F);
        this.Chair1.func_78785_a(f5);
        this.YemmaBody1.func_78785_a(f5);
        if (JGConfigClientSettings.CLIENT_DA22) {
            this.Desk1.func_78785_a(f5);
        }
        GL11.glPopMatrix();
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

