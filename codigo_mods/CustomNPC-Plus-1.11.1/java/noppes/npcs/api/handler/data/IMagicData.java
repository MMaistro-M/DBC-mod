/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.api.handler.data;

public interface IMagicData {
    public void removeMagic(int var1);

    public boolean hasMagic(int var1);

    public void clear();

    public boolean isEmpty();

    public void addMagic(int var1, float var2, float var3);

    public float getMagicDamage(int var1);

    public float getMagicSplit(int var1);
}

