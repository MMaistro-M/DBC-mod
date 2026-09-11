/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

import noppes.npcs.api.ITelegraph;
import noppes.npcs.api.ITelegraphInstance;
import noppes.npcs.api.IWorld;
import noppes.npcs.api.entity.IEntity;

public interface ITelegraphHandler {
    public ITelegraph createCircle(float var1);

    public ITelegraph createRing(float var1, float var2);

    public ITelegraph createLine(float var1, float var2);

    public ITelegraph createCone(float var1, float var2);

    public ITelegraph createSquare(float var1);

    public ITelegraph createPoint();

    public ITelegraph create(String var1);

    public ITelegraphInstance spawn(ITelegraph var1, IWorld var2, double var3, double var5, double var7);

    public ITelegraphInstance spawn(ITelegraph var1, IWorld var2, double var3, double var5, double var7, float var9);

    public ITelegraphInstance spawn(ITelegraph var1, IEntity var2);

    public ITelegraphInstance spawn(ITelegraph var1, IEntity var2, float var3);

    public ITelegraph get(String var1);

    public void save(String var1, ITelegraph var2);

    public void delete(String var1);

    public boolean has(String var1);

    public String[] getSavedNames();

    public void remove(String var1);

    public void remove(ITelegraphInstance var1);
}

