/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler;

import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.handler.data.ICustomEffect;

public interface ICustomEffectHandler {
    public ICustomEffect createEffect(String var1);

    public ICustomEffect getEffect(String var1);

    public void deleteEffect(String var1);

    public boolean hasEffect(IPlayer var1, int var2);

    public boolean hasEffect(IPlayer var1, ICustomEffect var2);

    public int getEffectDuration(IPlayer var1, int var2);

    public int getEffectDuration(IPlayer var1, ICustomEffect var2);

    public void applyEffect(IPlayer var1, int var2, int var3, byte var4);

    public void applyEffect(IPlayer var1, ICustomEffect var2, int var3, byte var4);

    public void removeEffect(IPlayer var1, int var2);

    public void removeEffect(IPlayer var1, ICustomEffect var2);

    public void clearEffects(IPlayer var1);

    public void applyEffect(IPlayer var1, int var2, int var3, byte var4, int var5);

    public void applyEffect(IPlayer var1, ICustomEffect var2, int var3, byte var4, int var5);

    public void removeEffect(IPlayer var1, int var2, int var3);

    public void removeEffect(IPlayer var1, ICustomEffect var2, int var3);

    public void clearEffects(IPlayer var1, int var2);

    public int getEffectDuration(IPlayer var1, int var2, int var3);

    public int getEffectDuration(IPlayer var1, ICustomEffect var2, int var3);

    public ICustomEffect getEffect(String var1, int var2);

    public ICustomEffect getEffect(int var1, int var2);

    public ICustomEffect saveEffect(ICustomEffect var1);
}

