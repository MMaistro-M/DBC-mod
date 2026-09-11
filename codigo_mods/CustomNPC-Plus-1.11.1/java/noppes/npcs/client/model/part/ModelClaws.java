/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 */
package noppes.npcs.client.model.part;

import net.minecraft.client.model.ModelBase;
import noppes.npcs.client.model.ModelMPM;
import noppes.npcs.client.model.util.Model2DRenderer;
import noppes.npcs.client.model.util.ModelPartInterface;
import noppes.npcs.entity.data.ModelData;
import noppes.npcs.entity.data.ModelPartData;

public class ModelClaws
extends ModelPartInterface {
    private Model2DRenderer model;
    private boolean isRight = false;

    public ModelClaws(ModelMPM base, boolean isRight) {
        super(base);
        this.isRight = isRight;
        this.model = new Model2DRenderer((ModelBase)base, 0.0f, 16.0f, 4, 4, 64.0f, 32.0f);
        if (isRight) {
            this.model.func_78793_a(-2.0f, 14.0f, -2.0f);
        } else {
            this.model.func_78793_a(3.0f, 14.0f, -2.0f);
        }
        this.model.field_78796_g = -1.5707964f;
        this.model.setScale(0.25f);
        this.func_78792_a(this.model);
    }

    @Override
    public void initData(ModelData data) {
        ModelPartData config = data.getPartData("claws");
        if (config == null || this.isRight && config.type == 1 || !this.isRight && config.type == 2) {
            this.field_78807_k = true;
            return;
        }
        this.color = config.color;
        this.field_78807_k = false;
        this.location = !config.playerTexture ? config.getResource() : null;
    }
}

