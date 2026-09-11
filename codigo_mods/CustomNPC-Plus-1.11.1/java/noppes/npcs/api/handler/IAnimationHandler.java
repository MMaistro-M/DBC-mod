/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

import noppes.npcs.api.handler.data.IAnimation;

public interface IAnimationHandler {
    public IAnimation saveAnimation(IAnimation var1);

    public void delete(String var1);

    public void delete(int var1);

    public boolean has(String var1);

    public IAnimation get(String var1);

    public IAnimation get(int var1);

    public IAnimation[] getAnimations();

    public IAnimation[] getBuiltInAnimations();

    public IAnimation[] getAllAnimations();

    public boolean isBuiltIn(String var1);

    public String[] getBuiltInAnimationNames();
}

