/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.client.model.blocks.banner;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBannerFloorFlag
extends ModelBase {
    public final ModelRenderer BannerFlag;

    public ModelBannerFloorFlag() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.BannerFlag = new ModelRenderer((ModelBase)this);
        this.BannerFlag.func_78793_a(0.0f, -8.0f, 5.0f);
        this.BannerFlag.field_78804_l.add(new ModelBox(this.BannerFlag, 2, 0, -7.0f, 0.4f, -6.05f, 14, 29, 0, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.BannerFlag.func_78785_a(f5);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

