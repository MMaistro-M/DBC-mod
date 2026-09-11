/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.model.blocks.legacy;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelLegacyPedestal
extends ModelBase {
    ModelRenderer Main_Block = new ModelRenderer((ModelBase)this, 1, 0);
    ModelRenderer Front;

    public ModelLegacyPedestal() {
        this.Main_Block.func_78789_a(-7.0f, 0.0f, -8.0f, 14, 3, 16);
        this.Main_Block.func_78793_a(0.0f, 16.0f, 0.0f);
        this.Front = new ModelRenderer((ModelBase)this, 16, 8);
        this.Front.func_78789_a(-8.0f, 0.0f, -8.0f, 16, 5, 16);
        this.Front.func_78793_a(0.0f, 19.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        GL11.glPushMatrix();
        GL11.glScalef((float)1.0f, (float)1.0f, (float)0.5f);
        this.Main_Block.func_78785_a(f5);
        GL11.glScalef((float)1.0f, (float)1.0f, (float)1.25f);
        this.Front.func_78785_a(f5);
        GL11.glPopMatrix();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

