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

public class ModelFin
extends ModelPartInterface {
    private Model2DRenderer model;

    public ModelFin(ModelMPM base) {
        super(base);
        this.model = new Model2DRenderer((ModelBase)base, 48.0f, 8.0f, 16, 24, 64.0f, 32.0f);
        this.model.func_78793_a(-0.5f, 12.0f, 10.0f);
        this.model.setScale(0.74f);
        this.model.field_78796_g = 1.5707964f;
        this.func_78792_a(this.model);
    }

    @Override
    public void initData(ModelData data) {
        ModelPartData config = data.getPartData("fin");
        if (config == null) {
            this.field_78807_k = true;
            return;
        }
        this.color = config.color;
        this.field_78807_k = false;
        this.location = !config.playerTexture ? config.getResource() : null;
    }
}

