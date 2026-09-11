/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package hedaox.ninjinentities.models;

import JinRyuu.DragonBC.common.Npcs.ModelNPCNormal;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelNPCNormalScale
extends ModelNPCNormal {
    private float scaleX = 1.0f;
    private float scaleY = 1.0f;
    private float scaleZ = 1.0f;

    public ModelNPCNormalScale(float _scaleX, float _scaleY, float _scaleZ) {
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
    }

    @Override
    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        GL11.glPushMatrix();
        GL11.glScalef((float)this.scaleX, (float)this.scaleY, (float)this.scaleZ);
        GL11.glTranslatef((float)0.0f, (float)((float)((double)-1.04f + 5.5 / (1.0 + Math.pow(this.scaleY / 0.45f, 1.88f)))), (float)0.0f);
        this.field_78116_c.func_78785_a(f5);
        this.field_78115_e.func_78785_a(f5);
        this.field_78112_f.func_78785_a(f5);
        this.field_78113_g.func_78785_a(f5);
        this.field_78123_h.func_78785_a(f5);
        this.field_78124_i.func_78785_a(f5);
        this.field_78114_d.func_78785_a(f5);
        GL11.glPopMatrix();
    }
}

