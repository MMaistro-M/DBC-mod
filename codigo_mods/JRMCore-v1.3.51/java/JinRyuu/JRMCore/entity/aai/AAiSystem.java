/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package JinRyuu.JRMCore.entity.aai;

import JinRyuu.JRMCore.entity.aai.AAi;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.entity.Entity;

public class AAiSystem {
    public Entity entity;
    public ArrayList<AAi> aais;

    public AAiSystem(Entity entity, AAi ... aais) {
        this.entity = entity;
        this.aais = new ArrayList();
        this.addAAis(aais);
    }

    public void addAAis(AAi ... aais) {
        this.aais.addAll(Arrays.asList(aais));
        for (AAi aai : aais) {
            aai.aaiSystem = this;
        }
    }

    public void addAAi(AAi aai) {
        this.aais.add(aai);
        aai.aaiSystem = this;
    }

    public void update() {
        for (AAi aai : this.aais) {
            aai.update();
        }
    }
}

