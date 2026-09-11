/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.entity.data;

public interface ITintData {
    public boolean isTintEnabled();

    public void setTintEnabled(boolean var1);

    public boolean isHurtTintEnabled();

    public void setHurtTintEnabled(boolean var1);

    public boolean isGeneralTintEnabled();

    public void setGeneralTintEnabled(boolean var1);

    public int getHurtTint();

    public void setHurtTint(int var1);

    public int getGeneralTint();

    public void setGeneralTint(int var1);

    public int getGeneralAlpha();

    public void setGeneralAlpha(int var1);
}

