/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package hedaox.ninjinentities.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelBaba
extends ModelBase {
    private final ModelRenderer Tete;
    private final ModelRenderer Corps;
    private final ModelRenderer Dos;
    private float scaleX;
    private float scaleY = 1.0f;
    private float scaleZ = 1.0f;

    public ModelBaba(float _scaleX, float _scaleY, float _scaleZ) {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.scaleX = _scaleX;
        this.scaleY = _scaleY;
        this.scaleZ = _scaleZ;
        this.Tete = new ModelRenderer((ModelBase)this);
        this.Tete.func_78793_a(0.0f, 7.0f, 1.0f);
        this.Tete.field_78804_l.add(new ModelBox(this.Tete, 33, 33, -3.0f, -6.0f, -3.0f, 6, 6, 6, 0.0f));
        ModelRenderer couronne_Chapeau = new ModelRenderer((ModelBase)this);
        couronne_Chapeau.func_78793_a(11.0f, -7.0f, -1.0f);
        this.Tete.func_78792_a(couronne_Chapeau);
        couronne_Chapeau.field_78804_l.add(new ModelBox(couronne_Chapeau, 73, 1, -18.0f, 0.0f, -6.0f, 14, 1, 14, 0.0f));
        ModelRenderer chapeau_1 = new ModelRenderer((ModelBase)this);
        chapeau_1.func_78793_a(0.0f, -7.0f, -1.0f);
        this.Tete.func_78792_a(chapeau_1);
        chapeau_1.field_78804_l.add(new ModelBox(chapeau_1, 0, 22, -3.0f, -3.5f, -2.0f, 6, 4, 6, 0.0f));
        ModelRenderer chapeau_2 = new ModelRenderer((ModelBase)this);
        chapeau_2.func_78793_a(0.0f, -7.0f, -1.0f);
        this.Tete.func_78792_a(chapeau_2);
        chapeau_2.field_78804_l.add(new ModelBox(chapeau_2, 84, 24, -2.0f, -7.5f, -1.0f, 4, 4, 4, 0.0f));
        ModelRenderer chapeau_3 = new ModelRenderer((ModelBase)this);
        chapeau_3.func_78793_a(0.0f, -7.0f, -1.0f);
        this.Tete.func_78792_a(chapeau_3);
        chapeau_3.field_78804_l.add(new ModelBox(chapeau_3, 101, 28, -1.0f, -9.5f, 0.0f, 2, 2, 2, 0.0f));
        this.Corps = new ModelRenderer((ModelBase)this);
        this.Corps.func_78793_a(0.0f, 24.0f, 0.0f);
        ModelRenderer boule_Principale = new ModelRenderer((ModelBase)this);
        boule_Principale.func_78793_a(0.0f, -24.0f, 0.0f);
        this.Corps.func_78792_a(boule_Principale);
        boule_Principale.field_78804_l.add(new ModelBox(boule_Principale, 0, 44, -5.0f, 11.0f, -4.0f, 10, 10, 10, 0.0f));
        ModelRenderer boule_Cote_1 = new ModelRenderer((ModelBase)this);
        boule_Cote_1.func_78793_a(0.0f, -24.0f, 0.0f);
        this.Corps.func_78792_a(boule_Cote_1);
        boule_Cote_1.field_78804_l.add(new ModelBox(boule_Cote_1, 98, 48, 4.5f, 12.0f, -3.0f, 1, 8, 8, 0.0f));
        ModelRenderer boule_Cote_2 = new ModelRenderer((ModelBase)this);
        boule_Cote_2.func_78793_a(0.0f, -24.0f, 0.0f);
        this.Corps.func_78792_a(boule_Cote_2);
        boule_Cote_2.field_78804_l.add(new ModelBox(boule_Cote_2, 59, 48, -5.5f, 12.0f, -3.0f, 1, 8, 8, 0.0f));
        ModelRenderer boule_Cote_3 = new ModelRenderer((ModelBase)this);
        boule_Cote_3.func_78793_a(0.0f, -24.0f, 0.0f);
        this.Corps.func_78792_a(boule_Cote_3);
        boule_Cote_3.field_78804_l.add(new ModelBox(boule_Cote_3, 41, 55, -4.0f, 12.0f, -4.5f, 8, 8, 1, 0.0f));
        ModelRenderer boucle_Cote_4 = new ModelRenderer((ModelBase)this);
        boucle_Cote_4.func_78793_a(0.0f, -24.0f, 0.0f);
        this.Corps.func_78792_a(boucle_Cote_4);
        boucle_Cote_4.field_78804_l.add(new ModelBox(boucle_Cote_4, 78, 55, -4.0f, 12.0f, 5.5f, 8, 8, 1, 0.0f));
        ModelRenderer boule_Cote_5 = new ModelRenderer((ModelBase)this);
        boule_Cote_5.func_78793_a(0.0f, -24.0f, 0.0f);
        this.Corps.func_78792_a(boule_Cote_5);
        boule_Cote_5.field_78804_l.add(new ModelBox(boule_Cote_5, 71, 46, -4.0f, 20.5f, -3.0f, 8, 1, 8, 0.0f));
        ModelRenderer gros_cul = new ModelRenderer((ModelBase)this);
        gros_cul.func_78793_a(0.0f, -24.0f, 0.0f);
        this.Corps.func_78792_a(gros_cul);
        gros_cul.field_78804_l.add(new ModelBox(gros_cul, 0, 33, -4.0f, 8.5f, -3.0f, 8, 3, 8, 0.0f));
        ModelRenderer main = new ModelRenderer((ModelBase)this);
        main.func_78793_a(0.0f, -24.0f, 0.0f);
        this.Corps.func_78792_a(main);
        main.field_78804_l.add(new ModelBox(main, 0, 18, -1.0f, 6.5f, -4.0f, 2, 2, 1, 0.0f));
        ModelRenderer bras_Gauche = new ModelRenderer((ModelBase)this);
        bras_Gauche.func_78793_a(0.0f, -24.0f, 0.0f);
        this.Corps.func_78792_a(bras_Gauche);
        bras_Gauche.field_78804_l.add(new ModelBox(bras_Gauche, 58, 25, 3.0f, 6.5f, -2.0f, 2, 2, 6, 0.0f));
        ModelRenderer bras_Droit = new ModelRenderer((ModelBase)this);
        bras_Droit.func_78793_a(0.0f, -24.0f, 0.0f);
        this.Corps.func_78792_a(bras_Droit);
        bras_Droit.field_78804_l.add(new ModelBox(bras_Droit, 58, 33, -5.0f, 6.5f, -2.0f, 2, 2, 6, 0.0f));
        ModelRenderer bras_Devant = new ModelRenderer((ModelBase)this);
        bras_Devant.func_78793_a(0.0f, -24.0f, 0.0f);
        this.Corps.func_78792_a(bras_Devant);
        bras_Devant.field_78804_l.add(new ModelBox(bras_Devant, 58, 42, -5.0f, 6.5f, -3.0f, 10, 2, 1, 0.0f));
        ModelRenderer epaule_Droite = new ModelRenderer((ModelBase)this);
        epaule_Droite.func_78793_a(0.0f, -24.0f, 0.0f);
        this.Corps.func_78792_a(epaule_Droite);
        epaule_Droite.field_78804_l.add(new ModelBox(epaule_Droite, 75, 34, -5.0f, 4.5f, 2.0f, 2, 2, 2, 0.0f));
        ModelRenderer epaule_Droite_2 = new ModelRenderer((ModelBase)this);
        epaule_Droite_2.func_78793_a(0.0f, -24.0f, 0.0f);
        this.Corps.func_78792_a(epaule_Droite_2);
        epaule_Droite_2.field_78804_l.add(new ModelBox(epaule_Droite_2, 75, 39, -5.0f, 5.5f, 1.0f, 2, 1, 1, 0.0f));
        ModelRenderer epaule_Gauche = new ModelRenderer((ModelBase)this);
        epaule_Gauche.func_78793_a(0.0f, -24.0f, 0.0f);
        this.Corps.func_78792_a(epaule_Gauche);
        epaule_Gauche.field_78804_l.add(new ModelBox(epaule_Gauche, 75, 26, 3.0f, 4.5f, 2.0f, 2, 2, 2, 0.0f));
        ModelRenderer epaule_Gauche_2 = new ModelRenderer((ModelBase)this);
        epaule_Gauche_2.func_78793_a(0.0f, -24.0f, 0.0f);
        this.Corps.func_78792_a(epaule_Gauche_2);
        epaule_Gauche_2.field_78804_l.add(new ModelBox(epaule_Gauche_2, 75, 31, 3.0f, 5.5f, 1.0f, 2, 1, 1, 0.0f));
        this.Dos = new ModelRenderer((ModelBase)this);
        this.Dos.func_78793_a(0.0f, -24.0f, 0.0f);
        this.Corps.func_78792_a(this.Dos);
        this.Dos.field_78804_l.add(new ModelBox(this.Dos, 27, 24, -3.0f, 6.5f, -2.0f, 6, 2, 6, 0.0f));
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        GL11.glScalef((float)this.scaleX, (float)this.scaleY, (float)this.scaleZ);
        GL11.glTranslatef((float)0.0f, (float)((float)((double)-1.04f + 5.5 / (1.0 + Math.pow(this.scaleY / 0.45f, 1.88f)))), (float)0.0f);
        this.Tete.func_78785_a(f5);
        this.Corps.func_78785_a(f5);
        GL11.glPopMatrix();
        this.Tete.field_78796_g = f3 / 57.295776f;
        this.Tete.field_78795_f = f4 / 57.295776f;
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

