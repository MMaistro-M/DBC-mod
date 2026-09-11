/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package JinRyuu.JRMCore.entity;

import JinRyuu.JRMCore.entity.RenderJRMC;
import JinRyuu.JRMCore.m.mEnergy;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderEnergyAttack<E extends Entity>
extends RenderJRMC {
    public final byte DBC = 0;
    public final byte NARUTO_C = 1;
    public final byte DBC_CHARGE = (byte)2;
    public final byte FIRE = 0;
    public final byte WIND = 1;
    public final byte LIGHTNING = (byte)2;
    public final byte EARTH = (byte)3;
    public final byte WATER = (byte)4;
    public final byte WAVE = 0;
    public final byte BLAST = 1;
    public final byte DISK = (byte)2;
    public final byte LASER = (byte)3;
    public final byte SPIRAL = (byte)4;
    public final byte LARGE = (byte)5;
    public final byte BARRAGE = (byte)6;
    public final byte SHIELD = (byte)7;
    public final byte EXPLOSION = (byte)8;
    public final String[] texture_type = new String[]{"wave", "blast", "disk", "laser", "spiral", "blast", "blast", "shield", "explosion"};
    public final String[] texture_element = new String[]{"fire", "wind", "lightning", "earth", "water"};
    public final String TEXTURE_HEAD = "head";
    public final String TEXTURE_HEAD2 = "head_connect";
    public final String TEXTURE_MIDDLE = "middle";
    public final String TEXTURE_NOISE = "noise";
    public final String TEXTURE_TAIL = "tail";
    public final String TEXTURE_TAIL2 = "tail_connect";
    public byte mode;
    public float rotationSpeed;
    public double modifierTranslation;
    public double endSize;
    public int detail;
    public int brightness;
    public float red;
    public float green;
    public float blue;
    public float red2;
    public float green2;
    public float blue2;
    public float alpha;
    public float scale_head;
    public float scale_head_connect;
    public float scale_middle;
    public float scale_noise;
    public float scale_tail;
    public float scale_tail_connect;
    public boolean render_head;
    public boolean render_head_connect;
    public boolean render_middle;
    public boolean render_noise;
    public boolean render_tail;
    public boolean render_tail_connect;
    public byte rendermode_tail;
    public final byte RENDER_NORMAL = 0;
    public final byte RENDER_DISK = 1;
    public final byte RENDER_SHIELD = (byte)2;
    public boolean rotate_head;
    public boolean rotate_head_connect;
    public boolean rotate_middle;
    public boolean rotate_noise;
    public boolean rotate_tail;
    public boolean rotate_tail_connect;
    public boolean scaling_head;
    public boolean scaling_tail;
    public float scale_head_more;
    public float scale_tail_more;
    public boolean head_follow;
    public boolean tail_follow;
    public boolean random_texture;
    public final String TEXTURE_FOLDER = "jinryuumodscore:textures/fx/";
    public String texture;
    public boolean transparent;

    public RenderEnergyAttack() {
        super((ModelBase)new mEnergy(), 0.5f);
    }

    @Override
    public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9) {
        this.field_76989_e = 0.0f;
        this.renderEnergy(entity, par2, par4, par6, par8, par9);
    }

    public void renderEnergy(E entity, double par2, double par4, double par6, float par8, float par9) {
    }

    public void updateEffect(E entity) {
    }

    public void updateEffect2(E entity) {
    }

    public void setVisuals(E entity, byte type, short effect, int color, int color2, float scale, byte mode) {
    }

    public ResourceLocation set_resource(String texture, int id) {
        return new ResourceLocation("jinryuumodscore:textures/fx/" + this.texture + texture + (id > 0 ? Integer.valueOf(id) : "") + ".png");
    }

    public void apply_detail_rotation(int id) {
        GL11.glRotatef((float)(id * 360 / this.detail), (float)0.0f, (float)1.0f, (float)0.0f);
    }

    protected float handleRotationFloat(Entity entity, float par2) {
        return (float)entity.field_70173_aa + par2;
    }

    public void setColors(int color, float alpha) {
        this.red = (float)(color >> 16 & 0xFF) / 255.0f;
        this.green = (float)(color >> 8 & 0xFF) / 255.0f;
        this.blue = (float)(color & 0xFF) / 255.0f;
        this.alpha = alpha;
    }

    public void setColors2(int color) {
        this.red2 = (float)(color >> 16 & 0xFF) / 255.0f;
        this.green2 = (float)(color >> 8 & 0xFF) / 255.0f;
        this.blue2 = (float)(color & 0xFF) / 255.0f;
    }

    public void glStart(E entity, double par2, double par4, double par6, float par9) {
        this.glStart();
        this.glRotate(entity, par2, par4, par6, par9);
    }

    public void glRotate(E entity, double par2, double par4, double par6, float par9) {
        GL11.glTranslatef((float)((float)par2), (float)((float)par4), (float)((float)par6));
        GL11.glRotatef((float)(((Entity)entity).field_70126_B + (((Entity)entity).field_70177_z - ((Entity)entity).field_70126_B) * par9 - 180.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(((Entity)entity).field_70127_C + (((Entity)entity).field_70125_A - ((Entity)entity).field_70127_C) * par9), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
    }

    public void glStart() {
        GL11.glPushMatrix();
        GL11.glEnable((int)2977);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
    }

    public void glEnd() {
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2977);
        GL11.glPopMatrix();
    }

    public void render_2d(E entity, double par2, double par4, double par6, float par8, float par9, float scale, double sx, double sy, double sz, int color, float rotation) {
    }

    public void render_3d(E entity, double par2, double par4, double par6, float par8, float par9, float scale, double sx, double sy, double sz, int color, float rotation) {
    }
}

