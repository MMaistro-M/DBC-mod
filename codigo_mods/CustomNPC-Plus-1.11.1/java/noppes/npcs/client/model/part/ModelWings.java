/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package noppes.npcs.client.model.part;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import noppes.npcs.client.model.ModelMPM;
import noppes.npcs.client.model.util.Model2DRenderer;
import noppes.npcs.client.model.util.ModelPartInterface;
import noppes.npcs.entity.data.ModelData;
import noppes.npcs.entity.data.ModelPartData;

public class ModelWings
extends ModelPartInterface {
    private Model2DRenderer lWing;
    private Model2DRenderer rWing;

    public ModelWings(ModelMPM base) {
        super(base);
        this.lWing = new Model2DRenderer((ModelBase)base, 48.0f, 32.0f, 16, 32, 64.0f, 32.0f);
        this.lWing.field_78809_i = true;
        this.lWing.func_78793_a(2.0f, 4.0f, 2.0f);
        this.lWing.setRotationOffset(-16.0f, -12.0f);
        this.setRotation(this.lWing, 0.7141593f, -0.5235988f, -0.5090659f);
        this.func_78792_a(this.lWing);
        this.rWing = new Model2DRenderer((ModelBase)base, 48.0f, 32.0f, 16, 32, 64.0f, 32.0f);
        this.rWing.func_78793_a(-2.0f, 4.0f, 2.0f);
        this.rWing.setRotationOffset(-16.0f, -12.0f);
        this.setRotation(this.rWing, 0.7141593f, 0.5235988f, 0.5090659f);
        this.func_78792_a(this.rWing);
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        this.rWing.field_78795_f = 0.7141593f;
        this.rWing.field_78808_h = 0.5090659f;
        this.lWing.field_78795_f = 0.7141593f;
        this.lWing.field_78808_h = -0.5090659f;
        float motion = Math.abs(MathHelper.func_76126_a((float)(par1 * 0.033f + (float)Math.PI)) * 0.4f) * par2;
        if (!entity.field_70170_p.field_72995_K && !entity.field_70122_E || (double)motion > 0.1) {
            float speed = 0.55f + 0.5f * motion;
            float y = MathHelper.func_76126_a((float)(par3 * 0.67f));
            this.rWing.field_78808_h += y * 0.5f * speed;
            this.rWing.field_78795_f += y * 0.5f * speed;
            this.lWing.field_78808_h -= y * 0.5f * speed;
            this.lWing.field_78795_f += y * 0.5f * speed;
        } else {
            this.lWing.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.rWing.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.lWing.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
            this.rWing.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        }
    }

    @Override
    public void initData(ModelData data) {
        ModelPartData config = data.getPartData("wings");
        if (config == null) {
            this.field_78807_k = true;
            return;
        }
        this.color = config.color;
        this.field_78807_k = false;
        this.location = !config.playerTexture ? config.getResource() : null;
    }
}

