/*
 * Decompiled with CFR 0.152.
 */
package JinRyuu.JRMCore.entity.aai;

import JinRyuu.JRMCore.entity.aai.AAiSystem;
import java.util.Random;

public class AAi {
    public AAiSystem aaiSystem;

    public void update() {
    }

    public boolean checkChanceToUse(double rate) {
        if (rate >= 1.0) {
            return true;
        }
        if (rate <= 0.0) {
            return false;
        }
        return new Random().nextInt(100) < (int)(rate * 100.0);
    }
}

