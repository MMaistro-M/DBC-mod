/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.TextureOffset
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.Tessellator
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JBRA;

import JinRyuu.JBRA.ModelBoxJBRA;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.TextureOffset;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

public class ModelRendererJBRA {
    public float textureWidth = 64.0f;
    public float textureHeight = 32.0f;
    private int textureOffsetX;
    private int textureOffsetY;
    public float rotationPointX;
    public float rotationPointY;
    public float rotationPointZ;
    public float rotateAngleX;
    public float rotateAngleY;
    public float rotateAngleZ;
    private boolean compiled;
    private int displayList;
    public boolean mirror;
    public boolean showModel = true;
    public boolean isHidden;
    public List cubeList = new ArrayList();
    public List childModels;
    public final String boxName;
    private ModelBase baseModel;
    public float offsetX;
    public float offsetY;
    public float offsetZ;
    private static final String __OBFID = "CL_00000874";
    public float lengthY = 1.0f;
    public float sizeXZ = 1.0f;

    public ModelRendererJBRA(ModelBase p_i1172_1_, String p_i1172_2_) {
        this.baseModel = p_i1172_1_;
        p_i1172_1_.field_78092_r.add(this);
        this.boxName = p_i1172_2_;
        this.setTextureSize(p_i1172_1_.field_78090_t, p_i1172_1_.field_78089_u);
    }

    public ModelRendererJBRA(ModelBase p_i1173_1_) {
        this(p_i1173_1_, null);
    }

    public ModelRendererJBRA(ModelBase p_i1174_1_, int p_i1174_2_, int p_i1174_3_) {
        this(p_i1174_1_);
        this.setTextureOffset(p_i1174_2_, p_i1174_3_);
    }

    public void addChild(ModelRendererJBRA p_78792_1_) {
        if (this.childModels == null) {
            this.childModels = new ArrayList();
        }
        this.childModels.add(p_78792_1_);
    }

    public ModelRendererJBRA setTextureOffset(int p_78784_1_, int p_78784_2_) {
        this.textureOffsetX = p_78784_1_;
        this.textureOffsetY = p_78784_2_;
        return this;
    }

    public ModelRendererJBRA addBox(String p_78786_1_, float p_78786_2_, float p_78786_3_, float p_78786_4_, int p_78786_5_, int p_78786_6_, int p_78786_7_) {
        p_78786_1_ = this.boxName + "." + p_78786_1_;
        TextureOffset textureoffset = this.baseModel.func_78084_a(p_78786_1_);
        this.setTextureOffset(textureoffset.field_78783_a, textureoffset.field_78782_b);
        this.cubeList.add(new ModelBoxJBRA(this, this.textureOffsetX, this.textureOffsetY, p_78786_2_, p_78786_3_, p_78786_4_, p_78786_5_, p_78786_6_, p_78786_7_, 0.0f).func_78244_a(p_78786_1_));
        return this;
    }

    public ModelRendererJBRA addBox(float p_78789_1_, float p_78789_2_, float p_78789_3_, int p_78789_4_, int p_78789_5_, int p_78789_6_) {
        this.cubeList.add(new ModelBoxJBRA(this, this.textureOffsetX, this.textureOffsetY, p_78789_1_, p_78789_2_, p_78789_3_, p_78789_4_, p_78789_5_, p_78789_6_, 0.0f));
        return this;
    }

    public void addBox(float p_78790_1_, float p_78790_2_, float p_78790_3_, int p_78790_4_, int p_78790_5_, int p_78790_6_, float p_78790_7_) {
        this.cubeList.add(new ModelBoxJBRA(this, this.textureOffsetX, this.textureOffsetY, p_78790_1_, p_78790_2_, p_78790_3_, p_78790_4_, p_78790_5_, p_78790_6_, p_78790_7_));
    }

    public void setRotationPoint(float p_78793_1_, float p_78793_2_, float p_78793_3_) {
        this.rotationPointX = p_78793_1_;
        this.rotationPointY = p_78793_2_;
        this.rotationPointZ = p_78793_3_;
    }

    @SideOnly(value=Side.CLIENT)
    public void render(float p_78785_1_) {
        if (!this.isHidden && this.showModel) {
            if (!this.compiled) {
                this.compileDisplayList(p_78785_1_);
            }
            GL11.glTranslatef((float)this.offsetX, (float)this.offsetY, (float)this.offsetZ);
            if (this.rotateAngleX == 0.0f && this.rotateAngleY == 0.0f && this.rotateAngleZ == 0.0f) {
                if (this.rotationPointX == 0.0f && this.rotationPointY == 0.0f && this.rotationPointZ == 0.0f) {
                    GL11.glCallList((int)this.displayList);
                    if (this.childModels != null) {
                        for (int i = 0; i < this.childModels.size(); ++i) {
                            ((ModelRendererJBRA)this.childModels.get(i)).render(p_78785_1_);
                        }
                    }
                } else {
                    GL11.glTranslatef((float)(this.rotationPointX * p_78785_1_), (float)(this.rotationPointY * p_78785_1_), (float)(this.rotationPointZ * p_78785_1_));
                    GL11.glScalef((float)this.sizeXZ, (float)this.lengthY, (float)this.sizeXZ);
                    GL11.glCallList((int)this.displayList);
                    GL11.glScalef((float)(1.0f / this.sizeXZ), (float)(1.0f / this.lengthY), (float)(1.0f / this.sizeXZ));
                    if (this.childModels != null) {
                        for (int i = 0; i < this.childModels.size(); ++i) {
                            ((ModelRendererJBRA)this.childModels.get(i)).render(p_78785_1_);
                        }
                    }
                    GL11.glTranslatef((float)(-this.rotationPointX * p_78785_1_), (float)(-this.rotationPointY * p_78785_1_), (float)(-this.rotationPointZ * p_78785_1_));
                }
            } else {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)(this.rotationPointX * p_78785_1_), (float)(this.rotationPointY * p_78785_1_), (float)(this.rotationPointZ * p_78785_1_));
                if (this.rotateAngleZ != 0.0f) {
                    GL11.glRotatef((float)(this.rotateAngleZ * 57.295776f), (float)0.0f, (float)0.0f, (float)1.0f);
                }
                if (this.rotateAngleY != 0.0f) {
                    GL11.glRotatef((float)(this.rotateAngleY * 57.295776f), (float)0.0f, (float)1.0f, (float)0.0f);
                }
                if (this.rotateAngleX != 0.0f) {
                    GL11.glRotatef((float)(this.rotateAngleX * 57.295776f), (float)1.0f, (float)0.0f, (float)0.0f);
                }
                GL11.glScalef((float)this.sizeXZ, (float)this.lengthY, (float)this.sizeXZ);
                GL11.glCallList((int)this.displayList);
                GL11.glScalef((float)(1.0f / this.sizeXZ), (float)(1.0f / this.lengthY), (float)(1.0f / this.sizeXZ));
                GL11.glTranslatef((float)0.0f, (float)(this.lengthY * 0.15f - 0.15f), (float)0.0f);
                if (this.childModels != null) {
                    for (int i = 0; i < this.childModels.size(); ++i) {
                        ((ModelRendererJBRA)this.childModels.get(i)).render(p_78785_1_);
                    }
                }
                GL11.glPopMatrix();
            }
            GL11.glTranslatef((float)(-this.offsetX), (float)(-this.offsetY), (float)(-this.offsetZ));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void renderWithRotation(float p_78791_1_) {
        if (!this.isHidden && this.showModel) {
            if (!this.compiled) {
                this.compileDisplayList(p_78791_1_);
            }
            GL11.glPushMatrix();
            GL11.glTranslatef((float)(this.rotationPointX * p_78791_1_), (float)(this.rotationPointY * p_78791_1_), (float)(this.rotationPointZ * p_78791_1_));
            if (this.rotateAngleY != 0.0f) {
                GL11.glRotatef((float)(this.rotateAngleY * 57.295776f), (float)0.0f, (float)1.0f, (float)0.0f);
            }
            if (this.rotateAngleX != 0.0f) {
                GL11.glRotatef((float)(this.rotateAngleX * 57.295776f), (float)1.0f, (float)0.0f, (float)0.0f);
            }
            if (this.rotateAngleZ != 0.0f) {
                GL11.glRotatef((float)(this.rotateAngleZ * 57.295776f), (float)0.0f, (float)0.0f, (float)1.0f);
            }
            GL11.glCallList((int)this.displayList);
            GL11.glPopMatrix();
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void postRender(float p_78794_1_) {
        if (!this.isHidden && this.showModel) {
            if (!this.compiled) {
                this.compileDisplayList(p_78794_1_);
            }
            if (this.rotateAngleX == 0.0f && this.rotateAngleY == 0.0f && this.rotateAngleZ == 0.0f) {
                if (this.rotationPointX != 0.0f || this.rotationPointY != 0.0f || this.rotationPointZ != 0.0f) {
                    GL11.glTranslatef((float)(this.rotationPointX * p_78794_1_), (float)(this.rotationPointY * p_78794_1_), (float)(this.rotationPointZ * p_78794_1_));
                }
            } else {
                GL11.glTranslatef((float)(this.rotationPointX * p_78794_1_), (float)(this.rotationPointY * p_78794_1_), (float)(this.rotationPointZ * p_78794_1_));
                if (this.rotateAngleZ != 0.0f) {
                    GL11.glRotatef((float)(this.rotateAngleZ * 57.295776f), (float)0.0f, (float)0.0f, (float)1.0f);
                }
                if (this.rotateAngleY != 0.0f) {
                    GL11.glRotatef((float)(this.rotateAngleY * 57.295776f), (float)0.0f, (float)1.0f, (float)0.0f);
                }
                if (this.rotateAngleX != 0.0f) {
                    GL11.glRotatef((float)(this.rotateAngleX * 57.295776f), (float)1.0f, (float)0.0f, (float)0.0f);
                }
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    private void compileDisplayList(float p_78788_1_) {
        this.displayList = GLAllocation.func_74526_a((int)1);
        GL11.glNewList((int)this.displayList, (int)4864);
        Tessellator tessellator = Tessellator.field_78398_a;
        for (int i = 0; i < this.cubeList.size(); ++i) {
            ((ModelBoxJBRA)this.cubeList.get(i)).render(tessellator, p_78788_1_);
        }
        GL11.glEndList();
        this.compiled = true;
    }

    public ModelRendererJBRA setTextureSize(int p_78787_1_, int p_78787_2_) {
        this.textureWidth = p_78787_1_;
        this.textureHeight = p_78787_2_;
        return this;
    }
}

