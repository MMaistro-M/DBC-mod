/*
 * Decompiled with CFR 0.152.
 */
package noppes.npcs.controllers.data;

public class EffectKey {
    private final int id;
    private final int index;

    public EffectKey(int key1, int key2) {
        this.id = key1;
        this.index = key2;
    }

    public int getId() {
        return this.id;
    }

    public int getIndex() {
        return this.index;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EffectKey)) {
            return false;
        }
        EffectKey that = (EffectKey)o;
        return this.id == that.id && this.index == that.index;
    }

    public int hashCode() {
        return 31 * this.id + this.index;
    }

    public String toString() {
        return "EffectKey{key1=" + this.id + ", key2=" + this.index + '}';
    }
}

