/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.entity.data;

import noppes.npcs.api.entity.data.IModelRotatePart;

public interface IModelRotate {
    public boolean whileStanding();

    public void whileStanding(boolean var1);

    public boolean whileAttacking();

    public void whileAttacking(boolean var1);

    public boolean whileMoving();

    public void whileMoving(boolean var1);

    public IModelRotatePart getPart(int var1);
}

