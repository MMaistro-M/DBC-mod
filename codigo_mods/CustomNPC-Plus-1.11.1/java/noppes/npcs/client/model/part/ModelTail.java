/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.client.model.part;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import noppes.npcs.client.ClientProxy;
import noppes.npcs.client.model.ModelMPM;
import noppes.npcs.client.model.part.tails.ModelCanineTail;
import noppes.npcs.client.model.part.tails.ModelDragonTail;
import noppes.npcs.client.model.part.tails.ModelFeatherTail;
import noppes.npcs.client.model.part.tails.ModelMonkeyTail;
import noppes.npcs.client.model.part.tails.ModelRodentTail;
import noppes.npcs.client.model.part.tails.ModelSquirrelTail;
import noppes.npcs.client.model.part.tails.ModelTailFin;
import noppes.npcs.client.model.util.ModelScaleRenderer;
import noppes.npcs.constants.EnumAnimation;
import noppes.npcs.controllers.data.TintData;
import noppes.npcs.entity.EntityCustomNpc;
import noppes.npcs.entity.data.ModelPartData;
import org.lwjgl.opengl.GL11;

public class ModelTail
extends ModelScaleRenderer {
    private EntityCustomNpc entity;
    private ModelMPM base;
    public ModelRenderer tail;
    public ModelRenderer dragon;
    public ModelRenderer squirrel;
    public ModelRenderer horse;
    public ModelRenderer fin;
    public ModelRenderer rodent;
    public ModelRenderer feather;
    public ModelCanineTail fox;
    public ModelMonkeyTail monkey;
    private int color = 0xFFFFFF;
    private ResourceLocation location = null;

    public ModelTail(ModelMPM base) {
        super((ModelBase)base);
        this.base = base;
        this.field_78797_d = 11.0f;
        this.tail = new ModelRenderer((ModelBase)base, 56, 21);
        this.tail.func_78787_b(64, 32);
        this.tail.func_78789_a(-1.0f, 0.0f, 0.0f, 2, 9, 2);
        this.tail.func_78793_a(0.0f, 0.0f, 1.0f);
        this.setRotation(this.tail, 0.8714253f, 0.0f, 0.0f);
        this.func_78792_a(this.tail);
        this.horse = new ModelRenderer((ModelBase)base);
        this.horse.func_78787_b(32, 32);
        this.horse.func_78793_a(0.0f, -1.0f, 1.0f);
        this.func_78792_a(this.horse);
        ModelRenderer tailBase = new ModelRenderer((ModelBase)base, 0, 26);
        tailBase.func_78787_b(32, 32);
        tailBase.func_78789_a(-1.0f, -1.0f, 0.0f, 2, 2, 3);
        this.setRotation(tailBase, -1.134464f, 0.0f, 0.0f);
        this.horse.func_78792_a(tailBase);
        ModelRenderer tailMiddle = new ModelRenderer((ModelBase)base, 0, 13);
        tailMiddle.func_78787_b(32, 32);
        tailMiddle.func_78789_a(-1.5f, -2.0f, 3.0f, 3, 4, 7);
        this.setRotation(tailMiddle, -1.134464f, 0.0f, 0.0f);
        this.horse.func_78792_a(tailMiddle);
        ModelRenderer tailTip = new ModelRenderer((ModelBase)base, 0, 0);
        tailTip.func_78787_b(32, 32);
        tailTip.func_78789_a(-1.5f, -4.5f, 9.0f, 3, 4, 7);
        this.setRotation(tailTip, -1.40215f, 0.0f, 0.0f);
        this.horse.func_78792_a(tailTip);
        this.horse.field_78795_f = 0.5f;
        this.dragon = new ModelDragonTail(base);
        this.func_78792_a(this.dragon);
        this.squirrel = new ModelSquirrelTail(base);
        this.func_78792_a(this.squirrel);
        this.fin = new ModelTailFin(base);
        this.func_78792_a(this.fin);
        this.rodent = new ModelRodentTail(base);
        this.func_78792_a(this.rodent);
        this.feather = new ModelFeatherTail(base);
        this.func_78792_a(this.feather);
        this.fox = new ModelCanineTail(base);
        this.func_78792_a(this.fox);
        this.monkey = new ModelMonkeyTail(base);
        this.func_78792_a(this.monkey);
    }

    public void setData(EntityCustomNpc entity) {
        this.entity = entity;
        this.initData(entity);
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        ModelPartData partTail;
        if (this.entity == null) {
            return;
        }
        float rotateAngleY = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 0.3f * par2;
        float rotateAngleX = MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        this.field_78797_d = 11.0f;
        if (this.entity.modelData.legParts.type == 2) {
            this.field_78797_d = 13.0f;
            this.field_78798_e = 14.0f * this.entity.modelData.modelScale.legs.scaleZ;
            if (this.base.isSleeping(entity) || this.entity.currentAnimation == EnumAnimation.CRAWLING) {
                this.field_78797_d = 12.0f + 16.0f * this.entity.modelData.modelScale.legs.scaleZ;
                this.field_78798_e = this.entity.modelData.modelScale.legs.scaleY;
                rotateAngleX = -0.7853982f;
            }
        } else if (this.entity.modelData.legParts.type == 3) {
            this.field_78797_d = 10.0f;
            this.field_78798_e = 19.0f * this.entity.modelData.modelScale.legs.scaleZ;
        } else {
            this.field_78798_e = -1.0f;
        }
        if ((partTail = this.entity.modelData.getPartData("tail")) != null) {
            if (partTail.type == 2) {
                rotateAngleX += 0.5f;
            }
            if (partTail.type == 0) {
                rotateAngleX += 0.87f;
            }
            if (partTail.type == 7) {
                this.field_78797_d -= 1.0f;
                this.fox.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
            }
            if (partTail.type == 8) {
                this.monkey.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
                if (partTail.pattern == 1) {
                    this.field_78798_e += 1.0f;
                    rotateAngleX = 0.0f;
                }
            }
        }
        this.field_78798_e += this.base.field_78123_h.field_78798_e + 0.5f;
        this.fin.field_78795_f = this.rodent.field_78795_f = rotateAngleX;
        this.horse.field_78795_f = this.rodent.field_78795_f;
        this.squirrel.field_78795_f = this.rodent.field_78795_f;
        this.dragon.field_78795_f = this.rodent.field_78795_f;
        this.feather.field_78795_f = this.rodent.field_78795_f;
        this.tail.field_78795_f = this.rodent.field_78795_f;
        this.fox.field_78795_f = this.rodent.field_78795_f;
        this.monkey.field_78795_f = this.rodent.field_78795_f;
        this.fin.field_78796_g = this.rodent.field_78796_g = rotateAngleY;
        this.horse.field_78796_g = this.rodent.field_78796_g;
        this.squirrel.field_78796_g = this.rodent.field_78796_g;
        this.dragon.field_78796_g = this.rodent.field_78796_g;
        this.feather.field_78796_g = this.rodent.field_78796_g;
        this.tail.field_78796_g = this.rodent.field_78796_g;
        this.fox.field_78796_g = this.rodent.field_78796_g;
        this.monkey.field_78796_g = this.rodent.field_78796_g;
    }

    public void setLivingAnimations(ModelPartData data, EntityLivingBase entity, float par2, float par3, float par4) {
    }

    public void initData(EntityCustomNpc data) {
        ModelPartData config = data.modelData.getPartData("tail");
        if (config == null) {
            this.field_78807_k = true;
            return;
        }
        this.color = config.color;
        this.field_78807_k = false;
        this.tail.field_78807_k = config.type != 0;
        this.dragon.field_78807_k = config.type != 1;
        this.horse.field_78807_k = config.type != 2;
        this.squirrel.field_78807_k = config.type != 3;
        this.fin.field_78807_k = config.type != 4;
        this.rodent.field_78807_k = config.type != 5;
        this.feather.field_78807_k = config.type != 6;
        this.fox.field_78807_k = config.type != 7;
        this.monkey.field_78807_k = config.type != 8;
        this.monkey.monkey.field_78807_k = config.pattern != 0;
        this.monkey.monkey_wrapped.field_78807_k = config.pattern != 1;
        this.monkey.monkey_large.field_78807_k = config.pattern != 2;
        this.location = !config.playerTexture ? config.getResource() : null;
    }

    @Override
    public void func_78785_a(float par1) {
        boolean showColor;
        if (this.field_78807_k) {
            return;
        }
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
}

