/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  org.lwjgl.opengl.GL11
 */
package noppes.npcs.mixin;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import noppes.npcs.AnimationMixinFunctions;
import noppes.npcs.client.ClientEventHandler;
import noppes.npcs.constants.EnumAnimationPart;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={ModelRenderer.class})
public abstract class MixinModelRenderer {
    @Shadow
    public float field_78800_c;
    @Shadow
    public float field_78797_d;
    @Shadow
    public float field_78798_e;
    @Shadow
    public float field_78795_f;
    @Shadow
    public float field_78796_g;
    @Shadow
    public float field_78808_h;
    @Shadow
    public float field_82906_o;
    @Shadow
    public float field_82908_p;
    @Shadow
    public float field_82907_q;
    @Shadow
    public boolean field_78807_k;
    @Shadow
    public boolean field_78806_j;
    @Shadow
    public boolean field_78812_q;
    @Shadow
    private int field_78811_r;
    @Shadow
    public List field_78805_m;
    @Shadow
    public ModelBase field_78810_s;

    @Shadow
    abstract void func_78788_d(float var1);

    @Overwrite
    @SideOnly(value=Side.CLIENT)
    public void func_78785_a(float p_78785_1_) {
        if (ClientEventHandler.partNames.isEmpty()) {
            String[] headNames = new String[]{"field_78116_c", "bipedHead", "bipedHeadwear", "head", "Head", "headwear", "bipedHeadg", "bipedHeadt", "bipedHeadgh", "bipedHeadv", "bipedHeadb", "bipedHeadt2"};
            String[] bodyNames = new String[]{"field_78115_e", "bipedBody", "B1", "body", "Body", "UpperBody", "Body1", "BodyBase"};
            String[] larmNames = new String[]{"field_78113_g", "bipedLeftArm", "LA", "leftarm", "ArmL", "Arm1L", "ArmL1"};
            String[] rarmNames = new String[]{"field_78112_f", "bipedRightArm", "RA", "rightarm", "ArmR", "Arm1R", "ArmR1"};
            String[] llegNames = new String[]{"field_78124_i", "bipedLeftLeg", "LL", "leftleg", "LegL", "Leg1L", "LegL1"};
            String[] rlegNames = new String[]{"field_78123_h", "bipedRightLeg", "RL", "rightleg", "LegR", "Leg1R", "LegR1"};
            ClientEventHandler.partNames.put(EnumAnimationPart.HEAD, headNames);
            ClientEventHandler.partNames.put(EnumAnimationPart.BODY, bodyNames);
            ClientEventHandler.partNames.put(EnumAnimationPart.LEFT_ARM, larmNames);
            ClientEventHandler.partNames.put(EnumAnimationPart.RIGHT_ARM, rarmNames);
            ClientEventHandler.partNames.put(EnumAnimationPart.LEFT_LEG, llegNames);
            ClientEventHandler.partNames.put(EnumAnimationPart.RIGHT_LEG, rlegNames);
        }
        if (!this.field_78807_k && this.field_78806_j) {
            if (!this.field_78812_q) {
                this.func_78788_d(p_78785_1_);
            }
            float prevPointX = this.field_78800_c;
            float prevPointY = this.field_78797_d;
            float prevPointZ = this.field_78798_e;
            float prevAngleX = this.field_78795_f;
            float prevAngleY = this.field_78796_g;
            float prevAngleZ = this.field_78808_h;
            float prevOffsetX = this.field_82906_o;
            float prevOffsetY = this.field_82908_p;
            float prevOffsetZ = this.field_82907_q;
            boolean changedAngles = false;
            try {
                changedAngles = AnimationMixinFunctions.applyValues((ModelRenderer)this);
            }
            catch (Exception exception) {
                // empty catch block
            }
            GL11.glTranslatef((float)this.field_82906_o, (float)this.field_82908_p, (float)this.field_82907_q);
            if (this.field_78795_f == 0.0f && this.field_78796_g == 0.0f && this.field_78808_h == 0.0f) {
                if (this.field_78800_c == 0.0f && this.field_78797_d == 0.0f && this.field_78798_e == 0.0f) {
                    GL11.glCallList((int)this.field_78811_r);
                    if (this.field_78805_m != null) {
                        for (int i = 0; i < this.field_78805_m.size(); ++i) {
                            if (!(this.field_78805_m.get(i) instanceof ModelRenderer)) continue;
                            ((ModelRenderer)this.field_78805_m.get(i)).func_78785_a(p_78785_1_);
                        }
                    }
                } else {
                    GL11.glTranslatef((float)(this.field_78800_c * p_78785_1_), (float)(this.field_78797_d * p_78785_1_), (float)(this.field_78798_e * p_78785_1_));
                    GL11.glCallList((int)this.field_78811_r);
                    if (this.field_78805_m != null) {
                        for (int i = 0; i < this.field_78805_m.size(); ++i) {
                            if (!(this.field_78805_m.get(i) instanceof ModelRenderer)) continue;
                            ((ModelRenderer)this.field_78805_m.get(i)).func_78785_a(p_78785_1_);
                        }
                    }
                    GL11.glTranslatef((float)(-this.field_78800_c * p_78785_1_), (float)(-this.field_78797_d * p_78785_1_), (float)(-this.field_78798_e * p_78785_1_));
                }
            } else {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)(this.field_78800_c * p_78785_1_), (float)(this.field_78797_d * p_78785_1_), (float)(this.field_78798_e * p_78785_1_));
                if (this.field_78808_h != 0.0f) {
                    GL11.glRotatef((float)(this.field_78808_h * 57.295776f), (float)0.0f, (float)0.0f, (float)1.0f);
                }
                if (this.field_78796_g != 0.0f) {
                    GL11.glRotatef((float)(this.field_78796_g * 57.295776f), (float)0.0f, (float)1.0f, (float)0.0f);
                }
                if (this.field_78795_f != 0.0f) {
                    GL11.glRotatef((float)(this.field_78795_f * 57.295776f), (float)1.0f, (float)0.0f, (float)0.0f);
                }
                GL11.glCallList((int)this.field_78811_r);
                if (this.field_78805_m != null) {
                    for (int i = 0; i < this.field_78805_m.size(); ++i) {
                        if (!(this.field_78805_m.get(i) instanceof ModelRenderer)) continue;
                        ((ModelRenderer)this.field_78805_m.get(i)).func_78785_a(p_78785_1_);
                    }
                }
                GL11.glPopMatrix();
            }
            GL11.glTranslatef((float)(-this.field_82906_o), (float)(-this.field_82908_p), (float)(-this.field_82907_q));
            if (changedAngles) {
                this.field_78800_c = prevPointX;
                this.field_78797_d = prevPointY;
                this.field_78798_e = prevPointZ;
                this.field_78795_f = prevAngleX;
                this.field_78796_g = prevAngleY;
                this.field_78808_h = prevAngleZ;
                this.field_82906_o = prevOffsetX;
                this.field_82908_p = prevOffsetY;
                this.field_82907_q = prevOffsetZ;
            }
        }
    }
}

