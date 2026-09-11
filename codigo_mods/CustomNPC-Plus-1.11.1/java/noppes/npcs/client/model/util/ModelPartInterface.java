/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.model.util;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.ClientProxy;
import noppes.npcs.client.model.ModelMPM;
import noppes.npcs.controllers.data.TintData;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.data.ModelData;
import noppes.npcs.entity.data.ModelPartData;
import org.lwjgl.opengl.GL11;

public abstract class ModelPartInterface
extends ModelRenderer {
    public ModelData data;
    private EntityCustomNpc entity;
    public float scale = 1.0f;
    protected ResourceLocation location;
    public int color = 0xFFFFFF;
    public ModelMPM base;

    public ModelPartInterface(ModelMPM par1ModelBase) {
        super((ModelBase)par1ModelBase);
        this.base = par1ModelBase;
        this.func_78787_b(0, 0);
    }

    public void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
    }

    public void setLivingAnimations(ModelPartData data, EntityLivingBase entityliving, float f, float f1, float f2) {
    }

    public void setData(ModelData data, EntityCustomNpc entity) {
        this.data = data;
        this.entity = entity;
        this.initData(data);
    }

    public void func_78785_a(float par1) {
        boolean showColor;
        if (!this.base.isArmor) {
            if (this.location != null) {
                ClientProxy.bindTexture(this.location);
                this.base.currentlyPlayerTexture = false;
            } else if (!this.base.currentlyPlayerTexture) {
                ClientProxy.bindTexture(this.entity.textureLocation);
                this.base.currentlyPlayerTexture = true;
            }
        }
        TintData tintData = this.entity.display.tintData;
        boolean bl = !this.base.isArmor && tintData.processColor(this.entity.field_70737_aN > 0 || this.entity.field_70725_aQ > 0) ? true : (showColor = false);
        if (showColor) {
            float red = (float)(this.color >> 16 & 0xFF) / 255.0f;
            float green = (float)(this.color >> 8 & 0xFF) / 255.0f;
            float blue = (float)(this.color & 0xFF) / 255.0f;
            GL11.glColor4f((float)red, (float)green, (float)blue, (float)this.base.alpha);
        }
        super.func_78785_a(par1);
        if (showColor) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)this.base.alpha);
        }
    }

    public abstract void initData(ModelData var1);
}

