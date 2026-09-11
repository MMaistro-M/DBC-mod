/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.client.model.blocks;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAnvil
extends ModelBase {
    public final ModelRenderer Anvil;
    public final ModelRenderer Desk;
    public final ModelRenderer Bucket;
    private final ModelRenderer cube_r1;
    private final ModelRenderer cube_r2;
    public final ModelRenderer Lava;
    public final ModelRenderer Hammer;
    private final ModelRenderer Hammer_r1;
    public final ModelRenderer Mold;
    private final ModelRenderer cube_r3;
    private final ModelRenderer cube_r4;
    private final ModelRenderer cube_r5;
    private final ModelRenderer cube_r6;
    private final ModelRenderer cube_r7;
    private final ModelRenderer cube_r8;

    public ModelAnvil() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Anvil = new ModelRenderer((ModelBase)this);
        this.Anvil.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Anvil.field_78804_l.add(new ModelBox(this.Anvil, 0, 16, -6.0f, -4.0f, -8.0f, 12, 4, 12, 0.0f));
        this.Anvil.field_78804_l.add(new ModelBox(this.Anvil, 0, 32, -5.0f, -5.0f, -6.0f, 10, 1, 8, 0.0f));
        this.Anvil.field_78804_l.add(new ModelBox(this.Anvil, 0, 41, -4.0f, -10.0f, -4.0f, 8, 5, 4, 0.0f));
        this.Anvil.field_78804_l.add(new ModelBox(this.Anvil, 0, 0, -8.0f, -16.0f, -7.0f, 16, 6, 10, 0.0f));
        this.Desk = new ModelRenderer((ModelBase)this);
        this.Desk.func_78793_a(0.0f, 24.0f, 0.0f);
        this.Desk.field_78804_l.add(new ModelBox(this.Desk, 52, 0, 5.0f, -14.0f, 5.0f, 3, 14, 3, 0.0f));
        this.Desk.field_78804_l.add(new ModelBox(this.Desk, 52, 0, -8.0f, -14.0f, 5.0f, 3, 14, 3, 0.0f));
        this.Desk.field_78804_l.add(new ModelBox(this.Desk, 0, 50, -8.0f, -16.0f, 3.0f, 16, 2, 5, 0.0f));
        this.Bucket = new ModelRenderer((ModelBase)this);
        this.Bucket.func_78793_a(3.0f, 5.5f, 4.0f);
        this.setRotationAngle(this.Bucket, 0.0f, 0.3927f, 0.0f);
        this.Bucket.field_78804_l.add(new ModelBox(this.Bucket, 48, 17, -2.0f, 1.5f, -2.0f, 4, 1, 4, 0.0f));
        this.Bucket.field_78804_l.add(new ModelBox(this.Bucket, 54, 22, 1.5f, -2.5f, -2.0f, 1, 4, 4, 0.0f));
        this.Bucket.field_78804_l.add(new ModelBox(this.Bucket, 54, 22, -2.5f, -2.5f, -2.0f, 1, 4, 4, 0.0f));
        this.cube_r1 = new ModelRenderer((ModelBase)this);
        this.cube_r1.func_78793_a(0.0f, -0.5f, -2.0f);
        this.Bucket.func_78792_a(this.cube_r1);
        this.setRotationAngle(this.cube_r1, 0.0f, 1.5708f, 0.0f);
        this.cube_r1.field_78804_l.add(new ModelBox(this.cube_r1, 54, 22, -0.5f, -2.0f, -2.0f, 1, 4, 4, 0.0f));
        this.cube_r2 = new ModelRenderer((ModelBase)this);
        this.cube_r2.func_78793_a(0.0f, -0.5f, 2.0f);
        this.Bucket.func_78792_a(this.cube_r2);
        this.setRotationAngle(this.cube_r2, 0.0f, -1.5708f, 0.0f);
        this.cube_r2.field_78804_l.add(new ModelBox(this.cube_r2, 54, 22, -0.5f, -2.0f, -2.0f, 1, 4, 4, 0.0f));
        this.Lava = new ModelRenderer((ModelBase)this);
        this.Lava.func_78793_a(3.0f, 5.5f, 4.0f);
        this.setRotationAngle(this.Lava, 0.0f, 0.3927f, 0.0f);
        this.Lava.field_78804_l.add(new ModelBox(this.Lava, 48, 30, -2.0f, -1.5f, -2.0f, 4, 3, 4, 0.0f));
        this.Hammer = new ModelRenderer((ModelBase)this);
        this.Hammer.func_78793_a(4.9909f, 6.5642f, -4.0337f);
        this.setRotationAngle(this.Hammer, 0.1327f, 0.0189f, 0.1303f);
        this.Hammer_r1 = new ModelRenderer((ModelBase)this);
        this.Hammer_r1.func_78793_a(-0.2409f, 0.5358f, -0.4663f);
        this.Hammer.func_78792_a(this.Hammer_r1);
        this.setRotationAngle(this.Hammer_r1, 2.9697f, -0.7703f, -2.8972f);
        this.Hammer_r1.field_78804_l.add(new ModelBox(this.Hammer_r1, 56, 45, -2.8377f, -0.7659f, -1.0002f, 3, 1, 1, 0.0f));
        this.Hammer_r1.field_78804_l.add(new ModelBox(this.Hammer_r1, 48, 37, 0.1623f, -1.7659f, -3.0002f, 3, 3, 5, 0.0f));
        this.Mold = new ModelRenderer((ModelBase)this);
        this.Mold.func_78793_a(-3.2778f, 7.5556f, 1.5f);
        this.setRotationAngle(this.Mold, 0.0f, 0.7854f, 0.0f);
        this.Mold.field_78804_l.add(new ModelBox(this.Mold, -5, 59, -3.7222f, 0.3444f, -2.5f, 7, 0, 5, 0.0f));
        this.Mold.field_78804_l.add(new ModelBox(this.Mold, 14, 62, -3.7222f, -0.5556f, -2.5f, 7, 1, 1, 0.0f));
        this.Mold.field_78804_l.add(new ModelBox(this.Mold, 14, 62, -3.7222f, -0.5556f, 1.5f, 7, 1, 1, 0.0f));
        this.cube_r3 = new ModelRenderer((ModelBase)this);
        this.cube_r3.func_78793_a(0.7778f, -0.0556f, -1.0f);
        this.Mold.func_78792_a(this.cube_r3);
        this.setRotationAngle(this.cube_r3, 0.0f, 0.0f, 0.0f);
        this.cube_r3.field_78804_l.add(new ModelBox(this.cube_r3, 31, 62, 0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f));
        this.cube_r4 = new ModelRenderer((ModelBase)this);
        this.cube_r4.func_78793_a(-1.2222f, -0.0556f, -1.0f);
        this.Mold.func_78792_a(this.cube_r4);
        this.setRotationAngle(this.cube_r4, 0.0f, 0.0f, 0.0f);
        this.cube_r4.field_78804_l.add(new ModelBox(this.cube_r4, 25, 60, -1.5f, -0.5f, -0.5f, 3, 1, 1, 0.0f));
        this.cube_r5 = new ModelRenderer((ModelBase)this);
        this.cube_r5.func_78793_a(0.7778f, -0.0556f, 1.0f);
        this.Mold.func_78792_a(this.cube_r5);
        this.setRotationAngle(this.cube_r5, 0.0f, 0.0f, 0.0f);
        this.cube_r5.field_78804_l.add(new ModelBox(this.cube_r5, 31, 62, 0.5f, -0.5f, -0.5f, 1, 1, 1, 0.0f));
        this.cube_r6 = new ModelRenderer((ModelBase)this);
        this.cube_r6.func_78793_a(-1.2222f, -0.0556f, 1.0f);
        this.Mold.func_78792_a(this.cube_r6);
        this.setRotationAngle(this.cube_r6, 0.0f, 0.0f, 0.0f);
        this.cube_r6.field_78804_l.add(new ModelBox(this.cube_r6, 25, 60, -1.5f, -0.5f, -0.5f, 3, 1, 1, 0.0f));
        this.cube_r7 = new ModelRenderer((ModelBase)this);
        this.cube_r7.func_78793_a(2.7778f, -0.0556f, 0.0f);
        this.Mold.func_78792_a(this.cube_r7);
        this.setRotationAngle(this.cube_r7, 0.0f, -1.5708f, 0.0f);
        this.cube_r7.field_78804_l.add(new ModelBox(this.cube_r7, 15, 60, -1.5f, -0.5f, -0.5f, 3, 1, 1, 0.0f));
        this.cube_r8 = new ModelRenderer((ModelBase)this);
        this.cube_r8.func_78793_a(-3.2222f, -0.0556f, 0.0f);
        this.Mold.func_78792_a(this.cube_r8);
        this.setRotationAngle(this.cube_r8, 0.0f, -1.5708f, 0.0f);
        this.cube_r8.field_78804_l.add(new ModelBox(this.cube_r8, 15, 60, -1.5f, -0.5f, -0.5f, 3, 1, 1, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Anvil.func_78785_a(f5);
        this.Desk.func_78785_a(f5);
        this.Bucket.func_78785_a(f5);
        this.Hammer.func_78785_a(f5);
        this.Mold.func_78785_a(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

