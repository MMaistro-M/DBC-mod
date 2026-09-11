/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.client.model.part;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import noppes.npcs.client.model.ModelMPM;
import noppes.npcs.client.model.part.ModelDuckBeak;
import noppes.npcs.client.model.util.ModelPartInterface;
import noppes.npcs.entity.data.ModelData;
import noppes.npcs.entity.data.ModelPartData;

public class ModelSnout
extends ModelPartInterface {
    private ModelRenderer small;
    private ModelRenderer medium;
    private ModelRenderer large;
    private ModelRenderer bunny;
    private ModelRenderer beak;

    public ModelSnout(ModelMPM base) {
        super(base);
        this.small = new ModelRenderer((ModelBase)base, 24, 0);
        this.small.func_78787_b(64, 32);
        this.small.func_78789_a(0.0f, 0.0f, 0.0f, 4, 3, 1);
        this.small.func_78793_a(-2.0f, -3.0f, -5.0f);
        this.func_78792_a(this.small);
        this.medium = new ModelRenderer((ModelBase)base, 24, 0);
        this.medium.func_78787_b(64, 32);
        this.medium.func_78789_a(0.0f, 0.0f, 0.0f, 4, 3, 2);
        this.medium.func_78793_a(-2.0f, -3.0f, -6.0f);
        this.func_78792_a(this.medium);
        this.large = new ModelRenderer((ModelBase)base, 24, 0);
        this.large.func_78787_b(64, 32);
        this.large.func_78789_a(0.0f, 0.0f, 0.0f, 4, 3, 3);
        this.large.func_78793_a(-2.0f, -3.0f, -7.0f);
        this.func_78792_a(this.large);
        this.bunny = new ModelRenderer((ModelBase)base, 24, 0);
        this.bunny.func_78787_b(64, 32);
        this.bunny.func_78789_a(1.0f, 1.0f, 0.0f, 4, 2, 1);
        this.bunny.func_78793_a(-3.0f, -4.0f, -5.0f);
        this.func_78792_a(this.bunny);
        ModelRenderer tooth = new ModelRenderer((ModelBase)base, 24, 3);
        tooth.func_78787_b(64, 32);
        tooth.func_78789_a(2.0f, 3.0f, 0.0f, 2, 1, 1);
        tooth.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bunny.func_78792_a(tooth);
        this.beak = new ModelDuckBeak(base);
        this.beak.func_78787_b(64, 32);
        this.beak.func_78793_a(0.0f, 0.0f, -4.0f);
        this.func_78792_a(this.beak);
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
    }

    @Override
    public void initData(ModelData data) {
        ModelPartData config = data.getPartData("snout");
        if (config == null) {
            this.field_78807_k = true;
            return;
        }
        this.color = config.color;
        this.field_78807_k = false;
        this.small.field_78807_k = config.type != 0;
        this.medium.field_78807_k = config.type != 1;
        this.large.field_78807_k = config.type != 2;
        this.bunny.field_78807_k = config.type != 3;
        this.beak.field_78807_k = config.type != 4;
        this.location = !config.playerTexture ? config.getResource() : null;
    }
}

