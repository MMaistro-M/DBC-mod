/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package noppes.npcs.client.model.part;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import noppes.npcs.client.model.ModelMPM;
import noppes.npcs.client.model.util.Model2DRenderer;
import noppes.npcs.client.model.util.ModelPartInterface;
import noppes.npcs.entity.data.ModelData;
import noppes.npcs.entity.data.ModelPartData;

public class ModelEars
extends ModelPartInterface {
    private ModelRenderer ears;
    private ModelRenderer bunny;

    public ModelEars(ModelMPM par1ModelBase) {
        super(par1ModelBase);
        this.ears = new ModelRenderer((ModelBase)this.base);
        this.func_78792_a(this.ears);
        Model2DRenderer right = new Model2DRenderer((ModelBase)this.base, 56.0f, 0.0f, 8, 4, 64.0f, 32.0f);
        right.func_78793_a(-7.44f, -7.3f, -0.0f);
        right.setScale(0.234f, 0.234f);
        right.setThickness(1.16f);
        this.ears.func_78792_a((ModelRenderer)right);
        Model2DRenderer left = new Model2DRenderer((ModelBase)this.base, 56.0f, 0.0f, 8, 4, 64.0f, 32.0f);
        left.func_78793_a(7.44f, -7.3f, 1.15f);
        left.setScale(0.234f, 0.234f);
        this.setRotation(left, 0.0f, (float)Math.PI, 0.0f);
        left.setThickness(1.16f);
        this.ears.func_78792_a((ModelRenderer)left);
        Model2DRenderer right2 = new Model2DRenderer((ModelBase)this.base, 56.0f, 4.0f, 8, 4, 64.0f, 32.0f);
        right2.func_78793_a(-7.44f, -7.3f, 1.14f);
        right2.setScale(0.234f, 0.234f);
        right2.setThickness(1.16f);
        this.ears.func_78792_a((ModelRenderer)right2);
        Model2DRenderer left2 = new Model2DRenderer((ModelBase)this.base, 56.0f, 4.0f, 8, 4, 64.0f, 32.0f);
        left2.func_78793_a(7.44f, -7.3f, 2.31f);
        left2.setScale(0.234f, 0.234f);
        this.setRotation(left2, 0.0f, (float)Math.PI, 0.0f);
        left2.setThickness(1.16f);
        this.ears.func_78792_a((ModelRenderer)left2);
        this.bunny = new ModelRenderer((ModelBase)this.base);
        this.func_78792_a(this.bunny);
        ModelRenderer earleft = new ModelRenderer((ModelBase)this.base, 56, 0);
        earleft.field_78809_i = true;
        earleft.func_78789_a(-1.466667f, -4.0f, 0.0f, 3, 7, 1);
        earleft.func_78793_a(2.533333f, -11.0f, 0.0f);
        this.bunny.func_78792_a(earleft);
        ModelRenderer earright = new ModelRenderer((ModelBase)this.base, 56, 0);
        earright.func_78789_a(-1.5f, -4.0f, 0.0f, 3, 7, 1);
        earright.func_78793_a(-2.466667f, -11.0f, 0.0f);
        this.bunny.func_78792_a(earright);
    }

    @Override
    public void initData(ModelData data) {
        ModelPartData config = data.getPartData("ears");
        if (config == null) {
            this.field_78807_k = true;
            return;
        }
        this.field_78807_k = false;
        this.color = config.color;
        this.ears.field_78807_k = config.type != 0;
        this.bunny.field_78807_k = config.type != 1;
        this.location = !config.playerTexture ? config.getResource() : null;
    }
}

