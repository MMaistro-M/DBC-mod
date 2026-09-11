/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.client.model.part;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import noppes.npcs.client.model.ModelMPM;
import noppes.npcs.client.model.util.Model2DRenderer;
import noppes.npcs.client.model.util.ModelPartInterface;
import noppes.npcs.entity.data.ModelData;
import noppes.npcs.entity.data.ModelPartData;

public class ModelBeard
extends ModelPartInterface {
    private Model2DRenderer model;

    public ModelBeard(ModelMPM base) {
        super(base);
        this.model = new Model2DRenderer((ModelBase)base, 56.0f, 20.0f, 8, 12, 64.0f, 32.0f);
        this.model.func_78793_a(-3.99f, 11.9f, -4.0f);
        this.model.setScale(0.74f);
        this.func_78792_a(this.model);
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        this.field_78795_f = this.base.field_78116_c.field_78795_f > 0.0f ? -this.base.field_78116_c.field_78795_f : 0.0f;
    }

    @Override
    public void initData(ModelData data) {
        ModelPartData config = data.getPartData("beard");
        if (config == null) {
            this.field_78807_k = true;
            return;
        }
        this.color = config.color;
        this.field_78807_k = false;
        this.location = !config.playerTexture ? config.getResource() : null;
    }
}

