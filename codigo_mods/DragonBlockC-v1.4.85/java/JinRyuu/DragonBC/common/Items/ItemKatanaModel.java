/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package JinRyuu.DragonBC.common.Items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(value=Side.CLIENT)
public class ItemKatanaModel
extends ModelBase {
    ModelRenderer sw;
    ModelRenderer grip;
    ModelRenderer pummel;
    ModelRenderer guard;
    ModelRenderer edge;
    ModelRenderer kat;
    ModelRenderer kgrip;
    ModelRenderer kguard;
    ModelRenderer kedge;

    public ItemKatanaModel() {
        this(0.0f);
    }

    public ItemKatanaModel(float par1) {
        this(par1, 0.0f, 64, 32);
    }

    public ItemKatanaModel(float par1, float par2, int par3, int par4) {
        this.field_78090_t = par3;
        this.field_78089_u = par4;
        this.sw = new ModelRenderer((ModelBase)this, 0, 0);
        this.sw.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.sw.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.grip = new ModelRenderer((ModelBase)this, 0, 0);
        this.grip.func_78789_a(0.0f, 13.96667f, 0.0f, 1, 7, 1);
        this.grip.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.grip, 0.0f, 0.0f, 0.0f);
        this.guard = new ModelRenderer((ModelBase)this, 0, 26);
        this.guard.func_78789_a(-2.0f, 9.9f, -10.0f, 5, 1, 1);
        this.guard.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.guard, 0.7853982f, 0.0f, 0.0f);
        this.edge = new ModelRenderer((ModelBase)this, 4, 0);
        this.edge.func_78789_a(-1.0f, -10.1f, 0.5f, 3, 24, 0);
        this.edge.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.edge, 0.0f, 0.0f, 0.0f);
        this.pummel = new ModelRenderer((ModelBase)this, 0, 28);
        this.pummel.func_78789_a(-0.5f, 20.1f, -0.5f, 2, 2, 2);
        this.pummel.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.pummel, 0.0f, 0.0f, 0.0f);
        this.sw.func_78792_a(this.grip);
        this.sw.func_78792_a(this.pummel);
        this.sw.func_78792_a(this.guard);
        this.sw.func_78792_a(this.edge);
        this.kat = new ModelRenderer((ModelBase)this, 0, 0);
        this.kat.func_78790_a(-0.0f, -0.0f, -0.0f, 0, 0, 0, 0.02f);
        this.kat.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.kgrip = new ModelRenderer((ModelBase)this, 0, 0);
        this.kgrip.func_78789_a(0.0f, 13.96667f, 0.0f, 1, 7, 1);
        this.kgrip.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.kgrip, 0.0f, 0.0f, 0.0f);
        this.kguard = new ModelRenderer((ModelBase)this, 0, 29);
        this.kguard.func_78789_a(-1.0f, 13.9f, -1.0f, 3, 0, 3);
        this.kguard.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.kguard, 0.0f, 0.0f, 0.0f);
        this.kedge = new ModelRenderer((ModelBase)this, 4, 0);
        this.kedge.func_78789_a(-1.0f, -10.1f, 0.5f, 3, 24, 0);
        this.kedge.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.kedge, 0.0f, 0.0f, 0.0f);
        this.kat.func_78792_a(this.kgrip);
        this.kat.func_78792_a(this.kguard);
        this.kat.func_78792_a(this.kedge);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void render(String s) {
        float par1 = 0.0625f;
        if (s.contains("S") || s.contains("Z")) {
            this.sw.func_78785_a(par1);
        }
        if (s.contains("K")) {
            this.kat.func_78785_a(par1);
        }
    }
}

