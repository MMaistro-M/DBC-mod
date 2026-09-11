/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.model.part;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import noppes.npcs.client.model.ModelMPM;
import noppes.npcs.client.model.util.ModelPartInterface;
import noppes.npcs.client.model.util.ModelPlaneRenderer;
import noppes.npcs.entity.data.ModelData;
import noppes.npcs.entity.data.ModelPartData;
import org.lwjgl.opengl.GL11;

public class ModelSkirt
extends ModelPartInterface {
    private ModelPlaneRenderer Shape1;

    public ModelSkirt(ModelMPM base) {
        super(base);
        float pi = 0.62831855f;
        this.field_78801_a = 64.0f;
        this.field_78799_b = 32.0f;
        this.Shape1 = new ModelPlaneRenderer((ModelBase)base, 58, 18);
        this.Shape1.func_78787_b(64, 32);
        this.Shape1.addSidePlane(0.0f, 0.0f, 0.0f, 9, 2);
        ModelPlaneRenderer part1 = new ModelPlaneRenderer((ModelBase)base, 58, 18);
        part1.func_78787_b(64, 32);
        part1.addSidePlane(2.0f, 0.0f, 0.0f, 9, 2);
        part1.field_78796_g = -1.5707964f;
        this.Shape1.func_78792_a(part1);
        this.Shape1.func_78793_a(2.4f, 8.8f, 0.0f);
        this.setRotation(this.Shape1, 0.3f, -0.2f, -0.2f);
    }

    @Override
    public void func_78785_a(float par1) {
        if (this.field_78807_k || !this.field_78806_j) {
            return;
        }
        GL11.glPushMatrix();
        GL11.glScalef((float)1.7f, (float)1.04f, (float)1.6f);
        super.func_78785_a(par1);
        GL11.glPopMatrix();
    }

    public void renderParts(float par1) {
        for (int i = 0; i < 10; ++i) {
            GL11.glRotatef((float)36.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            this.Shape1.func_78785_a(par1);
        }
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        this.setRotation(this.Shape1, 0.3f, -0.2f, -0.2f);
        this.Shape1.field_78795_f += this.base.field_78113_g.field_78795_f * 0.04f;
        this.Shape1.field_78808_h += this.base.field_78113_g.field_78795_f * 0.06f;
        this.Shape1.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.04f - 0.05f;
    }

    @Override
    public void initData(ModelData data) {
        ModelPartData config = data.getPartData("skirt");
        if (config == null) {
            this.field_78807_k = true;
            return;
        }
        this.color = config.color;
        this.field_78807_k = false;
        this.location = config.getResource();
    }
}

