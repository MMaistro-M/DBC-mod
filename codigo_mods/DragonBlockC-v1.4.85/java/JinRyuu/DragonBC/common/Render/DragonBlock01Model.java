/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package JinRyuu.DragonBC.common.Render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class DragonBlock01Model
extends ModelBase {
    ModelRenderer starblock;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape18;
    ModelRenderer Shape19;
    ModelRenderer Shape110;
    ModelRenderer Shape111;
    ModelRenderer Shape112;
    ModelRenderer Shape113;
    ModelRenderer Shape114;
    ModelRenderer Shape115;
    ModelRenderer Shape116;
    ModelRenderer Shape117;
    ModelRenderer Shape118;
    ModelRenderer Shape119;
    ModelRenderer Shape120;
    ModelRenderer Shape121;
    ModelRenderer Shape122;
    ModelRenderer Shape123;
    ModelRenderer Shape124;
    ModelRenderer Shape125;
    ModelRenderer Shape126;
    ModelRenderer Shape127;
    ModelRenderer Shape128;
    ModelRenderer Shape129;
    ModelRenderer Shape130;
    ModelRenderer Shape131;

    public DragonBlock01Model() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.starblock = new ModelRenderer((ModelBase)this, 16, 0);
        this.starblock.func_78789_a(-1.0f, -4.0f, -1.0f, 2, 2, 2);
        this.starblock.func_78793_a(0.0f, 24.0f, 0.0f);
        this.starblock.func_78787_b(64, 32);
        this.starblock.field_78809_i = true;
        this.setRotation(this.starblock, 0.0f, 0.0f, 0.0f);
        this.Shape11 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape11.func_78789_a(3.0f, -5.0f, -2.0f, 0, 4, 4);
        this.Shape11.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape11.func_78787_b(64, 32);
        this.Shape11.field_78809_i = true;
        this.setRotation(this.Shape11, 0.0f, 0.0f, 0.0f);
        this.Shape12 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape12.func_78789_a(-2.0f, 0.0f, -2.0f, 4, 0, 4);
        this.Shape12.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape12.func_78787_b(64, 32);
        this.Shape12.field_78809_i = true;
        this.setRotation(this.Shape12, 0.0f, 0.0f, 0.0f);
        this.Shape13 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape13.func_78789_a(-2.0f, -5.0f, 3.0f, 4, 4, 0);
        this.Shape13.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape13.func_78787_b(64, 32);
        this.Shape13.field_78809_i = true;
        this.setRotation(this.Shape13, 0.0f, 0.0f, 0.0f);
        this.Shape14 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape14.func_78789_a(-3.0f, -5.0f, -2.0f, 0, 4, 4);
        this.Shape14.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape14.func_78787_b(64, 32);
        this.Shape14.field_78809_i = true;
        this.setRotation(this.Shape14, 0.0f, 0.0f, 0.0f);
        this.Shape15 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape15.func_78789_a(-2.0f, -1.0f, 2.0f, 4, 0, 1);
        this.Shape15.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape15.func_78787_b(64, 32);
        this.Shape15.field_78809_i = true;
        this.setRotation(this.Shape15, 0.0f, 0.0f, 0.0f);
        this.Shape16 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape16.func_78789_a(-2.0f, -1.0f, 2.0f, 4, 1, 0);
        this.Shape16.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape16.func_78787_b(64, 32);
        this.Shape16.field_78809_i = true;
        this.setRotation(this.Shape16, 0.0f, 0.0f, 0.0f);
        this.Shape17 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape17.func_78789_a(2.0f, -6.0f, -2.0f, 0, 1, 4);
        this.Shape17.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape17.func_78787_b(64, 32);
        this.Shape17.field_78809_i = true;
        this.setRotation(this.Shape17, 0.0f, 0.0f, 0.0f);
        this.Shape18 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape18.func_78789_a(2.0f, -5.0f, -3.0f, 0, 4, 1);
        this.Shape18.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape18.func_78787_b(64, 32);
        this.Shape18.field_78809_i = true;
        this.setRotation(this.Shape18, 0.0f, 0.0f, 0.0f);
        this.Shape19 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape19.func_78789_a(-2.0f, -1.0f, -2.0f, 0, 1, 4);
        this.Shape19.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape19.func_78787_b(64, 32);
        this.Shape19.field_78809_i = true;
        this.setRotation(this.Shape19, 0.0f, 0.0f, 0.0f);
        this.Shape110 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape110.func_78789_a(2.0f, -1.0f, -2.0f, 0, 1, 4);
        this.Shape110.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape110.func_78787_b(64, 32);
        this.Shape110.field_78809_i = true;
        this.setRotation(this.Shape110, 0.0f, 0.0f, 0.0f);
        this.Shape111 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape111.func_78789_a(-3.0f, -5.0f, 2.0f, 1, 4, 0);
        this.Shape111.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape111.func_78787_b(64, 32);
        this.Shape111.field_78809_i = true;
        this.setRotation(this.Shape111, 0.0f, 0.0f, 0.0f);
        this.Shape112 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape112.func_78789_a(-2.0f, -6.0f, -2.0f, 4, 1, 0);
        this.Shape112.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape112.func_78787_b(64, 32);
        this.Shape112.field_78809_i = true;
        this.setRotation(this.Shape112, 0.0f, 0.0f, 0.0f);
        this.Shape113 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape113.func_78789_a(-2.0f, -6.0f, 2.0f, 4, 1, 0);
        this.Shape113.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape113.func_78787_b(64, 32);
        this.Shape113.field_78809_i = true;
        this.setRotation(this.Shape113, 0.0f, 0.0f, 0.0f);
        this.Shape114 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape114.func_78789_a(-2.0f, -6.0f, 2.0f, 4, 1, 0);
        this.Shape114.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape114.func_78787_b(64, 32);
        this.Shape114.field_78809_i = true;
        this.setRotation(this.Shape114, 0.0f, 0.0f, 0.0f);
        this.Shape115 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape115.func_78789_a(-2.0f, -1.0f, -2.0f, 4, 1, 0);
        this.Shape115.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape115.func_78787_b(64, 32);
        this.Shape115.field_78809_i = true;
        this.setRotation(this.Shape115, 0.0f, 0.0f, 0.0f);
        this.Shape116 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape116.func_78789_a(-2.0f, -6.0f, -2.0f, 0, 1, 4);
        this.Shape116.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape116.func_78787_b(64, 32);
        this.Shape116.field_78809_i = true;
        this.setRotation(this.Shape116, 0.0f, 0.0f, 0.0f);
        this.Shape117 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape117.func_78789_a(-2.0f, -5.0f, -3.0f, 0, 4, 1);
        this.Shape117.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape117.func_78787_b(64, 32);
        this.Shape117.field_78809_i = true;
        this.setRotation(this.Shape117, 0.0f, 0.0f, 0.0f);
        this.Shape118 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape118.func_78789_a(-2.0f, -5.0f, 2.0f, 0, 4, 1);
        this.Shape118.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape118.func_78787_b(64, 32);
        this.Shape118.field_78809_i = true;
        this.setRotation(this.Shape118, 0.0f, 0.0f, 0.0f);
        this.Shape119 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape119.func_78789_a(2.0f, -5.0f, 2.0f, 0, 4, 1);
        this.Shape119.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape119.func_78787_b(64, 32);
        this.Shape119.field_78809_i = true;
        this.setRotation(this.Shape119, 0.0f, 0.0f, 0.0f);
        this.Shape120 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape120.func_78789_a(-2.0f, -5.0f, -3.0f, 4, 4, 0);
        this.Shape120.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape120.func_78787_b(64, 32);
        this.Shape120.field_78809_i = true;
        this.setRotation(this.Shape120, 0.0f, 0.0f, 0.0f);
        this.Shape121 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape121.func_78789_a(-3.0f, -5.0f, -2.0f, 1, 4, 0);
        this.Shape121.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape121.func_78787_b(64, 32);
        this.Shape121.field_78809_i = true;
        this.setRotation(this.Shape121, 0.0f, 0.0f, 0.0f);
        this.Shape122 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape122.func_78789_a(2.0f, -5.0f, -2.0f, 1, 4, 0);
        this.Shape122.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape122.func_78787_b(64, 32);
        this.Shape122.field_78809_i = true;
        this.setRotation(this.Shape122, 0.0f, 0.0f, 0.0f);
        this.Shape123 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape123.func_78789_a(2.0f, -5.0f, 2.0f, 1, 4, 0);
        this.Shape123.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape123.func_78787_b(64, 32);
        this.Shape123.field_78809_i = true;
        this.setRotation(this.Shape123, 0.0f, 0.0f, 0.0f);
        this.Shape124 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape124.func_78789_a(-2.0f, -6.0f, -2.0f, 4, 0, 4);
        this.Shape124.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape124.func_78787_b(64, 32);
        this.Shape124.field_78809_i = true;
        this.setRotation(this.Shape124, 0.0f, 0.0f, 0.0f);
        this.Shape125 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape125.func_78789_a(-3.0f, -5.0f, -2.0f, 1, 0, 4);
        this.Shape125.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape125.func_78787_b(64, 32);
        this.Shape125.field_78809_i = true;
        this.setRotation(this.Shape125, 0.0f, 0.0f, 0.0f);
        this.Shape126 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape126.func_78789_a(-3.0f, -1.0f, -2.0f, 1, 0, 4);
        this.Shape126.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape126.func_78787_b(64, 32);
        this.Shape126.field_78809_i = true;
        this.setRotation(this.Shape126, 0.0f, 0.0f, 0.0f);
        this.Shape127 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape127.func_78789_a(2.0f, -1.0f, -2.0f, 1, 0, 4);
        this.Shape127.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape127.func_78787_b(64, 32);
        this.Shape127.field_78809_i = true;
        this.setRotation(this.Shape127, 0.0f, 0.0f, 0.0f);
        this.Shape128 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape128.func_78789_a(2.0f, -5.0f, -2.0f, 1, 0, 4);
        this.Shape128.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape128.func_78787_b(64, 32);
        this.Shape128.field_78809_i = true;
        this.setRotation(this.Shape128, 0.0f, 0.0f, 0.0f);
        this.Shape129 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape129.func_78789_a(-2.0f, -5.0f, 2.0f, 4, 0, 1);
        this.Shape129.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape129.func_78787_b(64, 32);
        this.Shape129.field_78809_i = true;
        this.setRotation(this.Shape129, 0.0f, 0.0f, 0.0f);
        this.Shape130 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape130.func_78789_a(-2.0f, -1.0f, -3.0f, 4, 0, 1);
        this.Shape130.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape130.func_78787_b(64, 32);
        this.Shape130.field_78809_i = true;
        this.setRotation(this.Shape130, 0.0f, 0.0f, 0.0f);
        this.Shape131 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape131.func_78789_a(-2.0f, -5.0f, -3.0f, 4, 0, 1);
        this.Shape131.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Shape131.func_78787_b(64, 32);
        this.Shape131.field_78809_i = true;
        this.setRotation(this.Shape131, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.starblock.func_78785_a(f5);
        this.Shape11.func_78785_a(f5);
        this.Shape12.func_78785_a(f5);
        this.Shape13.func_78785_a(f5);
        this.Shape14.func_78785_a(f5);
        this.Shape15.func_78785_a(f5);
        this.Shape16.func_78785_a(f5);
        this.Shape17.func_78785_a(f5);
        this.Shape18.func_78785_a(f5);
        this.Shape19.func_78785_a(f5);
        this.Shape110.func_78785_a(f5);
        this.Shape111.func_78785_a(f5);
        this.Shape112.func_78785_a(f5);
        this.Shape113.func_78785_a(f5);
        this.Shape114.func_78785_a(f5);
        this.Shape115.func_78785_a(f5);
        this.Shape116.func_78785_a(f5);
        this.Shape117.func_78785_a(f5);
        this.Shape118.func_78785_a(f5);
        this.Shape119.func_78785_a(f5);
        this.Shape120.func_78785_a(f5);
        this.Shape121.func_78785_a(f5);
        this.Shape122.func_78785_a(f5);
        this.Shape123.func_78785_a(f5);
        this.Shape124.func_78785_a(f5);
        this.Shape125.func_78785_a(f5);
        this.Shape126.func_78785_a(f5);
        this.Shape127.func_78785_a(f5);
        this.Shape128.func_78785_a(f5);
        this.Shape129.func_78785_a(f5);
        this.Shape130.func_78785_a(f5);
        this.Shape131.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity) {
    }

    public void renderModel(float f) {
        this.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f);
    }

    public void render() {
        this.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
    }
}

