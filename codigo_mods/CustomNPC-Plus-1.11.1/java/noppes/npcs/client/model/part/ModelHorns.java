/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package noppes.npcs.client.model.part;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import noppes.npcs.client.model.ModelMPM;
import noppes.npcs.client.model.part.horns.ModelAntennasBack;
import noppes.npcs.client.model.part.horns.ModelAntennasFront;
import noppes.npcs.client.model.part.horns.ModelAntlerHorns;
import noppes.npcs.client.model.part.horns.ModelBullHorns;
import noppes.npcs.client.model.util.ModelPartInterface;
import noppes.npcs.entity.data.ModelData;
import noppes.npcs.entity.data.ModelPartData;

public class ModelHorns
extends ModelPartInterface {
    private ModelRenderer bull;
    private ModelRenderer antlers;
    private ModelRenderer antennasBack;
    private ModelRenderer antennasFront;

    public ModelHorns(ModelMPM base) {
        super(base);
        this.bull = new ModelBullHorns(base);
        this.func_78792_a(this.bull);
        this.antlers = new ModelAntlerHorns(base);
        this.func_78792_a(this.antlers);
        this.antennasBack = new ModelAntennasBack(base);
        this.func_78792_a(this.antennasBack);
        this.antennasFront = new ModelAntennasFront(base);
        this.func_78792_a(this.antennasFront);
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
    }

    @Override
    public void initData(ModelData data) {
        ModelPartData config = data.getPartData("horns");
        if (config == null) {
            this.field_78807_k = true;
            return;
        }
        this.color = config.color;
        this.field_78807_k = false;
        this.bull.field_78807_k = config.type != 0;
        this.antlers.field_78807_k = config.type != 1;
        this.antennasBack.field_78807_k = config.type != 2;
        this.antennasFront.field_78807_k = config.type != 3;
        this.location = !config.playerTexture ? config.getResource() : null;
    }
}

